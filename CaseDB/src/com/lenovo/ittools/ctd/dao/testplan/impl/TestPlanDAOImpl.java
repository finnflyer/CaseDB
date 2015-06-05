package com.lenovo.ittools.ctd.dao.testplan.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lenovo.ittools.ctd.bean.testplan.TestPlanBean;
import com.lenovo.ittools.ctd.bean.testplan.TestPlanContent;
import com.lenovo.ittools.ctd.dao.testplan.TestPlanDao;

public class TestPlanDAOImpl extends HibernateDaoSupport implements TestPlanDao {
	
	public List<TestPlanBean> findByConditionForPage(final String hql, final int offset, final int length) throws Exception{
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			}
		});
		
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public int getAllRowCount(final String hql) throws Exception {
		
		List result=this.getHibernateTemplate().executeFind(
				new HibernateCallback()
				{
					public Object doInHibernate(Session arg0) throws HibernateException, SQLException 
					{		
						Transaction tx=arg0.beginTransaction();	

						List rqs=new ArrayList();
						try{
						Query query=arg0.createQuery(hql);
							
						rqs=query.list();
						}catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
					
						tx.commit();
						return rqs;
					}
				});
		return  result.size();
	}
	public void saveTestPlan(TestPlanBean testplanBean) {
			this.getHibernateTemplate().save(testplanBean);
	}

	public void updateTestPlan(TestPlanBean testPlanBean) {
		this.getHibernateTemplate().update(testPlanBean);
	}

	public void deleteTestPlan(TestPlanBean testPlanBean) {
		this.getHibernateTemplate().delete(testPlanBean);
	}

	public void saveTestPlanContent(TestPlanContent testPlanContent) {
		 this.getHibernateTemplate().save(testPlanContent);
	}

	@SuppressWarnings("unchecked")
	public List<TestPlanBean> findTestPlanBeansAll() {
	     String hql = "from TestPlanBean as tpb where 1=1 and tpb.testPlanStatus='Active'";
	     List<TestPlanBean> list = this.getHibernateTemplate().find(hql);
	     if(list.size()>0){
	    	 return list;
	     }
	     return null;
	}

	public List<TestPlanContent> findTestPlanContentsByTestPlanInstkey(
			String testPlanInstkey) {
		String hql = "from TestPlanContent as t where t.testPlanInstkey='"+testPlanInstkey+"' Order by t.tpOrder";
		List<TestPlanContent> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public void deleteTestPlanContent(TestPlanContent testPlanContent) {
		this.getHibernateTemplate().delete(testPlanContent);
	}

	public TestPlanBean findTestPlanBeanByTestPlanInstkey(String testPlanInstkey) {
		String hql = "from TestPlanBean as i where i.testPlanInstkey='"+testPlanInstkey+"'";
		return (TestPlanBean) this.getHibernateTemplate().find(hql).get(0);
	}

}
