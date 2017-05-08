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
	label.error{
		font-size: 14px;
		margin-bottom: 6px;
	}
	
	tr{
		height:64px;
		width:100%;
	}
	td{
		padding-right:8px;
	}
	tbody{
		width:100%;
	}
</style>

</head>
<body style="margin:0; padding:0; background: white;">

	<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
		
		<div style="text-align:center;font-size:24px;">提现</div>
	</div>

	<form action="#" method="post">
	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:16px;">

		<div style="height: 48px;"></div>
		<table style="padding-left:4%;padding-right:4%;width:92%;">
			<tr>
				<td class="caption" style="width:15%;text-align:right;">开户名</td>
				<td style="width:35%;"><input type="text" name="name" style="width:90%;"></td>
				<td style="width:15%;text-align:right;"></td>
				<td style="width:35%;text-align:right;"></td>
			</tr>
			<tr>
				<td class="caption" style="text-align:right;">开户行</td>
				<td style=""><input type="text" name="bank" style="width:90%;"></td>
				<td class="caption" style="text-align:right;">银行账号</td>
				<td style=""><input type="text" name="bank_account" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="caption" style="text-align:right;">开户地区</td>
				<td colspan=3>
					<select name="province" style="width: 15%;">
					
					</select>
					<select name="city" style="width: 15%;">
					
					</select>
					<select name="block" style="width: 15%;">
					
					</select>
				</td>
			</tr>
			<tr>
				<td class="caption" style="text-align:right;">开户支行</td>
				<td colspan=3>
					<select name="subbank" style="width: 48%;">
					
					</select>
				</td>
			</tr>
			<tr>
				<td class="caption" style="text-align:right;">提现金额</td>
				<td style=""><input type="text" name="money" style="width:90%;" placeholder="可提金额200000" value=""></td>
				<td class="caption">元</td>
			</tr>
			<tr>
				<td class="caption" style="text-align:right;">密码</td>
				<td style=""><input type="password" name="password" style="width:90%;" autocomplete="off"></td>
			</tr>
		</table>


		<div align=center style="margin-top: 32px;margin-bottom: 24px;">
			<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
			<input type="button" id="" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回" onclick="history.back();">
			<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认提现">
		</div>
	</div>
	</form>

</body>
<script type="text/javascript">

	$("td").attr("valign", "top");
	$(".caption").css("padding-top", "10px");


	$("input").css("font-size", "16px");
	$("select").css("font-size", "16px");
	
	$("#back").click( function(){
		history.back();
	});
	

	$("form").validate();

	$("input[name=name]").rules("add", {required : true});
	$("input[name=bank]").rules("add", {required : true});
	$("input[name=bank_account]").rules("add", {required : true});
	$("input[name=money]").rules("add", {required : true,number : true});

	$("input[name=name]").attr("title", "请输入开户名。");
	$("input[name=bank]").attr("title", "请输入开户行。");
	$("input[name=bank_account]").attr("title", "请输入银行账号。");
	$("input[name=money]").attr("title", "请输入得正确。");
	
</script>
</html>