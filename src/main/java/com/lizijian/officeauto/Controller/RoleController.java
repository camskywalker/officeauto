package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.RoleService;
import com.lizijian.officeauto.mapper.RoleMapper;
import com.lizijian.officeauto.pojo.WebApiResult;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/user_role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;

    @DeleteMapping
    public WebApiResult deleteUserRole(HttpServletRequest request, HttpServletResponse response){
        Integer rid = Integer.valueOf(request.getParameter("rid"));
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        if (resourcesAuthenticateUtils.assertStuffInAuthenticateResources(request,uid)){
            return roleService.deleteUserRole(uid, rid);
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @PostMapping
    public WebApiResult insertUserRole(HttpServletRequest request, HttpServletResponse response, Integer uid, Integer rid){
        if (uid == (null) || rid ==null){
            WebApiResult webApiResult = new WebApiResult();
            webApiResult.isNull();
            webApiResult.setMsg("参数为空!!!");
            return webApiResult;
        }
        if (resourcesAuthenticateUtils.assertStuffInAuthenticateResources(request,uid)){
            return roleService.insertUserRole(uid, rid);
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return null;
    }

}
