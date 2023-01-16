package com.cec.workbench.web.controller.counsellor;

import com.cec.commons.constants.Constants;
import com.cec.commons.pojo.PageBean;
import com.cec.commons.utils.DateUtils;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Student;
import com.cec.settings.service.ClazzService;
import com.cec.settings.service.CounsellorService;
import com.cec.workbench.pojo.Application;
import com.cec.workbench.service.ApplicationService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
@RequestMapping("/pages/workbench/counsellor/approval")
@Controller("counsellorApplicationController")
public class ApplicationController {
    @Resource
    ApplicationService applicationService;
    @Resource
    ClazzService clazzService;
    @Resource
    CounsellorService counsellorService;
    @ResponseBody
    @RequestMapping("/showClazzs")
    public List<Clazz> showClazzs(HttpSession session) {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        return counsellorService.selectClazzById(counsellor.getId());
    }
    @ResponseBody
    @RequestMapping("/pageQuery")
    public PageBean<Application> pageQuery(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, String clazzId,HttpSession session) throws ParseException {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        String creatTime = null;
        if (time != null){
            creatTime = DateUtils.formateDateTime(time);
            creatTime = DateUtils.subDay(creatTime);
        }
        return applicationService.selectPageByCounsellorIdAndDateAndClazzId(counsellor.getId(),pageNum,pageSize,creatTime,clazzId);
    }
    @ResponseBody
    @RequestMapping("/checkCreatTime")
    public void checkCreatTime(HttpSession session){
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        applicationService.updatePermissionByNowTimeAndCounsellorId(new Date(),counsellor.getId());
    }
    @ResponseBody
    @RequestMapping({"/acceptApplication","/refuseApplication"})
    public String acceptApplication(Integer id,String permission){
        if (applicationService.updatePermissionById(id, permission) == 1)
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }
    @ResponseBody
    @RequestMapping("/queryPageApprovalRecords")
    public PageBean<Application> queryPageApprovalRecords(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, String clazzId,String permission,HttpSession session) throws ParseException {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        String creatTime = null;
        if (time != null){
            creatTime = DateUtils.formateDateTime(time);
            creatTime = DateUtils.subDay(creatTime);
        }
        return applicationService.selectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(counsellor.getId(),pageNum,pageSize,creatTime,clazzId,permission);
    }
}
