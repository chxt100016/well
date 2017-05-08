<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    
    <title>供应商平台登录</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/altframe/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/altframe/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/altframe/plugins/iCheck/square/blue.css">

	<script src="${pageContext.request.contextPath}/resources/houtai/js/jquery-1.8.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/altframe/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/altframe/plugins/iCheck/icheck.min.js"></script>
    <!--[if lt IE 9]>
        <script src="<c:url value="resources/common/js/html5shiv.min.js"/>"></script> 
        <script src="<c:url value="resources/common/js//respond.min.js"/>"></script> 
    <![endif]-->
  </head>
  <body class="hold-transition login-page">
    	<div class="login-box">
      		<div class="login-logo">
        		<a href="javascript:void(0);"><b>平台登录</b></a>
      		</div>
      		<div class="login-box-body">
        		<p class="login-box-msg">请输入登录信息</p>
        		<form method="post" style = "margin-bottom:30px;">
          			<div class="form-group has-feedback">
            			<input type="email" id = "name" class="form-control" placeholder="账号">
            			<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          			</div>
          			<div class="form-group has-feedback">
            			<input type="password" id = "pass" class="form-control" placeholder="密码" />
            			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
          			</div>
          			<div class="row">
            			<div class="col-xs-12">
              				<button type="button" id="login"  class="btn btn-primary btn-block btn-flat">登录</button>
            			</div>
          			</div>
        		</form>
      		</div>
    	</div>
    
	    <script>
	    	$(function () { 
		    	$('#name').keydown(function(e) {
		  			enterKey(e);
		  		});
		  		
		  		$('#pass').keydown(function(e) {
		  			enterKey(e);
		  		});
		  		
		  		$('#login').click(function() {
					var userName = $('#name').val();
					var userPassword = $('#pass').val();
					
					if(!userName || !userPassword) {
						alert("请输入用户名和密码！");
						//ShowWindowAlert("请输入用户名和密码！", " ", "", "确定", null);
						return;
					}
					
					$.post("${pageContext.request.contextPath}/ht/Login/LoginCtrl-login", {'userName':userName, 'userPassword':userPassword}, function(data) {
						if(data.state == 1) { 
							window.location.href = "${pageContext.request.contextPath}/main";
						} else {
							alert(data.msg); //ShowWindowAlert(data.msg, " ", "", "确定", null);	
						}
					}, 'json');
				});
	    	});
	    	
		    function enterKey(e) {
		  		if(e.keyCode == 13) {
		  			if($('#name').val() == "") {
		  				$('#name').focus();
		  				return;
		  			} else if($('#pass').val() == "") {
		  				$('#pass').focus();
		  				return;
		  			}else {
		  				$("#login").click();
		  			}
		  		}
		  	 }
	    </script>
  </body>
</html>
