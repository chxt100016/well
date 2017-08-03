<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE HTML>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>卖家管理员页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Admin Panel Template">
		<meta name="author" content="Westilian: Kamrujaman Shohel old">

		<link rel="stylesheet" href="<c:url value="/resources/library/css/bootstrap.min.3.35.css"/>">
		<%--<link rel="stylesheet" href="<c:url value="/resources/library/css/bootstrap-responsive.css"/>">--%>
		<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome.css"/>">
			<link rel="stylesheet" href="<c:url value="/resources/library/css/semantic.min.css"/>">
		<!--[if IE 7]>
		<link rel="stylesheet" href="<c:url value="/resources/library/css/font-awesome-ie7.min.css"/>">
		<![endif]-->
		<link rel="stylesheet" href="<c:url value="/resources/library/css/chosen.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/styles.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/new_style.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/theme-blue.css"/>">
		<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/library/css/ie/ie7.css"/>">
		<![endif]-->
		<!--[if IE 8]>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/library/css/ie/ie8.css"/>">
		<![endif]-->
		<!--[if IE 9]>
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/library/css/ie/ie9.css"/>">
		<!--============ javascript ===========-->
		<script src="<c:url value="/resources/library/js/jquery.js"/>"></script>
		<script src="<c:url value="/resources/library/js/jquery-ui-1.10.1.custom.min.js"/>"></script>
		<script src="<c:url value="/resources/library/js/bootstrap.min.3.35.js"/>"></script>
		<script src="<c:url value="/resources/wella/common/js/accordion.nav.js"/>"></script>
			<script src="<c:url value="/resources/library/js/semantic.min.js"/>"></script>
		<script src="<c:url value="/resources/common/js/global.js"/>"></script>
		
		<script src="${pageContext.request.contextPath}/resources/common/js/jquery.validate.js"></script>
	    <%--<script src="${pageContext.request.contextPath}/resources/houtai/js/jquery.form.js"></script>--%>
	    
	    <%--<link   href="${pageContext.request.contextPath}/resources/houtai/css/jquery.fancybox.css" rel="stylesheet" />
		<script src="${pageContext.request.contextPath}/resources/houtai/js/jquery.fancybox.js"></script>--%>
		
		<script src="${pageContext.request.contextPath}/resources/library/js/My97DatePicker/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath}/resources/common/js/common.js"></script>

		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/leftmenu.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/front_common.css"/>">
		
		<style>
			.more a:hover {
				background: #71160d;
			}
			/*li.topmenuselected{
				background: #0557ab;
			}*/
		</style>
	</head>

	<!-- navigation bar -->
	<div class="abovenavbar" style="height:45px" >
		<div align=center>
			<div style="width:1100px;">
				<span class="dropdown" style="float:left;">
					<a data-toggle="dropdown" class="dropdown">${user.userName}&nbsp;<b class="icon-angle-down"></b></a>
					<div class="dropdown-menu">
						<div id="logout"><a style = "color: #1F2325; text-decoration: none;" href = "${pageContext.request.contextPath}/login/out">退出登陆</a></div>
					</div>
				</span>
				<span style="float: right;"><a href="<c:url value="/login/success?type=0"/>">返回首页</a></span>
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
				   <a  href="${pageContext.request.contextPath}/seller/productList"> <i class="block layout icon"></i>产品中心</a>
			   </li>
			  <li  class=" <c:if test = "${parentMenuNo == '5' }"> topmenuselected </c:if>">
				  <a href="${pageContext.request.contextPath}/seller/order">订单中心</a>
			  </li>
			  <li id="financecenter" class="<c:if test = "${parentMenuNo == '2' }"> topmenuselected</c:if>" >
				  <%--<a href="${pageContext.request.contextPath}/seller/finance">财务中心</a></li>--%>
				  <a onclick="javascript:alert('功能待开发');">财务中心</a></li>
			  <li id="newscenter" class="<c:if test = "${parentMenuNo == '3' }"> topmenuselected</c:if>">
				  <a href="${pageContext.request.contextPath}/mes/message">消息中心</a></li>
				  <%--<a onclick="javascript:alert('功能待开发');">消息中心</a></li>--%>
							<li id="company_manage" class="<c:if test = "${parentMenuNo == '4' }"> topmenuselected</c:if>">
							<a href="${pageContext.request.contextPath}/seller/companyInfo">个人中心</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right hidden-sm">
            <li><a href="/about/" onclick="#">关于</a></li>
          </ul>
        </div>
      </div>
    </div>
<!--<%--顶部导航条end--%>-->
	
	<div id="main_content" align=center style="background:white;width: 1300px;margin: 0px auto;padding-top: 32px;">
		<!--<div id="leftmenu" class="" style="border:solid 1px #d0d0d0;width:190px;float:left;">
			<c:if test = "${parentMenuNo == '1'}">
				<ul>
					<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">产品管理</div>
					</li>
					<ul style="display: block;">
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '1' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/publishPage">产品发布</a></li>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '2' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/productList">产品列表</a></li>
					</ul>
					<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">订单管理</div>
					</li>
					<ul style="display: block;">				
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '3' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/orderListPage">订单列表</a></li>
					</ul>
					<%--<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">客户管理</div>
					</li>
					<ul style="display: block;">
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '4' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-userOrderList">已有客户</a></li>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '6' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-userProdList">客户商品</a></li>
					</ul>
					<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">评价返馈</div>
					</li>
					<ul>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '5' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-estiList">评价列表</a></li>
					</ul>--%>
				</ul>		
			</c:if>	
			<c:if test = "${parentMenuNo == '2'}">
				<ul>
					<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">财务中心</div>
					</li>
					<ul style="display: block;">
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '1' }">selected</c:if>" ><a href = "${pageContext.request.contextPath}/front/seller/SellerFinanceController-accountInfo">账户信息</a></li>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '2' }">selected</c:if>" ><a href = "${pageContext.request.contextPath}/front/seller/SellerFinanceController-txList">提现记录</a></li>
					</ul>
				</ul>
			</c:if>
			<c:if test = "${parentMenuNo == '3'}">
				<ul>
					<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">消息通知</div>
					</li>
					<ul style="display: block;">
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '1' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/front/seller/SellerNewsController-xxList">消息列表</a></li>
					</ul>
				</ul>
			</c:if>
			<c:if test = "${parentMenuNo == '4'}">
				<ul>
					<li class="leftmenulevel1" onclick="menuparentclicked( this);">
						<div style="">账号设置</div>
					</li>
					<ul style="display: block;">
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '1' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/companyInfo">公司信息</a></li>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '2' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/contact">联系方式</a></li>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '3' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/password">修改密码</a></li>
						<li class="leftmenuleaf <c:if test ="${childMenuNo == '3' }">selected</c:if>"><a onclick="javascript:alert('功能待开发');">银行卡管理</a></li>
							<%--<li class="leftmenuleaf <c:if test ="${childMenuNo == '3' }">selected</c:if>"><a href = "${pageContext.request.contextPath}/seller/bankCardPage">银行卡管理</a></li>--%>
					</ul>
				</ul>	
			</c:if>
		</div>-->

				<div class="ui vertical accordion menu" id="leftmenu" style="max-width:205px;float:left">
				<c:if test = "${parentMenuNo == '5'}">
						<div class="item">
							<a class="title <c:if test="${childMenuNo == '1'}">active </c:if>"><i class="dropdown icon"></i> 订单中心 </a>
							<div class="<c:if test="${childMenuNo == '1' }"> active </c:if> content">
								<div class="ui form">
									<div class="grouped fields">
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" name="size"  <c:if test ="${childMenuNo == '1'}"> checked="checked" </c:if> >
												<label> <a href = "${pageContext.request.contextPath}/customer/accountInfo">订单列表</a></label>
											</div>
										</div>
										

									</div>
								</div>
							</div>
						</div>

						<div class="item">
							<a class="title <c:if test="${childMenuNo == '2' or childMenuNo =='3'or childMenuNo =='4' }">active </c:if> "><i class="dropdown icon"></i> 授信中心 </a>
							<div class="content <c:if test="${childMenuNo == '2' or childMenuNo =='3'or childMenuNo =='4'}"> active </c:if>">
								<div class="ui form">
									<div class="grouped fields">
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '2'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/front/customer/FinanceController-creditAccount">授信账户</a></label>
											</div>
										</div>
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '3'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/front/customer/FinanceController-sxSqList">申请记录</a></label>
											</div>
										</div>
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '4'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/front/customer/FinanceController-hkList">还款记录</a></label>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="item item3">
							<a class="title <c:if test="${childMenuNo == '7'}">active </c:if>"><i class="dropdown icon"></i> 发票管理 </a>
							<div class="content <c:if test="${childMenuNo == '7'}">active </c:if>">
								<div class="ui form">
									<div class="grouped fields">
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '7'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/front/customer/FinanceController-fapiaoList">发票记录</a></label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
			    </c:if>

				<c:if test = "${parentMenuNo == '1'}">
					<div class="item">
					      <a class="title <c:if test="${childMenuNo == '1' or childMenuNo =='2' }">active </c:if>"><i class="dropdown icon"></i> 产品管理 </a>
						  <div class="content <c:if test="${childMenuNo == '1' or childMenuNo =='2'}"> active </c:if>">
								<div class="ui form">
									<div class="grouped fields">
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '1'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/seller/publishPage">产品发布</a></label>
											</div>
										</div>
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '2'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/seller/productList">产品列表</a></label>
											</div>
										</div>
										
									</div>
								</div>
							</div>

					</div>
				
				</c:if>

					<c:if test = "${parentMenuNo == '3'}">
						<div class="item">
							<a class="title <c:if test="${childMenuNo == '1' or childMenuNo =='2' }">active </c:if>"><i class="dropdown icon"></i> 消息中心 </a>
							<div class="content <c:if test="${childMenuNo == '1' or childMenuNo =='2'}"> active </c:if>">
								<div class="ui form">
									<div class="grouped fields">
										<div class="field">
											<div class="ui  checkbox">
												<input type="checkbox" <c:if test ="${childMenuNo == '1'}"> checked="checked" </c:if>>
												<label><a href = "${pageContext.request.contextPath}/mes/message">消息中心</a></label>
											</div>
										</div>
										<%--<div class="field">--%>
											<%--<div class="ui  checkbox">--%>
												<%--<input type="checkbox" <c:if test ="${childMenuNo == '2'}"> checked="checked" </c:if>>--%>
												<%--<label><a href = "/mes/creditrecord">征信计算</a></label>--%>
											<%--</div>--%>
										<%--</div>--%>

									</div>
								</div>
							</div>

						</div>

					</c:if>
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