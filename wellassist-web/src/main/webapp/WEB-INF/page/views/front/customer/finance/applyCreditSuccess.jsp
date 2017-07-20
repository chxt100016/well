<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title></title>
    <%--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>--%>
    <style>
        #parent {
            padding: 5% 0;
        }

        #child {
            padding: 10% 0;
        }
    </style>
</head>

<body>
    <div class="ui container segment" id="app1">
        <div id="parent">
            <div id="child">
                <p class="ui center aligned header"> <i class="check square icon"></i>申请成功</p>
                <p class="ui center aligned header">
                    请等待审核
                </p>
                <br><br>
                  <div class="ui horizontal divider">and </div>
                <p class="ui center aligned ">
                    <br><br>
                    <button class="ui pink button" style="display:block;margin:0px auto" onclick="javascript:window.location.href='${pageContext.request.contextPath}/customer/creditAccount';">返回财务中心</button>
                </p>
            </div>
        </div>
    </div>
</body>

</html>