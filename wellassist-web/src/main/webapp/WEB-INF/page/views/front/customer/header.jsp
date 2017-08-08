<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>维助供应链管理公司</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/library/css/semantic.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome.css"/>">

    	<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/header.css"/>">
    
    <script src="<c:url value="/resources/library/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/library/js/semantic.min.js"/>"></script>
    <script src="${pageContext.request.contextPath}/resources/common/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/library/js/My97DatePicker/WdatePicker.js"></script>
     <script src="<c:url value="/resources/library/js/vue.min.js"/>"></script>


    <!-- <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script> -->
    <style>
       
        .container{
            border: 1px solid #adadad;
            margin-left: 210px;
            float: left;
            width: 100%;
            height:990px;
            padding-top: 75px;
            padding-left: 30px;
        }

    </style>
</head>

<body>
    <div class="ui vertical inverted sticky  accordion  menu fixed top grey3" style="height: 800px !important;" id="leftMenu">
        <div class="item leftheader grey2">
            <a class="ui logo icon image" href="/">
                <h2>管理员</h2>
            </a>
            <a href="/"><b>UI 文档</b></a>
        </div>
        <a class="item grey3  ">  <i class="user icon"></i> 我的工作台 </a>

    <!-- parentMenu==5 -->
        <div class="item ">
            <a class="  title font-white  <c:if test = "${parentMenuNo == '5' }"> active</c:if>">
            <i class="dropdown icon " ></i>
                    产品中心
            </a>
            <div class="content <c:if test = "${parentMenuNo == '5' }"> active</c:if> ">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/prodList'"  class="content pd-10 pointer font-grey  <c:if test = "${parentMenuNo == '5' }"> font-white</c:if>  ">
                       产品大厅
                    </div>
                   
                </div>
            </div>
        </div>
   <!-- parentMenu==1 -->
        <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '1' }"> active</c:if>">
            <i class="dropdown icon"></i>
            订单中心
            </a>
          <div class="content <c:if test = "${parentMenuNo == '1' }"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/orderList'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '1'&&childMenuNo == '1' }"> font-white</c:if> ">
                       <span >订单列表</span>
                    </div>  
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/logisticsInfoList'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '1'&&childMenuNo == '2' }"> font-white</c:if> ">
                       <span >物流订单</span>
                    </div>                    
                </div>
            </div>
            
        </div>

         <!-- parentMenu==2 -->
        <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '2' }"> active</c:if>">
            <i class="dropdown icon"></i>
            财务中心
            </a>
          <div class="content <c:if test = "${parentMenuNo == '2' }"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/accountInfo'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '2'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 账户信息</span>
                    </div>     
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/withdrawRecordList'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '2'&&childMenuNo == '2' }"> font-white</c:if> ">
                       提现记录
                    </div>
                    <div  onclick="window.location.href='${pageContext.request.contextPath}/customer/rechargeRecord'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '2'&&childMenuNo  == '5' }"> font-white</c:if> ">
                       充值记录
                    </div>                  
                </div>
            </div>
            
        </div>

          <!-- parentMenu==6-->
        <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '6'}"> active</c:if>">
            <i class="dropdown icon"></i>
            授信中心
            </a>
          <div class="content <c:if test = "${parentMenuNo == '6'}"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/creditAccount'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '6'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 授信账户</span>
                    </div>     
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/creditApplys'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '6'&&childMenuNo  == '2' }"> font-white</c:if> ">
                       <span >  申请记录</span>
                    </div>
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/loansRepayRecords'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '6'&&childMenuNo  == '3' }"> font-white</c:if> ">
                        <span > 还款记录</span>
                    </div>                  
                </div>
            </div>
            
        </div>

          <!-- parentMenu==7-->
        <!-- <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '7'}"> active</c:if>">
            <i class="dropdown icon"></i>
              发票管理
            </a>
          <div class="content <c:if test = "${parentMenuNo == '7'}"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/front/customer/FinanceController-fapiaoList'"  class="content pd-10 pointer  font-grey  <c:if test = "${childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 发票记录</span>
                    </div>     
                                     
                </div>
            </div>
            
        </div> -->
        
         <!-- parentMenu==3-->
        <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '3'}"> active</c:if>">
            <i class="dropdown icon"></i>
            消息中心
            </a>
          <div class="content <c:if test = "${parentMenuNo == '3'}"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='#'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '3'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 消息列表</span>
                    </div>     
                                     
                </div>
            </div>
            
        </div> 

          <!-- parentMenu==4-->
        <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '4'}"> active</c:if>">
            <i class="dropdown icon"></i>
            账户设置
            </a>
          <div class="content <c:if test = "${parentMenuNo == '4'}"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/companyInfo'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 公司信息</span>
                    </div>
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/contact'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '2' }"> font-white</c:if> ">
                       <span > 联系方式</span>
                    </div>  
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/password'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '3' }"> font-white</c:if> ">
                       <span > 修改密码</span>
                    </div> 
                    <div onclick="window.location.href='${pageContext.request.contextPath}/customer/bankcardPage'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '4' }"> font-white</c:if> ">
                       <span > 银行卡管理</span>
                    </div>  
                          
                         
                                     
                </div>
            </div>
            
        </div> 


    </div>



    <div class="topheader">
        <div class="ui mini menu grey1">

            <div class="right menu">
                <div class="ui dropdown item">${user.userName}&nbsp;您好 <i class="dropdown icon"></i>
                    <div class="menu">
                        <a class="item">离开</a>

                    </div>
                </div>
                <div class="item" style="padding:5px 40px 5px 20px ">
                    <img class="ui avatar image" src="../img/xx.jpg" style="width:40px ;height:40px">
                </div>
            </div>
        </div>

    </div>



</body>
<script>
    var screenH = document.body.scrollHeight;
    $("#leftMenu").height(screenH);
    $('.ui.dropdown').dropdown()
        ;
    $('.ui.accordion')
        .accordion()
        ;

</script>

</html>