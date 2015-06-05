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

import com.lenovo.ittools.ctd.bean.common.User;
import com.lenovo.ittools.ctd.dao.common.UserDAO;

public class UserDAOImpl  extends HibernateDaoSupport implements UserDAO {

	@SuppressWarnings("unchecked")
	public User loginUser(String userName, String password) {
		String hql = "from User as u where u.username='"+userName+"' and u.password='"+password+"'";
		List<User> list = getHibernateTemplate().find(hql);
		User user = null;
		if(list.size()>0){
			user = list.get(0);
		}
		return user;
	}

	public void saveUser(User user) {
	     this.getHibernateTemplate().save(user);
	}

	public User findByID(String instkey) {
		User user = (User)this.getHibernateTemplate().get(User.class, instkey);
		return user;
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
	public List<User> findUserByConditionForPage(final String hql, final int offset,
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

	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}

}
