<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style>
	span.smallbutton{cursor: pointer;padding: 4px 8px;text-align: center;color: #666666;font-size: 13px;border-radius: 4px;}
	.header-title{font-size: 15px;font-weight: 600;}
	.row1{padding:6px;font-size:20px;margin-top:16px;padding-bottom:16px;}
	.row1_1{margin-top:16px; margin-left: 12px;font-size: 16px;text-align: left;color: #747474;}
	.row1_2{margin-top:16px; margin-left: 24px;font-size: 20px; margin-bottom:16px;text-align:left;}
	.row1_2 .col1{color:red;font-size:20px;font-weight:bold;}
	.row1_2 .col2{font-size:18px;margin-left:-4px;}
	
	.row2{text-align:left; margin-top:16px;font-size:15px;font-weight: 600;padding-bottom: 10px;border-bottom: 1px solid #d0d0d0;}
	.ui.table thead th {border-bottom: 0;}
    .ui.table tr td {border-top: 0;}
    .ui.table tr:nth-child(odd) td {background-color: #f2f2f2;}
    .ui.celled.table tr td, .ui.celled.table tr th {border-left: 0;}
    .ui.menu li{list-style: none;}
    .txtab tr td{
		line-height: 50px;
    }
</style>

</head>
<div class="container1" id="app">
	  <div style="margin:40px 0 0 210px;">
		<div id = "content-rect" style="width:90%;">		
			<div class = "row-header" style="border-bottom:1px solid #d0d0d0;padding-bottom:10px;"><span class = "header-title">提现</span></div>
			<!-- <div class = "row1">
				<div class = "row1_2">
					<span class = "col1">${withdrawMoney}</span>
					<span class = "col2">&nbsp;&nbsp;元</span>
				</div>
			</div> -->
			<form action="" class="ui form txform">
			<table style="font-size: 14px;font-weight: 600;margin: 20px 60px 60px;" class="txtab">
				
				<tr>
					<td>提现金额&emsp;&emsp;&emsp;</td>
					<td class="field">
						<div class="ui input" style="width:220px">
							<input placeholder="请输入你的提现金额" type="text" v-model='selCards.withdrawMoney' name='money'>
						</div>&emsp;元
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>提现账户&emsp;&emsp;&emsp;</td>
					<!-- <td style="text-align:center;">303**********3030&emsp;&emsp;</td> -->
					<!-- <td>（已绑定中信银行）&emsp;&emsp;</td> -->
					<td class="field">
						<select class="ui fluid normal dropdown " style="width:250px" id="bankS"  @change='secBank()' name='account'>
							<i class="dropdown icon"></i>
							      <option class="default text" value="">请选择提现账户</option> 
								 <option  class="item" v-for='(card,index) in Cards' v-bind:value="index" >
									 <span @click="selected(index,card.bankName,card.bankAccountNew)"> {{card.bankName}} *** </span>
									  <span class="bkAcc">{{card.bankAccountNew}}</span>
								</option >
								
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>&emsp;&emsp;<div class="ui primary button submit" @click="crash">提现</div></td>
					<td></td>
					<td></td>
				</tr>
				
			</table>
			</form>
			<form id="searchFrm" method="get" action="${pageContext.request.contextPath}/customer/withdrawRecordList">
				<input type="hidden" id="page" name="page" value="${param.page}">
				<input type="hidden" id="withdrawState" name="withdrawState" value="${param.withdrawState}">
			</form>
			<div class = "row2">提现记录</div>
		
			<table class="ui very basic table" style="border:0;text-align:center;">
			    <thead>
			        <tr>
			            <th width="27%">时间</th>
			            <th width="26%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;padding: 0;">账户</th>
			            <th width="24%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;padding: 0;">金额(元)</th>
			            <th width="23%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;padding: 0;">
			            <div class="ui compact">
			                <div class="ui simple dropdown item">进度
			                    <i class="dropdown icon"></i>
			                        <div class="menu">
			                          <div class="item" onClick = "setTxState('');">全部</div>
			                          <div class="item" onClick = "setTxState('0');">申请</div>
			                          <div class="item" onClick = "setTxState('1');">待付款</div>
			                          <div class="item" onClick = "setTxState('-1');">不通过</div>
			                          <div class="item" onClick = "setTxState('2');">已付款</div>
			                        </div>
			                    </div>
			                </div>
			            </th>
			        </tr>
			    </thead>
			    <tbody>
		        <c:forEach var = "item" items = "${list}">
		        <tr>
		            <td>[<fmt:formatDate value="${item.withdrawDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</td>
		            <td>${item.account}</td>
		            <td style="color:#009900;">${item.withdrawMoney}</td>
		            <td>
		                <c:if test = "${item.withdrawState == '-1'}">
		                        不通过
		                </c:if>
		                <c:if test = "${item.withdrawState == '0'}">
		                        申请
		                </c:if>
		                <c:if test = "${item.withdrawState == '1'}">
		                        待付款
		                </c:if>
		                <c:if test = "${item.withdrawState == '2'}">
		                        已付款
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
	// 功能函数
	
	
	// 状态选择函数
	$(function(){

	
$('.txform')
  .form({
	  fields: {
		  money:{
			   identifier: 'money',
				rules: [
				{
					type   : 'empty',
					prompt : '请输入金额'
				},
				{
				 type: 'number',
				 prompt:'请输入数字'	
				}
			]
		  },
		  account: {
			   identifier: 'account',
				rules: [
				{
					type   : 'empty',
					prompt : '请选择提现账户'
				}
			]
		  }
	  },
	  on: 'blur',
	  inline : true,
	onSuccess: function(e) {
        //阻止表单的提交
        e.preventDefault();
    }
  })	

  })

const url1= '${pageContext.request.contextPath}/userinfo/getCards';
const url2= '${pageContext.request.contextPath}/finance/withdrawProcess';
const vm = new Vue({
  el:"#app",
  data:{
   Cards:'',
   selCards:{
	   bankcardId:'',
	   withdrawMoney:'',
   }
//    selected:''
  },
  created:  function(){
	    var that =this;
	  $.get(url1,'',function(data){
			if(data.code==0){
			     console.log( data.Cards);
				for (var i = 0; i < data.Cards.length; i++) {
				var str='' ,str2;
				str= data.Cards[i].account;
				// str = Number(str);
				str2 = str.substring(str.length-4);
				data.Cards[i].bankAccountNew =str2;
				console.log(data.Cards[i].bankAccountNew);
			}
			 that.Cards= data.Cards
			}

			else{
				console.log('出错了')
			}
	  },'json')
  },
  methods:{
	  secBank:function(){
		   var that =this;
		  let sid= $("#bankS").val();
		  console.log(sid);
		  that.selCards.withdrawBank=that.Cards[sid].bankName;
		  that.selCards.bankcardId=that.Cards[sid].bankcardId;
		  console.log(that.selCards);
	  },
	  selected:function(index,bankName,bankAccountNew){
       console.log(index);
	  },
	  crash:function(){
		  var that =this;
					if( $('.txform').form('is valid')){
			// form is valid (both email and name)
		      	$.ajax({
						type: "post",  //提交方式  
						url: url2,//路径
						dataType: 'json',
						//  contentType: 'text/html;charset=UTF-8',
						data: that.selCards,
						success:function(result){
								if(result.code==0){
									alert("成功");
									window.location.reload()
								}
								else{
									alert(result.msg);
									window.location.reload()
								}
						}
				  })
			
			
			}
	 		 }
  
  
  }

})
</script>
<script>	
 $(function(){
	 $('.ui.dropdown')
  .dropdown()
;
 })
</script>
<%@ include file="../footer.jsp"%>