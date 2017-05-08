<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<!-- custom css -->
	<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
	<style>
		.row-fld-half{margin-bottom: 12px; float:left; width:50%; line-height:30px;font-size:14px;}
		.row-fld-all {width:100%; line-height:30px;margin-bottom: 12px;font-size:14px;}
		.row-fld{margin-left:48px;margin-bottom: 24px; font-size:14px;}
	</style>
</head>
<body style="margin:0; padding:0; background: #f5f5f5;">
	<div style = "width: 1000px; overflow:auto;margin:auto; ">
		<div style="margin-top:20px;margin-bottom: 24px;">
			<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
				<div id="back" style="text-align:center;font-size:16px;float:left;margin-top:6px;">&lt;&lt;&nbsp;&nbsp;返回</div>
				<div style="text-align:center;font-size:24px;">抢单</div>
			</div>

			<form id = "infoForm" action="<c:url value="/front/sender/FrontSenderOrderCtrl-sqQd"/>" method="post">
				<input type = "hidden" name = "orderId" value = "${info.orderId}"/>
				<input type = "hidden" name = "orderNo" value = "${info.orderNo}"/>
				<input type = "hidden" name = "vehicleSize" value = "${info.vehicleSize}"/>
				<input type = "hidden" name = "orderData" id = "orderData"  />	
				<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:16px;background:white;">
					<div style="margin-left:32px;margin-right:32px;padding-top:32px; border-bottom:solid 1px #ccc;padding-bottom: 10px;">物流信息</div>
					<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
						<div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">订单公司:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">${info.userName}</span>
							</div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">订单编号:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">${info.orderNo}</span>
							</div>
						</div>
						<div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">商品类型:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">
									<c:if test = "${info.prodType == '0'}">
										气体
								    </c:if>
								    <c:if test = "${info.prodType == '1'}">
										燃油
								    </c:if>		
								</span>
							</div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">商品名称:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">${info.prodName}</span>
							</div>
						</div>						
						<div class = "row-fld-all">
							<span style="display:inline-block; text-align:right;">购买数量:</span>
							<span style="display:inline-block; text-align:left;margin-left:10px;">${info.vehicleSize}顿</span>
						</div>
						<div class = "row-fld-all">
							<span style="display:inline-block; text-align:right;">提货地址:</span>
							<span style="display:inline-block; text-align:left; margin-left:10px;">${info.fromRegionName}&nbsp;${info.fromRegionAddr}</span>
						</div>
						<div class = "row-fld-all">
							<span style="display:inline-block; text-align:right;">配送地址:</span>
							<span style="display:inline-block; text-align:left;margin-left:10px;">${info.toRegionName}&nbsp;${info.toRegionAddr}</span>
						</div>
					</div>
					<div style = "clear:both;"></div>
					<div style="margin-left:32px;margin-right:32px; margin-top: 20px;padding-top:32px; border-bottom:solid 1px #ccc;padding-bottom: 10px;">
							抢单信息
							<span style="display:inline-block; text-align:right;width:10%;margin-right:2%; font-size:14px;">抢单金额</span>
							<input type="text" name="grabMoney" id="grabMoney" style="width:30%;" readonly = "readonly" value = "${info.grabMoney}">
					</div>
					<%----------- 暂时 ----------------------------------------------------------------------------------- 					
					<div class = "one-fld" style="margin-left:18px;margin-right:32px;padding-top:32px;"></div>
					<div id="wlxxDiv">
						<div class="row-fld">
							<span style = "margin-right: 64px;">发货时间:</span>
							<span>${info.cfDate}</span>
							<span style = "margin-right: 64px; margin-left:64px;">预计收货时间:</span>
							<span>${info.ddDate}</span>
						</div>
						
						<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;margin: 16px 48px;">
							<div style="height:30px;background:#e0e0e0;font-size:16px;">
								<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
									司机名称
								</div>
								<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
									电话
								</div>
								<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
									车牌号 
								</div>
								<div style="width:8%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;">
									操作
								</div>
							</div>
							<div id="sjListContent">
								<c:forEach var="item" items="${infoList}">
									<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
										${item.sjLxr}
									</div>
									<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
										${item.sjLxrPhone}
									</div>
									<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
										${item.vehicleNo}
									</div>
									<div style="width:8%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;">
										
									</div>
								</c:forEach>
							</div>
						</div>
					</div>			
					 ----------------------------------------------------------------------------------------------%>
					
					<div align=center style="margin-top: 32px;margin-bottom: 24px;">
						<input type="button"  class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none; margin-left:20px;" onclick = "window.history.back();" value="返回">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
    
	
	$("#back").click( function(){
		window.history.back();
	});
	
</script>
</html>