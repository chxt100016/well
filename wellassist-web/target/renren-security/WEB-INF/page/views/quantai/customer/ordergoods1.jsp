<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<link rel="stylesheet"
	href="<c:url value="/resources/sys/css/gyl.css"/>">

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/theme-blue.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/pagetempl.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/maifangzhuce/styles.css"/>">


<link rel="stylesheet"
	href="<c:url value="/resources/sys/css/reg.css"/>">
<script src="<c:url value="/resources/basic/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/basic/js/bootstrap-fileupload.js"/>">
</script>

<style>
</style>

</head>
<body style="margin:0; padding:0">
	<div class="abovenavbar" align=center >
		<div align=left style="width:1100px;">
			<span>hasdjsd</span>
			<span style="float:right;">返回首页</span>
		</div>
	</div>

	<div class="layout">


	<div class="navbar navbar-inverse top-nav" align=center style="height:50px;">
		<div class="navbar-inner">
			<div class="container">
				<div align=left style="width:1100px;">
					<span class="title_account">
						管理员
					</span>
					<div class="nav-collapse" style="margin-left:22px;">
						<ul class="nav">
							<li class="dropdown menu-break-left">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;background:#008;" onclick="selectLeftMenu(1);">订单中心</a>
							</li>
							<li class="dropdown menu-break-left">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(2);">财务中心</a>
							</li>
							<li class="dropdown menu-break-left">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(3);">消息中心</a>
							</li>
							<li class="dropdown menu-break-left menu-break-right">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(4);">企业管理</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="main_content" align=center style="background:white;height:1000px;">
		<div align=left style="width:1100px;padding-top:32px;">
			<div id="leftmenu" class="" style="border:solid 1px #d0d0d0; height:800px;width:190px;float:left;">
				<ul>
					<li>
						<div class="leftmenulevel1" style="">订单管理</div>
					</li>
					<ul style="display: block;">
						<li>产品订单</li>
						<li>物流订单</li>
					</ul>
					<li>
						<div class="leftmenulevel1" style="">评价返馈</div>
					</li>
					<ul>
						<li>评价列表</li>
					</ul>
				</ul>		
			</div>
			<div style="float:left;padding-left:20px;">
				<div style="border:solid 1px #d0d0d0;width:870px;padding:6px;font-size:24px;">产品订单</div>
				<div style="border:solid 1px #d0d0d0;width:870px;padding:6px;font-size:24px;margin-top:16px;height:20px;">
					<div style="width:300px;text-align:center;font-size:16px;float:left;">产品详情</div>
					<div style="width:150px;text-align:center;font-size:16px;float:left;">价格</div>
					<div style="width:150px;text-align:center;font-size:16px;float:left;">状态&nbsp;<b class="icon-angle-down"></b></div>
					<div style="width:270px;text-align:center;font-size:16px;float:left;">操作</div>
				</div>

				<!--  -->
				<div style="border:solid 1px #d0d0d0;width:882px;font-size:24px;margin-top:16px;height:135px;">
					<div style="width:882px;height:24px;background:#e0e0e0;font-size:16px;">
						<span style="padding:4px 10px;">2016-09-08&nbsp;&nbsp;&nbsp;&nbsp;订单编号: 12345678</span>
					</div>
					<div class="graybox" style="width:300px;height:110px;font-size:14px;float:left;">
						<img src="${pageContext.request.contextPath}/resources/wella/images/customer/yunshuchanpin.png" style="float:left;padding-left:10px;padding-top:25px;padding-bottom:25px;width:60px;height:60px;">
						<span style="float:left;padding-left:10px;padding-top:30px;">LNG现货进口高品质</span>
						<span style="float:right;color:#a0a0a0;padding-top:40px;">20吨</span>
					</div>
					<div class="grayboxwithoutleft" style="width:150px;height:110px;font-size:14px;float:left;">
						<div style="text-align:center;padding-top:20px;">总价: 10,0000元</div>
						<div style="text-align:center;padding-top:20px;">单价: 5000元/吨</div>
					</div>
					<div class="grayboxwithoutleft" style="width:150px;height:110px;font-size:14px;float:left;">
						<div style="text-align:center;padding-top:20px;color:red;">得确认</div>
						<div style="text-align:center;padding-top:10px;">订单详情</div>
					</div>
					<div class="grayboxwithoutleft" style="width:277px;height:110px;font-size:16px;float:left;">
						<div style="height:45px;"></div>
						<span class="graybutton" style="margin-left:100px;margin-top:55px;">取消订单</span>
					</div>
				</div>
				<!--  -->
				<div style="border:solid 1px #d0d0d0;width:882px;font-size:24px;margin-top:16px;height:135px;">
					<div style="width:882px;height:24px;background:#e0e0e0;font-size:16px;">
						<span style="padding:4px 10px;">2016-09-08&nbsp;&nbsp;&nbsp;&nbsp;订单编号</span>
					</div>
					<div class="graybox" style="width:300px;height:110px;font-size:14px;float:left;">
						<img src="${pageContext.request.contextPath}/resources/wella/images/customer/yunshuchanpin.png" style="float:left;padding-left:10px;padding-top:25px;padding-bottom:25px;width:60px;height:60px;">
						<span style="float:left;padding-left:10px;padding-top:30px;">LNG现货进口高品质</span>
						<span style="float:right;color:#a0a0a0;padding-top:40px;">20吨</span>
					</div>
					<div class="grayboxwithoutleft" style="width:150px;height:110px;font-size:16px;float:left;">
						<div style="text-align:center;padding-top:20px;">10,0000元</div>
					</div>
					<div class="grayboxwithoutleft" style="width:150px;height:110px;font-size:14px;float:left;">
						<div style="text-align:center;padding-top:20px;color:red;">已发货</div>
						<div style="text-align:center;padding-top:10px;">物流详情</div>
					</div>
					<div class="grayboxwithoutleft" style="width:277px;height:110px;font-size:16px;float:left;">
						<div style="height:45px;"></div>
						<span class="redwhitebutton" style="margin-left:100px;margin-top:55px;">取消订单</span>
					</div>
				</div>

				<div style="padding-top:12px;height:24px;">
					<input type="submit" class="bluebutton" style="float:right;" value="确定">
					<div style="float:right;font-size:14px;padding-top:8px;">&nbsp;页&nbsp;&nbsp;</div>
					<input type="text" name="pageno" style="float:right;width:64px;">
					<div style="float:right;font-size:14px;padding-top:8px;">共01页 至第</div>
					<div class="graywhitebutton" style="float:right;margin-right:32px;width:24px;">&gt;</div>
					<div class="graywhitebutton" style="float:right;width:24px;backgound:#e0e0e0;">1</div>
					<div class="graywhitebutton" style="float:right;width:24px;">&lt;</div>
				</div>
			
			</div>
		</div>
	</div>
</div>

</body>
</html>