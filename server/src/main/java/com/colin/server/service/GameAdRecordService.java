package com.colin.server.service;

import com.colin.server.request.RecordSaveRequest;
import com.colin.server.request.RecordUpdateRequest;

/**
 * @author zhaolz
 */
public interface GameAdRecordService {
    /**
     * 保存记录
     * @param request 记录
     * @return 保存记录数
     * @throws Exception 抛出异常
     */
    Long saveAd(RecordSaveRequest request) throws Exception;

    /**
     * 更改记录状态
     * @param request 更新体
     * @return 更新记录数
     * @throws Exception 抛出异常
     */
    int updateSate(RecordUpdateRequest request) throws Exception;
}
