<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<!-- custom css -->
	<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
	<style>
		.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:bold;font-size:24px;}
		.ware_button{float:right;color: white;background: red;padding: 6px;border-bottom-left-radius: 8px;border-top-left-radius: 8px;border-top-right-radius: 8px;border-bottom-right-radius: 8px;width: 61px;text-align: center;cursor:hand;font-size:16px;}
		.ware_maker_label{color: red;padding: 6px;float: left;}
		.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
		span.selectedtab{ background:#0077dd;color:white;}
		.row-fld{margin-left:48px;margin-bottom: 24px; font-size:14px;}
		.row-fld span{margin-right: 64px;}
		.my_button{padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:16px;border-radius: 6px;border:none; width:100px !important;}
		.error-div{margin-left:70px;}
	</style>
</head>
<body style="margin:0; padding:0; background: #f5f5f5;">
	<div class="main-wrapper" style="margin-left: 0px; margin-top:20px;">
		<div align=center style="padding-top:12px;">
			<div align=left style="width:1000px;">
				<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
					
					<div style="text-align:center;font-size:20px; font-weight:600;">授信信息</div>
				</div>
			</div>

			<form id = "infoForm" action="<c:url value="/front/customer/CustomerHomeCtrl-orderSuccess"/>" method="post">
				<input type = "hidden" id = "creditId" name = "creditId" value = "${info.creditId}"/>
				<div align=left style="width:1000px; background: white;box-shadow: 2px 2px 8px #808080;padding-bottom:16px;">
					<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 24px; padding-bottom:12px;border-bottom:solid 2px #d0d0d0;font-size:18px;">公司信息</div>
					<div class = "row-fld" >
						<div style = "margin-left:10px;line-height:56px; float:left;">
							<c:if test = "${info.companyImg == ''}">
								<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:50px; height:50px;" /></a>
							</c:if>
							<c:if test = "${info.companyImg != ''}">
								<a class="fancybox" href="${pageContext.request.contextPath}/${info.companyImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${info.companyImg}"  style="width:50px; height:50px;"  onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>	
							</c:if>	 
						</div>
						<div style = "margin-left:10px;line-height:56px; color: #807B7B;float:left; font-size:10px;">
							公司名称:&nbsp;&nbsp;${info.userName}
						</div>
						<div style = "margin-left:10px;line-height:56px; color: #807B7B; float:left; font-size:10px;">
							联系人:&nbsp;&nbsp;${info.companyLxr}
						</div>
						<div style = "margin-left:10px;line-height:56px; color: #807B7B; float:left; font-size:10px;">
							联系电话:&nbsp;&nbsp;${info.companyLxrPhone}
						</div>
					</div>
					<div style = "clear:both;">
					<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 24px; padding-bottom:12px;border-bottom:solid 2px #d0d0d0;font-size:18px;">基本信息</div>
					<div class = "row-fld">
						<span >申请日期:&nbsp;&nbsp; ${info.creditSqDate}</span>
						<span >申请金额: &nbsp;&nbsp;${info.creditMoney}&nbsp;元</span>
						<span > 利息率: &nbsp;&nbsp;${info.lixiRate} %</span>
					</div>
					<div class = "row-fld">
						<span>授信金额: &nbsp;&nbsp;<input name="creditSjMoney" id="creditSjMoney"  placeholder="请输入只数字！" style="width:180px;margin-top:-6px;margin-right: 12px; padding-left:3px;" value = "${info.creditMoney}">元</span>
					</div>
					<div class = "row-fld"  style = "margin-left:76px;">
						<span>账期: &nbsp;&nbsp;<input name="creditOverDate" id="creditOverDate"  placeholder="请输入只整数！" style="width:180px;margin-top:-6px;margin-right: 12px;padding-left:3px;" value = "3">个月</span>
					</div>
					<div align=center style="margin-bottom: 24px;">
						<input type="submit" class="bluebutton my_button"  value="通过">
						<input type="button"  class="bluebutton my_button" style = "margin-left:10px;" value="不通过"  onClick = "toURL('butongguo');">
					</div>
				</div>
			</form>
		</div>

	</div>
</body>
<script type="text/javascript">
	// 初始化函数
	$(function(){
		$('.fancybox').fancybox();
		// validation检查	
    	$("#infoForm").validate({
    	    rules: {
    	    	creditSjMoney:{required: true, number:true},
    	    	creditOverDate:{required: true, digits:true}
    	    },
    	    messages: {
    	    	creditSjMoney:{required:"请输入授信金额！" , number:"请输入只数字形式！"},
    	    	creditOverDate:{required:"请输入账期！" , digits:"请输入只整数形式！"}
    	    },
    	    errorPlacement: function (error, element) { 
    	    	if($(element).closest('.row-fld').children().filter("div.error-div").length < 1) 
    			$(element).closest('.row-fld').append("<div class='error-div'></div>");	
    		$(element).closest('.row-fld').children().filter("div.error-div").append(error);
    	    },
    	    submitHandler: function(form){
    	    	var creditMoney = '${info.creditMoney}';
    	    	var creditId = $("#creditId").val();
    	    	var creditSjMoney = $("#creditSjMoney").val();
    	    	var creditOverDate = $("#creditOverDate").val();
    	    	
    	    	if(parseInt(creditSjMoney) <= 0){
    	    		alert("授信金额不正确！");
    	    		return;
    	    	}
    	    	
    	    	if(parseInt(creditSjMoney) > parseInt(creditMoney)){
    	    		alert("授信金额比申请金额不大!");
    	    		return;
    	    			
    	    	}
    	    	$.post("${pageContext.request.contextPath}/front/fkf/FkfOrderCtrl-sxProcess",
    	    		{creditId:creditId, creditSjMoney:creditSjMoney,creditState: 3,creditOverDate:creditOverDate },function(data){
    	    		alert(data.content);
    	            if(data.state==1 ){
    	            	goBack();
    	            }
    	      	}, "json");
    	    }
    	});	
	});
	
	// 不通过点击时间
	function toURL(action, id){
		if(action == 'butongguo'){
			var creditId = $("#creditId").val();
			$.post("${pageContext.request.contextPath}/front/fkf/FkfOrderCtrl-sxProcess",
	    		{creditId:creditId,creditState: -2 },function(data){
	    		alert(data.content);
	            if(data.state==1 ){
	            	goBack();
	            }
	      	}, "json");
		}
	}
	// 返回点击时间	
	$("#back").click( function(){
		goBack();
	});
	
</script>
</html>
<%@ include file="../footer.jsp"%>