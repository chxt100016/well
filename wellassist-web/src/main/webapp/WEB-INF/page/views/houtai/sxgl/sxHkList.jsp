<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">授信列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxglSxListCtrl-getSxList">	
	            		<label>
	            			公司名:<input type="search" name = "userName"  value = "${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名">
	            			&nbsp;&nbsp;
	            			放款方:<input type="search" name = "creditUserName"  value = "${param.creditUserName}"  class="form-control input-sm houtai-search-control" placeholder="放款方">
	            			&nbsp;&nbsp;
	            			放款时间:<input id="startCreditDate" type="text" name="startCreditDate" style="width:120px;height:30px;padding-left:5px;" value="${param.startCreditDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            			&nbsp;~
	            			<input id="endCreditDate" type="text" name="endCreditDate" style="width:120px;height:30px;padding-left:5px;" value="${param.endCreditDate}" class="Wdate" readonly = "readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
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
							<th width = "20%">公司信息</th>
							<th width = "20%">放款信息</th>
							<th width = "8%">申请时间</th>
							<th width = "8%">放款时间</th>
							<th width = "8%">账期</th>
							<th width = "8%">授信金额</th>
							<th width = "8%">授信信息</th>
							<th width = "8%">利息信息</th>
							<th width = "5%">授信状态</th>
							<th style = "text-align:right;" width = "8%">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "5%">${status.count + start}</td>
									<td width = "20%">
										<div style = "width:100%;">
											<div style = "width:50%; float:left;">
												<c:if test = "${item.userImg != '' }">
													<a class="fancybox" href="${pageContext.request.contextPath}/${item.userImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.userImg}" alt="" style="width:80px; height:80px;" /></a>
												</c:if>
												<c:if test = "${item.userImg == '' }">
													<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" alt="" style="width:80px; height:80px;" /></a>
												</c:if>
											</div>
											<div style = "width:50%; float:right;">
												<p>${item.userName}</p>
												<p>${item.userLxr}</p>
												<p>${item.userLxrPhone}</p>
											</div>
										</div>
									</td>
									<td width = "20%">
										<div style = "width:100%;">
											<div style = "width:50%; float:left;">
												<c:if test = "${item.creditUserImg != '' }">
													<a class="fancybox" href="${pageContext.request.contextPath}/${item.creditUserImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.creditUserImg}" alt="" style="width:80px; height:80px;" /></a>
												</c:if>
												<c:if test = "${item.userImg == '' }">
													<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" alt="" style="width:80px; height:80px;" /></a>
												</c:if>
											</div>
											<div style = "width:50%; float:right;">
												<p>${item.creditUserName}</p>
												<p>${item.creditUserLxr}</p>
												<p>${item.creditUserLxrPhone}</p>
											</div>
										</div>
									</td>
									<td width = "8%">${item.creditSqDate}</td>
									<td width = "8%">${item.creditDate}</td>
									<td width = "8%">${item.creditOverDate}</td>
									<td width = "8%">${item.creditMoney}</td>
									<td width = "8%">
										<p>实际:${item.creditSjMoney}</p>
										<p>已还款:${item.retHkMoney}</p>
										<p>剩余还款:${item.remainHkMoney}</p>
									</td>
									<td width = "8%">
										<p>实际:${item.lixiMoney}</p>
										<p>已还款:${item.retLxMoney}</p>
										<p>剩余还款:${item.remainLxMoney}</p>
									</td>
									<td width = "5%">
										<c:if test = "${item.creditState == -1}">审核不通过</c:if>
												<c:if test = "${item.creditState == 0}">未审核</c:if>
												<c:if test = "${item.creditState == 1}">审核通过</c:if>
												<c:if test = "${item.creditState == 2}">确认中</c:if>
												<c:if test = "${item.creditState == 3}">还款中</c:if>
												<c:if test = "${item.creditState == 4}">还款完成</c:if>
									</td>
									
									<td style = "text-align:right;" width = "8%">
										<a onclick="toURL('detail', '${item.creditId}')" class="btn mini red icn-only">详细</a>
										<c:if test = "${item.creditState == 0}">
											<a onclick="toURL('shenhe', '${item.creditId}', '1')" class="btn mini red icn-only">审核通过</a>
											<a onclick="toURL('shenhe', '${item.creditId}', '-1')" class="btn mini red icn-only">审核不通过</a>
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
 			$("input[name='creditUserName']").val("");
 			$("input[name='startCreditSqDate']").val("");
 			$("input[name='endCreditSqDate']").val("");
 			$("select[name='creditState']").val("");
 		});
	});
    

	// 功能函数
	function toURL(action, id, param1){
		var url = "";
		if(action == 'detail'){ // 详细功能
			url = "${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxglSxHkListCtrl-getSxHkDetail?creditId="+id;
			window.location.href = url;
		}
	}
</script>
<%@ include file="../footer.jsp"  %>