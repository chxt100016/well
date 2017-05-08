<%@ include file="../../../houtai/header.jsp"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	
</head>
<body style="background-color:#FDFDFD !important; padding:20px;min-width:auto">
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-addProdList">
		<div class="" style="min-width:auto;background:none;border-bottom:2px solid #B1B1B1;margin-top:0px;padding-bottom:5px;text-align:right">
			<input type="text" name="prodName" style="width:100px;margin-bottom:0px;height:30px; padding:5px;" value="${param.prodName}" placeholder="商品名">
			<input id="btn_search" type="button" value="搜索">
			
			<a style="float:left;margin-top:7px;font-size:16px;font-weight:600;text-decoration:none;color: #232502;">添加商品</a>
		</div>
		<input type="hidden" id="page" name="page" value="${page}"/>
		<input type="hidden"  name="gysId" id = "gysId" value="${param.gysId}"/>
		<input type="hidden"  name="userId" id = "userId" value="${param.userId}"/>
	</form>
		
	<table class="page-table table table-striped table-bordered tbl-paper-theme table-paper dataTable" id="data-table" style="margin-top:5px;">
		<thead>
			<tr style="">
				<th width=30 >选择</th>
				<th >商品名称</th>
				<th >图片</th>
				<th >单价</th>
				<th >数量</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${list}" varStatus="status">
				<tr>
					<td style = "text-align:center;">
						<input type="radio" name = "checkVar" value = "${item.prodId}" />
					</td>
					<td>${item.prodName}
					</td>
					<td>
						<c:if test = "${item.prodImg != ''}">
           					<a class="fancybox" href="${pageContext.request.contextPath}/${item.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.prodImg}"  style="width:80px; height:80px;" /></a>
           				</c:if>
           				<c:if test = "${item.companyImg == ''}">
           					<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:80px; height:80px;" /></a>
           				</c:if>
					</td>
					<td>${item.prodMoney}</td>
					<td>${item.prodNum}</td>
				</tr>
		    </c:forEach>
		    <c:if test="${list== null || fn:length(list) == 0}">
		     <tr><td colspan="10">没有资料!</td></tr>
		    </c:if>
		</tbody>
	</table>
	<div class="page-area">
		<%@ include file="../../../houtai/pagination.jsp"%>
	</div>
	<div style = "text-align:center; margin-top: 20px;">
		<input type="button" value="确定" onclick = "toURL('queding')">
		<input style = "margin-left:20px;"  type="button" value="返回" onclick = "window.close();">
	</div>
</body>	


 <script type="text/javascript">
 	// 初始化函数
 	$(function(){
 		$('.fancybox').fancybox();	
 		$("#btn_search").click(function(){
 			$("#searchFrm").submit();
 		});
 	});
 	
 	function toURL(action){
 		var url = "";
 		if(action == 'queding'){
 			var prodId = $(":radio[name='checkVar'][checked]").val();
 			if(prodId == undefined || prodId == null){
 				alert("请选择商品!");
 				return;
 			}
 			url = "${pageContext.request.contextPath}/front/seller/SellerOrderController-addUserProd";
 			var userId = $("#userId").val();
 			var gysId = $("#gysId").val();
 			$.post(url,{userId:userId, gysId:gysId, prodId:prodId},function(data){
	            if(data.state==1 ){
	            	alert(data.content);
	            	//opener.setFkf(retValue);
	            	opener.window.location.reload();
	            	window.close();	
	            }else{
	            	alert(data.content);
	            }
	      	}, "json");
 			
 		}
 	}
 	
 	//搜索函数
	function searchData(page) {
		$("#page").val(page);
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		$("#searchFrm").submit();
	}
 	
</script>