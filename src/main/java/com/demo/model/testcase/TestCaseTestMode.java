package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdtestmode", schema = "", catalog = "casedb")
public class TestCaseTestMode {
    private int testmodeid;
    private String testmodecato;

    @Id
    @Column(name = "TESTMODEID")
    public int getTestmodeid() {
        return testmodeid;
    }

    public void setTestmodeid(int testmodeid) {
        this.testmodeid = testmodeid;
    }

    @Basic
    @Column(name = "TESTMODECATO")
    public String getTestmodecato() {
        return testmodecato;
    }

    public void setTestmodecato(String testmodecato) {
        this.testmodecato = testmodecato;
    }

}
