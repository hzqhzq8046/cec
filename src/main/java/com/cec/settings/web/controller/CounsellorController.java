package com.cec.settings.web.controller;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.*;
import com.cec.settings.service.ClazzService;
import com.cec.settings.service.CounsellorService;
import com.cec.settings.service.DeptService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages/settings/personal/counsellor")
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ClazzService clazzService;

    @ResponseBody
    @RequestMapping("/personalIncludeDeptName")
    public Map<String,Object> personalIncludeDeptName(@RequestBody Map<String,Object> map) {
        Integer id = (Integer) map.get("id");
        Map<String,Object> map1 = counsellorService.selectIncludeDeptNameByPrimaryKey(id);
        System.out.println(map1);
        return map1;
    }

    @ResponseBody
    @RequestMapping("/personal")
    public Counsellor personal(HttpSession session) {
        return (Counsellor) session.getAttribute(Constants.COUNSELLOR);
    }

    @ResponseBody
    @RequestMapping("/editLoginPwd")
    public String editLoginPwd(HttpSession session,@RequestBody Map<String,Object> map) {
        String pass = (String)map.get("pass");
        Counsellor counsellor = (Counsellor)session.getAttribute(Constants.COUNSELLOR);
        counsellor.setLoginPwd(pass);
        int count = counsellorService.updateByPrimaryKey(counsellor);
        if (count == 1) {
            session.setAttribute(Constants.COUNSELLOR,counsellor);
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        }
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }

    @ResponseBody
    @RequestMapping("/editPersonal")
    public Map<String,Object> editPersonal(HttpSession session,@RequestBody Counsellor counsellor) {
        int count = counsellorService.updateByPrimaryKey(counsellor);
        if (count == 1) {
            session.setAttribute(Constants.COUNSELLOR,counsellor);
            return counsellorService.selectIncludeDeptNameByPrimaryKey(counsellor.getId());
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
        session.setAttribute(Constants.COUNSELLOR,null);
        return Constants.RETURN_OBJECT_CODE_SUCCESS;
    }


}
