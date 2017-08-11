<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>
<!-- custom css -->
<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style>
.container1{min-height: 990px;width: 100%;}
.header-title{font-size: 15px;font-weight: 600;}
.ui.table thead th {border-bottom: 0;}
.ui.table tr td {border-top: 0;}
.ui.table tr:nth-child(odd) td {background-color: #f2f2f2;}
.ui.celled.table tr td, .ui.celled.table tr th {border-left: 0;}
.ui.menu li{list-style: none;}
.cztab tr td{line-height: 50px;}
.cztab tr td img{border: 1px solid #f2f2f2;}
.cztab tr td img:hover,.cztab tr td img:active{border: 1px solid #0068b7;}
.formtab tr td{border-top: 15px solid #fff;}
.red{color:#ff0000;}
</style>

</head>
<div class="container1">
	<div style="margin:40px 0 0 210px;" id='app'>
		<div id = "content-rect" style="width:90%;">
			<div class = "row-header" style="border-bottom:1px solid #d0d0d0;padding-bottom:10px;"><span class = "header-title">充值</span></div>
			<!-- <form id = "infoForm" action="${pageContext.request.contextPath}/customer/rechargeApply" method="post">
			<table style="font-size: 14px;font-weight: 600;margin:40px 60px 60px;" class="cztab">
				<tr>
					<td>充值金额</td>
					<td>
						<div class="ui input one-fld" style="margin-left:15px;">
							<input placeholder="请输入你的充值金额" id = "rechargeMoney" name="rechargeMoney" type="text">
						</div>
					</td>
					<td>&emsp;元</td>
				</tr>
				<tr>
					<td>充值方式</td>
					<td style="text-align:center;">&emsp;<img src="../img/zxlogo.jpg" /></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align:center;"><button class="ui primary button">充值</button></td>
				</tr>
			</table>
			</form> -->
			<form id = "infoForm" action="${pageContext.request.contextPath}/customer/rechargeApply" method="post">
			<input type = "hidden" id = "rechargeType" name = "rechargeType"/>
			
				
				<div style="width:100% ;">
					 <div class="column" style="padding-left:50px ;height:50px;padding-top:10px">
						<div class="caption middleAlign" style="font-size:14px;font-weight:600; width:90px">充值金额:</div>
						<div class="middleAlign">
							<div class = "one-fld ui input" >
								<input type="text" id = "rechargeMoney" name="rechargeMoney" placeholder="请输入你的充值金额" style="">

							</div>
						</div>
					 <div class="caption middleAlign" style="font-size:14px;font-weight:600; width:90px">元</div> 
					</div>
					 <div class="column" style="padding-left:50px ;height:50px;padding-top:10px"  >
						<div class="caption middleAlign" style="font-size:14px;font-weight:600; width:90px">充值方式:</div>
						<div class="middleAlign">
							<div class="ui floating labeled icon dropdown button">
								<i class="payment icon"></i>
							<span class="text">选择银行卡</span>
							<div class="menu" id="bankMenu"   style="width:230px">
										<!-- <div class="header">请选择您添加过的银行卡 </div> -->
										<!-- <div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国银行(1158)  </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国农业银行 </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国人民很行 </div> -->
								   <!-- <div class="header">请选择您添加过的银行卡 </div> -->
										  <div class="header">请选择您添加过的银行卡 </div>
								      <div class="item" v-for='card in Cards'><img class="ui avatar image"src="<c:url value="/resources/upload/images/bank_mark/zxyh.jpg"/>"s>  {{card.bankName}}*** <span class="bkAcc">{{card.bankAccount}}</span> </div>
									</div>
								</div>

							</div>
						</div>
					
					



<!-- 
					<tr>
						<td class="caption" style="font-size:14px;font-weight:600;">充值方式</td>
						<%--<td><img  class = "fsImg" src="<c:url value="/resources/upload/images/bank_mark/china_citic_bank.png"/>"  onclick = "selectPayFs('1', this);"></td>
						<td><img class = "fsImg  selected" src="<c:url value="/resources/upload/images/bank_mark/xianxia.png"/>"  onclick = "selectPayFs('0', this);"></td>--%>
						<td>
							<div class="ui floating labeled icon dropdown button"> -->
  								<!-- <i class="payment icon"></i> -->
									<!-- <span class="text">选择银行卡</span>
									<div class="menu"> -->
										<!-- <div class="header">请选择您添加过的银行卡 </div> -->
										<!-- <div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国银行(1158)  </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国农业银行 </div>
										<div class="item"><img class="ui avatar image" src="http://www.semantic-ui.cn/images/avatar/large/elliot.jpg"> 中国人民很行 </div>
									</div> -->
							<!-- </div>
						</td>
						<td></td> -->
					<!-- </tr> -->
					<tr>
						<td></td>
						<td style="text-align:right;">
						<div align=center style="margin-bottom: 24px;">
							<!-- <input type="button" value="返回" onclick="goBack();"> -->
							<button class="ui button primary" type="submit" id="submit">充值</button>
								<!-- <input type="submit" id="submit" value="确认充值"> -->
						</div></td>
						<td></td>
					</tr>
				</table>
		</form>
			<div class = "row-header" style="border-bottom:1px solid #d0d0d0;padding-bottom:10px;"><span class = "header-title">充值记录</span></div>
			<form id="searchFrm" method="get" action="${pageContext.request.contextPath}/front/customer/FinanceController-czList">
				<input type="hidden" id="page" name="page" value="${param.page}">
				<input type="hidden" id="zfState" name="zfState" value="${param.zfState}">
			</form>

		    <table class="ui very basic table" style="border:0;text-align:center;">
		    <thead>
		        <tr>
		            <th width="27%">时间</th>
		            <th width="27%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;padding: 0;">金额(元)</th>
		            <th width="23%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;padding: 0;">明细</th>
		            <th width="23%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;padding: 0;">
		            <div class="ui compact">
		                <div class="ui simple dropdown item">进度
		                    <i class="dropdown icon"></i>
		                        <div class="menu">
		                          <div class="item" onClick = "setZfState('');">全部</div>
		                          <div class="item" onClick = "setZfState('0');">申请</div>
		                          <div class="item" onClick = "setZfState('1');">完成</div>
		                        </div>
		                    </div>
		                </div>
		            </th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var = "item" items = "${list}">
		        <tr>
		            <td>[<fmt:formatDate value="${item.zfDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</td>
		            <td style="color:#ff0000;">${item.zfMoney}</td>
		            <td>
		                <c:if test = "${item.zfType == '0'}">
		                    线下
		                </c:if>
		                <c:if test = "${item.zfType == '1'}">
		                    银行
		                </c:if> 
		            </td>
		            <td>
		                <c:if test = "${item.zfState == '0'}">
		                    申请成功
		                </c:if>
		                <c:if test = "${item.zfState == '1'}">
		                    申请完成
		                </c:if> 
		            </td>  
		        </tr>
		        </c:forEach>
		        <c:if test="${list== null || fn:length(list) == 0}">
		            <tr>
		                <td colspan="4">没有资料</td>
		            </tr>  
		        </c:if> 
		    </tbody>
		</table>
		    <div class="right-pagination" style="text-align:center;padding-top:15px;">
				<%@ include file="../../pagination.jsp"%>
		    </div>	
		</div>
		</div>
	</div>
	
</div>
		
<script type = "text/javascript">
	// 状态选择函数
	function setZfState(zfState){
		$("#zfState").val(zfState);
		$("#searchFrm").submit();
	}	

</script>
<script type="text/javascript">
	var rechargeType = 0;
	// 选择支付方式
	function selectPayFs(selZfType, obj){
		$(".fsImg").removeClass("selected");
		$(obj).addClass("selected");
		rechargeType = selZfType;
	}
	// $('#infoForm')
	//   .form({	    
	//     inline : true,
	//     on     : 'blur',
	//     fields : {
	//     	rules: [
	//           {
	//             type: 'empty',
	//             prompt: '请输入充值金额！'
	//           },
	//           {
	//             type: 'integer',
	//             prompt: '请输入只数字形式！'
	//           },
	//           {
	//           	type:'/^\+?[1-9]\d*$/',
	//           	prompt:'充值金额不正确！'
	//           },
	//         ]
	//     }
	//   })
	// ;
	// 初始化函数
	$(function(){
		// validation检查	
    	$("#infoForm").validate({
    	    rules: {
    	    	rechargeMoney:{required: true, number:true}
    	    },
    	    messages: {
    	    	rechargeMoney:{required:"请输入充值金额！" , number:"请输入只数字形式！"}
    	    },
    	    // errorPlacement: function (error, element) { 
    	    // 	if($(element).closest('.one-fld').children().filter("div.error").length < 1) 
    		// 	$(element).closest('.one-fld').append("<div class='error-div'></div>");	
    		//     $(element).closest('.one-fld').children().filter("div.error").append(error);
    	    // },
			errorElement: "div",
		    errorClass: "error",
    	    submitHandler: function(form){
    	    	var rechargeMoney = $("#rechargeMoney").val();
    	    	if(parseInt(rechargeMoney) <= 0){
    	    		alert("充值金额不正确！");
    	    		return;
    	    	}
    	    	$("#rechargeType").val(rechargeType);
    	    	$.post($(form).attr("action"),$(form).serialize(),function(data){
    	    		alert(data.content);
    	            if(data.state==1 ){
    	            	window.location.href = "${pageContext.request.contextPath}/customer/rechargeRecord";
    	            }
    	      	}, "json");
    	    }
    	});	
		
     $('.ui.dropdown')
  .dropdown()
;
  
	

	});

	// $("td").attr("valign", "top");
	// $(".caption").css("padding-top", "10px");

	// $("input").css( "font-size", "16px");
	
	// $("#back").click( function(){
	// 	goBack();
	// });
// $('.ui.accordion').accordion();
$('.dropdown')
  .dropdown({
    // you can use any ui transition
    transition: 'drop',
	// action:'combo',
	// onclick: function(value, text, $selectedItem) {
    //  console.log(value);
    // }
  })
;
	
	
</script>

<script>	
const url1="${pageContext.request.contextPath}/userinfo/getCards"
  var vm = new Vue({
		  el:'#app',
		  data:{
           Cards:{},
		  },
		  created: function(){
			  var that =this;
               $.get(url1,'',function(data){
		  if(data.code==0){
			  console.log(data.Cards);
			//   $("#")
			for (var i = 0; i < data.Cards.length; i++) {
				var str='' ,str2;
				str= data.Cards[i].bankAccount;
				// str = Number(str);
				str2 = str.substring(str.length-4);
				data.Cards[i].bankAccount =str2;
				console.log(data.Cards[i].bankAccount);
			}
			
			 that.Cards= data.Cards
			
		  }else{
			  console.log('false')
		  }
		  
	  },'json')
		  },
    

	  })

</script>
<%@ include file="../footer.jsp"%>