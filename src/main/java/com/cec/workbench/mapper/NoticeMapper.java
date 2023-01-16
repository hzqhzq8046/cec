package com.cec.workbench.mapper;

import com.cec.workbench.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice row);

    Notice selectByPrimaryKey(Integer id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice row);

    List<Notice> selectByToId(@Param("toId") Integer toId);

    int selectNotReadCountById(@Param("id") Integer id);

}