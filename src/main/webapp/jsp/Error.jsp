<%@ page language="java"  pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title> Error2</title>
    <meta http-equiv="refresh" content ="5;url=<%=basePath%>index.jsp">

</head>


<body>
<div class="row-fluid">
    <div class="col-lg-2">
    </div>
    <div class="col-lg-8">
        <h1>Error </h1>
        <h3>Some errors were encountered while trying to render this page. Please see the list below for details.</h3>
        <h4>What you did?</h4>
        <ul>
            <li>You may have typed the address(URL) incorrectly. Check it to make sure you've got the exact right spelling, capitalization, etc.</li>
            <li>You may have uploaded incorrect file. </li>
        </ul>

        <h4>What do I do now?</h4>
        The Web Server may be down, too busy, or experiencing other problems preventing it from responding to requests, You may wish to try again at a late time.
        <ul>
            <li>You can try refreshing the page, the problem may be temporary.</li>
            <li>To return to your previous page inquiry, press your browsers "back" button, please report to us if the error is still happen.</li>
            <li>Logout system, and re-login system and try again.</li>
            <li>Email us with details steps to reproduce, we can try to debug on that in order to fix the problem.(Chillh@lenovo.com)</li>
        </ul>

    </div>
    <div class="span2">
    </div>
</div>

</body>
</html>