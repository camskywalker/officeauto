package com.lizijian.officeauto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})//暂时关闭安全验证
public class OfficeautoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeautoApplication.class, args);
    }

}
