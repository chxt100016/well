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
	<div id="wlxxDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">

				<div style="text-align:center;font-size:24px;">发货信息</div>
			</div>
		</div>
		<form id="wlxxForm" method="post">
			<div class="formDd">
				<div class="headDd">发货信息<a id="qrshAllBtn" style="display:none;float:right;cursor:pointer;color:#2482df;" onclick="toURL('qrshAll','','')">全部确认</a></div>
				
				<c:forEach var="item" items="${zorderList}">
					<div style="border:solid 1px #d0d0d0;font-size:24px;margin:16px 48px;overflow:auto;">
						<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
							<div class="graybox" style="width:20%;line-height:80px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
								&nbsp; 数量 : ${item.zorderNum}吨
							</div>
							<div class="grayboxwithoutleft" style="width:20%;line-height:80px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
								&nbsp; 金额 : ${item.zorderMoney}元
							</div>
							<div class="graybox" style="width:40%;height:80px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
								<table style="width:100%;height:70px;text-align:center;margin-top:5px;">
									<tr>
										<td>
											车牌号 : ${item.vehicleNo}
										</td>
									</tr>
									<tr>
										<td>
											司机 : ${item.sjLxr}
										</td>
									</tr>
									<tr>
										<td>
											电话 : ${item.sjLxrPhone}
										</td>
									</tr>
								</table>
							</div>
							<div class="grayboxwithoutleft" style="width:15%;line-height:80px;font-size:14px;float:left;border:none;text-align:center;">
								<c:if test="${item.zorderState==1}">
									<a class="qrshAClass" style="cursor:pointer;color:#2482df;" onclick="toURL('qrsh', '${item.zorderId}', '${item.vehicleId}')">确认</a>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:if test="${zorderList== null || fn:length(zorderList) == 0}">
					<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
			    </c:if>
			    <input id=""  name="pubbtn"  type="button" value="返回" class="bluebutton" style="margin-left:50px;font-size:20px;border-radius:6px;" onclick="goBack();">
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(".backBtn").click(function(){
		goBack();
	});

	var qrshAClassCn = 0;
	$(".qrshAClass").each(function(){
		qrshAClassCn++;
	});
	if(qrshAClassCn>0) $("#qrshAllBtn").show();

	function toURL(action, zorderId, vehicleId){
		
		var orderId = "${orderId}";
		
		if(zorderId!=''){
			if(action=='qrsh'){
				if(confirm("你要确定操作吗？")){
					$.post("${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-setShouhuo",{orderId:orderId,zorderId:zorderId,vehicleId:vehicleId},function(data){
			    		data = $.parseJSON(data);
			    		alert(data.content);
			            if(data.status=="1"){
			            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-prodOrderList";
			            }
			      	})
			      	.error(function(data){
			      		alert("操作失败！")
			      	});
				}
			}
		}
		
		if(action=='qrshAll'){
			if(confirm("你要确定操作吗？")){
				$.post("${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-setShouhuoAll",{orderId:orderId},function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-prodOrderList";
		            	//window.history.go(-1);
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
			}
		}
	}
</script>

<%@ include file="../footer.jsp"%>