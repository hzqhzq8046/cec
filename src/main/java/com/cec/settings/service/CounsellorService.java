package com.cec.settings.service;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Student;

import java.util.List;
import java.util.Map;

public interface CounsellorService {

    Counsellor selectByLoginNameAndLoginPassword(String loginName, String loginPassword);

    Counsellor selectByLoginName(String loginName);

    List<Clazz> selectClazzById(Integer id);

    PageBean<Map<String, Object>> selectPageByDeptIdAndStaffId(Integer pageNum, Integer pageSize, String deptId, Integer staffId);

    int updateByPrimaryKey(Counsellor counsellor);

    int insert(Counsellor counsellor);

    Map<String, Object> selectIncludeDeptNameByPrimaryKey(Integer id);

    List<Integer> selectIds();
}
