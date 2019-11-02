package com.lizijian.officeauto.mapper;

import com.lizijian.officeauto.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RoleMapper {
    Role getRoleByZhName(String zhName);
    Role getRoleById(Integer roleId);
    void insertUserRole(Integer userId, Integer roleId);
    void deleteUserRole(Integer userId, Integer roleId);
    List<HashMap<Integer,Integer>> getItem(Integer userId, Integer roleId);
    List<Integer> getUserRoleByUserId(Integer userId);
}
