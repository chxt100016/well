<%@ include file="header_new.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
   <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <script src="https://unpkg.com/vue/dist/vue.js"></script>
</head>
<body>
    <div class="ui container segment" id="app1" style="text-align:left;">
        <form id="infoForm" action="<c:url value="/customer/test"/>" method="post">
            <input type="hidden" name="toRegionId" id = "toRegionId">
            <input type="hidden" name="prodId" value="${prod.prodId}">
            <input type="hidden" name="orderData" id="orderData">
            <h2 class="ui header">订单信息</h2>
            <div class="ui divider"></div>

            <div class="column">
                <div class="ui form">
                    <div class="field">
                        <label>产品名称：${prod.prodName}</label>

                    </div>
                    <div class=" three fields">
                        <div class="field">
                            <label>商品类型：
                            <c:if test="${prod.prodType==0}">气体</c:if>
                            <c:if test="${prod.prodType==1}">燃油</c:if>
                            </label>
                        </div>
                        <div class="field">
                            <label>联系人：${prod.prodLxr}</label>
                        </div>
                        <div class="field">
                            <label>联系电话：${prod.prodLxrPhone}</label>
                        </div>
                    </div>

                </div>
            </div>
            <!--订单信息end-->
            <h2 class="ui header">配送信息</h2>
            <div class="ui divider"></div>
            <div class="column">
                <div class="ui form">
                    <div class="field">
                        <label>提货地址：${prod.fromRegionName}&nbsp;${prod.prodRegionAddr}</label>

                    </div>
                    <div class=" two fields">
                        <div class="field">
                            <label>发货时间： 
                                <input type="text" name="deliver_date" value="" style="width:300px" id="deliver_date" onfocus="var receive_date=$dp.$('receive_date');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){receive_date.focus();},maxDate:'#F{$dp.$D(\'receive_date\')}'})">
                                </label>
                        </div>
                        <div class="field">
                            <label>收货时间：
                                <input type="text" name="receive_date" value="" style="width:300px" id="receive_date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'deliver_date\')}'})">
                                </label>
                        </div>

                    </div>

                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input">
                                <div class="ui label">联系人 </div>
                                <input type="text" name="contacts" placeholder="" id="contacts">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input">
                                <div class="ui label">联系手机 </div>
                                <input type="text" placeholder="" name="con_tel" id="con_tel" maxlength="11" class="number isPhone">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>配送地址： <select class="ui dropdown" name="prodRegionId_0">
                    <option value="">-- 请选择省 --</option>
                        <c:forEach var="item" items="${shengRegionList}" varStatus="status">
                            <option value="${item.regionId}">${item.regionName}</option>
                        </c:forEach>
                    </select>
                        <select name="prodRegionId_1" class="ui dropdown" style="display: none">
                            <option value=''>-- 请选择市 --</option>
                        </select>
                        <select name="prodRegionId_2" class="ui dropdown" style="display: none">
                            <option value=''>-- 请选择区 --</option>
                        </select>
                    </label>

                </div>
                <br>
                <div class="field">
                    <div class="ui labeled input">
                        <div class="ui label">详细地址 </div>
                        <input type="text" placeholder="" id="full_address" name="full_address">
                    </div>

                </div>
            </div>
            <!--配送信息结束-->
            <h2 class="ui header">下单信息</h2>
            <div class="ui divider"></div>
            <div class="column">
                <div class="ui form">

                    <div class=" three fields">
                        <div class="field">
                            <div class="ui labeled input">
                                <div class="ui label">供应量 </div>
                                <input type="text" placeholder="" name="saleNum" id="saleNum" onkeyup="return validateNumber(this,value,0)"><a class=" ui tag label ">吨 </a>
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">单价 </div>
                                <input type="text " placeholder=" " id="danjia" name="danjia " value="${prod.prodMoney}" readonly="true" onkeyup="return validateNumber(this,value,0)"><a class="ui tag label ">元 </a>
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">总价 </div>
                                <input type="text " placeholder=" " name="saleMoney " id="saleMoney" readonly="true"><a class="ui tag label ">元</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!--//下单信息end-->
            <h2 class="ui header ">配送选择</h2>
            <div class="ui divider "></div>
            <div class="ui form">
                <div class=" inline fields ">
                    <div class="field ">
                        <div class="ui radio checkbox">
                            <input type="radio" name="vehicle_needs" checked="checked" value="0" onclick="checkSelfCar(0)">
                            <label>我有车</label></div>
                    </div>
                    <div class=" ui field ">
                        <div class="ui radio checkbox">
                            <input type="radio" name="vehicle_needs" value="1" onclick="checkSelfCar(1)">
                            <label>需要物流</label></div>
                    </div>
                </div>
            </div>

            <div class="ui active tab " data-tab="owns " id="vehiclepage">
                <!--<form class="ui form segment has_vehicle_form">-->
                <div class="ui form has_vehicle_form">
                    <div class=" four fields ">
                        <div class="field ">
                            <div class="ui labeled input  ">
                                <div class="ui label ">司机名称 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.dr_name " id="dr_name" name="dr_name">
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input vh">
                                <div class="ui label ">电话 </div>
                                <input type="text " class="vh number isPhone" placeholder=" " v-model="newVehicle.dr_tel " id="dr_tel" name="dr_tel">
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">车牌 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.dr_number " id="dr_number" name="dr_number">
                            </div>
                        </div>
                        <a class="ui primary button " @click="createVehicle" style="height:38px">保存 </a>

                    </div>
                    <ul class="errors">
                        <p style="color:#234">填写时请注意：</p>
                        <li v-show="!validation.dr_name">司机姓名不得为空</li>
                        <li v-show="!validation.dr_tel">请输入正确的联系电话</li>
                        <li v-show="!validation.dr_number">请输入车辆车牌号码</li>
                    </ul>
                </div>
                <!--</form>-->
                <table class="ui celled padded table ">
                    <thead>
                        <tr>
                            <th class="single line ">司机名称</th>
                            <th>电话</th>
                            <th>车牌号</th>
                            <th>操作</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="vehicle in Vehicles ">
                            <td>
                                <h2 class="ui center aligned header " id="driverName">{{vehicle.dr_name}}</h2>
                            </td>
                            <td class="single line " id="driverPhone">{{vehicle.dr_tel}}</td>
                            <td id="carCode"> {{vehicle.dr_number}}</td>
                            <td class="right aligned " style="width:10% "><a class="ui button " v-on:click="delVehicle($index) ">DELETE </a></td>

                        </tr>
                        <!--<tr>
                        <td>
                            <h2 class="ui center aligned header ">A</h2>
                        </td>
                        <td class="single line ">Weight </td>
                        <td>

                        </td>
                        <td class="right aligned " width="10% "><button class="ui button ">DELETE </button> </td>

                    </tr>-->
                    </tbody>
                </table>
            </div>
            <div class="ui tab " data-tab="needs ">


            </div>
            <div class="ui divider "></div>
            <button class="ui blue button " type="submit " id="submit ">Post </button>


        </form>
    </div>

</body>
<script>
    $(function() {
        $('.tabular.menu .item').tab();

        // $('.has_vehicle_form')
        //     .form({
        //         fields: {
        //             dr_name: {
        //                 identifier: 'dr_name',
        //                 rules: [{
        //                     type: 'empty',
        //                     prompt: '请输入司机姓名'
        //                 }]
        //             },
        //             dr_vh_num: {
        //                 identifier: 'dr_vh_num',
        //                 rules: [{
        //                     type: 'empty',
        //                     prompt: '车牌号'
        //                 }]
        //             },
        //             dr_tel: {
        //                 identifier: 'dr_tel',
        //                 rules: [{
        //                     type: 'regExp',
        //                     value: /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/,
        //                     prompt: '手机号码'
        //                 }]
        //             },
        //         },
        //         inline: true,
        //         on: 'blur'
        //     });
    })
</script>
<script>
    var telRE = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    var emailRE = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    var vehicleRef = '';
    var vm = new Vue({
        el: '#app1',
        data: {
            newVehicle: {
                dr_name: '',
                dr_tel: '',
                dr_number: '',
            },
            Vehicles: []
        },
        computed: {
            validation: function() {
                return {
                    dr_name: !!this.newVehicle.dr_name.trim(),
                    dr_tel: telRE.test(this.newVehicle.dr_tel),
                    dr_number: !!this.newVehicle.dr_number.trim(),
                }
            },
            isValid: function() {
                var validation = this.validation
                return Object.keys(validation).every(function(key) {
                    return validation[key]
                })
            }
        },
        methods: {
            createVehicle: function() {
                if (this.isValid) {
                    this.Vehicles.push(this.newVehicle)
                        // 添加完newPerson对象后，重置newPerson对象  
                    this.newVehicle = {
                        dr_name: '',
                        dr_tel: '',
                        dr_number: '',
                    }
                }

            },
            delVehicle: function(index) {
                // 删一个数组元素  
                this.Vehicles.splice(this.Vehicles.indexOf(index), 1);
            }
        }
    })
</script>
<script>
    // 初始化函数
    $(function() {
        // 检查模块

        $("#infoForm ").validate({
            debug:true,
            errorPlacement: function(error, element) {
                if ($(element).closest('div.field').children().filter("div.error-div ").length < 1)
                    $(element).closest('div.field').append("<div class='error-div'></div>");
                $(element).closest('div.field').children().filter("div.error-div").append(error);
            },
            rules: {
                saleNum: {
                    required: true
                },
                danjia: {
                    required: true
                },
                saleMoney: {
                    required: true
                },
                deliver_date: {
                    required: true
                },
                reveive_date: {
                    required: true
                },
                contacts: {
                    required: true
                },
                con_tel: {
                    required: true,
                },
                full_address: {
                    required: true
                },

            },
            messages: {
                saleNum: "请输入供应量！",
                danjia: "请输入单价！",
                saleMoney: "请输入总价！",
                deliver_date: "请输入发货时间",
                reveive_date: "请输入收货时间！",
                contacts: "请输入联系人",
                con_tel: {
                    required: "请输入联系电话",

                },
                full_address: "请输入完整收货地址！",
            },
            submitHandler: function(form) {
                var itemNum = 0;

                jQuery("#driverName").each(function(i){
                    itemNum++;
                });

                if(itemNum==0){
                    alert("请输入司机信息!");
                    return;
                }

                var arr = new Array();

                var driverName=jQuery("#driverName");
                var driverPhones=jQuery("#driverPhone");
                var carCodes=jQuery("#carCode");
                console.log(driverName);
                jQuery("#driverName").each(function(i){
                    var obj = new Object();

                    obj.driverName = driverName[i].html();
                    obj.driverPhone = driverPhones[i].html();
                    obj.carCode=carCodes[i].html();

                    arr[arr.length] = obj;
                });

                var orderData = JSON.stringify(arr);
                $("#orderData").val(orderData);
                console.log(orderData);
                /*if(confirm("你要确定操作吗?")){
                    console.log("has！");
                    $.post($(form).attr("action"),$(form).serialize(),function(data){
                        alert(data.content);
                        // showalert(data.content);
                        if(data.state==1 ){
                            window.location.href = "${pageContext.request.contextPath}/customer/test";
                        }
                    }, "json");
                }*/
            }

        }); // 



    }); //
    // 单价和数量变化函数 
    function validateNumber(e, pnumber, type) {
        var len = 0;
        if (!/^\d+$/.test(pnumber)) {
            if (isNaN(pnumber) == '0') return;
            len = pnumber.length;
            $(e).val(pnumber.substring(0, len - 1));
        }
        if (type == 0) {
            var saleNum = $("#saleNum").val();
            var saleDj = $("#danjia").val();
            if (saleNum == "" || saleDj == "") {
                $("#saleMoney").val(0);
            } else {
                console.log($("#danjia").length);
                $("#saleMoney").val(saleNum * saleDj);
            }
        }
        return false;
    }

    // 手机验证
    jQuery.validator.addMethod("isPhone", function(value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));

    }, "请正确填写您的联系电话");

    // function createVehicle() {
    //     var dr_name = $("#dr_name").val();
    //     var dr_tel = $("#dr_tel").val();
    //     var dr_vh_num = $("#dr_vh_num").val();
    //     console.log(1);
    //     if (dr_name == "") {
    //         alert("请输入司机姓名!");
    //         // showalert("请输入司机名称!");
    //         $("#dr_name").focus();
    //         return;
    //     };
    //     if (dr_tel == "") {
    //         alert("请输入司机联系电话!");
    //         // showalert("请输入司机名称!");
    //         $("#dr_name").focus();
    //         return;
    //     };
    //     if (dr_vh_num == "") {
    //         alert("请输入司机联系电话!");
    //         // showalert("请输入司机名称!");
    //         $("#dr_vh_num").focus();
    //         return;
    //     };


    // }

    function checkSelfCar(type) {
        if (type == '0') {
            // $("#wuliudingdandiv").hide();
            $("#vehiclepage").show();
        } else {
            // $("#wuliudingdandiv").show();
            $("#vehiclepage").hide();
        }
    }

    // 选择地区函数
    function getRegionList(pid,level){
        if(pid!=null && pid!=""){
            $.post("${pageContext.request.contextPath}/customer/getRegionList",{pid:pid},function(data){
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
                if(regionList.length != 0){
                    $("select[name='prodRegionId_" + level + "']").show();
                    $("#toRegionId").val("");
                }else{
                    $("#toRegionId").val(pid);
                    $("select[name='prodRegionId_" + level + "']").hide();
                }
            })
                .error(function(data){
                });
        }
    }
    // 省级目录变化函数
    $("select[name='prodRegionId_0']").change(function(){
        getRegionList($(this).val(),1);
    });

    // 市级目录变化函数
    $("select[name='prodRegionId_1']").change(function(){
        getRegionList($(this).val(),2);
    })

    // 区级目录变化函数
    $("select[name='prodRegionId_2']").change(function(){
        $("#toRegionId").val($(this).val());
    })
</script>
