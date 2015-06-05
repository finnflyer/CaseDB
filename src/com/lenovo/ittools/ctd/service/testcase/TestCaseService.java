package com.lenovo.ittools.ctd.service.testcase;

import java.util.List;
import java.util.Map;

import com.lenovo.ittools.ctd.bean.testcase.CaseLanMap;
import com.lenovo.ittools.ctd.bean.testcase.CaseLanguage;
import com.lenovo.ittools.ctd.bean.testcase.LanguagesBean;
import com.lenovo.ittools.ctd.bean.testcase.PictureBean;
import com.lenovo.ittools.ctd.bean.testcase.TestCase;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseBrand;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseContent;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseFunction;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseHistory;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseInfo;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseSupportOs;
import com.lenovo.ittools.ctd.bean.testcase.TestCaseTestMode;

public interface TestCaseService {
	public List<TestCaseFunction>  findTestcaseFunctionAll();
	public TestCaseFunction findTestCaseFunctionbyId(Integer funcId);
	public Map<Integer, String> findTestcaseFuntionAllForMap();
	
	public List<TestCaseTestMode>  findTestcaseTestModeAll();
	public Map<Integer, String> findTestcaseTestModeAllForMap();
	public List<TestCaseBrand>   findTestcaseBrandAll();
	public Map<Integer, String> findTestcaseBrandAllForMap();

	public List<TestCaseSupportOs> findTestcaseSupportOSAll();
	public Map<Integer, String> findTestcaseSupportOSAllForMap();
	
	public List<LanguagesBean> findLanguagesBeansAll() throws Exception;
	public Map<Integer, String> findLanguageBeanAllForMap() throws Exception;
	
	public TestCase findTestCaseByCaseInstkey(String caseInstkey);
	public TestCaseInfo findTestCaseInfoByCaseInfoStkey(String caseInfoInstkey);
	public TestCaseInfo findTestCaseInfoByCaseInstkey(String caseInstkey);
	public List<TestCaseContent> findTestCaseContentsByCaseInstkey(String caseInstkey);
	public List<PictureBean> findPictureBeansByCaseContentInstkey(String caseContentInstkey);
	public List<CaseLanMap> findCaseLanMapsByCaseInstkey(String caseInstkey);
	public PictureBean findPictureBeanByPictureInstkey(String pictureInstkey);
	public List<CaseLanguage> findCaseLanguagesByCaseInstkey(String caseInstkey);

	
	
	public void saveTestCase(TestCase testcase);
	public void saveTestCaseInfo(TestCaseInfo testCaseInfo);
	public void saveTestCaseContent(TestCaseContent testCaseContent);
	public void saveCaseLanMap(List<CaseLanguage> lanMap);
	public void savePictureBean(PictureBean pictureBean);
	public void saveTestcaseHistory(TestCaseHistory testCaseHistory);
	
	public void updateTestCaseInfo(TestCaseInfo testCaseInfo);
	public void updateTestContent(TestCaseContent testCaseContent);
	public void updateTestCase(TestCase testcase);
	public void updateCaseLanMap(List<CaseLanguage> lanMap);
	public void deleteTestCase(TestCase testCase);
	public void updatePictureBean(PictureBean pictureBean);
	
}
