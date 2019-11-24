package com.lizijian.officeauto;


import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import com.tencent.cloud.asr.offline.sdk.model.OasrBytesRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeechRecognitionTest {
    String mp3Url = "http://flashworking.cn:8088/radio/test.mp3";
    String callBackUrl = "";

    @Test
    public void test() {
        OasrRequesterSender oasrRequesterSender = new OasrRequesterSender();
        OasrBytesRequest oasrBytesRequest = new OasrBytesRequest(callBackUrl, mp3Url);
    }
}
