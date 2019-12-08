package com.lizijian.officeauto.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AsrConfig {

    @Autowired
    private Environment environment;

    @Bean
    AsrClient asrClient(){
        Credential cred = new Credential(environment.getProperty("tencent.cloud.secretId"), environment.getProperty("tencent.cloud.secretKey"));
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("asr.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new AsrClient(cred,  "ap-beijing", clientProfile);
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
