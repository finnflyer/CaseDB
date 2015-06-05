<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>AirTest Login</title>	
		<link rel="stylesheet" href="<%=basePath %>css/loginstyles.css" type="text/css" media="screen" />	
    	<style type="text/css">
		img, div { behavior: url(iepngfix.htc) }
		</style>
      </head>
	<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
							<a href=""><img src="img/Logo.png" height="76px" alt="Case DB" ></a>
						<div id="darkbanner" class="banner320">
							<h2>Register User</h2>
						</div>
						<div id="darkbannerwrap">
						</div>
						<form name="form1" method="post" action="<%=basePath %>user/regsiter">
						<fieldset class="form">
						   <p>
								<label for="user_name">Username:</label>
								<input name="userName" id="userName" type="text" value="">
							</p>
							<p>
								<label for="password">Password:</label>
								<input name="password" id="user_password" type="password">
							</p>
							<p>
								<label for="password">confirm Password</label>
								<input name ="password1" id="user_password1" type="password">
								</p>
							<button type="submit" class="positive" name="Submit">
								Register</button>
								
							 <ul id="forgottenpassword">
								<li class="boldtext">|</li>
								<li><a href="<%=basePath%>index.jsp">Log In</a></li>
							</ul>
								
							</fieldset>
						
						
					</form></div>
				</div>   

<div id="wrapperbottom_branding"><div id="wrapperbottom_branding_text"><a href="" style="text-decoration:none">Powered By CDL Automation Team</a>.</div></div></body></html>