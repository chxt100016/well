<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<style>
	#sample_editable_1 .txt_sort {margin-bottom:0px; height:25px !important; min-height: 25px;}
</style>

<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
	<thead>
		<tr>
			<th style = "width:50px;">ID </th>
			<th style = "">标题</th>
			<th style = "">链接</th>
			<th class="sortButton" value="sortNum" style = "width:60px;">排序</th>
			<th style = "">显示</th>
			<th style = "">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="vo" items="${menuList}" varStatus="status">
		<tr>		
			<td>
				<c:if test="${ empty vo.menuId}">无</c:if>
				<c:if test="${ !empty vo.menuId}">${vo.menuId}</c:if>
			</td>
			
			<td>
				<c:if test="${ empty vo.title}">无</c:if>
				<c:if test="${ !empty vo.title}">${vo.title}</c:if>
			</td>			
			<td>
				<c:if test="${ empty vo.url}">无</c:if>
				<c:if test="${ !empty vo.url}">${vo.url}</c:if>
			</td>
			<td>${vo.orderNum}</td>	
			<td>
				<c:if test="${vo.isShow==0}"><span class="badge">&nbsp;否&nbsp;</span></c:if>
				<c:if test="${vo.isShow==1}"><span class="badge badge-info">&nbsp;是&nbsp;</span></c:if>
			</td>
			<td>
				<a href="javascript:onGetDetailData(${vo.menuId})" class="btn mini icn-only"  title="编辑"><i class="icon-share"></i></a>
				<a href="javascript:onDeleteData(${vo.menuId})" class="btn mini red icn-only" title="删除"><i class="icon-remove icon-white"></i></a>
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
		$.post("${pageContext.request.contextPath}/ht/sys/MenuCtrl-setSortNum",{selIdx: selIdx,number:number}, function(data) {
            
        });
	});
</script>