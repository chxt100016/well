<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:bold;font-size:24px;}
	.ware_button{float:right;color: white;background: red;padding: 6px;border-bottom-left-radius: 8px;border-top-left-radius: 8px;border-top-right-radius: 8px;border-bottom-right-radius: 8px;width: 61px;text-align: center;cursor:hand;font-size:16px;}
	.ware_maker_label{color: red;padding: 6px;float: left;}
	.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
	.backBtn{font-weight: bold;cursor: pointer;color: #2482df;text-align:center;font-size:16px;float:left;margin-top:6px;}
	.cd-rect{width: 1000px; margin: auto;overflow: auto;}
	.row-fld{width: 230px; background-color: white;font-size: 13px;  overflow: auto; float: left;    padding: 0px 20px 20px 0px; display: inline-block;}
</style>

<div class="main-wrapper" style="margin-left: 0px;">
	<div align="center" style="padding-top:12px;">
		<div style="width:1000px;height:100%;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 56px;">
				<div style="text-align:center;font-size:24px;">产品详情</div>
			</div>
			<div style="display:flex;">
				<div style="float:left;width:140px;height:140px;background:white;border:solid 1px #c0c0c0;box-shadow: 3px 3px 3px #c0c0c0;">
					<table style="width:100%;height:100%;"><tr><td><p align=center><img style="width:120px;height:120px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');" src="${pageContext.request.contextPath}/${prodInfo.companyImg}"></p></td></tr></table>
				</div>
				<div style="float:left;margin-left:8px;margin-right:20px;height:140px;">
					<div style="padding-left: 20px;font-size: 24px;margin-bottom:12px;text-align: left;">${prodInfo.prodName}</div>
					<div style="padding-left: 20px;padding-top: 12px;font-size: 15px;">
						<table>
							<tr style="height:28px;">
								<td>公司: ${prodInfo.companyName}
								<td></td>
								<td></td>
							</tr>
							<tr style="height:28px;">
								<td>品类: <c:if test="${prodInfo.prodType=='0'}">气体</c:if><c:if test="${prodInfo.prodType=='1'}">燃油</c:if></td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;数量: ${prodInfo.prodNum}吨</td>
								<td></td>
							</tr>
							<tr style="height:28px;">
								<td>所在地: ${prodInfo.prodRegionName}</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;联系人: ${prodInfo.prodLxr}</td>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;联系电话: ${prodInfo.prodLxrPhone}</td>
							</tr>
							<tr style="height:28px;">
								<td>价格: <span style="color:red;font-size:16px;font-weight:bold;">￥${prodInfo.prodMoney}</span></td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
				<div style="float:left;margin-left:8px;height:140px;">
				  	<input type="button" id="lijigoumai" style="padding-left: 24px;padding-right: 24px;padding-top: 8px;padding-bottom: 8px;font-size: 18px;border-radius: 6px;border:none;background: #bf3028;color: white;<c:if test = "${userInfo.userState != 1}">display:none;</c:if>" value="立即购买" />
				</div>
			</div>

			<div style="display:flex; margin-top:40px;">
				<div style="float:left;margin-right:24px;width:332px;height:252px;">
					<img onerror = "noExitImg(this, '${pageContext.request.contextPath}');" src="${pageContext.request.contextPath}/${prodInfo.prodImg}" style="width:330px; height:220px;">
				</div>
				<div style="float:left;width: 640px;text-align: justify;">
					<div class="start_heading2">产品简介</div>
					<div style="font-size:16px;" >${prodInfo.prodIntro}</div>
				</div>
			</div>				

			<div class="start_heading2">其他产品</div>

			<div style="padding-bottom:48px;">
				<div class="cd-rect">
					<c:forEach var="item"  items="${prodList}">
					<div class="row-fld" style="position:relative;">	
					   	<div style="background:white;box-shadow: 0px 0px 9px 0px #989898;">
							<div>
								<a href="${pageContext.request.contextPath}/front/seller/SellerOrderController-prodDetail?prodId=${item.prodId}">
									<img src="${pageContext.request.contextPath}/${item.prodImg}" style="width:230px; height:160px;">
								</a>
							</div>
							<div style="padding:2px;overflow: hidden;border: 1px solid #ddd;">
								<span class="ware_maker_label">${item.prodName}</span>
							</div>
						</div>
						<div style="position:absolute;height:30px;line-height:30px;width:230px;bottom:55px;left:0px;background-color:black;opacity:0.5;text-align:left;color:white;">
							<c:if test="${item.prodType=='0'}">&nbsp;&nbsp;气体</c:if>
							<c:if test="${item.prodType=='1'}">&nbsp;&nbsp;燃油</c:if>
						</div>
					</div>
					</c:forEach>
			    </div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#lijigoumai").click( function(){
		window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerHomeCtrl-makeOrder?prodId=${prodInfo.prodId}";
	});

	$(".backBtn").click(function(){
		//goBack();
		window.history.go(-1);
	});
</script>

<%@ include file="../../footer.jsp"%>
