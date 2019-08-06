<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>">

<div>
    <table class="table table table-bordered table-striped" style="table-layout:fixed;">
        <thead>
        <tr>

            <th class="info" width="25%">Tool Name</th>
            <th class="info" width="8%">Download</th>
            <th class="info" width="40%">Description</th>
            <th class="info">Owner</th>
            <th class="info">Upload Time</th>

        </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.pageBean.list" var="toolInfo">
            <tr>

                <td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a
                        href='<%=path%>/phase4/ViewTool?toolKey=<s:property value="#toolInfo.instkey" />'>
                    <s:property value="#toolInfo.toolname"/>
                </a>
                </td>
                <td><a href='<%=path%>/phase4/DownloadFile?toolKey=<s:property value="#toolInfo.instkey" />'>
                    <img src="image/download2.jpg" class="img-thumbnail" style="height:30px"></img></a></td>
                <td><s:property value="#toolInfo.description"/></td>
                <td><s:property value="#toolInfo.owner"/></td>
                <td><s:date name="#toolInfo.uploadtime" format="yyyy-MM-dd"/> </td>

            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
	
	
