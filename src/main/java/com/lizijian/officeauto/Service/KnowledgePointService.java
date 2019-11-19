package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.CourseMapper;
import com.lizijian.officeauto.mapper.KnowledgePointMapper;
import com.lizijian.officeauto.pojo.KnowledgePoint;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgePointService {

    @Autowired
    KnowledgePointMapper knowledgePointMapper;

    @Autowired
    CourseMapper courseMapper;

    public WebApiResult getKnowledgePointById(Integer id){
        KnowledgePoint knowledgePoint = knowledgePointMapper.getKnowledgePointById(id);
        WebApiResult webApiResult = new WebApiResult();
        if (knowledgePoint == null){
            webApiResult.isNull();
            webApiResult.setMsg("知识点不存在");
            return webApiResult;
        }
        webApiResult.isOk();
        webApiResult.setMsg("查询成功");
        webApiResult.setData(knowledgePoint);
        return webApiResult;
    }

    public WebApiResult  getKnowledgePointListByCourseId(Integer courseId){
        WebApiResult webApiResult = new WebApiResult();
        if (courseMapper.getCourseByCourseId(courseId) == null){
            webApiResult.isNull();
            webApiResult.setMsg("课程不存在");
        }else {
            List<KnowledgePoint> knowledgesList = knowledgePointMapper.getKnowledgesPointByCourseId(courseId);
            webApiResult.isOk();
            webApiResult.setData("查询成功");
            webApiResult.setData(knowledgesList);
        }
        return webApiResult;
    }

    public WebApiResult insertKnowledgePoint(KnowledgePoint knowledgePoint){
        WebApiResult webApiResult = new WebApiResult();
        knowledgePointMapper.insertKnowledgePoint(knowledgePoint);
        webApiResult.isOk();
        webApiResult.setMsg("插入成功");
        return webApiResult;
    }

    //批量插入功能暂时写道service,后面在添加excel批量导入的功能
    public WebApiResult insertBatchKnowledgePoint(List<KnowledgePoint> knowledgePointList){
        WebApiResult webApiResult = new WebApiResult();
        knowledgePointMapper.insertBatchKnowledgePoint(knowledgePointList);
        webApiResult.isOk();
        webApiResult.setMsg("暂时没有做插入成功验证");
        return webApiResult;
    }

    public WebApiResult deleteKnowledgePointById(Integer id){
        WebApiResult webApiResult = new WebApiResult();
        KnowledgePoint knowledgePoint = knowledgePointMapper.getKnowledgePointById(id);
        if (knowledgePoint == null){
            webApiResult.isNull();
            webApiResult.setMsg("删除的id不存在");
        }else {
            knowledgePointMapper.deleteKnowledgePointById(id);
            webApiResult.isOk();
            webApiResult.setMsg("删除成功");
            webApiResult.setData(knowledgePoint);
        }
        return webApiResult;
    }

    public WebApiResult updateKnowledgePointById(KnowledgePoint knowledgePoint){
        WebApiResult webApiResult = new WebApiResult();
        if (knowledgePointMapper.getKnowledgePointById(knowledgePoint.getId()) == null){
            webApiResult.isNull();
            webApiResult.setMsg("更新的id不存在");
        }else {
            knowledgePointMapper.updateKnowledgePointById(knowledgePoint);
            webApiResult.isOk();
            webApiResult.setMsg("更新成功");
            webApiResult.setData(knowledgePointMapper.getKnowledgePointById(knowledgePoint.getId()));
        }
        return webApiResult;
    }

    public WebApiResult getKnowledgePointByCourseIdAndUserId(Integer courseId, Integer uesrId){
        WebApiResult webApiResult = new WebApiResult();
        List<KnowledgePoint> knowledgesList = knowledgePointMapper.getKnowledgePointByCourseIdAndUserId(courseId, uesrId);
        webApiResult.isOk();
        webApiResult.setData(knowledgesList);
        return webApiResult;
    }
}
