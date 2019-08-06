package com.demo.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.aspectj.util.FileUtil;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by finnf on 2018/5/29.
 */
@Namespace("/test")
@Scope("property")
@ParentPackage("custom-default")
public class UEUploadImage extends ActionSupport {

    private File[] upfile;
    private String[] upfileFileName;
    private String[] upfileContentType;
    private String caseInstkey;
    private String caseInfoInstkey;

    @Action(value = "ueuploadimage")
    public void ueUploadImgae() {

        if (upfile == null) {
            resultUEMessage("图片不能为空", false, "");
            return;
        }
        for (int i = 0; i < upfile.length; i++) {
            try {
                String name = upfileFileName[i];
                String filePath = "C:\\CTDDataBase\\CasePics\\Test.png";
                File file = new File(filePath);
                FileUtil.copyFile(upfile[i],file);
            } catch (IOException e) {
                e.printStackTrace();
                resultUEMessage("IO异常", false, "");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                resultUEMessage("上传失败", false, "");
                return;
            }
        }
        String url = "/test/showImage";
        resultUEMessage("SUCCESS", true, url);
        return;
    }

    /**
     * 返回UEditor的信息
     *
     * @param message 错误的消息提示
     * @param status  是否成功
     * @param url     图片的URL
     */
    private void resultUEMessage(String message, boolean status, String url) {
        JSONObject jsobject = new JSONObject();
        if (status) {
            jsobject.put("state", message);
            jsobject.put("url", url);
            jsobject.put("original", "");
        } else {
            jsobject.put("state", message);
            jsobject.put("url", "");
            jsobject.put("title", "");
            jsobject.put("original", "");
        }

        ServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        try {
            String outputStr = "";
            if (jsobject != null) {
                outputStr = jsobject.toString();
            }
            // ServletOutputStream对UTF-8的支持不好，此处用Writer
            PrintWriter out = response.getWriter();
            out.write(outputStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File[] getUpfile() {
        return upfile;
    }

    public void setUpfile(File[] upfile) {
        this.upfile = upfile;
    }

    public String[] getUpfileFileName() {
        return upfileFileName;
    }

    public void setUpfileFileName(String[] upfileFileName) {
        this.upfileFileName = upfileFileName;
    }

    public String[] getUpfileContentType() {
        return upfileContentType;
    }

    public void setUpfileContentType(String[] upfileContentType) {
        this.upfileContentType = upfileContentType;
    }

    public String getCaseInfoInstkey() {
        return caseInfoInstkey;
    }

    public void setCaseInfoInstkey(String caseInfoInstkey) {
        this.caseInfoInstkey = caseInfoInstkey;
    }

    public String getCaseInstkey() {
        return caseInstkey;
    }

    public void setCaseInstkey(String caseInstkey) {
        this.caseInstkey = caseInstkey;
    }
}
