<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML >
<html>
<head>
    <base href="<%=basePath%>">
    <title><decorator:title /></title>
    <decorator:head />
     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   
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
  <script type="text/javascript">

</script>

  <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid ">

        <div class="navbar-header ">
          <!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
          <a class="navbar-brand" href="<%=basePath%>phase4/Home">Case DataBase</a>
        </div>
        <!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
        <div id="navbar">
          <ul class="nav navbar-nav nav-pills">
            <li><a href="<%=basePath%>phase4/Home">Home</a></li>
            <li><a href="<%=basePath%>phase4/TestCaseHome">Test Case</a></li>
            <li><a href="<%=basePath%>phase4/preTestPlan">Test Plan</a></li>
        	
            <li><a href="<%=basePath%>jsp/user/RoleHome.jsp">Role Management</a>
            </li>
     
            <li><a href="<%=basePath %>index.jsp">Log out</a>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
 <div class="container-fluid">
				<decorator:body />
		</div>
</div>


  </body>
</html>
