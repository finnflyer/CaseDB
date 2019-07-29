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
    <base id='base' href="<%=basePath%>">
    <title>MFG File Property</title>
</head>

<body>
<script src="<%=basePath%>js/StringForHtml.js"></script>
<script type="text/javascript">
    $(function() {
        $("#Home").removeClass("active");
        $("#Debug").addClass("active");
    });
    function Upload() {
        var key = "<s:property value='toolBean.instkey' />";
        var url = "<%=basePath%>phase4/preUploadFile?toolKey=" + key;
        window.location.href = url;
    }
    function DeleteTool() {
        console.info("start to delete");
        var key = "<s:property value='toolBean.instkey' />";
        console.info(key);
        bootbox.confirm("Are you sure you are going to Delete this tool?",function(){
            var url = "<%=basePath%>phase4/Delete?toolKey=" + key;
            window.location.href = url;
        })
    }

</script>
<div class="col-lg-offset-9  " class="btn-toolbar" role="toolbar">
    <s:if test="#session.user.username !='tester' ">
        <s:if test="#session.user.role =='Leader' || #session.user.role =='Case Owner' || #session.user.role =='Admin' || #session.user.role =='Family Owner'">
            <div class="btn-group">
                <button id="DeleteTestCase" class="btn btn-warn" type="button" onclick="DeleteTool();">Delete File
                </button>
            </div>
        </s:if>
    </s:if>
</div>

<div class="row-fluid">
    <div class="col-lg-12" style="padding-left:60px;padding-right:60px">
        <div class="row">
            <div class="col-lg-12">
            </div>
            <legend style="font-size:16px">MFG File Basic Information:</legend>
            <table class="table table table-bordered ">
                <tr>
                    <td class="active">MFG Tool Name:</td>
                    <td colspan="3"><s:property value="toolBean.toolname"/></td>
                </tr>
                <tr>
                    <td class="active">Description:</td>
                    <td colspan="3"><s:property value="toolBean.description"/></td>
                </tr>
                <s:if test="toolBean.path!=null">
                    <tr>
                        <td class="active">MFG Tool File Name</td>
                        <td>
                            <a href="<%=basePath%>phase4/DownloadFile?toolKey=<s:property value="toolBean.instkey" />"><s:property
                                    value="toolBean.uploadFileName"/></a></td>
                    </tr>
                </s:if>
                <s:if test="toolBean.path == null">
                    <tr>
                        <td></td>
                        <td>
                            <button id="upload" class="btn btn-info" type="button" onclick="Upload();">Upload File
                            </button>
                        </td>
                    </tr>
                </s:if>
            </table>

        </div>

    </div>

</div>
</body>
</html>