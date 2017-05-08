<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
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
	.chkStar{
		color:#dd0000;
	}
</style>	
<div style="margin-left: 0px; margin-top:20px;">
	<div id="wlxxDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
				
				<div style="text-align:center;font-size:24px;">发票信息</div>
			</div>
		</div>
		<form id="fapiaoForm" name="fapiaoForm" method="post" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-setFapiao">
			<div class="formDd">
				<div class="headDd">发票信息</div>
				<div class="rowDd">
					<div class="labeldd" align="right">发票编号:</div>
					<div class="contentdd">${billNo}</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right">开票单位:</div>
					<div class="contentdd">${billUnit}</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right">开票金额:</div>
					<div class="contentdd">${orderInfo.saleSjMoney}</div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right"><font class="chkStar">*</font>开票税号:</div>
					<div class="contentdd"><input name="billSh" /></div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right"><font class="chkStar">*</font>开票人:</div>
					<div class="contentdd"><input name="toUserName" /></div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right"><font class="chkStar">*</font>快递号:</div>
					<div class="contentdd"><input name="kdNo" /></div>
				</div>
				<div class="rowDd">
					<div class="labeldd" align="right"><font class="chkStar">*</font>快递名称:</div>
					<div class="contentdd"><input name="kdName" /></div>
				</div>
				<div style="margin:10px 0px 0px 46px;">
					<input type="hidden" name="orderId" value="${orderInfo.orderId}" />
					<input type="hidden" name="billNo" value="${billNo}" />
					<input type="hidden" name="billUnit" value="${billUnit}" />
					<input  name="pubbtn"  type="button" value="返回" class="bluebutton" style="font-size:20px;border-radius:6px;" onclick="goBack();">
					<input id="pubbtn"  name="pubbtn"  type="submit" value="确定" class="bluebutton" style="font-size:20px;border-radius:6px;">
					
					
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	
	
	// validate检查
	$("#fapiaoForm").validate({
	    rules: {
	    	billSh		:{required: true},
	    	toUserName	:{required: true},
	    	kdNo		:{required: true},
	    	kdName		:{required: true}
	    },
	    messages: {
	    	billSh		:{required: "请输入开票人!"},
	    	toUserName	:{required: "请输入开票人!"},
	    	kdNo		:{required: "请输入快递号!"},
	    	kdName		:{required: "请输入快递名称!"}
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
		    	$.post($("#fapiaoForm").attr("action"),$("#fapiaoForm").serialize(),function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-orderList";
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