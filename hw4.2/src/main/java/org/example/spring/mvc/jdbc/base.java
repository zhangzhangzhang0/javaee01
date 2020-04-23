package org.example.spring.mvc.jdbc;



import java.sql.*;



public class base {



    //用于增删改的方法

    //接受sql语句和对象数组

    public int executeUpdate(String sql ,Object []ob) throws SQLException {

        // Connection conn=DatabasePool.getHikariDataSource().getConnection();

        PreparedStatement ps=null;



        try (Connection conn =  DatabasePool.getHikariDataSource().getConnection()){

            ps=prepareStatement(conn,sql,ob);

            int i=ps.executeUpdate();

            // System.out.println(i);

            return i;



        } catch (SQLException e) {

            e.printStackTrace();

            System.out.println("sql错了");

            return 0;

        }

    }



    /***

     * 查询方法

     */

    protected PreparedStatement prepareStatement(Connection conn,String sql,Object []ob){

        PreparedStatement ps=null;

        try {



            int index=1;

            ps = conn.prepareStatement(sql);

            if(ps!=null&&ob!=null){

                for (int i = 0; i < ob.length; i++) {

                    ps.setObject(index, ob[i]);

                    index++;

                }

            }



        } catch (SQLException e1) {

            System.out.println("false");

            e1.printStackTrace();



        }

        return ps;

    }

}