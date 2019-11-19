package com.lizijian.officeauto;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lizijian.officeauto.Config.security.JwtTool;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtToolTest {

    @Test
    public void generateJWTTest() throws JsonProcessingException {
        User user = new User();
        user.setUsername("离子键");
        user.setId(23);
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(1, "admin", "管理员"));
        user.setRoles(roles);
        System.out.println(JwtTool.generateJWT(user));
    }

    @Test
    public void parseJwtTest(){
        try {
            System.out.println(JwtTool.parseJwt("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoie1wiaWRcIjoxLFwibmFtZVwiOlwiYWRtaW5cIixcIm5hbWVaaFwiOlwi566h55CG5ZGYXCJ9IiwiaWQiOjIzLCJleHAiOjE1NzM5NzUxMjcsInVzZXJuYW1lIjoi56a75a2Q6ZSuIn0.B6mRmJ55UjJxLKpNXyWUBXM00Ge20TR0T7j32b20WzQ"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
