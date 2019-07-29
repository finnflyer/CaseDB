package com.demo.service.testcase;

import com.demo.model.testcase.*;
import com.demo.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/2.
 */
public interface TestCaseService extends BaseService<TestCase> {

    Map<Integer, String> findTestCaseBrandAllForMap();

    Map<Integer, String> findTestCaseFunctionAllForMap();

    Map<Integer, String> findSupportOSAllForMap();

    Map<Integer, String> findLanguageAllForMap();

    List<LanguagesBean> findLanguageBeanAll();

    TestCaseInfo findTestCaseInfoByCaseInfoStkey(String key);

    TestCaseInfo findTestCaseInfoByCaseKey(String key);

    List<TestCaseContent> findCaseContentByCaseKey(String key);

    List<CaseLanguage> findCaseLanguageByCaseId(String key);

    TestCaseInfo findTestCaseInfoByCasekey(String key);

    List<PictureBean> findPictureByCaseContentkey(String key);

    void saveTestCaseInfo(TestCaseInfo info);

    void saveTestCaseContent(TestCaseContent content);

    void saveCaseLanMap(List<CaseLanguage> caseLan);

    void savePictureBean(PictureBean pb);


    void updateTestCaseInfo(TestCaseInfo info);

    void updateCaseLanguage(CaseLanguage temp);

    void updateCaseContent(TestCaseContent content);
    void saveTestcaseHistory(TestCaseHistroy caseHistroy);
}
