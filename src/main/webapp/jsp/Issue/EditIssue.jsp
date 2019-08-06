<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Edit Issue</title>
</head>
<body>
<style type="text/css">
    .table-bordered {
        border-color: #FFFFFF;
    }

    .table-bordered > tbody > tr > td, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > td, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > thead > tr > th {
        border: 1px solid #FFFFFF;
    }

    .required {
        color: red;
        font-size: 100%;
    }


</style>
<script type="text/javascript">
    $(document).ready(
            function () {
                handleCheckBox();
            }
    );
    function handleCheckBox() {
        console.info("start");
        var phaseId = '<s:property value="issueBean.phaseFound"/>';
        var osId = '<s:property value= "issueBean.osid" />';
        console.info(osId);
        switch (phaseId.length) {
            case 1:
                $("#inputForm_phaseId-" + phaseId).attr("checked", "checked");
                break;
            case 2:
                var a = phaseId.substring(0, 1);
                var b = phaseId.substring(1, 2);
                $("#inputForm_phaseId-" + a).attr("checked", "checked");
                $("#inputForm_phaseId-" + b).attr("checked", "checked");
                break;
            case 3:
                var a = phaseId.substring(0, 1);
                var b = phaseId.substring(1, 2);
                var c = phaseId.substring(2, 3);
                $("#inputForm_phaseId-" + a).attr("checked", "checked");
                $("#inputForm_phaseId-" + b).attr("checked", "checked");
                $("#inputForm_phaseId-" + c).attr("checked", "checked");
                break;
            case 4:
                var a =phaseId.substring(0,1);
                var b = phaseId.substring(1,2);
                var c = phaseId.substring(2,3);
                var d = phaseId.substring(3,4);
                $("#inputForm_phaseId-" + a).attr("checked", "checked");
                $("#inputForm_phaseId-" + b).attr("checked", "checked");
                $("#inputForm_phaseId-" + c).attr("checked", "checked");
                $("#inputForm_phaseId-" + f).attr("checked", "checked");
                break;
            case 5:
                $("input[name='phaseId']").attr("checked", "checked");
        }
        switch (osId.length) {
            case 1:
                $("#inputForm_osId-" + osId).attr("checked", "checked");
                break;
            case 2:
                var a = osId.substring(0, 1);
                var b = osId.substring(1, 2);
                $("#inputForm_osId-" + a).attr("checked", "checked");
                $("#inputForm_osId-" + b).attr("checked", "checked");
                break;
            case 3:
                var a = osId.substring(0, 1);
                var b = osId.substring(1, 2);
                var c = osId.substring(2, 3);
                $("#inputForm_osId-" + a).attr("checked", "checked");
                $("#inputForm_osId-" + b).attr("checked", "checked");
                $("#inputForm_osId-" + c).attr("checked", "checked");
                break;
            case 4:
                $("input[name='osId']").attr("checked", "checked");
                break;
        }
    }
    function submitValidation() {
        console.info("start");
        var testSite = $('#siteId option:selected').val();
        console.info("site:" + testSite);
        if (testSite == " ") {
            bootbox.alert("Select one Site");
            return false;
        }
        var componentName = $('#componentName option:selected').val();
        console.info("site:"+testSite);
        if(componentName==" "){
            bootbox.alert("Select one component");
            return false;
        }
        var priority = $('#priority option:selected').val();
        console.info("site:" + priority);
        if (priority == " ") {
            bootbox.alert("Select one Priority");
            return false;
        }
        var Style = $('#StyleId option:selected').val();
        if(Style==" "){
            bootbox.alert("Select one Issue Style");
            return false;
        }
        var issueName = $("#issueName").val();
        var language = $("#language").val();
        if (issueName == "") {
            $("#issueName").closest('.form-group').addClass('has-error');
            return false;
        }
        if (language == "") {
            $("#language").closest('.form-group').addClass('has-error');
            return false;
        }
        var osid = "";
        $('input[name="osId"]:checked').each(function () {
            osid = osid + " " + $(this).val();
            ;
        });
        if (osid == "") {
            bootbox.alert("Select One OS");
            return false;
        }
        var phaseId = "";
        $('input[name="phaseId"]:checked').each(function () {
            phaseId = phaseId + " " + $(this).val();
            ;
        });
        if (phaseId == "") {
            bootbox.alert("Select phase");
            return false;
        }

    }
    function DeleteIssuePhoto(key) {
        bootbox.confirm("Are you sure you are going to Delete this Attachments?", function (result) {
            if (result) {
                var url = "<%=basePath%>phase4/DelIssueFile?issuePhotoKey=" + key;
                window.location.href = url;
            }

        })
    }
    function DeleteComments(key) {
        bootbox.confirm("Are you sure you are going to Delete this Comments?", function (result) {
            if (result) {
                var url = "<%=basePath%>phase4/DeleteComments?commentsKey=" + key;
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
    <div class="col-lg-10">
        <Button id="select" class="btn btn-warn" onclick="javascript:history.back(1);">Back</button>
    </div>
    <div class="col-lg-1"></div>
</div>

<div class="row-fluid">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <h4> Issue Info</h4>
        <h6>(The <span class="required">*</span> should not be null)</h6>
        <s:fielderror theme="bootstrap"/>
        <table class="table table-bordered ">
            <s:form id="inputForm" action="UpdateIssue" namespace="/phase4"
                    onsubmit="return submitValidation();" theme="simple">
                <tr>
                    <td width="20%">Abstract <span class="required">*</span>:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            id="issueName"
                            name="issueBean.issueName"
                            cssClass="form-control"
                            tooltip="Enter Abstract here"
                            placeholder="Abstract"/>
                    </td>
                </tr>
                <tr>
                    <td>Priority<span class="required">*</span>:</td>
                    <td><s:select list="{1,2,3,4}" id="priority" name="issueBean.priority"/></td>
                </tr>
                <tr>
                    <td>Issue Status<span class="required">*</span>:</td>
                    <td><s:select list="{'Open','Close','Limitation','Cancel','WAD','SW_OS_W10_DGN'}"
                                  name="issueBean.issueStatus" value="%{issueBean.issueStatus}"/></td>
                </tr>
                <tr>
                    <td>Operating System<span class="required">*</span>:</td>
                    <td><s:checkboxlist tooltip="Checkboxes with inline position" name="osId"
                                        list="%{issueFormBean.mapOs}"/></td>
                </tr>

                <tr>
                    <td>Language<span class="required">*</span>:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            id="language"
                            name="issueBean.language"
                            value="%{issueBean.language}"
                            cssClass="form-control"
                            tooltip="Enter Lanugage here"
                            placeholder="Language"/></td>
                </tr>
                <tr>
                    <td>Test Site<span class="required">*</span>:</td>
                    <td><s:select list="%{issueFormBean.mapTestsite}" id="siteId" value="%{issueBean.testSite}" name="siteId"/></td>
                </tr>
                <tr>
                    <td> Component Owner <span class="required">*</span>:</td>
                    <td><s:select list="%{issueFormBean.mapComponent}" value="%{issueBean.component}" id="componentName" name="componentName" /></td>
                </tr>
                <tr>
                    <td>Issue Style <span class="required">*</span>:</td>
                    <td><s:select id="StyleId" name="issueBean.issuestyle" list="#{'OS':'OS','Apps':'APPs',
                    'CTO':'CTO','SWBOM/Image':'SWBOM/Image','Preload Process':'Preload Process','Driver':'Driver','BIOS':'BIOS',
                    'HW/Others':'HW/Others.'}"/></td>
                </tr>
                <tr>
                    <td>Phase Found<span class="required">*</span>:</td>
                    <td><s:checkboxlist tooltip="Checkboxes with inline position" name="phaseId"
                                        list="%{issueFormBean.mapPhase}"/></td>
                </tr>
                <tr>
                    <td>ECR Number</td>
                    <td class="form-group" colspan="3">
                        <s:textfield
                                id="ecrno"
                                name="issueBean.ecrNumber"
                                value="%{issueBean.ecrNumber}"
                                cssClass="input-sm"
                                elementCssClass="col-sm-9"
                                tooltip="Enter ECR No here" placeholder="ECR Number"/>
                    </td>
                </tr>
                <tr>
                    <td>PA Case Number:</td>
                    <td class="form-group" colspan="3">
                        <s:textfield
                                id="CaseNum"
                                name="issueBean.caseNum"
                                value="%{issueBean.caseNum}"
                                cssClass="input-sm"
                                elementCssClass="col-sm-9"
                                tooltip="Case Numbere" placeholder="Case Number"/>
                    </td>
                </tr>
                <tr>
                    <td>Reproduce Steps::</td>
                    <td class="form-group" colspan="3"><s:textarea
                            id="reporduceStep"
                            name="issueBean.reproduceStep"
                            value="%{issueBean.reproduceStep}"
                            cssClass="form-control"
                            rows="3"
                            tooltip="Enter Reproduce here" placeholder="Reproduce Step"/></td>
                </tr>
                <tr>
                    <td>Configuration:</td>
                    <td class="form-group" colspan="3"><s:textarea
                            id="configuration"
                            name="issueBean.configuration"
                            value="%{issueBean.configuration}"
                            cssClass="form-control"
                            rows="3"
                            tooltip="Enter Configuration here" placeholder="Configuration"/></td>
                </tr>
                <tr>
                    <td>Platform:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            tooltip="Checkboxes with inline position"
                            value="%{issueBean.platform}"
                            name="issueBean.platform"/></td>
                </tr>
                <tr>
                    <td class: text-right colspan="3">
                        <div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar">
                            <div class="btn-group">
                                <s:hidden name="issueKey" value="%{issueBean.instkey}"/>
                                <s:hidden name="issueBean.projectinstkey" value="%{issueBean.projectinstkey}"/>
                                <s:submit type="button" cssClass="btn btn-primary" value="Updated"></s:submit>
                            </div>
                        </div>
                    </td>
                </tr>
            </s:form>
        </table>
        <legend style="font-size:16px">2 - Comments :
            <button class="btn btn-info" onclick="NewComments();">Create Comments</button>
        </legend>
        <s:if test="#request.commentsList.size()>0">
            <table class="table table table-bordered">
                <th>Comments</th>
                <th>Owner</th>
                <th>CreateTime</th>
                <tbody>
                <s:iterator value="#request.commentsList" status="st">
                    <tr>
                        <td><s:property value="comments"/></td>
                        <td><s:property value="createBy"/></td>
                        <td><s:date name="createDate" format="yyyy-MM-dd"/></td>
                        <td><a onclick="DeleteComments('<s:property value="instkey"/>');" style="color:red">X</a></td
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
                    <tr>
                        <td><a href='<%=path%>/phase4/DownloadIssueFile?issuePhotoKey=<s:property value="instkey"/>'>
                            <s:property value="issueFileName"/></a>
                            &nbsp;&nbsp;<a onclick="DeleteIssuePhoto('<s:property value="instkey"/>');"
                                           style="color:red">X</a></td>
                    </tr>
                </s:iterator>
            </table>
        </s:if>
        <s:else>
            <h3><span>No Attachment</span></h3>
        </s:else>
    </div>
    <div class="col-lg-1">
    </div>
</div>
<s:include value="CreateComments.jsp"/>
<s:include value="UploadFile.jsp"/>

</body>
</html>
