package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdsupportos", schema = "", catalog = "casedb")
public class TestCaseSupportOs {
    private int osinstkey;
    private String osdes;

    @Id
    @Column(name = "OSINSTKEY")
    public int getOsinstkey() {
        return osinstkey;
    }

    public void setOsinstkey(int osinstkey) {
        this.osinstkey = osinstkey;
    }

    @Basic
    @Column(name = "OSDES")
    public String getOsdes() {
        return osdes;
    }

    public void setOsdes(String osdes) {
        this.osdes = osdes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCaseSupportOs that = (TestCaseSupportOs) o;

        if (osinstkey != that.osinstkey) return false;
        if (osdes != null ? !osdes.equals(that.osdes) : that.osdes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = osinstkey;
        result = 31 * result + (osdes != null ? osdes.hashCode() : 0);
        return result;
    }
}
