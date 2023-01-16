package com.cec.workbench.web.controller.counsellor;

import com.cec.commons.constants.Constants;
import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Student;
import com.cec.settings.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/pages/workbench/counsellor/message")
@Controller("counsellorMessageController")
public class MessageController {
    @Resource
    StudentService studentService;
    @ResponseBody
    @RequestMapping("/queryPageStudents")
    public PageBean<Student> queryPageStudents(Integer pageNum, Integer pageSize, String clazzId,Integer studentId ,HttpSession session){
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        return studentService.selectPageByCounsellorIdAndClazzId(pageNum, pageSize,counsellor.getId(),clazzId,studentId);
    }
}
