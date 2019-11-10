package com.lizijian.officeauto.Config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.pojo.WebApiResult;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtVerifyFilter  extends BasicAuthenticationFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null) {
            //如果未登陆
            response.setHeader("content-type","application/json;charset=UTF-8");
            response.setStatus(response.SC_FORBIDDEN);
            PrintWriter writer = response.getWriter();
            WebApiResult webApiResult = new WebApiResult();
            webApiResult.isErr();
            webApiResult.setMsg("请登陆！");
            writer.write(new ObjectMapper().writeValueAsString(webApiResult));
            writer.flush();
            writer.close();
            chain.doFilter(request, response);
        } else {
            //测试判断
            if (header.equals("secretTest") ){
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken("liumin", "12345");
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            }

        }
    }

    public JwtVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
}
