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

	<div style="border-bottom: solid 1px #ccc; margin:0 auto; text-align:center; width:1000px; padding-bottom:12px;padding-top:20px;padding-bottom: 24px;padding-left: 48px;padding-right: 48px; ">
		
		<span style="text-align:center;font-size:24px;">还款</span>
	</div>

	<form  id = "infoForm" action="${pageContext.request.contextPath}/front/customer/FinanceControlle-clHk" method="post">
		<input type = "hidden" name = "creditId" value = "${info.creditId}"/>
		<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;padding-left: 48px;padding-right: 48px; font-size:16px; width:1000px; margin:0 auto;">
			<div style="margin-left:32px;margin-right:32px;padding-top:32px; border-bottom:solid 1px #ccc; padding-bottom:3px;">还款金额</div>
			<div style="margin-left:32px;margin-right:32px;padding-top:32px;height: 48px;">
				<div style="float:left;width: 65%;">
					<div style="margin-bottom: 12px;">
						<span style="display:inline-block; text-align:right;width:20%;margin-right:2%;">公司名称:</span>
						<span style="display:inline-block; text-align:left;width:40%;">${info.creditUserName}</span>
						<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">账期:</span>
						<span style="display:inline-block; text-align:left;width:20%;">${info.creditOverDate}</span>
					</div>
					<div style="margin-bottom: 12px;">
						<span style="display:inline-block; text-align:right;width:20%;margin-right:2%;">本金:</span>
						<span style="display:inline-block; text-align:left;width:40%;">${info.creditSjMoney}&nbsp;&nbsp;元</span>
						<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">利息:</span>
						<span style="display:inline-block; text-align:left;width:20%;">${info.remainLxMoney}&nbsp;&nbsp;元</span>
					</div>
					<div style="margin-bottom: 12px;">
						<span style="display:inline-block; text-align:right;width:20%;margin-right:2%;">现在余额:</span>
						<span style="display:inline-block; text-align:left;width:40%;">${userInfo.userMoney}&nbsp;&nbsp;元</span>
					</div>
				</div>
				<div style="float:right;width: 34%;padding-top: 14px;" align=right>
					<span style="margin-right:2%;">应还金额:</span>
					<span style="font-size:20px;color:red;">${info.creditSjMoney + info.remainLxMoney}&nbsp;&nbsp;元</span>
				</div>
			</div>
			<div style = "clear:both;"></div>
			<div style="margin-left:32px;margin-right:32px;padding-top:32px; border-bottom:solid 1px #ccc;padding-bottom:3px;">还款方式</div>

			<div class = "one-fld" style="margin-left:32px;margin-right:32px;margin-top:32px;padding-top:6px;padding-bottom:6px;padding-left:6px; border:solid 1px #ccc;">
				<input type="radio" name="zhMethod" value = "1" style="margin-right: 6px;margin-top: -4px;">
				<span>余额支付</span>
			</div>

			<div class = "one-fld" style="margin-left:32px;margin-right:32px;padding-top:32px;">
				<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">支付密码</span>
				<input type="password" name="pass" style="width:30%;">
			</div>

			<div align=center style="margin-top: 32px;margin-bottom: 24px;">
				<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
				<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认支付">
				<input type="button" onclick = "history.back();" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none; margin-left:20px;color: white; font-weight: 400;" value="返回">
			</div>
		</div>
	</form>

</body>
<script type="text/javascript">
	
	// 初始化函数
	$(function(){
		// 检查模块
		$("#infoForm").validate({
		    rules: {
		    	zhMethod: "required",
		    	pass   : {required: true,remote: {
    	    		url: "${pageContext.request.contextPath}/front/seller/SellerFinanceController-checkWorkPass",
    	    		type: "Post",
    	    		data: {
    	    			      userId:"${userInfo.userId}"
    	    			}
    	    		}
    	    	
    	    }
		    },
    
		    messages: {
		    	zhMethod: "选择支付方式!",
		    	pass:{required: "请输入支付密码",remote:"支付密码错误!" }
		    },
		    errorPlacement: function (error, element) { 
		    	if($(element).closest('div.one-fld').children().filter("div.error-div").length < 1) 
				$(element).closest('div.one-fld').append("<div class='error-div'></div>");	
			$(element).closest('div.one-fld').children().filter("div.error-div").append(error);
		    },
		    submitHandler: function(form){
		    	var userMoney = '${userInfo.userMoney}';
		    	if(parseInt(userMoney) <= 0 ) {
		    		alert("先充值再还款下!");
		    		return; 
		    	}
		    	
		    	if(!confirm("您确认操作吗？")) return;
		    	$.post($(form).attr("action"),$(form).serialize(),function(data){
		    		alert(data.content);
		            if(data.state==1 ){
		            		window.location.href = "${pageContext.request.contextPath}/front/customer/FinanceController-hkList";
		            	}
		        } , "json");
		    }
		});

		$("input").css("font-size", "16px");
		
		
	
	});
	
	
</script>
</html>