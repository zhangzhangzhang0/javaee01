package org.example.spring.mvc.bean;

import org.example.spring.mvc.jdbc.JdbcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean("jdbcService")
    public JdbcService jdbcService(){
        return new JdbcService();
    }
}

