<%@ include file="../header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<script src="${pageContext.request.contextPath}/statics/libs/ajaxupload.js"></script>
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
	.error-div{display:inline; margin-left:10px;}
	.zizhixinxi{
		border: dashed 2px #c0c0c0;
		padding: 8px;
		margin-right: 16px;
	}
	.yingyetxt{
		position: absolute;
	    bottom: -50px;
	    left: -4px;
	    width: 120px!important;
	 }
	 .fileManage{
		position:absolute;
		width: 140px!important;
	    float: left;
	    height: 140px!important;
	    position: absolute;
	    top: 0px;
	    left: 0px;
	    cursor:pointer;
	    opacity: 0;
	    z-index: 9999;
	}
	.companyImg{
		margin-left:50px;margin-top:10px;width:100px;height:155px;cursor:pointer;padding-left: 24px;float:left;
	}
</style>	
<div style="margin-left: 0px; margin-top:20px;">
	<div id="ddfkDiv" align="center">
		<div style="width:1000px;">
			<div style="border-bottom: solid 1px #e0e0e0; width: 1000px;padding-bottom:12px;margin-top:20px;margin-bottom: 24px;">

				<div style="text-align:center;font-size:24px;">订单预付款</div>
			</div>
		</div>
		<%--<form id="ddfkForm" action="${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-setFukuan" method="post" enctype="multipart/form-data">--%>
		<form id="ddfkForm" action="${pageContext.request.contextPath}/customer/payOrder" method="post" enctype="multipart/form-data">
				<input type="hidden" name="orderId" value="${orderInfo.orderId}" />
			<input type="hidden" name="saleMoney" value="${orderInfo.confirmPrice*orderInfo.confirmNumber}" />
			<div class="formDd"  style="width:1000px;">
	    		<div class="headDd">订单金额:${orderInfo.confirmPrice*orderInfo.confirmNumber}&nbsp;&nbsp;元</div>
				<div>账户余额:${orderInfo.userMoney}&nbsp;&nbsp;元</div>
				<div>授信余额:${orderInfo.userCreditMoney}&nbsp;&nbsp;元</div>
				<div style = "float : left; width:65%;">
					<div class="rowDd" style = "width:50%; float:left;">
						<div class="labeldd" align="right">公司名称:</div>
						<div class="contentdd">${orderInfo.sellerUserName}</div>
					</div>
					<div class="rowDd" style = "width:50%; float:left; clear:none;">
						<div class="labeldd" align="right">订单编号:</div>
						<div class="contentdd">${orderInfo.orderNo}</div>
					</div>
					<div class="rowDd">
						<div class="labeldd" align="right">订单内容:</div>
						<div class="contentdd">${orderInfo.prodName} &nbsp;&nbsp;${orderInfo.saleNum}吨</div>
					</div>
				</div>
				<%--<div style="float:left;width: 34%;padding-top: 35px; " >
					<span style="margin-left:30%; font-size:16px;">应还金额:</span>
					<span style="font-size:20px;color:red;">&nbsp;&nbsp;&nbsp;&nbsp;${orderInfo.saleMoney}&nbsp;&nbsp;元</span>
				</div>--%>
				<div style = "clear:both;"></div>
				<div class="headDd">付款方式</div>
					<div class="rowDd">
						<div class="contentdd" style="margin-left: 25px;width:900px;">
							<div class="contentdd" id="contentdd_2"  style="width:100%;border:solid 2px #c0c0c0;" ><label><input type = "radio" name = "zfMethod" value = "2" onclick = "changeZfMethod(2)" style = "margin-left:15px;margin-top:0px;" checked="checked" />&nbsp;&nbsp;余额支付</label></div>
							<div class="contentdd" id="contentdd_3"  style="width:100%;"><label><input type = "radio" name = "zfMethod" value = "3" onclick = "changeZfMethod(3)" style = "margin-left:15px;margin-top:0px;" />&nbsp;&nbsp;授信余额付款</label></div>
							<div class="contentdd" id="contentdd_4"  style="width:100%;">
								<label>
									<input type = "radio" name = "zfMethod" value = "4" onclick = "changeZfMethod(4)" style = "margin-left:15px;margin-top:0px;" />&nbsp;&nbsp;组合付款
								</label>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;授信占比&nbsp;&nbsp;<select style="padding-top: 3px;" name="rate" id="zhan_rate">
									<option value = "10">10%</option>
									<option value = "30">30%</option>
									<option value = "50" selected = "selected">50%</option>
									<option value = "80">80%</option>
								</select>
							</div>
							<div class="contentdd" id="contentdd_5"  style="width:100%;">
								<label>
									<input type = "radio" name = "zfMethod" value = "5" onclick = "changeZfMethod(5)" style = "margin-left:15px;margin-top:0px;" />&nbsp;&nbsp;线下付款
								</label>
								<%--<div id="zhifu_certificate" style="float:right;display:none;">
									<input type="hidden" id="certificateImg" name="certificateImg" />
									<a id="certificateImgA" class="fancybox" data-fancybox-group="zfxy" style="display:none;">
										<img id="certificateImgImg" src='' style="width:40px; height:40px; border:none; margin-right:20px;" />
									</a>
									<input id="certificateImgFile" name="file" type="file" style="cursor:pointer;" />
								</div>--%>
								<div id="zhifu_certificate" style="float:right;display: none">
									<input type="hidden" id="certificateImg" name="certificateImg" />
									<a id="certificateImgA" class="fancybox" data-fancybox-group="zfxy">
										<img id="certificateImgImg" src='' style="width:40px; height:40px; border:none; margin-right:20px;" />
									</a>
									<a  id="upload1"  class="btn btn-primary">上传文件</a>
								</div>
							</div>
							<%--<div class="contentdd" id="contentdd_1"  style="width:100%;"><label><input type = "radio" name = "zfMethod" value = "1" onclick = "changeZfMethod(4)" style = "margin-left:15px;margin-top:0px;" />&nbsp;&nbsp;银行转账</label></div>--%>
						</div>
					</div>
					<div class="rowDd" id="zhifu_pass">
						 <div class="labeldd" align="right" style="margin-left: 10px;">支付密码 :</div>
						 <div class="contentdd">
						    <input type = "password" name = "pass" style="margin-bottom: 0px;" id = "pass" placeholder = "请输入支付密码"/>
						 </div>
					</div>
					<div class="rowDd" id="zhifu_card" style="display:none;">
						 <div class="labeldd" align="right" style="margin-left: 10px;"></div>
						 <div class="companyImg">
							<div class="zizhixinxi" id="bank1" style="width:130px;height:130px;float: left;position:relative; margin-top: 10px;border:solid 2px #c0c0c0;" onclick="onSelBanc(1);">
								<div align=center style="height:70px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/zizhi_icon.png') no-repeat center;"></div>
								<div class="zizhititle" style="line-height:20px;text-align:center;font-weight:bold;font-size:16px;">中信银行</div>
								<img id="yingye_imgpath4" class="yingyeimg" style="" src="" />
							</div>
						</div>
						<%--
						<div class="companyImg">
							<div class="zizhixinxi"  id="bank2" style="width:130px;height:130px;float: left;position:relative; margin-top: 10px;" onclick="onSelBanc(2);">
								<div align=center style="height:70px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/zizhi_icon.png') no-repeat center;"></div>
								<div class="zizhititle" style="line-height:20px;text-align:center;font-weight:bold;font-size:16px;">线下支付</div>
								<img id="yingye_imgpath4" class="yingyeimg" style="" src="" />
							</div>
						</div>
						--%>
					</div>
				<div class="rowDd">
					<input type="button" class="bluebutton" style="margin-left:45px; margin-top:15px; padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="返回" <%--onclick="goBack();" --%> onclick="javascript:window.history.go(-1);"/>
					<input type="submit" id="submit" class="bluebutton" style="margin-left:45px; margin-top:15px; padding: 8px 16px; font-size:20px; border-radius: 6px; border:none;" value="确认支付" />
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        new AjaxUpload('#upload1', {
            action: '${pageContext.request.contextPath}/uploadFile',
            name: 'file',
            autoSubmit:true,
            responseType:"json",
            onSubmit:function(file, extension){
                if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                    alert('只支持jpg、png、gif格式的图片！');
                    return false;
                }
            },
            onComplete : function(file, data){
                if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
				$("#certificateImgImg").attr("src",data.path);
                $("#certificateImg").val(data.path);
                return;
            }
        });
    });

	var bankId = 0;
	
	/*$('.fancybox').fancybox();*/

	$(".backBtn").click(function(){
		goBack();
	});
	
	// 支付方式变径函数
	function changeZfMethod(index){
		var zfMethod = $(':radio[name="zfMethod"]:checked').val();
		$(".contentdd").css("border","none");
		$("#contentdd_"+zfMethod).css("border","2px solid #c0c0c0");
		
		if(zfMethod == '1'){
			$("#zhifu_pass").hide();
			$("#zhifu_card").show();
			$("#zhifu_certificate").hide();
		}else{
			$("#zhifu_pass").show();
			$("#zhifu_card").hide();
			$("#zhifu_certificate").hide();

			if(zfMethod == '5') $("#zhifu_certificate").show();
		}
	}

	function onSelBanc(type){
		$(".zizhixinxi").css("border","dashed 2px #c0c0c0");
		$("#bank"+type).css("border","solid 2px #c0c0c0");
		bankId = parseInt(type) -1 ;
	}
	
	$("#certificateImgFile").change(function(){
		uploadImage();
	});
	
	function uploadImage(){
 		var options = {
            url:  "${pageContext.request.contextPath}/uploadFile",
            type:"POST",
            dataType:"json",
            data:{},
            success : function(data) {
          	  if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
       		  $("#certificateImg").val(data.path);
       		  $("#certificateImgA").attr("href","${pageContext.request.contextPath}/" + data.path);
       		  $("#certificateImgImg").attr("src","${pageContext.request.contextPath}/" + data.path);
       		  $("#certificateImgA").show();
          	  return;
          	  //-----------
            },
            error : function(data) {
            	alert(data);
            }
 		}
 		$("#ddfkForm").ajaxSubmit(options);
 		
 	}
	
	// validate检查
	$("#ddfkForm").validate({
	    rules: {
	    },
	    messages: {
	    	
	    },
	    errorPlacement: function (error, element) {
	    	if($(element).closest('div.rowDd').children().filter("div.error-div").length < 1) 
				$(element).closest('div.rowDd').append("<div class='error-div'></div>");	
			$(element).closest('div.rowDd').children().filter("div.error-div").append(error);	
		},
	    submitHandler: function(form){
	    	var zfMethod = $(':radio[name="zfMethod"]:checked').val();
	    	if(zfMethod != "1"){
	    		var pass = $("input[name='pass']").val();
	    		if(pass == undefined || pass == ""){
	    			alert("请输入支付密码");
	    			return;
	    		}
	    		var url = "${pageContext.request.contextPath}/customer/checkCzPassword";
	    		$.post(url,{"pass":pass},function(data){
		            if(data=="false"){
		            	alert("支付密码错误！");
		            	return;
		            }
			    	if(confirm("你要确定操作吗？")){
				    	$.post($("#ddfkForm").attr("action"),$("#ddfkForm").serialize(),function(data){
				    		data = $.parseJSON(data);
				            if(data.status=="1"){
				            	alert("付款成功!");
				            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-prodOrderList";
				            } else {
				            	alert(data.content);
				            }
				      	})
				      	.error(function(data){
				      		alert("操作失败！")
				      	});
			    	}
		      	});
	    	}else {
	    		if (bankId == "0")
	    			zfMethod = "1";
	    		else 
	    			zfMethod = "5";
	    		if(zfMethod == "1")
	    		    alert("功能待开发");
	    		    return;
	    		$("input[name='zfMethod']").val(zfMethod);
	    		if(confirm("你要确定操作吗？")){
				    	$.post($("#ddfkForm").attr("action"),$("#ddfkForm").serialize(),function(data){
				    		data = $.parseJSON(data);
				            if(data.status=="1"){
				                if(zfMethod = "5"){
				                    alert("提交成功");
								}else{
                                    alert("付款成功!");
								}
				            	window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerBackOrderCtrl-prodOrderList";
				            } else {
				            	alert(data.content);
				            }
				      	})
				      	.error(function(data){
				      		alert("操作失败！")
				      	});
			    }
	    	}
	    }
    });
</script>

<%@ include file="../footer.jsp"%>