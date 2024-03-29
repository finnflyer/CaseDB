<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base id="base" href="<%=basePath%>">
    <title>TestCase</title>
</head>

<body>

<script type="text/javascript">
    $(function () {
        $("#Home").removeClass("active");
        $("#TestCase").addClass("active");
    });
    function NewCase() {
        var url = "<%=basePath%>phase4/CreateTestCase";
        window.location.href = url;
    }
    function AdvanceCase() {
        var url = "<%=basePath%>phase4/preTestPlan";
        window.location.href = url;
    }
</script>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9">
        <div class="row">
            <div class="col-lg-1">
            </div>
            <div class="col-offset-lg-11">
                <s:form action="SearchCaseForPage" namespace="/phase4" cssClass=" form-inline" theme="simple">
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Case Name  </span>&nbsp;
                        <input type="text" class="form-control" name="caseName" placeholder="Case Name">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Case Owner</span>&nbsp;
                        <input type="text" class="form-control" name="caseOwner" placeholder="Case Owner">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Case ID</span>&nbsp;
                        <input type="text" class="form-control" name="caseId" placeholder="Case Id">
                    </div>
                    &nbsp; &nbsp; &nbsp;
                    <s:submit cssClass="btn btn-primary" value="Search"></s:submit>

                </s:form>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-9"></div>
            <div class="col-lg-3">
            <s:if test="#session.user.username != 'tester'">
                <s:if test="#session.user.role =='Case Owner' || #session.user.role =='Admin' ||#session.user.role =='PA Team Lead'">
                    <button id="newCase" class="btn btn-warn" type="button" onclick="NewCase()" ;>New Test Case
                    </button>
                </s:if>
            </s:if>
            <a id="Advance" onclick="AdvanceCase()" ;>
                <ins style="font-Style:italic;color:black">Advanced</ins>
            </a>
            </div>
        </div>
    </div>

    <div class="col-lg-2">

    </div>

</div>
<br>
<br>

<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <s:if test="#request.pageBean.list.size() > 0">
            <div>
                <div>
                    <form id="TCForm" method="post" style="margin: 0px;">
                    </form>
                </div>
            </div>
            <s:include value="/jsp/TestCases/TCView.jsp"></s:include>
            <div class="col-lg-1">
            </div>
        </s:if>
        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
            <h3>There is no result found!</h3>
        </s:elseif>
    </div>
</div>

</body>
</html>