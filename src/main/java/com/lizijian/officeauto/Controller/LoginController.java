package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {

    @GetMapping("/logintest")
    public WebApiResult login(){
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.setMsg("请登录");
        return webApiResult;
    }
}
