package org.example.javaee.class01.servlet;

import org.example.javaee.class01.jdbc.StudentHomeworkJdbc;
import org.example.javaee.class01.jdbc.StudentJdbc;
import org.example.javaee.class01.model.Student;
import org.example.javaee.class01.model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;


public class DeleteStudentServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        StudentHomework sh = new StudentHomework();
        StudentHomeworkJdbc shj=new StudentHomeworkJdbc();
        Student s = new Student();
        StudentJdbc sj = new StudentJdbc();
        s.setStudentId(Long.parseLong(req.getParameter("id")));
        sh.setStudentId(Long.parseLong(req.getParameter("id")));

        int j=0;
        int i= 0;
        try {
            j=shj.deleteHomework(sh);
            i = sj.deleteStudent(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(i>0){
            System.out.println("true");
            out.print("<script>alert('Delete Successfully'); window.location='TDeleteStudent.jsp' </script>");
        }else{
            System.out.println("false");
            out.print("<script>alert('Failed'); window.location='TDeleteStudent.jsp' </script>");
        }

        out.flush();
        out.close();

    }
}
