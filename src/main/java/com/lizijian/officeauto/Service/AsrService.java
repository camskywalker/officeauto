package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.AsrMapper;
import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import com.tencent.cloud.asr.offline.sdk.model.OasrBytesRequest;
import com.tencent.cloud.asr.offline.sdk.model.OasrResponse;
import com.tencent.cloud.asr.realtime.sdk.config.AsrBaseConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    OasrRequesterSender oasrRequesterSender;

    public void writeAsrCallbackResponse(Integer requestId, String text) {
        asrMapper.writeAsrCallbackResponse(requestId, text);
    }

    public void createAsrTask(String Uuid, Integer userId, Integer requestId) {
        asrMapper.createAsrTask(Uuid, userId, requestId);
    }

    public Integer postTencentCloudAsr(String mp3Url, String callBackUrl) {
        OasrBytesRequest oasrBytesRequest = new OasrBytesRequest(callBackUrl, mp3Url);
        OasrResponse oasrResponse = oasrRequesterSender.send(oasrBytesRequest);
        //返回任务Id
        return Integer.valueOf(oasrResponse.getRequestId());
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
