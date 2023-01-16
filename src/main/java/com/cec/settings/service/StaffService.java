package com.cec.settings.service;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Staff;
import com.cec.settings.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StaffService {

    Staff selectByLoginNameAndLoginPassword(String loginName, String loginPassword);

    Staff selectByLoginName(String loginName);

    int insertBySubmitNameAndSubmitPassword(String submitName, String submitPassword);

    int insert(Staff staff);

    PageBean<Map<String, Object>> selectPageByStaffId(Integer pageNum, Integer pageSize,Integer staffId);

    List<Integer> selectIds();

    int updateByPrimaryKey(Staff staff);
}
