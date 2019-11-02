package com.lizijian.officeauto;

import com.lizijian.officeauto.Controller.CourseController;
import com.lizijian.officeauto.pojo.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseControllerTest {

    @Autowired
    CourseController courseController;
    @Test
    public void updateCourseTest(){
        Course course = new Course();
        course.setCourseName("test");
        course.setMajorId(1);
        courseController.updateCourse(1, course);
    }
}
