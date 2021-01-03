package com.qst.dao;

import com.qst.bean.Applicant;
import com.qst.utils.DBUtils;

public class ApplicantDAO {
    //保存注册对象
    public Boolean saveApplicant(Applicant applicant){
        /*String sql="insert into user(user,password,status,authority) values(?,?,?,?)";
        return DBUtils.save(sql,applicant.getUser(),applicant.getPassword(),applicant.getStatus(),applicant.getAuthority());*/
        String sql="insert into user(user,password,status,authority,birthday) values(?,?,?,?,?)";
        return DBUtils.save(sql,applicant.getUser(),applicant.getPassword(),applicant.getStatus(),applicant.getAuthority(),applicant.getBirthday());
    }

    public Applicant getApplicantByUserAndPass(String user, String password) {
        String sql="select user,password,status,authority,birthday from user where user=? and password=?";
        return DBUtils.getSingleObj(Applicant.class,sql,user,password);
    }

    public Integer selectApplicantUserCount(String user) {
        String sql="select count(*) from user where user=?";
        Integer count=DBUtils.getCount(sql,user);
        return count;
    }

    //判断当前用户是否填写了简历
    public Integer isExistResume(String user) {
        String sql="select qid from resume where email=?";
        Integer qid=DBUtils.getCount(sql,user);
        return qid;
    }
}
