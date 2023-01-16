package com.cec.settings.service.impl;

import com.cec.commons.pojo.PageBean;
import com.cec.settings.mapper.CounsellorMapper;
import com.cec.settings.pojo.Clazz;
import com.cec.settings.pojo.Counsellor;
import com.cec.settings.service.CounsellorService;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("counsellorService")
public class CounsellorServiceImpl implements CounsellorService {

    @Resource
    private CounsellorMapper counsellorMapper;

    @Override
    public Counsellor selectByLoginNameAndLoginPassword(String loginName, String loginPassword) {
        return counsellorMapper.selectByLoginNameAndLoginPassword(loginName, loginPassword);
    }

    @Override
    public Counsellor selectByLoginName(String loginName) {
        return counsellorMapper.selectByLoginName(loginName);
    }

    @Override
    public List<Clazz> selectClazzById(Integer id) {
        return counsellorMapper.selectClazzById(id);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public PageBean<Map<String, Object>> selectPageByDeptIdAndStaffId(Integer pageNum, Integer pageSize, String deptId,Integer staffId) {
        PageBean<Map<String, Object>> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        pageBean.setRows(counsellorMapper.selectPageByDeptIdAndStaffId(deptId,staffId));
        pageBean.setTotals(counsellorMapper.selectCountByDeptIdAndStaffId(deptId,staffId));
        return pageBean;
    }
    @Override
    public int updateByPrimaryKey(Counsellor counsellor) {
        return counsellorMapper.updateByPrimaryKey(counsellor);
    }

    @Override
    public int insert(Counsellor counsellor) {
        return counsellorMapper.insert(counsellor);
    }

    @Override
    public Map<String,Object> selectIncludeDeptNameByPrimaryKey(Integer id) {
        return counsellorMapper.selectIncludeDeptNameByPrimaryKey(id);
    }

    @Override
    public List<Integer> selectIds() {
        return counsellorMapper.selectIds();
    }
}
