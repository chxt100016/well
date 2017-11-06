<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title></title>
    <%--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>

    <style>
        .ui.form .inline.field .prompt,
        .ui.form .inline.fields .field .prompt {
            vertical-align: middle;
        }
        [v-cloak]{
            display: block;
        }
    </style>
</head>

<body>
    <div class="container1">
        <div class="container2">

        
    <div class="ui container segment" id="app1" style="width:990px">
        <h3>还款</h3>
        <div class="ui divider"></div>
        <div class="ui three  grid" v-cloak>
           
                <div class="three wide column">未还款:<span>{{repaymentInfo.Amounts}} 元</span></div>
                <div class="three wide column">利息:{{repaymentInfo.Interest}} 元</div>
            <div class="three wide column">滞纳金:{{repaymentInfo.OverdueFine}} 元&nbsp<a href="">详情</a></div>
                <div class="three wide column">还款期限:{{repaymentInfo.Deadline}}</div>
           
        </div>
        <form class="ui form segment repayment">


            <div class=" inline fields">
                <label for="">还款金额：</label>
                <div class="field">
                    <div class="ui input focus">
                        <input type="text" id="repaymentAmount" placeholder="还款金额" name="repaymentAmount" @change="valid('repay',$event)" >
                       
                    </div>
                     
                </div>
                <span>还款本金：{{repays.Pincepal}} 元  <span id='overdueFine'> ; 归还滞纳金：{{repays.overdueFine}}</span><span id="repayprin"></span> 还款利息：{{repaymentInfo.Interest}} 元</span>
            </div>
            <div class="inline fields">
                <label>还款方式：</label>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="payment" checked="checked" value="1"  >
                        <label>余额支付</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui radio checkbox">
                        <input type="radio" name="payment" disabled="disabled" value="2">
                        <label>银行卡</label>
                    </div>
                </div>

            </div>
        </form>
            <div class="inline fields ui centered grid">
                <div class="six wide tablet eight wide computer column">
                    <div type="" class="ui primary button submit" @click="repaySubmit">提交</div>
                    <div type="" class="ui positive button" @click="goback">返回</div>
                </div>
            </div>
    </div>

    </div>
</div>
    </div>
</body>
<script>
    $(function(){
        var a=1.0005;
      let b= a^2;
      console.log(b)
    $('.repayment')
        .form({
            fields: {
                repaymentAmount: {
                    identifier: 'repaymentAmount',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '请输入还款金额'
                        },
                        {
                            type: 'number',
                            prompt: '务必输入正确的金额'
                        },
                        // {
                        //     type: 'minCount[2]',
                        //     prompt: '不能小于0哦'
                        // },

                    ]
                },

            },
            inline: true,
            on: 'blur'
        })
})
</script>
<script>
    var mockdata={
        repaymentInfo:{
               Amounts:'${loan.remainRepayMoney+loan.remainLixiMoney+loan.remainOverdueFine}',
               Interest:'${loan.remainLixiMoney}',
               OverdueFine:'${loan.remainOverdueFine}',
               Deadline:'<fmt:formatDate value="${loan.paymentDate}" pattern="yyyy-MM-dd"/>',
               loanId:'${loan.loanId}',
        },
        repays:{
                Pincepal:'0',
                overdueFine:'0'
        }
    };
    var vm= new Vue({
        el:"#app1",
        data:mockdata,
        methods:{
            valid:function(msg,event){
                let repays=Number(event.currentTarget.value);
                let intersts=Number(this.repaymentInfo.Interest);
                let amount=Number(this.repaymentInfo.Amounts);
                let overdueFine= Number(this.repaymentInfo.OverdueFine);
                let midDue= Number(intersts+overdueFine)
                console.log(repays);
                console.log(intersts);
                if(repays < intersts){
                  console.log('太小了');
                 
                  this.repays.Pincepal= 0;
                   event.currentTarget.value = intersts;
                   console.log("new"+repays);
                }
                if(repays > amount){
                    event.currentTarget.value = amount;
                    
                     console.log('太多了');
                     this.repays.Pincepal=(event.currentTarget.value-intersts-overdueFine).toFixed(2);
                     $('#overdueFine').val(overdueFine);
                     this.repays.overdueFine=overdueFine;
                       
                }
                else{
                    console.log("可以");
                    let repayFir=(event.currentTarget.value-intersts).toFixed(2)
                    this.repays.overdueFine=repayFir;
                    $('#overdueFine').val(repayFir);
                    if(repayFir >overdueFine){
                        this.repays.overdueFine=overdueFine;
                        $('#overdueFine').val(overdueFine);
                        this.repays.Pincepal=(event.currentTarget.value-intersts-overdueFine).toFixed(2);
                    }
                    //  this.repays.Pincepal=(event.currentTarget.value-intersts).toFixed(2);
                }
            },
            repaySubmit:function(){
                $.post('${pageContext.request.contextPath}/customer/repayLoan',
                {loanId:this.repaymentInfo.loanId,repayMoney:$('#repaymentAmount').val(),interest:this.repaymentInfo.Interest,overdueFine:$('#overdueFine').val()},
                function(data){
                    if(data.code==0){
                        alert("还款处理中...");
                        window.history.go(-1);
                    }else{
                        alert(data.msg);
                    }
                },'json');
            },
            goback:function(){
                window.history.go(-1);
            }
        }
    })
</script>

<%@ include file="../footer.jsp"%>