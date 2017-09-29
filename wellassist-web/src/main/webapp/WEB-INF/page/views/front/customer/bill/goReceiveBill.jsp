<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="container1">
    <div class="container2">
        <div class="ui segment container" id='bill'>
                <div class="column">
                        <h4>发票操作</h4>
                        <div class="ui divider"></div>
                </div>
                <table class="ui table celled">
                    <tr>
                        <td colspan="3">发票信息（申请）</td>
                    </tr>
                    <tr>
                        <td>
                            发票抬头：{{billInfo.receiveCompanyName}}
                        </td>
                        <td>
                            发票总额：{{billInfo.billMoney}}
                        </td>

                        <td>
                            纳税人识别号：{{billInfo.receiveSh}}
                        </td>
                    </tr>
                    <tr>
                    <td>
                        发票种类：<span v-if="billInfo.kpType==1 "> 电子发票</span>
                        <span v-if="billInfo.kpType==2"> 线下发票</span>
                    </td>
                    <td>
                        发票编码号：{{billInfo.billNo}}
                    </td>

                    <td>
                        快递单号（线下）：{{billInfo.kdNo}} ；快递公司：{{billInfo.kdName}}
                         
                    </td>
                </tr>
                    <tr>
                            <td>
                                收件人：{{billInfo.receiveName}}
                            </td>
                            <td>
                                收件人电话：{{billInfo.receivePhone}}
                            </td>
    
                            <td>
                                申请时间：{{billInfo.applyDate}}
                            </td>
                        </tr>

                </table>
                <br>
                <div class="column">
                        <h4>发票收取</h4>
                        <div class="ui divider"></div>
                </div>
                <table class="ui table celled">
                    <tr>
                        <td>
                            <span>文件下载：
                                    <button class="ui button green" @click="downLoad(billInfo.eBill,'电子发票.pdf')"><i class="download icon"></i></button>
                            </span>
                        </td>
                        <td>
                                <span>操作：
                                        <button class="ui button teal" @click='received()'>收到确认</button>
                                        <button class="ui button negative" disabled >收到否认</button>
                                </span>

                        </td>

                    </tr>
                </table>
            
        </div>

        
    </div>

</div>
<script>
     const  billId='${param.billId}'
     const vvv= new Vue({
            el:'#bill',
            data:{
                billInfo:''
            },
            created:function(){
                let that =this;
                let url2='${pageContext.request.contextPath}/bill/bill';
                // let billId='${param.billId}'
                $.ajax({
                        type:'get', 
                        url:url2,
                        data:{'billId':billId},
                        dataType:'json',
                        success:
                                function(result){
                                        if(result.code==0){
                                        that.billInfo= result.bill;
                                        console.log(that.billInfo)
                                        }
                                        else{
                                                    console.log(result.msg)
                                            }
                                                }


                        })

            },
            methods:{
                downLoad: function (targetUrl,fileName){
                    var form=$("<form>");//定义一个form表单
                    form.attr("style","display:none");
                    form.attr("target","");
                    form.attr("method","post");
                    form.attr("action","${pageContext.request.contextPath}/downloadCrossDomainFile");
                    var input1=$("<input>");
                    input1.attr("type","hidden");
                    input1.attr("name","targetUrl");
                    input1.attr("value",targetUrl);
                    var input2=$("<input>");
                    input2.attr("type","hidden");
                    input2.attr("name","outputFileName");
                    input2.attr("value",fileName);
                    $("body").append(form);//将表单放置在web中
                    form.append(input1);
                    form.append(input2);
                    form.submit();//表单提交
                },
                received:function () {
                    let url5 ='${pageContext.request.contextPath}/customer/receiveBill';
                    $.ajax({
                            type:'post',
                            url: url5,
                            data:{'billId':billId},
                            dataType:'json',
                            success:
                                    function(result){
                                            if(result.code==0){
                                                alert('提交成功')
                            window.location.href='${pageContext.request.contextPath}/customer/goOrderBills'
                                            }
                                            else{
                                                        console.log(result.msg)
                                                        alert('抱歉，出错啦')
                                                }
                                            }


                            })
                   
                    
                }
            }


     })

  
</script>
${param.billId}   
<%@ include file="../footer.jsp"%>