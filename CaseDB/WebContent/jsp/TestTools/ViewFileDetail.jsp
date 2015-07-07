<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

 <head>
    <base id='base' href="<%=basePath%>">
    <title>File Property</title>
  </head>  
  
<body>
   <script type="text/javascript" src="<%=basePath%>common/js/jquery-1.6.1.min.js"> </script>
   <script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery-ui-1.7.3.custom.js"></script>
   	<script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery.blockUI.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugs/jQueryUI/themes/base/ui.all.css">
<script type="text/javascript">

  $(document).ready(
		   function(){
		       replaceItemTableBlank();
		   }
		  );
 function  replaceItemTableBlank(){
 
 	   var history=document.getElementById("history");
  		var historyCom=history.innerHTML;
  		var regx=new RegExp("&lt;br&gt;","g");
  		if(historyCom!=""){
  				historyCom = historyCom.replace(regx," <br/>");
  				history.innerHTML = historyCom;
  		}
  }
function Upload(){
     var key= "<s:property value='testToolBean.instkey' />";
      		var url = "<%=basePath%>phase4/preUploadFile?instkey="+key;
      		window.location.href =url;	
      }
      function DeleteCase(){
      console.info("start to delete");
       var key= "<s:property value='testToolBean.instkey' />";
      console.info(key);
      if(confirm('Are you sure you are going to Delete this Case?')){
		  		var url = "<%=basePath%>phase4/DeleteCase?caseInstkey="+key;
      		window.location.href =url;	
			}	
    
      }
	
</script>
<div class="col-lg-offset-9  " class="btn-toolbar" role="toolbar" >
<s:if test="#session.userInfo.userName !='tester' ">
	<s:if test="#session.userInfo.Role =='Leader' || #session.userInfo.Role =='Case Owner' || #session.userInfo.Role =='Admin' || #session.userInfo.Role =='Family Owner'">	 
  					<div class="btn-group">
  						<button id="DeleteTestCase" class="btn btn-warn" type="button" onclick="DeleteFile();">Delete File
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
          <legend style="font-size:16px">File Basic Information:</legend>
		    <table class="table table table-bordered ">
          		   <tr>
          		    <td class="active">Tool Name:</td>
		    		<td id="history" colspan="3" > <s:property value="testToolBean.toolName" /></td>
          			</tr>
                     <tr>
				    <td class="active">Description:</td>
		    		<td id="history" colspan="3">  <s:property value="testToolBean.description" /></td>
				    </tr>
				    <s:if test="testToolBean.path!=null">
				    	<tr>
				    		<td class="active">Tool File Name</td>
				    		<td> <a href="<%=basePath%>phase4/downloadFileAction?instkey=<s:property value="testToolBean.instkey" />"><s:property value="testToolBean.uploadFileName" /></a></td>
				    	</tr>
				    </s:if>
				    <s:if test="testToolBean.path == null">
				    	<tr>
				    		<td></td>
				    		<td><button id="upload" class="btn btn-info" type="button" onclick="Upload();">Upload File
	      			</button>
	      			</td>
				    	</tr>
				    </s:if>
            </table>
    		
          </div>
   
</div>
    
	
</body>
</html>