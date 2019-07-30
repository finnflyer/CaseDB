package com.demo.action.testplan;

import com.demo.model.formbean.PageBean;
import com.demo.model.testcase.SearchCaseBean;
import com.demo.model.testplan.TestPlanBean;
import com.demo.service.testcase.SearchCaseService;
import com.demo.service.testplan.TestPlanService;
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
import java.util.List;

/**
 * Created by admin on 2016/9/6.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class SearchCaseForPlan extends ActionSupport{
    @Autowired
    private SearchCaseService searchCaseService;
@Autowired
private TestPlanService testPlanService;

    private String caseName;
    private String caseOwner;
    private PageBean pageBean;
    private List<TestPlanBean> testPlanList;


    public List<TestPlanBean> getTestPlanList() {
        return testPlanList;
    }

    public void setTestPlanList(List<TestPlanBean> testPlanList) {
        this.testPlanList = testPlanList;
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

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }


    @Action(value="SimpleSearchCaseForPlan",results={@Result(name="success",location = "/jsp/TestPlan/SearchTestCaseForPlan.jsp")})
    public String SearchCaseForPlanSimple(){
        findProjectTestPlan();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("casecode ", "asc");
        String where = "upper(casename) like ?0 and upper(owner) like ?1 and status='draft'";
        String[] param = {"%%", "%%"};
        if (caseName != null && !"".equals(caseName))
            param[0] = "%" + caseName.toUpperCase() + "%";
        if (null != caseOwner && (!"".equals(caseOwner)))
            param[1] = "%" + caseOwner.toUpperCase() + "%";
        QueryResult<SearchCaseBean> resultList = searchCaseService.getScrollData(0 , 300, where, param, orderby);
        for (SearchCaseBean temp : resultList.getDatas())
            SearchBeanCatoSetting(temp);
        pageBean = pageBenInital(resultList);
        return SUCCESS;
    }
    public PageBean pageBenInital(QueryResult<SearchCaseBean> resultQurey) {
        int pageSize=300;
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));

        pageBean.setCurrentPage(1);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;

    }
    public void SearchBeanCatoSetting(SearchCaseBean temp) {
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
            default:
                break;
        }
    }


    private String mapOs;
    private String mapFunction;
    private String mapBrand;
    private String mapTestMode;

    public String getMapTestMode() {
        return mapTestMode;
    }

    public void setMapTestMode(String mapTestMode) {
        this.mapTestMode = mapTestMode;
    }

    public String getMapOs() {
        return mapOs;
    }

    public void setMapOs(String mapOs) {
        this.mapOs = mapOs;
    }

    public String getMapFunction() {
        return mapFunction;
    }

    public void setMapFunction(String mapFunction) {
        this.mapFunction = mapFunction;
    }

    public String getMapBrand() {
        return mapBrand;
    }

    public void setMapBrand(String mapBrand) {
        this.mapBrand = mapBrand;
    }

    @Action(value="AdvanceSearchCaseForPlan",results={@Result(name="success",location = "/jsp/TestPlan/SearchTestCaseForPlan.jsp")})
    public String SearchCaseForPlanAdvance(){
        findProjectTestPlan();
        StringBuffer hql = new StringBuffer();
        hql.append("from SearchCaseBean as i where 1=1 and (");
        if(null != mapOs  && !"".equals(mapOs)){
            mapOs = mapOs.replace(", ", " ");
            String[] aryFunc  = mapOs.split(" ");
            if(aryFunc.length>0){
                for(int i=0;i<=aryFunc.length-1;i++){
                    hql.append(" i.osid like '%"+aryFunc[i]+"%' or ");
                }
                hql.append("i.osid like '%"+aryFunc[aryFunc.length-1]+"%') and ");
                hql.append(" 1=1 and ( ");
            }

        }
        if(null !=mapBrand && !"".equals(mapBrand)){
            mapBrand = mapBrand.replace(", ", " ");
            String[] aryFunc  = mapBrand.split(" ");
            if(aryFunc.length>0){
                for(int i=0;i<=aryFunc.length-1;i++){
                    hql.append(" i.brandid like '%"+aryFunc[i]+"%' or ");
                }
                hql.append(" i.brandid like '%"+aryFunc[aryFunc.length-1]+"%')");
                hql.append(" and  1=1 and (");
            }
        }
        if(null != mapFunction  && !"".equals(mapFunction)){
            mapFunction = mapFunction.replace(", ", " ");
            String[] aryFunc  = mapFunction.split(" ");
            if(aryFunc.length>0){
                for(int i = 0;i<aryFunc.length-1;i++){
                    hql.append(" i.funcid="+Integer.parseInt(aryFunc[i])+" or");
                }
                hql.append(" i.funcid="+Integer.parseInt(aryFunc[aryFunc.length-1])+") and ");
            }
        }
        if(null !=mapTestMode && !"".equals(mapTestMode)){
            //mapTestMode "2" "1" "1,2"
            if(mapTestMode.equals("2")){
                hql.append("i.testmodeid = 12 and");
            }
            if(mapTestMode.equals("1,2")){
                hql.append("i.testmodeid = 1 or i.testmodeid = 12 and");
            }
            if(mapTestMode.equals("1")){
                hql.append("i.testmodeid = 1 or i.testmodeid = 12 and");
            }
//            mapTestMode = mapTestMode.replace(", "," ");
//            String[] aryFunc = mapTestMode.split(" ");
//            if(aryFunc.length>0){
//                for(int i =0;i<aryFunc.length-1;i++){
//                    hql.append(" i.testmodeid="+Integer.parseInt(aryFunc[i])+" or");
//                }
//                hql.append(" i.testmodeid="+Integer.parseInt(aryFunc[aryFunc.length-1])+" and ");
//            }
        }

        hql.append(" i.status='Draft' and 1= 1  " );
        hql.append(" Order by i.casecode asc");
        System.out.println(hql);
        QueryResult<SearchCaseBean> resultList = searchCaseService.findResultByHql(hql.toString());
        for (SearchCaseBean temp : resultList.getDatas())
            SearchBeanCatoSetting(temp);
        pageBean = pageBenInital(resultList);

        return SUCCESS;
    }
    public void findProjectTestPlan(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String projectInstkey =(String)session.getAttribute("projectKey");

        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("createdate ", "desc");
        String where = "projectinstkey=?0 and testplanstatus!=?1 ";
        String[] param = {projectInstkey,"del"};
        QueryResult<TestPlanBean> queryResult = testPlanService.getScrollData(0,30,where,param,orderby);
        testPlanList = queryResult.getDatas();

    }
}
