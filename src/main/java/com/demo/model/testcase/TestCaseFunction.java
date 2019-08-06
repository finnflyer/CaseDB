package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcasefunction", schema = "", catalog = "casedb")
public class TestCaseFunction {
    private int functionid;
    private String functioncato;

    @Id
    @Column(name = "FUNCTIONID")
    public int getFunctionid() {
        return functionid;
    }

    public void setFunctionid(int functionid) {
        this.functionid = functionid;
    }

    @Basic
    @Column(name = "FUNCTIONCATO")
    public String getFunctioncato() {
        return functioncato;
    }

    public void setFunctioncato(String functioncato) {
        this.functioncato = functioncato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseFunction that = (TestCaseFunction) o;

        if (functionid != that.functionid) return false;
        if (functioncato != null ? !functioncato.equals(that.functioncato) : that.functioncato != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = functionid;
        result = 31 * result + (functioncato != null ? functioncato.hashCode() : 0);
        return result;
    }
}
