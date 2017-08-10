<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>卖家物流详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>--%>
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
        
        .span_time {}
        
        .span_status {
            color: #adadad;
        }
        
        .fl-rg {
            float: right;
        }
        
        .fl-lf {
            float: left;
        }
        .tx-rg{
            text-align:right;
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
            float: left
        }
    </style>
</head>

<body>
    <div class="ui container segment" id="content-rect" style="margin-top:0px;left: 10px;" >
        <h3 class="ui header">物流详情</h3>
        <div class="ui divider"></div>
        <div class="column ">
            <c:if test="${info.isSelfCar==0}">
                <div class="fl-lf " style="width: 80%;">取货方式：买家自提</div>
            </c:if>
            <c:if test="${info.isSelfCar==1}">
                <div class="fl-lf " style="width: 80%;">
                <div  class="fl-lf "><span class="a-color-base">物流公司名称：</span> </div>
                <div class="fl-rg "> <span >${info.senderName}</span></div>
                </div>
            </c:if>

            <div class="fl-lf " style="width: 80%;"> 
                <div  class="fl-lf "><span class="a-color-base">发货时间：</span> </div>
                <div class="fl-rg "> <span ><fmt:formatDate value="${info.deliverDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span></div>
            </div>

            <div class="fl-lf " style="width: 80%;">
                <div  class="fl-lf "><span class="a-color-base">收货时间：</span> </div>
                <div class="fl-rg "> <span ><fmt:formatDate value="${info.receiveDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span> </div>
             </div>
            <div class="fl-lf " style="width: 80%;">
                <div  class="fl-lf "><span class="a-color-base">发货地址：</span> </div>
                <div class="fl-rg "> <span >${info.fromAddress}</span> </div>
            </div>
            <div class="fl-lf " style="width:80%;">
                <div  class="fl-lf "><span class="a-color-base">收货地址：</span> </div>
                <div class="fl-rg "><span> ${info.toAddress}</span> </div>
            </div>
            <div class="fl-lf " style="width: 80%;">
                <div  class="fl-lf "><span class="a-color-base">买家联系人：</span> </div>
                <div class="fl-rg "><span>${info.customerContacts}</span> </div>
            </div>
            <div class="fl-lf " style="width: 80%;">
                <div  class="fl-lf "><span class="a-color-base">买家联系电话：</span> </div>
                <div class="fl-rg "><span>${info.customerConTel}</span> </div>
            </div>
            <div class="right item fl-rg" style=" "><span class=""></span>

            </div>
            <br><br>
            
        </div>
        <c:if test="${!empty info.vehicles}">
            <table class="ui celled padded table ">
                <thead>
                    <tr>
                        <th class="single line ">司机</th>
                        <th>电话</th>
                        <th>车牌号</th>
                        <th>车挂号</th>
                        <th>容量</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${info.vehicles}" var="vehicle">
                    <tr>
                        <td>
                            <h2 class="ui center aligned header" >${vehicle.driverName}</h2>
                        </td>
                        <td class="single line ">${vehicle.driverPhone}</td>
                        <td> ${vehicle.vehicleNo}</td>
                        <td>${vehicle.vehicleHangingNo}</td>
                        <td>${vehicle.vehicleSize} 吨</td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
            </c:if>
        <button onclick="javascript:window.history.go(-1);">返回</button>
    </div>

</body>
<%@ include file="../footer.jsp"%>