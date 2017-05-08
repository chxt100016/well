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
		<div class="ui-dialog-content" style="height: 330px; width: 480px; overflow: hidden; ">
			<div class="tip-box icon-box" style="width: 360px;">
				<div class="item-fore" style="width: 100%;margin-left:0px;">
					<table style="width: 98%;">
						<tr><td style="">
							<textarea id="dlg-edit_content" style="width:100%;height:220px;">
							</textarea>
						</td></tr>
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
	function openEditDialog(title,content,processFunc){
		var wHeight = ($(window).height()-420)/2;
		var wWidth  = ($(window).width()-480)/2;
		if(wHeight < 0) wHeight = 0;
		if(wWidth  < 0) wWidth  = 0;
		
		$("#dlg-edit").show();
	    $("#editDialogBody").css({top:wHeight,left:wWidth});
	    
	    $("#dlg-edit-title").html(title);
	    $("#dlg-edit_content").html(content);
	    
	    $("#dlgBtnConfirm").unbind("click").bind("click", function(){
			if (processFunc && typeof(processFunc) == "function"){
				processFunc($("#dlg-edit_content").val());
				$('#dlg-edit').hide();
			} 
		});
	}

	//屏幕滚动时固定警报对话框
    $(window).scroll(function(){
    	var wHeight = ($(window).height()-250)/2;
		if(wHeight < 0) wHeight = 0;
		
        $("#editDialogBody").css({position:'fixed',top:wHeight});
        $(".ui-mask").css({position:'fixed',top:'0px'});
	});
	
</script>
