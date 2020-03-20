package org.example.javaee.class01.servlet;

import org.example.javaee.class01.jdbc.HomeworkJdbc;
import org.example.javaee.class01.model.Homework;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class HomeworkServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Homework h = new Homework();
        HomeworkJdbc hj=new HomeworkJdbc();
        PrintWriter out = resp.getWriter();//如果成功弹出并跳到下一个页面
        /**
         * 赋值
         */

        h.setHomeworkTitle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        h.setCreateTime(date);

        int i= 0;
        try {
            i = hj.addHomework(h);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(i>0){
            System.out.println("true");
            out.print("<script>alert('Add Successfully'); window.location='TAddHomework.jsp' </script>");

        }else{
            System.out.println("false");
            out.print("<script>alert('Failed'); window.location='TAddHomework.jsp' </script>");
        }

        out.flush();
        out.close();

    }

}
