<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base id="base" href="<%=basePath%>">
    <title>Setting User Role</title>
</head>

<body>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/indexCss.css" rel="stylesheet">
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery-1.11.3.js"></script>

<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/bootbox.min.js"></script>
<script src="<%=basePath%>js/import.js"></script>
<script type="text/javascript">
    $(function() {
        $("#Home").removeClass("active");
        $("#Role").addClass("active");
    });
    $(document).ready(function () {
        $("#roleTable").bootstrapTable({
            method: "get",
            url: "<%=basePath%>phase4/GetUserList",
//            params: {
//                pageNumber: "2",
//                pageSize: "30"
//            },
//            editable: true,//开启编辑模式
//            clickToSelect: true,
            columns: [
                [
                    {field:"instkey",title:"Instkey",align:"center"},
                    {field:"username",title: "UserName", align: "center"},
                    {field:"role",title: "Role", align: "center"},
                    {field:"mailbox",title:"Mailbox",align:"center"},
                    {field:"live",title:"Active",align:"center"}
                ],
            ],
            onLoadSuccess:function(data){
                console.info(data);
            },
            //sidePagination:"server",
            //data:[{"instkey":"201503261552320705VC","live":"1","mailbox":"chillh@lenovo.com","role":"Admin","username":"admin"},{"instkey":"20150413092315043DH","live":"1","role":"Admin","username":"Raylei"},{"instkey":"20150527134158022OF","live":"1","role":"Family Owner","username":"louisalou"},{"instkey":"201505271344500129DY","live":"1","role":"Family Owner","username":"kristycheng"},{"instkey":"201505271345040671BV","live":"1","role":"Tester","username":"petercai@lenovo.com"},{"instkey":"201505271429190632HF","live":"1","role":"Leader","username":"duanzg"},{"instkey":"201505271431310891SV","live":"1","role":"Family Owner","username":"petercai"},{"instkey":"201505271453120780ZI","live":"1","role":"Tester","username":"jennyzh"},{"instkey":"201505271718080370OH","live":"1","role":"Family Owner","username":"Huangje"},{"instkey":"201505271718560235LT","live":"1","role":"Case Owner","username":"huangje@lenovo.com"},{"instkey":"201505291317450817UC","live":"1","role":"Family Owner","username":"Selinah"},{"instkey":"20150529144814033SN","live":"1","role":"Family Owner","username":"rockyyu"},{"instkey":"20150603143804068QY","live":"1","role":"Family Owner","username":"glenfl"},{"instkey":"201506031539510566HR","live":"1","role":"Tester","username":"jeff"},{"instkey":"201506031543000144IH","live":"1","role":"Case Owner","username":"zhanglh5"},{"instkey":"201506031547010979SP","live":"1","role":"Tester","username":"jfcaic@isoftstone.com"},{"instkey":"201506031611580143NP","live":"1","role":"Tester","username":"obama"},{"instkey":"20150604143244095GO","live":"1","role":"Case Owner","username":"emmali"},{"instkey":"201506041433110902ZD","live":"1","role":"Case Owner","username":"karsonfang"},{"instkey":"201506041433110902ZK","live":"1","role":"Tester","username":"tester"}]

        });
    })
</script>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <table class="table table-bordered" id="roleTable">

        </table>
    </div>
    <div class="col-lg-1">
    </div>
</div>
</body>
</html>