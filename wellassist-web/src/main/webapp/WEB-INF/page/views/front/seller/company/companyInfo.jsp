<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>"></script>

<style>
.container1{min-height: 990px;width: 100%;}
select{width:120px;}
input {
	padding: 5px;
    font-size: 14px;
}
div.error{
	width: 200px;
   	display: inline-block;
   	padding-left: 42px;
}
</style>

</head>

<div class="container1">
	<div style="margin:40px 0 0 210px;">
		<div id = "content-rect" style="width:90%;">
			<div style="border:solid 1px #d0d0d0;">
				<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:18px;color:#0557ab;font-weight: bold;">公司信息</div>

				<form action="#" method="post">		
					<div style="font-size:14px;padding:32px 45px;display: inline-block;width: 90%;">
						<div>
							<div style="float:left;margin-right:8px;width:10%;">当前头像: </div>
							<div id="companyicon" style="float:left;width:12%;height:100px;border:solid 1px #ccc;">
								<img id="icon" style="margin:8px;" src="${userInfo.companyImg}">
							</div>
							<div style="float:left;margin-left:32px;">
								<div style="font-size:20px;font-weight:bold;margin-bottom:12px;">${user.userName}</div>
								<div style="margin-bottom:12px;font-size:16px;">
									<img src="<c:url value="/resources/wella/front/images/seller/lianxiicon1.png"/>">
									<span style="margin-left:8px;margin-right:42px;">实名认证</span>
									<img src="<c:url value="/resources/wella/front/images/seller/lianxiicon2.png"/>">
									<span style="margin-left:8px;margin-right:42px;">手机认证</span>
									<img src="<c:url value="/resources/wella/front/images/seller/lianxiicon3.png"/>">
									<span style="margin-left:8px;margin-right:42px;">营业执照已认证</span>
								</div>
								<div style="margin-bottom:12px;font-size:16px;">
									<span style="margin-right:12px;">近三个月交易:</span>
									<span style="color:red;">${threeJyCn}</span>
									<span style="margin-right:24px;">笔</span>
									<span style="margin-right:12px;">进行中的交易:</span>
									<span style="color:red;">${ingJyCn}</span>
									<span style="margin-right:24px;">笔</span>
								</div>
							</div>
						</div>
					</div>
					<div style="margin-top:24px;display: inline-block;width: 90%;padding-left:45px;">
						<span style="display:inline-block;width:10%;font-size:14px;">所在地区</span>
						<select id="provinceId" name="provinceId" onchange="selRegion(0);">
		                	<option>--请选择省--</option>
		                	<c:forEach items="${provinceList}" var="item" varStatus="status">
		                    	<option value="${item.regionId}" <c:if test="${item.regionId==provinceId}" >selected</c:if> >${item.regionName}</option>
		                    </c:forEach>
				        </select>
			            <select id="cityId" name="cityId" onchange="selRegion(1);">
		                    <option>--请选择市--</option>
		                    <c:forEach items="${cityList}" var="item" varStatus="status">
		                    	<option value="${item.regionId}"  <c:if test="${item.regionId==cityId}" >selected</c:if>  >${item.regionName}</option>
		                    </c:forEach>
		                </select>
		                <select id="regionId" name="regionId" onchange="selRegion(2);">
		                	<option>--请选择区--</option>
		                	<c:forEach items="${countyList}" var="item" varStatus="status">
		                    	<option value="${item.regionId}"  <c:if test="${item.regionId==userInfo.zcRegionId}" >selected</c:if> >${item.regionName}</option>
		                    </c:forEach>
		                </select>
					</div>
					<div style="margin-top:24px;padding-left:45px;display: inline-block;width: 90%;">
						<span style="display:inline-block;width:10%;font-size:14px;">详细地址</span>
						<input name="zcXxAddress" id="zcXxAddress" placeholder="请输入详细地址" style="width:40%;height: 30px;" value="${userInfo.zcXxAddress}">
					</div>
			
			
					<div style="margin-top:24px;padding-left:45px;display: inline-block;width: 90%;">
						<span style="display:inline-block;width:10%;font-size:14px;">电子邮箱</span>
						<input name="userEmail" id="userEmail" placeholder="请输入电子邮箱" style="width:40%;height: 30px;" value="${user.userEmail}">
					</div>
					
					<div style="margin-top:24px;padding-left:45px;display: inline-block;width: 90%;">
						<span style="display:inline-block;width:10%;font-size:14px;">手机号码</span>
						<input name="userPhone" id="userPhone" placeholder="请输入手机号码" style="width:40%;height: 30px;" value="${user.userPhone}">
					</div>
					
					
					<div style="margin-bottom: 32px;margin-top: 32px;margin-left: 11%;">
						<input type="submit" id="submit" class="bluebutton" style="padding-left: 24px;padding-right: 24px;padding-top: 8px;padding-bottom: 8px;font-size:20px;border-radius: 6px;border:none;" value="保存">
					</div>
					<input type="hidden" name="zc_region_id" id="zc_region_id" value="${userInfo.zcRegionId}" />
				</form>
			</div>
        </div>
    </div>
</div>

<script type="text/javascript">
	$("#companyicon").height($("#companyicon").width());
	$("#icon").height($("#companyicon").innerHeight()-16);
	$("#icon").width($("#companyicon").innerWidth()-16);
	
	$(function() {
		
		$("form").validate({
	        rules: {
	        	zcXxAddress: "required",
	        	userPhone: "required",
	        	userEmail: "required"
	        },
	        messages: {
	        	zcXxAddress: "请输入详细地址！",
	        	userPhone: "请输入手机号码！",
	        	userEmail: "请输入电子邮箱！"
	        },
		    submitHandler: function(form){
		    	var region_id = $("#zc_region_id").val();
		    	if(region_id=="")
	    		{
	    			alert("请选择地区信息");
	    			return false;
	    		}
		    	var act_url = "${pageContext.request.contextPath}/front/seller/company/SellerCompanyController-companyInfoUpdate";
		    	$(form).attr("action", act_url);
				$.post($(form).attr("action"),$(form).serialize(),function(data){
		    		data = $.parseJSON(data);
		    		if(data.state==1){
		            	alert("保存成功!");
		            }else{
		            	alert(data.content);
		            }
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
		    	
		    },
			errorElement: "div",
			errorClass: "error"
		});
	});
	
	
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
</script>
<%@ include file="../footer.html"%>