<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="container1">
        <div class="container2">
            <div class="ui container segment" id='sb2'>
                <h4>出票信息</h4>
                <div class="ui divider"></div>
                <div class="ui container segement" >
                    <table class="ui table">
                        <thead>

                            <tr>
                                <th>#</th>
                                <th width='10%'>品类</th>
                                <th width='20%'>出货方</th>
                                <th>订单号</th>
                                <th>详情</th>
                                <th>交易时间（完成时间）</th>
                                <th>金额</th>
                                <th>状态</th>
                            </tr>

                        </thead>
                        <tbody>
                            <tr v-for='(item,index) in list'>
                                <td>{{index+1}}</td>
                                <td>{{item.prodName}}</td>
                                <td>{{item.senderUserName}}</td>
                                <td>{{item.orderNo}}</td>
                                <td><a href="">详情 </a></td>
                                <td>{{item.completeDate}}</td>
                                <td>{{item.prePayment}}</td>
                                <td>已选择</td>
                            </tr>
                        </tbody>
                        <tfoot class="full-width">
                            <tr>
                                <th>已选：{{list.length}}笔订单</th>
                                <th colspan="7">
                                    总计：{{billMoney}}元
                                </th>
                            </tr>
                        </tfoot>
                    </table>

                </div>
                <br>


                <div class="ui container pd-10 pd-lf-30 segment">

                    <div class="column">
                        <h4>收票信息</h4>
                        <div class="ui divider"></div>
                    </div>
                    <form class="ui form" id='billform'>
                        <input type="hidden" name="logisticsInfoIds" value="${param.ids}">
                        <input type="hidden"  name="supplierId" value="" v-model='supplierId'>
                        <input type="hidden"  name="billMoney" value="" v-model='billMoney'>
                        <input type="hidden"  name="orderType" value="2" >
                        <div class="inline fields">
                            <label for="fruit">发票类型:</label>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="billType" checked="" value="1">
                                    <label>普通发票</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="billType" tabindex="0" value="2">
                                    <label>增值税专用发票</label>
                                </div>
                            </div>

                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>收件人</label>
                                <input type="text" name="receiveName" placeholder="收件人" v-model='info.receiveName'>
                            </div>
                            <div class="field">
                                <label>联系电话</label>
                                <input type="text" name="receivePhone" placeholder="联系电话" v-model='info.receivePhone'>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>开户行</label>
                                <input type="text" name="bankName" placeholder="开户行" v-model='info.bankName'>
                            </div>
                            <div class="field">
                                <label>开户行账号</label>
                                <input type="text" name="bankAccount" placeholder="开户行账号" v-model='info.bankAccount'>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>公司抬头</label>
                                <input type="text" name="receiveCompanyName" placeholder="公司抬头" v-model='info.receiveCompanyName'>
                            </div>
                            <div class="field">
                                <label>地址信息</label>
                                <input type="text" name="receiveAddress" placeholder="地址信息" v-model='info.receiveAddress'>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>纳税人识别号</label>
                                <input type="text" name="receiveSh" placeholder="纳税人识别号" v-model='info.receiveSh'>
                            </div>

                        </div>
                        <div class="ui submit button pink">提交</div>
                        <div class="ui cancel button grey" onclick="history.go(-1)">返回</div>
                    </form>
                </div>
               
           
            </div>
            <!-- ${param.ids}   -->

        </div>

    </div>
    <script>
            const ding = new Vue({
                el: sb2,
                data: {
                    list: '',
                    billMoney: '',
                    supplierId:'',
                    info:''

                },
                created: function () {
                    let url = '${pageContext.request.contextPath}/customer/billLogisticss';
                    let ids = '${param.ids}';
                    const url2='${pageContext.request.contextPath}/customer/billAddress';
                    let that = this;
                    $.ajax({
                        type: 'get',
                        url: url,
                        data: { 'ids': ids },
                        dataType: 'json',
                        success:
                        function (result) {
                            if (result.code == 0) {
                                console.log(result);
                                that.list = result.items;
                                that.supplierId=result.items[0].senderUserId;
                                //    总计
                                let sum = 0;
                                for (var i = 0; i < that.list.length; i++) {
                                    sum += Number(that.list[i].prePayment)
                                }
                                that.billMoney = sum;
                                // console.log(sum);
                            }
                            else {
                                console.log(result.msg)
                            }
                        }


                    });
                       //基本信息
                        $.ajax({
                        type:'get', 
                        url:url2,
                        data:'',
                        dataType:'json',
                        success:
                                function(result){
                                        if(result.code==0){
                                        ding.info=result.billAddress;
                                          console.log(ding.info)
                                        }
                                        else{
                                            alert('加载失败，请稍后..')
                                            history.go(-1);
                                                    console.log(result.msg)
                                            }
                                                }


                        })

                },


            })
        </script>
        <script>
            $(function () {
                $('#billform').form({
                    on: 'blur',
                    inline: true,
                    fields: {
                        billType: {
                            identifier: 'billType',
                            rules: [{
                                type: 'empty',
                                prompt: '请选择类型'
                            }]
                        },
                        receiveSh: {
                            identifier: 'receiveSh',
                            rules: [{
                                type: 'empty',
                                prompt: '请填写纳税人识别号'
                            }]
                        },
                        receiveName: {
                            identifier: 'receiveName',
                            rules: [{
                                type: 'empty',
                                prompt: '请填写收件人姓名'
                            }]
                        },
                        receivePhone: {
                            identifier: 'receivePhone',
                            rules: [{
                                type: 'empty',
                                prompt: '请填写收件人电话'
                            }]
                        },
                        receiveAddress: {
                            identifier: 'receiveAddress',
                            rules: [{
                                type: 'empty',
                                prompt: '请填写收件人地址'
                            }]
                        },
                        receiveCompanyName: {
                            identifier: 'receiveCompanyName',
                            rules: [{
                                type: 'empty',
                                prompt: '请填写发票抬头'
                            }]
                        },

                    },
                    onSuccess: function (e) {
                        e.preventDefault();
                        console.log('stop')
                        let  $form = $('#billform'),
                        allFields = JSON.stringify($form.form('get values')), 
                        applyurl='${pageContext.request.contextPath}/customer/applyBill';
                        console.log(allFields)
                        $.ajax({
                            type:'post', 
                            url:applyurl,
                            data:allFields,
                            contentType:'application/json',
                            dataType:'json',
                            success:
                                    function(result){
                                            if(result.code==0){
                                              alert('提交成功');
                                              location.href='${pageContext.request.contextPath}/customer/goOrderBills'
                                            }
                                            else{
                                                        console.log(result.msg)
                                                }
                                                    }


                            })
                        

          }
      
      
      
      
      
  });
        })
        </script>



<%@ include file="../footer.jsp"%>