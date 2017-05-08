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

</head>
<body style="margin:0; padding:0; background: white;">

	<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
		
		<div style="text-align:center;font-size:24px;">物流详情</div>
	</div>

	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:14px;">

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;padding-bottom:6px;border-bottom:solid 1px #ddd; font-size:18px;font-weight:bold;">物流信息</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">发货时间:</span>
			<input name="send_time" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
			<span style="display:inline-block; text-align:right;width:15%;">预计收货时间:</span>
			<input name="receive_time"  class="Wdate" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:24px;">
			<span style="display:inline-block; text-align:right;width:10%;">司机名称:</span>
			<input type="text" style="width:60px;">
			<span style="display:inline-block; text-align:right;width:10%;">电话:</span>
			<input type="text" style="width:100px;">
			<span style="display:inline-block; text-align:right;width:15%;">车牌号:</span>
			<input type="text" style="width:60px;">
		</div>
		<a href="<c:url value="/front/sender/order/sheet_show"/>"><span class="bluebutton graybutton" style="margin-left:45%;">确认发货</span></a>

	</div>

</body>
<script type="text/javascript">

	$("#back").click( function(){
		history.back();
	});
	
	function showcalendar(){
		var endDate=$dp.$('endDate');
		WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'});
	}
	
</script>
</html>