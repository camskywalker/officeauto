package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.AsrMapper;
import com.lizijian.officeauto.pojo.OasrCallBackResponse;
import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import com.tencent.cloud.asr.offline.sdk.model.OasrBytesRequest;
import com.tencent.cloud.asr.offline.sdk.model.OasrResponse;
import com.tencent.cloud.asr.realtime.sdk.config.AsrBaseConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;


@Service
public class AsrService {


    @Autowired
    AsrMapper asrMapper;

    @Autowired
    OasrRequesterSender oasrRequesterSender;

    //初始化参数
    static {
        initBaseParameters();
    }
    private static void initBaseParameters(){
        AsrBaseConfig.appId = "1259377876";
        AsrBaseConfig.secretId = "AKIDHYZJo3ijAMKehU6IJ8g182h5OjpfsZGx";
        AsrBaseConfig.secretKey = "ax832mNmyvc2IIJ5ctugCKC4i7vTmoaZ";
        AsrInternalConfig.SUB_SERVICE_TYPE = 0;
    }

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

    public OasrCallBackResponse getOasrCallBackResponse(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        String[] strings = URLDecoder.decode(stringBuilder.toString(), StandardCharsets.UTF_8).split("&");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String string : strings) {
            String[] split = string.split("=");
            hashMap.put(split[0], split[1]);
        }
        //出现空指针异常待处理
        OasrCallBackResponse oasrCallBackResponse = new OasrCallBackResponse();
        oasrCallBackResponse.setAppid(Integer.valueOf(hashMap.get("appid")));
        oasrCallBackResponse.setCode(Integer.valueOf(hashMap.get("code")));
        oasrCallBackResponse.setRequestId(Integer.valueOf(hashMap.get("requestId")));
        oasrCallBackResponse.setProjectid(Integer.valueOf(hashMap.get("projectid")));
        oasrCallBackResponse.setText(hashMap.get("text"));
        oasrCallBackResponse.setAudioUrl(hashMap.get("audioUrl"));
        oasrCallBackResponse.setAudioTime(Double.valueOf(hashMap.get("audioTime")));
        oasrCallBackResponse.setMessage(hashMap.get("message"));
        return oasrCallBackResponse;
    }

    public String filePersistence(MultipartFile file) throws IOException {
        String Uuid = UUID.randomUUID().toString();
        String fileName = Uuid + ".mp3";
        String originalPath = ResourceUtils.getURL("classpath:").getPath() + "audio/";
        //将左侧斜杠去掉，不然报错
        String path = originalPath.substring(1);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        Files.write(Paths.get(path + fileName), file.getBytes());
        //返回唯一Uuid
        return Uuid;
    }

    public String getAsrResultText(String uuid){
        return asrMapper.getAsrResultText(uuid);
    }
}
