package com.cec.workbench.web.controller.student;

import com.cec.commons.constants.Constants;
import com.cec.commons.utils.DateUtils;
import com.cec.settings.pojo.Dept;
import com.cec.settings.pojo.Student;
import com.cec.settings.service.DeptService;
import com.cec.workbench.pojo.Application;
import com.cec.commons.pojo.PageBean;
import com.cec.workbench.service.ApplicationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RequestMapping("/pages/workbench/student/application")
@Controller("studentApplicationController")
public class ApplicationController {
    @Resource
    DeptService deptService;
    @Resource
    ApplicationService applicationService;

    @ResponseBody
    @RequestMapping("/showDepts")
    public List<Dept> showDepts(){
        return deptService.selectAll();
    }

    @ResponseBody
    @RequestMapping("/applicate")
    public String applicate(@RequestBody Application application, HttpSession session){
        Student student = (Student) session.getAttribute(Constants.STUDENT);
        application.setStudentId(student.getId());
        application.setStudentName(student.getName());
        application.setSex(student.getSex());
        application.setStartTime(DateUtils.subDay(application.getStartTime()));
        application.setEndTime(DateUtils.subDay(application.getEndTime()));
        if (applicationService.insert(application) == 1) {
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        }
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }
    @ResponseBody
    @RequestMapping("/pageQuery")
    public PageBean<Application> pageQuery(Integer pageNum, Integer pageSize, String permission, @DateTimeFormat(pattern = "yyyy-MM-dd") Date creatTime, HttpSession session) throws ParseException {
        Student student = (Student) session.getAttribute(Constants.STUDENT);
        String formateDateTime = null;
        if (creatTime != null){
            formateDateTime = DateUtils.formateDateTime(creatTime);
            formateDateTime = DateUtils.subDay(formateDateTime);
        }
        return applicationService.selectPageByPermissionAndDate(student.getId(), pageNum, pageSize, permission, formateDateTime);
    }
    @ResponseBody
    @RequestMapping("/recall")
    public String recall(@RequestBody Application application){
        if (applicationService.deleteById(application.getId()) == 1)
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }
    @ResponseBody
    @RequestMapping("/modify")
    public String modify(@RequestBody Application application){
        if (applicationService.updateByApplication(application) == 1)
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }
    @ResponseBody
    @RequestMapping("/checkCreatTime")
    public void checkCreatTime(HttpSession session){
        Student student = (Student) session.getAttribute(Constants.STUDENT);
        applicationService.updatePermissionByNowTimeAndStudentId(new Date(),student.getId());
    }
    @ResponseBody
    @RequestMapping("/republish")
    public void republish(@RequestBody Application application,HttpSession session){
        System.out.println(application);
        session.setAttribute(Constants.APPLICATION,application);
    }
    @ResponseBody
    @RequestMapping("/queryApplication")
    public Application queryApplication(HttpSession session){
        Application application = (Application) session.getAttribute(Constants.APPLICATION);
        System.out.println(application);
        session.removeAttribute(Constants.APPLICATION);
        return application;
    }
}
