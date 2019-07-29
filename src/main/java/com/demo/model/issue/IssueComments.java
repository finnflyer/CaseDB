package com.demo.model.issue;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/8.
 */
@Entity
@Table(name = "ctdissuecomments", schema = "", catalog = "casedb")
public class IssueComments implements Serializable{
    private String instkey;
    private String issueInstkey;
    private String comments;
    private String createBy;
    private Date createDate;
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
    @Column(name = "IssueInstkey")
    public String getIssueInstkey() {
        return issueInstkey;
    }

    public void setIssueInstkey(String issueInstkey) {
        this.issueInstkey = issueInstkey;
    }

    @Basic
    @Column(name = "Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "CreateBy")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

        IssueComments that = (IssueComments) o;

        if (instkey != null ? !instkey.equals(that.instkey) : that.instkey != null) return false;
        if (issueInstkey != null ? !issueInstkey.equals(that.issueInstkey) : that.issueInstkey != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instkey != null ? instkey.hashCode() : 0;
        result = 31 * result + (issueInstkey != null ? issueInstkey.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
