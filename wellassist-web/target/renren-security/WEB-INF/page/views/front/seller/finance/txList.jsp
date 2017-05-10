<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style>
	span.smallbutton{border: solid 1px #d0d0d0;cursor: pointer;padding: 4px 8px;text-align: center;color: #666666;font-size: 13px;border-radius: 4px;}
	
	.row1{border:solid 1px #d0d0d0;padding:6px;font-size:20px;margin-top:16px;padding-bottom:16px;}
	.row1_1{margin-top:16px; margin-left: 12px;font-size: 16px;text-align: left;color: #747474;}
	.row1_2{margin-top:16px; margin-left: 24px;font-size: 20px; margin-bottom:16px;text-align:left;}
	.row1_2 .col1{color:red;font-size:20px;font-weight:bold;}
	.row1_2 .col2{color:red;font-size:18px;margin-left:-4px;}
	
	.row2{text-align:left; margin-top:16px;border:solid 1px #d0d0d0;padding:6px; padding-left: 12px;font-size:15px;font-weight: 600;}
	
</style>

</head>
<div id = "content-rect">

	<div class = "row-header"><span class = "header-title">提现记录</span></div>
	<div class = "row1">
		<div class = "row1_1">提现余额</div>
		<div class = "row1_2">
			<span class = "col1">${txMoney}</span>
			<span class = "col2">&nbsp;&nbsp;元</span>
		</div>
	</div>
	<form id="searchFrm" method="get" action="${pageContext.request.contextPath}/front/seller/SellerFinanceController-txList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="txState" name="txState" value="${param.txState}">
	</form>
	<div class = "row2">申请记录</div>

	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:25%;text-align:center;float:left; font-weight:600;">时间</div>
		<div style="width:25%;text-align:center;float:left; font-weight:600;">提现名称</div>
		<div style="width:25%;text-align:center;float:left; font-weight:600;">提现金额</div>
		<div style="width:20%;text-align:center;float:left; font-weight:600;">
			<span class="dropdown"><a data-toggle="dropdown" class="dropdown">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onClick = "setTxState('');">全部</div>
					<div onClick = "setTxState('0');">申请</div>
					<div onClick = "setTxState('1');">待付款</div>
					<div  onClick = "setTxState('-1');">不通过</div>
					<div onClick = "setTxState('2');">已付款</div>
				</div>
			</span>
		</div>
	</div>

	
	<c:forEach var = "item" items = "${list}">
		<div style="height:108px; font-size:14px;">
			<div style="border-bottom:solid 1px #d0d0d0;width:25%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.txDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:25%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.txName}</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:25%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.txMoney}元</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">
					<c:if test = "${item.txState == '-1'}">
							不通过
					</c:if>
					<c:if test = "${item.txState == '0'}">
							申请
					</c:if>
					<c:if test = "${item.txState == '1'}">
							待付款
					</c:if>
					<c:if test = "${item.txState == '2'}">
							已付款
					</c:if>
				</div>	
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

<script type = "text/javascript">
	// 功能函数
	function toURL(action){
	}
	
	// 状态选择函数
	function setTxState(txState){
		$("#txState").val(txState);
		$("#searchFrm").submit();
	}
</script>
<%@ include file="../footer.html"%>