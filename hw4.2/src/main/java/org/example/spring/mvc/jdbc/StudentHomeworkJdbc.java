package org.example.spring.mvc.jdbc;



import org.example.spring.mvc.model.StudentHomework;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



import java.sql.*;

import java.util.ArrayList;

import java.util.List;

/**



 * StudentHomework 查操作



 **/

//调用base类中的方法进行增和查操作

@Configuration

public class StudentHomeworkJdbc extends base {



    @Bean

    public StudentHomeworkJdbc getStudentHomeworkJdbc(){

        return new StudentHomeworkJdbc();

    }



    //学生上交作业

    public int addHomework(StudentHomework sh) throws SQLException {

        String str="INSERT INTO `s_student_homework`(student_id,homework_id,homework_title,homework_content,create_time) VALUE(?,?,?,?,?)";

        Timestamp ts = new Timestamp(sh.getCreateTime().getTime());

        return executeUpdate(str, new Object[]{sh.getStudentId(),sh.getHomeworkId(),sh.getHomeworkTitle(),sh.getHomeworkContent(),ts});

    }

    public int deleteHomework(StudentHomework sh) throws SQLException {

        String str="DELETE FROM `s_student_homework`WHERE student_id=?";

        System.out.println(sh.getStudentId());

        System.out.println(str);



        return executeUpdate(str, new Object[]{sh.getStudentId()});

    }



    public static List<StudentHomework> find(Long hwId){



        String sqlString = "SELECT * FROM j1.s_student_homework WHERE homework_id="+ hwId;

        List<StudentHomework> list2 = new ArrayList<>();



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



                        System.out.println(sh.getCreateTime());

                        list2.add(sh);





                    }



                }



            }



        } catch (SQLException e) {

            e.printStackTrace();

        }



        return list2;



    }



    //读取数据库中 s_student_homework表

    public static List<StudentHomework> selectAll(){



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



}