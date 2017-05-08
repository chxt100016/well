<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<link rel="stylesheet" 	href="<c:url value="/resources/altframe/common.css"/>">

<style>
	body{
		background: #f5f5f5;
	}
	div.formDd{
		width:1000px;
		text-align: left; 
		background: white;
		box-shadow: 2px 2px 8px #808080;
		padding:16px 0px 16px 0px;
	}
	div.headDd{
		margin-left:48px;
		margin-right:48px;
		padding-top:32px;
		margin-bottom: 24px;
		padding-bottom:12px;
		border-bottom:solid 2px #d0d0d0;
		font-size:18px;
	}
	div.rowDd{
		clear: both;
		height: 50px;
		line-height: 50px;
		font-size: 14px;	
	}
	.rowDd .labeldd{
		float: left;
		width: 100px;
	}
	.rowDd .contentdd{
		float: left;
		margin-left: 15px;
	}
	.backBtn{
		font-weight: bold;
    	cursor: pointer;
    	color: #2482df;
    	text-align:center;
    	font-size:16px;
    	float:left;
    	margin-top:6px;
	}
</style>	
<div style="margin-left: 0px; margin-top:20px;">
	<div id="ddpjDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
				
				<div style="text-align:center;font-size:24px;">评价</div>
			</div>
		</div>
		<form id="ddpjForm" action="${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-setPingjia" method="post">
			<div class="formDd">
				<div class="headDd">评价</div>
				<div class="rowDd">
					<div class="labeldd" align="right"><font class="chkStar">*</font> 评价状态:</div>
					<div class="contentdd">
						<label style="margin-right:20px;"><input name="pjState" type="radio" value="0" />&nbsp;好评 </label> 
						<label style="margin-right:20px;"><input name="pjState" type="radio" value="1" />&nbsp;中评 </label> 
						<label style="margin-right:20px;"><input name="pjState" type="radio" value="2" />&nbsp;差评 </label> 
					</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right"><font class="chkStar">*</font> 评价内容 :</div>
					<div class="contentdd"><textarea name="pjContent" style="width:600px; height:50px;"></textarea></div>
				</div>
				
				<input type="hidden" name="orderId" value="${orderInfo.orderId}" />

				<div style="margin: 40px 0px 40px 80px;">
					<input type="button"  class="bluebutton" style="padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="返回" onclick="goBack();"/>
					<input type="submit" id="submit" class="bluebutton" style="padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="确认评价" />
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

	$(".backBtn").click(function(){
		goBack();
	});
	
	// validate检查
	$("#ddpjForm").validate({
	    rules: {
	    	pjState		:{required: true},
	    	pjContent	:{required: true}
	    },
	    messages: {
	    	pjState		:{required: "请选择评价状态!"},
	    	pjContent	:{required: "请输入评价内容!"}
	    },
	    errorPlacement: function (error, element) {
	    	/*
	    	if($(element).closest('div').children().filter("div.error-div").length < 1) 
			$(element).closest('div').append("<div class='error-div'></div>");	
			$(element).closest('div').children().filter("div.error-div").append(error);
			*/
	    },
	    submitHandler: function(form){
	    	if(confirm("你要确定操作吗?")){
		    	$.post($("#ddpjForm").attr("action"),$("#ddpjForm").serialize(),function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-prodOrderList";
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
	    	}
	    }
    });
</script>

<%@ include file="../footer.jsp"%>