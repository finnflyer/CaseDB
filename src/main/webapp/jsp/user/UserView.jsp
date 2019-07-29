<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
    

     	<table class="table table table-bordered table-striped" >
    		<thead>
    		<tr>
    			<th class="info"  width="20%">User Name</th>
    			<th class="info" >Role </th>
        		</tr>
    		</thead>
    		<tbody>
    		<s:iterator value="#request.pageBean.list" var="userinfo">
    		<tr>
    			<td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a href='${pageContext.servletContext.contextPath}/user/EditUserRole?instkey=<s:property value="#userinfo.instkey" />' >    				
    					<s:property value="#userinfo.username" />
    			</a> 
    			</td> 
    			<td><s:property value="#userinfo.role" /></td>
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
			<a href="chilltest/getTCInfoView?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=1" onclick="displayBusyBox();">First Page</a>
		</s:if>
		<s:else>
		<font class="link_disable">First Page</font>
		</s:else>
		
		<s:if test="%{pageBean.currentPage > 1}">
			<a href="chilltest/getTCInfoView?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage-1}'/>" onclick="displayBusyBox();">Previous</a>
		</s:if>
		<s:else>
			<font class="link_disable">Previous</font>
		</s:else>

		<s:if test="%{pageBean.currentPage < pageBean.totalPage}">
			<a href="chilltest/getTCInfoView?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage+1}'/>" onclick="displayBusyBox();">Next</a>
		</s:if>
		<s:else>
			<font class="link_disable">Next</font>
		</s:else>
		
		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
			<a href="chilltest/getTCInfoView?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.totalPage}'/>" onclick="displayBusyBox();">Last Page</a>
		</s:if>
		<s:else>
			<font class="link_disable">Last Page</font>
		</s:else>
	</div>
    