package com.cec.workbench.web.controller.student;


import com.cec.commons.constants.Constants;
import com.cec.workbench.pojo.Notice;
import com.cec.workbench.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller("studentNoticeController")
@RequestMapping("/pages/workbench/student/content")
public class NoticeController {

    @Resource
    NoticeService noticeService;

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
}
