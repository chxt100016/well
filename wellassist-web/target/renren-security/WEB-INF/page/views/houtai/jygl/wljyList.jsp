<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">物流列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglWljyListCtrl-getWljyList">	
	            		<label>
	            			公司名:<input type="search" name="userName"  value="${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名">
	            		</label>
	            		<input type = "hidden" id = "page" name = "page" value = "${page}"/>
	            		<button type="button" class="btn btn-default houtai-search-control houtai-search-btn" onclick = "$('#searchFrm').submit();">搜索</button>
	            		<button type="button" id = "btn_clear"  class="btn btn-default houtai-search-control houtai-search-btn" >清空</button>
	            	</form>
	            </div>
            </div>
        </div>
      	
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th width = "10%">订单编号</th>
							<th width = "10%">公司名</th>
							<th width = "10%">产品名</th>
							<th width = "10%">图片</th>
							<th width = "10%">联系人</th>
							<th width = "10%">电话</th>
							<th width = "10%">支付金额(元)</th>
							<th width = "10%">远输量(吨)</th>
							<th width = "10%">交易状态</th>
							<th style = "text-align:right;" width = "10%">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "10%">${item.orderNo}</td>
									<td width = "10%">${item.userName}</td>
									<td width = "10%">${item.prodName}</td>
									<td width = "10%">
										<c:if test = "${item.prodImg != '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/${item.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.prodImg}" alt="" style="width:80px; height:80px;" /></a>
										</c:if>
										<c:if test = "${item.prodImg == '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" alt="" style="width:80px; height:80px;" /></a>
										</c:if>
									</td>
									<td width = "10%">${item.vehicleLxr}</td>
									<td width = "10%">${item.vehicleLxrPhone}</td>
									<td width = "10%">${item.payMoney}</td>
									<td width = "10%">${item.vehicleSize}</td>
									<td width = "10%">
										<c:if test = "${item.vehicleState == 1}">未确定</c:if>
										<c:if test = "${item.vehicleState == 2}">待安排</c:if>
										<c:if test = "${item.vehicleState == 3}">已安排</c:if>
										<c:if test = "${item.vehicleState == 4}">已发货</c:if>
										<c:if test = "${item.vehicleState == 5}">已完成</c:if>
									</td>
									<td style = "text-align:right;" width = "10%">
										<a onclick="toURL('detail', '${item.orderId}')" class="btn mini red icn-only">详细</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${list== null || fn:length(list) == 0}">
						    	 <tr><td colspan="11">没有资料</td></tr>	 
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
		$('.fancybox').fancybox();
		
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
		
		$("#btn_clear").click(function(){
 			$("input[name='userName']").val("");
 		});
	});

	// 功能函数
	function toURL(action, orderId){
		var url = "";
		if(action == 'detail'){ // 详细功能
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglWljyListCtrl-getWljyDetail?orderId=" + orderId;
			window.location.href = url;
		}
	}
</script>
<%@ include file="../footer.jsp"  %>