<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<style>
	.ulSelected{
            color: #617B90;
            border-bottom: 2px solid #617B90;
		}
		
</style>
<div class="container1">
	<div class="container2">

	
<div id = "content-rect" style="width:90%">
	<ul class="ds-bl pd-10 ft-sz-15">
            <li class="ds-bl fl-lt pd-10 pointer  <c:if test="${empty param.orderState ||(param.orderState != '1'&&param.orderState != '2' && param.orderState != '3')}" >ulSelected</c:if>" onclick="$('#orderState').val('');searchData(1);" style="padding-left:0px">全部订单</li>
            <li class="ds-bl fl-lt pd-10 pointer <c:if test="${param.orderState == '1'}">ulSelected</c:if>" onclick="$('#orderState').val('1');searchData(1);">待付款</li>
            <li class="ds-bl fl-lt pd-10 pointer <c:if test="${param.orderState == '2'}">ulSelected</c:if>" onclick="$('#orderState').val('2');searchData(1);">待发货</li>
            <li class="ds-bl fl-lt pd-10 pointer <c:if test="${param.orderState == '3'}">ulSelected</c:if>" onclick="$('#orderState').val('3');searchData(1);">待收货</li>
        </ul>
	<br>
		
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/seller/orderListPage">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="orderState" name="orderState" value="${param.orderState}">
	</form>
        <table class="ui basic table ft-sz-14">
            <thead>
                <tr class="grey-4">
                    <th width="30%">产品详情</th>
                    <th class="tx-ct">金额（元）</th>
                    <th>数量（吨）</th>
                    <!-- <th>物流信息</th> -->
					<!-- <th>交易状态</th> -->
					<th>
						<div class="ui pointing dropdown link item" tabindex="0">
							<span class="text">交易状态</span>
							<i class="dropdown icon"></i>
							<div class="menu transition hidden" tabindex="-1">
								<div onclick="$('#orderState').val('');searchData(1);" class="item">全部</div>  
								<div onclick="$('#orderState').val('0');searchData(1);" class="item">待确认</div>
								<div  onclick="$('#orderState').val('1');searchData(1);" class="item">待付款</div>
								<div onclick="$('#orderState').val('2');searchData(1);" class="item">已付款</div>
								<div onclick="$('#orderState').val('3');searchData(1);" class="item">发货中</div>
								<div onclick="$('#orderState').val('4');searchData(1);" class="item">已发货</div>  
								<div onclick="$('#orderState').val('5');searchData(1);"  class="item">已收货</div>
								<div onclick="$('#orderState').val('6');searchData(1);" class="item">待评价</div>
								<div onclick="$('#orderState').val('7');searchData(1);" class="item">已完成</div>
								<div onclick="$('#orderState').val('-1');searchData(1);" class="item">已取消</div>
          					</div>
						</div>
					</th>
                    <th>交易操作</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${waOrderList}">
                <tr class="blue-3">
                    <td colspan="5"><span><fmt:formatDate value="${item.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </span><span>订单号：${item.orderNo}</span></td>
                    <td> <span  class="pointer" onClick="toURL('cancelOrder', '${item.orderId}')" style="font-size:15px"><i class="trash icon"></i></span></td>
                </tr>
                <tr>
                    <td class="right-border">
                        <img src="${item.prodImg}" alt="" width="100px" height="74px" class="ds-bl fl-lt bordered">
                        <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="padding-top: 25px;width: 190px;"> ${item.prodName}</span>
                        <br>
                        <span class="ds-bl fl-lt  pd-lf-20">
                            <c:choose>
								<c:when test="${not empty item.orderPrice}">
									单价：${item.orderPrice}元/吨
								</c:when>
								<c:otherwise>
									<c:if test="${item.saleNum!=null and item.saleNum>0}">
										单价：${item.saleMoney/item.saleNum}元/吨
									</c:if>
								</c:otherwise>
							</c:choose>
                        </span>
                    </td>
                    <td class="right-border tx-ct" >
                        <span>
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
						</span>
                    </td>
                    <td class="right-border tx-ct" >
                        <c:if test="${not empty item.orderNumber and not empty item.orderPrice}">
							${item.orderNumber}吨
						</c:if>
						<c:if test="${empty item.orderNumber or empty item.orderPrice}">
							${item.saleNum}吨
						</c:if>
						<c:if test="${item.orderState==5||item.orderState==6||item.orderState==7}">(成交量 ${item.saleSjNum}吨)</c:if>
                    </td>
                    <td class="right-border tx-ct" >
                        <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="">
							<c:if test="${item.orderState=='0'}">待确认</c:if>
							<c:if test="${item.orderState=='1'}">待付款</c:if>
							<c:if test="${item.orderState=='2'}">已付款(未发货)</c:if>
							<c:if test="${item.orderState=='3'}">发送中</c:if>
							<c:if test="${item.orderState=='4'}">已发货</c:if>
							<c:if test="${item.orderState=='5'}"><%--发送发票--%>已收货</c:if>
							<c:if test="${item.orderState=='6'}">待评价</c:if>
							<c:if test="${item.orderState=='7'}">已完成</c:if>
                        </span>
                        <br>
                        <span class="ds-bl fl-lt  pd-lf-20">
                            <c:if test="${item.orderState!=-1}">
                                <a style="cursor:pointer;color:black;" onclick="toURL('orderDetail', '${item.orderId}')">订单详情</a>
                            </c:if>
                        </span>
                    </td>
                    <td>
                        <c:if test="${item.orderState=='0'}">
							<span class="span_btn" onClick="toURL('confirmOrder', '${item.orderId}')">确认订单</span>
						</c:if>
						<c:if test="${item.orderState=='1'}">
							<span class="span_btn" onClick="toURL('editOrder', '${item.orderId}')">编辑订单</span>
						</c:if>
						<c:if test="${item.orderState=='2' || item.orderState=='3'}">
							<span class="span_btn" onClick="toURL('sendProd', '${item.orderId}')">发货</span>
							<span class="span_btn" onClick="toURL('sendProdOver', '${item.orderId}')">结束发货</span>
						</c:if>
						<c:if test="${item.orderState=='4'}">
							<%--<span class="span_btn" onClick="toURL('editFapiao', '${item.orderId}')">开发票</span>--%>
							<%--暂无操作...--%>
						</c:if>
						<c:if test="${item.orderState==0 || item.orderState==1}">
							<span class="span_btn_gray" onClick="toURL('cancelOrder', '${item.orderId}')">取消订单</span>
						</c:if>
                    </td>
                    <td></td>               
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>	
				 <tr>
					 <th colspan="6">
						<div class="right-pagination ui right floated pagination menu">
								<%@ include file="../../pagination.jsp"%>
							</div>
					 </th>
				 </tr>
			</tfoot>
        </table>
	<c:if test="${waOrderList== null || fn:length(waOrderList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	
</div>
</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		// $('.fancybox').fancybox();
		
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);

		 console.log("${page}");
		 $('.ui.dropdown')
  .dropdown()
;
	});
	
	function toURL(action, orderId){
		if(orderId!=''){
			if(action=='orderDetail'){
				window.location.href = "${pageContext.request.contextPath}/seller/orderDetail?orderId=" + orderId;
			} else if(action=='confirmOrder'){
				window.location.href="${pageContext.request.contextPath}/seller/confirmOrder?orderId="+ orderId;
            }else if(action=='sendProd'){
			    window.location.href="${pageContext.request.contextPath}/seller/sendProd?orderId="+ orderId;
            }else if(action=='sendProdOver'){
				if(confirm("确定结束发货？")){
				    $.get("${pageContext.request.contextPath}/seller/sendProdOver",{orderId:orderId},function(data){

                        alert(data.content);
                        if(data.status=="1"){
                            window.location.reload();
                        }
					},"json")
                }
            }
			else if(action=='logisticsDetail'){
				window.location.href = "${pageContext.request.contextPath}/seller/logisticsDetail?orderId=" + orderId;
			} else if(action=='editOrder'){
				window.location.href = "${pageContext.request.contextPath}/seller/editOrder?orderId=" + orderId;
			} else if(action=='editVehicle'){
				window.location.href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-detailOrder?isEdit=1&orderType=1&orderId=" + orderId;
			} else if(action=='editFapiao'){
				window.location.href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-editFapiao?orderId=" + orderId;
			} else if(action=='cancelOrder'){
				if(confirm("你要确定取消操作吗？")){
                    $.post("${pageContext.request.contextPath}/customer/cancelOrder",{orderId:orderId},function(data){
                        data = $.parseJSON(data);
                        if(data.status==1){
                            window.location.reload();
                        }
                    })
				}
			} else if(action=='setOrderState'){
				if(confirm("你要确定操作吗?")){
					$.post("${pageContext.request.contextPath}/front/seller/SellerOrderController-setOrderState",{orderId:orderId},function(data){
			    		data = $.parseJSON(data);
			    		alert(data.content);
			            if(data.status=="1"){
			            	window.location.reload();
			            }
			      	})
			      	.error(function(data){
			      		alert("操作失败！")
			      	});
				}
			}
		}
	}
</script>

<%@ include file="../footer.jsp"%>