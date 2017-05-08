<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*,org.wella.common.utils.CommonUtil" %>
<%
	int gap = 5;
	int pageSize = Integer.parseInt(request.getAttribute("pageSize").toString());
	int curentPage = Integer.parseInt(request.getAttribute("page").toString());
	int totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
	int totalPage = (int)Math.floor((totalCount + pageSize - 1) / pageSize) - 1;
%>

<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">公告列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/notice/HoutaiNoticeListCtrl-getNoticeList">	
	            		<label>
	            			公告名称 : <input  name="noticeName"  value="${param.noticeName}" class="form-control input-sm houtai-search-control" placeholder="公告名称" />
	            			&nbsp;&nbsp;
	            			公告状态 : <select name="state">
	            						<option value="">全部</option>
	            						<option value="0"  <c:if test="${param.state=='0'}" >selected="selected"</c:if> >暂停</option>
	            						<option value="1"  <c:if test="${param.state=='1'}" >selected="selected"</c:if> >正常</option>
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
							<th>编号</th>
							<th>公告名称</th>
							<th>创建者</th>
							<th>公告类型</th>
							<th>状态</th>
							<th>日期</th>
							<th style = "text-align:right;">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${noticeList}" varStatus="status">
								<tr>		
									<td>${item.noticeId}</td>
									<td>${item.noticeTitle}</td>
									<td>${item.createUserId}</td>
									<td>${item.noticeType}</td>
									<td>
										<c:if test="${item.state=='0'}">正常</c:if>
										<c:if test="${item.state=='1'}">暂停</c:if>
									</td>			
									<td>${item.createDate}</td>
									<td style = "text-align:right;">
										<a href="javascript:getNoticeDetail('${item.noticeId}')">编辑</a> | 
										<a href="javascript:delNotice('${item.noticeId}')">删除</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${noticeList== null || fn:length(noticeList) == 0}">
						     	<tr><td colspan="10">没有资料!</td></tr>
						    </c:if>
	                    </tbody>
                  </table>
            </div>
		</div>
		
		<div class="page-area">
		<%
			out.println(CommonUtil.DispPagination(totalPage, curentPage, gap, "searchData", "上一页", "下一页"));
		%>
		</div>
		<div class="controls" style="margin-left:5px;padding-top:20px;">
			<button type="button" class="btn btn-primary" style="margin-left:30px;" onclick="addNotice();">添加</button>
		</div>
	</div>
</div>

<script type="text/javascript">
 	$(function(){
 		$("#btn_clear").click(function(){
 			$("input[name='noticeName']").val("");
 			$("select[name='state']").val("");
 		});
 		
 		$("#btn_search").click(function(){
 			searchData(1);
 		});
 	});
 	function getNoticeDetail(noticeId){
 		window.location.href = "${pageContext.request.contextPath}/houtai/notice/HoutaiNoticeListCtrl-getNoticeDetail?noticeId=" + noticeId;
 	}
 	function addNotice(){
 		location.href="${pageContext.request.contextPath}/houtai/notice/HoutaiNoticeListCtrl-getNoticeDetail";
 	}
 	
 	function searchData(page) {
 		$("#page").val(page);
		$("#searchFrm").submit();
	}
 	function delNotice(idx){
 		if(confirm("你确定删除吗？")){
	 		$.post("${pageContext.request.contextPath}/houtai/notice/HoutaiNoticeListCtrl-delNotice",{noticeId:idx},function(data){
				 var ret = $.parseJSON(data); 
				 if(ret.state=="1"){
					alert("删除成功！");
					searchData(1);
	           	 } else {
		    		 alert(ret.msg);
	        	 }
			})
			.error(function(data){
				alert("操作失败！");
			})
 		}
 	}
</script>

<%@ include file="../footer.jsp"  %>