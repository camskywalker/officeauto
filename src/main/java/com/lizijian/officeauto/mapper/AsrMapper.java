package com.lizijian.officeauto.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AsrMapper {
    void writeAsrCallbackResponse(Integer requestId, String text);
    void createAsrTask(@Param("uuid") String uuid,
                       @Param("userId")Integer userId,
                       @Param("requestId")Integer requestId);
    String getAsrResultText(String uuid);
}
