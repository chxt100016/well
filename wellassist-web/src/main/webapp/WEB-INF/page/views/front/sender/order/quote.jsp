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
    <div class="container1">
        <div class="container2">

       
   <div class="ui segment container" id='app'>
        <h4>放款详情</h4>
        <div class="ui divider"></div>
        
        <table class="ui table">
            <thead>
                <th>
                    商品名称：
                </th>
                <th>
                    载货量：
                </th>
                <th>
                    下单日期：
                </th>
            </thead>
            <tbody>
                <tr class="blue-3">
                    <td>
                        卖家：
                    </td>
                    <td>
                        联系人：
                    </td>
                    <td>
                        联系电话：
                    </td>
                </tr>
                <tr>
                    <td colspan="3">提货地址：${info.fromAddress}</td>
                </tr>
                <tr>
                    <td>
                        买家：
                    </td>
                    <td>
                        联系人：
                    </td>
                    <td>
                        联系电话：
                    </td>
                </tr>
                <tr>
                    <td colspan="3">配送地址: ${info.toAddress}</td>
                </tr>
                <tr>
                    <td colspan="3">客户报价：</td>
                </tr>
                <tr>
                    
                    <td colspan="3">报价：
                        <input type="text"  name="grabMoney" v-model=grabMoney>
                    </td>
                </tr>
            </tbody>
        </table>

        <h4>司机信息 <a href="#" onclick="addDrivers()" style="float:right">+添加司机</a> </h4>
        <div class="ui divider"></div>
        <form class="ui form drivers segment transition hidden" action="">
            <div class="two fields">
                <div class="inline field">
                    <label>司机姓名：</label>
                    <input type="text" name="driverName" v-model='newVehicle.driverName'>
                </div>
                <div class="inline field">
                    <label>&#8195司机电话：</label>
                    <input type="text" name="driverPhone" v-model='newVehicle.driverPhone'>
                </div>
            </div>
            <div class="two fields">
                <div class="inline field">
                    <label>司机车牌：</label>
                    <input type="text" name="vehicleNo" v-model='newVehicle.vehicleNo'>
                </div>
                <div class="inline field">
                    <label>司机车挂号：</label>
                    <input type="text" name="vehicleHangingNo" v-model='newVehicle.vehicleHangingNo'>
                </div>
            </div>
            <div class="two fields">
                <div class="inline field">
                    <label>&#8195&#8195容量：</label>
                    <input type="text" name="vehicleSize" v-model='newVehicle.vehicleSize'>
                    <input type="hidden" name="orderId" value="${info.orderId}" >
                </div>

            </div>
            <div class="ui button submit" @click='createVehicle' >保存 </div>
            

        </form>
         
        <table class="ui table">
            <thead>
                <th>司机姓名</th>
                <th>联系电话</th>
                <th>车牌号</th>
                <th>车挂号</th>
                <th>容量</th>
                <th>操作</th>
            </thead>
            <tbody>
                <tr v-for="(vehicle,index) in grabVehicles " v-cloak>
                    <td>
                        {{vehicle.driverName}}
                    </td>
                    <td class="single line ">{{vehicle.driverPhone}}</td>
                    <td> {{vehicle.vehicleNo}}</td>
                    <td>{{vehicle.vehicleHangingNo}}</td>
                    <td>{{vehicle.vehicleSize}} 吨</td>

                    <td width="15%"><a class="negative ui button " v-on:click="delVehicle(index) " style="height:35px">删除 </a></td>

                </tr>

            </tbody>

        </table>
        <div class="column">
            <div class="ui button">确认 </div>
            <div class="ui button cancel">取消</div>

        </div>

    </div>
 </div>
    </div>
</body>
<script>
    // const orderId= ${info.orderId};
    // const url1='${pageContext.request.contextPath}/userinfo/operationDriver';
  $(function(){

  
    $('.drivers').form({
        on: 'blur',
        fields: {
            driverName: {
                identifier: 'driverName',
                rules: [{
                    type: 'empty',
                    prompt: '朋友别忘记填写哦'
                }]
            },
            driverPhone: {
                identifier: 'driverPhone',
                rules: [{
                    type: 'empty',
                    prompt: '朋友别忘记填写哦'
                }, {
                    type: 'regExp[/^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$/]',
                    prompt: '手机号哦'
                }
                ]
            },
            vehicleHangingNo: {
                identifier: 'vehicleHangingNo',
                rules: [{
                    type: 'empty',
                    prompt: '朋友别忘记填写哦'
                }]
            },
            vehicleNo: {
                identifier: 'vehicleNo',
                rules: [{
                    type: 'empty',
                    prompt: '朋友别忘记填写哦'
                }]
            },
            vehicleSize: {
                identifier: 'vehicleSize',
                rules: [{
                    type: 'empty',
                    prompt: '朋友别忘记填写哦'
                }]
            },
        },
        inline: true,
        onSuccess: function (e) {
            e.preventDefault();
            let allFields = $('.drivers').form('get values');
            console.log(typeof allFields);
            //   json.stringfy

        }
    });
})
    function addDrivers() {
        $('.drivers').transition({
            animation: 'scale',
        });
    }
    function delVihicle(vehicleInfoId) {
        console.log(vehicleInfoId);
        let url2 = "${pageContext.request.contextPath}/userinfo/deleteDriver"
        $.ajax({
            type: 'get',
            url: url2,
            data: { 'id': vehicleInfoId },
            dataType: 'json',
            // contentType:'application/json',
            success: function (result) {
                if (result.code == 0) {
                    alert("成功");
                    window.location.reload();
                }
                else {
                    alert(result.msg)
                }

            }

        })

    }

</script>
<script>
       const logisticsInfoId= ${info.logisticsId};
       const senderUserId = ${senderUserId};
        const vm = new Vue({
            el: '#app',
            data: {
            newVehicle: {
                driverName: '',
                driverPhone: '',
                vehicleNo: '',
                vehicleHangingNo: '',
                vehicleSize: '',
            },
            grabVehicles: [],
            logisticsInfoId: logisticsInfoId,
            senderUserId:senderUserId,
            grabMoney:'',
        },
        methods: {
            createVehicle: function() {
               if( $('.drivers').form('is valid') ){
                   console.log(this.Vehicles);
                  this.grabVehicles.push(this.newVehicle);
                        // 添加完newPerson对象后，重置newPerson对象  
                    this.newVehicle = {
                        driverName: '',
                        driverPhone: '',
                        vehicleNo: '',
                        vehicleSize: '',
                        vehicleHangingNo: ''
                    };
                   addDrivers();

                }
                },
            delVehicle: function(index) {
                console.log(index);
                // 删一个数组元素  
                this.grabVehicles.splice(this.grabVehicles.indexOf(index), 1);
            },


        }

        })

</script>
<%@ include file="../footer.jsp"%>