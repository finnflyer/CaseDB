<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>">
<table class="table table table-bordered table-striped">
    <thead>
    <tr>
        <th class="info" width="3%" style="text-align:center"><input type="checkbox" name="AllSelect" id="All"
                                                                     onclick="selectAll();"></th>
        <th class="info" width="6%">CaseID</th>
        <th class="info" width="13%">Category</th>
        <th class="info" width="30%">CaseName</th>
        <th class="info" width="4%">Ver</th>
        <th class="info">OS</th>
        <th class="info" width="18%">Brand</th>
        <th class="info">Owner</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="#request.pageBean.list" var="tcinfo">

        <tr>
            <td><input type="checkbox" name="scriptid" value='<s:property value="#tcinfo.caseinstkey" />'/></td>
            <td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a
                    href='<%=path%>/phase4/ShowTestCaseDetail?testCase.caseinstkey=<s:property value="#tcinfo.caseinstkey" />'>
                <s:property value="#tcinfo.casecode"/>
            </a>
            </td>
            <td><s:property value="#tcinfo.functioncato"/></td>
            <td>
                <a href='<%=path%>/phase4/ShowTestCaseDetail?testCase.caseinstkey=<s:property value="#tcinfo.caseinstkey" />'>
                    <s:property value="#tcinfo.casename"/> </a></td>
            <td><s:property value="#tcinfo.version"/></td>
            <td><s:property value="#tcinfo.osCato"/></td>
            <td><s:property value="#tcinfo.brandCato"/></td>
            <td><s:property value="#tcinfo.owner"/></td>

            <s:if test="Assign==1 && (Leader==true || Champion==true) ">
                <td><input type="checkbox" name='box' value="<s:property value='#tcinfo.CaseID' /> "></td>
            </s:if>
        </tr>
    </s:iterator>
    </tbody>
</table>
</div>


<%----%>
<%--<div class="row">--%>
<%--<div class="col-lg-8">--%>
<%--</div>--%>
<%--<div class="col-lg-4">--%>
<%--Page<font style="font-weight:bolder;"> <s:property value="pageBean.currentPage"/></font> of <font style="font-weight:bolder;"><s:property value="pageBean.totalPage"/></font>--%>
<%----%>
<%----%>
<%--<s:if test="%{pageBean.currentPage != 1}">--%>
<%--<a href="phase4/SearchTestCaseForPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=1" onclick="displayBusyBox();">First Page</a>--%>
<%--</s:if>--%>
<%--<s:else>--%>
<%--<font class="link_disable">First Page</font>--%>
<%--</s:else>--%>
<%----%>
<%--<s:if test="%{pageBean.currentPage > 1}">--%>
<%--<a href="phase4/SearchTestCaseForPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage-1}'/>" onclick="displayBusyBox();">Previous</a>--%>
<%--</s:if>--%>
<%--<s:else>--%>
<%--<font class="link_disable">Previous</font>--%>
<%--</s:else>--%>

<%--<s:if test="%{pageBean.currentPage < pageBean.totalPage}">--%>
<%--<a href="phase4/SearchTestCaseForPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage+1}'/>" onclick="displayBusyBox();">Next</a>--%>
<%--</s:if>--%>
<%--<s:else>--%>
<%--<font class="link_disable">Next</font>--%>
<%--</s:else>--%>
<%----%>
<%--<s:if test="%{pageBean.currentPage != pageBean.totalPage}">--%>
<%--<a href="phase4/SearchTestCaseForPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.totalPage}'/>" onclick="displayBusyBox();">Last Page</a>--%>
<%--</s:if>--%>
<%--<s:else>--%>
<%--<font class="link_disable">Last Page</font>--%>
<%--</s:else>--%>
<%--</div>--%>
    