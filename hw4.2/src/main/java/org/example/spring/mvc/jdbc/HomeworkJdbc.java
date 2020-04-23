package org.example.spring.mvc.jdbc;



import org.example.spring.mvc.model.Homework;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



import java.sql.*;

import java.util.ArrayList;

import java.util.List;



/**



 * Homework 操作



 **/

//调用base类中的方法进行增和查操作

@Configuration

public class HomeworkJdbc extends base {



    @Bean

    public HomeworkJdbc getHomeworkJdbc(){

        return new HomeworkJdbc();

    }



    //老师增加作业

    public int addHomework(Homework h) throws SQLException {

        String str = "INSERT INTO `s_homework`(title,content,create_time) VALUE(?,?,?)";

        //Date转为Timestamp

        Timestamp ts = new Timestamp(h.getCreateTime().getTime());

        return executeUpdate(str, new Object[]{h.getHomeworkTitle(), h.getHomeworkContent(), ts});

    }

    //寻找id相应作业信息

    public static Homework find(Long homeworkId){

        String sqlString = "SELECT * FROM j1.s_homework WHERE id="+ homeworkId;

        Homework  hw=new Homework();



        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()) {

            try(Statement statement = connection.createStatement()){

                try(ResultSet resultSet = statement.executeQuery(sqlString)){

                    // 获取执行结果

                    while (resultSet.next()){

                        // Homework h = new Homework();

                        //  hw.setId(resultSet.getLong("id"));

                        hw.setId(homeworkId);

                        System.out.println("find"+hw.getId());

                        hw.setHomeworkTitle(resultSet.getString("title"));

                        hw.setHomeworkContent(resultSet.getString("content"));

                        //  hw.setCreateTime(resultSet.getTimestamp("create_time"));

                        System.out.println("find");

                        return hw;



                    }



                }



            }



        } catch (SQLException e) {

            e.printStackTrace();

        }

        return hw;



    }





    public static List<Homework> selectAll(){

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







}