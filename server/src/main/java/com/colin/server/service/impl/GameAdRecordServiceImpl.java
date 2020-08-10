package com.colin.server.service.impl;

import com.colin.server.mapper.GameAdRecordMapper;
import com.colin.server.mapper.GetTableSequenceMapper;
import com.colin.server.service.GameAdRecordService;
import com.colin.server.entity.GameAdRecordPO;
import com.colin.server.exception.ServerAException;
import com.colin.server.request.RecordSaveRequest;
import com.colin.server.request.RecordUpdateRequest;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaolz
 */
@Service
public class GameAdRecordServiceImpl implements GameAdRecordService {

    @Resource
    private GameAdRecordMapper gameAdRecordMapper;
    @Resource
    private GetTableSequenceMapper getTableSequenceMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Long saveAd(RecordSaveRequest request) throws Exception{
        GameAdRecordPO saves = new GameAdRecordPO();
        saves.setAdId(request.getAddId());
        saves.setNumberId(request.getNumberId());
        saves.setPlatformId(request.getPlatformId());
        saves.setRecordTime(new Date());
        saves.setId(getTableSequenceMapper.initGameAdRecordSeq());
        saves.setState(0);
        int save = gameAdRecordMapper.insert(saves);
        if(save < 1){
            throw new ServerAException("插入失败");
        }
        return saves.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int updateSate(RecordUpdateRequest request) throws Exception{
        GameAdRecordPO update = new GameAdRecordPO();
        update.setId(request.getRecordId());
        update.setState(request.getState());
        int raws = gameAdRecordMapper.updateByPrimaryKey(update);
        if(raws < 1){
            throw new ServerAException("未找到需要更新的记录");
        }
        return raws;
    }
}
