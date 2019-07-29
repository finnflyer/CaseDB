<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/12/1
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Edit User Role</title>
</head>
<body>
<script type="text/javascript">
  $(document).ready(
          function () {
            handleCheckBox();
          }
  );
  function handleCheckBox(){

  }
  function validation(){
    var obj=$("#role").val();
    console.info(obj);
    if(obj==0)
    {
      alert("role setting is required");
      return false;
    }
  }
</script>
<div class="row">
  <div class="col-lg-2">
  </div>
  <div class="col-lg-7">
    <table class="table table table-bordered table-striped" style="table-layout:fixed">
      <s:form action="phase4/updateUserRole"  theme="simple" onsubmit="return validation(); ">
      <tr>
        <td>User:</td>
        <td><s:hidden name="user.instkey" value="%{user.instkey}" /><s:property value="user.username"  /></td>
      </tr>
      <tr>
        <td>Role:</td>
        <td>
        <select name="user.role" id="role">
          <option value="0"> </option>
          <option value="1">Admin</option>
          <option value="2">Tester</option>
          <option value="3">Family Owner</option>
          <option value="4">Test Leader</option>
        </select>
        </td>
      </tr>
        <tr>
          <td colspan="2">
            <div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar">
              <div class="btn-group">
                <s:submit type="button" cssClass="btn btn-primary" value="Update"></s:submit>
              </div>
            </div>
          </td>
        </tr>
      </s:form>
    </table>
  </div>
  <div class="col-lg-3"></div>
</div>

</body>
</html>
