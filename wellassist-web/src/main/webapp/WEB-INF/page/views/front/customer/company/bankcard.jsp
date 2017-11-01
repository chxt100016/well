<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>银行卡管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
</head>
<body>   
<div class="container1">
    <div style="margin:40px 0 0 210px;">

        <div class="content-rect" style="width:90%;" id="app1">
            <!-- <div style="border-bottom:solid 1px #d0d0d0;padding-bottom:10px;font-size:15px;font-weight: 600;">我的银行卡</div>
            <table>
                <tr>
                    <td><img src="img/u5445.jpg" /></td>
                    <td>{{card.bankName}}</td>
                    <td>{{card.bankAccount}}</td>
                    <td></td>
                    <td><button class="negative ui button" v-on:click="delCard(card)">删除</button></td>
                </tr>
            </table>
            <div style="border-bottom:solid 1px #d0d0d0;padding-bottom:10px;font-size:15px;font-weight: 600;">绑定银行卡</div>
            <form class="ui form">
                <div class="field inline">
                    <label>请选择你的银行</label>
                    <div class="ui selection dropdown">
                        <input name="gender" type="hidden">
                        <i class="dropdown icon"></i>
                        <div class="default text" class="vh" v-model="new_card.bankName " id="hanging" >请选择你的银行</div>
                        <div class="menu">
                            <div class="item" data-value="1">中信银行</div>
                            <div class="item" data-value="0">工商银行</div>
                        </div>
                    </div>
                </div>
                <div class="field inline">
                    <label>请输入你的银行卡号</label>
                    <input name="cardID" placeholder="请输入银行卡号" class="vh" v-model="new_card.bankAccount " id="capacity"  type="text">
                </div>
                <div class="field inline">
                    <label>请输入银行卡预留手机号</label>
                    <input name="caraPhone" placeholder="请输入银行卡预留手机号" type="text">
                    <a class="ui primary button ">获取验证码</a>
                </div>
                <div class="field inline">
                    <label>请输入你的验证码</label>
                    <input name="code" placeholder="请输入你的验证码" type="text">
                </div>
                <a class="ui primary button " @click="createCard">添加</a>
            </form>
                    <ul v-if="!isValid" class="errors">
                        <p style="color:#234">填写时请注意：</p>
                        <li v-show="!validation.bankName">请输入正确的银行名称</li>
                        <li v-show="!validation.dr_number">请输入银行卡号</li>
                    </ul> -->
            <h4 class="ui header" style="font-size:15px;font-weight:600;">我的银行卡</h4>
            <div class="ui divider"></div>
            <table class="ui table " style="width:85%;border:0;text-align:center;">
                <tbody>
                    <tr v-for="card in Cards " v-cloak>
                        <td width="8%">
                            <!-- <input type="hidden" v-model="card.bankcardId"/> -->
                            <img src="../img/u5445.jpg" width="30" height="30" />
                        </td>
                        <td width="10%">
                            <span style="font-size:15px;">{{card.bankName}}</span>
                        </td>
                        <td width="15%"><span style="font-size:15px;">{{card.account}}</span></td>
                        <td><a class="ui button red" v-on:click="delCard(card.bankcardId)">删除</a></td>
                    </tr>
                </tbody>
            </table>

            <h4 class="ui header" style="font-size:15px;font-weight:600;margin-top:40px;">绑定银行卡</h4>
            <div class="ui divider"></div>
            <!-- <a class="ui button blue" id="add" onclick="add()" style="display: block; width: 133px; margin: 0px auto; ">添加新银行卡</a> -->
            <div class="ui" id="vehiclepage">
            <form class="ui form has_vehicle_form">
            <!-- <i class="close icon"></i> -->
            <!-- <div class="header">添加新银行卡信息</div> -->
                <div class="ui form ">
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入你的开户银行</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankName " id="bankName" name="bankName">
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入你的银行卡号</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.account " id="account" name="account">
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入你的银行账户名</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.accountName " id="accountName" name="accountName">
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请选择您的证件类型</label>
                        <div class="ui input ">
               
                            <select class="ui compact selection dropdown vh" v-model="new_card.certType " id="certType" name="certType" >
                                <option value="1">身份证</option>
                                <option selected="" value="2">军官证</option>
                                <option value="3">学生证</option>
                              </select>
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入您的证件号</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.certNum " id="certNum" name="certNum">
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入银行预留手机号</label>
                        <div class="ui input ">  
                            <input type="text " class="vh" placeholder=" " v-model="new_card.phone " id="phone" name="phone">
                            <a class="ui button" style="margin-left:15px;">获取验证码</a>
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入开户行支付联行号</label>
                        <div class="ui input ">  
                            <input type="text " class="vh" placeholder=" " v-model="new_card.openBankTgfi " id="openBankTgfi" name="openBankTgfi">
                           
                        </div>
                    </div><div class="field inline">
                        <label style="width:15%;text-align:right;">请输入开户行名</label>
                        <div class="ui input ">  
                            <input type="text " class="vh" placeholder=" " v-model="new_card.openBankName " id="openBankName" name="openBankName">
                          
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入验证码</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankCode " id="bankcode" name="">
                        </div>
                    </div>

                    <div class="field inline">
                        <label style="width:18.5%;"></label>
                        <a class="ui primary button " @click="createCard" style="height:38px">添加 </a>
                    </div>
                 
                </div>
            </div>
            <!-- 添加新司机弹框end -->
        </div>
        
        
    </div>
</div>
</body>
<script>
    $(function(){
        $('.has_vehicle_form').form({
       on:'blur',
       inline : true,
         fields:{
            bankName :{
                identifier:'bankName',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记银行卡哦'
                }]
            },
            account :{
                identifier:'account',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            accountName :{
                identifier:'accountName',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            certType :{
                identifier:'certType',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            certNum :{
                identifier:'certNum',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            phone :{
                identifier:'phone',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            openBankTgfi :{
                identifier:'openBankTgfi',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            openBankName :{
                identifier:'openBankName',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
         },
        
      
      
      
      
      
  })
    })
</script>
<script>
     const getUrl='${pageContext.request.contextPath}/userinfo/getCards';
     const addUrl="${pageContext.request.contextPath}/userinfo/addBankcard";
    var vm = new Vue({
        el: app1,
        data: {
            new_card: {
                bankName: '',
                account: '',
                bankPhone: '',
                bankCode: ''
            },
            Cards:[] ,
           
        },
        computed: {
            validation: function() {
                return {
                    bankName: !!this.new_card.bankName.trim(),
                    bankAccount: !!this.new_card.account.trim(),
                    bankPhone: !!this.new_card.bankPhone.trim(),
                    bankCode: !!this.new_card.bankCode.trim(),
                }
            },
            isValid: function() {
                var validation = this.validation
                return Object.keys(validation).every(function(key) {
                    return validation[key]
                })
            }
        },
        created:function () {
            // $.get(addUrl,function(data){
            //     if(typeof data ==="object"){
            //         vm.Cards = data.Cards;
            //     }else {
            //         vm.Cards = JSON.parse(data).Cards;
            //     };
            // });
            $.ajax({
                    type:'get' , 
                    url:getUrl,
                    data:'',
                    dataType:'json',
                    success:
                            function(result){
                                    if(result.code==0){
                                        vm.Cards = result.Cards;
                                        console.log(result.Cards)
                                    }
                                    else{
                                                console.log(result.msg)
                                        }
                                            }


                    })
        },
        methods: {
            createCard: function() {
                let  allFields =$('.has_vehicle_form').form('get values');
                let alls=JSON.stringify(allFields)
                     console.log(typeof alls)
                   if( $('.has_vehicle_form').form('is valid')){
                 
                     
                     console.log(allFields)
                   
                    
                    //项后台发送ajax请求，完成数据存储
                    // $.post(addUrl,allFields,function (data) {
                    //     if(data.code == 0){
                    //         // alert(JSON.stringify(this));
                    //         vm.Cards.push(vm.new_card);
                    //         // 添加完newPerson对象后，重置newPerson对象
                    //         vm.new_card = {
                    //             bankName: '',
                    //             account: '',
                    //             bankPhone: '',
                    //             bankCode: ''
                    //         };
                    //         alert("添加成功");
                    //     }else{
                    //         alert("系统错误");
                    //     }
                    // },"json")
                    $.ajax({
                            type:'post', 
                            url:addUrl,
                            data:alls,
                            dataType:'json',
                            contentType:'application/json',
                            success:
                                    function(result){
                                            if(result.code==0){
                                                alert("添加成功");
                                            }
                                            else{
                                                alert("系统错误");
                                                        console.log(result.msg)
                                                }
                                                    }


                            })
                        }
                    $('.modal').modal('hide');
                

            },
            delCard: function(card) {
                console.log(card);
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/userinfo/delBankcard',
                    dataType:'json',
                    data:{bankcardId:card},
                    success: function(r){
                        if(r.code == 0){
                            alert("成功删除");
                            vm.Cards.splice(vm.Cards.indexOf(card), 1);
                        }else{
                            alert(r.msg);
                        }
                    }
                });
               
            },
        }

    })
</script>

<!-- <script>
    function add() {
        $('.modal')
            .modal('show');
    }

    function close_m() {
        $('.modal')
            .modal('hide');
    }
</script> -->

</html>

<%@ include file="../footer.jsp"%>
