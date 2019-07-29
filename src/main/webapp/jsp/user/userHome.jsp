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
	<title>Personal Setting</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="User Home">



</head>

<body>
<script type="text/javascript">
	$(function() {
		$("#Home").removeClass("active");
		$("#User").addClass("active");
	});
</script>
<div style="padding-right:60px; text-align: right;">
	<s:form action="showChangePWPage" namespace="/phase4" theme="simple"  cssStyle="">
		<s:submit type="button" cssClass="btn btn-primary" value="Change Password" ></s:submit>
	</s:form>
</div>
<s:include value="/jsp/user/viewUserInfo_.jsp"></s:include>

<div style="padding-right:60px; text-align: right;">
	<s:form action="EditUserInfo" namespace="/phase4" theme="simple"  cssStyle="">
		<s:submit type="button" cssClass="btn btn-primary" value="Edit sInformation" ></s:submit>
	</s:form>
</div>
</body>
</html>
