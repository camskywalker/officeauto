package com.lizijian.officeauto.mapper;

import com.lizijian.officeauto.pojo.Course;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseMapper {
    public List<Course> getCoursesByUserId(Integer userId);
    public Course getCourseByCourseId(Integer courseId);
    public void updateCourseByCourseId(Course course);
    public void deleteCourseByCourseId(Integer courseId);
    public void insertCourseByCourseId(Course course);
    public Course getCourseByCourseName(String couesrName);
    public Map<String, Long> getCourseProgress(Integer courseId);
    public void setCourseUserRelation(@Param("courseId") Integer courseId,
                                        @Param("userId") Integer userId);
    public Map<String, Integer> getCourseUserRelation(@Param("courseId") Integer courseId,
                                              @Param("userId") Integer userId);
    public void deleteCourseUserRelation(@Param("courseId") Integer courseId,
                                            @Param("userId") Integer userId);
}
