<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Login Error</title>
    <meta http-equiv="refresh" content="5;url=<%=basePath%>index.jsp">

</head>

<body>
<script type="text/javascript">
    var i = 6;
    $(function () {
        bootbox.alert("Sign In first!");
        shownum();
    });
    function shownum() {
        i = i - 1;
        document.getElementById("time").innerHTML = i + "Seconds turn to Index.jsp";
        setTimeout('shownum()', 1000);
        if(i==0)
          window.location.href="<%=basePath%>index.jsp";

    }
</script>
<div id="time">adf</div>
</body>
</html>
