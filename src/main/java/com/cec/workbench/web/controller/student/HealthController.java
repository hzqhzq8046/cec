package com.cec.workbench.web.controller.student;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.Student;
import com.cec.workbench.pojo.Health;
import com.cec.workbench.service.HealthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping("/pages/workbench/student/health")
@Controller("studentHealthController")
public class HealthController {
    @Resource
    HealthService healthService;

    @ResponseBody
    @RequestMapping("/health")
    //String morningCheck,String afternoonCheck,String inSchool,String inRisk,String inIsolation
    public String health(@RequestBody Health health, HttpSession session){
        Student student = (Student) session.getAttribute(Constants.STUDENT);
        health.setUserId(student.getId());
        health.setUserName(student.getName());
        health.setClazzId(student.getClazzId());
        health.setClazzName(student.getClazzName());
        if(healthService.insert(health) == 1)
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }
    @ResponseBody
    @RequestMapping("/checkClock")
    public String checkClock(HttpSession session){
        Student student = (Student) session.getAttribute(Constants.STUDENT);
        if (healthService.selectByUserId(student.getId()) == null)
            return Constants.CHECK_FAIL;
        return Constants.CHECK_SUCCESS;
    }
}
