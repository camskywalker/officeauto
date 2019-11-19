package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.CourseMapper;
import com.lizijian.officeauto.mapper.UserMapper;
import com.lizijian.officeauto.pojo.Course;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Autowired
    UserMapper userMapper;

    public List<Course> getCourseListByUserId(Integer userId){
        List<Course> courses = courseMapper.getCoursesByUserId(userId);
        return courses;
    }

    public WebApiResult updateCourseByCourseId(Course course){
        WebApiResult webApiResult = new WebApiResult();
        if(courseMapper.getCourseByCourseId(course.getId())==null){
            webApiResult.isNull();
            webApiResult.setMsg("查找的课程不存在！");
            webApiResult.setMsg("查找的课程不存在！");
            return webApiResult;
        }
        courseMapper.updateCourseByCourseId(course);
        Course updatedCourse = courseMapper.getCourseByCourseId(course.getId());
        webApiResult.isOk();
        webApiResult.setMsg("更新成功");
        webApiResult.setData(updatedCourse);
        return webApiResult;
    }

    public WebApiResult deleteCourseByCourseId(Integer courseId){
        WebApiResult webApiResult = new WebApiResult();
        if(courseMapper.getCourseByCourseId(courseId)==null){
            webApiResult.isNull();
            webApiResult.setMsg("删除的课程不存在");
            return webApiResult;
        }
        Course deletedCourse = courseMapper.getCourseByCourseId(courseId);
        courseMapper.deleteCourseByCourseId(courseId);
        webApiResult.isOk();
        webApiResult.setMsg("删除成功");
        webApiResult.setData(deletedCourse);
        return webApiResult;
    }

    public WebApiResult insertCourseByCourseId(Course course){
        WebApiResult webApiResult = new WebApiResult();
        if (course.getCourseName()==null | course.getMajorId()==null) {
            webApiResult.isErr();
            webApiResult.setMsg("课程信息不完整");
            return webApiResult;
        }

        if (!((courseMapper.getCourseByCourseName(course.getCourseName())) ==null)){
            webApiResult.isErr();
            webApiResult.setMsg("课程已存在");
            return webApiResult;
        }

        courseMapper.insertCourseByCourseId(course);
        Course insertedCourse = courseMapper.getCourseByCourseId(course.getId());
        webApiResult.isOk();
        webApiResult.setMsg("添加成功");
        webApiResult.setData(insertedCourse);
        return webApiResult;
    }

    private List<User> getCourseUserRelationByCourseId(Integer courseId){
        List<User> userList = userMapper.getUserByCourseId(courseId);
        for (User user : userList) {
            List<Role> userRoles = userMapper.getUserRolesByUid(user.getId());
            user.setRoles(userRoles);
        };
        return userList;
    }

    public WebApiResult setCourseUserRelation(Integer courseId, List<Integer> newUserList){
        WebApiResult webApiResult = new WebApiResult();
        List<User> users = getCourseUserRelationByCourseId(courseId);
        ArrayList<Integer> coincideUser;
        ArrayList<Integer> oldUserList = new ArrayList<>();
        for (User user : users) {
            oldUserList.add(user.getId());
        }
        //流过流式处理得到新用户ID列表与旧用户ID列表的交集，新用户ID列表去除交集得到要增加的用户ID列表，旧用户ID列表去除交集得到要删除的用户ID列表。
        coincideUser = (ArrayList<Integer>) oldUserList.stream().filter(newUserList::contains).collect(Collectors.toList());
        oldUserList.removeAll(coincideUser);
        newUserList.removeAll(coincideUser);
        if (oldUserList.size() != 0){
            for (Integer deleteUser : oldUserList) {
                courseMapper.deleteCourseUserRelation(courseId, deleteUser);
            }
        };
        if (newUserList.size() != 0){
            for (Integer addUser : newUserList){
                courseMapper.setCourseUserRelation(courseId, addUser);
            }
        };
        webApiResult.setData(getCourseUserRelationByCourseId(courseId));
        webApiResult.isOk();
        return webApiResult;
    }

    public WebApiResult deleteCourseUserRelation(Integer courseId, Integer userId){
        WebApiResult webApiResult = new WebApiResult();
        courseMapper.deleteCourseUserRelation(courseId, userId);
        webApiResult.isOk();
        webApiResult.setMsg("删除成功");
        return webApiResult;
    }

    public Map<String, Long> getCourseProgress(Integer courseId){
        return courseMapper.getCourseProgress(courseId);
    };

    public WebApiResult getCourseByUserIdFromKnowledgePoint(Integer userId){
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setData(courseMapper.getCourseByUserIdFromKnowledgePoint(userId));
        return webApiResult;
    }
}
