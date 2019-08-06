package com.demo.action.testcase;

import com.demo.model.testcase.PictureBean;
import com.demo.service.testcase.PictureService;
import com.demo.service.testcase.TestCaseService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Admin on 2016/9/6.
 */
@Namespace("/download")
public class ShowPictureBean extends ActionSupport {
    @Autowired
    private PictureService pictureService;
    private String photoPath;

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    @Action(value = "ShowPicture", results = {})
    public String ShowPicture() throws Exception {
        HttpServletResponse resp = ServletActionContext.getResponse();

        resp.setContentType("image/jpeg");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");

        PictureBean pictureBean = pictureService.findById(photoPath);
        //PrintWriter out = resp.getWriter();
        ServletOutputStream out = resp.getOutputStream();
        FileInputStream is = new FileInputStream(new File(pictureBean.getFilepath()));
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
