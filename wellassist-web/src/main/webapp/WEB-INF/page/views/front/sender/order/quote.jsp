<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>报价</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>
    <style>
        .content-ul>.header {
            line-height: 2em;
        }
    </style>
</head>

<body>
    <div class="ui container segment" id="app1">
        <form id="grabForm" action="${pageContext.request.contextPath}/sender/grabLogisticsSubmit">
            <input type="hidden" name="senderUserId" value="${senderUserId}">
            <input type="hidden" name="grabVehicles" id="grabVehicles">
            <input type="hidden" name="logisticsInfoId" value="${info.logisticsId}">
            <h3 class="ui header">报价信息</h3>
            <div class="ui divider"></div>
            <div class="content content-ul" id="app2">
                <%--<div class="header">客户报价：{{price_origin}}</div>--%>
                <br>
                <div>
                    <span> 报价：</span>
                    <div class="ui left action input">
                        <a class="ui teal  button" v-on:click="minusprice"><i class="minus icon"></i></a>
                        <input type="text" name="grabMoney" value="" v-model=price_quotes>
                        <a class="ui right teal button" v-on:click="plusprice"><i class="plus icon"></i></a>
                    </div>
                </div>
                <br>
                <div class="header">提货地址：${info.fromAddress}</div>
                <div class="header">配送地址：${info.toAddress}</div>
            </div>
        </form>
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
                    <tr v-for="vehicle in Vehicles " v-cloak>
                        <td>
                            <h2 class="ui center aligned header xs " id="x" onclick="x()" value="{{{vehicle.driverName}}}">{{vehicle.driverName}}</h2>
                        </td>
                        <td class="single line ">{{vehicle.driverPhone}}</td>
                        <td> {{vehicle.vehicleNo}}</td>
                        <td>{{vehicle.vehicleHangingNo}}</td>
                        <td>{{vehicle.vehicleSize}} 吨</td>

                        <td class="right aligned " style="width:10% "><a class="ui button " v-on:click="delVehicle($index) ">DELETE </a></td>

                    </tr>

                </tbody>
            </table>
            <div><a class="ui button blue" id="add" onclick="add()" style="display: block; width: 133px; margin: 0px auto; ">添加新司机</a></div>

            <!--添加新司机弹框-->

            <div class="ui modal " id="vehiclepage">
                <!--<form class="ui form segment has_vehicle_form">-->
                <i class="close icon"></i>
                <div class="header">添加新的司机信息</div>
                <div class="ui form has_vehicle_form">
                    <div class="three fields ">
                        <div class="field ">
                            <div class="ui labeled input  ">
                                <div class="ui label ">司机名称 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.driverName " id="driverName" name="driverName">
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input vh">
                                <div class="ui label ">电话 </div>
                                <input type="text " class="vh number isPhone" placeholder=" " v-model="newVehicle.driverPhone " id="driverPhone" name="driverPhone">
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">车牌 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.vehicleNo " id="vehicleNo" name="vehicleNo">
                            </div>
                        </div>
                    </div>
                    <div class="three fields ">
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">车挂号 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.vehicleHangingNo " id="vehicleHangingNo" name="vehicleHangingNo">
                            </div>
                        </div>
                        <div class="field ">
                            <div class="ui labeled input ">
                                <div class="ui label ">容量 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.vehicleSize " id="vehicleSize" name="vehicleSize">
                            </div>
                        </div>

                        <a class="ui primary button " @click="createVehicle" style="height:38px">添加 </a>

                    </div>
                    <ul class="errors">
                        <p style="color:#234">填写时请注意：</p>
                        <li v-if="!validation.driverName">司机姓名不得为空</li>
                        <li v-show="!validation.driverPhone">请输入正确的联系电话</li>
                        <li v-show="!validation.vehicleNo">请输入车辆车牌号码</li>
                        <li v-show="!validation.vehicleHangingNo">请输入车辆车挂号</li>
                        <li v-show="!validation.vehicleSize">请输入车辆容量</li>
                    </ul>
                </div>
            </div>
            <!--添加新司机弹框end-->


        <br>
        <div><a class="ui button green" id="add" @click="grabSubmit" style="display: block; width: 133px; margin: 0px auto; ">确认</a></div>
    </div>


</body>
<script>
    var telRE = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    var emailRE = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    var vehicleRef = '';
    var vm = new Vue({
        el: '#app1',
        data: {
            newVehicle: {
                driverName: '',
                driverPhone: '',
                vehicleNo: '',
                vehicleHangingNo: '',
                vehicleSize: '',

            },
            Vehicles: [],
            price_origin: "300",
            price_quotes: '',
        },
        computed: {
            validation: function() {
                return {
                    driverName: !!this.newVehicle.driverName.trim(),
                    driverPhone: telRE.test(this.newVehicle.driverPhone),
                    vehicleNo: !!this.newVehicle.vehicleNo.trim(),
                    vehicleHangingNo: !!this.newVehicle.vehicleHangingNo.trim(),
                    vehicleSize: !!this.newVehicle.vehicleSize.trim(),

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
                        driverName: '',
                        driverPhone: '',
                        vehicleNo: '',
                        vehicleSize: '',
                        vehicleHangingNo: ''
                    }
                };
                $('.modal').modal('hide')

            },
            delVehicle: function(index) {
                // 删一个数组元素  
                this.Vehicles.splice(this.Vehicles.indexOf(index), 1);
            },
            plusprice: function() {
                this.price_quotes++;
            },
            minusprice: function() {
                this.price_quotes--;
                if (this.price_quotes <= 0) {
                    console.log(0);
                    this.price_quotes = 0
                }
            },
            grabSubmit:function () {
                $("#grabVehicles").val(JSON.stringify(this.Vehicles));
                $.post($("#grabForm").attr("action"),$("#grabForm").serialize(),function(data){
                    if(data.code==0 ){
                        /*window.location.href = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-sqResult";*/
                        window.location.href = "${pageContext.request.contextPath}/sender/logisticsGrabResult";
                    }else {
                        alert(data.msg);
                    }
                },"json");
            }
        }
    })
</script>
<script>
    function add() {
        $('.modal')
            .modal('show');
    }

    function close_m() {
        $('.modal')
            .modal('hide');
    }
</script>

<script>
    // var app = new vue({
    //     el = app2,
    //     data: {
    //         price_origin: "3000"
    //     },

    // })
</script>
<%@ include file="../footer.jsp"%>