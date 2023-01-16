package com.cec.settings.mapper;

import com.cec.settings.pojo.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClazzMapper {
    int deleteByPrimaryKey(String id);

    int insert(Clazz row);

    Clazz selectByPrimaryKey(String id);

    List<Clazz> selectAll();

    List<Clazz> selectByDeptId(@Param("deptId") String deptId);

    int updateByPrimaryKey(Clazz row);
}