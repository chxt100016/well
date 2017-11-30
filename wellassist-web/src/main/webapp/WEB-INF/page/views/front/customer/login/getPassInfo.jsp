<%@ include file="../../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
        <html lang="en">

        <head>
            <title>WellAssist供应链系统(找密码)</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" type="text/css" href="<c:url value="/resources/wella/front/css/home.css "/>">
            
            
          <style>
          .th-input {
       width: 400px;
      }
      .ui.labeled.input>.label{
          width:111px;
      }
          </style>

        </head>

        <body>


            <div class="masthead">

                <div class="container" style="height:833px">
                    <div id="header">
                        <div id="header_inner">
                            <div class="pure-menu pure-menu-horizontal pure-menu-open">
                                <ul>
                                    <li class="pure-dropdown hide-xs hide-sm hide-md"><a href="">登录 <i class="arrow"></i></a>

                                    </li>
                                    <li class="hide-xs hide-sm hide-md mum_inc"><a href="">注册</a></li>


                                    <li class="toggle_mobile_search pure-dropdown hide-md hide-lg hide-xl"><a><i class="icon icon_menu_loupe"></i></a></li>

                                </ul>
                            </div>
                            <a id="logo" href="#" class="hover_opacity"> WellAssist供应链系统</a>

                        </div>
                    </div>
                    <div class="introduction">
                        <div id="sign_in_out" style="padding:10px">
                            <div class="window" style="padding:0;margin:5% auto 0; width:640px">
                                <div style="padding:20px 25px">
                                    <h1 style="color: #555;text-align:left;font-weight: normal;">找回密码</h1>

                                    <div class=""> </div>
                                    <div class="">
                                        <form id="mainData" class="right_side pure-form" action="." method="post">

                                            <div class="ui labeled input th-input ">
                                                <div class="ui label label-hr">用户名：</div>
                                                <input type="text" name="userName" id="userName" placeholder="请输入用户名/手机/邮箱" class="max_text">
                                            </div>
                                            <p>
                                                <div class="ui labeled input  th-input">
                                                    <div class="ui label label-hr">邮箱：</div>
                                                    <input type="email" id="email" placeholder="请输入邮箱" name="email" class="max_text">
                                                </div>
                                                <p>
                                                    <div class="ui labeled input  th-input">
                                                        <div class="ui label label-hr">验证码：</div>
                                                        <input type="text" name="checkCode" id="checkCode" placeholder="请输入验证码" class="max_text" style="width:50%">
                                                        <button href="#" class="ui  icon button teal" id="getCheckCode" style="height:41px">发送</button>
                                                    </div>
                                                    <p>
                                                        <div class="ui labeled input th-input ">
                                                            <div class="ui label label-hr">输入新密码:</div>
                                                            <input type="password" id="password" placeholder="请输入新密码" name="password" class="max_text">
                                                        </div>
                                                        <p>
                                                            <div class="ui labeled input th-input ">
                                                                <div class="ui label label-hr">确认新密码:</div>
                                                                <input type="password" id="confirmPassword" placeholder="请确认你的新密码" name="confirmPassword" class="max_text">
                                                            </div>
                                                            <p>
                                                                <div><input style="text-align:center;width:300px" class="ui positive button" type="submit" value="确认"></div>
                                        </form>
                                    </div>

                                </div>
                                <div style="margin:1px;padding:20px 25px;background:#f7f7f7;font-size:13px;line-height:1.7;color: #555;">
                                    <div class="tx-al-lf">
                                        <div class="">
                                            <div style="margin-right:25px" class="no-margin-right-md">
                                                <br><a href="${pageContext.request.contextPath}/">返回首页</a>
                                            </div>
                                        </div>
                                        <div class="">
                                            <div class="right_side" style="border-color:transparent !important;padding-top:0 !important;margin:0 !important">
                                                <a href="${pageContext.request.contextPath}/login/page"data-w="600">登录</a>
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
                                <h5 class="footer-number">Want to talk to a human?&nbsp;<strong class="footer-bold"><a href="">121124</a></strong></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>
        <script type="text/javascript">
            var userType = '${userType}';
            $(function() {
                $("#mainData").validate({
                    rules: {
                        userName: "required",
                        eMail: {
                            required: true,
                            email: true
                        },
                        checkCode: "required",
                        password: {
                            required: true,
                            minlength: 6
                        },
                        confirmPassword: {
                            equalTo: "#password"
                        }
                    },
                    messages: {
                        userName: "请输入用户名",
                        eMail: "请输入有效的电子邮件地址",
                        checkCode: "请输入验证码",
                        password: "密码长度不小于6",
                        confirmPassword: "两次密码输入不一致"
                    },
                    submitHandler: function(form) { //通过之后回调
                        $.post("${pageContext.request.contextPath}/register/resetPassword", $("#mainData").serialize(),
                            function(data) {
                                if (data.state != 0) {
                                    alert("密码修改成功!");
                                    window.location.href = "${pageContext.request.contextPath}/";
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
            var wait = 60;

            function time(o) {
                if (wait == 0) {
                    o.removeAttribute("disabled");
                    o.value = "获取验证码";
                    wait = 60;
                } else {
                    o.setAttribute("disabled", true);
                    o.value = "重新发送(" + wait + ")";
                    wait--;
                    setTimeout(function() {
                            time(o)
                        },
                        1000)
                }
            }

            function getCheckCode(object) {
                //先进行邮箱与用户是否对应
                $.ajax("${pageContext.request.contextPath}/register/checkAccount", {
                    data: {
                        email: $("#email").val(),
                        userName: $("#userName").val()
                    },
                    success: function(data) {
                        //根据返回的状态判断是否发送
                        var jsonObj = JSON.parse(data);
                        if (jsonObj.state == 1) {
                            $.ajax("${pageContext.request.contextPath}/register/sendCheckCode", {
                                data: {
                                    email: $("#email").val()
                                },
                                success: function(data) {
                                    time(object);
                                },
                                error: function(data) {
                                    alert(data);
                                }
                            });
                            time(object);
                        } else {
                            alert("用户与邮箱不一致");
                        }

                    },
                    error: function(data) {
                        alert(data);
                    }
                });
            }
            document.getElementById("getCheckCode").onclick = function() {
                getCheckCode(this);
            }
        </script>

        </html>