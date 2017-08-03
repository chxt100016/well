<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <html lang="en">

        <head>
            <title>维助供应链登录</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <%--<link href="home.css" rel="stylesheet">--%>
            <link rel="Stylesheet" type="text/css" href="<c:url value="/resources/wella/front/css/home.css "/>" />
            <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">--%>
            <%--<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>--%>
            <%--<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>--%>


        </head>

        <body>


            <div class="masthead">

                <div class="container" style="height:833px">
                    <div id="header">
                        <div id="header_inner">
                            <div class="pure-menu pure-menu-horizontal pure-menu-open">
                                <ul>
                                    <%--<li class="pure-dropdown hide-xs hide-sm hide-md"><a href="">登录 <i class="arrow"></i></a>

                                    </li>
                                    <li class="hide-xs hide-sm hide-md mum_inc"><a href="">注册</a></li>--%>


                                    <li class="toggle_mobile_search pure-dropdown hide-md hide-lg hide-xl"><a><i class="icon icon_menu_loupe"></i></a></li>

                                </ul>
                            </div>
                            <a id="logo" href="/" class="hover_opacity"> WellAssist供应链系统</a>

                        </div>
                    </div>
                    <div class="introduction">
                        <div id="sign_in_out" style="padding:10px">
                            <div class="window" style="padding:0;margin:5% auto 0; width:640px">
                                <div style="padding:20px 25px">
                                    <h1 style="color: #555;text-align:left;font-weight: normal;">登录</h1>

                                    <div class=""> </div>
                                    <div class="">
                                        <form class="right_side pure-form"  method="post" id="loginForm" name="loginForm">

                                            <div class="ui  input login-input">
                                                <input type="text" placeholder="用户名、电子邮件或者手机号" id="username" maxlength="300" name="username">
                                            </div>
                                            <p>
                                                <div class="ui  input login-input">
                                                    <input type="password" name="password"  placeholder="密码" id="passowrd" maxlength="300">
                                                </div>
                                                <input type="hidden" name="next" value="">
                                                <p>

                                        </form>
                                        <div><input style="text-align:center;width:300px" class="ui positive button"  value="登录" id="login" onclick="loginSubmit()"></div>
                                    </div>

                                </div>
                                <div style="margin:1px;padding:20px 25px;background:#f7f7f7;font-size:13px;line-height:1.7;color: #555;">
                                    <div class="tx-al-lf">
                                        <div class="">
                                            <div style="margin-right:25px" class="no-margin-right-md">
                                                <br><a href="${pageContext.request.contextPath}/register/registerPage">注册</a>
                                            </div>
                                        </div>
                                        <div class="">
                                            <div class="right_side" style="border-color:transparent !important;padding-top:0 !important;margin:0 !important">
                                                <a href="${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-getPass?userType=1"  data-w="600">忘记密码</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!--<div class="content">
            <div class="main-title">为您提供一站式解决方案</div>
            <div class="main-description">维助提供安全可靠的服务、灵活强大的数据处理服务以及更快更稳定的内容分发服务，让企业抛开底层基础建设的枷锁，把更多的精力放在核心产品的迭代以及业务的运营上</div>
            <br> <br> <br> <br>
        </div>-->
                <div class="footer-bottom">
                    <div class="footer-bottom-inset">
                        <div class="w-row">
                            <div class="w-col w-col-6">
                                <div>Copyright 2017 WellAssist, Inc. All rights reserved.</div>
                            </div>
                            <div class="w-col w-col-6">
                                <h5 class="footer-number">联系方式：&nbsp;<strong class="footer-bold"><a href="">0571-86057333</a></strong></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>
        <script type="text/javascript">
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

        </html>