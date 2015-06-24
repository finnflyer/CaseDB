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
    <title>Step Final</title>
</head>
  
  <body>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
   <script type="text/javascript" src="<%=basePath%>common/js/jquery.min.js"> </script>
	<script type="text/javascript" src="<%=basePath%>common/js/EditTestCase.js"> </script>
	 <script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery-ui-1.7.3.custom.js"></script>
   	<script type="text/javascript" src="<%=basePath%>plugs/jQueryUI/ui/jquery.blockUI.js"></script>
   	<script type="text/javascript" src="<%=basePath%>jslib/jqBootstrapValidation.js"></script>
   	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugs/jQueryUI/themes/base/ui.all.css">
 	<div class="row-fluid">
   		 <div class="col-lg-12">
   		  <h4 style="color:red;">Case Name :   <s:property  value="%{testCase.caseName}" /></h4>
          <h4> Step 2 </h4>
        <p class="text-right">Case Owner:<s:property value="#session.userInfo.userName" /></p>
             <fieldset >
		    <legend>3-Case Step :</legend>
		     <s:fielderror theme="bootstrap"/>
		       <ui>
             <li style="color:red" >P1 – MUST executing in every testing</li>
             <li style="color:red">P2 – US&Localized APP Testing</li>
             <li style="color:red">P3 – Function Test</li>
             </ui>	 <s:hidden  name="caseInfoInstkey"  value="#session.caseInfoInstkey" />
   			<s:hidden   name="caseInstkey" value="#session.caseInstkey" />
            <s:form action="phase4/UpdateCaseStepFinal"  enctype="multipart/form-data" method="post"  onsubmit="return submitValidation();" theme="simple" >
            <table id="TCItems" class="table table-bordered" style="table-layout:fixed">
            <thead>
               <th style="width:4%;"></th>
            	<th style="width:4%;">Level</th>
            	<th style="width:20%;">Items</th>
            	<th style="width:20%;">Case Steps</th>
            	<th style="width:20%;">Expect</th>
            	<th style="width:10%">Pics</th>
            	<th style="width:4%">Time</th>
            	<th>Comments</th>
            </thead>
             <tbody id="tablebody">
                <s:iterator value="testcasecontent"  status="st">
                <tr>
                <td style="width:8%">
	     		     	     <a href='javascript:void(0);' onclick='removeRow(this);'><img src='common/images/minus.gif' height='16' width='16' alt='Delete'/></a>
	     		     	     <br/>
	     		     	     <a href='javascript:void(0);' onclick='addRow(this);'><img src='common/images/plus.gif' height='16' width='16' alt='Insert' /></a>
	     		     	     <br/>
	     		     	     <a href='javascript:void(0);' onclick='moveUp(this);'><img src='common/images/arrow_up.gif' height='16' width='16' alt='Up' /></a>
	     		     	     <br/>
	     		     	     <a href='javascript:void(0);' onclick='moveDown(this);'><img src='common/images/arrow_down.gif' height='16' width='16' alt='Down'/></a>
	     		     	    </td>
 	     			<td >
 	     			        <s:select  list="{'P1','P2','P3'}" value='%{testcasecontent[#st.index].caseLevel}' cssStyle="width:100%" name='testcasecontent[%{#st.index}].caseLevel' theme='simple'/> 
 	     			</td>
	     		    <td>
	     		    		<s:textarea  value='%{testcasecontent[#st.index].testItem}' name='testcasecontent[%{#st.index}].testItem'  rows='4'  cssStyle="width:100%" theme='simple'/> 
	     		    </td>
	     		     <td>
                           <s:textarea  value='%{testcasecontent[#st.index].testStep}' name='testcasecontent[%{#st.index}].testStep' rows='4'  cssStyle="width:100%" theme='simple'/> 
                     </td>
	     		     <td>
	     		     		<s:textarea  value='%{testcasecontent[#st.index].testResult}' name='testcasecontent[%{#st.index}].testResult' rows='4' cssStyle="width:100%"  theme='simple'/>
	     		      </td>
	     		      <td style="word-wrap:break-word">
						<div id="photoLinkListDiv">
	     		   	  		<s:set value="testcasecontent.CasePics" var="CasePics"/>
	     		   	  		<s:iterator value="CasePics" id="cp">  
	     		   	  			<s:a id="pic%{#cp.pictureInstkey}" href="javascript:void(0);" onclick="showPhoto('%{#cp.pictureInstkey}');return false;"> <s:property value="fileName"  /> </s:a>
	     		   	  			 <s:a id="del%{#cp.pictureInstkey}" href='javascript:void(0);' onclick="delphoto('%{#cp.pictureInstkey}');return false;"><img src='common/images/minus.gif' height='16' width='16' alt='Delete'/></s:a>
	     		     	         <s:hidden name="oldpic_path"  value="%{filePath}" />
	     		   	  			 <s:hidden name="oldpic_order"  value="0" />
	     		   	  		</s:iterator>
    						</div>
    						<div id="photoDlg" title="Pic" style="display: none;"></div>	
							<div id="uploadfile" title="fileload">
							  <input type='file' name='upload' />
							  <input name="picName" type="hidden" value="-1">
							  <input name="hasPic"  type="hidden" value="">&nbsp; &nbsp;
							  <a href='javascript:void(0);' onclick='addInput(this);'>
							  <img src='common/images/plus.gif' height='16' width='16' alt='Add'/>
							    </a>&nbsp; &nbsp;
							    <a href='javascript:void(0);' onclick='removeLink(this);'>
							       <img src='common/images/minus.gif' height='16' width='16' alt='minus'/>
							    </a>
							</div>
    						<div id="photoDlg"  style="display: none;">
							</div>	
							</td>
	     		     	  <td>
	     		     	  		<s:textarea  value='%{testcasecontent[#st.index].stepTime}' name='testcasecontent[%{#st.index}].stepTime' cssStyle="width:100%"  theme='simple'/> 
	     		     	  	</td>
	     		     	  <td>
	     		     	  		<s:textarea id='Comments' value='%{testcasecontent[#st.index].Comments}' name='testcasecontent[%{#st.index}].comments'  cssStyle="width:100%" rows='4'  theme='simple'/> 
	     		     	  </td>
	     		     	  </s:iterator>
	     		     	  </tr>
              	</tbody>
            </table>
            
 					<div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar" >
 						<div class="btn-group" >
 							<button type="button" class="btn btn-primary" id='AddRow' onclick='var abc=null;addRow(abc)'>Add a row</button>
 						</div>		 
				     <div class="btn-group">
  						<s:submit type="button" cssClass="btn btn-primary" value="Update"></s:submit>
  					</div>
				    </div>
        
       </s:form>
            </div>
</div>
     	 
  </body>
</html>
