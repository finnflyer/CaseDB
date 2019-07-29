package com.demo.model.issue;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/8.
 */
@Entity
@Table(name = "ctdissuephoto", schema = "", catalog = "casedb")
public class IssuePhotoBean implements Serializable {
    private String instkey;
    private String issueFileName;
    private String issuePath;
    private Date updateTime;
    private String issueInstkey;
    private String creator;
    private String status;

    @Id
    @Column(name = "Instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "IssueFileName")
    public String getIssueFileName() {
        return issueFileName;
    }

    public void setIssueFileName(String issueFileName) {
        this.issueFileName = issueFileName;
    }

    @Basic
    @Column(name = "IssuePath")
    public String getIssuePath() {
        return issuePath;
    }

    public void setIssuePath(String issuePath) {
        this.issuePath = issuePath;
    }

    @Basic
    @Column(name = "UpdateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "IssueInstkey")
    public String getIssueInstkey() {
        return issueInstkey;
    }

    public void setIssueInstkey(String issueInstkey) {
        this.issueInstkey = issueInstkey;
    }

    @Basic
    @Column(name = "Creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssuePhotoBean that = (IssuePhotoBean) o;

        if (instkey != null ? !instkey.equals(that.instkey) : that.instkey != null) return false;
        if (issueFileName != null ? !issueFileName.equals(that.issueFileName) : that.issueFileName != null)
            return false;
        if (issuePath != null ? !issuePath.equals(that.issuePath) : that.issuePath != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (issueInstkey != null ? !issueInstkey.equals(that.issueInstkey) : that.issueInstkey != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instkey != null ? instkey.hashCode() : 0;
        result = 31 * result + (issueFileName != null ? issueFileName.hashCode() : 0);
        result = 31 * result + (issuePath != null ? issuePath.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (issueInstkey != null ? issueInstkey.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
