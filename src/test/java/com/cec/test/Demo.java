package com.cec.test;

import com.cec.commons.utils.DateUtils;
import com.cec.commons.utils.ExcelUtils;
import com.cec.settings.mapper.StudentMapper;
import com.cec.settings.pojo.Student;
import com.cec.workbench.mapper.ApplicationMapper;
import com.cec.workbench.mapper.HealthMapper;
import com.cec.workbench.pojo.Application;
import com.cec.workbench.pojo.Health;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class Demo {
    @Test
    public void tesrt(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentMapper studentMapper = applicationContext.getBean("studentMapper", StudentMapper.class);
        List<Student> students = studentMapper.selectAll();
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectAll();
    }
    @Test
    public void testSelectPageByPermissionAndDate(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationMapper applicationMapper = applicationContext.getBean("applicationMapper", ApplicationMapper.class);
        PageHelper.startPage(1,4);
        List<Application> applications = applicationMapper.selectPageByStudentIdAndPermissionAndDate(32021, null, null);
    }
    @Test
    public void testUpdatePermissionByNowTimeAndStudentId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationMapper applicationMapper = applicationContext.getBean("applicationMapper", ApplicationMapper.class);
        String formateDateTime = DateUtils.formateDateTime(new Date());
        applicationMapper.updatePermissionByNowTimeAndStudentId(formateDateTime,32021);
    }
    @Test
    public void testSelectClockPageByCounsellorIdAndCondition(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectClockPageByCounsellorIdAndCondition(12020,"2022-12-01","C003");
        healthMapper.selectClockCountByCounsellorIdAndCondition(12020,"2022-12-01","C003");
    }
    @Test
    public void testSelectNotClockPageByCounsellorIdAndCondition(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectNotClockPageByCounsellorIdAndCondition(12020,"2022-12-01","C002");
        healthMapper.selectNotClockCountByCounsellorIdAndCondition(12020,"2022-12-01","C002");
    }
    @Test
    public void testDate(){
        Date current = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(current);
        calendar.add(calendar.DATE, 1);
        current = calendar.getTime();
        System.out.println(current);
    }
    @Test
    public void testSelectClockPageTagByCounsellorIdAndCondition(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        String[] tags = null;
        healthMapper.selectClockPageTagByCounsellorIdAndCondition(12020,"2022-12-02",null,tags);
        System.out.println(healthMapper.selectClockCountTagByCounsellorIdAndCondition(12020, "2022-12-02", null, tags));
    }
    @Test
    public void testExcelUtils(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        String[] tags = null;
        List<Health> healthList = healthMapper.selectClockPageTagByCounsellorIdAndCondition(12020, "2022-12-02", null, tags);
        String[] title = {"日期","姓名","班级","晨检体温是否超过37.3°C","午检体温是否超过37.3°C",
                "是否在校","是否在疫情中高风险地区","是否处于医学隔离","打卡状态"};
        String fileName = "2022-12-02"+"王燕平管理班级"+"打卡情况"+".xls";
        String sheetName = "学生健康打卡信息";
        String [][] content = null;
        if(healthList != null && healthList.size() > 0){
            content = new String[healthList.size()][title.length];
            for (int i = 0;i < healthList.size(); i++){
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
                content[i][8] = "已打卡";
            }
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName,title,content);
    }
    @Test
    public void testSelectPageByCounsellorIdAndDateAndClazzId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationMapper applicationMapper = applicationContext.getBean("applicationMapper", ApplicationMapper.class);
        List<Application> applications = applicationMapper.selectPageByCounsellorIdAndDateAndClazzId(12020, null, null);
    }
    @Test
    public void testUpdatePermissionByNowTimeAndCounsellorId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationMapper applicationMapper = applicationContext.getBean("applicationMapper", ApplicationMapper.class);
        applicationMapper.updatePermissionByNowTimeAndCounsellorId("2022-12-03",12020);
    }
    @Test
    public void testSelectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationMapper applicationMapper = applicationContext.getBean("applicationMapper", ApplicationMapper.class);
        applicationMapper.selectPageApprovalRecordsByCounsellorIdAndDateAndClazzId(12020,null,null,"已通过");
        applicationMapper.selectCountApprovalRecordsByCounsellorIdAndDateAndClazzId(12020,null,null,null);
    }
    @Test
    public void testSelectPageByCounsellorIdAndClazzId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentMapper studentMapper = applicationContext.getBean("studentMapper", StudentMapper.class);
        studentMapper.selectPageByCounsellorIdAndClazzId(12020,null,32021);
        studentMapper.selectCountByCounsellorIdAndClazzId(12020,null,32021);

    }
    @Test
    public void testSelectPageByDeptIdAndClazzId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentMapper studentMapper = applicationContext.getBean("studentMapper", StudentMapper.class);
        List<Map<String, Object>> mapList = studentMapper.selectPageByDeptIdAndClazzIdAndStudentId("D001", null,32020);
        for (Map<String, Object> stringObjectMap : mapList) {
            for (String key: stringObjectMap.keySet()){
                System.out.println(key +"  "+stringObjectMap.get(key));
            }
        }
        studentMapper.selectCountByDeptIdAndClazzIdAndStudentId("D001",null,32020);
    }
    @Test
    public void testSelectClockAndNotClockPageByDeptIdAndClazzIAndTag(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectClockPageByDeptIdAndClazzIAndTag("2022-12-04",null,null,null);
        healthMapper.selectClockCountByDeptIdAndClazzIAndTag("2022-12-04",null,null,null);
    }
    @Test
    public void testSelectNotClockPageByDeptIdAndClazzId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectNotClockPageByDeptIdAndClazzId("2022-12-04","D001","C001");
        healthMapper.selectNotClockCountByDeptIdAndClazzId("2022-12-04","D001","C001");
    }
    @Test
    public void testSelectCounsellorClockPageByDeptIdAndTag(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectCounsellorClockPageByDeptIdAndTag("2022-12-04","D001",new String[]{"体温异常"});
        healthMapper.selectCounsellorClockCountByDeptIdAndTag("2022-12-04","D001",null);

    }
    @Test
    public void testSelectStaffClockPageByDeptIdAndTag(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectStaffClockPageByTag("2022-12-05",new String[]{"体温异常"});
        healthMapper.selectStaffClockCountByTag("2022-12-05",new String[]{"体温异常"});
    }
    @Test
    public void testSelectStaffNotClockPage(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectStaffNotClockPage("2022-12-05");
        healthMapper.selectStaffNotClockCount("2022-12-05");
    }
    @Test
    public void testSelectCounsellorNotClockPageByDeptId(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        HealthMapper healthMapper = applicationContext.getBean("healthMapper", HealthMapper.class);
        healthMapper.selectCounsellorNotClockPageByDeptId("2022-12-05","D001");
        healthMapper.selectCounsellorNotClockCountByDeptId("2022-12-05","D001");
    }


}
