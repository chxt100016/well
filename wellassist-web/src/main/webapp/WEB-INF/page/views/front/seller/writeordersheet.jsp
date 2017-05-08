<%@ include file="header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/seller/register.css"/>">


<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>">
</script>

<style>
	.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:bold;font-size:24px;}
	.ware_button{float:right;color: white;background: red;padding: 6px;border-bottom-left-radius: 8px;border-top-left-radius: 8px;border-top-right-radius: 8px;border-bottom-right-radius: 8px;width: 61px;text-align: center;cursor:hand;font-size:16px;}
	.ware_maker_label{color: red;padding: 6px;float: left;}
	.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
	span.selectedtab{ background:#0077dd;color:white;}
</style>

</head>
<body style="margin:0; padding:0">
	<div class="main-wrapper" style="margin-left: 0px;">

		<!-- navigation bar -->
		<div class="abovenavbar">
			<div align=center>
				<div style="width:1000px;">
<!-- 					<span style="float:left;">WellAssist供应链系统</span> -->
 					<span style="float: right;"><a href="<c:url value="/front/SellerHomeController-main"/>">管理员</a></span>
				</div>
			</div>
		</div>
		
		<div align=center style="padding-top:12px;">
			<div align=left style="width:1000px;">
				<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
					
					<div style="text-align:center;font-size:24px;">下单(${id})</div>
				</div>
			</div>

			<form action="<c:url value="/front/seller/ordersheetresult"/>" method="post">
			<div align=left style="width:1000px; background: white;box-shadow: 2px 2px 8px #808080;padding-bottom:16px;">
				<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 24px; padding-bottom:12px;border-bottom:solid 2px #d0d0d0;font-size:20px;">订单信息</div>
				<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">产品名称: 优质进口LNG管道气</div>
				<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">
					<span style="margin-right: 64px;">货源类型: 进口管道气</span>
					<span style="margin-right: 64px;">货运方式: 自提</span>
					<span style="margin-right: 64px;">联系人: 王莫莫</span>
					<span style="margin-right: 64px;"> 联系电话: 1234567890</span>
				</div>
				<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">
					<span style="margin-right: 64px;">供应量: <input name="gongyingliang" placeholder="填写整车吨位的倍数" style="width:180px;margin-top:-6px;margin-right: 12px;">吨</span>
					<span style="margin-right: 64px;">单价: <input name="danjia" placeholder="填写单价" style="width:120px;margin-top:-6px;margin-right: 12px;">元</span>
					<span style="margin-right: 64px;">总价: <input name="zongjia" placeholder="填写总价" style="width:120px;margin-top:-6px;margin-right: 12px;">元</span>
				</div>
				<div style="margin-left:48px;margin-right:48px;margin-bottom: 24px; font-size:20px;background: #f0f0f0;border-bottom: solid 2px #e0e0e0;display:flex;">
					<span id="youche" class="selectedtab" style="padding-top: 12px;padding-bottom: 12px;width: 100px; cursor:pointer;" align=center>我有车</span>
					<span id="wuliudingdan" style="padding-top: 12px;padding-bottom: 12px; width: 100px; cursor:pointer;" align=center>物流订单</span>
				</div>

				<div id="youchediv">
					<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">
						<span style="margin-right: 64px;">车号: <input name="chehao" placeholder="填写车牌号" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
					</div>
					<div style="margin-left:48px;margin-bottom: 48px; font-size:14px;">
						<span style="margin-right: 64px;">电话: <input name="dianhua" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
						<span style="margin-right: 64px;">司机名: <input name="sijiming" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
					</div>
				</div>

				<div id="wuliudingdandiv" style="display:none;">
					<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">提货地址: 浙江省杭州市南环路中</div>
					<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">
						<span style="margin-left: 28px;">运费: <input name="yunfei" placeholder="填写运费" style="width:250px;margin-top:-6px;margin-right: 12px;">元</span>
					</div>
					<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">
						<span style="margin-right: 64px;">联系电话: <input name="lianxidianhua" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
						<span style="margin-right: 64px;">联系人: <input name="lianxiren" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
					</div>
					<div style="margin-left:48px;margin-bottom: 24px; font-size:14px;">
						<span>配送地址: </span>
						<span>
							<select name="sheng" style="width:80px;margin-top:-6px;margin-right: 12px;">
								<option value="1" selected>浙江</option>
								<option value="2">吉林</option>
							</select>
						</span>
						<span>
							<select name="shi" style="width:80px;margin-top:-6px;margin-right: 12px;">
								<option value="1" selected>杭州</option>
								<option value="2">吉林</option>
							</select>
						</span>
					</div>
					<div style="margin-left:48px;margin-bottom: 48px; font-size:14px;">
						<span style="margin-left: 70px;"><input name="dizhi" style="width:250px;margin-top:-6px;margin-right: 12px;" placeholder="填写详细地址"></span>
					</div>
					
				</div>
				
				<div align=center style="margin-bottom: 24px;">
					<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
					<input type="button" id="back" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回">
					<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认下单">
				</div>
			</div>
			</form>
		</div>

	</div>
</body>
<script type="text/javascript">
	$("#youche").click( function(){
		$("#wuliudingdandiv").hide();
		$("#youchediv").show();
		if( $("#youche").hasClass("selectedtab")==false) $("#youche").addClass("selectedtab");
		$("#wuliudingdan").removeClass("selectedtab");
	});
	$("#wuliudingdan").click( function(){
		$("#youchediv").hide();
		$("#wuliudingdandiv").show();
		if( $("#wuliudingdan").hasClass("selectedtab")==false) $("#wuliudingdan").addClass("selectedtab");
		$("#youche").removeClass("selectedtab");
	});
	
	$("#back").click( function(){
		history.back();
	});
	
</script>
</html>