package com.demo.action.user;

import com.demo.model.common.User;
import com.demo.service.common.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Created by Admin on 2016/12/1.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class RoleDetailAction extends ActionSupport{
    @Autowired
    private UserService userService;

    private List<User> userList;


    @Action(value = "RoleHome", results = {@Result(name = "success", location = "/jsp/user/adminTools.jsp")})
    public String pressRole(){
        userList = userService.getScrollData().getDatas();
        return SUCCESS;
    }

    private User user;
    private String instkey;
    @Action(value="viewUserRole",results = {@Result(name="success",location = "/jsp/user/viewUserRole.jsp")})
    public String viewUserRole(){
        user = userService.findById(instkey);
        return SUCCESS;
    }
    @Action(value="updateUserRole",results = {@Result(name="success",type="redirect",location = "RoleHome")})
    public String updateUserRole(){
        User updateUser = userService.findById(user.getInstkey());
        switch (user.getRole()){
            case "1": updateUser.setRole("Admin"); break;
            case "2": updateUser.setRole("Tester");break;
            case "3":updateUser.setRole("Family Owner");break;
            case "4":updateUser.setRole("Leader");break;
            default:updateUser.setRole("Tester");
        }
        userService.update(updateUser);

        userList = userService.getScrollData().getDatas();
        return SUCCESS;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInstkey() {
        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }
}
