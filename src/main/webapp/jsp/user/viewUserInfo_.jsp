<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<base href="<%=basePath%>">

<div class="row-fluid">
    <div class="col-lg-12" style="padding-left:60px;padding-right:60px">
        <div class="row">
            <div class="col-lg-12">
            </div>
            <legend style="font-size:16px">Personal Information:</legend>
            <table class="table table table-bordered ">
                <tr>
                    <td class="active">User:</td>
                    <td ><s:property value="user.username"/></td>
                </tr>
                <tr>
                    <td class="active">Employee ID:</td>
                    <td ><s:property value="user.employeeid"/></td>
                </tr>
                <tr>
                    <td class="active">E-mail:</td>
                    <td ><s:property value="user.mailbox"/></td>
                </tr>
                <tr>
                    <td class="active">Site:</td>
                    <td ><s:property value="user.site"/></td>
                </tr>
                <tr>
                    <td class="active" width="150px">Role:</td>
                    <td ><s:property value="user.role"/></td>
                </tr>
                <tr>
                    <td class="active" width="150px">Description</td>
                    <td colspan="3"><s:property value="user.description"/></td>
                </tr>
            </table>
        </div>
    </div>
</div>
