package com.lizijian.officeauto;

import com.lizijian.officeauto.Service.AsrService;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskRequest;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskResponse;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsrControllerTest {

    private String mp3Url = "http://flashworking.cn:8088/radio/2ec2b8e7-d205-4f74-9a00-2f54f30f34fe.mp3";
    private String callBackUrl = "http://flashworking.cn:8080/asr/callback";


    @Autowired
    AsrService asrService;

    @Autowired
    AsrClient asrClient;

    @Test
    public void Test() throws TencentCloudSDKException {
        String params = "{" +
                "\"EngineModelType\":\"8k_0\"," +
                "\"ChannelNum\":1," +
                "\"ResTextFormat\":0," +
                "\"CallbackUrl\":\""+callBackUrl+"\","+
                "\"SourceType\":0," +
                "\"Url\":\""+ mp3Url+"\"}";
        String string = "{\"EngineModelType\":\"8k_0\"," +
                "\"ChannelNum\":1," +
                "\"ResTextFormat\":0," +
                "\"CallbackUrl\":\"" + callBackUrl + "\"," +
                "\"SourceType\":0," +
                "\"Url\":\"" + mp3Url + "\"}";
        System.out.println(string);
//        CreateRecTaskRequest req = CreateRecTaskRequest.fromJsonString(params, CreateRecTaskRequest.class);
//        CreateRecTaskResponse response = asrClient.CreateRecTask(req);
//        System.out.println(response.getRequestId());
//        System.out.println(response.toString());
//        System.out.println(response.getData().getTaskId());
    }
    @Test
    public void mapperTest(){
        asrService.writeAsrCallbackResponse(587851069, "test");
    }
}
