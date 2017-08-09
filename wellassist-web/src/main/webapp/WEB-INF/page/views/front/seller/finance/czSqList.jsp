<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>
<!-- custom css -->
<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style>
</style>

</head>
<div id = "content-rect">
	<div class = "row-header"><span class = "header-title">充值记录</span></div>
	<form id="searchFrm" method="get" action="${pageContext.request.contextPath}/front/seller/SellerFinanceController-czList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="zfState" name="zfState" value="${param.zfState}">
	</form>
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:26%;text-align:center;float:left;">时间</div>
		<div style="width:17%;text-align:center;float:left;">额度</div>
		<div style="width:40%;text-align:center;float:left;">类型</div>
		<div style="width:17%;text-align:center;float:left;">
			<span class="dropdown"><a data-toggle="dropdown" class="dropdown">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onClick = "setZfState('');">全部</div>
					<div onClick = "setZfState('0');">申请</div>
					<div onClick = "setZfState('1');">完成</div>
				</div>
			</span>
		</div>
	</div>

	<c:forEach var = "item" items = "${list}">
		<div style="height:88px; font-size:14px;">
			<div style="border-bottom:solid 1px #d0d0d0;width:26%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.zfDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:17%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.zfMoney}元</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:40%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">
					<c:if test = "${item.zfType == '0'}">
						线下
					</c:if>
					<c:if test = "${item.zfType == '1'}">
						银行
					</c:if>		
				</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:17%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">
					<c:if test = "${item.zfState == '0'}">
						申请成功
					</c:if>
					<c:if test = "${item.zfState == '1'}">
						申请完成
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
	function setZfState(zfState){
		$("#zfState").val(zfState);
		$("#searchFrm").submit();
	}	
</script>
<%@ include file="../footer.jsp"%>