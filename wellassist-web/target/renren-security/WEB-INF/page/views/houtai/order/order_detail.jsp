<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error-div{text-align:left; line-height: 10px;}
</style>

<div class="box">
	<div class="col-xs-12">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">订单详情</h3>
           	</div>
           	<div class="box-body">
            	<div class="dl-horizontal detail-hz">
               		<dl class="ddOdd">
               			<dt>商品名称 : </dt>
               			<dd>${orderInfo.prodName}</dd>
               		</dl>
               		<dl>
               			<dt>商品图片 : </dt>
               			<dd><a class="fancybox" href="${pageContext.request.contextPath}/${orderInfo.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${orderInfo.prodImg}" alt="" style="width:50px; height:50px;" /></a></dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>买家名称 :</dt>
               			<dd style="word-wrap: break-word;">${orderInfo.userName}</dd>
               		</dl>
               		<dl>
               			<dt>购买数量 :	</dt>
               			<dd>${orderInfo.saleNum}</dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>购买总额(元) :	</dt>
               			<dd>${orderInfo.saleMoney}</dd>
               		</dl>
               		<dl>
               			<dt>单价修正时间 :	</dt>
               			<dd><fmt:formatDate value="${orderInfo.djModifyDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>实际成交数量 :	</dt>
               			<dd>${orderInfo.saleSjNum}</dd>
               		</dl>
               		<dl>
               			<dt>实际成交金额(元) :	</dt>
               			<dd>${orderInfo.saleSjMoney}</dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>过路费收取费用(元) :	</dt>
               			<dd>${orderInfo.sqMoney}</dd>
               		</dl>
               		<dl>
               			<dt>现在状态 : </dt>
               			<dd>
           					<c:if test="${orderInfo.orderState=='0'}">待确认</c:if>
							<c:if test="${orderInfo.orderState=='1'}">待付款</c:if>
							<c:if test="${orderInfo.orderState=='2'}">已付款</c:if>
							<c:if test="${orderInfo.orderState=='3'}">已发货</c:if>
							<c:if test="${orderInfo.orderState=='4'}">待评价</c:if>
							<c:if test="${orderInfo.orderState=='5'}">已完成</c:if>
           				</dd>
           			</dl>
           			<dl class="ddOdd">
               			<dt>下单时间 :	</dt>
               			<dd><fmt:formatDate value="${orderInfo.orderDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
               		</dl>
               		<dl>
               			<dt>下单Ip : </dt>
               			<dd>${orderInfo.orderIp}</dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>开票状态: </dt>
               			<dd>
               				<c:if test="${orderInfo.kpState=='0'}">未确定</c:if>
							<c:if test="${orderInfo.kpState=='1'}">发货</c:if>
							<c:if test="${orderInfo.kpState=='2'}">已发货</c:if>
               			</dd>
               		</dl>
               		<dl>
               			<dt>开票完成时间: </dt>
						<dd><fmt:formatDate value="${orderInfo.kpCompleteDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
					</dl>
					<dl class="ddOdd">
               			<dt>是否自己派车: </dt>
               			<dd>
           					<c:if test="${orderInfo.isSelfCar=='0'}">自己</c:if>
							<c:if test="${orderInfo.isSelfCar=='1'}">物流</c:if>
           				</dd>
           			</dl>
             	</div>
           </div>
        </div>
	</div>
	
	<div class="col-xs-12" style="margin-top: 10px;"></div>
	
	<div class="col-xs-12">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">订单历史</h3>
           	</div>
           	<div class="box-body">
           		<div class = "row">
					<div class = "col-sm-12">
						<table id="example2" class="table table-bordered table-striped table-hover">
			                    <thead>
			                      <tr>
									<th>状态</th>
									<th>操作人</th>
									<th>操作时间</th>
									<th>操作内容</th>
									<th>操作Ip</th>
								  </tr>
			                    </thead>
			                    <tbody>
			                      <c:forEach var="item" items="${orderInfoList}" varStatus="status">
										<tr>
											<td>
												<c:if test="${item.infoType=='0'}">待确认</c:if>
												<c:if test="${item.infoType=='1'}">待付款</c:if>
												<c:if test="${item.infoType=='2'}">已付款</c:if>
												<c:if test="${item.infoType=='3'}">已发货</c:if>
												<c:if test="${item.infoType=='4'}">待评价</c:if>
												<c:if test="${item.infoType=='5'}">已完成</c:if>
											</td>		
											<td>${item.userName}</td>
											<td><fmt:formatDate value="${item.mgrDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${item.mgrContent}</td>
											<td>${item.mgrIp}</td>
										</tr>
									</c:forEach>
			                    </tbody>
		                  </table>
		            </div>
				</div>
           		<div class = "row">
					<div class = "col-sm-12">
						<input type="button" id="btn_cancle" value="返回" style="width: 50px; line-height: 25px; margin-left: 20px;" />
					</div>
				</div>
           	</div>
        </div>
    </div>
</div>

<script type="text/javascript">
 	$(function(){
 		$('.fancybox').fancybox();
 		
 		$('#btn_cancle').click(function() {
 			history.back();
		});
 	});
</script>

<%@ include file="../footer.jsp"  %>