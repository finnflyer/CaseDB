<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Edit Personal</title>

  </head>
  	
  <body>


  <div class="row-fluid">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
      <legend>Edit Personal Information:</legend>
      <s:form action="phase4/UpdateUserInfo" theme="simple">
        <table class="table table table-bordered ">
          <tr action="updateUserInfo" theme="simple" namespace="/phase4">
              <s:fielderror cssErrorStyle="color:red"></s:fielderror>
          <tr>
            <td class="active" width="150px">User:</td>
            <td><s:textfield name="user.username" value="%{user.username}"
                             disabled="true"/></td>
          </tr>
          <tr>
            <td class="active" width="150px">E-mail:</td>
            <td><s:textfield name="user.mailbox" value="%{user.mailbox}"
                             cssErrorStyle="color:red"/></td>
          </tr>
          <td class="active" width="150px">Site:</td>
          <td><s:select list="%{issueFormBean.mapTestsite}" value="%{siteId}"  id="siteId" name="user.site"/></td>
          </tr>
          <tr>
            <td class="active" width="150px">Employee ID:</td>
            <td><s:textfield name="user.employeeid" value="%{user.employeeid}"
                             cssErrorStyle="color:red"/></td>
          </tr>
          <tr>
            <td class="active" width="150px">Description</td>
            <td colspan="3"><s:textarea cols="80" rows="3" name="user.description"
                                        value="%{user.description}"
                                        cssErrorStyle="color:red"></s:textarea></td>
          </tr>
          <tr>
            <td colspan="4" style="text-align: right;">
              <s:hidden name="instkey" value="%{user.instkey}"></s:hidden>
              <s:submit value="Update"  cssClass="btn btn-primary" type="button"></s:submit>
            </td>
          </tr>
        </table>
      </s:form>
    </div>
    <div class="col-lg-1">
    </div>
  </div>
    
    
  </body>
</html>
