<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error-div{text-align:left; line-height: 10px;}
</style>

<div class="box" >
	<div class="col-xs-12">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">供应商信息<c:if test="${userId==0}"></c:if>添加<c:if test="${userId!=0}">编辑</c:if></h3>
           	</div>
           	<div class="box-body">
           		<form id="sellerEditForm" name="sellerEditForm" action="${pageContext.request.contextPath}/houtai/user/HoutaiUserSellerCtrl-setSellerInfo">
            		
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
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">密码信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt><font class="chkStar">*</font> 登录密码 : </dt>
		               			<dd><input type="password" name="userPass" value=""></dd>
		               		</dl>   							
		               		<dl>
		               			<dt><font class="chkStar">*</font> 确认密码 : </dt>
		               			<dd><input type="password" name="userPass2" value=""></dd>
		               		</dl>
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">其本信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt><font class="chkStar">*</font> 企业类型 : </dt>
		               			<dd>
		               				<select name="companyType">
		               					<option>-- 请选择企业类型 --</option>
		               					<option value="0">央企</option>
		               					<option value="1">国企</option>
		               					<option value="2">民企</option>
		               					<option value="3">合资</option>
		               					<option value="4">上市公司</option>
		               				</select>
		               			</dd>
		               		</dl>   							
		               		<dl>
		               			<dt><font class="chkStar">*</font> 商品类型 : </dt>
		               			<dd>
		               				<select name="spType">
		               					<option>-- 请选择商品类型 --</option>
		               					<option value="0">贸易</option>
		               					<option value="1">工业客户</option>
		               					<option value="2">加气站</option>
		               					<option value="3">物流用途</option>
		               					<option value="4">公交</option>
		               					<option value="5">城市天然气</option>
		               				</select>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 用户账号 : </dt>
		               			<dd><input type="text" name="userAccount" value=""></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 所在地区 : </dt>
		               			<dd>
		               				<select name="spType">
		               					<option>-- 请选择省 --</option>
		               				</select>
		               				<select name="spType">
		               					<option>-- 请选择市 --</option>
		               				</select>
		               				<select name="spType">
		               					<option>-- 请选择区 --</option>
		               				</select>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 详细地址 : </dt>
		               			<dd><input type="text" name="zcXxAddress" value=""></dd>
		               		</dl>
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">联系方式</div>
						<div class="pull-left col-xs-12 ddOdd">
		               		<dl>
		               			<dt><font class="chkStar">*</font> 联系人 : </dt>
		               			<dd><input type="text" name="companyLxr" value=""></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 电子邮箱 : </dt>
		               			<dd><input type="text" name="userEmail" value=""></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 手机号码 : </dt>
		               			<dd><input type="text" name="userPhone" value=""></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 座机号码 : </dt>
		               			<dd><input type="text" name="companyLxrPhone" value=""></dd>
		               		</dl>
		   				</div>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">资质信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt><font class="chkStar">*</font> 公司营业执照 : </dt>
		               			<dd><input type="text" name="companyYyZz" value=""></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 公司营业许可证 : </dt>
		               			<dd><input type="text" name="companyKhXkz" value=""></dd>
		               		</dl>
		               		<dl>
		               			<dt><font class="chkStar">*</font> 特许经营许可证 : </dt>
		               			<dd><input type="text" name="companyTxkz" value=""></dd>
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
 			$("#sellerEditForm").submit();
		});
 		
 		$('#btn_cancle').click(function() {
 			goBack();
		});
 		
 		// validate检查
 		$("#sellerEditForm").validate({
		    rules: {
		    	prodState:{required: true}
		    },
		    messages: {
		    	prodState:{required: "请选择状态!"},
		    },
		    errorPlacement: function (error, element) { 
		    	if($(element).closest('dd').children().filter("div.error-div").length < 1) 
				$(element).closest('dd').append("<div class='error-div'></div>");	
				$(element).closest('dd').children().filter("div.error-div").append(error);
		    },
		    submitHandler: function(form){
		    	$.post($(form).attr("action"),$(form).serialize(),function(data){
		    		data = $.parseJSON(data);
		    		alert(data.content);
		            if(data.status=="1"){
		            	window.location.href = "${pageContext.request.contextPath}/houtai/user/HoutaiUserSellerCtrl-getSellerList";
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
		    }
	    });
 	});
</script>

<%@ include file="../footer.jsp"  %>