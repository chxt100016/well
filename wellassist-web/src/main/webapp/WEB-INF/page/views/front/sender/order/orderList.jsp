<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div class="container1">
	<div style="margin:40px 0 0 210px;">

	
<div id = "content-rect" style="width:90%">
	<h4 class="ui header">订单列表</h4>
    <div class="ui divider"></div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/sender/logisticsOrderList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="vehicleState" name="state" value="${param.state}">
	</form>
	
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;    height: 36px;">
		<div style="width:40%;text-align:center;font-size:16px;float:left;">运输产品</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">运输</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">
			状态
			<!-- <span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#vehicleState').val('');searchData(1);">全部</div>
					<div onclick="$('#vehicleState').val('2');searchData(1);">待支付</div>
					<div onclick="$('#vehicleState').val('3');searchData(1);">待提货</div>
					<div onclick="$('#vehicleState').val('4');searchData(1);">配送中</div>
					<div onclick="$('#vehicleState').val('5');searchData(1);">已完成</div>
				</div>
			</span> -->
		</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>
	<c:forEach var="item" items="${info}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;">
			<div style="height:30px;background:#e0e0e0;font-size:16px;">
				<div style = "margin-left:10px;line-height:30px; color: #807B7B;float:left; font-size:10px;">
					${item.orderDate} &nbsp;&nbsp;&nbsp;&nbsp; 订单编号：${item.orderNo}
				</div>
			</div>
			 
			<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
				<div class="graybox" style="width:40%;height:110px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
					<div style = "margin-left:10px;line-height:106px; float:left;">
						<a class="fancybox" href="${item.prodImg}" data-fancybox-group="gallery" title=""><img src="${item.prodImg}"  style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
					</div>
					<div style = "margin-left:10px;line-height:106px; float:left;">
						${item.prodName}
					</div>
					<div style = "margin-right:10px;line-height:106px; float:right;color:#A1A2A9;">
						${item.num}吨
						<%--(已发送 ${item.saleSjNum}吨)--%>
					</div>	
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:110px;line-height: 110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					${item.orderPrice}元
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					<table style="width:100%;height:90px;text-align:center;margin-top:10px;">
						<tr>
							<td style="color:#a00;">
								<c:if test="${item.state==0}">无效</c:if>
								<c:if test="${item.state==2}">待支付</c:if>
								<c:if test="${item.state==3}">待提货</c:if>
								<c:if test="${item.state==4}">配送中</c:if>
								<c:if test="${item.state==5}">已完成</c:if>
							</td>
						</tr>
						<%--<c:if test="${item.state>=2}">
							<tr>
								<td>
									<a style="cursor:pointer;color:black;" onclick="toURL('detailVehicle', '${item.vehicleTrans}')">物流详情</a>
								</td>
							</tr>
						</c:if>--%>
						<c:if test="${item.state=='4' || item.state=='5'}">
							<tr>
								<td>
									<a style="cursor:pointer;color:black;" onclick="toURL('detailVehicle', '${item.orderId}')">物流信息</a>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
				<div class="grayboxwithoutleft" style="height:110px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
					<%--<c:if test="${item.vehicleState=='2'}">
						<span class="span_btn" onClick="toURL('fahuo', '${item.vehicleTrans}')">发货</span>
					</c:if>--%>
					<%--<c:if test="${item.vehicleState=='4'}">
						<span class="span_btn" onClick="toURL('jiesuan', '${item.vehicleTrans}')">结算</span>
					</c:if>	--%>
				
						<span class="span_btn" onclick="window.location.href='${pageContext.request.contextPath}/sender/detail';">修改</span>
				</div>
			</div>
			
		</div>
	</c:forEach>
	<c:if test="${empty info || fn:length(info) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>
</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		$('.fancybox').fancybox();
		
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
	});
	
	function toURL(action, vehicleTrans){
		var url = "";
		if(vehicleTrans!=''){
			if(action=='fahuo'){
				window.location.href = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-editFahuo?vehicleTrans=" + vehicleTrans;
			} else if(action=='detailVehicle'){
				/*window.location.href = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-detailVehicle?vehicleTrans=" + vehicleTrans;*/
                window.location.href = "${pageContext.request.contextPath}/sender/orderDetail?orderId=" + vehicleTrans;
			}else if(action=='jiesuan'){
				if(confirm("你要确定操作吗?")){
					url = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-jiesuan";
						$.post(url,{vehicleTrans:vehicleTrans},function(data){
							alert(data.content);
				            if(data.state==1 ){
				            	window.location.reload();
				            }
			      	}, "json");
				}
			}
		}
	}
</script>

<%@ include file="../footer.jsp"%>