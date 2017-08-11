<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE HTML>
<html lang="en">


    <title>维助供应链管理公司</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/library/css/semantic.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome.css"/>">
   <link rel="stylesheet" href="<c:url value="/resources/wella/front/css/common.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/wella/front/css/header.css"/>">
       
    
    <script src="<c:url value="/resources/library/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/library/js/semantic.min.js"/>"></script>
    <script src="<c:url value="/resources/common/js/global.js"/>"></script>
    <script src="${pageContext.request.contextPath}/resources/common/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/library/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/js/common.js"></script>
     <script src="<c:url value="/resources/library/js/vue.min.js"/>"></script>


    <!-- <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script> -->
    <style>
       
        .container1{
            float: right;
            width: 100%;
            min-height:990px;
      
          
        }
        .container2{
            margin-left: 210px;
        }

    </style>
</head>

<body>
    <div class="ui vertical inverted sticky  accordion  menu fixed top grey3" style="height: 800px !important;width:210px ;margin-right:-210px;" id="leftMenu">
        <div class="item leftheader grey2">
            <a class="ui logo icon image" href="/">
                <h2>管理员</h2>
            </a>
            <a href="/"><b>UI 文档</b></a>

        </div>
        <a class="item grey3  ">  <i class="user icon"></i> 我的工作台 </a>

    <!-- parentMenu==5 -->
      
   <!-- parentMenu==1 -->
        <div class="item">
            <a class="title <c:if test = "${parentMenuNo == '1' }"> active</c:if>">
            <i class="dropdown icon"></i>
            订单中心
            </a>
          <div class="content <c:if test = "${parentMenuNo == '5' }"> active</c:if>">
                <div class="accordion transition visible" style="display: block !important;margin-top:0px">

                    <div onclick="window.location.href='${pageContext.request.contextPath}/sender/logisticsOrderList'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '1'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span >订单列表</span>
					</div>
					<div onclick="window.location.href='${pageContext.request.contextPath}/sender/logisticsGrabList'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '1'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span >抢单列表</span>
					</div>
					  
					<div onclick="window.location.href='${pageContext.request.contextPath}/sender/vehicleGrabHall'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '1'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span >抢单大厅</span>
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

                    <div onclick="window.location.href='${pageContext.request.contextPath}/sender/accountInfo'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '2'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 账户信息</span>
                    </div>     
                    <div onclick="window.location.href='${pageContext.request.contextPath}/sender/withdrawRecordList'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '2'&&childMenuNo == '6' }"> font-white</c:if> ">
                       提现记录
                    </div>
                    <div  onclick="window.location.href='${pageContext.request.contextPath}/sender/rechargeRecord'" class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '2'&&childMenuNo  == '5' }"> font-white</c:if> ">
                       充值记录
                    </div>                  
                </div>
            </div>
            
        </div>

          <!-- parentMenu==6-->
        

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

                    <div onclick="window.location.href='${pageContext.request.contextPath}/sender/companyInfo'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '1' }"> font-white</c:if> ">
                       <span > 公司信息</span>
                    </div>
                    <!-- <div onclick="window.location.href='${pageContext.request.contextPath}/seller/contact'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '2' }"> font-white</c:if> ">
                       <span > 联系方式</span>
                    </div>   -->
                    <div onclick="window.location.href='${pageContext.request.contextPath}/sender/password'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '3' }"> font-white</c:if> ">
                       <span > 修改密码</span>
                    </div> 
                    <div onclick="window.location.href='${pageContext.request.contextPath}/sender/bankcardPage'"  class="content pd-10 pointer  font-grey  <c:if test = "${parentMenuNo == '4'&&childMenuNo  == '4' }"> font-white</c:if> ">
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
                        <a class="item" href="${pageContext.request.contextPath}/login/out">离开</a>

                    </div>
                </div>
                <div class="item" style="padding:5px 40px 5px 20px ">
                    <img class="ui avatar image" src="${userInfo.companyImg}" style="width:40px ;height:40px">
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
