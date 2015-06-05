<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base id="base" href="<%=basePath%>">
    <title>User Role</title>
			<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>css/style.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/scripts.js"></script>

  </head>

<body >
<script type="text/javascript">
   		function SearchCase(){
   			var url ="<%=basePath%>phase4/SearchTestCase";
   		    window.location.href=url;
   		}
   </script>
   <ol class="breadcrumb">
  <li><a href="<%=basePath%>phase4/Home">Home</a></li>
  <li><a href="<%=basePath%>jsp/user/RoleHome.jsp">RoleManagement</a></li>
</ol>
	 <div class="container-fluid">
	<div class="row">
		<div class="col-lg-3">
		</div>
		<div class="col-lg-5">
		<s:form action="user/SearchRole"  cssClass=" form-inline"  theme="simple" >
            <div class="form-group">
           	 <span>User Name</span>
              <input type="text" class="form-control" name="userName" placeholder="User Name">
            </div> &nbsp;&nbsp;
            <div class="form-group">
            	<span>Role</span>
              <input type="text" class="form-control" name="userRole" placeholder="Role ">
            </div>
            &nbsp;&nbsp;    &nbsp;&nbsp;    &nbsp;&nbsp;
           	 <s:hidden name="searchflag" value="1" />
            <s:submit cssClass="btn btn-primary" value="Search"></s:submit>
          </s:form>
		</div>
		<div class = "col-lg-4">
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
	 	<s:include value="/jsp/user/UserView.jsp"></s:include>
	 		<div class="col-lg-1">
		</div>
 	</s:if>
 	<s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
 		<h3>There is no result found!</h3>
 	</s:elseif>
		</div>
	</div>
</div>
</body>
</html>