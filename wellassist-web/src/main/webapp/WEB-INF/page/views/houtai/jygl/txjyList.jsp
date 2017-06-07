<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.abutton{color: #337ab7;text-decoration: none;margin-right:10px;cursor:pointer;}
</style>
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">提现列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglTxjyListCtrl-getTxjyList">	
	            		<label>
	            			公司名:<input type="search" name = "userName"  value = "${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名">
	            			&nbsp;&nbsp;
	            			交易时间:<input id="startDate" type="text" name="startDate" style="width:120px;height:30px;padding-left:5px;" value="${param.startDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            			&nbsp;~
	            			<input id="endDate" type="text" name="endDate" style="width:120px;height:30px;padding-left:5px;" value="${param.endDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            			<select name = "txState" style = "width:180px; margin-right:10px; height:30px;">
	            				<option value = "" >全部</option>
	            				<option value = "-1" <c:if test = "${param.txState == '-1'}">selected = 'selected'</c:if>>不通过</option>
	            				<option value = "0" <c:if test = "${param.txState == '0'}">selected = 'selected'</c:if>>申请</option>
	            				<option value = "1" <c:if test = "${param.txState == '1'}">selected = 'selected'</c:if>>待付款</option>
	            				<option value = "2" <c:if test = "${param.txState == '2'}">selected = 'selected'</c:if>>已付款</option>
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
							<th width ="5%">No </th>
							<th width = "15%">公司名</th>
							<th width = "15%">图片</th>
							<th width = "5%">提现金额</th>
							<th width = "10%">提现开户行</th>
							<th width = "15%">账号</th>
							<th width = "15%">提现时间</th>
							<th width = "10%">状态</th>
							<th style = "text-align:right;" width = "10%">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "5%">${status.count + start}</td>
									<td width = "15%">${item.userName}</td>
									<td width = "15%">
										<c:if test = "${item.companyImg != '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/${item.companyImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.companyImg}" alt="" style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
										</c:if>
										<c:if test = "${item.companyImg == '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" alt="" style="width:80px; height:80px;" /></a>
										</c:if>
									</td>
									<td width = "5%">
										${item.txMoney}
									</td>
									<td width = "10%">${item.txKhh}</td>
									<td width = "15%">${item.account}</td>
									<td width = "15%"><fmt:formatDate value="${item.txDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td width = "10%">
										<c:if test = "${item.txState == -1}">不通过</c:if>
										<c:if test = "${item.txState == 0}">申请</c:if>
										<c:if test = "${item.txState == 1}">待付款</c:if>
										<c:if test = "${item.txState == 2}">已付款</c:if>
									</td>
									
									<td style = "text-align:right;" width = "10%">
										<c:if test = "${item.txState == '-1'}">
											<a onclick="toURL('del', '${item.txId}')" class = "abutton">删除</a>
											<a onclick="toURL('zaishenhe', '${item.txId}')" class = "abutton">再审核</a>
									    </c:if>
									    <c:if test = "${item.txState == '0'}">
											<a onclick="toURL('shenhe', '${item.txId}')" class = "abutton">审核</a>
											<a onclick="toURL('butongguo', '${item.txId}')" class = "abutton">不通过</a>
									    </c:if>
									    <c:if test = "${item.txState == '1'}">
											<a onclick="toURL('fukuan', '${item.txId}')" class = "abutton">付款</a>
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
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
		
		$("#btn_clear").click(function(){
 			$("input[name='userName']").val("");
 			$("input[name='startJyDate']").val("");
 			$("input[name='endJyDate']").val("");
 			$("select[name='jyType']").val("");
 		});
	});
    

	// 功能函数
	function toURL(action, id){
		var url = "";
		if(action == 'shenhe'){ // 详细功能
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglTxjyListCtrl-updateTxState?txId="+id + "&txState=1";
			$.post(url,{},function(data){
				alert(data.content);
	            if(data.state==1 ){
	            	window.location.reload();
	            }
	      	}, "json");
		}else if(action == 'fukuan'){
		    url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglTxjyListCtrl-updateTxState?txId="+id + "&txState=2";
			$.post(url,{},function(data){
				alert(data.content);
	            if(data.state==1 ){
	            	window.location.reload();
	            }
	      	}, "json");
		}else if(action == 'butongguo'){
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglTxjyListCtrl-updateTxState?txId="+id + "&txState=-1";
			$.post(url,{},function(data){
				alert(data.content);
	            if(data.state==1 ){
	            	window.location.reload();
	            }
	      	}, "json");
		}else if(action == 'zaishenhe'){
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglTxjyListCtrl-updateTxState?txId="+id + "&txState=0";
			$.post(url,{},function(data){
				alert(data.content);
	            if(data.state==1 ){
	            	window.location.reload();
	            }
	      	}, "json");
		}else if(action == 'del'){
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglTxjyListCtrl-updateTxState?txId="+id + "&txState=-2";
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