package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.CourseMapper;
import com.lizijian.officeauto.pojo.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTest {
    @Autowired
    CourseMapper courseMapper;

    @Test
    public void setCourseUserByCourseIdTest(){
        courseMapper.setCourseUserRelation(9, 18);
    };

    @Test
    public void getCourseUserRelation(){
        System.out.println(courseMapper.getCourseUserRelation(1, 1));
    }

    @Test
    public void deleteCourseUserRelation(){
        courseMapper.deleteCourseUserRelation(9,18);
    }

    @Test
    public void getCourseProgressTest(){
        System.out.println(courseMapper.getCourseProgress(1));
    }

    @Test
    public void updateCourseByCourseId(){
        Course course = new Course();
        course.setId(1);
        course.setCourseName("test");
        course.setMajorId(1);
        courseMapper.updateCourseByCourseId(course);
        System.out.println(courseMapper.getCourseByCourseId(1));
    }

    @Test
    public void getCourseByUserId(){
        System.out.println(courseMapper.getCoursesByUserId(1));
    }
}
