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
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/seller/publishPage">
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
		
	<div class="fl-rg pd-20" style="font-size:14px;font-weight:600;color:#FF4400;" >
		<a style="color:#FF4400;" href="${pageContext.request.contextPath}/front/seller/SellerOrderController-prodPub">+&ensp;产品上新</a>				
	</div>
	<table class="ui table" style="text-align:center;border:0;border-bottom: 0;background-color:#e4ecf3;border-radius:0;margin-bottom:0;">
		<thead>
			 <tr>
			  	<th width="30%" colspan="2">产品详情</th>
                <th width="14%">品类</th>
                <th width="14%">单价（元/吨）</th>
				<th width="14%">储备量</th>
				<th width="14%">产品状态</th>
				<th width="14%">操作</th>
			</tr>
		</thead>
	</table>
	<c:forEach var="item" items="${waProdList}">
		<table class="ui fixed single line celled table" style="text-align:center;border-radius:0;margin:10px 0 0;">
			<tbody>
				 <tr>
					<td width="10%">
	                   	<img class="bordered" src="${item.prodImg}" alt="" width="100px" height="74px">
	                </td>
	                <td width="20%" style="border-left:0;text-align:left;">
	                	${item.prodName}
					</td>
					<td width="14%">
						<c:if test="${item.prodType==0}">天然气</c:if>
	            		<c:if test="${item.prodType==1}">原油</c:if>
	            		<c:if test="${item.prodType==2}">管道气</c:if>
					</td>
					<td width="14%">
	                   ${item.prodPrice}
					</td>
					<td width="14%">
						${item.prodNum}
					</td>
					<td width="14%">
						<span style="color:#a00;">
							<c:if test="${item.prodState=='-2'}">审核不通过</c:if>
							<c:if test="${item.prodState=='-1'}">已下架</c:if>
							<c:if test="${item.prodState=='0'}">未提交</c:if>
							<c:if test="${item.prodState=='1'}">审核中</c:if>
							<c:if test="${item.prodState=='2'}">已上架</c:if>
						</span><br>
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
					<td width="14%" style="color:#617B90;">
						<c:if test="${item.prodState!='1'}">
							<span class="span_btn" onClick = "editProduct('${item.prodId}')">编辑</span>
						</c:if>
						<c:if test="${item.prodState=='-2' or item.prodState=='-1' or item.prodState=='0'}">
							<span class="span_btn_gray" onClick = "editProduct('${item.prodId}')">&ensp;|&ensp;删除</span>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</c:forEach>
	<div class="right-pagination" style="margin: 20px 0 0;text-align: right;">
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