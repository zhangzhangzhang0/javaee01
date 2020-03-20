package org.example.javaee.class01.servlet;

import org.example.javaee.class01.jdbc.StudentHomeworkJdbc;
import org.example.javaee.class01.jdbc.StudentJdbc;
import org.example.javaee.class01.model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class SubmitHomeworkServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        StudentHomework sh = new StudentHomework();
        StudentHomeworkJdbc shj=new StudentHomeworkJdbc();
        StudentJdbc sj=new StudentJdbc();
        /**
         * 赋值
         */
        sh.setStudentId(Long.parseLong(req.getParameter("studentId")));
        sh.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));
        sh.setHomeworkTitle(req.getParameter("homeworkTitle"));
        sh.setHomeworkContent(req.getParameter("homeworkContent"));
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        sh.setCreateTime(date);

        //   if(req.getParameter(!"studentId").equals())

        int i= 0;
        try {
            i = shj.addHomework(sh);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(i>0){
            //完成数据库操作，返回响应给jsp
            System.out.println("true");
            out.print("<script>alert('Submit Successfully'); window.location='SSubmit.jsp' </script>");

        }else{
            System.out.println("false");
            out.print("<script>alert('Failed'); window.location='SSubmit.jsp' </script>");
        }

        out.flush();
        out.close();

    }

}
