package com.lenovo.ittools.ctd.bean.testplan;

import java.util.Date;

public class TestPlanContent {
		private String testPlanInstkey;
		private String testPlanContentInstkey;
		private Integer tpOrder;
		private String testCaseInstkey;
		private Date createTime;
		private String testCaseCode;
		
		public String getTestCaseCode() {
			return testCaseCode;
		}
		public void setTestCaseCode(String testCaseCode) {
			this.testCaseCode = testCaseCode;
		}
		public String getTestPlanInstkey() {
			return testPlanInstkey;
		}
		public void setTestPlanInstkey(String testPlanInstkey) {
			this.testPlanInstkey = testPlanInstkey;
		}
		public String getTestPlanContentInstkey() {
			return testPlanContentInstkey;
		}
		public void setTestPlanContentInstkey(String testPlanContentInstkey) {
			this.testPlanContentInstkey = testPlanContentInstkey;
		}
		public Integer getTpOrder() {
			return tpOrder;
		}
		public void setTpOrder(Integer tpOrder) {
			this.tpOrder = tpOrder;
		}
		public String getTestCaseInstkey() {
			return testCaseInstkey;
		}
		public void setTestCaseInstkey(String testCaseInstkey) {
			this.testCaseInstkey = testCaseInstkey;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
		
}
