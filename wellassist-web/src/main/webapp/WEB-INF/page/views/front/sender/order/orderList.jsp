<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<style>	
.ulSelected{
            color: #617B90;
            border-bottom: 2px solid #617B90;
        }
</style>

<div class="container1">
	<div style="margin:40px 0 0 210px;">

	
<div id = "content-rect" style="width:90%">
	<h4 class="ui header">订单列表</h4>
    <div class="ui divider"></div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/sender/logisticsOrderList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="vehicleState" name="state" value="${param.state}">
	</form>
	
	<ul class="ds-bl pd-10 ft-sz-15">
            <li class="ds-bl fl-lt pd-10  <c:if test="${empty param.state ||(param.state != '2'&&param.state != '3' && param.state != '4')}" >ulSelected</c:if>" onclick="$('#vehicleState').val('');searchData(1);">全部订单</li>
            <li class="ds-bl fl-lt pd-10 <c:if test="${param.state == '2'}">ulSelected</c:if>" onclick="$('#vehicleState').val('2');searchData(1);">待付款</li>
            <li class="ds-bl fl-lt pd-10 <c:if test="${param.state == '3'}">ulSelected</c:if>" onclick="$('#vehicleState').val('3');searchData(1);">待发货</li>
            <li class="ds-bl fl-lt pd-10 <c:if test="${param.state == '4'}">ulSelected</c:if>" onclick="$('#vehicleState').val('4');searchData(1);">待收货</li>
        </ul>
        <br>
        <table class="ui basic table">
            <thead>
                <tr class="grey-4">
                    <th width="30%">运输产品</th>
                    <th>运输信息</th>
                    <th>数量（吨）</th>
                    <th>运费</th>
                    <th>
							<div class="ui dropdown">
									<div class="text">状态</div>
									<i class="dropdown icon"></i>
									<div class="menu">
										
											<div  class="item" onclick="$('#vehicleState').val('');searchData(1);">全部</div>
											<div class="item" onclick="$('#vehicleState').val('2');searchData(1);">待支付</div>
											<div class="item" onclick="$('#vehicleState').val('3');searchData(1);">待提货</div>
											<div class="item" onclick="$('#vehicleState').val('4');searchData(1);">配送中</div>
											<div class="item" onclick="$('#vehicleState').val('5');searchData(1);">已完成</div>
									</div>
							</div>
					</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
					<c:forEach var="item" items="${info}">
                <tr class="blue-3">
                    <td colspan="5"><span><fmt:formatDate value="${item.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span><span>订单号：${item.orderNo}</span></td>
                    <td> <a href="" ><i class="trash icon"></i></a></td>
                </tr>
                <tr>
                    <td class="right-border">
                        <img src="${item.prodImg}" alt="" width="100px" height="74px" class="ds-bl fl-lt">
                        <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="padding-top: 25px;width: 190px;"> ${item.prodName}</span>
                        <br>
                        <!-- <span class="ds-bl fl-lt  pd-lf-20">32,80元/吨</span> -->
                    </td>
                    <td class="right-border tx-ct" >...</td>
                    <td class="right-border tx-ct" ><span>${item.num}吨</span>
                    </td>
                    <td class="right-border tx-ct" >
                        <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style=""> ${item.orderPrice}元</span>
                        
                    </td>
                    <td class="right-border tx-ct" >
                         <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="">
                             <c:if test="${item.state==-1}">已取消</c:if>
								<c:if test="${item.state==0}">无效</c:if>
								<c:if test="${item.state==2}">待支付</c:if>
								<c:if test="${item.state==3}">待提货</c:if>
								<c:if test="${item.state==4}">配送中</c:if>
								<c:if test="${item.state==5}">已完成</c:if>
                             <c:if test="${item.state==6}">已结算</c:if>
						</span>
						<c:if test="${item.state=='4' || item.state=='5'}">
							<br>
							<span class=" ds-bl fl-lt pd-lf-20">
									<a style="cursor:pointer;color:black;" onclick="toURL('detailVehicle', '${item.orderId}')">物流信息</a>
							</span>
                        </c:if>
                        
                    </td>
                    <td>
						<c:if test="${item.state==2||item.state==3||item.state==4}"><span class="span_btn pointer" onclick="window.location.href='${pageContext.request.contextPath}/sender/detail?logisticsId=${item.logisticsId}';">修改</span></c:if>
                    </td>
                  
				</tr>
			</c:forEach>
			
			</tbody>
			
		</table>
		<div class="right-pagination">
			<%@ include file="../../pagination.jsp"%>
	    </div>
	<c:if test="${empty info || fn:length(info) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>
</div>
</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		$('.dropdown').dropdown({});
		
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