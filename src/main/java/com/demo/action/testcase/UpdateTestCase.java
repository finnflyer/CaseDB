package com.demo.action.testcase;

import com.demo.model.common.User;
import com.demo.model.formbean.TCFormBean;
import com.demo.model.testcase.*;
import com.demo.service.testcase.TestCaseService;
import com.demo.util.EncryptUtil;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
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
 * Created by Admin on 2016/9/6.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class UpdateTestCase extends ActionSupport {
    private static final Logger logger = Logger.getLogger(UpdateTestCase.class);

    @Autowired
    private TestCaseService tcService;
    private TCFormBean tcFormbean = TCFormBean.getInstance();
    private TestCase testCase;
    private TestCaseInfo testCaseInfo;
    private String mapOs;
    private String testmodeId;
    private String mapBrand;
    private List<CaseLanguage> initalCaseLan;
    private List<LanguagesBean> LanBean;
    private String functionId;
    private String caseName;
    private String caseCode;
    private String modifyReason;
    private String version;
    private String sid;
    private List<CaseLanguage> modifyCaseLan;
    private String languageComment;
    private String HWinfo;
    private String HWinfoComment;
    private List<TestCaseContent> testcasecontent;
    private String mapTestMode;

    @Action(value = "UpdateCaseStepOne", results = {@Result(name = "success", location = "/jsp/TestCases/EditTestCaseStepTwo.jsp")})
    public String UpdateTestCaseStepOne() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        Map sessionMap = (HashMap) session.getAttribute(sid);
        String caseInstkey = EncryptUtil.deString(sessionMap.get("caseInstkey").toString());
        String caseInfoInstkey = EncryptUtil.deString(sessionMap.get("caseInfoInstkey").toString());
        User user = (User) session.getAttribute("user");

        mapOs = mapOs.replace(", ", "");
        mapBrand = mapBrand.replace(", ", "");
        mapTestMode = mapTestMode.replace(", ","");

        testCase = tcService.findById(caseInstkey);
        testCaseInfo = tcService.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);

        testCase.setCasecode(caseCode);
        testCase.setCasename(caseName);
        testCase.setCreator(user.getUsername());
        testCase.setOwner(user.getUsername());
        testCase.setVersion(version);
        testCase.setDate(new Date());

        testCaseInfo.setFuncid(Integer.valueOf(functionId));
        testCaseInfo.setTestmodeid(Integer.valueOf(mapTestMode));
        testCaseInfo.setModifyreason(modifyReason);
        testCaseInfo.setBrandid(Integer.valueOf(mapBrand));
        testCaseInfo.setOsid(mapOs);

        tcService.updateTestCaseInfo(testCaseInfo);
        tcService.update(testCase);
        LanBean = tcService.findLanguageBeanAll();
        initalCaseLan = tcService.findCaseLanguageByCaseId(caseInstkey);
        return SUCCESS;
    }

    @Action(value = "UpdateCaseStepTwo", results = {@Result(name = "success", location = "/jsp/TestCases/EditTestCaseStepFinal.jsp")})
    public String UpdateTestCaseStepTwo() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        Map sessionMap = (HashMap) session.getAttribute(sid);
        String caseInstkey = EncryptUtil.deString(sessionMap.get("caseInstkey").toString());
        String caseInfoInstkey = EncryptUtil.deString(sessionMap.get("caseInfoInstkey").toString());
        testCase = tcService.findById(caseInstkey);
        testCaseInfo = tcService
                .findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
        testCaseInfo.setComments(HWinfoComment);
        testCaseInfo.setHardwareinfo(HWinfo);
        testCaseInfo.setLanguagecomment(languageComment);
        tcService.updateTestCaseInfo(testCaseInfo);
        List<CaseLanguage> ctdCaseLan = tcService.findCaseLanguageByCaseId(caseInstkey);
        for (int i = 0; i <ctdCaseLan.size(); i++) {
            ctdCaseLan.get(i).setLocalset(modifyCaseLan.get(i).getLocalset());
        }
        for (CaseLanguage temp : ctdCaseLan)
            tcService.updateCaseLanguage(temp);

        testcasecontent = tcService.findCaseContentByCaseKey(caseInstkey);
        for (TestCaseContent temp : testcasecontent) {
            List<PictureBean> casePics = tcService.findPictureByCaseContentkey(temp.getCasecontentinstkey());
            temp.setCasePics(casePics);
        }
        return SUCCESS;
    }

    private List<TestCaseContent> tcContent;
    private List<PictureBean> picturebean;
    private List<File> upload;
    private List<String> uploadFileName = new ArrayList();
    private List<String> uploadContentType = new ArrayList();
    private List<OldPicRelation> olderPicRelations = new ArrayList();
    private String savePath;
    private String caseKey;

    @Action(value = "UpdateCaseStepFinal", results = {@Result(name = "success", type="redirect", location = "ShowTestCaseDetail",
    params = {"testCase.caseinstkey","%{sid}"})})
    public String UpdateTestCaseFinal() throws Exception {
        logger.info("Test case content item " + tcContent.size());

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Map sessionMap = (HashMap) session.getAttribute(sid);
        String caseInstkey = EncryptUtil.deString(sessionMap.get("caseInstkey").toString());
        String caseInfoInstkey = EncryptUtil.deString(sessionMap.get("caseInfoInstkey").toString());
        testCase=tcService.findById(caseInstkey);

        savePath = "D:\\CTDDataBase\\CasePics\\" + caseInstkey;
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
                saveFile.mkdir();
            }
            List<PictureBean> pictureBeanList = new ArrayList();
            if (upload != null) {
                for (int i = 0; i < upload.size(); i++) {
                    String[] strs = uploadFileName.get(i).split("\\.");
                    String surfix = strs[strs.length - 1];
                    String filename = strs[strs.length - 2];
                    FileOutputStream fos =
                            new FileOutputStream(savePath + "\\" + filename + "." + surfix);
                    FileInputStream fis = new FileInputStream(upload.get(i));
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    PictureBean cp = new PictureBean();
                    cp.setType(0);
                    cp.setFilepath(savePath + "\\" + filename + '.' + surfix);
                    cp.setFilename(uploadFileName.get(i));
                    cp.setCreatedate(new Date());
                    pictureBeanList.add(cp);
                }
            session.removeAttribute(sid);
        }

/**
 * @author Chill Huang 2012-6-23
 * @description update test case content by two ways a, if new test
 *              content items = older one, not create the new records,
 *              b, if new test content items != older one, then update
 *              older records ,not link to case id create the new
 *              records in DB. save the new data.
 */
        List<PictureBean> olderPictureBeans = new ArrayList<PictureBean>();
        List<TestCaseContent> olderTestCaseContent = tcService
                .findCaseContentByCaseKey(caseInstkey);
        for (TestCaseContent temp : olderTestCaseContent) {
            List<PictureBean> pictureBeans = tcService
                    .findPictureByCaseContentkey(temp.getCasecontentinstkey());
            for (PictureBean cp : pictureBeans) {
                olderPictureBeans.add(cp);
            }
        }
        TestCase testCase = tcService.findById(caseInstkey);
        for (TestCaseContent temp : olderTestCaseContent) {
            temp.setCaseinstkey(testCase.getCasecode());
            tcService.updateCaseContent(temp);
        }
        int order = 0;
        int totalTime = 0;
        for (TestCaseContent temp : tcContent) {
            if (temp != null) {
                temp.setCaseinstkey(caseInstkey);
                String testCaseContentInstkey = Generator.generatorID();
                temp.setCasecontentinstkey(testCaseContentInstkey);
                temp.setOrderid(order);

                tcService.saveTestCaseContent(temp);
                totalTime = totalTime + temp.getSteptime();
                // save Older content picture
                for (OldPicRelation relation : olderPicRelations) {
                    if (relation.getOldpic_order() == order) {
                        for (PictureBean cp : olderPictureBeans) {
                            if (cp.getFilepath().equalsIgnoreCase(
                                    relation.getOldpic_path())) {
                                cp.setCasecontentinstkey(testCaseContentInstkey);
                                cp.setCaseinstkey(caseInstkey);
                                tcService.savePictureBean(cp);
                            }
                        }
                    }
                }
                // save Each content picture.
                if (temp.getHasPic().get(0) != -1) {
                    for (Integer hasPic : temp.getHasPic()) {
                        pictureBeanList.get(0).setCasecontentinstkey(
                                testCaseContentInstkey);
                        pictureBeanList.get(0).setCaseinstkey(caseInstkey);
                        pictureBeanList.get(0).setPictureinstkey(
                                Generator.generatorID());
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


        session.removeAttribute("EditCaseMode");
        sid = EncryptUtil.enString(testCase.getCaseinstkey());
        return SUCCESS;
    }


    public TCFormBean getTcFormbean() {
        return tcFormbean;
    }

    public void setTcFormbean(TCFormBean tcFormbean) {
        this.tcFormbean = tcFormbean;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestCaseInfo getTestCaseInfo() {
        return testCaseInfo;
    }

    public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
        this.testCaseInfo = testCaseInfo;
    }

    public String getMapOs() {
        return mapOs;
    }

    public void setMapOs(String mapOs) {
        this.mapOs = mapOs;
    }

    public String getTestmodeId() {
        return testmodeId;
    }

    public void setTestmodeId(String testmodeId) {
        this.testmodeId = testmodeId;
    }

    public String getMapBrand() {
        return mapBrand;
    }

    public void setMapBrand(String mapBrand) {
        this.mapBrand = mapBrand;
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

    public List<TestCaseContent> getTestcasecontent() {
        return testcasecontent;
    }

    public void setTestcasecontent(List<TestCaseContent> testcasecontent) {
        this.testcasecontent = testcasecontent;
    }

    public List<TestCaseContent> getTcContent() {
        return tcContent;
    }

    public void setTcContent(List<TestCaseContent> tcContent) {
        this.tcContent = tcContent;
    }

    public List<PictureBean> getPicturebean() {
        return picturebean;
    }

    public void setPicturebean(List<PictureBean> picturebean) {
        this.picturebean = picturebean;
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

    public List<OldPicRelation> getOlderPicRelations() {
        return olderPicRelations;
    }

    public void setOlderPicRelations(List<OldPicRelation> olderPicRelations) {
        this.olderPicRelations = olderPicRelations;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public List<LanguagesBean> getLanBean() {
        return LanBean;
    }

    public void setLanBean(List<LanguagesBean> lanBean) {
        LanBean = lanBean;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setInitalCaseLan(List<CaseLanguage> initalCaseLan) {
        this.initalCaseLan = initalCaseLan;
    }

    public List<CaseLanguage> getInitalCaseLan() {
        return initalCaseLan;
    }

    public void setModifyCaseLan(List<CaseLanguage> modifyCaseLan) {
        this.modifyCaseLan = modifyCaseLan;
    }

    public List<CaseLanguage> getModifyCaseLan() {
        return modifyCaseLan;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMapTestMode() {
        return mapTestMode;
    }

    public void setMapTestMode(String mapTestMode) {
        this.mapTestMode = mapTestMode;
    }
}
