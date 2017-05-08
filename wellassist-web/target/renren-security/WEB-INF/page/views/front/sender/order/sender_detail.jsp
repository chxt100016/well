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
		
		<div style="text-align:center;font-size:24px;">物流详情</div>
	</div>

	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:14px;">

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;padding-bottom:6px;border-bottom:solid 1px #ddd; font-size:18px;font-weight:bold;">物流信息</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">发货时间:</span>
			<span style="display:inline-block; text-align:left;width:20%;">2016-09-08</span>
			<span style="display:inline-block; text-align:right;width:15%;">预计收货时间:</span>
			<span style="display:inline-block; text-align:left;width:20%;">2016-09-10</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">司机名称:</span>
			<span style="display:inline-block; text-align:left;width:20%;">王师簿</span>
			<span style="display:inline-block; text-align:right;width:10%;">电话:</span>
			<span style="display:inline-block; text-align:left;width:20%;">1234567890</span>
			<span style="display:inline-block; text-align:right;width:15%;">车牌号:</span>
			<span style="display:inline-block; text-align:left;width:20%;">1234567890</span>
		</div>
		<div style="margin:10px 0px 0px 46px;">
			<input  name="pubbtn" type="button" value="返回" class="bluebutton" style="font-size:20px;border-radius:6px;" onclick="goBack();">
		</div>
	</div>

</body>
<script type="text/javascript">

	$("#back").click( function(){
		history.back();
	});
	
</script>
</html>