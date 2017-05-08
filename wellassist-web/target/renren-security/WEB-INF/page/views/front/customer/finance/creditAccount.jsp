<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

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
	<div class = "row-header"><span class = "header-title">授信账户</span></div>	
	<div class = "row1">
		<div class = "row1_1">授信余额</div>
		<div class = "row1_2">
			<span class = "col1">${sxMoney}</span>
			<span class = "col2">&nbsp;&nbsp;元</span>
			<span class="smallbutton" style="margin-left:32px;" onClick = "toURL('sxSq')">申请提额</span>
		</div>
	</div>
	<div class = "row2">授信记录</div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/customer/FinanceController-creditAccount">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="creditState" name="creditState" value="${param.creditState}">
	</form>
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:25%;text-align:center;float:left;">时间</div>
		<div style="width:10%;text-align:center;float:left;">金额</div>
		<div style="width:10%;text-align:center;float:left;">利息</div>
		<div style="width:15%;text-align:center;float:left;">还款日期</div>
		<div style="width:20%;text-align:center;float:left;">
			<span class="dropdown"><a data-toggle="dropdown" class="dropdown">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onClick = "setCreditState('');">全部</div>
					<div onClick = "setCreditState('3');">未还款</div>
					<div onClick = "setCreditState('4');">已还款</div>
				</div>
			</span>
		</div>
		<div style="width:20%;text-align:center;float:left;">操作</div>
	</div>
    <c:forEach var = "item" items = "${list}">
		<div style="height:108px; font-size:14px;">
			<div style="border-bottom:solid 1px #d0d0d0;width:25%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.creditDate}" pattern="yyyy-MM-dd"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:10%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">-${item.creditSjMoney}元</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:10%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.lixiRate}%</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:15%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;"><fmt:formatDate value="${item.creditOverDate}" pattern="yyyy-MM-dd"/></div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;color:red;">
					<c:if test = "${item.creditState == '3'}">
						未还款
					</c:if>
					<c:if test = "${item.creditState == '4'}">
						已还款		
					</c:if>		
				</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
				<div style="text-align:center;padding-top:20px;color:red;cursor:pointer;">
					<c:if test = "${item.creditState == '3'}">		
						<a href="<c:url value="/front/customer/FinanceController-returnMoney?creditId=${item.creditId}"/>" style="color:red;">还款</a>
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
	// 状态选择函数
	function setCreditState(creditState){
		$("#creditState").val(creditState);
		$("#searchFrm").submit();
	}	
	
	// 功能函数
	function toURL(action){
		var url = "";
		if(action == 'sxSq'){
			url = "${pageContext.request.contextPath}/front/customer/FinanceController-sxSq";
			window.location.href = url;
		}
	}
</script>
<%@ include file="../footer.jsp"%>