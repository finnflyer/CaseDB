<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>CaseDB</title>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/indexCss.css" rel="stylesheet">


</head>
<body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery-1.11.3.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/bootbox.min.js"></script>

<script type="text/javascript">


    $(function () {
        $("#logOutBtn").click(function () {
            console.info("click log out btn ");
            $.ajax({

                type: "post",
                url: '<%=basePath%>/User/logOut',
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
        $("#loginModal").on("shown.bs.modal", function(event){
            console.info($("#imgVcode"));
            console.info(document.getElementById('imgVcode').src);
            changeValidateCode(document.getElementById('imgVcode'));
        });
    });
    function changeValidateCode(obj) {
        //获取当前的时间作为参数，无具体意义
        var timenow = new Date().getTime();
        obj.src="<%=basePath%>/phase4/CreateImgCode?d="+timenow;
    }
</script>
<nav class="navbar  navbar-custom navbar-fixed-top " style="height:40px">
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
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <s:if test="#session.user.username!=null">
                    <li><a href="#"><p>Welcome：<s:property value="#session.user.username"/></p></a></li>
                    <li>
                        <button id="logOutBtn" class="btn btn-info">Log Out</button>
                    </li>
                </s:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="<%=basePath%>index.jsp">Home<span class="sr-only">(current)</span></a></li>
                <%--<li><a href="<%=basePath%>/jsp/Test.jsp">Test</a></li>--%>
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
                        <li id="IssueHome"><a href="<%=basePath%>jsp/Issue/IssueHome.jsp">Issue</a></li>
                        <li id="abbreviation"><a href="phase4/AbbreviationHome">Abbreviation</a></li>
                    </ul>
                </li>
                <s:if test="#session.user.username!=null">
                    <li><a href="phase4/UserHome">UserInfo</a></li>
                    <s:if test="#session.user.role=='Admin'">
                        <li id="Role"><a href="phase4/RoleHome">Role</a></li>
                    </s:if>
                </s:if><s:else>
                <li>
                    <button id="login" class="btn btn-info navbar-btn" data-toggle="modal" data-target="#loginModal">Log
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
    <div class="row">
        <div class="col-lg-9"><img src="<%=basePath%>image/6.png"></div>
        <div class="col-lg-3"><img src="<%=basePath%>image/8.png">
        </div>
    </div>
    <br>
    <br>
</div>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="image/4.png" onclick="javascript:window.location.href='phase4/TestToolHome';">

            <div class="option" Style="">
                <%--<button class="btn btn-info">Go</button>--%>
            </div>
        </div>
        <div class="item">
            <img src="image/2.png" onclick="javascript:window.location.href='phase4/ProjectHome';">

            <div class="option" Style="">

            </div>

        </div>
        <div class="item">
            <img src="image/3.png" onclick="javascript:window.location.href='phase4/TestCaseHome';">


            <div class="option" Style="">
                <%--<button class="btn btn-info ">Go</button>--%>
            </div>

        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">上一页</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">下一页</span>
    </a>
</div>
<jsp:include page="jsp/user/LogIn.jsp"/>

<jsp:include page="jsp/user/reg.jsp"></jsp:include>


<div class="container">
    <footer class="footer">
        <p style="text-align:center;">
            Design by CDL Preload PA, Admin: Chillhuang@Lenovo.com (Chill Huang), DB Owner: Raylei@Lenovo.com (Raymond M
            Lei)
        </p>
    </footer>
</div>

</body>