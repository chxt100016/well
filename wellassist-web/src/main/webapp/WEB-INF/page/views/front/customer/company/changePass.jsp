<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>"></script>

<style>
	div.error{
		width: 200px;
    	display: inline-block;
    	padding-left: 42px;
	}
</style>

</head>

<div style="min-height: 990px;width: 100%;">
	<div style="margin:40px 0 0 210px;">
		<div id = "content-rect" style="width:90%;">

			<div style="margin-top:16px;">
				<div style="font-size:15px;font-weight:600;border-bottom:1px solid #d0d0d0;padding-bottom:10px;">修改登录密码</div>
				<form action="" id="frm_pass" method="post">
					<div style="font-size:14px;padding-left:8px;padding-top:16px;">
						<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">当前密码</span>
						<div class="ui input">
							<input type="password" name="oldpass" id="oldpass" style="width:40%;">
						</div>
					</div>
					<div style="font-size:14px;padding-left:8px;padding-top:12px;">
						<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">设置新密码</span>
						<div class="ui input">
							<input type="password" name="newpass"  id="newpass" style="width:40%;">
						</div>
					</div>
					<div style="font-size:14px;padding-left:8px;padding-top:12px;">
						<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">确认新密码</span>
						<div class="ui input">
							<input type="password" name="confirm"  id="confirm" style="width:40%;">
						</div>
					</div>
			
					<div style="margin-bottom: 32px;margin-top: 32px;margin-left: 11%;">
						<input type="submit" id="submitloginpass" class=" ui primary button" value="确定">
						<input type="reset" id="reset" class=" ui button" value="重置">
					</div>
				</form>
			</div>

			<div style="margin-top:16px;">
				<div style="font-size:15px;font-weight:600;border-bottom:1px solid #d0d0d0;padding-bottom:10px;">修改支付密码</div>
				
				<form action="" id="frm_pay_pass" method="post">
					<div style="font-size:14px;padding-left:8px;padding-top:16px;">
						<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">当前密码</span>
						<div class="ui input">
							<input type="password" name="payoldpass" id="payoldpass" style="width:40%;">
						</div>
					</div>
					<div style="font-size:14px;padding-left:8px;padding-top:12px;">
						<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">设置新密码</span>
						<div class="ui input">
							<input type="password" name="paynewpass" id="paynewpass"  style="width:40%;">
						</div>
					</div>
					<div style="font-size:14px;padding-left:8px;padding-top:12px;">
						<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">确认新密码</span>
						<div class="ui input">
							<input type="password" name="payconfirm"  id="payconfirm" style="width:40%;">
						</div>
					</div>
			
					<div style="margin-bottom: 32px;margin-top: 32px;margin-left: 11%;">
						<!-- <span id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;" align=center>确认下单</span> -->
						<input type="submit" id="submitpaypass" class=" ui primary button" value="确定">
						<input type="reset" id="reset" class=" ui button" value="重置">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("select").height(24);
 	$("select").css("margin-top", "-6px");
	$("#companyicon").height($("#companyicon").width());
	$("#icon").height($("#companyicon").innerHeight()-16);
	$("#icon").width($("#companyicon").innerWidth()-16);
	// $("input[type=password]").css("font-size", "16px");
	
	
	$("#frm_pass").validate({
        rules: {
        	oldpass: "required",
        	newpass: "required",
        	confirm: {required: true, equalTo: "#newpass"}
        },
        messages: {
        	oldpass: "请输入原来密码",
        	newpass: "请输入密码",
        	confirm: {  
                required: "请输入确认密码",  
                equalTo: "输入密码不一致"  
            }
        },
	    submitHandler: function(form){
	    	var oldPass = $("#oldpass").val();
	    	$.post("${pageContext.request.contextPath}/front/seller/company/SellerCompanyController-checkOrgPass", {oldPass:oldPass},	function(data) {
				if(data.state == 1) {
					var act_url = "${pageContext.request.contextPath}/front/seller/company/SellerCompanyController-updateNewPass";
			    	$.post(act_url,$(form).serialize(),function(data){
			    		data = $.parseJSON(data);
			    		if(data.state==1){
			            	alert("保存成功!");
			            }else{
			            	alert(data.content);
			            }
			      	})
			      	.error(function(data){
			      		alert("保存失败！")
			      	});
				}else{
					alert("原来密码不正确!");
				}
			}, 'json');
	    	
	    },
		errorElement: "div",
		errorClass: "error"
	});
	
	
	$("#frm_pay_pass").validate({
        rules: {
        	payoldpass: "required",
        	paynewpass: "required",
        	payconfirm: {required: true, equalTo: "#paynewpass"}
        },
        messages: {
        	payoldpass: "请输入原来密码",
        	paynewpass: "请输入密码",
        	payconfirm: {  
                required: "请输入确认密码",  
                equalTo: "输入密码不一致"  
            }
        },
	    submitHandler: function(form){
	    	var oldPass = $("#payoldpass").val();
	    	$.post("${pageContext.request.contextPath}/front/customer/CompanyController-checkPayOrgPass", {oldPass:oldPass},	function(data) {
				if(data.state == 1) {
					var act_url = "${pageContext.request.contextPath}/front/customer/CompanyController-updatePayNewPass";
			    	$.post(act_url,$(form).serialize(),function(data){
			    		data = $.parseJSON(data);
			    		if(data.state==1){
			            	alert("保存成功!");
			            }else{
			            	alert(data.content);
			            }
			      	})
			      	.error(function(data){
			      		alert("保存失败！")
			      	});
				}else{
					alert("原来密码不正确!");
				}
			}, 'json');
	    	
	    },
		errorElement: "div",
		errorClass: "error"
	});

</script>
<%@ include file="../footer.jsp"%>