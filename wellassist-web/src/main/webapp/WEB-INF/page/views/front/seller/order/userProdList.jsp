<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<head>

<style>

</style>

</head>
<div id = "content-rect">
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-userProdList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<div class = "row-header">
		     <span class = "header-title">客户商品列表</span>
		     <div style = "float:right;">
		     	<input type = "text" name = "userName" style = "height:10px; float:left;margin-bottom:0px;" value = "${param.userName}"/>
		     	<span class="span_search_btn_blue" style = "margin-bottom:0px;" onclick = "searchData(1);" >搜索</span>
		     </div>
		</div>
	</form>
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;height:20px;">
		<div style="width:50%;text-align:center;font-size:16px;float:left;">产品详情</div>
		<div style="width:30%;text-align:center;font-size:16px;float:left;">价格</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>

	<!--  -->
	<c:forEach var = "item" items = "${list}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;"">
			<div style="height:60px;background:#e0e0e0;font-size:16px;">
				<div style = "margin-left:10px;line-height:56px; float:left;">
					<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:50px; height:50px;" /></a>
				</div>
				<div style = "margin-left:10px;line-height:56px; color: #807B7B;float:left; font-size:10px;">
					公司名称:${item.userName}
				</div>
				<div style = "margin-left:10px;line-height:56px; color: #807B7B; float:left; font-size:10px;">
					联系人:${item.companyLxr}
				</div>
				<div style = "margin-left:10px;line-height:56px; color: #807B7B; float:left; font-size:10px;">
					联系电话:${item.companyLxrPhone}
				</div>
				<div style = "margin-right:10px; color: #807B7B; float:right;">
					<span class="span_btn_gray" onClick = "openAddProdDlg('${item.userId}', ${item.gysId})">添加</span>
				</div>
			</div>
			<c:forEach var = "item2"  items = "${item.spList}"> 
				<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
					<div class="graybox" style="width:50%;height:110px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
						<div style = "margin-left:10px;line-height:106px; float:left;">
							<a class="fancybox" href="${pageContext.request.contextPath}/resources/wella/front/images/seller/ordersheet/icon01.png" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/wella/front/images/seller/ordersheet/icon01.png"  style="width:80px; height:80px;" /></a>
						</div>
						<div style = "margin-left:10px;line-height:106px; float:left;">
							${item2.prodName}
						</div>
						<div style = "margin-right:10px;line-height:106px; float:right;color:#A1A2A9;">
							${item2.prodNum}吨
						</div>	
					</div>
					
					<div class="grayboxwithoutleft" style="width:30%;line-height:106px;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
						单价: ${item2.prodMoney}
					</div>
					<div class="grayboxwithoutleft" style="height:110px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
						<span class="span_btn" onClick = "toURL('detailProd', '${item2.prodId}')">详情</span>
						<span class="span_btn_gray" onClick = "toURL('delProd', '${item2.logId}')">删除</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	<c:if test="${list== null || fn:length(list) == 0}">
		     	<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		$('.fancybox').fancybox();
	});
	
	// 功能函数
	function toURL(action, id){
		var url = "";
		if(action == 'delProd'){ // 商品删除
			if(!confirm('您确定操作吗？')) return;
			url = "${pageContext.request.contextPath}/front/seller/SellerOrderController-delUserProd?logId="+id;
			$.post(url,{},function(data){
	    		data = $.parseJSON(data);
	            if(data.state==1 ){
	            	alert(data.content);
	            	window.location.reload();
	            }else{
	            	alert(data.content);
	            }
	      	});
		}else if(action == 'detailProd'){// 商品详情
			url = "${pageContext.request.contextPath}/front/seller/SellerOrderController-prodDetail?prodId="+id;
			window.location.href = url;
		}
	}
	
	// 显示选择商品的dialog的函数
	function openAddProdDlg(userId, gysId){
		var url = "${pageContext.request.contextPath}/front/seller/SellerOrderController-addProdList?userId="+userId+"&gysId="+gysId;
		window.open(url, "选择添加商品", " height = 500, width = 500, top = 300 , left = 300  ");
	}
</script>
<%@ include file="../footer.jsp"%>