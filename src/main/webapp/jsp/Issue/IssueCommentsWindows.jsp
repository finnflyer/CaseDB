<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/9/12
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript">
    $(function () {
        $("#addBtn").click(function () {
            $("#commentsListModal").modal("hide");
            $("#commentsModal").modal("show");
        });
    });
</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Upload Attached File Frame -->
<div id="commentsListModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="CreateModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>

                <h1 class="text-left text-primary">CommentList</h1>
                <button id="addBtn" class="btn btn-warn">Add</button>
            </div>
            <div id="CommentList" class="modal-body">
                <table class="table table-border">
                    <th>Comments</th>
                    <th>Owner</th>
                    <th>Date</th>
                    <tbody id="showCommentList">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>