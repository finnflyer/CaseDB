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
    <title>Search Test Case For Plan</title>


    <style type="text/css">
        body {
            padding-bottom: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>

</head>

<body>

<script type="text/javascript">
    function submitValidation() {
        var catoSelect = document.getElementsByName("mapFunction");
        var list = 0;
        for (var i = 0; i < catoSelect.length; i++) {
            if (catoSelect[i].checked) {
                list += 1;
            }
        }
        if (list == 0) {
            alert("Please select on Category!");
            return false;
        }
    }
    function NewTestPlan() {
        var checkbox = document.getElementsByName("scriptid");
        var checklist = 0;
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                checklist += 1;
            }
        }
        if (checklist == 0) {
            alert("please select one case");
            return;
        }
        $("#testPlanCreateModal").modal("show");
    }
    function addtoTestPlan() {
        var checkbox = document.getElementsByName("scriptid");
        var checklist = 0;
        for (var i = 0; i < checkbox.length; i++) {
            if (checkbox[i].checked) {
                checklist += 1;
            }
        }
        if (checklist == 0) {
            alert("please select one case");
            return;
        }
        $("#testPlanModal").modal("show");
    }

    $(document).ready(function () {
        $("#Simple").click(function () {
            $("#simpleSearch").show();
            $("#advanceSearch").hide();
        })
        $("#Advance").click(function () {
            $("#advanceSearch").show();
            $("#simpleSearch").hide();
        })
    });

    $(document).ready(function () {
        $("#addTomyList").click(function () {
            var str = "";
            $("[@type='checkbox'][@name='scriptid'][checked]").each(function () {
                str += " " + $(this).val();
            });
            var inkey = "";
            var radios = document.getElementsByName("testPlanInstkey");
            console.info(radios);
            for (var i = 0; i < radios.length; i++) {
                if (radios[i].checked) {
                    inkey = radios[i].value;
                }
            }
            console.info(inkey);
            if (inkey == "") {
                alert("Please select one Test Plan");
            }
            $.ajax({
                type: "POST",
                url: "phase4/SaveContentToOlderTestPlan",
                dataType: "html",
                data: {
                    "testPlanInstkey": inkey,
                    "str": str
                },
                success: function (returnedData) {
                    if (returnedData == "success") {
                        $("#TestPlanForm").dialog("close");
                        alert("Adding succeed.");
                        var url = "phase4/ShowTestPlan?testPlanInstkey=" + inkey;
                        document.forms["solutionForm"].action = url;
                        document.forms["solutionForm"].submit();

                    }
                }
            });
        });

    });
    function selectAll() {
        var allselect = document.getElementsByName("AllSelect");
        console.info(allselect);

        var checkbox = document.getElementsByName("scriptid");
        console.info(allselect.checked);
        if (allselect[0].checked) {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = true;
            }
        } else {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = false;
            }
        }
    }
    function selectBrand() {
        var allselect = document.getElementsByName("Brand");
        console.info(allselect);

        var checkbox = document.getElementsByName("mapBrand");
        console.info(allselect.checked);
        if (allselect[0].checked) {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = true;
            }
        } else {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = false;
            }
        }

    }
    function selectCate() {
        console.info("start select");
        var allselect = document.getElementsByName("selectCategory");
        console.info(allselect);

        var checkbox = document.getElementsByName("mapFunction");
        console.info(allselect.checked);
        if (allselect[0].checked) {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = true;
            }
        } else {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = false;
            }
        }
    }
    function selectTestMode(){
        var allselect = document.getElementsByName("TestMode");
        console.info(allselect);
        var checkbox = document.getElementsByName("mapTestMode");
        console.info(allselect.checked);
        if (allselect[0].checked) {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = true;
            }
        } else {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = false;
            }
        }
    }
    function selectOs() {
        var allselect = document.getElementsByName("Os");
        console.info(allselect);

        var checkbox = document.getElementsByName("mapOs");
        console.info(allselect.chceked);
        if (allselect[0].checked) {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = true;
            }
        } else {
            for (var i = 0; i < checkbox.length; i++) {
                checkbox[i].checked = false;
            }
        }
    }
</script>

<div id="simpleSearch" class="row" style="display:none">

    <button id="Advance" class="btn btn-warn" type="button">Advance Search
    </button>
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9">
        <div class="row">

            <div class="col-lg-1">
            </div>

            <div class="col-offset-lg-3">
                <s:form action="phase4/SimpleSearchCaseForPlan" cssClass=" form-inline" theme="simple">
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Case Name  </span>&nbsp;&nbsp;&nbsp;
                        <input type="text" class="form-control" name="caseName" placeholder="Case Name">
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <span style="font-size:11pt;font-weight:bold">Case Owner</span>&nbsp;&nbsp;&nbsp;
                        <input type="text" class="form-control" name="caseOwner" placeholder="Case Owner">
                    </div>
                    &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                    <s:hidden name="searchflag" value="1"/>

                    <s:submit cssClass="btn btn-primary" value="Search"></s:submit>
                    <div class="form-group">
                        <s:if test="#session.user.username !='tester' ">
                            <button id="findTestPlan" class="btn btn-info" type="button" onclick="NewTestPlan();" ;>
                                Create New
                            </button>
                        </s:if>

                        <buton id="addTestPlan" class="btn btn-info" type="button" onclick="addtoTestPlan()" ;>Add to
                            Plan
                        </buton>
                    </div>
                </s:form>

            </div>
        </div>
    </div>

    <div class="col-lg-2">

    </div>

</div>
<div id="advanceSearch" class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <div class="row">
            <button id="Simple" class="btn btn-warn" type="button">Simple Search
            </button>
            <div class="col-offset-lg-9">

                <H3 style="color:red">Advanced Search</H3>
                <s:form action="phase4/AdvanceSearchCaseForPlan" cssClass=" form-inline"
                        onsubmit="return submitValidation();" theme="simple">
                    <table class="table">
                        <tr>
                            <td width="120px" style="font-weight:bold">System Brand</td>

                            <td class="form-group" colspan="6">

                                <input type="checkbox" name="Brand" onclick="selectBrand();"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" name="mapBrand" value="2" id="mapBrand-1"/>
                                <label for="mapBrand-1" class="checkboxLabel"
                                       style="font-weight:normal">ThinkStation</label>
                                <input type="checkbox" name="mapBrand" value="1" id="mapBrand-2"/>
                                <label for="mapBrand-2" class="checkboxLabel"
                                       style="font-weight:normal">ThinkPad</label>
                                <input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapBrand"
                                       name="__multiselect_mapBrand" value=""/></td>

                        </tr>
                        <tr>
                            <td width="120px" style="font-weight:bold">Test Mode</td>

                            <td class="form-group" colspan="6">

                                <input type="checkbox" name="TestMode" onclick="selectTestMode();"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" name="mapTestMode" value="1" id="mapTestMode-1"/>
                                <label for="mapBrand-1" class="checkboxLabel"
                                       style="font-weight:normal">Normal</label>
                                <input type="checkbox" name="mapTestMode" value="2" id="mapTestMode-2"/>
                                <label for="mapBrand-2" class="checkboxLabel"
                                       style="font-weight:normal">S Mode</label>
                                <input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapTestMode"
                                       name="__multiselect_mapTestMode" value=""/></td>

                        </tr>
                        <tr>
                            <td style="font-weight:bold">Support OS</td>
                            <td colspan="6">
                                <input type="checkbox" name="Os" onclick="selectOs();"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" name="mapOs" value="1" id="mapOs-1"/>
                                <label for="mapOs-1" class="checkboxLabel" style="font-weight:normal">XP</label>
                                <input type="checkbox" name="mapOs" value="2" id="mapOs-2"/>
                                <label for="mapOs-2" class="checkboxLabel " style="font-weight:normal">Win7</label>
                                <input type="checkbox" name="mapOs" value="3" id="mapOs-3"/>
                                <label for="mapOs-3" class="checkboxLabel" style="font-weight:normal">Win8.x</label>
                                <input type="checkbox" name="mapOs" value="4" id="mapOs-4"/>
                                <label for="mapOs-4" class="checkboxLabel " style="font-weight:normal">Win10</label>
                                <input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapOs"
                                       name="__multiselect_mapOs" value=""/></td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td style="font-weight:bold">Category</td>
                            <td colspan="6">
                                <input type="checkbox" name="selectCategory" onclick="selectCate();"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" name="mapFunction" value="1" id="mapFunction-1"/>
                                <label for="mapFunction-1" class="checkboxLabel" style="font-weight:normal">1 - Preload
                                    Process</label>
                                <input type="checkbox" name="mapFunction" value="2" id="mapFunction-2"/>
                                <label for="mapFunction-2" class="checkboxLabel" style="font-weight:normal">2 - OS
                                    Basic</label>
                                <input type="checkbox" name="mapFunction" value="3" id="mapFunction-3"/>
                                <label for="mapFunction-3" class="checkboxLabel" style="font-weight:normal">3 - Store
                                    App</label>
                                <input type="checkbox" name="mapFunction" value="4" id="mapFunction-4"/>
                                <label for="mapFunction-4" class="checkboxLabel" style="font-weight:normal">4 - Win32
                                    App</label>
                                <input type="checkbox" name="mapFunction" value="5" id="mapFunction-5"/>
                                <label for="mapFunction-5" class="checkboxLabel" style="font-weight:normal">5 - HW and
                                    Drivers</label>
                                <input type="checkbox" name="mapFunction" value="6" id="mapFunction-6"/>
                                <label for="mapFunction-6" class="checkboxLabel" style="font-weight:normal"> 6 -
                                    Multimedia</label>
                                <input type="checkbox" name="mapFunction" value="7" id="mapFunction-7"/>
                                <label for="mapFunction-7" class="checkboxLabel" style="font-weight:normal">7 -
                                    Recovery</label>
                                <input type="checkbox" name="mapFunction" value="8" id="mapFunction-8"/>
                                <label for="mapFunction-8" class="checkboxLabel" style="font-weight:normal">8 - CTO and
                                    Special Bid</label>
                                <input type="checkbox" name="mapFunction" value="9" id="mapFunction-9"/>
                                <label for="mapFunction-9" class="checkboxLabel" style="font-weight:normal">9 - IoT</label>
                                <input type="hidden" id="__multiselect_SearchTestCaseForPlan_mapFunction"
                                       name="__multiselect_mapFunction" value=""/></td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td colspan="7">


                            </td>
                            <td>
                                <s:hidden name="searchflag" value="1"/>
                                <s:submit cssClass="btn btn-primary" value="Search"></s:submit> &nbsp;&nbsp;
                            </td>
                            <td>
                                <buton id="addTestPlan" class="btn btn-info" type="button" onclick="addtoTestPlan()" ;>
                                    Add
                                    to Plan
                                </buton>
                            </td>
                            <td>
                                <s:if test="#session.user.username !='tester' ">
                                    <button id="findTestPlan" class="btn btn-warn" type="button"
                                            onclick="NewTestPlan();" ;>Create New Test Plan
                                    </button>
                                </s:if>
                            </td>
                        </tr>

                    </table>


                </s:form>

            </div>
        </div>
    </div>
    <div class="col-lg-1">
    </div>
</div>

<s:include value="TestPlanListForAdd.jsp"/>
<s:include value="CreateTestPlan.jsp"/>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-10">
        <s:if test="#request.pageBean.list.size() > 0">
            <div class="col-offset-lg-9">
                <s:include value="/jsp/TestPlan/CaseForPlanView.jsp"></s:include>
            </div>
        </s:if>
        <s:elseif test="#request.pageBean.list!=null && #request.pageBean.list.size() == 0">
            <h3>There is no result found!</h3>
        </s:elseif>
    </div>
    <div class="col-lg-1">
    </div>
</div>

</body>
</html>