<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>OT List</title>
</head>
<body>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/bootstrap-table/bootstrap-table.css">
</link>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/bootstrap3-editable/css/bootstrap-editable.css">
</link>

<script src="<%=basePath%>js/Plugin/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="<%=basePath%>js/Plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=basePath%>js/Plugin/bootstrap-table/extensions/editable/bootstrap-table-editable.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>

<script src="<%=basePath%>js/Plugin/tableExport/libs/pdfmake/pdfmake.min.js"></script>
<script src="<%=basePath%>js/Plugin/tableExport/libs/pdfmake/vfs_fonts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/FileSaver/FileSaver.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/jsPDF/jspdf.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/tableExport.js"></script>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9">
        <button id="applyOT" class="btn btn-warn" type="button" onclick="applyOT()" ;>我要加班
        </button>
        <button id="applyVacation" class="btn btn-warn" type="button" onclick="applyVacation()" ;>我要休假
        </button>
        <butonn id="exportRequest" class="btn btn-warn" type="button" onclick="goExoprt()">格式导出</butonn>
        <butonn id="EditOTUser" class="btn btn-warn" type="button" onclick="goEditOTUser()">编辑用户</butonn>
    </div>
</div>
<div class="col-lg-2">
</div>
</div>
<!--Search Area --->
<br>
<br>
<!-- Show the Table of Vacation -->
<div class="row">

    <div class="col-lg-1">
    </div>

    <div class="col-lg-9">
        <!--Search Area --->
        <div id="toolbar" class="input-group">
            <select id="Dep" class="form-control" style="width: auto;">
                <option value="All">All</option>
                <option value="PAI">PAI</option>
                <option value="PAII">PAII</option>
            </select>
            &nbsp;&nbsp;
            <button id="edit" class="btn btn-info" onclick="btn_edit()">
                <i class="glyphicon glyphicon-pencil"></i> 编辑
            </button>
        </div>
        <table id="tb_overtime">
        </table>
    </div>
    <div class="col-lg-1">
    </div>
</div>
<script>
    $(function () {
        var oTable = new TableInit();
        oTable.Init();
        $("#Dep").on("change",function(){
            $('#tb_overtime').bootstrapTable('refresh');
        });
    });
    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#tb_overtime').bootstrapTable({
                url: '<%=basePath%>phase4/OverTimeViewJson',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: 20,                       //每页的记录行数（*）
                pageList: [10,20,30,50,100,200],        //可供选择的每页的行数（*）
                strictSearch: false,
                search:true,
                trimOnSearch:true,
                //showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
               // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                cardView: false,                    //是否显示详细视图
                //detailView: false,                   //是否显示父子表onEditableSave
                showExport: true,
                exportDataType: 'all',
                exportTypes:[ 'csv', 'txt', 'excel', 'xlsx',],  //导出文件类型
                columns: [
                    { checkbox: true },
                    {
                        field: 'id',
                        title: '序号',
                        visible:false,
                    }, {
                        field: 'Department',
                        title: '部门',

                    }, {
                        field: 'LoginName',
                        title: '登录名',
                    }, {
                        field:"Name",
                        title:"名字",

                    },{
                        field:"RemainTime",
                        title:"剩余年假",
                    },
                    {
                        field: 'Daysoff',
                        title: '剩余调休',

                    }, {
                        field: 'Payment',
                        title: 'Payment',

                    }, {
                        field: 'TIL',
                        title: 'TIL',
                    },{
                        field: 'Total',
                        title: '总计'
                    }
                ],

            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {

            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                Dep:$("#Dep option:selected").val(),

            };
            return temp;
        };
        return oTableInit;
    };
    function btn_edit(){
        var url = "<%=basePath%>phase4/PressEdit";
        window.location.href = url;
    }
    $(function () {
        $("#Home").removeClass("active");
        $("#TestCase").addClass("active");
    });
    function applyOT() {
        var url = "<%=basePath%>phase4/OverTimePage";
        window.location.href = url;
    }
    function applyVacation() {
        var url = "<%=basePath%>phase4/VacationPage";
        window.location.href = url;
    }

    function goExoprt(){
        var url = "<%=basePath%>phase4/ExportOT";
        window.location.href = url;
    }
    function goEditOTUser(){
        var url = "<%=basePath%>phase4/EditOTUser";
        window.location.href = url;
    }
</script>
</body>
</html>
