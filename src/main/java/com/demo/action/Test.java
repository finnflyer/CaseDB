package com.demo.action;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


/**
 * Created by finnf on 2018/7/4.
 */
@Namespace("/user")
@ParentPackage("json-default")
public class Test extends ActionSupport {

    private JSONObject dataOb;

    public JSONObject getDataOb() {
        return dataOb;
    }

    public void setDataOb(JSONObject dataOb) {
        this.dataOb = dataOb;
    }

    @Action(value = "",results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String excetue() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse response = ServletActionContext.getResponse();
        String URI = request.getRequestURI().replace("/", "\\");
        //文件路径自行替换一下就行,就是上图中生成验证文件的路径,因为URI中已经包含了/.well-known/acme-challenge/,所以这里不需要
        File file = new File("D:\\win-acme.v1.9.11.1\\dtf.lenovo.com\\CaseDB\\" + URI);
        InputStream is = new FileInputStream(file);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(("验证文件").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }


        return SUCCESS;
    }

}
