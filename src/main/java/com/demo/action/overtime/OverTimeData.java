package com.demo.action.overtime;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.overtime.ApplyOTBean;
import com.demo.model.overtime.OTBean;
import com.demo.service.overtime.OverTimeUserService;
import com.demo.service.overtime.ApplyOverTimeService;
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
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by finnf on 2018/7/2.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class OverTimeData extends ActionSupport {

    @Autowired
    public OvertimeService overtimeService;
    @Autowired
    public ApplyOverTimeService applyOverTimeService;
    @Autowired
    public OverTimeUserService overTimeUserService;
    private String dataMap;
    private JSONObject dataOb;
    public String strJson;
    public String Dep;
    public String rangeTime;


    @Action(value = "OverTimeViewJson", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String OverTimeJson() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String limit1 = request.getParameter("limit");
        int limit = Integer.parseInt(limit1);
        //获取已经显示的个数，然后除以一页的个数就是页数
        String offset1 = request.getParameter("offset");
        int offset = Integer.parseInt(offset1) / limit;
        String where = "upper(department) like ?0  and active != ?1";
        String[] param = {"%%", "False"};
        if (Dep != null && !"All".equals(Dep))
            if (Dep.length() >= 4)
                param[0] = "PA__";
            else
                param[0] = "PA_";

        QueryResult<OTBean> queryResult = overtimeService.getScrollData(offset * limit, limit, where, param);
        JSONArray arr = new JSONArray();
        for (OTBean temp : queryResult.getDatas()) {
            JSONObject ob = new JSONObject();
            ob.put("Id", temp.getInstkey());
            ob.put("Department", temp.getDepartment());
            ob.put("LoginName", temp.getUserName());
            ob.put("Payment", temp.getPaymentTime());
            ob.put("TIL", temp.getTilTime());
            ob.put("RemainTime", temp.getRemainTime());
            ob.put("Daysoff",temp.getRemainDaysOffTime());
            ob.put("Total", temp.getTotalTime());
            ob.put("Name", temp.getName());
            arr.add(ob);
        }
        //获取数据库中所有的数据
        JSONObject ob = new JSONObject();
        ob.put("total", queryResult.getTotalCount());
        ob.put("rows", arr);
        dataOb = ob;
        dataMap = ob.toJSONString();
        return SUCCESS;
    }

    @Action(value="OverTimeExportView",results =  {@Result(name="success",type ="json",params = {"root","dataOb"})})
    public String OverTimeExportJson(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
        HttpServletRequest request = ServletActionContext.getRequest();
        String limit1 = request.getParameter("limit");
        int limit = Integer.parseInt(limit1);
        //获取已经显示的个数，然后除以一页的个数就是页数
        String offset1 = request.getParameter("offset");
        int offset = Integer.parseInt(offset1) / limit;

        String hql = "from ApplyOTBean as i where 1=1 and i.type ='Payment' ";

        if(rangeTime!=null &&  !"".equals(rangeTime)){
            String[] timeSplit = rangeTime.split("-");
            String startTime = timeSplit[0].trim();
            String endTime = timeSplit[1].trim();
            hql += " and i.startTime >='"+ startTime +"' ";
            hql += "and i.startTime <='"+endTime+"' ";
        }

        List<ApplyOTBean> resultList = applyOverTimeService.findApplyOTBean(hql);

        JSONArray arr = new JSONArray();
        for(ApplyOTBean temp:resultList){
            JSONObject ob = new JSONObject();
            String userRealName = overTimeUserService.findOverTimeUserByName(temp.getUserName()).getUsername();
            ob.put("id",temp.getInstkey());
            ob.put("Employid",overTimeUserService.findOverTimeUserByName(temp.getUserName()).getEmployid());
            ob.put("Name",userRealName);
            ob.put("Date",sdf.format(temp.getStartTime().getTime()));
            ob.put("StartTime",tdf.format(temp.getStartTime().getTime()));
            ob.put("EndTime",tdf.format(temp.getEndTime().getTime()));
            ob.put("Type","Payment");
            ob.put("Description",temp.getDescription());
            arr.add(ob);
        }
        //获取数据库中所有的数据
        JSONObject ob = new JSONObject();
        ob.put("total", resultList.size());
        ob.put("rows", arr);
        dataOb = ob;
        dataMap = ob.toJSONString();
        return SUCCESS;
    }
    @Action(value = "EditOverTime", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String EditOverTime() {
        System.out.println(strJson);
        JSONObject ob = JSONObject.parseObject(strJson);
        OTBean otBean = overtimeService.findById(ob.getString("Id"));
        otBean.setTilTime(Double.parseDouble(ob.getString("TIL")));
        otBean.setName(ob.getString("Name"));
        otBean.setRemainDaysOffTime(Double.parseDouble(ob.getString("Daysoff")));
        otBean.setPaymentTime(Double.parseDouble(ob.getString("Payment")));
        otBean.setDepartment(ob.getString("Department"));
        otBean.setUserName(ob.getString("LoginName"));
        otBean.setRemainTime(Double.parseDouble(ob.getString("RemainTime")));
        double total = Double.parseDouble(ob.getString("Daysoff"))+Double.parseDouble(ob.getString("RemainTime"));
        otBean.setTotalTime(total);
        overtimeService.update(otBean);
        return SUCCESS;
    }

    @Action(value = "DeleteOverTime", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String DeleteOverTime() {
        JSONObject ob = JSONObject.parseObject(strJson);
        OTBean otBean = overtimeService.findById(ob.getString("id"));
        overtimeService.update(otBean);
        return SUCCESS;
    }

    private String Name;
    private String newDep;
    private String RemainTime;
    private String RemainDaysoffTime;


    @Action(value = "AddOTBean", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String AddOTBean() {
        OTBean ob = new OTBean();
        ob.setInstkey(Generator.generatorID());
        ob.setDepartment(newDep);
        ob.setRemainTime(Double.parseDouble(RemainTime));
        ob.setUserName(Name);
        ob.setName(Name);
        ob.setRemainDaysOffTime(Double.parseDouble(RemainDaysoffTime));
        double total = Double.parseDouble(RemainDaysoffTime) + Double.parseDouble(RemainTime);
        ob.setTotalTime(total);
        ob.setActive("True");
        overtimeService.save(ob);
        dataOb.put("isSuccess", "1");
        return SUCCESS;
    }

    public String getRangeTime() {
        return rangeTime;
    }

    public void setRangeTime(String rangeTime) {
        this.rangeTime = rangeTime;
    }

    public String getDep() {
        return Dep;
    }

    public void setDep(String dep) {
        Dep = dep;
    }

    public String getStrJson() {
        return strJson;
    }

    public void setStrJson(String strJson) {
        this.strJson = strJson;
    }

    public JSONObject getDataOb() {
        return dataOb;
    }

    public void setDataOb(JSONObject dataOb) {
        this.dataOb = dataOb;
    }

    public String getDataMap() {
        return dataMap;
    }

    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNewDep() {
        return newDep;
    }

    public void setNewDep(String newDep) {
        this.newDep = newDep;
    }


    public String getRemainTime() {
        return RemainTime;
    }

    public void setRemainTime(String remainTime) {
        RemainTime = remainTime;
    }

    public String getRemainDaysoffTime() {
        return RemainDaysoffTime;
    }

    public void setRemainDaysoffTime(String remainDaysoffTime) {
        RemainDaysoffTime = remainDaysoffTime;
    }
}
