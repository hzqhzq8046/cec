package com.cec.workbench.service;

import com.cec.workbench.pojo.Application;
import com.cec.commons.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ApplicationService {
    int insert(Application application);
    PageBean<Application> selectPageByPermissionAndDate(Integer studentId,Integer pageNum, Integer pageSize, String permission, String creatTime);

    int deleteById(Integer id);

    int updateByApplication(Application application);

    int updatePermissionByNowTimeAndStudentId(Date nowTime,Integer studentId);
    PageBean<Application> selectPageByCounsellorIdAndDateAndClazzId(Integer counsellorId,Integer pageNum, Integer pageSize,String creatTime,String clazzId);
    int updatePermissionByNowTimeAndCounsellorId(Date nowTime,Integer counsellorId);

    int updatePermissionById(Integer id,String permission);


    PageBean<Application> selectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(Integer counsellorId, Integer pageNum, Integer pageSize, String creatTime, String clazzId,String permission);
}

