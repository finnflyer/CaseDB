package com.demo.Test;

import com.demo.model.common.User;
import com.demo.model.testcase.SearchCaseBean;
import com.demo.service.common.UserService;
import com.demo.service.testcase.SearchCaseService;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.QueryResult;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedHashMap;

/**
 * Created by finnf on 2018/10/14.
 */
public class TestHibernate {
    ApplicationContext ac = null;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-hibernate.xml"});
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    @Test
    public void TestHibernateUser(){
        UserService userService = (UserService) ac.getBean("userService");
        User user = userService.findByName("Admin");
        System.out.println(user.getUsername());
    }

    @Test
    public void TestHibernateTestCase(){
        SearchCaseService searchCaseService = (SearchCaseService) ac.getBean("searchCaseService");
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("casecode ", "asc");
        String where = "upper(casename) like ?0 and upper(owner) like ?1";
        String[] param = {"%%", "%%"};
        QueryResult<SearchCaseBean> resultList = searchCaseService.getScrollData(0, 30, where, param, orderby);
        System.out.println(resultList.getTotalCount());
    }
}
