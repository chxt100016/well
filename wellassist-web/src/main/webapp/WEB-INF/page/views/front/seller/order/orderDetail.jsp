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
		<form id="ddxqForm" action="${pageContext.request.contextPath}/seller/processOrder" method="post">
		<%--<form id="ddxqForm" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-setOrderInfo" method="post">--%>
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
					<div class="labeldd" align="right">供应量 :</div>
					<div class="contentdd">
						<c:if test="${isEdit=='1'}"><input type="text" name="saleNum" value="${orderInfo.saleNum}" placeholder="请输入供应量" onkeyup="return validateNumber(this,value,0)" /></c:if>
						<!--
						<c:if test="${orderInfo.orderState=='1' and isEdit=='1'}"><input type="text" name="saleNum" value="${orderInfo.saleNum}" placeholder="请输入供应量" onkeyup="return validateNumber(this,value,0)" /></c:if>
						<c:if test="${orderInfo.orderState!='1' or  isEdit!='1'}">${orderInfo.saleNum}吨</c:if>-->
					</div>
					<div class="labeldd" align="right">单价 :</div>
					<div class="contentdd">
						<c:if test="${isEdit=='1'}"><input type="text" name="saleDj" value="<c:if test="${orderInfo.saleNum!=null and orderInfo.saleNum>0}">${orderInfo.saleMoney/orderInfo.saleNum}</c:if>" placeholder="请输入单价" onkeyup="return validateNumber(this,value,0)" /></c:if>
						<!--
						<c:if test="${orderInfo.orderState=='1' and isEdit=='1'}"><input type="text" name="saleDj" value="<c:if test="${orderInfo.saleNum!=null and orderInfo.saleNum>0}">${orderInfo.saleMoney/orderInfo.saleNum}</c:if>" placeholder="请输入单价" onkeyup="return validateNumber(this,value,0)" /></c:if>
						<c:if test="${orderInfo.orderState!='1' or  isEdit!='1'}"><c:if test="${orderInfo.saleNum!=null and orderInfo.saleNum>0}">${orderInfo.saleMoney/orderInfo.saleNum}吨/元</c:if></c:if>
						-->
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
						<c:if test="${orderInfo.orderState=='3'}">未发货</c:if>
						<c:if test="${orderInfo.orderState=='4'}">已发货</c:if>
						<c:if test="${orderInfo.orderState=='5'}">待评价</c:if>
						<c:if test="${orderInfo.orderState=='6'}">已完成</c:if>
					</div>
				</div>
				
				<input type="hidden" name="orderId" value="${orderInfo.orderId}" />

				<div style="margin: 40px 0px 40px 80px;">
					<c:if test="${isEdit=='1'}">
						<input type="button" class="bluebutton" style="padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="返回" onclick="goBack();" />
						<input type="submit" id="submit" class="bluebutton" style="padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="确认" />
					</c:if>
					<c:if test="${isEdit!='1'}">
						<c:if test="${orderInfo.orderState=='5' or orderInfo.orderState=='6'}">
							<a style="font-size: 18px; color: #1177dd; cursor: pointer;" onclick="showDd(1)">物流信息</a>				
						</c:if>	
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
		<form id="wlxxForm" action="${pageContext.request.contextPath}/seller/processOrder" method="post">
			<%--<form id="wlxxForm" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-setOrderInfo" method="post">--%>
			<div class="formDd">
				<div class="headDd">物流信息 <a id="qrvhAllBtn" style="display: none; float: right; font-size: 14px; color: #1177dd; cursor: pointer;" onclick="setAllVehicle()">全部确认</a></div>
				
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
									<c:if test="${isEdit=='1' and item.vehicleState=='0'}">
											&nbsp; 装载量 : <input type="text" vehicleid="${item.vehicleId}" id="vehicleSjSize_${item.vehicleId}" name="vehicleSjSize" style="width:60px;" onkeyup="return validateNumber(this,value,1)" />吨
									</c:if>
									<c:if test="${isEdit!='1' or item.vehicleState!='0'}">
											&nbsp; 装载量 : ${item.vehicleSjSize}吨
									</c:if>
							</div>
							<c:if test="${isEdit=='1'}">
								<div class="graybox" style="width:20%;height:60px;line-height:60px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
									<c:if test="${item.vehicleState=='0'}">
											&nbsp; 单价 : <input type="text" vehicleid="${item.vehicleId}" id="zorderMoney_${item.vehicleId}" name="zorderMoney" style="width:60px;" onkeyup="return validateNumber(this,value,1)" />元/吨
									</c:if>
									<c:if test="${item.vehicleState!='0'}">
											&nbsp; 单价 : ${item.zorderDj}元/吨
									</c:if>
								</div>
								<div class="graybox" style="width:18%;height:60px;line-height:60px;font-size:14px;float:left; border:none;text-align:center;">
									<c:if test="${item.vehicleState=='0'}">
										<a class="qrvhAClass" style="font-size: 14px; color: #1177dd; cursor: pointer;" onclick="setVehicle('${item.vehicleId}')">确认</a>
									</c:if>
								</div>
							</c:if>
							<c:if test="${isEdit!='1'}">
								<div class="grayboxwithoutleft" style="width:28%;height:60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
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
							</c:if>
						</div>
					</div>
				</c:forEach>
				<c:if test="${vehicleList== null || fn:length(vehicleList) == 0}">
					<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
			    </c:if>
				
				<input type="hidden" name="orderId" value="${orderInfo.orderId}" />

				<div style="margin: 40px 0px 40px 80px;">
					<c:if test="${isEdit!='1'}">
						<c:if test="${orderInfo.orderState=='5' or orderInfo.orderState=='6'}">
							<a style="font-size: 18px; color: #1177dd; cursor: pointer;" onclick="showDd(0)">订单详情</a>				
						</c:if>	
					</c:if>
				</div>
				<input type="button" class="bluebutton" style="margin-left:50px;padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="返回" onclick="goBack();" /> 
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

	var orderState = '${orderInfo.orderState}';

	$(".backBtn").click(function(){
		goBack();
	});
	
	var qrvhAClassCn = 0;
	$(".qrvhAClass").each(function(){
		qrvhAClassCn++;
	});
	if(qrvhAClassCn>0) $("#qrvhAllBtn").show();
	
	// validate检查
	$("#ddxqForm").validate({
	    rules: {},
	    messages: {},
	    errorPlacement: function (error, element) {},
	    submitHandler: function(form){
	    	
	    	// if(orderState=="1"){
	    		var saleNum = $("input[name='saleNum']").val();
	    		var saleDj  = $("input[name='saleDj']").val();
	    		if(saleNum==""){
	    			alert("请输入供应量!");
	    			return false;
	    		}
				if(saleDj==""){
					alert("请输入单价!");
					return false;
	    		}
	    	// }
	    	if(confirm("你要确定操作吗？")){
		    	$.post($("#ddxqForm").attr("action"),$("#ddxqForm").serialize(),function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-orderList";
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
	    	}
	    }
    });
	
	function setVehicle(vehicleId){
		if(vehicleId>0){
			
			var vehicleSjSize = 0;
			var zorderMoney   = 0;
			
			if(orderState=="3"){
				vehicleSjSize = $("#vehicleSjSize_"+vehicleId).val();
	    		zorderMoney   = $("#zorderMoney_"+vehicleId).val();
	    		
	    		if(vehicleSjSize==null || vehicleSjSize=="" || vehicleSjSize<=0){
	    			alert("请输入装载量!");
	    			return false;
	    		}
				if(zorderMoney==null || zorderMoney=="" || zorderMoney<=0){
					alert("请输入单价!");
					return false;
	    		}
	    	} else {
	    		return false;
	    	}
			
			if(confirm("你要确定操作吗？")){
				$.post("${pageContext.request.contextPath}/front/seller/SellerOrderController-setVehicle",{vehicleId:vehicleId, vehicleSjSize:vehicleSjSize, zorderMoney:zorderMoney},function(data){
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
	
	function setAllVehicle(){

		var vehicleArray  = new Array();
		var vehicleId 	  = 0;
		var vehicleSjSize = 0;
		var zorderMoney   = 0;
		var flag		  = false;
		
		if(orderState=="3"){
			
			$("input[name='vehicleSjSize']").each(function(){
				var vehicleObj	  = new Object();
				
				if(!flag){
					vehicleId = $(this).attr("vehicleid");
					if(vehicleId==null || vehicleId=="" || vehicleId<=0){
		    			alert("操作错误!");
		    			flag = true;
		    			return false;
		    		} else {
		    			vehicleObj.vehicleId = vehicleId;	
		    		}
				}

				if(!flag){
					vehicleSjSize = $(this).val();
					if(vehicleSjSize==null || vehicleSjSize=="" || vehicleSjSize<=0){
		    			alert("请输入装载量!");
		    			$(this).focus();
		    			flag = true;
		    			return false;
		    		} else {
		    			vehicleObj.vehicleSjSize = vehicleSjSize;	
		    		}
				}
				
				if(!flag){
				    $("input[name='zorderMoney']").each(function(){
					    if(vehicleId == $(this).attr("vehicleid")){
					    	zorderMoney   = $("#zorderMoney_"+vehicleId).val();
					    	if(zorderMoney==null || zorderMoney=="" || zorderMoney<=0){
								alert("请输入单价!");
								$(this).focus();
								flag = true;
								return false;
				    		} else {
					    		vehicleObj.zorderMoney = zorderMoney;
					    		
					    		vehicleArray[vehicleArray.length] = vehicleObj;
				    		}
					    }
					});
				}
			});
			
			if(flag || vehicleArray.length<1) return;
			
    	} else {
    		return false;
    	}
		
		if(confirm("你要确定操作吗？")){
			var vehicleData = JSON.stringify(vehicleArray);
			$.post("${pageContext.request.contextPath}/front/seller/SellerOrderController-setAllVehicle",{vehicleData:vehicleData},function(data){
	    		data = $.parseJSON(data);
	    		alert(data.content);
	            if(data.status=="1"){
	            	window.history.go(-1);
	            }
	      	})
	      	.error(function(data){
	      		alert("操作失败！")
	      	});
		}

	}
	
	function showDd(type){
		if(type==0){
			$("#ddXqDiv").show();
			$("#wlxxDiv").hide();
		} else {
			$("#ddXqDiv").hide();
			$("#wlxxDiv").show();
		}
	}
	
	function validateNumber(e, pnumber, type){
		var len = 0;
		if (!/^\d+$/.test(pnumber)){
			if(isNaN(pnumber) == '0') return;
			len = pnumber.length;
			$(e).val(pnumber.substring(0,len-1));
		}
		
		if(type==0){
			var saleNum = $("input[name='saleNum']").val();
			var saleDj  = $("input[name='saleDj']").val();
			
			if(saleNum=="" || saleDj==""){
				$("#saleMoney").html("0");
			} else {
				$("#saleMoney").html(saleNum*saleDj);	
			}
		}
		
		return false;
	}

</script>

<%@ include file="../footer.html"%>