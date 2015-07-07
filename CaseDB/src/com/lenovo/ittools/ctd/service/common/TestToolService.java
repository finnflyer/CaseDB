package com.lenovo.ittools.ctd.service.common;

import com.lenovo.ittools.ctd.bean.common.TestToolBean;
import com.lenovo.ittools.ctd.common.PageBean;

public interface TestToolService {
	public void save(TestToolBean toolBean);
	public void delete(TestToolBean toolBean);
	public void update(TestToolBean toolBean);
	public TestToolBean findTestToolBeanByInstkey(String instkey);
	public PageBean findTestToolBeansByCondition(String hql,
			int pageSize, int page) throws Exception;
}
