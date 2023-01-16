package com.cec.settings.service;

import com.cec.settings.pojo.Clazz;

import java.util.List;

public interface ClazzService {

    List<Clazz> selectAll();

    List<Clazz> selectByDeptId(String DeptId);

    Clazz selectByPrimaryKey(String id);
}
