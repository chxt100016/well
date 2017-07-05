<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<title>订单提交成功</title>
<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/customer/register.css"/>">


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
	
		
		<div align=center style="padding-top:12px;  height: 500px;">
			<div align=left style="width:1000px;">
				<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
					<div style="text-align:center;font-size:24px;">下单</div>
				</div>
			</div>

			<div align=center style="width:1000px; background: white;box-shadow: 2px 2px 8px #808080;padding-bottom:16px;">
				<div><img src="<c:url value="/resources/wella/front/images/zhuceok_icon.png"/>" style="margin-top: 56px;margin-bottom: 56px;"></div>
				<div style="font-size:32px;margin-bottom: 48px;">订单提交成功、数量待审核中</div>
				<div id="backtodingdanlist" style="font-weight:bold;font-size:16px;margin-bottom: 56px;color:#0077dd;cursor:pointer;">返回订单列表</div>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
	
	$("#backtodingdanlist").click( function(){
		window.location.href="<c:url value="/customer/orderList"/>";
	});
	
</script>
</html>