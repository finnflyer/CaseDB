package com.lenovo.ittools.ctd.service.testcase.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.lenovo.ittools.ctd.dao.testcase.TestCaseDAO;
import com.lenovo.ittools.ctd.service.testcase.TestCaseService;

public class TestServiceImpl implements TestCaseService {
	private TestCaseDAO tcDAO;
	
	public TestCaseDAO getTcDAO() {
		return tcDAO;
	}

	public void setTcDAO(TestCaseDAO tcDAO) {
		this.tcDAO = tcDAO;
	}
	public void saveTestCase(TestCase testCase){
		this.tcDAO.saveTestCase(testCase);
	}
	public void saveTestCaseInfo(TestCaseInfo testCaseInfo){
		this.tcDAO.saveTestCaseInfo(testCaseInfo);
	}

	public List<TestCaseFunction>  findTestcaseFunctionAll() {
		try{
			return tcDAO.findTestcaseFunctionAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public TestCaseFunction findTestCaseFunctionbyId(Integer funcId){
		return this.tcDAO.findTestCaseFunctionbyID(funcId);
	}
	public List<LanguagesBean> findLanguagesBeansAll() throws Exception{
		return this.tcDAO.findLanguagesBeansAll();
	}
	public Map<Integer, String> findLanguageBeanAllForMap() throws Exception{

		Map map=new LinkedHashMap(0);
		List<LanguagesBean>  list=findLanguagesBeansAll();
		if(list.size()>0){
			for(int i=0; i<list.size();i++){
				LanguagesBean LanBean=(LanguagesBean)list.get(i);
				map.put(LanBean.getLanguageId(),LanBean.getLanValue());	
			}
			return map;
		}
		return null;
	}
	
	public Map<Integer, String> findTestcaseFuntionAllForMap(){
		
			Map<Integer, String> map = new LinkedHashMap();
			List<TestCaseFunction> list =  findTestcaseFunctionAll();
			if(list.size()>0){
			for(int i=0; i<list.size(); i++){
				TestCaseFunction f=(TestCaseFunction)list.get(i);
				map.put(f.getFunctionId(),f.getFunctionCato());
				}
			return map;
			}
		return null;
	}
	
		public List<TestCaseTestMode>  findTestcaseTestModeAll(){
		try{
			return this.tcDAO.findTestcaseTestModeAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<Integer,String> findTestcaseTestModeAllForMap(){
		Map map=new LinkedHashMap(0);
		List<TestCaseTestMode>  list= findTestcaseTestModeAll();
		if(list.size()>0){
			for(int i=0; i<list.size();i++){
				TestCaseTestMode tm=(TestCaseTestMode)list.get(i);
				map.put(tm.getTestmodeId(), tm.getTestmodeCato());
			
			}
			return map;
		}
		return null;
	}
	
	public List<TestCaseSupportOs>  findTestcaseSupportOSAll(){
		try{
			return tcDAO.findTestcaseSupportOSAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public Map<Integer, String> findTestcaseSupportOSAllForMap(){
		
		Map map=new LinkedHashMap(0);
		List<TestCaseSupportOs>  list= findTestcaseSupportOSAll();
		if(list.size()>0){
			for(int i=0; i<list.size();i++){
				TestCaseSupportOs os=(TestCaseSupportOs)list.get(i);
				map.put(os.getOsId(), os.getOsDes());
			}
			return map;
		}
		return null;
	}

	public List<TestCaseBrand>  findTestcaseBrandAll(){
		try {
			return tcDAO.findTestcaseTestcaseBrandAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public Map<Integer, String> findTestcaseBrandAllForMap(){
		Map map=new LinkedHashMap(0);
		List<TestCaseBrand>  list= findTestcaseBrandAll();
		if(list.size()>0){
			for(int i=0; i<list.size();i++){
				TestCaseBrand brand=(TestCaseBrand)list.get(i);
				map.put(brand.getBrandId(),brand.getBrandCato());
			}
			return map;
		}
		return null;
	}

	public void saveCaseLanMap(List<CaseLanguage> lanMap) {
		this.tcDAO.saveLangeMap(lanMap);
		
	}
	public TestCase findTestCaseByCaseInstkey(String caseInstkey){
		return this.tcDAO.findTestCaseByCaseInstkey(caseInstkey);
	}
	public TestCaseInfo findTestCaseInfoByCaseInfoStkey(String caseInfoInstkey){
		return this.tcDAO.findTestCaseInfoByCaseInfoStkey(caseInfoInstkey);
	}
	public void saveTestCaseContent(TestCaseContent testCaseContent) {
		this.tcDAO.saveTestCaseContent(testCaseContent);
	}
	public void savePictureBean(PictureBean pictureBean) {
		this.tcDAO.savePictureBean(pictureBean);
	}

	public void updateTestCaseInfo(TestCaseInfo testCaseInfo) {
		this.tcDAO.updateTestCaseInfo(testCaseInfo);
	}

	public void updateTestContent(TestCaseContent testCaseContent) {
		this.tcDAO.updateTestCaseContent(testCaseContent);
		
	}

	public TestCaseInfo findTestCaseInfoByCaseInstkey(String caseInstkey) {
		// TODO Auto-generated method stub
		return this.tcDAO.findTestCaseInfoByCaseInstkey(caseInstkey);
	}

	public List<TestCaseContent> findTestCaseContentsByCaseInstkey(
			String caseInstkey) {
		return this.tcDAO.findTestCaseContentsByCaseInstkey(caseInstkey);
	}

	public List<PictureBean>findPictureBeansByCaseContentInstkey(String caseContentInstkey){
		return this.tcDAO.findPictureBeansByCaseContentInstkey(caseContentInstkey);
	}

	public List<CaseLanMap> findCaseLanMapsByCaseInstkey(String caseInstkey) {
		 return this.tcDAO.findCaseLanMapsByCaseInstkey(caseInstkey);
	}

	public PictureBean findPictureBeanByPictureInstkey(String pictureInstkey) {
		 return this.tcDAO.findPictureBeanByPictureInstkey(pictureInstkey);
	}

	public void updateTestCase(TestCase testcase) {
		this.tcDAO.updateTestCase(testcase);
		
	}

	public List<CaseLanguage> findCaseLanguagesByCaseInstkey(String caseInstkey) {
		 return this.tcDAO.findCaseLanguagesByCaseInstkey(caseInstkey);
	}

	public void updateCaseLanMap(List<CaseLanguage> lanMap) {
		this.tcDAO.updateLangeMap(lanMap);
	}

	public void saveTestcaseHistory(TestCaseHistory testCaseHistory) {
		this.tcDAO.saveHistoryTestCase(testCaseHistory);
	}

	public void deleteTestCase(TestCase testCase) {
		this.tcDAO.deleteTestCase(testCase);
	}

	public void updatePictureBean(PictureBean pictureBean) {
		this.tcDAO.updatePictureBean(pictureBean);
		
	}
}
