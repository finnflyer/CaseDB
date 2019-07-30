<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/9/9
  Time: 15:04
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
    <title>Issue Detail</title>
</head>
<body>
<script src="<%=basePath%>js/StringForHtml.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("table tr td").each(function(){
            var txt = $(this).context.innerHTML;
            console.info($(this).context.innerHTML);

            if(txt!="" && txt!=undefined){
                txt = txt.replace(/(\r\n|\n|\r)/gm,"<br/>");
                $(this).context.innerHTML = txt;
            }
        });
    })
    function EditIssue() {
        var key = "<s:property value='issueBean.instkey' />";
        var url = "<%=basePath%>phase4/EditIssue?issueKey=" + key;
        window.location.href = url;
    }
    function DeleteIssue() {
        console.info("start to delete");
        var key = "<s:property value='issueBean.instkey' />";
        console.info(key);
        bootbox.confirm("Are you sure you are going to Delete this Issue?", function (result) {
            if (result) {
                var url = "<%=basePath%>phase4/DeleteIssue?issueKey=" + key;
                window.location.href = url;
            }

        })
    }
    function NewComments() {
        $("#commentsModal").modal("show");
    }
    function NewAttachment() {
        $("#attachedModal").modal("show");
    }
</script>
<div class="row">
    <div class="col-lg-1"></div>
    <div class="col-lg-10"><Button id="select" class="btn-info" onclick="javascript:history.back(1);" >back</button></div>
    <div class="col-lg-1"></div>
</div>
<div class="col-lg-offset-10 " class="btn-toolbar" role="toolbar">
    <%--<s:if test="#session.user.username !='tester' ">--%>
        <s:if test="#session.user.role =='Leader' || #session.user.role =='Case Owner' || #session.user.role =='Admin' || #session.user.role =='Family Owner'">
            <div class="btn-group">
                <button id="EditIssue" class="btn btn-warn" type="button" onclick="EditIssue()" ;>Edit Issue
                </button>
            </div>
            <div class="btn-group">
                <button id="DeleteIssue" class="btn btn-warn" type="button" onclick="DeleteIssue();">Delete Issue
                </button>
            </div>
        </s:if>
    <%--</s:if>--%>
</div>

<div class="row-fluid">
    <div class="col-lg-12" style="padding-left:60px;padding-right:60px">
        <legend style="font-size:16px">Issue Information:</legend>
        <table class="table table table-bordered ">

            <tr>
                <td class="active" width="130px">Abstract:</td>
                <td class="form-group" colspan="3"><s:property value="issueBean.issueName"/></td>
            </tr>
            <tr>
                <td class="active">Priority</td>
                <td><s:property value="issueBean.priority"/></td>
            </tr>
            <tr>

                <td class="active">ECR Number</td>
                <td><s:property value="issueBean.ecrNumber"/></td>
            </tr>
            <tr>
                <td class="active">Language:</td>
                <td><s:property value="issueBean.language"/></td>
            </tr>
            <tr>
                <td class="active">Operating System:</td>
                <td><s:property value="issueBean.osCato"/></td>
            </tr>
            <tr>
                <td class="active" width="130px">Test Site:</td>
                <td><s:property value="issueBean.testSiteCato"/></td>

            </tr>
            <tr>
                <td class="active">Component Owner:</td>
                <td><s:property value="issueBean.component"/></td>
            </tr>
            <tr>
                <td class="active">Issue Style:</td>
                <td><s:property value="issueBean.issuestyle"/></td>
            </tr>
            <tr>
                <td class="active">Phase Found:</td>
                <td><s:property value="issueBean.phaseCato"/></td>
            </tr>
            <tr>
                <td class="active">Reproduce Step</td>
                <td><s:property value="issueBean.reproduceStep"/></td>
            </tr>
            <tr>
                <td class="active">Configuration:</td>
                <td><s:property value="issueBean.configuration"/></td>
            </tr>
            <tr>
                <td class="active">Platform:</td>
                <td><s:property value="issueBean.platform"/></td>

            </tr>

            <tr>
                <td class="active">Creator:</td>
                <td><s:property value="issueBean.owner"/></td>

            </tr>
            <tr>

                <td class="active">Open Date:</td>
                <td><s:property value="issueBean.createdate"/></td>
            </tr>
            <tr>
                <td width="14%" class="active">PA Test Case Number:</td>
                <td><s:property value="issueBean.caseNum"/></td>
            </tr>

        </table>
        <legend style="font-size:16px">2 - Comments :
            <button class="btn btn-info" onclick="NewComments();">Create Comments</button>
        </legend>
        <s:if test="#request.commentsList.size()>0">
            <table class="table table table-bordered">
                <th>Owner</th>
                <th>CreateTime</th>
                <th>Comments</th>

                <tbody>
                <s:iterator value="#request.commentsList" status="st">
                    <tr>
                        <td width="10%"><s:property value="createBy"/></td>
                        <td width="10%"><s:date name="createDate" format="yyyy-MM-dd"/></td>

                    <td><s:property value="comments"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <h3><span>No Comments</span></h3>
        </s:else>
        <legend style="font-size:16px">3 - Attachments :
            <button class="btn btn-info" onclick="NewAttachment();">Add Attachment</button>
        </legend>
        <s:if test="#request.photoList.size()>0">
            <table class="table table table-bordered">
                <s:iterator value="#request.photoList">
                    <tr><a href='<%=path%>/phase4/DownloadIssueFile?issuePhotoKey=<s:property value="instkey"/>'>
                        <s:property value="issueFileName"/></a></tr>
                    <br/>
                </s:iterator>
            </table>
        </s:if>
        <s:else>
            <h3><span>No Attachment</span></h3>
        </s:else>
    </div>
    <div class="col-lg-12" style="padding-left:60px;padding-right:60px">
    </div>
</div>
<s:include value="CreateComments.jsp"/>
<s:include value="UploadFile.jsp"/>

</body>
</html>
