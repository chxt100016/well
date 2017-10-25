<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style>
	.container1{min-height: 990px;width: 100%;}
	span.smallbutton{border: solid 1px #d0d0d0;cursor: pointer;padding: 4px 8px;text-align: center;color: #666666;font-size: 13px;border-radius: 4px;}
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
</style>

</head>
<div class="container1">
	<div style="margin:40px 0 0 210px;">
		<div id = "content-rect" style="width:90%;">	
			<div class = "row-header" style="border-bottom:1px solid #d0d0d0;padding-bottom:10px;"><span class = "header-title">账户余额</span></div>
			<div class = "row1">
				<div class = "row1_2">
					<span class="col1" id=''>
						<div class="ui active inline loader" id='loader'></div>
						<span id='balance'></span>
					</span>
					<span class="col2">&nbsp;&nbsp;元</span>
					<span id="fillmoney" class="smallbutton" style="margin-left:32px;" onClick="toURL('czSqList')">充值</span>
					<span id="getmoney" class="smallbutton" style="margin-left:12px;" onClick="toURL('txList')">提现</span>
					<span id="reload" class="smallbutton" style="margin-left:12px;" onClick="reLoad()">刷新</span>
				</div>
			</div>
			<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/seller/FinanceController-accountInfo">
				<input type="hidden" id="page" name="page" value="${param.page}">
			</form>
			<div class = "row2">交易记录</div>
		
			<table class="ui very basic table" style="text-align:center;">
			    <thead>
			        <tr>
			            <th width="36%">时间</th>
			            <th width="32%" style="border-bottom: 15px solid #fff;border-left:1px solid #d0d0d0;border-right: 1px solid #d0d0d0;padding: 0;">金额(元)</th>
			            <th width="32%">明细</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var = "item" items = "${list}">
			        <tr>
			            <td>[<fmt:formatDate value="${item.completeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>]</td>
			            <td>${item.jySjMoney}</td>
			            <c:if test = "${item.jyState == '2'}">
			                <c:set value="${ fn:split(item.content, ';') }" var="names" />
			                <c:forEach items="${ names }" var="name">
			                    <td>${name}</td>
			                </c:forEach>    
			            </c:if>
			        </tr>
			        </c:forEach>
			        <c:if test="${list== null || fn:length(list) == 0}">
						<tr>
							<td colspan="3">没有资料</td>
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

<script type = "text/javascript">
	// 功能函数
	function toURL(action){
		var url = "";
		if(action == 'txSq'){
			var userMoney = ${userMoney};
			if(parseInt(userMoney) <= 0){
				alert("账号金额不够！");
				return;
			}
			url = "${pageContext.request.contextPath}/sender/FinanceCtrl-txSq?userMoney="+userMoney;
			window.location.href = url;
		}else if(action == 'czSq'){
			url = "${pageContext.request.contextPath}/sender/FinanceController-czSq";
			window.location.href = url;
		}
		
	}
</script>
<script>
		//获取账户余额
	  const urrr = '${pageContext.request.contextPath}/finance/localBalance';
	  const syurl='${pageContext.request.contextPath}/finance/syncBalance';
	
		$(function () {
			$.ajax({
				type: 'get',
				url: urrr,
				data: '',
				dataType: 'json',
				success:
				function (result) {
					if (result.code == 0) {
						let bal= result.balance;
						console.log(result.msg);
						 $('#balance').html(bal)
						 $('#loader').fadeOut();
					}
					else {
						console.log(result.msg)
					}
				}
			})
		})
		function reLoad() {
			$('#loader').fadeIn();
			$('#balance').html(' ');
			
			$.ajax({
				type: 'get',
				url: syurl,
				data: '',
				dataType: 'json',
				success:
				function (result) {
					if (result.code == 0) {
						let bal= result.balance;
						console.log(result.msg);
						 $('#balance').html(bal)
						 $('#loader').hide();
					}
					else {
						console.log(result.msg)
					}
				}
			})
			 
		}
  </script> 
<%@ include file="../footer.jsp"%>