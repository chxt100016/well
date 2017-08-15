<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<style type="text/css">

</style>
<div class="container1">
	<div class="container2">

<div id = "content-rect" style="width:90%">
	 <br>
   <h4>产品列表</h4>
   <div class="ui divider"></div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-prodList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="prodState" name="prodState" value="${param.prodState}">
		<%--
		<div class="row-header">
		     <span class="header-title">产品列表</span>
		     <div style="float:right;">
		     	<input type="text" name="prodName" style="height:10px; float:left;margin-bottom:0px;" value="${param.prodName}"/>
		     	<span class="span_search_btn_blue" style="margin-bottom:0px;" onclick="searchData(1);">搜索</span>
		     </div>
		</div>
		--%>
	</form>
	 <!-- <ul class="ds-bl pd-10 ft-sz-15">
            <li class="ds-bl fl-lt pd-10  ulSelected" style="padding-left:0px">全部订单</li>
            <li class="ds-bl fl-lt pd-10">待付款</li>
            <li class="ds-bl fl-lt pd-10">待发货</li>
            <li class="ds-bl fl-lt pd-10">待收货</li>
        </ul> -->
		<br>
		
	<div>
		<div class="fl-rg pd-20" style="font-size:14px;font-weight:600;color:#FF4400;" >
		   <a style="color:#FF4400;" href="${pageContext.request.contextPath}/front/seller/SellerOrderController-prodPub">+&ensp;产品上新</a>				
			</div>
	</div>
	<!-- <table style="background-color:#e4ecf3; text-align:center;width:100%;line-height:35px;">
		<thead>
			<tr class="grey-4">
			  	<th>产品详情</th>
                <th>品类</th>
                <th>单价(元/吨)</th>
				<th>储备量</th>
				<th>产品状态</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
	<c:forEach var="item" items="${waProdList}">
	<table class="prodtab" style="width:100%;border:1px solid #f2f2f2;">
		<tr>
			<td><img src="${item.prodImg}" alt="" width="100px" height="74px">&emsp;${item.prodName}</td>
			<td>一级品</td>
			<td>${item.prodPrice}</td>
			<td>${item.prodNum}</td>
			<td>
				<c:if test="${item.prodState=='-2'}">审核不通过</c:if>
				<c:if test="${item.prodState=='-1'}">已下架</c:if>
				<c:if test="${item.prodState=='0'}">未提交</c:if>
				<c:if test="${item.prodState=='1'}">审核中</c:if>
				<c:if test="${item.prodState=='2'}">已上架</c:if>
			</td>
			<td>
				<c:if test="${item.prodState=='-2' || item.prodState=='0'}">				
					<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item.prodId}')">提交</a>								
				</c:if>
				<c:if test="${item.prodState=='-1'}">						
					<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item.prodId}')">上架</a>									
				</c:if>
				<c:if test="${item.prodState=='2'}">								
					<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item.prodId}')">下架</a>	
				</c:if>
			</td>
		</tr>
	</table>
	</c:forEach> -->
	<table class="ui table ft-sz-14" style="text-align:center;">
		<thead>
			 <tr class="grey-4">
			  	<th>产品详情</th>
                <th>品类</th>
                <th>单价（元/吨）</th>
				<th>储备量</th>
				<th>产品状态</th>
				<th>操作</th>
			</tr>
			<tbody>	
				<c:forEach var="item" items="${waProdList}">
					 <tr class="">
						<td class="right-border">
                           	<img class="bordered" src="${item.prodImg}" alt="" width="100px" height="74px" class="ds-bl fl-lt">
                        	<span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="padding-top: 25px;width: 190px;"> ${item.prodName}</span>
						</td>
						<td class="right-border tx-ct">

						</td>
						<td class="right-border tx-ct">
                           ${item.prodPrice}
						</td>
						<td class="right-border tx-ct">
							${item.prodNum}
						</td>
						<td class="right-border ">
							<c:if test="${item.prodState=='-2'}">审核不通过</c:if>
							<c:if test="${item.prodState=='-1'}">已下架</c:if>
							<c:if test="${item.prodState=='0'}">未提交</c:if>
							<c:if test="${item.prodState=='1'}">审核中</c:if>
							<c:if test="${item.prodState=='2'}">已上架</c:if>
						</td>
						<td class=" ">
							<c:if test="${item.prodState=='-2' || item.prodState=='0'}">				
										<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item.prodId}')">提交</a>								
							</c:if>
							<c:if test="${item.prodState=='-1'}">								
										<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item.prodId}')">上架</a>									
							</c:if>
							<c:if test="${item.prodState=='2'}">								
										<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item.prodId}')">下架</a>
									
							</c:if>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</thead>
	</table>

	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>

	</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		$('.fancybox').fancybox();
		
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
	});

	//进入编辑页面
	function editProduct(prodId) {
        window.location.href = "${pageContext.request.contextPath}/seller/productEditPage?prodId=" + prodId;
    }
	function toURL(action, prodId){
		if(prodId!=''){
			if(action=='editProd'){
				window.location.href = "${pageContext.request.contextPath}/seller/editPage?prodId=" + prodId;
			} else if(action=='deleteProd'){
				if(confirm("你要确定删除操作吗？")){
					$.post("${pageContext.request.contextPath}/front/seller/SellerOrderController-deleteProd",{prodId:prodId},function(data){
			    		data = $.parseJSON(data);
			    		alert(data.content);
			            if(data.status=="1"){
			            	window.location.reload();
			            }
			      	})
			      	.error(function(data){
			      		alert("操作失败！")
			      	});
				}
			} else if(action=='setProdState'){
				$.post("${pageContext.request.contextPath}/front/seller/SellerOrderController-setProdState",{prodId:prodId},function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.reload();
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
			}
		}
	}
</script>

<%@ include file="../footer.html"%>