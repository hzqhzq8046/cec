package com.cec.workbench.web.controller.manager;

import com.cec.commons.constants.Constants;
import com.cec.commons.pojo.PageBean;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Dept;
import com.cec.settings.pojo.Student;
import com.cec.settings.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/pages/workbench/manager/message")
@Controller("managerMessageController")
public class MessageController {
    @Resource
    DeptService deptService;
    @Resource
    ClazzService clazzService;
    @Resource
    StudentService studentService;
    @Resource
    CounsellorService counsellorService;
    @Resource
    StaffService staffService;
    @ResponseBody
    @RequestMapping("/showDepts")
    public List<Dept> showDepts(){
        return deptService.selectAll();
    }
    @ResponseBody
    @RequestMapping("/showClazzs")
    public List<Clazz> showClazz(String deptId){
        return clazzService.selectByDeptId(deptId);
    }
    @ResponseBody
    @RequestMapping("/queryPageStudents")
    public PageBean<Map<String,Object>> queryPageStudents(Integer pageNum, Integer pageSize, String deptId, String clazzId,Integer studentId){
        return studentService.selectPageByDeptIdAndClazzId(pageNum,pageSize,deptId,clazzId,studentId);
    }
    @ResponseBody
    @RequestMapping("/queryPageStaffAndCounsellor")
    public PageBean<Map<String,Object>> queryPageStaffAndCounsellor(Integer pageNum, Integer pageSize,String staffType,String deptId,Integer staffId){
        if(Constants.COUNSELLOR_TYPE.equals(staffType)){
            return counsellorService.selectPageByDeptIdAndStaffId(pageNum, pageSize, deptId,staffId);
        }else{
            return staffService.selectPageByStaffId(pageNum,pageSize,staffId);
        }
    }

}
