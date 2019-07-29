package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcasebrand", schema = "", catalog = "casedb")
public class TestCaseBrand {
    private int brandid;
    private String brandcato;

    @Id
    @Column(name = "BRANDID")
    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    @Basic
    @Column(name = "BRANDCATO")
    public String getBrandcato() {
        return brandcato;
    }

    public void setBrandcato(String brandcato) {
        this.brandcato = brandcato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseBrand that = (TestCaseBrand) o;

        if (brandid != that.brandid) return false;
        if (brandcato != null ? !brandcato.equals(that.brandcato) : that.brandcato != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brandid;
        result = 31 * result + (brandcato != null ? brandcato.hashCode() : 0);
        return result;
    }
}
