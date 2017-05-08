<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body class="hold-transition skin-blue sidebar-mini">
   	<div class="wrapper">
		<header class="main-header">
	        <a href="javscript:void(0);" class="logo">
	          	<span class="logo-mini"><b>平台</b></span>
	          	<span class="logo-lg"><b>WellAssist</b>管理平台</span>
	        </a>
       		<nav class="navbar navbar-static-top" role="navigation">
         		<a href="javscript:void(0);" class="sidebar-toggle" data-toggle="offcanvas" role="button">
           			<span class="sr-only">Toggle navigation</span>
         		</a>
         		<div class="navbar-custom-menu">
           			<ul class="nav navbar-nav">
             			<li class="dropdown user user-menu">
               				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                 				<img src="${pageContext.request.contextPath}/${userInfo.userImg}" class="user-image" alt="User Image">
                 				<span class="hidden-xs">${userInfo.userName}</span>
               				</a>
               				<ul class="dropdown-menu">
                 				<li class="user-header">
                   					<img src="${pageContext.request.contextPath}/${userInfo.userImg}" class="img-circle" alt="User Image">
                   					<p>
                     					${userInfo.userName}
                   					</p>
                 				</li>
                 				<li class="user-footer">
                   					<div class="pull-left">
                     					<a href="#" class="btn btn-default btn-flat">信息</a>
                   					</div>
                   					<div class="pull-right">
                     					<a href="${pageContext.request.contextPath}/back" class="btn btn-default btn-flat">退出</a>
                   					</div>
                 				</li>
               				</ul>
             			</li>
             			<li>
               				<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
             			</li>
           			</ul>
         		</div>
       		</nav>
     	</header>
     
    	<aside class="main-sidebar">
      		<section class="sidebar">
        		<div class="user-panel">
           			<div class="pull-left image">
             			<img src="${pageContext.request.contextPath}/${userInfo.userImg}" class="img-circle" alt="User Image">
           			</div>
           			<div class="pull-left info">
             			<p>${userInfo.userName}</p>
             			<a href="${pageContext.request.contextPath}/back"><i class="fa fa-circle text-success"></i> 退出</a>
           			</div>
        		</div>
        		<ul class="sidebar-menu">
          			<li class="header">平台菜单</li>
         			<c:forEach var="item1" items="${menuList}">
         				<li class="treeview">
            				<a href="javascript:void(0);">
              					<i class="fa fa-dashboard"></i>
              					<span>${item1.title}</span>
              					<i class="fa fa-angle-left pull-right"></i>
            				</a>
            				<ul class="treeview-menu">
              					<c:forEach var="item2" items="${item1.subMenuList}">
              						<li><a href="javascript:void(0);" onclick="leftSubMenuClick(this, '${item2.url}', '${item1.title}','${item2.title}')" ><i class="fa fa-circle-o"></i>${item2.title}</a></li>
              					</c:forEach>
            				</ul>
         				</li>
         			</c:forEach>
        		</ul>
      		</section>
    	</aside>

     	<div class="content-wrapper">
       		<section class="content-header">
        		<h1 id = "parentTitle">
           			<c:forEach var="item1"  items = "${menuList}" varStatus="status" begin="0" end="0">
		            	<c:forEach var="item2"  items = "${item1.subMenuList}" varStatus="status" begin="0" end="0">
		            		${item2.title}
		            		<c:set var = "childMenuName" value = "${item2.title}"/>
		            	</c:forEach>
		            	<c:set var = "parentMenuName" value = "${item1.title}"/>
		            </c:forEach>
        		</h1>
	          	<ol class="breadcrumb">
	            	<li><a href="#" id = "parentMenuName">${parentMenuName}</a></li>
	            	<li class="active" id = "childMenuName">${childMenuName}</li>
	          	</ol>
       		</section>
       		
       		<section class="content">
       			<div class="box-body">
       				<iframe id="mainFrame" name="mainFrame" style="width:100%;border:none;display:block"></iframe>
       			</div>	
       		</section>
     	</div>
    </div>
</body>

<script type = "text/javascript">
	// 初始化函数
   	$(function(){
    	init();
    	$(window).resize(function() {
    		init();
        });		
    });
      	
    function init(){
   		$('#mainFrame').height($(window).height()-$('.main-header').outerHeight()-$('.content-header').outerHeight()-50);
    }
      	
	function resize(){
      	if (document.compatMode == "BackCompat") {
       		clientWidth = document.body.clientWidth; ;
      	} else if (document.compatMode == "CSS1Compat") {
        	clientWidth = document.documentElement.clientWidth;
      	}
      
      	$("#navMod").width(clientWidth - 300); 
      	$("#container").width(clientWidth - 350);
      	num = parseInt($("#container").width() / navWidth);
      
      	totalWidth = navWidth * num;
      
  	   	var allNavsNum = $(".dropdown").lengleftSubMenuClickth; //导航总数
  	   
      	if(parseInt($("#container").width())>parseInt(allNavsNum*navWidth)){
   	   		$("#left").hide();
   	   		$("#right").hide();
   	   		$("#scroll").css("left","0px");
      	}else{
   	   		$("#left").show();
   	   		$("#right").show();
      	}
   	}
	
	function leftSubMenuClick(obj,url, title1, title2) {
		$(".treeview-menu").each(function(){
			$(".fa-circle-o").removeClass("fa-dot-circle-o");	
		});
		$(obj).children(0).addClass("fa-dot-circle-o");
		$("#parentTitle").text(title2);
		$("#parentMenuName").text(title1);
		$("#childMenuName").text(title2);
		if(url == undefined || url == '') return ;
		url = '${pageContext.request.contextPath}/'+url;
		$('#mainFrame').attr('src', url);
	}
</script>

<%@ include file="footer.jsp" %>