<%@ include file="../header.jsp"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <link rel="stylesheet" href="<c:url value="/resources/wella/front/css/seller/publishpage.css"/>">
<script src="${pageContext.request.contextPath}/statics/libs/ajaxupload.js"></script>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
        <div id="app" style="width:90%;">
            <h4 class="ui header" style="font-size:15px;font-weight:600;">编辑产品</h4>
            <div class="ui divider"></div>
            <form id="product-publish" method="post" class="ui form">
                <table>
                    <tbody>
                        <tr>
                            <td class="form_label"><label for="">产品名称：</label></td>
                            <td>
                                <input type="text" placeholder="请填写您的产品名称" class="" name="prodName" value="${prod.prodName}">
                            </td>
                            <input type="hidden" name="prodId" value="${prod.prodId}">
                            
                            <td class="form_label"><label for="">货源类型：</label></td>
                            <td>
                                <select class="form-control" name="prodType">
                                    <option>请选择你的货源分类</option>
                                    <option value="2" <c:if test="${prod.prodType==2}" >selected</c:if>>管道气</option>
                                    <option value="1" <c:if test="${prod.prodType==1}" >selected</c:if>>原油</option>
                                    <option value="0" <c:if test="${prod.prodType==0}" >selected</c:if>>天然气</option>
                                </select>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="form_label"><label for="">供应量：</label></td>
                            <td>
                                <span style="display: table-cell;vertical-align: middle;"><input type="text" placeholder="请填写供应量" class="" name="prodNum" value="${prod.prodNum}"></span>
                                <span style="display: table-cell;vertical-align: middle;">&emsp;吨</span>
                            </td>
                            <td class="form_label"><label for="" class="form_label">单价：</label></td>
                            <td><input type="text" placeholder="请填写产品单价" class="" name="prodPrice" value="${prod.prodPrice}"> </td>
                            <td>&emsp;元/吨</td>
                        </tr>
                        <tr>
                            <td class="form_label"><label for="" class="form_label">所在地区：</label></td>

                            <input type="hidden" name="prodRegionId" id="prodRegionId" value="${prod.prodRegionId}">
                            <td colspan="3">
                                <select id="provinceId" name="provinceId" onchange="selRegion(0);" style="width:132px;float:left;margin-right:3%;">

                                    <option>--请选择省--</option>
                                    <c:forEach items="${provinceList}" var="item" varStatus="status">
                                        <option value="${item.regionId}" <c:if test="${item.regionId==provinceId}" >selected</c:if>>${item.regionName}</option>
                                    </c:forEach>
						        </select>
                                <select id="cityId" name="cityId" onchange="selRegion(1);"  style="width:132px;float:left;margin-right:3%;">
                                    <option>--请选择市--</option>
                                    <c:forEach items="${cityList}" var="item" varStatus="status">
                                        <option value="${item.regionId}"  <c:if test="${item.regionId==cityId}" >selected</c:if>  >${item.regionName}</option>
                                    </c:forEach>
                                </select>
                                <select id="regionId" name="regionId" onchange="selRegion(2);"  style="width:132px;float:left">
                                    <option>--请选择区--</option>
                                    <c:forEach items="${countyList}" var="item" varStatus="status">
                                        <option value="${item.regionId}"  <c:if test="${item.regionId==prod.get('prodRegionId')}" >selected</c:if> >${item.regionName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="" class="form_label"></label>详细地址：</td>
                            <td colspan="2"><input type="text" placeholder="请填写具体地址" class="" name="prodRegionAddr" value="${prod.prodRegionAddr}"></td>
                            
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="form_label"><label for="">联系人：</label></td>
                            <td><input type="text" placeholder="请填写该产品联系人的姓名" class="" name="prodLxr" value="${prod.prodLxr}"> </td>
                            
                            <td class="form_label"><label for="">联系电话：</label></td>
                            <td><input type="text" placeholder="请填写该联系人电话" class="" name="prodLxrPhone" value="${prod.prodLxrPhone}"> </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="form_label"><label for="">产品图片：</label></td>
                            <td>

                                <img id="upload2" class="yingyeimg" style="width:270PX;height:230px;border:1px solid #adadad" src="${prod.prodImg}" name="prodImg" />
                            </td>
                            <td>
                                <input type="hidden" name="prodImg" value="${prod.prodImg}"/><br>
                                <%--<input type="file" id="prodImg" name="prodImg_src" value="${prod.prodImg_src}" class="fileManage" />--%>
                                <div  >（点击图片更换）</div>
                            </td>
                        </tr>
                        <br>
                        <tr>
                            <td class="form_label">
                                <label for="">产品详情：</label>
                            </td>
                            <td  colspan="3">
                                <div id="editor">
                                    <p>${prod.prodIntro}</p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <%--<td><button class="ui primary button">提交</button>&emsp;<a class="ui button" href="#" role="button" type="reset">返回</a></td>
                            <td></td>--%>
                            <td><button class="ui button primary" type="submit">修改</button>&emsp;<a class="ui button" href="${pageContext.request.contextPath}/seller/productList" role="button"  type="reset">返回</a></td>
                            
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <!-- <div class="row">
                    <div style="width:300px;margin:0px auto">
                        <input type="reset" name="reset" style="display: none;" />
                        <button class="btn btn-info" style="width:120px;float:left;display:block">发布</button>
                        <a class="btn btn-default" href="#" role="button" style="width:120px;float:right;display:block" type="reset">返回</a>
                    </div>
                </div> -->
            </form>
        </div>

        </div>
      </div>
        </div>

        <script>
            $(document).ready(function () {
                new AjaxUpload('#upload2', {
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
                        $("input[name='prodImg']").val(data.path);
                        $("#upload2").prop("src", data.path);
                        $("#prodImgpath").show();
                        return;
                    }
                });
            });

            function selRegion(type) {
                var regionId = '';

                if (type == 0) {
                    regionId = $("#provinceId").val();
                } else if (type == 1) {
                    regionId = $("#cityId").val();
                } else if (type == 2) {
                    regionId = $("#regionId").val();
                } else return;
                $("#prodRegionId").val(regionId);
                if (regionId != '') {
                    $.post("${pageContext.request.contextPath}/front/sender/SenderLoginController-getChildRegionListInSite", { regionId: regionId }, function (data) {
                        if (data.state == 1) {
                            html = "";
                            for (var i = 0; i < data.regionList.length; i++) {
                                region = data.regionList[i];
                                html += "<option value='" + region.regionId + "'>" + region.regionName + "</option>";
                            }

                            if (type == 0) {
                                $("#cityId").html("<option>--请选择市--</option>" + html);
                                $("#regionId").html("<option>--请选择区--</option>");
                            } else if (type == 1) {
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
                    prodType: { required: true },
                    prodNum: {
                        required:true,
                        number:true,
                        range:[1,500]
                        },
                    prodPrice: { required: true },
                    regionId: "required",
                    prodRegionAddr: { required: true },
                    prodLxr: { required: true },
                    prodLxrPhone: { required: true },
                    prodImg: "required",
                    prodIntro: "required"
                },
                messages: {
                    prodName: "请输入产品名称",
                    userId: "请选择卖家",
                    prodType: "请选择产品类型",
                    prodNum: {
                        required:"请输入产品供应量",
                        number:"请输入数字",
                        range:"产品供应链应在1-500顿之间"
                        },
                    prodPrice: "请输入产品单价（元/顿）",
                    prodRegionId: "请选择产品区域",
                    prodRegionAddr: "请输入产品详细地址",
                    prodLxr: "请输入产品联系人",
                    prodLxrPhone: "请输入产品联系人电话号码",
                    prodImg: "请上传产品图片",
                    prodIntro: "请填写产品简介"
                },
                submitHandler: function (form) {
                    var data=$(form).serialize();
                    data=data+"&prodIntro="+editor.txt.html();
                    $.post("${pageContext.request.contextPath}/seller/updateproduct",data,function (data) {
                        var obj = JSON.parse(data);
                        var code = obj.code;
                        if(code == 0){
                            alert("修改成功");
                            window.location.href = "${pageContext.request.contextPath}/seller/productList";
                        }else {
                            alert("修改失败");
                        }
                    });
                }
            });

        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/library/js/jquery.form.js"></script>
        <script type="text/javascript" src="https://unpkg.com/wangeditor@3.0.2/release/wangEditor.min.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 或者 var editor = new E( document.getElementById('#editor') )
        editor.create()
    </script>

        <%@ include file="../footer.html"%>