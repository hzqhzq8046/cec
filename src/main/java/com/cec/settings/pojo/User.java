package com.cec.settings.pojo;

public class User {

    private String loginName;

    private String loginPassword;

    private boolean remember;

    public User() {
    }

    public User(String loginName, String loginPassword, boolean remember) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", remember=" + remember +
                '}';
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
