<%--
  Created by IntelliJ IDEA.
  User: finnf
  Date: 2018/8/17
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Abbrevication Word</title>
</head>
<body>
<style type="text/css">
  .W120 .th-inner {
    width:120px !important;
  }
  .W80 .th-inner {
    width:80px !important;
  }
  .W60 .th-inner {
    width:60px !important;
  }

  .W150 .th-inner {
    width:150px !important;
  }
</style>
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
    <button id="addNew" class="btn btn-warn" type="button" onclick="Create()" ;>New
    </button>
    <button id="Editor" class="btn btn-warn" type="button" onclick="btn_edit()" ;>Editor
    </button>
  </div>
</div>
<div class="col-lg-2">
</div>
</div>
<br>
<br>
<!-- Show the Table of Vacation -->
<div class="row">

  <div class="col-lg-1">
  </div>

  <div class="col-lg-9">
    <!--Search Area -->
    <table id="tb_abbreviation">
    </table>
  </div>
  <div class="col-lg-1">
  </div>
</div>

<s:include value="CreateAbbreviation.jsp"></s:include>
<script>


  $(function () {
    var oTable = new TableInit();
    oTable.Init();

  });
  var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
      $('#tb_abbreviation').bootstrapTable({
        url: '<%=basePath%>phase4/AbbreviationViewJson',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                    //是否启用排序

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
         // { checkbox: true },
          {
            field: 'Id',
            title: '序号',
            visible:false,

          }, {
            field: 'Abbreviation',
            title: '缩写',
            class: "W80",
            formatter: function (value, row, index){ // 单元格格式化函数

              var div = "<div style='width:250px;'>"+value+"</div>";//调列宽，在td中嵌套一个div，调整div大小
              return div;
            }

          }, {
            field: 'Description',
            title: '描述',
            class: "W80",
            formatter: function (value, row, index){ // 单元格格式化函数
              var div = "<div style='width:250px;'>"+value+"</div>";//调列宽，在td中嵌套一个div，调整div大小
              return div;
            }
          }, {
            field:"Comments",
            title:"备注",
            formatter: function (value, row, index){ // 单元格格式化函数
              var str; var div;
              console.info(value.length);

              if (value.length>30){
                str = value.substr(0,30);
                 div = "<div style='width:80px;'>"+str+"<button data-container='body' data-toggle='popover' data-placement='top' "+
                        "data-content='"+value+"'>...</button>"+
                        "</div>";//调列宽，在td中嵌套一个div，调整div大小
              }
              else{
                 div = "<div style='width:100px;'>"+value+
                        "</div>";//调列宽，在td中嵌套一个div，调整div大小
              }
              return div;
            },

          },{
            field:"Uploador",
            title:"上传人",
          },
        ],


      }).on('all.bs.table', function (e, name, args) {
        $('[data-toggle="tooltip"]').tooltip();
        $('[data-toggle="popover"]').popover();
      });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {

      var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
        search:params.search,
      };
      return temp;
    };
    return oTableInit;
  };
  function btn_edit(){
    var url = "<%=basePath%>phase4/AbbreviationEdit";
    window.location.href = url;
  }
  function Create(){
    $("#createAbbModal").modal("show");
  }
  $(function (){
    $("[data-toggle='popover']").popover();
  });
</script>

</body>
</html>
