<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>百度UEditor编辑器</title>
    <script type="text/javascript" src="<%=basePath%>js/UEditor/ueditor.config.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/UEditor/ueditor.all.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/js/UEditor/themes/default/css/ueditor.css">
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/indexCss.css" rel="stylesheet">

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8"
            src="<%=basePath%>/js/UEditor/lang/zh-cn/zh-cn.js"></script>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/jquery-1.11.3.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/bootbox.min.js"></script>
    <style type="text/css">
        div {
            width: 100%;
        }
    </style>

</head>
<body>
<script type="text/javascript">
    UE.getEditor("container");
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadfile') {
            return '<%=basePath%>test/ueuploadimage';  //此处改需要把图片上传到哪个Action（Controller）中
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };
</script>
<div class="row-fluid">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
    <textarea id="container" name="container">

    </textarea>
    </div>
</div>

</body>
</html>