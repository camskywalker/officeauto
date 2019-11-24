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
            String callBackUrl = request.getRequestURL().toString()+"/callback";
            String resourceUrl = request.getRequestURL()+"/"+Uuid+".mp3";
            Integer requestID = asrService.postTencentCloudAsr(resourceUrl, callBackUrl);
            asrService.createAsrTask(Uuid, user.getId(), requestID);
            WebApiResult webApiResult = new WebApiResult();
            webApiResult.isOk();
            webApiResult.setMsg("上传成功，识别中……");
            webApiResult.setData(Uuid);
            return webApiResult;
        }
    }

    @PostMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OasrCallBackResponse oasrCallBackResponse = asrService.getOasrCallBackResponse(request.getInputStream());
        asrService.writeAsrCallbackResponse(oasrCallBackResponse.getRequestId(), oasrCallBackResponse.getText());
        PrintWriter writer = response.getWriter();
        writer.write("{ \"code\" : 0, \"message\" : \"success\" }");
        response.setCharacterEncoding("UTF-8");
        writer.flush();
        writer.close();
    }

    @GetMapping("/{uuid}")
    public WebApiResult getAsrResult(@PathVariable("uuid") String Uuid){
        String asrResultText = asrService.getAsrResultText(Uuid);
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("识别成功");
        webApiResult.setData(asrResultText);
        return webApiResult;
    }
}

