<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>卖家订单详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<link href="css/style.css" rel="stylesheet">-->
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>-->
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
        
        
        .container .item span {
            display: block;
            width: 70%;
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
        .grey{
            color: #8590a6;
        }
        .Dtab tr td:first-child{text-align: right;}
    </style>
</head>

<body>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
    <div class="ui container" id="app1" style="width:90%;left:80px;top:-15px">
        <h4 class="ui header">订单详情</h4>
        <div class="ui divider"></div>
        <table class="Dtab" style="font-size:14px;font-weight:600;margin:20px 0 40px 60px;border-radius:0;">
            <!-- <tr>
                <td>当前订单付款状态：</td>
                <td>
                    <c:if test="${fn:startsWith(info.orderState,'-')||fn:startsWith(info.orderState,0)||fn:startsWith(info.orderState,1)}">未付款</c:if>
                    <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=2}">已付款</c:if>
                </td>
                <td colspan="3"></td>
            </tr> -->
            <tr>
                <td>订单号：</td>
                <td>${info.orderNo}</td>
            </tr>
            <tr>
                <td>下单时间：</td>
                <td><fmt:formatDate value="${info.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
            <tr>
                <td>发货时间：</td>
                <td><fmt:formatDate value="${info.deliverDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
            <tr>
                <td>预计收货时间：</td>
                <td><fmt:formatDate value="${info.receiveDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
            <tr>
                <td>货运方式：</td>
                <td>
                    <c:if test="${info.isSelfCar==0}">自提</c:if>
                    <c:if test="${info.isSelfCar==1}">平台物流</c:if>
                </td>
            </tr>
        </table>
        <table class="ui table" style="border:0;background-color:#DAF3FF;height:45px;line-height:45px;border-radius:0;text-align:center;">
            <tr>
                <th colspan="2" width="30%">产品</th>
                <th width="13%">品类</th>
                <th width="15%">单价(元/吨)</th>
                <th width="14%">数量(吨)</th>
                <th width="15%">总价(元)</th>
                <th width="13%">状态</th>
            </tr>
        </table>
        <table class="ui line celled  table" style="margin-top:-8px;text-align:center;border-radius:0;">
            <tr>
                <td width="10%">
                    <img src="${info.prodImg}" width="100" height="75">
                </td>
                <td width="20%" style="text-align:left;border-left:0;">${info.prodName}</td>
                <td width="13%">
                    <c:if test="${info.prodType==0}">天然气</c:if>
                    <c:if test="${info.prodType==1}">原油</c:if>
                    <c:if test="${info.prodType==2}">管道气</c:if>
                </td>
                <td width="14%">
                    <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleMoney/info.saleNum}</c:if>
                    <c:if test="${!empty info.orderNumber  }">${info.orderPrice}</c:if>元</div>
                    <c:if test="${not empty info.orderNumber and (info.orderPrice-(info.saleMoney/info.saleNum)>0.0000000001 or info.orderPrice-(info.saleMoney/info.saleNum)<-0.0000000001)}"><s>${info.saleMoney/info.saleNum}</s></c:if>
                </td>
                <td width="14%">
                    <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleNum}</c:if>
                    <c:if test="${!empty info.orderNumber}">${info.orderNumber}</c:if>吨
                    <c:if test="${not empty info.orderNumber and (info.orderNumber-info.saleNum>0.0000000001 or info.orderNumber-info.saleNum<-0.0000000001)}"><s>${info.saleNum}</s></c:if>
                </td>
                <td width="15%">  
                    <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleMoney}</c:if>
                    <c:if test="${!empty info.orderNumber }">${info.orderNumber*info.orderPrice}</c:if>元</div>
                    <c:if test="${not empty info.orderNumber and (info.orderNumber*info.orderPrice-info.saleMoney>0.0000000001 or info.orderNumber*info.orderPrice-info.saleMoney<-0.0000000001)}"><s>${info.saleMoney}</s></c:if>
                </td>
                <td width="13%">
                    <c:if test="${fn:startsWith(info.orderState,'-')||fn:startsWith(info.orderState,0)||fn:startsWith(info.orderState,1)}">未付款</c:if>
                    <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=2}">已付款</c:if>
                </td>
            </tr>
        </table>
<!--         <div class="column container">
            <div class="fl-lf " style="width: 50%;"> 订单号：${info.orderNo}</div>
            <div class="right item fl-rg" style="width: 40%;"><span class="ui inverted ">下单时间:<fmt:formatDate value="${info.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span>
            </div>
            <br><br>
        </div>
        <hr>
        <div class="">
            <div class="ui comments">
                <div class="comment">
                    <a class="avatar">
                        <img src="${info.prodImg}">
                    </a>
                    <div class="content">
                        <a class="author">商品名称：${info.prodName}</a>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <ul>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">货源类型：
                </div>
                <div class="fl-lf"><c:if test="${info.prodType==0}">天然气</c:if>
                    <c:if test="${info.prodType==1}">原油</c:if>
                    <c:if test="${info.prodType==2}">管道气</c:if></div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">总吨数：</div>
                <div class="fl-lf">
                    <%--<c:if test="${not empty info.orderNumber and info.orderNumber!=info.saleNum}"><s>${info.saleNum}</s></c:if>--%>
                        
                        <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleNum}</c:if>
                <%--<c:if test="${empty info.orderNumber && !empty info.confirmNumber}">${info.confirmNumber}</c:if>--%>
                <c:if test="${!empty info.orderNumber}">${info.orderNumber}</c:if>吨
                <c:if test="${not empty info.orderNumber and (info.orderNumber-info.saleNum>0.0000000001 or info.orderNumber-info.saleNum<-0.0000000001)}"><s>${info.saleNum}</s></c:if>
                </div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">总价：</div>
                <div class="fl-lf">
                    <%--<c:if test="${not empty info.orderNumber and info.orderNumber*info.orderPrice!=info.saleMoney}"><s>${info.saleMoney}</s></c:if>--%>
                       
                        <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleMoney}</c:if>
                    <%--<c:if test="${empty info.orderNumber && !empty info.confirmNumber}">${info.confirmNumber*info.comfirmPrice}</c:if>--%>
                    <c:if test="${!empty info.orderNumber }">${info.orderNumber*info.orderPrice}</c:if>元</div>
                     <c:if test="${not empty info.orderNumber and (info.orderNumber*info.orderPrice-info.saleMoney>0.0000000001 or info.orderNumber*info.orderPrice-info.saleMoney<-0.0000000001)}"><s>${info.saleMoney}</s></c:if>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">单价：</div>
                <div class="fl-lf">
                    <%--<c:if test="${not empty info.orderNumber and info.orderPrice!=(info.saleMoney/info.saleNum)}"><s>${info.saleMoney/info.saleNum}</s></c:if>--%>
                      
                        <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleMoney/info.saleNum}</c:if>
                    <%--<c:if test="${empty info.orderNumber && !empty info.confirmNumber}">${info.comfirmPrice}</c:if>--%>
                    <c:if test="${!empty info.orderNumber  }">${info.orderPrice}</c:if>元</div>
                      <c:if test="${not empty info.orderNumber and (info.orderPrice-(info.saleMoney/info.saleNum)>0.0000000001 or info.orderPrice-(info.saleMoney/info.saleNum)<-0.0000000001)}"><s>${info.saleMoney/info.saleNum}</s></c:if>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">付款状态：</div>
                <div class="fl-lf"><c:if test="${fn:startsWith(info.orderState,'-')||fn:startsWith(info.orderState,0)||fn:startsWith(info.orderState,1)}">未付款</c:if>
                    <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=2}">已付款</c:if>
                </div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">已发吨数：</div>
                <div class="fl-lf"><c:if test="${fn:startsWith(info.orderState,'-')||fn:substring(info.orderState,0,1)<=2}">未发货</c:if>
                    <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=3}">${info.sumNum}</c:if>
                </div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">货运方式：</div>
                <div class="fl-lf"><c:if test="${info.isSelfCar==0}">自提</c:if>
                    <c:if test="${info.isSelfCar==1}">平台物流</c:if>
                </div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">发货时间：</div>
                <div class="fl-lf">${info.deliverDate}</div>
            </li>
            <li class="col-line">
                <div class="fl-lf  tx-rg " style="width:200px;">收货时间：</div>
                <div class="fl-lf">${info.receiveDate}</div>
            </li>
        </ul><br><br> -->
        <c:if test="${!empty info.zorders}">
            <h4 class="ui header">物流信息</h4>
            <div class="ui divider"></div>
            <c:forEach items="${info.zorders}" var="zorder">
                <div style="margin:20px 0 40px 60px;">
                    <span>发货时间：</span><fmt:formatDate value="${zorder.zorderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate><br>
                    <span>子订单单价：</span>${zorder.zorderPrice}&emsp;&emsp;&emsp;&emsp;<span>子订单发货量：</span>${zorder.zorderNum}&emsp;&emsp;&emsp;&emsp;<span>子订单成交额：</span>${zorder.zorderMoney}<br>
                    
                    <c:if test="${not empty zorder.sendComment}">
                        <span>发货备注：</span>${zorder.sendComment}&emsp;&emsp;&emsp;
                    </c:if>
                    <c:if test="${not empty zorder.receiveComment}">
                        <span>收货备注：</span>${zorder.receiveComment}
                    </c:if>
                </div>
                <table class="ui table" style="border:0;background-color:#DAF3FF;height:45px;line-height:45px;border-radius:0;text-align:center;">
                    <tr>
                        <th width="20%">司机</th>
                        <th width="20%">电话</th>
                        <th width="20%">车牌号</th>
                        <th width="20%">车挂号</th>
                        <th width="20%">装载量</th>
                    </tr>
                </table>
                <table class="ui line celled  table" style="margin-top:-8px;text-align:center;border-radius:0;">
                    <c:forEach items="${zorder.orderVehicles}" var="orderVehicle">
                        <tr >
                            <td width="20%">
                                <h2 class="ui center aligned header " value="${orderVehicle.driverName}">${orderVehicle.driverName}</h2>
                            </td>
                            <td width="20%" class="single line ">${orderVehicle.driverPhone}</td>
                            <td width="20%"> ${orderVehicle.vehicleNo}</td>
                            <td width="20%">${orderVehicle.vehicleHangingNo}</td>
                            <td width="20%">${orderVehicle.vehicleActualSize} 吨</td>
                        </tr>
                    </c:forEach>
                </table>

            <c:if test="${zorder.zorderState==1}">已发货</c:if>
                <c:if test="${zorder.zorderState==2}">已收货</c:if>
                    <div class="ui divider"></div>
            </c:forEach>

        </c:if>
        <button onclick="javascript:window.history.go(-1);" class="ui primary button">返回</button>
    </div>
    </div>
</div>
</body>

<%@ include file="../footer.html"%>