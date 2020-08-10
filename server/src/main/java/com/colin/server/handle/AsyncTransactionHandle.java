package com.colin.server.handle;

import com.colin.server.properties.TransProperties;
import com.colin.server.exception.ServerAException;
import com.colin.server.util.JedisUtil;
import com.colin.server.util.AsyncTransConstants;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 异步事务处理
 * @author zhaolz
 */
@Component
public class AsyncTransactionHandle {
    @Resource
    private TransProperties transProperties;

    public void handle(String relateTransIds) throws Exception {
        String[] keys = relateTransIds.split(",");
        //记录等待次数
        int count = 0;
        //允许等待的最大次数
        int permitTimes = transProperties.waitTimes.intValue();
        while(true){
            count++;

            //校验是否处理完成
            boolean done = true;
            for(String key : keys){
                //避免数据异常，造成空key的情况
                if(StringUtils.isBlank(key)){
                    continue;
                }
                String value = JedisUtil.get(key);
                //存在失败或失效的事务，直接回滚
                if(StringUtils.isBlank(value)){
                    throw new ServerAException(key+"处理失败");
                }
                //存在未处理完成的事务，进入等待
                if(AsyncTransConstants.TRANS_READY.equals(value)){
                    done = false;
                    break;
                }
            }
            //若都成功，则退出此程序，使上层事务直接提交，否则进入等待
            if(done){
                break;
            }
            //判断等待次数，超出则回滚，否则进入等待
            if(count > permitTimes){
                throw new ServerAException("处理超时,请稍后再试");
            }
            //等待
            Thread.sleep(transProperties.perWaitTime);
        }

    }
}
