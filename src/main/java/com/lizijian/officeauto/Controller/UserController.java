package com.lizijian.officeauto.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.Service.UserService;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;

    @PostMapping
    public WebApiResult insertUser(HttpServletRequest request, @RequestBody Map<String, String> jsonMap) {
        User user = new User();
        user.setUsername(jsonMap.get("username"));
        user.setEmail(jsonMap.get("email"));
        user.setPassword(jsonMap.get("email").substring(0, 5));
        user.setAdminId(((User) request.getAttribute("user")).getId());
        ArrayList<Role> roleList = new ArrayList<>();
        Integer roleID = Integer.valueOf(jsonMap.get("roleId"));
        Role role = new Role();
        role.setId(roleID);
        roleList.add(role);
        user.setRoles(roleList);
        return userService.insertUser(user);
    }

    @GetMapping("/{userId}")
    //职能自己获取自己的用户信息
    public WebApiResult getUser(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("userId") Integer userId) {
        User user = (User) request.getAttribute("user");
        if (user.getId().equals(userId)) {
            return userService.getUserByUserId(userId);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

    }

    @GetMapping("/groupbyadminid/{adminId}")
    public WebApiResult getUsersByAdminId(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @PathVariable("adminId") Integer adminId) {
        User user = (User) request.getAttribute("user");
        if (user.getId().equals(adminId)) {
            return userService.getUsersByAdminId(adminId);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @GetMapping("/groupbycourseid/{courseId}")
    public WebApiResult getUserByCourseId(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @PathVariable("courseId") Integer courseId) {
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
            return userService.getUserByCourseId(courseId);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @DeleteMapping("/{userId}")
    public WebApiResult deleteUser(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @PathVariable("userId") Integer userId) {
        if (resourcesAuthenticateUtils.assertStuffInAuthenticateResources(request, userId)){
            return userService.deleteUserByUserId(userId);
        }else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @PutMapping
    public WebApiResult updateUser(HttpServletRequest request,
                                   HttpServletResponse response,
                                   User user) {
        User userSelf = (User) request.getAttribute("user");
        if (userSelf.getId().equals(user.getId()) || resourcesAuthenticateUtils.assertStuffInAuthenticateResources(request, user.getId())){
            return userService.updateUser(user);
        }else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
}
