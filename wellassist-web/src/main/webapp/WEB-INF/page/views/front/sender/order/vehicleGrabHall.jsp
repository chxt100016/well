<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>抢单大厅</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>
    <style>
        .oliver-color {
            color: #B5CC18;
        }
        
        .teal-color {
            color: #00B5AD;
        }
    </style>
</head>

<body>
    <div class="ui container segment" id="app1">
        <h3 class="ui header">抢单大厅</h3>
        <div class="ui divider"></div>

        <div class="ui four cards">
            <c:forEach items="${info}" var="ii">
            <div class="card">
                <div class="content">
                    <img class="right floated mini ui image" src="${ii.prodImg}">
                    <div class="header">产品名称：${ii.prodName}</div>
                    <div class="meta">下单日期： <fmt:formatDate value="${ii.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>
                    <div class="description">卖家名称：${ii.sellerUserName} </div>
                </div>
                <div class="content">
                    <h4 class="ui sub header">详细信息</h4>
                    <div class="ui small feed">
                        <div class="event">
                            <div class="content">
                                <div class="summary">货物数量： <span class="oliver-color"> ${ii.num}  </span>吨</div>
                            </div>
                        </div>
                        <%--<div class="event">
                            <div class="content">
                                <div class="summary">运费报价：<span class="teal-color"> 30  </span>元</div>
                            </div>
                        </div>--%>
                        <div class="event">
                            <div class="content">
                                <div class="summary">提货地址:${ii.fromAddress} </div>
                            </div>
                        </div>
                        <div class="event">
                            <div class="content">
                                <div class="summary">提货地址:${ii.toAddress} </div>
                            </div>
                        </div>
                    </div>
                    <div class="extra">
                        <button class="ui red button" onclick="grab(${ii.logisticsId})">抢单</button>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
        <%--<div class="ui right floated pagination menu">
            <a class="icon item">
                <i class="left chevron icon"></i>
            </a>
            <a class="item">1</a>
            <a class="item">2</a>
            <a class="item">3</a>
            <a class="item">4</a>
            <a class="icon item">
                <i class="right chevron icon"></i>
            </a>
        </div>--%>
        <div class="right-pagination">
            <%@ include file="../../pagination.jsp"%>
        </div>
        <br><br>
    </div>

</body>
<script>
    function grab(logisticsId){
        window.location.href="${pageContext.request.contextPath}/sender/grabLogistics?logisticsId="+logisticsId;
    }
</script>
<%@ include file="../footer.jsp"%>