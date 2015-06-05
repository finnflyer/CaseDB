<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>css/style.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jslib/Bootstrap/scripts.js"></script>
    <base href="<%=basePath%>">
  <title>Case DB Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<style type="text/css">
        body {
            padding-top: 15px; /* 60px to make the container go all the way to the bottom of the topbar */
            padding-bottom: 70px; 
          }
          .bgtest{
          background:url(img/Logo.png) no-repeat ;background-size:contain;  
 		 height:235px;
          }
    </style>
	</head>
<body>

<div class="container bgtest">
</div>
<br>
<div class="container">
<div class="row">
<div class="col-lg-10">
</div>
<div class="col-lg-2">
		<a class="btn btn-info" href="<%=basePath %>index.jsp" role="button">Log Out</a>
		</div>
</div>
</div>
<br>
<div class="container ">
	<div class="row">
		
		<div class="col-lg-3">
		
		<a href="phase4/TestCaseHome">
			<img src="img/testcase.png" class="img-thumbnail" style="width:250px"></img>
			</a>
			
			<br><br>
			<a href="<%=basePath%>phase4/TestCaseHome">
		<h3  class="text-center">
		Test Case</h3></a>
		</p>
		</div>
		<div class="col-lg-3">
				<a href="<%=basePath%>phase4/preTestPlan">
				<img src="img/testplan.jpg" class="img-thumbnail" style="width:250px;height:162px">
				</a>
				</img>
				<br><br>
	<h3  class="text-center"> 
		<a href="">Test Plan </a></h3>
		</div>
		<div class="col-lg-3">
				<img src="img/help.png" class="img-thumbnail" style="width:250px;height:162px"></img>
				<br><br>
	<h3  class="text-center"> 
		Help </h3>
		</div>
		<div class="col-lg-3">
				<img src="img/more.png" class="img-thumbnail" style="width:250px;height:162px"></img>
				<br><br>
	<h3  class="text-center"> 
		More</h3>
		</div>
		
		
		
	</div>

</div>
</div>

<br> <br><br><br><br>
<div class="container">
<footer class="footer" >
<p style="text-align:center">
Design by CDL Preload PA, Admin: Chillhuang@Lenovo.com (Chill Huang), DB Owner: Raylei@Lenovo.com (Raymond M Lei) 
</p>
</footer>
</div>


</body>
</html>
