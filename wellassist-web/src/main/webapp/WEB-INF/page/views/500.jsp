<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<meta http-equiv="X-UA-Compatible" content="ie=edge">-->
    <title>Document</title>
    <style>
        body {
            background: rgb(240, 242, 245);
            margin-top: -20px;
        }

        .div404 {
            position: relative;
            width: 1200px;
            margin: 20px auto 60px;
            padding: 0 100px;
            overflow: hidden;
        }
        .pic-404{
            position: relative;
            float: left;
            width: 600px;
            padding: 150px 0;
            overflow: hidden;
        }
        .bullshit{
            position: relative;
            float: left;
            width: 300px;
            padding: 150px 0;
            overflow: hidden;
        }
        .bullshit__oops{
            color: #1482f0;
            font-size: 32px;
            font-weight: 700;
            line-height: 40px;
            margin-bottom: 20px;
            -webkit-animation-fill-mode: forwards;
            animation-fill-mode: forwards;
        }
        .bullshit__headline{
            font-size: 20px;
            line-height: 24px;
            margin-bottom: 10px;
            -webkit-animation-fill-mode: forwards;
            animation-fill-mode: forwards;
            color: #1482f0;
        }
        .bullshit__info{
            font-size: 13px;
            line-height: 21px;
            color: grey;
            margin-bottom: 30px;
        }
    </style>
</head>

<body>
    <div class="div404">
        <div class="pic-404">
          <img src="${pageContext.request.contextPath}/img/500.jpg" alt="" width="100%">
            <img src="${pageContext.request.contextPath}/img/500yuan.jpg" alt="" width="100%">
        </div>
        <div class="bullshit">
            <div  class="bullshit__oops">OOPS!</div>
            <div  class="bullshit__headline">出错了......</div>
            <div  class="bullshit__info">请点击以下按钮返回主页或者发送错误报告</div>
            <a   href="/wellassist/" class="bullshit__return-home">返回首页</a>

        </div>

    </div>


</body>


</html>