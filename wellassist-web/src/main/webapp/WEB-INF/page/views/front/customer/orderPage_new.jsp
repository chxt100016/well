<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
   <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <script src="https://unpkg.com/vue/dist/vue.js"></script>
     <style>
     .errors{
         color: #900b09;
         width: 100%;
         height: 120px;
     }
     .errors li{
         width: 250px;
         float:left;
     }
     </style>
</head>
<body>
    <div class="ui container segment mid-content" id="app1" style="text-align:left; width: 900px;left: 110px; top: -13px">
        <form id="infoForm" action="<c:url value="/customer/order"/>" method="post">
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
                            <c:if test="${prod.prodType==2}">管道气</c:if>
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
                            <div class="ui labeled input">
                                <label class="ui label">发货时间： </label>
                                        <input type="text" name="deliverDate" value="" style="width:300px" id="deliverDate" onfocus="var receiveDate=$dp.$('receiveDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){receiveDate.focus();},maxDate:'#F{$dp.$D(\'receiveDate\')}'})">
                                
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input">
                            <label class="ui label">收货时间： </label>
                                <input type="text" name="receiveDate" value="" style="width:300px" id="receiveDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'deliverDate\')}'})">
                             </div>  
                        </div>

                    </div>

                    <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input" style="width:80%">
                                <div class="ui label">联系人 </div>
                                <input type="text" name="contacts" placeholder="" id="contacts" >
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui labeled input" style="width:80%">
                                <div class="ui label">联系手机 </div>
                                <input type="text" placeholder="" name="conTel" id="conTel" maxlength="11" class="number isPhone">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field">
                     <div class="ui labeled input">
                    <label  class="ui label">配送地址：</label> <select class="ui dropdown" name="prodRegionId_0">
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
                    
                    </div>
                </div>
                <br>
                <div class="field">
                    <div class="ui labeled input">
                        <div class="ui label">详细地址 </div>
                        <input type="text" placeholder="" id="toRegionAddr" name="toRegionAddr"style="width:360px">
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
                                <input type="text " placeholder=" " id="danjia" name="danjia" value="${prod.prodPrice}" readonly="true" onkeyup="return validateNumber(this,value,0)"><a class="ui tag label ">元 </a>
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">总价 </div>
                                <input type="text " placeholder=" " name="saleMoney" id="saleMoney" readonly="true"><a class="ui tag label ">元</a>
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
                            <input type="radio" name="isSelfCar" checked="checked" value="0" onclick="checkSelfCar(0)" style="margin-left:0px">
                            <label>我有车</label></div>
                    </div>
                    <div class=" ui field ">
                        <div class="ui radio checkbox">
                            <input type="radio" name="isSelfCar" value="1" onclick="checkSelfCar(1)" style="margin-left:0px">
                            <label>需要物流</label></div>
                    </div>
                </div>
            </div>

            <div class="ui active tab " data-tab="owns " id="vehiclepage">
               
                <div>
                    <span class="ui green button" onclick="$('.ui.modal').modal('show');" >添加司机信息</span>

                </div>

                
                <table class="ui celled padded table ">
                    <thead>
                        <tr>
                            <th class="single line ">司机名称</th>
                            <th>电话</th>
                            <th>车牌号</th>
                            <th>车挂号</th>
                            <th>容量</th>
                            <th>操作</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="vehicle in Vehicles ">
                            <td>
                                <h2 class="ui center aligned header driverName">{{vehicle.dr_name}}</h2>
                            </td>
                            <td class="single line driverPhone" >{{vehicle.dr_tel}}</td>
                            <td class="carCode" > {{vehicle.dr_number}}</td>
                            <td> {{vehicle.hanging_number}} </td>
                            <td>{{vehicle.actual_size}}<span>吨</span></td>
                            <td class="right aligned " style="width:10% "><a class="ui button red " v-on:click="delVehicle(index) " style="width:70px;">删除</a></td>
                            <td class="vehicleHangingNo"> {{vehicle.hanging_number}} </td>
                            <td class="right aligned " style="width:10% "><a class="ui button red " v-on:click="delVehicle($index) ">DELETE </a></td>

                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="ui tab " data-tab="needs ">


            </div>
            <div class="ui divider "></div>
            <button class="ui blue button " type="submit " id="submit ">提交 </button>


        </form>
        <div class="ui small modal" style=" height: 480px">
                     <i class="close icon"></i>
                        <div class="header">
                        添加新司机
                        </div>
                <div class="content">
                    <form class="ui form segment has_vehicle_form">
                     <div class="ui form has_vehicle_form">
                    <div class=" two fields ">
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
                     
                        

                    </div>
                    <div class="two fields">
                           <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">车牌 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.dr_number " id="dr_number" name="dr_number">
                            </div>
                        </div>
                         <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">车挂号 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.hanging_number " id="dr_number" name="dr_number">
                            </div>
                        </div>
                    </div>
                    <div class="two fields">
                           <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">容量 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.actual_size" id="actual_size" name="actual_size">
                            </div>
                        </div>
                         
                    </div>
                  
                    <ul class="errors" v-if="!isValid" >
                        <p style="color:#234">填写时请注意：</p>
                        <li v-if="!validation.dr_name">司机姓名不得为空</li>
                        <!--<li v-show="!validation.dr_tel">请输入联系电话</li>-->
                         <li v-if="!validation.dr_tel_num">请输入正确的联系电话</li>
                        <li v-show="!validation.dr_number">请输入车辆车牌号码</li>
                        <li v-show="!validation.actual_size">请输入车辆容量</li>
                        <li v-if="!validation.actual_size_num">请输入正确车辆容量</li>
                        <li  v-if="!validation.hanging_number">请输入车挂号</li>
                        

                    </ul>
                </div>
                    
                </div>
                <div class="actions">
                    <div class="ui button cancel">取消</div>
                    <div class="ui button green"  @click="createVehicle">保存</div>
                </div>
                </form>
        </div>
    </div>

        
</body>
<script>
    $(function() {
        $('.tabular.menu .item').tab();
    })
</script>
<script>
    var telRE = /^1(3|4|5|7|8)\d{9}$/;
    var emailRE = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    var vehicleRef = '';
    var vm = new Vue({
        el: '#app1',
        data: {
            newVehicle: {
                dr_name: '',
                dr_tel: '',
                dr_number: '',
                hanging_number:'',
                actual_size:'',
            },
            Vehicles: []
        },
        computed: {
            validation: function() {
                return {
                    dr_name: !!this.newVehicle.dr_name.trim(),
                    dr_tel:!!this.newVehicle.dr_tel.trim(),
                    dr_tel_num: telRE.test(this.newVehicle.dr_tel),                 
                    dr_number: !!this.newVehicle.dr_number.trim(),
                    hanging_number: !!this.newVehicle.hanging_number.trim(),
                    actual_size:!!this.newVehicle.actual_size.trim(),
                    actual_size_num:!isNaN(this.newVehicle.actual_size),
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
                        hanging_number:'',
                        actual_size:'',
                    }
                }
                console.log(this.newVehicle);
                $('.ui.modal').modal('hide');
             
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
            errorPlacement: function(error, element) {
                if ($(element).closest('div.field').children().filter("div.error-div ").length < 1)
                    $(element).closest('div.field').append("<div class='error-div'></div>");
                $(element).closest('div.field').children().filter("div.error-div").append(error);
            },
            rules: {
                saleNum: {
                    required: true,
                    range:[1,500]
                },
                danjia: {
                    required: true
                },
                saleMoney: {
                    required: true
                },
                deliverDate: {
                    required: true
                },
                reveive_date: {
                    required: true
                },
                contacts: {
                    required: true
                },
                conTel: {
                    required: true,
                },
                toRegionAddr: {
                    required: true
                },

            },
            messages: {
                saleNum: {
                    required:"请输入供应量！",
                    range:'供应量应在1-500吨以内！'
                    },
                danjia: "请输入单价！",
                saleMoney: "请输入总价！",
                deliverDate: "请输入发货时间",
                reveive_date: "请输入收货时间！",
                contacts: "请输入联系人",
                conTel: {
                    required: "请输入联系电话",

                },
                toRegionAddr: "请输入完整收货地址！",
            },
            submitHandler: function(form) {
                var isSelfCar=$("input[type='radio'][name='isSelfCar']:checked").val();
                console.log(isSelfCar);
                if (isSelfCar == 0){
                    var itemNum = 0;

                    jQuery(".driverName").each(function(i){
                        itemNum++;
                    });

                    if(itemNum==0){
                        alert("请输入司机信息!");
                        return;
                    }

                    var arr = new Array();

                    var driverName=$(".driverName");
                    var driverPhones=$(".driverPhone");
                    var carCodes=$(".carCode");
                    var vehicleHangingNo=$(".vehicleHangingNo");
                    jQuery(".driverName").each(function(i){
                        var obj = new Object();
                        obj.driverName = driverName[i].innerHTML;
                        obj.driverPhone = driverPhones[i].innerHTML;
                        obj.carCode=carCodes[i].innerHTML;
                        obj.vehicleHangingNo=vehicleHangingNo[i].innerHTML;
                        arr[arr.length] = obj;
                    });

                    var orderData = JSON.stringify(arr);
                    $("#orderData").val(orderData);
                }

                if(confirm("你要确定操作吗?")){
                    $.post($(form).attr("action"),$(form).serialize(),function(data){
                        alert(data.content);
                        if(data.state==1 ){
                            window.location.href = "${pageContext.request.contextPath}/front/customer/CustomerHomeCtrl-orderSuccess";
                        }
                    }, "json");
                }
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
