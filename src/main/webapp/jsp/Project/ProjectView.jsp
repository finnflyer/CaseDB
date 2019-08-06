<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">
<div>

<table class="table table table-bordered table-striped" style="table-layout:fixed">
  <thead>
  <tr>
    <th class="info" >Project Name</th>
    <th class="info"  width="10%">Owner Name</th>
    <th class="info" width="30%"> Comments </th>
    <th class="info">Create Time</th>
  </tr>
  </thead>
  <tbody>
  <s:iterator value="#request.pageBean.list" var="projectinfo">

    <tr>
      <td style="width:250;word-wrap:break-word;color:darkblue;font-size:120%"><a href='<%=path%>/phase4/ShowProjectDetail?projectKey=<s:property value="#projectinfo.projectInstkey" />' >
        <s:property value="#projectinfo.projectName" />
      </a>
      </td>
      <td><s:property value="#projectinfo.projectOwner" /></td>
      <td><s:property value="#projectinfo.comments" /></td>
      <td><s:property value="#projectinfo.createDate" /></td>
    </tr>
  </s:iterator>
  </tbody>
</table>
</div>



<div class="row">
  <div class="col-lg-8">
  </div>
  <div class="col-lg-4">
    Page<span style="font-weight:bolder;"> <s:property value="pageBean.currentPage"/></span> of <span style="font-weight:bolder;"><s:property value="pageBean.totalPage"/></span>


    <s:if test="%{pageBean.currentPage != 1}">
      <a href="phase4/SearchProject?searchflag=0&pageNumber=1" >First Page</a>
    </s:if>
    <s:else>
      <span class="link_disable">First Page</span>
    </s:else>

    <s:if test="%{pageBean.currentPage > 1}">
      <a href="phase4/SearchProject?searchflag=0&pageNumber=<s:property value='%{pageBean.currentPage-1}'/>" >Previous</a>
    </s:if>
    <s:else>
      <span class="link_disable">Previous</span>
    </s:else>

    <s:if test="%{pageBean.currentPage < pageBean.totalPage}">
      <a href="phase4/SearchProject?searchflag=0&pageNumber=<s:property value='%{pageBean.currentPage+1}'/>" >Next</a>
    </s:if>
    <s:else>
      <span class="link_disable">Next</span>
    </s:else>

    <s:if test="%{pageBean.currentPage != pageBean.totalPage}">
      <a href="phase4/SearchProject?searchflag=0&pageNumber=<s:property value='%{pageBean.totalPage}'/>" >Last Page</a>
    </s:if>
    <s:else>
      <span class="link_disable">Last Page</span>
    </s:else>
  </div>
</div>