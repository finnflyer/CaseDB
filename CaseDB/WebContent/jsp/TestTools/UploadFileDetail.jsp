<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <sb:head/>
     <style type="text/css">
     td.label_item{ 
     background-color: #48d1cc;
     font-weight:bolder;	text-align: right;
     color:#272727;
     empty-cells : show;
     }
    </style>
  <head>
    <base href="<%=basePath%>">
          <style type="text/css">
     td.label_item{ 
     background-color: #48d1cc;
     font-weight:bolder;	text-align: right;
     color:#272727;
     empty-cells : show;
     }
    </style>
    <title>Upload File</title>
  </head>
  
  <body>
    	<script language="javascript"  src="plugs/uploadify/swfobject.js"></script>
		<script language="javascript"  src="plugs/uploadify/jquery.uploadify.v2.1.4.js"></script>
	
		 <script type="text/javascript"> 
	     function showResult(){//删除显示的上传成功结果   
	          $("#result").html("");
	     }   
	    
	    $(document).ready(function() {
	    	//alert('<s:property value="#request.automationFile.instkey"/>');
	        $('#fileInput').uploadify({   
		 		'uploader' 		: '<%=basePath%>/plugs/uploadify/uploadify.swf',
		        'script'		: '<%=basePath%>/phase4/uploadFile',	         
		    	'cancelImg'   	: '<%=path%>/plugs/uploadify/cancel.png',
		        'fileDataName'	: 'fileInput', 			//the name is same as input and Action property
		        'queueID'		: 'fileQueue',   
		        'auto'			: false,				//automation to upload once file selected
		        'multi'			: true,					//if support more than one file upload
		        'queueSizeLimit': 1,					//maximum number of upload file for one time
		      'sizeLimit'   	: 100*1024*1024, 			//limit the size fo file(100M)
		        'buttonText'	: 'Browse Files',		//Button Text  
		        'removeCompleted' : false,
		        'displayData'	: 'percentage',			//speed of percentage, 
		        'onSelect'         : function (event, queueID, fileObj) {
			        $("#fileInput").uploadifySettings(
			        		'scriptData',{
			        			'instkey':'<s:property value="#request.testToolBean.instkey"/>'
			        			});
			        },
		        'onComplete'	: function (event, queueID, fileObj, response, data){ 	   
		        					var id='<s:property value="#request.testToolBean.instkey"/>';
		        					var url="<%=basePath%>phase4/ShowToolDetail?testToolBean.instkey="+id;
		     	  				  	window.location.href=url;
		          },
		          'onError'        : function(event, queueID, fileObj,errorObj){
   						alert(errorObj.type + "Error:" + errorObj.info);
  				 }
	        });   
	    });  

	</script>   
     

		<div class="row-fluid">
 		 <div class="col-lg-12" style="padding-left:60px;padding-right:60px">
 		 	<div class="row">
 		 	<div class="col-lg-12">
 		 	</div>
 		 	<br>
 		 	<br>
          <legend style="font-size:16px">1 - Basic Information:</legend>
		    <table class="table table table-bordered ">
          		   <tr>
          		    <td class="active">File Name:</td>
		    		<td id="history" colspan="" 6> <s:property value="testToolBean.toolName" /></td>
          			</tr>
                     <tr>
				    <td class="active">Description:</td>
		    		<td id="history" colspan="" 6> <s:property value="testToolBean.description" /></td>
				    </tr>
            </table>
    		
          </div>
   
</div>
     	<div class="row-fluid">
     	<div class="col-lg-1">
     	</div>
       <div class="col-lg-11">
	  	<input type="file" name="fileInput" id="fileInput" /> 
	     <div id="fileQueue"></div>   
	  	<br><br>
		<input type="button" class="btn btn-success" value="Upload" onclick="javascript:$('#fileInput').uploadifyUpload();"/>
	    <div id="result"></div>
	    </div>
    
   
  </body>
</html>
