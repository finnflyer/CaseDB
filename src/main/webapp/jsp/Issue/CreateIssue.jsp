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
    <title>Create Issue</title>
</head>
<body>
<style type="text/css">
.table-bordered{
    border-color:  #FFFFFF;
}
    .table-bordered > tbody > tr > td, .table-bordered > tbody > tr > th, .table-bordered > tfoot > tr > td, .table-bordered > tfoot > tr > th, .table-bordered > thead > tr > td, .table-bordered > thead > tr > th {
        border: 1px solid #FFFFFF;
    }
.required{
    color: red;
    font-size: 100%;
  }



</style>
<script type="text/javascript">
    function submitValidation(){
        console.info("start");
        var testSite = $('#siteId option:selected').val();
        console.info("site:"+testSite);
        if(testSite==" "){
            bootbox.alert("Select one Site");
            return false;
        }
        var priority = $('#priority option:selected').val();
        console.info("site:"+priority);
        if(priority==" "){
            bootbox.alert("Select one Priority");
            return false;
        }

        var issueName=$("#issueName").val();
        var Lanuage = $("#Lanuage").val();
        if(issueName==""){
           // bootbox.alert("Input ");
            $("#issueName").closest('.form-group').addClass('has-error');
            return false;
        }
        if(Lanuage==""){
            bootbox.alert("Input Language Related");
            $("#Lanuage").closest('.form-group').addClass('has-error');
            return false;
        }
        var osid="";
        $('input[name="osId"]:checked').each(function(){
            osid =osid+" "+$(this).val();;
        });
        if(osid=="  "){
            bootbox.alert("Select One OS");
            return false;
        }
        console.info(osid);
        var phaseId="";
        $('input[name="phaseId"]:checked').each(function(){
            phaseId =phaseId+" "+$(this).val();;
        });

        if(phaseId=="  "){
            bootbox.alert("Select phase");
            return false;
        }

    }
</script>
<div class="row-fluid">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <h4>Input Detail Issue Info </h4>
        <h6>(The <span class="required">*</span> should not be null)</h6>
        <s:fielderror theme="bootstrap"/>
        <table class="table table-bordered ">
            <s:form id="inputForm" action="SaveIssueContent" namespace="/phase4"
                    onsubmit="return submitValidation();"   theme="simple">
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
                    <td>Priority  <span class="required">*</span>:</td>
                    <td><s:select list="{1,2,3,4}" id="priority" name="issueBean.priority" headerKey=" " headerValue=" "/></td>
                </tr>
                <tr>
                    <td>Operating System <span class="required">*</span>:</td>
                    <td><s:checkboxlist  tooltip="Checkboxes with inline position" name="osId" list="%{issueFormBean.mapOs}"/></td>
                </tr>

                <tr>
                    <td>Language <span class="required">*</span>:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            id="Lanuage"
                            name="issueBean.language"
                            cssClass="form-control"
                            tooltip="Enter Lanugage here"
                            placeholder="Language"/></td>
                </tr>
                <tr>
                    <td>Test Site <span class="required">*</span>:</td>
                    <td><s:select list="%{issueFormBean.mapTestsite}" id="siteId" name="siteId" headerKey=" " headerValue=" "/></td>
                </tr>
                <tr>
                    <td>Phase Found <span class="required">*</span>:</td>
                    <td><s:checkboxlist  tooltip="Checkboxes with inline position" name="phaseId" list="%{issueFormBean.mapPhase}"/></td>

                </tr>
                <tr>
                    <td>ECR Number</td>
                    <td class="form-group" colspan="3">
                        <s:textfield
                                id="ecrno"
                                name="issueBean.ecrNumber"
                                cssClass="input-sm"
                                elementCssClass="col-sm-9"
                                tooltip="Enter ECR No here" placeholder="ECR Number"/>
                    </td>
                </tr>
                <tr>
                    <td>Reproduce Steps::</td>
                    <td class="form-group" colspan="3"><s:textarea
                            id="reporduceStep"
                            name="issueBean.reproduceStep"
                            cssClass="form-control"
                            rows="3"
                            tooltip="Enter Reproduce here" placeholder="Reproduce Step"/></td>
                </tr>
                <tr>
                    <td>Configuration:</td>
                    <td class="form-group" colspan="3"><s:textarea
                            id="configuration"
                            name="issueBean.configuration"
                            cssClass="form-control"
                            rows="3"
                            tooltip="Enter Configuration here" placeholder="Configuration"/></td>
                </tr>
                <tr>
                    <td>Platform:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            tooltip="Checkboxes with inline position"
                            name="issueBean.platform"/></td>
                </tr>
                <tr>
                    <td>PA Test Case Number:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            tooltip="Checkboxes with inline position"
                            name="issueBean.caseNum" placeholder="PA Test Case Number"/></td>
                </tr>
                <tr>
                    <td> Component Owner:</td>
                    <td class="form-group" colspan="3"><s:textfield
                            tooltip="Checkboxes with inline position"
                            name="issueBean.component"  placeholder="Component Owner"/></td>
                </tr>
                Component Owner
                <tr>
                    <td class: text-right colspan="3">
                        <div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar">
                            <div class="btn-group">
                                <s:submit type="button" cssClass="btn btn-primary" value="Next"></s:submit>
                            </div>
                        </div>
                    </td>
                </tr>
            </s:form>
        </table>
    </div>
    <div class="col-lg-1">
    </div>

</div>


</body>
</html>
