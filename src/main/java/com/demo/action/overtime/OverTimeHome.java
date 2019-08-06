package com.demo.action.overtime;


import com.demo.model.formbean.PageBean;
import com.demo.model.overtime.OTBean;
import com.demo.service.overtime.OvertimeService;
import com.demo.util.QueryResult;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Created by finnf on 2018/6/19.
 */

@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class OverTimeHome extends ActionSupport {

    @Autowired
    public OvertimeService overtimeService;
    private PageBean pageBean;
    public String department;

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    @Action(value = "OverTimeHome", results = {@Result(name = "success", location = "/jsp/OverTime/OverTimeHome.jsp")})
    public String PressOverTimeHome() {

        QueryResult<OTBean> queryResult = overtimeService.getScrollData();
        pageBean = pageBeanInital(queryResult);
        return SUCCESS;
    }

    @Action(value="SelectedOverTime",results ={@Result(name = "success", type = "redirect", location = "searchPage")} )
    public String SelecedOverTime(){
        String where ="department != ?";
        String[] param = {department};
        QueryResult<OTBean>  queryResult = overtimeService.getScrollData(0,150,where,param);
        pageBean = pageBeanInital(queryResult);

        return SUCCESS;
    }


    public PageBean pageBeanInital(QueryResult<OTBean> resultQurey ){
        int pageNumber = 1;
        int pageSize = 50;
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));
        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;
    }

    public List<OTBean> overTimeContent;

    public List<OTBean> getOverTimeContent() {
        return overTimeContent;
    }

    public void setOverTimeContent(List<OTBean> overTimeContent) {
        this.overTimeContent = overTimeContent;
    }

    @Action(value="PressEdit",results = {@Result(name="success",location = "/jsp/OverTime/EditOverTime.jsp")})
    public String EditOverTime(){

        return SUCCESS;
    }


    @Action(value = "ExportOT", results = {@Result(name = "success", location = "/jsp/OverTime/OverTimeExport.jsp")})
    public String ExportOT() {

        return SUCCESS;
    }

    @Action(value = "preOverTime", results = {@Result(name = "success", location = "/jsp/OverTime/OverTimeHome.jsp")})
       public String preOverTime() {

        return SUCCESS;
    }
    @Action(value = "OverTimePage", results = {@Result(name = "success", location = "/jsp/OverTime/ApplyOT.jsp")})
    public String ApplyOverTime() {

        return SUCCESS;
    }
    @Action(value="EditOTUser",results = {@Result(name="success",location="/jsp/OverTime/OverTimeUser.jsp")})
    public String EditOTUser(){
        return SUCCESS;
    }

    @Action(value = "VacationPage",results = {@Result(name = "success",location = "/jsp/OverTime/ApplyVacation.jsp")})
    public String ApplyVacation(){
        return SUCCESS;
    }


}
