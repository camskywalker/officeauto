package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.KnowledgePointMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KnowledgePointMapperTest {
    @Autowired
    KnowledgePointMapper knowledgePointMapper;

    @Test
    public void getKnowledgesPointByCourseIdTest(){
        System.out.println(knowledgePointMapper.getKnowledgesPointByCourseId(1));
    }

    @Test
    public void getKnowledgePointBycourseIdAndUserIdTest(){
        System.out.println(knowledgePointMapper.getKnowledgePointByCourseIdAndUserId(30, 48));
    }
}
