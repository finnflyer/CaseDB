<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Test Tools</title>

  </head>

	
  <body>
    <script type="text/javascript">
   		function UploadTool(){
   			 var url ="<%=basePath%>phase4/preSaveFile";
   		    window.location.href=url;
   		}
   </script>
    	<div class="row">
		<div class="col-lg-3">
		</div>
		<div class="col-lg-8">
		<div class="row">
		<div class="col-lg-1">
		</div>
			<br>
		<br>
		<div class="col-offset-lg-8">
	
		<s:form action="phase4/SearchToolForPage"  cssClass=" form-inline"  theme="simple" >
            <div class="form-group">
           	 <span style="font-size:11pt;font-weight:bold">Tools Name  </span>&nbsp;&nbsp;&nbsp;
              <input type="text" class="form-control" name="toolName" placeholder="Tool Name">
            </div>
            &nbsp;&nbsp;    &nbsp;&nbsp;    &nbsp;&nbsp;
           	 <s:hidden name="searchflag" value="1" />
           	  
            <s:submit cssClass="btn btn-primary" value="Search"></s:submit>   &nbsp;&nbsp;
            <s:if test="#session.userInfo.userName != 'tester'">
            <s:if test="#session.userInfo.Role =='Case Owner' || #session.userInfo.Role =='Admin' ||#session.userInfo.Role =='PA Team Lead'">
           		 <button id="Upload" class="btn btn-warn" type="button" onclick="UploadTool()";>Upload Tool
	      		</button>
	      	</s:if>
	      	</s:if>
          </s:form>
      
          		</div>
          		</div>
		</div>
	
       <div class="col-lg-2">
		
	    </div>

</div>
</div>
	<br>
	<br>
	<div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">
	    <s:if test="#request.pageBean.list.size() > 0">
	    <div >
           <div >
	    	<form id="TCForm" method="post" style="margin: 0px;">
	    	</form>
	    </div>
	    </div>
	 	<s:include value="/jsp/TestTools/TestToolView.jsp"></s:include>
	 		<div class="col-lg-1">
		</div>
 	</s:if>
 	<s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
 		<h3>There is no result found!</h3>
 	</s:elseif>
		</div>
	</div>
  </body>
</html>
