package com.lenovo.ittools.ctd.dao.testcase.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibm.db2.jcc.a.l;
import com.ibm.db2.jcc.a.p;
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

public class TestCaseDAOImpl extends HibernateDaoSupport implements TestCaseDAO {

	@SuppressWarnings("unchecked")
	public List<TestCaseFunction>  findTestcaseFunctionAll() throws Exception {
		String hql = "from TestCaseFunction as s order by s.functionId asc";
		List<TestCaseFunction> list=this.getHibernateTemplate().find(hql);
		return list;
	}
	public TestCaseFunction findTestCaseFunctionbyID(Integer funcId){
		String hql = "from TestCaseFunction as s where s.functionId="+funcId;
		return (TestCaseFunction) this.getHibernateTemplate().find(hql).get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<TestCaseTestMode>  findTestcaseTestModeAll() throws Exception {
		String hql = "from TestCaseTestMode as m order by m.testmodeId asc";
		List<TestCaseTestMode> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	public List<LanguagesBean>         findLanguagesBeansAll()  throws Exception{
		String hql = "from LanguagesBean as m order by m.languageId asc";
		List<LanguagesBean> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	

	
	@SuppressWarnings("unchecked")
	public List<TestCaseBrand>     findTestcaseTestcaseBrandAll() throws Exception {
		String hql = "from TestCaseBrand as b  order by b.brandId asc";
		List<TestCaseBrand> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	
	@SuppressWarnings("unchecked") 
	public List<TestCaseSupportOs> findTestcaseSupportOSAll()  throws Exception {
		String hql = "from TestCaseSupportOs as o order by o.osId asc";
		List<TestCaseSupportOs> list=new ArrayList<TestCaseSupportOs>();
		list= this.getHibernateTemplate().find(hql);
		return list;
	}


	public void saveTestCase(TestCase testcase) {
		this.getHibernateTemplate().save(testcase);
	}
	public void saveTestCaseInfo(TestCaseInfo testCaseInfo){
		this.getHibernateTemplate().save(testCaseInfo);
	}

	


	public TestCase findTestCaseByCaseInstkey(String caseInstkey) {
		String hql = "from TestCase as s where s.caseInstkey='"+caseInstkey+"'"; 
		TestCase testCase = (TestCase) this.getHibernateTemplate().find(hql).get(0);
		return testCase;
	}


	public TestCaseInfo findTestCaseInfoByCaseInfoStkey(String caseInfoInstkey) {
		String hqlString = "from TestCaseInfo as m where m.caseInfoInstkey='"+caseInfoInstkey+"'";
		TestCaseInfo testCaseInfo = (TestCaseInfo) this.getHibernateTemplate().find(hqlString).get(0);
		return testCaseInfo;
	}


	public void updateTestCase(TestCase testCase) {
			this.getHibernateTemplate().update(testCase);
	}
	public void updateTestCaseInfo(TestCaseInfo testCaseInfo) {
		   this.getHibernateTemplate().update(testCaseInfo);
		
	}
	public void updateLangeMap(List<CaseLanguage> lanMap) {
		for(CaseLanguage temp:lanMap){
			this.getHibernateTemplate().saveOrUpdate(temp);
		}
	}

	public void saveLangeMap(List<CaseLanguage> lanMap){
		for(CaseLanguage temp:lanMap){
			System.out.println(temp.getLocalSet());
			this.getHibernateTemplate().save(temp);
		}
	}


	public void saveTestCaseContent(TestCaseContent testCaseContent) {
		this.getHibernateTemplate().save(testCaseContent);
	}


	public void savePictureBean(PictureBean pictureBean) {
		this.getHibernateTemplate().saveOrUpdate(pictureBean);
	}


	public void updateTestCaseContent(TestCaseContent testCaseContent) {
		this.getHibernateTemplate().update(testCaseContent);
	}


	public TestCaseInfo findTestCaseInfoByCaseInstkey(String caseInstkey) {
		String hqlString = "from TestCaseInfo as m where m.caseInstkey='"+caseInstkey+"'";
		TestCaseInfo testCaseInfo = (TestCaseInfo) this.getHibernateTemplate().find(hqlString).get(0);
		return testCaseInfo;
	}


	public List<TestCaseContent> findTestCaseContentsByCaseInstkey(
			String caseInstkey) {
		String hqlString = "from TestCaseContent as m where m.caseInstkey='"+caseInstkey+"' Order by m.orderId asc";
		List  list = this.getHibernateTemplate().find(hqlString);
		return list;
	}


	public List<PictureBean> findPictureBeansByCaseContentInstkey(String caseContentInstkey) {
		String hqlString = "from PictureBean as m where m.caseContentInstkey='"+caseContentInstkey+"'";
		List  list = this.getHibernateTemplate().find(hqlString);
		return list;
	}


	public List<CaseLanMap> findCaseLanMapsByCaseInstkey(String caseInstkey) {
		String hqlString = "from CaseLanMap as m where m.caseInstkey='"+caseInstkey+"'";
		List  list = this.getHibernateTemplate().find(hqlString);
		return list;
	}


	public PictureBean findPictureBeanByPictureInstkey(String pictureInstkey) {
		String hqlString = "from PictureBean as m where m.pictureInstkey='"+pictureInstkey+"'";
	       return   (PictureBean) this.getHibernateTemplate().find(hqlString).get(0);
	}
	public List<CaseLanguage> findCaseLanguagesByCaseInstkey(String caseInstkey) {
		String hqlString = "from CaseLanguage as m where m.caseInstkey='"+caseInstkey+"'";
		List  list = this.getHibernateTemplate().find(hqlString);
		return list;
	}
	public void saveHistoryTestCase(TestCaseHistory testCaseHistory) {
		this.getHibernateTemplate().save(testCaseHistory);
	}
	public void deleteTestCase(TestCase testCase) {
		this.getHibernateTemplate().delete(testCase);
	}
	public void updatePictureBean(PictureBean pictureBean) {
		this.getHibernateTemplate().update(pictureBean);
	}
}
