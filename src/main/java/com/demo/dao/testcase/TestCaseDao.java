package com.demo.dao.testcase;

import com.demo.model.testcase.*;

import java.util.List;

/**
 * Created by Admin on 2016/9/2.
 */
public interface TestCaseDao {
     List<TestCaseFunction> findTestCaseFunctionAll();
     List<TestCaseBrand>    findBrandAll();
     List<TestCaseTestMode> findTestModeAll();
     List<LanguagesBean>    findLanguageBeanAll();
     List<TestCaseSupportOs> findSupportOSAll();
     List<TestCaseContent> findCaseContentByCaseKey(String key);
     List<CaseLanguage> findCaseLanguageByCaseID(String key);
     TestCaseInfo findTestCaseInfoByCaseInfoStkey(String key);
     TestCaseInfo findTestCaseInfoByCasekey(String key);
     List<PictureBean> findPictureByCaseContentkey(String key);
     List<TestCaseHistroy>  findTestCaseHistoryAll();

     void saveTestCaseInfo(TestCaseInfo info);
     void saveTestCaseContent(TestCaseContent content);
     void saveCaseLanMap(List<CaseLanguage> caseLan);
     void savePictureBean(PictureBean pb);
     void saveTestcaseHistory(TestCaseHistroy caseHistroy);

     void updateTestCaseInfo(TestCaseInfo info);
     void updateCaseLanguage(CaseLanguage temp);
     void updateCaseContent(TestCaseContent content);


}
