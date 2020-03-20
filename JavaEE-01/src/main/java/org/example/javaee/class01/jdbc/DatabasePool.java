package org.example.javaee.class01.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePool {
    private static HikariDataSource hikariDataSource;
    public static HikariDataSource getHikariDataSource(){
        if(null!=hikariDataSource){
            return hikariDataSource;
        }

        synchronized (DatabasePool.class){
            if(null==hikariDataSource){
                String jdbcUrl="jdbc:mysql://127.0.0.1:3306/j1?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT";
                String driverName = "com.mysql.cj.jdbc.Driver";
                HikariConfig hikariConfig=new HikariConfig();
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("123456");
                hikariConfig.setDriverClassName(driverName);
                hikariConfig.setJdbcUrl(jdbcUrl);

                hikariDataSource=new HikariDataSource(hikariConfig);
                return hikariDataSource;

            }
        }

        return null;

    }

    public static void main(String[] args) throws InterruptedException {
        while(true){
            getHikariDataSource();

            Thread.sleep(3000);
        }



    }
}
