<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Step One </title>
   	
  </head>
  
  <body>
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.11.1.js"> </script>
		<script src="jslib/Bootstrap/bootstrap.js" type="text/javascript" charset="utf-8"></script> 
	   <script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.js"></script>
<script type="text/javascript">
function submitValidation(){
	console.info("start");
	var caseName=$("#caseName").val();
    var caseCode = $("#caseCode").val();
    var version = $("#version").val();
    var reason =$("#modifyReason").val();
    	console.info("start a "+caseName+" b"+caseCode+" c "+version);
	if(caseName==""){
	 $("#caseName").closest('.form-group').addClass('has-error');
		return false;
	}
	console.info("start  b"+caseCode+" c "+version);
    if(caseCode==""){
	 $("#caseCode").closest('.form-group').addClass('has-error');
		return false;
	}
	console.info("start  c "+version);
	if(version==""){
	 $("#version").closest('.form-group').addClass('has-error');
	 console.info("start  c "+version);
		return false;
	}
		if(reason==""){
	 $("#modifyReason").closest('td').addClass('has-error');
		return false;
	}
	
}
</script>
 	<div class="row-fluid">
        <div class="col-lg-1">      	    	  </div>
 		 <div class="col-lg-10">
            <h4> Step 1</h4>
            <p class="text-right">Case Owner:<s:property value="#session.userInfo.userName" /></p>
		    <legend>1-Basic Information:</legend>
	      	     <s:fielderror theme="bootstrap"/>
		    <table class="table table table-bordered ">
            <s:form id="inputForm" action="phase4/StepTwo" onsubmit="return submitValidation();" theme="simple" >
            		<tr>
		    		<td width="15%">Category:</td>
		    		<td colspan="3" ><s:select name="functionId" list="%{tcFormbean.mapFunction}" theme="simple" headerKey=" " headerValue=" "></s:select></td> 
				    </tr>
				     <tr>
		    		<td>CaseName:</td>
		    		<td class="form-group" colspan="3"> <s:textfield
                        id="caseName"
                        name="caseName"
                        cssClass="form-control" 
                        tooltip="Enter Case Name here" 
                         placeholder="Case Name"/></td>
				    </tr>
				    <tr>
				     <td>Case ID</td>
                        <td class="form-group" colspan="3">
                            <s:textfield
                        id="caseCode"
                        name="caseCode"
                        cssClass="input-sm" 
                                elementCssClass="col-sm-9"
                        tooltip="Enter Case ID here" placeholder="Case Code"/>
                        </td>
				    </tr>
				    <tr>
		    		<td>Version:</td>
		    		<td class="form-group" colspan="3"> <s:textfield
                        id="version"
                        name="version"
                        cssClass="input-sm" 
                        tooltip="Enter version here" placeholder="Version"/></td>
				    </tr>
					<tr>
				    <td>Modify Reason:</td>
		    		<td class="form-group" colspan="3" > <s:textarea
                        id="modifyReason"
                        name="modifyReason"
                  		 cssClass="form-control" 
                  		 rows = "3"
                        tooltip="Enter Modify here" placeholder="Modify Reason" /></td>
				    </tr>
				    <tr>
				       <td>SupportOS</td>
				       <td colspan="3" > <s:checkboxlist
                        tooltip="Checkboxes with inline position"
                        list="%{tcFormbean.mapOs}"
                        name="mapOs"/></td>
  			    </tr>
				    <tr>
				       <td>System Brand</td>
				       <td class="form-group" colspan="3" > <s:checkboxlist
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
