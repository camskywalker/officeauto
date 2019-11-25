package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.AsrService;
import com.lizijian.officeauto.pojo.OasrCallBackResponse;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/asr")
public class AsrController {

    @Autowired
    AsrService asrService;

    @PostMapping
    public WebApiResult createAsrTask(HttpServletResponse response,
                                      HttpServletRequest request,
                                      @RequestParam("file") MultipartFile audioFile) throws IOException {
        User user = (User) request.getAttribute("user");
        if (audioFile.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        } else {
            String Uuid = asrService.filePersistence(audioFile);
            String callBackUrl = request.getRequestURL().toString() + "/callback";
            String resourceUrl = "http://flashworking.cn:8088/radio/" + Uuid + ".mp3";
            System.out.println(resourceUrl);
            Integer requestID = asrService.postTencentCloudAsr(resourceUrl, callBackUrl);
            asrService.createAsrTask(Uuid, 1, requestID);
            WebApiResult webApiResult = new WebApiResult();
            webApiResult.isOk();
            webApiResult.setMsg("上传成功，识别中……");
            webApiResult.setData(Uuid);
            return webApiResult;
        }
    }

    @PostMapping("/callback")
    public void callback( HttpServletResponse response,
                         @RequestParam("code") Integer code,
                         @RequestParam("requestId") Integer requestId,
                         @RequestParam("appid") Integer appid,
                         @RequestParam("projectid") Integer projectid,
                         @RequestParam("text") String text,
                         @RequestParam("audioUrl") String audioUrl,
                         @RequestParam("audioTime") Double audioTime,
                         @RequestParam("message") String message
    ) throws IOException {
        OasrCallBackResponse oasrCallBackResponse = new OasrCallBackResponse(
                code, requestId, appid, projectid,
                URLDecoder.decode(text, StandardCharsets.UTF_8),
                URLDecoder.decode(audioUrl, StandardCharsets.UTF_8),
                audioTime,
                URLDecoder.decode(message, StandardCharsets.UTF_8)
        );
        asrService.writeAsrCallbackResponse(oasrCallBackResponse.getRequestId(), oasrCallBackResponse.getText());
        PrintWriter writer = response.getWriter();
        writer.write("{ \"code\" : 0, \"message\" : \"success\" }");
        response.setCharacterEncoding("UTF-8");
        writer.flush();
        writer.close();
    }

    @GetMapping("/{uuid}")
    public WebApiResult getAsrResult(@PathVariable("uuid") String Uuid) {
        String asrResultText = asrService.getAsrResultText(Uuid);
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("识别成功");
        webApiResult.setData(asrResultText);
        return webApiResult;
    }
}

