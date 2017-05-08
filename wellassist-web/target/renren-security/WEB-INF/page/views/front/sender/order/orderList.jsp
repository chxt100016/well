<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align:left;">&nbsp;&nbsp;订单列表</div>

	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-orderList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="vehicleState" name="vehicleState" value="${param.vehicleState}">
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
		<div style="width:40%;text-align:center;font-size:16px;float:left;">远输产品</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">远输</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">
			<span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#vehicleState').val('');searchData(1);">全部</div>
					<div onclick="$('#vehicleState').val('0');searchData(1);">无效</div>
					<div onclick="$('#vehicleState').val('1');searchData(1);">未确定</div>
					<div onclick="$('#vehicleState').val('2');searchData(1);">待安排</div>
					<div onclick="$('#vehicleState').val('3');searchData(1);">已安排</div>
					<div onclick="$('#vehicleState').val('4');searchData(1);">已发货</div>
					<div onclick="$('#vehicleState').val('5');searchData(1);">已完成</div>
				</div>
			</span>
		</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>
	<c:forEach var="item" items="${waUserVehicleList}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;"">
			<div style="height:30px;background:#e0e0e0;font-size:16px;">
				<div style = "margin-left:10px;line-height:30px; color: #807B7B;float:left; font-size:10px;">
					${item.createDate} &nbsp;&nbsp;&nbsp;&nbsp; 订单编号：${item.orderNo}
				</div>
			</div>
			 
			<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
				<div class="graybox" style="width:40%;height:110px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
					<div style = "margin-left:10px;line-height:106px; float:left;">
						<a class="fancybox" href="${pageContext.request.contextPath}/${item.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.prodImg}"  style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
					</div>
					<div style = "margin-left:10px;line-height:106px; float:left;">
						${item.prodName}
					</div>
					<div style = "margin-right:10px;line-height:106px; float:right;color:#A1A2A9;">
						${item.vehicleSize}吨
						(已发送 ${item.saleSjNum}吨)
					</div>	
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:110px;line-height: 110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					${item.payMoney}元
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					<table style="width:100%;height:90px;text-align:center;margin-top:10px;">
						<tr>
							<td style="color:#a00;">
								<c:if test="${item.vehicleState=='0'}">无效</c:if>
								<c:if test="${item.vehicleState=='1'}">未确定</c:if>
								<c:if test="${item.vehicleState=='2'}">待安排</c:if>
								<c:if test="${item.vehicleState=='3'}">已安排</c:if>
								<c:if test="${item.vehicleState=='4'}">已发货</c:if>
								<c:if test="${item.vehicleState=='5'}">已完成</c:if>
							</td>
						</tr>
						<c:if test="${item.vehicleState=='4' || item.vehicleState=='5'}">
							<tr>
								<td>
									<a style="cursor:pointer;color:black;" onclick="toURL('detailVehicle', '${item.vehicleTrans}')">物流信息</a>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
				<div class="grayboxwithoutleft" style="height:110px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
					<c:if test="${item.vehicleState=='2'}">
						<span class="span_btn" onClick="toURL('fahuo', '${item.vehicleTrans}')">发货</span>
					</c:if>
					<c:if test="${item.vehicleState=='4'}">
						<span class="span_btn" onClick="toURL('jiesuan', '${item.vehicleTrans}')">结算</span>
					</c:if>	
				</div>
			</div>
			
		</div>
	</c:forEach>
	<c:if test="${waUserVehicleList== null || fn:length(waUserVehicleList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
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
				window.location.href = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-detailVehicle?vehicleTrans=" + vehicleTrans;
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