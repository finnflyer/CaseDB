package com.demo.model.overtime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by finnf on 2018/8/23.
 */
@Entity
@Table(name = "ctdotuser", schema = "", catalog = "casedb")
public class OverTimeUser implements Serializable {
    private String instkey;
    private String loginname;
    private String username;
    private String employid;

    @Id
    @Column(name = "Instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "loginname")
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "employid")
    public String getEmployid() {
        return employid;
    }

    public void setEmployid(String employid) {
        this.employid = employid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OverTimeUser that = (OverTimeUser) o;

        if (instkey != null ? !instkey.equals(that.instkey) : that.instkey != null) return false;
        if (loginname != null ? !loginname.equals(that.loginname) : that.loginname != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (employid != null ? !employid.equals(that.employid) : that.employid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instkey != null ? instkey.hashCode() : 0;
        result = 31 * result + (loginname != null ? loginname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (employid != null ? employid.hashCode() : 0);
        return result;
    }
}
