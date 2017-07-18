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
    <div class="ui container segment" id="app1" style="width:990px ;left: 70px; top: -14px;">
        <h3>授信详情</h3>
        <div class="ui divider"></div>
        <div class="ui three column grid">
            <div class="row centered">
                <div class="column">
                    <h4>授信额度</h4>
                </div>
            </div>
            <div class="row" style="display: inline-table">
                <div class="column">总额度：<span>${sxMoney}</span> <span class="grey"> 万元</span></div>
                <div class="column">到期日期：<span></span></div>
                <div class="column">可用额度:<span>xxx</span> <span class="grey"> 万元</span></div>
            </div>
            <div class="row" style="display: inline-table">
                <div class="column">我的借款：<span>xxx</span> <span class="grey"> 万元</span></div>
                <div class="column">未还款：<span></span> <span class="grey"> 万元</span></div>
                <div class="column">还款日期:<span></span> </div>
            </div>

            <div class="row" style="display: inline-table">
                <div class="column" ><button class="ui positive button" onClick = "toURL('sxSq')">申请/修改授信额度</button></div>

            </div>

        </div>
         <div class="ui divider"></div>
         
        <table class="ui celled striped  table ">
            <thead>
                <tr>
                    <th colspan="6">借款记录 </th>
                </tr>
                <tr class="teal-bg ">
                    <th class="teal-bg">申请时间</th>
                    <th class="teal-bg">授信详情</th>
                     <th class="teal-bg">授信金额(万元)</th>
                    <th class="teal-bg">利率(%)</th>
                     <th class="teal-bg">尾款剩余(万元)</th>
                     <th class="teal-bg center aligned">操作</th>
                </tr>
                
            </thead>
            <tbody> 
                <tr>
                    <td>2017-7-14</td>
                     <td >申请成功</td>
                     <td>100</td>
                     <td>0.0126</td>
                     <td>100</td>
                     <td class="center aligned">
                         <button class="ui positive button">还款</button>
                     </td>
                </tr>
            </tbody>

        </table>

    </div>

</body>
<script>
    // 功能函数
    function toURL(action){
        var url = "";
        if(action == 'sxSq'){
            url = "${pageContext.request.contextPath}/customer/creditApply";
            window.location.href = url;
        }
    }
</script>

<%@ include file="../footer.jsp"%>