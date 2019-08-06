<%@ page language="java" pageEncoding="UTF-8"%>
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
   <script type="text/javascript" src="<%=basePath%>js/TestCase.js"> </script>
 	<div class="row-fluid">
   		 <div class="col-lg-12">
   		   <h4> Step 3</h4>
        <p class="text-right">Case Owner:<s:property value="#session.user.username" /></p>
             <fieldset >
		    <legend>3-Case Step :  <s:property value="caseName"></s:property>  </legend>
		     <s:fielderror theme="bootstrap"/>
		      <ui>
             <li style="color:red" >P1 – MUST executing in every testing</li>
             <li style="color:red">P2 – US&Localized APP Testing</li>
             <li style="color:red">P3 – Function Test</li>
             </ui>	 <s:hidden  name="caseInfoInstkey"  value="#session.caseInfoInstkey" />
   			<s:hidden   name="caseInstkey" value="#session.caseInstkey" />
   			   <s:property value="#session.caseInfoInstkey" />
   <s:property value="#session.caseInstkey" />
            <s:form action="SaveCaseEnd" namespace="/phase4" enctype="multipart/form-data" method="post"  onsubmit="return submitValidation();" theme="simple" >
            <table id="TCItems" class="table table-bordered">
            <thead>
               <th>action</th>
            	<th>Level</th>
            	<th>Items</th>
            	<th>Case Steps</th>
            	<th>Expect</th>
            	<th>Attachment</th>
            	<th>Time(Mins)</th>
            	<th>Comments</th>
            </thead>
             <tbody id="tablebody">
                <s:iterator value='items' var='item' status='st'>
 	     			<td>
 	     			        <s:select  list="{'P1','P2','P3'}"  name='items[%{#st.index}].caselevel' theme='simple'/>
 	     			</td>
	     		     	     
	     		    <td>
	     		    		<s:textarea  name='items[%{#st.index}].testitem'  rows='4'  theme='simple'/>
	     		    </td>
	     		     <td>
                           <s:textarea   name='items[%{#st.index}].teststep' rows='4' theme='simple'/>
                     </td>
	     		     <td>
	     		     		<s:textarea  name='items[%{#st.index}].testresult' rows='4'  theme='simple'/>
	     		      </td>
            	  <s:if test='%{items[#st.index].hasPic} !=-1' >
	     		     	   <td>
	     		     	      <div>
	     		     	      <s:file   name='upload' cssStyle="width:250px" theme='simple'/><br/>
	     		     	      <a href='javascript:void(0);' onclick='addInput(this);'><img src='common/images/plus.gif' height='16' width='16' alt='Add'/></a> 
	     		     	      <s:hidden name='items[%{#st.index}].hasPic' />
	     		     	      <s:hidden  name='items[%{#st.index}].picName' />
	     		     	      </div>
	     		     	   </td>
	     		     	   <s:set var="size" value='#size+1' />
	     		     	  </s:if>
	     		     	  <s:else>
            	     	   <td>
	     		     	      <div>
	     		     	       <s:file id='upload'  name='upload' cssStyle="width:250px" theme='simple'/> 
	     		     	       <s:hidden  name='items[%{#st.index}].hasPic' />
	     		     	       <s:hidden name='items[%{#st.index}].picName' />
	     		     	       </div>
	     		     	   </td>
	     		     	  </s:else>
	     		     	  <td>
	     		     	  		<s:textarea rows='1' cols='4'   name='items[%{#st.index}].steptime' onclick='recalTime();' cssStyle="width:60px" theme='simple'/>
	     		     	  	</td>
	     		     	  <td>
	     		     	  		<s:textarea id='Comments'  name='items[%{#st.index}].comments' rows='4' cols='40' cssStyle="width:300px" theme='simple'/>
	     		     	  </td>
	     		     	  </s:iterator>
              	</tbody>
            </table>
            
 					<div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar" >
 						<div class="btn-group" >
 							<button type="button" class="btn btn-primary" id='AddRow' onclick='var abc=null;addRow(abc)'>Add a row</button>
 						</div>
 						
  					 
				     <div class="btn-group">
  						<s:submit type="button" cssClass="btn btn-primary" value="Save"></s:submit>
  					</div>
				    </div>
        
       </s:form>
            </div>
</div>
     	 
  </body>
</html>
