<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<!-- custom css -->
	<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
	<style>
		.row-fld-half{margin-bottom: 12px; float:left; width:50%; line-height:30px;font-size:14px;}
		.row-fld-all {width:100%; line-height:30px;margin-bottom: 12px;font-size:14px;}
		.row-fld{margin-left:48px;margin-bottom: 24px; font-size:14px;}
	</style>
</head>
<body style="margin:0; padding:0; background: #f5f5f5;">
	<div style = "width: 1000px; overflow:auto;margin:auto; ">
		<div style="margin-top:20px;margin-bottom: 24px;">
			<div style="border-bottom: solid 1px #ccc; padding-bottom:12px;margin-top:20px;margin-bottom: 24px;margin-left: 48px;margin-right: 48px;">
				<div id="back" style="text-align:center;font-size:16px;float:left;margin-top:6px;">&lt;&lt;&nbsp;&nbsp;返回</div>
				<div style="text-align:center;font-size:24px;">抢单</div>
			</div>

			<form id = "infoForm" action="<c:url value="/front/sender/FrontSenderOrderCtrl-sqQd"/>" method="post">
				<input type = "hidden" name = "orderId" value = "${info.orderId}"/>
				<input type = "hidden" name = "orderNo" value = "${info.orderNo}"/>
				<input type = "hidden" name = "vehicleSize" value = "${info.vehicleSize}"/>
				<input type = "hidden" name = "orderData" id = "orderData"  />	
				<div align=left style="box-shadow: 2px 2px 8px #aaa;padding-bottom:16px;margin-left: 48px;margin-right: 48px; font-size:16px;background:white;">
					<div style="margin-left:32px;margin-right:32px;padding-top:32px; border-bottom:solid 1px #ccc;padding-bottom: 10px;">物流信息</div>
					<div style="margin-left:32px;margin-right:32px;padding-top:32px;">
						<div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">订单公司:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">${info.userName}</span>
							</div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">订单编号:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">${info.orderNo}</span>
							</div>
						</div>
						<div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">商品类型:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">
									<c:if test = "${info.prodType == '0'}">
										气体
								    </c:if>
								    <c:if test = "${info.prodType == '1'}">
										燃油
								    </c:if>		
								</span>
							</div>
							<div class = "row-fld-half">
								<span style="display:inline-block; text-align:right;">商品名称:</span>
								<span style="display:inline-block; text-align:left;margin-left:10px;">${info.prodName}</span>
							</div>
						</div>						
						<div class = "row-fld-all">
							<span style="display:inline-block; text-align:right;">购买数量:</span>
							<span style="display:inline-block; text-align:left;margin-left:10px;">${info.vehicleSize}顿</span>
						</div>
						<div class = "row-fld-all">
							<span style="display:inline-block; text-align:right;">提货地址:</span>
							<span style="display:inline-block; text-align:left; margin-left:10px;">${info.fromRegionName}&nbsp;${info.fromRegionAddr}</span>
						</div>
						<div class = "row-fld-all">
							<span style="display:inline-block; text-align:right;">配送地址:</span>
							<span style="display:inline-block; text-align:left;margin-left:10px;">${info.toRegionName}&nbsp;${info.toRegionAddr}</span>
						</div>
					</div>
					<div style = "clear:both;"></div>
					<div style="margin-left:32px;margin-right:32px; margin-top: 20px;padding-top:32px; border-bottom:solid 1px #ccc;padding-bottom: 10px;">
							抢单信息
							<span style="display:inline-block; text-align:right;width:10%;margin-right:2%; font-size:14px;">抢单金额</span>
							<input type="text" name="grabMoney" id="grabMoney" style="width:30%;">
					</div>
					<%--------------------------- 暂时 ---------------------------------------------- 					
					<div class = "one-fld" style="margin-left:18px;margin-right:32px;padding-top:32px;"></div>
					<div id="wlxxDiv">
						<div class="row-fld">
							<span style = "margin-right: 64px;">发货时间:</span>
							<input id="cfDate" name = "cfDate" style="height:18px;width:150px;font-size:14px;cursor:pointer;" value="" class="Wdate" type="text" onfocus="var ddDate=$dp.$('ddDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){ddDate.focus();},maxDate:'#F{$dp.$D(\'ddDate\')}'})">
							<span style = "margin-right: 64px; margin-left:64px;">预计收货时间:</span>
							<input id="ddDate"  name="ddDate" style="height:18px;width:150px;font-size:14px;cursor:pointer;" value="" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'cfDate\')}'})">
						</div>
						<div style="border:solid 1px #d0d0d0;font-size:24px;margin:16px 48px;overflow:auto;">
							<div style="border-bottom: solid 1px#E0E0E0; overflow:auto;">
								<div class="graybox" 			style="width:30%;line-height:50px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 司机名称 : <input type="text" id="sjmc" style="width:130px;" />
								</div>
								<div class="grayboxwithoutleft" style="width:30%;line-height:50px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 电话 : <input type="text" id="sjdh" style="width:130px;" />
								</div>
								<div class="grayboxwithoutleft" style="width:30%;line-height:50px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 车牌号 : <input type="text" id="cph" style="width:130px;" />
								</div>
								<div class="grayboxwithoutleft" style="width:8%;line-height:50px;font-size:14px;float:left;border:none; text-align:center;">
									<a style="cursor:pointer;color:black;" onclick="addSj()">添加</a>
								</div>
							</div>
						</div>
						
						<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;margin: 16px 48px;">
							<div style="height:30px;background:#e0e0e0;font-size:16px;">
								<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
									司机名称
								</div>
								<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
									电话
								</div>
								<div style="width:30%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;border-right:solid 1px #d0d0d0;">
									车牌号 
								</div>
								<div style="width:8%;line-height:30px;color:#807B7B;float:left;font-size:10px;text-align:center;">
									操作
								</div>
							</div>
							<div id="sjListContent"><font style="line-height:30px; font-size:14px; margin-left:20px;">没有数据!</font></div>
						</div>
					</div>			
					
					<!-- <div class = "one-fld" style="margin-left:32px;margin-right:32px;padding-top:32px;">
						<span style="display:inline-block; text-align:right;width:10%;margin-right:2%;">支付密码</span>
						<input type="pass" name="pass" style="width:30%;">
					</div> -->
					 ----------------------------------------------------------------------------------------------%> 
					<div align=center style="margin-top: 32px;margin-bottom: 24px;">
						<input type="submit" id="submit" class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认">
						<input type="button"  class="bluebutton" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none; margin-left:20px;" onclick = "window.history.back();" value="取消">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
    // 初始化函数
    $(function(){
    	
    	// validation检查	
    	$("#infoForm").validate({
    	    rules: {
    	    	grabMoney:{required: true, number:true}/* ,
    	    	pass:{required:true, remote: {
    	    		url: "${pageContext.request.contextPath}/front/sender/FinanceCtrl-checkWorkPass",
    	    		type: "Post",
    	    		data: {
    	    			      userId:"${wlUserId}"
    	    			}
    	    		}
    	    	} */
    	    },
    	    
    	    messages: {
    	    	grabMoney:{required:"请输入抢单金额！" , number:"请输入只数字形式！"}/*,
    	    	pass:{required:"请输入密码！" , remote:"错误密码！"}*/
    	    },
    	    errorPlacement: function (error, element) { 
    	    	if($(element).closest('.one-fld').children().filter("div.error-div").length < 1) 
    			$(element).closest('.one-fld').append("<div class='error-div'></div>");	
    		$(element).closest('.one-fld').children().filter("div.error-div").append(error);
    	    },
    	    submitHandler: function(form){
    	    	var grabMoney = $("#grabMoney").val();
    	    	if(parseInt(grabMoney) <= 0){
    	    		alert("提现抢单金额不正确！");
    	    		return;
    	    	}
    	    	/* ----------------暂时---------------------------------------------------------
    	    	var cfDate = $("#cfDate").val();
				var ddDate = $("#ddDate").val();
				var itemNum = 0;
				
				jQuery("input[name='sjmc']").each(function(i){
					itemNum++;
				});
				
				if(cfDate==""){
					alert("请输入发货时间!");
					$("#cfDate").focus();
					return;
				}
				
				if(ddDate==""){
					alert("请输入预计收货时间!");
					$("#ddDate").focus();
					return;
				}
				
				if(itemNum==0){
					alert("请输入司机信息!");
					$("#sjmc").focus();
					return;
				}
				
				var arr = new Array();
				
				jQuery("input[name='sjmc']").each(function(i){
					var cn = $(this).attr("cn");
					
					var obj = new Object();
					
					obj.sjmc = $("#sjmc_" + cn).val();
					obj.sjdh = $("#sjdh_" + cn).val();
					obj.cph  = $("#cph_" + cn).val();
					
					arr[arr.length] = obj;
				});
				
				var orderData = JSON.stringify(arr);
				$("#orderData").val(orderData);
    	    	-------------------------------------------------------------------------------*/
    	    	if(confirm("你要确定操作吗?")){
	    	    	$.post($(form).attr("action"),$(form).serialize(),function(data){
	    	    		alert(data.content);
	    	            if(data.state==1 ){
	    	            	window.location.href = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-sqResult";
	    	            }
	    	      	}, "json");
    	    	}
    	    }
    	});
    })						

	// 我有车的话添加按键点击事件
	var itemCn = 0;	
	function addSj(){
		
		var sjmc = $("#sjmc").val();
		var sjdh = $("#sjdh").val();
		var cph  = $("#cph").val();
		
		if(sjmc==""){
			alert("请输入司机名称!");
			$("#sjmc").focus();
			return;
		}
		
		if(sjdh==""){
			alert("请输入电话!");
			$("#sjdh").focus();
			return;
		}else if(!checkPhone(sjdh)){
			alert("不正确电话!");
			$("#sjdh").focus();
			return;
		}
		
		if(cph==""){
			alert("请输入车牌号!");
			$("#cph").focus();
			return;
		}
		
		var htmlStr = "<div id='sjListContent_" + itemCn + "' style='border-bottom: solid 1px #E0E0E0; overflow:auto;'>";
		htmlStr = htmlStr + "<div class='graybox' style='width:30%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>";
		htmlStr = htmlStr + sjmc + "<input type='hidden' cn='" + itemCn + "' id='sjmc_" + itemCn + "' name='sjmc' value='" + sjmc + "' style='width:130px;' /></div>";
		htmlStr = htmlStr + "<div class='grayboxwithoutleft' style='width:30%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>";
		htmlStr = htmlStr + sjdh + "<input type='hidden' cn='" + itemCn + "' id='sjdh_" + itemCn + "' name='sjdh' value='" + sjdh + "' style='width:130px;' /></div>";
		htmlStr = htmlStr + "<div class='grayboxwithoutleft' style='width:30%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;border-right: solid 1px #d0d0d0;'>";
		htmlStr = htmlStr + cph + "<input type='hidden'  cn='" + itemCn + "' id='cph_" + itemCn + "'  name='cph'  value='" + cph + "'  style='width:130px;' /></div>";
		htmlStr = htmlStr + "<div class='grayboxwithoutleft' style='width:8%;line-height:50px;font-size:14px;float:left;border:none;text-align:center;'>";
		htmlStr = htmlStr + "<a style='cursor:pointer;color:black;' onclick='delSj(" + itemCn + ")'>删除</a></div></div>";
		
		var itemNum = 0;
		
		jQuery("input[name='sjmc']").each(function(i){
			itemNum++;
		});
		
		if(itemNum==0){
			$("#sjListContent").html(htmlStr);	
		} else {
			$("#sjListContent").append(htmlStr);	
		}
		
		itemCn++;
		
		$("#sjmc").val("");
		$("#sjdh").val("");
		$("#cph").val("");
		
	}
	// 我有车的话删除按键点击事件
	function delSj(cn){
		$("#sjListContent_" + cn).remove();
		var itemNum = 0;
		
		jQuery("input[name='sjmc']").each(function(i){
			itemNum++;
		});
		
		if(itemNum==0){
			$("#sjListContent").html("<font style='line-height:30px; font-size:14px;margin-left:20px;'>没有数据!</font>");	
		}
	}	
	
	$("#back").click( function(){
		window.history.back();
	});
	
</script>
</html>