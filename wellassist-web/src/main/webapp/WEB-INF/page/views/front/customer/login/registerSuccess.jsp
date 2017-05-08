<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body style = "background-color:#F5F5F5;">
<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<div class="main-wrapper" style="margin-left: 0px;">
	<!-- navigation bar -->
	<div class="abovenavbar">
		<span style="margin-left: 5%;">WellAssist供应链系统</span>
		<span style="float: right; margin-right: 5%;"><a href="${pageContext.request.contextPath}/">返回首页</a></span>
	</div>
	<div align=center style="padding-bottom:1px;">
		<form id="mainData" method="POST" class="form form-aligned item-publish-form" action="${pageContext.request.contextPath}/login/LoginCtrl-registerNewCgdw" method="post" >
			<div style="width: 100%; margin-top: 16px; margin-bottom: 72px;" align = left>
				<span style="float: left; margin-left: 10%;">
					<img src="${pageContext.request.contextPath}/resources/wella/front/images/mainmark.png" style="margin-left: 20%;">
				</span>
				<div style="width: 100%;">
					<div class="label-heading3" style="padding-left: 30%; padding-bottom: 16px;">密码重置成功</div>
				</div>
			</div>
			<div align=left style="margin-top: 70px; margin-bottom: 48px; width: 800px; height: 80%; background: white; box-shadow: 0 -1px 6px rgba(0, 0, 0, 0.5); margin:0 auto;">
				<div class="box-in-level1 titlewithline" style="margin-left: 7%; padding-top:42px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:46px;">成功</div>
				<div style = "padding: 20px; text-align: center; font-size: 16px; padding-bottom: 50px;">
					<span class="font1">恭喜你！密码重置成功。</span>
                    <input type="hidden" id="userName" value="${userName}" />
                    <input type="hidden" id="userType" value="${userType}" />
                    <input type="hidden" id="userPass" value="${userPass}" />
                    <a href="#" onclick="gotoMain();">进入平台</a>
				</div>
			</div>
		</form>
	</div>
</div>

</body>
<script type="text/javascript">
	var userType='${userType}';
	function gotoMain(){
			var userName = $('#userName').val();
			var userType = $('#userType').val();
		    if(userType == "1"){
		    	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-login";
		    }else if(userType == "0"){
		    	window.location.href = "${pageContext.request.contextPath}/front/SellerLoginController-login";
		    }else if(userType == "2"){
		    	window.location.href = "${pageContext.request.contextPath}/front/fkf/FkfLoginCtrl-login";
		    }else if(userType == "3"){
		    	window.location.href = "${pageContext.request.contextPath}/front/sender/SenderLoginController-login";
		    }
	}
</script>
</html>

