package com.cec.workbench.web.controller.manager;

import com.cec.commons.constants.Constants;
import com.cec.settings.service.CounsellorService;
import com.cec.settings.service.StaffService;
import com.cec.settings.service.StudentService;
import com.cec.workbench.pojo.Notice;
import com.cec.workbench.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("managerNoticeController")
@RequestMapping("/pages/workbench/manager/content")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @Resource
    StudentService studentService;

    @Resource
    StaffService staffService;

    @Resource
    CounsellorService counsellorService;

    @ResponseBody
    @RequestMapping("/sendNotice")
    public int sendNotice(@RequestBody Map<String,Object> map) {
        String acceptor = (String)map.get("acceptor");
        List<Integer> ids1 = staffService.selectIds();
        List<Integer> ids2 = studentService.selectIds();
        List<Integer> ids3 = counsellorService.selectIds();
        List<Integer> ids = new ArrayList<>();
        if("6".equals(acceptor)) {
            ids.addAll(ids1);
            ids.addAll(ids2);
            ids.addAll(ids3);
        } else if("4".equals(acceptor)) {
            ids.addAll(ids1);
            ids.addAll(ids3);
        } else if("3".equals(acceptor)) {
            ids = ids3;
        } else if("2".equals(acceptor)) {
            ids = ids2;
        } else if("1".equals(acceptor)) {
            ids = ids1;
        }
        Notice notice = new Notice();
        notice.setFromId((Integer) map.get("fromId"));
        notice.setFromName((String) map.get("fromName"));
        notice.setTitle((String) map.get("title"));
        notice.setContent((String) map.get("content"));
        return noticeService.insertNotices(notice,ids);
    }
}
