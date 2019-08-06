package com.demo.model.testcase;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/6.
 */
@Entity
@Table(name = "ctdhistorytestcase", schema = "", catalog = "casedb")
public class TestCaseHistroy {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseHistroy that = (TestCaseHistroy) o;

        if (caseinstkey != null ? !caseinstkey.equals(that.caseinstkey) : that.caseinstkey != null) return false;
        if (casename != null ? !casename.equals(that.casename) : that.casename != null) return false;
        if (casecode != null ? !casecode.equals(that.casecode) : that.casecode != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseinstkey != null ? caseinstkey.hashCode() : 0;
        result = 31 * result + (casename != null ? casename.hashCode() : 0);
        result = 31 * result + (casecode != null ? casecode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }
}
