<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;">&nbsp;&nbsp;产品列表</div>

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
	
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;height:20px;">
		<div style="width:40%;text-align:center;font-size:16px;float:left;">产品详情</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">价格</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">
			<span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#prodState').val('');searchData(1);">全部</div>
					<div onclick="$('#prodState').val('0');searchData(1);">未提交</div>
					<div onclick="$('#prodState').val('1');searchData(1);">审核中</div>
					<div onclick="$('#prodState').val('-2');searchData(1);">审核不通过</div>
					<div onclick="$('#prodState').val('2');searchData(1);">已上架</div>
					<div onclick="$('#prodState').val('-1');searchData(1);">已下架</div>
				</div>
			</span>
		</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>
	<c:forEach var="item" items="${waProdList}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;"">
			<div style="height:30px;background:#e0e0e0;font-size:16px;">
				<div style = "margin-left:10px;line-height:30px; color: #807B7B;float:left; font-size:10px;">
					${item.createDate}
				</div>
			</div>
			<c:forEach var="item2"  items="${item.spList}"> 
				<div style= "border-bottom: solid 1px #E0E0E0; overflow:auto;">
					<div class="graybox" style="width:40%;height:110px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
						<div style = "margin-left:10px;line-height:106px; float:left;">
							<a class="fancybox" href="${pageContext.request.contextPath}/${item2.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item2.prodImg}"  style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
						</div>
						<div style = "margin-left:10px;line-height:106px; float:left;">
							<a href="${pageContext.request.contextPath}/front/seller/SellerOrderController-prodDetail?prodId=${item2.prodId}">${item2.prodName}</a>
						</div>
						<div style = "margin-right:10px;line-height:106px; float:right;color:#A1A2A9;">
							${item2.prodNum}吨
						</div>	
					</div>
					<div class="grayboxwithoutleft" style="width:20%;line-height:106px;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
						${item2.prodMoney}元
					</div>
					<div class="grayboxwithoutleft" style="width:20%;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0; text-align: center;">
						<table style="width:100%;height:90px;text-align:center;margin-top:10px;">
							<tr>
								<td style="color:#a00;">
									<c:if test="${item2.prodState=='-2'}">审核不通过</c:if>
									<c:if test="${item2.prodState=='-1'}">已下架</c:if>
									<c:if test="${item2.prodState=='0'}">未提交</c:if>
									<c:if test="${item2.prodState=='1'}">审核中</c:if>
									<c:if test="${item2.prodState=='2'}">已上架</c:if>
								</td>
							</tr>
							
							<c:if test="${item2.prodState=='-2' || item2.prodState=='0'}">
								<tr>
									<td>
										<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item2.prodId}')">提交</a>
									</td>
								</tr>
							</c:if>
							<c:if test="${item2.prodState=='-1'}">
								<tr>
									<td>
										<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item2.prodId}')">上架</a>
									</td>
								</tr>
							</c:if>
							<c:if test="${item2.prodState=='2'}">
								<tr>
									<td>
										<a style="cursor:pointer;color:black;" onclick="toURL('setProdState', '${item2.prodId}')">下架</a>
									</td>
								</tr>
							</c:if>
						</table>
					</div>
					<div class="grayboxwithoutleft" style="height:110px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
						<c:if test="${item2.prodState!='1'}">
							<span class="span_btn" onClick = "toURL('editProd', '${item2.prodId}')">编辑</span>
						</c:if>
						<c:if test="${item2.prodState=='-2' or item2.prodState=='-1' or item2.prodState=='0'}">
							<span class="span_btn_gray" onClick = "toURL('deleteProd', '${item2.prodId}')">删除</span>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	<c:if test="${waProdList== null || fn:length(waProdList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
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
	
	function toURL(action, prodId){
		if(prodId!=''){
			if(action=='editProd'){
				window.location.href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-prodPub?prodId=" + prodId;
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