package com.cec.workbench.web.controller.staff;

import com.cec.commons.constants.Constants;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Staff;
import com.cec.workbench.pojo.Health;
import com.cec.workbench.service.HealthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/pages/workbench/staff/health")
@Controller("staffHealthController")
public class HealthController {
    @Resource
    HealthService healthService;
    @ResponseBody
    @RequestMapping("/health")
    //String morningCheck,String afternoonCheck,String inSchool,String inRisk,String inIsolation
    public String health(@RequestBody Health health, HttpSession session){
        Staff staff = (Staff) session.getAttribute(Constants.STAFF);
        health.setUserId(staff.getId());
        health.setUserName(staff.getName());
        if(healthService.insert(health) == 1)
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }
    @ResponseBody
    @RequestMapping("/checkClock")
    public String checkClock(HttpSession session){
        Staff staff = (Staff) session.getAttribute(Constants.STAFF);
        if (healthService.selectByUserId(staff.getId()) == null)
            return Constants.CHECK_FAIL;
        return Constants.CHECK_SUCCESS;
    }
}
