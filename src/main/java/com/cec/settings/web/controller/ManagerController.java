package com.cec.settings.web.controller;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Dept;
import com.cec.settings.pojo.Manager;
import com.cec.settings.service.ClazzService;
import com.cec.settings.service.CounsellorService;
import com.cec.settings.service.DeptService;
import com.cec.settings.service.ManagerService;
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
@RequestMapping("/pages/settings/personal/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @ResponseBody
    @RequestMapping("/personal")
    public Manager personal(HttpSession session) {
        return (Manager) session.getAttribute(Constants.MANAGER);
    }

    @ResponseBody
    @RequestMapping("/editLoginPwd")
    public String editLoginPwd(HttpSession session,@RequestBody Map<String,Object> map) {
        String pass = (String)map.get("pass");
        Manager manager = (Manager)session.getAttribute(Constants.MANAGER);
        manager.setLoginPwd(pass);
        int count = managerService.updateByPrimaryKey(manager);
        if (count == 1) {
            session.setAttribute(Constants.MANAGER,manager);
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        }
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws IOException {
        session.setAttribute(Constants.COUNSELLOR,null);
        return Constants.RETURN_OBJECT_CODE_SUCCESS;
    }


}
