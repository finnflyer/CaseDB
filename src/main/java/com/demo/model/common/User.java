package com.demo.model.common;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by admin on 2016/8/31.
 */
@Entity
@Table(name = "ctduser", schema = "", catalog = "casedb")
public class User implements Serializable{
    private static final long serialVersionUID = 7080181733477245904L;
    private String instkey;
    private String username;
    private String mailbox;
    private String role;
    private Integer employeeid;
    private String description;
    private String password;
    private Integer live;
    private String activecode;
    private String site;

    @Id
    @Column(name = "INSTKEY", nullable = false, insertable = true, updatable = true, length = 20)
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }


    @Basic
    @Column(name = "USERNAME", nullable = true, insertable = true, updatable = true, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "MAILBOX", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    @Basic
    @Column(name = "ROLE", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "EMPLOYEEID", nullable = true, insertable = true, updatable = true)
    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, insertable = true, updatable = true, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "LIVE", nullable = true, insertable = true, updatable = true)
    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    @Basic
    @Column(name = "ACTIVECODE", nullable = true, insertable = true, updatable = true, length = 32)
    public String getActivecode() {
        return activecode;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }



    @Basic
    @Column(name= "SITE",nullable = true, insertable = true,updatable = true, length = 255)
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (instkey != null ? !instkey.equals(user.instkey) : user.instkey != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (mailbox != null ? !mailbox.equals(user.mailbox) : user.mailbox != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (employeeid != null ? !employeeid.equals(user.employeeid) : user.employeeid != null) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (live != null ? !live.equals(user.live) : user.live != null) return false;
        if (activecode != null ? !activecode.equals(user.activecode) : user.activecode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instkey != null ? instkey.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mailbox != null ? mailbox.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (employeeid != null ? employeeid.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (live != null ? live.hashCode() : 0);
        result = 31 * result + (activecode != null ? activecode.hashCode() : 0);
        return result;
    }
}
