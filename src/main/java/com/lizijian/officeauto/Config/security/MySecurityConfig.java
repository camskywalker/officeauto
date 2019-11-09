package com.lizijian.officeauto.Config.security;


import com.lizijian.officeauto.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        //暂时关闭密码家加密
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new MyAuthenticationFailureHandler();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        return new MyAccessDecisionManager();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource metadataSource(){
        return new MyMetadataSource();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                                o.setSecurityMetadataSource(metadataSource());
                                o.setAccessDecisionManager(accessDecisionManager());
                                return o;
                            }
                        }
                )
                .and()
                .formLogin().loginProcessingUrl("/login").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(failureHandler()).successHandler(successHandler())
                .and().csrf().disable();
    }



}
