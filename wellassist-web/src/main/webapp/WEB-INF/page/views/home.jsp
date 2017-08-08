<%@ include file="header.jsp"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

        <html lang="en">

        <head>
            <title>维助供应链首页</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <%--<link href="home.css" rel="stylesheet">--%>
          <link rel="Stylesheet" type="text/css" href="<c:url value="resources/wella/front/css/index.css "/>" />
          <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
             <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
            <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>--%>
            <script src="<c:url value="resources/wella/front/js/typetype.min.js "/>"></script>

        </head>
        <body>
<div class="ui horizontal list">
    <div class="item">
        <img class="ui image" href="index.html" src="img/logo.png" width="110" height="auto">
    </div>
    <div class="item" style="margin:0 30px 0 485px;">
        <div class="ui top secondary pointing menu">
            <a class="active item" data-tab="first">首页</a>
            <a class="item" data-tab="second">公司动态</a>
            <a class="item" data-tab="third">技术支持</a>
            <a class="item" data-tab="fouth">关于我们</a>
            <a class="item" data-tab="fifth">联系我们</a>
        </div>
    </div>
    <div class="item" id="loginbox">
        <span style="cursor:pointer;"><i class="user icon" style="color:#1a84cc; font-size:17px;"></i><span style="font-size:15px;">登录</span></span>
    </div>
</div>
<div class="ui bottom active tab segment" data-tab="first">
    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
        </ol>   
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="img/slide1.jpg" alt="First slide">
                <div class="flex-caption">
                    <h3>维助供应链</h3> 
                </div> 
            </div>
            <div class="item">
                <img src="img/slide2.jpg" alt="Second slide">
                <div class="flex-caption">
                    <h3>专为清洁能源提供优质服务</h3>  
                </div>
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel" 
            data-slide="prev">&lsaquo;
        </a>
        <a class="carousel-control right" href="#myCarousel" 
            data-slide="next">&rsaquo;
        </a>
    </div>
    <section class="jumbobox">
        <div class="container">
            <div class="row">
                <div class="col-lg-12" style="color:#333;font-size:17px;">
                    <div><h1>我们的服务</h1>维助为您提供一站式解决方案，安全可靠的服务、灵活强大的数据处理服务以及更快更稳定的内容分发服务，让企业抛开底层基础建设的枷锁，把更多的精力放在核心产品的迭代以及业务的运营上</div>                 
                </div>
            </div>
        </div>
    </section>
</div>
<div class="ui bottom tab segment" data-tab="second">
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">公司动态</h2>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="ui bottom tab segment" data-tab="third">
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">技术支持</h2>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="ui bottom tab segment" data-tab="fouth">
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">关于我们</h2>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="ui bottom tab segment" data-tab="fifth">
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">联系我们</h2>
                </div>
            </div>
        </div>
    </section>
    <section id="content" style="margin-bottom:50px;">
        <div class="row">           
            <div class="col-lg-6">
                <img src="img/about-us.jpg" width="600" height="700" />
            </div>
            <div class="col-lg-6">
                <iframe src="http://www.map-generator.org/ca443ddb-2746-4679-918b-a9715ff83784/iframe-map.aspx" scrolling="no" width="600px" height="550px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe><br>
                <div style="margin-top: 20px;font-size:15px;">
                    <h3>浙江维助供应链管理有限公司</h3>
                    <i class="marker icon"></i><span>地址：杭州市江干区九环路9号浙江省国家大学科技园D幢8楼801室</span><br>
                    <i class="volume control phone icon"></i><span>电话：0571-12345678</span><br>
                    <i class="mail outline icon"></i><span>邮箱：xxx@abcde.com</span><br>
                </div>              
            </div>
        </div>
    </section>
</div>
<footer>
    <div class="sub-footer">
        <span>Copyright &copy; 2017.Company name All rights reserved.</span>
    </div>
</footer>
<div id="mask"></div>
<div class="hidebox">
    <i class="remove icon" id="cancel" style="float: right;
    margin: -35px 5px 50px 0;cursor:pointer;"></i>
    <form class="ui form" method="post" id="loginForm" name="loginForm">
        <div class="field" style="text-align:center;">
            <img src="img/logo.png" width="100" height="50" alt="logo"/>
        </div>
        <div class="field">
            <input placeholder="请输入你的用户名" name="username" type="text">
        </div>
        <div class="field">
            <input placeholder="请输入你的密码" name="password" type="password">
        </div>
        <div class="ui primary submit button" style="display:list-item;" id="login" onclick="loginSubmit()">登录</div><br>
        <span><a href="${pageContext.request.contextPath}/register/registerPage" onclick="regist();">注册</a></span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span><a href="${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-getPass?userType=1"  data-w="600">忘记密码?</a></span>
    </form>
</div>
<script type="text/javascript">
$('.menu .item').tab();
    $('.hidebox').hide();
    $('#loginbox').click(function(){
        $('.hidebox').show();
        $("#mask").css("height",$(document).height());     
        $("#mask").css("width",$(document).width());     
        $("#mask").show(); 
    });
    $('#cancel').click(function(){
        $('.hidebox').hide();
        $("#mask").hide(); 
    });
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
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
        </html>