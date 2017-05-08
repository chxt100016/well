<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">线下支付列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/Order/HoutaiOrderZfqrCtrl-getZfQrList">	
	            		<label>
	            			订单编号 : <input  name="orderNo"  value="${param.orderNo}" class="form-control input-sm houtai-search-control" placeholder="订单编号" />
	            			&nbsp;&nbsp;
	            			商品名称 : <input name="prodName"  value="${param.prodName}" class="form-control input-sm houtai-search-control" placeholder="商品名称" />
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
							<th>订单编号</th>
							<th>商品名称</th>
							<th>金额</th>
							<th>下单时间</th>
							<th style = "text-align:right;">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${orderList}" varStatus="status">
								<tr>		
									<td>${item.orderNo}</td>
									<td>${item.prodName}</td>
									<td>${item.saleMoney}</td>
									<td><fmt:formatDate value="${item.orderDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td style = "text-align:right;">
										<a href="javascript:toURL('queren', '${item.orderId}')">确认</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${orderList== null || fn:length(orderList) == 0}">
						    	 <tr><td colspan="15">没有资料</td></tr>	 
						    </c:if>
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
	// 初始化函数
 	$(function(){
 		$("#btn_clear").click(function(){
 			$("input[name='prodName']").val("");
 			$("input[name='orderNo']").val("");
 		});
 		
 		$("#btn_search").click(function(){
 			searchData(1);
 		});
 	});
 	
 	// 功能函数
 	function toURL(action, orderId){
 		var url = "";
 		if(action == 'queren'){
 			if(!confirm("您要操作吗?")) return;
 			url = "${pageContext.request.contextPath}/houtai/Order/HoutaiOrderZfqrCtrl-setZfQr?orderId=" + orderId; 
 			$.post(url,{},function(data){
	    		data = $.parseJSON(data);
	    		alert(data.content);
	            if(data.state==1 ){
	            	window.location.reload();
	            }
	      	});
 		}
 		
 	}
</script>

<%@ include file="../footer.jsp"  %>