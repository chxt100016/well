<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <%--<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>--%>
    </head>
  <style>
        .teal-bg {
            background-color: #00B5AD!important;
            color: #FFF!important;
        }
    </style>
    <body>
    <form id="searchFrm" method="post" action="${pageContext.request.contextPath}/customer/creditApplys">
        <input type="hidden" id="page" name="page" value="${param.page}">
    </form>
    <div class="container1">
        <div class="container2">

       
    <div class="ui container segment" id="app1" style="width:990px; left: 10px;margin-top: -1px;float:left">
        <h3>申请记录</h3>
        <div class="ui divider"></div>
           <table class="ui celled striped  table ">
            <thead>
                <tr>
                    <th colspan="6">申请记录 </th>
                </tr>
                <tr class="teal-bg ">
                    <th class="teal-bg">申请时间</th>
                    <th class="teal-bg">申请金额(元)</th>
                    <th class="teal-bg">审批金额(元)</th>
                     <th class="teal-bg">授信状态</th>
                     <th class="teal-bg center aligned">备注</th>
                </tr>
                
            </thead>
            <tbody>
            <c:if test="${not empty credits}">
            <c:forEach items="${credits}" var="credit">
                <tr>
                    <td><fmt:formatDate value="${credit.creditApplyDate}" pattern="yyyy-MM-dd"/> </td>
                     <td>${credit.creditMoney}</td>
                    <td>${credit.creditSjMoney}</td>
                       <td >
                        <c:if test="${credit.creditState==-2}">已过期</c:if>
                           <c:if test="${credit.creditState==-1}">不通过</c:if>
                           <c:if test="${credit.creditState==0}">审核中</c:if>
                           <c:if test="${credit.creditState==1}">已通过</c:if>
                           <c:if test="${credit.creditState==2}">已提额</c:if>
                       </td>
               
        
                     <td class="center aligned">
                         ${credit.comment}
                     </td>
                </tr>
            </c:forEach>
            </c:if>
            <c:if test="${empty credits}">
                无记录...
            </c:if>
            </tbody>

        </table>
        <div class="right-pagination">
            <%@ include file="../../pagination.jsp"%>
        </div>

    </div>
     </div>
    </div>
    </body>
</html>