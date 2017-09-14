<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
        <div class="container1">
            <div class="container2">
                <div class="ui container segment" id='bb'>
                    <div class=" ui items column">
                        <div class="column">
                            <h4>订单金额结算信息</h4>
                            <div class="ui divider"></div>
                        </div>
                        <div class="ui container pd-10 pd-lf-30">
                            <span class="ft-sz-15">注：平台交易订单秉持多退少补原则，已支付金额少于实际交易金额时，请先完成支付，再进行结算操作</span>
                            <table class="ui table">
                                <tr>
                                    <td>实际金额： </td>
                                    <td>已支付金额： </td>
                                </tr>

                                <tr>
                                    <td>账户余额 ：<span id='balance'></span>元</td>
                                    <td>授信余额：
                                        <c:if test="${userSumCredit!=0}">元</c:if>
                                        <!-- <c:if test="${userSumCredit==0}">未授信</c:if> -->
                                    </td>
                                </tr>
                                <tr>
                                    <td>订单内容:</td>
                                    <td>状态：</td>
                                </tr>
                            </table>
                        </div>
                        <br>
                        <div class="column">
                            <div class="ui button primary "> 结算</div>

                        </div>
                    </div>

                </div>
            </div>
        </div>


<script>
  
    $(function(){
     
      


    })
    </script>

    <script>
          const balanceUrl='${pageContext.request.contextPath}/finance/balance';
          const creditUrl='${pageContext.request.contextPath}/finance/creditBalance';
          const vm =new Vue({
              el:'#bb',
              created:function(){
                  let that= this;
                $.get(balanceUrl,'',function(result){
                   if(result.code==0){
                       that.balance=result.balance;
                     }else{
                          console.log(result.msg)
                     }
                         }) 

              },
               data:{
                    balance:'',

               },
          })
        </script>
<%@ include file="../footer.jsp"%>