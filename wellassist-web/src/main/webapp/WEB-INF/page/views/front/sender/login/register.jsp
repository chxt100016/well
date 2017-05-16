<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>

<style>
	div.error{
		font-size: 14px;
		margin-bottom: 6px;
		float:right;
		margin-right:190px;
		line-height: 30px;
	}
	.caption{
		padding-top:10px;
	}
.yingyeimg{
	position:absolute;position: absolute;display:none;left: 0px;top: 0px;width: 136px;height: 137px;background: white; z-index: 999;
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
.sub-accordion-nav>li.active a {
	background: white;
	color: #DA3610;
}

.sub-accordion-nav>li a {
	cursor: pointer;
}

.title_account {
	margin-top: 16px;
	color: white;
	float: left;
	margin-left: 200px;
	font-size: 32px;
}

td.kehu_item {
	width: 240px;
	height: 240px;
	border-style: solid;
	border-width: 1px;
}

div.kehu_item {
	font-size: 16px;
	font-weight: bold;
}

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
.box-in-level2{color:darkgray;font-weight:500;}
.companyImg{
	position:absolute;width:100px;height:100px;top:-10px;right:190px;cursor:pointer;
}
</style>


	<div class="main-wrapper" style="margin-left: 0px;">

		<!-- navigation bar -->
		<div class="abovenavbar">
			<span style="margin-left: 5%;">WellAssist供应链系统(物流端)</span>
			<span style="float: right; margin-right: 5%;"><a href="${pageContext.request.contextPath}/">返回首页</a></span>
		</div>
		
			
		<!-- form area -->
		<div align=center style="padding-bottom:1px;">
<%-- 			<form method="post" action="<c:url value="/front/customer/register_result"/>"> --%>
			<form method="post" action="javascript:myfunc();" id="sender_register" method="post">
				<!-- form page1 -->
				<div id="kehuzhuce_page1">
					<!-- area of progress status -->
					<div style="width: 100%; margin-top: 16px; margin-bottom: 72px;" align = left>
						<span style="float: left; margin-left: 10%;">
							<img src="${pageContext.request.contextPath}/resources/wella/front/images/mainmark.png" style="margin-left: 20%;">
						</span>
						<div style="width: 100%;">
							<div class="label-heading3" style="padding-left: 30%; padding-bottom: 16px;">账号注册</div>
							<div style="padding-left: 30%; width: 40%;">
								<!-- top -->
								<span style="float: left;">
									<div align=center><img src="${pageContext.request.contextPath}/resources/wella/front/images/round1_1.png"></div>
									<div>创建账号</div>
								</span>
								<span style="float: left; width: 30%;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/breakline_0.png" style="height: 30px; width: 100%;">
									</div>
								</span>
								<span style="float: left;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/round2_0.png">
									</div>
									<div>资料完善</div>
								</span>
								<span style="float: left; width: 30%;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/breakline_0.png" style="height: 30px; width: 100%;">
									</div>
								</span>
								<span style="float: left;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/roundok.png">
									</div>
									<div>注册车成功</div>
								</span>
							</div>
						</div>
					</div>
	
					<div align=left style="margin-top: 70px; margin-bottom: 48px; width: 800px; height: 80%; background: white; box-shadow: 0 -1px 6px rgba(0, 0, 0, 0.5);">
						<div class="box-in-level1 titlewithline" style="margin-left: 7%; padding-top:42px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:46px;">企业信息</div>
						<table style="margin-left: 15%; margin-right: 15%; width: 100%;position:relative;">
							<tr>
								<td class="caption" style="width: 15%;">
									<span class="box-in-level2">企业名称</span>
									<div class="companyImg">
										<div class="zizhixinxi" style="width:111px;height:120px;float: left;position:relative;">
											<div align=center style="height:70px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/zizhi_icon.png') no-repeat center;"></div>
											<div class="zizhititle">企业图片</div>
											<div>格式: JPG, PNG, GIF</div>
											<div>大小: 小于 5M</div>
											<input type="hidden" name="yingye_img4" />
											<input type="file" id="yingye_img4" name="yingye_img4_src" class="fileManage"  />
											<img id="yingye_imgpath4" class="yingyeimg" style="" src="" />
										</div>
									</div>
								</td>
								<td style="width: 85%;">
									<input type="text" name="companyname" style="width: 50%;" placeholder="请输入企业名称">
								</td>
							</tr>
							<tr>
								<td class="caption"><span class="box-in-level2">企业注册号</span></td>
								<td><input type="text" name="companyaccount"
									style="width: 50%;" placeholder="请输入企业注册号"></td>
							</tr>
						</table>
						<div class="box-in-level1 titlewithline" style="margin-left: 7%; margin-top: 40px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:6px;">账号信息</div>
						<table style="margin-left: 15%; margin-right: 15%; width: 100%;">
							<tr>
								<td class="caption"><span class="box-in-level2">电子邮箱</span></td>
								<td>
									<input type="text" name="contactemail" style="width: 50%;" placeholder="请输入电子邮箱">
								</td>
							</tr>
							<tr>
								<td class="caption"><span class="box-in-level2">手机号码</span></td>
								<td>
									<input type="text" name="contactphone" style="width: 50%;" placeholder="请输入手机号码">
								</td>
							</tr>
							<tr>
								<td></td>
								<td><input type="checkbox" name="checkvalue" id="agreeBtn">&nbsp;&nbsp;&nbsp;我同意为助供应链服务协议</td>
							</tr>
							<tr>
								<td></td>
<!-- 								<td><input id="nextstep" class="blue-button" type="button" value="下一步"></td> -->
								<td><input id="nextstep" class="blue-button" type="submit" value="下一步"></td>
							</tr>
						</table>
					</div>
				</div>
				<!-- form page2 -->

				<div id="kehuzhuce_page2" style="display:none;">
					<!-- area of progress status -->
					<div style="width: 100%; margin-top: 16px; margin-bottom: 72px;" align = left>
						<span style="float: left; margin-left: 10%;">
							<img src="${pageContext.request.contextPath}/resources/wella/front/images/mainmark.png" style="margin-left: 20%;">
						</span>
						<div style="width: 100%;">
							<div class="label-heading3" style="padding-left: 30%; padding-bottom: 16px;">账号注册</div>
							<div style="padding-left: 30%; width: 40%;">
								<!-- top -->
								<span style="float: left;">
									<div align=center><img src="${pageContext.request.contextPath}/resources/wella/front/images/round1_1.png"></div>
									<div>创建账号</div>
								</span>
								<span style="float: left; width: 30%;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/breakline_1.png" style="height: 30px; width: 100%;">
									</div>
								</span>
								<span style="float: left;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/round2_1.png">
									</div>
									<div>资料完善</div>
								</span>
								<span style="float: left; width: 30%;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/breakline_0.png" style="height: 30px; width: 100%;">
									</div>
								</span>
								<span style="float: left;">
									<div align=center>
										<img src="${pageContext.request.contextPath}/resources/wella/front/images/roundok.png">
									</div>
									<div>注册车成功</div>
								</span>
							</div>
						</div>
					</div>
	
					<div align=left style="margin-top: 70px; margin-bottom: 48px; width: 800px; height: 80%; background: white; box-shadow: 0 -1px 6px rgba(0, 0, 0, 0.5);">
						<div class="box-in-level1" style="margin-left: 7%; padding-top: 36px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:40px;">基本信息</div>
						<table style="margin-left: 15%; margin-right: 15%; width: 100%;">
							<tr>
								<td class="caption" style="width: 15%;"><span class="box-in-level2">企业类型</span></td>
								<td style="width: 85%;padding: 5px 0px;">
									<select name="compkind" data-placeholder="asdasd">
										<option value="0">央企</option>
										<option value="1">国企</option>
										<option value="2">民企</option>
										<option value="3">合资</option>
										<option value="4">上市公司</option>
										<option value="5">公交</option>
										<option value="6">城市天然气</option>
										
									</select>	
								</td>
							</tr>
							<tr>
								<td class="caption"><span class="box-in-level2">产品类型</span></td>
								<td  style="padding: 5px 0px;">
									<select name="prodkind" placeholder="asdasd">
										<option value="0" selected>贸易</option>
										<option value="1" >工业客户</option>
										<option value="2" >加气站</option>
										<option value="3" >物流用途</option>
										<option value="4" >公交</option>
										<option value="5" >城市天然气</option>
										
										
									</select>	
								</td>
							</tr>
							<!-- <tr>
								<td class="caption"><span class="box-in-level2">用户名</span></td>
								<td>
									<input type="text" name="username" style="width: 50%;" placeholder="请输入用户名">
								</td>
							</tr> -->
							<tr>
								<td class="caption" ><span class="box-in-level2">所在地区</span></td>
								<td style="padding: 5px 0px;">
									<select id="provinceId" name="provinceId" onchange="selRegion(0);">
					                	<option>--请选择省--</option>
					                	<c:forEach items="${provinceList}" var="item" varStatus="status">
					                    	<option value="${item.regionId}">${item.regionName}</option>
					                    </c:forEach>
							        </select>
						            <select id="cityId" name="cityId" onchange="selRegion(1);">
					                    <option>--请选择市--</option>
					                </select>
					                <select id="regionId" name="regionId" onchange="selRegion(2);">
					                	<option>--请选择区--</option>
					                </select>
								</td>
							</tr>
							<tr>
								<td class="caption"><span class="box-in-level2">详细地址</span></td>
								<td><input type="text" name="address" style="width: 50%;" placeholder="请输入详细地址"></td>
							</tr>

							<tr>
								<td class="caption" style="width: 15%;"><span class="box-in-level2">登录密码</span></td>
								<td style="width: 85%;">
									<input type="password" name="pass1"  id="pass1" style="width: 50%;">
								</td>
							</tr>
							<tr>
								<td class="caption"><span class="box-in-level2">确认密码</span></td>
								<td><input type="password" name="pass2" style="width: 50%;"></td>
							</tr>
							<tr style="display:none;">
								<td class="caption"><span class="box-in-level2">验证码</span></td>
								<td>
									<input style="width: 20%; margin-right: 12px;" name="checkCode" id="checkCode"/>
									<div class="yzm" style="display: inline;"><img id='imageCode' onclick='changeCheckCode();' src='${pageContext.request.contextPath}/front/sender/SenderLoginController-getVerifyImage' style='cursor: pointer;width:75px;height:28px;margin: 0px 10px 4px 1px;' title='看不清，点击换一张' /></div>
									<div class="change" onclick='changeCheckCodeImage();' style="display: inline;">点击换一张</div>
								</td>

							</tr>
							
						</table>
						<div class="box-in-level1" style="margin-left: 7%; margin-top: 16px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:6px;">联系方式</div>
						<table style="margin-left: 15%; margin-right: 15%; width: 100%;">
							<tr>
								<td class="caption" style="width: 15%;"><span class="box-in-level2">联系人</span></td>
								<td style="width: 85%;">
									<input type="text" name="contact" style="width: 50%;" placeholder="请输入联系人">
								</td>
							</tr>
							<tr>
								<td class="caption"><span class="box-in-level2">联系人电话</span></td>
								<td>
									<input type="text" name="contactseat" style="width: 50%;" placeholder="请输入联系人电话">
								</td>
							</tr>
						</table>
						<div class="box-in-level1" style="margin-left: 7%; margin-top: 16px; margin-bottom: 16px;background: url('${pageContext.request.contextPath}/resources/wella/front/images/breakline.png') no-repeat 93px 50%;background-size:600px 10px;background-position-y:6px;">资质信息</div>
						<table style="margin-left: 15%; margin-right: 15%; width: 100%;">
							<tr>
								<td style="width: 15%;"></td>
								<td style="width: 85%;height:170px;">
									<div class="zizhixinxi" style="width:120px;height:120px;float: left;position:relative;">
										<div align=center style="height:70px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/zizhi_icon.png') no-repeat center;"></div>
										<div class="zizhititle">营业执照</div>
										<div>格式: JPG, PNG, GIF</div>
										<div>大小: 小于 5M</div>
										<input type="file" id="yingye_img1" name="yingye_img1_src"  class="fileManage"  />
										<input type="hidden" name="yingye_img1" />
										<img id="yingye_imgpath1" class="yingyeimg" style="" src="" />
										<input type="text" name="company_yy_zz" class="yingyetxt" placeholder="请输入营业执照"/>
									</div>									
									<div class="zizhixinxi" style="width:120px;height:120px;float: left;position:relative;">
										<div align=center style="height:70px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/zizhi_icon.png') no-repeat center;"></div>
										<div class="zizhititle">营业许可证</div>
										<div>格式: JPG, PNG, GIF</div>
										<div>大小: 小于 5M</div>
										<input type="hidden" name="yingye_img2"  class="fileManage"  />
										<input type="file" id="yingye_img2" name="yingye_img2_src"  class="fileManage" />
										<img id="yingye_imgpath2" class="yingyeimg" style="" src="" />
										<input type="text" name="company_xkz" class="yingyetxt" placeholder="请输入营业许可证"/>
									</div>									
									<div class="zizhixinxi" style="width:120px;height:120px;float: left;position:relative;">
										<div align=center style="height:70px;background:url('${pageContext.request.contextPath}/resources/wella/front/images/zizhi_icon.png') no-repeat center;"></div>
										<div class="zizhititle">特许经营许可证</div>
										<div>格式: JPG, PNG, GIF</div>
										<div>大小: 小于 5M</div>
										<input type="hidden" name="yingye_img3" />
										<input type="file" id="yingye_img3" name="yingye_img3_src" class="fileManage"  />
										<img id="yingye_imgpath3" class="yingyeimg" style="" src="" />
										<input type="text" name="company_txkz" class="yingyetxt" placeholder="请输入特许经营许可证"/>
									</div>									
								</td>
							</tr>


							<tr>
								<td></td>
								
								<td>
									<input type="hidden" name="zc_region_id" id="zc_region_id" />
									<input type="hidden" name="user_type" id="user_type" value="3">
									<input id="submit" class="blue-button" type="submit" value="确定">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	
		$("td").attr("valign", "top");

		$("#nextstep").click( function(){
		});
		
		$(function() {
			
		});
		
		
		$("#yingye_img1").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(1);
 		});
		
		$("#yingye_img2").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(2);
 		});
		
		$("#yingye_img3").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(3);
 		});
		
		$("#yingye_img4").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(4);
 		});
		
		
		function clearFileName(){
	 		$("input[type='file']").each(function(){
			    $(this).attr("name", "");
			});
	 	}
		
		function uploadImage(idx){
			
	 		var options = {
	              url:  "${pageContext.request.contextPath}/uploadFile",
	              type:"POST",
	              dataType:"json",
	              data:{},
	              success : function(data) {
	            	  if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
	            	  //-----------
	            	   $("input[name='yingye_img"+idx+"']").val(data.path);
	            	  $("#yingye_imgpath"+idx).attr("src", data.path);
	            	  $("#yingye_imgpath"+idx).show();
	            	  return;
	            	  //-----------
	              },
	              error : function(data) {
	            	  //alert(data);
	              }
	 		}
	 		$("#sender_register").ajaxSubmit(options);
	 	}


		
		
		function selRegion(type){
			var regionId = '';
			
			if(type==0){
				regionId = $("#provinceId").val();
			} else if(type==1){
				regionId = $("#cityId").val();
			} else if(type==1){
				regionId = $("#regionId").val();
			} else return;
			$("#zc_region_id").val(regionId);
			if(regionId!=''){
				$.post("${pageContext.request.contextPath}/front/sender/SenderLoginController-getChildRegionListInSite", {regionId:regionId},	function(data) {
					if(data.state == 1) {
						html = "";
						for(var i=0; i<data.regionList.length; i++){
							region = data.regionList[i];
							html += "<option value='" + region.regionId + "'>" + region.regionName + "</option>";
						}
						
						if(type==0){
							$("#cityId").html("<option>--请选择市--</option>" + html);
							$("#regionId").html("<option>--请选择区--</option>");
						} else if(type==1){
							$("#regionId").html("<option>--请选择区--</option>" + html);
						}
					}
				}, 'json');
			}
		}
		
		//发生验证号图片
		function changeCheckCode(){
			var append = "?clearCache=" + new Date().getTime() + "a" + Math.random();    
			$("#imageCode").attr("src", "${pageContext.request.contextPath}/front/sender/SenderLoginController-getVerifyImage" + append);
			$("#checkcode").val("");
			$("#checkcode").focus();
		}
		
		function changeCheckCodeImage(){
			$(".loginError").hide("slow");
			changeCheckCode();
		}
		var flag = false;
 		function myfunc(){
	 		if($("#agreeBtn").prop("checked")){
	 			$("#kehuzhuce_page1").hide();
 				$("#kehuzhuce_page2").show();
 				flag = true;
	 		}else{
	 			alert("请点击同意为助供应链服务协议");
	 		}
	 		return false;
 		}
 		
 		
 		$("form").validate({
            rules: {
            	companyname: "required",
            	companyaccount: "required",
            	pass1: "required",
            	pass2: {required: true, equalTo: "#pass1"},
            	address: "required",
            	contact: {required: true},
            	contactemail: {required: true, email: true},
            	contactphone: {required: true},
            	contactseat: "required"
            },
	        messages: {
	        	companyname: "请输入企业名称",
	        	companyaccount: "请输入企业注册号",
	        	pass1: "请输入密码",
	        	pass2: {  
                    required: "请输入确认密码",  
                    equalTo: "输入密码不一致"  
                },
                address: "请输入详细地址",
                contact: "请输入联系人",
                contactemail: "请输入电子邮箱",
                contactphone: "请输入手机号码",
                contactseat: "请输入座机号码"
	        },
		    submitHandler: function(form){
		    	if($("#agreeBtn").prop("checked")){
		 			
 					var companyname =$("input[name='companyname']").val();
		    		$.post("${pageContext.request.contextPath}/front/sender/SenderLoginController-onCheckCompanyName", {companyname:companyname},	function(data) {
						if(data.state == 1) {
							$("#kehuzhuce_page1").hide();
			 				$("#kehuzhuce_page2").show();
							flag = true;
						}else{
							alert(data.content);
							return false;
						}
						if(flag){
				    		if($("input[name='yingye_img1_src']").val()==""){
					    		alert("请选择营业执照!"); return;
					    	}
					    	
					    	if($("input[name='yingye_img2_src']").val()==""){
					    		alert("请选择营业许可证!"); return;
					    	}
					    	
					    	if($("input[name='yingye_img3_src']").val()==""){
					    		alert("请选择特许经营许可证!"); return;
					    	}
					    	
					    	if($("input[name='company_yy_zz']").val()==""){
					    		alert("请输入营业执照!"); return;
					    	}
					    	
					    	if($("input[name='company_xkz']").val()==""){
					    		alert("请输入营业许可证!"); return;
					    	}
					    	
					    	if($("input[name='company_txkz']").val()==""){
					    		alert("请输入特许经营许可证!"); return;
					    	}
					    	if($("#zc_region_id").val()==""){
					    		alert("请选择地区信息!"); return;
					    	}
					    				    	
					    	var contactemail =$("input[name='contactemail']").val();
				 			var contactphone =$("input[name='contactphone']").val();
				 			$.post("${pageContext.request.contextPath}/front/sender/SenderLoginController-onCheckMobileEmail", {contactphone:contactphone,contactemail:contactemail},	function(data) {
								if(data.state == -3 || data.state == -4) {
									alert(data.content);
									return false;
								}else{
									$(form).attr("action", "<c:url value="/register/register"/>");
									$.post($(form).attr("action"),$(form).serialize(),function(data){
							    		data = $.parseJSON(data);
							    		if(data.state==1){
							            	window.location.href = "${pageContext.request.contextPath}/front/sender/SenderLoginController-registerNext";
							            }else{
							            	alert(data.content);
							            }
							      	})
							      	.error(function(data){
							      		alert("操作失败！")
							      	});
								}
							}, 'json'); 
				    	}
					}, 'json');
	 				
		 		}else{
		 			alert("请点击同意为助供应链服务协议");
		 			return;
		 		}
		    	
		    },
			errorElement: "div",
			errorClass: "error"
		});
 		
 		
	</script>
</body>