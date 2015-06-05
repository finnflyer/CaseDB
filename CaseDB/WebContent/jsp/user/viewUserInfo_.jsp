<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <base href="<%=basePath%>">

<div style="width: 1024px;">
	<fieldset>
		<legend>Personal Information:</legend>
		<table  class="form" width="100%">
			<tr>
				<td class="label_item" width="150px">User:</td>
				<td width="300px"><s:property value="user.username"/></td>
				<td class="label_item" width="150px">Employee ID:</td>
				<td width="300px"><s:property value="user.employeeid"/></td>
			<tr>
				<td class="label_item" width="150px">E-mail:</td>
				<td width="300px"><s:property value="user.email"/></td>
				<td class="label_item" width="150px">Site:</td>
				<td width="300px"><s:property value="#request.bufferCfg.userSite[#request.user.site]"/></td>
			</tr>
			<tr>
				<td class="label_item" width="150px">Phone:</td>
				<td width="300px"><s:property value="user.phone"/></td>
			</tr>
			<tr>
				<td class="label_item" width="150px">Description</td>
				<td colspan="3"><s:property value="user.description"/></td>
			</tr>
		</table>
    </fieldset>
</div>