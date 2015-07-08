var globalCount=0;
var globalresult;
var id=1;
function submitValidation(){
	console.info("start");
	if (reCalcPicLocation() !=1){
		console.info("not work");
			 return false;
	}
	if (basicTest() !=2){
		return false;
		}
	console.info("end");

}  
function basicTest(){
	 var testTable=document.getElementById("TCItems");
	  if (testTable.rows.length==1){
		 alert("No test steps!!");
		 return false;
	  }
	  console.info(testTable.rows.length);
	  for (var i=1;i<testTable.rows.length;i++){				  
		  var row=testTable.rows.item(i);	
		  //console.info(document.getElementById("tcContent["+(testTable.rows.length-2)+"].stepTime").value);
		if (document.getElementById("tcContent["+(testTable.rows.length-2)+"].testItem").value==""
			|| document.getElementById("tcContent["+(testTable.rows.length-2)+"].testStep").value==""
				|| document.getElementById("tcContent["+(testTable.rows.length-2)+"].testResult").value==""){
			  alert("step/Description ,Expected Result are required, at Row["+i+"]");
			  return false;
		  }	  
	
	   }
	  for(var i=1;i<testTable.rows.length;i++){
		  var  time = document.getElementById("tcContent["+i+"].stepTime").value;
			time = time.replace(" ","");
			if(time==""){
				alert("Step time should not be null");
				return;
			}
			console.info("time"+i+" "+time);
			document.getElementById("tcContent["+i+"].stepTime").value=time;
	  }
	  globalresult=2;
		  if (globalresult==0){return false;}
		  return 2;
}
 function reCalcPicLocation(){
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
		 for (var x=0;x<vars.length;x++){
			 if (typeof vars[x].name== "undefined"){
				 continue;
			 }
			 if (vars[x].name.indexOf("caseLevel")!=-1) {
				 Case_level.push(vars[x]);
			 }
		 }
		 console.info("caselevel:"+Case_level.length);
		 vars=document.getElementsByTagName("textarea");
		 for (var x=0;x<vars.length;x++){
			 if (typeof vars[x].name== "undefined"){
				 continue;
			 }
			 if (vars[x].name.indexOf("testItem")!=-1) {
				 TestItem.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("testStep")!=-1)  {
				 Desc.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("testResult")!=-1) {
				 Test_result.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("stepTime")!=-1) {
				 Step_time.push(vars[x]);
			 }
			 if (vars[x].name.indexOf("comments")!=-1) {
				 comments.push(vars[x]);
			 }
		 }
		 console.info(TestItem.length+" "+Desc.length+" "+Test_result.length+" "+Step_time.length+" "+comments.length);
		 console.info();
		 var inputs=document.getElementsByTagName("input");
		 var str="";
		 
		 for (var x=0;x<inputs.length;x++){
			 if (typeof(inputs[x].name)== "undefined"){
				 continue;
			 }
			 if (inputs[x].name.indexOf("hasPic")!=-1) {
				 hasPics.push(inputs[x]);
			 }
			 
			 if (inputs[x].name.indexOf("upload") !=-1  ){			
				 uploads.push(inputs[x]);
			 }
			 if (inputs[x].name.indexOf('accessory') !=-1){
				 accessory.push(inputs[x]);
			  }
			 if (inputs[x].name.indexOf("picName") !=-1){
				 picName.push(inputs[x]);
			 }
			 if (inputs[x].name.indexOf("oldpic_path") !=-1){
				 oldpic_path.push(inputs[x]);
			 }
			 if (inputs[x].name.indexOf("oldpic_order") !=-1){
				 oldpic_order.push(inputs[x]);
			 }
		 }
		console.info("oldpic_order:"+oldpic_path.length);
		console.info("test"+hasPics.length+" "+uploads.length+" "+picName.length);
		 for (var i=0;i<Case_level.length;i++){
			console.info("++"+Case_level.length+"::"+TestItem.length+"::"+Desc.length+"::"+Test_result.length+"::"+Step_time.length+"::"+comments.length);
			 Case_level[i].name="tcContent["+i+"].caseLevel";
			 Case_level[i].id="tcContent["+i+"].caseLevel";
			 TestItem[i].name="tcContent["+i+"].testItem";
			 TestItem[i].id="tcContent["+i+"].testItem";
			 Desc[i].name="tcContent["+i+"].testStep";
			 Desc[i].id="tcContent["+i+"].testStep";
			 Test_result[i].name="tcContent["+i+"].testResult";
			 Test_result[i].id="tcContent["+i+"].testResult";
			 Step_time[i].name="tcContent["+i+"].stepTime";
			 Step_time[i].id="tcContent["+i+"].stepTime";
			 comments[i].name="tcContent["+i+"].comments";
			 comments[i].id="tcContent["+i+"].comments";
			console.info(i+"::"+Case_level[i].name+":"+TestItem[i].name+":"+Desc[i].name+":"+Test_result[i].name+":"+hasPics[i].name+":"+Step_time[i].name);
		    console.info(uploads[i].name);
		 }
		console.info("uploads:"+hasPics.length);
		 
		 for (var i=0;i<uploads.length;i++){
			 var index=uploads[i].parentNode.parentNode.parentNode.sectionRowIndex ; //Tr index in tbody ,based on 0 compare to oder;
			 hasPics[i].name="tcContent["+index+"].hasPic";
             picName[i].name="tcContent["+index+"].picName";
             uploads[i].name="upload";
			 if (uploads[i].value !=""){
				 hasPics[i].value=index;
				 picName[i].value=uploads[i].value;
			  }else{
				 hasPics[i].value=-1;
			 }
		  }
		 for (var i=0;i<oldpic_path.length;i++){
			 var index=oldpic_path[i].parentNode.parentNode.parentNode.sectionRowIndex ; //Tr index in tbody ,based on 0 compare to oder;
			
			 oldpic_path[i].name="olderPicRelations["+i+"].oldpic_path";
			 oldpic_order[i].name="olderPicRelations["+i+"].oldpic_order";
			 oldpic_order[i].value=index;
			 console.info(index);
		  }
		 return 1;
	 }


function addRow(insertOne) {
	var body = document.getElementById("tablebody");
	var testTable = document.getElementById("TCItems");

	if (body) {
		var row = document.createElement("tr");
		if (insertOne == null) {
			body.appendChild(row);
		} else {
			body.insertBefore(row, insertOne.parentNode.parentNode);
		}
		var caseLevel = document.createElement("select");
		caseLevel.name = "caseLevel";
		//caseLevel.className="form-control"
		var Token = document.createElement("input");
		Token.name = "hasPic";
		Token.type = "hidden";
		Token.value = -1;
		var picName = document.createElement("input");
		picName.name = "picName";
		picName.type = "hidden";
		picName.value = "";

		var executetime = document.createElement("textarea");
		executetime.cols = "4";
		executetime.rows = '1';
		executetime.defaultValue = " ";
		executetime.value = " ";

		for ( var j = 1; j < 4; j++) {
			var P = document.createElement("option");
			P.value = "P" + j;
			P.label = "P" + j;
			caseLevel.appendChild(P);
		}
		console.info(testTable.tHead.rows[0].cells.length);
		for ( var i = 0; i < testTable.tHead.rows[0].cells.length; i++) {

			var cell = document.createElement("td");
			row.appendChild(cell);
			var textfield = document.createElement("textarea");
			textfield.rows = 4;
			var str = "";
			if (i == 0) {
				str = "<a href='javascript:void(0);' onclick=\"removeRow(this);\"><img src='common/images/minus.gif' height='16' width='16' alt='Delete'/></a><br/><a href='javascript:void(0);' onclick=\"addRow(this);\"><image src='common/images/plus.gif' height='16' width='16' alt='Insert'/></a><br/>";
				str = str
						+ "<a href='javascript:void(0);' onclick=\"moveUp(this);\"><img src='common/images/arrow_up.gif' height='16' width='16' alt='Up'/></a><br/><a href='javascript:void(0);' onclick=\"moveDown(this);\"><img src='common/images/arrow_down.gif' height='16' width='16' alt='Down'/></a>";
				cell.innerHTML = str;
				cell.appendChild(Token);
				cell.appendChild(picName);
			}
			if (i == 1) {
				cell.appendChild(caseLevel);
			}
			if (i == 2) {
				textfield.name = "testItem";
				textfield.className = "form-control"
			    textfield.id ="test"+id;
				cell.appendChild(textfield);			
			}
			if (i == 3) {
				textfield.name = "testStep";
				textfield.className = "form-control"
				textfield.setAttribute("id", "test" + id);
				cell.appendChild(textfield);
				var test = "des" + id;
				var te = document.getElementById(test);

			}
			if (i == 4) {
				cell.appendChild(textfield);
				textfield.name = "testResult";
				textfield.className = "form-control"
				textfield.setAttribute("id", "result" + id);
	
		}
			if (i == 5) {
				//Upload pic
				//Why i use innerHTML to assign <input> to cell?? Marcus,2012/06/09, because created <input> by createElement("input") can't be assigned name property. You can google this proplem,it's weird happending on my IE8
				cell.innerHTML = "<div><input type='file' name='upload' />&nbsp; &nbsp;<a href='javascript:void(0);' onclick='addInput(this);'><img src='common/images/plus.gif' height='16' width='16' alt='Add'/></a>&nbsp; &nbsp;<a href='javascript:void(0);' onclick='removeLink(this);'><img src='common/images/minus.gif' height='16' width='16' alt='minus'/></a></div>";
			}
			if (i == 6) {
				cell.appendChild(executetime);
				textfield.className = "form-control"
				executetime.name = "stepTime";	
			}
			if (i == 7) {
				cell.appendChild(textfield);
				textfield.name = "comments";
				textfield.className = "form-control"
				textfield.setAttribute("id", "comm" + id);
				}

		}
		//recalTime();

	}
	id = id + 1;
}
function addInput(obj)  //obj must be <a> 
{
	  var ele;
	  var td=obj.parentNode.parentNode;  

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
	   var Token=document.createElement("input");
	  Token.name="hasPic";
    Token.type="hidden";
    Token.value=-1;
    var picName=document.createElement("input");
	  picName.name="picName";
    picName.type="hidden";
    picName.value="";
    div.appendChild(Token);
    div.appendChild(picName);
	   
}
function removeLink(obj) //<td><div><a><img></img></a></div></td> ,i want to remove <a> and its descendants and siblings starting from <a>
{
	
	  if(obj.parentNode.nextSibling ==null && obj.parentNode.previousSibling ==null){return;}
	  obj.parentNode.parentNode.removeChild(obj.parentNode);
}
function removeRow(obj) { 
	//delete row
	var testTable = document.getElementById("TCItems");
	var bodies = testTable.tBodies;
	var aBody = null;
	if (bodies) {
		aBody = bodies[0];
		if (aBody) {
			aBody.removeChild(obj.parentNode.parentNode); //Because <a>--<td>--<tr>
			recalTime();
		}
	}
}

function swapNode(node1,node2)
 {
	  var _parent=node1.parentNode;
	  var _t1=node1.nextSibling;
	  var _t2=node2.nextSibling;
	  if(_t1) _parent.insertBefore(node2,_t1);
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


function showPhoto(path){
			console.info(path);
		   var html = "";
		   html +="<img src='download/getPictureForAjax?photoPath="+path+"' onload='javascript:DrawImage(this,800,600)' />";
		   console.info(html);
		   $("#photoDlg").html(html);
		   console.info("test");
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
	    console.info("dialog");
		$('#photoDlg').dialog('open');

	};	
	var flag=false;
	function DrawImage(ImgD,iwidth,iheight){
		var image=new Image();
		image.src=ImgD.src;
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
	} 
	
	
	function delphoto(id){
		console.info("start");
		console.info(id);
		$.post("phase4/DeletePhoto?pictureInstkey="+id,{},function(data,status){
			if(status=="success"){
				alert(" delete successful.");
				// <a> <div> <div>
				$("#pic"+id).each(function(){this.parentNode.parentNode.removeChild(this.parentNode);});
			}
		});
		//alert("=========list==="+id);
	}
	