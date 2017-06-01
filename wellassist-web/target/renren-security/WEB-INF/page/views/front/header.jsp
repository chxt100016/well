<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<title>WellAssist供应链系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Admin Panel Template">
<meta name="author" content="Westilian: Kamrujaman Shohel">
<!-- styles -->
<link rel="stylesheet" href="<c:url value="/resources/library/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/library/css/bootstrap-responsive.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome.css"/>">
<!--[if IE 7]>
<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome-ie7.min.css"/>">
<![endif]-->
<link rel="stylesheet" href="<c:url value="/resources/library/css/chosen.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/styles.css"/>">

<!--[if IE 7]>
<link rel="stylesheet" href="<c:url value="/resources/library/css/ie/ie7.css"/>">
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" href="<c:url value="/resources/library/css/ie/ie8.css"/>">
<![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" href="<c:url value="/resources/library/css/ie/ie9.css"/>">
<![endif]-->
<!--fav and touch icons -->
<!-- extend_css -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/wella/front/css/extend_styles.css"/>">
<!-- extend_css -->
<!--============ javascript ===========-->
<script type="text/javascript" src="<c:url value="/resources/library/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/library/js/jquery-ui-1.10.1.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/library/js/bootstrap.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/common/js/accordion.nav.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/library/js/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/common/js/card.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/library/js/jquery.validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/library/js/jquery.form.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/common/js/AlertWindow.js"/>"></script>
<link rel="Stylesheet" type="text/css" href="<c:url value="/resources/wella/front/css/style_ex.css"/>" />

<script type="text/javascript" src="<c:url value="/resources/library/js/jquery.fancybox.js"/>"></script>
<link   href="${pageContext.request.contextPath}/resources/houtai/css/jquery.fancybox.css" rel="stylesheet" />

<!-- -datepicker -->
<script type="text/javascript" src="<c:url value="/resources/library/js/My97DatePicker/WdatePicker.js"/>"></script>
<!-- -datepicker -->
<script type="text/javascript" src="<c:url value="/resources/common/js/global.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/common/js/common.js"/>"></script>


</head>
<body>