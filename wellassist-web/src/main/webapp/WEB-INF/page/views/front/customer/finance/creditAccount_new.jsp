<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title></title>
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
        .teal-bg{
          background-color: #00B5AD!important;
    color: #FFF!important;
        }
    </style>
</head>


<body>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
        <div id = "content-rect" style="width:90%;">    
            <div class="ui container" id="app1" style="width: 100%; left: 70px; top: -14px;">
                <h4>授信额度</h4>
                <div class="ui divider"></div>
                <table style="text-align:right;font-size:14px;font-weight:600;line-height:35px;margin:20px 0 40px 60px;">
                    <tr>
                        <td width="10%">总额度</td>
                        <td>
                            <span style="font-size:16px;">
                                <c:if test="${not empty info.credit}"><fmt:formatNumber value="${info.credit.creditSjMoney}" pattern="#,###.##" type="number"/></c:if>
                                <c:if test="${empty info.credit}">0</c:if>
                            </span>&emsp;元
                        </td>
                        <td></td>
                        <td>到期日期</td>
                        <td style="color:#ff0000;">
                            <c:if test="${not empty info.credit}"><fmt:formatDate value="${info.credit.creditDeadline}" pattern="yyyy-MM-dd"/> </c:if>
                            <c:if test="${empty info.credit}">无</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>可用额度</td>
                        <td>
                            <span><fmt:formatNumber value="${info.user.userCreditMoney}" pattern="#,###.##" type="number"/></span>&emsp;元
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>已用额度</td>
                        <td>
                            <span><fmt:formatNumber value="${sumLoans}" pattern="#,###.##" type="number"/></span>&emsp;元
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button class="ui positive button" onClick = "toURL('sxSq')">申请/修改授信额度</button></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <!-- <div class="ui three column grid">
                    <div class="row" style="display: inline-table">
                        <div class="column">总额度：<span><c:if test="${not empty info.credit}"><fmt:formatNumber value="${info.credit.creditSjMoney}" pattern="#,###.##" type="number"/> </c:if>
                        <c:if test="${empty info.credit}">0</c:if>
                        </span> <span> 元</span></div>
                        <div class="column" style="color:#ff0000;">到期日期：<span>
                            <c:if test="${not empty info.credit}"><fmt:formatDate value="${info.credit.creditDeadline}" pattern="yyyy-MM-dd"/> </c:if>
                        <c:if test="${empty info.credit}">无</c:if>
                        </span></div>
                        <div class="column">可用额度:<span><fmt:formatNumber value="${info.user.userCreditMoney}" pattern="#,###.##" type="number"/></span> <span> 元</span></div>
                    </div>
                    <div class="row" style="display: inline-table">
                        <%--<div class="column">我的借款：<span>xxx</span> <span class="grey"> 元</span></div>--%>
                        <div class="column">已用额度：<span><fmt:formatNumber value="${sumLoans}" pattern="#,###.##" type="number"/></span> <span class="grey"> 元</span></div>
                        <%--<div class="column">还款日期:<span>xxx</span> </div>--%>
                    </div>

                    <div class="row" style="display: inline-table">
                        <div class="column" ><button class="ui positive button" onClick = "toURL('sxSq')">申请/修改授信额度</button></div>

                    </div>

                </div> -->
                <h4>授信详情</h4>
                <div class="ui divider"></div>
                <form id="searchFrm" method="post" action="${pageContext.request.contextPath}/customer/creditAccount">
                    <input type="hidden" id="page" name="page" value="${param.page}">
                </form>
                <table class="ui celled striped  table ">
                    <thead>
                        <tr>
                            <th colspan="7">授信待还款列表（注：本平台利率为${loan.lixiRate}‱/日） </th>
                        </tr>
                        <tr class="teal-bg ">
                            <th class="teal-bg">申请日-还款日</th>
                            <th class="teal-bg">授信详情</th>
                             <th class="teal-bg">授信金额(元)</th>
                            <th class="teal-bg">剩余利息(元)</th>
                            <th class="teal-bg">滞纳金(元)</th>
                             <th class="teal-bg">余款(元)</th>
                             <th class="teal-bg center aligned">操作</th>
                        </tr>
                        
                    </thead>
                    <tbody>
                    <c:if test="${not empty loans}">
                        <c:forEach items="${loans}" var="loan">
                        <tr>
                            <td><fmt:formatDate value="${loan.applyDate}" pattern="yyyy-MM-dd"/></td>
                            <td ><c:if test="${loan.loanState==2}">待还款</c:if><c:if test="${loan.loanState==21}">还款处理中</c:if></td>
                             <td><fmt:formatNumber value="${loan.loanMoney}" pattern="#,###.##" type="number"/></td>
                             <td><fmt:formatNumber value="${loan.remainLixiMoney}" pattern="#,###.##" type="number"/></td>
                             <td><fmt:formatNumber value="${loan.remainOverdueFine}" pattern="#,###.##" type="number"/></td>
                             <td><fmt:formatNumber value="${loan.remainRepayMoney+loan.remainLixiMoney+loan.remainOverdueFine}" pattern="#,###.##" type="number"/></td>
                             <td class="center aligned">
                                 <button class="ui positive button" onclick="toURL('goRepayLoan',${loan.loanId})">还款</button>
                                 <button class="ui secondary basic button" onclick="toURL('goRepayDetail',${loan.loanId})">明细</button>   
                             </td>
                        </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty loans}">无欠款...</c:if>
                    <tfoot>
                        
                             <th colspan="7">
                                    <div class="right-pagination">
                                     <%@ include file="../../pagination.jsp"%>
                                     </div>
                             </th>
                    </tfoot>
                    </tbody>

                </table>
                
            </div>
        </div>
    </div>
</div>
</body>
<script>
    // 功能函数
    function toURL(action,loanId){
        var url = "";
        if(action == 'sxSq'){
            $.get("${pageContext.request.contextPath}/customer/isCreditApplyAvailable",{},function(r){
                if(r.code==0){
                    url = "${pageContext.request.contextPath}/customer/creditApply";
                    window.location.href = url;
                }else {
                    alert(r.msg);
                }
            },"json");
        }else if (action == 'goRepayLoan'){
            window.location.href="${pageContext.request.contextPath}/customer/goRepayLoan?loanId="+loanId;
        }
        else if (action == 'goRepayDetail'){
            window.location.href="${pageContext.request.contextPath}/customer/loanRepayDetailPage?loanId="+loanId;
        }
    }
</script>

<%@ include file="../footer.jsp"%>