<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">订单列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/ht/Order/OrderCtrl-getOrderList">	
	            		<label>
	            			商品名称 : <input  name="prodName"  value="${param.prodName}" class="form-control input-sm houtai-search-control" placeholder="商品名称" />
	            			&nbsp;&nbsp;
	            			买家名称 : <input name="userName"  value="${param.userName}" class="form-control input-sm houtai-search-control" placeholder="买家名称" />
	            			&nbsp;&nbsp;
	            			订单状态 : <select name="orderState">
	            						<option value="">全部</option>
	            						<option value="0"  <c:if test="${param.orderState=='0'}" >selected="selected"</c:if> >待确认</option>
	            						<option value="1"  <c:if test="${param.orderState=='1'}" >selected="selected"</c:if> >待付款</option>
	            						<option value="2"  <c:if test="${param.orderState=='2'}" >selected="selected"</c:if> >已付款</option>
	            						<option value="3"  <c:if test="${param.orderState=='3'}" >selected="selected"</c:if> >已发货</option>
	            						<option value="4"  <c:if test="${param.orderState=='4'}" >selected="selected"</c:if> >待评价</option>
	            						<option value="5"  <c:if test="${param.orderState=='5'}" >selected="selected"</c:if> >已完成</option>
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
							<th>买家名称</th>
							<th>购买数量</th>
							<th>购买总额</th>
							<th>实际成交数量</th>
							<th>实际成交金额</th>
							<th>过路费收取费用</th>
							<th>下单时间</th>
							<th>商品状态</th>
							<th style = "text-align:right;">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${orderList}" varStatus="status">
								<tr>		
									<td>${item.prodName}</td>
									<td>${item.userName}</td>
									<td>${item.saleNum}</td>
									<td>${item.saleMoney}</td>
									<td>${item.saleSjNum}</td>
									<td>${item.saleSjMoney}</td>
									<td>${item.sqMoney}</td>
									<td><fmt:formatDate value="${item.orderDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>
										<c:if test="${item.orderState=='0'}">待确认</c:if>
										<c:if test="${item.orderState=='1'}">待付款</c:if>
										<c:if test="${item.orderState=='2'}">已付款</c:if>
										<c:if test="${item.orderState=='3'}">已发货</c:if>
										<c:if test="${item.orderState=='4'}">待评价</c:if>
										<c:if test="${item.orderState=='5'}">已完成</c:if>
									</td>			
									<td style = "text-align:right;">
										<a href="javascript:getOrderDetail('${item.orderId}')">详情</a>
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
 			$("select[name='orderState']").val("");
 		});
 		
 		$("#btn_search").click(function(){
 			searchData(1);
 		});
 	});
 	
 	function getOrderDetail(orderId){
 		window.location.href = "${pageContext.request.contextPath}/ht/Order/OrderCtrl-getOrderDetail?orderId=" + orderId;
 	}
</script>

<%@ include file="../footer.jsp"  %>