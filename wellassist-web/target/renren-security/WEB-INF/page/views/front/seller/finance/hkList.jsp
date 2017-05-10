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

	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:20px;color:#0000d0;">还款记录</div>

	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:20%;text-align:center;float:left;">时间</div>
		<div style="width:20%;text-align:center;float:left;">金融</div>
		<div style="width:15%;padding-left: 5%;float:left;">授信详情</div>
		<div style="width:20%;text-align:center;float:left;">还款企业</div>
		<div style="width:20%;text-align:center;float:left;">利息</div>
	</div>

	<!--  -->
	<div style="height:108px; font-size:14px;">
		<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">[2015-04-23  11:22:31]</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">10000元</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;padding-left:5%;width:15%;height:100px;float:left;">
			<div style="padding-top:10px;">LNG现货</div>
			<div style="padding-top:10px;">5000吨</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">xxx有限公司</div>
		</div>
		<div style="border-bottom:solid 1px #d0d0d0;width:20%;height:100px;float:left;">
			<div style="text-align:center;padding-top:20px;">5000元</div>
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
<%@ include file="../footer.html"%>