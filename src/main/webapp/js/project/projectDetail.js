/**
 * Created by Admin on 2016/9/13.
 */

var issueInProjectKey;
var projectKey = $("#key").val();
function moreComment(key) {
    issueInProjectKey = key;
    console.info(key);
    $.ajax({
        type: "post",
        url: 'phase4/ShowComments',
        data: {//设置数据源
            issueKey: key,
        },
        dataType: "json",//设置需要返回的数据类型
        success: function (d) {
            console.info(d.length);
            var html = "";
            for (var i = 0; i < d.length; i++) {
                html += "<tr><td>" + d[i].comments + "</td>";
                html += "<td>" + d[i].createBy + "</td>";
                html += "<td>" + d[i].createDate + "</td>";

            }
            $("#showCommentList").html(html);
            $("#commentsListModal").modal('show');
        },
        error: function (d) {
            console.info(d.responseText);
        },

    })
}
$(document).ready(function(){
    $("#show").click(function () {
        $("#testPlan").toggle(500,function(){
            var btnText = document.getElementById("show").innerHTML;
            if(String(btnText).lastIndexOf("Show")<0){
                document.getElementById("show").innerHTML="Show Test Plan Sheet";
            }else{
                document.getElementById("show").innerHTML = "Hide Test Plan Sheet";
            }
        });
    });
});
//$(document).ready(function () {
//
//    console.info("lllll: "+ $("#key").val());
//    var key =  $("#key").val();
//    //生成可编辑表格
//    $("#EditTable").bootstrapTable({
//        method: 'get',
//        url:'phase4/EditIssueForJson',
//        params : {
//            projectKey:"iiiii",
//            aaaa:"skskksks"
//        },
//        editable: true,//开启编辑模式
//        clickToSelect: true,
//        columns: [
//            [
//                {title:"ECR",align:"center"},
//                {title:"Name",align:"center"},
//                {field:"Status",title:"Status",
//                    edit:{
//                        type:'select',//下拉框
//                        //url:'user/getUser.htm',
//                        data:[{id:1,text:'lzx'},{id:2,text:'lsl'}],
//                        valueField:'id',
//                        textField:'text',
//                        onSelect:function(val,rec){
//                            console.log(val,rec);
//                        }
//                    },align:"center",valign:'middle'}
//            ]
//    ]
//    });
//});


