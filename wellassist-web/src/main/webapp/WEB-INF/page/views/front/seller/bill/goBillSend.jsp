<%@ include file="../header.jsp"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
        <div class="container1">
            <div class="container2">
                <div class="ui container segment" id='sb2'>
                    <div class="column">
                        <h4>订单详情</h4>
                        <div class="ui divider"></div>
                    </div>
                    <table class="ui celled table" >
                        <thead>
                          <tr><th>#</th>
                              <th>商品名:</th>
                              <th>订单编号:</th>
                              <th>申请用户:</th>
                              <th>金额:</th>
                              <th>完成时间:</th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item,index) in list">
                                <td>{{index+1}}</td>
                                <td>{{item.prodName}}</td>
                                <td>{{item.orderNo}}</td>
                                <td>{{item.customerUserName}}</td>
                                <td>{{item.saleSjMoney}}</td>
                                <td>{{item.completeDate}}</td>
                            </tr>
                            
                        </tbody>
                        <tfoot>
                                <tr>
                                        <th>共：{{list.length}}笔订单</th>
                                        <th colspan="7">
                                            总计：{{billMoney}}元
                                        </th>
                                    </tr>
                        </tfoot>
                        
                    </table>
                    <br>
                    <div class="column">
                            <h4>发票详情</h4>
                            <div class="ui divider"></div>
                    </div>

                    <table class="ui table celled">
                        <tbody>
                            <tr>
                                <td>发票抬头：{{billInfo.receiveCompanyName}}</td>
                                <td>发票类型：
                                    <span v-if='billInfo.billType==1'>普通发票</span>
                                    <span v-if='billInfo.billType==2'>增值税发票</span>
                                </td>
                                <td>发票金额：{{billInfo.billMoney}} 元</td>
                            </tr>
                            <tr>
                                    <td>联系人：{{billInfo.receiveName}}</td>
                                    <td>联系地址：{{billInfo.receiveAddress}}</td>
                                    <td>纳税识别号：{{billInfo.receiveSh}}</td>
                            </tr>
                            <tr>
                                    <td>联系电话：{{billInfo.receivePhone}}</td>
                                    <td>其他信息：</td>
                                    <td>其他信息：</td>
                            </tr>
                        </tbody>
                        
                    </table>
                      <br>
                     <div class="column">
                            <h4>开具发票信息填写</h4>
                            <div class="ui divider"></div>
                    </div>
                    <div class="ui segment">
                        <form class="ui form " id='sendbill'>
                                <div class="inline fields">
                                        <label for="fruit">发票种类:</label>
                                        <div class="field">
                                            <div class="ui radio checkbox">
                                                <input type="radio" name="billType" checked="" value="1" data-tab="billclass1">
                                                <label>线上发票</label>
                                            </div>
                                        </div>
                                        <div class="field">
                                            <div class="ui radio checkbox">
                                                <input type="radio" name="billType" tabindex="0" value="2"  data-tab="billclass2" disabled="disabled">
                                                <label>线下发票</label>
                                            </div>
                                        </div>
        
                                </div>
                                <div class="two fields">
                                        <div class="field">
                                            <label>发票编号</label>
                                            <input type="text" name="receiveName" placeholder="收件人">
                                        </div>
                                        <div class="field">
                                            <label>上传发票</label>
                                            <input type="file" name="receivePhone" placeholder="联系电话">
                                        </div>
                                </div>
                                <div class="ui button submit primary"> 提交  </div>
                                <div class="ui button cancel"> 返回 </div>
                                 
                        </form>

                    </div>


                </div>

            </div>

        </div>
        ${param.billId}
    <script>
         const url1='${pageContext.request.contextPath}/bill/billOrders',
         url2='${pageContext.request.contextPath}/bill/bill'
         billId='${param.billId}';
         const ding = new Vue({
                el: sb2,
                data: {
                    list: '',
                    billMoney: '',
                    billInfo:''

                },
                created: function () {
                 
                    let ids = '${param.ids}';
                    let that = this;
                    // 订单信息
                    $.ajax({
                        type: 'get',
                        url: url1,
                        data: { 'billId': billId },
                        dataType: 'json',
                        success:
                        function (result) {
                            if (result.code == 0) {
                                // console.log(result);
                                that.list = result.items;
                               
                                //    总计
                                let sum = 0;
                                for (var i = 0; i < that.list.length; i++) {
                                    sum += Number(that.list[i].saleSjMoney)
                                }
                                that.billMoney = sum;
                                // console.log(sum);
                            }
                            else {
                                console.log(result.msg)
                            }
                        }


                    });
                    // 发票信息
                    $.ajax({
                        type:'get', 
                        url:url2,
                        data: { 'billId': billId },
                        dataType:'json',
                        success:
                                function(result){
                                        if(result.code==0){
                                            console.log(result.bill);
                                            that.billInfo=result.bill

                                        }
                                        else{
                                                    console.log(result.msg)
                                            }
                                                }


                        })

                

                },


            })
       
    </script>
    <script>
        $(function () {
            $('#sendbill').tab();
        })
    </script>
        <%@ include file="../footer.jsp"%>