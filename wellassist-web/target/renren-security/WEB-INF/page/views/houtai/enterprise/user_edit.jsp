<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>	
<div class="row-fluid">
<div class="span12">
	<div class="portlet box blue">

		<div class="portlet-title">
			<div class="caption"><i class="icon-reorder"></i>基本信息</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a>
				<a href="javascript:;" class="reload"></a>
			</div>
		</div>

		<div class="portlet-body form">
			<div action="#" class="form-horizontal">
				<div class="control-group">
					<label class="control-label"><span class="required">* </span>用户名 : </label>
					<div class="controls">
						<input id="userName" type="text" class="span3 m-wrap " value="${userInfo.regName}"/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"><span class="required">* </span>手机号 : </label>
					<div class="controls">
						<input id="userMobile" type="text" class="span3 m-wrap " value="${userInfo.mobileAddr}"/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<button type="submit" class="btn blue"  onclick="onSaveInfo()">保存信息</button>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"><span class="required">* </span>密码 : </label>
					<div class="controls">
						<input id="userPass" type="password" class="span3 m-wrap " value=""/>
						<span class="help-inline"></span>
					</div>
				</div>
				
				<div class="control-group last">
					<label class="control-label"><span class="required">* </span>重密码 : </label>
					<div class="controls">
						<input id="rpt-password" type="password" class="span3 m-wrap"/>
						<span class="help-inline"></span>
					</div>
				</div>
				
<!-- 				<div class="control-group hide"> -->
<!-- 					<label class="control-label"><span class="required">* </span>挂账金额 : </label> -->
<!-- 					<div class="controls"> -->
<!-- 						<input id="userMoney" type="text" class="span3 m-wrap " value="{$info.money}"/> -->
<!-- 						<span class="help-inline"></span> -->
<!-- 					</div> -->
<!-- 				</div> -->
				
				<div class="form-actions">
					<button type="submit" class="btn green" onclick="onSavePass()">保存密码</button>
					<button type="button" class="btn" onclick="goHistoryPage()">返回</button>  
				</div>

			</div>
		</div>
	</div>
</div>
</div>
<input type="hidden" id="userId"    value="${userInfo.id}"/>

<script>
	
	jQuery(document).ready(function() {  
		
	});
	
	//保存信息
	function onSaveInfo()
	{
		//检查输入信息
		if(!checkContent('userName', 	'请输入用户名!')) 	{return;}
		if(!checkContent('userMobile', 	'请输入手机号!')) 	{return;}
		if(!checkMobilePhone('userMobile')) {
			showControlTip('userMobile','请您输入有效的手机号！');
			return;
		}
		
		$.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-saveUserInfo", {
			  selIdx	: $("#userId").val(),
			  regName	: $("#userName").val(),
			  mobileAddr	: $("#userMobile").val()
		 }, 
		 function(data) {
			if(data.state == "0"){
				showAlert("参数错误!","warn");
			}
			else if(data.state == "1"){
				showAlert("用户名或手机号已存在，保存失败!","success");			
			}
			else if(data.state == "2"){
				showAlert("保存成功!","success");
			}
			else{
				showAlert("保存失败!","warn");
			}
		},'json');
	}
	
	function onSavePass(){
		//检查密码信息
		if(!checkContent('userPass', 	'请输入密码!')) 	{return;}
		if(!checkContent('rpt-password', '请输入重新密码!')) 	{return;}
		
		var userPass = $("#userPass").val();
		var rptPassword = $("#rpt-password").val();
		
		if($.trim(userPass) == '' || userPass != rptPassword) {
			$("#rpt-password").parent().parent().addClass("error");
			$("#rpt-password+span").html('请再输入重新密码');
			return;
		}
		
		$.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-saveUserPass", {
			  selIdx	: $("#userId").val(),
			  regPass	: $("#userPass").val()
		 }, 
		 function(data) {
			if(data.state == "1"){
				showAlert("保存成功!","success");
			}
			else{
				showAlert("保存失败!","warn");
			}
		},'json');
	}
	
	function goHistoryPage(){
        $("#rt-list-area").show();
        $("#rt-detail-area").hide();
        onSearch();
    }
</script>
			

	