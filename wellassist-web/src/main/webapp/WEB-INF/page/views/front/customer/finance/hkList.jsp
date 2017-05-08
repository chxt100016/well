<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<!-- custom css -->
	<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
	<style>
	</style>
</head>
<div id = "content-rect">
	<div class = "row-header"><span class = "header-title">还款记录</span></div>
	<form id="searchFrm" method="get" action="${pageContext.request.contextPath}/front/customer/FinanceController-sxSqList">
		<input type="hidden" id="page" name="page" value="${param.page}">
	</form>

	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:30%;text-align:center;float:left;">放款时间</div>
		<div style="width:25%;padding-left: 5%;float:left;">还款时间</div>
		<div style="width:20%;text-align:center;float:left;">本金</div>
		<div style="width:20%;text-align:center;float:left;">利息</div>
	</div>

	<c:forEach var = "item" items = "${list}">
		<div style="height:108px; font-size:14px;">
			<div style="border-bottom:solid 1px #d0d0d0;width:30%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.creditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;padding-left:5%;width:25%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.mgrDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.money}元</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.lixiMoney}元</div>
			</div>
		</div>
	</c:forEach>
	<c:if test="${list== null || fn:length(list) == 0}">
		  <div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
    
    <div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>	
</div>
<%@ include file="../footer.jsp"%>