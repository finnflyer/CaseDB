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
    <base id="base" href="<%=basePath%>">
    <title>Search Test Case For Plan</title>
    


		<style type="text/css">
        body {
            padding-bottom: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
          }
          .link_disable	{ color: gray;	text-decoration: underline;}
    </style>

  </head>

<body >
   <script type="text/javascript" src="<%=basePath%>common/js/jquery-1.6.1.min.js"> </script>
   <script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery-ui-1.7.3.custom.js"></script>
   	<script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery.blockUI.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugs/jQueryUI/themes/base/ui.all.css">
<script type="text/javascript">
$(document).ready(function() {
		 var allselect = document.getElementsByName("AllSelect");
  	    console.info(allselect);
  	    allselect[0].checked=true;
  	    var checkbox = document.getElementsByName("scriptid");
  	    if(allselect[0].checked){
		  for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=true;
		  }
	   	}
 		$("#submitNewTestPlan").click(function() {
 			  var str="";
 			  var checkbox = document.getElementsByName("scriptid");
 			  var checklist = 0;
 			  var mark =0;
 			  for(var i=0;i<checkbox.length;i++) {
 					if(checkbox[i].checked){	
 						checklist +=1;
 					}
 			  }
 			  if(checklist==0){
 				  alert("please select one Case ");	 
 				  return;
 			  }	
 			  for(var i=0;i<checkbox.length;i++){
 				   if(checkbox[i].checked){
 					   str+=" "+checkbox[i].getAttribute("value");
 				   }
 			  }
 			  console.info(str);
 			  console.info($("#testPlanName").val()+""+$("#testPlanDescription").val()+""+$("#projectName").val()+""+$("#testPlanComments").val());
 			$.ajax({
 				type : "POST",
 				url : "<%=basePath%>phase4/SaveContentToNewPlan",
 				dataType : "html",
 				data : {
 					"testPlanName" : $("#testPlanName").val(),
 				
 					"testPlanComments":$("#testPlanComments").val(),
 					"projectName":$("#projectName").val(),
 					"str":str
 				},
 				success : function(returnedData) {
 					if (returnedData == "success") {
 						$("#NewTestPlanForm").dialog("close");
 						alert("Create Test Plan succeed.");
 						 var url = "<%=basePath%>phase4/TestPlanHome";
 						  window.location.href=url;
 					}
 				}
 			});
 		});

 	});
 	


  $(document).ready(function() {
	  $("#addTomyList").click(function() {
		  var str="";
		  $("[@type='checkbox'][@name='scriptid'][checked]").each(function(){ 
			  	str+=" "+$(this).val();  	
			});
		  var inkey ="";
		  var radios = document.getElementsByName("testPlanInstkey"); 
		  console.info(radios);
		  for(var i=0;i<radios.length;i++) 
		    {  
		        if(radios[i].checked) 
		        { 
		          inkey = radios[i].value;
		        }  
		    }  
		    console.info(inkey);
		  if(inkey==""){
			  alert("Please select one Test Plan");
		  }
		  return;
			$.ajax({
				type : "POST",
				url : "phase4/AddContentToOlderTestPlan",
				dataType : "html",
				data : {
					"testPlanInstkey": inkey,
					"str":str
				},
				success : function(returnedData) {
					if (returnedData == "success") {
						$("#TestPlanForm").dialog("close");
						alert("Adding succeed.");
						var url = "phase4/ShowTestPlan?testPlanInstkey="+inkey;
						document.forms["solutionForm"].action = url;
						document.forms["solutionForm"].submit();		
						displayBusyBox();
					}
				}
			});
		});
		  
  	});
function createTestPlanDlg(){
		$("#TestPlanForm").dialog("close"); 
		$("#NewTestPlanForm").dialog({
			height : 400,
			width : 800,
			modal : true
		});
		//$("#NewSolution").dialog("open");
		$("#NewTestPlanForm").dialog("open");
}
function openSolutionDlg(){
	 //console.info("2323");
	  var checkbox = document.getElementsByName("scriptid");
	  var checklist = 0;
	  for(var i=0;i<checkbox.length;i++) {
			if(checkbox[i].checked){
				checklist +=1;
			}
	  }
	  if(checklist==0){
		  alert("please select one case");
		  return;
	  }
	  $("#NewTestPlanForm").dialog({
			height : 400,
			width : 800,
			modal : true
		});
	  var radios = document.getElementsByName("testPlanInstkey"); 
	  for(var i=0;i<radios.length;i++) {
		   radios[i].checked=false;
	  }
		$("#NewTestPlanForm").dialog("open");
  }
  
  function selectAll(){
  	    var allselect = document.getElementsByName("AllSelect");
  	    console.info(allselect);
  	    
  	    var checkbox = document.getElementsByName("scriptid");
  	    console.info(allselect.checked);
	   	if(allselect[0].checked){
		  for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=true;
		  }
	   	}else{
	   		for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=false;
		  }
	   	}
  	}
  	function selectBrand(){
  	   var allselect = document.getElementsByName("Brand");
  	    console.info(allselect);
  	    
  	    var checkbox = document.getElementsByName("mapBrand");
  	    console.info(allselect.chceked);
	   	if(allselect[0].checked){
		  for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=true;
		  }
	   	}else{
	   		for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=false;
		  }
	   	}
  	
  	}
  	function selectCate(){
  		console.info("start select"); 	   
  		    var allselect = document.getElementsByName("selectCategory");
  	    console.info(allselect);
  	    
  	    var checkbox = document.getElementsByName("mapFunction");
  	    console.info(allselect.chceked);
	   	if(allselect[0].checked){
		  for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=true;
		  }
	   	}else{
	   		for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=false;
		  }
	   	}
  	}
  	function selectOs(){
  	   var allselect = document.getElementsByName("Os");
  	    console.info(allselect);
  	    
  	    var checkbox = document.getElementsByName("mapOs");
  	    console.info(allselect.chceked);
	   	if(allselect[0].checked){
		  for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=true;
		  }
	   	}else{
	   		for(var i=0;i<checkbox.length;i++){
			  checkbox[i].checked=false;
		  }
	   	}
  	}
  	function FindTestPlan(){
  	 var url ="<%=basePath%>phase4/TestPlanHome";
   		    window.location.href=url;
  	}
   </script>
	
	<br>
	<br>
	<div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-11">
		<div class="row">

		<button id="addTestPlan" class="btn btn-warn" type="button" onclick="FindTestPlan();";>View Saved Test Plan</button>
		<div class="col-offset-lg-9">
		
		<H3 style="color:red">Advanced Search</H3>
		<s:form action="phase4/SearchTestCaseForPlan"  cssClass=" form-inline"  theme="simple" >
            <table class="table">
            	<tr>
            		<td width="120px" style="font-weight:bold">System Brand</td>
            		
            	   <td class="form-group" colspan="6" > 
            	   
            	     <input type="checkbox" name="Brand"  onclick="selectBrand();" />
            	   &nbsp;&nbsp;&nbsp;&nbsp;
            	   <input type="checkbox" name="mapBrand" value="2" id="mapBrand-1"/>
<label for="mapBrand-1" class="checkboxLabel" style="font-weight:normal">ThinkStation</label>
<input type="checkbox" name="mapBrand" value="1" id="mapBrand-2"/>
<label for="mapBrand-2" class="checkboxLabel" style="font-weight:normal">ThinkPad</label>
<input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapBrand" name="__multiselect_mapBrand" value="" /></td>	
             
            	</tr>
            	<tr>
            	  <td style="font-weight:bold">Support OS</td>
            	  <td colspan="6" >
            	  <input type="checkbox" name="Os"  onclick="selectOs();" />
            	&nbsp;&nbsp;&nbsp;&nbsp;
            	   <input type="checkbox" name="mapOs" value="1" id="mapOs-1"/>
<label for="mapOs-1" class="checkboxLabel" style="font-weight:normal">XP</label>
<input type="checkbox" name="mapOs" value="2" id="mapOs-2"/>
<label for="mapOs-2" class="checkboxLabel " style="font-weight:normal">Win7</label>
<input type="checkbox" name="mapOs" value="3" id="mapOs-3"/>
<label for="mapOs-3" class="checkboxLabel" style="font-weight:normal">Win8.x</label>
<input type="checkbox" name="mapOs" value="4" id="mapOs-4"/>
<label for="mapOs-4" class="checkboxLabel " style="font-weight:normal">Win10</label>
<input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapOs" name="__multiselect_mapOs" value="" /></td>
                        <td>
            	</td>
            	</tr>
            	<tr>
            		<td style="font-weight:bold">Category</td>
            		<td colspan = "6">
            		<input type="checkbox" name="selectCategory"  onclick="selectCate();" />
            		&nbsp;&nbsp;&nbsp;&nbsp;
            		<input type="checkbox" name="mapFunction" value="1" id="mapFunction-1"/>
<label for="mapFunction-1" class="checkboxLabel" style="font-weight:normal">1 - Preload Process</label>
<input type="checkbox" name="mapFunction" value="2" id="mapFunction-2"/>
<label for="mapFunction-2" class="checkboxLabel" style="font-weight:normal">2 - OS Basic</label>
<input type="checkbox" name="mapFunction" value="3" id="mapFunction-3"/>
<label for="mapFunction-3" class="checkboxLabel" style="font-weight:normal">3 - Store App</label>
<input type="checkbox" name="mapFunction" value="4" id="mapFunction-4"/>
<label for="mapFunction-4" class="checkboxLabel" style="font-weight:normal">4 - Win32 App</label>
<input type="checkbox" name="mapFunction" value="5" id="mapFunction-5"/>
<label for="mapFunction-5" class="checkboxLabel" style="font-weight:normal">5 - HW and Drivers</label>
<input type="checkbox" name="mapFunction" value="6" id="mapFunction-6"/>
<label for="mapFunction-6" class="checkboxLabel" style="font-weight:normal"> 6 - Multimedia</label>
<input type="checkbox" name="mapFunction" value="7" id="mapFunction-7"/>
<label for="mapFunction-7" class="checkboxLabel" style="font-weight:normal">7 - Recovery</label>
<input type="checkbox" name="mapFunction" value="8" id="mapFunction-8"/>
<label for="mapFunction-8" class="checkboxLabel" style="font-weight:normal">8 - CTO and Special Bid</label>
<input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapFunction" name="__multiselect_mapFunction" value="" /></td>
            		     <td>
            	</td>
            	</tr>
            	<tr>
            	</tr>
            	<tr>
            	<td colspan="7">
            
             			
            </td>
            <td>
               <s:hidden name="searchflag" value="1" />
            		<s:submit cssClass="btn btn-primary" value="Search"></s:submit>   &nbsp;&nbsp;
            </td>
            
            <td>
           <s:if test="#session.userInfo.userName !='tester'">
               <button id="findTestPlan" class="btn btn-warn" type="button" onclick="openSolutionDlg();";>Save to New Test Plan
	      	</button>
	      	</s:if>
            </td>
            </tr>
         
            </table>
          
	      	
          </s:form>
      
          		</div>
          		</div>
		</div>

</div>
</div>
	<div id= "TestPlanForm"  style="magrin-left:20;display:none" >
	  		<input type="button" id="addTomyList" value="add"  />
	  		<input type="button" id="CreateNew" value="New" onclick="createTestPlanDlg();" />
			<table border='1'  class="tablesorter" style="width:400px">
				<thead>
				<tr>
					<th>#</th>
					<th>Test Plan Name</th>
					<th> Date </th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="testPlanList" id="tpList">
			<tr>
				<td><input type="radio"   name="testPlanInstkey" value='<s:property value="#tpList.testPlanInstkey" />' /></td>
				<td><s:property value="#tpList.testPlanName" /></td>
				<td><s:property value="#tpList.createDate" /></td>
			</tr>
			</s:iterator>
			</tbody>
			</table>
</div>

	<div id="NewTestPlanForm" style="display:none">
	<div class="row">
	<div class="col-lg-12">
		<table  class="table table-bordered ">
			<tr>	
				<td>Test Plan Name</td>
				<td>
					<input type="text" name="testPlanName" value=""  id="testPlanName" class="form-control" placeholder="Test Plan Name">
				</td>
			</tr>
			<tr>	
				<td>Project Name</td>
				<td>
					<input type="text" name="projectName" value="" id="projectName" class="form-control" placeholder="Project  Name">
				</td>
			</tr>
				
			<tr>	
				<td>Test Plan Comments</td>
				<td >
					<textarea rows="3" name="testPlanComments" value="" id="testPlanComments" class="form-control" placeholder="Test Plan Comments"></textarea>
				</td>
			</tr>
		</table>
		</div>
	</div>
	
		<DIV class=controls><BUTTON id="submitNewTestPlan" class="btn btn-success">Create Test Plan</BUTTON> </DIV>
	
	</div>
	<div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">
	    <s:if test="#request.pageBean.list.size() > 0">
	    <div >
           <div >
	    	<form id="TCForm" method="post" style="margin: 0px;">
	    	</form>
	    </div>
	    </div>
	 	<s:include value="/jsp/TestPlan/CaseForPlanView.jsp"></s:include>
	 		<div class="col-lg-1">
		</div>
 	</s:if>
 	<s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
 		<h3>There is no result found!</h3>
 	</s:elseif>
		</div>
	</div>

</body>
</html>