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
    <title>TestCaseDetail</title>
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
  		  var lan=document.getElementById("lanCom");
  		var lanCom=lan.innerHTML;
  	
  		if(lanCom!=""){
  				lanCom = lanCom.replace(regx," <br/>");
  				lan.innerHTML = lanCom;
  		}
    	var testTable=document.getElementById("TCItems");
    	var rows     = testTable.rows;
       	 for(var i=1;i<rows.length;i++){
    		 for(var j=0;j<rows[i].cells.length;j++){
    			 if(j==4){
    			 }
    			 else{   	
    					 var temp=rows[i].cells[j].innerHTML;					
        				 var regBr=new RegExp("&lt;br&gt;","g");
        				 temp = temp.replace(regBr,"<br\>");
        				 rows[i].cells[j].innerHTML = temp; 
    			 }
    		 }
    	 }

    	
    }
   function showPhoto(path){
   			console.info(path);
		   var html = "";
		   html +="<img src='download/getPictureForAjax?photoPath="+path+"' onload='javascript:DrawImage(this,960,540)' alt='800 X 600'/>";
		   console.info(html);
		   $("#photoDlg").html(html);
		   console.info("test");
		   showPhotoDlg();
	}
	// show image Dlg
	function showPhotoDlg(){
		$("#photoDlg").dialog({
			bgiframe: true,
			height	: 630,
			width	: 1000,
			modal	: true
		});
	    console.info("dialog");
		$('#photoDlg').dialog('open');

	};	
	var flag=false;
	function DrawImage(ImgD,iwidth,iheight){
		var image=new Image();
		image.src=ImgD.src;
		image.width = ImgD.width;
		image.height = ImgD.height;
		console.info("aaa"+image.width);
		if(image.width>0 && image.height>0){
				flag=true;
					if(image.width/image.height>= iwidth/iheight){
					   console.info("a");
						if(image.width>iwidth){ 
								ImgD.width=iwidth;
								ImgD.height=(image.height*iwidth)/image.width;
								
						}else{
								ImgD.width=image.width; 
								ImgD.height=image.height;
							 }
					ImgD.alt=image.width+"×"+image.height;
					}
				else{
					console.info("no a");
						if(image.height>iheight){ 
								ImgD.height=iheight;
								ImgD.width=(image.width*iheight)/image.height; 
							
						}else{
								ImgD.width=image.width; 
								ImgD.height=image.height;
								
					}
					ImgD.alt=image.width+"×"+image.height;
				}
		}
		console.info(ImgD.alt);
		console.info(ImgD.width);
		console.info(ImgD.height);
		flag=false;
	} 
	
	  function EditCaseStepOne(){
      		var url = "<%=basePath%>phase4/EditCaseStepOne";
      		window.location.href =url;	
      }
      function DeleteCase(){
      console.info("start to delete");
      var key= "<s:property value='testCase.caseInstkey' />";
      console.info(key);
      if(confirm('Are you sure you are going to Delete this Case?')){
		  		var url = "<%=basePath%>phase4/DeleteCase?caseInstkey="+key;
      		window.location.href =url;	
			}	
    
      }
	
</script>
<div class="col-lg-offset-9  " class="btn-toolbar" role="toolbar" >
<s:if test="#session.userInfo.userName !='tester' ">
	<s:if test="#session.userInfo.Role =='Case Owner' || #session.userInfo.Role =='Admin'">
				  <div class="btn-group">
  					<button id="EditCase" class="btn btn-warn" type="button" onclick="EditCaseStepOne()";>Edit Test Case </button>
	      		
  					</div>	 
  					<div class="btn-group">
  						<button id="DeleteTestCase" class="btn btn-warn" type="button" onclick="DeleteCase();">Delete Test Case
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
 		 
          <legend style="font-size:16px">1 - Basic Information:</legend>
		    <table class="table table table-bordered ">
          		   <tr>
          			<td class="active" width="130px">Category:</td>
		    		<td width="200px"><s:property value="testCaseInfo.funcCato"  /></td>	 
           		 	<td class="active" width='130px'>Case Name:</td>
		    		<td > <s:property  value="testCase.caseName" /></td>
		    		<td class="active" width="130px">Case Owner:</td>
		    		<td > <s:property  value="testCase.owner" /></td>
                   </tr>
 				    <tr>
				      <td class="active">SupportOS</td>
				      <td > <s:property value="testCaseInfo.osCato"  /></td>
				      <td class="active">System Brand</td>
				      <td > <s:property   value="testCaseInfo.brandCato" /></td>
				      <td class="active">Case ID</td>
                     <td  > <s:property value="testCase.caseCode"     /></td>
				     </tr>
		
				    <tr>
                        <td class="active">Version</td>
                     <td  ><s:property value="testCase.version"  /></td>	
                     </tr>
                     <tr>
				    <td class="active">Modify Reason:</td>
		    		<td id="history" colspan="7" > <s:property value="testCaseInfo.modifyReason" /></td>
				    </tr>
            </table>
              <legend style="font-size:16px">2 - Language and HW Support :</legend>
                <table class="table table-bordered">
            	<tr>
            		<td class="active">language Support</td>
            		<td class="active" width='40%'>Language Comments</td>
            	</tr>
            	<tbody>
            		<tr>
            			 <td>
            			   <table class="table table-condensed table-bordered">
                			<s:set value="LanBean" var="langsDB"/>
	     	                 <tr> 
	     	                   <td style="font-size:14px" bgcolor="#E4EEF9" align="center" width="30px" >OS</td>
	     	                       	<s:iterator value="LanBean" id="LanBean" status="st" >
	     	      							 <s:if test="#st.index<=16 && #st.index>1">
	     	                           			<td align="center"  bgcolor="#E4EEF9" width="40px">
	     	                               			<p  style="font-size:14px"><s:property value="lanValue"/></p>
	     	                              		 </td>
	     	                        		 </s:if>
	     	      						</s:iterator>
	     	                 </tr>
	     	                  <tr>
	     	                   <td align="center">Language</td>
	     	                      <s:iterator value="CaseLan" id="CaseLan" status="st">
	     	      							<s:if test="#st.index<15 && #st.index>=0">
	     	      							    <td align="center">
	     	      							    <s:property value="lanValue"/>
	     	      							    </td>
	     	      							</s:if>
								</s:iterator>
	     	                  </tr>
	     	                   <tr> 
	     	                 <td style="font-size:14px" bgcolor="#E4EEF9" align="center" width="30px" >OS</td>
	     	                       <s:iterator value="LanBean" id="LanBean" status="st">
	     	                         <s:if test="#st.index<=31 && #st.index>=17">
	     	                           	<td align="center"  bgcolor="#E4EEF9" width="40px">
	     	                              <s:property value="lanValue"/>
	     	                           </td>
	     	                         </s:if>
	     	                      </s:iterator>
	     	         
	     	                 </tr>
	     	                 <tr>
	     	                  <td align="center">Language</td>
	     	                      <s:iterator value="CaseLan" id="CaseLan" status="st">
	     	                        <s:if test="#st.index<=33 && #st.index>=15">	                
	     	                          <td align="center" ><s:property value="lanValue"/></td>
	     	                        </s:if>
	     	                      </s:iterator>
	     	                  </tr>
            				</table>           			 
            			 </td>
            			   <td id='lanCom'> <s:property value="testCaseInfo.languageComment"  /></td>
            		</tr>       	
            	</tbody>
            </table>
          
            <table class="table table-bordered">
            	<tr>
            		<td class="active" > HW requirements</td>
            		<td class="active" width="40%"> HW Comments</td>
            		</tr>
               <tr>
                    <td ><s:property value="testCaseInfo.hardwareInfo"  /></td>
	     	        <td ><s:property value="testCaseInfo.comments"  /></td>
               </tr>
            </table>  
             <legend style="font-size:16px">3 - Test Items :</legend>
             <ui>
             <li style="color:red" >P1 – MUST executing in every testing</li>
             <li style="color:red">P2 – US&Localized APP Testing</li>
             <li style="color:red">P3 – Function Test</li>
             </ui>
              <div class="row">
            	<div class="col-lg-offset-10" style="color:red">
            			Total Time: <s:property  value="testCaseInfo.executeTime" />mins
            			
            	</div>
 
	         <table id="TCItems" class="table table-bordered " style="table-layout:fixed">
	           	   <thead>
	     			<tr>
	     			<th style="width:5%;background-color: #f5f5f5;font-size:10pt;">Level</th>
	     			<th style="width:12%;background-color: #f5f5f5;font-size:10pt;">Test Item</th>
	     			<th style="width:24%;background-color: #f5f5f5;font-size:10pt;">Test Steps/Description</th>
	     			<th style="width:24%;background-color: #f5f5f5;font-size:10pt;">Expect Results</th>
	     			<th style="width:10%;background-color: #f5f5f5;font-size:10pt;">Pictures</th>
	     			<th style="width:4%;text-align:center;background-color: #f5f5f5;font-size:10pt;">Time</th>
	     			<th style="background-color: #f5f5f5;font-size:10pt;" id='comHead'>Comments</th>
	     			</tr>	     		
	     		   </thead>
	     		   <tbody>
	     		   		<s:iterator value="testCaseContents"  status="st">
	     		   	  	<tr>     	
	     		   	  	  	<td style="color:red;text-align:center"><s:property value="caseLevel" /></td>
	     		   	  		<td style="font-weight:normal" ><p style="font-weight:normal"> <s:property value="testItem" /></p></td>
	     		   	  		<td > <s:property value="testStep" /></td>
	     		   	  		<td style="word-wrap:break-word"><s:property value="testResult" /></td>
	     		   	   	  	<td style="color:darkblue;word-wrap:break-word">
	     		   	  		<div id="photoLinkListDiv">
	     		   	  		<s:set value="testCaseContents.CasePics" var="CasePics"/>
	     		   	  		<s:iterator value="CasePics" id="cp">  
	     		   	  			<s:a href="javascript:void(0);" onclick="showPhoto('%{#cp.pictureInstkey}');return false;"> <s:property value="fileName"  /> </s:a>
	     		   	  		</s:iterator>
    						</div>
    						<div id="photoDlg"  style="display: none;">
							</div>	
	     		   	  		<td ><pre><s:property value="stepTime" /></pre></td>
	     		   	  		<td style="word-wrap:break-word" long='<s:property value="comments" />'  short=''><s:property value="comments" /></td>	   		   	  		
	     		   	  	</tr>
	     		   	  </s:iterator>
	     		   
	     		   </tbody>
	         </table>
	     		
          </div>
   
</div>
    
	
</body>
</html>