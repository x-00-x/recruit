package com.qst.servlet;

import com.qst.bean.Applicant;
import com.qst.dao.ApplicantDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/ApplicatRegisterServlet")
public class ApplicatRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获取前台的提交的数据
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        //插入到数据库里面，对数据进行封装
        Applicant applicant=new Applicant(user,password,"普通用户","查看",new Date());
//        applicant.setUser(user);
//        applicant.setPassword(password);
        //保存applicant到数据库 ApplicantDAO
        ApplicantDAO applicantDAO=new ApplicantDAO();
        //判断是否有相同的邮箱
        Integer count=applicantDAO.selectApplicantUserCount(user);
        if(count>0){
            //数据库有已有相同邮箱的用户了
            //通过response对象给客户端一个错误提示
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('申请的邮箱已存在！');");
            writer.write("window.location.href='register.html';");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }else{
            //flag是否注册成功
            Boolean flag=applicantDAO.saveApplicant(applicant);
            if (flag) {
                //注册成功就跳转至登陆界面 重定向
                response.sendRedirect("/offers/login.html");
            }else{
                //注册失败返回注册页面 请求转发
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register.html");
                dispatcher.forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
