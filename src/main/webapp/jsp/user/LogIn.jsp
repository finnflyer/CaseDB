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
    $("#loginBtn").click(function() {
      var userName = $("#userName").val().trim();
      var password1 =$("#pw").val().trim();
      var vali = $("#loginCode").val().trim();
      if(password1=="")  {
        bootbox.alert("Password should not be null!");
        return false;
      }
      if (userName == '') {
        bootbox.alert("Empty User Name!!");
        return false;
      }
      if(vali==''){
        bootbox.alert("No Validation Code！！");
        return;
      }
      console.info(userName+" pw  "+password1);
      $.ajax({
        type : "post",
        url : '<%=basePath%>/User/LoginUser',
        data : {//设置数据源
          userName : userName,
          password : password1,
          vali:vali,
        },
        dataType : "json",//设置需要返回的数据类型
        success : function(d) {
          console.info(d.isSuccess);
          if(d.isSuccess==1){
            bootbox.alert("Successfully!");
            setTimeout('window.location.href="index.jsp"' ,500);
          }
          if(d.isSuccess==0){
            bootbox.alert("User is not registered!");
            $("#Register").click();
          }
          if(d.isSuccess==2){
            bootbox.alert("Error Password!");
            setTimeout('window.location.href="index.jsp"' ,500);

          }
          if(d.isSuccess==3){
            bootbox.alert("Error ValiCode! ");
            setTimeout('window.location.href="index.jsp"' ,500);

          }
        },
        error : function(d) {
          console.info(d.responseText);
        },
//                error : function() {
//                    alert("用户名未被注册!");
//                    $("#Register").click();
//                }
      });
    });
  });
</script>
<script type="text/javascript">
  function changeValidateCode(obj) {
    //获取当前的时间作为参数，无具体意义
    var timenow = new Date().getTime();
    //每次请求需要一个不同的参数，否则可能会返回同样的验证码
    //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
    obj.src="<%=basePath%>/phase4/CreateImgCode?d="+timenow;
  }
</script>

<!-- Login Frame -->
<div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h1 class="text-center text-primary">Sign In</h1>
      </div>
      <div class="modal-body">
        <form class="form col-md-12 center-block">
          <div class="form-group">
            <input type="text" id="userName" class="form-control input-lg" placeholder="User Name">
          </div>
          <div class="form-group">
            <input type="password" id="pw" class="form-control input-lg" placeholder="Password">
          </div>
          <div class="form-group">
              <input name="vali" id="loginCode" type="text" class="form-control" placeholder="请输入验证码">
           </div>
          <div class="form-group">
          <img id='imgVcode' src="<%=basePath%>/phase4/CreateImgCode" onclick="changeValidateCode(this)" title="点击图片切换" style="cursor: pointer;" />
          </div>
          <div class="form-group">
            <button id="loginBtn" class="btn btn-primary btn-lg btn-block" data-dismiss="modal">Log in</button>
            <!-- <span><a href="#">找回密码</a></span> -->
            <%--<span><a href="">Change Password</a></span>--%>
            <br>
            <span><a onclick="$('#loginModal').modal('hide')" data-toggle="modal" data-target="#Register" class="pull-right">Reigster</a></span>
          </div>
        </form>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>


<!-- debug-->
<%--<div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
  <%--<div class="modal-dialog">--%>
    <%--<img src="image/3.png" />--%>
  <%--</div>--%>
<%--</div>--%>

