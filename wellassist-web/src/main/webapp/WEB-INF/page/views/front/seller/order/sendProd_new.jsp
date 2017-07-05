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
           width: 991px;
    left: 80px;
    position: relative;
            margin: 0px auto;
            border: 1px solid rgba(34, 36, 38, .15);
            box-shadow: 0 2px 4px 0 rgba(34, 36, 38, .12), 0 2px 10px 0 rgba(34, 36, 38, .15);
            padding: 1em;
            font-size: 14px;
           
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
    <div class="ui container segment mid_box" id="app1" style="width:991px;left:60px;top:-13px">
        <form id="infoForm" action="${pageContext.request.contextPath}/seller/sendProdSubmit" method="post">
            <input type="hidden" name="orderId" value="${info.orderId}">
            <input type="hidden" name="orderVehicles" id="orderVehicles">
            <h2 class="ui header">买家：${info.userName}</h2>
            <div class="ui divider"></div>
            <div class="column ">
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
                            <th>装载量</th>
                            <th>操作</th>
                           
                        

                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(vehicle,index) in SelectedVehicles" v-cloak>
                            <td>
                                <h2 class="ui center aligned header xs " id="x" onclick="x()" >{{vehicle.driverName}}</h2>
                            </td>
                            <td class="single line ">{{vehicle.driverPhone}}</td>
                            <td> {{vehicle.vehicleNo}}</td>
                            <td>{{vehicle.vehicleHangingNo}}</td>
                             <td>{{vehicle.vehicleSize}} 吨</td>
                            <td><span class="VhicleLoads">{{vehicle.vehicleActualSize}}</span> 吨</td>
                            <td class="right aligned " style="width:10% "><a class="ui button red " v-on:click="delVehicle(vehicle) " style="width:70px">删除 </a></td>
                        </tr>
                    </tbody>
                </table>
                <div><a class="ui button blue"  id="add" onclick="add()" style="display: block; width: 133px; margin: 0px auto; ">添加新司机</a></div>

                <!--添加新司机弹框-->

                 <div class="ui modal "  id="vehiclepage">
                     <i class="close icon"></i>
                <div class="header">添加新的司机信息</div>
                   <table class="ui  celled  table">
                       <thead class="full-width">
                           <th>司机名称</th>
                           <th>电话</th>
                           <th>车牌</th>
                           <th>车挂号</th>
                           <th>装载量</th>
                           <th>操作</th>
                       </thead>
                       <tbody >
                           <tr v-for="(vehicle,index) in Vehicles" v-cloak>
                               <td>{{vehicle.driverName}}</td>
                               <td>{{vehicle.driverPhone}}</td>
                               <td>{{vehicle.vehicleNo}}</td>
                               <td>{{vehicle.vehicleHangingNo}} </td>
                               <td>
                                   <input  class="vh" placeholder=" "  id="vehicleSize"  v-model="vehicle.vehicleActualSize" type="number" onblur="validedTex(this)"><span> 容量：<span >{{vehicle.vehicleSize}}</span> 顿</span></td>
                               <td>
                                   <div class="ui checkbox">
                                    <input type="checkbox" name="example" v-bind:value="index" class="example" style="margin-left:0px" v-model="Selected">
                                    <label>选择</label>
                                    </div> 
                            </td>
                           </tr>
                       </tbody>
                   </table>
                   <div class="actions">
                        <div class="ui black deny button">
                       取消
                        </div>
                        <div class="ui positive right labeled icon button" @click="qwer()">
                        确认
                        <i class="checkmark icon"></i>
                        </div>
                    </div>
                 </div>
                 <!--添加新司机弹框end-->
            <div class="ui form" style="width: 550px;height:200px">
                  <div class="field">
                <label>备注信息：</label>
                <textarea name="sendComment" style="width:549px;height:150px; max-width:550px;max-height:155px"></textarea>
                  </div>
            </div>
        </form>
        <div class="column"> <label>收货地址：</label>${info.toAddress}</div>

        <div><a class="ui button green"  id="add" @click="sendProdSubmit" style="display: block; width: 133px; margin: 0px auto; ">确认</a></div>
        <div>
            <button onclick="AllLoads()">summary</button>
        </div>
    </div>

</body>

<script>
  
    var vm= new Vue({
        el:'#app1',
        data:
        {
            Vehicles:[],
            Selected: [],
            SelectedVehicles:[]
        },
        created:function(){
            $.post('${pageContext.request.contextPath}/seller/getOrderVehicles',{orderId:'${info.orderId}'},function(data){
                var arr=JSON.parse(data);
                for (i = 0; i < arr.length; i++) {
                    vm.Vehicles[i]=arr[i];
                    vm.Vehicles[i].vehicleActualSize=arr[i].vehicleSize;
                }
                vm.$forceUpdate();
            },'json');
        },
        computed:{
              validation: function() {
                return {
                    vehicleActualSize: 1                 
                }
            },
            isValid: function() {
                var validation = this.validation
                return Object.keys(validation).every(function(key) {
                    return validation[key]
                })
            }

        },
        methods:{
            qwer:function(){
                for (var i = 0; i < this.Selected.length; i++) {
                    var index=this.Selected[i];
                    this.SelectedVehicles.push(this.Vehicles[index]);
                };
                for (var i = this.Selected.length-1; i >=0; i--) {
                    var index=this.Selected[i];
                    this.Vehicles.splice(index,1);
                };
                this.Selected=[];
            },
            delVehicle: function(vehicle) {
                // 删一个数组元素
                this.Vehicles.push(vehicle);
                this.SelectedVehicles.splice(this.SelectedVehicles.indexOf(vehicle), 1);
            },
            sendProdSubmit:function () {
                $("#orderVehicles").val(JSON.stringify(this.SelectedVehicles));
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
    vm.qwer();
    $('.modal').modal('show');
}
function close_m(){
    $('.modal').modal('hide');
}
 function validedTex(tex){
     var Tex= Number(tex.value);
    //  console.log(Tex);
     var b= tex.nextSibling.firstChild.nextSibling;
     var c= Number(b.innerHTML);
     console.log(c); 
     var r = /^\+?[1-9][0-9]*$/ ;
    //  console.log(r.test(Tex));
     if(Tex==''){
               console.log('null!');
               }
    //  if(Tex!==Number){
    //      console.log("不是数字！")
    //     tex.value= 0;
    //  }
    if(Tex=='NaN'){
            tex.value= c;
            console.log("nan")
    }
    if(Tex<=0){
        console.log("小于0！");
       tex.value= c;
    }
    if(Tex>=c){
        console.log("太大了！");
        tex.value=c;
    }
   else{
    //    return tex.value= Tex;
   }

            }
/* function AllLoads(){
     var allloads=0;
     var vihicleloads=document.getElementsByClassName("VhicleLoads");
     console.log(vihicleloads);
     for (var index = 0; index < vihicleloads; index++) {
         var allloads = array[index];
         
     }
 }*/
</script>

</html>
