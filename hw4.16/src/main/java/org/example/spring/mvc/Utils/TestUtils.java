package org.example.spring.mvc.Utils;

import org.example.spring.mvc.jdbc.JdbcService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUtils {
    public static void main (String[] args){
        AnnotationConfigApplicationContext c=new AnnotationConfigApplicationContext(TestUtils.class);
        JdbcService jdbcService=c.getBean("jdbcService",JdbcService.class);
        jdbcService.selectAll1();
    }
}
