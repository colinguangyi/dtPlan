package com.colin.server.feignservice;

import com.colin.server.request.RecordSaveRequest;
import com.colin.server.request.RecordUpdateRequest;
import com.colin.server.feignservice.fallback.RecordServiceImpl;
import com.colin.server.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhaolz
 */
@Component
@FeignClient(value = "server", fallback = RecordServiceImpl.class)
public interface RecordService {
    /**
     * 保存记录
     */
    @PostMapping("/server/a/saveRecord")
    public BaseResponse saveRecord(RecordSaveRequest request);

    /**
     * 更新记录
     */
    @PostMapping("/server/a/updateRecord")
    public BaseResponse updateRecord(@RequestBody RecordUpdateRequest request);

}
