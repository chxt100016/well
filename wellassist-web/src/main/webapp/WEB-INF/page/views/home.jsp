<%@ include file="header.jsp"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

        <html lang="en">

        <head>
            <title>维助供应链首页</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <%--<link href="home.css" rel="stylesheet">--%>
          <link rel="Stylesheet" type="text/css" href="<c:url value="resources/wella/front/css/home.css "/>" />
             <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
            <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>--%>
            <script src="<c:url value="resources/wella/front/js/typetype.min.js "/>"></script>

        </head>

        <body>
            <div class="masthead">

                <div class="container" style="height:833px">
                    <div id="header">
                        <div id="header_inner">
                            <div class="pure-menu pure-menu-horizontal pure-menu-open">
                                <ul>
                                    <li class="pure-dropdown hide-xs hide-sm hide-md"><a href="${pageContext.request.contextPath}/login/page" onclick="">登录 <i class="arrow"></i></a>

                                    </li>
                                    <li class="hide-xs hide-sm hide-md mum_inc"><a href="${pageContext.request.contextPath}/register/registerPage">注册</a></li>


                                    <li class="toggle_mobile_search pure-dropdown hide-md hide-lg hide-xl"><a><i class="icon icon_menu_loupe"></i></a></li>

                                </ul>
                            </div>
                            <a id="logo" href="/" class="hover_opacity"> WellAssist供应链系统 </a>

                        </div>
                    </div>
                    <div class="introduction">
                        <a class=""> </a>
                        <h1 class="ui inverted header">
                            <span class="library" id="bigtype" >WellAssist 供应链系统 </span>
                            <span class="tagline" id="type2"> 维助 专为油气厂商提供优质供应链服务</span>
                        </h1>
                        <div class="ui hidden divider"></div>

                        <a href="${pageContext.request.contextPath}/register/registerPage" class="ui huge inverted download button">成为会员 </a>
                        <a href="#" class="ui huge inverted basic button">了解更多</a>
                    </div>

                </div>
                <div class="content">
                    <div class="main-title">为您提供一站式解决方案</div>
                    <div class="main-description">维助提供安全可靠的服务、灵活强大的数据处理服务以及更快更稳定的内容分发服务，让企业抛开底层基础建设的枷锁，把更多的精力放在核心产品的迭代以及业务的运营上</div>
                    <br> <br> <br> <br>
                </div>
                <div class="footer-bottom">
                    <div class="footer-bottom-inset">
                        <div class="w-row">
                            <div class="w-col w-col-6">
                                <div>Copyright 2017 WellAssist, Inc. All rights reserved.</div>
                            </div>
                            <div class="w-col w-col-6">
                                <h5 class="footer-number">W联系方式：&nbsp;<strong class="footer-bold"><a href="">0571-86057333</a></strong></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>
        <script>
            // 功能函数	
            function toURL(action, id) {
                var url = "";
                if (action == 'login') { // 抢单点击
                    url = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-qdPage?vehicleTrans=" + id;
                    window.location.href = url;
                } else if (action == 'goto') {
                    window.open(id);
                }
            }

            function login() {
                url = "${pageContext.request.contextPath}/login/page";
                window.location.href = url;
            }

            function regist() {
                url = "${pageContext.request.contextPath}/register/registerPage";
                window.location.href = url;
            }

            
        </script>
        <script>    
           $(function() {
            function type_in() {
            // console.log("in!");
            $('#bigtype').typetype(
                'WellAssist 供应链系统', {
                    e:0, // error rate. (use e=0 for perfect typing)
                    t: 50, // interval between keypresses
                    keypress: function() {
                        // called after every keypress (this may be an erroneous keypress!)
                    },
                    callback: function() {
                        $("#type2").typetype("维助 专为油气厂商提供优质供应链服务");
                    }
                }
            )
        };
        // type_in();

    })</script>

        </html>