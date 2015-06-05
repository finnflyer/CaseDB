
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>EditTestCase</title>
  </head>
     <script type="text/javascript" src="common/js/jquery-1.6.1.min.js"> </script>
   <script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery-ui-1.7.3.custom.js"></script>

  <body>
     <script type="text/javascript">
       function validation(){
       //input validation
				  var obj=document.getElementById("TCName");
				  if (obj.value==""){
					  alert("TestCase name is required");
					  return false;
				  }
				  obj=document.getElementById("version");
				  if (obj.value==""){
					  alert("TestCase version is required");
					  return false;
				  }
				  	  obj=document.getElementById("caseCode");
				  if (obj.value==""){
					  alert("TestCase CaseCode is required");
					  return false;
				  }
				  obj=document.getElementById("modifyReason");
				  if (obj.value==""){
					  alert("TestCase Modify Reason  is required");
					  return false;
				  }
       }
   		$(document).ready(
		   function(){
		      handleCheckBox();
		   }
		  );
   function handleCheckBox(){
        console.info("start");
         var  brandId = '<s:property value="testCaseInfo.brandId"/>';
         var  osId = '<s:property value= "testCaseInfo.osId" />';
         switch(brandId.length){
         	case 1:
         	  $("#mapBrand-"+brandId).attr("checked","checked");
		         break;
		     case 2:
		       $("input[name='mapBrand']").attr("checked","checked");
         }
  		  switch (osId.length){
		     case 1:
		         $("#mapOs-"+osId).attr("checked","checked");
		         break;
		      case 2:
		         var a = osId.substring(0, 1);
		         var b = osId.substring(1,2);
		          $("#mapOs-"+a).attr("checked","checked");
		          $("#mapOs-"+b).attr("checked","checked");
		          break;
		         case 3:
		         var a = osId.substring(0, 1);
		         var b = osId.substring(1,2);
		         var c = osId.substring(2, 3);
		          $("#mapOs-"+a).attr("checked","checked");
		          $("#mapOs-"+b).attr("checked","checked");
		          $("#mapOs-"+c).attr("checked","checked");
		          break;
		          case 4:
		          $("input[name='mapOs']").attr("checked","checked");
		          break;
		  }
   }
   </script>
  
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>css/bootstrap-responsive.min.css" rel="stylesheet">
 	<div class="row-fluid">
        <div class="col-lg-1">      	    	  </div>
 		 <div class="col-lg-10">
 		    <h4 style="color:red;">CaseName :   <s:property  value="%{testCase.caseName}" /></h4>
            <h4> Step 1:   </h4>
            <p class="text-right">CaseOwner:<s:property value="#session.userInfo.userName" /></p>
		    <legend>1-Basic Information:</legend>
	      	     <s:fielderror theme="bootstrap"/>
		    <table class="table table table-bordered ">
            <s:form action="phase4/UpdateCaseStepone" theme="simple" onsubmit="return validation(); ">
            		<tr>
		    		<td width="15%">Category:</td>
		    		<td colspan="3" ><s:select name="functionId" list="%{tcFormbean.mapFunction}" theme="simple" value="%{testCaseInfo.funcId}"></s:select></td> 
				    </tr>
				     <tr>
		    		<td>CaseName:</td>
		    		<td colspan="3"> <s:textfield
		    			id="TCName"
                        label="Name"
                        name="caseName"
                        cssClass="form-control" 
                        value="%{testCase.caseName}"
                        tooltip="Enter Case Name here" placeholder="CaseName"/></td>
				    </tr>
				    <tr>
				     <td>Case ID</td>
                        <td colspan="3">
                            <s:textfield
                         id="caseCode"
                        label="caseCode"
                        name="caseCode"
                        cssClass="input-sm"
                        value="%{testCase.caseCode}" 
                        elementCssClass="col-sm-9"
                        tooltip="Enter Case ID here" placeholder="CaseCode"/>
                        </td>
				    </tr>
				    <tr>
		    		<td>Version:</td>
		    		<td colspan="3"> <s:textfield
		    			id="version"
                        label="Version"
                        name="version"
                        cssClass="input-sm" 
                        value="%{testCase.version}"
                        tooltip="Enter version here" placeholder="Version"/></td>
				    </tr>
					<tr>
				    <td>Modify Reason:</td>
		    		<td colspan="3" > <s:textarea
		    			id="modifyReason"
                        label="modifyReason"
                        name="modifyReason"
                  		 cssClass="form-control" 
                  		 rows = "3"
                  		 value="%{testCaseInfo.modifyReason}"
                        tooltip="Enter Modify here" placeholder="modifyReason" /></td>
				    </tr>
				    <tr>
				       <td>SupportOS</td>
				       <td colspan="3" > <s:checkboxlist id="osid"
                        tooltip="Checkboxes with inline position"
                        list="%{tcFormbean.mapOs}"
                        name="mapOs"/></td>
  			    </tr>
				    <tr>
				       <td>System Brand</td>
				       <td colspan="3" > <s:checkboxlist
                        tooltip="Checkboxes with inline position"
                        list="%{tcFormbean.mapBrand}"
                        name="mapBrand"/></td>
			    </tr>
  				    <tr>
			       <td class: text-right  colspan="3">
				    <div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar" >
				     <div class="btn-group">
  						<s:submit type="button" cssClass="btn btn-primary" value="Next"></s:submit>
  					</div>	    
				    </div></td>
				    </tr>
            </s:form>
            </table>
            </div>
            <div class="col-lg-1">                
            </div>

</div>
  </body>
</html>
