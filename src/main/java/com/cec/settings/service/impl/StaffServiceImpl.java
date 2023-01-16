package com.cec.settings.service.impl;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.mapper.StaffMapper;
import com.cec.settings.pojo.Staff;
import com.cec.settings.service.StaffService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Staff selectByLoginNameAndLoginPassword(String loginName, String loginPassword) {
        return staffMapper.selectByLoginNameAndLoginPassword(loginName, loginPassword);
    }

    @Override
    public Staff selectByLoginName(String loginName) {
        return staffMapper.selectByLoginName(loginName);
    }

    @Override
    public int insertBySubmitNameAndSubmitPassword(String submitName, String submitPassword) {
        return staffMapper.insertBySubmitNameAndSubmitPassword(submitName,submitPassword);
    }

    @Override
    public int insert(Staff staff) {
        return staffMapper.insert(staff);
    }

    @Override
    public PageBean<Map<String, Object>> selectPageByStaffId(Integer pageNum, Integer pageSize,Integer staffId) {
        PageBean<Map<String, Object>> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        pageBean.setRows(staffMapper.selectPageByStaffId(staffId));
        pageBean.setTotals(staffMapper.selectCountByStaffId(staffId));
        return pageBean;
    }

    @Override
    public List<Integer> selectIds() {
        return staffMapper.selectIds();
    }

    @Override
    public int updateByPrimaryKey(Staff staff) {
        return staffMapper.updateByPrimaryKey(staff);
    }
}
