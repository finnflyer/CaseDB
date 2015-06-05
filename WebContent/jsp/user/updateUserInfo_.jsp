<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <base href="<%=basePath%>">

<div style="width: 1024px;" >
	<fieldset >
		<legend>Edit Personal Information:</legend>
		
		<s:fielderror cssErrorStyle="color:red"></s:fielderror>
		<table  class="form" width="100%">
		<s:form cssStyle="margin: 0px;" action="updateUserInfo" theme="simple" namespace="/user">
			<tr>
				<td class="label_item" width="150px">User:</td>
				<td width="300px"><s:textfield name="user.username" value="%{user.username}" size="50" disabled="true" /></td>
			</tr>
			<tr>
				<td class="label_item" width="150px">E-mail:</td>
				<td width="300px"><s:textfield name="user.email" value="%{user.email}" cssErrorStyle="color:red" /></td>
				<td class="label_item" width="150px">Site:</td>
				<td width="300px"><s:select name="user.site" list="#request.bufferCfg.userSite" value="user.site"></s:select> </td>
			</tr>
			<tr>
				<td class="label_item" width="150px">Phone:</td>
				<td width="300px"><s:textfield name="user.phone" value="%{user.phone}" cssErrorStyle="color:red" /></td>
				<td class="label_item" width="150px">Employee ID:</td>
				<td width="300px"><s:textfield name="user.employeeid" value="%{user.employeeid}" cssErrorStyle="color:red"/></td>
			</tr>
			<tr>
				<td class="label_item" width="150px">Description</td>
				<td colspan="3"><s:textarea cols="80" rows="3" name="user.description" value="%{user.description}" cssErrorStyle="color:red"></s:textarea></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">
					<s:hidden name="user.instkey" value="%{user.instkey}" />
					<s:submit value="Update"></s:submit>
				</td>
			</tr>
			</s:form>
		</table>
		
    </fieldset>
</div>