package com.lizijian.officeauto;

import com.lizijian.officeauto.mapper.CourseMapper;
import com.lizijian.officeauto.mapper.KnowledgePointMapper;
import com.lizijian.officeauto.mapper.RoleMapper;
import com.lizijian.officeauto.mapper.UserMapper;
import com.lizijian.officeauto.pojo.KnowledgePoint;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeautoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    KnowledgePointMapper knowledgePointMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void dBTest(){
//        DataSource dataSource = applicationContext.getBean(DataSource.class);
//        System.out.println(dataSource);
        User liumin = userMapper.getUserByUsername("liumin");
        System.out.println(liumin);
    }

    @Test
    public void userMapperTest(){
        User userByUserId = userMapper.getUserByUserId(6);
        System.out.println(userByUserId);
    };

    @Test
    public void roleMapperInsertUserRoleTest(){
        roleMapper.insertUserRole(4,4);
    }

    @Test
    public void roleMapperGetRoleByZhNameTest(){
        Role role = roleMapper.getRoleByZhName("教师");
        System.out.println(role);
    }

    @Test
    public void roleMapperGetRoleByIdTest(){
        System.out.println(roleMapper.getRoleById(2));
    }

    @Test
    public void roleMapperDeleteUserRoleTest(){
        roleMapper.deleteUserRole(4,4);
    }

    @Test
    public void roleMapperGetItemTest(){
        System.out.println(roleMapper.getItem(3,3));
    }

    @Test
    public void deleteRoleTest(){
        System.out.println(userMapper.getUsersByAdminId(1));
    }

    @Test
    public void knowledgePointTest(){
        System.out.println(knowledgePointMapper.getKnowledgePointById(21));
        System.out.println(knowledgePointMapper.getKnowledgesPointByCourseId(1));
        KnowledgePoint knowledgePoint = new KnowledgePoint();
        knowledgePoint.setName("第三个考点");
        knowledgePoint.setChapter(2);
        knowledgePoint.setSection(1);
        knowledgePoint.setSpot(1);
        knowledgePoint.setTeacherId(2);
        knowledgePoint.setTeacherEditorId(3);
        knowledgePoint.setVideoEditorId(4);
        knowledgePoint.setCourseId(1);
        knowledgePointMapper.insertKnowledgePoint(knowledgePoint);
    }

    @Test
    public void knowledgePointBatchTest(){
        KnowledgePoint knowledgePoint1 = new KnowledgePoint();
        knowledgePoint1.setName("第三个考点");
        knowledgePoint1.setChapter(2);
        knowledgePoint1.setSection(1);
        knowledgePoint1.setSpot(1);
        knowledgePoint1.setTeacherId(2);
        knowledgePoint1.setTeacherEditorId(3);
        knowledgePoint1.setVideoEditorId(4);
        knowledgePoint1.setCourseId(1);
        KnowledgePoint knowledgePoint2 = new KnowledgePoint();
        knowledgePoint2.setName("第三个考点");
        knowledgePoint2.setChapter(2);
        knowledgePoint2.setSection(1);
        knowledgePoint2.setSpot(1);
        knowledgePoint2.setTeacherId(2);
        knowledgePoint2.setTeacherEditorId(3);
        knowledgePoint2.setVideoEditorId(4);
        knowledgePoint2.setCourseId(1);
        List<KnowledgePoint> knowledgePoints = new ArrayList<>();
        knowledgePoints.add(knowledgePoint1);
        knowledgePoints.add(knowledgePoint2);
        knowledgePointMapper.insertBatchKnowledgePoint(knowledgePoints);
    }

    @Test
    public void knowledgePointDeleteTest(){
        knowledgePointMapper.deleteKnowledgePointById(22);
    }

    @Test
    public void knowledgePointUploadTest(){
        java.util.Date javaDate = new java.util.Date();
        Date sqlDate = new Date(javaDate.getTime());
        Timestamp timestamp = new Timestamp(javaDate.getTime());
        KnowledgePoint knowledgePoint2 = new KnowledgePoint();
        knowledgePoint2.setId(21);
        knowledgePoint2.setCourseId(9);
        knowledgePoint2.setChapter(5);
        knowledgePoint2.setSection(5);
        knowledgePoint2.setSpot(5);
        knowledgePoint2.setName("更新测试考点");
        knowledgePoint2.setPptFirstDraftAt(timestamp);
        knowledgePoint2.setPptFinalizationAt(timestamp);
        knowledgePoint2.setVideoFirstDraftAt(timestamp);
        knowledgePoint2.setVideoFinalizationAt(timestamp);
        knowledgePoint2.setVideoUploadAt(timestamp);
        knowledgePoint2.setTeacherId(3);
        knowledgePoint2.setTeacherEditorId(3);
        knowledgePoint2.setVideoEditorId(3);
        knowledgePointMapper.updateKnowledgePointById(knowledgePoint2);
    }

    @Test
    public  void courseProgressTest(){
        System.out.println(courseMapper.getCourseProgress(1));
    }





}


