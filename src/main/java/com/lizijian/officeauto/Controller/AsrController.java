package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.AsrService;
import com.lizijian.officeauto.pojo.OasrCallBackResponse;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${asr.callbackUrl}")
    private String callBackUrl;

    @Value("${asr.resourceUrl}")
    private String resourceUrl;

    @Value("${asr.persistencePath}")
    private String path;

    @PostMapping("/upload")
    public WebApiResult createAsrTask(HttpServletResponse response,
                                      HttpServletRequest request,
                                      @RequestParam("file") MultipartFile file) throws IOException, TencentCloudSDKException {
        WebApiResult webApiResult = new WebApiResult();
        if (file.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            webApiResult.isErr();
            webApiResult.setMsg("文件不能为空");
        } else {
            String Uuid = asrService.filePersistence(file, this.path);
            Integer requestID = asrService.postTencentCloudAsr(this.resourceUrl + Uuid + ".mp3", this.callBackUrl);
            User user = (User) request.getAttribute("user");
            asrService.createAsrTask(Uuid, user.getId(), requestID);
            webApiResult.isOk();
            webApiResult.setMsg("上传成功，识别中……");
            webApiResult.setData(Uuid);
        }
        return webApiResult;
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
                URLDecoder.decode(this.urlDecodeFormat(text), StandardCharsets.UTF_8),
                URLDecoder.decode(this.urlDecodeFormat(audioUrl), StandardCharsets.UTF_8),
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

    @GetMapping("/result/{uuid}")
    public WebApiResult getAsrResult(@PathVariable("uuid") String Uuid) {
        String asrResultText = asrService.getAsrResultText(Uuid);
        WebApiResult webApiResult = new WebApiResult();
        if (asrResultText == null){
            webApiResult.isErr();
            webApiResult.setMsg("识别中");
            webApiResult.setData("内容识别中…………");
        } else {
            webApiResult.isOk();
            webApiResult.setData(asrResultText);
        }
        return webApiResult;
    }

    //处理urldecode参数，否则遇到单独的%和+会报错。
    private String urlDecodeFormat(String string){
        return string.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B");
    }

}

