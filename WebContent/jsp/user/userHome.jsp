<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Personal Setting</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="User Home">
	
	

  </head>
  
  <body>

    	<s:include value="/jsp/user/viewUserInfo_.jsp"></s:include>
    	
    	<div style="width: 1024px; text-align: right;">
	    	<s:form action="updateUserInfoP" namespace="/user" theme="simple" id="functionForm" cssStyle="margin: 0;">
	    		<s:hidden id="userInstkey" name="user.instkey" value="%{user.instkey}" />
	    		<s:submit value="Edit Personal Information" ></s:submit>
	    	</s:form>
    	</div>

    
   
    
  </body>
</html>
