package com.demo.model.overtime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by finnf on 2018/6/19.
 */
@Entity
@Table(name = "ctdot", schema = "", catalog = "casedb")
public class OTBean implements Serializable {
    private String instkey;
    private String userName;
    private String name;
    private String department;
    private Double remainTime; //剩余年假
    private Double remainDaysOffTime;//剩余调休
    private Double totalTime;
    private Double tilTime; //Total 调休假
    private Double paymentTime; //total payment
    private String active;

    @Id
    @Column(name = "Instkey")
    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "RemainTime")
    public Double getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Double remainTime) {
        this.remainTime = remainTime;
    }

    @Basic
    @Column(name = "RemainDaysOffTime")
    public Double getRemainDaysOffTime() {
        return remainDaysOffTime;
    }

    public void setRemainDaysOffTime(Double remainDaysOffTime) {
        this.remainDaysOffTime = remainDaysOffTime;
    }

    @Basic
    @Column(name = "TotalTime")
    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @Column(name = "TILTime")
    public Double getTilTime() {
        return tilTime;
    }

    public void setTilTime(Double tilTime) {
        this.tilTime = tilTime;
    }

    @Basic
    @Column(name = "PaymentTime")
    public Double getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Double paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Basic
    @Column(name="Active")
    public String getActive(){return active;}

    public void setActive(String active) {
        this.active = active;
    }
}
