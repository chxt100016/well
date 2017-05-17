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
		<form id="mainData" method="POST"  style = "text-align:center;" class="form form-aligned item-publish-form" action="${pageContext.request.contextPath}/login/LoginCtrl-registerNewCgdw" method="post" >
			<div style="width: 100%; margin-top: 16px; margin-bottom: 72px;" align = left>
				<span style="float: left; margin-left: 10%;">
					<img src="${pageContext.request.contextPath}/resources/wella/front/images/mainmark.png" style="margin-left: 20%;">
				</span>
				<div style="width: 100%;">
					<div class="label-heading3" style="padding-left: 30%; padding-bottom: 16px;">找密码</div>
				</div>
			</div>
			<div align=left style="margin-top: 70px; margin-bottom: 48px; width: 800px; height: 80%; background: white; box-shadow: 0 -1px 6px rgba(0, 0, 0, 0.5); margin:0 auto;">
				<div class="box-in-level1 titlewithline" style="margin-left: 7%; padding-top:42px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:46px;">用户信息</div>
				<table style="margin-left: 15%; margin-right: 15%; width: 100%;">
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">用户名：</span></td>
						<td style="width: 85%;">
							<input type="text" placeholder="请输入用户名" name="userName" id="userName" style="width: 50%;">
							<input type="hidden" class="form-control"  name="userType" id="userType" value="${userType}">
						</td>
					</tr>
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">邮箱：</span></td>
						<td style="width: 85%;">
							<input type="text" id="email" placeholder="请输入邮箱" name="email" style="width: 50%;">
						</td>
					</tr>
					<tr>
						<td class="caption"><span class="box-in-level2">验证码</span></td>
						<td>
							<input style="width: 20%; margin-right: 12px;" name="checkCode" id="checkCode"/>
							<input type="button" id="getCheckCode" value="获取验证码" />
						</td>
					</tr>
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">输入新密码：</span></td>
						<td style="width: 85%;">
							<input type="password" id = "password" placeholder="请输入新密码" name="password" style="width: 50%;">
						</td>
					</tr>
					<tr>
						<td class="caption" style="width: 15%;"><span class="box-in-level2">确认新密码：</span></td>
						<td style="width: 85%;">
							<input type="password" id="confirmPassword" placeholder="请确认你的新密码" name="confirmPassword" style="width: 50%;">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input id="nextstep" class="blue-button" type="submit" value="确认"></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
</body>

<script type="text/javascript">

	var userType = '${userType}';
	$(function() {
		$("#mainData").validate({
	        rules: {
	        	userName: "required",
	        	eMail: {required: true,email: true},
	        	checkCode: "required",
				password:{required:true,minlength:6},
				confirmPassword:{equalTo: "#password"}
	        },
	        messages: {
	        	userName: "请输入用户名",
	        	eMail: "请输入有效的电子邮件地址",
	        	checkCode: "请输入验证码",
				password:"密码长度不小于6",
                confirmPassword:"两次密码输入不一致"
	        },submitHandler: function(form) {  //通过之后回调
              	$.post("${pageContext.request.contextPath}/register/resetPassword",$("#mainData").serialize(),
					function(data) {
							if(data.state != 0) {
								var userName= $("#userName").val();
								window.location.href = "${pageContext.request.contextPath}/login/page";
							} else {
								alert(data.msg);
								return;
							}
						},
						'json'
				);
	        }
	    });		
	});
	<%--//发生验证号图片--%>
	<%--function changeCheckCode(){--%>
		<%--var append = "?clearCache=" + new Date().getTime() + "a" + Math.random();    --%>
		<%--$("#imageCode").attr("src", "${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-getVerifyImage" + append);--%>
		<%--$("#checkcode").val("");--%>
		<%--$("#checkcode").focus();--%>
	<%--}--%>

    /**
	 * 验证码倒计时
     * @type {number}
	 *
     */
    var wait=60;
    function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.value="获取验证码";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.value="重新发送(" + wait + ")";
            wait--;
            setTimeout(function() {
                time(o)
            },
                1000)
        }
    }
    function getCheckCode(object) {
        //先进行邮箱与用户是否对应
		$.ajax("${pageContext.request.contextPath}/register/checkAccount",{
            data:{
                email:$("#email").val(),
				phone:$("#userName").val()
            },
            success:function(data){
                //根据返回的状态判断是否发送
				var jsonObj = JSON.parse(data);
				alert(data);
                if(jsonObj.state==1){
                    $.ajax("${pageContext.request.contextPath}/register/sendCheckCode",{
                        data:{
                            email:$("#email").val()
                        },
                        success:function(data){
                            time(object);
                        },
                        error:function (data) {
                            alert(data);
                        }
                    });
                    time(object);
                }else {
                    alert("用户与邮箱不一致");
                }

            },
            error:function (data) {
                alert(data);
            }
        });
    }
    document.getElementById("getCheckCode").onclick=function(){getCheckCode(this);}

</script>
</html>

