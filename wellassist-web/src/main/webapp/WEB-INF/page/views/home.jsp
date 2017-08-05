<%@ include file="header.jsp"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

        <html lang="en">

        <head>
            <title>维助供应链首页</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <%--<link href="home.css" rel="stylesheet">--%>
          <link rel="Stylesheet" type="text/css" href="<c:url value="resources/wella/front/css/index.css "/>" />
             <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
            <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>--%>
            <script src="<c:url value="resources/wella/front/js/typetype.min.js "/>"></script>

        </head>
        <body>
<div id="wrapper" class="home-page">
    <header>
        <div class="navbar">
            <div class="container">
                <a class="navbar-brand" href="index.html"><img src="${pageContext.request.contextPath}/resources/wella/front/images/logo.png" width="100" height="50" alt="logo"/></a>
                <div class="navbar-text">
                    <span>维助</span>
                    <span>供应链管理系统</span>
                </div>
                <ul class="navbar-nav">
                    <li class="active"><a href="index.html">首页</a></li> 
                    <li><a href="CompanyNews.html">公司动态</a></li>
                    <li><a href="Support.html">技术支持</a></li> 
                    <li><a href="AboutUs.html">关于我们</a></li>
                    <li><a href="ContactUs.html">联系我们</a></li>
                </ul>
                <div class="navbar-login">
                    <img src="${pageContext.request.contextPath}/resources/wella/front/images/user.jpg" /></span><span>登录</span>
                </div>
                <div class="navbar-loginwin">
                    <form method="post" id="loginForm" name="loginForm">
                        <img src="${pageContext.request.contextPath}/resources/wella/front/images/logo.png" width="100" height="50" alt="logo"/><br>
                        <input type="text" name="username" placeholder="请输入你的用户名"><br>
                        <input type="password" name="password" placeholder="请输入你的密码"><br>
                         <div  class="loginbtn"id="login" onclick="loginSubmit()">登录</div><br>
                        <span><a href="${pageContext.request.contextPath}/register/registerPage">注册</a></span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span><a href="${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-getPass?userType=1"  data-w="600">忘记密码?</a></span>
                    </form>
                </div>
            </div>
        </div>
    </header><!-- header end -->
    <banner>
        <div style="position:relative;">
            <div class="banner-img">
                <img src="${pageContext.request.contextPath}/resources/wella/front/images/banner.jpg" width="100%" height="auto" />
            </div>
            <div class="banner-brand">
                <ul>
                    <li>WellAssist</li>
                    <li>供应链系统</li>
                    <li>维助&ensp;专为绿色能源提供优质供应链服务</li>
                </ul>
            </div>
        </div>
        <div class="banner-text">
            <span>为您提供一站式解决方案</span>
            <hr width="200" style="color:#000;">
            <p>&nbsp;&nbsp; &nbsp; &nbsp; 维助提供安全可靠的服务、灵活强大的数据处理服务以及更快更稳定的内容分发服务，让企业抛开底层基础建设的枷锁，把更多的精力放在核心产品的迭代以及业务的运营上</p>
        </div>
    </banner><!-- banner end -->
    <footer>
        <div class="footer">
            <span>Copyright 2017 WellAssist, Inc. All rights reserved.</span>
        </div>
    </footer><!-- footer end -->
</div>
<script type="text/javascript">
$(".navbar-loginwin").hide();
$(".navbar-login").click(function(){
    $(".navbar-loginwin").show();
})
function loginSubmit() {
    $.post("${pageContext.request.contextPath}/login/in", $("#loginForm").serialize(), function(data) {
        var tempData = $.parseJSON(data);
        //根据登录时的验，根据登录时的验证情况进行页面的跳转
        //0-供货商, 1-客户方, 2-放款方， 3-物流方
        if (tempData.status == "1") {
            window.location.href = "${pageContext.request.contextPath}/login/success?type=" + tempData.type;
        } else {
            alert(tempData.content);
             window.location.href = "${pageContext.request.contextPath}/login/page";
        }
    })
    .error(function(data) {
        window.location.href = "${pageContext.request.contextPath}/login/page";
        alert("操作失败！")
    });
}
</script>
</body>
        </html>