<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.form-horizontal .control-group {margin-bottom: 5px;padding-bottom: 7px;border-bottom: 1px dotted #E6E6E6;}
	.form-horizontal .control-group.last{margin-bottom: 0px;border-bottom:0px;}
	.checkbox{float: left;color:#666;}
</style>
<div class="row-fluid">
<div class="span12">
	<div class="portlet box blue">

		<div class="portlet-title">
			<div class="caption"><i class="icon-reorder"></i>后台栏目编辑</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a>
				<a href="javascript:;" class="reload"></a>
			</div>
		</div>

		<div class="portlet-body form">
			<div action="#" class="form-horizontal">
				<div class="control-group">
					<label class="control-label">基本分类 : </label>
					<div class="controls">
						<select class="m-wrap" id="menu_parentIdx" onchange="">
							<option value="0"></option>
							<c:forEach var="item" items="${topMenuList}" varStatus="status">
								<option value="${item.menuId}" <c:if test="${menuInfo.parentId == item.menuId}">selected</c:if>>${item.title }</option>
							</c:forEach>						
						</select>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"><span class="required">* </span>题目 : </label>
					<div class="controls">
						<input type="text" class="span3 m-wrap" id="menu_title" value="${menuInfo.title}"/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"><span class="required">* </span>链接 : </label>
					<div class="controls">
						<input type="text" class="span3 m-wrap" id="menu_linkUrl" value="${menuInfo.url }"/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">显示状态 : </label>
					<div class="controls">
						<label class="radio"><input type="radio" name="optShow" value="0" checked />&nbsp;否</label><label class="radio">
						<input type="radio" name="optShow" value="1" <c:if test="${menuInfo.isShow==1}">checked</c:if>>&nbsp;是</label>
					</div>
				</div>
				
				<div class="control-group last">
					<label class="control-label">排序 : </label>
					<div class="controls">
						<input type="text" class="span3 m-wrap" id="menu_sortNum" value="${menuInfo.orderNum}"/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="form-actions">
					<button type="submit" class="btn blue" onclick="onSaveMenuData()">保存</button>
					<button type="button" class="btn" onclick="goHistoryPage()">返回</button>         
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<input type="hidden" id="menuId"    value="${menuInfo.menuId}"/>
	
<script laguage="javascript">

	jQuery(document).ready(function() {   		
	});
	

	
	//保存文章资料
	function onSaveMenuData(){
		if(!checkContent('menu_title','请输入题目')) {return;}
		if(!checkContent('menu_linkUrl','请输入链接') && $("#menu_parentIdx").val() != 0) {return;}
		
		var menuSortNum	= $("#menu_sortNum").val();
		if(!jQuery.isNumeric(menuSortNum)){
			$("#menu_sortNum").parent().parent().addClass("error");
			$("#menu_sortNum+span").html('排序只该输入整数');
			return;
		}
		
		var is_show 		= $("input[name='optShow']:checked").val(); 
		var parentIdx 	= $('#menu_parentIdx option:selected').val();
		
		var menuId 	= $("#menuId").val();
		var menuTitle 	= $("#menu_title").val();
		var menuUrl		= $("#menu_linkUrl").val();
		
		$.post("${pageContext.request.contextPath}/ht/sys/MenuCtrl-saveMenuInfo", {
				menuId		: menuId,
				menuTitle	: menuTitle,
				menuUrl		: menuUrl,
				menuType	: 0,
				sortNum 	: menuSortNum,
				parentIdx	: parentIdx,
				is_show		: is_show
		}, 
		function(data) {
			var ret = $.parseJSON(data);

			if(ret.result > 0){
				$("#menuId").val(ret.result);
				showAlert("提示", "保存成功!", "确认");
			}
			else{
				showAlert("提示", "保存失败!", "确认");
			}
		});
	}
	
    function goHistoryPage(){
        $("#rt-list-area").show();
        $("#rt-detail-area").hide();
        
        onSearch();
    }
    
	
</script>