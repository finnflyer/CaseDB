package com.demo.action.testcase;

import com.demo.model.testcase.CaseLanguage;
import com.demo.model.testcase.LanguagesBean;
import com.demo.model.testcase.PictureBean;
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
public class SaveTestCaseSecond extends ActionSupport {
    @Autowired
    private TestCaseService testCaseService;
    private String languageComment;
    private String HWinfo;
    private String HWinfoComment;
    private List<CaseLanguage> lanMap;
    private String caseInfoInstkey;
    private String caseInstkey;
    private List<CaseLanguage> CaseLan;
    private List<LanguagesBean> LanBean;
    private String caseName;
    private List<File> accessory = new ArrayList<File>();
    private List<String> accessoryFileName = new ArrayList<String>();
    private List<String> accessoryContentType = new ArrayList<String>();

    @Action(value = "SaveStepTwo",results={@Result(name="success",location ="/jsp/TestCases/StepThree.jsp")
            , @Result(name="input",location = "/jsp/TestCases/StepTwo.jsp")})
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Map sessionMap =(HashMap) session.getAttribute("CreateCaseMode");
        caseInstkey = sessionMap.get("caseInstkey").toString();
        caseInfoInstkey = sessionMap.get("caseInfoInstkey").toString();
        String savePath = "C:\\CTDDataBase\\Attachment";
        TestCaseInfo testCaseInfo = testCaseService
                .findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
        testCaseInfo.setComments(HWinfoComment);
        testCaseInfo.setHardwareinfo(HWinfo);
        testCaseInfo.setLanguagecomment(languageComment);
        testCaseService.updateTestCaseInfo(testCaseInfo);
        List<CaseLanguage> ctdCaseLan = new ArrayList();
        for(int i=2;i<32;i++){
            //lanMap.get(i).setLanguagekey(i-1);
            lanMap.get(i).setCaseinstkey(caseInstkey);
            ctdCaseLan.add(lanMap.get(i));
        }
        testCaseService.saveCaseLanMap(ctdCaseLan);
//
//        if(accessory!=null){
//            for (int i = 0; i < accessory.size(); i++) {
//                String[] strs = accessoryFileName.get(i).split("\\.");
//                String surfix = strs[strs.length - 1];
//                String filename = strs[strs.length - 2];
//                savePath = savePath + "\\" + caseInstkey;
//                File saveFile = new File(savePath);
//                if (!saveFile.exists()) {
//                    saveFile.mkdir();
//                }
//                java.io.FileOutputStream fos = new FileOutputStream(savePath + "\\"
//                        + filename + '.' + surfix);
//                FileInputStream fis = new FileInputStream(accessory.get(i));
//                byte[] buffer = new byte[1024];
//                int len = 0;
//                while ((len = fis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, len);
//                }
//                PictureBean pictureBean = new PictureBean();
//                pictureBean.setType(1);
//                pictureBean.setCaseinstkey(caseInstkey);
//                pictureBean.setCasecontentinstkey("");
//                pictureBean.setFilepath(filename + '.' + surfix);
//                pictureBean.setFilename(accessoryFileName.get(i));
//                pictureBean.setPictureinstkey(Generator.generatorID());
//                pictureBean.setCreatedate(new Date());
//                testCaseService.savePictureBean(pictureBean);
//            }
//        }
        return SUCCESS;
    }
    @Override
    public void validate() {

        if (null == languageComment || "".equals(languageComment)) {
            this.addFieldError("languageComment",
                    " The languageComment field is required and should not be null");
        }
        if (null == HWinfo || "".equals(HWinfo)) {
            this.addFieldError("HWinfo",
                    " The HWinfo field is required and should not be null");
        }
        if (null == HWinfoComment || "".equals(HWinfoComment)) {
            this.addFieldError("HWinfoComment",
                    " The HWinfoComment field is required and should not be null");
        }
    }
    public String getLanguageComment() {
        return languageComment;
    }

    public void setLanguageComment(String languageComment) {
        this.languageComment = languageComment;
    }

    public String getHWinfo() {
        return HWinfo;
    }

    public void setHWinfo(String HWinfo) {
        this.HWinfo = HWinfo;
    }

    public String getHWinfoComment() {
        return HWinfoComment;
    }

    public void setHWinfoComment(String HWinfoComment) {
        this.HWinfoComment = HWinfoComment;
    }

    public List<CaseLanguage> getLanMap() {
        return lanMap;
    }

    public void setLanMap(List<CaseLanguage> lanMap) {
        this.lanMap = lanMap;
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

    public List<CaseLanguage> getCaseLan() {
        return CaseLan;
    }

    public void setCaseLan(List<CaseLanguage> caseLan) {
        CaseLan = caseLan;
    }

    public List<LanguagesBean> getLanBean() {
        return LanBean;
    }

    public void setLanBean(List<LanguagesBean> lanBean) {
        LanBean = lanBean;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public List<File> getAccessory() {
        return accessory;
    }

    public void setAccessory(List<File> accessory) {
        this.accessory = accessory;
    }

    public List<String> getAccessoryFileName() {
        return accessoryFileName;
    }

    public void setAccessoryFileName(List<String> accessoryFileName) {
        this.accessoryFileName = accessoryFileName;
    }

    public List<String> getAccessoryContentType() {
        return accessoryContentType;
    }

    public void setAccessoryContentType(List<String> accessoryContentType) {
        this.accessoryContentType = accessoryContentType;
    }
}
