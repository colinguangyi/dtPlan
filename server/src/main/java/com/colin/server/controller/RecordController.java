package com.colin.server.controller;

import com.colin.server.exception.ServerAException;
import com.colin.server.request.RecordSaveRequest;
import com.colin.server.request.RecordUpdateRequest;
import com.colin.server.response.BaseResponse;
import com.colin.server.response.RecordSaveResponse;
import com.colin.server.response.RecordUpdateResponse;
import com.colin.server.service.GameAdRecordService;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaolz
 */
@RestController
public class RecordController {
    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Resource
    private GameAdRecordService gameAdRecordService;

    @PostMapping("/server/a/saveRecord")
    public BaseResponse saveRecord(@RequestBody RecordSaveRequest request){
        BaseResponse response = new BaseResponse(request.getTransId());
        RecordSaveResponse data = new RecordSaveResponse();
        response.success(data);
        try{
            checkParam(request);

            Long aLong = gameAdRecordService.saveAd(request);
            data.setRecordId(aLong);

        }catch (ServerAException se){
            response.busiError(se);
        } catch (Exception e){
            response.sysError();
            logger.error("saveRecord_error:" + e.getMessage(), e);
        }

        return response;
    }

    @PostMapping("/server/a/updateRecord")
    public BaseResponse updateRecord(@RequestBody RecordUpdateRequest request){
        BaseResponse response = new BaseResponse(request.getTransId());
        RecordUpdateResponse data = new RecordUpdateResponse();
        response.success(data);
        try{
            checkParam(request);

            int raws = gameAdRecordService.updateSate(request);
            data.setRaws(raws);

        }catch (ServerAException se){
            response.busiError(se);
        } catch (Exception e){
            response.sysError();
            logger.error("saveRecord_error:" + e.getMessage(), e);
        }

        return response;
    }

    private void checkParam(RecordSaveRequest request) throws ServerAException {
        if(StringUtils.isBlank(request.getAddId())){
            throw new ServerAException("addId不能为空");
        }
        if(request.getNumberId() == null){
            throw new ServerAException("numberId不能为空");
        }
        if(request.getPlatformId() == null){
            throw new ServerAException("platformIdId不能为空");
        }
        if(request.getSlwId() == null){
            throw new ServerAException("slwId不能为空");
        }
    }

    private void checkParam(RecordUpdateRequest request) throws ServerAException {
        if(request.getRecordId() == null){
            throw new ServerAException("recordId不能为空");
        }
        if(request.getState() == null){
            throw new ServerAException("state不能为空");
        }
    }
}
