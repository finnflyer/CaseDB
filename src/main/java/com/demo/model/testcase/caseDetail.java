package com.demo.model.testcase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ctdcasedetail", schema = "casedb", catalog = "")
public class caseDetail implements Serializable {
    private String caseDetailId;
    private String caseId;
    private String caseDetailContent;
    private String createby;
    private Date createdate;
    private String updateby;
    private Date updatedate;

    @Id
    @Column(name = "case_detail_id")
    public String getCaseDetailId() {
        return caseDetailId;
    }

    public void setCaseDetailId(String caseDetailId) {
        this.caseDetailId = caseDetailId;
    }

    @Basic
    @Column(name = "case_id")
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "case_detail_content")
    public String getCaseDetailContent() {
        return caseDetailContent;
    }

    public void setCaseDetailContent(String caseDetailContent) {
        this.caseDetailContent = caseDetailContent;
    }

    @Basic
    @Column(name = "createby")
    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
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
    @Column(name = "updateby")
    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    @Basic
    @Column(name = "updatedate")
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        caseDetail that = (caseDetail) o;

        if (caseDetailId != null ? !caseDetailId.equals(that.caseDetailId) : that.caseDetailId != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (caseDetailContent != null ? !caseDetailContent.equals(that.caseDetailContent) : that.caseDetailContent != null)
            return false;
        if (createby != null ? !createby.equals(that.createby) : that.createby != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (updateby != null ? !updateby.equals(that.updateby) : that.updateby != null) return false;
        if (updatedate != null ? !updatedate.equals(that.updatedate) : that.updatedate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseDetailId != null ? caseDetailId.hashCode() : 0;
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (caseDetailContent != null ? caseDetailContent.hashCode() : 0);
        result = 31 * result + (createby != null ? createby.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (updateby != null ? updateby.hashCode() : 0);
        result = 31 * result + (updatedate != null ? updatedate.hashCode() : 0);
        return result;
    }
}
