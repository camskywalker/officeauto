package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.RoleService;
import com.lizijian.officeauto.mapper.RoleMapper;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/user_role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @DeleteMapping
    public WebApiResult deleteUserRole(HttpServletRequest request){
        Integer rid = Integer.valueOf(request.getParameter("rid"));
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        return roleService.deleteUserRole(uid, rid);
    }

    @PostMapping
    public WebApiResult insertUserRole(Integer uid, Integer rid){
        if (uid == (null) || rid ==null){
            WebApiResult webApiResult = new WebApiResult();
            webApiResult.isNull();
            webApiResult.setMsg("参数为空!!!");
            return webApiResult;
        }
        return roleService.insertUserRole(uid, rid);
    }


}
