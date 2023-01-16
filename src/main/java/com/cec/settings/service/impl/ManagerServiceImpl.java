package com.cec.settings.service.impl;

import com.cec.settings.mapper.ManagerMapper;
import com.cec.settings.pojo.Manager;
import com.cec.settings.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager selectByLoginNameAndLoginPassword(String loginName, String loginPassword) {
        return managerMapper.selectByLoginNameAndLoginPassword(loginName, loginPassword);
    }

    @Override
    public Manager selectByLoginName(String loginName) {
        return managerMapper.selectByLoginName(loginName);
    }

    @Override
    public int updateByPrimaryKey(Manager manager) {
        return managerMapper.updateByPrimaryKey(manager);
    }
}
