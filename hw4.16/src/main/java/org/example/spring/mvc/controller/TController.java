package org.example.spring.mvc.controller;
import org.example.spring.mvc.jdbc.JdbcService;
import org.example.spring.mvc.model.Homework;
import org.example.spring.mvc.model.Student;
import org.example.spring.mvc.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


@Controller
//@RequestMapping("/teacher/")
public class TController extends HttpServlet {

    ApplicationContext applicationContext3 = new AnnotationConfigApplicationContext(JdbcService.class);
    JdbcService jdbcService = (JdbcService) applicationContext3.getBean("getJdbcService");

    @RequestMapping("/DeleteStudentServlet")
    public void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        StudentHomework sh = new StudentHomework();
        Student s = new Student();
        s.setStudentId(Long.parseLong(req.getParameter("id")));
        sh.setStudentId(Long.parseLong(req.getParameter("id")));
        int j=0;
        int i=0;
        try {
            j=jdbcService.deleteHomework(sh);
            i=jdbcService.deleteStudent(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(i>0){
            System.out.println("true");
            out.print("<script>alert('Delete Successfully')");
        }else{
            System.out.println("false");
            out.print("<script>alert('Failed')");
        }
        req.getRequestDispatcher("/TDeleteStudent.jsp").forward(req,resp);
    }

    @RequestMapping("/addStudent")
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        Student s = new Student();

        PrintWriter out = resp.getWriter();
        System.out.println("11");
        s.setStudentId(Long.parseLong(req.getParameter("id")));
        s.setName(req.getParameter("name"));
        //获取当前系统时间，存入数据库创建时间
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        s.setCreateTime(date);
        //调用jdbc中增添学生的方法
        int i= 0;
        try {
            i = jdbcService.addStudent(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(i>0){
            System.out.println("true");
            out.print("<script>alert('Add Successfully')");
        }else{
            System.out.println("false");
            out.print("<script>alert('Failed')");
        }

        req.getRequestDispatcher("/TAddStudent.jsp").forward(req,resp);
    }

    @RequestMapping("/check")
    public void check (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println(req.getParameter("hwId"));
        req.getRequestDispatcher("/TCheckHomework2.jsp").forward(req,resp);
    }

    @RequestMapping("/HomeworkServlet")
    public void addHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework();
        //  HomeworkJdbc hj=new HomeworkJdbc();
        PrintWriter out = resp.getWriter();//如果成功弹出并跳到下一个页面
        h.setHomeworkTitle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        h.setCreateTime(date);
        int i= 0;
        try {
            i = jdbcService.addHomework(h);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(i>0){
            System.out.println("true");
            out.print("<script>alert('Add Successfully')");
        }else{
            System.out.println("false");
            out.print("<script>alert('Failed')");
            //  return "/TAddHomework.jsp";
        }
        req.getRequestDispatcher("/TAddHomework.jsp").forward(req,resp);
    }

}