package com.example.mybatis.service;


import com.example.mybatis.dao.HomeworkDao;
import com.example.mybatis.dao.StudentDao;
import com.example.mybatis.dao.StudentHomeworkDao;
import com.example.mybatis.model.Homework;
import com.example.mybatis.model.Student;
import com.example.mybatis.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AllTServiceImpl implements AllTService {
    @Resource
    private final HomeworkDao homeworkDao;
    private final StudentDao studentDao;
    private final StudentHomeworkDao studentHomeworkDao;

    @Autowired
    public AllTServiceImpl(HomeworkDao homeworkDao, StudentDao studentDao, StudentHomeworkDao studentHomeworkDao) {
        this.homeworkDao = homeworkDao;
        this.studentDao = studentDao;
        this.studentHomeworkDao = studentHomeworkDao;
    }

    @Override
    public void checkHW(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void checkHW2(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void checkHW3(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public boolean deleteStudent(HttpServletRequest req) {
        StudentHomework sh = new StudentHomework();
        Student s = new Student();
        s.setStudentId(Long.parseLong(req.getParameter("id")));
        sh.setStudentId(Long.parseLong(req.getParameter("id")));

        boolean i=studentHomeworkDao.deleteHomework(sh.getStudentId());
        boolean j=studentDao.deleteStudent(s);

        if(j=true){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean editHomework(HttpServletRequest req) {
        Long studentId=Long.parseLong(req.getParameter("sId"));
        Long homeworkId=Long.parseLong(req.getParameter("hwId"));
        Long score=Long.parseLong(req.getParameter("score"));

        return studentHomeworkDao.editHomework(studentId,homeworkId,score);
    }

    @Override
    public boolean addStudent(HttpServletRequest req) {
        Student s = new Student();
        s.setStudentId(Long.parseLong(req.getParameter("id")));
        s.setName(req.getParameter("name"));
        //获取当前系统时间，存入数据库创建时间
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        s.setCreateTime(date);

        return studentDao.addStudent(s);

    }

    @Override
    public void check(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public boolean addHomework(HttpServletRequest req) {
        Homework h = new Homework();
        h.setHomeworkTitle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        h.setCreateTime(date);

        return homeworkDao.addHomework(h);
    }

    @Override
    public List<Student> selectAll2() {
        return studentDao.selectAll2();
    }

    @Override
    public List<StudentHomework> selectAll1() {
        return studentHomeworkDao.selectAll1();
    }

    @Override
    public List<StudentHomework> find12(Long homeworkId) {
        return studentHomeworkDao.find12(homeworkId);
    }
}