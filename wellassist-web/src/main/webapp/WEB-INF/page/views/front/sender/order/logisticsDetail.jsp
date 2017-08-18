<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>

    <title>Title</title>
    
    <style> 
.ui.table tr td{
 border-top: none;
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
                    商品名称：${info.prodName}
                </th>
                <th>
                    载货量：${info.num}
                </th>
                <th>
                    下单日期 ：${info.orderDate}
                </th>
            </thead>
            <tbody>
                <tr class="blue-3">
                    <td>
                        卖家：${info.sellerUserName}
                    </td>
                    <td>
                        联系人：${info.sellerName}
                    </td>
                    <td>
                        联系电话：${info.sellerPhone}
                    </td>
                </tr>
                <tr class="blue-3">
                    <td colspan="3">提货地址：${info.fromAddress}</td>
                </tr>
                <tr class="grey-4">
                    <td>
                        买家：${info.customerUserName}
                    </td>
                    <td>
                        联系人：${info.customerName}
                    </td>
                    <td>
                        联系电话：${info.customerPhone}
                    </td>
                </tr>
                <tr class="grey-4">
                    <td colspan="3">配送地址: ${info.toAddress}</td>
                </tr>
              
                <tr >
                    
                    <td colspan="3">报价：
                        
                    </td>
                </tr>
            </tbody>
        </table>
        <h4>添加时间</h4>
        <div class="ui divider"></div>
        <div class="ui column segment">
          <div class=" inline fields">
              <div class="  inline  input field " style="width:380px; float:left">
                  <label for=" " style="padding-top:5px">发货时间:</label>
                  <!-- <input type="text"id="deliverDate" v-model="model.deliverDate"  onfocus="var receiveDate=$dp.$('receiveDate');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){receiveDate.focus();},maxDate:'#F{$dp.$D(\'receiveDate\')}'})" > -->
                  <span v-if="!DateEdit">{{deliverDate}}</span>
                  <input  v-if="DateEdit" type="text"id="deliverDate" v-model="model.deliverDate"  v-on:focus='date' >
                </div>
                <div class="  inline input field"  style="width:380px; float:left">
                  <label for="" style="padding-top:5px">收货时间:</label>
                  <span v-if="!DateEdit">{{receiveDate}}</span>
                  <input type="text" v-if="DateEdit" id='receiveDate'   v-model="model.receiveDate" v-on:focus="data2">
              </div>
              <div> 
                  <div v-if="!DateEdit" class="ui button " v-on:click='editDate'>编辑</div>
                  <div v-if="DateEdit" class="ui button " v-on:click='cancelEditDate'>取消</div>
              </div>
                <div style="clear:both"></div>
            </div>

        </div>
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
                    <td width="15%"><a class="negative ui button " v-on:click="delOldVihicle(vehicle.vehicleGrabInfoId,index) " style="height:35px">删除 </a></td>
                </tr>
                <tr v-for="(vehicle,index) in newVehicles " v-cloak>
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
            <div class="ui button primary" @click='submitVehicle'>保存 </div>
            <div class="ui button cancel">取消</div>
            

        </div>

    </div>
 </div>
    </div>
</body>
<script>
     const orderId= '${info.orderId}';
     const url1='${pageContext.request.contextPath}/userinfo/operationDriver';
     var logisticsInfoId=${info.logisticsId};
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
    // $('#deliverDate').date_input();
  	// $('#receiveDate').date_input();

})
    function addDrivers() {
        $('.drivers').transition({
            animation: 'scale',
        });
    }
    function delVihicle() {
       
    }

</script>
<script>
       const logisticsId= ${info.logisticsId};
       <%--const url1='${pageContext.request.contextPath}/sender/grabLogisticsSubmit';--%>
      //  const senderUserId = ${senderUserId};



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
            newVehicles:[],
            grabVehicles: [],
            logisticsInfoId: '',
            senderUserId:'',
            grabMoney:'',
            deliverDate:'',
            receiveDate:'',
            model:{orderId:orderId,
                   deliverDate:'',
                   receiveDate:'',
                   list:[],
            },
            DateEdit:false,

        },
        watch:{
            //此处是判断数字的
           grabMoney: function(val){
               let reg= /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;

             if(reg.test(val)){
                //  console.log(true)
             }
             else{
                 return this.grabMoney=0
             }
           }
        },
            created:function (){
                var param = {"logisticsId":logisticsId};
                //var url="${pageContext.request.contextPath}/sender/selectDriver";
                var url="${pageContext.request.contextPath}/sender/selectDriver"
                $.get(url,param,function(result){
                    if(result.code==0){
                        console.log(result);
                        // vm.model.
                        vm.grabVehicles=result.list;
                        vm.deliverDate=result.deliverDate;
                        vm.receiveDate=result.receiveDate;
                        console.log(vm.grabVehicles);
                    }

                },"json");
            },
        methods: {
            createVehicle: function() {
               if( $('.drivers').form('is valid') ){
                   console.log(this.grabVehicles);
                  //this.grabVehicles.push(this.newVehicle);
                  this.newVehicles.push(this.newVehicle);

                  this.model.list.push(this.newVehicle);
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
                this.newVehicles.splice(index, 1);
                //this.grabVehicles.splice(index)
               // delete this.grabVehicles(index);
       
                //  window.location.reload();
            },
            delOldVihicle:function(vehicleInfoId,index){
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
                            vm.grabVehicles.splice(index, 1);
                            vm.$forceUpdate();
                            // window.location.reload();
                        }
                        else {
                            alert(result.msg)
                        }

                    }

                })
            },
            editDate:function(){
            this.DateEdit=true;
            },
            cancelEditDate:function(){
            this.DateEdit=false;
            },
            

          date:function(){
              let that= this;
        //   var receiveDate=$dp.$('receiveDate');
          WdatePicker({
              dateFmt:'yyyy-MM-dd HH:mm:ss',
              onpicked:function(){receiveDate.focus();}
              ,maxDate:'#F{$dp.$D(\'receiveDate\')}'
              });
            //   $('#deliverDate').val();
              that.model.deliverDate= $('#deliverDate').val();
          },
          data2:function(){
            let that= this;
            WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'deliverDate\')}'})
            that.model.receiveDate= $('#receiveDate').val();
          },

            submitVehicle:function () {
                console.log(JSON.stringify(vm.model));
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/userinfo/operationDriver',
                    data:JSON.stringify(vm.model),
                    dataType:'json',
                    contentType:'application/json',
                    success:function(result) {
                        if(result.code==0){
                            alert("修改成功");
                            console.log(result.msg)
                        }

                    }

                });
            }


        }

        })

</script>

</html>
