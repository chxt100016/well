<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">商品列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/ht/Product/ProductCtrl-getProductList">	
	            		<label>
	            			商品名称 : <input  name="prodName"  value="${param.prodName}" class="form-control input-sm houtai-search-control" placeholder="商品名称" />
	            			&nbsp;&nbsp;
	            			供应商名称 : <input name="userName"  value="${param.userName}" class="form-control input-sm houtai-search-control" placeholder="供应商名称" />
	            			&nbsp;&nbsp;
	            			商品状态 : <select name="prodState">
	            						<option value="">全部</option>
	            						<option value="1"  <c:if test="${param.prodState=='1'}" >selected="selected"</c:if> >审核申请</option>
	            						<option value="-2" <c:if test="${param.prodState=='-2'}">selected="selected"</c:if> >审核不通过</option>
	            						<option value="2"  <c:if test="${param.prodState=='2'}" >selected="selected"</c:if> >审核通过</option>
	            					</select>
	            		</label>
	            		<input id="btn_search" type="button" value="搜索">
						<input id="btn_clear" type="button" value="清空">
						<input type="hidden" id="page" name="page" value="${page}" />
	            	</form>
	            </div>
            </div>
        </div>
      	
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th>商品名称</th>
							<th>商品图片</th>
							<th>供应商名称</th>
							<th>商品添加时间</th>
							<th>商品金额</th>
							<th>商品状态</th>
							<th style = "text-align:right;">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${productList}" varStatus="status">
								<tr>		
									<td>${item.prodName}</td>
									<td><a class="fancybox" href="${pageContext.request.contextPath}/${item.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.prodImg}" alt="" style="width:50px; height:50px;" /></a></td>
									<td>${item.userName}</td>
									<td><fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${item.prodMoney}</td>
									<td>
										<c:if test="${item.prodState==-2}"><font class="text-yellow">审核不通过</font></c:if>
										<c:if test="${item.prodState==1}"><font class="text-aqua">审核申请</font></c:if>
										<c:if test="${item.prodState==2}"><font class="text-green">审核通过</font></c:if>
									</td>			
									<td style = "text-align:right;">
										<a href="javascript:getProductDetail('${item.prodId}')">详情</a>
									</td>
								</tr>
							</c:forEach>
	                    </tbody>
                  </table>
            </div>
		</div>
		
		<div id="table_pagination" class="table_pagination">
			<%@ include file="../pagination.jsp"%>
		</div>
	</div>
</div>

<script type="text/javascript">
 	$(function(){
 		$("#btn_clear").click(function(){
 			$("input[name='prodName']").val("");
 			$("input[name='userName']").val("");
 			$("select[name='prodState']").val("");
 		});
 		
 		$("#btn_search").click(function(){
 			searchData(1);
 		});
 		
 		$('.fancybox').fancybox();
 		
 		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
 	});
 	
 	function getProductDetail(prodId){
 		window.location.href = "${pageContext.request.contextPath}/ht/Product/ProductCtrl-getProductDetail?prodId=" + prodId;
 	}
</script>

<%@ include file="../footer.jsp"  %>