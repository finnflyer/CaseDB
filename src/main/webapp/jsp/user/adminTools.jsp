<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/12/1
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>User Role</title>
</head>
<body>
<script type="text/javascript">
    $(function() {
        $("#Home").removeClass("active");
        $("#Role").addClass("active");
    });
</script>
<div class="row">
    <div class="col-lg-2">
    </div>
    <div class="col-lg-7">
        <legend style="font-size:16px">User Role Information:</legend>
        <table class="table table table-bordered table-striped" style="table-layout:fixed">
            <thead>
            <tr>
                <th>UserName</th>
                <th>UserRole</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="#request.userList" var="userInfo">
                <tr>
                    <td>
                        <a href='<%=path%>/phase4/viewUserRole?instkey=<s:property value="#userInfo.instkey" />'>
                            <s:property value="#userInfo.username"/>
                        </a></td>
                    <td><s:property value="#userInfo.role"/></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
    <div class="col-lg-3"></div>
</div>
</body>
</html>
