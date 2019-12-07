package com.lizijian.officeauto.Config.security;

import com.lizijian.officeauto.Service.UserService;

import com.lizijian.officeauto.utils.JwtTool;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    JwtTool jwtTool;

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;

    @Bean
    PasswordEncoder passwordEncoder() {
        //暂时关闭密码家加密
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests().anyRequest().permitAll();
        httpSecurity
                .csrf().disable()
                .cors().and()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/asr/upload").hasRole("admin")
                .antMatchers("/asr/callback").permitAll()
                .antMatchers("/asr/result/*").hasRole("admin")
                .antMatchers("/users/**").hasRole("admin")
                .antMatchers("/courses/*").hasRole("admin")
                .antMatchers("/courses/*/finished/*").hasRole("admin")
                .antMatchers("/courses/groupbyuserid/*").hasRole("admin")
                .antMatchers("/courses/*/yesterday").hasRole("admin")
                .antMatchers("/courses/groupbyuseridfromknowledgepoint/*").hasAnyRole("admin", "teacheditor", "videoeditor", "teacher")
                .antMatchers("/major/**").hasRole("admin")
                .antMatchers(HttpMethod.GET, "/knowledgepoints/**").hasAnyRole("admin", "teacheditor", "videoeditor", "teacher")
                .antMatchers(HttpMethod.PUT, "/knowledgepoints/**").hasAnyRole("admin", "teacheditor", "videoeditor", "teacher")
                .antMatchers(HttpMethod.POST, "/knowledgepoints/**").hasRole("admin")
                .antMatchers(HttpMethod.DELETE, "/knowledgepoints/**").hasRole("admin")
                .and()
                .addFilter(new JwtVerifyFilter(super.authenticationManager(), this.jwtTool, this.resourcesAuthenticateUtils))
                .addFilter(new MyJwtUsernamePasswordAuthenticationFilter(super.authenticationManager(), this.jwtTool))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
