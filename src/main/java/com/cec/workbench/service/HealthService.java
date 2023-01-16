package com.cec.workbench.service;

import com.cec.commons.pojo.ClockBean;
import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Student;
import com.cec.workbench.pojo.Health;

import java.util.Date;
import java.util.List;

public interface HealthService {
    int insert(Health health);
    Health selectByUserId(Integer userId);

    ClockBean<Health> selectClockAndNotClockPageByCounsellorIdAndCondition(Integer counsellorId, Integer pageNum, Integer pageSize, String clockTime, String clazzId, String clockType);

    ClockBean<Health> selectClockAndNotClockPageByCounsellorIdAndConditionAndTag(Integer counsellorId, Integer pageNum, Integer pageSize, String clockTime, String clazzId, String clockType,String[] tag);

    PageBean<Health> selectClockAndNotClockPageByDeptIdAndClazzIAndTag(Integer pageNum, Integer pageSize, String clockTime, String deptId, String clazzId, String clockType,String[] tag);

    PageBean<Health> selectStaffClockAndNotClockPageByDeptIdAndTag(Integer pageNum, Integer pageSize, String clockTime, String staffType, String deptId, String clockType, String[] tag);
}
