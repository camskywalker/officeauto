package com.lizijian.officeauto.Controller;


import com.lizijian.officeauto.Service.CourseService;
import com.lizijian.officeauto.Service.UserService;
import com.lizijian.officeauto.pojo.Course;
import com.lizijian.officeauto.pojo.User;
import com.lizijian.officeauto.pojo.WebApiResult;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;


    @GetMapping("/groupbyuserid/{userId}")
    public WebApiResult courses(HttpServletRequest request, @PathVariable("userId") Integer userId) {
        WebApiResult webApiResult = new WebApiResult();
        User user = (User) request.getAttribute("user");
        if (userId.equals(user.getId())) {
            List<Course> courseList = courseService.getCourseListByUserId(userId);
            webApiResult.isOk();
            webApiResult.setMsg("查询成功");
            webApiResult.setData(courseList);
        } else {
            webApiResult.isErr();
            webApiResult.setMsg("不给你看");
        }
        return webApiResult;
    }

    @PostMapping
    public WebApiResult addCourse(HttpServletRequest request,HttpServletResponse response, Course course) {
        User user = (User) request.getAttribute("user");
        if (course.getAdminId().equals(user.getId())){
            return courseService.insertCourseByCourseId(course);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

    }

    @DeleteMapping("/{courseid}")
    public WebApiResult deleteCourse(HttpServletRequest request,
                                     @PathVariable("courseid") Integer courseId) {
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
            return courseService.deleteCourseByCourseId(courseId);
        } else {
            return this.errWebApiResult("不给你看");
        }
    }

    @PutMapping("/{courseid}")
    public WebApiResult updateCourse(HttpServletRequest request,
                                     @PathVariable("courseid") Integer courseId,
                                     @RequestBody Course course) {
        course.setId(courseId);
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
            return courseService.updateCourseByCourseId(course);
        } else {
            return this.errWebApiResult("不给你看");
        }
    }

    @PostMapping("/{courseid}/userrelation")
    public WebApiResult insertCourseUserRelation(HttpServletRequest request,
                                                 @PathVariable("courseid") Integer courseId,
                                                 @RequestBody List<Integer> newUserList) {
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
            return courseService.setCourseUserRelation(courseId, newUserList);
        } else {
            return this.errWebApiResult("不给你看");
        }
    }

    @GetMapping("/{courseid}/progress")
    public WebApiResult getCourseProgress(HttpServletRequest request,
                                          @PathVariable("courseid") Integer courseId) {
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
            WebApiResult webApiResult = new WebApiResult();
            Map<String, Long> courseProgress = courseService.getCourseProgress(courseId);
            webApiResult.setData(courseProgress);
            webApiResult.isOk();
            return webApiResult;
        } else {
            return this.errWebApiResult("不给你看");
        }

    }

    @GetMapping("/groupbyuseridfromknowledgepoint/{userId}")
    public WebApiResult getCourseByUserIdFromKnowledgePoint(HttpServletRequest request,
                                                            @PathVariable("userId") Integer userId) {
        User user = (User) request.getAttribute("user");
        if (user.getId().equals(userId)) {
            return courseService.getCourseByUserIdFromKnowledgePoint(userId);
        } else {
            return this.errWebApiResult("不给你看");
        }
    }

    @PutMapping("/{courseId}/finished/{finished}")
    public WebApiResult setFinished(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("courseId") Integer courseId,
                                    @PathVariable("finished") Integer finished) {
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
            if (finished.equals(1)) {
                return courseService.setFinished(courseId, true);
            } else if (finished.equals(0)) {
                return courseService.setFinished(courseId, false);
            } else {
                WebApiResult webApiResult = new WebApiResult();
                webApiResult.isErr();
                webApiResult.setMsg("输入参数不正确，1表示完成，0表示未完成。");
                return webApiResult;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    private WebApiResult errWebApiResult(String msg) {
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isErr();
        webApiResult.setMsg(msg);
        return webApiResult;
    }

}
