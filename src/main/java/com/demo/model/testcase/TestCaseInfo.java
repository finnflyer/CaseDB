package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcaseinfo", schema = "", catalog = "casedb")
public class TestCaseInfo {
    private String caseinfoinstkey;
    private String caseinstkey;
    private Integer executetime;
    private int brandid;
    private int funcid;
    private Integer testmodeid;
    private String osid;
    private String componerowner;
    private String automation;
    private String hardwareinfo;
    private String languagecomment;
    private String comments;
    private String modifyreason;
    private String introduction;

    private String brandCato;
    private String funcCato;
    private String osCato;

    @Transient
    public String getBrandCato() {
        return brandCato;
    }

    public void setBrandCato(String brandCato) {
        this.brandCato = brandCato;
    }
@Transient
    public String getFuncCato() {
        return funcCato;
    }

    public void setFuncCato(String funcCato) {
        this.funcCato = funcCato;
    }
@Transient
    public String getOsCato() {
        return osCato;
    }

    public void setOsCato(String osCato) {
        this.osCato = osCato;
    }

    @Id
    @Column(name = "CASEINFOINSTKEY")
    public String getCaseinfoinstkey() {
        return caseinfoinstkey;
    }

    public void setCaseinfoinstkey(String caseinfoinstkey) {
        this.caseinfoinstkey = caseinfoinstkey;
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
    @Column(name = "EXECUTETIME")
    public Integer getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Integer executetime) {
        this.executetime = executetime;
    }

    @Basic
    @Column(name = "BRANDID")
    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    @Basic
    @Column(name = "FUNCID")
    public int getFuncid() {
        return funcid;
    }

    public void setFuncid(int funcid) {
        this.funcid = funcid;
    }

    @Basic
    @Column(name = "TESTMODEID")
    public Integer getTestmodeid() {
        return testmodeid;
    }

    public void setTestmodeid(Integer testmodeid) {
        this.testmodeid = testmodeid;
    }

    @Basic
    @Column(name = "OSID")
    public String getOsid() {
        return osid;
    }

    public void setOsid(String osid) {
        this.osid = osid;
    }

    @Basic
    @Column(name = "COMPONEROWNER")
    public String getComponerowner() {
        return componerowner;
    }

    public void setComponerowner(String componerowner) {
        this.componerowner = componerowner;
    }

    @Basic
    @Column(name = "AUTOMATION")
    public String getAutomation() {
        return automation;
    }

    public void setAutomation(String automation) {
        this.automation = automation;
    }

    @Basic
    @Column(name = "HARDWAREINFO")
    public String getHardwareinfo() {
        return hardwareinfo;
    }

    public void setHardwareinfo(String hardwareinfo) {
        this.hardwareinfo = hardwareinfo;
    }

    @Basic
    @Column(name = "LANGUAGECOMMENT")
    public String getLanguagecomment() {
        return languagecomment;
    }

    public void setLanguagecomment(String languagecomment) {
        this.languagecomment = languagecomment;
    }

    @Basic
    @Column(name = "COMMENTS")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "MODIFYREASON")
    public String getModifyreason() {
        return modifyreason;
    }

    public void setModifyreason(String modifyreason) {
        this.modifyreason = modifyreason;
    }

    @Basic
    @Column(name = "INTRODUCTION")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseInfo that = (TestCaseInfo) o;

        if (brandid != that.brandid) return false;
        if (funcid != that.funcid) return false;
        if (caseinfoinstkey != null ? !caseinfoinstkey.equals(that.caseinfoinstkey) : that.caseinfoinstkey != null)
            return false;
        if (caseinstkey != null ? !caseinstkey.equals(that.caseinstkey) : that.caseinstkey != null) return false;
        if (executetime != null ? !executetime.equals(that.executetime) : that.executetime != null) return false;
        if (testmodeid != null ? !testmodeid.equals(that.testmodeid) : that.testmodeid != null) return false;
        if (osid != null ? !osid.equals(that.osid) : that.osid != null) return false;
        if (componerowner != null ? !componerowner.equals(that.componerowner) : that.componerowner != null)
            return false;
        if (automation != null ? !automation.equals(that.automation) : that.automation != null) return false;
        if (hardwareinfo != null ? !hardwareinfo.equals(that.hardwareinfo) : that.hardwareinfo != null) return false;
        if (languagecomment != null ? !languagecomment.equals(that.languagecomment) : that.languagecomment != null)
            return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (modifyreason != null ? !modifyreason.equals(that.modifyreason) : that.modifyreason != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseinfoinstkey != null ? caseinfoinstkey.hashCode() : 0;
        result = 31 * result + (caseinstkey != null ? caseinstkey.hashCode() : 0);
        result = 31 * result + (executetime != null ? executetime.hashCode() : 0);
        result = 31 * result + brandid;
        result = 31 * result + funcid;
        result = 31 * result + (testmodeid != null ? testmodeid.hashCode() : 0);
        result = 31 * result + (osid != null ? osid.hashCode() : 0);
        result = 31 * result + (componerowner != null ? componerowner.hashCode() : 0);
        result = 31 * result + (automation != null ? automation.hashCode() : 0);
        result = 31 * result + (hardwareinfo != null ? hardwareinfo.hashCode() : 0);
        result = 31 * result + (languagecomment != null ? languagecomment.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (modifyreason != null ? modifyreason.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        return result;
    }
}
