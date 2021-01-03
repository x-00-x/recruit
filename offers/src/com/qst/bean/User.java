package com.qst.bean;

import java.util.Date;

public class User {
    public User(){
        System.out.println("---------默认无参构造方法------------");
    }
    //和数据库中的Users表的列对应
    private String user;
    private String password;
    private String status;
    private String authority;
    private Date birthday;

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
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", authority='" + authority + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
