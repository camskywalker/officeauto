package com.lizijian.officeauto.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.Service.UserService;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public WebApiResult insertUser(@RequestBody Map<String, String> jsonMap){
        User user = new User();
        user.setUsername(jsonMap.get("username"));
        user.setEmail(jsonMap.get("email"));
        user.setPassword(jsonMap.get("email").substring(0,5));
        user.setAdminId(Integer.valueOf(jsonMap.get("adminId")));
        ArrayList<Role> roleList = new ArrayList<>();
        Integer roleID = Integer.valueOf(jsonMap.get("roleId"));
        Role role = new Role();
        role.setId(roleID);
        roleList.add(role);
        user.setRoles(roleList);
        return userService.insertUser(user);
    }

    @GetMapping("/{username}")
    public WebApiResult getUser(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/usergroup/{adminname}")
    public WebApiResult getUsersByAdminId(@PathVariable("adminname") String admiName){
        return userService.getUsersByAdminName(admiName);
    }

    @DeleteMapping("/{username}")
    public WebApiResult deleteUser(@PathVariable("username") String username){
        return userService.deleteUserByUserName(username);
    }

    @PutMapping
    public WebApiResult updataUser(User user){
        return userService.updateUser(user);
    }


}
