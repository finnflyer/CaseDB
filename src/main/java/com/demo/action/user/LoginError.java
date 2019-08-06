package com.demo.action.user;

import com.demo.util.VerificationCodeUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import java.io.ByteArrayInputStream;

/**
 * Created by admin on 2016/9/4.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("struts-default")
public class LoginError extends ActionSupport{
    @Action(value = "LoginError.action", results = {@Result(name = "success", location = "/jsp/Error.jsp")})
    public String execute(){
        return SUCCESS;
    }

    private ByteArrayInputStream inputStream;

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Action(value="CreateImgCode",results={@Result(name = "success",type = "stream",params={
            "contentType", "image/jpeg", "inputName",
            "inputStream"
    })})
    public String getRandomPictrue() {
        VerificationCodeUtil vcu = VerificationCodeUtil.Instance();
        this.setInputStream(vcu.getImage());
        ActionContext.getContext().getSession().put("random", vcu.getVerificationCodeValue());// 取得随机字符串放入HttpSession
        return SUCCESS;
    }
}
