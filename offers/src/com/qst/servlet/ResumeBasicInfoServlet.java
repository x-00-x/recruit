package com.qst.servlet;

import com.qst.bean.Applicant;
import com.qst.bean.ResumeBasicInfo;
import com.qst.dao.ResumeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@WebServlet(urlPatterns = "/ResumeBasicInfoServlet")
public class ResumeBasicInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置post提交时候，参数解码方式
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        //添加简历基本信息
        if("add".equals(method)){
            //获取页面参数
            //封装对象
            ResumeBasicInfo resumeBasicInfo=requestDataObj(request);
            ResumeDAO resumeDAO = new ResumeDAO();
            //将对象保存至数据库
            Integer qid= resumeDAO.saveResumeBasicInfo(resumeBasicInfo);
//            System.out.println(qid);
            //将当前登陆用户的简历ID，保存到Session中了
            request.getSession().setAttribute("SESSION_QID",qid);
            //跳转到简历信息，展示刚添加的简历信息
            resumeBasicInfo.setQid(qid);
            //简历放入请求作用域，在简历展示页面显示该对象信息
//            System.out.println("---------------servlet-----"+resumeBasicInfo.getName());
            request.setAttribute("resumeBasicInfo",resumeBasicInfo);
//            response.sendRedirect("applicant/resume.jsp");
            request.getRequestDispatcher("applicant/resume.jsp").forward(request,response);
        }else if("findById".equals(method)){
            String resumeId=request.getParameter("resumeId");
            ResumeDAO resumeDAO = new ResumeDAO();
            Integer qid=Integer.valueOf(resumeId);
            ResumeBasicInfo resumeBasicInfo = resumeDAO.getResumeBasicInfoById(qid);
            request.setAttribute("resumeBasicInfo",resumeBasicInfo);
            request.getRequestDispatcher("applicant/resumeBasicinfoUpdate.jsp").forward(request,response);
        }
    }
    //获取页面数据，封装ResumeBasicInfo
    private ResumeBasicInfo requestDataObj(HttpServletRequest request) {
        String name = request.getParameter("name");
        String gender=request.getParameter("gender");
        String birthdayStr=request.getParameter("birthday");
//            String curLoc=request.getParameter("curLoc");
//            String resLoc=request.getParameter("resLoc");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        String intension=request.getParameter("intension");
        String experience=request.getParameter("experience");
//            String headShot=request.getParameter("headShot");
        Date birthday=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday=sdf.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //从session中取出登陆用户的ID
        Applicant applicant=(Applicant)request.getSession().getAttribute("SESSION_APPLICANT");
        ResumeBasicInfo resumeBasicInfo=new ResumeBasicInfo(null,name,gender,birthday,null,null,phone,email,intension,experience,null);
        resumeBasicInfo.setEmail(applicant.getUser());
        return resumeBasicInfo;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
