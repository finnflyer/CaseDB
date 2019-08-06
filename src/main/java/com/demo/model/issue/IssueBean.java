package com.demo.model.issue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/9/8.
 */
@Entity
@Table(name = "ctdissue", schema = "", catalog = "casedb")
public class IssueBean implements Serializable {
    private String instkey;
    private String ecrNumber;
    private String issueName;
    private String description;
    private String configuration;
    private int testSite;
    private String caseNum;
    private String priority;
    private String projectinstkey;
    private int phaseFound;
    private String component;
    private String issueStatus;
    private String language;
    private String reproduceStep;
    private int osid;
    private String platform;
    private Date createdate;
    private String owner;
    private String issuestyle;
    private Date editordate;

    private String osCato;
    private String phaseCato;
    private String testSiteCato;

    private String comments;
    private String createBy;

    @Transient
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Transient
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Transient
    public String getOsCato() {
        return osCato;
    }

    public void setOsCato(String osCato) {
        this.osCato = osCato;
    }

    @Transient
    public String getPhaseCato() {
        return phaseCato;
    }

    public void setPhaseCato(String phaseCato) {
        this.phaseCato = phaseCato;
    }

    @Transient
    public String getTestSiteCato() {
        return testSiteCato;
    }

    public void setTestSiteCato(String testSiteCato) {
        this.testSiteCato = testSiteCato;
    }

    @Id
    @Column(name = "Instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "ECRNumber")
    public String getEcrNumber() {
        return ecrNumber;
    }

    public void setEcrNumber(String ecrNumber) {
        this.ecrNumber = ecrNumber;
    }

    @Basic
    @Column(name = "IssueName")
    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Configuration")
    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    @Basic
    @Column(name = "TestSite")
    public int getTestSite() {
        return testSite;
    }

    public void setTestSite(int testSite) {
        this.testSite = testSite;
    }

    @Basic
    @Column(name = "CaseNum")
    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    @Basic
    @Column(name = "Priority")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "Projectinstkey")
    public String getProjectinstkey() {
        return projectinstkey;
    }

    public void setProjectinstkey(String projectinstkey) {
        this.projectinstkey = projectinstkey;
    }

    @Basic
    @Column(name = "PhaseFound")
    public int getPhaseFound() {
        return phaseFound;
    }

    public void setPhaseFound(int phaseFound) {
        this.phaseFound = phaseFound;
    }


    @Basic
    @Column(name = "Component")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Basic
    @Column(name = "IssueStatus")
    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    @Basic
    @Column(name = "Language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "ReproduceStep")
    public String getReproduceStep() {
        return reproduceStep;
    }

    public void setReproduceStep(String reproduceStep) {
        this.reproduceStep = reproduceStep;
    }

    @Basic
    @Column(name = "Osid")
    public int getOsid() {
        return osid;
    }

    public void setOsid(int osid) {
        this.osid = osid;
    }

    @Basic
    @Column(name = "Platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Basic
    @Column(name = "createdate")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    @Basic
    @Column(name = "editordate")
    public Date getEditordate() {
        return editordate;
    }

    public void setEditordate(Date editordate) {
        this.editordate = editordate;
    }

    @Basic
    @Column(name = "issuestyle")
    public String getIssuestyle() {
        return issuestyle;
    }

    public void setIssuestyle(String issuestyle) {
        this.issuestyle = issuestyle;
    }

}
