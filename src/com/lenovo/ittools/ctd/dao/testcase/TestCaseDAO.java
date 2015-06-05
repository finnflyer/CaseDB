package com.lenovo.ittools.ctd.dao.testcase;

import java.util.List;

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

public interface TestCaseDAO {
	
	public List<TestCaseFunction>    findTestcaseFunctionAll() throws Exception;
	public TestCaseFunction findTestCaseFunctionbyID(Integer funcId);
	public List<TestCaseTestMode>   findTestcaseTestModeAll() throws Exception;
	public List<TestCaseBrand>          findTestcaseTestcaseBrandAll() throws Exception;	
	public List<TestCaseSupportOs>  findTestcaseSupportOSAll()  throws Exception;
	public List<LanguagesBean>         findLanguagesBeansAll()  throws Exception;
	
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
	public void saveLangeMap(List<CaseLanguage> lanMap);
	public void savePictureBean(PictureBean pictureBean);
	public void saveHistoryTestCase(TestCaseHistory testCaseHistory);

	
	
	public void updateTestCase(TestCase testCase);
	public void updateTestCaseInfo(TestCaseInfo testCaseInfo);
	public void updateTestCaseContent(TestCaseContent testCaseContent);
	public void updateLangeMap(List<CaseLanguage> lanMap);
	public void updatePictureBean(PictureBean pictureBean);
	public void deleteTestCase(TestCase testCase);
}
