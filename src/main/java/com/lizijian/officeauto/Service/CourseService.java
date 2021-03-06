package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.CourseMapper;
import com.lizijian.officeauto.mapper.UserMapper;
import com.lizijian.officeauto.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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

    @Autowired
    AsyncService asyncService;

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

    public WebApiResult setFinished(Integer courseId, Boolean finished){
        courseMapper.setFinished(courseId,finished);
        WebApiResult webApiResult = new WebApiResult();
        webApiResult.isOk();
        webApiResult.setMsg("修改成功");
        return webApiResult;
    }

    private final String[] filedNameArr = {"ppt_first_draft_at", "ppt_finalization_at", "video_first_draft_at", "video_finalization_at", "video_upload_at"};

    public Map<String, List<KnowledgePoint>> getYesterdayCommit(Integer courseId) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String  endTime = simpleDateFormat.format(calendar.getTimeInMillis()) + " 00:00:00";
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String startTime = simpleDateFormat.format(calendar.getTimeInMillis()) + " 00:00:00";

        HashMap<String, List<KnowledgePoint>> resultMap = new HashMap<>();
        for (String filedName : this.filedNameArr) {
            List<KnowledgePoint> knowledgePointList = courseMapper.getCommitByTimeSlot(courseId, filedName, startTime, endTime);
            resultMap.put(filedName, knowledgePointList);
        }
        return resultMap;
    }

    public Map<String, List<KnowledgePoint>> asyncGetYesterdayCommit(Integer courseId) throws ExecutionException, InterruptedException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String  endTime = simpleDateFormat.format(calendar.getTimeInMillis()) + " 00:00:00";
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String startTime = simpleDateFormat.format(calendar.getTimeInMillis()) + " 00:00:00";

        HashMap<String, Future<List<KnowledgePoint>>> taskMap = new HashMap<>();
        //循环发起异步任务，将字段名与结果以key-value，put到map中
        for (String filedName : this.filedNameArr) {
            Future<List<KnowledgePoint>> listFutureTask = asyncService.asyncGetYesterdayCommit(courseId, filedName, startTime, endTime);
            taskMap.put(filedName, listFutureTask);
        }

        HashMap<String, List<KnowledgePoint>> resultMap = new HashMap<>();
        for (Map.Entry<String, Future<List<KnowledgePoint>>> entry : taskMap.entrySet()) {
            //遍历map，获取任务结果
            resultMap.put(entry.getKey(), entry.getValue().get());
        }
        return resultMap;
    }
}
