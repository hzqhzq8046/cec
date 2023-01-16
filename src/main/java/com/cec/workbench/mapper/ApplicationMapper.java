package com.cec.workbench.mapper;

import com.cec.settings.pojo.Counsellor;
import com.cec.workbench.pojo.Application;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Application row);

    Application selectByPrimaryKey(Integer id);

    List<Application> selectAll();

    int updateByPrimaryKey(Application row);

    List<Application> selectPageByStudentIdAndPermissionAndDate(@Param("studentId") Integer studentId,@Param("permission") String permission,@Param("creatTime") String creatTime);
    int selectCountByStudentIdAndPermissionAndDate(@Param("studentId") Integer studentId,@Param("permission") String permission,@Param("creatTime") String creatTime);

    int updateByApplication(Application application);

    int updatePermissionByNowTimeAndStudentId(@Param("nowTime") String nowTime,@Param("studentId") Integer studentId);

    List<Application> selectPageByCounsellorIdAndDateAndClazzId(@Param("counsellorId")Integer counsellorId , @Param("creatTime") String date,@Param("clazzId")String clazzId);

    int selectCountByCounsellorIdAndDateAndClazzId(@Param("counsellorId")Integer counsellorId , @Param("creatTime") String date , @Param("clazzId")String clazzId);

    int updatePermissionByNowTimeAndCounsellorId(@Param("nowTime") String nowTime, @Param("counsellorId")Integer counsellorId);

    int updatePermissionById(@Param("id") Integer id,@Param("permission") String permission);

    List<Application> selectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(@Param("counsellorId")Integer counsellorId,@Param("creatTime") String creatTime,@Param("clazzId") String clazzId,@Param("permission") String permission);

    Integer selectCountApprovalRecordsByCounsellorIdAndDateAndClazzId(@Param("counsellorId")Integer counsellorId,@Param("creatTime") String creatTime,@Param("clazzId") String clazzId,@Param("permission") String permission);
}