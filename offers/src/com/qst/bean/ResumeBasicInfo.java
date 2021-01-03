package com.qst.bean;

import java.util.Date;

public class ResumeBasicInfo {
    private Integer qid;
    private String name;
    private String gender;
    private Date birthday;
    private String curLoc;
    private String resLoc;
    private String phone;
    private String email;
    private String intension;
    private String experience;
    private String headShot;

    public ResumeBasicInfo(Integer qid, String name, String gender, Date birthday, String curLoc, String resLoc, String phone, String email, String intension, String experience, String headShot) {
        this.qid = qid;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.curLoc = curLoc;
        this.resLoc = resLoc;
        this.phone = phone;
        this.email = email;
        this.intension = intension;
        this.experience = experience;
        this.headShot = headShot;
    }

    public ResumeBasicInfo() {
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCurLoc() {
        return curLoc;
    }

    public void setCurLoc(String curLoc) {
        this.curLoc = curLoc;
    }

    public String getResLoc() {
        return resLoc;
    }

    public void setResLoc(String resLoc) {
        this.resLoc = resLoc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntension() {
        return intension;
    }

    public void setIntension(String intension) {
        this.intension = intension;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHeadShot() {
        return headShot;
    }

    public void setHeadShot(String headShot) {
        this.headShot = headShot;
    }

    @Override
    public String toString() {
        return "ResumeBasicInfo{" +
                "qid=" + qid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", curLoc='" + curLoc + '\'' +
                ", resLoc='" + resLoc + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", intension='" + intension + '\'' +
                ", experience='" + experience + '\'' +
                ", headShot='" + headShot + '\'' +
                '}';
    }
}
