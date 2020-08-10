package com.colin.servera;

import com.colin.server.entity.GameAdRecordPO;
import com.colin.server.mapper.GameAdRecordMapper;
import com.colin.server.mapper.GetTableSequenceMapper;
import com.colin.server.util.JedisUtil;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest// 指定启动类
public class SpringTest {
    @Resource
    private GameAdRecordMapper gameAdRecordMapper;
    @Resource
    private GetTableSequenceMapper getTableSequenceMapper;

    @Test
    public void test1(){
        GameAdRecordPO gameAdRecordPO = new GameAdRecordPO();
        gameAdRecordPO.setId(getTableSequenceMapper.initGameAdRecordSeq());
        gameAdRecordPO.setAdId("2222");
        gameAdRecordPO.setNumberId(1234343L);
        gameAdRecordPO.setPlatformId(123211232113123L);
        gameAdRecordPO.setRecordTime(new Date());
        gameAdRecordPO.setSlwId(56920L);
        gameAdRecordPO.setState(1);
        int i = gameAdRecordMapper.insert(gameAdRecordPO);
        System.out.println(i);
    }

    @Test
    public void test2(){
        JedisUtil.setNxEx("test_ex_nx", "1", 60);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
