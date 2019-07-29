<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/9/8
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
  $(function() {
    $("#addTestPlanBtn").click(function() {
      var str = "";
      var checkbox = document.getElementsByName("scriptid");
      var checklist = 0;
      for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          checklist += 1;
        }
      }
      for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          str += " " + checkbox[i].getAttribute("value");
        }
      }
      var inkey = "";
      var radios = document.getElementsByName("testPlanInstkey");
      console.info(radios);
      for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
          inkey = radios[i].value;
        }
      }
      if (inkey == "") {
        bootbox.alert("Please select one Test Plan");
        return;
      }

      console.info(str);
      console.info(inkey+" Name  ");
      $.ajax({
        type : "post",
        url : '<%=basePath%>/phase4/AddContentToTestPlan',
        data : {//设置数据源
          "testPlanInstkey": inkey,
          "str": str
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(d) {
          console.info(d);
          if(d.projectKey!=""){
            bootbox.alert("Added Successfully!");
            window.location.href="<%=basePath%>phase4/ShowProjectDetail?projectKey="+ d.projectKey;
          }
        },
        error : function(d) {
          console.info(d.responseText);
        },
      });
    });
  });
</script>
<!--  Exist TestPlan Frame -->
<div id="testPlanModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="testPlanModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">TestPlan</h1>
      </div>
      <div class="modal-body">
        <Button class="btn btn-sm btn-info" id="addTestPlanBtn">Select to Add</Button>
        <br>
        <br>
        <table class=" table table-bordered" >
          <thead>
          <tr>
            <th>#</th>
            <th>Test Plan Name</th>
            <th> Create Date</th>
          </tr>
          </thead>
          <tbody>
          <s:iterator value="testPlanList" var="tpList">
            <tr>
              <td><input type="radio" name="testPlanInstkey" value='<s:property value="#tpList.testplaninstkey" />'/>
              </td>
              <td><s:property value="#tpList.testplanname"/></td>
              <td><s:property value="#tpList.createdate"/></td>
            </tr>
          </s:iterator>
          </tbody>
        </table>
      </div>

      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>


