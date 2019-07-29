<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<sb:head/>

<head>
    <base href="<%=basePath%>">
    <title>Upload File</title>
</head>

<body>
<script language="javascript" src="js/uploadify/swfobject.js"></script>
<script language="javascript" src="js/uploadify/jquery.uploadify.v2.1.4.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        //alert('<s:property value="#request.automationFile.instkey"/>');
        $('#fileInput').uploadify({
            'uploader': '<%=basePath%>/js/uploadify/uploadify.swf',
            'script': '<%=basePath%>/phase4/UploadToolFile',
            'cancelImg': '<%=path%>/js/uploadify/cancel.png',
            'fileDataName': 'fileInput', 			//the name is same as input and Action property
            'queueID': 'fileQueue',
            'auto': false,				//automation to upload once file selected
            'multi': true,					//if support more than one file upload
            'queueSizeLimit': 1,					//maximum number of upload file for one time
            'sizeLimit': 200 * 1024 * 1024, 			//limit the size fo file(100M)
            'buttonText': 'Browse Files',		//Button Text
            'removeCompleted': false,
            'displayData': 'percentage',			//speed of percentage,
            'onSelect': function (event, queueID, fileObj) {
                $("#fileInput").uploadifySettings(
                        'scriptData', {
                            'toolKey': '<s:property value="#request.toolBean.instkey"/>'
                        });
            },
            'onComplete': function (event, queueID, fileObj, response, data) {
                var id = '<s:property value="#request.toolBean.instkey"/>';
                var url = "<%=basePath%>phase4/ViewMFGTool?toolKey=" + id;
                window.location.href = url;
            },
            'onError': function (event, queueID, fileObj, errorObj) {
                alert(errorObj.type + "Error:" + errorObj.info);
            }
        });
    });

</script>


<div class="row-fluid">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <div class="row">
            <br>
            <br>
            <legend style="font-size:16px">1 - Basic Information:</legend>
            <table class="table table table-bordered ">
                <tr>
                    <td class="active">MFG File Name:</td>
                    <td colspan=""><s:property value="toolBean.toolname"/></td>
                </tr>
                <tr>
                    <td class="active">Description:</td>
                    <td colspan=""><s:property value="toolBean.description"/></td>
                </tr>
            </table>

        </div>
        <div class="row-fluid">
            <div class="col-lg-1">
            </div>
            <div class="col-lg-11">
                <div class="form-group">
                    <input type="file" name="fileInput" id="fileInput"/>
                    <input type="button" class="btn btn-success" value="Upload"
                           onclick="javascript:$('#fileInput').uploadifyUpload();"/>
                </div>

                <div id="fileQueue"></div>
            </div>


        </div>
    </div>
    <div class="col-lg-1">
    </div>

</div>
</body>
</html>
