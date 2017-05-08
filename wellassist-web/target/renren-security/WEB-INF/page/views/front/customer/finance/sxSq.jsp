<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<style>
	label.error{font-size: 14px;margin-bottom: 6px;}
	td{padding-right: 8px;}
	td.caption{padding-top:10px;}
	tr{height:64px;}
</style>

</head>
<body style="margin:0; padding:0; background: #F5F5F5;">
	<div style = "width:1000px; overflow:auto;margin:0px auto; margin-top:30px;">
		<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
			
			<div style="text-align:center;font-size:24px;">申请提额</div>
		</div>

		<form id = "infoForm" action="${pageContext.request.contextPath}/front/customer/FinanceControlle-addSx" method="post">
			<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:16px;background-color:white;border-bottom: 1px solid #aaa;">
				<div style="height: 48px;"></div>
				<table style="padding-left:4%;padding-right:4%;width:92%;">
					<tr>
						<td class="caption" style="width:20%;text-align:right;">当前额度:</td>
						<td class="caption" style="width:30%;color:red;font-weight:bold;">
							<c:if test = "${creditDengji == '0'}">
								不可能申请， 请联系与管理员
							</c:if>
							<c:if test = "${creditDengji != '0'}">
									${minMoney}	&nbsp;&nbsp;~&nbsp;&nbsp;${maxMoney}&nbsp;&nbsp元
							</c:if>		
						</td>
						<td></td>
					</tr>
					<c:if test = "${creditDengji != '0'}">		
						<tr>
							<td class="caption" style="width:20%;text-align:right;">提额:</td>
							<td>
								<div class = "one-fld">
									<input type="text" name="creditMoney" id="creditMoney" style="width:90%;">
								</div>
							</td>
							<td class="caption" style="text-align:left;">元</td>
						</tr>
					</c:if>
				</table>
				<div align=center style="margin-top: 24px;margin-bottom: 24px;">
					<input type="button" id="" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回" onclick="history.back();">
					<c:if test = "${creditDengji != '0'}">				
						<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认提交">
					</c:if>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">

	$("input").css("font-size", "16px");
	$("td").attr("valign", "top");
	
	$("#back").click( function(){
		history.back();
	});
	
	
	// 初始化函数
	$(function(){
		// validation检查	
    	$("#infoForm").validate({
    	    rules: {
    	    	creditMoney:{required: true, number:true}
    	    },
    	    messages: {
    	    	creditMoney:{required:"请输入提额金额！" , number:"请输入只数字形式！"}
    	    },
    	    errorPlacement: function (error, element) { 
    	    	if($(element).closest('.one-fld').children().filter("div.error-div").length < 1) 
    			$(element).closest('.one-fld').append("<div class='error-div'></div>");	
    		$(element).closest('.one-fld').children().filter("div.error-div").append(error);
    	    },
    	    submitHandler: function(form){
    	    	var creditMoney = $("#creditMoney").val();
    	    	if(parseInt(creditMoney) <= 0){
    	    		alert("提额金额不正确！");
    	    		return;
    	    	}
    	    	if(parseInt(creditMoney) >= parseInt('${maxMoney}')){
    	    		alert("提额金额不能超过当前额度");
    	    		return;
    	    	}
    	    	if(!confirm("您确认操作吗?")) return; 
    	    	$.post($(form).attr("action"),$(form).serialize(),function(data){
    	    		if(data.state==1 ){
    	            	ShowWindowAlert_new ("提示","操作成功！","确定",function(){
   	            			window.location.href = "${pageContext.request.contextPath}/front/customer/FinanceController-sxSqList";		
   	            		});
   	            	}else{
   	            		alert(data.content);
    	            }
    	      	}, "json");
    	    }
    	});	
	});
	
	
	
</script>
</html>