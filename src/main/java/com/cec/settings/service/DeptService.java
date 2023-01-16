package com.cec.settings.service;

import com.cec.settings.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> selectAll();

    Dept selectById(String deptId);
}
