package com.demo.Test;


import com.demo.model.testcase.CaseLanguage;
import com.demo.model.testcase.TestCase;
import com.demo.model.testcase.TestCaseInfo;
import com.demo.model.testplan.TestPlanBean;
import com.demo.model.testplan.TestPlanContent;
import com.demo.service.testcase.TestCaseService;
import com.demo.service.testplan.TestPlanContentService;
import com.demo.service.testplan.TestPlanService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by admin on 2016/9/29.
 */
public class POITest {
    ApplicationContext ac = null;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-hibernate.xml"});
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    @Test
    public void TestPOI() {
        long startTime = System.currentTimeMillis();
        String key = "201609051443060797AF";
        TestPlanService testPlanService = (TestPlanService) ac.getBean("testPlanService");
        TestPlanContentService testPlanContentService = (TestPlanContentService) ac.getBean("caseContentService");
        List<TestPlanContent> tpContentList = testPlanContentService.findTestPlanContentByTestPlanKey(key);
        TestPlanBean testPlanBean = testPlanService.findById(key);
        MSWordTool changer = new MSWordTool();
        String wordTemple = "C:\\TestFolder\\template.doc";
        changer.setTemplate(wordTemple);
        String testPlanFolder = "C:\\TestFolder\\" + testPlanBean.getTestplanname();

        //no exist folder to create;
        File file = new File(testPlanFolder);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }

        for (TestPlanContent temp : tpContentList) {
            String caseKey = temp.getTestcaseinstkey();
            Map<String, String> content = setContentForCase(changer, caseKey);
            changer.replaceBookMark(content);
            String caseWordFile = testPlanFolder + "\\" + caseKey + ".doc";
            changer.saveAsNewFile(caseWordFile);
        }

        System.out.println("time==" + (System.currentTimeMillis() - startTime));
    }

    public void caseInfoCatoSetting(TestCaseInfo temp) {
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


    @Test
    public void ExportSingleCase() {
        long startTime = System.currentTimeMillis();
        String key = "201505121756410526TU";
        MSWordTool changer = new MSWordTool();
        String wordTemple = "D:\\template.doc";
        changer.setTemplate(wordTemple);
        Map<String, String> content = setContentForCase(changer, key);
        changer.replaceBookMark(content);
        changer.replaceBookMark(content);
        String caseWordFile = "d:\\" + key + ".doc";
        changer.saveAsNewFile(caseWordFile);
        System.out.println("time=" + (System.currentTimeMillis() - startTime));
    }


    public Map<String, String> setContentForCase(MSWordTool changer, String caseKey) {
        TestCaseService testCaseService = (TestCaseService) ac.getBean("testCaseService");
        TestCaseInfo caseInfo = testCaseService.findTestCaseInfoByCaseKey(caseKey);
        TestCase testCase = testCaseService.findById(caseKey);
        caseInfoCatoSetting(caseInfo);
        Map<String, String> content = new HashMap<String, String>();
        content.put("CaseName", testCase.getCasename());
        content.put("CaseID", testCase.getCasecode());
        content.put("Version", testCase.getVersion());
        content.put("Owner", testCase.getOwner());
        content.put("Time", caseInfo.getExecutetime().toString());
        content.put("Brand", caseInfo.getBrandCato());
        content.put("Automation", caseInfo.getAutomation());
        content.put("COwner", caseInfo.getComponerowner());
        content.put("Function", caseInfo.getFuncCato());
        content.put("Introduction", caseInfo.getModifyreason());
        content.put("TestMode", "N/A");

        //Set language Bean
        List<CaseLanguage> caseLanList = testCaseService.findCaseLanguageByCaseId(caseKey);
        List<String> languagesBeanList = getLanguageList();
        List<String> localizedSetList = new ArrayList<>();
        Map<Integer, String> map = testCaseService.findLanguageAllForMap();
        for (CaseLanguage temp : caseLanList) {
            temp.setLanvalue(map.get(temp.getLocalset()));
        }
        for (int i = 0; i < caseLanList.size(); i++) {
            int setInt = caseLanList.get(i).getLocalset();
            String setValue = languagesBeanList.get(setInt - 1);
            localizedSetList.add(setValue);
        }

        Iterator<String> bookAll = changer.getAllBookMarkIndoc();
        while (bookAll.hasNext()) {
            switch (bookAll.next()) {
                case "RE":
                    content.put("RE", localizedSetList.get(0));
                    break;
                case "FR":
                    content.put("FR", localizedSetList.get(1));
                    break;
                case "GR":
                    content.put("GR", localizedSetList.get(2));
                    break;
                case "IT":
                    content.put("IT", localizedSetList.get(3));
                    break;
                case "SP":
                    content.put("SP", localizedSetList.get(4));
                    break;
                case "NO":
                    content.put("NO", localizedSetList.get(5));
                    break;
                case "HU":
                    content.put("HU", localizedSetList.get(6));
                    break;
                case "FI":
                    content.put("FI", localizedSetList.get(7));
                    break;
                case "RU":
                    content.put("RU", localizedSetList.get(8));
                    break;
                case "NL":
                    content.put("NL", localizedSetList.get(9));
                    break;
                case "BR":
                    content.put("BR", localizedSetList.get(10));
                    break;
                case "PO":
                    content.put("PO", localizedSetList.get(11));
                    break;
                case "AR":
                    content.put("AR", localizedSetList.get(12));
                    break;
                case "CZ":
                    content.put("CZ", localizedSetList.get(13));
                    break;
                case "GK":
                    content.put("GK", localizedSetList.get(14));
                    break;
                case "HB":
                    content.put("HB", localizedSetList.get(15));
                    break;
                case "SV":
                    content.put("SV", localizedSetList.get(16));
                    break;
                case "PL":
                    content.put("PL", localizedSetList.get(17));
                    break;
                case "DK":
                    content.put("DK", localizedSetList.get(18));
                    break;
                case "SL":
                    content.put("SL", localizedSetList.get(19));
                    break;
                case "TR":
                    content.put("TR", localizedSetList.get(20));
                    break;
                case "RO":
                    content.put("RO", localizedSetList.get(21));
                    break;
                case "SR":
                    content.put("SR", localizedSetList.get(22));
                    break;
                case "SK":
                    content.put("SK", localizedSetList.get(23));
                    break;
                case "KR":
                    content.put("KR", localizedSetList.get(24));
                    break;
                case "CS":
                    content.put("CS", localizedSetList.get(25));
                    break;
                case "CT":
                    content.put("CT", localizedSetList.get(26));
                    break;
                case "HK":
                    content.put("HK", localizedSetList.get(27));
                    break;
                case "JP":
                    content.put("JP", localizedSetList.get(28));
                    break;
                case "NE":
                    content.put("NE", localizedSetList.get(29));
                    break;
                default:
                    break;
            }
        }

        return content;
    }


    public List<String> getLanguageList() {
        List<String> languageList = new ArrayList<>();
        languageList.add("/");
        languageList.add("EN");
        languageList.add("RE");
        languageList.add("FR");
        languageList.add("GR");
        languageList.add("IT");
        languageList.add("SP");
        languageList.add("NO");
        languageList.add("HU");
        languageList.add("FI");
        languageList.add("RU");

        languageList.add("NL");
        languageList.add("BR");
        languageList.add("PO");
        languageList.add("AR");
        languageList.add("CZ");

        languageList.add("GK");
        languageList.add("HB");
        languageList.add("SV");
        languageList.add("PL");
        languageList.add("DK");

        languageList.add("SL");
        languageList.add("TR");
        languageList.add("RO");
        languageList.add("SR");

        languageList.add("SK");
        languageList.add("KR");
        languageList.add("CS");
        languageList.add("CT");
        languageList.add("HK");
        languageList.add("JP");
        languageList.add("NE");
        return languageList;
    }

    @Test
    public void zip() throws Exception {
        File f = new File("C:\\TestFolder\\Win10 RS P2 LCFC  Cross Refresh Full Test Plan");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                "c:/TestFolder/test.zip"));
        zip(out, f, null);
        System.out.println("zip done");
        out.close();
    }

    public void zip(ZipOutputStream out, File f, String base) throws Exception {
        System.out.println("zipping " + f.getAbsolutePath());
        if (f.isDirectory()) {
            File[] fc = f.listFiles();
            if (base != null) out.putNextEntry(new ZipEntry(base + "/"));
            base = base == null ? "" : base + "/";
            for (int i = 0; i < fc.length; i++) {
                zip(out, fc[i], base + fc[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) out.write(b);
            in.close();
        }
    }
}
