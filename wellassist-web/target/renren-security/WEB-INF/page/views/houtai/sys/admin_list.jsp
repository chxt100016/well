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
                  <h3 class="box-title">管理员列表</h3>
            </div>
          </div>
      	
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th width ="10%">ID </th>
							<th style = "">名称</th>
							<th style = "">手机</th>
							<th style = "">注册时间</th>
							<th style = "">权限</th>
							<th style = "">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="vo" items="${adminList}" varStatus="status">
								<tr>		
									<td>
										<c:if test="${ empty vo.id}">无</c:if>
										<c:if test="${ !empty vo.id}">${vo.id}</c:if>
									</td>
									<td>
										<c:if test="${ empty vo.adminName}">无</c:if>
										<c:if test="${ !empty vo.adminName}">${vo.adminName}</c:if>
									</td>			
									<td>${vo.adminPhone}</td>	
									<td>${vo.regTime}</td>
									<td>${vo.roleName}</td>
									<td>
										<a onclick="editAdmin('${vo.id}')" class="btn mini icn-only" >编辑</a>
										<a onclick="delAdmin('${vo.id}')" class="btn mini red icn-only">删除</a>
									</td>
								</tr>
							</c:forEach>
	                    </tbody>
	                  </table>
	              </div>
		</div>
		<div class="page-area">
		<%
			out.println(CommonUtil.DispPagination(totalPage, curentPage, gap, "searchData", "上一页", "下一页"));
		%>
		</div>
		<div class="controls" style="margin-left:5px;padding-top:20px;padding-bottom:20px;">
			<button type="button" class="btn btn-primary" style="margin-left:30px;" onclick="addAdmin();">添加</button>
		</div>
	</div>
</div>

<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/ht/sys/AdminCtrl-adminList">
	<input type="hidden" id="page" name="page" value="1" />
	<input type="hidden" id="act" name="act" value="" />
</form>

<input type=hidden id="sortFld"  value="sortNum">
<input type=hidden id="sortDesc" value="asc">


<div class="row-fluid" id="rt-detail-area" style="display:none;"></div>

<script type="text/javascript">

	$(function(){
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
	});
	
	
	function searchData(page) {
 		$("#page").val(page);
		$("#searchFrm").submit();
	}
	
    function editAdmin(adminId){
    	location.href="${pageContext.request.contextPath}/ht/sys/AdminCtrl-editAdmin?adminId="+adminId
	}
    function addAdmin(){
    	location.href="${pageContext.request.contextPath}/ht/sys/AdminCtrl-editAdmin";
	}
    function delAdmin(adminId){
    	if(confirm("你确定删除吗？")){
	    	$.post("${pageContext.request.contextPath}/ht/sys/AdminCtrl-delAdmin",{adminId: adminId}, function(data) {
	    		if(data.state==1){
	    			alert("删除成功！");
	    			searchData(0);
	    		}
	        }, "json");
    	}
	}
    
</script>
<%@ include file="../footer.jsp"  %>