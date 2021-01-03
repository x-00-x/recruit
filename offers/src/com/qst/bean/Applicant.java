package com.qst.bean;

import java.util.Date;

public class Applicant {
    private String user;
    private String password;
    private String status;
    private String authority;
    private Date birthday;

    public Applicant() {

    }

    public Applicant(String user, String password, String status, String authority, Date birthday) {
        this.user = user;
        this.password = password;
        this.status = status;
        this.authority = authority;
        this.birthday = birthday;/*******可以改成注册时间*****/
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", authority='" + authority + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
