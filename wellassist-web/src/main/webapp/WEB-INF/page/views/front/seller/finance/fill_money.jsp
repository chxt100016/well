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
		
		<div style="text-align:center;font-size:24px;">充值</div>
	</div>

	<form action="#" method="post">
	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px;">
		<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 12px; padding-bottom:12px;font-size:20px;">
			<span style="margin-right:32px;">充值金额</span><input type="text" name="money"><span style="margin-left:12px;">元</span>
		</div>
		
		<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 32px; padding-bottom:12px;font-size:20px;">
			<span style="margin-right:32px;">充值方式</span><img src="<c:url value="/resources/upload/images/bank_mark/china_citic_bank.png"/>" style="border:solid 1px #aaa;">
		</div>


		<div align=center style="margin-bottom: 24px;">
			<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
			<input type="button"  class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回" onclick="history.back();">
			<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认充值">
		</div>
	</div>
	</form>

</body>
<script type="text/javascript">
	
	$("#back").click( function(){
		history.back();
	});
	
</script>
</html>