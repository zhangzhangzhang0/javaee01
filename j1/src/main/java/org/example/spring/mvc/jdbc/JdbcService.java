package org.example.spring.mvc.jdbc;

import org.example.spring.mvc.model.Homework;
import org.example.spring.mvc.model.Student;
import org.example.spring.mvc.model.StudentHomework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcService{
    String driverName = "com.mysql.cj.jdbc.Driver";

    @Bean
   public JdbcService getJdbcService(){
        return new JdbcService();
    }

    //老师添加学生
    public int addStudent(Student s) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String str = "INSERT INTO `s_student`(id,name,create_time) VALUE(?,?,?)";
        Timestamp ts = new Timestamp(s.getCreateTime().getTime());
        int resultSet = 0;
        System.out.println(str);

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(str)) {
                ps.setLong(1,s.getStudentId());
                ps.setString(2,s.getName());
                ps.setTimestamp(3,new Timestamp(s.getCreateTime().getTime()));
                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
       // return executeUpdate(str, new Object[]{s.getStudentId(), s.getName(), s.getCreateTime()});
    }

    //老师删除学生
    public int deleteStudent(Student s) throws SQLException {
        String sql = "DELETE FROM `s_student` WHERE id=?";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int resultSet = 0;

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1,s.getStudentId());
                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
        //return executeUpdate(sql, new Object[]{s.getStudentId()});
    }

    //根据特定id寻找学生信息
    public static List<Student> find2(Long studentId){
        String sqlString = "SELECT * FROM j1.s_student WHERE id="+ studentId;
        Student  s=new Student();
        List<Student> list = new ArrayList<>();

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);//不自动commit
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
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Student> selectAll2(){
        String sqlString = "SELECT * FROM j1.s_student";

        List<Student> list = new ArrayList<>();
     //   base b=new base();

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()){
            connection.setAutoCommit(false);//不自动commit
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
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //学生上交作业
    public int addHomework(StudentHomework sh) throws SQLException {
        String str="INSERT INTO `s_student_homework`(student_id,homework_id,homework_title,homework_content,create_time) VALUE(?,?,?,?,?)";
        Timestamp ts = new Timestamp(sh.getCreateTime().getTime());
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int resultSet = 0;

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(str)) {
                ps.setLong(1,sh.getStudentId());
                ps.setLong(2,sh.getHomeworkId());
                ps.setString(3,sh.getHomeworkTitle());
                ps.setString(4,sh.getHomeworkContent());
                ps.setTimestamp(5,new Timestamp(sh.getCreateTime().getTime()));
                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
      //  return executeUpdate(str, new Object[]{sh.getStudentId(),sh.getHomeworkId(),sh.getHomeworkTitle(),sh.getHomeworkContent(),ts});
    }

    public int editHomework(Long sId,Long hwId,Long score) throws SQLException {
        String str="UPDATE s_student_homework SET score=? WHERE student_id=? AND homework_id=?";
        //Timestamp ts = new Timestamp(sh.getCreateTime().getTime());
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int resultSet = 0;

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(str)) {
                ps.setLong(1,score);
                ps.setLong(2,sId);
                ps.setLong(3,hwId);
                System.out.println(str);
                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
        //  return executeUpdate(str, new Object[]{sh.getStudentId(),sh.getHomeworkId(),sh.getHomeworkTitle(),sh.getHomeworkContent(),ts});
    }


    public int deleteHomework(StudentHomework sh) throws SQLException {
        String str="DELETE FROM `s_student_homework`WHERE student_id=?";
        System.out.println(sh.getStudentId());
        System.out.println(str);
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int resultSet = 0;

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(str)) {
                ps.setLong(1,sh.getStudentId());

                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

       // return executeUpdate(str, new Object[]{sh.getStudentId()});
    }

    public static List<StudentHomework> find1(Long sId,Long hwId){

        String sqlString = "SELECT * FROM j1.s_student_homework WHERE student_id="+ sId+" AND homework_id="+hwId;
        List<StudentHomework> list2 = new ArrayList<>();

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()){
            connection.setAutoCommit(false);//不自动commit
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
                        sh.setScore(resultSet.getLong("score"));

                        System.out.println(sh.getCreateTime());
                        list2.add(sh);


                    }
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list2;

    }

    public static List<StudentHomework> find12(Long hwId){

        String sqlString = "SELECT * FROM j1.s_student_homework WHERE homework_id="+hwId;
        List<StudentHomework> list2 = new ArrayList<>();

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()){
            connection.setAutoCommit(false);//不自动commit
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
                        sh.setScore(resultSet.getLong("score"));

                        System.out.println(sh.getCreateTime());
                        list2.add(sh);


                    }
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list2;

    }

    //读取数据库中 s_student_homework表
    public static List<StudentHomework> selectAll1(){

        String sqlString = "SELECT * FROM j1.s_student_homework";

        List<StudentHomework> list = new ArrayList<>();

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()){
            connection.setAutoCommit(false);//不自动commit
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
                        sh.setScore(resultSet.getLong("score"));
                        list.add(sh);

                    }
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //老师增加作业
    public int addHomework(Homework h) throws SQLException {
        String str = "INSERT INTO `s_homework`(title,content,create_time) VALUE(?,?,?)";
        //Date转为Timestamp
        Timestamp ts = new Timestamp(h.getCreateTime().getTime());
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int resultSet = 0;

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(str)) {
                ps.setString(1,h.getHomeworkTitle());
                ps.setString(2,h.getHomeworkContent());
                ps.setTimestamp(3,new Timestamp(h.getCreateTime().getTime()));
                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
        //return executeUpdate(str, new Object[]{h.getHomeworkTitle(), h.getHomeworkContent(), ts});
    }
    public static Homework find3(Long homeworkId){
        String sqlString = "SELECT * FROM j1.s_homework WHERE id="+ homeworkId;
        Homework  hw=new Homework();

        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);//不自动commit
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
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hw;

    }

    public static int editHW(Long hwId, Long num) throws SQLException {
        String str="UPDATE s_homework SET num=? WHERE id=?";
        String driverName = "com.mysql.cj.jdbc.Driver";
        //Timestamp ts = new Timestamp(sh.getCreateTime().getTime());
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int resultSet = 0;

        try (Connection connection = DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(str)) {
                ps.setLong(1,num);
                ps.setLong(2,hwId);

                System.out.println(str);
                resultSet = ps.executeUpdate();

                connection.commit();
                return resultSet;

            }catch (SQLException exception){
                System.out.println("数据写入失败");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
        //  return executeUpdate(str, new Object[]{sh.getStudentId(),sh.getHomeworkId(),sh.getHomeworkTitle(),sh.getHomeworkContent(),ts});
    }

    public static List<Homework> selectAll3(){
        String sqlString = "SELECT * FROM j1.s_homework";

        List<Homework> list = new ArrayList<>();
    //    base b=new base();

        //  try(Connection connection =  b.getconn()) {
        try(Connection connection =  DatabasePool.getHikariDataSource().getConnection()) {
            connection.setAutoCommit(false);//不自动commit
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Homework h = new Homework();
                        h.setId(resultSet.getLong("id"));
                        h.setHomeworkTitle(resultSet.getString("title"));
                        h.setHomeworkContent(resultSet.getString("content"));
                        h.setCreateTime(resultSet.getTimestamp("create_time"));
                        List<StudentHomework> list2 =JdbcService.find12(h.getId());
                        int i=list2.size();
                        Long num=new Long((long)i);
                        h.setNum(num);
                        int i2=editHW(num,h.getId());
                        System.out.println(num);

                        list.add(h);

                    }
                    connection.commit(); //提交事务
                }catch (SQLException e1){
                    System.out.println("数据读取失败");
                    connection.rollback(); //回滚事务
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

}
