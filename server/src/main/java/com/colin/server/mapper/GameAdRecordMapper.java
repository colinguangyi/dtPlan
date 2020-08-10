package com.colin.server.mapper;

import com.colin.server.entity.GameAdRecordPO;

/**
 * @author zhaolz
 */
public interface GameAdRecordMapper {
    /**
     * 插入
     * @param gameAdRecordPo 实体
     * @return 影响行数
     */
    int insert(GameAdRecordPO gameAdRecordPo);

    /**
     * 更新
     * @param gameAdRecordPo 实体
     * @return 影响行数
     */
    int updateByPrimaryKey(GameAdRecordPO gameAdRecordPo);
}
