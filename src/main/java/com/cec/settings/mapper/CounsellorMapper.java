package com.cec.settings.mapper;

import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CounsellorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Counsellor row);

    Counsellor selectByPrimaryKey(Integer id);

    List<Counsellor> selectAll();

    int updateByPrimaryKey(Counsellor row);

    Counsellor selectByLoginNameAndLoginPassword (@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);

    Counsellor selectByLoginName (@Param("loginName") String loginName);

    List<Clazz> selectClazzById(Integer id);

    List<Map<String,Object>> selectPageByDeptIdAndStaffId(@Param("deptId") String deptId,@Param("staffId") Integer staffId);

    Integer selectCountByDeptIdAndStaffId(@Param("deptId") String deptId,@Param("staffId") Integer staffId);

    Map<String,Object> selectIncludeDeptNameByPrimaryKey(Integer id);

    List<Integer> selectIds();
}