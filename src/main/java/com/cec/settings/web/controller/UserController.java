package com.cec.settings.web.controller;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.*;
import com.cec.settings.service.CounsellorService;
import com.cec.settings.service.ManagerService;
import com.cec.settings.service.StaffService;
import com.cec.settings.service.StudentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/pages")
public class UserController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private CounsellorService counsellorService;

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpSession session, @RequestBody User user) {

        Student student;
        Manager manager;
        Staff staff;
        Counsellor counsellor;

        if((student = studentService.selectByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword())) != null ) {
            session.setAttribute(Constants.STUDENT, student);
            return Constants.STUDENT;
        } else if ((manager = managerService.selectByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword())) != null ){
            session.setAttribute(Constants.MANAGER, manager);
            return Constants.MANAGER;
        } else if ((staff = staffService.selectByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword())) != null ){
            session.setAttribute(Constants.STAFF, staff);
            return Constants.STAFF;
        } else if ((counsellor = counsellorService.selectByLoginNameAndLoginPassword(user.getLoginName(), user.getLoginPassword())) != null ){
            session.setAttribute(Constants.COUNSELLOR, counsellor);
            return Constants.COUNSELLOR;
        } else {
            return Constants.RETURN_OBJECT_CODE_FAIL;
        }
    }

    @ResponseBody
    @RequestMapping("/checkSubmitName")
    public Integer checkSubmitName(@RequestBody Map<String,Object> map) {
        if (studentService.selectByLoginName((String) map.get("submitName"))==null && staffService.selectByLoginName((String) map.get("submitName"))==null
                && managerService.selectByLoginName((String) map.get("submitName"))==null && counsellorService.selectByLoginName((String) map.get("submitName"))==null) {
            return 1;
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/submit")
    public Integer submit(@RequestBody Map<String,Object> map) {
        String submitName = (String) map.get("submitName");
        String submitPassword = (String) map.get("submitPassword");
        String identity = (String) map.get("identity");
        if("1".equals(identity)) {
            Student student = new Student();
            student.setLoginName(submitName);
            student.setLoginPwd(submitPassword);
            student.setName("Student");
            student.setSex("??????");
            student.setAddress("??????");
            student.setAge(0);
            student.setOrigin("??????");
            student.setPhoneNumber("??????");
            student.setClazzId("??????");
            student.setClazzName("??????????????????");
            return studentService.insert(student);
        } else {
            Staff staff = new Staff();
            staff.setLoginName(submitName);
            staff.setLoginPwd(submitPassword);
            staff.setName("Staff");
            staff.setSex("??????");
            staff.setAddress("??????");
            staff.setAge(0);
            staff.setOrigin("??????");
            staff.setPhoneNumber("??????");
            return staffService.insert(staff);
        }
    }
}
