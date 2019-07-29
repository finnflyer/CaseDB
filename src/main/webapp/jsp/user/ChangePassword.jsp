<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/12/1
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>Change Password</title>
</head>
<body>
<script type="text/javascript">
  function Validation(){
    var pw1 = $("#password2").val().trim();
    var pw2 = $("#password3").val().trim();
      console.info(pw1);
      console.info(pw2);
      if(pw1.length<6){
        $("#password2").val("");
        $("#password3").val("");
      alert("Password Length should be more than 6 bit");

      return false;
    }
    if(pw1!=pw2){
      alert("Re input the password");
      $("#password2").val("");
      $("#password3").val("");
      return false;
    }
  }
</script>
<div class="row-fluid">
  <div class="col-lg-1">
  </div>
  <div class="col-lg-10">
    <legend>Change Password:</legend>
    <s:form action="phase4/ChangePassword" theme="simple" onsubmit="return Validation();"  >
      <table class="table table table-bordered ">
        <tr theme="simple"  >
            <s:fielderror cssErrorStyle="color:red"></s:fielderror>
        <tr>
        <td class="active" width="150px">New Password:</td>
        <td><s:textfield type="password" id="password2" name="password2"/></td>
      </tr>
        <tr>
          <td class="active" width="150px">New Password:</td>
          <td><s:textfield type="password" id="password3" name="password3"/></td>
        </tr>
        <tr>
          <td colspan="4" style="text-align: right;">
            <s:submit value="Change"  cssClass="btn btn-primary" type="button"></s:submit>
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
