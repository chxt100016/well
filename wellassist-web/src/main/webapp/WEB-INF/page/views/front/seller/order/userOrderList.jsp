<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/seller/userorderinfo.css"/>">

<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align:left;">
		&nbsp;&nbsp;已有客户
		<input class="bluebutton" type="button" onclick="$('#searchtext').val('');$('#companyName').val('');$('#pjDay').val('');" value="清空" style="float:right;border-radius:5px;">
		<input class="bluebutton" type="button" id="search" name="search" onclick="$('#companyName').val($('#searchtext').val());searchData(1);" value="搜索" style="float:right;border-radius:5px;">
		<input id="searchtext" name="searchtext" type="text" value="${param.companyName}" style="float:right;">
	</div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-userOrderList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="companyName" name="companyName" value="${param.companyName}">
		<input type="hidden" id="pjDay" name="pjDay" value="${param.pjDay}">
		<%--
		<div class="row-header">
		     <span class="header-title">订单列表</span>
		     <div style="float:right;">
		     	<input type="text" name="prodName" style="height:10px; float:left;margin-bottom:0px;" value="${param.prodName}"/>
		     	<span class="span_search_btn_blue" style="margin-bottom:0px;" onclick="searchData(1);">搜索</span>
		     </div>
		</div>
		--%>
	</form>
	<div style="padding:6px;font-size:24px;margin-top:16px;height:20px;">
		<div style="width:100px;text-align:center;font-size:14px;float:left;">选择时间：</div>
		<a style="cursor:pointer;" onclick="$('#pjDay').val('');searchData(1);"><div class="dateselect" <c:if test="${param.pjDay==''}">style='font-weight:bold;'</c:if> >所有</div></a>
		<a style="cursor:pointer;" onclick="$('#pjDay').val('0');searchData(1);"><div class="dateselect" <c:if test="${param.pjDay=='0'}">style='font-weight:bold;'</c:if> >今天</div></a>
		<a style="cursor:pointer;" onclick="$('#pjDay').val('7');searchData(1);"><div class="dateselect" <c:if test="${param.pjDay=='7'}">style='font-weight:bold;'</c:if> >近一周</div></a>
		<a style="cursor:pointer;" onclick="$('#pjDay').val('30');searchData(1);"><div class="dateselect" <c:if test="${param.pjDay=='30'}">style='font-weight:bold;'</c:if> >近一个月</div></a>
		<a style="cursor:pointer;" onclick="$('#pjDay').val('90');searchData(1);"><div class="dateselect" <c:if test="${param.pjDay=='90'}">style='font-weight:bold;'</c:if> >近一季周</div></a>
		<a style="cursor:pointer;" onclick="$('#pjDay').val('365');searchData(1);"><div class="dateselect" <c:if test="${param.pjDay=='365'}">style='font-weight:bold;'</c:if> >近一年</div></a>
	</div>
	<div style="border-bottom:solid 1px blue;margin-top:16px;height:50px;">
		<div class="labelitem">交易时间</div>
		<div class="labelitem">客户名称</div>
		<div class="labelitem">所在省市</div>
		<div class="labelitem">商品数量</div>
		<div class="labelitem">成交总额</div>
		<div class="labelitem">评价</div>
	</div>
	<c:forEach var="item" items="${userOrderList}">
	<div style="border-bottom:solid 1px #d0d0d0;height:70px;">
		<div class="labelitem"><fmt:formatDate value="${item.pjDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		<div class="labelitem">${item.companyName}</div>
		<div class="labelitem">${item.zcRegionName}</div>
		<div class="labelitem">${item.saleSjNum}屯</div>
		<div class="labelitem">${item.saleSjMoney}元</div>
		<div class="labelitem">
			<c:if test="${item.pjState=='0'}">好评</c:if>
			<c:if test="${item.pjState=='1'}">中评</c:if>
			<c:if test="${item.pjState=='2'}">差评</c:if>
		</div>
	</div>
	</c:forEach>
	<c:if test="${userOrderList== null || fn:length(userOrderList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>
<script type = "text/javascript">
	// 初始化函数
	$(function(){
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
	});
</script>
<%@ include file="../footer.jsp"%>