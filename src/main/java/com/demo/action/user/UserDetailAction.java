package com.demo.action.user;

import com.demo.model.common.User;
import com.demo.model.formbean.IssueFormBean;
import com.demo.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by admin on 2016/9/20.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class UserDetailAction extends ActionSupport {
    @Autowired
    private UserService userService;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Action(value = "UserHome", results = {@Result(name = "success", location = "/jsp/user/userHome.jsp")})
    public String UserDetailInfo(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        return SUCCESS;
    }
    private IssueFormBean issueFormBean = IssueFormBean.getInstance();
    private Integer siteId;


    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public IssueFormBean getIssueFormBean() {
        return issueFormBean;
    }

    public void setIssueFormBean(IssueFormBean issueFormBean) {
        this.issueFormBean = issueFormBean;
    }

    @Action(value="EditUserInfo",results={@Result(name="success",location="/jsp/user/updateUserInfo.jsp")})
    public String EditUserInfo(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        Map testSiteMap= new LinkedHashMap();
        testSiteMap.put(0,"");
        testSiteMap.put(1,"CDL");
        testSiteMap.put(2,"LCFC");
        testSiteMap.put(3,"Compal");
        testSiteMap.put(4,"Wistron");
        testSiteMap.put(5,"Quata");
        issueFormBean.setMapTestsite(testSiteMap);
        if(user.getSite()==null){
            user.setSite("CDL");
        }
        switch(user.getSite()){
            case "CDL":
                siteId=1;
                break;
            case "LCFC":
                siteId=2;
                break;
            case "Compal":
                siteId=3;
                break;
            case "Wistron":
                siteId =4;
                break;
            case "Quata":
                siteId =5;
                break;
            default: siteId=0;
                break;
        }

        return SUCCESS;
    }
    private String instkey;

    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }

    @Action(value="UpdateUserInfo",results={@Result(name="success",type="redirect",location="UserHome")})
    public String UpdateUserInfo(){
        User updateUser = userService.findById(instkey);
        System.out.println("update the User Info");
        updateUser.setEmployeeid(user.getEmployeeid());
        updateUser.setMailbox(user.getMailbox());
        updateUser.setDescription(user.getDescription());
        Integer userSite = Integer.parseInt(user.getSite());
        Map<Integer,String> siteMap = issueFormBean.getMapTestsite();
        String site = siteMap.get(userSite);
        updateUser.setSite(site);
        userService.update(updateUser);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user",updateUser);
        return SUCCESS;
    }

}
