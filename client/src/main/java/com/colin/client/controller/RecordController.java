package com.colin.client.controller;

import com.colin.client.thread.RecordSaveRunnable;
import com.colin.client.thread.RecordUpdateRunnable;
import com.colin.client.thread.ThreadPool;
import com.colin.client.util.JedisUtil;
import com.colin.server.feignservice.RecordService;
import com.colin.server.request.RecordSaveRequest;
import com.colin.server.request.RecordUpdateRequest;
import com.colin.server.util.AsyncTransConstants;
import com.colin.server.util.IdUtil;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaolz
 */
@RestController
public class RecordController {

    @Resource
    private RecordService recordService;

    @GetMapping("/client/record")
    public Object record(){
        //创建异步事务标志
        String transId1 = IdUtil.getUUID();
        String transId2 = IdUtil.getUUID();
        JedisUtil.setEx(transId1, AsyncTransConstants.TRANS_READY, AsyncTransConstants.EXPIRE_TIME);
        JedisUtil.setEx(transId2, AsyncTransConstants.TRANS_READY, AsyncTransConstants.EXPIRE_TIME);

        //事务一
        RecordSaveRequest saveRequest = new RecordSaveRequest();
        saveRequest.setTransId(transId1);
        saveRequest.setRelateTransIds(transId2);
        saveRequest.setAddId("12321321");
        saveRequest.setNumberId(111L);
        saveRequest.setPlatformId(11111111L);
        saveRequest.setSlwId(111L);
        RecordSaveRunnable saveRunnable = new RecordSaveRunnable(recordService, saveRequest);

        //事务二
        RecordUpdateRequest updateRequest = new RecordUpdateRequest();
        updateRequest.setTransId(transId2);
        updateRequest.setRelateTransIds(transId1);
        updateRequest.setRecordId(101L);
        updateRequest.setState(2);
        RecordUpdateRunnable updateRunnable = new RecordUpdateRunnable(recordService, updateRequest);

        //放入线程池进行异步操作
        ThreadPool threadPool = ThreadPool.getInstance();
        threadPool.putThread(saveRunnable);
        threadPool.putThread(updateRunnable);

        //判断最终结果
        String result1;
        String result2;
        while(true){
            result1 = JedisUtil.get(transId1);
            result2 = JedisUtil.get(transId2);
            if(StringUtils.isBlank(result1) && StringUtils.isBlank(result2)){
                return "FAIL";
            }
            if(AsyncTransConstants.TRANS_DONE.equals(result1) && AsyncTransConstants.TRANS_DONE.equals(result2)){
                return "SUCCESS";
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
