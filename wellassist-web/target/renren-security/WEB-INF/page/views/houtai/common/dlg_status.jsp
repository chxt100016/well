<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!-- <link href="Public/css/dialog.css" rel="stylesheet" type="text/css" /> -->

<div id="dlg-edit" style="display:none;">
	<div class="ui-dialog" id="editDialogBody" style="width: 500px;">
		<div class="ui-dialog-title" style="width: 480px; ">      
			<span id="dlg-edit-title"></span>     
		</div>    
		<div class="ui-dialog-content" style="height: 300px; width: 480px; overflow: hidden; ">
			<div class="tip-box icon-box" style="width: 330px;">
				<div class="item-fore" style="width: 100%;margin-left:0px;">
					<table style="width: 98%;">
						<tr>
							<td style="width:70px;">状态 : </td>
							<td style="">
							<label class="radio fl"><input type="radio" name="dlg_state" value="1" />&nbsp;通过</label>
							<label class="radio fl ml10"><input type="radio" name="dlg_state" value="4" />&nbsp;不通过</label>
							</td>
						</tr>
						<tr>
							<td style="width:70px;">审核人 : </td>
							<td style="">
							<select id="dlg_shenheren" name="dlg_shenheren" data-placeholder="请输入审核人"
							 class="chzn-select"  style="">
							<option value=""></option>
							<c:forEach var="item" items="${arrayList}" varStatus="status">
								<option value="${item.id}">${item.adminName}</option>
							</c:forEach>
						</select>
							</td>
						</tr>
						<tr>
							<td style="width:70px;">原因 : </td>
							<td style=""><textarea id="dlg-edit_content" style="width:100%;height:130px;margin-top:15px;" disabled></textarea></td>
						</tr>
					</table>
				</div>
				<div class="edit-btn">
					<a href="javascript:void(0)" id="dlgBtnConfirm" class="btn blue" style="width:60px;margin-right:10px;">确认</a>
					<a href="javascript:$('#dlg-edit').hide()" class="btn" style="width:60px;margin-right:10px;">取消</a>
				</div>
			</div>
		</div>
		<a class="ui-dialog-close" href="javascript:$('#dlg-edit').hide()" title="关闭"><span class="ui-icon ui-icon-delete"></span></a>
	</div>
	<div class="ui-mask" style=""></div>
</div>

<script type=text/javascript>
	
	//显示警报对话框
	function openStatusDialog(title,state,content,processFunc){
		var wHeight = ($(window).height()-390)/2;
		var wWidth  = ($(window).width()-480)/2;
		if(wHeight < 0) wHeight = 0;
		if(wWidth  < 0) wWidth  = 0;
		
		$("#dlg-edit").show();
	    $("#editDialogBody").css({top:wHeight,left:wWidth});
	    
	    $("#dlg-edit-title").html(title);
	    $("#dlg-edit_content").html(content);
	    $("input[name='dlg_state']").eq(state).attr('checked',true);
	    

	  
	    $("#dlgBtnConfirm").unbind("click").bind("click", function(){
			if (processFunc && typeof(processFunc) == "function"){
				var state   = $("input[name='dlg_state']:checked").val();
				var content = $("#dlg-edit_content").val();
				var shenheren = $("#dlg_shenheren").val();
				if(state == 4 && content == ''){
					 alert("请输入不通过原因");
					 return;
				}
				if(state == 1 && shenheren == ''){
					 alert("请输入审核人");
					 return;
				}
				
				processFunc(state,content,shenheren);
				$('#dlg-edit').hide();
			} 
		});
	}
	$("input[name='dlg_state']").click(function() {
		if($(this).val()==1){
			$("#dlg-edit_content").attr("disabled", "disabled");
			$("#dlg_shenheren").removeAttr("disabled");
			
		}else{
			$("#dlg-edit_content").removeAttr("disabled");
			$("#dlg_shenheren").attr("disabled", "disabled");
			
		}
  		  
  	});
	//屏幕滚动时固定警报对话框
    $(window).scroll(function(){
    	var wHeight = ($(window).height()-250)/2;
		if(wHeight < 0) wHeight = 0;
		
        $("#editDialogBody").css({position:'fixed',top:wHeight});
        $(".ui-mask").css({position:'fixed',top:'0px'});
	});
	
</script>