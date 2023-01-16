package com.cec.workbench.web.controller.counsellor;


import com.cec.commons.constants.Constants;
import com.cec.settings.service.StudentService;
import com.cec.workbench.pojo.Notice;
import com.cec.workbench.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller("counsellorNoticeController")
@RequestMapping("/pages/workbench/counsellor/content")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @Resource
    StudentService studentService;

    @ResponseBody
    @RequestMapping("/getNotices")
    public List<Notice> getNotices(@RequestBody Map<String,Integer> map) {
        Integer toId = map.get("toId");
        return noticeService.selectByToId(toId);
    }

    @ResponseBody
    @RequestMapping("/readNotice")
    public String readNotice(@RequestBody Notice notice) {
        notice.setIsRead((byte) 1);
        int count = noticeService.updateByPrimaryKey(notice);
        if (count == 1) {
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        }
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }

    @ResponseBody
    @RequestMapping("/getNotReadNoticesCount")
    public int getNotReadNoticesCount(@RequestBody Map<String,Integer> map) {
        Integer id = map.get("id");
        return noticeService.selectNotReadCountById(id);
    }

    @ResponseBody
    @RequestMapping("/sendNotice")
    public int sendNotice(@RequestBody Map<String,Object> map) {
        String acceptor = (String)map.get("acceptor");
        List<Integer> ids = null;
        if("1".equals(acceptor)) {
            ids = studentService.selectIds();
        }
        Notice notice = new Notice();
        notice.setFromId((Integer) map.get("fromId"));
        notice.setFromName((String) map.get("fromName"));
        notice.setTitle((String) map.get("title"));
        notice.setContent((String) map.get("content"));
        return noticeService.insertNotices(notice,ids);
    }
}
