package com.lizijian.officeauto.Config;

import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import com.tencent.cloud.asr.realtime.sdk.config.AsrBaseConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrGlobelConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsrConfig {
    @Bean
    OasrRequesterSender oasrRequesterSender(){
        OasrRequesterSender oasrRequesterSender = new OasrRequesterSender();
        AsrBaseConfig.appId = "1259377876";
        AsrBaseConfig.secretId = "AKIDHYZJo3ijAMKehU6IJ8g182h5OjpfsZGx";
        AsrBaseConfig.secretKey = "ax832mNmyvc2IIJ5ctugCKC4i7vTmoaZ";
        AsrInternalConfig.SUB_SERVICE_TYPE = 0;
        return oasrRequesterSender;
    }
}
