<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>
<!-- custom css -->
<link rel="stylesheet"	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<style>
.container{margin: 0 8%;}
.header-title{font-size: 15px;font-weight: 600;}
.ui.table thead th {border-bottom: 0;}
.ui.table tr td {border-top: 0;}
.ui.table tr:nth-child(odd) td {background-color: #f2f2f2;}
.ui.celled.table tr td, .ui.celled.table tr th {border-left: 0;}
.ui.menu li{list-style: none;}
.active{color: #000 !important;}
.cztab tr td img:hover,.cztab tr td img:active{border: 1px solid #0068b7;}
</style>

</head>
<div class="container">
	<div style="width:75%;margin:0 auto;">
		<div id = "content-rect">
			<div class = "row-header" style="border-bottom:1px solid #d0d0d0;padding-bottom:10px;"><span class = "header-title">充值</span></div>
			<div style="margin:40px 60px 60px;">
				<table style="font-size: 14px;font-weight: 600;" class="cztab">
					<tr>
						<td>充值金额</td>
						<td>
							<div class="ui input" style="margin-left:15px;">
								<input placeholder="请输入你的充值金额" type="text">
							</div>
						</td>
						<td>&emsp;元</td>
						<td>&emsp;&emsp;&emsp;充值方式</td>
						<td>&emsp;<img src="img/u3142.png" /></td>
						<td>&emsp;&emsp;<button class="ui primary button">充值</button></td>
					</tr>
				</table>
			</div>
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
		
<script type = "text/javascript">
	// 状态选择函数
	function setZfState(zfState){
		$("#zfState").val(zfState);
		$("#searchFrm").submit();
	}	
</script>
<%@ include file="../footer.jsp"%>