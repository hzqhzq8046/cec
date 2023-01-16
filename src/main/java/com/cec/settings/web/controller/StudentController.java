package com.cec.settings.web.controller;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Dept;
import com.cec.settings.pojo.Student;
import com.cec.settings.pojo.User;
import com.cec.settings.service.ClazzService;
import com.cec.settings.service.DeptService;
import com.cec.settings.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages/settings/personal/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ClazzService clazzService;

    @ResponseBody
    @RequestMapping("/personal")
    public Student personal(HttpSession session) {
        return (Student)session.getAttribute(Constants.STUDENT);
    }

    @ResponseBody
    @RequestMapping("/editLoginPwd")
    public String editLoginPwd(HttpSession session,@RequestBody Map<String,Object> map) {
        String pass = (String)map.get("pass");
        Student student = (Student)session.getAttribute(Constants.STUDENT);
        student.setLoginPwd(pass);
        int count = studentService.updateByPrimaryKey(student);
        if (count == 1) {
            session.setAttribute(Constants.STUDENT,student);
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        }
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }


    @ResponseBody
    @RequestMapping("/editPersonal")
    public Student editPersonal(HttpSession session,@RequestBody Student student) {
        int count = studentService.updateByPrimaryKey(student);
        if (count == 1) {
            session.setAttribute(Constants.STUDENT,student);
            return student;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/selectDept")
    public List<Dept> selectDept() {
        return deptService.selectAll();
    }

    @ResponseBody
    @RequestMapping("/selectClazz")
    public List<Clazz> selectClazz(@RequestBody Dept dept) {
        return clazzService.selectByDeptId(dept.getId());
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute(Constants.STUDENT,null);
//        response.sendRedirect(request.getContextPath()+"/pages/login.html");
        return Constants.RETURN_OBJECT_CODE_SUCCESS;
    }


    }
