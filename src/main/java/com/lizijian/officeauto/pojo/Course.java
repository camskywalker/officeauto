package com.lizijian.officeauto.pojo;

import java.util.List;
import java.util.Map;

public class Course {
    private Integer id;
    private String courseName;
    private Integer majorId;
    private String majorName;
    private Integer adminId;
    private List<User> teacherList;
    private List<User> teacherEditorList;
    private List<User> videoEditorList;
    private Map<String, Long> progress;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", adminId=" + adminId +
                ", teacherList=" + teacherList +
                ", teacherEditorList=" + teacherEditorList +
                ", videoEditorList=" + videoEditorList +
                ", progress=" + progress +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public List<User> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<User> teacherList) {
        this.teacherList = teacherList;
    }

    public List<User> getTeacherEditorList() {
        return teacherEditorList;
    }

    public void setTeacherEditorList(List<User> teacherEditorList) {
        this.teacherEditorList = teacherEditorList;
    }

    public List<User> getVideoEditorList() {
        return videoEditorList;
    }

    public void setVideoEditorList(List<User> videoEditorList) {
        this.videoEditorList = videoEditorList;
    }

    public Map<String, Long> getProgress() {
        return progress;
    }

    public void setProgress(Map<String, Long> progress) {
        this.progress = progress;
    }
}
