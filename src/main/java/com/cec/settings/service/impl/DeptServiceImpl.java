package com.cec.settings.service.impl;

import com.cec.settings.mapper.DeptMapper;
import com.cec.settings.pojo.Dept;
import com.cec.settings.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptMapper deptMapper;
    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @Override
    public Dept selectById(String deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }
}
