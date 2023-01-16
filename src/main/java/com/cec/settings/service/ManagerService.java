package com.cec.settings.service;

import com.cec.settings.pojo.Manager;
import com.cec.settings.pojo.Student;

public interface ManagerService {

    Manager selectByLoginNameAndLoginPassword(String loginName, String loginPassword);

    Manager selectByLoginName(String loginName);

    int updateByPrimaryKey(Manager manager);
}
