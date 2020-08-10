package com.colin.server.feignservice.fallback;

import com.colin.server.feignservice.RecordService;
import com.colin.server.request.RecordSaveRequest;
import com.colin.server.request.RecordUpdateRequest;
import com.colin.server.response.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * @author zhaolz
 */
@Component
public class RecordServiceImpl implements RecordService {

    @Override
    public BaseResponse saveRecord(RecordSaveRequest request) {
        return BaseResponse.fallback(request);
    }

    @Override
    public BaseResponse updateRecord(RecordUpdateRequest request) {
        return BaseResponse.fallback(request);
    }
}
