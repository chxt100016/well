<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<style>
	body{
		background: #f5f5f5;
	}
	div.formDd{
		width:1000px;
		text-align: left; 
		background: white;
		box-shadow: 2px 2px 8px #808080;
		padding:16px 0px 16px 0px;
	}
	div.headDd{
		margin-left:48px;
		margin-right:48px;
		padding-top:32px;
		margin-bottom: 24px;
		padding-bottom:12px;
		border-bottom:solid 2px #d0d0d0;
		font-size:18px;
	}
	div.rowDd{
		clear: both;
		height: 50px;
		line-height: 50px;
		font-size: 14px;	
	}
	.rowDd .labeldd{
		float: left;
		width: 100px;
	}
	.rowDd .contentdd{
		float: left;
		margin-left: 15px;
	}
	.backBtn{
		font-weight: bold;
    	cursor: pointer;
    	color: #2482df;
    	text-align:center;
    	font-size:16px;
    	float:left;
    	margin-top:6px;
	}
</style>	
<div style="margin-left: 0px; margin-top:20px;">
	<div id="ddXqDiv" align="center" <c:if test="${orderType=='1'}">style="display: none;"</c:if> >
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
				
				<div style="text-align:center;font-size:24px;">订单详情</div>
			</div>
		</div>
		<form id="ddxqForm" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-setOrderInfo" method="post">
			<div class="formDd">
				<div class="headDd">订单详情</div>
				<div class="rowDd">
					<div class="labeldd" align="right">企业名称:</div>
					<div class="contentdd">${orderInfo.companyName}</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right">产品名称 :</div>
					<div class="contentdd">${orderInfo.prodName}</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right">联系人 :</div>
					<div class="contentdd">${orderInfo.userLxr}</div>
					<div class="labeldd" align="right">联系电话 :</div>
					<div class="contentdd">${orderInfo.userLxrPhone}</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right">购买数量 :</div>
					<div class="contentdd">
						${orderInfo.saleNum}吨
					</div>
					<div class="labeldd" align="right">单价 :</div>
					<div class="contentdd">
						<c:if test="${orderInfo.saleNum!=null and orderInfo.saleNum>0}">${orderInfo.saleMoney/orderInfo.saleNum}吨/元</c:if>
					</div>
					<div class="labeldd" align="right">总价 :</div>
					<div class="contentdd"><span id="saleMoney">${orderInfo.saleMoney}</span>元</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right">状态 :</div>
					<div class="contentdd">
						<c:if test="${orderInfo.orderState=='0'}">待确认</c:if>
						<c:if test="${orderInfo.orderState=='1'}">待付款</c:if>
						<c:if test="${orderInfo.orderState=='2'}">已付款</c:if>
						<c:if test="${orderInfo.orderState=='21' || orderInfo.orderState=='22'}">已付款(申请)</c:if>
						<c:if test="${orderInfo.orderState=='3'}">未发货</c:if>
						<c:if test="${orderInfo.orderState=='4'}">已发货</c:if>
						<c:if test="${orderInfo.orderState=='5'}">待评价</c:if>
						<c:if test="${orderInfo.orderState=='6'}">已完成</c:if>
						<c:if test="${orderInfo.orderState=='-1'}">已取消</c:if>
					</div>
				</div>
				
				<input type="hidden" name="orderId" value="${orderInfo.orderId}" />

				<div style="margin: 40px 0px 40px 80px;">
					<c:if test="${orderInfo.orderState=='5' or orderInfo.orderState=='6'}">
						<a style="font-size: 18px; color: #1177dd; cursor: pointer;" onclick="showDd(1)">物流信息</a>				
					</c:if>
				</div>
			</div>
		</form>
	</div>
	
	<div id="wlxxDiv" align="center" <c:if test="${orderType!='1'}">style="display: none;"</c:if> >
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
				
				<div style="text-align:center;font-size:24px;">物流信息</div>
			</div>
		</div>
		<form id="wlxxForm" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-setOrderInfo" method="post">
			<div class="formDd">
				<div class="headDd">物流信息</div>
				
				<c:forEach var="item" items="${vehicleList}">
					<div style="border:solid 1px #d0d0d0;font-size:24px;margin:16px 48px;overflow:auto;">
						<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
							<div class="graybox" style="width:21%;height:60px;line-height:60px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 车牌号 : ${item.vehicleNo}
							</div>
							<div class="grayboxwithoutleft" style="width:20%;height:60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
								<table style="width:100%;height:40px;margin-top:10px;">
									<tr>
										<td>
											&nbsp; 司机名称 : ${item.sjLxr}
										</td>
									</tr>
									<tr>
										<td>
											&nbsp; 电话 : ${item.sjLxrPhone}
										</td>
									</tr>
								</table>
							</div>
							<div class="graybox" style="width:20%;height:60px;line-height:60px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 装载量 : ${item.vehicleSjSize}吨
							</div>
							<div class="grayboxwithoutleft" style="width:25%;height:60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
									<table style="width:100%;height:40px;margin-top:10px;">
										<tr>
											<td>
												&nbsp; 出发时间 : <fmt:formatDate value="${item.cfDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp; 到达时间 : <fmt:formatDate value="${item.ddDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
										</tr>
									</table>
							</div>
							<div class="grayboxwithoutleft" style="width:10%;height:60px;line-height:60px;font-size:14px;float:left;border:none; text-align: center;">
									&nbsp; 状态 :<c:if test="${item.vehicleState=='0'}">待发送</c:if><c:if test="${item.vehicleState=='1'}">发送中</c:if><c:if test="${item.vehicleState=='2'}">已发送</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:if test="${vehicleList== null || fn:length(vehicleList) == 0}">
					<div style = "margin-top:10px; margin-left:48px; float:left;">没有资料</div>	 
			    </c:if>
				
				<input type="hidden" name="orderId" value="${orderInfo.orderId}" />

				<div style="margin: 40px 0px 40px 80px;">
					<c:if test="${orderInfo.orderState=='5' or orderInfo.orderState=='6'}">
						<a style="font-size: 18px; color: #1177dd; cursor: pointer;" onclick="showDd(0)">订单详情</a>				
					</c:if>	
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

	$(".backBtn").click(function(){
		goBack();
	});
	
	function showDd(type){
		if(type==0){
			$("#ddXqDiv").show();
			$("#wlxxDiv").hide();
		} else {
			$("#ddXqDiv").hide();
			$("#wlxxDiv").show();
		}
	}
</script>

<%@ include file="../footer.jsp"%>