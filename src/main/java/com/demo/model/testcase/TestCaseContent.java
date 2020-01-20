package com.demo.model.testcase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcasecontent", schema = "", catalog = "casedb")
public class TestCaseContent {
    private String casecontentinstkey;
    private String caseinstkey;
    private int orderid;
    private String caselevel;
    private String testitem;
    private String teststep;
    private String testresult;
    private int steptime;
    private String comment;
    private String priority;
    private List<Integer> hasPic=new ArrayList();
    private String picName;
    private List<PictureBean>   CasePics;

    @Transient
    public List<PictureBean> getCasePics() {
        return CasePics;
    }

    public void setCasePics(List<PictureBean> casePics) {
        CasePics = casePics;
    }

    @Transient //不用映射为数据库字段
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @Transient
    public List<Integer> getHasPic() {
        return hasPic;
    }

    public void setHasPic(List<Integer> hasPic) {
        this.hasPic = hasPic;
    }

    @Id
    @Column(name = "CASECONTENTINSTKEY")
    public String getCasecontentinstkey() {
        return casecontentinstkey;
    }

    public void setCasecontentinstkey(String casecontentinstkey) {
        this.casecontentinstkey = casecontentinstkey;
    }

    @Basic
    @Column(name = "CASEINSTKEY")
    public String getCaseinstkey() {
        return caseinstkey;
    }

    public void setCaseinstkey(String caseinstkey) {
        this.caseinstkey = caseinstkey;
    }

    @Basic
    @Column(name = "ORDERID")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "CASELEVEL")
    public String getCaselevel() {
        return caselevel;
    }

    public void setCaselevel(String caselevel) {
        this.caselevel = caselevel;
    }

    @Basic
    @Column(name = "TESTITEM")
    public String getTestitem() {
        return testitem;
    }

    public void setTestitem(String testitem) {
        this.testitem = testitem;
    }

    @Basic
    @Column(name = "TESTSTEP")
    public String getTeststep() {
        return teststep;
    }

    public void setTeststep(String teststep) {
        this.teststep = teststep;
    }

    @Basic
    @Column(name = "TESTRESULT")
    public String getTestresult() {
        return testresult;
    }

    public void setTestresult(String testresult) {
        this.testresult = testresult;
    }

    @Basic
    @Column(name = "STEPTIME")
    public int getSteptime() {
        return steptime;
    }

    public void setSteptime(int steptime) {
        this.steptime = steptime;
    }

    @Basic
    @Column(name = "COMMENTS")
    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comments;
    }
    @Basic
    @Column(name = "PRIORITY")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
