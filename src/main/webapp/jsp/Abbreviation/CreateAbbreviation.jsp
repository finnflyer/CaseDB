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
      var abbreviation = $("#abbreviationName").val();
      var description = $("#description").val();
      var comments =$("#comments").val();
      console.info(abbreviation);
      console.info(description);
      if(abbreviation=="")  {
        bootbox.alert("Abbreviation  should not be null!");
        return false;
      }

      $.ajax({
        type : "post",
        url : '<%=basePath%>/phase4/CreateAbbreviation',
        data : {//设置数据源
          abbreviation : abbreviation,
          description: description,
          abbComment : comments,
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(d) {
          console.info(d);
          bootbox.alert("Create Abbreviation Successfully!");
          window.location.href="<%=basePath%>/phase4/AbbreviationHome";
        },
        error : function(d) {
          console.info(d.responseText);
        },
      });
    });
  });
</script>

<!-- Create Abbreviation Frame -->
<div id="createAbbModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="CreateModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">Create New Abbreviation</h1>
      </div>
      <div class="modal-body">
        <form class="form col-md-12 center-block">
          <div class="form-group">
            <input type="text" id="abbreviationName" class="form-control input-lg" placeholder="Abbreviation Name">
          </div>
          <div class="form-group">
            <textarea placeholder="Description" class="form-control input-lg" id="description" rows="2"></textarea>
          </div>
          <div class="form-group">
            <textarea placeholder="Comments" class="form-control input-lg" id="comments" rows="2"></textarea>
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