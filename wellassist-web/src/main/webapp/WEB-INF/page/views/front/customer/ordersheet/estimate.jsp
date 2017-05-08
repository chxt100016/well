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
		
		<div style="text-align:center;font-size:24px;">评价</div>
	</div>

	<form action="#" method="post">
	<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:14px;">

		<div style="margin-left:32px;margin-right:32px;padding-top:48px;">
			<input name="level" type="radio" style="margin-left:10%;margin-top:-4px;">
			<span style="display:inline-block; width:15%;">好评</span>
			<input name="level" type="radio" style="margin-left:10%;margin-top:-4px;">
			<span style="display:inline-block; width:15%;">中评</span>
			<input name="level" type="radio" style="margin-left:10%;margin-top:-4px;">
			<span style="display:inline-block; width:15%;">差评</span>
		</div>

		<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
			<span style="display:inline-block; text-align:right;width:10%;">评价内容:</span>
			<input name="content" type="text" style="width:86%;">
		</div>

		<div align=center style="margin-bottom: 24px;margin-top: 32px;">
			<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
			<input type="button" id="back" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回">
			<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认评价">
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