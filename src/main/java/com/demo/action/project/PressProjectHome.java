package com.demo.action.project;

import com.demo.model.formbean.PageBean;
import com.demo.model.issue.IssueBean;
import com.demo.model.issue.IssueComments;
import com.demo.model.project.ProjectBean;
import com.demo.model.testplan.TestPlanBean;
import com.demo.service.issue.IssueCommentsService;
import com.demo.service.issue.IssueService;
import com.demo.service.project.ProjectService;
import com.demo.service.testplan.TestPlanService;
import com.demo.util.CatoSetting;
import com.demo.util.DBToExcelFile;
import com.demo.util.Generator;
import com.demo.util.QueryResult;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/7.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class PressProjectHome extends ActionSupport {
    @Autowired
    private ProjectService projectService;

    @Action(value = "ProjectHome", results = {@Result(name = "success",type="redirect" ,location = "SearchProject",
    params={"searchflag","1"})})
    public String pressProjectHome() {
        return SUCCESS;
    }

    private String projectName;
    private String projectOwner;
    private int searchflag = 1;
    private PageBean pageBean;
    private int pageNumber = 1;
    int pageSize = 30;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSearchflag(int searchflag) {
        this.searchflag = searchflag;
    }

    public int getSearchflag() {
        return searchflag;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    @Action(value = "SearchProject", results = {@Result(name = "success", location = "/jsp/Project/ProjectHome.jsp")})
    public String SearchProject() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("createDate ", "desc");
        String where = "upper(projectName) like ?0 and upper(projectOwner) like ?1 and enable != ?2";
        String[] param = {"%%", "%%","Delete"};
        if (projectName != null && !"".equals(projectName))
            param[0] = "%" + projectName.toUpperCase() + "%";
        if (null != projectOwner && (!"".equals(projectOwner)))
            param[1] = "%" + projectOwner.toUpperCase() + "%";
        if (searchflag == 1)
            session.setAttribute("ohql", param);
        else
            param = (String[]) session.getAttribute("ohql");
        QueryResult<ProjectBean> queryResult = projectService.getScrollData(0+pageSize*(pageNumber-1),30,where,param,orderby);
        pageBean = pageBeanInital(queryResult);
        return SUCCESS;
    }
    public PageBean pageBeanInital(QueryResult<ProjectBean> resultQurey) {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));

        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;

    }
    @Autowired
    private IssueCommentsService commentsService;
    private String projectKey;
    private ProjectBean projectBean;
    private PageBean issuePageBean;
    private PageBean allIssuePageBean;
    private PageBean limistationPageBean;
    private PageBean OsKnownPageBean;


    @Autowired
    private TestPlanService testPlanService;
    @Autowired
    private IssueService issueService;


    public PageBean getOsKnownPageBean() {
        return OsKnownPageBean;
    }

    public void setOsKnownPageBean(PageBean osKnownPageBean) {
        OsKnownPageBean = osKnownPageBean;
    }

    public PageBean getLimistationPageBean() {
        return limistationPageBean;
    }

    public void setLimistationPageBean(PageBean limistationPageBean) {
        this.limistationPageBean = limistationPageBean;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    public PageBean getIssuePageBean() {
        return issuePageBean;
    }

    public void setIssuePageBean(PageBean issuePageBean) {
        this.issuePageBean = issuePageBean;
    }

    public PageBean getAllIssuePageBean() {
        return allIssuePageBean;
    }

    public void setAllIssuePageBean(PageBean allIssuePageBean) {
        this.allIssuePageBean = allIssuePageBean;
    }

    @Action(value="ShowProjectDetail",results={@Result(name="success",location="/jsp/Project/ProjectDetail.jsp")})
    public String ShowProject(){
        projectBean = projectService.findById(projectKey);
        //TestPlan
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("createdate ", "desc");
        String where = "projectinstkey=?0 and testplanstatus != ?1";
        String[] param = {projectKey,"Del"};
        QueryResult<TestPlanBean> queryResult = testPlanService.getScrollData(0,30,where,param,orderby);
        pageBean = pageBeanIni(queryResult);
        //issue
         where = "projectinstkey=?0 and issueStatus =?1 and issueStatus !=?2";
        String[] paramDel = {projectKey,"Open","Del",};
        QueryResult<IssueBean> issueQueryResult = issueService.getScrollData(0,300,where,paramDel,orderby);
        for(IssueBean temp :issueQueryResult.getDatas()){
            List<IssueComments> commentsList = commentsService.findIssueCommentsByIssueKey(temp.getInstkey());
            if(commentsList.size()>0){
                temp.setComments(commentsList.get(0).getComments());
                temp.setCreateBy(commentsList.get(0).getCreateBy());
            }


            Map<Integer,String> testSiteMap= new LinkedHashMap();
            testSiteMap.put(1,"CDL");
            testSiteMap.put(2,"LCFC");
            testSiteMap.put(3,"Compal");
            testSiteMap.put(4,"Wistron");
            testSiteMap.put(5,"Quata");
            String testSite = testSiteMap.get(temp.getTestSite());
            temp.setTestSiteCato(testSite);
            CatoSetting.issueBeanCatoSetting(temp);
        }
        issuePageBean = issuePageBeanIni(issueQueryResult);
        //Limitation
        where = "projectinstkey=?0 and issueStatus =?1 and issueStatus !=?2";
        String[] paramLimi = {projectKey,"Limitation","Del",};
        QueryResult<IssueBean> limitationIssueQuery = issueService.getScrollData(0,300,where,paramLimi,orderby);
        for(IssueBean temp :limitationIssueQuery.getDatas()){
            List<IssueComments> commentsList = commentsService.findIssueCommentsByIssueKey(temp.getInstkey());
            if(commentsList.size()>0){
                temp.setComments(commentsList.get(0).getComments());
                temp.setCreateBy(commentsList.get(0).getCreateBy());
            }
            Map<Integer,String> testSiteMap= new LinkedHashMap();
            testSiteMap.put(1,"CDL");
            testSiteMap.put(2,"LCFC");
            testSiteMap.put(3,"Compal");
            testSiteMap.put(4,"Wistron");
            testSiteMap.put(5,"Quata");
            String testSite = testSiteMap.get(temp.getTestSite());
            temp.setTestSiteCato(testSite);
            CatoSetting.issueBeanCatoSetting(temp);
        }
        limistationPageBean = issuePageBeanIni(limitationIssueQuery);
        //OSKnwon
        where = "projectinstkey=?0 and issueStatus =?1 and issueStatus !=?2";
        String[] paramOSKnown = {projectKey,"SW_OS_W10_DGN","Del",};
        QueryResult<IssueBean> OSKnownIssueQuery = issueService.getScrollData(0,300,where,paramLimi,orderby);
        for(IssueBean temp :limitationIssueQuery.getDatas()){
            List<IssueComments> commentsList = commentsService.findIssueCommentsByIssueKey(temp.getInstkey());
            if(commentsList.size()>0){
                temp.setComments(commentsList.get(0).getComments());
                temp.setCreateBy(commentsList.get(0).getCreateBy());
            }
            Map<Integer,String> testSiteMap= new LinkedHashMap();
            testSiteMap.put(1,"CDL");
            testSiteMap.put(2,"LCFC");
            testSiteMap.put(3,"Compal");
            testSiteMap.put(4,"Wistron");
            testSiteMap.put(5,"Quata");
            String testSite = testSiteMap.get(temp.getTestSite());
            temp.setTestSiteCato(testSite);
            CatoSetting.issueBeanCatoSetting(temp);
        }
        OsKnownPageBean = issuePageBeanIni(limitationIssueQuery);

        //All Issue
        where = "projectinstkey=?0 and issueStatus !=?1";
        String[] paramAll = {projectKey,"Del",};
        QueryResult<IssueBean> allIssueQueryResult = issueService.getScrollData(0,300,where,paramAll,orderby);
        for(IssueBean temp :allIssueQueryResult.getDatas()){
            List<IssueComments> commentsList = commentsService.findIssueCommentsByIssueKey(temp.getInstkey());
            if(commentsList.size()>0){
                temp.setComments(commentsList.get(0).getComments());
                temp.setCreateBy(commentsList.get(0).getCreateBy());
            }
            Map<Integer,String> testSiteMap= new LinkedHashMap();
            testSiteMap.put(1,"CDL");
            testSiteMap.put(2,"LCFC");
            testSiteMap.put(3,"Compal");
            testSiteMap.put(4,"Wistron");
            testSiteMap.put(5,"Quata");
            String testSite = testSiteMap.get(temp.getTestSite());
            temp.setTestSiteCato(testSite);
            CatoSetting.issueBeanCatoSetting(temp);
        }
        allIssuePageBean = issuePageBeanIni(allIssueQueryResult);

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("projectKey",projectKey);

        return SUCCESS;
    }
    public PageBean issuePageBeanIni(QueryResult<IssueBean> resultQurey) {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));

        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;

    }

    public PageBean pageBeanIni(QueryResult<TestPlanBean> resultQurey) {
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setAllRow((int) resultQurey.getTotalCount());
        pageBean.setTotalPage(pageBean.countTotalPage(pageSize, (int) resultQurey.getTotalCount()));

        pageBean.setCurrentPage(pageNumber);
        pageBean.setList(resultQurey.getDatas());
        pageBean.init();
        return pageBean;

    }


    @Action(value = "DownloadExcelFile", results = {@Result(name = "download",
            type = "stream", params = {
            "contentType", "application/octet-stream",
            "inputName", "inputStream", "contentDisposition",
            "attachment;filename=${projectName}.xlsx", "bufferSize",
            "4096"})})
    public String DownloadFile() {

        return "download";
    }

    /**
     * 获取下载流
     * 对应 annotation 注解里面的 "inputName", "inputStream"
     * 假如 annotation 注解改为 "inputName", "myStream"，则下面的方法则应改为：getMyStream
     *
     * @return InputStream
     */
    public InputStream getInputStream() {
        projectName = projectService.findById(projectKey).getProjectName();
        List<IssueBean> list = issueService.findIssueBeanByProjectKey(projectKey);
        for(IssueBean temp:list)
            CatoSetting.issueBeanCatoSetting(temp);
        String title = "Issue";
        String[] rowsName = new String[]{"No","Family","Platform","Phase","ECR","Sev","Abstract",
                "Reproduce Step","Component Owner","Lang","OS/SKU","Open_Date",
                "Comments\n" +
                        "(Issue module/BIOS/Config.etc.)","Finder","Tracker","Fixed Module","Defect Status","Close_Date","Issue Style"};
        List<Object[]>  dataList = new ArrayList<Object[]>();

        Object[] objs = null;
        for(int i=0;i<list.size();i++){
            IssueBean issueBean = list.get(i);

            objs = new Object[rowsName.length];
            objs[0]=i;
            objs[1]=projectName;
            objs[2]=issueBean.getPlatform();
            objs[3]=setPahseFound(issueBean);
            objs[4]=issueBean.getEcrNumber();
            objs[5]=issueBean.getPriority();
            objs[6]=issueBean.getIssueName();
            objs[7]=issueBean.getReproduceStep();
            objs[8]=issueBean.getComponent();
            if(issueBean.getLanguage()!=null)
                objs[9]= issueBean.getLanguage();
            else
                objs[9]= "";
            objs[10]=issueBean.getOsCato();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(issueBean.getCreatedate());
            objs[11]=date;
            List<IssueComments> commentsList = commentsService.findIssueCommentsByIssueKey(issueBean.getInstkey());
            objs[12] = "";
            if(commentsList.size()>0){
                for(IssueComments temp:commentsList)
                  objs[12] = objs[12]+"\n"+temp.getComments();
            }
            objs[13]=issueBean.getOwner();

            objs[14]=issueBean.getOwner();
            objs[15] = "N/A";
            objs[16] = issueBean.getIssueStatus();
            if(issueBean.getEditordate()!=null){
                date = df.format(issueBean.getEditordate());
                objs[17] = date;
            }else{
                objs[17]="";
            }

            objs[18] = issueBean.getIssuestyle();
            dataList.add(objs);
        }
        System.out.println(dataList.size());
        try {
            XSSFWorkbook workbook = DBToExcelFile.dbDataToExcel(title, rowsName, dataList);
            String filePath = "D:\\CTDDataBase\\Issue\\"+projectKey;// 定义一个到处文件名字的生成规则

            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            String fileName = filePath+ "\\"+Generator.generatorID()+".xlsx";
            File file = new File(fileName);
            FileOutputStream fs = new FileOutputStream(file);
            workbook.write(fs);

            return new FileInputStream(new File(fileName));
        }catch (Exception e){

        }
        return  null;
    }

    @Action(value="DeleteProject",results={@Result(name="success",location="/jsp/Project/ProjectHome.jsp")})
    public String execute(){
        projectBean = projectService.findById(projectKey);
        projectBean.setEnable("Delete");
        projectService.update(projectBean);
        return SUCCESS;
    }

    public String setPahseFound(IssueBean issueBean){
        String phaseFound = "";
        switch (issueBean.getPhaseFound()){
            case 1:phaseFound = "Wave0";
                    break;
            case 2:phaseFound ="Wave1";
                break;
            case 3:phaseFound = "Wave2";
                break;
            case 4:phaseFound = "Reresh";
                break;
            case 5:phaseFound = "Other";
                break;
            case 123:phaseFound = "Wave0/Wave1/Wave2";
                break;
            case 234:phaseFound = "Wave1/Wave2/Refresh";
                break;
            case 25:phaseFound = "Wave1/Other";
                break;
            case 24:phaseFound = "Wave1/Refresh";
                break;
            case 23:phaseFound = "Wave1/Wave2";
                break;
            case 12:phaseFound = "Wave0/Wave1";
                break;
            default:phaseFound = "";
                break;
        }
        return phaseFound;
    }
}
