package com.lizijian.officeauto.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import com.tencent.cloud.asr.realtime.sdk.config.AsrBaseConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrGlobelConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AsrConfig {

    @Autowired
    private Environment environment;

    @Bean
    OasrRequesterSender oasrRequesterSender(){
        OasrRequesterSender oasrRequesterSender = new OasrRequesterSender();
        AsrBaseConfig.appId = environment.getProperty("tencent.cloud.appId");
        AsrBaseConfig.secretId = environment.getProperty("tencent.cloud.secretId");
        AsrBaseConfig.secretKey = environment.getProperty("tencent.cloud.secretKey");
        AsrInternalConfig.SUB_SERVICE_TYPE = 0;
        return oasrRequesterSender;
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
