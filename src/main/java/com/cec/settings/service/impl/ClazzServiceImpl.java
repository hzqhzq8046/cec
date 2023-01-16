package com.cec.settings.service.impl;

import com.cec.settings.mapper.ClazzMapper;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.service.ClazzService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {

    @Resource
    ClazzMapper clazzMapper;

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }

    @Override
    public List<Clazz> selectByDeptId(String DeptId) {
        return clazzMapper.selectByDeptId(DeptId);
    }

    @Override
    public Clazz selectByPrimaryKey(String id) {
        return clazzMapper.selectByPrimaryKey(id);
    }
}
