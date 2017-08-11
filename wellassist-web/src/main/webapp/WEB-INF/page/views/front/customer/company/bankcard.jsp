<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>银行卡管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>
</head>
<div style="min-height: 990px;width: 100%;">
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
                        <td width="15%"><span style="font-size:15px;">{{card.bankAccount}}</span></td>
                        <td><a class="ui button red" v-on:click="delCard(card)">删除</a></td>
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
                <div class="ui form has_vehicle_form">
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入你的开户银行</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankName " id="hanging" name="bankName">
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入你的银行卡号</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankAccount " id="capacity" name="bankAccount">
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入银行预留手机号</label>
                        <div class="ui input ">  
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankPhone " id="capacity" name="bankPhone">
                            <a class="ui button" style="margin-left:15px;">获取验证码</a>
                        </div>
                    </div>
                    <div class="field inline">
                        <label style="width:15%;text-align:right;">请输入验证码</label>
                        <div class="ui input ">
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankCode " id="capacity" name="bankcode">
                        </div>
                    </div>

                    <div class="field inline">
                        <label style="width:18.5%;"></label>
                        <a class="ui primary button " @click="createCard" style="height:38px">添加 </a>
                    </div>
                    <ul v-if="!isValid" class="errors">
                        <p style="color:#234">填写时请注意：</p>
                        <li v-show="!validation.bankName">请输入正确的银行名称</li>
                        <li v-show="!validation.bankAccount">请输入正确的银行卡号</li>
                        <li v-show="!validation.bankPhone">请输入正确的银行预留手机号</li>
                        <li v-show="!validation.bankCode">请输入正确的验证码</li>
                    </ul>
                </div>
            </div>
            <!-- 添加新司机弹框end -->
        </div>
        <div>
        

        
    </div>
</div>
<script>
// $('.ui.form').form({
//     inline : true,
//     on     : 'blur'
//     fields : {
//         bankName:{
//             identifier: 'bankName',
//             rules: [
//               {
//                 type   : 'empty',
//                 prompt : '请输入你的开户银行'
//               }
//             ]
//         },
//         bankAccount:{
//             identifier: 'bankAccount',
//             rules: [
//               {
//                 type   : 'empty',
//                 prompt : '请输入你的银行卡号'
//               }
//               {
//                 type   : 'minLength[16]',
//                 prompt : '请输入正确的银行卡号'
//               }
//             ]
//         },
//         bankPhone:{
//             identifier: 'bankPhone',
//             rules: [
//               {
//                 type   : 'empty',
//                 prompt : '请输入银行卡预留手机号'
//               }
//             ]
//         },
//         bankCode:{
//             identifier: 'bankCode',
//             rules: [
//               {
//                 type   : 'empty',
//                 prompt : '请输入你的验证码'
//               }
//             ]
//         },
//     }
//   })
// ;
    var vm = new Vue({
        el: app1,
        data: {
            new_card: {
                bankName: '',
                bankAccount: '',
                bankPhone: '',
                bankCode: ''
            },
            Cards:[] ,
           
        },
        computed: {
            validation: function() {
                return {
                    bankName: !!this.new_card.bankName.trim(),
                    bankAccount: !!this.new_card.bankAccount.trim(),
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
            $.get('${pageContext.request.contextPath}/userinfo/getCards',function(data){
                if(typeof data ==="object"){
                    vm.Cards = data.Cards;
                }else {
                    vm.Cards = JSON.parse(data).Cards;
                };
            });
        },
        methods: {
            createCard: function() {
                if (this.isValid) {
                    //项后台发送ajax请求，完成数据存储
                    $.post("${pageContext.request.contextPath}/userinfo/addBankcard",vm.new_card,function (data) {
                        if(data.code == 0){
                            // alert(JSON.stringify(this));
                            vm.Cards.push(vm.new_card);
                            // 添加完newPerson对象后，重置newPerson对象
                            vm.new_card = {
                                bankName: '',
                                bankAccount: '',
                                bankPhone: '',
                                bankCode: ''
                            };
                            alert("添加成功");
                        }else{
                            alert("系统错误");
                        }
                    },"json")
                    // $('.modal').modal('hide');
                };

            },
            delCard: function(card) {
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/userinfo/delBankcard',
                    dataType:'json',
                    data:card,
                    success: function(r){
                        if(r.code == 0){
                            alert("成功删除");
                        }else{
                            alert(r.msg);
                        }
                    }
                });
                vm.Cards.splice(vm.Cards.indexOf(card), 1);
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
