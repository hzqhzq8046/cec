package com.cec.workbench.service;

import com.cec.workbench.pojo.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> selectByToId(Integer toId);

    int updateByPrimaryKey(Notice notice);

    int selectNotReadCountById(Integer id);

    int insertNotices(Notice notice,List<Integer> toIds);

}
