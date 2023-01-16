package com.cec.settings.service.impl;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.mapper.StudentMapper;
import com.cec.settings.pojo.Student;
import com.cec.settings.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student selectByLoginNameAndLoginPassword(String loginName, String loginPassword) {
        return studentMapper.selectByLoginNameAndLoginPassword(loginName, loginPassword);
    }

    @Override
    public Student selectByLoginName(String loginName) {
        Student student = studentMapper.selectByLoginName(loginName);
        return student;
    }

    @Override
    public int insertBySubmitNameAndSubmitPassword(String submitName, String submitPassword) {
        return studentMapper.insertBySubmitNameAndSubmitPassword(submitName,submitPassword);
    }

    @Override
    public int updateByPrimaryKey(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public PageBean<Student> selectPageByCounsellorIdAndClazzId(Integer pageNum,Integer pageSize,Integer counsellorId,String clazzId,Integer studentId) {
        PageBean<Student> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        pageBean.setRows(studentMapper.selectPageByCounsellorIdAndClazzId(counsellorId,clazzId,studentId));
        pageBean.setTotals(studentMapper.selectCountByCounsellorIdAndClazzId(counsellorId,clazzId,studentId));
        return pageBean;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public PageBean<Map<String,Object>> selectPageByDeptIdAndClazzId(Integer pageNum, Integer pageSize, String deptId, String clazzId,Integer studentId) {
        PageBean<Map<String,Object>> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        pageBean.setRows(studentMapper.selectPageByDeptIdAndClazzIdAndStudentId(deptId,clazzId,studentId));
        pageBean.setTotals(studentMapper.selectCountByDeptIdAndClazzIdAndStudentId(deptId,clazzId,studentId));
        return pageBean;
    }
    @Override
    public List<Integer> selectIds() {
        return studentMapper.selectIds();
    }
}
