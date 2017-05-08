<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align:left;">&nbsp;&nbsp;发票列表</div>

	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/customer/FinanceController-fapiaoList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="billState" name="billState" value="${param.billState}">
		<%--
		<div class="row-header">
		     <span class="header-title">订单列表</span>
		     <div style="float:right;">
		     	<input type="text" name="prodName" style="height:10px; float:left;margin-bottom:0px;" value="${param.prodName}"/>
		     	<span class="span_search_btn_blue" style="margin-bottom:0px;" onclick="searchData(1);">搜索</span>
		     </div>
		</div>
		--%>
	</form>
	
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;height:20px;">
		<div style="width:20%;text-align:center;font-size:16px;float:left;">发票编号</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">开票单位</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">开票金额</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">
			<span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#billState').val('');searchData(1);">全部</div>
					<div onclick="$('#billState').val('0');searchData(1);">发送中</div>
					<div onclick="$('#billState').val('1');searchData(1);">发送确认</div>
				</div>
			</span>
		</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>
	<c:forEach var="item" items="${fapiaoList}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;"">
			<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
				<div class="graybox" style="width:20%;line-height:60px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
					${item.billNo}	
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:60px;line-height: 60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					${item.billUnit}
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:60px;line-height: 60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					${item.billMoney}元
				</div>
				<div class="grayboxwithoutleft" style="width:20%;height:60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
					<table style="width:100%;height:40px;text-align:center;margin-top:10px;">
						<tr>
							<td style="color:#a00;">
								<c:if test="${item.billState=='0'}">发送中</c:if>
								<c:if test="${item.billState=='1'}">发送确认</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div class="grayboxwithoutleft" style="height:60px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
					<c:if test="${item.billState=='0'}">
						<span class="span_btn" onClick="toURL('fsqr', '${item.billId}')">发送确认</span>
					</c:if>
				</div>
			</div>
		</div>
	</c:forEach>
	<c:if test="${fapiaoList== null || fn:length(fapiaoList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	
	function toURL(action, billId){
		var url = "";
		if(billId!=''){
			if(action=='fsqr'){
				if(confirm("你要确定确认操作吗？")){
					$.post("${pageContext.request.contextPath}/front/customer/FinanceController-setFapiao",{billId:billId},function(data){
			    		data = $.parseJSON(data);
			    		alert(data.content);
			            if(data.status=="1"){
			            	window.location.reload();
			            }
			      	})
			      	.error(function(data){
			      		alert("操作失败！")
			      	});
				}
			}
		}
	}
</script>

<%@ include file="../footer.jsp"%>