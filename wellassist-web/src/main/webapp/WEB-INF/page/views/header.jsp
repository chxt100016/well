<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Admin Panel Template">
<meta name="author" content="Westilian: Kamrujaman Shohel">

<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/bootstrap.min.css"/>">
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/bootstrap-responsive.min.css"/>">
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/font-awesome.min.css"/>">	
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/style-metro.css"/>">
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/style.css"/>">
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/style-responsive.css"/>">	

<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/default.css"/>">
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/uniform.default.css"/>">
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/DT_bootstrap.css"/>">	
<link rel="stylesheet"	href="<c:url value="/resources/houtai/css/dialog.css"/>">	
	

<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/jquery-1.10.1.min.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/jquery-migrate-1.2.1.min.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/jquery-ui-1.10.1.custom.min.js"/>"></script>	
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/bootstrap.min.js"/>"></script>
		
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/ui-modals.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/jquery.flot.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/jquery.flot.resize.js"/>"></script>

<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/index.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/jquery.uniform.min.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/app.js"/>"></script>
<script language="javascript" type="text/javascript"	src="<c:url value="/resources/houtai/js/chosen.jquery.js"/>"></script>


<!--[if lte IE 8]>
    <script type="text/javascript" src="/resources/houtai/js/excanvas.min.js"></script>
<![endif]-->

<!--********************************** EasyUI Part ******************************************************-->

<link   href="<c:url value="/resources/houtai/css/easyui.css" />" rel="stylesheet" type="text/css" > 
<script src ="<c:url value="/resources/houtai/js/jquery.easyui.min.js"/>" 	type="text/javascript"></script>
<script src ="<c:url value="/resources/houtai/js/easyui-lang-zh_cn.js"/>" 	type="text/javascript"></script>

<!--********************************** RoleManage Part ******************************************************-->

<script src ="<c:url value="/resources/houtai/js/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src ="<c:url value="/resources/houtai/js/validata.js"/>" 	type="text/javascript"></script>

</head>
	<!--*****************************************************************************************************-->
	<script src="<c:url value="/resources/houtai/js/check.js" />" 	type="text/javascript"></script>
	
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init(); // initlayout and core plugins
			Index.init();

			var menu_num    = "";			
			var tab_num 	= "";
			
			var selMenu = $('#lmenu'+menu_num);		 
			selMenu.attr('class', 'active');
			selMenu.children().append("<span class=\"selected\"></span>");
			$("#lgmenu"+tab_num).attr('class', 'active');
			selMenu.find(".arrow").attr('class', 'arrow open');
			
			$("#url-title").text(selMenu.find(".title").text());
			$(".breadcrumb>li").append("<a href='#'>"+selMenu.find("#lgmenu"+tab_num+">a").text()+"</a>");						 
		
		});

	</script>
</head>
<body class="page-header-fixed page-footer-fixed">
	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">

				<a style="display: inline-block;" href="javascript:void(0)">
					<img src="<c:url value="/resources/houtai/image/logo.png" />" /></a>

				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
					<img src="<c:url value="/resources/houtai/image/menu-toggler.png"/>" alt="" />
				</a>          
				<!-- END RESPONSIVE MENU TOGGLER -->    
				
				<ul class="nav pull-right">
					
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img alt="" src="<c:url value="/resources/houtai/image/avatar1_small.jpg" />">
							<span class="username"></span>
							<i class="icon-angle-down"></i>
						</a>

						<ul class="dropdown-menu" style="width:120px;">
							<li><a href="${pageContext.request.contextPath}/"><i class="icon-key"></i>  退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- BEGIN CONTAINER -->
	<div class="page-container">

		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
			<ul id="page-menu" class="page-sidebar-menu">
				<li>
					<div class="sidebar-toggler"></div>
				</li>
				<li class="start" id="lmenu0">
					<a href="javascript:void(0);">
						<i class="icon-star"></i>
						<span class="title">我的首页</span>
						<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li id="lgmenu0"><a href="${pageContext.request.contextPath}/main/0/0">我的信息</a></li>
					</ul>
				</li>				
				<c:forEach var="item" items="${menuList}" varStatus="itemStatus">				
 				<li id="lmenu${item.menuId}">
					<a href="javascript:void(0);"><i class="${item.className}"></i><span class="title">${item.title}</span><span class="arrow"></span></a>
					<ul class="sub-menu">
					<c:forEach var="sitem" items="${item.subMenuList}">
						<li id="lgmenu${sitem.menuId}"><a href="${pageContext.request.contextPath}/main/${item.menuId}/${sitem.menuId}">${sitem.title}</a></li>
					</c:forEach>
					</ul>
				</li>
				</c:forEach>				
			</ul>
		</div>
		<!-- END SIDEBAR -->
		
		<!-- BEGIN PAGE -->
		<div class="page-content">

			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a id="url-title" href="javascript:void(0)"></a>
								<i class="icon-angle-right"></i>
							</li>
						</ul>
					</div>
				</div>
				<!-- END PAGE HEADER-->