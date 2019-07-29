<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>MFG Tools</title>

</head>


<body>
<script type="text/javascript">
    $(function() {
        $("#Home").removeClass("active");
        $("#Debug").addClass("active");
    });
    $(document).ready(function(){
        $("table tbody tr td").each(function(){
            var txt = $(this).context.innerHTML;
            console.info($(this).context.innerHTML);
            var regx=new RegExp("&lt;br&gt;","g");
            if(txt!="" && txt!=undefined){
                txt = txt.replace(regx,"<br/>");
                $(this).context.innerHTML = txt;
            }
        })
    })
    function UploadTool() {
        var url = "<%=basePath%>phase4/preSaveFile";
        window.location.href = url;
    }
</script>

<div class="row">

    <div class="col-lg-offset-1">
        <h3>MFG Tool For Preload Debug</h3>
    </div>
    <div class="col-lg-2">
    </div>
</div>
<div class="row">
    <div class="col-lg-10">

    </div>
    <div class="col-lg-2">
        <s:if test="#session.user.username != 'tester'">
            <s:if test="#session.user.role =='Case Owner' || #session.user.role =='Admin' ||#session.user.role =='PA Team Lead'">
                <button id="Upload" class="btn btn-warn" type="button" onclick="UploadTool()" ;>Upload Tool
                </button>
            </s:if>
        </s:if>
    </div>
</div>
<br>
<br>

<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <s:if test="#request.pageBean.list.size() > 0">
            <div>
                <div>
                    <form id="TCForm" method="post" style="margin: 0px;">
                    </form>
                </div>
            </div>
            <s:include value="/jsp/MFGTools/MFGToolView.jsp"></s:include>
            <div class="col-lg-1">
            </div>
        </s:if>
        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
            <h3>There is no result found!</h3>
        </s:elseif>
    </div>
</div>
</body>
</html>
