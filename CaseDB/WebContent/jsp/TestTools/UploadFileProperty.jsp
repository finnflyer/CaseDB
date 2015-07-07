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
    <title>Upload Test Tool </title>
   	
  </head>
  
  <body>
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.11.1.js"> </script>
		<script src="jslib/Bootstrap/bootstrap.js" type="text/javascript" charset="utf-8"></script> 
	   <script type="text/javascript" src="<%=basePath%>common/js/jquery.validate.js"></script>
<script type="text/javascript">

</script>
 	<div class="row-fluid">
        <div class="col-lg-1">      	    	  </div>
 		 <div class="col-lg-10">
 		 <br>
 		 <br>
 		 <br>
   	    <legend>Basic Information:</legend>
	      <s:fielderror theme="bootstrap"/>
		    <table class="table table table-bordered ">
            <s:form id="inputForm" action="phase4/SaveFile" theme="simple" >
				<tr>
		    		<td>Tool Name:</td>
		    		<td class="form-group" colspan="3"> <s:textfield
                        id="fileName"
                        name="toolName"
                        cssClass="form-control" 
                        tooltip="Enter Case Name here" 
                         placeholder="fileName"/></td>
				</tr>
				<tr>
				    <td>Description:</td>
		    		<td class="form-group" colspan="3" > <s:textarea
                        id="Description"
                        name="description"
                  		 cssClass="form-control" 
                  		 rows = "3"
                        tooltip="Enter Description here" placeholder="Description " /></td>
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
