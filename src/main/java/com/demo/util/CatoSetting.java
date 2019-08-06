package com.demo.util;

import com.demo.model.issue.IssueBean;
import com.demo.model.testcase.TestCaseInfo;

/**
 * Created by Admin on 2016/9/9.
 */
public class CatoSetting {
    public static void testCaseInfoSetting(TestCaseInfo testCaseInfo){
        switch(testCaseInfo.getBrandid()){
            case 1:
                testCaseInfo.setBrandCato("ThinkPad");
                break;
            case 12:
                testCaseInfo.setBrandCato("ThinkPad/ThinkStation");
                break;
            case 2:
                testCaseInfo.setBrandCato("ThinkStation");
        }
        switch (Integer.parseInt(testCaseInfo.getOsid())) {
            case 1:
                testCaseInfo.setOsCato("xp");
                break;
            case 12:
                testCaseInfo.setOsCato("xp/win7");
                break;
            case 123:
                testCaseInfo.setOsCato("xp/win7/win8.x");
                break;
            case 1234:
                testCaseInfo.setOsCato("xp/win7/win8.x/win10");
                break;
            case 2:
                testCaseInfo.setOsCato("win7");
                break;
            case 23:
                testCaseInfo.setOsCato("win7/win8.x");
                break;
            case 234:
                testCaseInfo.setOsCato("win7/win8.x/win10");
                break;
            case 3:
                testCaseInfo.setOsCato("win8.x");
                break;
            case 34:
                testCaseInfo.setOsCato("win8.x/win10");
                break;
            case 4:
                testCaseInfo.setOsCato("win10");
                break;
            case 24:
                testCaseInfo.setOsCato("win7/win10");
                break;
            default:
                break;
        }
    }
    public static void issueBeanCatoSetting(IssueBean issueBean){
       switch (issueBean.getTestSite()){
           case 1:
               issueBean.setTestSiteCato("CDL");
               break;
           case 2:
               issueBean.setTestSiteCato("LCFC");
               break;
           case 3:
               issueBean.setTestSiteCato("Compal");
               break;
           case 4:
               issueBean.setTestSiteCato("Wistron");
               break;
           case 5:
               issueBean.setTestSiteCato("Quata");
               break;
           case 6:
               issueBean.setTestSiteCato("Foxcom");
               break;
           default:
               break;
       }

        switch(issueBean.getPhaseFound()){
            case 1:
                issueBean.setPhaseCato("Wave0");
                break;
            case 12:
                issueBean.setPhaseCato("Wave0/Wave1");
                break;
            case 123:
                issueBean.setPhaseCato("Wave0/Wave1/Wave2");
                break;
            case 1234:
                issueBean.setPhaseCato("Wave0/Wave1/Wave2/Refresh");
                break;
            case 12345:
                issueBean.setPhaseCato("Wave0/Wave1/Wave2/Refresh/Other");
                break;
            case 2:
                issueBean.setPhaseCato("Wave1");
                break;
            case 23:
                issueBean.setPhaseCato("Wave1/Wave2");
                break;
            case 234:
                issueBean.setPhaseCato("Wave1/Wave2/Refresh");
                break;
            case 2345:
                issueBean.setPhaseCato("Wave1/Wave2/Refresh/Other");
                break;
            case 3:
                issueBean.setPhaseCato("Wave2");
                break;
            case 34:
                issueBean.setPhaseCato("Wave2/Refresh");
                break;
            case 345:
                issueBean.setPhaseCato("Wave2/Refresh/Other");
                break;
            case 4:
                issueBean.setPhaseCato("Refresh");
                break;
            case 45:
                issueBean.setPhaseCato("Refresh/Other");
                break;
            case  5:
                issueBean.setPhaseCato("Other");
                break;
            default:
                break;
        }
        switch (issueBean.getOsid()) {
            case 1:
                issueBean.setOsCato("xp");
                break;
            case 12:
                issueBean.setOsCato("xp/win7");
                break;
            case 123:
                issueBean.setOsCato("xp/win7/win8.x");
                break;
            case 1234:
                issueBean.setOsCato("xp/win7/win8.x/win10");
                break;
            case 12345:
                issueBean.setOsCato("xp/win7/win8.x/win10/win10 RS");
                break;
            case 2:
                issueBean.setOsCato("win7");
                break;
            case 23:
                issueBean.setOsCato("win7/win8.x");
                break;
            case 24:
                issueBean.setOsCato("win7/win10");
                break;
            case 234:
                issueBean.setOsCato("win7/win8.x/win10");
                break;
            case 2345:
                issueBean.setOsCato("win7/win8.x/win10/win10 RS");
                break;
            case 3:
                issueBean.setOsCato("win8.x");
                break;
            case 34:
                issueBean.setOsCato("win8.x/win10");
                break;
            case 345:
                issueBean.setOsCato("win8.x/win10/win10 RS");
                break;
            case 4:
                issueBean.setOsCato("win10");
                break;
            case 45:
                issueBean.setOsCato("win10/win10 RS");
                break;
            case 5:
                issueBean.setOsCato("win10 RS");
            default:
                break;
        }
    }

}
