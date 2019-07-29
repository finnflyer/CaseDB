package com.demo.model.testplan;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Admin on 2016/9/5.
 */
@Entity
@Table(name = "ctdtestplancontent", schema = "", catalog = "casedb")
public class TestPlanContent implements Serializable{
    private String testplancontentinstkey;
    private String testcaseinstkey;
    private Integer tporder;
    private String testplaninstkey;
    private Date createtime;

    @Id
    @Column(name = "TESTPLANCONTENTINSTKEY")
    public String getTestplancontentinstkey() {
        return testplancontentinstkey;
    }

    public void setTestplancontentinstkey(String testplancontentinstkey) {
        this.testplancontentinstkey = testplancontentinstkey;
    }

    @Basic
    @Column(name = "TESTCASEINSTKEY")
    public String getTestcaseinstkey() {
        return testcaseinstkey;
    }

    public void setTestcaseinstkey(String testcaseinstkey) {
        this.testcaseinstkey = testcaseinstkey;
    }

    @Basic
    @Column(name = "TPORDER")
    public Integer getTporder() {
        return tporder;
    }

    public void setTporder(Integer tporder) {
        this.tporder = tporder;
    }

    @Basic
    @Column(name = "TESTPLANINSTKEY")
    public String getTestplaninstkey() {
        return testplaninstkey;
    }

    public void setTestplaninstkey(String testplaninstkey) {
        this.testplaninstkey = testplaninstkey;
    }

    @Basic
    @Column(name = "CREATETIME")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


}
