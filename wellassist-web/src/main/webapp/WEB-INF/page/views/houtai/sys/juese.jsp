<%@ include file="../../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row-fluid" id="rt-list-area">
	<div class="span12">
		<div class="alert alert-warning">
			<i class="icon-search"></i>
			<input type="text" class="m-wrap medium" style="margin-bottom:0px;" id="searchKey" value="" placeholder="">
			<button type="button" class="btn blue" onclick="onSearch()">搜索</button>
		
			<a href="javascript:onEditRole(0);" class="btn blue fr"><i class="icon-plus"></i> 添加</a>
		</div>				
		<div id="table_content"></div>
		<div id="table_pagination" class="table_pagination"></div>
	</div>
</div>

<div class="row-fluid" id="rt-detail-area" style="display:none;"></div>

<script type="text/javascript">

	jQuery(document).ready(function() { 
		onSearch();
	});

	//--- 列表分页 ---//   	
    $('#table_pagination').pagination({ 
        total       : 1, 
        pageSize    : 10,
        pageNumber  : 1,
        pageList	: [5, 10, 15, 20, 25],
		layout      : ['list','first','prev','links','next','last'],
        onSelectPage:function(pageNum, pageSz){
            $(this).pagination('loading');
            onSearch();
            $(this).pagination('loaded');
        }  
    });
	
	//--- 列表搜索 ---//
    function onSearch(){
		var searchKey = $("#searchKey").val();
        var option = $('#table_pagination').pagination("options");
        $.post("${pageContext.request.contextPath}/ht/sys/JueSeCtrl-search",
            {	
        		searchKey : searchKey,
				pageNumber	: option.pageNumber,
				pageSize	: option.pageSize,
			}, function(data) {
            $("#table_content").html(data);
        });
    }
	
 	function onEditRole(id)
 	{
		$.post("${pageContext.request.contextPath}/ht/sys/MenuCtrl-getRoleInfo", {roleId: id}, function(data) {
            $("#rt-list-area").hide();
			$("#rt-detail-area").show();
			$("#rt-detail-area").html(data);
        });
 	}
 	
 	//删除栏目
 	function onDeleteRole(id)
 	{
 		var Url = '?act=del&role_id='+id;
 		ShowWindowAlert("提示：","您确定删除该角色吗！","确定","取消",function(){
 			location = Url;
 		});
 	}
</script>	
<%@ include file="../../footer.jsp"  %>