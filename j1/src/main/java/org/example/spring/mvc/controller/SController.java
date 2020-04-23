package org.example.spring.mvc.controller;
import org.example.spring.mvc.jdbc.JdbcService;
import org.example.spring.mvc.model.Student;
import org.example.spring.mvc.model.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class SController {

    ApplicationContext applicationContext3 = new AnnotationConfigApplicationContext(JdbcService.class);
    JdbcService jdbcService = (JdbcService) applicationContext3.getBean("getJdbcService");

    //登陆跳转到学生选择功能界面
    @RequestMapping("/login")
    public void login (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        Student sId = new Student();
        sId.setStudentId(Long.parseLong(req.getParameter("sId")));
        List<Student> list= jdbcService.find2(sId.getStudentId());
        if(null == list || list.size() <= 0){
            out.print("<script>alert('No such student')");
            req.getRequestDispatcher("/j1_war_exploded/main.jsp").forward(req,resp);
        }else{
           // req.getRequestDispatcher("/SChoose.jsp").forward(req,resp);
            req.getRequestDispatcher("/SSubmit.jsp").forward(req,resp);
        }
        req.getRequestDispatcher("/j1_war_exploded/main.jsp").forward(req,resp);
    }

    //学生选择功能界面跳转到作业列表
    @RequestMapping("/submit1")
    public void submit1 (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/SSubmit.jsp").forward(req,resp);
    }

    //学生查看的作业列表跳转到提交界面
    @RequestMapping("/submit2")
    public void submit2 (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/SSubmit2.jsp").forward(req,resp);
    }

    //作业提交执行
    @RequestMapping("/SubmitHomeworkServlet")
    public void addHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        StudentHomework sh = new StudentHomework();
        System.out.println("SubmitHomeworkServlet"+Long.parseLong(req.getParameter("sId")));
        sh.setStudentId(Long.parseLong(req.getParameter("sId")));
        sh.setHomeworkId(Long.parseLong(req.getParameter("hwId")));
        sh.setHomeworkTitle(req.getParameter("hwTitle"));
        sh.setHomeworkContent(req.getParameter("finish"));
        System.out.println(sh.getHomeworkContent());
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        sh.setCreateTime(date);

        int i= 0;
        try {
            i = jdbcService.addHomework(sh);
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
        req.setAttribute("sId",sh.getStudentId());
        req.getRequestDispatcher("/SSubmit.jsp").forward(req,resp);
    }

}