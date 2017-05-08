<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- styles -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<style>
	div#backimgpart{
		background:url('${pageContext.request.contextPath}/resources/wella/front/images/backobj01.png'),url('${pageContext.request.contextPath}/resources/wella/front/images/back01.png');
		background-repeat:no-repeat,no-repeat;
		background-size: 900px 600px, cover;
		background-position-x: 132px,0px;
		background-position-y: 54px,0px;
		height: 100%;
		padding-bottom:64px;display:flex;
	}
</style>

	<div class="abovenavbar" align=center >
		<div align=left style="width:1100px;">
			<span>WellAssist供应链系统</span>
			<span id="back" style="float:right;">返回首页</span>
		</div>
	</div>
	
	<!-- <div style="background:#3a9cf4; height: 100%; padding-bottom:64px;display:flex;" align=center> -->
	<div id="backimgpart" align=center>
		<div align=left style="width: 1000px;">
			<div class="whiteframe" style="background:white;border:solid 1px #c0c0c0;width:400px;height:430px;margin-left:580px;margin-top:232px;font-size:24px;">
				<div style="padding-top:56px;padding-left:52px;"><input type="text" name="userid" placeholder="手机/邮箱" style="width:286px;height:34px;font-size:16px;"></div>
				<div style="padding-top:26px;padding-left:52px;"><input type="password" name="password" placeholder="密码" style="width:286px;height:34px;font-size:16px;"></div>
				<div style="padding-top:26px;padding-left:52px;">
					<span  style="width:157px;height:34px;font-size:24px;display:inline-flex;">角色</span>
					<select id="actor" name="actor" style="width:143px;height:34px;font-size:16px;">
						<option value="buy" selected>买家
						<option value="sell">卖家
					</select>
				</div>
				<div style="padding-top:26px;padding-left:52px;padding-right:46px;">
					<span style="float:left; font-size: 24px;">忘记密码</span>
					<span id="register" class="textlink" style="float:right; font-size: 24px;">注册</span>
				</div>
				<div id="login" class="bluebutton" style="margin-top:46px;margin-left:52px;margin-right:46px;height:29px;padding-top:15px;font-size:20px;cursor: pointer;">登  录</div>
			</div>
		</div>
		
	</div>
	
<script type="text/javascript">

	$("#login").click( function(){
		var actor=$("select#actor").val();
		
		var userid = $('input[name=userid]').val();
		var userPassword = $('input[name=password]').val();
		
		if(!userid || !userPassword) {
			ShowWindowAlert("请输入用户名和密码！", " ", "", "确定", null);
			return;
		}
		
		$.post("${pageContext.request.contextPath}/front/require_login", 
				{userid:userid, userpass:userPassword, actor:actor},
				function(data) {
					if(data.state == 1) {
						if( actor=="buy"){
							window.location.href = "${pageContext.request.contextPath}/";
						}
						else if( actor=="sell") {
							window.location.href = "${pageContext.request.contextPath}/front/SellerHomeController-home";
						}
						
// 						if(isRemeber == 1) {
// 							storage.setItem("lcb_user_name",userName); 
// 						} else {
// 							storage.setItem("lcb_user_name",''); 
// 						}
						
// 						var regUrl = "";
// 						if(userType == 2) {
// 							regUrl = "${pageContext.request.contextPath}/login/GylRegCtrl-mainInfo";
// 						} else if(userType == 3) {
// 							regUrl = "${pageContext.request.contextPath}/login/FwzhRegCtrl-mainInfo";
// 						} else if(userType == 4) {
// 							regUrl = "${pageContext.request.contextPath}/login/WbsRegCtrl-mainInfo";
// 						}
						
						window.location.href = regUrl;
					} else {
						ShowWindowAlert(data.msg, " ", "", "确定", null);	
					}
				},
				'json'
		);
	});
		
// 		if( actor=="buy"){
// 			window.location.href = "${pageContext.request.contextPath}/front/customer/start";
// 		}
// 		else if( actor=="sell") {
// 			window.location.href = "${pageContext.request.contextPath}/front/SellerHomeController-home";
// 		}
				

	$("#register").click( function(){
		var actor=$("select#actor").val();
		if( actor=="buy"){
			window.location.href = "${pageContext.request.contextPath}/front/customer/register";
		}
		else if( actor=="sell") {
			window.location.href = "${pageContext.request.contextPath}/front/SellerLoginController-register";
		}
				
	});

	$("#back").click( function(){
		history.back();
	});
	
</script>


</body>
</html>