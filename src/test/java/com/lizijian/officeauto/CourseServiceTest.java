package com.lizijian.officeauto;

import com.lizijian.officeauto.Service.CourseService;
import com.lizijian.officeauto.pojo.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    CourseService courseService;

    @Test
    public void getCourseUserRelationByCourseId(){
        System.out.println();
    }

    @Test
    public void updateCourseByCourseIdTest(){
        Course course = new Course();
        course.setId(1);
        course.setCourseName("test");
        course.setMajorId(1);
        courseService.updateCourseByCourseId(course);
    }
}
