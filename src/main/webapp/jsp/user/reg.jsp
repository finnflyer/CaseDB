<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/9/1
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

  $(function() {
    $("#regBtn").click(function() {
      var userName = $("#reguserName").val().trim();
      var password1 =$("#regpw").val().trim();
      var password2 =$("#regpws").val().trim();
      console.info(password1);
      console.info(password2);
      if(password1!=password2)  {
          alert("Password is not the same!");
          return false;
      }
      if (userName == '') {
        alert("Empty User name is not allowed");
        return false;
      }
      console.info(userName+" pw  "+password1);

      //login1为Action类命名空间名称；AjaxExecute为Action方法名称
      $.ajax({
        type : "post",
        url : '<%=basePath%>/User/userRegAction',
        data : {//设置数据源
          userName : userName,
          password : password1
        },
        dataType : "json",//设置需要返回的数据类型
        success : function() {
          alert("Register done");
            $("#loginModal").click();
        },
        error : function(d) {
          alert(d.responseText)
          alert("UserName has been used!");
            $("#Register").click();
        }
      });
    });
  });
  </script>
<div id="Register" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="Label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">注册</h1>
      </div>
      <div class="modal-body">
        <form  class="form col-md-12 center-block">
          <div class="form-group">
            <input type="text" id="reguserName" name="userName" class="form-control input-lg" placeholder="UserName">
          </div>
          <div class="form-group">
            <input type="password" id="regpw" class="form-control input-lg" placeholder="Password">
          </div>
          <div class="form-group">
            <input type="password" id="regpws" class="form-control input-lg" placeholder="Confirm Password">
          </div>

          <div class="form-group">
            <button id="regBtn" class="btn btn-primary btn-lg btn-block">Register</button>
          </div>
        </form>
      </div>
      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>
