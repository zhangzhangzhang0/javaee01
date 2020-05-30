package com.example.mybatis.dao;

import com.example.mybatis.model.Homework;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkDao {
    boolean addHomework(Homework h);
    Homework find3(Long homeworkId);
    boolean editHW(Long homeworkId, Long num);
    List<Homework> selectAll3();
}
