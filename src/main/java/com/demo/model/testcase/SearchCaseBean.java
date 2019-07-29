package com.demo.model.testcase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcasesearch", schema = "", catalog = "casedb")
public class SearchCaseBean implements Serializable {
    private static final long serialVersionUID = 7080181733477245904L;
    private String caseinstkey;
    private String casename;
    private String casecode;
    private String owner;
    private String version;
    private int brandid;
    private int funcid;
    private String osid;
    private Integer testmodeid;
    private String functioncato;
    private String brandCato;
    private String osCato;
    private Integer tpOrder;

    public void setTpOrder(Integer tpOrder) {
        this.tpOrder = tpOrder;
    }

    @Transient
    public Integer getTpOrder() {
        return tpOrder;
    }

    @Transient
    public String getOsCato() {
        return osCato;
    }

    public void setOsCato(String osCato) {
        this.osCato = osCato;
    }

    @Transient
    public String getBrandCato() {
        return brandCato;
    }

    public void setBrandCato(String brandCato) {
        this.brandCato = brandCato;
    }

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
    @Column(name = "OSID")
    public String getOsid() {
        return osid;
    }

    public void setOsid(String osid) {
        this.osid = osid;
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
    @Column(name = "FUNCTIONCATO")
    public String getFunctioncato() {
        return functioncato;
    }

    public void setFunctioncato(String functioncato) {
        this.functioncato = functioncato;
    }


}
