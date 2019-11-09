package com.lizijian.officeauto.Config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        WebApiResult webApiResult = new WebApiResult();
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        PrintWriter responseWriter = httpServletResponse.getWriter();
        webApiResult.isOk();
        webApiResult.setMsg("登陆成功!!!");
        responseWriter.write(jsonObjectMapper.writeValueAsString(webApiResult));
        responseWriter.flush();
        responseWriter.close();
    }
}
