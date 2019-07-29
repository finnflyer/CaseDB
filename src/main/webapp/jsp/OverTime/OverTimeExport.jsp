<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>OverTime Export</title>
</head>
<body>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/bootstrap-table/bootstrap-table.css">
</link>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/bootstrap3-editable/css/bootstrap-editable.css">
</link>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/daterangepicker/daterangepicker-bs3.css">
</link>
<script src="<%=basePath%>js/Plugin/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="<%=basePath%>js/Plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=basePath%>js/Plugin/bootstrap-table/extensions/editable/bootstrap-table-editable.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/daterangepicker/daterangepicker.js"></script>

<script src="<%=basePath%>js/Plugin/tableExport/libs/pdfmake/pdfmake.min.js"></script>
<script src="<%=basePath%>js/Plugin/tableExport/libs/pdfmake/vfs_fonts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/FileSaver/FileSaver.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/jsPDF/jspdf.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/tableExport.js"></script>

<div class="row">
  <div class="col-lg-1"></div>
  <div class="col-lg-9">
    <div class="form-group">
      <label class="col-lg-2 col-md-2 col-sm-12 control-label">Date range picker</label>
      <div class="col-lg-10 col-md-10">
        <div class="row">
          <div class="col-lg-4 col-md-4">
            <div class="input-group">
              <input class="form-control" id="daterangepicker">
              <span class="input-group-addon"><i class="fa-calendar"></i></span>
            </div>
          </div>
          <div class="col-lg-4 col-md-4">
              <button id="searchOverTimeUser" class="btn btn-primary">Search</button>
          </div>
          <div class="col-lg-4 col-md-4">
          </div>
        </div>
      </div>
      </div>
    </div>
  <div class="col-lg-2"></div>
</div>

<!-- Show the Table of OT -->
<div class="row">
  <div class="col-lg-1">
  </div>
  <div class="col-lg-9">
    <!--Search Area --->
    <div id="toolbar" class="input-group">

    </div>
    <div class="input-group">

    </div>
    <table id="tb_overtimeExport">
    </table>
  </div>
  <div class="col-lg-1">
  </div>
</div>

<script>
  $(function () {
    var oTable = new TableInit();
    oTable.Init();
    $("#searchOverTimeUser").click(function () {
      if ($("#daterangepicker").val() == "") {
        bootbox.alert("No Time Select");
        return;
      }
      console.info($("#daterangepicker").val());
      $('#tb_overtimeExport').bootstrapTable('refresh', {
        url: '<%=basePath%>phase4/OverTimeExportView',
        silent: true,
        query: {
          rangeTime: $("#daterangepicker").val(),
        }
      });
    });
  });
    var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
      $('#tb_overtimeExport').bootstrapTable({
        url: '<%=basePath%>phase4/OverTimeExportView',         //请求后台的URL（*）
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
        //search:true,
        //trimOnSearch:true,
        //showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        cardView: false,                    //是否显示详细视图
        showExport: true,
        exportDataType: 'all',
        exportTypes:[ 'csv', 'txt', 'excel', 'xlsx'],  //导出文件类型
        columns: [
          { checkbox: true },
          {
            field: 'id',
            title: '序号',
            visible:false,
          }, {
            field:"Name",
            title:"名字",

          },{
            field:"Employid",
            title:"工号",
          },
          {
            field: 'Date',
            title: '日期',

          }, {
            field: 'StartTime',
            title: '开始时间',

          }, {
            field: 'EndTime',
            title: '结束时间',
          },{
            field: 'Type',
            title: '报酬类型',
          },{
            field:'Description',
            title:'任务说明'
          }
        ],

      });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {

      var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        rangeTime:$("#daterangepicker").val(),

      };
      return temp;
    };
    return oTableInit;
  };

  $(document).ready(function(){
    $('#daterangepicker').daterangepicker();
    });



</script>
</body>
</html>
