package com.lenovo.ittools.ctd.dao.testcase.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.lenovo.ittools.ctd.bean.testcase.SearchCaseBean;
import com.lenovo.ittools.ctd.dao.testcase.SearchCaseDAO;

public class SearchCaseDAOImpl extends HibernateDaoSupport implements SearchCaseDAO {
	public List<SearchCaseBean> findByConditionForPage(final String hql, final int offset, final int length) throws Exception {
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
	public SearchCaseBean findSearchCaseBeanByCaseInstkey(String caseInstkey) {
		 String hqlString = "from SearchCaseBean as scb where scb.caseInstkey='"+caseInstkey+"'";
		 List list = this.getHibernateTemplate().find(hqlString);
		 if(list.size()>0){
			 return (SearchCaseBean) this.getHibernateTemplate().find(hqlString).get(0);
		 }
		return null;
	}
	
}
