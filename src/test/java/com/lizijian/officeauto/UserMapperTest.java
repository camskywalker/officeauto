package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserByCourseIdTest(){
        System.out.println(userMapper.getUserByCourseId(1));
    }

    @Test
    public void getUserByUsername(){
        System.out.println(userMapper.getUserByUsername("liumin"));
    }

    @Test
    public void getUserByUserId(){
        System.out.println(userMapper.getUserByUserId(1));
    }

    @Test
    public void getAuthenticateResourcesByUserIdTest(){
        System.out.println(userMapper.getAuthenticateResourcesByUserId(18));
    }

    @Test
    public void getAuthenticateResourcesByAdminId(){
        System.out.println(userMapper.getAuthenticateResourcesByAdminId(1));
    }

}
