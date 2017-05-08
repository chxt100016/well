<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${title }</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Admin Panel Template">
<meta name="author" content="Westilian: Kamrujaman Shohel">
<!-- styles -->
<link rel="stylesheet"
	href="<c:url value="/resources/basic/css/bootstrap.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/basic/css/bootstrap-responsive.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/basic/css/font-awesome.css"/>">
<!--[if IE 7]>
<link rel="stylesheet" href="<c:url value="/resources/basic/css/font-awesome-ie7.min.css"/>">
<![endif]-->
<link rel="stylesheet"
	href="<c:url value="/resources/basic/css/chosen.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/basic/css/styles.css"/>">

<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/basic/css/ie/ie7.css"/>">
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/basic/css/ie/ie8.css"/>">
<![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/basic/css/ie/ie9.css"/>">
<![endif]-->
<!--fav and touch icons -->
<link rel="shortcut icon"
	href="<c:url value="/resources/basic/ico/favicon.ico"/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value="/resources/basic/ico/apple-touch-icon-144-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value="/resources/basic/ico/apple-touch-icon-114-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value="/resources/basic/ico/apple-touch-icon-72-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value="/resources/basic/ico/apple-touch-icon-57-precomposed.png"/>">
<!--============ javascript ===========-->
<script src="<c:url value="/resources/basic/js/jquery.js"/>"></script>
<script
	src="<c:url value="/resources/basic/js/jquery-ui-1.10.1.custom.min.js"/>"></script>
<script src="<c:url value="/resources/basic/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/basic/js/accordion.nav.js"/>"></script>
<script src="<c:url value="/resources/basic/js/global.js"/>"></script>

</head>
<style>
.more a:hover {
	background: #71160d;
}
</style>
<body onload="resize();">
	<div class="layout">
		<!-- Navbar
    ================================================== -->
		<div class="navbar navbar-inverse top-nav">
			<div class="navbar-inner">
				<div class="navbar-title">
					<a class="brand" href="${pageContext.request.contextPath}/main">
						<img src="resources/basic/images/logo.png"
						style="float: left; height: 60px;">
						<div style="font-size: 15px; width: 210px; overflow: hidden;">${title }</div>
					</a>
				</div>
				<div class="nav-collapse"
					style="overflow: hidden; height: 80px; position: relative; float: left; width: 1114px;"
					id="navMod">
					<div class="more" style="float: left;">
						<a id="left" href="javascript:void(0)"
							style="display: none; width: 30px; height: 80px; line-height: 110px; font-size: 28px; color: #FFF; text-align: center; text-decoration: none;"><</a>
					</div>
					<div id="container"
						style="overflow: hidden; height: 80px; position: relative; float: left;"
						class="container clearfix">
						<div id="scroll" class="scroll"
							style="overflow: hidden; float: left; position: absolute; left: 0px; top: 0px; height: 80px;">
							<ul class="nav" id="navi">
								<c:forEach var="item" items="${menuList}" varStatus="status">
									<li class="dropdown" id="navi_li_${status.count}"><a
										data-toggle="dropdown" class="dropdown-toggle"
										href="${item.url}"><i class="${item.className}"></i>
											${item.title} <b class="icon-angle-down"></b></a>
										<div class="dropdown-menu">
											<ul
												style="position: fixed; min-width: 170px; background-color: #d0cfd6; padding: 5px 5px; top: 80px; margin: 0 0px !important;">
												<c:forEach var="sitem" items="${item.subMenuList}">
													<li style="background-color: white;"><a
														onclick="topMenuClick(${item.menuId}, ${sitem.menuId})"
														href="${sitem.url}" target="mainFrame"><i
															class="${sitem.className}"></i> ${sitem.title} </a></li>
												</c:forEach>
											</ul>
										</div></li>
								</c:forEach>
							</ul>
						</div>
						<div class="clear" style="clear: both;"></div>
					</div>
					<div class=" more" style="float: right;">
						<a id="right" href="javascript:void(0)"
							style="display: none; width: 30px; height: 80px; line-height: 110px; font-size: 28px; color: #FFF; text-align: center; text-decoration: none;">></a>
					</div>
				</div>
			</div>
		</div>
		<div class="leftbar leftbar-close clearfix">
			<div class="admin-info clearfix">
				<div class="admin-thumb">
					<i class="icon-sitemap"></i>
				</div>
				<div class="admin-meta">
					<div class="left-title">管理人员</div>
					<div class="right-title">
						<a>账户和密码</a> <a href="${pageContext.request.contextPath}/"><i
							class="icon-wrench"></i>注销</a>
					</div>
				</div>
			</div>
			<div class="left-nav clearfix">
				<div class="left-primary-nav" style="overflow: hidden;">
					<div class="more" style="float: left;">
						<a id="up" href="javascript:void(0)" class="tip-top"
							style="display: block; width: 50px; height: 50px; line-height: 50px; font-size: 28px; color: #FFF; text-align: center; text-decoration: none;"><i
							class="icon-double-angle-up"></i></a>
					</div>
					<div id="container_left"
						style="overflow: hidden; position: relative; float: left;"
						class="container clearfix">
						<div id="scroll_left" class="scroll"
							style="overflow: hidden; float: left; position: inherit; left: 0px; top: 0px;">
							<ul>
								<c:forEach var="item" items="${menuList}">
									<li id="lmenu${item.menuId}"><a
										onclick="leftMainMenuClick(${item.menuId})"
										class="${item.className}" title="${item.title}"></a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="more" style="float: left;">
						<a id="down" href="javascript:void(0)" class="tip-top"
							style="display: block; width: 50px; height: 50px; line-height: 50px; font-size: 28px; color: #FFF; text-align: center; text-decoration: none;"><i
							class="icon-double-angle-down"></i></a>
					</div>
				</div>
				<div class="responsive-leftbar">
					<i class="icon-list"></i>
				</div>
				<div class="left-secondary-nav tab-content">
					<c:forEach var="item" items="${menuList}">
						<div class="tab-pane" id="lgmenu${item.menuId}">
							<ul class="accordion-nav">
								<c:forEach var="sitem" items="${item.subMenuList}">
									<li id="lmenu${sitem.menuId}" data-subid="${sitem.menuId}"><a
										onclick="leftSubMenuClick(${item.menuId}, ${sitem.menuId})"
										data-frameurl="${sitem.url}" target="mainFrame"><i
											class="${sitem.className}"></i> ${sitem.title} </a></li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="main-wrapper">
			<iframe id="mainFrame" name="mainFrame"
				style="width: 100%; border: none; display: block"></iframe>
		</div>
	</div>

	<script type="text/javascript">
	var clientWidth = 0;
	var moveNum = 1; //每次移动三个导航
	var navWidth = 150; //导航宽度
	var totalNavs = $("#scroll ul").children().length; //导航总数
	var num = parseInt($("#container").width() / navWidth); //导航栏可显示的导航数
	var totalWidth = navWidth * num;  //导航栏宽度
	var li_index=1;
	function leftMainMenuClick(menuId) {
		$('.left-primary-nav li').removeClass('active');
		$('.left-primary-nav #lmenu'+menuId).addClass('active');
		
		$('.tab-pane').removeClass('active');
		$('.left-secondary-nav #lgmenu'+menuId).addClass('active');
		leftSubMenuClick(menuId, 0);		
		
		createCookie("selMainMenuIndex", menuId);
		
		$('.left-secondary-nav #lgmenu'+menuId + ' li').eq(0).children("a:eq(0)").click();
	}
	
	function leftSubMenuClick(menuId1, menuId2) {
		$('.tab-pane li').removeClass('active');
		$('#lmenu'+menuId2).addClass('active');
		createCookie("selSubMenuIndex", menuId2);
		url = "${pageContext.request.contextPath}/"+$(".left-secondary-nav>#lgmenu" + menuId1 + ">ul>#lmenu" + menuId2 + ">a").data("frameurl");
		
		createCookie("pageUrl", url);
		$('#mainFrame').attr('src', url);
	}
	
	function topMenuClick(menuId1, menuId2) {
		$('.left-primary-nav li').removeClass('active');
		$('#lmenu'+menuId1).addClass('active');
		
		$('.tab-pane').removeClass('active');
		$('#lgmenu'+menuId1).addClass('active');
		
		$('.tab-pane li').removeClass('active');
		$('#lmenu'+menuId2).addClass('active');
		createCookie("selMainMenuIndex", menuId1);
		createCookie("selSubMenuIndex", menuId2);
		
		url = "${pageContext.request.contextPath}/"+$(".left-secondary-nav>#lgmenu" + menuId1 + ">ul>#lmenu" + menuId2 + ">a").data("frameurl");
		createCookie("pageUrl", url);
	}
	
	function emulateClick(index, index2) {
		$('#lgmenu'+index).addClass('active');
		$('#lmenu'+index).addClass('active');
		$(".left-primary-nav>ul>#lmenu" + index + ">a").click();
		$(".left-secondary-nav>#lgmenu" + index + ">ul>#lmenu" + index2 + ">a").click();
		url = $(".left-secondary-nav>#lgmenu" + index + ">ul>#lmenu" + index2 + ">a").data("frameurl");
		$('#mainFrame').attr('src', url);
	}
	
	$(function() {
		
		setTimeout(function() {
			$('#mainFrame').height($(window).height()-$('.top-nav').outerHeight());
			$("#container_left").height($(window).height()-$('.top-nav').outerHeight()-$('.admin-info').outerHeight());
			var l_num = $("#scroll_left ul").children().length;
			if(parseInt($(window).height()-$('.top-nav').outerHeight()-$('.admin-info').outerHeight()-100)>parseInt(l_num*50))
		    {
		    	   $("#up").hide();
		    	   $("#down").hide();
		    	   $("#scroll_left").css("top","0px");
		    }else{
		    	   $("#up").show();
		    	   $("#down").show();
		    }
			resize();
        }, 0);
		$(window).resize(function() {
			$('#mainFrame').height($(window).height()-$('.top-nav').outerHeight());
			$('.left-primary-nav').height($(window).height()-$('.top-nav').outerHeight()-$('.admin-info').outerHeight());
			$("#container_left").height($(window).height()-$('.top-nav').outerHeight()-$('.admin-info').outerHeight()-100);
			var l_num = $("#scroll_left ul").children().length;
			if(parseInt($(window).height()-$('.top-nav').outerHeight()-$('.admin-info').outerHeight()-100)>parseInt(l_num*50))
		    {
		    	   $("#up").hide();
		    	   $("#down").hide();
		    	   $("#scroll_left").css("top","0px");
		    }else{
		    	   $("#up").show();
		    	   $("#down").show();
		    }
			li_index=1;
			resize();
		   	   
        });
		
		
		var index1 = readCookie("selMainMenuIndex");
		var index2 = readCookie("selSubMenuIndex");
		
		if (index1!="" && index1!=null && index1!="null" && index2!="" && index2!=null && index2!="null"){ 
			emulateClick(index1, index2);
		}else {
			emulateClick("${fmmenuId}", "${fsmenuId}");
		}
		
		
	});
	
	
    function resize(){
       if (document.compatMode == "BackCompat") {
        clientWidth = document.body.clientWidth; ;
       } else if (document.compatMode == "CSS1Compat") {
         clientWidth = document.documentElement.clientWidth;
       }
       
       $("#navMod").width(clientWidth - 350); 
       $("#container").width(clientWidth - 450);
       num = parseInt($("#container").width() / navWidth);
       
       
       totalWidth = navWidth * num;
       
   	   var allNavsNum = $(".dropdown").length; //导航总数
   	   
       if(parseInt($("#container").width())>parseInt(allNavsNum*navWidth))
       {
    	   $("#left").hide();
    	   $("#right").hide();
    	   $("#scroll").css("left","0px");
       }else{
    	   $("#left").show();
    	   $("#right").show();
       }
   	  
	
    }
    $("#right").click(function () {
    	
    	$("#right").hide();
    	 var cl = parseInt($("#scroll").css("left"));
         var v = 0;
    	var cl1  =0
        if(li_index>0)
        	cl1 = $("#navi_li_"+li_index).width();
        
        var allNavsNum = $(".dropdown").length; //导航总数
        v = ((allNavsNum - num) * navWidth - Math.abs(cl)) > moveNum * navWidth ? cl1 :0;
        if(((allNavsNum - num) * navWidth - Math.abs(cl)) > moveNum * navWidth) li_index++;
        $("#scroll").animate({ left: "-=" + v + "px" }, 100, "", function () {
            $("#right").show();
        });
        //if(li_index>0) li_index++;
    });
    $("#left").click(function (event) {
    	if(li_index>0) li_index--;
    	$("#left").hide();
        var cl = parseInt($("#scroll").css("left"));
        
        var v = 0;
        var cl1  =0
        if(li_index>0)
        	cl1 = $("#navi_li_"+li_index).width();
        
        if (cl == 0) { $("#left").show(); if(li_index==0) li_index=1; return; }
        v  = (Math.abs(cl) >= (moveNum * navWidth)) ? cl1 : Math.abs(cl1);
        $("#scroll").animate({ left: "+=" + v + "px" }, 100, "", function () {
            $("#left").show();
        });
        if(li_index==0) li_index=1;
    });
    
    
    $("#up").click(function () {
        $("#up").hide();
        var cl = parseInt($("#scroll_left").css("top"));
        var v = 0;
        num = parseInt($("#container_left").height() / 50);
        var l_num = $("#scroll_left ul").children().length;
        v = ((l_num - num) * 50 - Math.abs(cl)) > 50 ? 50 : (l_num - num) * 50 - Math.abs(cl);
        //v = 50;
        $("#scroll_left").animate({ top: "-=" + v + "px" }, 100, "", function () {
            $("#up").show();
        });
    });
    
    $("#down").click(function (event) {
        $("#down").hide();
        var cl = parseInt($("#scroll_left").css("top"));
        
        var v = 0;
        if (cl == 0) { $("#down").show(); return; }
        //v = (Math.abs(cl) >= (moveNum * navWidth)) ? moveNum * navWidth : Math.abs(cl);
        v = 50;
        $("#scroll_left").animate({ top: "+=" + v + "px" }, 100, "", function () {
            $("#down").show();
        });
    });
    
</script>

</body>
</html>
