package com.lenovo.ittools.ctd.bean.testplan;

import java.util.Date;

public class TestPlanBean {
		private String testPlanInstkey;
		private String testPlanName;
		private String testPlanType;
		private String projectInstkey;
		private String projectName;
		private String testPlanOwnerInstkey;
		private String testPlanDescription;
		private Date createDate;
		private String testPlanStatus;
		private String testPlanComments;
		private String testPlanOwner;
		
		public String getTestPlanOwner() {
			return testPlanOwner;
		}
		public void setTestPlanOwner(String testPlanOwner) {
			this.testPlanOwner = testPlanOwner;
		}
		public String getTestPlanInstkey() {
			return testPlanInstkey;
		}
		public void setTestPlanInstkey(String testPlanInstkey) {
			this.testPlanInstkey = testPlanInstkey;
		}
		public String getTestPlanName() {
			return testPlanName;
		}
		public void setTestPlanName(String testPlanName) {
			this.testPlanName = testPlanName;
		}
		public String getTestPlanType() {
			return testPlanType;
		}
		public void setTestPlanType(String testPlanType) {
			this.testPlanType = testPlanType;
		}
		public String getProjectInstkey() {
			return projectInstkey;
		}
		public void setProjectInstkey(String projectInstkey) {
			this.projectInstkey = projectInstkey;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public String getTestPlanOwnerInstkey() {
			return testPlanOwnerInstkey;
		}
		public void setTestPlanOwnerInstkey(String testPlanOwnerInstkey) {
			this.testPlanOwnerInstkey = testPlanOwnerInstkey;
		}
		public String getTestPlanDescription() {
			return testPlanDescription;
		}
		public void setTestPlanDescription(String testPlanDescription) {
			this.testPlanDescription = testPlanDescription;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getTestPlanStatus() {
			return testPlanStatus;
		}
		public void setTestPlanStatus(String testPlanStatus) {
			this.testPlanStatus = testPlanStatus;
		}
		public String getTestPlanComments() {
			return testPlanComments;
		}
		public void setTestPlanComments(String testPlanComments) {
			this.testPlanComments = testPlanComments;
		}
		
}
