<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">交易列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglJyListCtrl-getJyList">	
	            		<label>
	            			公司名:<input type="search" name = "userName"  value = "${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名">
	            			&nbsp;&nbsp;
	            			交易时间:<input id="startJyDate" type="text" name="startJyDate" style="width:120px;height:30px;padding-left:5px;" value="${param.startJyDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            			&nbsp;~
	            			<input id="endJyDate" type="text" name="endJyDate" style="width:120px;height:30px;padding-left:5px;" value="${param.endJyDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            			&nbsp;&nbsp;
	            			交易类型:
	            			<select name = "jyType" style = "width:120px;">
	            				<option value = "">全部</option>
	            				<option value = "0" <c:if test = "${param.jyType == 0}">selected="selected"</c:if>>充值</option>
	            				<option value = "1" <c:if test = "${param.jyType == 1}">selected="selected"</c:if>>订单</option>
	            				<option value = "2" <c:if test = "${param.jyType == 2}">selected="selected"</c:if>>提现</option>
	            				<option value = "3" <c:if test = "${param.jyType == 3}">selected="selected"</c:if>>物流</option>
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
							<th width = "5%">交易类型</th>
							<th width = "10%">交易名称</th>
							<th width = "15%">交易金额(实际金额)</th>
							<th width = "15%">交易时间</th>
							<th width = "10%">交易状态</th>
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
											<a class="fancybox" href="${pageContext.request.contextPath}/${item.companyImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.companyImg}" alt="" style="width:80px; height:80px;"  onerror = "noExitImg(this, '${pageContext.request.contextPath}');" /></a>
										</c:if>
										<c:if test = "${item.companyImg == '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" alt="" style="width:80px; height:80px;" /></a>
										</c:if>
									</td>
									<td width = "5%">
										<c:if test = "${item.jyType == 0}">充值</c:if>
										<c:if test = "${item.jyType == 1}">订单</c:if>
										<c:if test = "${item.jyType == 2}">提现</c:if>
										<c:if test = "${item.jyType == 3}">物流</c:if>
									</td>
									<td width = "10%">${item.jyMc}</td>
									<td width = "15%">${item.jyMoney}(${item.jySjMoney})</td>
									<td width = "15%"><fmt:formatDate value="${item.jyDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td width = "10%">
										<c:if test = "${item.jyState == -1}">取消</c:if>
										<c:if test = "${item.jyState == 0}">未确定</c:if>
										<c:if test = "${item.jyState == 1}">进行中</c:if>
										<c:if test = "${item.jyState == 2}">进行中</c:if>
									</td>
									
									<td style = "text-align:right;" width = "10%">
										<a onclick="toURL('detail', '${item.moneyId}')" class="btn mini red icn-only">详细</a>
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
		if(action == 'detail'){ // 详细功能
			url = "${pageContext.request.contextPath}/houtai/jygl/HoutaiJyglJyListCtrl-getJyDetailList?moneyId="+id;
			window.location.href = url;
		}
	}
</script>
<%@ include file="../footer.jsp"  %>