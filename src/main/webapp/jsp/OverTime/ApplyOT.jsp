<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>">
<html>
<head>
    <title>Apply OT</title>
    <style type="text/css">
        .input {
        }

        .input-wide {
            width: 500px;
        }

    </style>
</head>
<body>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9">
        <fieldset>
            <legend>Apply for OT</legend>
            <s:form class="form-horizontal" role="form" action="recordOverTime" namespace="/phase4" onsubmit="return check(this);">
                <div class="form-group">
                    <label class="col-sm-3 control-label">OT类型:
                    </label>

                    <div class="col-sm-3 controls">
                        <select name="OTType" id="ottype" class="form-control">
                            <option value=""></option>
                            <option value="TIL">TIL</option>
                            <option value="Payment">Payment</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">时间类型:
                    </label>

                    <div class="col-sm-3 controls">
                        <label class="radio-inline">
                            <input type="radio" name="btnStatus"
                                   value="1" checked>整天
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="btnStatus"
                                   value="0">时间段
                        </label>
                    </div>

                </div>
                <div id="days" class="form-group">
                    <label class="col-sm-3 control-label">起止日期:
                    </label>

                    <div class="col-sm-2">
                        <input type="text" name="StartDate" id="StartDate"/>
                    </div>
                    <div class="col-sm-1"><span class="input-group-addon" id="span"> 到</span></div>
                    <div class="col-sm-2">
                        <input type="text" name="EndDate" id="EndDate"/>
                    </div>
                </div>
                <div id="Oneday" class="form-group hidden">
                    <label class="col-sm-3 control-label">起止时间:
                    </label>

                    <div class="col-sm-2">
                        <input type="text" name="StartTime" id="StartTime"/>
                    </div>
                    <div class="col-sm-1"><span class="input-group-addon"> 到</span></div>
                    <div class="col-sm-2">
                        <input type="text" name="EndTime" id="EndTime"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">申请理由:
                    </label>

                    <div class="col-sm-9 controls">
                        <textarea name="applyreason" cols="" rows="10" id="index_thoughts"
                                  class="form-control input-xxlarge"></textarea></div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-md-9">
                        <input type="submit" value="Submit" id="index_0" class="btn btn-primary">
                    </div>
                </div>
            </s:form>
        </fieldset>

    </div>
    <div class="col-lg-1">
    </div>
</div>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/datetimepicker/jquery.datetimepicker.css"/>

<script src="<%=basePath%>js/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">

   function check(form){
       console.info(form.applyreason.value);
        if(form.applyreason.value==""){
            bootbox.alert("No Apply Reason");
            return false;
        }
       if(form.OTType.value==""){
           bootbox.alert("please select OT Type");
           return false;
       }
       return true;
    }
    $(document).ready(function () {
        $('input[type=radio][name=btnStatus]').change(function () {
            if (this.value == '1') {
                $("#EndDate").show();
                $("#span").show();
                $('#Oneday').removeClass("show");
                $('#Oneday').addClass("hidden");
            }
            else if (this.value == '0') {
                $("#EndDate").hide();
                $('#span').hide();
                $('#Oneday').removeClass("hidden");
                $('#Oneday').addClass("show");
            }
        });

    });


    $.datetimepicker.setLocale('en');
    $('#StartDate').datetimepicker({
        yearOffset: 0,
        lang: 'ch',
        timepicker: false,
        format: 'Y/m/d',
        formatDate: 'Y/m/d',
        minDate: '-1970/01/01', // yesterday is minimum date
        //maxDate: '+1970/01/02' // and tommorow is maximum date calendar
    });
    $('#EndDate').datetimepicker({
        yearOffset: 0,
        lang: 'ch',
        timepicker: false,
        format: 'Y/m/d',
        formatDate: 'Y/m/d',
        minDate: '+1970/01/02', // yesterday is minimum date
    });
    $('#StartTime').datetimepicker({
        datepicker: false,
        format: 'H:i',
        allowTimes: ['8:00', '8:30', '9:00', '9:30', '10:00', '10:30', '11:00', '11:30',
            '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '16:30',
            '17:00', '17:30', '18:00', '18:30', '19:00', '19:30', '20:00', '20:30',
            '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'
        ],
        step: 5
    });
    $('#EndTime').datetimepicker({
        datepicker: false,
        format: 'H:i',
        allowTimes: ['8:00', '8:30', '9:00', '9:30', '10:00', '10:30', '11:00', '11:30',
            '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '16:30',
            '17:00', '17:30', '18:00', '18:30', '19:00', '19:30', '20:00', '20:30',
            '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'
        ],
        step: 5
    });
</script>
</body>
</html>
