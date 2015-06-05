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
    <title>Step Two</title>
   	
  </head>
  
  <body>
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>css/bootstrap-responsive.min.css" rel="stylesheet">
   <script type="text/javascript">
      function backStepOne(){
      		var url = "<%=basePath%>phase4/EditCaseStepOne";
      		window.location.href =url;	
      		
      }

   </script>
   <s:hidden  name="caseInfoInstkey"  value="#session.caseInfoInstkey" />
   <s:hidden   name="caseInstkey" value="#session.caseInstkey" />
 	<div class="row-fluid">
        <div class="col-lg-1">         </div>
 		 <div class="col-lg-10">
            <h4> Step 2 <s:property value="caseName"></s:property></h4>
            
            <p class="text-right">Case Owner:<s:property value="#session.userInfo.userName" /></p>
             <fieldset >
		    <legend>2-Language and HW Support :</legend>
		     <s:fielderror theme="bootstrap"/>
            <s:form action="phase4/StepFinal"  enctype="multipart/form-data" method="post"  theme="simple">
            <table class="table table-bordered">
            	<tr>
            		<td>Language Support</td>
            		<td>Language Comments</td>
            	</tr>
            	<tbody>
            		<tr>
            			 <td>
            			     <table border="1">
                			<s:set value="LanBean" var="langsDB"/>
	     	                 <tr> 
	     	                   <td bgcolor="#FFFF00" align="center">OS</td>
	     	                       <s:iterator value="#langsDB" id='langs' status='st'>
	     	                         <s:if test="#st.index<=16 && #st.index>=2">
	     	                           <td bgcolor="#FFFF00" >
	     	                               <s:textfield  readonly='true' value="%{#langsDB[#st.index].lanValue}"   name="" cssStyle="width:40px;border:0 solid;background-color:#FFFF00" theme="simple"/>
	     	                               <s:hidden value="%{#langsDB[#st.index].languageId}" name="lanMap[%{#st.index}].osDes"/>
	     	                           </td>
	     	                         </s:if>
	     	                      </s:iterator>
	     	         
	     	                 </tr>
	     	                  <tr>
	     	                   <td>Localized</td>
	                           <s:iterator value="#langsDB" id='langs' status='st'>
	     	                        <s:if test="#st.index<=16 && #st.index>=2">	                
	     	                          <td ><s:select readonly='true' value="%{#langsDB[#st.index].languageId}" list="%{#langsDB}"  cssStyle="width:40px;" listKey='languageId' listValue='lanValue' name="lanMap[%{#st.index}].localSet"  theme="simple"/></td>
	     	                        </s:if>
	     	                      </s:iterator>
	     	                  </tr>
	     	                   <tr> 
	     	                   <td  bgcolor="#FFFF00" align="center" >OS</td>
	     	                       <s:iterator value="#langsDB" id='langs' status='st'>
	     	                         <s:if test="#st.index<=31 && #st.index>=17">
	     	                           <td align="center"  >
	     	                               <s:textfield  readonly='true' value="%{#langsDB[#st.index].lanValue}"   name="" cssStyle='width:40px;background-color:#FFFF00'    theme="simple"/>
	     	                               <s:hidden value="%{#langsDB[#st.index].languageId}" name="lanMap[%{#st.index}].osDes"/>
	     	                           </td>
	     	                         </s:if>
	     	                      </s:iterator>
	     	         
	     	                 </tr>
	     	                 <tr>
	     	                  <td>Localized</td>
	     	                      <s:iterator value="#langsDB" id='langs' status='st'>
	     	                        <s:if test="#st.index<=31 && #st.index>=17">	                
	     	                          <td ><s:select readonly='true' value="%{#langsDB[#st.index].languageId}" list="%{#langsDB}"  cssStyle="width:40px;" listKey='languageId' listValue='lanValue' name="lanMap[%{#st.index}].localSet"  theme="simple"/></td>
	     	                        </s:if>
	     	                      </s:iterator>
	     	                  </tr>
	     	                  
            				</table>
            			 
            			 </td>
            			   <td > <s:textarea  id='languageComment' name="languageComment" cssClass="form-control" rows="4" theme="simple"/></td>
            		</tr>       	
            	</tbody>
            </table>
            <h5>HW Releated</h5>
            <table class="table table-bordered">
            	<tr>
            		<td width="50%"> HW requirements</td>
            		<td> HW Comments</td>
            		</tr>
               <tr>
                  <td ><s:textarea id='HWinfo' name='HWinfo' theme="simple" cssClass="form-control"/></td>
	     	        <td ><s:textarea id='HWinfoComment' name='HWinfoComment' cssClass="form-control" theme="simple"/></td>
               </tr>
            </table>
<!--  
               <table class="table table-bordered">
            	<tr>
            		<td width="50%"> Attachment File</td>
            		<td> Attachment Comments</td>
            		</tr>
               <tr>
			       <td > 
						<div class="form-group">
						    <label for="exampleInputFile">附带文件</label>
						    <input type="file" id='Accessory' name='accessory'>
						  </div>
			  </td>
	     	        <td ><s:textarea id='FileComments' name='FileComments' cssClass="form-control" rows ="3" theme="simple"/></td>
               </tr>
            </table>
  -->          
 					<div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar" >
 						<div class="btn-group">
  							<button type="button" class="btn btn-primary" onclick="backStepOne()">Back</button>
  					</div>	  
				     <div class="btn-group">
  						<s:submit type="button" cssClass="btn btn-primary" value="Next"></s:submit>
  					</div>
				    </div>
             </s:form>
            </fieldset>
            </div>
            <div class="col-lg-1">                
          
            </div>
       
       
</div>
     	 
  </body>
</html>
