package org.example.spring.mvc.jdbc;

import org.example.spring.mvc.model.Student;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



import java.sql.*;

import java.util.ArrayList;

import java.util.List;

/**



 * Student 查操作



 **/



//调用base类中的方法进行增删查操作

@Configuration

public class StudentJdbc extends base {



    @Bean

    public StudentJdbc getStudentJdbc(){

        return new StudentJdbc();

    }



    //老师添加学生

    public int addStudent(Student s) throws SQLException {

        String str = "INSERT INTO `s_student`(id,name,create_time) VALUE(?,?,?)";

        Timestamp ts = new Timestamp(s.getCreateTime().getTime());



        return executeUpdate(str, new Object[]{s.getStudentId(), s.getName(), s.getCreateTime()});

    }



    //老师删除学生

    public int deleteStudent(Student s) throws SQLException {

        String sql = "DELETE FROM `s_student` WHERE id=?";

        return executeUpdate(sql, new Object[]{s.getStudentId()});

    }



    //根据特定id寻找学生信息

    public static List<Student> find(Long studentId){

        String sqlString = "SELECT * FROM j1.s_student WHERE id="+ studentId;

        Student  s=new Student();

        List<Student> list = new ArrayList<>();



        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()) {

            try(Statement statement = connection.createStatement()){

                try(ResultSet resultSet = statement.executeQuery(sqlString)){

                    // 获取执行结果

                    while (resultSet.next()){

                        // Student s = new Student();

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



    public static List<Student> selectAll(){

        String sqlString = "SELECT * FROM j1.s_student";



        List<Student> list = new ArrayList<>();

        base b=new base();



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