package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.AsrMapper;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskRequest;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskResponse;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class AsrService {

    @Autowired
    AsrMapper asrMapper;

    @Autowired
    AsrClient asrClient;

    public void writeAsrCallbackResponse(Integer requestId, String text) {
        asrMapper.writeAsrCallbackResponse(requestId, text);
    }

    public void createAsrTask(String Uuid, Integer userId, Integer requestId) {
        asrMapper.createAsrTask(Uuid, userId, requestId);
    }

    public Integer postTencentCloudAsr(String mp3Url, String callBackUrl) throws TencentCloudSDKException {
        String params =
                "{\"EngineModelType\":\"8k_0\"," +
                "\"ChannelNum\":1," +
                "\"ResTextFormat\":0," +
                "\"CallbackUrl\":\"" + callBackUrl + "\"," +
                "\"SourceType\":0," +
                "\"Url\":\"" + mp3Url + "\"}";
        CreateRecTaskRequest req = CreateRecTaskRequest.fromJsonString(params, CreateRecTaskRequest.class);
        CreateRecTaskResponse response = asrClient.CreateRecTask(req);
        return response.getData().getTaskId().intValue();
    }

    public String filePersistence(MultipartFile file, String path) throws IOException {
        String Uuid = UUID.randomUUID().toString();
        String fileName = Uuid + ".mp3";
        Files.write(Paths.get(path + fileName), file.getBytes());
        //返回唯一Uuid
        return Uuid;
    }

    public String getAsrResultText(String uuid) {
        return asrMapper.getAsrResultText(uuid);
    }
}
