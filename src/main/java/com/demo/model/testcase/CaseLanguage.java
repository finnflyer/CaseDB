package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdcaselangmap", schema = "", catalog = "casedb")
public class CaseLanguage {
    private int languagekey;
    private String caseinstkey;
    private int osdes;
    private int localset;
    private String lanvalue;

    @Transient
    public String getLanvalue() {
        return lanvalue;
    }

    public void setLanvalue(String lanvalue) {
        this.lanvalue = lanvalue;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LANGUAGEKEY")
    public int getLanguagekey() {
        return languagekey;
    }

    public void setLanguagekey(int languagekey) {
        this.languagekey = languagekey;
    }

    @Basic
    @Column(name = "CASEINSTKEY")
    public String getCaseinstkey() {
        return caseinstkey;
    }

    public void setCaseinstkey(String caseinstkey) {
        this.caseinstkey = caseinstkey;
    }

    @Basic
    @Column(name = "OSDES")
    public int getOsdes() {
        return osdes;
    }

    public void setOsdes(int osdes) {
        this.osdes = osdes;
    }

    @Basic
    @Column(name = "LOCALSET")
    public int getLocalset() {
        return localset;
    }

    public void setLocalset(int localset) {
        this.localset = localset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaseLanguage that = (CaseLanguage) o;

        if (languagekey != that.languagekey) return false;
        if (osdes != that.osdes) return false;
        if (localset != that.localset) return false;
        if (caseinstkey != null ? !caseinstkey.equals(that.caseinstkey) : that.caseinstkey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = languagekey;
        result = 31 * result + (caseinstkey != null ? caseinstkey.hashCode() : 0);
        result = 31 * result + osdes;
        result = 31 * result + localset;
        return result;
    }
}
