<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="container1">
    <div class="container2">
        <div class="ui segment container" id='app2'>
             <h4>还款明细</h4>
             <div class="ui divider"></div>
                <div class="ui equal width grid">
                    <div class="column">订单编号：{{loan.order_no}}</div>
                    <div class="column">还款状态:
                         <span v-if='loan.loan_state==2'><i class="hourglass start icon redxx"></i>待还款</span>
                        <span v-if='loan.loan_state==21'><i class="hourglass start icon redxx"></i>还款处理中</span>
                         <span  v-if='loan.loan_state==3 ||loan.loan_state==4'><i class="check circle outline icon greenxx" style="font-size:16px"></i> 已还清 </span>
                    </div>
                    <div class="column">借款时间：{{loan.loan_date}} </div>
                </div>
                <div class="ui equal width grid">
                    <div class="column">借款金额：<span class="ft-wt-bd ft-sz-16"> {{loan.loan_money}}</span>元</div>
                    <div class="column">产生利息：{{loan.lixi_money}}元</div>
                    <div class="column">还款期限：{{loan.payment_date}}</div>
                </div>
                 <div class="ui equal width grid">
                    <div class="column">已还本金：<span class="ft-wt-bd ft-sz-16">{{loan.repay_money}}</span> 元</div>
                    <div class="column">已还利息：{{loan.repay_lixi}}元</div>
                    <div class="column">余款：{{loan.remain_repay_money+loan.remain_lixi_money}} 元</div>
                </div>
                <br>
                <table class="ui single line  table ">
                        <thead>
                                <tr >
                                    <th >还款时间</th>
                                    <th >还款方式</th>
                                    <th >还款本金</th>
                                    <th >还款滞纳金</th>
                                    <th >还款利息</th>
                                    <th>借款利率</th>
                                </tr>
            
                            </thead>
                            <tbody v-if='loan.repays.length!=0'>
                                        <tr v-for='item in loan.repays'>
                                            <td>{{item.repayDate}}</td>
                                            <td>余额支付</td>
                                            <td>{{item.repayMoney}}元</td>
                                            <td>{{item.repayOverdueFine}}元</td>
                                            <td>{{item.repayInterestMoney}}元</td>
                                            <td>5‱</td>
                                        </tr>                                                                                               
                            </tbody>
                            <div v-if='loan.repays.length==0'>暂无还款记录</div>
                    
                </table>
                <br>
                <div  class="ui button basic" onclick="window.history.go(-1)">返回</div>
        </div>

    </div>

</div>
<script>
    $(function(){

    
    const url1 ='${pageContext.request.contextPath}/finance/loanRepayDetail';
    const loanId=${param.loanId};
    var vm = new Vue({
        el:'#app2',
        data:{
            loan:{},
                      
        },
        mounted:function(){
            let that =this;
            $.ajax({
                type:'get', 
                url:url1,
                data:{'loanId':loanId},
                dataType:'json',
                // contentType:'application/json',
                success: function(result){
                     if(result.code==0){
                     that.loan = result.loan;
                     console.log(that.loan)
                     }
            else{
                alert('无法获取')
                    
                }
                }
            });
        }
    })
})
</script>