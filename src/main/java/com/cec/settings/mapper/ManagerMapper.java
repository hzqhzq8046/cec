package com.cec.settings.mapper;

import com.cec.settings.pojo.Manager;
import com.cec.settings.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager row);

    Manager selectByPrimaryKey(Integer id);

    List<Manager> selectAll();

    int updateByPrimaryKey(Manager row);

    Manager selectByLoginNameAndLoginPassword (@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);

    Manager selectByLoginName (@Param("loginName") String loginName);
}