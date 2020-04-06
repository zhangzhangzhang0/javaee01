package org.example.spring.mvc.bean;

import org.example.spring.mvc.jdbc.*;
import org.example.spring.mvc.model.Homework;
import org.example.spring.mvc.model.Student;
import org.example.spring.mvc.model.StudentHomework;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Beans {
    public Beans() {
    }

    public Beans(Long aLong, String s) {
    }

    @Bean
    public StudentHomeworkJdbc studentHomeworkJdbc(){
        return new StudentHomeworkJdbc();
    }

    @Bean
    public HomeworkJdbc homeworkJdbc(){
        return new HomeworkJdbc();
    }

    @Bean
    public StudentJdbc studentJdbc(){
        return new StudentJdbc();
    }

    @Bean
    public DatabasePool databasePool(){
        return new DatabasePool();
    }

    public static List<Homework> selectAll1(){
        String sqlString = "SELECT * FROM j1.s_homework";
        List<Homework> list = new ArrayList<>();
        base b=new base();


        //  try(Connection connection =  b.getconn()) {
        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Homework h = new Homework();
                        h.setId(resultSet.getLong("id"));
                        h.setHomeworkTitle(resultSet.getString("title"));
                        h.setHomeworkContent(resultSet.getString("content"));
                        h.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(h);

                    }

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    //读取数据库中 s_student_homework表
    public static List<StudentHomework> selectAll2(){

        String sqlString = "SELECT * FROM j1.s_student_homework";

        List<StudentHomework> list = new ArrayList<>();
        base b=new base();
        //   try(Connection connection =  b.getconn()){

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(sh);

                    }

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public static List<Student> selectAll3(){
        String sqlString = "SELECT * FROM j1.s_student";

        List<Student> list = new ArrayList<>();
        base b=new base();
        //   try(Connection connection =  b.getconn()){

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Student s = new Student();
                        s.setStudentId(resultSet.getLong("id"));
                        s.setName(resultSet.getString("name"));
                        s.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(s);

                    }

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }


}
