<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
	<style>
		.row_fld{border-bottom:solid 1px #d0d0d0;height:130px;float:left;}
	</style>
</head>
<div id= "content-rect">
	<div class = "row-header"><span class = "header-title">利息记录</span></div>
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:25%;text-align:center;float:left;">放款时间</div>
		<div style="width:25%;float:left;text-align:left;">授信详情</div>
		<div style="width:25%;text-align:center;float:left;">本金</div>
		<div style="width:25%;text-align:center;float:left;">利息</div>
	</div>
	<c:forEach var = "item" items = "${list}">
		<div style="height:120px; font-size:14px;">
			<div class = "row_fld" style = "width:25%;">
				<div style="text-align:center;padding-top:20px;">[${item.creditDate}]</div>
			</div>
			<div class = "row_fld" style="text-align:left;width:25%;">
				<div style="padding-top:10px;">公司名: &nbsp;&nbsp;${item.userName}</div>
				<div style="padding-top:10px;">联系人: &nbsp;&nbsp;${item.companyLxr}</div>
				<div style="padding-top:10px;">联系电话: &nbsp;&nbsp;${item.companyLxrPhone}</div>
				<div style="padding-top:10px;">账期: ${item.creditOverDate}</div>
			</div>
			<div class = "row_fld" style = "width:25%;">
				<div style="text-align:center;padding-top:20px;">${item.creditSjMoney}元</div>
			</div>
			<div class = "row_fld" style = "width:25%;">
				<div style="text-align:center;padding-top:20px;">产生利息:&nbsp;&nbsp;${item.lixiMoney}元</div>
				<div style="text-align:center;padding-top:20px;">未还利息:&nbsp;&nbsp;${item.remainLxMoney}元</div>
				<div style="text-align:center;padding-top:20px;">已还利息:&nbsp;&nbsp;${item.retLxMoney}元</div>
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