package com.lizijian.officeauto;

import com.lizijian.officeauto.Service.AsrService;
import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import com.tencent.cloud.asr.offline.sdk.model.OasrBytesRequest;
import com.tencent.cloud.asr.offline.sdk.model.OasrResponse;
import com.tencent.cloud.asr.realtime.sdk.config.AsrBaseConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsrControllerTest {

    private String mp3Url = "http://flashworking.cn:8088/radio/test.mp3";
    private String callBackUrl = "http://flashworking.cn:8085";


    @Autowired
    AsrService asrService;
    @Test
    public void Test(){
        System.out.println(asrService.getAsrResultText("7083c627-b029-4692-85e5-0097224b4487"));
    }
    @Test
    public void mapperTest(){
        asrService.writeAsrCallbackResponse(587851069, "test");
    }
}
