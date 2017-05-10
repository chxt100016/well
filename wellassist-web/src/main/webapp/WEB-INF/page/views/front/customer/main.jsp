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
	href="<c:url value="/resources/wella/front/css/new_style.css"/>">
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

li.topmenuselected>a{
	/*background: #0557ab;*/
	color: #fff !important;
    text-decoration: none;
	  opacity: 1 !important;
    /*background-color: #99d2fc;*/

}

</style>
<body onload="page_loaded();">

	<!-- navigation bar -->
	<div class="abovenavbar">
		<div align=center>
			<div style="width:1100px;">
			
 					<span class="dropdown" style="float:left;">
						<a data-toggle="dropdown" class="dropdown">${username}&nbsp;<b class="icon-angle-down"></b></a>
						<div class="dropdown-menu">
							<div id="logout">退出登陆</div>
						</div>
 					
 					</span>
					<span style="float: right;"><a href="<c:url value="/front/customer/CustomerHomeCtrl-home"/>">返回首页</a></span>
			</div>
		</div>
	</div>

	<!-- top menu -->
	<%--<div class="navbar navbar-inverse top-nav" align=center style="height:50px;overflow:hidden">--%>
		<%--<div class="navbar-inner">
			<div class="container">
				<div align=left style="width:1100px;">
					<span class="title_account" style="line-height:100%; margin-top:9px">管理员</span>
					<div class="nav-collapse" style="margin-left:22px;">
						<ul class="nav">
							<li id="dingdancenter" class="dropdown menu-break-left topmenuselected">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;">订单中心</a>
							</li>
							<li id="financecenter" class="dropdown menu-break-left">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;">财务中心</a>
							</li>
							<li id="newscenter" class="dropdown menu-break-left">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;">消息中心</a>
							</li>
							<li id="company_manage" class="dropdown menu-break-left menu-break-right">
								<a class="dropdown-toggle" href="#" style="font-size:16px;color:white;font-weight:bold;">企业管理</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>--%>
	<%--</div>--%>
	<%--顶部导航条--%>
		<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
				
					<!-- .btn-navbar is used as the toggle for collapsed navbar content -->
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
				
					<!-- Be sure to leave the brand out there if you want it shown -->
					<a class="brand" href="#">管理员</a>
				
					<!-- Everything you want hidden at 940px or less, place within here -->
					<div class="nav-collapse collapse" style="height:0px">
						<!-- .nav, .navbar-search, .navbar-form, etc -->
						 <ul class="nav">
						
      						<li class="topmenuselected" id="dingdancenter"><a href="#">订单中心</a></li>
      						<li id="financecenter"><a href="#">财务中心</a></li>
      						<li id="newscenter"><a href="#">消息中心</a></li>
							<li id="company_manage"><a href="#">财务管理</a></li>
                         </ul>

					</div>
				
					</div>
				</div>
		</div>
<%--顶部导航条end--%>

	<div id="main_content" align=center style="background:white;">
		<div align=left style="width:1100px;padding-top:32px;">

			<iframe id="mainFrame" name="mainFrame"
				style="width: 100%; height:100%; border: none; display: block; noscroll"></iframe>

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
		$("#mainFrame").attr("src", "<c:url value="/front/customer/ordersheet_center"/>");
	});

	$("#financecenter").click( function(){
		topmenu_sel_clear();
		$("#financecenter").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/customer/finance_center"/>");
	});
	
	$("#newscenter").click( function(){
		topmenu_sel_clear();
		$("#newscenter").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/customer/news_center"/>");
	});

	$("#company_manage").click( function(){
		topmenu_sel_clear();
		$("#company_manage").addClass("topmenuselected");
		$("#mainFrame").attr("src", "<c:url value="/front/customer/company_manage"/>");
	});

	function topmenu_sel_clear(){
		$("li").removeClass("topmenuselected");
		$("li").removeClass("active");
	}

	function page_loaded() {
		$("#mainFrame").height( $(window).height()-$("#mainFrame").outerHeight());
		$("#mainFrame").attr("src", "<c:url value="/front/customer/ordersheet_center"/>");

	}
	
	$("#logout").click( function(){
		window.location.href="<c:url value="/front/customer/logout"/>";
	});

</script>

</body>
</html>
