<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/customer/register.css"/>">
<title>买家网站</title>

<script src="<c:url value="/resources/wella/front/js/operation.js"/>"></script>

<style>
	.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:500;font-size:24px;}
	.ware_button{float:right;color: white;background: #ff5500;padding: 6px;width: 61px;text-align: center;cursor:hand;font-size:16px;}
	.ware_maker_label{color: #ff5500;padding: 6px 0px;float: left;font-size:14px}
	.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
	.home-table img{width:100%;}
	.img-title{width:100%; background:rgba(0, 0, 0, 0.5); position:absolute; bottom: 0px;}
	.img-title>span{padding:10px;line-height:30px;color:#ffffff; }
	
	.cd-rect{width: 1000px; margin: auto;overflow: auto;}
	.row-fld{width:230px; background: white;box-shadow: 3px 3px 3px #c0c0c0;padding:0px;overflow: auto; float: left; margin-top:10px; margin-right:20px;}
	.sp_img{width:230px; height:169px;}
</style>

</head>
<body style="margin:0; padding:0">
	<div class="main-wrapper" style="margin-left: 0px;">

		<!-- navigation bar -->
		<div class="abovenavbar">
			<div align=center>
				<div style="width:1000px;">
					<span class="dropdown" style="float:left;">
						<a data-toggle="dropdown" class="dropdown">${user.userName}&nbsp;<b class="icon-angle-down"></b></a>
						<div class="dropdown-menu">
							<div id="logout">退出登录</div>
						</div>
					</span>
					<span style="float: right;"><a href="<c:url value="/seller/productList"/>">管理员</a></span>
				</div>
			</div>
		</div>
		
		<div align=center style="padding-top:12px;">
			<div align=left style="width:1000px;height:100%;">
				<img src="<c:url value="/resources/wella/front/images/customer/banner.png"/>" style="width:1000px;">
				<div class="start_heading2">昆仑燃气</div>
				<div style="display:block; overflow:auto">
					<div style="float:left;width:200px;height:200px;background:white;border:solid 1px #c0c0c0;">
						<table style="width:100%;height:100%;"><tr><td><p align=center><img src="<c:url value="/resources/upload/images/company_mark/kunlun.png"/>"></p></td></tr></table>
					</div>
					<div style="float:left;margin-left:20px;margin-right:20px;width:504px;height:200px;background:white;border:solid 1px #c0c0c0;">
						<div style="padding-left: 10px;padding-top: 16px;font-size: 20px;font-weight:500;">公告栏</div>
						<marquee direction="up" style="height: 155px; margin-top: 5px;" loop="infinite" scrollamount="3">
							<c:forEach items="${noticeList}" var="item" varStatus="status">
		                    	<div onclick="viewNotice(${item.noticeId})" style="cursor:pointer;padding-left:25px;padding-top: 16px;font-size: 15px;color:#666666">${item.createDate} &nbsp;&nbsp; ${item.noticeTitle}</div>
		                    </c:forEach>
						</marquee>
					</div>
					<div style="float:left;width:250px;height:200px;background:white;border:solid 1px #c0c0c0;">
						<div style="padding-left: 10px;padding-top: 16px;font-size: 20px;font-weight:500;">联系方式</div>
						<table style="width:100%;margin-top:32px;font-size:15px;" >
						<tr>
							<td style="text-align:right; width:75px; padding-right:5px; height:30px">联系人:</td>
							<td>
								<img src="<c:url value="/resources/wella/front/images/customer/lianxiicon1.png"/>" style="float:left;">
								<img src="<c:url value="/resources/wella/front/images/customer/lianxiicon2.png"/>" style="float:left;">
								<img src="<c:url value="/resources/wella/front/images/customer/lianxiicon3.png"/>" style="float:left;">
							</td>
						</tr>
						<tr>
							<td style="text-align:right; width:75px; padding-right:5px; height:30px">联系电话:</td>
							<td>${userInfo.companyLxrPhone}</td>
						</tr>
						<tr>
							<td style="text-align:right; width:75px; padding-right:5px; height:30px">联系地址:</td>
							<td>${userInfo.bgXxAddress}</td>
						</tr>
						</table>
					</div>
				</div>
				
				<div class="start_heading2" style="font-size:22px; margin-top:30px;">企业介绍</div>
				<div style="font-size:15px;line-height:150%;color:#666666">
				重石油昆仑燃气有限公司（简称昆仑燃气公司）市委实现天然气业务上中下游一体化，更好地履行责任，
			服务社会， 经中国石油天然气集团公司批准、国家工商管理总局核准于2008年8月6日， 由中石油天然气管道燃气投资有限公司、中国华油集团燃气事业部、中油然气有限责任公司、重组整合成立， 是中国石油城市燃气运菅的专业化公司。公司注册资本金60.6亿元。
				</div>

				<div class="start_heading2" style="font-size:22px; margin-top:30px;">产品列表</div>
				

				<div class = "cd-rect" style="padding:5px;">
					  <c:forEach var="item" items="${spList}">
							<div class = "row-fld">
								<div style="position:relative;">
									<a href="${pageContext.request.contextPath}/front/seller/SellerOrderController-prodDetail?prodId=${item.prodId}">
										<img class = "sp_img" src="${pageContext.request.contextPath}/${item.prodImg}" onerror = "noExitImg(this, '${pageContext.request.contextPath}');">
									</a>
									<div class="img-title"><span>
										<c:if test = "${item.prodType == '0'}">
											气体
										</c:if>
										<c:if test = "${item.prodType == '1'}">
											燃油
										</c:if>
										</span></div>
								</div>
								<div style="padding:10px;">
									<span class="ware_maker_label">${item.prodName}</span>
									<div style="clear:both;"></div>
								</div>
							</div>
						</c:forEach> 
				</div>
			</div>
		</div>

</div>
</body>
<script type="text/javascript">
	<%--function show_companies() {--%>
		<%--window.location.href="<c:url value="/front/customer/show_companies"/>";--%>
	<%--}--%>
	
	$("#logout").click( function(){
		window.location.href="<c:url value="/front/front/SellerLoginController-logout"/>";
	});
	
	function viewNotice(id){
		
	}
</script>
</html>