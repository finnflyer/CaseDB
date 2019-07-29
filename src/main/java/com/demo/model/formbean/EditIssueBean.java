package com.demo.model.formbean;

/**
 * Created by Admin on 2016/9/13.
 */
public class EditIssueBean {
    private String  issueName;
    private String  issueStatus;
    private String  issueEcr;

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getIssueEcr() {
        return issueEcr;
    }

    public void setIssueEcr(String issueEcr) {
        this.issueEcr = issueEcr;
    }
}
