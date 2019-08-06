package com.demo.util;

import com.demo.model.common.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

/**
 * Created by admin on 2016/9/4.
 */
public class LoginInterceptor extends AbstractInterceptor {
    /**
     * @Title: intercept
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param arg0
     * @param @return
     * @param @throws Exception    设定文件
     * @throws
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("=============拦截器============="+new Date().toString());

        User user = (User) ActionContext.getContext().getSession().get("user");
        if(user!=null)
            return invocation.invoke();
        return "loginError";
    }
}
