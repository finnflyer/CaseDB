package com.demo.model.overtime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by finnf on 2018/6/19.
 */
@Entity
@Table(name = "ctdvaction", schema = "", catalog = "casedb")
public class ApplyVacationBean implements Serializable {
    private String instkey;
    private String username;
    private Timestamp starttime;
    private Timestamp endtime;
    private Double totaltime;
    private Date inputime;
    private Boolean active;
    private String type;
    private String Description;

    @Id
    @Column(name = "instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
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
    @Column(name = "starttime")
    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "endtime")
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    @Basic
    @Column(name = "totaltime")
    public Double getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(Double totaltime) {
        this.totaltime = totaltime;
    }

    @Basic
    @Column(name = "inputime")
    public Date getInputime() {
        return inputime;
    }

    public void setInputime(Date inputime) {
        this.inputime = inputime;
    }

    @Basic
    @Column(name = "Active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name="type")
    public String getType(){return type;}

    public void setType(String type){ this.type = type;}
    @Basic
    @Column(name="Description")
    public String getDescription(){return Description;}
    public void setDescription(String description){this.Description = description;}
}

