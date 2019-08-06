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
    <title>Project</title>


    <style type="text/css">
        body {
            padding-bottom: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>

</head>

<body>
<script type="text/javascript">
    function NewTestPlan() {
        $("#createModal").modal("show");
    }
    $(function() {
        $("#Home").removeClass("active");
        $("#Project").addClass("active");
    });
</script>

<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9">
        <div class="row">
            <div class="col-lg-1">
            </div>
            <div class="col-offset-lg-4">
                <s:form action="phase4/SearchProject" cssClass=" form-inline" theme="simple">
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Project Name  </span>&nbsp;&nbsp;&nbsp;
                        <input type="text" class="form-control" name="projectName" placeholder="Project Name">
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Project Owner</span>&nbsp;&nbsp;&nbsp;
                        <input type="text" class="form-control" name="projectOwner" placeholder="Project Owner">
                    </div>
                    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                    <s:hidden name="searchflag" value="1"/>

                    <s:submit cssClass="btn btn-primary" value="Search"></s:submit>
                    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                    <s:if test="#session.user.role =='Leader' || #session.user.role =='Case Owner' || #session.user.role =='Admin' || #session.user.role =='Family Owner'">
                        <a class="btn btn-primary " id="newProject" onclick="NewTestPlan()" ;>
                            New Project
                        </a>
                    </s:if>
                </s:form>

            </div>
        </div>
    </div>

    <div class="col-lg-2">

    </div>

</div>
</div>
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
            <s:include value="/jsp/Project/ProjectView.jsp"></s:include>
            <div class="col-lg-1">
            </div>
        </s:if>
        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
            <h3>There is no result found!</h3>
        </s:elseif>
    </div>
</div>
<s:include value="CreateProject.jsp"></s:include>
</body>
</html>