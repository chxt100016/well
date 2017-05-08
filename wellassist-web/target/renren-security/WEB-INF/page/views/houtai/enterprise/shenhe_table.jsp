<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>

<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
	<thead>
		<tr>
			<th style = "">No</th>
			<th style = "">企业名</th>
			<th style = "">用户名</th>
			<th style = "">手机号</th>
			<th class = "center" style = "width:60px;">城市</th>
<!-- 			<th class = "center" style = "width:70px;">ä»»å¡</th> -->
			<th style = "" class="sortButton" value="regTime">注册时间</th>
			<th style = "" >供货方</th>
			<th class = "center" style = "width:60px;">状态</th>
			<th class = "center" style = "width:200px;">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${arrayList}" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${item.enterpriseName}</td>
			<td>${item.regName}</td>
			<td>${item.mobileAddr}</td>
			<td class = "center">${item.regAddrId}</td>	
			<td class = "center">${item.regTime}</td>	
			<c:if test="${item.ghfName==Null}"><td class = "center">无</td></c:if>
			<c:if test="${item.ghfName!=Null}"><td class = "center">${item.ghfName}</td></c:if>
			<td class = "center">
				<c:if test="${item.state==1}"><span class="label label-warning">未审核</span></c:if>
				<c:if test="${item.state==2}"><span class="label label-success">审核中</span></c:if>
				<c:if test="${item.state==4}"><span class="label label-error">不通过</span></c:if>
			</td>
			<td class = "center">
				<a href="javascript:onGetDetailData(${item.id})" class="btn mini icn-only"  title="编辑"><i class="icon-share"></i></a>
				<a href="javascript:onSetStatus(${item.id},${item.state})" class="btn mini purple icn-only" title="审核"><i class="icon-filter icon-white"></i></a>
			
<%-- 				<c:if test="${item.isLock==0}"> --%>
<%-- 					<a href="javascript:onSetLockState(${item.id}, 1)" class="btn mini green" title="可用"><i class="icon-ok-circle icon-white"></i></a></c:if> --%>
<%-- 				<c:if test="${item.isLock==1}"> --%>
<%-- 					<a href="javascript:onSetLockState(${item.id}, 0)" class="btn mini yellow" title="不可用"><i class="icon-ban-circle icon-white"></i></a></c:if> --%>
<%-- 				<a href="javascript:onDeleteData(${item.id})" class="btn mini red icn-only" title="删除"><i class="icon-remove icon-white"></i></a> --%>
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
		if($(this).attr("value") == "{$sortFld}"){
			$(this).attr('class', 'sortButton sorting_'+"{$sortDesc}");
		}
		else{
			$(this).attr('class', 'sortButton sorting');
		}
	});
	
	$(".sortButton").click(function(){
		if($(this).attr("value") == "{$sortFld}"){
			if("{$sortDesc}" == "desc")
				$("#sortDesc").attr("value", "asc");
			else 
				$("#sortDesc").attr("value", "desc");
		} else {
			$("#sortFld").attr("value", $(this).attr("value"));
			$("#sortDesc").attr("value", "desc");
		}
		onSearch();
	});
</script>