<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>">
</script>

<style>
	span.smallbutton{
		border: solid 1px #d0d0d0;
		cursor: pointer;
		padding: 4px 8px;
		text-align: center;
		color: #404040;
		font-size: 16px;
		border-radius: 4px;
	}
</style>

</head>
<div id = "content-rect">

	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;color:#0000d0;">金融中心</div>
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;padding-bottom:16px;">
		<div style="margin-top:16px; margin-left: 12px;font-size: 20px;">授信余额</div>
		<div style="margin-top:16px; margin-left: 24px;font-size: 20px;">
			<span style="color:red;font-size:20px;font-weight:bold;">800000</span>
			<span style="color:red;font-size:18px;margin-left:-4px;">.00元</span>
			<span id="fillmoney" class="smallbutton" style="margin-left:32px;">充值</span>
			<span id="getmoney" class="smallbutton" style="margin-left:12px;">提现</span>
		</div>
	</div>

	<div style="margin-top:16px;border:solid 1px #d0d0d0;padding:6px;font-size:16px;font-weight: bold;">授信记录</div>

	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:18%;text-align:center;float:left;">时间</div>
		<div style="width:16%;text-align:center;float:left;">金额</div>
		<div style="width:16%;text-align:center;float:left;">授信详情</div>
		<div style="width:14%;text-align:center;float:left;">利息</div>
		<div style="width:16%;text-align:center;float:left;">还款日期</div>
		<div style="width:10%;text-align:center;float:left;">状态</div>
	</div>

	<!--  -->
	<div style="height:108px; font-size:14px;">
		<div style="border-bottom:solid 1px #d0d0d0;width:18%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">[2015-04-23  11:22:31]</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:16%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">-100000元</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;padding-left:5%;width:11%;height:100px;float:left;">
			<div style="padding-top:10px;">xxxx有限公司</div>
			<div style="padding-top:10px;">LNG现货</div>
			<div style="padding-top:10px;">5000吨</div>			
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:14%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">5%</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:16%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">2015-05-13</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:10%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">已还款</div>
		</div>
	</div>

			
 	<div style="padding-top:12px;height:24px;">
		<input type="submit" class="bluebutton" style="float:right;" value="确定">
		<div style="float:right;font-size:14px;padding-top:8px;">&nbsp;页&nbsp;&nbsp;</div>
		<input type="text" name="pageno" style="float:right;width:64px;">
		<div style="float:right;font-size:14px;padding-top:8px;">共01页 至第</div>
		<div class="graywhitebutton" style="float:right;margin-right:32px;width:24px;">&gt;</div>
		<div class="grayrectbutton" style="float:right;width:24px;backgound:#e0e0e0;">1</div>
		<div class="graywhitebutton" style="float:right;width:24px;">&lt;</div>
	</div>
</div>
<%@ include file="../footer.jsp"%>