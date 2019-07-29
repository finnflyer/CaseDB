package com.demo.action.overtime;

import com.demo.model.common.User;
import com.demo.model.overtime.ApplyVacationBean;
import com.demo.service.overtime.ApplyVacationService;
import com.demo.util.Generator;
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
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by finnf on 2018/6/23.
 */

@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class ApplyVacation  extends ActionSupport{
    @Autowired
    public ApplyVacationService applyVacationService;
    public String VacationType;
    public String btnStatus;
    public String applyreason;
    public String StartTime;
    public String EndTime;
    public String StartDate;
    public String EndDate;



    @Action(value = "applyVacation",results = {@Result(name = "success",location = "/jsp/OverTime/OverTimeHome.jsp")})
    public  String applyVacation(){

        ApplyVacationBean applyVacationBean = new ApplyVacationBean();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        applyVacationBean.setInstkey(Generator.generatorID());
        applyVacationBean.setUsername(user.getUsername());
        applyVacationBean.setActive(true);
        applyVacationBean.setType(VacationType);
        applyVacationBean.setDescription(applyreason);
        if(btnStatus.equals("1")){
            //mean is all days apply
            //handle the TimeFormat
            StartDate = StartDate.replace("/","-")+" 00:00:00";
            EndDate = EndDate.replace("/","-")+" 24:00:00";
            applyVacationBean.setStarttime(Timestamp.valueOf(StartDate));
            applyVacationBean.setEndtime(Timestamp.valueOf(EndDate));
            double days = (double)(Timestamp.valueOf(EndDate).getTime()-Timestamp.valueOf(StartDate).getTime())/(24*3600*1000);
            applyVacationBean.setTotaltime(days*8);

        }else{
            //一天中的时间
            String TempDate = StartDate;
            StartDate = TempDate.replace("/","-")+" "+StartTime+":00";
            EndDate   = TempDate.replace("/","-")+" "+EndTime+":00";
            applyVacationBean.setStarttime(Timestamp.valueOf(StartDate));
            applyVacationBean.setEndtime(Timestamp.valueOf(EndDate));
            Double Hour = (double)(Timestamp.valueOf(EndDate).getTime()-Timestamp.valueOf(StartDate).getTime())/(3600*1000);
            applyVacationBean.setTotaltime(Hour);

        }
        applyVacationBean.setInputime(new Date());

        applyVacationService.save(applyVacationBean);

        //



        return SUCCESS;
    }


    public String getVacationType() {
        return VacationType;
    }

    public void setVacationType(String vacationType) {
        VacationType = vacationType;
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
}
