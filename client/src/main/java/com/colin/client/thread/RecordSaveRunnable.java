package com.colin.client.thread;

import com.colin.server.feignservice.RecordService;
import com.colin.server.request.RecordSaveRequest;

/**
 * @author zhaolz
 */
public class RecordSaveRunnable implements Runnable{

    private final RecordService recordService;
    private final RecordSaveRequest recordSaveRequest;

    public RecordSaveRunnable(RecordService service, RecordSaveRequest request){
        this.recordService = service;
        this.recordSaveRequest = request;
    }

    @Override
    public void run() {
        recordService.saveRecord(recordSaveRequest);
    }
}
