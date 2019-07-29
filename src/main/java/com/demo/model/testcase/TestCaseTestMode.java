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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseTestMode that = (TestCaseTestMode) o;

        if (testmodeid != that.testmodeid) return false;
        if (testmodecato != null ? !testmodecato.equals(that.testmodecato) : that.testmodecato != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testmodeid;
        result = 31 * result + (testmodecato != null ? testmodecato.hashCode() : 0);
        return result;
    }
}
