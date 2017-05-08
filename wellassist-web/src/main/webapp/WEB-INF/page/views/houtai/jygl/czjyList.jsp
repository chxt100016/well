<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">充值交易列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglCzjyListCtrl-getCzjyList">	
	            		<label>
	            			公司名:<input type="search" name="userName"  value="${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名">
	            			<select name = "zfState" style = "width:180px; margin-right:10px; height:30px;">
	            				<option value = "" >全部</option>
	            				<option value = "0" <c:if test = "${param.txState == '0'}">selected = 'selected'</c:if>>未确定</option>
	            				<option value = "1" <c:if test = "${param.txState == '1'}">selected = 'selected'</c:if>>确定</option>
	            			</select>
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
							<th width = "15%">公司名</th>
							<th width = "15%">银行名称</th>
							<th width = "15%">支付金额(元)</th>
							<th width = "15%">支付时间</th>
							<th width = "15%">支付类型</th>
							<th width = "15%">状态</th>
							<th style = "text-align:right;" width = "10%">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "15%">${item.userName}</td>
									<td width = "15%">${item.bankName}</td>
									<td width = "15%">${item.zfMoney}</td>
									<td width = "15%"><fmt:formatDate value="${item.zfDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td width = "15%">
										<c:if test = "${item.zfType == 0}">线下</c:if>
										<c:if test = "${item.zfType == 1}">在线</c:if>
									</td>
									<td width = "15%">
										<c:if test = "${item.zfState == 0}">未确定</c:if>
										<c:if test = "${item.zfState == 1}">确定</c:if>
									</td>
									<td style = "text-align:right;" width = "10%">
										<c:if test = "${item.zfState == 0}">
											<a onclick="toURL('queding', '${item.orderId}')" class="btn mini red icn-only">确定</a>
										</c:if>
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
		if(action == 'queding'){ // 确定 
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglCzjyListCtrl-czQd?orderId=" + orderId;
			$.post(url,{},function(data){
				alert(data.content);
	            if(data.state==1 ){
	            	window.location.reload();
	            }
	      	}, "json");
		}
	}
</script>
<%@ include file="../footer.jsp"  %>