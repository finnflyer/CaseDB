package com.demo.model.testcase;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/2.
 */
@Entity
@Table(name = "ctdlanguage", schema = "", catalog = "casedb")
public class LanguagesBean {
    private int languageinstkey;
    private String lanvalue;

    @Id
    @Column(name = "LANGUAGEINSTKEY")
    public int getLanguageinstkey() {
        return languageinstkey;
    }

    public void setLanguageinstkey(int languageinstkey) {
        this.languageinstkey = languageinstkey;
    }

    @Basic
    @Column(name = "LANVALUE")
    public String getLanvalue() {
        return lanvalue;
    }

    public void setLanvalue(String lanvalue) {
        this.lanvalue = lanvalue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguagesBean that = (LanguagesBean) o;

        if (languageinstkey != that.languageinstkey) return false;
        if (lanvalue != null ? !lanvalue.equals(that.lanvalue) : that.lanvalue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = languageinstkey;
        result = 31 * result + (lanvalue != null ? lanvalue.hashCode() : 0);
        return result;
    }
}
