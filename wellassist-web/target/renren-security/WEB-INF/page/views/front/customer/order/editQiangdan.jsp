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
</style>	
<div style="margin-left: 0px; margin-top:20px;">
	<div id="qdDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">

				<div style="text-align:center;font-size:24px;">抢单详情</div>
			</div>
		</div>
		<form id="qdForm" action="${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-setQiangdan" method="post">
			<div class="formDd">
				<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;margin: 16px 48px;">
					<div style="height:30px;background:#e0e0e0;font-size:16px;">
						<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
							物流公司名
						</div>
						<div style="width:22%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
							联系人
						</div>
						<div style="width:22%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
							联系电话
						</div>
						<div style="width:14%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
							运费 
						</div>
						<div style="width:9%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;">
							选择
						</div>
					</div>
					<c:forEach var="item" items="${vehicleGrabList}">
						<div id='sjListContent_0' style='border-bottom: solid 1px #E0E0E0; overflow:auto;'>
							<div class='graybox' style='width:30%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>
								&nbsp;${item.companyName}
							</div>
							<div class='grayboxwithoutleft' style='width:22%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>
								&nbsp;${item.companyLxr}
							</div>
							<div class='grayboxwithoutleft' style='width:22%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>
								&nbsp;${item.companyLxrPhone}
							</div>
							<div class='grayboxwithoutleft' style='width:14%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>
								&nbsp;${item.grabMoney}
							</div>
							<div class='grayboxwithoutleft' style='width:9%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;'>
								<input type="radio" name="grabId" value="${item.grabId}" />
							</div>
						</div>
					</c:forEach>
					<c:if test="${vehicleGrabList== null || fn:length(vehicleGrabList) == 0}">
							  <div id='sjListContent_0' style='border-bottom: solid 1px #E0E0E0; overflow:auto; height:30px;font-size:14px;padding-left:10px; padding-top:5px;'>
							  	  	没有抢单
							  </div>	
				    </c:if>
				</div>
			    <input type="hidden" name="vehicleTrans" value="${vehicleTrans}" />
				<div style="margin: 40px 0px 40px 80px;">
					<input type="button" class="bluebutton" style="padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="返回" onclick="goBack();"  />
					<input type="submit" id="submit" class="bluebutton" style="padding: 8px 16px; font-size:20px; border-radius: 6px; border:none; <c:if test = "${fn:length(vehicleGrabList) == 0}">display:none;</c:if>" value="确认"   />
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
	$("#qdForm").validate({
	    rules: {
	    	grabId:{required: true}
	    },
	    messages: {
	    	grabId:{required: "请选择物流公司!"}
	    },
	    errorPlacement: function (error, element) {},
	    submitHandler: function(form){
	    	if(confirm("你要确定操作吗？")){
		    	$.post($("#qdForm").attr("action"),$("#qdForm").serialize(),function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-wlOrderList";
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