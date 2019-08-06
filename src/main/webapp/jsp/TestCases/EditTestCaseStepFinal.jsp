<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>Step Final</title>
</head>

<body>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="<%=basePath%>js/EditTestCase.js"></script>
<div class="row">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <Button id="select" class="btn btn-warn" onclick="javascript:history.back(1);">Back</button>
    </div>
    <div class="col-lg-1"></div>
</div>
<div class="row-fluid">
    <div class="col-lg-12">
        <h4 style="color:red;">Case Name : <s:property value="%{testCase.casename}"/></h4>
        <h4> Step 3 </h4>

        <p class="text-right">Case Owner:<s:property value="#session.user.username"/></p>
        <fieldset>
            <legend>3-Case Step :</legend>
            <s:fielderror theme="bootstrap"/>
            <ui>
                <li style="color:red">P1 – MUST executing in every testing</li>
                <li style="color:red">P2 – US&Localized APP Testing</li>
                <li style="color:red">P3 – Function Test</li>
            </ui>
            <s:hidden name="caseInfoInstkey" value="#session.caseinfoinstkey"/>
            <s:hidden name="caseInstkey" value="#session.caseInstkey"/>
            <s:form action="phase4/UpdateCaseStepFinal" enctype="multipart/form-data" method="post"
                    onsubmit="return submitValidation();" theme="simple">
                <s:hidden name="sid" value="%{sid}"/>

            <table id="TCItems" class="table table-bordered" style="table-layout:fixed">
                <thead>
                <th style="width:4%;"></th>
                <th style="width:4%;">Level</th>
                <th style="width:20%;">Items</th>
                <th style="width:20%;">Case Steps</th>
                <th style="width:20%;">Expect</th>
                <th style="width:10%">Pics</th>
                <th sytle="width:10%">Priority</th>
                <th style="width:4%">Time</th>
                <th>Comments</th>
                </thead>
                <tbody id="tablebody">
                <s:iterator value="testcasecontent" status="st">
                <tr>
                    <td style="width:8%">
                        <a href='javascript:void(0);' onclick='removeRow(this);'><img src='image/minus.png' height='16'
                                                                                      width='16' alt='Delete'/></a>
                        <br/>
                        <a href='javascript:void(0);' onclick='addRow(this);'><img src='image/plus.png' height='16'
                                                                                   width='16' alt='Insert'/></a>
                        <br/>
                        <a href='javascript:void(0);' onclick='moveUp(this);'><img src='image/arrow_up.png' height='16'
                                                                                   width='16' alt='Up'/></a>
                        <br/>
                        <a href='javascript:void(0);' onclick='moveDown(this);'><img src='image/arrow_down.png'
                                                                                     height='16' width='16' alt='Down'/></a>
                    </td>
                    <td>
                        <s:select list="{'P1','P2','P3'}" value='%{testcasecontent[#st.index].caselevel}'
                                  cssStyle="width:100%" name='testcasecontent[%{#st.index}].caselevel' theme='simple'/>
                    </td>
                    <td>
                        <s:textarea value='%{testcasecontent[#st.index].testitem}'
                                    name='testcasecontent[%{#st.index}].testitem' rows='4' cssStyle="width:100%"
                                    theme='simple'/>
                    </td>
                    <td>
                        <s:textarea value='%{testcasecontent[#st.index].teststep}'
                                    name='testcasecontent[%{#st.index}].teststep' rows='4' cssStyle="width:100%"
                                    theme='simple'/>
                    </td>
                    <td>
                        <s:textarea value='%{testcasecontent[#st.index].testresult}'
                                    name='testcasecontent[%{#st.index}].testresult' rows='4' cssStyle="width:100%"
                                    theme='simple'/>
                    </td>
                    <td style="word-wrap:break-word">

                        <s:set value="testcasecontent.CasePics" var="CasePics"/>
                        <s:iterator value="CasePics" var="cp">
                            <div id="photoLinkListDiv">
                                <s:a id='pic%{#cp.pictureinstkey}' href="javascript:void(0);"
                                     onclick="showPhoto('%{#cp.pictureinstkey}');return false;"> <s:property
                                        value="filename"/> </s:a>
                                <s:a id='del%{#cp.pictureinstkey}' href='javascript:void(0);'
                                     onclick="delphoto('%{#cp.pictureinstkey}');return false;"><img
                                        src='image/minus.png' height='16' width='16' alt='Delete'/></s:a>
                                <s:hidden name="oldpic_path" value="%{filepath}"/>
                                <s:hidden name="oldpic_order" value="0"/>
                            </div>
                        </s:iterator>

                        <div id="uploadfile" title="fileload">
                            <input type='file' name='upload'/>
                            <input name="picName" type="hidden" value="-1">
                            <input name="hasPic" type="hidden" value="">&nbsp; &nbsp;
                            <a href='javascript:void(0);' onclick='addInput(this);'>
                                <img src='image/plus.png' height='16' width='16' alt='Add'/>
                            </a>&nbsp; &nbsp;
                            <a href='javascript:void(0);' onclick='removeLink(this);'>
                                <img src='image/minus.png' height='16' width='16' alt='minus'/>
                            </a>
                        </div>
                        <div id="photoDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header" style="background-color: #0480be;">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                                        </button>
                                    </div>
                                    <div id="photoBody" class="modal-body" style="padding: 0">
                                    </div>
                                    <div class="modal-footer">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </td>
                    <td>
                        <s:select list="{'High','Medium','Emergency','Low'}"
                                  value='%{testcasecontent[#st.index].priority}'
                                  cssStyle="width:100%" name='testcasecontent[%{#st.index}].priority' theme='simple'/>
                    </td>
                    <td>
                        <s:textarea value='%{testcasecontent[#st.index].steptime}'
                                    name='testcasecontent[%{#st.index}].steptime' cssStyle="width:100%" theme='simple'/>
                    </td>
                    <td>
                        <s:textarea id='Comments' value='%{testcasecontent[#st.index].comments}'
                                    name='testcasecontent[%{#st.index}].comments' cssStyle="width:100%" rows='4'
                                    theme='simple'/>
                    </td>
                    </s:iterator>
                </tr>
                </tbody>
            </table>

            <div class="col-lg-offset-10  col-lg-10" class="btn-toolbar" role="toolbar">
                <div class="btn-group">
                    <button type="button" class="btn btn-primary" id='AddRow' onclick='var abc=null;addRow(abc)'>Add a
                        row
                    </button>
                </div>
                <div class="btn-group">
                    <s:submit type="button" cssClass="btn btn-primary" value="Update"></s:submit>
                </div>
            </div>

            </s:form>
    </div>
</div>

</body>
</html>
