<%@ include file="../../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">买家列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/user/HoutaiUserBuyerCtrl-getBuyerList">	
	            		<label>
	            			买家名称 : <input name="userName"  value="${param.userName}" class="form-control input-sm houtai-search-control" placeholder="买家名称" />
	            			&nbsp;&nbsp;
	            			状态 : <select name="userState">
            						<option value="">全部</option>
            						<option value="1"  <c:if test="${param.userState=='1'}" >selected="selected"</c:if> >审核通过</option>
            						<option value="0"  <c:if test="${param.userState=='0'}" >selected="selected"</c:if> >未审核</option>
            						<option value="-1" <c:if test="${param.userState=='-1'}">selected="selected"</c:if> >审核不同过</option>
            						<option value="-2" <c:if test="${param.userState=='-2'}" >selected="selected"</c:if> >冻结</option>
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
				<table id="example2" class="table table-bordered table-striped table-hover" style="border: 1px solid #ddd;">
	                    <thead>
	                      <tr>
						  <th>#</th>
							<th>公司</th>
							<th style = "">公司详情</th>
							<th>账户情况</th>
							<th>还款情况</th>
							<th>授信额度</th>
							<th>所属卖家</th>
							
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${sellerList}" varStatus="status">
								<tr>		
								    <td>${status.count}</td>
									<td>${item.userName}</td>
									<td>
										<a href="javascript:getUserDetail('${item.userId}')">查看</a>
									</td>
									<td>
										<h5>200w</h5><button class="btn">查看</button>				
									</td>
									<td>
										<h5>无欠款</h5><button class="btn">查看</button>
									</td>
									<td>
										<h5>200w元</h5><button class="btn">授信额度</button>
									</td>	
									<td>
										<b>昆仑能源</b>
									</td>			
									
								</tr>
							</c:forEach>
	                    </tbody>
                  </table>
            </div>
		</div>
		
		<div id="table_pagination" class="table_pagination">
			<%@ include file="../../pagination.jsp"%>
		</div>
		
		<div>
			<button id="btn_add" class="btn">添加</button>
		</div>
	</div>
</div>

<script type="text/javascript">
 	$(function(){
 		
 		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
 		
 		$("#btn_clear").click(function(){
 			$("input[name='userName']").val("");
 			$("select[name='userState']").val("");
 		});
 		
 		$("#btn_search").click(function(){
 			searchData(1);
 		});
 		
 		$("#btn_add").click(function(){
 			window.location.href = "${pageContext.request.contextPath}/houtai/user/HoutaiUserBuyerCtrl-editBuyerInfo";
 		});
 	});
 	
 	function getUserDetail(userId){
 		window.location.href = "${pageContext.request.contextPath}/houtai/user/HoutaiUserBuyerCtrl-getBuyerDetail?userId=" + userId;
 	}
</script>

<%@ include file="../../footer.jsp"  %>