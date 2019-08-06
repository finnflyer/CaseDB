<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
    

     	<table class="table table table-bordered table-striped" style="table-layout:fixed">
    		<thead>
    		<tr>
    			<th class="info" width="28%">Test Plan Name</th>
    			<th class="info"  width="10%">Owner Name</th>
    			<th class="info" width="30%"> Comments </th>
    			<th class="info">Create Time</th>
        		</tr>
    		</thead>
    		<tbody>
    		<s:iterator value="#request.pageBean.list" var="tcinfo">
    		
    		<tr>
    			<td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a href='<%=path%>/phase4/ViewTestPlan?testPlanInstkey=<s:property value="#tcinfo.testplaninstkey" />' >
    					<s:property value="#tcinfo.testplanname" />
    			</a> 
    			</td>
    			<td><s:property value="#tcinfo.testplanowner" /></td>
    			<td><s:property value="#tcinfo.testplancomments" /></td>
    			<td><s:property value="#tcinfo.createdate" /></td>
    		</tr>
    		</s:iterator>
    		</tbody>
    	</table>
    </div>
	
	
	
	<div class="row">
		<div class="col-lg-8">
		</div>
		<div class="col-lg-4">
		Page<font style="font-weight:bolder;"> <s:property value="pageBean.currentPage"/></font> of <font style="font-weight:bolder;"><s:property value="pageBean.totalPage"/></font>
	
		
		<s:if test="%{pageBean.currentPage != 1}">
			<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=1" onclick="displayBusyBox();">First Page</a>
		</s:if>
		<s:else>
		<font class="link_disable">First Page</font>
		</s:else>
		
		<s:if test="%{pageBean.currentPage > 1}">
			<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage-1}'/>" onclick="displayBusyBox();">Previous</a>
		</s:if>
		<s:else>
			<font class="link_disable">Previous</font>
		</s:else>

		<s:if test="%{pageBean.currentPage < pageBean.totalPage}">
			<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage+1}'/>" onclick="displayBusyBox();">Next</a>
		</s:if>
		<s:else>
			<font class="link_disable">Next</font>
		</s:else>
		
		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
			<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.totalPage}'/>" onclick="displayBusyBox();">Last Page</a>
		</s:if>
		<s:else>
			<font class="link_disable">Last Page</font>
		</s:else>
	</div>
    