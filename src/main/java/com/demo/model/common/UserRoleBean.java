package com.demo.model.common;

/**
 * Created by admin on 2016/9/21.
 */
public class UserRoleBean {
    private String instkey;
    private String username;
    private String mailbox;
    private String live;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getLive() {
        return live;
    }

    public String getInstkey() {

        return instkey;
    }

    public void setInstkey(String instkey) {
        this.instkey = instkey;
    }
}
