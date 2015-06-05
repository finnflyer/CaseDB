<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Edit User Role</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>css/style.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/scripts.js"></script>
	<style type="text/css">
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
            padding-bottom: 70px; 
          }
    </style>
  </head>
  
  <body>
    <div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">
			<s:form action="user/UpdateRole" enctype="multipart/form-data" method="post"  theme="simple">
				 <table class="table table-bordered">
				 	<tr>
            		<td>User Name</td>
            		<td>User Role</td>
            	</tr>
            	<tbody>
            		<td><s:property value="user.username" /></td>
            		<td>
            		<select name="role" id="role">
					 	 <option value=" "> </option>
					 	 <option value="Admin">Administrator</option>
					  	 <option value="Case Owner">Case Owner</option>
					  	 <option value="Leader">PA Team Lead</option>
					  	 <option value="Family Owner">Family Owner</option>
					  	 <option value="Tester">Tester</option>
					  	</select>
                  </td>
            	</tbody>
				 </table>
			<s:hidden name="instkey" value="%{instkey}"></s:hidden>
			<s:submit type="button" cssClass="btn btn-primary" value="Update"></s:submit>
			</s:form>
		</div>
		<div class="col-lg-1">
		</div>
   </div>
		
  </body>
</html>
