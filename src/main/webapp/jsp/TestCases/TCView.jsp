<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<base href="<%=basePath%>">
<div>
    <table class="table table table-bordered table-striped" style="table-layout:fixed">
        <thead>
        <tr>
            <th class="info" width="6%">CaseID</th>
            <th class="info" width="14%">Category</th>
            <th class="info" width="30%">CaseName</th>
            <th class="info" width="8%">Version</th>
            <th class="info" width="19%">OS</th>
            <th class="info" width="18%">Brand</th>
            <th class="info">Owner</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.pageBean.list" var="tcinfo">
            <tr>
                <td style="width:250px;word-wrap:break-word;color:darkblue;font-size:120%">
                    <a href='<%=path%>/phase4/ShowTestCaseDetail?testCase.caseinstkey=<s:property value="#tcinfo.caseinstkey" />'>
                        <s:property value="#tcinfo.casecode"/>
                    </a>
                </td>
                <td><s:property value="#tcinfo.functioncato"/></td>
                <td>
                    <a href='<%=path%>/phase4/ShowTestCaseDetail?testCase.caseinstkey=<s:property value="#tcinfo.caseinstkey" />'><s:property
                            value="#tcinfo.casename"/></a></td>
                <td><s:property value="#tcinfo.version"/></td>
                <td><s:property value="#tcinfo.osCato"/></td>
                <td><s:property value="#tcinfo.brandCato"/></td>
                <td><s:property value="#tcinfo.Owner"/></td>

                <s:if test="Assign==1 && (Leader==true || Champion==true) ">
                    <td><input type="checkbox" name='box' value="<s:property value='#tcinfo.CaseID' /> "></td>
                </s:if>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>


<div class="row">
    <div class="col-lg-8">
    </div>
    <div class="col-lg-4">
        Page<span style="font-weight:bolder;"> <s:property value="pageBean.currentPage"/> </span>of <span
            style="font-weight:bolder;"><s:property value="pageBean.totalPage"/></span>


        <s:if test="%{pageBean.currentPage != 1}">
            <a href="phase4/SearchCaseForPage?&pageNumber=1">First Page</a>
        </s:if>
        <s:else>
            <span class="link_disable">First Page</span>
        </s:else>

        <s:if test="%{pageBean.currentPage > 1}">
            <a href="phase4/SearchCaseForPage?searchflag=0&pageNumber=<s:property value='%{pageBean.currentPage-1}'/>
            ">Previous</a>
        </s:if>
        <s:else>
            <span class="link_disable">Previous</span>
        </s:else>

        <s:if test="%{pageBean.currentPage < pageBean.totalPage}">
            <a href="phase4/SearchCaseForPage?searchflag=0&pageNumber=<s:property value='%{pageBean.currentPage+1}'/>
            ">Next</a>
        </s:if>
        <s:else>
            <span class="link_disable">Next</span>
        </s:else>

        <s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            <a href="phase4/SearchCaseForPage?searchflag=0&pageNumber=<s:property value='%{pageBean.totalPage}'/>
               ">Last Page</a>
        </s:if>
        <s:else>
            <span class="link_disable">Last Page</span>
        </s:else>
    </div>
</div>