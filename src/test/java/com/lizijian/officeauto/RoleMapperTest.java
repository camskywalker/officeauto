package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void getItemTest(){
        System.out.println(roleMapper.getItem(19, 4));
    }

    @Test
    public void getUserRoleByUserIdTest(){
        System.out.println(roleMapper.getUserRoleByUserId(24));
    }

    @Test
    public void deleteUserRoleTest(){
        roleMapper.deleteUserRole(24,2);
    }
}
