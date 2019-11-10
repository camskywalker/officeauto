package com.lizijian.officeauto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//exclude = {SecurityAutoConfiguration.class}
@SpringBootApplication
public class OfficeautoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficeautoApplication.class, args);
    }

}
