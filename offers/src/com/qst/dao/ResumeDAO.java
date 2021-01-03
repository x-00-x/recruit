package com.qst.dao;

import com.qst.bean.ResumeBasicInfo;
import com.qst.utils.DBUtils;

import java.util.Date;

//简历基本信息DAO
public class ResumeDAO {
    public Integer saveResumeBasicInfo(ResumeBasicInfo resumeBasicInfo) {
        String sql="insert into resume(name,gender,birthday,curLoc,resLoc,phone,email,intension,experience) values(?,?,?,?,?,?,?,?,?)";
        return DBUtils.updateForPrimary(sql,resumeBasicInfo.getName(),resumeBasicInfo.getGender(),resumeBasicInfo.getBirthday(),
                resumeBasicInfo.getCurLoc(),resumeBasicInfo.getResLoc(),resumeBasicInfo.getPhone(),resumeBasicInfo.getEmail(),
                resumeBasicInfo.getIntension(),resumeBasicInfo.getExperience());
    }

    public void updateHeadShot(int qid, String fileName) {
        String sql="update resume set headShot=? where qid=?";
        DBUtils.update(sql,fileName,qid);
    }

    public ResumeBasicInfo getResumeBasicInfoById(Integer resumeID) {
        String sql="select * from resume where qid=?";
        ResumeBasicInfo resumeBasicInfo=DBUtils.getSingleObj(ResumeBasicInfo.class,sql,resumeID);
        return resumeBasicInfo;
    }
}
