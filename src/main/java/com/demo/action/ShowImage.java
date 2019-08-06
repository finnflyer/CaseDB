package com.demo.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by finnf on 2018/5/29.
 */
@Namespace("/test")
@Scope("property")
@ParentPackage("custom-default")
public class ShowImage extends ActionSupport{


    @Action(value = "showImage")
    public String showImage() throws Exception{
        HttpServletResponse resp = ServletActionContext.getResponse();

        resp.setContentType("image/jpeg");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");

        String filePath = "C:\\CTDDataBase\\CasePics\\Test.png";
        ServletOutputStream out = resp.getOutputStream();
        FileInputStream is = new FileInputStream(new File(filePath));
        byte[] buffer = new byte[1024];
        int length = 0;

        while (-1 != (length = is.read(buffer))) {
            out.write(buffer, 0, length);
        }

        is.close();
        out.flush();
        out.close();
        return null;
    }
}
