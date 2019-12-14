package com.lizijian.officeauto.Service;

import com.lizijian.officeauto.mapper.CourseMapper;
import com.lizijian.officeauto.pojo.KnowledgePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Future;

@Service
public class AsyncService {

    @Autowired
    CourseMapper courseMapper;

    private final String[] filedNameArr = {
            "ppt_first_draft_at",
            "ppt_finalization_at",
            "video_first_draft_at",
            "video_finalization_at",
            "video_upload_at"
    };

    @Async
    public Future<List<KnowledgePoint>> asyncGetYesterdayCommit(Integer courseId,
                                                                String filedName,
                                                                String startTime,
                                                                String endTime) {
        List<KnowledgePoint> knowledgePointList = courseMapper.getCommitByTimeSlot(courseId, filedName, startTime, endTime);
        return new AsyncResult<>(knowledgePointList);
    }
}
