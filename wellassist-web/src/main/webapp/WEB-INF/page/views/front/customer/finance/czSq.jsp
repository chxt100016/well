<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style type="text/css">
	label.error{font-size: 14px;margin-bottom: 6px;	}
	tr{height:84px;}
	.fsImg{border:solid 2px darkgray; width: 180px; height:56px;cursor:pointer;}
	.selected{border:2px solid #C2231A;cursor:pointer;}
</style>

</head>
<body style="margin:0; padding:0; background: #F5F5F5;">
	<div style = "width:1000px; overflow:auto;margin:0px auto; margin-top:30px;">
		<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
			
			<div style="text-align:center;font-size:24px;">充值</div>
		</div>
	
		<form id = "infoForm" action="${pageContext.request.contextPath}/customer/rechargeApply" method="post">
			<input type = "hidden" id = "rechargeType" name = "rechargeType"/>
			<div align=left style="height: 500px;box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px;font-size:16px;border-bottom: 1px solid #aaa; background-color:white;">
				<div style="height: 48px;background-color:white;"></div>
				<table style="padding-left:4%;padding-right:4%;width:92%;background-color:white;">
					<tr>
						<td class="caption" style="width:20%;text-align:center;">充值金额</td>
						<td style="width:30%;">
							<div class = "one-fld">
								<input type="text" id = "rechargeMoney" name="rechargeMoney" style="width:90%;">
							</div>
						</td>
						<td class="caption" style="text-align:left;">元</td>
					</tr>
					<tr>
						<td class="caption" style="text-align:center;">充值方式：</td>
						<%--<td><img  class = "fsImg" src="<c:url value="/resources/upload/images/bank_mark/china_citic_bank.png"/>"  onclick = "selectPayFs('1', this);"></td>
						<td><img class = "fsImg  selected" src="<c:url value="/resources/upload/images/bank_mark/xianxia.png"/>"  onclick = "selectPayFs('0', this);"></td>--%>
						<td>
							<div class="ui floating labeled icon dropdown button">
  								<i class="payment icon"></i>
									<span class="text">选择银行卡</span>
									<div class="menu">
										<div class="header">请选择您添加过的银行卡 </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国银行(1158)  </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国农业银行 </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国人民很行 </div>
									</div>
							</div>
						</td>
						<td></td>
					</tr>
				
				</table>
	
				<div align=center style="margin-bottom: 24px;">
					<input type="button"  class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回" onclick="goBack();">
					<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认充值">
				</div>
			</div>
		</form>
	</div>

</body>
<script type="text/javascript">
	var rechargeType = 0;
	// 选择支付方式
	function selectPayFs(selZfType, obj){
		$(".fsImg").removeClass("selected");
		$(obj).addClass("selected");
		rechargeType = selZfType;
	}
	
	// 初始化函数
	$(function(){
		// validation检查	
    	$("#infoForm").validate({
    	    rules: {
    	    	rechargeMoney:{required: true, number:true}
    	    },
    	    messages: {
    	    	rechargeMoney:{required:"请输入充值金额！" , number:"请输入只数字形式！"}
    	    },
    	    errorPlacement: function (error, element) { 
    	    	if($(element).closest('.one-fld').children().filter("div.error-div").length < 1) 
    			$(element).closest('.one-fld').append("<div class='error-div'></div>");	
    		$(element).closest('.one-fld').children().filter("div.error-div").append(error);
    	    },
    	    submitHandler: function(form){
    	    	var rechargeMoney = $("#rechargeMoney").val();
    	    	if(parseInt(rechargeMoney) <= 0){
    	    		alert("充值金额不正确！");
    	    		return;
    	    	}
    	    	$("#rechargeType").val(rechargeType);
    	    	$.post($(form).attr("action"),$(form).serialize(),function(data){
    	    		alert(data.content);
    	            if(data.state==1 ){
    	            	window.location.href = "${pageContext.request.contextPath}/customer/rechargeRecord";
    	            }
    	      	}, "json");
    	    }
    	});	
	});

	$("td").attr("valign", "top");
	$(".caption").css("padding-top", "10px");

	$("input").css( "font-size", "16px");
	
	$("#back").click( function(){
		goBack();
	});

	
	
</script>

<script>	
$(function(){


$('.ui.accordion').accordion();
$('.dropdown')
  .dropdown({
    // you can use any ui transition
    transition: 'drop',
	// action:'combo',
	onChange: function(value, text, $selectedItem) {
     console.log(value);
    }
  })
;

})

</script>
</html>