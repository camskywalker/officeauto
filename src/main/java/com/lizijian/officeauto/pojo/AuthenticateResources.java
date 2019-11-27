package com.lizijian.officeauto.pojo;

import java.io.Serializable;
import java.util.Set;

public class AuthenticateResources implements Serializable {
    private Integer userId;
    private Set<Integer> stuffIdSet;
    private Set<Integer> courseIdSet;
    private Set<Integer> knowledgePointIdSet;

    @Override
    public String toString() {
        return "AuthenticateResources{" +
                "userId=" + userId +
                ", stuffIdSet=" + stuffIdSet +
                ", courseIdSet=" + courseIdSet +
                ", knowledgePointIdSet=" + knowledgePointIdSet +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<Integer> getStuffIdSet() {
        return stuffIdSet;
    }

    public void setStuffIdSet(Set<Integer> stuffIdSet) {
        this.stuffIdSet = stuffIdSet;
    }

    public Set<Integer> getCourseIdSet() {
        return courseIdSet;
    }

    public void setCourseIdSet(Set<Integer> courseIdSet) {
        this.courseIdSet = courseIdSet;
    }

    public Set<Integer> getKnowledgePointIdSet() {
        return knowledgePointIdSet;
    }

    public void setKnowledgePointIdSet(Set<Integer> knowledgePointIdSet) {
        this.knowledgePointIdSet = knowledgePointIdSet;
    }
}
