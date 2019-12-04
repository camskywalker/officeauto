package com.lizijian.officeauto.Config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import com.lizijian.officeauto.utils.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyJwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    JwtTool jwtTool;

    private AuthenticationManager authenticationManager;

    public MyJwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtTool jwtTool) {
        this.authenticationManager = authenticationManager;
        this.jwtTool = jwtTool;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            User user = null;
            try {
                user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert user != null;
            String password = user.getPassword();
            String username = user.getUsername();
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, usernamePasswordAuthenticationToken);
            return this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }

    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        String token = jwtTool.generateJWT(user);
        response.setStatus(response.SC_OK);
        response.setHeader("content-type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("认证通过，颁发token");
        webApiResult.setData(token);
        writer.write(new ObjectMapper().writeValueAsString(webApiResult));
        writer.flush();
        writer.close();
    }
}
