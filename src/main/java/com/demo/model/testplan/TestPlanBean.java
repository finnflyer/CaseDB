package com.demo.model.testplan;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/5.
 */
@Entity
@Table(name = "ctdtestplan", schema = "", catalog = "casedb")
public class TestPlanBean implements Serializable{
    private String testplaninstkey;
    private String testplanname;
    private String testplantype;
    private String projectinstkey;
    private String projectname;
    private String testplanownerinstkey;
    private Date createdate;
    private String testplanstatus;
    private String testplancomments;
    private String testplandescription;
    private String testplanowner;

    @Id
    @Column(name = "TESTPLANINSTKEY")
    public String getTestplaninstkey() {
        return testplaninstkey;
    }

    public void setTestplaninstkey(String testplaninstkey) {
        this.testplaninstkey = testplaninstkey;
    }

    @Basic
    @Column(name = "TESTPLANNAME")
    public String getTestplanname() {
        return testplanname;
    }

    public void setTestplanname(String testplanname) {
        this.testplanname = testplanname;
    }

    @Basic
    @Column(name = "TESTPLANTYPE")
    public String getTestplantype() {
        return testplantype;
    }

    public void setTestplantype(String testplantype) {
        this.testplantype = testplantype;
    }

    @Basic
    @Column(name = "PROJECTINSTKEY")
    public String getProjectinstkey() {
        return projectinstkey;
    }

    public void setProjectinstkey(String projectinstkey) {
        this.projectinstkey = projectinstkey;
    }

    @Basic
    @Column(name = "PROJECTNAME")
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "TESTPLANOWNERINSTKEY")
    public String getTestplanownerinstkey() {
        return testplanownerinstkey;
    }

    public void setTestplanownerinstkey(String testplanownerinstkey) {
        this.testplanownerinstkey = testplanownerinstkey;
    }

    @Basic
    @Column(name = "CREATEDATE")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "TESTPLANSTATUS")
    public String getTestplanstatus() {
        return testplanstatus;
    }

    public void setTestplanstatus(String testplanstatus) {
        this.testplanstatus = testplanstatus;
    }

    @Basic
    @Column(name = "TESTPLANCOMMENTS")
    public String getTestplancomments() {
        return testplancomments;
    }

    public void setTestplancomments(String testplancomments) {
        this.testplancomments = testplancomments;
    }

    @Basic
    @Column(name = "TESTPLANDESCRIPTION")
    public String getTestplandescription() {
        return testplandescription;
    }

    public void setTestplandescription(String testplandescription) {
        this.testplandescription = testplandescription;
    }

    @Basic
    @Column(name = "TESTPLANOWNER")
    public String getTestplanowner() {
        return testplanowner;
    }

    public void setTestplanowner(String testplanowner) {
        this.testplanowner = testplanowner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestPlanBean that = (TestPlanBean) o;

        if (testplaninstkey != null ? !testplaninstkey.equals(that.testplaninstkey) : that.testplaninstkey != null)
            return false;
        if (testplanname != null ? !testplanname.equals(that.testplanname) : that.testplanname != null) return false;
        if (testplantype != null ? !testplantype.equals(that.testplantype) : that.testplantype != null) return false;
        if (projectinstkey != null ? !projectinstkey.equals(that.projectinstkey) : that.projectinstkey != null)
            return false;
        if (projectname != null ? !projectname.equals(that.projectname) : that.projectname != null) return false;
        if (testplanownerinstkey != null ? !testplanownerinstkey.equals(that.testplanownerinstkey) : that.testplanownerinstkey != null)
            return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (testplanstatus != null ? !testplanstatus.equals(that.testplanstatus) : that.testplanstatus != null)
            return false;
        if (testplancomments != null ? !testplancomments.equals(that.testplancomments) : that.testplancomments != null)
            return false;
        if (testplandescription != null ? !testplandescription.equals(that.testplandescription) : that.testplandescription != null)
            return false;
        if (testplanowner != null ? !testplanowner.equals(that.testplanowner) : that.testplanowner != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testplaninstkey != null ? testplaninstkey.hashCode() : 0;
        result = 31 * result + (testplanname != null ? testplanname.hashCode() : 0);
        result = 31 * result + (testplantype != null ? testplantype.hashCode() : 0);
        result = 31 * result + (projectinstkey != null ? projectinstkey.hashCode() : 0);
        result = 31 * result + (projectname != null ? projectname.hashCode() : 0);
        result = 31 * result + (testplanownerinstkey != null ? testplanownerinstkey.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (testplanstatus != null ? testplanstatus.hashCode() : 0);
        result = 31 * result + (testplancomments != null ? testplancomments.hashCode() : 0);
        result = 31 * result + (testplandescription != null ? testplandescription.hashCode() : 0);
        result = 31 * result + (testplanowner != null ? testplanowner.hashCode() : 0);
        return result;
    }
}
