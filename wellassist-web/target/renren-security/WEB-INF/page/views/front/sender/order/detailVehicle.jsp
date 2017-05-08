<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<style>
	body{
		background: #f5f5f5;
	}
	div.formDd{
		width:1000px;
		text-align: left; 
		background: white;
		box-shadow: 2px 2px 8px #808080;
		padding:16px 0px 16px 0px;
	}
	div.headDd{
		margin-left:48px;
		margin-right:48px;
		padding-top:32px;
		margin-bottom: 24px;
		padding-bottom:12px;
		border-bottom:solid 2px #d0d0d0;
		font-size:18px;
	}
	div.rowDd{
		clear: both;
		height: 50px;
		line-height: 50px;
		font-size: 14px;	
	}
	.rowDd .labeldd{
		float: left;
		width: 100px;
	}
	.rowDd .contentdd{
		float: left;
		margin-left: 15px;
	}
	.backBtn{
		font-weight: bold;
    	cursor: pointer;
    	color: #2482df;
    	text-align:center;
    	font-size:16px;
    	float:left;
    	margin-top:6px;
	}
</style>	
<div style="margin-left: 0px; margin-top:20px;">
	<div id="wlxxDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
				
				<div style="text-align:center;font-size:24px;">物流信息</div>
			</div>
		</div>
		<form id="wlxxForm" method="post">
			<div class="formDd">
				<div class="headDd">物流信息</div>
				
				<c:forEach var="item" items="${vehicleList}">
					<div style="border:solid 1px #d0d0d0;font-size:24px;margin:16px 48px;overflow:auto;">
						<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
							<div class="graybox" style="width:21%;height:60px;line-height:60px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
								&nbsp; 车牌号 : ${item.vehicleNo}
							</div>
							<div class="grayboxwithoutleft" style="width:20%;height:60px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
								<table style="width:100%;height:40px;margin-top:10px;">
									<tr>
										<td>
											&nbsp; 司机名称 : ${item.sjLxr}
										</td>
									</tr>
									<tr>
										<td>
											&nbsp; 电话 : ${item.sjLxrPhone}
										</td>
									</tr>
								</table>
							</div>
							<div class="graybox" style="width:20%;height:60px;line-height:60px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
								&nbsp; 装载量 : ${item.vehicleSjSize}吨
							</div>
							<div class="grayboxwithoutleft" style="width:25%;height:60px;font-size:14px;float:left;border:none;">
								<table style="width:100%;height:40px;margin-top:10px;">
									<tr>
										<td>
											&nbsp; 出发时间 : <fmt:formatDate value="${item.cfDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
									</tr>
									<tr>
										<td>
											&nbsp; 到达时间 : <fmt:formatDate value="${item.ddDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:if test="${vehicleList== null || fn:length(vehicleList) == 0}">
					<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
			    </c:if>
			</div>
			<input  name="pubbtn" type="button" value="返回" class="bluebutton" style="font-size:20px;border-radius:6px;" onclick="goBack();">
		</form>
	</div>
</div>

<script type="text/javascript">
	$(".backBtn").click(function(){
		goBack();
	});
</script>

<%@ include file="../footer.jsp"%>