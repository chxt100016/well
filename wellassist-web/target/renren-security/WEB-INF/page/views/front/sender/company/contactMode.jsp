<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>">
</script>

<style>
div.error{
	width: 200px;
   	display: inline-block;
   	padding-left: 42px;
}
</style>

</head>
<div id = "content-rect"  style="text-align:left;">

	<div style="border:solid 1px #d0d0d0;">
		<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:18px;color:#0557ab;font-weight: bold;">联系方式</div>
		
		<form action="#" method="post">
			<div style="font-size:14px;padding-left:8px;padding-top:32px;">
				<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">联系人</span>
				<input type="text" name="companyLxr" id="companyLxr" placeholder="请输入联系人" value="${myInfo.companyLxr}">
			</div>
			<div style="font-size:14px;padding-left:8px;padding-top:24px;">
				<span style="display:inline-block;width:10%;text-align:right;margin-right:16px;">联系手机号码</span>
				<input type="text" name="companyLxrPhone" id="companyLxrPhone" placeholder="请输入联系手机号码"  value="${myInfo.companyLxrPhone}">
			</div>
			
			<div style="margin-bottom: 32px;margin-top: 32px;margin-left: 11%;">
				<input type="submit" id="submit" class="bluebutton" style="padding-left: 24px;padding-right: 24px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="保存">
			</div>
		</form>
	</div>


</div>
<script type="text/javascript">
	$("#companyicon").height($("#companyicon").width());
	$("#icon").height($("#companyicon").innerHeight()-16);
	$("#icon").width($("#companyicon").innerWidth()-16);
	$("input[type=text]").css("font-size", "16px");
	
	
	$("form").validate({
        rules: {
        	companyLxr: "required",
        	companyLxrPhone: "required"
        },
        messages: {
        	companyLxr: "请输入联系人！",
        	companyLxrPhone: "请输入联系手机号码！"
        },
	    submitHandler: function(form){
	    	var act_url = "${pageContext.request.contextPath}/front/sender/CompanyController-contactModeUpdate";
	    	$(form).attr("action", act_url);
			$.post($(form).attr("action"),$(form).serialize(),function(data){
	    		data = $.parseJSON(data);
	    		if(data.state==1){
	            	alert("保存成功!");
	            }else{
	            	alert(data.content);
	            }
	      	})
	      	.error(function(data){
	      		alert("操作失败！")
	      	});
	    	
	    },
		errorElement: "div",
		errorClass: "error"
	});

</script>
<%@ include file="../footer.jsp"%>