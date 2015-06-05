<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <title>Case DB Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>

<body>
	
 <div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">
			<table class="table table table-bordered">
				<thead>
    			<tr>
    				<th>Team</th>
    				<th >Database Name</th>
    				<th >Used By(Coordinator)</th>
    				<th>How To Access</th>
    			</tr>
    		</thead>
<tbody>
			    <tr>
    		        <td style='color:red;font-weight:bold;'>Abbreviation</td>
    		        <td>专业名词缩写</td>
    		        <td>Gloria Xu</td>
    		        <td>\\10.176.36.152\ShareFile\Preload PA\部门资料\Preload PA II\Training\Freshman training\Abbreviation for Preload team.docx
					</td>
    		    </tr>
				<tr>
    		        <td style='color:red;font-weight:bold;'>Abbreviation</td>
    		        <td>软件缩写及对应图标</td>
    		        <td>Gloria Xu</td>
    		        <td>\\10.176.36.152\ShareFile\Preload PA\部门资料\Preload PA II\Training\Freshman training\软件简称与图标.xlsx
					</td>
    		    </tr>
    		    <tr>
    		        <td style='color:red;font-weight:bold;'>CTD导出工具</td>
    		        <td>可以导出单个case或plan</td>
    		        <td>CDL(MarcusHe/Chill Huang)</td>
    		        <td>\\iipcpld\PreloadAuto\QATestManagementSystem\CTD-Export-UI\CTD-Export\bin 请按照debug\CTD-Export-Readme.docx手册执行导出工作。</td>
    		    </tr>
    		    <tr>
    		        <td style='color:red;font-weight:bold;'>Win8 Sniff自动化工具</td>
    		        <td>Win8 Sniff自动化工具</td>
    		        <td>CDL(Troy Wang/Oliver Hu/Chill Huang)</td>
    		        <td>\\iipcpld\PreloadAuto\Win8_Project\SniffAuto 请先阅读Readme for Win8 Sniff Automation.mht </td>
    		    </tr>
    		    <tr>
    		        <td style='color:red;font-weight:bold;'>Win8 RDVD自动化工具</td>
    		        <td>Win8 RDVD自动化工具</td>
    		        <td>CDL(Troy Wang/Oliver Hu/Chill Huang)</td>
    		        <td>\\iipcpld\PreloadAuto\Win8_Project\RDVDAuto 请先阅读Readme For RDVD Automation.mht</td>
    		    </tr>
    		      <tr>
    		        <td style='color:red;font-weight:bold;'>Win7 Sniff自动化工具</td>
    		        <td>Win7 Sniff自动化工具</td>
    		        <td>CDL(Troy Wang/Oliver Hu/Chill Huang)</td>
    		        <td>\\iipcpld\PreloadAuto\SniffAuto_Project  请先阅读Readme For Sniff Automation.mht</td>
    		    </tr>
    		    <tr>
    		        <td style='color:red;font-weight:bold;'>Win7 RDVD自动化工具</td>
    		        <td>Win7 RDVD自动化工具</td>
    		        <td>CDL(Troy Wang/Oliver Hu/Chill Huang)</td>
    		        <td>\\iipcpld\PreloadAuto\RDVDAuto_Project  请先阅读Readme For RDVD Automation.mht</td>
    		    </tr>
    			<tr>
    				<td rowspan="3" style="vertical-align: middle">ThinkPad</td>
    				<td style="vertical-align: middle">Test Case DB</td>
					<td style="vertical-align: middle">Japan users<br/>(Kazuo 1 Matsumoto/LENOVO)</td>
					<td >
				<pre>
					<a href="notes://10.131.128.3/tems/ppa_test_case_db.nsf" >notes://10.131.128.3/tems/ppa_test_case_db.nsf</a>
(Please make sure you have Lotus Notes client (ver7.0 or higher is required) in your PC)

If you cannot access the database, please follow the instruction.
1. Create connection document in your address book
   - Open your address book
   - Choose "Advanced" > "Connections"
   - Click "Create connections"
   - Input, Server name "NotesSrv1/Portable_PA", IP Address "10.131.128.3"
   - Save and close this document
2. Open TEMS DB as follows
   If you open from File > Database > Open, type "NotesSrv1/Portable_PA", 
   then go to "TEMS" folder, you will see "Test Case DB" and "PA Test Plan DB"
				</pre>
			</td>
    			</tr>
    			<tr >
			<td style="vertical-align: middle">Test Plan DB</td>
			<td style="vertical-align: middle">Japan users<br/>(Kazuo 1 Matsumoto/LENOVO)</td>
			<td >
			<pre>
<a href="notes://10.131.128.3/tems/ppa_test_plan_db.nsf" target="_blank">notes://10.131.128.3/tems/ppa_test_plan_db.nsf</a>
(Please make sure you have Lotus Notes client (ver7.0 or higher is required) in your PC)

If you cannot access the database, please follow the same instruction of Test Case DB
	</pre>

		</td>
	</tr>
	<tr >
		<td >Windchill(Defect Tracking)</td>
		<td >Japan users<br/>(Kazuo 1 Matsumoto/LENOVO)</td>
		<td > 
			<a href="http://bjplmapp.lenovo.com/Windchill" target="_blank">http://bjplmapp.lenovo.com/Windchill</a>
		</td>
	</tr>
	<tr >
		<td rowspan="2" style="vertical-align: middle">ThinkCentre</td>
		<td >Testcase&Testplan</td>
		<td >CDL<br/>(Chill Huang/LENOVO)</td>
		<td >
			<a href="http://pldtest:8080/dfsys" target="_blank">http://pldtest:8080/dfsys</a>&nbsp;&nbsp;(or <a href="http://10.176.36.3:8080/dfsys" target="_blank">http://10.176.36.3:8080/dfsys</a>)
		</td>
	</tr>
	<tr >
		<td >Windchill(Defect Tracking)</td>
		<td >CDL<br/>(Chill Huang/LENOVO)</td>
		<td >
			<a href="http://bjplmapp.lenovo.com/Windchill" target="_blank">http://bjplmapp.lenovo.com/Windchill</a>
		</td>
	</tr>
	<tr >
		<td  rowspan="2" style="vertical-align: middle">ThinkChina</td>
		<td >BIM(Bug Information management)</td>
		<td >Beijing<br/>(Na Ni/GDDL/LENOVO)</td>
		<td >
			<a href="http://bim.lenovo.com.cn" target="_blank">http://bim.lenovo.com.cn</a>
		</td>
	</tr>
	<tr >
		<td >Testcase &Testplan</td>
		<td >Beijing<br/>(Na Ni/GDDL/LENOVO)</td>
		<td >
			Method 1: Access <a href="http://gddl.lenovo.com/oa" target="_blank">http://gddl.lenovo.com/oa</a>, and select "VIS"<br/>
			Method 2: Directly access <a href="http://10.96.25.94:8000/background/manager/default.asp" target="_blank">http://10.96.25.94:8000/background/manager/default.asp</a>
		</td>
	</tr>
	<tr >
		<td rowspan="2" style="vertical-align: middle">IdeaCentre</td>
		<td >Testcase&Testplan</td>
		<td >Beijing<br/>(FangJ Liu/IDRD/LENOVO)</td>
		<td ><a href="http://10.96.25.94:9090/background/manager/default.asp" target="_blank">http://10.96.25.94:9090/background/manager/default.asp</a>
	</td>
		</tr>
	<tr >
		
		<td >BIM(Bug Information management)</td>
		<td >Beijing<br/>(FangJ Liu/IDRD/LENOVO)</td>
		<td >Same to "ThinkChina"</td>
	</tr>

	<tr >
		<td  rowspan="2" style="vertical-align: middle">IdeaPad</td>
		<td >Defect tracking system</td>
		<td >Shanghai<br/>(LiuD Yang/INRD/LENOVO)</td>
		<td >
			<a href="http://116.228.188.30:8081/tdms" target="_blank">http://116.228.188.30:8081/tdms</a>
		</td>
	</tr>
	
	<tr >
		<td >Ideapad Test DB</td>
		<td >Shanghai<br/>(LiuD Yang/INRD/LENOVO)</td>
		<td >it still under development and will integrated with defect tracking system</td>
	</tr>
    
    	</tbody>	

			</table>
		</div>
			<div class="col-lg-1">
		</div>
	</div>
	

</body>
</html>
