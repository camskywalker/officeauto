package com.lizijian.officeauto.Config;

import com.lizijian.officeauto.pojo.AuthenticateResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    //设置序列化器
    @Bean
    public RedisTemplate<Integer, AuthenticateResources> redisTemplateAuthenticateResources(RedisConnectionFactory factory) {
        RedisTemplate<Integer, AuthenticateResources> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
