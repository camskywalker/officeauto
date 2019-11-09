package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.UrlMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlTest {

    @Autowired
    UrlMapper urlMapper;

    @Test
    public void getUrlTest(){
        System.out.println(urlMapper.getUrl());
    }
}
