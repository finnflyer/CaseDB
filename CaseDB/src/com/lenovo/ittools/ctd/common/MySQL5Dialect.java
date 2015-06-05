package com.lenovo.ittools.ctd.common;

import java.sql.Types;

import org.hibernate.Hibernate;

public class MySQL5Dialect extends org.hibernate.dialect.MySQL5Dialect {
	public MySQL5Dialect()
	{
		   super();
		   // register additional hibernate types for default use in scalar
		   // sqlquery type auto detection
		   registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
		}
}
