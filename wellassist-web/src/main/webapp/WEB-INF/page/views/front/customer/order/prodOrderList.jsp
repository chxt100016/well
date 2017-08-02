<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<style type="text/css">
.ui.table td{
	vertical-align: middle;
}
</style>
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
	
	<table class="ui selectable striped blue table" style="text-align:center;">
	  <thead>
	    <tr>
	      <th>日期</th>
	      <th width="18%">订单编号</th>
	      <th>商品</th>
	      <th>单价(元/吨)</th>
	      <th>数量(吨)</th>
	      <th>实付款(元)</th>
	      <th colspan="3">
			<select class="ui dropdown" type="hidden">
				<option>交易状态</option>
				<option onclick="$('#orderState').val('');searchData(1);">全部</option>
				<option onclick="$('#orderState').val('0');searchData(1);">待确认</option>
				<option onclick="$('#orderState').val('1');searchData(1);">待付款</option>
				<option onclick="$('#orderState').val('22');searchData(1);">已付款(线下申请)</option>
				<option onclick="$('#orderState').val('2');searchData(1);">已付款</option>
				<option onclick="$('#orderState').val('3');searchData(1);">发货中</option>
				<option onclick="$('#orderState').val('4');searchData(1);">已发货</option>
				<option onclick="$('#orderState').val('5');searchData(1);">已收货</option>
				<option onclick="$('#orderState').val('6');searchData(1);">待评价</option>
				<option onclick="$('#orderState').val('7');searchData(1);">已完成</option>
				<option onclick="$('#orderState').val('-1');searchData(1);">已取消</option>
			</select>
	      </th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="item" items="${waOrderList}">
	    <tr>
	      <td>${item.orderDate}</td>
	      <td>${item.orderNo}</td>
	      <td>${item.prodName}</td>
	      <td>
	      	<c:choose>
				<c:when test="${not empty item.orderPrice}">
					${item.orderPrice}
				</c:when>
				<c:otherwise>
					<c:if test="${item.saleNum!=null and item.saleNum>0}">
						${item.saleMoney/item.saleNum}
					</c:if>
				</c:otherwise>
			</c:choose>
		  </td>
	      <td width="12%">
	      	<c:if test="${not empty item.orderNumber and not empty item.orderPrice}">${item.orderNumber}</c:if>
			<c:if test="${empty item.orderNumber or empty item.orderPrice}">${item.saleNum}</c:if>
			<c:if test="${item.orderState==5||item.orderState==6||item.orderState==7}"><br>(成交量 ${item.saleSjNum})</c:if>
		  </td>
	      <td width="14%">
			<c:if test="${not empty item.orderNumber and not empty item.orderPrice}">${item.orderNumber*item.orderPrice}</c:if>
			<c:if test="${empty item.orderNumber or empty item.orderPrice}">${item.saleMoney}
			</c:if>
			<c:if test="${item.orderState==5||item.orderState==6||item.orderState==7}"><br>(成交额：${item.saleSjMoney})</c:if>
	      </td>
	      <td style="color:#f20;">
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
	      <td onclick="toURL('logisticsDetail', '${item.orderId}')">物流信息</td>
	      <td onclick="toURL('orderDetail', '${item.orderId}')">订单详情</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>

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