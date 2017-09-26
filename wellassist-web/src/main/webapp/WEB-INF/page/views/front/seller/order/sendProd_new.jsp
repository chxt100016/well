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
    <script src="${pageContext.request.contextPath}/statics/libs/ajaxupload.js"></script>
    <style>
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
        .ui .segment{
            line-height:40px;
            background-color: #f5f9fc;
            font-size:15px;
        }
    </style>
</head>

<body>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
    <div class="ui container" id="app1" style="width:90%;">
        <form id="infoForm" action="${pageContext.request.contextPath}/seller/sendProdSubmit" method="post">
        <input type="hidden" name="orderVehicles" id="orderVehicles">
        <input type="hidden" name="orderId" value="${info.orderId}">
            <h4 class="ui header">发货详情</h4>
            <div class="ui divider"></div>

            <div class="colunm">
                <div class="ui form">
                    <div class="two fields">
                        <div class="field">
                            <div class="ui segment">
                                <span class="ui header" style="color:#617b90;">买家：${info.userName}</span><span style="color:#999;margin-left:5%;">${info.prodName}</span><br>
                                <span>收货地址：${info.toAddress}</span>
                            </div>
                        </div> 
                        <div class="field">
                            <div class="ui segment">
                                <span>总量：${info.orderNumber}&emsp;吨</span>
                                <span style="margin-left:5%;">已发货：<span style="color:#FF4400;font-size:17px;">${info.deliverNumCount}</span>&emsp;吨</span><br>
                                <span>当前库存：${info.restNum}&emsp;吨</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="colunm ">
                <div class="ui form">
                    <div class="three fields">
                        <div class="field">
                            <div class="ui labeled input">
                                <div class="ui label">发货量: </div>
                                <input type="text" name="zorderNum" placeholder="" id="zorderNum" onkeyup="change()">
                                <div class="ui basic label">吨</div>
                            </div>
                        </div> 
                        <div class="field">
                            <div class="ui labeled input">
                                <div class="ui label">单价：</div>
                                <input type="text" name="zorderPrice" placeholder="${info.orderPrice}" value="${info.orderPrice}"  class="number isPhone">
                                <div class="ui basic label">元</div>
                            </div> 
                        </div>
                        <div class="field">
                            <div class="ui labeled input">
                                <div class="ui label">提货时间：</div>
                                <input type="text" name="zorderDate" placeholder="" value=""  class="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',})">                                
                            </div> 
                        </div>
                    </div>
                </div>
            </div>

            <div class="colunm" style="margin-top:40px;">
                <div class="ui form">
                    <div class="two fields">
                        <div class="field">
                            <h4 class="ui header">司机信息</h4>
                        </div>
                        <div class="field" style="text-align:right;">
                            <span style="color:#FF4400;" id="add" onclick="add()">+选择司机</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui divider"></div>

            <table class="ui celled padded table ">
                <thead>
                    <tr>
                        <th>司机</th>
                        <th>电话</th>
                        <th>车牌号</th>
                        <th>车挂号</th>
                        <th>容量(吨)</th>
                        <th>装载量(吨)</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(vehicle,index) in SelectedVehicles" v-cloak>
                        <td>
                            <span class="ui center aligned" id="x" onclick="x()" >{{vehicle.driverName}}</span>
                        </td>
                        <td class="single line ">{{vehicle.driverPhone}}</td>
                        <td> {{vehicle.vehicleNo}}</td>
                        <td>{{vehicle.vehicleHangingNo}}</td>
                        <td>{{vehicle.vehicleSize}}</td>
                        <td><span class="VhicleLoads">{{vehicle.vehicleActualSize}}</span></td>
                        <td class="right aligned " style="width:10% "><a class="ui button red " v-on:click="delVehicle(vehicle) " style="width:70px">删除 </a></td>
                    </tr>
                </tbody>
            </table>
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
                                <input  class="vh" placeholder=" "  id="vehicleSize"  v-model="vehicle.vehicleActualSize" type="number" onchange="validedTex(this)"><span> 容量：<span >{{vehicle.vehicleSize}}</span> 顿</span></td>
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
                    <div class="ui black deny button">取消</div>
                    <div class="ui positive right labeled icon button" @click="qwer()">确认<i class="checkmark icon"></i></div>
                </div>
            </div>
            <!--添加新司机弹框end-->

            <h4 class="ui header" style="margin-top:40px;">凭证与备注</h4>
            <div class="ui divider"></div>
            <div class="column">
                <div class="ui form">
                    <div class="two fields">
                        <div class="field" id="zorderdiv" style="visibility:visible;height:150px;">
                            <label>上传凭证：</label>
                            <img class="ui medium rounded image" id="zorderBillImg" src="">
                            <input type="hidden" name="zorderBill" id="zorderBill">
                            <div class="ui labeled input" style="margin-top:10px;">
                                  <button class="ui button blue" id="upload">上传凭证</button><br>
                                  <span>请上传小于5M、清晰、发货信息照片</span>
                            </div>
                        </div>       
                        <div class="field">
                            <div class="field" id="zorderdiv">
                                <label>备注信息：</label>
                                <textarea name="sendComment" style="width:549px;height:150px; max-width:550px;max-height:155px"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <  
            <div><a class="ui button green"  id="add" @click="sendProdSubmit" style="display: block; width: 133px; margin: 0px auto; ">确认</a></div>

            <!-- <input type="hidden" name="orderId" value="${info.orderId}">
            <input type="hidden" name="orderVehicles" id="orderVehicles">
            <h4 class="ui header">买家：${info.userName}</h4>
            <div class="ui divider"></div>
            <div class="column ">
                <div class="fl-lf " style="width:50%"> </div>
                <div class="right item fl-rg" style="width:40%">
                    <span class="ui inverted ">总量:${info.orderNumber}</span>
                    <span class="ui inverted ">已发货:${info.deliverNumCount}</span>
                    <span class="ui inverted "    id="infoRestNum">当前库存:${info.restNum}<span>

                </div>
                <br><br>
               
            </div>
            <div class="colunm ">
                <div class="ui form">
                     <div class=" two fields">
                        <div class="field">
                            <div class="ui labeled input"><div class="ui label">发货量: </div> <input type="text" name="zorderNum" placeholder="" id="zorderNum" onkeyup="change()">
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
                <div><a class="ui button blue"  id="add" onclick="add()" style="display: block; width: 133px; margin: 0px auto; ">选择司机</a></div> -->

                <!--添加新司机弹框-->

                <!-- <div class="ui modal "  id="vehiclepage">
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
                                   <input  class="vh" placeholder=" "  id="vehicleSize"  v-model="vehicle.vehicleActualSize" type="number" onchange="validedTex(this)"><span> 容量：<span >{{vehicle.vehicleSize}}</span> 顿</span></td>
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
                 添加新司机弹框end

           <div class="ui form" style="width: 550px;height:200px">
                  <div class="field">
                <label>备注信息：</label>
                <textarea name="sendComment" style="width:549px;height:150px; max-width:550px;max-height:155px"></textarea>
                  </div>
            </div>
            <div class=" ui column">   
                <div class="field">
                    <div class="ui labeled input">
                        <div class="ui label">提货时间： </div>
                        <input type="text" name="zorderDate" placeholder="" value=""  class="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',})">
                    </div>
                </div>
            </div>
            <br>
            
            <div class=" two fields">
                        <div class="field" id="zorderdiv" style="visibility: hidden">
                            <label>凭证：</label>
                            <img class="ui medium rounded image" id="zorderBillImg" src="">
                        </div>
                <input type="hidden" name="zorderBill" id="zorderBill">
                        <div class="field">
                            <div class="ui labeled input ">
                                  <button class="ui button blue" id="upload">上传凭证</button><span>请上传小于5M、清晰、发货信息照片</span>
                            </div>
                        </div>
            </div>
            <br>
             <div class=" ui column"> <label>收货地址：</label>${info.toAddress}</div>
        </form>
        <div><a class="ui button green"  id="add" @click="sendProdSubmit" style="display: block; width: 133px; margin: 0px auto; ">确认</a></div> -->
        
    </div>

</body>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script>
    $(document).ready(function () {
        new AjaxUpload('#upload', {
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
                $("#zorderdiv").css("visibility","visible");
                $("#zorderBillImg").attr("src",data.path);
                $("#zorderBill").val(data.path);
                return;
            }
        });
    });
    // $('#infoForm')
    // .form({
    //     inline : true,
    //     on: 'blur',
    //     fields: {
    //       zorderNum: {
    //         identifier : 'zorderNum',
    //         rules: [
    //           {
    //             type   : 'empty',
    //             prompt : '请输入你的发货量'
    //           }
    //         ]
    //       }
    //     }
    // });
    var form = $('#updateform');
    $(document).ready(function(){
        form.bootstrapValidator({
            field:{
                zorderNum:{
                    message:'发货量不合法',
                    validators:{
                        notEmpty:{
                            message:'发货量不能为空'
                        },
                    }
                }
            }
        });
        $("#add").click(function(){
            var bv = form.data('bootstrapValidator');
            bv.validata();
            if (bv.isValid()) {
                console.log(form.serialize());
                $.ajax({
                url: 'validator.json',
                async: false,//同步，会阻塞操作
                type: 'GET',//PUT DELETE POST
                data: form.serialize(),
                complete: function (msg) {
                    console.log('完成了');
                },
                success: function (result) {
                    console.log(result);
                    if (result) {
                        window.location.reload();
                    } else {
                        $("#returnMessage").hide().html('<label class="label label-danger">修改失败!</label>').show(300);
                    }
                }, error: function () {
                    $("#returnMessage").hide().html('<label class="label label-danger">修改失败!</label>').show(300);
                }
            })
            }
        })
    })
    
    // $(function(){
    //     // 检查模块，表单验证
    //     var validator = $("#infoForm").validate({
    //           errorPlacement: function(error, element) {
    //             if ($(element).closest('div.field').children().filter("div.error-div ").length < 1)
    //                 $(element).closest('div.field').append("<div class='error-div'></div>");
    //                 $(element).closest('div.field').children().filter("div.error-div").append(error);
    //         },
    //         rules:{
    //             zorderNum:{
    //                 required:true,
    //             },
    //             getTime:{
    //                  required:true,
    //             }

    //         },
    //         messages:{
    //             zorderNum:{
    //                 required:"请输入发货量",
    //             },
    //             getTime:{
    //                 required:"请输入提货时间" 

    //             }

    //         },
    //         onfocusout:true,

    //      });


    // })
  
    var vm= new Vue({
        el:'#app1',
        data:
        {
            Vehicles:[],
            Selected: [],
            SelectedVehicles:[]
        },
        mounted:function(){
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
                        window.location.href = "${pageContext.request.contextPath}/seller/order";
                    }
                },"json");
            }
        }
    })
</script>
<script>    
function add(){
    $('.modal').modal('show');
}


function change(){
    var infoRestNum=${info.restNum}
    var zorderNum=$("#zorderNum").val();
    if(zorderNum>infoRestNum){
        alert("当前库存不足！");
    }
    var num=infoRestNum-zorderNum;
    $("#infoRestNum").text("当前库存:"+num);
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
