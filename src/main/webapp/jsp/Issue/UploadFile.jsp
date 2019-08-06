<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript" src="<%=basePath%>js/uploadify/swfobject.js"></script>
<script language="javascript" src="<%=basePath%>js/uploadify/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript">
  $(document).ready(function () {
    var key = "<s:property value='issueBean.instkey' />";
    $('#fileInput').uploadify({
      'uploader': '<%=basePath%>js/uploadify/uploadify.swf',
      'script': '<%=basePath%>phase4/UploadIssuePhoto',
      'cancelImg': '<%=basePath%>js/uploadify/cancel.png',
      'fileDataName': 'fileInput', 			//the name is same as input and Action property
      'queueID': 'fileQueue',
      'auto': false,				//automation to upload once file selected
      'multi': true,					//if support more than one file upload
      'queueSizeLimit': 3,					//maximum number of upload file for one time
      'sizeLimit': 100 * 1024 * 1024, 			//limit the size fo file(100M)
      'buttonText': 'Select Files',		//Button Text
      'removeCompleted': false,
      'displayData': 'percentage',			//speed of percentage,
      'onSelect': function (event, queueID, fileObj) {
        $("#fileInput").uploadifySettings(
                'scriptData', {
                  'issueKey': key
                });
      },
      'onComplete': function (event, queueID, fileObj, response, data) {
        bootbox.alert("Add Successfully!");
        setTimeout(function(){window.location.reload(true)},500);

      },
      'onError': function (event, queueID, fileObj, errorObj) {
        bootbox.alert("Upload Failed!");
        console.info(errorObj.type + "Error:" + errorObj.info);
      }
    });
  });

</script>
<!-- Upload Attached File Frame -->
<div id="attachedModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="CreateModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">Add New Attachment</h1>
      </div>
      <div class="modal-body">
        <form class="form col-md-12 center-block">
          <div class="form-group">
            <input type="file" name="fileInput" id="fileInput"/>
          </div>
          <div id="fileQueue"></div>
          <div class="form-group">
        <button id="UploadBtn" class="btn btn-primary btn-lg btn-block" data-dismiss="modal"
                onclick="javascript:$('#fileInput').uploadifyUpload();">Upload</button>
      </div>
      </form>
      </div>

      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>