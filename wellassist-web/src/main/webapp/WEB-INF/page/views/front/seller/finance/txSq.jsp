<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">



<style>
.error-div {display: inline;}
</style>

</head>
<body style="margin:0; padding:0; background: #F5F5F5;">
	<div style = "width:1000px; overflow:auto;margin:0px auto; margin-top:30px;">
			<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
				<div id="back" style="text-align:center;font-size:15px;float:left;margin-top:6px; color:#679EE4;">&lt;&lt; &nbsp;&nbsp;返回</div>
				<div style="text-align:center;font-size:18px; font-weight:500;">提现</div>
			</div>
		
			<form id = "infoForm" action="${pageContext.request.contextPath}/seller/withdrawProcess" method="post">
			<div   align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:16px;border-bottom: 1px solid #aaa; background-color:white;">
				<div class = "one-fld" style="margin-left:32px;margin-right:32px;padding-top:32px;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户名</span>
					<input type="text" id = "txName" name="txName" style="width:30%;">
				</div>
		
				<div class = "one-fld"  style="margin-left:32px;margin-right:32px;padding-top:32px;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户行</span>
					<input type="text" name="txKhh" style="width:30%;">
				</div>
				<div class = "one-fld" style="margin-left:32px;margin-right:32px;padding-top:32px;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">银行账号</span>
					<input type="text" name="account" style="width:30%;">
				</div>
				
				<div class = "one-fld"  style="margin-left:32px;margin-right:32px;padding-top:32px;display:none;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户地区</span>
					<select name="province" style="width: 15%;">
					
					</select>
					<select name="city" style="width: 15%;">
					
					</select>
					<select name="block" style="width: 15%;">
					
					</select>
				</div>
		
				<div class = "one-fld"  style="margin-left:32px;margin-right:32px;padding-top:32px; display:none;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">开户支行</span>
					<select name="subbank" style="width: 46%;">
					
					</select>
				</div>
		
				<div class = "one-fld"  style="margin-left:32px;margin-right:32px;padding-top:32px;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">提现金额</span>
					<input type="text" name="txMoney" id = "txMoney" style="width:30%;" placeholder="可提金额${userMoney}">
					<span style="margin-left:12px;">元</span>
				</div>
		
				<div class = "one-fld"  style="margin-left:32px;margin-right:32px;padding-top:32px;">
					<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">密码</span>
					<input type="password" name="pass" style="width:30%;">
				</div>
		
				<div align=center style="margin-top: 32px;margin-bottom: 24px;">
					<input type="button" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;background-color: #0077DD;font-weight:600;" value="返回" onclick="history.back();">
					<input type="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;background-color: #0077DD;font-weight:600;" value="确认提现">
				</div>
			</div>
			</form>
		</div>

</body>
<script type="text/javascript">
    // 初始化函数
    $(function(){
    	
    	// validation检查	
    	$("#infoForm").validate({
    	    rules: {
    	    	txName: "required",
    	    	txKhh: "required",
    	    	account: {required:true},
    	    	txMoney:{required: true, number:true},
    	    	pass:{required:true, remote: {
    	    		url: "${pageContext.request.contextPath}/seller/checkCzPassword",
    	    		type: "Post",
    	    		data: {
    	    			      userId:"${userId}"
    	    			}
    	    		}
    	    	} 
    	    },
    	    
    	    messages: {
    	    	txName:"请输入开户名！",
    	    	txKhh:"请输入开户名！" ,
    	    	account:{required:"请输入银行账号！"},
    	    	txMoney:{required:"请输入提现金额！" , number:"请输入只数字形式！"},
    	    	pass:{required:"请输入密码！" , remote:"错误密码！"}
    	    },
    	    errorPlacement: function (error, element) { 
    	    	if($(element).closest('.one-fld').children().filter("div.error-div").length < 1) 
    			$(element).closest('.one-fld').append("<div class='error-div'></div>");	
    		$(element).closest('.one-fld').children().filter("div.error-div").append(error);
    	    },
    	    submitHandler: function(form){
    	    	var userMoney = "${userMoney}";
    	    	var txMoney = $("#txMoney").val();
    	    	if(parseInt(txMoney) <= 0){
    	    		alert("提现金额不正确！");
    	    		return;
    	    	}
    	    	if(parseInt(txMoney) >= parseInt(userMoney)){
    	    		alert("提现金额比账号金额不大！");
    	    		return;
    	    	}
    	    	$.post($(form).attr("action"),$(form).serialize(),function(data){
//    	    	    var obj = JSON.parse()
					alert("text");
					alert(data.state);
    	    		alert(data);

    	            if(data.state==1 ){
    	            	window.location.href = "${pageContext.request.contextPath}/seller/withdrawRecordList";
    	            }
    	      	}, "json");
    	    }
    	});
    })

	$("input").css("font-size", "16px");
	$("select").css("font-size", "16px");
	
	$("#back").click( function(){
		history.back();
	});
</script>
</html>