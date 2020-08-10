package com.colin.server.aop;

import com.colin.server.enums.StatusEnum;
import com.colin.server.exception.ServerAException;
import com.colin.server.handle.AsyncTransactionHandle;
import com.colin.server.request.BaseRequest;
import com.colin.server.util.JedisUtil;
import com.colin.server.util.ASyncTransConstants;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 异步事务处理切面
 * 不加order属性，使当前切面执行在事务之内
 * @author zhaolz
 */
@Aspect
@Component
public class AsyncTransactionalAspect {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTransactionalAspect.class);

    @Resource
    private AsyncTransactionHandle asyncTransactionHandle;

    /**
     * 切入点表达式
     */
    @Pointcut("execution(public * com.colin.server.service.impl..*.*(..)) ")
    public void pointcut(){}

    /**
     * 环绕通知
     * 此方法执行在事务之内，不可吃掉异常
     */
    @Around("pointcut()")
    public Object processBefore(ProceedingJoinPoint joinPoint) throws Exception{
        Object result = null;
        //当前事务ID
        String transId = null;
        //其他关联事务ID
        String relateTransIds = null;
        try{
            //获取事务相关参数
            Object args = null;
            Object[] argsArray = joinPoint.getArgs();
            if(argsArray != null && argsArray.length > 0) {
                args = argsArray[0];
                if(args instanceof BaseRequest){
                    BaseRequest request = (BaseRequest)args;
                    transId = request.getTransId();
                    relateTransIds = request.getRelateTransIds();
                }
            }

            result = joinPoint.proceed();

            curSuccessDeal(transId, relateTransIds);

        }catch (Exception e){
            curFailDeal(transId, relateTransIds);
            throw e;
        } catch (Throwable t){
            curFailDeal(transId, relateTransIds);
            logger.error("AsyncTransactionalAspect_error:", t);
            throw new ServerAException(StatusEnum.ST_500.getCode(), "当前方法异常");
        }
        return result;
    }

    /**
     * 当前事务成功后处理
     * @param transId 当前事务ID
     * @param relateTransIds 其他关联事务ID
     * @throws Exception 抛出异常
     */
    private void curSuccessDeal(String transId, String relateTransIds) throws Exception{
        if(StringUtils.isNotBlank(transId) && StringUtils.isNotBlank(relateTransIds)){
            //将当前事务标记为成功
            JedisUtil.setEx(transId, ASyncTransConstants.TRANS_DONE, ASyncTransConstants.EXPIRE_TIME);
            //进行异步事务判断处理
            asyncTransactionHandle.handle(relateTransIds);
        }
    }

    /**
     * 当前事务失败处理
     * @param transId 当前事务ID
     * @throws Exception 抛出异常
     */
    private void curFailDeal(String transId, String relateTransIds) throws Exception{
        if(StringUtils.isNotBlank(transId) && StringUtils.isNotBlank(relateTransIds)){
            JedisUtil.del(transId);
        }
    }

}
