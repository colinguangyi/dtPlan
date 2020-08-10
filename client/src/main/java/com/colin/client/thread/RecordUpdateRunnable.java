package com.colin.client.thread;

import com.colin.server.feignservice.RecordService;
import com.colin.server.request.RecordUpdateRequest;

/**
 * @author zhaolz
 */
public class RecordUpdateRunnable implements Runnable{

    private final RecordService recordService;
    private final RecordUpdateRequest updateRequest;

    public RecordUpdateRunnable(RecordService recordService, RecordUpdateRequest updateRequest) {
        this.recordService = recordService;
        this.updateRequest = updateRequest;
    }

    @Override
    public void run() {
        recordService.updateRecord(updateRequest);
    }
}
