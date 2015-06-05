<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset=utf-8 " />
<title>TimeOut</title>
</head>
<body>
	<script> 
var t=5;//设定跳转的时间 
setInterval("refer()",1000); //启动1秒定时 
function refer(){ 
if(t==0){ 
location="<%=basePath%>
	"; //#设定跳转的链接地址 
		}
		document.getElementById('show').innerHTML = "LogIn Needed" + t
				+ "seconds to login page"; // 显示倒计时 
		t--; // 计数器递减  
	}
</script>
	<span id="show"></span>
</body>
</html>
