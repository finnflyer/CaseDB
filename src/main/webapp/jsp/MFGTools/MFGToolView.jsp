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

            <th class="info" width="25%">Debug Tool Name</th>
            <th class="info" width="8%">Download</th>
            <th class="info" width="40%">Description</th>
            <th class="info">Owner</th>
            <th class="info">Upload Time</th>

        </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.pageBean.list" var="mfgToolInfo">
            <tr>

                <td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a
                        href='<%=path%>/phase4/ViewMFGTool?toolKey=<s:property value="#mfgToolInfo.instkey" />'>
                    <s:property value="#mfgToolInfo.toolname"/>
                </a>
                </td>
                <td><a href='<%=path%>/phase4/DownloadFile?toolKey=<s:property value="#mfgToolInfo.instkey" />'>
                    <img src="image/downloadimage.jpg" class="img-thumbnail" style="height:30px"></img></a></td>
                <td><s:property value="#mfgToolInfo.description"/></td>
                <td><s:property value="#mfgToolInfo.owner"/></td>
                <td><s:date name="#mfgToolInfo.uploadtime" format="yyyy-MM-dd"/> </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
	
	
