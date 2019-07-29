package com.demo.Test;

import com.demo.model.abbreviation.AbbreviationBean;
import com.demo.model.common.User;
import com.demo.model.formbean.PageBean;
import com.demo.model.issue.IssueBean;
import com.demo.model.overtime.ApplyOTBean;
import com.demo.model.overtime.OTBean;
import com.demo.model.overtime.OverTimeUser;
import com.demo.model.project.ProjectBean;
import com.demo.model.testcase.*;
import com.demo.model.testplan.TestPlanBean;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.abbreviation.AbbreviationService;
import com.demo.service.overtime.OverTimeUserService;
import com.demo.service.common.UserService;
import com.demo.service.issue.IssueService;
import com.demo.service.overtime.ApplyOverTimeService;
import com.demo.service.overtime.OvertimeService;
import com.demo.service.project.ProjectService;
import com.demo.service.testcase.SearchCaseService;
import com.demo.service.testcase.TestCaseService;
import com.demo.service.testplan.TestPlanContentService;
import com.demo.service.testplan.TestPlanService;
import com.demo.util.DBToExcelFile;
import com.demo.util.Generator;
import com.demo.util.QueryResult;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Admin on 2016/9/2.
 */
public class TestAction {
    ApplicationContext ac = null;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-hibernate.xml"});
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    @Test
    public void Test() {
        TestCaseService testCaseService = (TestCaseService) ac.getBean("testCaseService");
        String key = "201505121756410558PE";
        CaseLanguage l = new CaseLanguage();
        l.setCaseinstkey(key);
        List<CaseLanguage> i = new ArrayList<CaseLanguage>();
        i.add(l);
        testCaseService.saveCaseLanMap(i);

    }

    @Test
    public void data() {
        UserService userService = (UserService) ac.getBean("userService");
        OvertimeService overtimeService = (OvertimeService) ac.getBean("OvertimeService");
        QueryResult<OTBean> query = overtimeService.getScrollData();
        List<OTBean> otBeanList = query.getDatas();
        PageBean pageBean = pageBeanInital(query);
        System.out.println(pageBean.getAllRow());


    }

    @Test
    public void list() {
        TestAction t = new TestAction();
        System.out.println(t.Permutation("cabd").toString());
    }

    public ArrayList<String> Permutation(String str) {
        List<String> res = new ArrayList<>();
        if (str != null && str.length() > 0) {
            PermutationHelper(str.toCharArray(), 0, res);
            Collections.sort(res);
        }
        return (ArrayList) res;
    }

    public void PermutationHelper(char[] cs, int i, List<String> list) {
        if (i == cs.length - 1) {
            String val = String.valueOf(cs);
            if (!list.contains(val)) list.add(val);
        } else {
            for (int j = i; j < cs.length; j++) {
                swap(cs, i, j);
                PermutationHelper(cs, i + 1, list);
                swap(cs, i, j);
            }
        }
    }

    public void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public PageBean pageBeanInital(QueryResult<OTBean> resultQurey) {
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

    @Test
    public void TestTimeStamp() {
        String StartDate = "2018-6-20 00:00:00";
        String EndDate = "2018-6-20 1:30:00";
        double days = (double) (Timestamp.valueOf(StartDate).getTime() - Timestamp.valueOf(EndDate).getTime()) / (3600 * 1000);
        System.out.println(Timestamp.valueOf(EndDate).getTime() - Timestamp.valueOf(StartDate).getTime());
        System.out.print("Days" + days);
    }

    @Test // 如何查询 通过QueryResult
    public void TestSearchService() {
        SearchCaseService searchCaseService = (SearchCaseService) ac.getBean("searchCaseService");
        int i = (int) searchCaseService.getTotalCount();
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("casecode ", "asc");
        String where = "upper(casename) like ? and upper(owner) like ?";
        String[] param = {"%%", "%%"};

        System.out.println(i);
        int j = 1;
        int pagenumber = j;
        QueryResult<SearchCaseBean> list = searchCaseService.getScrollData(0 + 30 * j, 30, where, param, orderby);

        System.out.println(list.getDatas().size());

    }

    @Test
    public void TestOverTimeUserService() {
        OverTimeUserService overTimeUserService = (OverTimeUserService) ac.getBean("otUserService");
        UserService userService = (UserService) ac.getBean("userService");
        List<User> userList = userService.getScrollData().getDatas();
        for (User temp : userList) {
            OverTimeUser otUser = new OverTimeUser();
            otUser.setInstkey(Generator.generatorID());
            otUser.setLoginname(temp.getUsername());
            otUser.setEmployid(String.valueOf(temp.getEmployeeid()));
            overTimeUserService.save(otUser);
        }
    }

    @Test
    public void TestDate() {
        ApplyOverTimeService applyOverTimeService = (ApplyOverTimeService) ac.getBean("ApplyOverTimeService");
        List<ApplyOTBean> applyOTBeans = applyOverTimeService.getScrollData().getDatas();
        for(ApplyOTBean temp :applyOTBeans){
            Timestamp i = temp.getStartTime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
            String out = sdf.format(new Date(i.getTime()));
            String in = tdf.format(new Date(i.getTime()));
            System.out.println(out+in);
        }

    }

    @Test
    public void TestIssueService() {
        IssueService issueService = (IssueService) ac.getBean("issueService");
        IssueBean testBean = new IssueBean();
        testBean.setInstkey(Generator.generatorID());
        testBean.setPhaseFound(12);
        testBean.setProjectinstkey("aldjflasjldf");
        testBean.setCaseNum("testBean");
        testBean.setConfiguration("test");
        testBean.setComponent("lkjalksdjf");
        testBean.setEcrNumber("");
        testBean.setPriority("1");
        testBean.setTestSite(2);
        testBean.setIssueName("lkajldjflasjf");
        testBean.setDescription("djaldfl");
        issueService.save(testBean);
    }

    @Test
    public void TestMapPhase() {
        IssueService issueService = (IssueService) ac.getBean("issueService");
        Map<Integer, String> mapPhase = issueService.findIssuePhaseBeanForMap();
        System.out.println(mapPhase.size());
    }

    @Test
    public void TestUser(){
        AbbreviationService abbreviationService = (AbbreviationService)ac.getBean("abbService");
        List<AbbreviationBean> abbreviationBeans = abbreviationService.getScrollData().getDatas();
        for(AbbreviationBean temp :abbreviationBeans){
            if(temp.getComments()==null )
                temp.setComments("");
            abbreviationService.update(temp);
        }
    }
    @Test
    public void TestSort() {
        TestPlanContentService testPlanContentService = (TestPlanContentService) ac.getBean("caseContentService");
        TestCaseService testCaseService = (TestCaseService) ac.getBean("testCaseService");
        List<TestPlanContent> tpc = testPlanContentService.findTestPlanContentByTestPlanKey("20160908234416036OJ");
        Collections.sort(tpc, new Comparator<TestPlanContent>() {
            public int compare(TestPlanContent o1, TestPlanContent o2) {

                //按照学生的年龄进行升序排列
                if (Integer.parseInt(getCaseCode(o2)) < Integer.parseInt(getCaseCode(o1))) {
                    return 1;
                }
                if (Integer.parseInt(getCaseCode(o2)) == Integer.parseInt(getCaseCode(o1))) {
                    return 0;
                }
                return -1;
            }
        });
        for (int i = 0; i < tpc.size(); i++) {
            tpc.get(i).setTporder(i + 1);
            testPlanContentService.update(tpc.get(i));
        }
        System.out.println("llll");
    }

    @Test
    public void Prepare() {
        TestPlanService testPlanService = (TestPlanService) ac.getBean("testPlanService");
        ProjectService projectService = (ProjectService) ac.getBean("projectService");
        String[] params = {"Active"};
        QueryResult<TestPlanBean> queryResult = testPlanService.getScrollData(0, 300, "testplanstatus =? ", params);
        // System.out.println(queryResult.getDatas().size());


        for (TestPlanBean temp : queryResult.getDatas()) {
            if (temp.getTestplanstatus() != "Del") {
                ProjectBean projectBean = new ProjectBean();
                projectBean.setProjectInstkey(temp.getProjectinstkey());
                projectBean.setProjectName(temp.getProjectname());
                projectBean.setEnable("Enable");
                projectBean.setCreateDate(temp.getCreatedate());
                projectBean.setComments(temp.getTestplancomments());
                projectBean.setProjectOwner(temp.getTestplanowner());
                projectService.saveOrUpdate(projectBean);
            }
        }
        QueryResult<ProjectBean> projectList = projectService.getScrollData();
        List<ProjectBean> tempList = projectList.getDatas();
//        for ( int i = 0 ; i < tempList.size() - 1 ; i ++ ) {
//            for ( int j = tempList.size() - 1 ; j > i; j -- ) {
//                if (tempList.get(j).getProjectName().equals(tempList.get(i).getProjectName())) {
//                    projectService.delete(tempList.get(j));
//                }
//            }
//        }
//        for(ProjectBean tempProjectBean : tempList){
//            for(TestPlanBean testPlanBean: list.getDatas()){
//                if(testPlanBean.getProjectname()==tempProjectBean.getProjectName()||
//                        testPlanBean.getProjectname().contains(tempProjectBean.getProjectName())){
//                    testPlanBean.setProjectinstkey(tempProjectBean.getProjectInstkey());
//                    testPlanService.update(testPlanBean);
//                }
//            }
//        }

    }

    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    public String getCaseCode(TestPlanContent temp) {
        TestCaseService testCaseService = (TestCaseService) ac.getBean("testCaseService");
        TestCase testCase = testCaseService.findById(temp.getTestcaseinstkey());
        return testCase.getCasecode();
    }

    @Test
    public void TestExcel() {
        IssueService issueService = (IssueService) ac.getBean("issueService");
        List<IssueBean> list = issueService.findIssueBeanByProjectKey("201608301739410815FM");
        String title = "Issue";
        String[] rowsName = new String[]{"IssueNo", "IssueName", "ECR", "TestSite", "Date"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < list.size(); i++) {
            IssueBean issueBean = list.get(i);
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = issueBean.getIssueName();
            objs[2] = issueBean.getEcrNumber();
            objs[3] = issueBean.getTestSite();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(issueBean.getCreatedate());
            objs[4] = date;
            dataList.add(objs);
        }
        try {
            XSSFWorkbook workbook = DBToExcelFile.dbDataToExcel(title, rowsName, dataList);
            String fileName = "C:\\CTDDataBase\\2.xlsx";       // 定义一个到处文件名字的生成规则
            File file = new File(fileName);
            FileOutputStream fs = new FileOutputStream(file);
            workbook.write(fs);
        } catch (Exception e) {

        }
    }

}
