package com.lizijian.officeauto.Controller;


import com.lizijian.officeauto.Service.CourseService;
import com.lizijian.officeauto.Service.UserService;
import com.lizijian.officeauto.pojo.Course;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @GetMapping("/groupbyuserid/{userId}")
    public WebApiResult courses(@PathVariable("userId") Integer userId){
        WebApiResult webApiResult = new WebApiResult();
        List<Course> courseList = courseService.getCourseListByUserId(userId);
        webApiResult.isOk();
        webApiResult.setMsg("查询成功");
        webApiResult.setData(courseList);
        return webApiResult;
    }

    @PostMapping
    public WebApiResult addCourse(Course course){
        return courseService.insertCourseByCourseId(course);
    }

    @DeleteMapping("/{courseid}")
    public WebApiResult deleteCourse(@PathVariable("courseid") Integer courseId){
        return courseService.deleteCourseByCourseId(courseId);
    }

    @PutMapping("/{courseid}")
    public WebApiResult updateCourse(
            @PathVariable("courseid") Integer courseId,
            @RequestBody Course course){
        course.setId(courseId);
        return courseService.updateCourseByCourseId(course);
    }

    @DeleteMapping("/{courseid}/userrelation/{userid}")
        public WebApiResult deleteCourseUserRelation(@PathVariable("courseid") Integer courseId,
                                                 @PathVariable("userid") Integer userId){
        return courseService.deleteCourseUserRelation(courseId, userId);
    }

    @PostMapping("/{courseid}/userrelation")
    public WebApiResult insertCourseUserRelation(@PathVariable("courseid") Integer courseId,
                                                 @RequestBody List<Integer> newUserList){
        return courseService.setCourseUserRelation(courseId, newUserList);
    }

    @GetMapping("/{courseid}/progress")
    public WebApiResult getCourseProgress(@PathVariable("courseid") Integer courseId){
        WebApiResult webApiResult = new WebApiResult();
        Map<String, Long> courseProgress = courseService.getCourseProgress(courseId);
        webApiResult.setData(courseProgress);
        webApiResult.isOk();
        return webApiResult;
    }

}
