package com.demo.service.testcase.impl;

import com.demo.dao.testcase.TestCaseDao;
import com.demo.model.testcase.*;
import com.demo.service.impl.BaseServiceImpl;
import com.demo.service.testcase.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/9/2.
 */
@Service("testCaseService")
public class TestCaseServiceImpl extends BaseServiceImpl<TestCase> implements TestCaseService{
    @Autowired
    private TestCaseDao testCaseDao;
    @Override
    public Map<Integer, String> findTestCaseBrandAllForMap() {
        Map map=new LinkedHashMap(0);
        List<TestCaseBrand>  list= testCaseDao.findBrandAll();
        if(list.size()>0){
            for(int i=0; i<list.size();i++){
                TestCaseBrand tcb = list.get(i);
                map.put(tcb.getBrandid(),tcb.getBrandcato());
            }
        }
        return map;
    }

    @Override
    public Map<Integer, String> findTestCaseFunctionAllForMap() {
        Map map= new LinkedHashMap();
        List<TestCaseFunction> list = testCaseDao.findTestCaseFunctionAll();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                TestCaseFunction tcf = list.get(i);
                map.put(tcf.getFunctionid(),tcf.getFunctioncato());
            }
        }
        return map;
    }

    @Override
    public Map<Integer, String> findLanguageAllForMap() {
        Map map= new LinkedHashMap();
        List<LanguagesBean> list = testCaseDao.findLanguageBeanAll();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                LanguagesBean lb = list.get(i);
                map.put(lb.getLanguageinstkey(),lb.getLanvalue());
            }
        }
        return map;
    }

    @Override
    public Map<Integer, String> findTestCaseModeAllForMap() {
        Map map= new LinkedHashMap();
        List<TestCaseTestMode> list = testCaseDao.findTestModeAll();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                TestCaseTestMode testCaseTestMode = list.get(i);
                map.put(testCaseTestMode.getTestmodeid(),testCaseTestMode.getTestmodecato());
            }
        }
        return map;
    }

    @Override
    public Map<Integer, String> findSupportOSAllForMap() {
        Map map= new LinkedHashMap();
        List<TestCaseSupportOs> list = testCaseDao.findSupportOSAll();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                TestCaseSupportOs so = list.get(i);
                map.put(so.getOsinstkey(),so.getOsdes());
            }
        }
        return map;
    }

    @Override
    public void saveTestCaseInfo(TestCaseInfo info) {
        testCaseDao.saveTestCaseInfo(info);
    }

    @Override
    public void updateTestCaseInfo(TestCaseInfo info) {
        testCaseDao.updateTestCaseInfo(info);
    }

    @Override
    public void saveCaseLanMap(List<CaseLanguage> caseLan) {
        testCaseDao.saveCaseLanMap(caseLan);
    }

    @Override
    public void savePictureBean(PictureBean pb) {
        testCaseDao.savePictureBean(pb);
    }

    @Override
    public TestCaseInfo findTestCaseInfoByCaseInfoStkey(String key) {
        return testCaseDao.findTestCaseInfoByCaseInfoStkey(key);
    }

    @Override
    public TestCaseInfo findTestCaseInfoByCaseKey(String key) {
        return testCaseDao.findTestCaseInfoByCasekey(key);
    }

    @Override
    public void saveTestCaseContent(TestCaseContent content) {
        testCaseDao.saveTestCaseContent(content);
    }

    @Override
    public List<LanguagesBean> findLanguageBeanAll() {
        return testCaseDao.findLanguageBeanAll();
    }

    @Override
    public List<CaseLanguage> findCaseLanguageByCaseId(String key) {
        return testCaseDao.findCaseLanguageByCaseID(key);
    }

    @Override
    public List<TestCaseContent> findCaseContentByCaseKey(String key) {
        return testCaseDao.findCaseContentByCaseKey(key);
    }

    @Override
    public TestCaseInfo findTestCaseInfoByCasekey(String key) {
        return testCaseDao.findTestCaseInfoByCasekey(key);
    }

    @Override
    public List<PictureBean> findPictureByCaseContentkey(String key) {
        return testCaseDao.findPictureByCaseContentkey(key);
    }

    @Override
    public void updateCaseLanguage(CaseLanguage temp) {
        testCaseDao.updateCaseLanguage(temp);
    }

    @Override
    public void updateCaseContent(TestCaseContent content) {
        testCaseDao.updateCaseContent(content);
    }

    @Override
    public void saveTestcaseHistory(TestCaseHistroy caseHistroy) {
        testCaseDao.saveTestcaseHistory(caseHistroy);
    }
}
