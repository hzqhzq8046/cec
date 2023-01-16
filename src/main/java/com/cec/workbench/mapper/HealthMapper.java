package com.cec.workbench.mapper;

import com.cec.settings.pojo.Student;
import com.cec.workbench.pojo.Health;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HealthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Health row);

    Health selectByPrimaryKey(Integer id);

    List<Health> selectAll();

    int updateByPrimaryKey(Health row);

    Health selectByUserId(Integer userId);

    List<Health> selectClockPageByCounsellorIdAndCondition(@Param("id") Integer id, @Param("clockTime") String clockTime, @Param("clazzId") String clazzId);

    List<Health> selectNotClockPageByCounsellorIdAndCondition(@Param("id") Integer id, @Param("clockTime") String clockTime, @Param("clazzId") String clazzId);

    List<Health> selectClockPageTagByCounsellorIdAndCondition(@Param("id") Integer id, @Param("clockTime") String clockTime, @Param("clazzId") String clazzId, @Param("tags") String[] tag);

    int selectClockCountTagByCounsellorIdAndCondition(@Param("id") Integer id, @Param("clockTime") String clockTime, @Param("clazzId") String clazzId, @Param("tags") String[] tags);

    int selectClockCountByCounsellorIdAndCondition(@Param("id") Integer id, @Param("clockTime") String clockTime, @Param("clazzId") String clazzId);

    int selectNotClockCountByCounsellorIdAndCondition(@Param("id") Integer id, @Param("clockTime") String clockTime, @Param("clazzId") String clazzId);


    List<Health> selectClockPageByDeptIdAndClazzIAndTag(@Param("clockTime") String clockTime,@Param("deptId") String deptId,@Param("clazzId") String clazzId,@Param("tags") String[] tag);

    Integer selectClockCountByDeptIdAndClazzIAndTag(@Param("clockTime") String clockTime,@Param("deptId") String deptId,@Param("clazzId") String clazzId,@Param("tags") String[] tag);

    List<Health> selectNotClockPageByDeptIdAndClazzId(@Param("clockTime") String clockTime,@Param("deptId") String deptId,@Param("clazzId") String clazzId);
    Integer selectNotClockCountByDeptIdAndClazzId(@Param("clockTime") String clockTime,@Param("deptId") String deptId,@Param("clazzId") String clazzId);

    List<Health> selectCounsellorClockPageByDeptIdAndTag(@Param("clockTime") String clockTime,@Param("deptId") String deptId,@Param("tags") String[] tag);

    Integer selectCounsellorClockCountByDeptIdAndTag(@Param("clockTime") String clockTime,@Param("deptId") String deptId,@Param("tags") String[] tag);

    List<Health> selectStaffClockPageByTag(@Param("clockTime") String clockTime,@Param("tags") String[] tag);

    Integer selectStaffClockCountByTag(@Param("clockTime") String clockTime,@Param("tags") String[] tag);

    List<Health> selectCounsellorNotClockPageByDeptId(@Param("clockTime") String clockTime,@Param("deptId") String deptId);

    Integer selectCounsellorNotClockCountByDeptId(@Param("clockTime") String clockTime,@Param("deptId") String deptId);

    List<Health> selectStaffNotClockPage(@Param("clockTime") String clockTime);

    Integer selectStaffNotClockCount(@Param("clockTime") String clockTime);
}
