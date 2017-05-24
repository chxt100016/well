<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resources/library/css/semantic.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <script src="${pageContext.request.contextPath}/resources/library/js/My97DatePicker/WdatePicker.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/library/js/semantic.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/library/js/jquery.form.js"></script>
    <style>
        .seven_wid {
            width: 70%
        }
        
        .max_text {
            max-width: 350px;
            color: red
        }
        
        .inactive {
            color:
        }
    </style>
</head>

<body>
    <div class="ui container">
        <div class="ui container">
            <div class="introduction">
                <h1 class="ui header">WellAssist
                    <div class="sub header">
                        维助供应链系统用户注册 </div>
                </h1>
          
                <div class="ui teal progress" id="example3">
                    <div class="bar">
                        <div class="progress"></div>
                    </div>
                    <div class="label left"> <span style="display:block;width:33%;float:left"> 基本信息</span>
                        <span style="display:block;width:33%;float:left"> 企业资质</span>
                        <span style="display:block;width:33%;float:left"> 完成</span>
                    </div>
                </div>

                <div class="ui hidden divider"></div>
            </div>



        </div>
        <!--基本信息页-->
        <div class="ui container segment" id="first_rgpage">
            <form class="ui form segment" id="register_form" style="border:none" enctype="multipart/form-data">
                <h3 class="ui header">企业信息</h3>
                <div class="ui divider"></div>
                <div class="ui form">
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业名称：</div>
                                <input type="text" name="companyname" placeholder="请输入企业名称" id="companyname" class="max_text">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业执照号：</div>
                                <input type="text" name="companyaccount" placeholder="请输入企业执照号" id="companyaccount" class="">
                            </div>
                        </div>

                    </div>

                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业类型：</div>
                                <select class="ui dropdown" name="compkind">
                                    <option value="">请选择</option>
                                    <option value="0">央企</option>
                                    <option value="1">国企</option>
                                    <option value="2">民企</option>
                                    <option value="3">合资</option>
                                    <option value="4">上市公司</option>
                                    <option value="5">公交</option>
                                    <option value="6">城市天然气</option>
                                </select>
                            </div>
                        </div>

                        <div class="field">
                            <div class="two fields">
                                <div class="field">
                                    <div class="ui labeled input ">
                                        <div class="ui label">用户类型：</div>
                                        <select class="ui dropdown" name="user_type" id="user_type" onchange="getUserType();">
                                                <option value="">请选择</option>
                                                <option value="3">物流方</option>
                                                <option value="1">买家方</option>
                                                <option value="0">卖家方</option>
                                                <option value="2">放款方</option>
                                       </select>
                                    </div>
                                </div>
<<<<<<< HEAD
                                <div id ="sellerList" hidden="hidden" >
                                    <div class="caption "><span class="box-in-level2 ui label " style="display: block;float: left; width: 86px;height: 38px;line-height: 30px; font-size: 14px;">联系卖家</span>
                                    <select name="contactcustomer"  id="contactcustomer" class="ui dropdown" style="display: block;float: left;width: 163px;">
=======
                                <div id ="sellerList" hidden="hidden">
                                    <div class="caption"><span class="box-in-level2">联系卖家</span></div>
                                    <div>
                                        <select name="supply_id"  id="supply_id">
>>>>>>> 85840505250e12c306abeaf44ebec320dc8332b0
                                            <option value="0">--请选择卖家--</option>
                                            <c:forEach items="${customerList}" var="item" varStatus="status">
                                                <option value="${item.userId}">${item.userName}</option>
                                            </c:forEach>
                                        </select></div>
                                   
                                        
                                    
                                </div>
                                <div class="field" id="seller_binding" style="display:none">
                                    <div class="ui labeled input ">
                                        <div class="ui label">买家绑定：</div>
                                        <select class="ui dropdown" name="user_type">
                                    <option value="">请选择</option>
                                    <option value="1">物流方</option>
                                     <option value="2">买家方</option>
                                     <option value="3">卖家方</option>
                                     <option value="4">放款方</option>
                                </select>
                                    </div>
                                </div>
                            </div>
                            <!--//买家绑定-->
                        </div>

                    </div>

                    <div class=" fields">
                        <div class="three field">
                            <div class="ui labeled input ">
                                <div class="ui label">地址:</div>
                                <input type="hidden" name="zc_region_id" id="zc_region_id" />
                                <select class="ui dropdown" name="provinceId" id="provinceId" onchange="selRegion(0);">
                                    <option value="">--请选择省--</option>
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

                            </div>
                        </div>
                    </div>
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">具体地址：</div>
                                <input type="text" name="address" placeholder="请输入具体地址" id="address" class="">
                            </div>
                        </div>
                    </div>

                    <h3 class="ui header">账户信息</h3>
                    <div class="ui divider"></div>
                    <div class="ui form">
                        <div class=" two fields">
                            <div class="field">
                                <div class="ui labeled input ">
                                    <div class="ui label">登录账户：</div>
                                    <input type="text" name="user_name" placeholder="请输入登录账户" id="" class="max_text">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui labeled input ">
                                    <div class="ui label">登录密码：</div>
                                    <input type="password" name="password" placeholder="请输入登录密码" id="" class="max_text">
                                </div>
                            </div>
                        </div>
                        <div class=" two fields">
                            <div class="field">
                                <div class="ui labeled input ">
                                    <div class="ui label">确认密码：</div>
                                    <input type="password" name="Confirm_password" placeholder="请确认登录密码" id="" class="max_text">
                                </div>
                            </div>


                            <div class="field">
                                <div class="two fields">
                                    <div class="field">
                                        <div class="ui labeled input ">
                                            <div class="ui label">验证码：</div>
                                            <input type="text" name="Verification_code" placeholder="输入验证码" id="" class="max_text">
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="inline field">
                            <div class="ui checkbox">
                                <input type="checkbox" name="terms">
                                <label>我同意</label>
                            </div>
                        </div>




                    </div>
                    <br>
                    <a id="next_step" class="ui primary" onclick="next()">Next</a>
                </div>
        
        <!--基本信息页end-->

        <!--<资质信息-->
        
                <h3 class="ui header">联系方式</h3>
                <div class="ui divider"></div>
                <div class="ui form">
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">联系人：</div>
                                <input type="text" name="contact" placeholder="" id="" class="max_text" onkeyup="progress(50)">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">联系邮箱：</div>
                                <input type="email" name="contactemail" placeholder="" id="" class="">
                            </div>
                        </div>

                    </div>
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">手机号码：</div>
                                <input type="text" name="contactphone" placeholder="" id="" class="max_text">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">座机号码：</div>
                                <input type="text" name="contactseat" placeholder="" id="" class="">
                            </div>
                        </div>

                    </div>
                    <h3 class="ui header">资质信息</h3>
                    <div class="ui divider"></div>
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业法人身份证号：</div>
                                <input type="text" name="legalIdCard" placeholder="" id="" class="max_text" onkeyup="progress(80)">
                            </div>
                        </div>
                    </div>
                    <div class="inline field">
                        <label>请上传大小在5M以内的JPG.PNG.GIF格式的电子版</label>
                    </div>
                    <div class=" four fields">
                        <%--<div class="field">
                            <div class="ui card">
                                <div class="image">
                                    <img src="http://www.semantic-ui.cn/images/avatar2/large/kristy.png">
                                </div>
                                <div class="content">
                                    <a class="header">营业执照</a>
                                    <div class="meta">
                                        <span class="date">请上传营业执照</span>
                                    </div>
                                    <div class="item " style="float:right"> <a class="" style="font-size:2em"><i class="cloud upload icon" ></i></a> </div>
                                </div>

                            </div>
                        </div>--%>
                        <%--<div class="field">
                            <div class="ui card">
                                <div class="image">
                                    <img src="http://www.semantic-ui.cn/images/avatar2/large/kristy.png">
                                </div>
                                <div class="content">
                                    <a class="header">开户许可证</a>
                                    <div class="meta">
                                        <span class="date">请上传开户许可证</span>
                                    </div>
                                    <div class="item " style="float:right"> <a class="" style="font-size:2em"><i class="cloud upload icon" ></i></a> </div>
                                </div>

                            </div>
                        </div>--%>
                        <%--<div class="field">
                            <div class="ui card">
                                <div class="image">
                                    <img src="http://www.semantic-ui.cn/images/avatar2/large/kristy.png">
                                </div>
                                <div class="content">
                                    <a class="header">公司章程</a>
                                    <div class="meta">
                                        <span class="date">请上传公司章程</span>
                                    </div>
                                    <div class="item " style="float:right"> <a class="" style="font-size:2em"><i class="cloud upload icon" ></i></a> </div>
                                </div>

                            </div>
                        </div>
                        <div class="field">
                            <div class="ui card">
                                <div class="image">
                                    <img src="http://www.semantic-ui.cn/images/avatar2/large/kristy.png">
                                </div>
                                <div class="content">
                                    <a class="header">法人身份证</a>
                                    <div class="meta">
                                        <span class="date">请上传法人身份证</span>
                                    </div>
                                    <div class="item " style="float:right"> <a class="" style="font-size:2em"><i class="cloud upload icon" ></i></a> </div>
                                </div>

                            </div>
                        </div>--%>
                            <div class="zizhixinxi field" >
                                 <div class="ui card">
                                <div class="zizhititle">企业图片</div>
                                <div>格式: JPG, PNG, GIF</div>
                                <div>大小: 小于 5M</div>
                                
                                <input type="file" id="yingye_img4" name="yingye_img4_src" class="fileManage ui labeled input"  />
                                <input type="hidden" name="yingye_img4" />
                                <img id="yingye_imgpath4" class="yingyeimg" style="width:100%;height:230px" src=""  />
                                </div>
                            </div>
                            <div class="zizhixinxi field">
                                 <div class="ui card">
                                <div class="zizhititle">营业执照</div>
                                <div>格式: JPG, PNG, GIF</div>
                                <div>大小: 小于 5M</div>
                                <input type="file" id="yingye_img1" name="yingye_img1_src"  class="fileManage"  />
                                <input type="hidden" name="yingye_img1" />
                                <img id="yingye_imgpath1" class="yingyeimg" style="width:100%; height:230px" src="" />
                                
                                 </div>
                            </div>
                            <div class="zizhixinxi field" >
                               <div class="ui card">
                                <div class="zizhititle">营业许可证</div>
                                <div>格式: JPG, PNG, GIF</div>
                                <div>大小: 小于 5M</div>
                                <input type="hidden" name="yingye_img2"  class="fileManage"  />
                                <input type="file" id="yingye_img2" name="yingye_img2_src"  class="fileManage" />
                                <img id="yingye_imgpath2" class="yingyeimg" style="width:100%; height:230px" src="" />
                               
                                </div>
                            </div>
                            <div class="zizhixinxi field" >
                                 <div class="ui card">
                                <div class="zizhititle">特许经营许可证</div>
                                <div>格式: JPG, PNG, GIF</div>
                                <div>大小: 小于 5M</div>
                                <input type="hidden" name="yingye_img3" />
                                <input type="file" id="yingye_img3" name="yingye_img3_src" class="fileManage"  />
                                <img id="yingye_imgpath3" class="yingyeimg" style="width:100%;height:230px" src="" />
                            
                                </div>
                            </div>
                    </div>
                </div>
            <br><br>

             <div style="align:center"> <button id="submit" class="ui primary submit button" style="width:100px;margin:0 auto;display:block;">提交</button> </div>
            </form>

        </div>
        <!--资质信息end-->
    </div>
</body>
<script>
    function next() {
      $('body,html').animate({scrollTop:1300},1000);

    }
    function progress(index){
        $('#example3').progress({
            percent: index,
            text: {
                ratio: '...'
            }
        });
    }

    function getUserType(){
        var userType = $("#user_type").val();
        if(userType==1){
            $("#sellerList").show();
        }else
        {
            $("#sellerList").hide();
        }

    }

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
    /**
     * 上传图片处理方法
     * @param idx
     */
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
                <%--$("#yingye_imgpath"+idx).attr("src","${pageContext.request.contextPath}/" + data.path);--%>
                $("#yingye_imgpath"+idx).attr("src",  data.path);
                $("#yingye_imgpath"+idx).show();
                console.log(data.path);
                return;
                //-----------
            },
            error : function(data) {
                //alert(data);
            }
        }
        $("#register_form").ajaxSubmit(options);
    }

    function selRegion(type){
        var regionId = '';

        if(type==0){
            regionId = $("#provinceId").val();
        } else if(type==1){
            regionId = $("#cityId").val();
        } else if(type==2){
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

    function binding() {

    };


    $(function() {
        var registerForm = $('#register_form');
        $('#register_form').form({
            fields: {
                companyname: {
                    identifier: 'companyname',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter your Enterprise name'
                    }]
                },
                companyaccount: {
                    identifier: 'companyaccount',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter Enterprise number'
                    }]
                },
                compkind: {
                    identifier: 'compkind',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please select a Enterprise Type'
                    }]
                },
                address: {
                    identifier: 'address',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please select a address'
                    }]
                },
                Province: {
                    identifier: 'Province',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter Province'
                    }]
                },

                user_type: {
                    identifier: 'user_type',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please select a User Type'
                    }]
                },

                user_name: {
                    identifier: 'user_name',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please select a User ID'
                    }]
                },
                password: {
                    identifier: 'password',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter a password'
                    }, {
                        type: 'minLength[6]',
                        prompt: 'Your password must be at least 6 characters'
                    }]
                },
                Confirm_password: {
                    identifier: 'Confirm_password',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter a password'
                    }, {
                        type: 'match[password]',
                        prompt: 'different',
                    }]
                },
                Verification_code: {
                    identifier: 'Verification_code',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter Verification_code'
                    }]
                },

                terms: {
                    identifier: 'terms',
                    rules: [{
                        type: 'checked',
                        prompt: 'You must agree to the terms and conditions'
                    }]
                },
                contact: {
                    identifier: 'contact',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter your Contact name'
                    }]
                },
                contactemail: {
                    identifier: 'contactemail',
                    rules: [{
                        type: 'email',
                        prompt: 'Please enter Contact email'
                    }]
                },
                contactphone: {
                    identifier: 'contactphone',
                    rules: [{
                        type: 'regExp',
                        value: /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/,
                        prompt: 'Please enter Contact tel'
                    }]
                },
                contactseat: {
                    identifier: 'contactseat',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入电话'
                    }, {
                        type: 'number',
                        prompt: '请输入正确电话'
                    }]
                },

                legalIdCard: {
                    identifier: 'legalIdCard',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入身份证'
                    }, {
                        type: 'maxLength[18]',
                        prompt: '请输入身份证号码'
                    }]
                },

                yingye_img4 :{
                    identifier: 'yingye_img4',
                     rules: [{
                        type: 'empty',
                        prompt: '请上传企业头像'
                    }]
                },
                 yingye_img3 :{
                    identifier: 'yingye_img3',
                     rules: [{
                        type: 'empty',
                        prompt: '请上传图片'
                    }]
                },
                 yingye_img2 :{
                    identifier: 'yingye_img2',
                     rules: [{
                        type: 'empty',
                        prompt: '请上传图片'
                    }]
                },
                 yingye_img1 :{
                    identifier: 'yingye_img1',
                     rules: [{
                        type: 'empty',
                        prompt: '请上传图片'
                    }]
                },

                //   ...
            },
            inline: true,
            on: 'blur',
            onSuccess: function(e) {
                //阻止表单的提交
                //console.log("onSuccess");
                var allFields1 = registerForm.form('get values');
                var allFields2=registerForm.serialize();
                console.log(allFields1);
                console.log(allFields2);
                e.preventDefault();
                //next();
                var flag = false;
                var companyname =$("input[name='companyname']").val();
                $.post("${pageContext.request.contextPath}/front/sender/SenderLoginController-onCheckCompanyName", {companyname:companyname},	function(data) {
                    if(data.state == 1) {
                        flag = true;
                    }else{
                        alert(data.content);
                        return false;
                    }
                    if(flag){
                        var contactemail =$("input[name='contactemail']").val();
                        var contactphone =$("input[name='contactphone']").val();
                        $.post("${pageContext.request.contextPath}/front/sender/SenderLoginController-onCheckMobileEmail", {contactphone:contactphone,contactemail:contactemail},	function(data) {
                            if(data.state == -3 || data.state == -4) {
                                alert(data.content);
                                return false;
                            }else{
                                registerForm.attr("action", "<c:url value="/register/register"/>");
                                $.post(registerForm.attr("action"),registerForm.serialize(),function(data){
                                    data = $.parseJSON(data);
                                    if(data.state==1){
                                        window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerLoginCtrl-registerNext";
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
            }

        });

    })
</script>

</html>