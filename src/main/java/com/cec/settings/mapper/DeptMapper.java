package com.cec.settings.mapper;

import com.cec.settings.pojo.Dept;
import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dept row);

    Dept selectByPrimaryKey(String id);

    List<Dept> selectAll();

    int updateByPrimaryKey(Dept row);
}