<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>卖家订单详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<link href="css/style.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>
    <style>
        .mid_box {
            width: 1100px;
            margin: 0px auto;
            border: 1px solid rgba(34, 36, 38, .15);
            box-shadow: 0 2px 4px 0 rgba(34, 36, 38, .12), 0 2px 10px 0 rgba(34, 36, 38, .15);
            padding: 1em;
            font-size: 14px;
            position: absolute;
        }
        
        ul {
            padding: 0;
        }
        
        ul li {
            list-style-type: none;
            padding: 0px;
        }
        
        .tx-rg {
            text-align: right;
        }
        
        .span_status {
            color: #adadad;
        }
        
        .fl-rg {
            float: right;
        }
        
        .fl-lf {
            float: left;
        }
        
        .pd-lf-10 {
            padding-left: 10px;
        }
        
        .pd-bt-10 {
            padding-bottom: 10px;
        }
        
        table {
            margin: 10px;
        }
        
        .container .item span {
            display: block;
            width: 30%;
            float: left
        }
        
        .comment .avatar {
            width: 120px !important;
            height: 120px !important
        }
        
        .comment .content a {
            line-height: 120px !important;
            padding-left: 1em;
        }
        
        .col {
            display: block;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        
        .col-line {
            height: 30px;
        }
    </style>
</head>

<body>
    <div class="ui container segment" id="app1">
        <h3 class="ui header">订单详情：</h3>
        <div class="ui divider"></div>
        <div class="column container">
            <div class="fl-lf " style="width: 50%;"> 订单号：</div>
            <div class="right item fl-rg" style="width: 40%;"><span class="ui inverted ">下单时间:</span>
            </div>
            <br><br>
        </div>
        <hr>
        <div class="">
            <div class="ui comments">
                <div class="comment">
                    <a class="avatar">
                        <img src="">
                    </a>
                    <div class="content">
                        <a class="author">商品名称：</a>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <ul>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">货源类型：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">总吨数：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">总价：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">单价：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">付款状态：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">已发吨数：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">货运方式：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">发货时间：</div>
                <div class="fl-lf">2523</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">收货时间：</div>
                <div class="fl-lf">2523</div>
            </li>
        </ul>
        <table class="ui celled padded table ">
            <thead>
                <tr>
                    <th class="single line ">车队联系人</th>
                    <th>电话</th>
                    <th>车牌号</th>
                    <th>车挂号</th>
                    <th>容量</th>
                    <th>状态</th>


                </tr>
            </thead>
            <tbody>
                <tr v-for="vehicle in Vehicles " v-cloak>
                    <td>
                        <h2 class="ui center aligned header xs " id="x" onclick="x()" value="{{{vehicle.dr_name}}}">{{vehicle.dr_name}}</h2>
                    </td>
                    <td class="single line ">{{vehicle.dr_tel}}</td>
                    <td> {{vehicle.dr_number}}</td>
                    <td>{{vehicle.hanging}}</td>
                    <td>{{vehicle.capacity}} 吨</td>
                    <td rowspan="5">已收货</td>

                </tr>
                <tr v-for="vehicle in Vehicles " v-cloak>
                    <td>
                        <h2 class="ui center aligned header xs " id="x" onclick="x()" value="{{{vehicle.dr_name}}}">{{vehicle.dr_name}}</h2>
                    </td>
                    <td class="single line ">{{vehicle.dr_tel}}</td>
                    <td> {{vehicle.dr_number}}</td>
                    <td>{{vehicle.hanging}}</td>
                    <td>{{vehicle.capacity}} 吨</td>


                </tr>

            </tbody>
        </table>

    </div>
</body>

<%@ include file="../footer.html"%>