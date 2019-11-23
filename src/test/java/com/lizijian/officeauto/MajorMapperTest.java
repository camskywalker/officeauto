package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.MajorMapper;
import com.lizijian.officeauto.pojo.Major;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorMapperTest {

    @Autowired
    MajorMapper majorMapper;

    @Test
    public void getMajorListTest(){
        System.out.println(majorMapper.getMajorList());
    }

    @Test
    public void addMajorTest(){
        majorMapper.addMajor(new Major("test"));
    }

    @Test
    public void upDateMajorNameTest(){
        majorMapper.upDateMajorName(new Major("test2",3));
    }

    @Test
    public void deleteMajorTest(){
        majorMapper.deleteMajor(3);
    }

    @Test
    public void getMajorByMajorNameTest(){
//        System.out.println(majorMapper.getMajorByMajorName("test"));
//        System.out.println(majorMapper.getMajorByMajorName("testnull"));
    }
}
