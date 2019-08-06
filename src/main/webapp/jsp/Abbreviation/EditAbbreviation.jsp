<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Edit</title>
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
<div class="col-lg-1">

</div>
<div class="btn-group">
    <Button id="select" class="btn btn-warn" onclick="javascript:history.back(1);">Back</button>
</div>
<br>
<br>
<!-- Show the Table of Vacation -->
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9">
        <table id="tb_abbreviation">
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
                    {checkbox: true},
                    {
                        field: 'Id',
                        title: '序号',
                        visible: false,
                    }, {
                        field: "Abbreviation",
                        title: "缩写",
                        editable: {
                            type: 'text',
                            title: '缩写',
                            validate: function (v) {
                                if (!v) return '缩写不能为空';
                                var reg=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
                                if(reg.test(v))
                                    return "备注中含有非法字符";
                            }
                        }
                    }, {
                        field: 'Description',
                        title: '描述',
                        editable:{
                            type: 'text',
                            title: '描述',
                            validate: function (v) {
                                if (!v) return '描述不能为空';
                                var reg=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
                                if(reg.test(v))
                                    return "备注中含有非法字符";
                            }
                        }

                    },  {
                        field: 'Comments',
                        title: '备注',
                        editable:{
                            type: 'text',
                            title: '备注',
                            validate: function (v) {
                                var reg=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
                                if(reg.test(v))
                                    return "备注中含有非法字符";
                            }
                        }

                    },{
                        field:"Uploador",
                        title:"上传人",
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
                        url: "<%=basePath%>phase4/EditAbbreviation",
                        data: {strJson: JSON.stringify(row)},
                        success: function (data, status) {
                            if (status == "success") {
                                alert("编辑成功");
                            }
                        },
                        error: function () {
                            alert("Error");
                        },
                        complete: function () {
                            $('#tb_abbreviation').bootstrapTable("refresh");
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
                search:params.search,
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
                url: "<%=basePath%>phase4/DeleteAbbreviation",
                data: {strJson: JSON.stringify(row)},
                success: function (data, status) {
                    if (status == "success") {
                        bootbox.alert("Successfully!");
                    }
                },
                error: function () {
                    bootbox.alert("Error");
                },
                complete: function () {

                }

            });
            $('#DeleteAbbreviation').bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });

        }
    };
</script>
</body>
</html>
