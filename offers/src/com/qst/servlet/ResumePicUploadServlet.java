package com.qst.servlet;

import com.qst.bean.ResumeBasicInfo;
import com.qst.dao.ResumeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/ResumePicUploadServlet")
@MultipartConfig
public class ResumePicUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("headShot");
        //上传文件名
        String fileName=part.getSubmittedFileName();
        //创建文件保存目录
        String dir=this.getServletContext().getRealPath("/applicant/images");
        //判断目录是否存在
        File imgDir=new File(dir);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        //上传到服务器路径imgDir+'/'+fileName
        //D:\\其他\\apache-tomcat-9.0.22\\webapps\\offers_war_exploded\applicant\images
        part.write(dir+"/"+fileName);
        //将文件名保存到简历基本信息表，就是更新简历
        ResumeDAO resumeDao=new ResumeDAO();


//        //请求转发
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/applicant/resume.jsp");
//        dispatcher.forward(request,response);
        //从session中取出简历ID
        Integer resumeID=(Integer)request.getSession().getAttribute("SESSION_QID");
        resumeDao.updateHeadShot(resumeID,fileName);
        //根据简历ID，查询简历对象
        ResumeBasicInfo resumeBasicInfo=resumeDao.getResumeBasicInfoById(resumeID);
        request.setAttribute("resumeBasicInfo",resumeBasicInfo);

        request.getRequestDispatcher("applicant/resume.jsp").forward(request,response);
//        response.sendRedirect("applicant/resume.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
