package com.lizijian.officeauto.pojo;

public class OasrCallBackResponse {
    private Integer code;
    private Integer requestId;
    private Integer appid;
    private Integer projectid;
    private String text;
    private String audioUrl;
    private Double audioTime;
    private String message;

    @Override
    public String toString() {
        return "OasrResponse{" +
                "code=" + code +
                ", requestId=" + requestId +
                ", appid=" + appid +
                ", projectid=" + projectid +
                ", text='" + text + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", audioTime=" + audioTime +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Double getAudioTime() {
        return audioTime;
    }

    public void setAudioTime(Double audioTime) {
        this.audioTime = audioTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
