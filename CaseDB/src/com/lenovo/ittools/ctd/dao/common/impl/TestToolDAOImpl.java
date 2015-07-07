package com.lenovo.ittools.ctd.dao.common.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.dao.common.TestToolDAO;

public class TestToolDAOImpl extends HibernateDaoSupport implements TestToolDAO  {

	public void save(TestToolBean toolBean) {
		this.getHibernateTemplate().save(toolBean);
	}

	public void delete(TestToolBean toolBean) {
		this.getHibernateTemplate().update(toolBean);
	}

	public TestToolBean findTestToolBeanByInstkey(String instkey) {
		String hql = "from TestToolBean as i where i.instkey='"+instkey+"'";
		return (TestToolBean) this.getHibernateTemplate().find(hql).get(0);
	}

	public void upload(TestToolBean toolBean) {
		this.getHibernateTemplate().update(toolBean);
		
	}
	@SuppressWarnings("rawtypes")
	public int getAllRowCount(final String hql) throws Exception {	
		List result=this.getHibernateTemplate().executeFind(
				new HibernateCallback(){
					public Object doInHibernate(Session arg0) throws HibernateException, SQLException {		
						Transaction tx=arg0.beginTransaction();	
						List rqs=new ArrayList();
						try{
						Query query=arg0.createQuery(hql);	
						rqs=query.list();
						}catch(Exception e){
							System.out.println(e.getMessage());
						}				
						tx.commit();
						return rqs;
					}
				});
		return  result.size();
	}

	public List<TestToolBean> findByConditionForPage(final String hql, final int offset,
			final int length) throws Exception {
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
}
