package org.example.spring.mvc.controller;
import org.example.spring.mvc.jdbc.StudentHomeworkJdbc;
import org.example.spring.mvc.jdbc.StudentJdbc;
import org.example.spring.mvc.model.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@Controller
public class SController {
    @RequestMapping("/SubmitHomeworkServlet")
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
            out.print("<script>alert('Submit Successfully')");
          //  return "SSubmit.jsp";
        }else{
            System.out.println("false");
            out.print("<script>alert('Failed')");
          //  return "/SSubmit.jsp";
        }
        req.getRequestDispatcher("/SSubmit.jsp").forward(req,resp);
    }
}
