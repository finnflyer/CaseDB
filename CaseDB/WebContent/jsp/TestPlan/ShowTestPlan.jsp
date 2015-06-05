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
    
    <title>Show Test Plan Detail</title>

  </head>
  
  <body>
  <script type="text/javascript">
    		function EditTestPlan(){
    		var key="<s:property value='testPlanInstkey' />";
    		console.info(key);
   			 var url ="<%=basePath%>phase4/EditTestPlan?testPlanInstkey="+key;
   		    window.location.href=url;
   		}
   		 function DeleteTestPlan(){
   		     var key="<s:property value='testPlanInstkey' />";
    		console.info(key);
    		if(confirm('Are you sure you are going to Delete this TestPlan?')){
    			 var url ="<%=basePath%>phase4/DeleteTestPlan?testPlanInstkey="+key;
   		    window.location.href=url;
    		}
   		
   		 }
   </script>
	
	
  	<div class="col-lg-offset-1" class="btn-toolbar" role="toolbar" >
  				 <div class="btn-group">
  				 <h4> Project Name: <s:property value="#request.testPlan.projectName" /></h4>
  				 </div>
  				 &nbsp; &nbsp; &nbsp; &nbsp;
  				 <div class="btn-group">
  				 <h4> Test Plan Name: <s:property value="testPlanName" /></h4>
  				 </div>
  				  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  				 <div class="btn-group">
  				 <h4> Test Plan Owner: <s:property value="#request.testPlan.testPlanOwner" /> </h4> 
  				 </div>
  				 &nbsp; 	 &nbsp;	 &nbsp;	 &nbsp;
				  <div class="btn-group">
  					<button id="EditTestPlan" class="btn btn-warn" type="button" onclick="EditTestPlan()";>Edit Test Plan </button>
	      		
  					</div>	 
  					<div class="btn-group">
  						<button id="DeleteTestPlan" class="btn btn-warn" type="button" onclick="DeleteTestPlan();">Delete Test Plan
	      			</button>
  					</div>   
				    </div>	
				    <br>
				        <br>
    <div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">

		<h5> Total Case: <s:property value="#request.contentList.size()" /></h5>
			<h5> Total Time: <s:property value="totalTime" /> Mins</h5> 
	     <table class="table table table-bordered table-striped" style="table-layout:fixed">
    		<thead>
    		<tr>
    			<th class="info" width="6%">CaseID</th>
    			<th class="info" width="14%">Category</th>
    			<th class="info" width="30%">CaseName</th>
    			<th class="info" width="8%">Version</th>
       			<th class="info">OS</th>
    			<th class="info" width="18%">Brand</th>
				<th class="info">Owner</th>
        		</tr>
    		</thead>
    		<tbody>
    		<s:iterator value="#request.contentList" id="tcinfo">
    		
    		<tr>
    			<td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a href='${pageContext.servletContext.contextPath}/phase4/ShowTestCaseDetail?testCase.caseInstkey=<s:property value="#tcinfo.caseInstkey" />'   target="_blank" >    				
    					<s:property value="#tcinfo.caseCode" />
    			</a> 
    			</td> 
    			<td><s:property value="#tcinfo.functionCato" /></td>
    			<td><s:property value="#tcinfo.caseName" /></td>
				<td><s:property value="#tcinfo.Version" /></td>
    			<td><s:property value="#tcinfo.osCato" /></td>
    			<td><s:property value="#tcinfo.brandCato" /></td>
				<td><s:property value="#tcinfo.Owner" /></td>
    		</tr>
    		</s:iterator>
    		</tbody>
    	</table>
    </div>
		</div>
	    <div class="col-lg-1">
	    </div>
		
	</div>
  </body>
</html>
