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
	<div id="wlxxDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">

				<div style="text-align:center;font-size:24px;">发货信息</div>
			</div>
		</div>
		<div class="formDd">
			<div class="headDd">发货信息</div>
			<div class="rowDd">
				<div class="labeldd" align="right">发货时间:</div>
				<div class="contentdd"><input id="cfDate" style="height:18px;width:150px;" value="" class="Wdate" type="text" onfocus="var ddDate=$dp.$('ddDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){ddDate.focus();},maxDate:'#F{$dp.$D(\'ddDate\')}'})"></div>
				<div class="labeldd" align="right">预计收货时间:</div>
				<div class="contentdd"><input id="ddDate" style="height:18px;width:150px;" value="" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'cfDate\')}'})"></div>
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
				<div id="sjListContent"><font style="line-height:30px; font-size:14px;">没有数据!</font></div>
			</div>
			
			<div style="margin:10px 0px 0px 46px;">
				<input id=""  name="pubbtn"  type="button" value="返回" class="bluebutton" style="font-size:20px;border-radius:6px;" onclick="goBack();">
				<input id="pubbtn"  name="pubbtn"  type="button" value="确定" class="bluebutton" style="font-size:20px;border-radius:6px;">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(".backBtn").click(function(){
		goBack();
	});
	
	$("#pubbtn").click(function(){
		
		var orderId = '${orderId}';
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
		
		if(confirm("你要确定操作吗?")){
			$.post("${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-setFahuo",{orderId:orderId,cfDate:cfDate,ddDate:ddDate,orderData:orderData},function(data){
				data = $.parseJSON(data);
	    		alert(data.content);
	            if(data.status=="1"){
	            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-prodOrderList";
	            }
	      	})
	      	.error(function(data){
	      	});
		}
		
	});
	
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
	
	function delSj(cn){
		$("#sjListContent_" + cn).remove();
		var itemNum = 0;
		
		jQuery("input[name='sjmc']").each(function(i){
			itemNum++;
		});
		
		if(itemNum==0){
			$("#sjListContent").html("<font style='line-height:30px; font-size:14px;'>没有数据!</font>");	
		}
	}
</script>

<%@ include file="../footer.jsp"%>