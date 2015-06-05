<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

 <head>
    <script type="text/javascript" src="common/js/jquery-1.6.1.min.js"> </script>
    <script language="javascript" src="plugs/uploadify/swfobject.js"></script>
	<script language="javascript" src="plugs/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script  type="text/javascript" src="common/js/marcus.js"></script>
    <base href="<%=basePath%>">
    <title>TestCaseDetail</title>
	<meta http-equiv="description" content="CTD Home">	
	<style type="text/css">
		textarea {			
			overflow: auto;
			border: 1px solid black ;
		}
		
	</style>	
	<link rel="stylesheet" type="text/css" href="common/css/ctd_style.css">
	<link rel="stylesheet" type="text/css" href="plugs/jQueryUI/themes/tablesorter/style.css">
	<link href="plugs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
  </head>  
<body>
<div style="float:right;position:relative;left:45">

 <img  src='common/images/lenovo.png'  height='60' width='200' />

</div>
<br/>
<br>
<br>
   <div style="margin-left:20">You are here: <s:a href="index.jsp" cssClass="blodfont">CTD Home</s:a> &gt; <s:a href="chilltest/pressTCHomeAction" cssClass="blodfont">Test Case Search</s:a></div>
 

  
<script type="text/javascript">
  $(document).ready(
		   function()
		   {
			  $.ajaxSetup ({cache:false});
			   $("#Introduction").click(function(){$("#Introductiontable").slideToggle();});
			   $("#BasicInfo").click(function(){$("#BasicTable").slideToggle();});
		       $("#SupportInfo").click(function(){$("#SupportTable").slideToggle();});
		       $("#his").click(function(){$("#histable").slideToggle();});
		       $("#TCItems").tablesorter({ 
			        sortList:[], 
			        widgets: ['zebra'],
			        headers:{0:{sorter:false},1:{sorter:false},2:{sorter:false},
			        	3:{sorter:false},4:{sorter:false},5:{sorter:false},6:{sorter:false}
			        }
			    }); 
		       
		 	  $("textarea").each(		 			  
			          function(){
						  //this.value="chill";
			        	  this.value=replaceBR(this.value);
			        	  }
			   );
		       var time=0;
		       $('tbody textarea[name$="Step_time"]').each(function(){
		    	     time=time+parseInt(this.value);
		    	   
		       });
		       $('input[name="testcase.Excute_time"]').each(function(){this.value=time});
			    //replaceItemTableBlank();
		   }
		);
  
  function  replaceItemTableBlank(){
    	var testTable=document.getElementById("TCItems");
    	var rows     = testTable.rows;
    	 for(var i=1;i<rows.length;i++){
    		 for(var j=0;j<rows[i].cells.length;j++){
    			 
    			 if(j==4){
    			 }
    			 else{   				 
    					 var temp=rows[i].cells[j].innerHTML;
						 console.info(temp);
						 temp = temp.replace(/nbsp;/ig," "); 
						 console.info(temp);
        				 rows[i].cells[j].innerHTML = temp; 
    			 }
    		 }
    	 }

    	
    }
  
  var globalCount=0;
  var globalresult;
  function swapNode(node1,node2)
	  {
		  var _parent=node1.parentNode;
		  var _t1=node1.nextSibling;
		  var _t2=node2.nextSibling;
		  if(_t1)_parent.insertBefore(node2,_t1);
		  else _parent.appendChild(node2);
		  if(_t2)_parent.insertBefore(node1,_t2);
		  else _parent.appendChild(node1);
		 }
	 function moveUp(_a)
	  {
		  var _row=_a.parentNode.parentNode;
		  if(_row.previousSibling)swapNode(_row,_row.previousSibling);
	  }
	 function moveDown(_a)
	  {
		  var _row=_a.parentNode.parentNode;
		  if(_row.nextSibling)swapNode(_row,_row.nextSibling);
	  }
	 function reCalcPicLocation()
	 {
		 
		 var count=0;	
		 var Case_level=new Array();
		 var TestItem=new Array();
		 var Desc=new Array();
		 var Test_result=new Array();
		 var hasPics=new Array();
		 var picName=new Array();
		 var uploads=new Array();
		 var accessory=new Array();
		 var Step_time=new Array();
		 var comments=new Array();
		 var oldpic_path=new Array();
		 var oldpic_order=new Array();
		 var vars=document.getElementsByTagName("select");
		 for (var x=0;x<vars.length;x++)
		 {
			 if (typeof vars[x].name== "undefined")
			 {
				 continue;
			 }
			 
			 if (vars[x].name.indexOf("Case_level")!=-1) 
			 {
	
				 Case_level.push(vars[x]);
			 }
		 }
		// alert("caseleve:"+Case_level.length);
		 vars=document.getElementsByTagName("textarea");
		 for (var x=0;x<vars.length;x++)
		 {
			 if (typeof vars[x].name== "undefined")
			 {
				 continue;
			 }
			 
			 if (vars[x].name.indexOf("TestItem")!=-1) 
			 {
	
				 TestItem.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("Step_des")!=-1) 
			 {
	
				 Desc.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("Test_result")!=-1) 
			 {
	
				 Test_result.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("Step_time")!=-1) 
			 {
	
				 Step_time.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("Comments")!=-1) 
			 {
	
				 comments.push(vars[x]);
			 }
		 }
		// alert(TestItem.length+" "+Desc.length+" "+Test_result.length+" "+Step_time.length+" "+comments.length);
		 var inputs=document.getElementsByTagName("input");
		 var str="";
		 
		 for (var x=0;x<inputs.length;x++)
		 {
			 if (typeof(inputs[x].name)== "undefined")
			 {
				 continue;
			 }
			 if (inputs[x].name.indexOf("hasPic")!=-1) 
			 {
				 hasPics.push(inputs[x]);
			 }
			 
			 if (inputs[x].name.indexOf("upload") !=-1  )
			 {			
				 uploads.push(inputs[x]);
			 }
			 if (inputs[x].name.indexOf('accessory') !=-1)
			 {
				 accessory.push(inputs[x]);
			  }
			 if (inputs[x].name.indexOf("picName") !=-1)
			 {
				 picName.push(inputs[x]);
			 }
			 if (inputs[x].name.indexOf("oldpic_path") !=-1)
			 {
				 oldpic_path.push(inputs[x]);
			 }
			 if (inputs[x].name.indexOf("oldpic_order") !=-1)
			 {
				 oldpic_order.push(inputs[x]);
			 }
		 }
		 
		
		// alert("uploads:"+uploads.length);
		// alert(hasPics.length+" "+uploads.length+" "+picName.length);
		 for (var i=0;i<Case_level.length;i++)
		 {
			// alert("++"+Case_level.length+"::"+TestItem.length+"::"+Desc.length+"::"+Test_result.length+"::"+Step_time.length+"::"+comments.length);
			 Case_level[i].name="tcupdatebean.tccontent["+i+"].Case_level";
			 Case_level[i].id="tcupdatebean.tccontent["+i+"].Case_level";
			 TestItem[i].name="tcupdatebean.tccontent["+i+"].TestItem";
			 TestItem[i].id="tcupdatebean.tccontent["+i+"].TestItem";
			 Desc[i].name="tcupdatebean.tccontent["+i+"].Step_des";
			 Desc[i].id="tcupdatebean.tccontent["+i+"].Step_des";
			 Test_result[i].name="tcupdatebean.tccontent["+i+"].Test_result";
			 Test_result[i].id="tcupdatebean.tccontent["+i+"].Test_result";
			 Step_time[i].name="tcupdatebean.tccontent["+i+"].Step_time";
			 Step_time[i].id="tcupdatebean.tccontent["+i+"].Step_time";
			 comments[i].name="tcupdatebean.tccontent["+i+"].Comments";
			 comments[i].id="tcupdatebean.tccontent["+i+"].Comments";
			 
			// alert(i+"::"+Case_level[i].name+":"+TestItem[i].name+":"+Desc[i].name+":"+Test_result[i].name+":"+hasPics[i].name+":"+Step_time[i].name);
		    // alert(uploads[i].name);
		 }
		 //alert("uploads:"+hasPics.length);
		 
		 for (var i=0;i<uploads.length;i++)
		 {
			 // <input>  <div> <td>  <tr>
			 var index=uploads[i].parentNode.parentNode.parentNode.sectionRowIndex ; //Tr index in tbody ,based on 0 compare to oder;
			 hasPics[i].name="tcupdatebean.tccontent["+index+"].hasPic";
             picName[i].name="tcupdatebean.tccontent["+index+"].picture_path";
             uploads[i].name="upload";
			 if (uploads[i].value !="")
			 {
				 hasPics[i].value=index;
				 picName[i].value=uploads[i].value;
				
			  }else
			 {
				 hasPics[i].value=-1;
			 }
		  }
		 
		 for (var i=0;i<oldpic_path.length;i++)
		 {
			 // <input> <div><div>  <td> <tr>
			 var index=oldpic_path[i].parentNode.parentNode.parentNode.parentNode.sectionRowIndex ; //Tr index in tbody ,based on 0 compare to oder;
			
			 oldpic_path[i].name="oldpic_relation["+i+"].oldpic_path";
			 oldpic_order[i].name="oldpic_relation["+i+"].oldpic_order";
			 oldpic_order[i].value=index;
		  }
		 return 1;
	 }
     var id=1;
	function addRow(insertOne) { //insert row
		      
			  if(insertOne==1){
			  insertOne=null;
			  }
		      var body = document.getElementById("tablebody");
		      var testTable=document.getElementById("TCItems");
		   
		    if(body)
		     {
		        var row = document.createElement("tr");
		        if (insertOne==null)
		        {
		        	
		        	body.appendChild(row);
		        }else
		        {
		        	body.insertBefore(row,insertOne.parentNode.parentNode);
		        }
		        var case_level=document.createElement("select");
		        case_level.name="Case_level";
		        
		     
				var pic_token = document.createElement('input');
				pic_token.name = 'hasPic';
		        pic_token.type="hidden";		       
		        pic_token.value=-1;
		        var picName=document.createElement("input");
				picName.name="picName";
		        picName.type="hidden";
		        picName.value="";
		        
		        var executetime=document.createElement("textarea");
		        executetime.cols="4";
		        executetime.rows='1';
		        executetime.defaultValue="10";
		        executetime.value="10";
		       
		        
		        for (var j=1;j<4;j++)
		        {
		        	  var P=document.createElement("option");
		        	  P.value="P"+j;
			          P.label="P"+j;
			          case_level.appendChild(P);
		         }
		        
		       
		        for(var i = 0 ; i < testTable.tHead.rows[0].cells.length; i++)
		        {	        	
		        	
		           var cell= document.createElement("td"); 
		           row.appendChild(cell);	          
		           var textfield=document.createElement("textarea");   
		          
		           textfield.rows=4;
		           
		           var str = "";			
		           if(i == 0) 
		           {
		               str = "<a href='javascript:void(0);' onclick=\"removeRow(this);\"><img src='common/images/minus.gif' height='16' width='16' alt='Delete'/></a><br/><a href='javascript:void(0);' onclick=\"addRow(this);\"><image src='common/images/plus.gif' height='16' width='16' alt='Insert'/></a><br/>";
		               str =str+ "<a href='javascript:void(0);' onclick=\"moveUp(this);\"><img src='common/images/arrow_up.gif' height='16' width='16' alt='Up'/></a><br/><a href='javascript:void(0);' onclick=\"moveDown(this);\"><img src='common/images/arrow_down.gif' height='16' width='16' alt='Down'/></a>";
		               cell.innerHTML = str; 
		               cell.appendChild(pic_token);
		               cell.appendChild(picName);
		               
		           }
		           if (i==1)
		           {
		        	   
		        	   cell.appendChild(case_level);
		           }
		           if (i==2)
		           {
		        	   
		        	   textfield.setAttribute("id","test"+id);
		        	   textfield.name="TestItem";
		        	   var test="test"+id;
			           cell.appendChild(textfield);       
			           var te=document.getElementById(test);		        	  	
		        	  	te.style.width="200";
		        	  	
		           }
		           if (i==3)
		           {
		        	  
		        	   textfield.name="Step_des";
		        	   textfield.setAttribute("id","des"+id);
			           cell.appendChild(textfield);
			           var test="des"+id;
			           var te=document.getElementById(test);
		        	  	te.style.width="380";
		        	  	
		           }
		           if (i==4)
		           {
		        	  
		        	   textfield.name="Test_result" ; 
		        	   textfield.setAttribute("id","result"+id);
			           cell.appendChild(textfield);
			           var test="result"+id;
			           var te=document.getElementById(test);		        	
		        	  	te.style.width="240";
		           }
		           if (i==5)
		           {
		        	   //Upload pic
		        	   //Why i use innerHTML to assign <input> to cell?? Marcus,2012/06/09, because created <input> by createElement("input") can't be assigned name property. You can google this proplem,it's weird happending on my IE8
		        	   cell.innerHTML="<div><input type='file' name='upload' />&nbsp; &nbsp;<a href='javascript:void(0);' onclick='addInput(this);'><img src='common/images/plus.gif' height='16' width='16' alt='Add'/></a>&nbsp; &nbsp;<a href='javascript:void(0);' onclick='removeLink(this);'><img src='common/images/minus.gif' height='16' width='16' alt='minus'/></a></div>";
		           }
		           if (i==6)
		           {
		        	   executetime.name="Step_time";
		        	   cell.appendChild(executetime);
		           }
		           if (i==7)
		           {
		        	   textfield.name="Comments";		        	  
		        	   cell.appendChild(textfield);
		        	   textfield.setAttribute("id","comm"+id);
		        	   var test="comm"+id;
			           var te=document.getElementById(test);		        	
		        	  	te.style.width="180";
		           } 
		           	         
		       }
		         
		        
		     }
		     
		    id=id+1;
     	   
  }
		
		  function removeRow(obj) { //delete row
		     var testTable = document.getElementById("TCItems");
		     var bodies = testTable.tBodies;
		     var aBody = null;
		     if(bodies)
		     {
		        aBody = bodies[0];
		        if(aBody)
		        {
		           aBody.removeChild(obj.parentNode.parentNode); //Because <a>--<td>--<tr>
		        }
		       
		     }
		  }
		  function removeLink(obj) //<td><div><a><img></img></a></div></td> ,i want to remove <a> and its descendants and siblings starting from <a>
		  {
			  if(obj.parentNode.nextSibling ==null && obj.parentNode.previousSibling ==null){return;}
			  obj.parentNode.parentNode.removeChild(obj.parentNode);
		  }
		  function addInput(obj)  //obj must be <a> 
		  {
			  var ele=document.createElement("br");
			  var td=obj.parentNode.parentNode;  
			  td.appendChild(ele);
			  ele=document.createElement("div");
			  td.appendChild(ele);
			  var div=ele;
			  ele=document.createElement("input");
			  ele.name="upload";
			  ele.type='file';
			  div.appendChild(ele);
			  //+ link
			  ele=document.createElement("span");
			  ele.innerHTML='&nbsp;&nbsp;&nbsp;';
			  div.appendChild(ele);
			  ele=document.createElement("a");
			  ele.href='javascript:void(0);';
			  ele.onclick=function(){addInput(ele);};
			  
			  var im=document.createElement("img");
			 
			  im.src='common/images/plus.gif';
			  im.height='16';
			  im.width='16';
			  im.alt='Add';
			  ele.appendChild(im);
			  div.appendChild(ele);
			  //- link
			  ele=document.createElement("span");
			  ele.innerHTML='&nbsp;&nbsp;&nbsp;';
			  div.appendChild(ele);
			  ele=document.createElement("a");
			  ele.href='javascript:void(0);';
			  ele.onclick=function(){removeLink(ele);};
			  var im=document.createElement("img");
			 
			  im.src='common/images/minus.gif';
			  im.height='16';
			  im.width='16';
			  im.alt='minus';
			  
			  ele.appendChild(im);
			  div.appendChild(ele);
			  var pic_token=document.createElement("input");
			  pic_token.name="hasPic";
		      pic_token.type="hidden";
		      pic_token.value=-1;
		      var picName=document.createElement("input");
			  picName.name="picName";
		      picName.type="hidden";
		      picName.value="";
		      div.appendChild(pic_token);
		      div.appendChild(picName);
			   
		  }
		  
		  function validate()
		  {
			  
			  $("textarea").each(
			          function(){
			        	  this.value=replaceRN(this.value);
			        	  var regBr=new RegExp("\r\n","g");
			        	  this.value=this.value.replace(regBr,"<br>");
			        	  }
			   );
			  var time=0;
		       $('tbody textarea[name$="Step_time"]').each(function(){
		    	     time=time+parseInt(this.value);
		    	   
		       });
		       $('input[name="testcase.Excute_time"]').each(function(){this.value=time});
			 
		  }
		
	
		  //  chill function
		  function goToPreUpdateCaseAction(){
			  
				  
				  validate();
			  
				 
			   	 reCalcPicLocation();
			   	 
			     //input validation
				  var obj=document.getElementById("TCName");
				  if (obj.value=="")
				  {
					  alert("TestCase name is required");
					  return false;
				  }
				  obj=document.getElementById("TCVersion");
				  if (obj.value=="")
				  {
					  alert("TestCase version is required");
					  return false;
				  }
				  obj=document.getElementById("TCPAID");
				  if (obj.value=="")
				  {
					  alert("TestCase id is required");
					  return false;
				  }
				  obj=document.getElementById("Brand");
				  if (obj.value=="")
				  {
					  alert("TestCase Brand is required");
					  return false;
				  }
				  obj=document.getElementById("TM");
				  if (obj.value=="")
				  {
					  alert("Test Mode is required");
					  return false;
				  }
				  obj=document.getElementById("OS");
				  if (obj.value=="")
				  {
					  alert("OS is required");
					  return false;
				  }
				  obj=document.getElementById("Function");
				  if (obj.value=="")
				  {
					  alert("Function is required");
					  return false;
				  }
				  var str=document.getElementById("Accessory").value;
					  if (str !="" )					  
				  {
						 var result=str.split(".");
						 if (result.length==1 ||( result[result.length-1].toLowerCase() =="jpg" && result[result.length-1].toLowerCase() =="gif" && result[result.length-1].toLowerCase() =="png" && result[result.length-1].toLowerCase() =="bmp"))
						 {	  
					 	  alert("Accessory must not be like *.jpg,*.gif,*.png,*.bmp , at row "+i);	
					 	  document.getElementById("Accessory").focus();
					 
						  return false;
						 }
				  }
				  var testTable=document.getElementById("TCItems");
				  if (testTable.rows.length==0)
				  {
					 alert("No test steps!!");
					 return false;
				  }
				  for (var i=1;i<testTable.rows.length;i++)
				  {				  
					  var row=testTable.rows.item(i);	
	 				  if (document.getElementById("tcupdatebean.tccontent["+(testTable.rows.length-2)+"].TestItem").value=="" || document.getElementById("tcupdatebean.tccontent["+(testTable.rows.length-2)+"].Step_des").value=="" || document.getElementById("tcupdatebean.tccontent["+(testTable.rows.length-2)+"].Test_result").value=="")					  
					  {
						  alert("step/Description ,Expected Result are required, at Row["+i+"]");
						  return false;
					  }
	 				  
					  var re = /^[0-9]+.?[0-9]*$/;     
					  if (!re.test(document.getElementById("tcupdatebean.tccontent["+(testTable.rows.length-2)+"].Step_time").value))
					  {
						  alert("Execution time must be positive number ,1~9999 minutes");
						  return false;
					  }
					  
				   }
		
			    var CaseID='<s:property value="testcaseinfoview.CaseID"/>';
			    displayBusyBox();
				
				var url="chilltest/updateTestCase";
				
				//alert(CaseID);
				document.forms["FunctionForm"].action = url;
				document.forms["FunctionForm"].submit();
			}
  
		  
		  	var curfile=null;
		  	function download(file){
		  		curfile=file;
		  		document.getElementById("downloadbody").disabled=true;
		  		document.getElementById("downloaddiv").style.display="block";
		  		
		  	}
		  	function OK(){
		  		document.getElementById("downloaddiv").style.display="none";
		  		document.getElementById("downloadbody").disabled=false;
		  		window.open(curfile);
		  		curfile=null;
		  	}
		  	function Cancel(){
		  		document.getElementById("downloaddiv").style.display="none";
		  		document.getElementById("downloadbody").disabled=false;
		  		curfile=null;
		  	}
		   
		    function showPhoto(path){
	    	 		
		    	  // alert(path);
				   var html = "<img src='download/getPictureForAjax?photoPath="+path+"' onload='javascript:DrawImage(this,800,600)' />";
				   $("#photoDlg").html(html);
				   showPhotoDlg();
			}
			// show image Dlg
			function showPhotoDlg(){
				$("#photoDlg").dialog({
					bgiframe: true,
					height	: 600,
					width	: 800,
					modal	: true
				});
				$('#photoDlg').dialog('open');
			};
			// show uploadify div
		   function showUploadFileDiv(){
				   $("#showUploadDivBtn").hide();
				   $("#photoLinkListDiv").hide();
				   $("#uploadfile").show();
			};
			
			
			var flag=false;
			function DrawImage(ImgD,iwidth,iheight){
				var image=new Image();
				image.src=ImgD.src;
				//alert(image.width);
				if(image.width>0 && image.height>0){
						flag=true;
							if(image.width/image.height>= iwidth/iheight){
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
				flag=false;
			};
			
			
			function delphoto(id){
				$.post("chilltest/HideTCphotoAction?pic_id="+id,{},function(data,status){
					if(status=="success"){
						alert(" delete successful.");
						// <a> <div> <div>
						$("#pic"+id).each(function(){this.parentNode.parentNode.removeChild(this.parentNode);});
						
					}
				});
				//alert("=========list==="+id);
			}
			function saveORsubmit()
			{
				document.getElementById('savesubmit').value="1"; //Submit
				
			}
  </script>
  
  
  	
  	<div title="Test Case Detail" style="width: 1280px; text-align: right;margin-left:20;">
  		<s:form  id="FunctionForm" action="updateTestCase" namespace="/chilltest" onsubmit="return goToPreUpdateCaseAction();" method="post"  enctype="multipart/form-data">
  		    <s:hidden name="testcase.CaseID" value="%{testcaseinfoview.CaseID}"/>
  		  	<s:hidden name='savesubmit' id='savesubmit' value='0'></s:hidden>
  		  
  			<s:submit value="Save"  theme="simple"></s:submit>
  			<s:submit value="Submit"  theme="simple" onclick="saveORsubmit();"></s:submit>
  			<s:hidden name="testcase.Stage" value='%{testcaseinfoview.Stage}'></s:hidden>
	   <table   cellspacing=0 cellpadding=0 ><!-- cellspacing=0 cellpadding=0 -->
	   		<tr>
	   			<td>
	     	      <table  border="2" style="width:1380px">
	     
	     	      <tr>
	     	      <td align="left" style="background-color: #e6EEEE;font-size:10pt;font-weight:600;font-style:Calibri;">Case Name</td>
	     		  <td align="left" width="300px"><s:textfield id="TCName" cssStyle='width:300px' name="testcase.CaseName" value ="%{testcaseinfoview.CaseName}" theme="simple"/></td>
	     		  <td align="left" style="background-color: #e6EEEE;font-size:10pt;font-weight:600;font-style:Calibri;">Version</td>
	     		  <td align="left" ><s:textfield id="TCVersion" cssStyle='width:40px' name="testcase.Version" value = "%{testcaseinfoview.Version}"  theme="simple"/></td>
	     		  <td align="left" style="background-color: #e6EEEE;font-size:10pt;font-weight:600;font-style:Calibri;">Owner</td>
	     		  <td align="left" ><s:textfield  cssStyle='width:180px' name="testcase.Owner" value = "%{testcaseinfoview.Owner}" theme="simple"/></td>
	     		  <td align="left" style="background-color: #e6EEEE;font-size:10pt;font-weight:600;font-style:Calibri;">ID</td>
	     		  <td align="left"><s:textfield id="TCPAID" cssStyle='width:80px' name="testcase.Case_Code" value ="%{testcaseinfoview.Case_Code}" theme="simple"/></td>
	     		  <td align="left" style="background-color: #e6EEEE;font-size:10pt;font-weight:600;font-style:Calibri;">Time</td>
	     		  <td align="left"><s:textfield id="TCTime" readonly="true" cssStyle='width:20px'  name="testcase.Excute_time" value ="%{testcaseinfoview.Excute_Time}" theme="simple"/></td>
	     	      </tr>
	     	 
	     	      </table>
				</td>
			</tr>
			<tr>
	     	  	  <td><a href="javascript:void(0);" style="font-size:15px;color:darkblue;font-weight:600"id="Introduction" >Introduction +</a></td>
			</tr>
			<tr>	     	 
	     	  	 	<td>
		     		   <table id='Introductiontable' border='1' style="width:1380px;border-collapse=collapse;">
		     	 	  	<tr>
		     	   			<td>
		     	   				<s:textarea wrap="true" cssStyle='width:1380px;height:100px' name="testcase.Introduction" value = "%{testcaseinfoview.Introduction}" theme="simple"/>
		     				</td>
		     			</tr>
		     		  </table>	
	     	  		</td>	     		
	     	</tr>
	     	<tr><td style='text-align:left'>
	     	     <br/><a id='reason' href='javascript:void(0);' style='font-size:15px;color:darkblue;font-weight:600'>pls input your modify reason:</a><br/>
	     	     <s:textarea name='testcase.ModifyReason' cssStyle='width:1380;height:50' theme='simple'/>
	     	    </td>
	     	</tr>		
			<tr>
	     	  		 <td><a href="javascript:void(0);" style="font-size:15px;color:darkblue;font-weight:600" id="BasicInfo" >Basic Information +</a></td>
	     	</tr>	     	
	     	<tr>
	     	   <td>
		     	   	<table id='BasicTable' class="tablesorter" border='1'style="width:1380px;text-align:left">
		     	   	
		     	       <thead>
		     	         <tr>
		     	         <th>Brand</th>
		     	         <th>TestMode</th>
		     	         <th>SupportOS</th>
		     	         <th>Automation</th>
		     	         <th>Component Owner</th>
		     	         <th>Function</th>
		     	         </tr>
		     	       </thead>
		     	       <tbody>
		     	      	 <tr>
		     	      	 	<td><s:select  id='Brand' name="testcase.Brand_id" list="%{tcFormbean.mapBrand}" value="%{testcaseinfoview.Brand_id}"  theme="simple"/>
		     	      	 	<td><s:select id='TM' name="testcase.Test_Mode_id" list="%{tcFormbean.mapTestMode}" value="%{testcaseinfoview.Test_Mode_id}"  theme="simple"/>
		     	 			<td><s:select id="OS" name="testcase.OS_id" list="%{tcFormbean.mapOs}" value="%{testcaseinfoview.OS_id}" theme="simple"/></td>
  	  					    <td><s:select name="testcase.Automation" list="{'Y','N','Limited'}" value="%{testcaseinfoview.Automation}" theme="simple" />
		     	      		<td><s:textfield name="testcase.Component_Owner"  value="%{testcaseinfoview.Component_owner}" theme='simple' />
		     	      		<td><s:select id="Function" name="testcase.Func_id" list="%{tcFormbean.mapFunction}" value="%{testcaseinfoview.Func_id}" theme="simple"/></td>	     	     
		     	      	 </tr>
		     	       </tbody> 
		     	   </table>
	     	   </td>
	     	</tr>
	     	
	     	<tr>
	     	  <td ><a id="SupportInfo" style="font-size:15px;color:darkblue;font-weight:600" href="javascript:void(0);" >Support Information +</a></td>
	     	</tr>
	     	<tr>
	     	  <td>
	     	  <table id="SupportTable"  border='1' >
	     	    <thead>
	     	      <tr>
	     	 	  <th align="left" style="background-color: #e6EEEE;width:600px">Localized Information</th>
	     	      <th style="background-color: #e6EEEE;width:400px">Comments</th>
	     	      
	     	      </tr>
	     	    </thead>
	     	      <tbody>
	     	      		<tr>
	     	      			<td>
	     	      				<table border='2'>
	     	      						<tr>
	     	      						<td align="center" style="background-color:#FFFF00;width:200px">OS Languages</td>
	     	      						<s:iterator value="LanBean" id="LanBean" status="st">
	     	      							 <s:if test="#st.index<=16 && #st.index>1">
	     	                           			<td align="center" bgcolor="#FFFF00">
	     	                               			<s:property value="Lan_value"/>
	     	                              		 </td>
	     	                        		 </s:if>
	     	      						</s:iterator>
	     	      						</tr>
	     	      						<tr>
	     	      						<td align="center">Locallized Infomation</td>
	     	      						<s:iterator value="CaseLan" id="CaseLan" status="st">
	     	      							<s:if test="#st.index<15 && #st.index>=0">
	     	      							    <td>
	     	      							      <s:select name="tcupdatebean.CaseLan[%{#st.index}].Localized_set" list="%{tcFormbean.mapLanguage}" value="%{CaseLan[#st.index].Localized_set}" theme="simple"/>
	     		     						  	  
	     		     						  </td>
	     	      							</s:if>	     	      							
	     	      						</s:iterator>
	     	      						
	     	      						<s:if test="CaseLan.size==0">
	     	      						
	     	      						  <s:iterator value='LanBean' status='st'> 
	     	      						    <s:if test="#st.index<16 && #st.index>0">    	      						   
	     	      							<td>
	     	      							
	     	      						    <s:select name="tcupdatebean.CaseLan[%{#st.index-1}].Localized_set" list="%{LanBean}" listValue='Lan_value' listKey='Language_id'  theme="simple"/>  
	     		     					    </td>
	     		     					    </s:if>
	     	      						  </s:iterator>
	     	      						</s:if>				
	     	      						
	     	      					    </tr>
	     	      					    <tr>
	     	      						<td  align="center" bgcolor='#FFFF00' >OS Languages</td>
	     	      						<s:iterator value="LanBean" id="LanBean" status="st">
	     	      							 <s:if test="#st.index<=31 && #st.index>16">
	     	                           			<td align="center" bgcolor="#FFFF00">
	     	                         				 <s:property value="Lan_value"/>
	     	                              		 </td>
	     	                        		 </s:if>
	     	      						</s:iterator>
	     	      						</tr>
	     	      						<tr>
	     	      						<td align="center">Locallized Infomation</td>
	     	      						<s:iterator value="CaseLan" id="CaseLan" status="st">
	     	      							<s:if test="#st.index<30 && #st.index>=15">
	     	      							    <td>
	     	      							     <s:select name="tcupdatebean.CaseLan[%{#st.index}].Localized_set" list="%{tcFormbean.mapLanguage}"  value="%{CaseLan[#st.index].Localized_set}" theme="simple"/>
	     		     						 </td> 
	     	      							</s:if>
	     	      						</s:iterator>
	     	      						<s:if test="CaseLan.size==0">
	     	      						
	     	      						  <s:iterator value='LanBean' status='st'> 
	     	      						    <s:if test="#st.index<31 && #st.index>15">    	      						   
	     	      							<td>
	     	      							<s:select name="tcupdatebean.CaseLan[%{#st.index-1}].Localized_set" list="%{LanBean}" listValue='Lan_value' listKey='Language_id'  theme="simple"/>  					    
	     		     					    </td>
	     		     					    </s:if>
	     	      						  </s:iterator>
	     	      						</s:if>		
	     	      					    </tr>
	     	      				</table>
	     	      			</td>
	     	      	   <td><s:textarea cssStyle='width:400px;height:100px;font-style:Calibri;font-size:8pt' name="testcase.LanguageComment" value="%{testcaseinfoview.LanguageComments}" theme="simple"/></td>
	     
	     	       		</tr>
	     	       	
	     	      		
	     	      </tbody>	        	                     
	     	   	  <tr>
	     	   	  <th align="left" style="background-color: #e6EEEE;">HW Support Information</th>
	     	   	  <th style="background-color: #e6EEEE;">Comments</th></tr>
	     	    	<tbody>
	     	      	<tr>
	     	   			<td><s:textarea cssStyle='font-style:Calibri;font-size:8pt;width:900px' name="testcase.HwInfo" value="%{testcaseinfoview.HwInfo}" theme="simple"/></td>
	     	 	    	<td><s:textarea cssStyle='font-style:Calibri;font-size:8pt;width:400px' name="testcase.HwComment" value="%{testcaseinfoview.Comments}" theme="simple"/></td>
	      		 	</tr>
	      		 	 <tr>
	     	        	   	<td>
	     	        	   	<div><input type='file' id='Accessory' name='accessory' /></div>
	     	         		<div id="downloadbody">
	     		   	  		<s:set value="testcaseinfoview.Accessory" var="Accessory"/>
	     		   	  		<s:iterator value="Accessory" id="cp">  
	     		   	  			<s:a href="javascript:void(0);" onclick="download('%{fullpath(picPath,%{#cp.picture_path})}');return false;"> <s:property value="Pic_name"  /> </s:a>
	     		   	  		</s:iterator>
    						</div>
    						<div id="downloaddiv"  style="display:none;border:solid 2px red;">
    						  Are you sure to download this file?<br/>
    						  <input type="button" value="agree" onclick="OK()" />
    						  <input type="button" value="cancel" onclick="Cancel()" />
    						 </div>
	     	         	</td>
	     	         	<td>
	     	         	</td>
	     	        </tr> 
	     	   
	     	    	</tbody>
	     	 	 </table>
	     	  </td>
	     	</tr>
				
	  </table>
	   
  	
 
  
   	
	     <table style="margin-left:20">
	     <tr>
	     	<td>
	     		<table cellspacing=0 cellpadding=0 style="width: 1280px;"><!-- cellspacing=0 cellpadding=0 -->
	     		<tr>	
	     		<td><br/><a href="javascript:void(0)" style="font-size:15px;color:darkblue;font-weight:600">Case Items</a></td>
	     	</tr>
			<tr>
	     		<td>
	     		<p style="color:red;font-size:15px;font-style:italic ">Prerequisites:  
	     		1.System flashed to the latest BIOS.  2.System configured and preloaded to test plan configuration specifications.  
	     		3.System successfully completed 				</p></td>
	     	</tr>
			<tr>
				<td>
					<p style="color:black;font-size:15px;font-style:italic "> NLS Test use P1, US Test use P1&P2; Function Test use P1&P2P3;
					</p>
				</td>
			</tr>
	     		</table>
	     	</td>
	    </tr>
	     	<tr>
	     		<td>	
	   	 			<table id="TCItems" class="TCItems" border='1' style="table-layout:fixed;"title="Test Case Detail" style="width: 1024px; text-align: left;margin: 0;" >
	     			   <thead>
	     				<tr id="TCItem0" class="TCItem0">
	     				<th style="width:25px"></th>
	     				<th style="width:40px;">Level</th>
	     				<th style="width:200px;">TestItem</th>
	     				<th style="width:400px;">TestSteps/Description</th>
	     				<th style="width:250px">Expect Results</th>
	     				<th style="width:200px">Reference Pics</th>
	     				<th style="width:25px;font-size:6pt">Time</th>
	     				<th style="width:200px">Comments</th>
	     				</tr>	     		
	     		  	  </thead>
	     		  	  <tbody id="tablebody">	 
	     		   		  <s:iterator value="tc" id="ttcc" status="st">
	     		   	  		<tr>
	     		   	  		 <td>
	     		     	     <a href='javascript:void(0);' onclick='removeRow(this);'><img src='common/images/minus.gif' height='16' width='16' alt='Delete'/></a>
	     		     	     <br/>
	     		     	     <a href='javascript:void(0);' onclick='addRow(this);'><img src='common/images/plus.gif' height='16' width='16' alt='Insert' /></a>
	     		     	     <br/>
	     		     	     <a href='javascript:void(0);' onclick='moveUp(this);'><img src='common/images/arrow_up.gif' height='16' width='16' alt='Up' /></a>
	     		     	     <br/>
	     		     	     <a href='javascript:void(0);' onclick='moveDown(this);'><img src='common/images/arrow_down.gif' height='16' width='16' alt='Down'/></a>
	     		     	    </td>
	     		   	  		<td><s:select  list="{'P1','P2','P3'}"  cssStyle='width:40px;height:120px' name="tcupdatebean.tccontent[%{#st.index}].Case_level"  value="%{tc[#st.index].Case_level}" theme="simple"/></td>
	     		   	  		<td> <s:textarea cssStyle='font-style:Calibri;font-size:8pt;width:200px;height:120px' name="tcupdatebean.tccontent[%{#st.index}].TestItem"   value="%{tc[#st.index].TestItem}"  theme="simple"/></td>
	     		   	  	    <td style="word-warp:break-word"> <s:textarea cssStyle='font-style:Calibri;font-size:8pt;width:380px;height:120px'   name="tcupdatebean.tccontent[%{#st.index}].Step_des"   value="%{tc[#st.index].Step_des}" theme="simple" /></td>
				   	  		<td style="word-warp:break-word"> <s:textarea cssStyle='font-style:Calibri;font-size:8pt;width:250px;height:120px' name="tcupdatebean.tccontent[%{#st.index}].Test_result" value="%{tc[#st.index].Test_result}" theme="simple"/></td>
							<td>
	     		   	  		<div id="photoLinkListDiv">
	     		   	  		   <s:set value="tc.CasePics" var="CasePics"/>
	     		   	  		    <s:iterator value="CasePics" id="cp">  
	     		   	  		    <div>
	     		   	  			<s:a id="pic%{#cp.pic_id}" href="javascript:void(0);" onclick="showPhoto('%{fullpath(picPath,#cp.picture_path)}');return false;"> <s:property value="Pic_name"  /> </s:a>
	     		   	  			<s:a id="del%{#cp.pic_id}" href="javascript:void(0);" onclick="delphoto('%{#cp.pic_id}');">Del</s:a>
	     		   	  			<s:hidden name="oldpic_path"  value="%{picture_path}" />
	     		   	  			<s:hidden name="oldpic_order"  value="0" />
	     		   	  			</div>
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
	     		   	  		<td><s:textarea name="tcupdatebean.tccontent[%{#st.index}].Step_time"    value="%{tc[#st.index].Step_time}"  theme="simple"/></td>
	     		   	  		<td style="word-warp:break-word;"> <s:textarea cssStyle='font-style:Calibri;font-size:8pt;width:200px;height:120px' wrap="physical" name="tcupdatebean.tccontent[%{#st.index}].Comments" value="%{tc[#st.index].Comments}"  theme="simple"/></td>	     	     		  	
	     		   	  		</tr>
	     		   	  	</s:iterator>
	     		  	 </tbody>  
	    		 </table>
	    	 .</td>
	    </tr>
	    <tr>
	    	<td>
	    		<Button id='AddRow' onclick='javascript:void(0);addRow(1);'>Append a new row</Button>
	    	</td>
	    </tr>
	 
	  </table>
	  
	  	</s:form>
	</div>   

</body>
</html>