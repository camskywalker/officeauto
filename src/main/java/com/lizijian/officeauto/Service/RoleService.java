package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.RoleMapper;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public Role getRoleByZhName(String zhName){
        return roleMapper.getRoleByZhName(zhName);
    };

    public Role getRoleById(Integer roleId){
        return roleMapper.getRoleById(roleId);
    }

    public WebApiResult insertUserRole(Integer userId, Integer roleId){
        List<Integer> userRoleIdList = roleMapper.getUserRoleByUserId(userId);
        WebApiResult webApiResult = new WebApiResult();
       if (userRoleIdList.isEmpty()){
           roleMapper.insertUserRole(userId, roleId);
       }else if (!userRoleIdList.get(0).equals(roleId)){
           roleMapper.deleteUserRole(userId, userRoleIdList.get(0));
           roleMapper.insertUserRole(userId, roleId);
       }
       webApiResult.isOk();
       webApiResult.setData(roleMapper.getItem(userId, roleId));
       return webApiResult;
    };

    public WebApiResult deleteUserRole(Integer userId, Integer roleId){
        WebApiResult webApiResult = new WebApiResult();
        roleMapper.deleteUserRole(userId,roleId);
        webApiResult.isOk();
        webApiResult.setMsg("删除角色权限成功");
        return webApiResult;
    };

}
