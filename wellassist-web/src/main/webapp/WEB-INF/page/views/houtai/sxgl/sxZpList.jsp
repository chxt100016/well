<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">指派列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxglSxZpListCtrl-getSxZpList">	
	            		<label>
	            			公司名:<input type="search" name = "userName"  value = "${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名">
	            			&nbsp;&nbsp;
	            			申请时间:<input id="startCreditSqDate" type="text" name="startCreditSqDate" style="width:120px;height:30px;padding-left:5px;" value="${param.startCreditSqDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            			&nbsp;~
	            			<input id="endCreditSqDate" type="text" name="endCreditSqDate" style="width:120px;height:30px;padding-left:5px;" value="${param.endCreditSqDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
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
							<th width = "10%">公司信息</th>
							<th width = "10%">联系人</th>
							<th width = "10%">联系电话</th>
							<th width = "10%">申请时间</th>
							<th width = "8%">授信金额</th>
							<th width = "8%">授信信息</th>
							<th width = "8%">利息率(%)</th>
							<th style = "text-align:right;" width = "8%">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "5%">${status.count + start}</td>
									<td width = "10%">
										<c:if test = "${item.userImg != '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/${item.userImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.userImg}" alt="" style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
										</c:if>
										<c:if test = "${item.userImg == '' }">
											<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" alt="" style="width:80px; height:80px;" /></a>
										</c:if>
									</td>
									<td width = "10%">${item.userName}</td>
									<td width = "10%">${item.userLxr}</td>
									<td width = "10%">${item.userLxrPhone}</td>
									<td width = "8%">${item.creditSqDate}</td>
									<td width = "8%">${item.creditMoney}</td>
									<td width = "8%">${item.lixiRate}</td>
									<td style = "text-align:right;" width = "8%">
										<a onclick="toURL('detail', '${item.creditId}')" class="btn mini red icn-only">详细</a>
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
 			$("input[name='startCreditSqDate']").val("");
 			$("input[name='endCreditSqDate']").val("");
 		});
	});
    

	// 功能函数
	function toURL(action, id){
		var url = "";
		if(action == 'detail'){ // 详细功能
			url = "${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxglSxZpListCtrl-getSxZpDetail?creditId="+id;
			window.location.href = url;
		}
	}
</script>
<%@ include file="../footer.jsp"  %>