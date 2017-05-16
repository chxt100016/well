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
             	<h3 class="box-title">买家信息</h3>
           	</div>
           	<div class="box-body">
           		<div class="dl-horizontal detail-hz">
					<div class="pull-left col-xs-12 ddt-hz">企业信息</div>
					<div class="pull-left col-xs-12 ddOdd">
						<dl>
	               			<dt>企业名称 : </dt>
	               			<dd>${userMainInfo.userName}</dd>
	               		</dl>   							
	               		<dl>
	               			<dt>企业注册号 : </dt>
	               			<dd>${userMainInfo.userRegNo}</dd>
	               		</dl>
	               		<c:if test="${userOtherInfo.companyImg!=null && userOtherInfo.companyImg!=''}">
	               		<dl>
	               			<dt>企业图标 : </dt>
	               			<dd>
	               				<a class="fancybox" href="${userOtherInfo.companyImg}" data-fancybox-group="gallery" title=""><img src="${userOtherInfo.companyImg}" alt="" style="width:50px; height:50px;" /></a>
	               			</dd>
	               		</dl>
	               		</c:if>
	   				</div>
	   				
	   				<div class="pull-left col-xs-12 ddt-hz">其本信息</div>
					<div class="pull-left col-xs-12 ddOdd">
						<dl>
	               			<dt>电子邮箱 : </dt>
	               			<dd>${userMainInfo.userEmail}</dd>
	               		</dl>
	               		<dl>
	               			<dt>手机号码 : </dt>
	               			<dd>${userMainInfo.userPhone}</dd>
	               		</dl>
						<dl>
	               			<dt>企业类型 : </dt>
	               			<dd>
               					<c:if test="${userOtherInfo.companyType==1}">央企</c:if>
               					<c:if test="${userOtherInfo.companyType==2}">国企</c:if>
               					<c:if test="${userOtherInfo.companyType==3}">民企</c:if>
               					<c:if test="${userOtherInfo.companyType==4}">合资</c:if>
               					<c:if test="${userOtherInfo.companyType==5}">上市公司</c:if>
	               			</dd>
	               		</dl>   							
	               		<dl>
	               			<dt>商品类型 : </dt>
	               			<dd>
               					<c:if test="${userOtherInfo.spType==1}">贸易</c:if>
               					<c:if test="${userOtherInfo.spType==2}">工业客户</c:if>
               					<c:if test="${userOtherInfo.spType==3}">加气站</c:if>
               					<c:if test="${userOtherInfo.spType==4}">物流用途</c:if>
               					<c:if test="${userOtherInfo.spType==5}">公交</c:if>
               					<c:if test="${userOtherInfo.spType==6}">城市天然气</c:if>
	               			</dd>
	               		</dl>
	               		<dl>
	               			<dt>所在地区 : </dt>
	               			<dd>${userOtherInfo.zcRegionName}</dd>
	               		</dl>
	               		<dl>
	               			<dt>详细地址 : </dt>
	               			<dd>${userOtherInfo.zcXxAddress}</dd>
	               		</dl>
	   				</div>
	   				
	   				<div class="pull-left col-xs-12 ddt-hz">联系方式</div>
					<div class="pull-left col-xs-12 ddOdd">
	               		<dl>
	               			<dt>联系人 : </dt>
	               			<dd>${userOtherInfo.companyLxr}</dd>
	               		</dl>
	               		<dl>
	               			<dt>联系人电话 : </dt>
	               			<dd>${userOtherInfo.companyLxrPhone}</dd>
	               		</dl>
	   				</div>
	   				
	   				<div class="pull-left col-xs-12 ddt-hz">资质信息</div>
					<div class="pull-left col-xs-12 ddOdd">
						<dl>
	               			<dt>公司营业执照 : </dt>
	               			<dd>${userOtherInfo.companyYyZz}</dd>
	               		</dl>
	               		<c:if test="${userOtherInfo.companyYyZzImg!=null && userOtherInfo.companyYyZzImg!=''}">
	               		<dl>
	               			<dd>
	               				<a class="fancybox" href="${userOtherInfo.companyYyZzImg}" data-fancybox-group="gallery" title=""><img src="${userOtherInfo.companyYyZzImg}" alt="" style="width:50px; height:50px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
	               			</dd>
	               		</dl>
	               		</c:if>
	               		<dl>
	               			<dt>公司营业许可证 : </dt>
	               			<dd>${userOtherInfo.companyKhXkz}</dd>
	               		</dl>
	               		<c:if test="${userOtherInfo.companyXkzImg!=null && userOtherInfo.companyXkzImg!=''}">
	               		<dl>
	               			<dd>
	               				<a class="fancybox" href="${userOtherInfo.companyXkzImg}" data-fancybox-group="gallery" title=""><img src="${userOtherInfo.companyXkzImg}" alt="" style="width:50px; height:50px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
	               			</dd>
	               		</dl>
	               		</c:if>
	               		<dl>
	               			<dt>特许经营许可证 : </dt>
	               			<dd>${userOtherInfo.companyTxkz}</dd>
	               		</dl>
	               		<c:if test="${userOtherInfo.companyTxkzImg!=null && userOtherInfo.companyTxkzImg!=''}">
	               		<dl>
	               			<dd>
	               				<a class="fancybox" href="${userOtherInfo.companyTxkzImg}" data-fancybox-group="gallery" title=""><img src="${userOtherInfo.companyTxkzImg}" alt="" style="width:50px; height:50px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
	               			</dd>
	               		</dl>
	               		</c:if>
					</div>
					
					<div class="pull-left col-xs-12 ddt-hz">操作</div>
					<div class="pull-left col-xs-12 ddOdd">
						<dl>
	               			<dt>现在状态 : </dt>
	               			<dd>
	               				<c:if test="${userMainInfo.userState==-2}"><font class="text">冻结</font></c:if>
								<c:if test="${userMainInfo.userState==-1}"><font class="text-yellow">审核不同过</font></c:if>
								<c:if test="${userMainInfo.userState==0}"><font class="text-aqua">未审核</font></c:if>
								<c:if test="${userMainInfo.userState==1}"><font class="text-green">审核通过</font></c:if>
	               			</dd>
	               		</dl>   							
	               		<dl>
	               			<dt>状态选择 : </dt>
	               			<dd>
	               				<form id="sellerStateForm" name="sellerStateForm" action="${pageContext.request.contextPath}/houtai/user/HoutaiUserSellerCtrl-setUserState">
	               					<input type="radio" name="userState" value="-2"  style="cursor: pointer;" /> 冻结
   									<input type="radio" name="userState" value="-1" style="margin-left: 20px; cursor: pointer;" /> 审核不同过
   									<input type="radio" name="userState" value="1" style="margin-left: 20px; cursor: pointer;" /> 审核通过
   									<input type="hidden" name="userId" value="${userId}" />
   								</form>
	               			</dd>
	               		</dl>
	               		<dl>
	               			<dt></dt>
	               			<dd>
	               				<input type="button" id="btn_ok" 	 value="确定" style="width: 50px; line-height: 25px;" />
   								<input type="button" id="btn_cancle" value="取消" style="width: 50px; line-height: 25px; margin-left: 20px;" />
	               			</dd>
	               		</dl>
	   				</div>
            	</div>
           </div>
        </div>
	</div>
</div>

<script type="text/javascript">
 	$(function(){
 		$('.fancybox').fancybox();
 		
 		$('#btn_ok').click(function() {
 			$("#sellerStateForm").submit();
		});
 		
 		$('#btn_cancle').click(function() {
 			goBack();
		});
 		
 		// validate检查
 		$("#sellerStateForm").validate({
		    rules: {
		    	userState:{required: true}
		    },
		    messages: {
		    	userState:{required: "请选择状态!"}
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
		            	window.location.href = "${pageContext.request.contextPath}/houtai/user/HoutaiUserBuyerCtrl-getBuyerList";
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
		    }
	    });
 	});
</script>

<%@ include file="../../footer.jsp"  %>