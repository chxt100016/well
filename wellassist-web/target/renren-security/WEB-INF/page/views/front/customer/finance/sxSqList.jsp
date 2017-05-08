<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>
	<!-- custom css -->
	<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
	<style>
	</style>
</head>
<div id = "content-rect">
	<div class = "row-header"><span class = "header-title">申请记录</span></div>
	<form id="searchFrm" method="get" action="${pageContext.request.contextPath}/front/customer/FinanceController-sxSqList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="creditState" name="creditState" value="${param.creditState}">	
	</form>
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:26%;text-align:center;float:left;">时间</div>
		<div style="width:17%;text-align:center;float:left;">额度</div>
		<div style="width:40%;text-align:center;float:left;">利息率</div>
		<div style="width:17%;text-align:center;float:left;">
			<span class="dropdown"><a data-toggle="dropdown" class="dropdown">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onClick = "setCreditState('');">全部</div>
					<div onClick = "setCreditState('-2');">放款不通过</div>
					<div onClick = "setCreditState('-1');">审核不通过</div>
					<div onClick = "setCreditState('0');">未审核</div>
					<div onClick = "setCreditState('1');">审核通过</div>
					<div onClick = "setCreditState('2');">放款确认中</div>
				</div>
			</span>
		</div>
	</div>

	<c:forEach var = "item" items = "${list}">
		<div style="height:88px; font-size:14px;">
			<div style="border-bottom:solid 1px #d0d0d0;width:26%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">[<fmt:formatDate value="${item.creditSqDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:17%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.creditMoney}元</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:40%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;">${item.lixiRate}%</div>
			</div>
			<div style="border-bottom:solid 1px #d0d0d0;width:17%;height:80px;float:left;">
				<div style="text-align:center;padding-top:20px;color:#117ec6;">
					<c:if test = "${item.creditState lt '0'}">
						申请失败	
					</c:if>
					<c:if test = "${item.creditState == '-2'}">
						(放款不通过)	
					</c:if>	
					<c:if test = "${item.creditState == '-1'}">
						(审核不通过)		
					</c:if>	
					<c:if test = "${item.creditState == '0'}">
						未审核
					</c:if>	
					<c:if test = "${item.creditState == '1'}">
						审核通过(放款指派中)
					</c:if>
					<c:if test = "${item.creditState == '2'}">
						放款指派完成
					</c:if>	
					<c:if test = "${item.creditState ge '3'}">					
						申请成功
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
	function setCreditState(creditState){
		$("#creditState").val(creditState);
		$("#searchFrm").submit();
	}
</script>
<%@ include file="../footer.jsp"%>