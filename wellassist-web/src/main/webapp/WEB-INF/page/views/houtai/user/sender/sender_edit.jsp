<%@ include file="../../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error-div{text-align:left; line-height: 10px;}
</style>

<div class="box" >
	<div class="col-xs-12">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">物流信息<c:if test="${userId==0}"></c:if>添加<c:if test="${userId!=0}">编辑</c:if></h3>
           	</div>
           	<div class="box-body">
           		<form id="senderEditForm" name="senderEditForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/houtai/user/HoutaiUserSenderCtrl-setSenderInfo">
            		
            		<input type="hidden" name="userId" value="${userId}" />
            		
            		<div class="dl-horizontal detail-hz">
						<div class="pull-left col-xs-12 ddt-hz">企业信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt><font class="chkStar">*</font> 企业名称 : </dt>
		               			<dd><input type="text" name="userName" value="${userMainInfo.userName}"></dd>
		               		</dl>   							
		               		<dl>
		               			<dt><font class="chkStar">*</font> 企业注册号 : </dt>
		               			<dd><input type="text" name="userRegNo" value="${userMainInfo.userRegNo}"></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 企业图标 : </dt>
		               			<dd>
		               				<input type="file" id="companyImgFile" name="companyImgFile" />
		               				<input type="hidden" name="companyImg" />
		               				<div id="companyImgDiv" style="display: none; margin-top: 10px;">
										<a id="companyImgA" class="fancybox" href="" data-fancybox-group="zfxy">
							             	<img id="companyImgDis" class="img_100_100" src="" alt="">
							            </a>
									</div>
		               			</dd>
		               		</dl>
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">其本信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt><font class="chkStar">*</font> 电子邮箱 : </dt>
		               			<dd><input type="text" name="userEmail" value="${userMainInfo.userEmail}"></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 手机号码 : </dt>
		               			<dd><input type="text" name="userPhone" value="${userMainInfo.userPhone}"></dd>
		               		</dl>
							<dl>
		               			<dt><font class="chkStar">*</font> 企业类型 : </dt>
		               			<dd>
		               				<select name="companyType">
		               					<option value=''>-- 请选择企业类型 --</option>
		               					<option value="1" <c:if test="${userOtherInfo.companyType==1}">selected="selected"</c:if> >央企</option>
		               					<option value="2" <c:if test="${userOtherInfo.companyType==2}">selected="selected"</c:if> >国企</option>
		               					<option value="3" <c:if test="${userOtherInfo.companyType==3}">selected="selected"</c:if> >民企</option>
		               					<option value="4" <c:if test="${userOtherInfo.companyType==4}">selected="selected"</c:if> >合资</option>
		               					<option value="5" <c:if test="${userOtherInfo.companyType==5}">selected="selected"</c:if> >上市公司</option>
		               				</select>
		               			</dd>
		               		</dl>   							
		               		<dl>
		               			<dt><font class="chkStar">*</font> 商品类型 : </dt>
		               			<dd>
		               				<select name="spType">
		               					<option value=''>-- 请选择商品类型 --</option>
		               					<option value="1" <c:if test="${userOtherInfo.spType==1}">selected="selected"</c:if> >贸易</option>
		               					<option value="2" <c:if test="${userOtherInfo.spType==2}">selected="selected"</c:if> >工业客户</option>
		               					<option value="3" <c:if test="${userOtherInfo.spType==3}">selected="selected"</c:if> >加气站</option>
		               					<option value="4" <c:if test="${userOtherInfo.spType==4}">selected="selected"</c:if> >物流用途</option>
		               					<option value="5" <c:if test="${userOtherInfo.spType==5}">selected="selected"</c:if> >公交</option>
		               					<option value="6" <c:if test="${userOtherInfo.spType==6}">selected="selected"</c:if> >城市天然气</option>
		               				</select>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 所在地区 : </dt>
		               			<dd>
		               				<select name="zcRegionId_0">
		               					<option value=''>-- 请选择省 --</option>
		               					<c:forEach var="item" items="${regionList}" varStatus="status">
											<option value="${item.regionId}">${item.regionName}</option>
						                </c:forEach>
		               				</select>
		               				<select name="zcRegionId_1">
		               					<option value=''>-- 请选择市 --</option>
		               				</select>
		               				<select name="zcRegionId_2">
		               					<option value=''>-- 请选择区 --</option>
		               				</select>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 详细地址 : </dt>
		               			<dd><input type="text" name="zcXxAddress" value="${userOtherInfo.zcXxAddress}"></dd>
		               		</dl>
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">联系方式</div>
						<div class="pull-left col-xs-12 ddOdd">
		               		<dl>
		               			<dt><font class="chkStar">*</font> 联系人 : </dt>
		               			<dd><input type="text" name="companyLxr" value="${userOtherInfo.companyLxr}"></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 联系人电话 : </dt>
		               			<dd><input type="text" name="companyLxrPhone" value="${userOtherInfo.companyLxrPhone}"></dd>
		               		</dl>
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">资质信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt><font class="chkStar">*</font> 公司营业执照 : </dt>
		               			<dd><input type="text" name="companyYyZz" value="${userOtherInfo.companyYyZz}"></dd>
		               		</dl>
		               		<dl>
		               			<dd>
		               				<input type="file" id="companyYyZzImgFile" name="companyYyZzImgFile" />
		               				<input type="hidden" name="companyYyZzImg" />
		               				<div id="companyYyZzImgDiv" style="display: none; margin-top: 10px;">
										<a id="companyYyZzImgA" class="fancybox" href="" data-fancybox-group="zfxy">
							             	<img id="companyYyZzImgDis" class="img_100_100" src="" alt="">
							            </a>
									</div>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 公司营业许可证 : </dt>
		               			<dd><input type="text" name="companyKhXkz" value="${userOtherInfo.companyKhXkz}"></dd>
		               		</dl>
		               		<dl>
		               			<dd>
		               				<input type="file"   id="companyKhXkzImgFile" name="companyKhXkzImgFile" />
		               				<input type="hidden" name="companyKhXkzImg" />
		               				<div id="companyKhXkzImgDiv" style="display: none; margin-top: 10px;">
										<a id="companyKhXkzImgA" class="fancybox" href="" data-fancybox-group="zfxy">
							             	<img id="companyKhXkzImgDis" class="img_100_100" src="" alt="">
							            </a>
									</div>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt>特许经营许可证 : </dt>
		               			<dd><input type="text" name="companyTxkz" value="${userOtherInfo.companyTxkz}"></dd>
		               		</dl>
		               		<dl>
		               			<dd>
		               				<input type="file"   id="companyTxkzImgFile" name="companyTxkzImgFile" />
		               				<input type="hidden" name="companyTxkzImg" />
		               				<div id="companyTxkzImgDiv" style="display: none; margin-top: 10px;">
										<a id="companyTxkzImgA" class="fancybox" href="" data-fancybox-group="zfxy">
							             	<img id="companyTxkzImgDis" class="img_100_100" src="" alt="">
							            </a>
									</div>
		               			</dd>
		               		</dl>
						</div>
			   			
			   			<div class="pull-left col-xs-12 ddt-hz">
			   				<dl>
			   					<dt><input type="button" id="btn_ok" 	 value="确定" style="width: 50px; line-height: 25px;" /></dt>
   								<dd><input type="button" id="btn_cancle" value="取消" style="width: 50px; line-height: 25px; margin-left: 20px;" /></dd>
   							</dl>
	   					</div>
             		</div>
             	</form>
           </div>
        </div>
	</div>
</div>

<script type="text/javascript">
 	$(function(){
 		$('.fancybox').fancybox();
 		
 		$('#btn_ok').click(function() {
 			$("#senderEditForm").submit();
		});
 		
 		$('#btn_cancle').click(function() {
 			goBack();
		});
 		
 		$("select[name='zcRegionId_0']").change(function(){
 			getRegionList($(this).val(),1);
 		});
 		
 		$("select[name='zcRegionId_1']").change(function(){
			getRegionList($(this).val(),2);
		});
 		
 		$("#companyImgFile").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(0);
 		});
 		
 		$("#companyYyZzImgFile").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(1);
 		});
 		
 		$("#companyKhXkzImgFile").change(function(){
 			clearFileName();
 			$(this).attr("name", "file");
 			uploadImage(2);
 		});
 		
		$("#companyTxkzImgFile").change(function(){
			clearFileName();
			$(this).attr("name", "file");
			uploadImage(3);
		});
 		
 		// validate检查
 		$("#senderEditForm").validate({
		    rules: {
		    	companyName			:{required: true},
		    	userRegNo			:{required: true},
		    	companyImgFile		:{required: true},
		    	companyType			:{required: true},
		    	spType				:{required: true},
		    	userName			:{required: true},
		    	zcRegionId_0		:{required: true},
		    	zcXxAddress			:{required: true},
		    	companyLxr			:{required: true},
		    	userEmail			:{required: true},
		    	userPhone			:{required: true},
		    	companyLxrPhone 	:{required: true},
		    	companyYyZz			:{required: true},
		    	companyYyZzImgFile	:{required: true},
				companyKhXkz		:{required: true},
				companyKhXkzImgFile	:{required: true}
		    },
		    messages: {
		    	companyName			:{required: "请输入企业名称!"},
		    	userRegNo			:{required: "请输入企业注册号!"},
		    	companyImgFile		:{required: ""},//"请选择公司图标!"
		    	companyType			:{required: "请选择企业类型!"},
		    	spType				:{required: "请选择商品类型!"},
		    	userName			:{required: "请输入用户名称!"},
		    	zcRegionId_0		:{required: "请选择所在地区!"},
		    	zcXxAddress			:{required: "请输入详细地址!"},
		    	companyLxr			:{required: "请输入联系人!"},
		    	userEmail			:{required: "请输入电子邮箱!"},
		    	userPhone			:{required: "请输入手机号码!"},
		    	companyLxrPhone 	:{required: "请输入座机号码!"},
		    	companyYyZz			:{required: "请输入公司营业执照!"},
		    	companyYyZzImgFile	:{required: ""},//"请选择公司营业执照图片!"
		    	companyKhXkz		:{required: "请输入公司营业许可证!"},
		    	companyKhXkzImgFile	:{required: ""}//"请选择公司营业许可证图片!"
		    },
		    errorPlacement: function (error, element) { 
		    	if($(element).closest('dd').children().filter("div.error-div").length < 1) 
				$(element).closest('dd').append("<div class='error-div'></div>");	
				$(element).closest('dd').children().filter("div.error-div").append(error);
		    },
		    submitHandler: function(form){
		    	
		    	if($("#companyImgFile").val()==""){
		    		alert("请选择公司图标!"); return;
		    	}
		    	
				if($("#companyYyZzImgFile").val()==""){
		    		alert("请选择公司营业执照图片!"); return;
		    	}
		    	
				if($("#companyKhXkzImgFile").val()==""){
					alert("请选择公司营业许可证图片!"); return;
				}
		    	
		    	$.post($(form).attr("action"),$(form).serialize(),function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/houtai/user/HoutaiUserSenderCtrl-getSenderList";
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
		    }
	    });
 	});
 	
 	function getRegionList(pid,level){
 		if(pid!=null && pid!=""){
	 		$.post("${pageContext.request.contextPath}/houtai/user/HoutaiUserSellerCtrl-getRegionList",{pid:pid},function(data){
	 			data = $.parseJSON(data);
	            var regionList = data.regionList;
	            
	            $('#zcRegionId_' + level).empty();
	            var str = "";
	            
	            if(level==1) str = "<option value=''>-- 请选择市 --</option>";
	            else 		 str = "<option value=''>-- 请选择区 --</option>";
	            
	 			for(var i=0 ; i<regionList.length ; i++){
	 				str = str + "<option value='" + regionList[i].regionId + "'>" + regionList[i].regionName + "</option>";
	 			}
	 			
	 			$("select[name='zcRegionId_" + level + "']").html(str);
	      	})
	      	.error(function(data){
	      	});
 		}
 	}
 	
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
            	  if(idx==0){
            		  $("input[name='companyImg']").val(data.path);
            		  $("#companyImgA").attr("href","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyImgDis").attr("src","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyImgDiv").show();
            	  } else if(idx==1){
            		  $("input[name='companyYyZzImg']").val(data.path);
            		  $("#companyYyZzImgA").attr("href","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyYyZzImgDis").attr("src","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyYyZzImgDiv").show();
            	  } else if(idx==2){
            		  $("input[name='companyKhXkzImg']").val(data.path);
            		  $("#companyKhXkzImgA").attr("href","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyKhXkzImgDis").attr("src","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyKhXkzImgDiv").show();
            	  } else if(idx==3){
            		  $("input[name='companyTxkzImg']").val(data.path);
            		  $("#companyTxkzImgA").attr("href","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyTxkzImgDis").attr("src","${pageContext.request.contextPath}/" + data.path);
            		  $("#companyTxkzImgDiv").show();
            	  }
            	  
            	  return;
            	  //-----------
              },
              error : function(data) {
            	  //alert(data);
              }
 		}
		
 		$("#senderEditForm").ajaxSubmit(options);
 		
 	}
</script>

<%@ include file="../../footer.jsp"  %>