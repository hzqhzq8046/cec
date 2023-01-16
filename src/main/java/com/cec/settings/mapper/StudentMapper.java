package com.cec.settings.mapper;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student row);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student row);

    Student selectByLoginNameAndLoginPassword(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);

    Student selectByLoginName(@Param("loginName") String loginName);

    int insertBySubmitNameAndSubmitPassword(@Param("submitName") String submitName, @Param("submitPassword") String submitPassword);

    List<Student> selectPageByCounsellorIdAndClazzId(@Param("counsellerId") Integer counsellerId,@Param("clazzId") String clazzId,@Param("studentId") Integer studentId);

    int selectCountByCounsellorIdAndClazzId(@Param("counsellerId") Integer counsellerId,@Param("clazzId") String clazzId,@Param("studentId") Integer studentId);

    List<Map<String,Object>> selectPageByDeptIdAndClazzIdAndStudentId(@Param("deptId") String deptId, @Param("clazzId") String clazzId,@Param("studentId") Integer studentId);

    Integer selectCountByDeptIdAndClazzIdAndStudentId(@Param("deptId") String deptId,@Param("clazzId") String clazzId,@Param("studentId") Integer studentId);

    List<Integer> selectIds();
}