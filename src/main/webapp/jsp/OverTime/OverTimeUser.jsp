<%--
  Created by IntelliJ IDEA.
  User: auto
  Date: 2018/9/2
  Time: 21:46
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
    <title>OverTime User</title>
</head>
<body>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/bootstrap-table/bootstrap-table.css">
</link>
<link rel="stylesheet" href="<%=basePath%>js/Plugin/bootstrap3-editable/css/bootstrap-editable.css">
</link>

<script src="<%=basePath%>js/Plugin/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script src="<%=basePath%>js/Plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=basePath%>js/Plugin/bootstrap-table/extensions/editable/bootstrap-table-editable.js"></script>
<script type="text/javascript"
        src="<%=basePath%>js/Plugin/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>

<script src="<%=basePath%>js/Plugin/tableExport/libs/pdfmake/pdfmake.min.js"></script>
<script src="<%=basePath%>js/Plugin/tableExport/libs/pdfmake/vfs_fonts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/FileSaver/FileSaver.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/libs/jsPDF/jspdf.min.js"></script>
<script type="text/javascript"
        src="<%=basePath%>js/Plugin/tableExport/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<script type="text/javascript" src="<%=basePath%>js/Plugin/tableExport/tableExport.js"></script>

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
    <table id="tb_overTimeUser">
    </table>
  </div>
  <div class="col-lg-1">
  </div>
</div>

<script>
  $(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $("#Dep").on("change", function () {
      $('#tb_overtime').bootstrapTable('refresh');
    });

  });
  var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
      $('#tb_overTimeUser').bootstrapTable({
        url: '<%=basePath%>phase4/OverTimeUserViewJson',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: oTableInit.queryParams,//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 20,                       //每页的记录行数（*）
        pageList: [10, 20, 30, 50, 100, 200],        //可供选择的每页的行数（*）
        strictSearch: false,
        search: true,
        trimOnSearch: true,              //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        cardView: false,                    //是否显示详细视图
        //detailView: false,                   //是否显示父子表onEditableSave
        showExport: true,
        exportDataType: 'all',
        exportTypes: ['csv', 'txt', 'excel', 'xlsx',],  //导出文件类型
        columns: [
          {
            field: 'id',
            title: '序号',
            visible: false,
          }, {
             field:'LoginName',
             title:'登录名'

          },{
            field: "UserName",
            title: "名字",
            editable: {
              type: 'text',
              title: '用户名',
              validate: function (v) {
                if (!v) return '用户名不能为空';
              }
            }
          },{
            field: 'Employid',
            title: '工号',
            editable: {
              type: 'text',
              title: '工号',
              validate: function (v) {
                if (!v) return '工号不能为空';
                var regu = /^[0-9]+\.?[0-9]*$/;
                if (!regu.test(v))
                  return ("请输入正确的数字");
              }
            }
          },
          {
            field: 'operate',
            title: '操作',
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
          }
        ],
        onEditableSave: function (field, row, oldValue, $el) {
          console.info(oldValue);
          $.ajax({
            type: "post",
            url: "<%=basePath%>phase4/EditOverTimeUser",
            data: {strJson: JSON.stringify(row)},
            success: function (data, status) {
              if (status == "success") {
                bootbox.alert("Edit Successfully!");
              }
            },
            error: function () {
              bootbox.alert("Error");
            },
            complete: function () {
              $('#tb_overTimeUser').bootstrapTable("refresh");
            }

          });
        }
      });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
      var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset,  //页码
      };
      return temp;
    };
    return oTableInit;
  };
  var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
      //初始化页面上面的按钮事件
    };

    return oInit;
  };
  function operateFormatter(value, row, index) {
    return [
      '<a class="remove" href="javascript:void(0)" title="Remove">',
      '<i class="glyphicon glyphicon-remove"></i>',
      '</a>'
    ].join('');
  }
  window.operateEvents = {
    'click .remove': function (e, value, row, index) {
      $.ajax({
        type: "post",
        url: "<%=basePath%>phase4/DeleteOverTimeUser",
        data: {strJson: row.id},
        success: function (data, status) {
          if (status == "success") {
            bootbox.alert("Delete Successfully!");
          }
        },
        error: function () {
          bootbox.alert("Error");
        },
        complete: function () {

        }

      });
      $('#tb_overTimeUser').bootstrapTable('remove', {
        field: 'id',
        values: [row.id]
      });

    }
  };
</script>
<script>

  $(function () {
    $("#btn_submit").click(function () {
      if ($("input[name='Name']").val() == "") {
        bootbox.alert("User Name should not be null!");
        $('#newOTModal').modal('show');
        return false;
      }
      var regu = /^[0-9]+\.?[0-9]*$/;
      if ($("input[name='RemainTime']").val() != ""){
        if (!regu.test($("input[name='RemainTime']").val())) {
          bootbox.alert("年假请输入正确的数字");
          $('#newOTModal').modal('show');
          return false;
        }
      }
      if ($("input[name='RemainDaysoffTime']").val() != ""){
        if (!regu.test($("input[name='RemainDaysoffTime']").val())) {
          bootbox.alert("调休请输入正确的数字");
          $('#newOTModal').modal('show');
          return false;
        }
      }


      $.ajax({
        type: "post",
        url: '<%=basePath%>/phase4/AddOTBean',
        data: {//设置数据源
          newDep: $("#newDep option:selected").val(),
          Name: $("input[name='Name']").val(),
          RemainTime: $("input[name='RemainTime']").val(),
          RemainDaysoffTime: $("input[name='RemainDaysoffTime']").val(),
        },
        dataType: "json",//设置需要返回的数据类型
        success: function (d) {
          console.info(d.isSuccess);
          if (d.isSuccess == 1) {
            bootbox.alert("Successfully!");
            setTimeout('window.location.href="index.jsp"', 500);
          }
        },
        error: function (d) {
          console.info(d.responseText);
        },
      });
    });
  });
  function validationNumber(e, num) {
    var regu = /^[0-9]+\.?[0-9]*$/;
    if (e.value != "") {
      if (!regu.test(e.value)) {
        bootbox.alert("请输入正确的数字");

        $('#newOTModal').modal('show');
        e.value = e.value.substring(0, e.value.length - 1);
        e.focus();
        return false;
      } else {
        if (num == 0) {
          if (e.value.indexOf('.') > -1) {
            e.value = e.value.substring(0, e.value.length - 1);
            e.focus();
          }
        }
        if (e.value.indexOf('.') > -1) {
          if (e.value.split('.')[1].length > num) {
            e.value = e.value.substring(0, e.value.length - 1);
            e.focus();
          }
        }
      }
    }
  }
</script>

</body>
</html>
