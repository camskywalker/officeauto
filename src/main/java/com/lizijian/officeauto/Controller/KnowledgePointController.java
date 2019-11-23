package com.lizijian.officeauto.Controller;

import com.lizijian.officeauto.Service.KnowledgePointService;
import com.lizijian.officeauto.pojo.KnowledgePoint;
import com.lizijian.officeauto.pojo.WebApiResult;
import com.lizijian.officeauto.utils.ResourcesAuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/knowledgepoints")
public class KnowledgePointController {

    @Autowired
    KnowledgePointService knowledgePointService;

    @Autowired
    ResourcesAuthenticateUtils resourcesAuthenticateUtils;

    @GetMapping("/{knowledgepointid}")
    public WebApiResult getKnowledgePointById(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @PathVariable("knowledgepointid") Integer knowledgePointId) {
        if (resourcesAuthenticateUtils.assertKnowledgePointInAuthenticateResources(request, knowledgePointId)) {
            return knowledgePointService.getKnowledgePointById(knowledgePointId);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @GetMapping("/getbycourse/{courseid}")
    public WebApiResult getKnowledgePointListByCourseId(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        @PathVariable("courseid") Integer courseId,
                                                        @RequestParam(value = "userId", required = false) Integer userId) {
        if (userId == null) {
            if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, courseId)) {
                return knowledgePointService.getKnowledgePointListByCourseId(courseId);
            }
        } else {
            if (resourcesAuthenticateUtils.assertStuffInAuthenticateResources(request, userId)) {
                return knowledgePointService.getKnowledgePointByCourseIdAndUserId(courseId, userId);
            }
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return null;
    }

    @PostMapping
    public WebApiResult insertKnowledgePoint(HttpServletRequest request,
                                             HttpServletResponse response,
                                             KnowledgePoint knowledgePoint) {
        //knowledgePoint必须提供八个属性，courseId,section,spot,name,teacherId, teacherEditorId,videoEditorId
        if (resourcesAuthenticateUtils.assertCourseInAuthenticateResources(request, knowledgePoint.getCourseId())) {
            return knowledgePointService.insertKnowledgePoint(knowledgePoint);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @DeleteMapping("/{knowledgepointid}")
    public WebApiResult deleteKnowledgePointById(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @PathVariable("knowledgepointid") Integer knowledgePointId) {
        if (resourcesAuthenticateUtils.assertKnowledgePointInAuthenticateResources(request, knowledgePointId)) {
            return knowledgePointService.deleteKnowledgePointById(knowledgePointId);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    //前端传递的时间格式为“yyyy-MM-dd HH:mm:ss”
    @PutMapping("/{knowledgepointid}")
    public WebApiResult updateKnowledgePointById(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @PathVariable("knowledgepointid") Integer knowledgePointId,
                                                 KnowledgePoint knowledgePoint) {
        if (resourcesAuthenticateUtils.assertKnowledgePointInAuthenticateResources(request, knowledgePointId)) {
            knowledgePoint.setId(knowledgePointId);
            return knowledgePointService.updateKnowledgePointById(knowledgePoint);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
}
