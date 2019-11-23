package com.lizijian.officeauto.utils;

import com.lizijian.officeauto.mapper.UserMapper;
import com.lizijian.officeauto.pojo.AuthenticateResources;
import com.lizijian.officeauto.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class ResourcesAuthenticateUtils {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisTemplate<Integer, AuthenticateResources> authenticateResourcesRedisTemplate;

    private final String COURSE = "course";
    private final String KNOWLEDGEPOINT = "knowledgepoint";
    private final String USER = "user";
    private final String USER_ROLE = "user_role";

    private AuthenticateResources getAuthenticateResourcesByUserId(Integer userId) {
        return userMapper.getAuthenticateResourcesByUserId(userId);
    }

    private AuthenticateResources getAuthenticateResourcesByAdminId(Integer adminId) {
        AuthenticateResources authenticateResources = userMapper.getAuthenticateResourcesByAdminId(adminId);
        List<User> userList = userMapper.getUsersByAdminId(adminId);
        HashSet<Integer> userIdSet = new HashSet<>();
        for (User user : userList) {
            userIdSet.add(user.getId());
        }
        authenticateResources.setStuffIdSet(userIdSet);
        return authenticateResources;
    }

    private void setRedisAuthenticateResources(User user) {
        AuthenticateResources authenticateResources;
        if ("ROLE_admin".equals(user.getRoles().get(0).getName())) {
            authenticateResources = this.getAuthenticateResourcesByAdminId(user.getId());
        } else {
            authenticateResources = this.getAuthenticateResourcesByUserId(user.getId());
        }
        authenticateResourcesRedisTemplate.opsForValue().set(user.getId(), authenticateResources);
    }

    private boolean assertAuthenticateResourcesInRedis(String resourcesCategory, Integer resourcesId, Integer userId) {
        if (this.COURSE.equals(resourcesCategory)) {
            return authenticateResourcesRedisTemplate.opsForValue().get(userId).getCourseIdSet().contains(resourcesId);
        } else if (this.KNOWLEDGEPOINT.equals(resourcesCategory)) {
            return authenticateResourcesRedisTemplate.opsForValue().get(userId).getKnowledgePointIdSet().contains(resourcesId);
        } else if (this.USER.equals(resourcesCategory)) {
            return authenticateResourcesRedisTemplate.opsForValue().get(userId).getStuffIdSet().contains(resourcesId);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Boolean assertStuffInAuthenticateResources(HttpServletRequest request, Integer stuffId) {
        User user = (User) request.getAttribute("user");
        if (!this.assertAuthenticateResourcesInRedis(this.USER, stuffId, user.getId())) {
            this.setRedisAuthenticateResources(user);
        }
        return this.assertAuthenticateResourcesInRedis(this.USER, stuffId, user.getId());
    }

    public Boolean assertCourseInAuthenticateResources(HttpServletRequest request, Integer courseId) {
        User user = (User) request.getAttribute("user");
        if (!authenticateResourcesRedisTemplate.hasKey(user.getId())) {
            this.setRedisAuthenticateResources(user);
        }
        if (!this.assertAuthenticateResourcesInRedis(this.COURSE, courseId, user.getId())) {
            this.setRedisAuthenticateResources(user);
        }
        return this.assertAuthenticateResourcesInRedis(this.COURSE, courseId, user.getId());
    }


}
