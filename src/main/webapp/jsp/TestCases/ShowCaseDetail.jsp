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
    <base id='base' href="<%=basePath%>">
    <title>TestCaseDetail</title>
</head>

<style type="text/css">
    #photoDlg .modal-body {
        max-height: 800px;
    }
</style>
<script src="<%=basePath%>js/StringForHtml.js"></script>
<script type="text/javascript">

    function centerModals() {
        $('#photoDlg').each(function (i) {
            var $clone = $(this).clone().css('display', 'block').appendTo('body');
            var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
            top = top > 0 ? top : 0;
            $clone.remove();
            $(this).find('.modal-content').css("margin-top", top);
        });
    }
    $('#photoDlg').on('show.bs.modal', centerModals);

   function showPhoto(path) {
        console.info(path);
        var html = "";
        html += "<img src='download/ShowPicture?photoPath=" + path + "' onload='javascript:AutoResizeImage(800,600,this)' alt='960X540'/>";
        console.info(html);
        $("#photoBody").html(html);
        console.info("test");
        $("#photoDlg").modal('show');
    }


    var flag = false;
    function AutoResizeImage(maxWidth, maxHeight, objImg) {
        var img = new Image();
        img.src = objImg.src;
        var hRatio;
        var wRatio;
        var Ratio = 1;
        var w = img.width;
        var h = img.height;
        wRatio = maxWidth / w;
        hRatio = maxHeight / h;
        if (maxWidth == 0 && maxHeight == 0) {
            Ratio = 1;
        } else if (maxWidth == 0) {//
            if (hRatio < 1) Ratio = hRatio;
        } else if (maxHeight == 0) {
            if (wRatio < 1) Ratio = wRatio;
        } else if (wRatio < 1 || hRatio < 1) {
            Ratio = (wRatio <= hRatio ? wRatio : hRatio);
        }
        if (Ratio < 1) {
            w = w * Ratio;
            h = h * Ratio;
        }
        objImg.height = h;
        objImg.width = w;
    }
    function EditCaseStepOne() {
        var key = "<s:property value='sid' />";
        var url = "<%=basePath%>phase4/EditCaseStepOne?sid=" + key;
        window.location.href = url;
    }
    function DeleteCase() {
        console.info("start to delete");
        var key = "<s:property value='testCase.caseinstkey' />";
        console.info(key);
        bootbox.confirm("Are you sure you are going to Delete this Case?", function (result) {
            if(result){
                var url = "<%=basePath%>phase4/DeleteCase?caseInstkey=" + key;
                window.location.href = url;
            }

        })
        <%--if (confirm('Are you sure you are going to Delete this Case?')) {--%>
        <%--var url = "<%=basePath%>phase4/DeleteCase?caseInstkey=" + key;--%>
        <%--window.location.href = url;--%>
        <%--}--%>

    }

</script>
<div class="row">
<div class="col-lg--12" style="padding-left:60px;padding-right:60px"  class="btn-toolbar" role="toolbar">
    <div class="btn-group">
    <Button id="select" class="btn btn-warn" onclick="javascript:history.back(1);" >Back</button>
        </div>
    <s:if test="#session.user.username !='tester' ">
        <s:if test="#session.user.role =='Leader' || #session.user.role =='Case Owner' || #session.user.role =='Admin' || #session.user.role =='Family Owner'">
            <div class="btn-group">
                <button id="EditCase" class="btn btn-warn" type="button" onclick="EditCaseStepOne()" ;>Edit Test Case
                </button>
            </div>
            <div class="btn-group">
                <button id="DeleteTestCase" class="btn btn-warn" type="button" onclick="DeleteCase();">Delete Test Case
                </button>
            </div>
        </s:if>
    </s:if>
</div>
</div>
<br>
<div class="row-fluid">
    <div class="col-lg-12" style="padding-left:60px;padding-right:60px">
        <div class="row">
            <div class="col-lg-12">
            </div>

            <legend style="font-size:16px">1 - Basic Information:</legend>
            <table class="table table table-bordered ">
                <tr>
                    <td class="active" width="130px">Category:</td>
                    <td width="200px"><s:property value="testCaseInfo.funcCato"/></td>
                    <td class="active" width='130px'>Case Name:</td>
                    <td><s:property value="testCase.casename"/></td>
                    <td class="active" width="130px">Case Owner:</td>
                    <td><s:property value="testCase.owner"/></td>
                </tr>
                <tr>
                    <td class="active">SupportOS</td>
                    <td><s:property value="testCaseInfo.osCato"/></td>
                    <td class="active">System Brand</td>
                    <td><s:property value="testCaseInfo.brandCato"/></td>
                    <td class="active">Case ID</td>
                    <td><s:property value="testCase.casecode"/></td>
                </tr>

                <tr>
                    <td class="active">Version</td>
                    <td><s:property value="testCase.version"/></td>
                </tr>
                <tr>
                    <td class="active">Modify Reason:</td>
                    <td id="history" colspan="7"><s:property value="testCaseInfo.modifyreason"/></td>
                </tr>
            </table>
            <legend style="font-size:16px">2 - Language and HW Support :</legend>
            <table class="table table-bordered">
                <tr>
                    <td class="active">language Support</td>
                    <td class="active" width='40%'>Language Comments</td>
                </tr>
                <tbody>
                <tr>
                    <td>
                        <table class="table table-condensed table-bordered">
                            <s:set value="LanBean" var="langsDB"/>
                            <tr>
                                <td style="font-size:14px" bgcolor="#E4EEF9" align="center" width="30px">OS</td>
                                <s:iterator value="LanBean" var="LanBean" status="st">
                                    <s:if test="#st.index<=16 && #st.index>1">
                                        <td align="center" bgcolor="#E4EEF9" width="40px">
                                            <p style="font-size:14px"><s:property value="lanvalue"/></p>
                                        </td>
                                    </s:if>
                                </s:iterator>
                            </tr>
                            <tr>
                                <td align="center">Language</td>
                                <s:iterator value="CaseLan" var="CaseLan" status="st">
                                    <s:if test="#st.index<15 && #st.index>=0">
                                        <td align="center">
                                            <s:property value="lanvalue"/>
                                        </td>
                                    </s:if>
                                </s:iterator>
                            </tr>
                            <tr>
                                <td style="font-size:14px" bgcolor="#E4EEF9" align="center" width="30px">OS</td>
                                <s:iterator value="LanBean" var="LanBean" status="st">
                                    <s:if test="#st.index<=31 && #st.index>=17">
                                        <td align="center" bgcolor="#E4EEF9" width="40px">
                                            <s:property value="lanvalue"/>
                                        </td>
                                    </s:if>
                                </s:iterator>

                            </tr>
                            <tr>
                                <td align="center">Language</td>
                                <s:iterator value="CaseLan" var="CaseLan" status="st">
                                    <s:if test="#st.index<=33 && #st.index>=15">
                                        <td align="center"><s:property value="lanvalue"/></td>
                                    </s:if>
                                </s:iterator>
                            </tr>
                        </table>
                    </td>
                    <td id='lanCom'><s:property value="testCaseInfo.languagecomment"/></td>
                </tr>
                </tbody>
            </table>

            <table class="table table-bordered">
                <tr>
                    <td class="active"> HW requirements</td>
                    <td class="active" width="40%"> HW Comments</td>
                </tr>
                <tr>
                    <td id="hwInfo"><s:property value="testCaseInfo.hardwareinfo"/></td>
                    <td><s:property value="testCaseInfo.comments"/></td>
                </tr>
            </table>
            <legend style="font-size:16px">3 - Test Items :</legend>
            <ui>
                <li style="color:red">P1 – MUST executing in every testing</li>
                <li style="color:red">P2 – US&Localized APP Testing</li>
                <li style="color:red">P3 – Function Test</li>
            </ui>
            <div class="row">
                <div class="col-lg-offset-10" style="color:red">
                    Total Time: <s:property value="testCaseInfo.executetime"/>mins

                </div>

                <table id="TCItems" class="table table-bordered " style="table-layout:fixed">
                    <thead>
                    <tr>
                        <th style="width:5%;background-color: #f5f5f5;font-size:10pt;">Level</th>
                        <th style="width:12%;background-color: #f5f5f5;font-size:10pt;">Test Item</th>
                        <th style="width:24%;background-color: #f5f5f5;font-size:10pt;">Test Steps/Description</th>
                        <th style="width:24%;background-color: #f5f5f5;font-size:10pt;">Expect Results</th>
                        <th style="width:10%;background-color: #f5f5f5;font-size:10pt;">Pictures</th>
                        <th style="width:8%;background-color: #f5f5f5;font-size:10pt;">Priority</th>
                        <%--<th style="width:10%;text-align:center;background-color: #f5f5f5;font-size:10pt;">Time</th>--%>
                        <th style="background-color: #f5f5f5;font-size:10pt;" id='comHead'>Comments</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="testCaseContents" status="st">
                        <tr>
                            <td style="color:red;text-align:center"><s:property value="caselevel"/></td>
                            <td style="font-weight:normal"><p style="font-weight:normal"><s:property
                                    value="testitem"/></p>
                            </td>
                            <td><s:property value="teststep"/></td>
                            <td style="word-wrap:break-word"><s:property value="testresult"/></td>
                            <td style="color:darkblue;word-wrap:break-word">
                                <div id="photoLinkListDiv">
                                    <s:set value="testCaseContents.CasePics" var="CasePics"/>
                                    <s:iterator value="CasePics" var="cp">
                                        <s:a href="javascript:void(0);"
                                             onclick="showPhoto('%{#cp.pictureinstkey}');return false;"> <s:property
                                                value="filename"/> </s:a>
                                    </s:iterator>
                                </div>
                            </td>

                            <td style="font-weight:normal"><p style="font-weight:normal"><s:property
                                    value="priority"/></p>
                            </td>
                            <%--<td>--%>
                                <%--<s:property value="steptime"/>--%>
                            <%--</td>--%>
                            <td style="word-wrap:break-word" long='<s:property value="comments" />' short=''><s:property
                                    value="comments"/></td>
                        </tr>
                    </s:iterator>

                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <div id="photoDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="width:800px;">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #0480be;">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div id="photoBody" class= "modal-body" style="padding: 0">
                </div>
                <div class="modal-footer">
                </div>
            </div>

        </div>
    </div>
    <div id="TestPlanForm" style="magrin-left:20;magrin-top:100;display:none">
        <input type="button" id="addTomyList" value="add"/>
        <table border='1' class="tablesorter" style="width:400px">
            <thead>
            <tr>
                <th>#</th>
                <th>Test Plan Name</th>
                <th> Date</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="testPlanList" var="tpList">
                <tr>
                    <td><input type="radio" name="testPlanInstkey"
                               value='<s:property value="#tpList.testPlanInstkey" />'/></td>
                    <td><s:property value="#tpList.testPlanName"/></td>
                    <td><s:property value="#tpList.createDate"/></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
    </body>
</html>