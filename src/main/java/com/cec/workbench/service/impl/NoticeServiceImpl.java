package com.cec.workbench.service.impl;

import com.cec.workbench.mapper.NoticeMapper;
import com.cec.workbench.pojo.Notice;
import com.cec.workbench.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectByToId(Integer toId) {
        return noticeMapper.selectByToId(toId);
    }

    @Override
    public int updateByPrimaryKey(Notice notice) {
        return noticeMapper.updateByPrimaryKey(notice);
    }

    @Override
    public int selectNotReadCountById(Integer id) {
        int count = noticeMapper.selectNotReadCountById(id);
        System.out.println(count);
        return count;
    }

    @Override
    public int insertNotices(Notice notice, List<Integer> toIds) {
        int count = 0;
        for(int toId:toIds) {
            notice.setToId(toId);
            count += noticeMapper.insert(notice);
        }
        return count;
    }
}
