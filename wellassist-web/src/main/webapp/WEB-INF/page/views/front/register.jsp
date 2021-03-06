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
        
        /*.inactive {
            color:
        }*/
        .ui.form .field .prompt.label{
            width: 300px;
            text-align: center;
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
                    <div class="label left">
                        <span style="display:block;width:33%;float:left"> 基本信息</span>
                        <span style="display:block;width:33%;float:left"> 企业资质</span>
                        <span style="display:block;width:33%;float:left"> 完成</span>
                    </div>
                </div>

                <div class="ui hidden divider"></div>
            </div>



        </div>
        <!--基本信息页-->
        <div class="ui container segment" id="first_rgpage">
            <form class="ui form segment" id="register_form" name="form1" style="border:none" enctype="multipart/form-data">
                <h3 class="ui header">企业信息</h3>
                <div class="ui divider"></div>
                <div class="ui form">
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业名称：</div>
                                <input type="text" name="companyname" placeholder="请输入企业名称" id="companyname" class="max_text focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业执照号：</div>
                                <input type="text" name="companyaccount" placeholder="请输入企业执照号" id="companyaccount" class="focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>

                    </div>

                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业类型：</div>
                                <select class="ui dropdown focus" name="compkind" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
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
                                        <select class="ui dropdown focus" name="user_type" id="user_type" onchange="getUserType();" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                            <option value="">请选择</option>
                                            <option value="0">卖家方</option>
                                            <option value="1">买家方</option>
                                            <option value="2">放款方</option>
                                            <option value="3">物流方</option>
                                       </select>
                                    </div>
                                </div>
                                <div class="field">
                                    <div id ="sellerList" hidden="hidden">
                                        <div class="ui labeled input ">
                                            <div class=" ui label">联系卖家:</div>
                                            <select name="supply_id"  id="supply_id" class="ui dropdown focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                                <option value="0">--请选择卖家--</option>
                                                <c:forEach items="${customerList}" var="item" varStatus="status">
                                                    <option value="${item.userId}">${item.userName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <!-- <div class="field" id="seller_binding" style="display:none">
                                    <div class="ui labeled input ">
                                        <div class="ui label">买家绑定：</div>
                                        <select class="ui dropdown focus" name="user_type" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                            <option value="">请选择</option>
                                            <option value="1">物流方</option>
                                            <option value="2">买家方</option>
                                            <option value="3">卖家方</option>
                                            <option value="4">放款方</option>
                                        </select>
                                    </div>
                                </div> -->
                            </div>
                            <!--//买家绑定-->
                        </div>

                    </div>

                    <div class=" fields">
                        <div class="three field">
                            <div class="ui labeled input ">
                                <div class="ui label">地址:</div>
                                <input type="hidden" name="zc_region_id" id="zc_region_id" />
                                <select class="ui dropdown focus" name="provinceId" id="provinceId" onchange="selRegion(0);"  onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                    <option value="">--请选择省--</option>
                                    <c:forEach items="${provinceList}" var="item" varStatus="status">
                                        <option value="${item.regionId}">${item.regionName}</option>
                                    </c:forEach>
                                </select>
                                <select class="focus" id="cityId" name="cityId" onchange="selRegion(1);" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                    <option>--请选择市--</option>
                                </select>
                                <select class="focus" id="regionId" name="regionId" onchange="selRegion(2);" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                    <option>--请选择区--</option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">具体地址：</div>
                                <input type="text" name="address" placeholder="请输入具体地址" id="address" class="focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>
                    </div>
                    
                    <div class="ui divider"></div>
<!--企业信息end-->
<br>
                    <h3 class="ui header">账户信息</h3>
                    <div class="ui divider"></div>
                    <div class="ui form">
                        <div class=" two fields">
                            <div class="field">
                                <div class="ui labeled input ">
                                    <div class="ui label">登录账户：</div>
                                    <input type="text"  autocomplete="off"  name="user_name" placeholder="请输入登录账户" id="" class="max_text focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui labeled input ">
                                    <div class="ui label">登录密码：</div>
                                    <input type="password"  autocomplete="off" name="password" placeholder="请输入登录密码" id="" class="max_text focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                </div>
                            </div>
                        </div>
                        <div class=" two fields">
                            <div class="field">
                                <div class="ui labeled input ">
                                    <div class="ui label">确认密码：</div>
                                    <input type="password"  autocomplete="off" name="Confirm_password" placeholder="请确认登录密码" id="" class="max_text focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                                </div>
                            </div>


                            <%--<div class="field">
                                <div class="two fields">
                                    <div class="field">
                                        <div class="ui labeled input ">
                                            <div class="ui label">验证码：</div>
                                            <input type="text" name="Verification_code" placeholder="输入验证码" id="" class="max_text">
                                        </div>
                                    </div>
                                </div>
                            </div>--%>


                        </div>
                        




                    </div>
                    <br>
                    <!--<a id="next_step" class="ui primary" onclick="next()">Next</a>-->
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
                                <input type="text" name="contact" placeholder="" id="" class="max_text focus" onkeyup="progress(50)" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">联系邮箱：</div>
                                <input type="email" name="contactemail" placeholder="" id="" class="focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>

                    </div>
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">手机号码：</div>
                                <input type="text" name="contactphone" placeholder="" id="contactphone" class="max_text focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">座机号码：</div>
                                <input type="text" name="contactseat" placeholder="" id="" class="focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
                            </div>
                        </div>

                    </div>
                    <br><br>
                    <h3 class="ui header">资质信息</h3>
                    <div class="ui divider"></div>
                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input ">
                                <div class="ui label">企业法人身份证号：</div>
                                <input type="text" name="legalIdCard" placeholder="" id="" class="max_text focus" onkeyup="progress(80)" onkeypress="if(event.keyCode==13) focusNextInput(this,event);">
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
                                <input type="file" id="yingye_img4" name="yingye_img4_src" class="fileManage ui labeled input focus imgInput" onkeypress="if(event.keyCode==13) focusNextInput(this,event);" />
                                <input type="hidden" name="yingye_img4" />
                                <img id="yingye_imgpath4" class="yingyeimg" style="width:100%;height:230px" src=""  />
                                </div>
                            </div>
                            <div class="zizhixinxi field">
                                 <div class="ui card">
                                <div class="zizhititle">营业执照</div>
                                <div>格式: JPG, PNG, GIF</div>
                                <div>大小: 小于 5M</div>
                                <input type="file" id="yingye_img1" name="yingye_img1_src"  class="fileManage focus imgInput"  onkeypress="if(event.keyCode==13) focusNextInput(this,event);" />
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
                                <input type="file" id="yingye_img2" name="yingye_img2_src"  class="fileManage focus imgInput"  onkeypress="if(event.keyCode==13) focusNextInput(this,event);"/>
                                <img id="yingye_imgpath2" class="yingyeimg" style="width:100%; height:230px" src="" />
                                </div>
                            </div>
                            <div class="zizhixinxi field" >
                                 <div class="ui card">
                                <div class="zizhititle">特许经营许可证</div>
                                <div>格式: JPG, PNG, GIF</div>
                                <div>大小: 小于 5M</div>
                                <input type="hidden" name="yingye_img3" />
                                <input type="file" id="yingye_img3" name="yingye_img3_src" class="fileManage focus imgInput"  onkeypress="if(event.keyCode==13) focusNextInput(this,event);" />
                                <img id="yingye_imgpath3" class="yingyeimg" style="width:100%;height:230px" src="" />
                                </div>
                            </div>
                       
                    </div>
                     <%--<div class="inline field">
                            <div class="ui checkbox">
                                <input type="checkbox" name="terms">
                                <label>我同意</label>
                            </div>
                        </div>--%>
                </div>
            <br><br>

             <div style="align:center"> <button id="submit" class="ui primary button focus" onkeypress="if(event.keyCode==13) focusNextInput(this,event);" style="width:100px;margin:0 auto;display:block;">提交</button> </div>
            </form>

        </div>
        <!--资质信息end-->
    </div>
</body>
<script>
    function focusNextInput(thisInput,e){
        if (!$(thisInput).hasClass("imgInput")) {
            e.preventDefault();
        }
        
        var inputs = document.getElementsByClassName("focus");
        for(var i = 0;i<inputs.length;i++){
            //如果是最后一个，则需判断验证错误个数
            if(i==(inputs.length-1)){
                $("form[name='form1']").submit(); // 提交表单 
            }else if(thisInput == inputs[i]){
                if (i==3) {
                    var userType = $("#user_type").val();
                    if (userType == 1) {
                        inputs[i+1].focus()||inputs[i+1].select();
                    }
                    else{
                        inputs[i+2].focus()||inputs[i+2].select();
                    }
                }
                else{
                    inputs[i+1].focus()||inputs[i+1].select();
                }
                break;
            }
        }
    }  
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
    /*
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
            $.post("${pageContext.request.contextPath}/region/getChildRegionListInSite", {regionId:regionId},	function(data) {
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
        $.fn.form.settings.rules.isPhoneRegistered = function(contactphone) {
            var valid = false;
            $.ajax({
                url: '${pageContext.request.contextPath}/register/checkPhone',
                data: {contactphone: contactphone},
                type: 'GET',
                async: false, // 同步 AJAX 请求
                dataType:    'json',
                contentType: 'application/json;charset=utf-8'
            })
                .done(function(data) {
                    valid = data.result;
                });
            return valid;
        };
        $.fn.form.settings.rules.isEmailRegistered = function(contactemail) {
            var valid = false;
            $.ajax({
                url: '${pageContext.request.contextPath}/register/checkEmail',
                data: {contactemail: contactemail},
                type: 'GET',
                async: false, // 同步 AJAX 请求
                dataType:    'json',
                contentType: 'application/json;charset=utf-8'
            })
                .done(function(data) {
                    valid = data.result;
                });
            return valid;
        };
        $.fn.form.settings.rules.isCompanyNameRegistered = function(companyname) {
            var valid = false;
            $.ajax({
                url: '${pageContext.request.contextPath}/register/checkCompanyName',
                data: {companyName: companyname},
                type: 'GET',
                async: false, // 同步 AJAX 请求
                dataType:    'json',
                contentType: 'application/json;charset=utf-8'
            })
                .done(function(data) {
                    valid = data.result;
                });
            return valid;
        };
        $.fn.form.settings.rules.isUserAccountRegistered = function(user_name) {
            var valid = false;
            $.ajax({
                url: '${pageContext.request.contextPath}/register/checkUserAccount',
                data: {userAccount: user_name},
                type: 'GET',
                async: false, // 同步 AJAX 请求
                dataType:    'json',
                contentType: 'application/json;charset=utf-8'
            })
                .done(function(data) {
                    valid = data.result;
                });
            return valid;
        };
        var registerForm = $('#register_form');
        $('#register_form').form({
            fields: {
                companyname: {
                    identifier: 'companyname',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入企业名称！'
                    },
                    {
                        type: 'maxLength[18]',
                        prompt: '长度不得多于18位'
                    },{
                        type:'isCompanyNameRegistered',
                        prompt:'企业名称已遭到使用！'
                    }
                    ]
                },
                companyaccount: {
                    identifier: 'companyaccount',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入企业执照号！'
                    },{
                        type: 'maxLength[18]',
                        prompt: '长度不得多于18位'
                    },
                    ]
                },
                compkind: {
                    identifier: 'compkind',
                    rules: [{
                        type: 'empty',
                        prompt: '请选择企业类型！'
                    },
                    {
                        type: 'maxLength[18]',
                        prompt: '长度不得多于18位'
                    },]
                },
                address: {
                    identifier: 'address',
                    rules: [{
                        type: 'empty',
                        prompt: '请选择企业省市地址！'
                    }]
                },
                Province: {
                    identifier: 'Province',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入详细地址'
                    },
                    {
                        type: 'maxLength[18]',
                        prompt: '长度不得多于18位'
                    },]
                },

                user_type: {
                    identifier: 'user_type',
                    rules: [{
                        type: 'empty',
                        prompt: '请选择用户类型'
                    }]
                },

                user_name: {
                    identifier: 'user_name',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入用户名称'
                    },
                    {
                        type: 'maxLength[18]',
                        prompt: '长度不得多于18位'
                    },
                    {
                        type:'isUserAccountRegistered',
                        prompt:'用户名称已遭到使用！'
                    }]
                },
                password: {
                    identifier: 'password',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入密码'
                    }, {
                        type: 'minLength[6]',
                        prompt: '密码不得低于6位'
                    },
                     {
                        type: 'maxLength[18]',
                        prompt: '密码不得多于18位'
                    },
                    ]
                },
                Confirm_password: {
                    identifier: 'Confirm_password',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入密码'
                    }, {
                        type: 'match[password]',
                        prompt: '两次输入的密码不一致！',
                    }]
                },
               /* Verification_code: {
                    identifier: 'Verification_code',
                    rules: [{
                        type: 'empty',
                        prompt: 'Please enter Verification_code'
                    }]
                },*/

               /* terms: {
                    identifier: 'terms',
                    rules: [{
                        type: 'checked',
                        prompt: 'You must agree to the terms and conditions'
                    }]
                },*/
                contact: {
                    identifier: 'contact',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入联系人信息'
                    },
                    {
                        type: 'maxLength[18]',
                        prompt: '长度不得多于18位'
                    },]
                },
                contactemail: {
                    identifier: 'contactemail',
                    rules: [{
                        type: 'email',
                        prompt: '请输入联系邮箱'
                    },{
                        type: 'maxLength[30]',
                        prompt: '长度不得多于30位'
                    },{
                        type:'isEmailRegistered',
                        prompt:'该邮箱已注册'
                    }]
                },
                contactphone: {
                    identifier: 'contactphone',
                    rules: [{
                        type: 'regExp',
                        value: /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/,
                        prompt: '请输入正确的手机号码'
                    },{
                        type:'isPhoneRegistered',
                        prompt:'该手机已注册'
                    }]
                },
                contactseat: {
                    identifier: 'contactseat',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入电话'
                    }, {
                        type: 'number',
                        prompt: '请输入正确电话（正确格式：012345679）'
                    },
                    {
                        type: 'maxLength[15]',
                        prompt: '电话不得多于15位'
                    },
                    ]
                },

                legalIdCard: {
                    identifier: 'legalIdCard',
                    rules: [{
                        type: 'empty',
                        prompt: '请输入身份证'
                    },{
                        type: 'regExp',
                        value: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
                        prompt: '请输入正确的身份证号码'
                    }, 
                    // {
                    //     type: 'maxLength[18]',
                    //     prompt: '请输入15位或18位身份证号码'
                    // },
                    // {
                    //     type: 'minLength[15]',
                    //     prompt: '请输入15位或18位身份证号码'
                    // },
                    ]
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
            keyboardShortcuts: false,
            onSuccess: function(e) {
                //阻止表单的提交
                //console.log("onSuccess");
                var allFields1 = registerForm.form('get values');
                var allFields2=registerForm.serialize();
                console.log(allFields1);
                console.log(allFields2);
                e.preventDefault();
                //next();
                                $.post("${pageContext.request.contextPath}/register/register",registerForm.serialize(),function(data){
                                    data = $.parseJSON(data);
                                    if(data.state==1){
                                        window.location.href = "${pageContext.request.contextPath}/register/registerNext";
                                    }else{
                                        alert(data.content);
                                    }
                                })
                                    .error(function(data){
                                        alert("操作失败！")
                                    });
                            
                        
                    
               
            }

        });

    })
</script>

</html>