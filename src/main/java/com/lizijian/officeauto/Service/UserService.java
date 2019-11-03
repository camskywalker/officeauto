package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.RoleMapper;
import com.lizijian.officeauto.mapper.UserMapper;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    public WebApiResult getUserByUsername(String userName){
        WebApiResult webApiResult = new WebApiResult();
        User user = userMapper.getUserByUsername(userName);
        if (user==null){
            webApiResult.isOk();
            webApiResult.setMsg("查找的用户不存在");
            return webApiResult;
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        webApiResult.isOk();
        webApiResult.setMsg("查询成功");
        webApiResult.setData(user);
        return webApiResult;
    }

    public WebApiResult insertUser(User user){
        WebApiResult webApiResult = new WebApiResult();
        if(!(userMapper.getUserByUsername(user.getUsername()) == null)){
            webApiResult.isErr();
            webApiResult.setMsg("插入的用户已存在");
            return webApiResult;
        }
        userMapper.insertUser(user);
        User insertUser = userMapper.getUserByUsername(user.getUsername());
        roleMapper.insertUserRole(insertUser.getId(), user.getRoles().get(0).getId());
        webApiResult.isOk();
        webApiResult.setMsg("新建用户成功");
        webApiResult.setData(insertUser);
        return webApiResult;
    }

    public WebApiResult deleteUserByUserId(Integer id){
        WebApiResult webApiResult = new WebApiResult();
        User deleteUser = userMapper.getUserByUserId(id);
        if (deleteUser == null){
            webApiResult.isNull();
            webApiResult.setMsg("删除的用户不存在！");
            return webApiResult;
        }
        userMapper.deleteUserByUserId(id);
        webApiResult.isOk();
        webApiResult.setMsg("删除成功！");
        webApiResult.setData(deleteUser);
        return webApiResult;
    }

    //必须提供userId
    public WebApiResult updateUser(User user){
        WebApiResult webApiResult = new WebApiResult();
        User oldUser = userMapper.getUserByUserId(user.getId());
        if (oldUser == null){
            webApiResult.isNull();
            webApiResult.setMsg("更新的用户不存在！");
            return webApiResult;
        }
        userMapper.updateUser(user);
        User newUser = userMapper.getUserByUserId(user.getId());
        webApiResult.isOk();
        webApiResult.setMsg("更新成功");
        webApiResult.setData(newUser);
        return webApiResult;
    }

    public WebApiResult getUserByUserId(Integer userId){
        WebApiResult webApiResult = new WebApiResult();
        User user = userMapper.getUserByUserId(userId);
        if (user == null){
            webApiResult.isNull();
            webApiResult.setMsg("用户不存在！");
            return webApiResult;
        }else {
            user.setRoles(userMapper.getUserRolesByUid(user.getId()));
            webApiResult.isOk();
            webApiResult.setMsg("查找成功！");
            webApiResult.setData(user);
            return webApiResult;
        }
    }

    public WebApiResult deleteUserByUserName(String userName){
        User user = userMapper.getUserByUsername(userName);
        WebApiResult webApiResult = new WebApiResult();
        if (user == null){
            webApiResult.isNull();
            webApiResult.setMsg("删除的用户不存在！");
            return webApiResult;
        }
        userMapper.deleteUserByUserId(user.getId());
        webApiResult.isOk();
        webApiResult.setMsg("删除成功");
        webApiResult.setData(user);
        return webApiResult;
    }

    public WebApiResult getUsersByAdminName(String adminName){
        WebApiResult webApiResult = new WebApiResult();
        Integer adminId = userMapper.getUserByUsername(adminName).getId();
        List<User> users = userMapper.getUsersByAdminId(adminId);
        for (User user : users) {
            user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        }
        webApiResult.isOk();
        webApiResult.setMsg("查找用户列表成功");
        webApiResult.setData(users);
        return webApiResult;
    }

    public WebApiResult getUserByCourseId(Integer courseId){
        WebApiResult webApiResult = new WebApiResult();
        List<User> userList = userMapper.getUserByCourseId(courseId);
        for (User user : userList) {
            user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        }
        webApiResult.isOk();
        webApiResult.setMsg("查询成功");
        webApiResult.setData(userList);
        return webApiResult;
    }

}
