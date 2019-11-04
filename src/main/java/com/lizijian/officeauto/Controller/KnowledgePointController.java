package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.KnowledgePointService;
import com.lizijian.officeauto.pojo.KnowledgePoint;
import com.lizijian.officeauto.pojo.WebApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/knowledgepoints")
public class KnowledgePointController {

    @Autowired
    KnowledgePointService knowledgePointService;

    @GetMapping("/{knowledgepointid}")
    public WebApiResult getKnowledgePointById(@PathVariable("knowledgepointid") Integer  knowledgePointId){
        return knowledgePointService.getKnowledgePointById(knowledgePointId);
    }

    @GetMapping("/getbycourse/{courseid}")
    public WebApiResult getKnowledgePointListByCourseId(@PathVariable("courseid") Integer courseId){
        return knowledgePointService.getKnowledgePointListByCourseId(courseId);
    }

    @PostMapping
    public WebApiResult insertKnowledgePoint(KnowledgePoint knowledgePoint){
        //knowledgePoint必须提供八个属性，courseId,section,spot,name,teacherId, teacherEditorId,videoEditorId
        return knowledgePointService.insertKnowledgePoint(knowledgePoint);
    }

    @DeleteMapping("/{knowledgepointid}")
    public WebApiResult deleteKnowledgePointById(@PathVariable("knowledgepointid") Integer  knowledgePointId){
        return knowledgePointService.deleteKnowledgePointById(knowledgePointId);
    }

    //前端传递的时间格式为“yyyy-MM-dd HH:mm:ss”
    @PutMapping("/{knowledgepointid}")
    public WebApiResult pdateKnowledgePointById(@PathVariable("knowledgepointid") Integer  knowledgePointId,
                                                KnowledgePoint knowledgePoint){
        knowledgePoint.setId(knowledgePointId);
        return knowledgePointService.updateKnowledgePointById(knowledgePoint);
    }
}
