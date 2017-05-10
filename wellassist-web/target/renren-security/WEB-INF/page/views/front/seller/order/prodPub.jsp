<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<link rel="stylesheet" 	href="<c:url value="/resources/wella/front/css/seller/publishpage.css"/>">
	
<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;">&nbsp;&nbsp;产品发布</div>
	<div style="margin-top: 20px;">
		<div style="border:solid 1px #d0d0d0; font-size:16px; background-color:#f0f0f0;line-height:36px;">&nbsp;&nbsp;卖方名片 </div>
		<div style="border:solid 1px #d0d0d0; font-size:14px; border-top:0px; padding-top:10px;height:120px;">					
			<div id="companyicon" style="margin-left: 10px; float: left; width: 12%; height: 104px; border: 1px solid rgb(204, 204, 204);">
				<c:if test="${companyImg==null or companyImg==''}">
					<img id="icon" style="margin:8px;" src="<c:url value="/resources/upload/images/company_mark/kunlun.png"/>" />
				</c:if>
				<c:if test="${companyImg!=null and companyImg!=''}">
					<img id="icon" style="margin:8px;" src="${pageContext.request.contextPath}/${companyImg}" />
				</c:if>
			</div>
			<div style="float:left;margin-left:32px;">
				<div style="font-size:20px;margin-bottom:12px;">${userName}</div>
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
	
	<div style="margin-top:20px;">
		<div style="border:solid 1px #d0d0d0; font-size:16px; background-color:#f0f0f0;line-height:36px;">&nbsp;&nbsp;发布产品 </div>
		<div style="margin-top:20px;">					
			<form id="prodPubForm" name="prodPubForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/seller/SellerOrderController-setProdPub">
				<div class="rowfluid">
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 产品名称: </div>
					<input type="text" name="prodName" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> value="${prodInfo.prodName}" style="margin-left:10px;margin-bottom: 0px;" placeholder="请填写产品名称">
				</div>
				<div class="rowfluid" >
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 品类: </div>
					<select name="prodType" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> style="width:108px;margin-left:10px;">
           				<option value=''>-- 请选择品类 --</option>
           				<option value='0' <c:if test="${prodInfo.prodType=='0'}">selected='selected'</c:if> >气体</option>
           				<option value='1' <c:if test="${prodInfo.prodType=='1'}">selected='selected'</c:if> >燃油</option>
         			</select>
				</div>
				<div class="rowfluid" >
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 供应量: </div>
					<input type="text" name="prodNum" value="${prodInfo.prodNum}" style="margin-left:10px;margin-bottom: 0px;" placeholder="请填写整车吨位的倍数">
					<span> 屯</span>
				</div>
				<div class="rowfluid">
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 单价: </div>
					<input type="text" name="prodMoney" value="${prodInfo.prodMoney}" style="margin-left:10px;margin-bottom: 0px;" placeholder="请填写单价">
					<span> 元</span>
				</div>
				<div class="rowfluid" >
					<div class="itemlabel" align="right" style="float:left;display:inline-block;"><font class="chkStar">*</font> 所在区式: </div>
					<div style="margin-left:15px;float:left;">
						<select name="prodRegionId_0" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> style="width:108px;">
             				<option value=''>-- 请选择省 --</option>
             				<c:forEach var="item" items="${shengRegionList}" varStatus="status">
								<option <c:if test="${shengRegionId==item.regionId}">selected='selected'</c:if> value="${item.regionId}">${item.regionName}</option>
			                </c:forEach>
           				</select>
           				<select name="prodRegionId_1" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> style="width:108px;">
           					<option value=''>-- 请选择市 --</option>
           					<c:forEach var="item" items="${shiRegionList}" varStatus="status">
								<option <c:if test="${shiRegionId==item.regionId}">selected='selected'</c:if> value="${item.regionId}">${item.regionName}</option>
			                </c:forEach>
           				</select>
					</div>
				</div>
				<div class="rowfluid" style="clear:both;">
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 联系人: </div>
					<input type="text" name="prodLxr" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> value="${prodInfo.prodLxr}" style="margin-left:10px;margin-bottom: 0px;" placeholder="请填写联系人">
				</div>
				<div class="rowfluid" style="clear:both;">
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 联系电话: </div>
					<input type="text" name="prodLxrPhone" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> value="${prodInfo.prodLxrPhone}" style="margin-left:10px;margin-bottom: 0px;" placeholder="请填写联系电话">
				</div>
				<div class="rowfluid" style="clear:both;">
					<div class="itemlabel" align="right" style="float:left;"><font class="chkStar">*</font> 产品图片: </div>
					<input type="file" id="prodImgFile" name="file" <c:if test="${isAllEdit=='1'}">style='display:none;'</c:if> style="margin-left:15px;float:left;" />
             		<input type="hidden" name="prodImg" value="${prodInfo.prodImg}" />
             		<div id="prodImgDiv" <c:if test="${prodInfo.prodImg==null or prodInfo.prodImg==''}">style="display: none;"</c:if> style="margin-left:15px;float:left;">
						<a id="prodImgA" class="fancybox" href="${pageContext.request.contextPath}/${prodInfo.prodImg}" data-fancybox-group="zfxy">
			             	<img id="prodImgDis" class="img_100_100" src="${pageContext.request.contextPath}/${prodInfo.prodImg}" alt="">
			            </a>
					</div>
				</div>
				<div class="rowfluid" style="height:110px;clear:both;">
					<div class="itemlabel" align="right"><font class="chkStar">*</font> 产品简介: </div>
					<textarea name="prodIntro" <c:if test="${isAllEdit=='1'}">readonly='readonly'</c:if> style="margin-top:10px;margin-left:10px;width:500px;height:100px;" placeholder="请填写产品简介">${prodInfo.prodIntro}</textarea>
				</div>
				
				<input type="hidden" name="prodId" value="${prodInfo.prodId}" />
				<input type="hidden" name="prodState" />
			</form>
		</div>
	</div>
	
	<div style="margin:40px 0px 0px 185px;">
		<input id="savebtn" name="savebtn" type="button" value="保存" class="bluebutton" style="font-size:20px; border-radius:6px;">
		<input id="pubbtn"  name="pubbtn"  type="submit" value="发布" class="bluebutton" style="font-size:20px;border-radius:6px;">
	</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type="text/javascript">
	
	$("#companyicon").height($("#companyicon").width());
	$("#icon").height($("#companyicon").innerHeight()-16);
	$("#icon").width($("#companyicon").innerWidth()-16);
	
	$('.fancybox').fancybox();
		
	$('#savebtn').click(function() {
		$("input[name='prodState']").val(0);
		$("#prodPubForm").submit();
	});
	
	$('#pubbtn').click(function() {
		$("input[name='prodState']").val(1);
		$("#prodPubForm").submit();
	});
	
	$("select[name='prodRegionId_0']").change(function(){
		getRegionList($(this).val(),1);
	});
	
	$("#prodImgFile").change(function(){
		//clearFileName();
		//$(this).attr("name", "file");
		uploadImage(0);
	});
	
	// validate检查
	$("#prodPubForm").validate({
	    rules: {
	    	prodName		:{required: true},
	    	prodType		:{required: true},
	    	prodNum:{
	    		required : true,
        		number:true
        	},
	    	prodMoney:{
	    		required : true,
        		number:true
        	},
	    	prodRegionId_0	:{required: true},
	    	prodRegionId_1	:{required: true},
	    	prodLxr			:{required: true},
	    	prodLxrPhone	:{required: true},
	    	prodIntro		:{required: true}
	    },
	    messages: {
	    	prodName		:{required: "请输入产品名称!"},
	    	prodType		:{required: "请选择品类!"},
	    	prodNum			:{required: "请输入供应量!"},
	    	prodMoney		:{required: "请输入单价!"},
	    	prodRegionId_0	:{required: "请选择省!"},
	    	prodRegionId_1	:{required: "请选择市!"},
	    	prodLxr			:{required: "请输入联系人!"},
	    	prodLxrPhone	:{required: "请输入联系电话!"},
	    	prodIntro		:{required: "请输入产品简介!"}
	    },
	    errorPlacement: function (error, element) {
	    	/*
	    	if($(element).closest('div').children().filter("div.error-div").length < 1) 
			$(element).closest('div').append("<div class='error-div'></div>");	
			$(element).closest('div').children().filter("div.error-div").append(error);
			*/
	    },
	    submitHandler: function(form){
	    	
	    	if($("#prodImg").val()==""){
	    		alert("请选择产品图片!"); return;
	    	}
	    	
	    	$.post($("#prodPubForm").attr("action"),$("#prodPubForm").serialize(),function(data){
	    		data = $.parseJSON(data);
	    		alert(data.content);
	            if(data.status=="1"){
	            	window.location.href = "${pageContext.request.contextPath}/front/seller/SellerOrderController-prodList";
	            }
	      	})
	      	.error(function(data){
	      		alert("操作失败！")
	      	});
	    }
    });
	
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
            		  $("input[name='prodImg']").val(data.path);
            		  $("#prodImgA").attr("href","${pageContext.request.contextPath}/" + data.path);
            		  $("#prodImgDis").attr("src","${pageContext.request.contextPath}/" + data.path);
            		  $("#prodImgDiv").show();
            	  }
            	  
            	  return;
            	  //-----------
              },
              error : function(data) {
            	  //alert(data);
              }
 		}
		
 		$("#prodPubForm").ajaxSubmit(options);
 		
 	}
</script>

<%@ include file="../footer.html"%>