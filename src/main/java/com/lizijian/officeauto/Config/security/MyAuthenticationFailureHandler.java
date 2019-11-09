package com.lizijian.officeauto.Config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        WebApiResult webApiResult = new WebApiResult();
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException){
            webApiResult.isErr();
            webApiResult.setMsg("用户名或密码输入错误！！！");
        }else{
            webApiResult.isErr();
            webApiResult.setMsg("登陆失败！！！");
        }
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        PrintWriter responseWriter = httpServletResponse.getWriter();
        responseWriter.write(jsonObjectMapper.writeValueAsString(webApiResult));
        responseWriter.flush();
        responseWriter.close();
    }
}
