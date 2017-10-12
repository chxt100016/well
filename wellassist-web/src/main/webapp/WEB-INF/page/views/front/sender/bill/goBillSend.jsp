<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="container1">
    <div class="container2">
        <div class="ui container segment" >
            
            <div class="column">
                <h4>订单详情</h4>
                <div class="ui divider"></div>
            </div>
            <div id='sb2'>
            <table class="ui celled table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>商品名:</th>
                        <th>订单编号:</th>
                        <th>申请用户:</th>
                        <th>金额:</th>
                        <th>完成时间:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-if="list.length==0">
                        <td>暂无数据...</td>
                    </tr>
                    <tr v-for="(item,index) in list">
                        <td>{{index+1}}</td>
                        <td>{{item.prodName}}</td>
                        <td>{{item.orderNo}}</td>
                        <td>{{item.customerUserName}}</td>
                        <td>{{item.prePayment}}</td>
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
        </div>
            <br>
            <div class="column">
                <h4>开具发票信息填写</h4>
                <div class="ui divider"></div>
            </div>
            <div class="ui segment">
                <form class="ui form " id='sendbill'>
                    <input type="hidden" name="billId" value="${param.billId}"> 
                    <div class="inline fields">
                        <label for="fruit">发票种类:</label>
                        <div class="field">
                            <div class="ui radio checkbox">
                                <input type="radio" name="kpType" value='1' checked='' onclick="typeChange(1)">
                                <label>线上发票</label>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui radio checkbox"> 
                                <input type="radio" name="kpType"  value='2' onclick="typeChange(2)">
                                <label>线下发票</label>
                            </div>
                        </div>

                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>发票编号</label>
                            <input type="text" name="billNo" placeholder="发票编号">
                        </div>
                        <div class="field" id='online' >
                            <label>上传发票</label>
                            <button class="ui button green " id='upload'><i class="upload icon"></i></button>
                            <input type="hidden" name="eBill" placeholder="联系电话">
                            <span id='uploadshow'></span>
                        </div>
                    </div>
                    <div class="two fields" id='offline' style="display:none">
                        <div class="field">
                            <label>快递公司</label>
                            <input type="text" name="kdName" placeholder="快递公司">
                        </div>
                        <div class="field">
                            <label>快递单号</label>
                            <input type="text" name="kdNo" placeholder="快递单号">
                        </div>
                    </div>
                    <div class="ui button submit primary"> 提交 </div>
                    <div class="ui button cancel"> 返回 </div>

                </form>

            </div>


        </div>
        ${param.billId}
    </div>

</div>

<script>
    const url1 = '${pageContext.request.contextPath}/bill/billLogisticss',
        url2 = '${pageContext.request.contextPath}/bill/bill'
    billId = '${param.billId}';
    const ding = new Vue({
        el: sb2,
        data: {
            list: '',
            billMoney: '',
            billInfo: '',
            billType:''

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
                        console.log(result);
                        that.list = result.items;
                        
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
            // 发票信息
            $.ajax({
                type: 'get',
                url: url2,
                data: { 'billId': billId },
                dataType: 'json',
                success:
                function (result) {
                    if (result.code == 0) {
                        // console.log(result.bill);
                        that.billInfo = result.bill

                    }
                    else {
                        console.log(result.msg)
                    }
                }


            })



        },
    
          


    })
</script>
<script src="/wellassist/statics/libs/ajaxupload.js"></script>
<script>
    $(function () {
        $('#sendbill').form({
            on: 'blur',
            inline: true,
            fields: {
                billNO: {
                    identifier: 'billNO',
                    rules: [{
                        type: 'empty',
                        prompt: '不可为空！'
                    }]
                },
                kpType: {
                    identifier: 'kpType',
                    rules: [{
                        type: 'empty',
                        prompt: '不可为空！'
                    }]
                },
            },
            onSuccess:function(e){
                let  allFields = $('#sendbill').form('get values');
                e.preventDefault();
                console.log(allFields);
                let sendurl='${pageContext.request.contextPath}/sender/sendBill';
                $.ajax({
                type: 'post', 
                url:sendurl,
                data:allFields,
                dataType:'json',
                success:
                        function(result){
                                if(result.code==0){
                                     alert('提交成功');
                                     location.href='${pageContext.request.contextPath}/sender/goBillApply'

                                }
                                else{
                                    alert('很抱歉，操作失败')
                                            console.log(result.msg)
                                    }
                                        }


                })
                
            }

        });
        new AjaxUpload('#upload', {
            action: '/wellassist/uploadFile',
            name: 'file',
            autoSubmit:true,
            responseType:"json",
            onSubmit:function(file, extension){
                if (!(extension && /^(jpg|png|pdf)$/.test(extension.toLowerCase()))){
                    alert('只支持jpg、png、pdf格式的文件！');
                    return false;
                }
            },
            onComplete : function(file, data){
                console.log("success!");
                if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
               
                $(":input[name='eBill'][type='hidden']").val(data.path);
                $('#uploadshow').html('上传成功！')
                /*$("a#bankBill").attr("href",data.path);*/
               
                return;
            }
        });


    })
</script>


<script>
    function typeChange (tyPe) {
         let billTYpe= $("input[type='radio'][name = 'kpType']").val();
         if(tyPe==1){
             $('#online').show();
             $('#offline').hide();
            // $('#sendbill')
            //     // adding longform
            //     .form('add rule', 'eBill', {
            //         rules: [
            //         {
            //             type   : 'empty',
            //             prompt : 'Entering your gender is necessary'
            //         }
            //         ]
            //     })
         }
         
        

    
        if(tyPe==2){
            $('#online').hide();
            $('#offline').show();
        }
    }

</script>
${param.billId} 

<%@ include file="../footer.jsp"%>