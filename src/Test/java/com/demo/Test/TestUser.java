package com.demo.Test;


import com.demo.model.common.User;
import com.demo.service.common.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by admin on 2016/8/31.
 */
public class TestUser {
    ApplicationContext ac = null;

    @Before
    public void init(){
        ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void test1(){
        System.out.println("进入TestAction");
    }
    @Test
    public void test2(){
        UserService userService = (UserService)ac.getBean("userService");
        User user = userService.findByName("Raylei");

        System.out.println(user.getInstkey());
    }

}
