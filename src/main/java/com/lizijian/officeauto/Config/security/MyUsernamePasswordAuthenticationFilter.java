package com.lizijian.officeauto.Config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String JwtToken = "secretTest";
    private AuthenticationManager authenticationManager;

    public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            String password = user.getPassword();
            String username = user.getUsername();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, usernamePasswordAuthenticationToken);
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            response.setStatus(400);
            throw new UsernameNotFoundException("json信息为空！");
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {
        response.addHeader("Authentication", this.JwtToken);
        response.setStatus(response.SC_OK);
        response.setHeader("content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("认证通过，颁发token");
        writer.write(new ObjectMapper().writeValueAsString(webApiResult));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(response.SC_OK);
        response.setHeader("content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isErr();
        webApiResult.setMsg("认证失败，请核对用户名和密码");
        writer.write(new ObjectMapper().writeValueAsString(webApiResult));
        writer.flush();
        writer.close();
    }
}
