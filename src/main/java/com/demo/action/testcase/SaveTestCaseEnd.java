package com.demo.action.testcase;

import com.demo.model.testcase.PictureBean;
import com.demo.model.testcase.TestCaseContent;
import com.demo.model.testcase.TestCaseInfo;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by admin on 2016/9/3.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class SaveTestCaseEnd extends ActionSupport {
    @Autowired
    private TestCaseService tcService;

    private String caseInstkey;
    private String caseInfoInstkey;
    private List<TestCaseContent> items;
    private String pictureInstkey;
    private List<File> upload;
    private List<String> uploadFileName=new ArrayList();
    private List<String> uploadContentType=new ArrayList();
    private String savePath;
    @Action(value="SaveCaseEnd",results = {@Result(name="success",type = "redirect",location ="TestCaseHome")})
    public String SaveCaseEnd() throws Exception{

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Map sessionMap =(HashMap) session.getAttribute("CreateCaseMode");
        caseInstkey = sessionMap.get("caseInstkey").toString();
        caseInfoInstkey = sessionMap.get("caseInfoInstkey").toString();

        savePath = "C:\\CTDDataBase\\CasePics\\"+caseInstkey;
        File saveFile = new File(savePath);
        if(!saveFile.exists()){
            saveFile.mkdir();
        }
        List<PictureBean> pictureBeanList = new ArrayList();
        if(upload != null){
            for (int i=0;i<upload.size();i++){
                String[] strs=uploadFileName.get(i).split("\\.");
                String surfix=strs[strs.length-1];
                String filename=strs[strs.length-2];
                java.io.FileOutputStream fos=new FileOutputStream(savePath+"\\"+filename+'.'+surfix);
                FileInputStream fis=new FileInputStream(upload.get(i));
                byte[] buffer=new byte[1024];
                int len=0;
                while((len=fis.read(buffer))>0){
                    fos.write(buffer,0,len);
                }
                PictureBean cp=new PictureBean();
                cp.setType(0);
                cp.setFilepath(savePath + "\\" + filename + '.' + surfix);
                cp.setFilename(uploadFileName.get(i));
                cp.setCreatedate(new Date());
                pictureBeanList.add(cp);
            }
        }

        int order=0;
        int totalTime=0;
        for(TestCaseContent temp:items){
            if(temp!=null){
                temp.setCaseinstkey(caseInstkey);
                String testCaseContentInstkey = Generator.generatorID();
                temp.setCasecontentinstkey(testCaseContentInstkey);
                temp.setOrderid(order);
                totalTime = totalTime+temp.getSteptime();
                tcService.saveTestCaseContent(temp);
                //save Each content picture.
                if(temp.getHasPic().get(0)!= -1){
                    for(Integer hasPic:temp.getHasPic()){
                        pictureBeanList.get(0).setCasecontentinstkey(testCaseContentInstkey);
                        pictureBeanList.get(0).setCaseinstkey(caseInstkey);
                        pictureBeanList.get(0).setPictureinstkey(Generator.generatorID());
                        tcService.savePictureBean(pictureBeanList.get(0));
                        pictureBeanList.remove(0);
                    }
                }
                order++;
            }
        }
        TestCaseInfo testCaseInfo = tcService.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
        testCaseInfo.setExecutetime(totalTime);
        tcService.updateTestCaseInfo(testCaseInfo);

        session.removeAttribute("CreateCaseMode");

        return SUCCESS;
    }

    public String getCaseInstkey() {
        return caseInstkey;
    }

    public void setCaseInstkey(String caseInstkey) {
        this.caseInstkey = caseInstkey;
    }

    public String getCaseInfoInstkey() {
        return caseInfoInstkey;
    }

    public void setCaseInfoInstkey(String caseInfoInstkey) {
        this.caseInfoInstkey = caseInfoInstkey;
    }

    public List<TestCaseContent> getItems() {
        return items;
    }

    public void setItems(List<TestCaseContent> items) {
        this.items = items;
    }

    public String getPictureInstkey() {
        return pictureInstkey;
    }

    public void setPictureInstkey(String pictureInstkey) {
        this.pictureInstkey = pictureInstkey;
    }

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
