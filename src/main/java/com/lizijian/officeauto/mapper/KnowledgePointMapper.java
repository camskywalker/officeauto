package com.lizijian.officeauto.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.lizijian.officeauto.pojo.KnowledgePoint;

import java.util.List;
import java.util.Set;

@Mapper
public interface KnowledgePointMapper {

    public KnowledgePoint getKnowledgePointById(Integer id);
    public List<KnowledgePoint> getKnowledgesPointByCourseId(Integer courseId);
    public void insertKnowledgePoint(KnowledgePoint knowledgePoint);
    public void insertBatchKnowledgePoint(List<KnowledgePoint> knowledgePointList);
    public void deleteKnowledgePointById(Integer id);
    public void updateKnowledgePointById(KnowledgePoint knowledgePoint);


}
