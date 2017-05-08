<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align:left;">&nbsp;&nbsp;评价列表</div>

	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-estiList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="pjState" name="pjState" value="${param.pjState}">
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
	
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;height:20px;">
		<div style="width:40%;text-align:center;font-size:16px;float:left;">
			<span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">评价&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#pjState').val('');searchData(1);">全部</div>
					<div onclick="$('#pjState').val('0');searchData(1);">好评</div>
					<div onclick="$('#pjState').val('1');searchData(1);">中评</div>
					<div onclick="$('#pjState').val('2');searchData(1);">差评</div>
				</div>
			</span>
		</div>
		<div style="width:18%;text-align:center;font-size:16px;float:left;">时间</div>
		<div style="width:40%;text-align:center;font-size:16px;float:left;">产品详情</div>
	</div>
	<c:forEach var="item" items="${estiList}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;"">
			<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
				<div class="graybox" style="width:40%;height:110px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
					<table style="width:100%;height:90px;text-align:left;margin-top:10px;">
						<tr>
							<td style="font-weight: bold;">
								<c:if test="${item.pjState=='0'}">好评</c:if>
								<c:if test="${item.pjState=='1'}">中评</c:if>
								<c:if test="${item.pjState=='2'}">差评</c:if>
							</td>
						</tr>
						<tr>
							<td>
								${item.pjContent}
							</td>
						</tr>
						<tr>
							<td>
								交易金额 : ${item.saleSjMoney}
							</td>
						</tr>
					</table>
				</div>
				<div class="grayboxwithoutleft" style="width:18%;height:110px;line-height: 110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					<fmt:formatDate value="${item.pjDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
				<div class="grayboxwithoutleft" style="width:40%;height:110px;font-size:14px;float:left;border:none;">
					<table style="width:100%;height:90px;text-align:left;margin-top:10px;">
						<tr>
							<td>
								${item.prodName}
							</td>
						</tr>
						<tr>
							<td>
								${item.saleSjNum}吨
							</td>
						</tr>
						<tr>
							<td>
								订单编号 : ${item.orderNo}
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</c:forEach>
	<c:if test="${estiList== null || fn:length(estiList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	
</script>

<%@ include file="../footer.jsp"%>