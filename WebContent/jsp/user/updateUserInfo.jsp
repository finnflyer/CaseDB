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
    <link rel="shortcut icon" href="common/images/favicon.ico">
    <title>Edit Personal</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="Edit Personal">
	<link rel="stylesheet" type="text/css" href="common/css/ctd_style.css">
	
  </head>
  	
  <body>
    
    <br/>
    <div>You are here: <s:a href="index.jsp" cssClass="blodfont">CTD Home</s:a> &gt; <s:a href="user/viewMyUserInfo" cssClass="blodfont">Personal information View</s:a> &gt; Edit Personal Information</div>
    <br>
    
    
    <s:include value="/jsp/user/updateUserInfo_.jsp"></s:include>
    
    
    
  </body>
</html>
