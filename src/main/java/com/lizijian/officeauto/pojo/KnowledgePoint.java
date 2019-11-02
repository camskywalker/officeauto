package com.lizijian.officeauto.pojo;

import java.sql.Timestamp;

public class KnowledgePoint {
   private Integer id;
   private Integer courseId;
   private Integer chapter;
   private Integer section;
   private Integer spot;
   private String name;
   private Timestamp pptFirstDraftAt;
   private Timestamp pptFinalizationAt;
   private Timestamp videoFirstDraftAt;
   private Timestamp videoFinalizationAt;
   private Timestamp videoUploadAt;
   private Integer teacherId;
   private Integer teacherEditorId;
   private Integer videoEditorId;
   private String teacherName;
   private String teacherEditorName;
   private String videoEditorName;

   @Override
   public String toString() {
      return "KnowledgePoint{" +
              "id=" + id +
              ", courseId=" + courseId +
              ", chapter=" + chapter +
              ", section=" + section +
              ", spot=" + spot +
              ", name='" + name + '\'' +
              ", pptFirstDraftAt=" + pptFirstDraftAt +
              ", pptFinalizationAt=" + pptFinalizationAt +
              ", videoFirstDraftAt=" + videoFirstDraftAt +
              ", videoFinalizationAt=" + videoFinalizationAt +
              ", videoUploadAt=" + videoUploadAt +
              ", teacherId=" + teacherId +
              ", teacherEditorId=" + teacherEditorId +
              ", videoEditorId=" + videoEditorId +
              ", teacherName='" + teacherName + '\'' +
              ", teacherEditorName='" + teacherEditorName + '\'' +
              ", videoEditorName='" + videoEditorName + '\'' +
              '}';
   }

   public String getTeacherName() {
      return teacherName;
   }

   public void setTeacherName(String teacherName) {
      this.teacherName = teacherName;
   }

   public String getTeacherEditorName() {
      return teacherEditorName;
   }

   public void setTeacherEditorName(String teacherEditorName) {
      this.teacherEditorName = teacherEditorName;
   }

   public String getVideoEditorName() {
      return videoEditorName;
   }

   public void setVideoEditorName(String videoEditorName) {
      this.videoEditorName = videoEditorName;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getCourseId() {
      return courseId;
   }

   public void setCourseId(Integer courseId) {
      this.courseId = courseId;
   }

   public Integer getChapter() {
      return chapter;
   }

   public void setChapter(Integer chapter) {
      this.chapter = chapter;
   }

   public Integer getSection() {
      return section;
   }

   public void setSection(Integer section) {
      this.section = section;
   }

   public Integer getSpot() {
      return spot;
   }

   public void setSpot(Integer spot) {
      this.spot = spot;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Timestamp getPptFirstDraftAt() {
      return pptFirstDraftAt;
   }

   public void setPptFirstDraftAt(Timestamp pptFirstDraftAt) {
      this.pptFirstDraftAt = pptFirstDraftAt;
   }

   public Timestamp getPptFinalizationAt() {
      return pptFinalizationAt;
   }

   public void setPptFinalizationAt(Timestamp pptFinalizationAt) {
      this.pptFinalizationAt = pptFinalizationAt;
   }

   public Timestamp getVideoFirstDraftAt() {
      return videoFirstDraftAt;
   }

   public void setVideoFirstDraftAt(Timestamp videoFirstDraftAt) {
      this.videoFirstDraftAt = videoFirstDraftAt;
   }

   public Timestamp getVideoFinalizationAt() {
      return videoFinalizationAt;
   }

   public void setVideoFinalizationAt(Timestamp videoFinalizationAt) {
      this.videoFinalizationAt = videoFinalizationAt;
   }

   public Timestamp getVideoUploadAt() {
      return videoUploadAt;
   }

   public void setVideoUploadAt(Timestamp videoUploadAt) {
      this.videoUploadAt = videoUploadAt;
   }

   public Integer getTeacherId() {
      return teacherId;
   }

   public void setTeacherId(Integer teacherId) {
      this.teacherId = teacherId;
   }

   public Integer getTeacherEditorId() {
      return teacherEditorId;
   }

   public void setTeacherEditorId(Integer teacherEditorId) {
      this.teacherEditorId = teacherEditorId;
   }

   public Integer getVideoEditorId() {
      return videoEditorId;
   }

   public void setVideoEditorId(Integer videoEditorId) {
      this.videoEditorId = videoEditorId;
   }

}

