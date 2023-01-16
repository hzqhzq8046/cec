package com.cec.workbench.service.impl;

import com.cec.commons.constants.Constants;
import com.cec.commons.pojo.ClockBean;
import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Student;
import com.cec.workbench.mapper.HealthMapper;
import com.cec.workbench.pojo.Health;
import com.cec.workbench.service.HealthService;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("healthService")
public class HealthServiceImpl implements HealthService {
    @Resource
    HealthMapper healthMapper;

    @Transactional
    @Override
    public int insert(Health health) {
        Health temp = healthMapper.selectByUserId(health.getUserId());
        if (temp != null)
            return 0;
        return healthMapper.insert(health);
    }

    @Override
    public Health selectByUserId(Integer userId) {
        return healthMapper.selectByUserId(userId);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public ClockBean<Health> selectClockAndNotClockPageByCounsellorIdAndCondition(Integer counsellorId, Integer pageNum, Integer pageSize, String clockTime, String clazzId, String clockType) {
        ClockBean<Health> clockBean = new ClockBean<>();
        PageHelper.startPage(pageNum, pageSize);
        if (Constants.CHECK_SUCCESS.equals(clockType)) {
            clockBean.setClocked(healthMapper.selectClockPageByCounsellorIdAndCondition(counsellorId, clockTime, clazzId));
            clockBean.setTotals(healthMapper.selectClockCountByCounsellorIdAndCondition(counsellorId, clockTime, clazzId));
        } else if (Constants.CHECK_FAIL.equals(clockType)) {
            clockBean.setClocked(healthMapper.selectNotClockPageByCounsellorIdAndCondition(counsellorId, clockTime, clazzId));
            clockBean.setTotals(healthMapper.selectNotClockCountByCounsellorIdAndCondition(counsellorId, clockTime, clazzId));
        }
        return clockBean;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public ClockBean<Health> selectClockAndNotClockPageByCounsellorIdAndConditionAndTag(Integer counsellorId, Integer pageNum, Integer pageSize, String clockTime, String clazzId, String clockType, String[] tag) {
        ClockBean<Health> clockBean = new ClockBean<>();
        PageHelper.startPage(pageNum, pageSize);
        if (Constants.CHECK_SUCCESS.equals(clockType)) {
            clockBean.setClocked(healthMapper.selectClockPageTagByCounsellorIdAndCondition(counsellorId, clockTime, clazzId, tag));
            clockBean.setTotals(healthMapper.selectClockCountTagByCounsellorIdAndCondition(counsellorId, clockTime, clazzId, tag));
        } else if (Constants.CHECK_FAIL.equals(clockType)) {
            clockBean.setClocked(healthMapper.selectNotClockPageByCounsellorIdAndCondition(counsellorId, clockTime, clazzId));
            clockBean.setTotals(healthMapper.selectNotClockCountByCounsellorIdAndCondition(counsellorId, clockTime, clazzId));
        }
        return clockBean;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public PageBean<Health> selectClockAndNotClockPageByDeptIdAndClazzIAndTag(Integer pageNum, Integer pageSize, String clockTime, String deptId, String clazzId, String clockType, String[] tag) {
        PageBean<Health> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        if (Constants.CHECK_SUCCESS.equals(clockType)) {
            pageBean.setRows(healthMapper.selectClockPageByDeptIdAndClazzIAndTag(clockTime,deptId,clazzId,tag));
            pageBean.setTotals(healthMapper.selectClockCountByDeptIdAndClazzIAndTag(clockTime,deptId,clazzId,tag));
        } else if (Constants.CHECK_FAIL.equals(clockType)) {
            pageBean.setRows(healthMapper.selectNotClockPageByDeptIdAndClazzId(clockTime,deptId,clazzId));
            pageBean.setTotals(healthMapper.selectNotClockCountByDeptIdAndClazzId(clockTime,deptId,clazzId));
        }
        return pageBean;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public PageBean<Health> selectStaffClockAndNotClockPageByDeptIdAndTag(Integer pageNum, Integer pageSize, String clockTime, String staffType, String deptId, String clockType, String[] tag) {
        PageBean<Health> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        if (Constants.CHECK_SUCCESS.equals(clockType)) {
            if(Constants.COUNSELLOR_TYPE.equals(staffType)){
                pageBean.setRows(healthMapper.selectCounsellorClockPageByDeptIdAndTag(clockTime,deptId,tag));
                pageBean.setTotals(healthMapper.selectCounsellorClockCountByDeptIdAndTag(clockTime,deptId,tag));
            }else if (Constants.STAFF_TYPE.equals(staffType)){
                pageBean.setRows(healthMapper.selectStaffClockPageByTag(clockTime,tag));
                pageBean.setTotals(healthMapper.selectStaffClockCountByTag(clockTime,tag));
            }
        } else if (Constants.CHECK_FAIL.equals(clockType)) {
            if(Constants.COUNSELLOR_TYPE.equals(staffType)){
                pageBean.setRows(healthMapper.selectCounsellorNotClockPageByDeptId(clockTime,deptId));
                pageBean.setTotals(healthMapper.selectCounsellorNotClockCountByDeptId(clockTime,deptId));
            }else if (Constants.STAFF_TYPE.equals(staffType)){
                pageBean.setRows(healthMapper.selectStaffNotClockPage(clockTime));
                pageBean.setTotals(healthMapper.selectStaffNotClockCount(clockTime));
            }
        }
        return pageBean;
    }
}
