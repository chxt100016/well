<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/seller/register.css"/>">


<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>">
</script>

<style>
	.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:bold;font-size:24px;}
	.ware_button{float:right;color: white;background: red;padding: 6px;border-bottom-left-radius: 8px;border-top-left-radius: 8px;border-top-right-radius: 8px;border-bottom-right-radius: 8px;width: 61px;text-align: center;cursor:hand;font-size:16px;}
	.ware_maker_label{color: red;padding: 6px;float: left;}
	.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
</style>

</head>
<body style="margin:0; padding:0">
	<div class="main-wrapper" style="margin-left: 0px;">

		<!-- navigation bar -->
		<div class="abovenavbar">
			<div align=center>
				<div style="width:1000px;">
<!-- 					<span style="float:left;">WellAssist供应链系统</span> -->
 					<span style="float: right;"><a href="<c:url value="/front/SellerHomeController-main"/>">管理员</a></span>
				</div>
			</div>
		</div>
		
		<div align=center style="padding-top:12px;">
			<div align=left style="width:1000px;height:100%;">
				<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 56px;">
					
					<div style="text-align:center;font-size:24px;">产品详情(${id})</div>
				</div>
				<div style="display:flex;">
					<div style="float:left;width:140px;height:140px;background:white;border:solid 1px #c0c0c0;box-shadow: 3px 3px 3px #c0c0c0;">
						<table style="width:100%;height:100%;"><tr><td><p align=center><img style="width:120px;height:120px;" src="<c:url value="/resources/upload/images/company_mark/kunlun.png"/>"></p></td></tr></table>
					</div>
					<div style="float:left;margin-left:8px;margin-right:20px;height:140px;">
						<div style="padding-left: 32px;padding-top: 16px;font-size: 24px;margin-bottom:12px;">昆仑燃气 进口LNG现货</div>
						<div style="padding-left:64px;padding-top: 12px;font-size: 15px;">
							<table>
								<tr style="height:28px;">
									<td style="width:150px;">品类: 管道气</td>
									<td style="width:150px;">数量: 25吨</td>
									<td style="width:250px;">货运方式: 配送</td>
								</tr>
								<tr style="height:28px;">
									<td>所在地: 浙江 杭州</td>
									<td>联系人: 王莫莫</td>
									<td>联系电话: 1234567890</td>
								</tr>
								<tr style="height:28px;">
									<td>价格: <span style="color: red;font-size:16px;font-weight:bold;">￥300000</span></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</div>
					</div>
					<div style="float:left;width:250px;height:200px;">
						<div id="lijigoumai" class="redbutton" style="margin-left: 86px;margin-top: 16px;padding: 12px;font-size: 24px;">立即购买</div>
					</div>
				</div>

				<div style="display:flex;">
					<div style="float:left;margin-right:24px;width:332px;height:252px;">
						<img src="<c:url value="/resources/upload/images/product_icon/p000001.png"/>">
					</div>
					<div style="float:left;width: 640px;text-align: justify;">
						<div class="start_heading2">产品简介</div>
						<div style="font-size:16px;" >
						中石油昆仑燃气有限公司（简称昆仑燃气公司）市委实现天然气业务上中下游一体化，更好地履行责任，
					服务社会， 经中国石油天然气集团公司批准、国家工商管理总局核准于2008年8月6日， 由中石油天然气管道燃气投资有限公司、中国华油集团燃气事业部、中油然气有限责任公司、重组整合成立， 是中国石油城市燃气运菅的专业化公司。公司注册资本金60.6亿元。
						</div>
					</div>
				</div>				

				<div class="start_heading2">其他产品</div>
				

				<div style="padding-bottom:48px;">
					<table style="border-spacing:18px;">
						<tr>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div>
										<a href="<c:url value="/qt/seller/chanpinxiangqing"/>">
										<img src="<c:url value="/resources/upload/images/product_icon/wares.png"/>">
										</a>
									</div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="<c:url value="/resources/upload/images/product_icon/wares.png"/>"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="<c:url value="/resources/upload/images/product_icon/wares.png"/>"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>										
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="<c:url value="/resources/upload/images/product_icon/wares.png"/>"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>										
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
	$("#lijigoumai").click( function(){
		window.location.href="<c:url value="/front/seller/write_ordersheet?id=kunlun_ware"/>";
	});

	$("#back").click( function(){
		history.back();
	});
</script>
</html>