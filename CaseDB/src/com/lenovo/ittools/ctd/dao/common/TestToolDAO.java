package com.lenovo.ittools.ctd.dao.common;

import java.util.List;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;

public interface TestToolDAO {
		public void save(TestToolBean toolBean);
		public void delete(TestToolBean toolBean);
		public void upload(TestToolBean toolBean);
		public TestToolBean findTestToolBeanByInstkey(String instkey);
		public int getAllRowCount(String hql) throws Exception;
		public List<TestToolBean> findByConditionForPage(final String hql, final int offset, final int length) throws Exception;
}
