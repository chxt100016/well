<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">


<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>">
</script>

<style>
</style>

</head>
<body style="margin:0; padding:0; background: white;">

	<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
		
		<div style="text-align:center;font-size:24px;">确认订单</div>
	</div>

	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:14px;">

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;padding-bottom:6px;border-bottom:solid 1px #ddd; font-size:18px;font-weight:bold;">订单详情</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">企业名称:</span>
			<span style="">昆仑燃气</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">产品名称:</span>
			<span style="">优质进口LNG管道气</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">资源类型:</span>
			<span style="display:inline-block;text-align:left;width:12%;">进口管道气</span>
			<span style="display:inline-block;text-align:right;width:10%;">货运方式:</span>
			<span style="display:inline-block;text-align:left;width:10%;">自提</span>
			<span style="display:inline-block;text-align:right;width:10%;">联系人:</span>
			<span style="display:inline-block;text-align:left;width:10%;">王莫莫</span>
			<span style="">联系电话:</span>
			<span style="">1234567890</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">供应量:</span>
			<span style="display:inline-block;text-align:left;width:12%;">20吨</span>
			<span style="display:inline-block;text-align:right;width:10%;">单价:</span>
			<span style="display:inline-block;text-align:left;width:10%;">5000元/吨</span>
			<span style="display:inline-block;text-align:right;width:10%;">总价:</span>
			<span style="display:inline-block;text-align:left;width:10%;">100000元</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px; margin-bottom: 52px;">
			<span style="display:inline-block; text-align:right;width:10%;">状态:</span>
			<span style="">待确认</span>
		</div>

		<div align=center style="margin-bottom: 24px;margin-top: 32px;">
			<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
			<input type="button" id="back" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回">
			<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认">
		</div>
	</div>

</body>
<script type="text/javascript">

	$("#back").click( function(){
		history.back();
	});
	
</script>
</html>