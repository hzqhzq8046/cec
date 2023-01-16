package com.cec.workbench.service.impl;


import com.cec.commons.utils.DateUtils;
import com.cec.workbench.mapper.ApplicationMapper;
import com.cec.workbench.pojo.Application;
import com.cec.commons.pojo.PageBean;
import com.cec.workbench.service.ApplicationService;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {
    @Resource
    ApplicationMapper applicationMapper;
    @Transactional
    @Override
    public int insert(Application application) {
        return applicationMapper.insert(application);
    }

    @Override
    public PageBean<Application> selectPageByPermissionAndDate(Integer studentId,Integer pageNum, Integer pageSize, String permission, String creatTime) {
        PageBean<Application> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Application> applications = applicationMapper.selectPageByStudentIdAndPermissionAndDate(studentId,permission,creatTime);
        int count = applicationMapper.selectCountByStudentIdAndPermissionAndDate(studentId,permission,creatTime);
        pageBean.setTotals(count);
        pageBean.setRows(applications);
        return pageBean;
    }
    @Transactional
    @Override
    public int deleteById(Integer id) {
        return applicationMapper.deleteByPrimaryKey(id);
    }
    @Transactional
    @Override
    public int updateByApplication(Application application) {
        return applicationMapper.updateByApplication(application);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public int updatePermissionByNowTimeAndStudentId(Date nowTime,Integer studentId) {
        String formateDateTime = DateUtils.formateDateTime(nowTime);
        return applicationMapper.updatePermissionByNowTimeAndStudentId(formateDateTime,studentId);
    }

    @Override
    public PageBean<Application> selectPageByCounsellorIdAndDateAndClazzId(Integer counsellorId, Integer pageNum, Integer pageSize, String creatTime, String clazzId) {
        PageBean<Application> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        pageBean.setRows(applicationMapper.selectPageByCounsellorIdAndDateAndClazzId(counsellorId,creatTime,clazzId));
        pageBean.setTotals(applicationMapper.selectCountByCounsellorIdAndDateAndClazzId(counsellorId,creatTime,clazzId));
        return pageBean;
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public int updatePermissionByNowTimeAndCounsellorId(Date nowTime, Integer counsellorId) {
        String formateDateTime = DateUtils.formateDateTime(nowTime);
        return applicationMapper.updatePermissionByNowTimeAndCounsellorId(formateDateTime,counsellorId);
    }
    @Transactional
    @Override
    public int updatePermissionById(Integer id, String permission) {
        return applicationMapper.updatePermissionById(id,permission);
    }

    @Override
    public PageBean<Application> selectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(Integer counsellorId, Integer pageNum, Integer pageSize, String creatTime,
                                                                                          String clazzId,String permission) {
        PageBean<Application> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        pageBean.setRows(applicationMapper.selectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(counsellorId,creatTime,clazzId,permission));
        pageBean.setTotals(applicationMapper.selectCountApprovalRecordsByCounsellorIdAndDateAndClazzId(counsellorId,creatTime,clazzId,permission));
        return pageBean;
    }


}
