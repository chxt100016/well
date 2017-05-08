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
		<div style="text-align:center;font-size:24px;">提现</div>
	</div>

	<form action="#" method="post">
	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:16px;">
		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户名</span><input type="text" name="name" style="width:30%;"><span style="margin-left:12px;">元</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户行</span><input type="text" name="bank" style="width:30%;">
			<span style="display:inline-block; text-align:right;width:12%;margin-right:2%;">银行账号</span><input type="text" name="bank_account" style="width:30%;">
		</div>
		
		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户地区</span>
			<select name="province" style="width: 15%;">
			
			</select>
			<select name="city" style="width: 15%;">
			
			</select>
			<select name="block" style="width: 15%;">
			
			</select>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户支行</span>
			<select name="subbank" style="width: 46%;">
			
			</select>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">提现金额</span>
			<input type="text" name="money" style="width:30%;" placeholder="可提金俄200000">
			<span style="margin-left:12px;">元</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">密码</span>
			<input type="password" name="password" style="width:30%;">
		</div>

		<div align=center style="margin-top: 32px;margin-bottom: 24px;">
			<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
			<input type="button" id="back" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回">
			<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认提现">
		</div>
	</div>
	</form>

</body>
<script type="text/javascript">

	$("input").css("font-size", "16px");
	$("select").css("font-size", "16px");
	
	$("#back").click( function(){
		history.back();
	});
	
</script>
</html>