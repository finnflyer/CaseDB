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
    $("#CreateBtn").click(function() {
      var testPlanName = $("#testPlanName").val().trim();
      var comments =$("#testPlanComments").val().trim();

      if(testPlanName=="")  {
        bootbox.alert("Test Plan Name should not be null!");
        return false;
      }
      var str = "";
      var checkbox = document.getElementsByName("scriptid");
      var checklist = 0;
      for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          checklist += 1;
        }
      }
      if (checklist == 0) {
        alert("please select one Case ");
        return;
      }
      for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          str += " " + checkbox[i].getAttribute("value");
        }
      }
      console.info(str);
      console.info(testPlanName+" Name  "+comments);
      $.ajax({
        type : "post",
        url : '<%=basePath%>/phase4/CreateTestPlan',
        data : {//设置数据源
          testPlanName : testPlanName,
          testPlanComments : comments,
          "str": str
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(d) {
          console.info(d);
          if(d.projectKey!=""){
            bootbox.alert("Create Successfully!");
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
<!-- New TestPlan Frame -->
<div id="testPlanCreateModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="testPlanModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">Create Test Plan</h1>
      </div>
      <div class="modal-body">
        <form class="form col-md-12 center-block">
          <div class="form-group">
            <input type="text" id="testPlanName" class="form-control input-lg" placeholder="Test Plan Name">
          </div>
          <div class="form-group">
            <input type="text" id="testPlanComments" class="form-control input-lg" placeholder="Comments">
          </div>

          <div class="form-group">
            <button id="CreateBtn" class="btn btn-primary btn-lg btn-block" data-dismiss="modal">Create</button>
            </div>
        </form>
      </div>

      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>


