package com.qst.servlet;

import com.qst.bean.Applicant;
import com.qst.bean.ResumeBasicInfo;
import com.qst.dao.ApplicantDAO;
import com.qst.dao.ResumeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/ApplicantLoginServlet")
public class ApplicantLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        //根据email和密码查询申请人
        ApplicantDAO applicantDAO=new ApplicantDAO();
        Applicant applicant=applicantDAO.getApplicantByUserAndPass(user,password);
//        System.out.println(applicant);
        if(applicant!=null){
            //将登陆用户对象保存到session
            request.getSession().setAttribute("SESSION_APPLICANT",applicant);

            //用户名和密码正确，是否填写简历
            //按照当前登录用户user判断，用户是否填写简历
            Integer qid=applicantDAO.isExistResume(applicant.getUser());
//            System.out.println(qid);
            if (qid!=null) {
                //将简历放入session中
                request.getSession().setAttribute("SESSION_QID",qid);
                response.sendRedirect("index.html");
                /*request.getRequestDispatcher("index.html").forward(request,response);*/
            }else{
                response.sendRedirect("applicant/resumeBasicInfoAdd.jsp");
            }
        }else{
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！');");
            writer.write("window.location.href='login.html';");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }
}
