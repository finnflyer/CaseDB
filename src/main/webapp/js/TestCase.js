
var globalresult;
var id=1;
function submitValidation(){
	console.info("Show Test JS start");
	if (reCalcPicLocation()!=2){
		console.info("not work");
		return false;
	}
	if (basicTest() !=2){
		return false;
	}
	console.info("Show Test JS end");
}

function basicTest(){
	var testTable=document.getElementById("TCItems");
	if (testTable.rows.length==1){
		alert("No test steps!!");
		return false;
	}
	for (var i=1;i<testTable.rows.length;i++){
		var row=testTable.rows.item(i);
		if (document.getElementById("items["+(testTable.rows.length-2)+"].testitem").value==""
			|| document.getElementById("items["+(testTable.rows.length-2)+"].teststep").value==""
			|| document.getElementById("items["+(testTable.rows.length-2)+"].testresult").value==""){
			alert("step/Description ,Expected Result are required, at Row["+i+"]");
			return false;
		}
		var re = /^[0-9]+.?[0-9]*$/;
		if (!re.test(document.getElementById("items["+(testTable.rows.length-2)+"].steptime").value)){
			alert("Execution time must be positive number ,1~9999 minutes");
			return false;
		}
	}
	globalresult=2;
	$("input[name$='picName']").each(
		function(){
			var str=this.value;
			if (str !=""){
				var result=str.split(".");
				if (result.length==1
					||( result[result.length-1].toLowerCase() !="jpg"
					&& result[result.length-1].toLowerCase() !="gif"
					&& result[result.length-1].toLowerCase() !="png"
					&& result[result.length-1].toLowerCase() !="bmp")){
					globalresult=0;
					alert("Pictures must be like *.jpg,*.png,*.png,*.bmp , at row "+this.parentNode.parentNode.parentNode.sectionRowIndex );
					return false;
				}
			}
		}
	);
	if (globalresult==0){return false;}
	return 2;
}

function reCalcPicLocation(){
	var count=0;
	var Case_level=new Array();
	var testitem=new Array();
	var Desc=new Array();
	var Test_result=new Array();
	var hasPics=new Array();
	var picName=new Array();
	var uploads=new Array();
	var accessory=new Array();
	var Step_time=new Array();
	var Priority = new Arrary();
	var comments=new Array();
	var vars=document.getElementsByTagName("select");
	for (var x=0;x<vars.length;x++){
		if (typeof vars[x].name== "undefined"){
			continue;
		}
		if (vars[x].name.indexOf("caselevel")!=-1) {
			Case_level.push(vars[x]);
		}
	}
	console.info("caselevel:"+Case_level.length);

	vars=document.getElementsByTagName("textarea");
	for (var x=0;x<vars.length;x++){
		if (typeof vars[x].name== "undefined"){
			continue;
		}
		if (vars[x].name.indexOf("testitem")!=-1) {
			testitem.push(vars[x]);
		}
		if (vars[x].name.indexOf("teststep")!=-1)  {
			Desc.push(vars[x]);
		}
		if (vars[x].name.indexOf("testresult")!=-1) {
			Test_result.push(vars[x]);
		}
		if(vars[x].name.indexOf("priority")!=-1){
			Priority.push(vars[x]);
		}
		if (vars[x].name.indexOf("steptime")!=-1) {
			Step_time.push(vars[x]);
		}
		if (vars[x].name.indexOf("comments")!=-1) {
			comments.push(vars[x]);
		}
	}
	//console.info(testitem.length+" "+Desc.length+" "+Test_result.length+" "+Step_time.length+" "+comments.length);
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
	}
	//console.info("uploads:"+uploads.length);
	//console.info(hasPics.length+" "+uploads.length+" "+picName.length);
	for (var i=0;i<Case_level.length;i++){
		console.info("++"+Case_level.length+"::"+testitem.length+"::"+Desc.length+"::"+Test_result.length+"::"+Step_time.length+"::"+comments.length);
		Case_level[i].name="items["+i+"].caselevel";
		Case_level[i].id="items["+i+"].caselevel";
		testitem[i].name="items["+i+"].testitem";
		testitem[i].id="items["+i+"].testitem";
		Desc[i].name="items["+i+"].teststep";
		Desc[i].id="items["+i+"].teststep";
		Test_result[i].name="items["+i+"].testresult";
		Test_result[i].id="items["+i+"].testresult";
		Step_time[i].name="items["+i+"].steptime";
		Step_time[i].id="items["+i+"].steptime";
		Priority[i].id="tcContent["+i+"].priority";
		Priority[i].name="tcContent["+i+"].priority";
		comments[i].name="items["+i+"].comments";
		comments[i].id="items["+i+"].comments";
		//console.info(i+"::"+Case_level[i].name+":"+testitem[i].name+":"+Desc[i].name+":"+Test_result[i].name+":"+hasPics[i].name+":"+Step_time[i].name);
		//console.info(uploads[i].name);
	}
	//console.info("uploads:"+uploads.length);

	for (var i=0;i<uploads.length;i++) {
		var index=uploads[i].parentNode.parentNode.parentNode.sectionRowIndex ; //Tr index in tbody ,based on 0
		hasPics[i].name="items["+index+"].hasPic";
		picName[i].name="items["+index+"].picName";
		uploads[i].name="upload";
		if (uploads[i].value !=""){
			hasPics[i].value=index;
			picName[i].value=uploads[i].value;
		}else{
			hasPics[i].value=-1;
		}
	}
	return 2;
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
		var caselevel = document.createElement("select");
		caselevel.name = "caselevel";
		var priority = document.createElement("select");
		priority.name = "priority";
		for(var m=0;m<4;m++){
			var im = document.createElement("option");
			if(m==0){
				im.value ="Emergency";
				im.label ="Emergency";
			}
			if(m==1){
				im.value="High";
				im.label="High";
			}
			if(m==2){
				im.value = "Medium";
				im.label = "Medium";
			}
			if(m==3){
				im.value = "Low";
				im.label = "Low";
			}
			priority.appendChild(im);
		}
		//caselevel.className="form-control"
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
		executetime.defaultValue = "";
		executetime.value = "";

		for ( var j = 1; j < 4; j++) {
			var P = document.createElement("option");
			P.value = "P" + j;
			P.label = "P" + j;
			caselevel.appendChild(P);
		}
		console.info(testTable.tHead.rows[0].cells.length);
		for ( var i = 0; i < testTable.tHead.rows[0].cells.length; i++) {

			var cell = document.createElement("td");
			row.appendChild(cell);
			var textfield = document.createElement("textarea");
			textfield.rows = 4;
			var str = "";
			if (i == 0) {
				str = "<a href='javascript:void(0);' onclick=\"removeRow(this);\"><img src='image/minus.png' height='16' width='16' alt='Delete'/></a><br/><a href='javascript:void(0);' onclick=\"addRow(this);\"><image src='image/plus.png' height='16' width='16' alt='Insert'/></a><br/>";
				str = str
					+ "<a href='javascript:void(0);' onclick=\"moveUp(this);\"><img src='image/arrow_up.png' height='16' width='16' alt='Up'/></a><br/><a href='javascript:void(0);' onclick=\"moveDown(this);\"><img src='image/arrow_down.png' height='16' width='16' alt='Down'/></a>";
				cell.innerHTML = str;
				cell.appendChild(Token);
				cell.appendChild(picName);
			}
			if (i == 1) {
				cell.appendChild(caselevel);
			}
			if (i == 2) {
				textfield.name = "testitem";
				textfield.className = "form-control"
				textfield.id ="test"+id;
				cell.appendChild(textfield);
			}
			if (i == 3) {
				textfield.name = "teststep";
				textfield.className = "form-control"
				textfield.setAttribute("id", "test" + id);
				cell.appendChild(textfield);
				var test = "des" + id;
				var te = document.getElementById(test);

			}
			if (i == 4) {
				cell.appendChild(textfield);
				textfield.name = "testresult";
				textfield.className = "form-control"
				textfield.setAttribute("id", "result" + id);

			}
			if (i == 5) {
				//Upload pic
				//Why i use innerHTML to assign <input> to cell?? Marcus,2012/06/09, because created <input> by createElement("input") can't be assigned name property. You can google this proplem,it's weird happending on my IE8
				cell.innerHTML = "<div><input type='file' name='upload' />&nbsp; &nbsp;<a href='javascript:void(0);' onclick='addInput(this);'><img src='image/plus.png' height='16' width='16' alt='Add'/></a>&nbsp; &nbsp;<a href='javascript:void(0);' onclick='removeLink(this);'><img src='image/minus.png' height='16' width='16' alt='minus'/></a></div>";
			}
			if(i==6){
				cell.appendChild(priority);
			}
			if (i == 7) {
				cell.appendChild(executetime);
				textfield.className = "form-control"
				executetime.name = "steptime";
			}
			if (i == 8) {
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

	im.src='image/plus.png';
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

	im.src='image/minus.png';
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