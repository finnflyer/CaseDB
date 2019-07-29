package com.demo.action.overtime;

import com.demo.model.common.User;
import com.demo.model.overtime.ApplyOTBean;
import com.demo.model.overtime.OTBean;
import com.demo.model.overtime.OverTimeUser;
import com.demo.service.overtime.ApplyOverTimeService;
import com.demo.service.overtime.OverTimeUserService;
import com.demo.service.overtime.OvertimeService;
import com.demo.util.Generator;
import com.demo.util.QueryResult;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by finnf on 2018/6/23.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class ApplyOverTime extends ActionSupport {
    @Autowired
    public ApplyOverTimeService applyOverTimeService;
    @Autowired
    public OverTimeUserService overTimeUserService;
    @Autowired
    public OvertimeService overtimeService;
    public String OTType;
    public String btnStatus;
    public String applyreason;
    public String StartTime;
    public String EndTime;
    public String StartDate;
    public String EndDate;

    public String getOTType() {
        return OTType;
    }

    public void setOTType(String OTType) {
        this.OTType = OTType;
    }

    public String getBtnStatus() {
        return btnStatus;
    }

    public void setBtnStatus(String btnStatus) {
        this.btnStatus = btnStatus;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getApplyreason() {
        return applyreason;
    }

    public void setApplyreason(String applyreason) {
        this.applyreason = applyreason;
    }


    @Action(value = "recordOverTime",results = {@Result(name = "success",type = "redirect", location = "OverTimeHome")})
    public String ApplyOverTime(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String[]  params = {user.getUsername()};
        OverTimeUser overTimeUser = overTimeUserService.findOverTimeUserByName(user.getUsername());
        QueryResult<OTBean> query = overtimeService.getScrollData(0,5,"userName = ?",params);
        OTBean otBean = new OTBean();
        if(query.getTotalCount()==0){
            otBean.setInstkey(Generator.generatorID());
           // otBean.setName(user.getUsername());
            otBean.setUserName(user.getUsername());
            otBean.setName(user.getUsername());
            otBean.setRemainDaysOffTime(0.0);
            otBean.setPaymentTime(0.0);
            otBean.setTilTime(0.0);
            otBean.setTotalTime(0.0);
            otBean.setActive("active");
            otBean.setRemainTime(0.0);
            otBean.setDepartment("");
            overtimeService.save(otBean);
        }else {
            otBean = overtimeService.getScrollData(0,5,"userName = ?",params).getDatas().get(0);
        }


        ApplyOTBean applyOTBean = new ApplyOTBean();
        applyOTBean.setInstkey(Generator.generatorID());
        applyOTBean.setUserName(overTimeUser.getLoginname());
        applyOTBean.setType(OTType);
        applyOTBean.setActive(true);
        applyOTBean.setDescription(applyreason);

        if(btnStatus.equals("1")){
            //mean is all days apply
            //handle the TimeFormat
            StartDate = StartDate.replace("/","-")+" 00:00:00";
            EndDate = EndDate.replace("/","-")+" 24:00:00";
            applyOTBean.setStartTime(Timestamp.valueOf(StartDate));
            applyOTBean.setEndTime(Timestamp.valueOf(EndDate));
            double days = (double)(Timestamp.valueOf(EndDate).getTime()-Timestamp.valueOf(StartDate).getTime())/(24*3600*1000);
            applyOTBean.setTotalHour(days*8);

        }else{
            //一天中的时间
            String TempDate = StartDate;
            StartDate = TempDate.replace("/","-")+" "+StartTime+":00";
            EndDate   = TempDate.replace("/","-")+" "+EndTime+":00";
            applyOTBean.setStartTime(Timestamp.valueOf(StartDate));
            applyOTBean.setEndTime(Timestamp.valueOf(EndDate));
            Double Hour = (double)(Timestamp.valueOf(EndDate).getTime()-Timestamp.valueOf(StartDate).getTime())/(3600*1000);
            applyOTBean.setTotalHour(Hour);

        }


        applyOTBean.setInputtime(new Date());
        applyOverTimeService.save(applyOTBean);


        if(OTType.equals("TIL")){
            Double currentRemainDaysOffTime = otBean.getRemainDaysOffTime();
            otBean.setRemainDaysOffTime(currentRemainDaysOffTime+applyOTBean.getTotalHour());
        }else {
            Double currentPaymentTime = otBean.getPaymentTime();
            otBean.setRemainDaysOffTime((currentPaymentTime+applyOTBean.getTotalHour()));
        }

        overtimeService.update(otBean);

        return SUCCESS;

    }
}
