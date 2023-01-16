package com.cec.settings.service;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.mapper.StudentMapper;
import com.cec.settings.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student selectByLoginNameAndLoginPassword(String loginName, String loginPassword);

    Student selectByLoginName(String loginName);

    int insertBySubmitNameAndSubmitPassword(String submitName, String submitPassword);

    int updateByPrimaryKey(Student student);

    int insert(Student student);

    PageBean<Student> selectPageByCounsellorIdAndClazzId(Integer pageNum, Integer pageSize,Integer counsellorId, String clazzId,Integer studentId);

    PageBean<Map<String,Object>> selectPageByDeptIdAndClazzId(Integer pageNum, Integer pageSize, String deptId, String clazzId,Integer studentId);

    List<Integer> selectIds();
}
