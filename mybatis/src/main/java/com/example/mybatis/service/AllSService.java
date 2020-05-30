package com.example.mybatis.service;

import com.example.mybatis.model.Homework;
import com.example.mybatis.model.Student;
import com.example.mybatis.model.StudentHomework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AllSService {

    List<Student> login (HttpServletRequest req);
    void submit1 (HttpServletRequest req, HttpServletResponse resp);
    void submit2 (HttpServletRequest req, HttpServletResponse resp);
    boolean addHomework(HttpServletRequest req);
    List<Homework> selectAll3();
    StudentHomework find1(Long studentId, Long homeworkId);
    boolean editHW(Long homeworkId, Long num);

}
