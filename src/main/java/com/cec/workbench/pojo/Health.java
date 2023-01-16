package com.cec.workbench.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Health {
    private Integer id;

    private Integer userId;

    private String userName;

    private String clazzId;

    private String clazzName;

    private String morningCheck;

    private String afternoonCheck;

    private String inSchool;

    private String inRisk;

    private String inIsolation;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date clockTime;

    @Override
    public String toString() {
        return "Health{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", clazzId='" + clazzId + '\'' +
                ", clazzName='" + clazzName + '\'' +
                ", morningCheck='" + morningCheck + '\'' +
                ", afternoonCheck='" + afternoonCheck + '\'' +
                ", inSchool='" + inSchool + '\'' +
                ", inRisk='" + inRisk + '\'' +
                ", inIsolation='" + inIsolation + '\'' +
                ", clockTime=" + clockTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId == null ? null : clazzId.trim();
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName == null ? null : clazzName.trim();
    }

    public String getMorningCheck() {
        return morningCheck;
    }

    public void setMorningCheck(String morningCheck) {
        this.morningCheck = morningCheck == null ? null : morningCheck.trim();
    }

    public String getAfternoonCheck() {
        return afternoonCheck;
    }

    public void setAfternoonCheck(String afternoonCheck) {
        this.afternoonCheck = afternoonCheck == null ? null : afternoonCheck.trim();
    }

    public String getInSchool() {
        return inSchool;
    }

    public void setInSchool(String inSchool) {
        this.inSchool = inSchool == null ? null : inSchool.trim();
    }

    public String getInRisk() {
        return inRisk;
    }

    public void setInRisk(String inRisk) {
        this.inRisk = inRisk == null ? null : inRisk.trim();
    }

    public String getInIsolation() {
        return inIsolation;
    }

    public void setInIsolation(String inIsolation) {
        this.inIsolation = inIsolation == null ? null : inIsolation.trim();
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }
}