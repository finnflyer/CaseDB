package com.demo.action.testcase;

import com.demo.model.formbean.PageBean;
import com.demo.model.testcase.SearchCaseBean;
import com.demo.service.testcase.SearchCaseService;
import com.demo.util.EncryptUtil;
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
import java.util.LinkedHashMap;

/**
 * Created by Admin on 2016/9/5.
 */
@Namespace("/phase4")
@ParentPackage("struts-default")
@Scope("property")
public class SearchTestCaseForPage extends ActionSupport {
    @Autowired
    private SearchCaseService searchCaseService;
    private String caseName;
    private String caseOwner;
    private PageBean pageBean;
    private int pageNumber = 1;
    int pageSize = 30;
    private int searchflag = 1;
    public String caseId;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public int getSearchflag() {
        return searchflag;
    }

    public void setSearchflag(int searchflag) {
        this.searchflag = searchflag;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseOwner() {
        return caseOwner;
    }

    public void setCaseOwner(String caseOwner) {
        this.caseOwner = caseOwner;
    }

    @Action(value = "SearchCaseForPage", results = {@Result(name = "success", location = "/jsp/TestCases/TCHome.jsp")})
    public String SearchCaseForPage() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("casecode ", "asc");
        String where = "upper(casename) like ?0 and upper(owner) like ?1 and upper(casecode) like ?2 and status ='draft'";
        String[] param = {"%%", "%%","%%"};
        if (caseName != null && !"".equals(caseName))
            param[0] = "%" + caseName.toUpperCase() + "%";
        if (null != caseOwner && (!"".equals(caseOwner)))
            param[1] = "%" + caseOwner.toUpperCase() + "%";
        if (null != caseId && (!"".equals(caseId)))
            param[2] = "%" + caseId.toUpperCase() + "%";
        if (searchflag == 1)
            session.setAttribute("ohql", param);
        else
            param = (String[]) session.getAttribute("ohql");
        QueryResult<SearchCaseBean> resultList = searchCaseService.getScrollData(0 + pageSize * (pageNumber-1), 30, where, param, orderby);
        for (SearchCaseBean temp : resultList.getDatas()){
            SearchBeanCatoSetting(temp);
            EncryptKey(temp);
        }

        pageBean = pageBenInital(resultList);
        return SUCCESS;
    }

    public PageBean pageBenInital(QueryResult<SearchCaseBean> resultQurey) {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));

        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;

    }

    public void SearchBeanCatoSetting(SearchCaseBean temp) {
        switch (temp.getTestmodeid()){
            case 1:
                temp.setModeCato("Normal");
                break;
            case 2:temp.setModeCato("S Mode");
                break;
            case 12:
                temp.setModeCato("Normal/SMode");
                break;
        }
        switch (temp.getBrandid()) {
            case 1:
                temp.setBrandCato("ThinkPad");
                break;
            case 12:
                temp.setBrandCato("ThinkPad/ThinkStation");
                break;
            case 2:
                temp.setBrandCato("ThinkStation");
                break;
        }
        switch (Integer.parseInt(temp.getOsid())) {
            case 1:
                temp.setOsCato("xp");
                break;
            case 12:
                temp.setOsCato("xp/win7");
                break;
            case 123:
                temp.setOsCato("xp/win7/win8.x");
                break;
            case 1234:
                temp.setOsCato("xp/win7/win8.x/win10");
                break;
            case 2:
                temp.setOsCato("win7");
                break;
            case 23:
                temp.setOsCato("win7/win8.x");
                break;
            case 234:
                temp.setOsCato("win7/win8.x/win10");
                break;
            case 3:
                temp.setOsCato("win8.x");
                break;
            case 34:
                temp.setOsCato("win8.x/win10");
                break;
            case 4:
                temp.setOsCato("win10");
                break;
            case 24:
                temp.setOsCato("win7/win10");
                break;
            default:
                break;
        }
    }

    public void EncryptKey(SearchCaseBean temp) throws Exception{
         temp.setCaseinstkey(EncryptUtil.enString(temp.getCaseinstkey()));
    }
}
