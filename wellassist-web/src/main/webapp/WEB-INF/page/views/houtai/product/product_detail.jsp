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
             	<h3 class="box-title">商品详情</h3>
           	</div>
           	<div class="box-body">
            	<div class="dl-horizontal detail-hz">
               		<dl class="ddOdd">
               			<dt>商品名称 : </dt>
               			<dd>${prodInfo.prodName}</dd>
               		</dl>
               		<dl>
               			<dt>商品图片 : </dt>
               			<dd><a class="fancybox" href="${pageContext.request.contextPath}/${prodInfo.prodImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${prodInfo.prodImg}" alt="" style="width:50px; height:50px;" /></a></dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>商品介绍 :</dt>
               			<dd style="word-wrap: break-word;">${prodInfo.prodIntro}</dd>
               		</dl>
               		<dl>
               			<dt>供应商名称 :	</dt>
               			<dd>${prodInfo.userName}</dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>商品添加时间 : </dt>
               			<dd><fmt:formatDate value="${prodInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
               		</dl>
               		<dl>
               			<dt>商品添加人 : </dt>
               			<dd>${prodInfo.createUserName}</dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>商品金额(元): </dt>
               			<dd>${prodInfo.prodMoney}</dd>
               		</dl>
               		<dl>
               			<dt>过路费收取类型 : </dt>
               			<dd>
							<c:if test="${prodInfo.vehicleSqMoneyType==0}">自提</c:if>
							<c:if test="${prodInfo.vehicleSqMoneyType==1}">配送</c:if>
					 	</dd>
					 </dl>
               		<dl class="ddOdd">
               			<dt>过路费收取费用(元): </dt>
               			<dd>${prodInfo.vehicleSqMoney}</dd>
               		</dl>
               		<dl>
               			<dt>现在状态 : </dt>
               			<dd>
           					<c:if test="${prodInfo.prodState==-2}"><font class="text-yellow">审核不通过</font></c:if>
							<c:if test="${prodInfo.prodState==1}"><font class="text-aqua">审核申请</font></c:if>
							<c:if test="${prodInfo.prodState==2}"><font class="text-green">审核通过</font></c:if>
           				</dd>
           			</dl>
               		<dl class="ddOdd">
               			<dt>操作 : </dt>
               			<dd>
               				<form id="prodStateForm" name="prodStateForm" action="${pageContext.request.contextPath}/ht/Product/ProductCtrl-setProdState">
   								<input type="radio" name="prodState" value="2"  style="cursor: pointer;" /> 通过
   								<input type="radio" name="prodState" value="-1" style="margin-left: 20px; cursor: pointer;" /> 不通过
   								<input type="hidden" name="prodId" value="${prodInfo.prodId}" />
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

<script type="text/javascript">
 	$(function(){
 		$('.fancybox').fancybox();
 		
 		$('#btn_ok').click(function() {
 			$("#prodStateForm").submit();
		});
 		
 		$('#btn_cancle').click(function() {
 			goBack();
		});
 		
 		// validate检查
 		$("#prodStateForm").validate({
		    rules: {
		    	prodState:{required: true}
		    },
		    messages: {
		    	prodState:{required: "请选择状态!"}
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
		            	window.location.href = "${pageContext.request.contextPath}/ht/Product/ProductCtrl-getProductList";
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