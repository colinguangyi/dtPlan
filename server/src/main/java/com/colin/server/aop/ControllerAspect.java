package com.colin.server.aop;

import com.colin.server.response.BaseResponse;
import com.colin.server.util.IpUtils;
import com.colin.server.util.ReflectUtil;
import com.colin.server.util.StringSerialize;
import com.colin.server.util.IdUtil;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * controller切面
 * 用于监听请求与返回
 * 将order设置为最先执行
 * @author zhaolz
 */
@Aspect
@Component
@Order(1)
public class ControllerAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private static final String TRANS_ID = "transId";
    private static final String CLIENT_IP = "clientIp";

    /**
     * 切入点表达式
     */
    @Pointcut("execution(public * com.colin.server.controller..*.*(..)) ")
    public void pointcut(){}

    @Around("pointcut()")
    public BaseResponse processBefore(ProceedingJoinPoint joinPoint){
        BaseResponse response = new BaseResponse();
        String transId = null;
        String ip = null;
        String requestUri = null;
        try{
            //获取相关参数transId,并写入当前线程日志
            Object args = null;
            Object[] argsArray = joinPoint.getArgs();
            if(argsArray != null && argsArray.length > 0) {
                args = argsArray[0];
                Object fieldValue = ReflectUtil.getFieldValue(args, TRANS_ID);
                transId = fieldValue != null ? fieldValue.toString(): IdUtil.getUUID();
            }
            MDC.put(TRANS_ID, StringSerialize.nullRemove(transId));
            //获取clientIp及请求uri
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(attributes != null){
                HttpServletRequest request = attributes.getRequest();
                requestUri = request.getRequestURI();
                ip = IpUtils.getClientIp(request);
            }
            MDC.put(CLIENT_IP, StringSerialize.nullRemove(ip));

            logger.info("servera_request[{}]:{}", StringSerialize.nullRemove(requestUri),StringSerialize.nullRemove(args));

            response = (BaseResponse) joinPoint.proceed();

            logger.info("servera_response:{}", StringSerialize.nullRemove(response));

        }catch (Throwable t){
            response.sysError();
            logger.error("ControllerAspect_error:" + t.getMessage(), t);
        }finally {
            MDC.clear();
        }
        return response;
    }

}
