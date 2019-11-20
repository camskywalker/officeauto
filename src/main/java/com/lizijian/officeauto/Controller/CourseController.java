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

    @GetMapping("/groupbyuseridfromknowledgepoint/{userId}")
    public WebApiResult getCourseByUserIdFromKnowledgePoint(@PathVariable("userId") Integer userId){
        return courseService.getCourseByUserIdFromKnowledgePoint(userId);
    }

    @PutMapping("/{courseId}/finished/{finished}")
    public WebApiResult setFinished(@PathVariable("courseId") Integer courseId,
                                    @PathVariable("finished") Integer finished){
        if (finished.equals(1)){
            return courseService.setFinished(courseId, true);
        } else if (finished.equals(0)){
            return courseService.setFinished(courseId, false);
        } else {
            WebApiResult webApiResult = new WebApiResult();
            webApiResult.isErr();
            webApiResult.setMsg("输入参数不正确，1表示完成，0表示未完成。");
            return webApiResult;
        }
    }

}
