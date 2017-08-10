<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
    <html lang="en">

    <head>
        <title>还款记录</title>
        <%--<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
        <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>--%>

        <style>
            .grey {
                color: rgba(0, 0, 0, .4)
            }

            .teal-bg {
                background-color: #00B5AD!important;
                color: #FFF!important;
            }
        </style>

    </head>

    <body>
    <form id="searchFrm" method="post" action="${pageContext.request.contextPath}/customer/loansRepayRecords">
        <input type="hidden" id="page" name="page" value="${param.page}">
    </form>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
        <div id = "content-rect" style="width:90%;"> 

        
        <div class="ui container" id="app1" style="width: 100%; left:10px;margin-top:-1px">
            <h4>还款记录</h4>
            <div class="ui divider"></div>
            <c:if test="${not empty loans}">
                <c:forEach items="${loans}" var="loan">
            <div class="ui">
                <br>
                 <h4 class="ui horizontal divider header"><i class="bar chart icon"></i> 借款信息 </h4>
                <div class="ui equal width grid">
                    <div class="column">订单编号：${loan.orderNo}</div>
                    <div class="column">还款状态:<c:if test="${loan.loanState==2}">待还款</c:if><c:if test="${loan.loanState==3}">已还清</c:if></div>
                    <div class="column">借款时间：<fmt:formatDate value="${loan.applyDate}" pattern="yyyy-MM-dd"/> </div>
                </div>
                <div class="ui equal width grid">
                    <div class="column">借款金额：${loan.loanMoney} 元</div>
                    <div class="column">产生利息：${loan.lixiMoney} 元</div>
                    <div class="column">还款期限：<fmt:formatDate value="${loan.paymentDate}" pattern="yyyy-MM-dd"/></div>
                </div>
                 <div class="ui equal width grid">
                    <div class="column">已还本金：${loan.repayMoney} 元</div>
                    <div class="column">已还利息：${loan.repayLixi} 元</div>
                    <div class="column">尾款：${loan.remainRepayMoney+loan.remainLixiMoney} 元</div>
                </div>
                <h4 class="ui horizontal divider header"><i class="bar alarm outline icon"></i> 还款记录 </h4>


                <c:if test="${not empty loan.repays}">
                <table class="ui single line  table ">
                <thead>
                    <!-- <tr>
                        <th colspan="6">还款记录 </th>
                    </tr> -->
                    <tr >
                        <th >还款时间</th>
                        <%--<th >还款详情</th>--%>
                        <th >还款方式</th>
                        <th >还款本金</th>
                        <th >还款利息</th>
                        <th>借款利率</th>
                    </tr>

                </thead>
                <tbody>
                <c:forEach items="${loan.repays}" var="repay">
                    <tr>

                        <td><fmt:formatDate value="${repay.repayDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <%--<td>还款成功</td>--%>
                        <td>余额支付</td>
                        <td>${repay.repayMoney}元</td>
                        <td>${repay.repayInterestMoney}元</td>
                        <td>${loan.lixiRate}‱</td>
                    </tr>
                    </c:forEach>

                </tbody>

            </table>
                </c:if>
                <c:if test="${empty loan.repays}">无还款记录...</c:if>
            </div>
                </c:forEach>
            </c:if>
            <div class="ui grid">
            <div class=" row">
            <div class=" right floated five column" style="text-align:center;margin-top:20px;">
                <!-- <div style="float:right"> -->
                <%@ include file="../../pagination.jsp"%>
                <!-- </div> -->
            </div>
            </div>
        </div>
            <c:if test="${empty loans}">
                没有记录...
            </c:if>
            
        </div>
        </div>
    </div>
</div>
    </body>

    </html>