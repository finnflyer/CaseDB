package com.demo.action.abbreviation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.abbreviation.AbbreviationBean;
import com.demo.model.common.User;
import com.demo.model.overtime.OTBean;
import com.demo.service.abbreviation.AbbreviationService;
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
import java.util.LinkedHashMap;

/**
 * Created by finnf on 2018/8/17.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("json-default")
public class AbbreviationData extends ActionSupport {
    @Autowired
    public AbbreviationService abbreviationService;

    private String dataMap;
    private JSONObject dataOb;
    private String strJson;


    @Action(value = "AbbreviationViewJson", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String AbbreviationJson(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String limit1 = request.getParameter("limit");
        int limit = Integer.parseInt(limit1);
        String offset1 = request.getParameter("offset");
        int offset = Integer.parseInt(offset1) / limit;
        String searchString  = request.getParameter("search");
       // byte[] b=searchString.getBytes("ISO-8859-1");
        //searchString = new String(b,"utf-8");
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("abbreviation ", "desc");
        String where = "status != ?0 and abbreviation like ?1";
        String[] param = {"Del","%%"};
        if(searchString != null && !"".equals(searchString))
            param[1] = "%"+searchString+"%";
        QueryResult<AbbreviationBean> queryResult = abbreviationService.getScrollData(offset * limit, limit, where, param,orderby);
        JSONArray arr = new JSONArray();
        for (AbbreviationBean temp : queryResult.getDatas()) {
            JSONObject ob = new JSONObject();
            ob.put("Id", temp.getInstkey());
            ob.put("Abbreviation", temp.getAbbreviation());
            ob.put("Description", temp.getDescription());
            ob.put("Comments", temp.getComments());
            ob.put("Uploador", temp.getUploador());
            arr.add(ob);
        }
        JSONObject ob = new JSONObject();
        ob.put("total", queryResult.getTotalCount());
        ob.put("rows", arr);
        dataOb = ob;
        dataMap = ob.toJSONString();
        return SUCCESS;
    }

    @Action(value = "DeleteAbbreviation", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String DeleteAbbreviation() {
        JSONObject ob = JSONObject.parseObject(strJson);
        AbbreviationBean abbreviationBean = abbreviationService.findById(ob.getString("Id"));
        abbreviationBean.setStatus("del");
        abbreviationService.update(abbreviationBean);
        return SUCCESS;
    }

    @Action(value = "EditAbbreviation", results = {@Result(name = "success", type = "json", params = {"root", "dataOb"})})
    public String EditAbbreviation() {

        JSONObject ob = JSONObject.parseObject(strJson);
        AbbreviationBean abbreviationBean = abbreviationService.findById(ob.getString("Id"));
        abbreviationBean.setUploadTime(new Date());
        abbreviationBean.setDescription(ob.getString("Description"));
        abbreviationBean.setAbbreviation(ob.getString("Abbreviation"));
        abbreviationBean.setComments(ob.getString("Comments"));
        abbreviationService.update(abbreviationBean);
        return SUCCESS;
    }


    private String abbreviation;
    private String description;
    private String abbComment;

    @Action(value = "CreateAbbreviation",results = {@Result(name = "success",type = "json",params = {"root","dataOb"})})
    public String CreateAbb(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        AbbreviationBean abbreviationBean = new AbbreviationBean();
        abbreviationBean.setInstkey(Generator.generatorID());
        abbreviationBean.setAbbreviation(abbreviation);
        abbreviationBean.setComments(abbComment);
        abbreviationBean.setDescription(description);
        abbreviationBean.setUploador(user.getUsername());
        abbreviationBean.setUploadTime(new Date());
        abbreviationBean.setStatus("active");
        abbreviationService.save(abbreviationBean);

        return SUCCESS;
    }

    public String getStrJson() {
        return strJson;
    }

    public void setStrJson(String strJson) {
        this.strJson = strJson;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbComment() {
        return abbComment;
    }

    public void setAbbComment(String abbComment) {
        this.abbComment = abbComment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public JSONObject getDataOb() {
        return dataOb;
    }

    public void setDataOb(JSONObject dataOb) {
        this.dataOb = dataOb;
    }

    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }

    public String getDataMap() {
        return dataMap;
    }
}
