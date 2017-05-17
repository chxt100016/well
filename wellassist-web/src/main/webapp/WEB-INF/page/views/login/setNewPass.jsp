<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body style = "background-color:#F5F5F5;">
<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<div class="main-wrapper" style="margin-left: 0px;">
	<!-- navigation bar -->
	<div class="abovenavbar">
		<span style="margin-left: 5%;">WellAssist供应链系统(找密码)</span>
		<span style="float: right; margin-right: 5%;"><a href="${pageContext.request.contextPath}/">返回首页</a></span>
	</div>
	<div align=center style="padding-bottom:1px;">
		<form id="mainData" method="POST" class="form form-aligned item-publish-form" action="" method="post" >
			<div style="width: 100%; margin-top: 16px; margin-bottom: 72px;" align = left>
				<span style="float: left; margin-left: 10%;">
					<img src="${pageContext.request.contextPath}/resources/wella/front/images/mainmark.png" style="margin-left: 20%;">
				</span>
				<div style="width: 100%;">
					<div class="label-heading3" style="padding-left: 30%; padding-bottom: 16px;">输入密码</div>
				</div>
			</div>
			<div align=left style="margin-top: 70px; margin-bottom: 48px; width: 800px; height: 80%; background: white; box-shadow: 0 -1px 6px rgba(0, 0, 0, 0.5); margin:0 auto;">
				<div class="box-in-level1 titlewithline" style="margin-left: 7%; padding-top:42px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:46px;">用户密码</div>
				<table style="margin-left: 15%; margin-right: 15%; width: 100%;">
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">用户名：</span></td>
						<td style="width: 85%;">
							<input type="hidden" class="form-control"  name="userName" id="userName"  value="${userName}">
		                    <input type="hidden" class="form-control"  name="userType" id="userType"  value="${userType}" >
						</td>
					</tr>
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">密码：</span></td>
						<td style="width: 85%;">
							<input type="password" placeholder="请输入新密码" name="userPass" id="userPass" style="width: 50%;">
						</td>
					</tr>
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">重复新密码：</span></td>
						<td style="width: 85%;">
							<input type="password" placeholder="请重复输入密码" name="confirmPass" id="confirmPass" style="width: 50%;">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input id="nextstep" class="blue-button" type="submit" value="确认重置密码"></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>

</body>
<script type="text/javascript">
	
	$(function() {
		$("#mainData").validate({
	        rules: {
	        	userPass: "required",
	        	confirmPass: {required: true, equalTo: "#userPass"}
	        },
	        messages: {
	        	userPass: "请输入密码",
	        	confirmPass: {  
                    required: "请输入确认密码",  
                    equalTo: "输入密码不一致"  
                }
	        },submitHandler: function(form) {  //通过之后回调
              	$.post("${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-setNewPass",$("#mainData").serialize(),
						function(data) {
							if(data.state == 1) {
								var userName = $("#userName").val();
								var userType = $("#userType").val();
								window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-goToSuccess?userName="+userName+"&userType="+userType;
							} else {
								alert(data.msg);
							}
						},
						'json'
				);
	        }
	    });		
	});
	//发生验证号图片
	function changeCheckCode(){
		var append = "?clearCache=" + new Date().getTime() + "a" + Math.random();    
		$("#imageCode").attr("src", "${pageContext.request.contextPath}/login/LoginCtrl-getVerifyImage" + append);
		$("#checkcode").val("");
		$("#checkcode").focus();
	}
</script>
</html>

