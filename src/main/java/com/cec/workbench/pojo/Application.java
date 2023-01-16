package com.cec.workbench.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Application {
    private Integer id;

    private String deptId;

    private Integer studentId;

    private String studentName;

    private String sex;

    private String phoneNumber;

    private String outRange;

    private String outLocation;

    private String reason;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    private String duration;

    private String parentsName;

    private String parentsNumber;

    private String permission;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date creatTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getOutRange() {
        return outRange;
    }

    public void setOutRange(String outRange) {
        this.outRange = outRange == null ? null : outRange.trim();
    }

    public String getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(String outLocation) {
        this.outLocation = outLocation == null ? null : outLocation.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName == null ? null : parentsName.trim();
    }

    public String getParentsNumber() {
        return parentsNumber;
    }

    public void setParentsNumber(String parentsNumber) {
        this.parentsNumber = parentsNumber == null ? null : parentsNumber.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", deptId='" + deptId + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", outRange='" + outRange + '\'' +
                ", outLocation='" + outLocation + '\'' +
                ", reason='" + reason + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration='" + duration + '\'' +
                ", parentsName='" + parentsName + '\'' +
                ", parentsNumber='" + parentsNumber + '\'' +
                ", permission='" + permission + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}