<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align:left;">&nbsp;&nbsp;订单列表</div>

	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/customer/orderList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="orderState" name="orderState" value="${param.orderState}">
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
	
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;height:40px;">
		<div style="width:40%;text-align:center;font-size:16px;float:left;">产品详情</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">价格</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">
			<span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#orderState').val('');searchData(1);">全部</div>
					<div onclick="$('#orderState').val('0');searchData(1);">待确认</div>
					<div onclick="$('#orderState').val('1');searchData(1);">待付款</div>
					<div onclick="$('#orderState').val('22');searchData(1);">已付款(线下申请)</div>
					<div onclick="$('#orderState').val('2');searchData(1);">已付款</div>
					<div onclick="$('#orderState').val('3');searchData(1);">发货中</div>
					<div onclick="$('#orderState').val('4');searchData(1);">已发货</div>
					<div onclick="$('#orderState').val('5');searchData(1);"><%--发送发票--%>已收货</div>
					<div onclick="$('#orderState').val('6');searchData(1);">待评价</div>
					<div onclick="$('#orderState').val('7');searchData(1);">已完成</div>
					<div onclick="$('#orderState').val('-1');searchData(1);">已取消</div>
				</div>
			</span>
		</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>
	<c:forEach var="item" items="${waOrderList}">
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
						<c:if test="${not empty item.orderNumber and not empty item.orderPrice}">
							${item.orderNumber}吨
						</c:if>
						<c:if test="${empty item.orderNumber or empty item.orderPrice}">
							${item.saleNum}吨
						</c:if>
						<c:if test="${item.orderState==5||item.orderState==6||item.orderState==7}">(成交量 ${item.saleSjNum}吨)</c:if>
					</div>	
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					<table style="width:100%;height:90px;text-align:center;margin-top:10px;">
						<tr>
							<td>
								<c:if test="${not empty item.orderNumber and not empty item.orderPrice}">
									总价：${item.orderNumber*item.orderPrice}元
								</c:if>
								<c:if test="${empty item.orderNumber or empty item.orderPrice}">
									总价：${item.saleMoney}元
								</c:if>
								<c:if test="${item.orderState==5||item.orderState==6||item.orderState==7}">
								<br/>
								(成交额：${item.saleSjMoney}元)
							</c:if>
							</td>
						</tr>
						<c:choose>
							<c:when test="${not empty item.orderPrice}">
								<tr>
									<td>
										单价：${item.orderPrice}元/吨
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:if test="${item.saleNum!=null and item.saleNum>0}">
									<tr>
										<td>
											单价：${item.saleMoney/item.saleNum}元/吨
										</td>
									</tr>
								</c:if>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					<table style="width:100%;height:90px;text-align:center;margin-top:10px;">
						<tr>
							<td style="color:#a00;">
								<c:if test="${item.orderState=='0'}">待确认</c:if>
								<c:if test="${item.orderState=='1'}">待付款</c:if>
								<c:if test="${item.orderState=='11'}">待物流付款</c:if>
								<c:if test="${item.orderState=='12'}">待付款</c:if>
								<c:if test="${item.orderState=='13'}">线下付款审核中</c:if>
								<c:if test="${item.orderState=='14'}">线下付款审核中</c:if>
								<c:if test="${item.orderState=='15'}">线下付款审核中</c:if>
								<c:if test="${item.orderState=='2'}">已付款</c:if>
								<c:if test="${item.orderState=='22'}">已付款(线下支付申请)</c:if>
								<c:if test="${item.orderState=='21'}">已付款(中信支付申请)</c:if>
								<c:if test="${item.orderState=='3'}">发货中</c:if>
								<c:if test="${item.orderState=='4'}">已发货</c:if>
								<c:if test="${item.orderState=='5'}"><%--发送发票--%>已收货</c:if>
								<c:if test="${item.orderState=='6'}">待评价</c:if>
								<c:if test="${item.orderState=='7'}">已完成</c:if>
								<c:if test="${item.orderState=='-1'}">已取消</c:if>
							</td>
						</tr>
						<c:if test="${item.orderState==2 || item.orderState==3 ||item.orderState==4 || item.orderState==5 || item.orderState==6 || item.orderState==7}">
							<tr>
								<td>
									<a style="cursor:pointer;color:black;" onclick="toURL('logisticsDetail', '${item.orderId}')">物流信息</a>
								</td>
							</tr>
						</c:if>
						<c:if test="${item.orderState!=-1}">
						<tr>
							<td>
								<a style="cursor:pointer;color:black;" onclick="toURL('orderDetail', '${item.orderId}')">订单详情</a>
							</td>
						</tr>
						</c:if>
					</table>
				</div>
				<div class="grayboxwithoutleft" style="height:110px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
					<c:if test="${item.orderState==0||item.orderState==1}">
						<span class="span_btn" onClick="toURL('cancelOrder', '${item.orderId}')">取消订单</span>
					</c:if>
					<c:if test="${item.orderState==1||item.orderState==12}">
						<span class="span_btn" onClick="toURL('editFukuan', '${item.orderId}')">付款</span>
					</c:if>
					<c:if test="${item.orderState==3 ||item.orderState==4}">
							<span class="span_btn" onClick="toURL('orderDetail', '${item.orderId}')">发货详情</span>
					</c:if>
							
					<c:if test="${item.orderState==6}">
						<span class="span_btn" onClick="toURL('editPingjia', '${item.orderId}')">评价</span>
					</c:if>
					<%--<c:if test="${item.orderState==0 or item.orderState==1}">
						<span class="span_btn_gray" onClick="toURL('cancelOrder', '${item.orderId}')">取消订单</span>
					</c:if>--%>
				</div>
			</div>
			
		</div>
	</c:forEach>
	<c:if test="${waOrderList== null || fn:length(waOrderList) == 0}">
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
	
	function toURL(action, orderId){
		if(orderId!=''){
			if(action=='detailOrder'){
				window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-detailOrder?orderType=0&orderId=" + orderId;
			}else if(action=="logisticsDetail"){
			    window.location.href="${pageContext.request.contextPath}/customer/logisticsDetail?orderId="+orderId;
            }else if(action=="orderDetail"){
                window.location.href="${pageContext.request.contextPath}/customer/orderDetail?orderId="+orderId;
            }
			else if(action=='detailVehicle'){
				window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-detailOrder?orderType=1&orderId=" + orderId;
			} else if(action=='editFukuan'){
				/*window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-editFukuan?orderId=" + orderId;*/
                /*alert("跳过付款过程");
                $.post("${pageContext.request.contextPath}/customer/testPayOrder",{orderId:orderId},function(data){
                    if(data.code==0){
                        window.location.reload();
                    }
                },"json")
                    .error(function(){
                        alert("未知错误，请联系管理员");
                    });*/
                window.location.href = "${pageContext.request.contextPath}/customer/goPayOrder?orderId=" + orderId;
			} else if(action=='editPingjia'){
				window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-editPingjia?orderId=" + orderId;
			} else if(action=='cancelOrder'){
				if(confirm("你要确定取消操作吗？")){
					/*$.post("${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-cancelOrder",{orderId:orderId},function(data){
			    		data = $.parseJSON(data);
			    		alert(data.content);
			            if(data.status=="1"){
			            	window.location.reload();
			            }
			      	})
			      	.error(function(data){
			      		alert("操作失败！");
			      	});*/
					$.post("${pageContext.request.contextPath}/customer/cancelOrder",{orderId:orderId},function(data){
                        data = $.parseJSON(data);
                        if(data.status==1){
                            window.location.reload();
                        }
                    })
				}
			} else if(action=='editFahuo'){
				window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-editFahuo?orderId=" + orderId;
			}
		}
	}
</script>

<%@ include file="../footer.jsp"%>