<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
  $(function() {
    $("#CreateBtn").click(function() {
      var comments =$("#comments").val().trim();

      if(comments=="")  {
        bootbox.alert("comments should not be null!");
        return false;
      }
      var key = "<s:property value='issueBean.instkey' />";
      if(key==""){
        key=issueInProjectKey;
      }

      console.info(" Name  "+comments);
      $.ajax({
        type : "post",
        url : '<%=basePath%>/phase4/CreateComment',
        data : {//设置数据源
          issueKey : key,
          comments : comments
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(d) {
          console.info(d);
          if(d.issueKey!=""){
            bootbox.alert("Create Comments Successfully!");
            window.location.href="<%=basePath%>/phase4/ShowIssueDetail?issueKey="+ d.issueKey;
          }
        },
        error : function(d) {
          console.info(d.responseText);
        },
      });
    });
  });
</script>
<!-- Upload Attached File Frame -->
<div id="commentsModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="CreateModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">Add New Comments</h1>
      </div>
      <div class="modal-body">
        <form class="form col-md-12 center-block">
          <div class="form-group">
            <textarea placeholder="Comments" class="form-control input-lg" id="comments" rows="4"></textarea>
          </div>
          <div class="form-group">
            <button id="CreateBtn" class="btn btn-primary btn-lg btn-block" data-dismiss="modal">Add</button>
          </div>
        </form>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>