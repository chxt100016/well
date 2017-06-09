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
    <div class="content-rect" id="app1">
        <h3 class="ui header">银行卡管理</h3>
        <div class="ui divider"></div>
        <table class="ui celled padded table " style="width:600px">
            <thead>
                <tr>
                    <th class="single line ">银行</th>
                    <th width="40%">卡号</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="card in Cards " v-cloak>
                    <td>
                        <input type="hidden" v-model="card.bankcardId"/>
                    </td>
                    <td>
                        <h2 class="ui center aligned header xs " id="x">{{card.bankName}}</h2>
                    </td>
                    <td class="single line ">{{card.bankAccount}}</td>
                    <td class="right aligned " style="width:10% "><a class="ui button red" v-on:click="delCard(card)">DELETE </a></td>
                </tr>

            </tbody>
        </table>
        <div><a class="ui button blue" id="add" onclick="add()" style="display: block; width: 133px; margin: 0px auto; ">添加新银行卡</a></div>

        <div class="ui modal " id="vehiclepage">
            <!--<form class="ui form segment has_vehicle_form">-->
            <i class="close icon"></i>
            <div class="header">添加新银行卡信息</div>

            <div class="ui form has_vehicle_form">

                <div class="three fields ">
                    <div class="field ">
                        <div class="ui labeled input ">
                            <div class="ui label ">银行 </div>
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankName " id="hanging" name="">
                        </div>
                    </div>
                    <div class="field ">
                        <div class="ui labeled input ">
                            <div class="ui label ">卡号 </div>
                            <input type="text " class="vh" placeholder=" " v-model="new_card.bankAccount " id="capacity" name="">
                        </div>
                    </div>

                    <a class="ui primary button " @click="createCard" style="height:38px">添加 </a>

                </div>
                <ul v-if="!isValid" class="errors">
                    <p style="color:#234">填写时请注意：</p>
                    <li v-show="!validation.bankName">请输入正确的银行名称</li>
                    <li v-show="!validation.dr_number">请输入银行卡号</li>
                </ul>
            </div>
        </div>
        <!--添加新司机弹框end-->


    </div>
<script>
    var vm = new Vue({
        el: app1,
        data: {
            new_card: {
                bankName: '',
                bankAccount: ''
            },
            Cards:[] ,
        },
        computed: {
            validation: function() {
                return {
                    bankName: !!this.new_card.bankName.trim(),
                    bankAccount: !!this.new_card.bankAccount.trim(),
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
            $.get('./getCards',function(data){
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
                    $.post("./addBankcard",vm.new_card,function (data) {
                        if(data.code == 0){
                            alert(JSON.stringify(this));
                            vm.Cards.push(vm.new_card);
                            // 添加完newPerson对象后，重置newPerson对象
                            vm.new_card = {
                                bankName: '',
                                bankAccount: ''
                            };
                            alert("添加成功");
                        }else{
                            alert("系统错误");
                        }
                    },"json")
                    $('.modal').modal('hide');
                };

            },
            delCard: function(card) {
                $.ajax({
                    type:'post',
                    url:'./delBankcard',
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

</html>

<%@ include file="../footer.jsp"%>
