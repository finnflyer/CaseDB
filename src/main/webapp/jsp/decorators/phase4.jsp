<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML >
<html>
<head>

    <title><decorator:title/></title>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/indexCss.css" rel="stylesheet">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/jquery-1.11.3.js"></script>

    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/bootbox.min.js"></script>

    <%--<script src="<%=basePath%>js/StringForHtml.js"></script>--%>
    <script type="text/javascript">
        $(function () {
            $("#logOutBtn").click(function () {
                console.info("click log out btn ");
                $.ajax({
                    type: "post",
                    url: '<%=basePath%>User/logOut',
                    data: {//设置数据源
                    },
                    dataType: "json",//设置需要返回的数据类型
                    success: function (dataMap) {
                        console.info("log out");
                        console.info(dataMap.responseText);
                        bootbox.alert("Log Out");
                        window.location.href = "index.jsp";
                    }
                });
            });
        });
    </script>

</head>

<body>
<nav class="navbar  navbar-custom navbar-fixed-top " style="height:30px">
    <div id="title" class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <%--style="font-size:3px"--%>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="height:30px">
            <ul class="nav navbar-nav navbar-left">
                <s:if test="#session.user.username!=null">
                    <li><a href="#"><p>Welcome：<s:property value="#session.user.username"/></p></a></li>
                    <li>
                        <button id="logOutBtn" class="btn btn-info navbar-btn">Log Out</button>
                    </li>
                </s:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li id="Home" class="active"><a href="<%=basePath%>index.jsp">Home <span
                        class="sr-only">(current)</span></a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        Preload <i class="caret"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="phase4/TestCaseHome">TestCase</a></li>
                        <li><a href="phase4/ProjectHome">Projects</a>
                        <li><a href="phase4/MFGToolHome">Debug</a></li>
                    </ul>
                </li>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">Tools <i class="caret"></i></a>
                    <ul class="dropdown-menu">
                        <li id="Debug"><a href="phase4/MFGToolHome">Debug Tools</a></li>
                        <li id="Tool"><a href="phase4/TestToolHome">Test Tools</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">More <i class="caret"></i></a>
                    <ul class="dropdown-menu">
                        <li id="OverTime"><a href="phase4/OverTimeHome">OT</a></li>
                        <li id="abbreviation"><a href="phase4/AbbreviationHome">Abbreviation</a></li>
                    </ul>
                </li>
                <s:if test="#session.user.username!=null">
                    <li id="User"><a href="phase4/UserHome">UserInfo</a></li>
                    <s:if test="#session.user.role=='Admin'">
                        <li id="Role"><a href="phase4/RoleHome">Role</a></li>
                    </s:if>
                </s:if><s:else>
                <li>
                    <button id="login" class="btn btn-info navbar-btn" style="font-size:3px" data-toggle="modal"
                            data-target="#loginModal">Log
                        In
                    </button>
                </li>
            </s:else>
                <li></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->

    </div>
    <!-- /.container-fluid -->

</nav>


<div class="container-fluid">
    <jsp:include page="/jsp/user/LogIn.jsp"></jsp:include>
    <jsp:include page="/jsp/user/reg.jsp"></jsp:include>
    <%--<s:include value="<%=basePath%>jsp/user/reg.jsp"></s:include>--%>
    <decorator:body/>
</div>
</div>

</body>
</html>
