package com.cec.workbench.web.controller.manager;

import com.cec.commons.constants.Constants;
import com.cec.commons.pojo.ClockBean;
import com.cec.commons.pojo.PageBean;
import com.cec.commons.utils.DateUtils;
import com.cec.commons.utils.ExcelUtils;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.service.ClazzService;
import com.cec.settings.service.DeptService;
import com.cec.workbench.pojo.Health;
import com.cec.workbench.service.HealthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RequestMapping("/pages/workbench/manager/health")
@Controller("managerHealthController")
public class HealthController {
    @Resource
    HealthService healthService;
    @Resource
    DeptService deptService;
    @Resource
    ClazzService clazzService;

    @ResponseBody
    @RequestMapping("/pageQuery")
    public PageBean<Health> pageQuery(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime
            , String deptId, String clazzId, String clockType, Boolean flag, String dynamicTags) throws ParseException {
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        String[] tag = null;
        if (!("".equals(dynamicTags) || dynamicTags == null)) {
            tag = dynamicTags.split(",");
        }
        PageBean<Health> pageBean = null;
        Date subDay = clockTime;
        if (flag) {
            formateDateTime = DateUtils.subDay(formateDateTime);
            subDay = DateUtils.subDay(clockTime);
        }
        pageBean = healthService.selectClockAndNotClockPageByDeptIdAndClazzIAndTag(pageNum, pageSize, formateDateTime, deptId, clazzId, clockType, tag);
        List<Health> healthList = pageBean.getRows();
        if (Constants.CHECK_FAIL.equals(clockType)) {
            for (Health health : healthList) {
                health.setClockTime(subDay);
                health.setMorningCheck("暂无数据");
                health.setAfternoonCheck("暂无数据");
                health.setInIsolation("暂无数据");
                health.setInRisk("暂无数据");
                health.setInSchool("暂无数据");
            }
        }
        return pageBean;
    }

    @ResponseBody
    @RequestMapping("/exportClockMeg")
    public void exportClockMeg(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime, String deptId,
                               String clazzId, String clockType, Boolean flag, String dynamicTags, HttpSession session, HttpServletResponse response) throws ParseException {
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        String[] tag = null;
        if (!("".equals(dynamicTags) || dynamicTags == null)) {
            tag = dynamicTags.split(",");
        }
        PageBean<Health> pageBean = null;
        Date subDay = clockTime;
        if (flag) {
            formateDateTime = DateUtils.subDay(formateDateTime);
            subDay = DateUtils.subDay(clockTime);
        }
        pageBean = healthService.selectClockAndNotClockPageByDeptIdAndClazzIAndTag(pageNum, pageSize, formateDateTime, deptId, clazzId, clockType, tag);
        List<Health> healthList = pageBean.getRows();
        if (Constants.CHECK_FAIL.equals(clockType)) {
            for (Health health : healthList) {
                health.setClockTime(subDay);
                health.setMorningCheck("暂无数据");
                health.setAfternoonCheck("暂无数据");
                health.setInIsolation("暂无数据");
                health.setInRisk("暂无数据");
                health.setInSchool("暂无数据");
            }
        }
        String[] title = {"日期", "姓名", "班级", "晨检体温是否超过37.3°C", "午检体温是否超过37.3°C",
                "是否在校", "是否在疫情中高风险地区", "是否处于医学隔离", "打卡状态"};
        String fileName = null;
        String sheetName = "学生健康打卡信息";
        String[][] content = null;
        if (healthList != null && healthList.size() > 0) {
            content = new String[healthList.size()][title.length];
            for (int i = 0; i < healthList.size(); i++) {
                content[i] = new String[title.length];
                Health health = healthList.get(i);
                content[i][0] = DateUtils.formateDateTime(health.getClockTime());
                content[i][1] = health.getUserName();
                content[i][2] = health.getClazzName();
                content[i][3] = health.getMorningCheck();
                content[i][4] = health.getAfternoonCheck();
                content[i][5] = health.getInSchool();
                content[i][6] = health.getInRisk();
                content[i][7] = health.getInIsolation();
                content[i][8] = clockType;
            }
        }
        try {
            HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content);
            fileName = "";
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream os = null;
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseBody
    @RequestMapping("/queryFileName")
    public String queryFileName(String deptId, String clazzId) {
        String fileName = "";
        if (deptId != null && !("".equals(deptId)))
            fileName += deptService.selectById(deptId).getName();
        if (clazzId != null && !("".equals(clazzId)))
            fileName += clazzService.selectByPrimaryKey(clazzId).getName();
        if ((deptId == null || "".equals(deptId)) && (clazzId == null || "".equals(clazzId)))
            fileName += "全体学生";
        return fileName;
    }

    @ResponseBody
    @RequestMapping("/pageQueryStaff")
    public PageBean<Health> pageQueryStaff(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime
            , String staffType, String deptId, String clockType, Boolean flag, String dynamicTags) throws ParseException {
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        String[] tag = null;
        Date subDay = clockTime;
        if (!("".equals(dynamicTags) || dynamicTags == null)) {
            tag = dynamicTags.split(",");
        }
        PageBean<Health> pageBean = null;
        if (flag) {
            formateDateTime = DateUtils.subDay(formateDateTime);
            subDay = DateUtils.subDay(clockTime);
        }
        pageBean = healthService.selectStaffClockAndNotClockPageByDeptIdAndTag(pageNum, pageSize, formateDateTime, staffType, deptId, clockType, tag);
        List<Health> healthList = pageBean.getRows();
        if (Constants.CHECK_FAIL.equals(clockType)) {
            for (Health health : healthList) {
                health.setClockTime(subDay);
                health.setMorningCheck("暂无数据");
                health.setAfternoonCheck("暂无数据");
                health.setInIsolation("暂无数据");
                health.setInRisk("暂无数据");
                health.setInSchool("暂无数据");
            }
        }
        return pageBean;
    }
    @ResponseBody
    @RequestMapping("/exportClockMegStaff")
    public void exportClockMegStaff(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime
            , String staffType, String deptId, String clockType, Boolean flag, String dynamicTags,HttpServletResponse response) throws ParseException {
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        String[] tag = null;
        Date subDay = clockTime;
        if (!("".equals(dynamicTags) || dynamicTags == null)) {
            tag = dynamicTags.split(",");
        }
        PageBean<Health> pageBean = null;
        if (flag) {
            formateDateTime = DateUtils.subDay(formateDateTime);
            subDay = DateUtils.subDay(clockTime);
        }
        pageBean = healthService.selectStaffClockAndNotClockPageByDeptIdAndTag(pageNum, pageSize, formateDateTime, staffType, deptId, clockType, tag);
        List<Health> healthList = pageBean.getRows();
        if (Constants.CHECK_FAIL.equals(clockType)) {
            for (Health health : healthList) {
                health.setClockTime(subDay);
                health.setMorningCheck("暂无数据");
                health.setAfternoonCheck("暂无数据");
                health.setInIsolation("暂无数据");
                health.setInRisk("暂无数据");
                health.setInSchool("暂无数据");
            }
        }
        String[] title = {"日期", "姓名","晨检体温是否超过37.3°C", "午检体温是否超过37.3°C",
                "是否在校", "是否在疫情中高风险地区", "是否处于医学隔离", "打卡状态"};
        String fileName = null;
        String sheetName = "学生健康打卡信息";
        String[][] content = null;
        if (healthList != null && healthList.size() > 0) {
            content = new String[healthList.size()][title.length];
            for (int i = 0; i < healthList.size(); i++) {
                content[i] = new String[title.length];
                Health health = healthList.get(i);
                content[i][0] = DateUtils.formateDateTime(health.getClockTime());
                content[i][1] = health.getUserName();
                content[i][2] = health.getMorningCheck();
                content[i][3] = health.getAfternoonCheck();
                content[i][4] = health.getInSchool();
                content[i][5] = health.getInRisk();
                content[i][6] = health.getInIsolation();
                content[i][7] = clockType;
            }
        }
        try {
            HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content);
            fileName = "";
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream os = null;
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @ResponseBody
    @RequestMapping("/queryFileNameStaff")
    public String queryFileNameStaff(String staffType,String deptId){
        String fileName = "";
        if (Constants.COUNSELLOR_TYPE.equals(staffType)){
            if (deptId != null && !("".equals(deptId))){
                fileName += deptService.selectById(deptId).getName();
            }else {
                fileName += "全体";
            }
            fileName += "辅导员";
        }else if(Constants.STAFF_TYPE.equals(staffType)){
            fileName += "其他教职工";
        }
        return fileName;
    }
}
