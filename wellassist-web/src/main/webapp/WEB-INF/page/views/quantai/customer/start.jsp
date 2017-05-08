<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<link rel="stylesheet"
	href="<c:url value="/resources/sys/css/gyl.css"/>">

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/pagetempl.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/maifangzhuce/styles.css"/>">


<link rel="stylesheet"
	href="<c:url value="/resources/sys/css/reg.css"/>">
<script src="<c:url value="/resources/basic/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/basic/js/bootstrap-fileupload.js"/>">
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
					<span style="float:left;">dondolari</span>
					<span style="float: right;"><a href="/customer/ordergoods">管理员</a></span>
				</div>
			</div>
		</div>
		
		<div align=center style="padding-top:12px;">
			<div align=left style="width:1000px;height:100%;">
				<img src="../resources/wella/images/customer/maifangbanner.png" style="width:1000px;">
				<div class="start_heading2">昆仑燃气</div>
				<div style="display:flex;">
					<div style="float:left;width:200px;height:200px;background:white;border:solid 1px #c0c0c0;">
						<table style="width:100%;height:100%;"><tr><td><p align=center><img src="../resources/wella/images/customer/mark.png"></p></td></tr></table>
					</div>
					<div style="float:left;margin-left:20px;margin-right:20px;width:504px;height:200px;background:white;border:solid 1px #c0c0c0;">
						<div style="padding-left: 32px;padding-top: 16px;font-size: 24px;">公告栏</div>
						<div style="padding-left:64px;padding-top: 12px;font-size: 16px;">1.2016-05-11 昆仑能源驱动 “两学一做” 学习教育</div>
						<div style="padding-left:64px;padding-top: 6px;font-size: 16px;">2.2016-05-11 昆仑能源驱动 “两学一做” 学习教育</div>
						<div style="padding-left:64px;padding-top: 6px;font-size: 16px;">3.2016-05-11 昆仑能源驱动 “两学一做” 学习教育</div>
						<div style="padding-left:64px;padding-top: 6px;font-size: 16px;">4.2016-05-11 昆仑能源驱动 “两学一做” 学习教育</div>
						<div style="padding-left:64px;padding-top: 6px;font-size: 16px;">5.2016-05-11 昆仑能源驱动 “两学一做” 学习教育</div>
						<div style="padding-left:64px;padding-top: 6px;font-size: 16px;">6.2016-05-11 昆仑能源驱动 “两学一做” 学习教育</div>
					</div>
					<div style="float:left;width:250px;height:200px;background:white;border:solid 1px #c0c0c0;">
						<div style="padding-left: 32px;padding-top: 16px;font-size: 24px;">联系方式</div>
						<table style="width:100%;margin-top:32px;font-size:16px;">
						<tr>
							<td style="text-align:right;">联系人:</td>
							<td>
								<img src="../resources/wella/images/customer/icon1.png" style="float:left;">
								<img src="../resources/wella/images/customer/icon2.png" style="float:left;">
								<img src="../resources/wella/images/customer/icon3.png" style="float:left;">
							</td>
						</tr>
						<tr>
							<td style="text-align:right;">联系电话:</td>
							<td>12345678912</td>
						</tr>
						<tr>
							<td style="text-align:right;">联系地址:</td>
							<td>浙江省杭州市</td>
						</tr>
						</table>
					</div>
				</div>
				
				<div class="start_heading2">企业介绍</div>
				<div style="font-size:16px;">
				重石油昆仑燃气有限公司（简称昆仑燃气公司）市委实现天然气业务上中下游一体化，更好地履行责任，
			服务社会， 经中国石油天然气集团公司批准、国家工商管理总局核准于2008年8月6日， 由中石油天然气管道燃气投资有限公司、中国华油集团燃气事业部、中油然气有限责任公司、重组整合成立， 是中国石油城市燃气运菅的专业化公司。公司注册资本金60.6亿元。
				</div>

				<div class="start_heading2">产品列表</div>
				

				<div style="padding-bottom:48px;">
					<table style="border-spacing:18px;">
						<tr>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
							<td style="padding:8px;">
 								<div style="background: white;display: inherit;box-shadow: 3px 3px 3px #c0c0c0;padding:4px;">
									<div><img src="../resources/wella/images/customer/wares.png"></div>
									<div style="padding:4px;">
										<span class="ware_maker_label">昆仑燃气</span>
										<span class="ware_button" style="float:right;">下单</span>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

</div>
</div>
</body>
</html>