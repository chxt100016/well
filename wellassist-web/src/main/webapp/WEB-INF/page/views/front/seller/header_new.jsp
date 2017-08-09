<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE HTML>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Admin Panel Template">
		<meta name="author" content="Westilian: Kamrujaman Shohel">
		<link rel="stylesheet" href="<c:url value="/resources/library/css/bootstrap.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/library/css/semantic.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome.css"/>">
		<!--[if IE 7]>
		<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome-ie7.min.css"/>">
		<![endif]-->
		<link rel="stylesheet" href="<c:url value="/resources/library/css/chosen.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/styles.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/new_style.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/header.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/theme-blue.css"/>">
		

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
		<!--============ javascript ===========-->
		<script src="<c:url value="/resources/library/js/jquery.js"/>"></script>
		<script src="<c:url value="/resources/library/js/jquery-ui-1.10.1.custom.min.js"/>"></script>
		<script src="<c:url value="/resources/library/js/bootstrap.min.js"/>"></script>
		<script src="<c:url value="/resources/wella/common/js/accordion.nav.js"/>"></script>
		<script src="<c:url value="/resources/common/js/global.js"/>"></script>
		<script src="<c:url value="/resources/common/js/AlertWindow.js"/>"></script>
		<script src="<c:url value="/resources/library/js/semantic.min.js"/>"></script>
		
		<script src="${pageContext.request.contextPath}/resources/common/js/jquery.validate.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/houtai/js/jquery.form.js"></script>
	    
	    <!--<link   href="${pageContext.request.contextPath}/resources/houtai/css/jquery.fancybox.css" rel="stylesheet" />
		<script src="${pageContext.request.contextPath}/resources/houtai/js/jquery.fancybox.js"></script>-->
		
		<script src="${pageContext.request.contextPath}/resources/library/js/My97DatePicker/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath}/resources/common/js/common.js"></script>

		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/leftmenu.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/front_common.css"/>">
	</head>
	<!-- navigation bar -->
	<div class="topheader" >
		<div align=center>
			<div style="width:1100px;">
 					<span class="dropdown" style="float:left;">
						<a data-toggle="dropdown" class="dropdown">${user.userName}&nbsp;<b class="icon-angle-down"></b></a>
						<div class="dropdown-menu">
							<div id="logout"><a style = "color: #1F2325; text-decoration: none;" href = "${pageContext.request.contextPath}/login/out">退出登陆</a></div>
						</div>
 					</span>
					<span style="float: right;"><a href="<c:url value="/login/success?type=1"/>">返回首页</a></span>
			</div>
		</div>
	</div>
		
<div class="navbar navbar-blue " style="margin-top:5px;">
      <div class="container">
        <div class="navbar-header">
          <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand hidden-sm" href="#" onclick="_hmt.push(['_trackEvent', 'navbar', 'click', 'navbar-首页'])">管理员</a>
        </div>
        <div class="navbar-collapse collapse" role="navigation" aria-expanded="false" style="height: 1px;">
          <ul class="nav navbar-nav">
               <li id="chanpincenter" class=" <c:if test = "${parentMenuNo == '1' }"> topmenuselected</c:if>">
								<a  href="${pageContext.request.contextPath}/seller/productList">产品中心</a>
							</li>
      						<li class="topmenuselected" class=" <c:if test = "${parentMenuNo == '5' }"> topmenuselected </c:if>" id="dingdancenter">
							  <a href="${pageContext.request.contextPath}/seller/order">订单中心</a></li>
      						<li id="financecenter" class="<c:if test = "${parentMenuNo == '2' }"> topmenuselected</c:if>" >
							  <a href="${pageContext.request.contextPath}/customer/accountInfo">财务中心</a></li>
      						<li id="newscenter" class="<c:if test = "${parentMenuNo == '3' }"> topmenuselected</c:if>">
								<a onclick="javascript:alert('功能待开发');">消息中心</a></li>
								<%--<a href="${pageContext.request.contextPath}/customer/messagePage">消息中心</a></li>--%>
							<li id="company_manage" class="<c:if test = "${parentMenuNo == '4' }"> topmenuselected</c:if>">
							<a href="${pageContext.request.contextPath}/seller/companyInfo">个人中心</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right hidden-sm">
            <li><a href="/about/" onclick="#">关于</a></li>
          </ul>
        </div>
      </div>
    </div>

<script type="text/javascript">
	
	function menuparentclicked( obj){
		var nextobj = $(obj).nextAll("ul:first");
		if( $(nextobj).css("display")!="none"){
			$(nextobj).slideUp();
			$(obj).css("list-style-image", "url('<c:url value="/resources/wella/front/images/lftmnuitmcollapsed.png"/>')");
		} else {
			$(nextobj).slideDown();
			$(obj).css("list-style-image", "url('<c:url value="/resources/wella/front/images/lftmnuitmexpanded.png"/>')");
		}
	}
</script>


