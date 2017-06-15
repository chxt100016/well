<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<head>
    <title>发货单</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>
    <style>
        .mid_box {
            width: 1100px;
            margin: 0px auto;
            border: 1px solid rgba(34, 36, 38, .15);
            box-shadow: 0 2px 4px 0 rgba(34, 36, 38, .12), 0 2px 10px 0 rgba(34, 36, 38, .15);
            padding: 1em;
            font-size: 14px;
            position: absolute;
        }
        
        .span_time {}
        
        .span_status {
            color: #adadad;
        }
        
        .fl-rg {
            float: right;
        }
        
        .fl-lf {
            float: left;
        }
        
        .pd-lf-10 {
            padding-left: 10px;
        }
        
        .pd-bt-10 {
            padding-bottom: 10px;
        }
        
        table {
            margin: 10px;
        }
        
        .container .item span {
            display: block;
            width: 30%;
            float: left
        }
    </style>
</head>

<body>
    <div class="ui container segment" id="app1">
        <form id="infoForm" action="${pageContext.request.contextPath}/seller/sendProdSubmit" method="post">
            <input type="hidden" name="orderId" value="${info.orderId}">
            <input type="hidden" name="orderVehicles" id="orderVehicles">
            <h2 class="ui header">买家：${info.userName}</h2>
            <div class="ui divider"></div>
            <div class="column container">
                <div class="fl-lf " style="width:50%"> ${info.prodName}</div>
                <div class="right item fl-rg" style="width:40%">
                    <span class="ui inverted ">总量:${info.orderNumber}</span>
                    <span class="ui inverted ">已发货:${info.deliverNumCount}</span>
                    <span class="ui inverted ">当前库存:${info.restNum}<span>
               
                </div>
                <br><br>
               
            </div>
            <div class="colunm ">
                <div class="ui form">
                     <div class=" two fields">
                        <div class="field">
                        <div class="ui labeled input"><div class="ui label">发货量： </div> <input type="text" name="zorderNum" placeholder="" id="zorderNum">
                         <div class="ui basic label">吨</div>
                      </div>
                      </div> 

                <div class="field">
                    <div class="ui labeled input">
                        <div class="ui label">单价： </div> 
                       <input type="text" name="zorderPrice" placeholder="${info.orderPrice}" value="${info.orderPrice}"  class="number isPhone">
                        <div class="ui basic label">元</div>
                       </div> 
                    
                      </div>
                    </div>
                </div>
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
                        <tr v-for="vehicle in Vehicles " v-cloak>
                            <td>
                                <h2 class="ui center aligned header xs " id="x" onclick="x()" value="{{{vehicle.driverName}}}">{{vehicle.driverName}}</h2>
                            </td>
                            <td class="single line ">{{vehicle.driverPhone}}</td>
                            <td> {{vehicle.vehicleNo}}</td>
                            <td>{{vehicle.vehicleHangingNo}}</td>
                             <td>{{vehicle.vehicleActualSize}} 吨</td>

                            <td class="right aligned " style="width:10% "><a class="ui button " v-on:click="delVehicle(index) ">DELETE </a></td>

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
                <div><a class="ui button blue"  id="add" onclick="add()" style="display: block; width: 133px; margin: 0px auto; ">添加新司机</a></div>

                <!--添加新司机弹框-->

                 <div class="ui modal "  id="vehiclepage">
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
                                <div class="ui label ">装载量 </div>
                                <input type="text " class="vh" placeholder=" " v-model="newVehicle.vehicleActualSize " id="vehicleActualSize" name="vehicleActualSize">
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
                        <li v-show="!validation.vehicleActualSize">请输入车辆实际装载量</li>
                     </ul>
                   </div>
                 </div>
                 <!--添加新司机弹框end-->

        </form>
          <div class="column">收货地址:${info.toAddress}</div>
           <div><a class="ui button green"  id="add" @click="sendProdSubmit" style="display: block; width: 133px; margin: 0px auto; ">确认</a></div>
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
                vehicleHangingNo:'',
                vehicleActualSize:'',
            },
            Vehicles: []
        },
        computed: {
            validation: function() {
                return {
                    driverName: !!this.newVehicle.driverName.trim(),
                    driverPhone: telRE.test(this.newVehicle.driverPhone),
                    vehicleNo: !!this.newVehicle.vehicleNo.trim(),
                    vehicleHangingNo:!!this.newVehicle.vehicleHangingNo.trim(),
                    vehicleActualSize:!!this.newVehicle.vehicleActualSize.trim(),

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
                        vehicleActualSize:'',
                        vehicleHangingNo:''
                    }
                };
                 $('.modal').modal('hide')

            },
            delVehicle: function(index) {
                // 删一个数组元素  
                this.Vehicles.splice(this.Vehicles.indexOf(index), 1);
            },
            sendProdSubmit:function () {
                $("#orderVehicles").val(JSON.stringify(this.Vehicles));
                $.post($("#infoForm").attr("action"),$("#infoForm").serialize(),function(data){
                    if(data.status==1 ){
                        window.location.href = "${pageContext.request.contextPath}/seller/orderListPage";
                    }
                },"json");
            }
        }
    })
</script>
<script>    
function add(){
    $('.modal')
  .modal('show')
;
}
function close_m(){
    $('.modal')
  .modal('hide')
;
}
</script>
</html>

<%@ include file="../footer.html"%>