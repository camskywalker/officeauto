package com.lizijian.officeauto.mapper;

import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);

    //提供user四个属性，username,email,adminId,Password。来创建
    void insertUser(User user);

    void deleteUserByUserId(Integer id);

    //只能更改username,email,adminId,Password
    void updateUser(User user);

    User getUserByUserId(Integer userId);
    List<User> getUsersByAdminId(Integer adminId);

    List<User> getUserByCourseId(Integer courseId);

}
