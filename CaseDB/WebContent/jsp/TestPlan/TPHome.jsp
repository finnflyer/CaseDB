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
    <base id="base" href="<%=basePath%>">
    <title>Test Plan</title>
    


		<style type="text/css">
        body {
            padding-bottom: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
          }
          .link_disable	{ color: gray;	text-decoration: underline;}
    </style>

  </head>

<body >
<script type="text/javascript">
    		function NewTestPlan(){
   			 var url ="<%=basePath%>phase4/preTestPlan";
   		    window.location.href=url;
   		}
   </script>
	
	<br>
	<br>
	<div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-9">
		<div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-offset-lg-4">
		<s:form action="phase4/SearchTestPlan"  cssClass=" form-inline"  theme="simple" >
            <div class="form-group">
           	 <span style="font-size:11pt;font-weight:bold">Test Plan Name  </span>&nbsp;&nbsp;&nbsp;
              <input type="text" class="form-control" name="testPlanName" placeholder="Test Plan Name">
            </div>
            &nbsp;&nbsp;&nbsp;
            <div class="form-group">
            	<span style="font-size:11pt;font-weight:bold" >Plan Owner</span>&nbsp;&nbsp;&nbsp;
              <input type="text" class="form-control" name="testPlanOwner" placeholder="Test Plan Owner">
            </div>
            &nbsp;&nbsp;    &nbsp;&nbsp;    &nbsp;&nbsp;
           	 <s:hidden name="searchflag" value="1" />
           	  
            <s:submit cssClass="btn btn-primary" value="Search"></s:submit>  
             &nbsp;&nbsp; &nbsp;&nbsp;  &nbsp;&nbsp; &nbsp;&nbsp;
           
	      	  <a id="newCase"  onclick="NewTestPlan()";><ins style="font-Style:italic;color:black">New Test Plan</ins></a>
          </s:form>
      
          		</div>
          		</div>
		</div>
	
       <div class="col-lg-2">
		
	    </div>

</div>
</div>
	<br>
	<br>
	<div class="row">
		<div class="col-lg-1">
		</div>
		<div class="col-lg-10">
	    <s:if test="#request.pageBean.list.size() > 0">
	    <div >
           <div >
	    	<form id="TCForm" method="post" style="margin: 0px;">
	    	</form>
	    </div>
	    </div>
	 	<s:include value="/jsp/TestPlan/TestPlanView.jsp"></s:include>
	 		<div class="col-lg-1">
		</div>
 	</s:if>
 	<s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
 		<h3>There is no result found!</h3>
 	</s:elseif>
		</div>
	</div>

</body>
</html>