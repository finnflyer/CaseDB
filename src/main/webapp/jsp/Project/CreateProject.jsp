<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/9/7
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
  $(function() {
    $("#CreateBtn").click(function() {
      var projectName = $("#projectName").val().trim();
      var comments =$("#comments").val().trim();

      if(projectName=="")  {
        bootbox.alert("Project Name should not be null!");
        return false;
      }

      console.info(projectName+" Name  "+comments);
      $.ajax({
        type : "post",
        url : '<%=basePath%>/phase4/CreateProject',
        data : {//设置数据源
          projectName : projectName,
          projectComments : comments
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(d) {
          console.info(d);
        if(d.projectKey!=""){
          bootbox.alert("Create Project Successfully!");
          window.location.href="<%=basePath%>/phase4/ShowProjectDetail?projectKey="+ d.projectKey;
        }
        },
        error : function(d) {
          console.info(d.responseText);
        },
      });
    });
  });
</script>

<!-- Create Project Frame -->
<div id="createModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="CreateModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">Create New Project</h1>
      </div>
      <div class="modal-body">
        <form class="form col-md-12 center-block">
          <div class="form-group">
            <input type="text" id="projectName" class="form-control input-lg" placeholder="Project Name">
          </div>
          <div class="form-group">
            <textarea placeholder="Project Comments" class="form-control input-lg" id="comments" rows="3"></textarea>
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