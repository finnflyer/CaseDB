<%@ page language="java" pageEncoding="UTF-8" %>
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
            <th class="info" width="10%">ECR</th>
            <th class="info" width="6%">Priority</th>
            <th class="info" width="30%">Abstract</th>
            <th class="info" width="10%">OS</th>
            <th class="info" width="10%">Language</th>
            <th class="info" width="5%">Status</th>
            <th class="info" width="40%">Comments</th>
            <th class="info" width="5%">Site</th>
            <th class="info">Create Date</th>
            <th class="info">Creator</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.issuePageBean.list" var="issue">
            <td><s:property value="#issue.ecrNumber"/></td>
            <td style="text-align:center"><s:property value="#issue.priority"/></td>
            <td><a href='<%=path%>/phase4/ShowIssueDetail?issueKey=<s:property value="#issue.instkey" />'>
                <s:property value="#issue.issueName"/></a>
            </td>
            <td sytle="span-size:1px"><s:property value="#issue.osCato"/></td>
            <td><s:property value="#issue.language"/></td>
            <td><s:property value="#issue.issueStatus"/></td>
            <td><s:property value="#issue.comments"/>
                <button id="moreComments" style="span-size:3px" class="btn btn-sm">more</button>
            </td>
            <td><s:property value="#issue.testSiteCato" /></td>
            <td><s:date name="#issue.createdate" format="yyyy-MM-dd" ></s:date></td>
            <td><s:property value="#issue.owner" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>


<div class="row">
    <div class="col-lg-8">
    </div>
    <div class="col-lg-4">
        Page<span style="span-weight:bolder;"> <s:property value="pageBean.currentPage"/></span> of <span
            style="span-weight:bolder;"><s:property value="pageBean.totalPage"/></span>

        <s:if test="%{pageBean.currentPage != 1}">
            <a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=1"
               >First Page</a>
        </s:if>
        <s:else>
            <span class="link_disable">First Page</span>
        </s:else>

        <s:if test="%{pageBean.currentPage > 1}">
            <a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage-1}'/>"
               >Previous</a>
        </s:if>
        <s:else>
            <span class="link_disable">Previous</span>
        </s:else>

        <s:if test="%{pageBean.currentPage < pageBean.totalPage}">
            <a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.currentPage+1}'/>"
               >Next</a>
        </s:if>
        <s:else>
            <span class="link_disable">Next</span>
        </s:else>

        <s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            <a href="phase4/SearchTestPlan?searchflag=0&pageflag=<s:property value="%{pageflag}" />&page=<s:property value='%{pageBean.totalPage}'/>"
               >Last Page</a>
        </s:if>
        <s:else>
            <span class="link_disable">Last Page</span>
        </s:else>
    </div>
</div>
    