package com.cec.settings.web.controller;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.Staff;
import com.cec.settings.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/pages/settings/personal/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    @ResponseBody
    @RequestMapping("/personal")
    public Staff personal(HttpSession session) {
        return (Staff)session.getAttribute(Constants.STAFF);
    }

    @ResponseBody
    @RequestMapping("/editLoginPwd")
    public String editLoginPwd(HttpSession session,@RequestBody Map<String,Object> map) {
        String pass = (String)map.get("pass");
        Staff staff = (Staff) session.getAttribute(Constants.STAFF);
        staff.setLoginPwd(pass);
        int count = staffService.updateByPrimaryKey(staff);
        if (count == 1) {
            session.setAttribute(Constants.STAFF,staff);
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        }
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }


    @ResponseBody
    @RequestMapping("/editPersonal")
    public Staff editPersonal(HttpSession session,@RequestBody Staff staff) {
        int count = staffService.updateByPrimaryKey(staff);
        if (count == 1) {
            session.setAttribute(Constants.STAFF,staff);
            return staff;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws IOException {
        session.setAttribute(Constants.STAFF,null);
        return Constants.RETURN_OBJECT_CODE_SUCCESS;
    }


}
