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
  <script type="text/javascript" src="<%=basePath %>jslib/jquery.tablednd.js"></script>
  <style type="text/css">
  tr.myDragClass td{
  		background-color:red;
  		color:#FFFF00;
  }
  </style>
  <script type='text/javascript'>
  $(document).ready(function() {  
	        $("#test").tableDnD({
	        	onDragClass:'myDragClass',
	        	 onDrop: function(){
	        	   		changeID();
	           }
	        });         
	    });
	  function deleteRow(obj){
		  if(confirm('Are you sure you are going to Delete this Case?')){
				  removeRow(obj);
		  }
		}
function removeRow(obj){
	   var testTable = document.getElementById("test");
	   var tbodies = testTable.tBodies;
	   var aBody=null;
	   if(tbodies){
		   	aBody = tbodies[0];
		   	 if(aBody){
		   		 console.info(aBody);
		   		 aBody.removeChild(obj.parentNode.parentNode);// td,tr remove this tr
		   		changeID();
		   	 }
	   }
  }
function changeID(){
	   console.info("start");
	  var testTable = document.getElementById("test");
	  for (var i=1; i < testTable.rows.length; i++){		    
		  var value =  testTable.rows.item(i).cells[0].innerHTML;
		   //console.info(value);
		  var newstr = "value='"+i+"'";
		  var reg=new RegExp("value=\".*?\"","i"); 
		  value=value.replace(reg,newstr);
		  console.info(value);
		  testTable.rows.item(i).cells[0].innerHTML = value;
		 }

  }
</script>
    <div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">
	   <s:form class="form-horizontal" id="editForm" action="phase4/UpdateTestPlan" style="margin: 0;">
					<s:submit type="button" cssClass="btn btn-primary" value="Update"></s:submit>		
     <s:hidden name="testPlanInstkey" value="%{testPlanInstkey}"></s:hidden>
	     <table class="table table table-bordered table-striped" id="test" style="table-layout:fixed">
	     		 
	     	   <br>
    		<thead>
    		<tr>
    		     <th class="info" width="4%"> </th>
    			<th class="info" width="6%">CaseID</th>
    			<th class="info" width="13%">Category</th>
    			<th class="info" width="30%">CaseName</th>
    			<th class="info" width="6%">Version</th>
    			<th class="info">OS</th>
    			<th class="info" width="15%">Brand</th>
       			
    			
				<th class="info" width="10%">Owner</th>
        		</tr>
    		</thead>
    		<tbody>
    		<s:iterator value="#request.contentList"  status="st">	
    		<tr>
    		<td style="text-align:center">
    		<a href="javascript:void(0);" onclick='deleteRow(this)'>
						<img src="<%=basePath%>img/cancel.png" width="15" height="15">
					</a><s:hidden style="width:30%;height:26px"  name="conList[%{#st.index}].tpOrder"  value='%{#st.index+1}' />	</td>
    		</td>
    			<td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a href='${pageContext.servletContext.contextPath}/phase4/ShowTestCaseDetail?testCase.caseInstkey=<s:property  value="%{contentList[#st.index].caseInstkey}" />' >    				
    			
    					<s:property  value="%{contentList[#st.index].caseCode}" />
    			</a> 
    			</td> 
    			<td><s:property  value="%{contentList[#st.index].functionCato}" /></td>
    			<td><s:hidden name="conList[%{#st.index}].caseInstkey" value="%{contentList[#st.index].caseInstkey}"></s:hidden>
    			<s:property  value="%{contentList[#st.index].caseName}" /></td>
				<td><s:property  value="%{contentList[#st.index].Version}" /></td>
    			<td><s:property  value="%{contentList[#st.index].osCato}" /></td>
    			<td><s:property  value="%{contentList[#st.index].brandCato}" /></td>
				<td><s:property  value="%{contentList[#st.index].Owner}" /></td>
    		</tr>
    		</s:iterator>
    		</tbody>
    	</table>
    	</s:form>
    </div>
		</div>
	    <div class="col-lg-1">
	    </div>
		
	</div>
  </body>
</html>
