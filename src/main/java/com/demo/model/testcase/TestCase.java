package com.demo.model.testcase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdtestcase", schema = "", catalog = "casedb")
public class TestCase implements Serializable {
    private static final long serialVersionUID = 7080181733477245904L;
    private String caseinstkey;
    private String casename;
    private String casecode;
    private String status;
    private String owner;
    private String version;
    private Date date;
    private String creator;

    @Id
    @Column(name = "CASEINSTKEY")
    public String getCaseinstkey() {
        return caseinstkey;
    }

    public void setCaseinstkey(String caseinstkey) {
        this.caseinstkey = caseinstkey;
    }

    @Basic
    @Column(name = "CASENAME")
    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    @Basic
    @Column(name = "CASECODE")
    public String getCasecode() {
        return casecode;
    }

    public void setCasecode(String casecode) {
        this.casecode = casecode;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "OWNER")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


}
