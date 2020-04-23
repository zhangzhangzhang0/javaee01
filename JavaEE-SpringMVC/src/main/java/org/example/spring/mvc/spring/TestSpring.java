package org.example.spring.mvc.spring;

import org.example.spring.mvc.bean.Beans;
import org.example.spring.mvc.bean.TestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.*;

public class TestSpring {

    public static void main(String[] args){
        AnnotationConfigApplicationContext Context=new AnnotationConfigApplicationContext(Beans.class);

     //   ApplicationContext context=new ClassPathWmlApplicationContext("app-context.xml");
    //    TestBean service=context.getBean("testBean",TestBean.class);
     //   System.out.println(service.toString());

     //   JdbcServlet service=context.getBean("jdbcServlet",JdbcService.class);
     //   service.getById(100L);
    }
}
