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

	<div class = "row-header"><span class = "header-title">账户信息</span></div>
	<div class = "row1">
		<div class = "row1_1">账户余额</div>
		<div class = "row1_2">
			<span class = "col1">${userMoney}</span>
			<span class = "col2">&nbsp;&nbsp;元</span>
			<span id="fillmoney" class="smallbutton" style="margin-left:32px; display:none;" onClick = "toURL('czSq')">充值</span>
			<span id="getmoney" class="smallbutton" style="margin-left:12px;"  onClick = "toURL('txSq')">提现</span>
		</div>
	</div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/customer/FinanceController-accountInfo">
		<input type="hidden" id="page" name="page" value="${param.page}">
	</form>
	<div class = "row2">交易记录</div>

	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:30%;text-align:center;float:left; font-weight:600;">时间</div>
		<div style="width:30%;text-align:center;float:left; font-weight:600;">金额</div>
		<div style="width:35%;padding-left: 5%;float:left; font-weight:600;">产品详情</div>
	</div>

	
	<c:forEach var = "item" items = "${list}">
		<div style="height:108px; font-size:14px;">
			<div style="border-bottom:solid 1px #d0d0d0;width:30%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.completeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:30%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.jySjMoney}元</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;padding-left:5%;width:35%;height:100px;float:left;">
				<c:if test = "${item.jyState == '2'}">
					<c:set value="${ fn:split(item.content, ';') }" var="names" />
					<c:forEach items="${ names }" var="name">
						<div style="padding-top:10px;">${name}</div>
					</c:forEach>	
				</c:if>
				
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
		var url = "";
		if(action == 'txSq'){
			var userMoney = ${userMoney};
			if(parseInt(userMoney) <= 0){
				alert("账号金额不够！");
				return;
			}
			url = "${pageContext.request.contextPath}/front/seller/SellerFinanceController-txSq?userMoney="+userMoney;
			window.location.href = url;
		}else if(action == 'czSq'){
			url = "${pageContext.request.contextPath}/front/seller/SellerFinanceController-czSq";
			window.location.href = url;
		}
		
	}
</script>
<%@ include file="../footer.jsp"%>