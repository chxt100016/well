<%@ include file="header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">


	<style>
		.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:bold;font-size:24px;}
		.ware_button{float:right;color: white;background: red;padding: 6px;border-bottom-left-radius: 8px;border-top-left-radius: 8px;border-top-right-radius: 8px;border-bottom-right-radius: 8px;width: 61px;text-align: center;cursor:hand;font-size:16px;}
		.ware_maker_label{color: red;padding: 6px;float: left;}
		.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
		span.selectedtab{ background:#0077dd;color:white;}
		
		.row-fld{margin-left:48px;margin-bottom: 24px; font-size:14px;}
		.error-div{margin-left:56px;}
	</style>
</head>
<body style="margin:0; padding:0; background-color:#f5f5f5;">
	<div class="main-wrapper" style="margin-left: 0px;margin-top:20px;">
		<div align=center style="padding-top:12px;">
			<div align=left style="width:1000px;">
				<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">
					
					<div style="text-align:center;font-size:20px; font-weight:600;">下&nbsp;&nbsp;单</div>
				</div>
			</div>

			<%--<form id = "infoForm" action="<c:url value="/front/customer/CustomerHomeCtrl-createOrder"/>" method="post">--%>
				<form id = "infoForm" action="<c:url value="/customer/order"/>" method="post">
				<input type = "hidden" name = "toRegionId"  id = "toRegionId" />
			    <input type = "hidden" name = "prodId"  value = "${spInfo.prodId}" />
			    <input type = "hidden" name = "orderData" id = "orderData"  />
				<div align= left style="width:1000px; background: white;box-shadow: 2px 2px 8px #808080;padding-bottom:16px;">
					<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 24px; padding-bottom:12px;border-bottom:solid 2px #d0d0d0;font-size:17px;">订单信息</div>
					<div class = "row-fld">产品名称: &nbsp;&nbsp;${spInfo.prodName}</div>
					<div class = "row-fld">
						<span style="margin-right: 64px;">商品类型: &nbsp;&nbsp;
							<c:if test = "${spInfo.prodType == '0'}">
								气体
							</c:if>
							<c:if test = "${spInfo.prodType == '1'}">
								燃油
							</c:if>
						</span>
						<span style="margin-right: 64px;">联系人: &nbsp;&nbsp;${spInfo.prodLxr}</span>
						<span style="margin-right: 64px;"> 联系电话: &nbsp;&nbsp;${spInfo.prodLxrPhone}</span>
					</div>
					<div class = "row-fld">
						<span style="margin-right: 64px;">供应量:&nbsp;&nbsp; <input name="saleNum" id="saleNum" placeholder="填写整车吨位的倍数" style="padding-left:3px;width:180px;margin-top:-6px;margin-right: 12px;" onkeyup="return validateNumber(this,value,0)">吨</span>
						<span style="margin-right: 64px;">单价:&nbsp;&nbsp; <input id="danjia" name = "danjia"  value = "${spInfo.prodMoney}" placeholder="填写单价" style="padding-left:3px;width:120px;margin-top:-6px;margin-right: 12px;background-color:white;" readonly = "readonly" onkeyup="return validateNumber(this,value,0)">元</span>
						<span style="margin-right: 64px;">总价:&nbsp;&nbsp; <input name="saleMoney" id="saleMoney"  style="width:120px;margin-top:-6px;margin-right: 12px; background-color:white;" readonly = "readonly">元</span>
					</div>
					<div class = "row-fld">
							<span>提货地址: </span>
							<span style="color:red;font-weight:bold;padding-left;10px;">${spInfo.fromRegionName} &nbsp;&nbsp;&nbsp;${spInfo.prodRegionAddr}</span>
					</div>
					<div class = "row-fld">
							<span style = "margin-right:20px;">物流方式: </span>
							<span style = "margin-right:20px;"><input type = "radio" name = "isSelfCar"  value = "0" checked = "checked"  style = "margin-right:10px;" onClick = "checkSelfCar(0);">&nbsp;&nbsp;自提</input></span>
							<span style = "margin-right:20px;"><input type = "radio" name = "isSelfCar"  value = "1" onClick = "checkSelfCar(1);">&nbsp;&nbsp;配送</input></span>
					</div>
					<div style="margin-left:48px;margin-right:48px;padding-top:32px; margin-bottom: 24px; padding-bottom:12px;border-bottom:solid 2px #d0d0d0;font-size:17px;">物流信息</div>
					<div id="wlxxDiv">
						<div class="row-fld">
							<span style = "margin-right: 64px;">发货时间:</span>
							<input id="cfDate" name = "cfDate" style="height:25px;width:185px;" value="" class="Wdate" type="text" onfocus="var ddDate=$dp.$('ddDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){ddDate.focus();},maxDate:'#F{$dp.$D(\'ddDate\')}'})">
							<span style = "margin-right: 64px; margin-left:64px;">预计收货时间:</span>
							<input id="ddDate"  name="ddDate" style="height:25px;width:185px;" value="" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'cfDate\')}'})">
						</div>
						<div style="border:solid 1px #d0d0d0;font-size:24px;margin:16px 48px;overflow:auto;">
							<div style="border-bottom: solid 1px#E0E0E0; overflow:auto;">
								<div class="graybox" 			style="width:30%;line-height:50px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 司机名称 : <input type="text" id="sjmc" style="width:130px; line-height:normal" />
								</div>
								<div class="grayboxwithoutleft" style="width:30%;line-height:50px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 电话 : <input type="text" id="sjdh" style="width:130px;line-height:normal" />
								</div>
								<div class="grayboxwithoutleft" style="width:30%;line-height:50px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
									&nbsp; 车牌号 : <input type="text" id="cph" style="width:130px;line-height:normal" />
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
					</div>	
					
					<div id="wuliudingdandiv" style="display:none;">
						<div class = "row-fld">
							<span style="margin-right: 64px;">联系人: <input name="vehicleLxr" id="vehicleLxr" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
							<span style="margin-right: 64px;">联系电话: <input name="vehicleLxrPhone" id="vehicleLxrPhone" style="width:250px;margin-top:-6px;margin-right: 12px;"></span>
						</div>
						<div class = "row-fld">
							<span>配送地址: </span>
								<span>
									<select name="prodRegionId_0" style="width:160px;padding: 3px 3px;">
										<option value=''>-- 请选择省 --</option>
										<c:forEach var="item" items="${shengRegionList}" varStatus="status">
											<option value="${item.regionId}">${item.regionName}</option>
										</c:forEach>
								</select>
								<select name="prodRegionId_1" style="width:160px;padding: 3px 3px;display:none;">
									<option value=''>-- 请选择市 --</option>
								</select>
								<select name="prodRegionId_2" style="width:160px;padding: 3px 3px;display:none;">
									<option value=''>-- 请选择区 --</option>
								</select>
							</span>
						</div>
						<div class = "row-fld">
							<span style="margin-left: 61px;"><input name="toRegionAddr" id="toRegionAddr" style="width:250px;margin-top:-6px;margin-right: 12px; padding-left:3px;" placeholder="填写详细地址"></span>
						</div>
						
					</div>
					
					<div align=center style="margin-bottom: 24px;">
						<input type="button" id="back" class="green-button" style=" padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="返回">
						<input type="submit" id="submit" class="blue-button" style="padding-left: 16px;padding-right: 16px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="确认下单">
					</div>
				</div>
			</form>
		</div>

	</div>
<%--别动这个是弹出的警告modal框--%>
<div class="modal fade warming_modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
	  <div class="modal-body">
          ...
        </div>
    </div>
  </div>
</div>
<%--modal框end	--%>
</body>
<script type="text/javascript">
	// 初始化函数
	$(function(){
		// 检查模块
	
		$("#infoForm").validate({
		    rules: {
		    	saleNum: {required:true},
		    	danjia: {required:true},
		    	saleMoney: {required:true}
		    },
		    
		    messages: {
		    	saleNum:"请输入供应量！",
		    	danjia:"请输入单价！",
		    	saleMoney:"请输入总价！"
		    },
		    errorPlacement: function (error, element) { 
		    	if($(element).closest('div.row-fld').children().filter("div.error-div").length < 1) 
				$(element).closest('div.row-fld').append("<div class='error-div'></div>");	
			$(element).closest('div.row-fld').children().filter("div.error-div").append(error);
		    },
		    submitHandler: function(form){
		    	var isSelfCar = $(':radio[name="isSelfCar"]:checked').val();
		    	var toRegionId = "";
		    	if(isSelfCar == '1'){
		    	  if($("#vehicleLxr").val() == undefined || $("#vehicleLxr").val() == ""){
		    	  	// alert("请输入联系人!");
						showalert("请输入联系人!");
		    	  	return;
		    	  }
		    	  
		    	  var vehicleLxrPhone = $("#vehicleLxrPhone").val();
		    	  if(vehicleLxrPhone == undefined || vehicleLxrPhone == ""){
						  showalert("请输入联系电话!");
			    	  	return;
		    	  }else if(!checkPhone(vehicleLxrPhone)){
		    	  		// alert("不正确联系电话!");
						  showalert("不正确的联系电话!");
		    	  		return;
		    	  }
		    	  
		    	  
		    	  if($("select[name='prodRegionId_0']").val() != undefined && $("select[name='prodRegionId_0']").val() != '')
		    	  	  toRegionId = $("select[name='prodRegionId_0']").val();
		    	  if($("select[name='prodRegionId_1']").val() != undefined && $("select[name='prodRegionId_1']").val() != '')
		    	  	  toRegionId = $("select[name='prodRegionId_1']").val();
		    	  if($("select[name='prodRegionId_1']").val() != undefined && $("select[name='prodRegionId_2']").val() != '')
		    	  	  toRegionId = $("select[name='prodRegionId_2']").val();
		    	  if(toRegionId == ""){
			    	  	// alert("请选择配送地址!");
						  showalert("请选择配送地址!");
			    	  	return;
		    	  }
		    	  
		    	  if($("#toRegionAddr").val() == undefined || $("#toRegionAddr").val() == ""){
		    	  	    // alert("请输入详细地址!");
                         showalert("请输入详细地址!");
		    	  	    return;
		    	  }
		    	}else if(isSelfCar == '0'){ // 我有车的话
		    		var cfDate = $("#cfDate").val();
					var ddDate = $("#ddDate").val();
					
					var itemNum = 0;
					
					jQuery("input[name='sjmc']").each(function(i){
						itemNum++;
					});
					
					if(cfDate==""){
						// alert("请输入发货时间!");
						showalert("请输入发货时间!");
						$("#cfDate").focus();
						return;
					}
					
					if(ddDate==""){
						// alert("请输入预计收货时间!");
						showalert("请输入预计收货时间");
						$("#ddDate").focus();
						return;
					}
					
					if(itemNum==0){
						// alert("请输入司机信息!");
						showalert("请输入司机信息!");
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
		    	}
		    	
		    	if(confirm("你要确定操作吗?")){
			    	$.post($(form).attr("action"),$(form).serialize(),function(data){
			    		alert(data.content);
						// showalert(data.content);
			            if(data.state==1 ){
			            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerHomeCtrl-orderSuccess";
			            }
			      	}, "json");
		    	}
		    }	
		})	
	});
	// 单价和数量变化函数	
	function validateNumber(e, pnumber, type){
		var len = 0;
		if (!/^\d+$/.test(pnumber)){
			if(isNaN(pnumber) == '0') return;
			len = pnumber.length;
			$(e).val(pnumber.substring(0,len-1));
		}
		
		if(type==0){
			var saleNum = $("#saleNum").val();
			var saleDj  = $("#danjia").val();
			
			if(saleNum=="" || saleDj==""){
				$("#saleMoney").val(0);
			} else {
				$("#saleMoney").val(saleNum*saleDj);	
			}
		}
		
		return false;
	}
	// 选择物流方式	
	function checkSelfCar(type){
		if(type == '0'){
			$("#wuliudingdandiv").hide();
			$("#wlxxDiv").show();
		}else{
			$("#wuliudingdandiv").show();			
			$("#wlxxDiv").hide();
		}
	}
	
	// 选择地区函数
	function getRegionList(pid,level){
	if(pid!=null && pid!=""){
 		$.post("${pageContext.request.contextPath}/front/seller/SellerOrderController-getRegionList",{pid:pid},function(data){
 			data = $.parseJSON(data);
            var regionList = data.regionList;
            
            $('#prodRegionId_' + level).empty();
            var str = "";
            
            if(level==1) str = "<option value=''>-- 请选择市 --</option>";
            else 		 str = "<option value=''>-- 请选择区 --</option>";
            
 			for(var i=0 ; i<regionList.length ; i++){
 				str = str + "<option value='" + regionList[i].regionId + "'>" + regionList[i].regionName + "</option>";
 			}
 			
 			$("select[name='prodRegionId_" + level + "']").html(str);
 			if(regionList.length != 0){
 				$("select[name='prodRegionId_" + level + "']").show();
 				$("#toRegionId").val("");
 			}else{
 				$("#toRegionId").val(pid);
 				$("select[name='prodRegionId_" + level + "']").hide();
 			}
      	})
      	.error(function(data){
      	});
		}
	}
	// 省级目录变化函数
	$("select[name='prodRegionId_0']").change(function(){
		getRegionList($(this).val(),1);
	});
	
	// 市级目录变化函数
	$("select[name='prodRegionId_1']").change(function(){
		getRegionList($(this).val(),2);
	})
		
	// 区级目录变化函数
	$("select[name='prodRegionId_2']").change(function(){
		$("#toRegionId").val($(this).val());
	})
	// 我有车的话添加按键点击事件
	var itemCn = 0;	
	function addSj(){
		
		var sjmc = $("#sjmc").val();
		var sjdh = $("#sjdh").val();
		var cph  = $("#cph").val();
		
		if(sjmc==""){
			// alert("请输入司机名称!");
			showalert("请输入司机名称!");
			$("#sjmc").focus();
			return;
		}
		
		if(sjdh==""){
			// alert("请输入电话!");
			showalert("请输入电话!");
			$("#sjdh").focus();
			return;
		}else if(!checkPhone(sjdh)){
			// alert("不正确电话!");
	       showalert("不正确电话!");
			$("#sjdh").focus();
			return;
		}
		
		if(cph==""){
			showalert("请输入车牌号!");
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
			$("#sjListContent").html("<font style='line-height:30px; font-size:14px;'>没有数据!</font>");	
		}
	}	
	
	function showalert(xx){
		$('.modal-body').html(xx);
	   $('.warming_modal').modal('show');
	};
		
		
	$("#youche").click( function(){
		$("#wuliudingdandiv").hide();
		$("#youchediv").show();
		if( $("#youche").hasClass("selectedtab")==false) $("#youche").addClass("selectedtab");
		$("#wuliudingdan").removeClass("selectedtab");
	});
	$("#wuliudingdan").click( function(){
		$("#youchediv").hide();
		$("#wuliudingdandiv").show();
		if( $("#wuliudingdan").hasClass("selectedtab")==false) $("#wuliudingdan").addClass("selectedtab");
		$("#youche").removeClass("selectedtab");
	});
	
	$("#back").click( function(){
		history.back();
	});
	
</script>
</html>
