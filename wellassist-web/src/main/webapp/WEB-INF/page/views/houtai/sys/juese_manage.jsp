<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<link rel="stylesheet" href="<c:url value="/resources/houtai/css/dtree.css"/>">
<script src="<c:url value="/resources/houtai/js/dtree.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/resources/houtai/css/spMain.css"/>">    
<style>
	.form-horizontal .control-group {margin-bottom: 5px;padding-bottom: 7px;border-bottom: 1px dotted #E6E6E6;}
	.form-horizontal .control-group.last{margin-bottom: 0px;border-bottom:0px;}
	.checkbox{float: left;color:#666;}
</style>
<form method="POST" id="mainData" method="">
<div class="row-fluid">
<div class="span12">
	<div class="portlet box">

		<div class="portlet-title" style="background-color:rgb(77, 115, 254);">
			<div class="caption"><i class="icon-reorder"></i>角色编辑</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a>
				<a href="javascript:;" class="reload"></a>
			</div>
		</div>

		<div class="portlet-body" style="width:50%;float:left;">
			<div action="#" class="form-horizontal">
				<div class="control-group">
					<label class="control-label"><span class="required">* </span>角色名称： </label>
					<div class="controls">
		            <input type="text" id="input-name" class="input-text is-filled" name="roleName" maxlength="200" placeholder="请输入角色名称" value="${roleInfo.roleName}" required="">
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="control-group last">
					<label class="control-label">排序 : </label>
					<div class="controls">
						<input type="text" style="width:200px;" class="span3 m-wrap" id="menu_sortNum" value="${info.orderNum}" placeholder="请输入排序" required/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="form-actions" style="padding-left: 11px;">
					<button type="submit" class="btn blue">确定</button>
					<span style="width: auto;color: #f20;">&nbsp;&nbsp;&nbsp;带 * 的项是必填项，请确认无未填的必填项</span>     
				</div>
				
			</div>
		</div>
        <div class="form-right">
	        <div class="widget-container">
				<div class="dtree" id="dtree" style="min-width:150px;width:auto;"></div>
			</div>
	    </div>
	</div>
</div>
</div>
<input type="hidden" id="permiss" name="permiss" value="${roleInfo.permission}" />
<input type="hidden" id="roleId" name="roleId" value="${roleInfo.roleId}" />
<input type="hidden" id="roleId" name="act" value="${act}" />
<input type="hidden" id="roleType" name="roleType" value="${roleInfo.type}" />
<input type="hidden" id="manageType" name="manageType" value="${manageType}" />
</form>
	
<script type="text/javascript">

	function showAlert(message,type, confirmFunc){
		$("#dlgAlert p").html(message);
		$("#dlgAlertBut").click();
		$("#dlgAlert #cancel").hide();
		$("#dlgAlert #confirm").unbind("click").bind("click", function(){
			if (confirmFunc && typeof(confirmFunc) == "function") confirmFunc();
		});
	}
	
	$(function () {
		var retVal = '${retVal}';
 		if(retVal!="")
 			ShowWindowAlert("提示",retVal,"","确定");
 		loadAllMenu();
		setPermiss();
		$("input[type='checkbox']").click(function(){
			 var pId=this.id;
			 var parent = $(this).attr("codeval");
			 var cur_parent = $(this).attr("parent");
			 if(cur_parent>0)
	       		$("input[codeval='"+cur_parent+"']").prop("checked",true);
			 else
				$("input[parent='"+parent+"']").prop("checked",this.checked);
				 
	 	});
		
		$("#mainData").validate({
            rules: {
            	roleName: "required"
            },
            messages:{
            	roleName: "请输入角色名称"
            },submitHandler: function(form) {
            	$("#permiss").val("");
            	var codevalList='';
        		var codeval="";
        		
        		$("[name='where']").each(function(){
            	 	if($(this).prop("checked")){
	       				 codeval =$(this).attr("codeval");	
	       				 codevalList+= codevalList==""?codeval:","+codeval;
       			 	}
       		 	});
        		$("#permiss").val(codevalList);
        		$.post("${pageContext.request.contextPath}/ht/sys/MenuCtrl-editRoleFunc?act="+"${act}",$("#mainData").serialize(),function(data){
            		data = $.parseJSON(data);
            		if(data.result>1 ){
                    	showAlert("更新成功！","确定",function(){
                    		window.location.href = "${pageContext.request.contextPath}/ht/sys/JueSeCtrl-adminCtrl";
                    	});
                    }else{
                    	showAlert("提示","操作失败！","确定");
                    } 
              	});     		
            }
        });	
	});

	//dtree
	function loadAllMenu(){

		d = new dTree('d');
		d.config.check = 0;
		d.config.useStatusText = true;
		d.add(0,-1,' <strong>系统类目</strong>','');
		
		<c:forEach var="item" items="${menuList}" varStatus="status">
			d.add("${item.menuId}","${item.parentId}",'<input type=\'checkbox\' parent=\'${item.parentId}\' codeval=\'${item.menuId}\' nameval=\'${item.title}\' name=\'where\' value=\'\' id=\'${item.menuId}\'>${item.title}');
		</c:forEach>
		$("#dtree").html(d.toString());
	}
	function setPermiss()
	{
		var permiss = '${roleInfo.permission}';
		if(permiss!=null){
			var ele = new Array();
		    ele = permiss.split(",");
			$("[name='where']").each(function(){
				$(this).prop("checked",false);
				ele = $(this).attr("id");
				if(permiss.indexOf(ele)>=0)
					$(this).prop("checked",true);
			});
		}
	}
	


</script>