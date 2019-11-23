package com.lizijian.officeauto;

import com.lizijian.officeauto.pojo.AuthenticateResources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    RedisTemplate<Integer, AuthenticateResources> authenticateResourcesRedisTemplate;

    @Test
    public void  RedisTemplateTest(){
        AuthenticateResources authenticateResources = new AuthenticateResources();
        HashSet<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        authenticateResources.setStuffIdSet(integers);
        authenticateResources.setCourseIdSet(integers);
        authenticateResources.setKnowledgePointIdSet(integers);
        authenticateResourcesRedisTemplate.opsForValue().set(1,authenticateResources);
        AuthenticateResources authenticateResources1 = authenticateResourcesRedisTemplate.opsForValue().get(1);
        System.out.println(authenticateResources1);
        integers.add(4);
        authenticateResourcesRedisTemplate.opsForValue().set(1,authenticateResources);
        AuthenticateResources authenticateResources2 = authenticateResourcesRedisTemplate.opsForValue().get(1);
        System.out.println(authenticateResources2);
    }
}
