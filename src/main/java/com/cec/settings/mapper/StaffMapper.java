package com.cec.settings.mapper;

import com.cec.settings.pojo.Staff;
import com.cec.settings.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Staff row);

    Staff selectByPrimaryKey(Integer id);

    List<Staff> selectAll();

    int updateByPrimaryKey(Staff row);

    Staff selectByLoginNameAndLoginPassword (@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);

    Staff selectByLoginName (@Param("loginName") String loginName);

    int insertBySubmitNameAndSubmitPassword(@Param("submitName") String submitName,@Param("submitPassword") String submitPassword);

    List<Map<String,Object>> selectPageByStaffId(Integer staffId);

    int selectCountByStaffId(Integer staffId);
    List<Integer> selectIds();
}