﻿<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<link rel="stylesheet" 	href="<c:url value="/resources/wella/front/css/seller/publishpage.css"/>">

<div class="mid_box" id="app">
	<form id="product-publish" method="post" >

		<table>
			<tbody>
			<tr>
				<td><input type="hidden" name="prodId" value="${prod.prodId}"></td>
				<td class="form_label"><label for="">产品名称：</label></td>
				<td><input type="text" placeholder="请填写您的产品名称" class="form-control" name="prodName" value="${prod.prodName}"></td>
			</tr>
			<tr>
				<td class="form_label"><label for="">货源类型：</label></td>
				<td>
					<select class="form-control" name="prodType">
						<option value="2">管道气</option>
						<option value="1">原油</option>
						<option value="0">天然气</option>
					</select>
				</td>
			</tr>

			<tr>
				<td class="form_label"><label for="">供应量：</label></td>
				<td><input type="text" placeholder="请填写供应量" class="form-control" name="prodNum" value="${prod.prodNum}"> </td>
				<td>吨</td>
			</tr>
			<tr>
				<td class="form_label"><label for="" class="form_label">单价：</label></td>
				<td><input type="text" placeholder="请填写产品单价" class="form-control" name="prodPrice" value="${prod.prodPrice}"> </td>
				<td>元/吨</td>
			</tr>
			<tr>
				<td><label for="" class="form_label">地址：</label></td>
				<td>
					<input type="hidden" name="prodRegionId" id="prodRegionId" value="${prod.prodRegionId}">
					<select class="form-control" style="width:100px;float:left" alt="..." onclick="selRegion(0)" name="provinceId" id="provinceId">
						<option>--请选择省--</option>
						<c:forEach items="${provinceList}" var="item" varStatus="status">
							<option value="${item.regionId}" <c:if test="${item.regionId==provinceId}" >selected</c:if> >${item.regionName}</option>
						</c:forEach>


					</select>
					<select class="form-control" style="width:100px; float:left" onclick="selRegion(1)" id="cityId" name="cityId">
						<!--<option value="">&#45;&#45;请选择市&#45;&#45;</option>-->
						<option>--请选择市--</option>
						<c:forEach items="${cityList}" var="item" varStatus="status">
							<option value="${item.regionId}"  <c:if test="${item.regionId==cityId}" >selected</c:if>  >${item.regionName}</option>
						</c:forEach>
					</select>
					<select class="form-control" style="width:100px; float:left" onclick="selRegion(2)" id="regionId" name="regionId">
						<!--<option>&#45;&#45;请选择区&#45;&#45;</option>-->
						<option>请选择区</option>
						<c:forEach items="${countyList}" var="item" varStatus="status">
						<option value="${item.regionId}"  <c:if test="${item.regionId==prod.get('prodRegionId')}" >selected</c:if> >${item.regionName}</option>
						</c:forEach>
					</select>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<label for="" class="form_label"></label></td>
				</td>
				<td><input type="text" placeholder="请填写具体地址" class="form-control" name="prodRegionAddr" value="${prod.prodRegionAddr}"></td>
			</tr>
			<tr>
				<td class="form_label"><label for="">联系人：</label></td>
				<td><input type="text" placeholder="请填写该产品联系人的姓名" class="form-control" name="prodLxr" value="${prod.prodLxr}"> </td>
				<td></td>
			</tr>
			<tr>
				<td class="form_label"><label for="">联系电话：</label></td>
				<td><input type="text" placeholder="请填写该联系人电话" class="form-control" name="prodLxrPhone" value="${prod.prodLxrPhone}"> </td>
				<td></td>
			</tr>
			<tr>
				<td class="form_label"><label for="">产品图片：</label></td>
				<td>
					<img id="prodImgpath" class="yingyeimg" style="width:270PX;height:230px" src="${prod.prodImg}" name="prodImg"/>
				</td>
				<td>
					<input type="hidden" name="prodImg" value="${prod.prodImg}"/>
					<input type="file" id="prodImg" name="prodImg_src" value="${prod.prodImg_src}" class="fileManage" />
				</td>
			</tr>
			<br>
			<tr>
				<td class="form_label">
					<label for="">产品详情：</label>
				</td>
				<td row-span="3"><textarea name="prodIntro"  id="" cols="30" rows="10" style="width:270px;max-width:270px">${prod.prodIntro}</textarea></td>
			</tr>
			</tbody>
		</table>
		<div class="row">
			<div style="width:300px;margin:0px auto">
				<input type="reset" name="reset" style="display: none;" />
				<button class="btn btn-info" style="width:120px;float:left;display:block" type="submit">修改</button>
				<a class="btn btn-default" href="${pageContext.request.contextPath}/seller/productList" role="button" style="width:120px;float:right;display:block" type="reset">返回</a>
			</div>
		</div>
	</form>
</div>

<script>
    function clearFileName() {
        $("input[type='file']").each(function() {
            $(this).attr("name", "");
        });
    }
    /**
     * 上传图片处理方法
     * @param idx
     */
    function uploadImage() {
        var options = {
            url: "${pageContext.request.contextPath}/uploadFile",
            type: "POST",
            dataType: "json",
            data: {},
            success: function(data) {
                if (data.result == "-10") {
                    ShowWindowAlert("提示", data.msg, "", "确 定", "");
                    return;
                }
                $("input[name='prodImg']").val(data.path);
                $("#prodImgpath").attr("src", data.path);
                $("#prodImgpath").show();
                console.log(data.path);
                return;
            },
            error: function(data) {
                //alert(data);
            }
        }
        $("#product-publish").ajaxSubmit(options);
    }

    $("#prodImg").change(function(){
        clearFileName();
        $(this).attr("name", "file");
        uploadImage();
    });

    function selRegion(type){
        var regionId = '';

        if(type==0){
            regionId = $("#provinceId").val();
        } else if(type==1){
            regionId = $("#cityId").val();
        } else if(type==2){
            regionId = $("#regionId").val();
        } else return;
        $("#prodRegionId").val(regionId);
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

    $("#product-publish").validate({
        rules: {
            prodName: "required",
            userId: "required",
            prodType: {required: true},
            prodNum: "required",
            prodPrice: {required: true},
            regionId: "required",
            prodRegionAddr: {required: true},
            prodLxr: {required: true},
            prodLxrPhone: {required: true},
            prodImg: "required",
            prodIntro: "required"
        },
        messages: {
            prodName: "请输入产品名称",
            userId: "请选择卖家",
            prodType: "请选择产品类型",
            prodNum: "请输入产品供应量",
            prodPrice: "请输入产品单价（元/顿）",
            prodRegionId: "请选择产品区域",
            prodRegionAddr: "请输入产品详细地址",
            prodLxr: "请输入产品联系人",
            prodLxrPhone: "请输入产品联系人电话号码",
            prodImg: "请上传产品图片",
            prodIntro: "请填写产品简介"
        },
        submitHandler: function(form){
            $.post("${pageContext.request.contextPath}/seller/updateproduct",$(form).serialize(),function (data) {
                var obj = JSON.parse(data);
                var code = obj.code;
                if(code == 0){
                    alert("修改成功");
                    window.location.href = "${pageContext.request.contextPath}/seller/productList";
                }else {
                    alert("修改失败");
                }
            })
        }
    });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/library/js/jquery.form.js"></script>
<%@ include file="../footer.html"%>