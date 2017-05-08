<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Admin Panel Template">
<meta name="author" content="Westilian: Kamrujaman Shohel">
<!-- styles -->
<link rel="stylesheet"
	href="<c:url value="/resources/library/css/bootstrap.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/library/css/bootstrap-responsive.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/library/css/font-awesome.css"/>">
<!--[if IE 7]>
<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome-ie7.min.css"/>">
<![endif]-->
<link rel="stylesheet"
	href="<c:url value="/resources/library/css/chosen.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/styles.css"/>">

<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/theme-blue.css"/>">

<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/library/css/ie/ie7.css"/>">
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/library/css/ie/ie8.css"/>">
<![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/library/css/ie/ie9.css"/>">
<![endif]-->
<!--fav and touch icons -->
<link rel="shortcut icon"
	href="<c:url value="/resources/wella/common/ico/wellassist.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value="/resources/wella/common/ico/wellassist-144.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value="/resources/wella/common/ico/wellassist-114.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value="/resources/common/ico/wellassist-72.png"/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value="/resources/wella/common/ico/wellassist-57.png"/>">
<!--============ javascript ===========-->
<script src="<c:url value="/resources/library/js/jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/jquery-ui-1.10.1.custom.min.js"/>"></script>
<script src="<c:url value="/resources/library/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/wella/common/js/accordion.nav.js"/>"></script>
<script src="<c:url value="/resources/common/js/global.js"/>"></script>

</head>
<style>

.more a:hover {
	background: #71160d;
}

li.topmenuselected{
	background: #0557ab;
}

.dropdown-toggle{margin-bottom:0px}

</style>
<body onload="page_loaded();">

	<!-- navigation bar -->
	<div class="abovenavbar">
		<div align=center>
			<div style="width:1100px;">
 					<span style="float:left;">${username}</span>
					<span style="float: right;"><a href="<c:url value="/front/SenderHomeController-home?username=${username}"/>">返回首页</a></span>
			</div>
		</div>
	</div>

	<!-- top menu -->
	<div class="navbar navbar-inverse top-nav" align=center style="height:50px;overflow:hidden">
		<div class="navbar-inner">
			<div class="container">
				<div align=left style="width:1100px;">
					<span class="title_account" style="line-height: 100%; margin-top:8px">管理员</span>
					<div class="nav-collapse" style="margin-left:22px;">
						<ul class="nav">
							<li id="dingdancenter" class="dropdown menu-break-left">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(1);">订单中心</a>
							</li>
							<li id="financecenter" class="dropdown menu-break-left">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(2);">财务中心</a>
							</li>
							<li id="xiaoxicenter" class="dropdown menu-break-left">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(3);">消息中心</a>
							</li>
							<li id="jiyeguanli" class="dropdown menu-break-left menu-break-right">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;" onclick="selectLeftMenu(4);">企业管理</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="main_content" align=center style="background:white;">
		<div align=left style="width:1100px;padding-top:32px;">

			<iframe id="mainFrame" name="mainFrame"
				style="width: 100%; height:100%; border: none; display: block; noscroll; padding: 0px"></iframe>

<!-- 			<div id="leftmenu" class="" style="border:solid 1px #d0d0d0; height:800px;width:190px;float:left;">
			</div>

			<div style="float:left;padding-left:20px;">
			</div>
 -->
		</div>
	</div>
	
	

<script type="text/javascript">
	
	$("#dingdancenter").click( function(){
		topmenu_sel_clear();
		$("#dingdancenter").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/sender/order_center"/>");
	});

	$("#financecenter").click( function(){
		topmenu_sel_clear();
		$("#financecenter").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/sender/finance_center"/>");
	});
	
	$("#xiaoxicenter").click( function(){
		topmenu_sel_clear();
		$("#xiaoxicenter").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/sender/news_center"/>");
	});

	$("#jiyeguanli").click( function(){
		topmenu_sel_clear();
		$("#jiyeguanli").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/sender/company_manage"/>");
	});

	function topmenu_sel_clear(){
/* 		$("#dingdancenter").removeClass("topmenuselected");
		$("#financecenter").removeClass("topmenuselected");
		$("#xiaoxicenter").removeClass("topmenuselected");
		$("#jiyeguanli").removeClass("topmenuselected");
 */		
		$("li").removeClass("topmenuselected");
	}

	function page_loaded() {
		$("#mainFrame").height( $(window).height()-$("#mainFrame").outerHeight());
		$("#mainFrame").attr("src", "<c:url value="/front/sender/order_center"/>");
	}
</script>

</body>
</html>
