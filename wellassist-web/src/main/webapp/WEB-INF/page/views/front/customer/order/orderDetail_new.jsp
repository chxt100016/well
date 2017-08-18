<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>订单详情</title>
    <%--<meta charset="UTF-8">
    <meta name="viewport" content=e-width, initial-scale=1">--%>
    <!--<link href="css/style.css" rel="stylesheet">-->
    <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>--%>
    <style>
        
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
        .tx-lf{
            text-align:left;
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
            width: 90%;
           
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
        .blue-c{
            color:#2185D0;
        }
    </style>
</head>

<body>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
    <div class="ui container" id="app1" style="width:90%;">
        <h4 class="ui header">订单详情</h4>
        <div class="ui divider"></div>

        <div style="font-size:14px;font-weight: 600;line-height: 30px; margin:20px 0 40px 60px;">
                <span>当前订单付款状态：</span>
                <span>
                    <c:if test="${fn:startsWith(info.orderState,'-')||fn:startsWith(info.orderState,0)||fn:startsWith(info.orderState,1)}">未付款</c:if>
                    <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=2}">已付款</c:if>
                </span><br>

                <span>订单号：</span>
                <span>${info.orderNo}</span>
                <span style="margin-left:9%;">货运方式：</span>
                <span>
                    <c:if test="${info.isSelfCar==0}">自提</c:if>
                    <c:if test="${info.isSelfCar==1}">平台物流</c:if>
                </span><br>

                <span>下单时间：</span>
                <span><fmt:formatDate value="${info.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
                <span style="margin-left:11.5%;">联系人：</span>
                <span>${info.customerContacts}</span><br>

                <span>预计发货时间：</span>
                <span><fmt:formatDate value="${info.deliverDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
                <span style="margin-left:9.6%;">联系电话：</span>
                <span>${info.customerConTel}</span><br>

                <span>预计收货时间：</span>
                <span><fmt:formatDate value="${info.receiveDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
            <span style="margin-left:11.5%;">已收吨数：</span>
            <span><c:if test="${fn:startsWith(info.orderState,'-')||fn:substring(info.orderState,0,1)<=2}">未收货</c:if>
                    <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=3}">${info.sumNum}</c:if></span><br>
            </div>

                <!-- <table class="ui table" style="border:0;background-color:#DAF3FF;height:45px;line-height:45px;border-radius:0;text-align:center;">
                    <tr>
                        <th colspan="2">产品</th>
                        <th width="13%">品类</th>
                        <th width="15%">单价(元/吨)</th>
                        <th width="14%">数量(吨)</th>
                        <th width="15%">总价(元)</th>
                    </tr>
                </table> -->
                <table class="ui line celled  table" style="margin-top:-8px;text-align:center;">
                    <thead>
                        <tr>
                            <th colspan="2">产品</th>
                            <th>品类</th>
                            <th>单价(元/吨)</th>
                            <th>数量(吨)</th>
                            <th>总价(元)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <img src="${info.prodImg}" height="75">
                            </td>
                            <td style="text-align:left;border-left:0;">${info.prodName}</td>
                            <td>
                                <c:if test="${info.prodType==0}">天然气</c:if>
                                <c:if test="${info.prodType==1}">原油</c:if>
                                <c:if test="${info.prodType==2}">管道气</c:if>
                            </td>
                            <td>
                                <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleMoney/info.saleNum}</c:if>
                                <%--<c:if test="${empty info.orderNumber && !empty info.confirmNumber}">${info.comfirmPrice}</c:if>--%>
                                <c:if test="${!empty info.orderNumber  }">${info.orderPrice}</c:if> 元</div>
                                <c:if test="${not empty info.orderNumber and (info.orderPrice-(info.saleMoney/info.saleNum)>0.0000000001 or info.orderPrice-(info.saleMoney/info.saleNum)<-0.0000000001)}"><s>${info.saleMoney/info.saleNum}</s></c:if>
                            </td>
                            <td>
                                <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleNum}</c:if>
                                <c:if test="${!empty info.orderNumber}">${info.orderNumber}</c:if> 吨
                                <c:if test="${not empty info.orderNumber and (info.orderNumber-info.saleNum>0.0000000001 or info.orderNumber-info.saleNum<-0.0000000001)}"><s>${info.saleNum}</s></c:if>
                            </td>
                            <td>  
                                <c:if test="${empty info.orderNumber && empty info.confirmNumber}">${info.saleMoney}</c:if>
                                <c:if test="${!empty info.orderNumber }">${info.orderNumber*info.orderPrice}</c:if> 元
                                <c:if test="${not empty info.orderNumber and (info.orderNumber*info.orderPrice-info.saleMoney>0.0000000001 or info.orderNumber*info.orderPrice-info.saleMoney<-0.0000000001)}"><s>${info.saleMoney}</s></c:if>
                            </td>
                        </tr>
                    </tbody><%--
                    <tfoot class="full-width">
                        <tr>
                            <th> 状态：</th>
                            <th colspan="5" style="font-weight:600;text-align:left;">
                                <span style="color:#a00;"><c:if test="${fn:startsWith(info.orderState,'-')||fn:substring(info.orderState,0,1)<=2}">未发货</c:if></span>
                                <c:if test="${!fn:startsWith(info.orderState,'-')&&fn:substring(info.orderState,0,1)>=3}">${info.sumNum}</c:if>
                            </th>
                        </tr>
                    </tfoot>--%>
                </table>

        <c:if test="${!empty info.zorders}">
            <h4 class="ui header">物流信息</h4>
          <div class="ui divider"></div>
        <c:forEach items="${info.zorders}" var="zorder">
        <div class="ui ignored positive message">
       <div class="col-line tx-lf"> 本次发货时间：<fmt:formatDate value="${zorder.zorderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>

        <div class="col-line tx-lf">子订单单价: ${zorder.zorderPrice} 元</div>
        <div class="col-line tx-lf">子订单发货量：${zorder.zorderNum} 吨</div>
        <div class="col-line tx-lf">子订单成交额：${zorder.zorderMoney} 元</div>
            <c:if test="${not empty zorder.sendComment}">
            <div class="col-line tx-lf">发货备注：${zorder.sendComment}</div>
            </c:if>
            <c:if test="${not empty zorder.receiveComment}">
            <div class="col-line tx-lf">收货备注：${zorder.receiveComment}</div>
            </c:if>
        </div>
        <table class="ui celled padded table " >
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
            <c:forEach items="${zorder.orderVehicles}" var="orderVehicle">
                <tr>
                    <td>
                        <h3 class="ui center aligned header xs " id="x" onclick="x()" value="${orderVehicle.driverName}">${orderVehicle.driverName}</h3>
                    </td>
                    <td class="single line ">${orderVehicle.driverPhone}</td>
                    <td> ${orderVehicle.vehicleNo}</td>
                    <td>${orderVehicle.vehicleHangingNo}</td>
                    <td>${orderVehicle.vehicleActualSize} 吨</td>
                </tr>
            </c:forEach>

            </tbody>
            <tfoot class="full-width">
                <tr>
                    <th> 状态：</th>
                    <th colspan="4">
                        <c:if test="${zorder.zorderState==1}">
                            <div style="width: 500px;height:100px">
                                 <span>备注信息:</span>
                                <textarea name="receiveComment"   style=" width: 444px;height:80px" ></textarea>
                            </div>
                            <div class="ui right floated small primary icon button" onclick="confirmReceive(${zorder.zorderId},this)"> 已收货，确认 </div>
                             <div class="ui right floated small  icon red button" onclick="doubtReceive(${info.orderId},${zorder.zorderId},this)"> 已收货，存疑 </div>

                        </c:if>
                         <c:if test="${zorder.zorderState==2}"><div class="ui  ">已收货,确认 </div></c:if>
                        <c:if test="${zorder.zorderState==11}"><div class="ui  ">已收货,存疑 </div></c:if>

                    </th>
                </tr>
            </tfoot>
        </table>
<br>
             <hr>
              
        </c:forEach>
         
            </c:if>
        <button onclick="javascript:window.history.go(-1);" class="ui button">返回</button>
            </div>
    </div>
</body>
<script>
    function confirmReceive(zorderId,com){
        var receiveComment=com.previousElementSibling.children[1].value;
        
        if(confirm("你要确定要操作吗？")){
            $.get("${pageContext.request.contextPath}/customer/zorderConfirmReceive",{zorderId:zorderId,receiveComment:receiveComment},function(data){
                if(data.code==0){
                    window.location.reload();
                }
            },"json")
                .error(function(data){
                    alert("操作失败！");
                });
        }
    }
    function doubtReceive(orderId,zorderId,com) {
        // var receiveComment=com.previousElementSibling.children[1].value;
        var receiveComment=$("textarea[name=receiveComment]").val();
        // console.log(receiveComment);
        if(confirm("你要确定要操作吗？")){
            $.get("${pageContext.request.contextPath}/customer/zorderDoubtReceive",{orderId:orderId,zorderId:zorderId,receiveComment:receiveComment},function(data){
                if(data.code==0){
                    window.location.reload();
                }
            },"json")
                .error(function(data){
                    alert("操作失败！");
                });
        }
    }
</script>

<%@ include file="../footer.jsp"%>