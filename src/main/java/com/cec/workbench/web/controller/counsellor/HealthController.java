package com.cec.workbench.web.controller.counsellor;

import com.cec.commons.constants.Constants;
import com.cec.commons.pojo.ClockBean;
import com.cec.commons.utils.DateUtils;
import com.cec.commons.utils.ExcelUtils;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.pojo.Student;
import com.cec.settings.service.ClazzService;
import com.cec.settings.service.CounsellorService;
import com.cec.workbench.pojo.Health;
import com.cec.workbench.service.HealthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/pages/workbench/counsellor/health")
@Controller("counsellorHealthController")
public class HealthController {
    @Resource
    HealthService healthService;
    @Resource
    CounsellorService counsellorService;

    @Resource
    ClazzService clazzService;

    @ResponseBody
    @RequestMapping("/health")
    //String morningCheck,String afternoonCheck,String inSchool,String inRisk,String inIsolation
    public String health(@RequestBody Health health, HttpSession session) {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        health.setUserId(counsellor.getId());
        health.setUserName(counsellor.getName());
        if (healthService.insert(health) == 1)
            return Constants.RETURN_OBJECT_CODE_SUCCESS;
        return Constants.RETURN_OBJECT_CODE_FAIL;
    }

    @ResponseBody
    @RequestMapping("/checkClock")
    public String checkClock(HttpSession session) {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        if (healthService.selectByUserId(counsellor.getId()) == null)
            return Constants.CHECK_FAIL;
        return Constants.CHECK_SUCCESS;
    }

    /**
     * 查找打卡和未打卡的名单
     * 已打卡的为Health类
     * 未打卡的为Student类
     *
     * @param pageNum
     * @param pageSize
     * @param clockTime
     * @param clazzId
     * @param clockType
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQuery")
    public ClockBean<Health> pageQuery(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime,
                                       String clazzId, String clockType, Boolean flag, HttpSession session) {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        ClockBean<Health> clockBean = healthService.selectClockAndNotClockPageByCounsellorIdAndCondition(counsellor.getId(), pageNum, pageSize, formateDateTime, clazzId, clockType);
        if (flag) {
            List<Health> healthList = clockBean.getClocked();
            Date subDay = DateUtils.subDay(clockTime);
            for (Health health : healthList) {
                health.setClockTime(subDay);
                if (Constants.CHECK_FAIL.equals(clockType)) {
                    health.setMorningCheck("暂无数据");
                    health.setAfternoonCheck("暂无数据");
                    health.setInIsolation("暂无数据");
                    health.setInRisk("暂无数据");
                    health.setInSchool("暂无数据");
                }
            }
        }
        return clockBean;
    }

    /**
     * 通过辅导员id查管理的班级
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/showClazzs")
    public List<Clazz> showClazzs(HttpSession session) {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        return counsellorService.selectClazzById(counsellor.getId());
    }

    @ResponseBody
    @RequestMapping("/pageQueryTag")
    public ClockBean<Health> pageQueryTag(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime,
                                          String clazzId, String clockType, Boolean flag, String dynamicTags, HttpSession session) throws ParseException {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        String[] tag = null;
        if (!("".equals(dynamicTags) || dynamicTags == null)) {
            tag = dynamicTags.split(",");
        }
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        Date subDay = clockTime;
        if (flag) {
            formateDateTime = DateUtils.subDay(formateDateTime);
            subDay = DateUtils.subDay(clockTime);
        }
        ClockBean<Health> clockBean = healthService.selectClockAndNotClockPageByCounsellorIdAndConditionAndTag(counsellor.getId(), pageNum, pageSize, formateDateTime, clazzId, clockType, tag);
        List<Health> healthList = clockBean.getClocked();
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
        return clockBean;
    }

    @ResponseBody
    @RequestMapping("/exportClockMeg")
    public void exportClockMeg(Integer pageNum, Integer pageSize, @DateTimeFormat(pattern = "yyyy-MM-dd") Date clockTime,
                               String clazzId, String clockType, Boolean flag, String dynamicTags, HttpSession session, HttpServletResponse response) throws ParseException {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        String[] tag = null;
        if (!("".equals(dynamicTags) || dynamicTags == null)) {
            tag = dynamicTags.split(",");
        }
        String formateDateTime = DateUtils.formateDateTime(clockTime);
        Date subDay = clockTime;

        if (flag) {
            formateDateTime = DateUtils.subDay(formateDateTime);
            subDay = DateUtils.subDay(clockTime);
        }
        ClockBean<Health> clockBean = healthService.selectClockAndNotClockPageByCounsellorIdAndConditionAndTag(counsellor.getId(), pageNum, pageSize, formateDateTime, clazzId, clockType, tag);
        List<Health> healthList = clockBean.getClocked();
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
        if (clazzId != null && !("".equals(clazzId))) {
            Clazz clazz = clazzService.selectByPrimaryKey(clazzId);
            fileName = formateDateTime + clazz.getName() + "打卡情况" + ".xls";
        } else {
            fileName = formateDateTime + counsellor.getName() + "管理班级" + "打卡情况" + ".xls";
        }
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
            fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
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
    @RequestMapping("/queryCounsellorName")
    public String queryCounsellorName(HttpSession session) {
        Counsellor counsellor = (Counsellor) session.getAttribute(Constants.COUNSELLOR);
        String counsellorName = counsellor.getName();
        return counsellorName;
    }

    @ResponseBody
    @RequestMapping("/queryClassName")
    public String queryClassNameName(String clazzId) {
        Clazz clazz = clazzService.selectByPrimaryKey(clazzId);
        String clazzName = clazz.getName();
        return clazzName;
    }
}
