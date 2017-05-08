<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<style>
	#sample_editable_1 .txt_sort {margin-bottom:0px; height:25px !important; min-height: 25px;}
</style>

<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
	<thead>
		<tr>
			<th style = "width:80px;">角色代码</th>
			<th style = "width:120px;">组名称</th>
			<th class="sortButton" value="sortNum" style = "width:60px;">排序</th>
			<th style = "">权限</th>
			<th style = "width:100px;">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="vo" items="${roleList}" varStatus="status">
		<tr>		
			<td>${vo.roleId}</td>
			<td>
				<c:if test="${ empty vo.roleName}">无</c:if>
				<c:if test="${ !empty vo.roleName}">${vo.roleName}</c:if>
			</td>
			<td>${vo.orderId}</td>			
			<td>
				<c:if test="${ empty vo.permission}">无</c:if>
				<c:if test="${ !empty vo.permission}">${vo.permission}</c:if>
			</td>	
			<td>
				<a href="javascript:onEditRole(${vo.roleId})" class="btn mini icn-only"  title="权限设定"><i class="icon-share"></i></a>
				<a href="javascript:onDeleteRole(${vo.roleId})" class="btn mini red icn-only" title="删除"><i class="icon-remove icon-white"></i></a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>         

<script type="text/javascript">
	jQuery(document).ready(function() {

	});
	
	$('#table_pagination').pagination({ 
		total       : parseInt('${totalCount}'), 
		pageSize    : parseInt('${pageSize}'),
		pageNumber  : parseInt('${pageNumber}')
	});
	
	$(".sortButton").each(function(){
		if($(this).attr("value") == "${sortFld}"){
			$(this).attr('class', 'sortButton sorting_'+"${sortDesc}");
		}
		else{
			$(this).attr('class', 'sortButton sorting');
		}
	});
	
	$(".sortButton").click(function(){
		if($(this).attr("value") == "${sortFld}"){
			if("${sortDesc}" == "desc")
				$("#sortDesc").attr("value", "asc");
			else 
				$("#sortDesc").attr("value", "desc");
		} else {
			$("#sortFld").attr("value", $(this).attr("value"));
			$("#sortDesc").attr("value", "desc");
		}
		onSearch();
	});
	
	//--- 改变排序 ---//
	$("input[type='text'].txt_sort").change(function(){
		selIdx = $(this).attr("p-idx");
		number 	= $(this).val(); 
		$.post("${pageContext.request.contextPath}/ht/sys/JueSeCtrl-setSortNum",{selIdx: selIdx,number:number}, function(data) {
            
        });
	});
	
	
</script>