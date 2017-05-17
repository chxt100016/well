<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<style>
	div#backimgpart{
		background:url('${pageContext.request.contextPath}/resources/wella/front/images/backobj.png') no-repeat;;
		padding-bottom:64px;
	}
</style>

<div class="abovenavbar" align=center >
	<div align=left style="width:1000px;">
		<span style="float:right;"><a href="${pageContext.request.contextPath}/">返回首页</a></span>
		<span>WellAssist供应链系统(买家端)</span>
	</div>
</div>

<div id="backimgpart" align=center>
	<div style="width: 1000px; margin:auto;">
		<div style="padding-top:100px;">
			<div style="padding-left:455px;padding-top:210px;padding-bottom:105px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/backobj01.png') no-repeat;">
				<div class="whiteframe" style="background:white;border:solid 1px #c0c0c0;width:400px;height:430px;font-size:24px;">
					<form id="loginForm" name="loginForm" method="post" action="${pageContext.request.contextPath}/login/in">
						<div style="margin:30px auto;"><input type="text" name="username" placeholder="手机/邮箱" style="margin-top:40px;width:286px;height:34px;font-size:16px;"></div>
						<div style="margin:auto;"><input type="password" name="password" placeholder="密码" style="width:286px;height:34px;font-size:16px;"></div>
						<div style="padding-top:26px;padding-left:52px;padding-right:46px;">
							<span style="float:left; font-size: 18px;"><a href="${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-getPass?userType=1">忘记密码</a></span>
							<span style="float:right; font-size: 18px;"><a href="${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-register">注册</a></span>
						</div>
						<div id="login" class="bluebutton" style="margin-top:80px;margin-left:52px;margin-right:46px;height:29px;padding-top:15px;font-size:20px;cursor: pointer;">登  录</div>
					</form>
				</div>
			</div>
		</div>
		<div style="margin-top:100px; font-size:15px;">供应链系统供应链系统供应链系统供应链系统</div>
	</div>
</div>
	
<script type="text/javascript">
	$("#login").click( function(){
		$.post($("#loginForm").attr("action"),$("#loginForm").serialize(),function(data){
    		data = $.parseJSON(data);
            //根据登录时的验，根据登录时的验证情况进行页面的跳转
            if(data.status=="1"){
            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerHomeCtrl-home";
            } else {
            	alert(data.content);
            }
      	})
      	.error(function(data){
      		alert("操作失败！")
      	});
	});
</script>

</body>
</html>