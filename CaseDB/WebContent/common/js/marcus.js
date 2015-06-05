function replaceRN(str)
{        
	var regBr=new RegExp("\r\n","ig");
	var regP=new RegExp(" ","ig");
	var regSingQuot=new RegExp("'","ig");
	var regDoubQuot=new RegExp("\"","ig");

    var retStr = str.replace(new RegExp("\n", "ig"), "\r\n");
    retStr=str.replace(regBr,"<br>");
	//retStr=retStr.replace(regP,"&nbsp;");
	retStr=retStr.replace(regSingQuot,"&#039;");
	retStr=retStr.replace(regDoubQuot,"&quot;");

	return retStr;
}
function replaceBR(str)
{	
	
	var regBr=new RegExp("<br>","ig");	
	var regSingQuot=new RegExp("&#039;","ig");
	var regDoubQuot=new RegExp("&quot;","ig");
    
    retStr=str.replace(regBr,"\r\n");
    retStr=retStr.replace(/&nbsp;/ig, " ");
    retStr=retStr.replace(regSingQuot,"\'");
  
    retStr=retStr.replace(regDoubQuot,"\"");
   
	return retStr;
}

String.prototype.LTrim = function() 
{ 
return this.replace(/(^\s*)/g, ""); 
};
String.prototype.RTrim = function() 
{ 
return this.replace(/(\s*$)/g, ""); 
} ;
/*方法一：
用的浏览器内部转换器实现转换，方法是动态创建一个容器标签元素，如DIV，
将要转换的字符串设置为这个元素的innerText(ie支持)||textContent(火狐支持)，
然后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串,显示的时候反过来就可以了（实际上显示的时候不用通过转换，
直接赋值在div就可以正常显示的）。 
 * */
function HTMLEncode(html)
{
var temp = document.createElement ("div");
(temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
var output = temp.innerHTML;
temp = null;
return output;
}
function HTMLDecode(text)
{
var temp = document.createElement("div");
temp.innerHTML = text;
var output = temp.innerText || temp.textContent;
temp = null;
return output;
} 
/*2012-10/23 , in MySQL query string, ' And " are special chars , which will make our string mess.Don't let
 * user input ' And "
 * */
function SpecialDBChar(str)
{
	if (str.indexOf('"')>-1)
	{
		
		return -1;
	}
	if (str.indexOf("'")>-1)
	{
		
		return -1;
	}
	return 1;
}