package com.lizijian.officeauto.Config.security;


import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.utils.JwtTool;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JwtVerifyFilter extends BasicAuthenticationFilter {

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;

    public JwtVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            chain.doFilter(request, response);
        } else {
            try {
                //判断token
                User user = JwtTool.parseJwt(authorizationHeader);
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRoles().get(0).getName()));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(user.getId(),null, authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                request.setAttribute("user", user);
                chain.doFilter(request, response);
            }catch (Exception e){
                chain.doFilter(request, response);
            }

        }
    }
}
