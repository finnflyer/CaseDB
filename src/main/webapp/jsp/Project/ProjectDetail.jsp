<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/9/7
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Project Detail</title>

</head>

<body>
<script src="<%=basePath%>js/StringForHtml.js"></script>
<!-- bootstrap-table.min.js -->
<script src="<%=basePath%>js/project/projectDetail.js" ></script>
<script type="text/javascript">
    function showEditProject(){
        var comments = '<s:property value="%{projectBean.comments}"/>';
        document.getElementById("projectName").value='<s:property value="%{projectBean.projectName}"/>';
        if(comments.indexOf("&lt;br&gt;") != -1 ){
            comments = comments.replace("&lt;br&gt;","\r\n");
            document.getElementById("projectComments").value= comments;
        }

        console.info($("#projectComments").val());

        $("#editProjectModal").modal('show');
    }
    function NewIssue() {
        var url = "<%=basePath%>phase4/CreateIssue";
        window.location.href = url;
    }

    function NewTestPlan() {
        var key = $("#key").val();
        var url = "<%=basePath%>phase4/preTestPlan?projectInstkey=" + key;
        window.location.href = url;
    }
    function DownloadProjectIssue() {
        var key = $("#key").val();
        console.info(key);
        var url = "<%=basePath%>phase4/DownloadExcelFile?projectKey=" + key;
        window.location.href = url;
    }
    function DeleteProject() {
        var key = $("#key").val();
        bootbox.confirm("Are you sure you are going to Delete this Project?", function (result) {
            if (result) {
                var url = "<%=basePath%>phase4/DeleteProject?projectKey=" + key;
                window.location.href = url;
            }

        })
    }
</script>
<!-- Project Detail  -->
<s:hidden id="key" name="projectInstkey" value="%{projectBean.projectInstkey}"/>
<div class="col-lg-offset-1" class="btn-toolbar" role="toolbar">
    <div class="btn-group">
        <Button id="select" class="btn btn-warn" onclick="javascript:history.back(1);">Back</button>
    </div>
    <div class="btn-group">
        <h4>Project Name:
            <span><s:property value="projectBean.projectName"/></span></h4>
    </div>
    &nbsp; &nbsp; &nbsp; &nbsp;
    <div class="btn-group">
        <h4>Project Owner: <s:property value="projectBean.projectOwner"/></h4>
    </div>
</div>
<div class="col-lg-offset-8" class="btn-toolbar" role="toolbar">

    <s:if test="#session.user.role =='Leader' || #session.user.role =='Case Owner' || #session.user.role =='Admin' || #session.user.role =='Family Owner'">
        <div class=" btn-group">
        <button class="btn btn-danger" onclick="showEditProject();">Edit Project</button>
            </div>
        <div class=" btn-group">
            <button class="btn btn-danger" onclick="DeleteProject();">Delete Project</button>
        </div>
    </s:if>
</div>

<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <s:if test="#session.user.role =='Leader' || #session.user.role =='Case Owner' || #session.user.role =='Admin' || #session.user.role =='Family Owner'">
            <button class="btn btn-info" value="New Test Plan" onclick="NewTestPlan();">New Test Plan</button>
        </s:if>
        <button id="show" class="btn btn-info">Hide Test Plan Sheet</button>
    </div>
    <div class="col-lg-1">
    </div>
</div>
<br>

<div class="row" id="testPlan">
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
            <table class="table table table-bordered table-striped" style="table-layout:fixed">
                <thead>
                <tr>
                    <th class="info" width="40%">Test Plan Name</th>
                    <th class="info" width="10%">Owner Name</th>
                    <th class="info" width="30%"> Comments</th>
                    <th class="info">Create Time</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#request.pageBean.list" var="tcinfo">

                    <tr>
                        <td style="width:60%;color:darkblue;"><a
                                href='<%=path%>/phase4/ViewTestPlan?testPlanInstkey=<s:property value="#tcinfo.testplaninstkey" />'>
                            <s:property value="#tcinfo.testplanname"/>
                        </a>
                        </td>
                        <td><s:property value="#tcinfo.testplanowner"/></td>
                        <td><s:property value="#tcinfo.testplancomments"/></td>
                        <td><s:date name="#tcinfo.createdate" format="yyyy-MM-dd"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
            <div class="col-lg-1">
            </div>
        </s:if>
        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
        </s:elseif>
    </div>
    <div class="col-lg-1">
    </div>
</div>
<!-- Issue -->

<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <button class="btn btn-info" value="New Issue" onclick="NewIssue();">New Defect</button>
<s:if test="#request.issuePageBean.list.size() > 0">
        <button class="btn btn-primary" onclick="DownloadProjectIssue();">Export Excel</button>
    </s:if>
        <%--<button class="btn btn-primary" onclick="ChangeStatus();">Change Status</button>--%>
        <br>
        <br>
<s:if test="#request.issuePageBean.list.size() > 0">
        <%--<div>--%>
            <%--<label><input name="Fruit" type="checkbox" value=""/>CheckBox </label>--%>
            <%--<label><input name="Fruit" type="checkbox" value=""/>Open </label>--%>
            <%--<label><input name="Fruit" type="checkbox" value="Closed"/>Closed </label>--%>
            <%--<label><input name="Fruit" type="checkbox" value=""/>ECR </label>--%>
        <%--</div>--%>
    </s:if>
    </div>

</div>

<br>

<div clas="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
<s:if test="#request.issuePageBean.list.size() >= 0">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">
                Open Issue</a></li>
            <li><a href="#Limitation" data-toggle="tab">Limitation</a></li>
            <li><a href="#ios" data-toggle="tab">All Issue</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <div class="row" id="Issue">
                    <div class="col-lg-12">
                        <s:if test="#request.issuePageBean.list.size() >= 0">
                            <div>
                                <div>
                                    <form id="IssueForm" method="post" style="margin: 0px;">
                                    </form>
                                </div>
                            </div>
                            <div>
                                <table class="table table table-bordered table-striped" style="table-layout:fixed">
                                    <thead>
                                    <tr>
                                        <th class="info" width="10%">ECR</th>
                                        <th class="info" width="6%">Priority</th>
                                        <th class="info" width="30%">Abstract</th>
                                        <th class="info" width="10%">OS</th>
                                        <th class="info" width="10%">Language</th>
                                        <th class="info" width="6%" >Status</th>
                                        <th class="info">Create Date</th>
                                        <th class="info">Comments</th>
                                            <%--<th class="info" width="5%">Site</th>--%>

                                            <%--<th class="info">Creator</th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="#request.issuePageBean.list" var="issue">
                                        <tr>
                                            <td><s:property value="#issue.ecrNumber"/></td>
                                            <td style="text-align:center"><s:property value="#issue.priority"/></td>
                                            <td>
                                                <a href='<%=path%>/phase4/ShowIssueDetail?issueKey=<s:property value="#issue.instkey" />'>
                                                    <s:property value="#issue.issueName"/></a>
                                            </td>
                                            <td sytle="span-size:1px"><s:property value="#issue.osCato"/></td>
                                            <td><s:property value="#issue.language"/></td>
                                                <%--<td><s:select list="{'Open','Close','Limiataion','Cancel','WAD'}"--%>
                                                <%--value="%{#issue.issueStatus}"/></td>--%>
                                            <td><s:property value="#issue.issueStatus" /></td>
                                            <td><s:property value="#issue.initalDate" /></td>
                                            <td>
                                                <s:if test="#issue.comments !=null"><Font color="blue">Creator:<s:property value="#issue.createBy" /></Font><br><s:property value="#issue.comments"/>
                                                </s:if>
                                                <button id="moreComments" style="span-size:3px" class="btn btn-sm"
                                                        onclick="javascript:void(0);
                                                                moreComment('<s:property value="#issue.instkey"/> ')">more
                                                </button>
                                            </td>
                                                <%--<td><s:property value="#issue.testSiteCato" /></td>--%>
                                                <%--<td><s:date name="#issue.createdate" format="yyyy-MM-dd" ></s:date></td>--%>
                                                <%--<td><s:property value="#issue.owner" /></td>--%>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>


                            <%--<div class="row">--%>
                                <%--<div class="col-lg-8">--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-4">--%>
                                    <%--Page<span style="span-weight:bolder;"> <s:property--%>
                                        <%--value="pageBean.currentPage"/></span> of <span--%>
                                        <%--style="span-weight:bolder;"><s:property value="pageBean.totalPage"/></span>--%>

                                    <%--<s:if test="%{pageBean.currentPage != 1}">--%>
                                        <%--<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=1"--%>
                                                <%-->First Page</a>--%>
                                    <%--</s:if>--%>
                                    <%--<s:else>--%>
                                        <%--<span class="link_disable">First Page</span>--%>
                                    <%--</s:else>--%>

                                    <%--<s:if test="%{pageBean.currentPage > 1}">--%>
                                        <%--<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage-1}'/>"--%>
                                                <%-->Previous</a>--%>
                                    <%--</s:if>--%>
                                    <%--<s:else>--%>
                                        <%--<span class="link_disable">Previous</span>--%>
                                    <%--</s:else>--%>

                                    <%--<s:if test="%{pageBean.currentPage < pageBean.totalPage}">--%>
                                        <%--<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage+1}'/>"--%>
                                                <%-->Next</a>--%>
                                    <%--</s:if>--%>
                                    <%--<s:else>--%>
                                        <%--<span class="link_disable">Next</span>--%>
                                    <%--</s:else>--%>

                                    <%--<s:if test="%{pageBean.currentPage != pageBean.totalPage}">--%>
                                        <%--<a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.totalPage}'/>"--%>
                                                <%-->Last Page</a>--%>
                                    <%--</s:if>--%>
                                    <%--<s:else>--%>
                                        <%--<span class="link_disable">Last Page</span>--%>
                                    <%--</s:else>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="col-lg-1">
                            </div>
                        </s:if>
                        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
                        </s:elseif>
                    </div>

                </div>

            </div>
            <div class="tab-pane fade" id="Limitation">
                <div class="row" id="LimitationRow">
                    <div class="col-lg-12">
                        <s:if test="#request.limistationPageBean.list.size() > 0">
                            <div>
                                <div>
                                    <form id="LimitationForm" method="post" style="margin: 0px;">
                                    </form>
                                </div>
                            </div>
                            <div>
                                <table class="table table table-bordered table-striped" style="table-layout:fixed">
                                    <thead>
                                    <tr>
                                        <th class="info" width="10%">ECR</th>
                                        <th class="info" width="6%">Priority</th>
                                        <th class="info" width="30%">Abstract</th>
                                        <th class="info" width="10%">OS</th>
                                        <th class="info" width="10%">Language</th>
                                        <th class="info" >Status</th>
                                        <th class="info">Create Date</th>
                                        <th class="info" >Comments</th>
                                            <%--<th class="info" width="5%">Site</th>--%>

                                            <%--<th class="info">Creator</th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="#request.limistationPageBean.list" var="issue">
                                        <tr>
                                            <td><s:property value="#issue.ecrNumber"/></td>
                                            <td style="text-align:center"><s:property value="#issue.priority"/></td>
                                            <td>
                                                <a href='<%=path%>/phase4/ShowIssueDetail?issueKey=<s:property value="#issue.instkey" />'>
                                                    <s:property value="#issue.issueName"/></a>
                                            </td>
                                            <td sytle="span-size:1px"><s:property value="#issue.osCato"/></td>
                                            <td><s:property value="#issue.language"/></td>
                                                <%--<td><s:select list="{'Open','Close','Limiataion','Cancel','WAD'}"--%>
                                                <%--value="%{#issue.issueStatus}"/></td>--%>
                                            <td><s:property value="#issue.issueStatus" /></td>
                                            <td><s:property value="#issue.initalDate" /></td>
                                            <td>
                                                <s:if test="#issue.comments !=null"><Font color="blue">Creator:<s:property value="#issue.createBy" /></Font><br><s:property value="#issue.comments"/>
                                                </s:if>
                                                <button id="moreComments" style="span-size:3px" class="btn btn-sm"
                                                        onclick="javascript:void(0);
                                                                moreComment('<s:property value="#issue.instkey"/> ')">more
                                                </button>
                                            </td>
                                                <%--<td><s:property value="#issue.testSiteCato" /></td>--%>

                                                <%--<td><s:date name="#issue.createdate" format="yyyy-MM-dd" ></s:date></td>--%>
                                                <%--<td><s:property value="#issue.owner" /></td>--%>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>



                            <div class="col-lg-1">
                            </div>
                        </s:if>
                        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
                            No Limitations
                        </s:elseif>
                    </div>

                </div>
            </div>
            <div class="tab-pane fade" id="ios">
                <div class="row" id="AllIssue">
                    <div class="col-lg-12">
                        <s:if test="#request.allIssuePageBean.list.size() > 0">
                            <div>
                                <div>
                                    <form id="AllIssueForm" method="post" style="margin: 0px;">
                                    </form>
                                </div>
                            </div>
                            <div>
                                <table class="table table table-bordered table-striped" style="table-layout:fixed">
                                    <thead>
                                    <tr>
                                        <th class="info" width="10%">ECR</th>
                                        <th class="info" width="6%">Priority</th>
                                        <th class="info" width="30%">Abstract</th>
                                        <th class="info" width="10%">OS</th>
                                        <th class="info" width="10%">Language</th>
                                        <th class="info" >Status</th>
                                        <th class="info">Create Date</th>
                                        <th class="info" >Comments</th>
                                            <%--<th class="info" width="5%">Site</th>--%>

                                            <%--<th class="info">Creator</th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="#request.allIssuePageBean.list" var="issue">
                                        <tr>
                                            <td><s:property value="#issue.ecrNumber"/></td>
                                            <td style="text-align:center"><s:property value="#issue.priority"/></td>
                                            <td>
                                                <a href='<%=path%>/phase4/ShowIssueDetail?issueKey=<s:property value="#issue.instkey" />'>
                                                    <s:property value="#issue.issueName"/></a>
                                            </td>
                                            <td sytle="span-size:1px"><s:property value="#issue.osCato"/></td>
                                            <td><s:property value="#issue.language"/></td>
                                                <%--<td><s:select list="{'Open','Close','Limiataion','Cancel','WAD'}"--%>
                                                <%--value="%{#issue.issueStatus}"/></td>--%>
                                            <td><s:property value="#issue.issueStatus" /></td>
                                            <td><s:property value="#issue.initalDate" /></td>
                                            <td>
                                                <s:if test="#issue.comments !=null"><Font color="blue">Creator:<s:property value="#issue.createBy" /></Font><br><s:property value="#issue.comments"/>
                                            </s:if>
                                                <button id="moreComments" style="span-size:3px" class="btn btn-sm"
                                                        onclick="javascript:void(0);
                                                                moreComment('<s:property value="#issue.instkey"/> ')">more
                                                </button>
                                            </td>
                                                <%--<td><s:property value="#issue.testSiteCato" /></td>--%>
                                                <%--<td><s:date name="#issue.createdate" format="yyyy-MM-dd" ></s:date></td>--%>
                                                <%--<td><s:property value="#issue.owner" /></td>--%>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>



                            <div class="col-lg-1">
                            </div>
                        </s:if>
                        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
                        </s:elseif>
                    </div>

                </div>
            </div>

        </div>
  </s:if>
    </div>


    <div class="col-lg-1">
    </div>
</div>
</div>


<s:include value="/jsp/Issue/IssueCommentsWindows.jsp"/>
<s:include value="/jsp/Issue/CreateComments.jsp"/>
<s:include value="/jsp/Project/EditProject.jsp"/>
</body>
</html>
