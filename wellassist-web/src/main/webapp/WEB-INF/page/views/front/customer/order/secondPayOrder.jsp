<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<style> #combox{
    display: none;
}
#combox2{
    display: none;
}</style>
        <div class="container1">
            <div class="container2">
                <div class="ui container segment" id='bb' v-cloak>
                    <div class=" ui items column">
                        <div class="column">
                            <h4>订单金额结算信息</h4>
                            <div class="ui divider"></div>
                        </div>
                        <div class="ui container pd-10 pd-lf-30">
                            <span class="ft-sz-15">注：平台交易订单秉持多退少补原则，已支付金额少于实际交易金额时，请先完成支付，再进行结算操作</span>
                            <input type="hidden" name="" v-model='payMoney' id='payMoney'>
                            <input type="hidden" name=""  id='balanceNeed'>
                            <input type="hidden" name=""  id='loanNeed'>
                            <table class="ui table">
                                <tr>
                                    <td>实际金额：<span>{{sum}} 元</span> </td>
                                    <td>已支付金额：{{paidAmount}} 元
                                     
                                    </td>
                                    
                                </tr>

                                <tr>
                                    <td>账户余额 ：<span  class="ui inline active loader" id='loader'></span> <span id='balance'>{{balance}} 元 </span>
                                        <input type="hidden"  id='balancehid' v-model='balance'>
                                    </td>
                                    <td>授信余额：
                                             <span v-if='creditBalance.length!=0'>{{creditBalance}} 元</span>
                                             <input type="hidden"  id='creditBalance' v-model='creditBalance'>
                                             <span v-if='creditBalance.length==0'>未授信</span>
                                        <!-- <c:if test="${userSumCredit==0}">未授信</c:if> -->
                                    </td>
                                </tr>
                                <tr>
                                    <td>订单内容: 货物订单</td>
                                    <td>状态：
                                        <span v-if='payState==1'>未结清，需支付</span>
                                        <span v-if='payState==0'>已结清</span>
                                        <span v-if='payState==2'>可退款</span>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                        <br>
                        <div class="column">
                            <div class="ui button primary " @click='finish' id="paySub"> 结算</div>
                            <div class="ui button yellow" @click='paypay' id='paypay' v-if='payState==1'>去支付</div>

                        </div>
                    </div>

                </div>

            <!-- //支付弹框 -->
                <div class="ui modal">
                        <div class="header">支付</div>
                        <div class="content">
                                <form class="ui form" id="payform"   autocomplete="new-password" >
                                        <div class="ui container pd-10 pd-lf-30">
                                            <div class="grouped fields">
                        
                                                <div class="field pd-10">
                                                    <div class="ui radio checkbox">
                                                        <input type="radio" name="zfMethod" checked="checked" onchange="bond(1)" value="2">
                                                        <label>余额支付</label>
                                                    </div>
                                                </div>
                                                <div class="field pd-10">
                                                    <div class="ui radio checkbox">
                                                        <input type="radio" name="zfMethod" onchange="bond(2)" value="3">
                                                        <label>授信支付</label>
                                                    </div>
                                                </div>
                        
                                                <div class="field pd-10">
                                                    <div class="ui radio checkbox">
                                                        <input type="radio" name="zfMethod" id="combinationPay" onchange="bond(3)"  value="4">
                        
                                                        <label>组合支付</label>
                                                        
                                                    </div>
                                                    <div style="width:300px;margin-bottom:0px" class="inline  pd-10" id="combox">
                                                        <div class="field" >
                                                            <label style="display:block;float:left">授信金额:</lebel>
                                                                <input type="text" style="display:none">
                                                            <input type="text" name="loans" id='loanId' style="display:block;float:left" placeholder="输入授信金额"  onchange="moneyCheck(this)">
                                                            <input type="checkbox" name="cb" id="bondc"  style=" visibility:hidden;">
                                                        </div>
                                                        <div class="">
                                                            <label style="display:block;float:left">余额金额:</lebel>
                                                                <input type="text" name="" id="balancePay" style="display:block;float:left" disabled=true >
                                                        </div>
                                                     </div>
                                                </div>
                        
                                                <div class="field pd-10">
                                                    <div class="ui radio checkbox">
                                                        <input type="radio" name="zfMethod" onchange="bond(4)" id="offLine" value="5">
                                                        <label>线下支付</label>
                                                    </div>
                                                    <div style="width:400px;margin-bottom:0px; height:auto;min-height:200px" class="inline  pd-10" id="combox2" >
                                                        <div class="field" >
                                                            <label style="display:block;float:left">上传凭证:</lebel>
                                                            <div class="meta" style=" height: 35px" id="proInfo">
                                                            无文件
                        
                                                             </div>
                                                        <a href="javascript:;" class="file">
                                                             <i class="upload icon white" ></i>
                                                            <input type="hidden" name="proof" id="prohidden">
                                                            <input type="file" name="" style="display:block;float:left" id="profile" >
                                                            <input type="checkbox" name="proflag" id="proflag"  style=" visibility:hidden;">
                                                        </a>
                                                        </div>
                                                         <img id="proimg" class="yingyeimg" style="display:none" src=""  width="250px" height="180px"/>
                                                       
                                                     </div>
                                                </div>
                                            </div>
                        
                                        </div>
                                        <br>
                                        <div class="ui divider"></div>
                                        <div class="ui container field ">
                                            <div  style="width:100% ">
                                            <span>支付密码：</span> <input type="password" style="width:250px" name="pass" placeholder="别忘记输入密码哦" autocomplete = 'new-password'>
                                            </div>
                                        </div>
                                        <div class=" ui button primary submit" id="paySure">确定支付</div> <div class="ui button " onclick=" window.location.href ='${pageContext.request.contextPath}/customer/logisticsInfoList'">返回</div>
                                         
                        
                                    </form>
                          
                        </div>
                </div>
              
            </div>
        </div>
 
        <script>
    var xm =1000;
    var payform = $('#payform');
    const url1="${pageContext.request.contextPath}/customer/checkCzPassword";
    const url2="${pageContext.request.contextPath}/customer/secondPayOrderSub";
    
    // const payMoney=vm.sum-vm.paidAmount;
  

    $('.ui.form').form({
            on: 'blur',
            inline: true,
            fields: {
                combinationPay: {
                    identifier: 'loans',
                    depends: 'cb',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '请输入授信额度'
                        },
                        {
                            type: 'regExp[/^[0-9].*$/]',
                            prompt:"别小于0哦"
                        },
                        {
                            type: 'number',
                            prompt:"数字哦"
                        },
                      
                    ]

                },
                offLine:{
                   identifier: 'proof',
                    depends: 'proflag',
					rules: [
                        {
                            type: 'empty',
                            prompt: '请上传凭证'
                        },
                    
                      

                    ]
                },
              
                password: {
                    identifier: 'pass',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '密码'
                        }
                    ]
                }

            },
            onSuccess: function(e) {
                //阻止表单的提交
                console.log("onSuccess");
                e.preventDefault();
                var allFields = payform.form('get values');
                var paypass= payform.form('get value','pass');
                // var zfMethod= payform.form("get value","zfMethod");
                // var loan = payform.form("get value","loans");
                // var balance= $("balancePay").val();
                // console.log(paypass);
                // console.log($("#balancePay").val());
                // console.log(allFields);
               function GetJsonData() {
                    var json = {
                        "zfMethod":payform.form("get value","zfMethod"),
                        "loan": payform.form("get value","loans"),
                        "balance": $("#balanceNeed").val(),
                        'secondPayMoney':$('#payMoney').val(),
                        'orderId':${param.orderId},
                        'certificateImg':$("#prohidden").val()
                    };
                    return json;
                };
                console.log(GetJsonData());
              
                $.ajax({
                     type: "Post",
                     url: url1,
                     data:{pass:paypass},
                    //  contentType:'application/json',
                     dataType: "json",
                     success:function(result){
                         if(result.code==0){
                             console.log("ok");
                             //密码无误
                             
                            //  $.ajax({
                            //     type: "Post",
                            //     url: url2,
                            //     data:JSON.stringify(GetJsonData()),
                            //     dataType: "json",
                            //       contentType:'application/json',
                            //     success:function(result){
                            //       if(result.status==1){
                            //           alert(result.content)
                            //       }else{
                            //           alert(result.content)
                            //       }

                            //     }
                            //  })
                       $.post(url2,GetJsonData(),function(result){
                            if(result.code==0){
                                alert("处理中...");
                                       window.location.href = "${pageContext.request.contextPath}/customer/orderList";
                                  }else{
                                      alert(result.msg);
                                    
                                  }
                         },'json');
                         }
                         else if(result.code==500){
                             alert("朋友你的密码好像有点问题");
                              console.log("error pass")
                         }
                         else{
                             console.log("fail!")
                         }
                     }
                })

               
            }

        });



    function bond(x) {
        $('#balanceNeed').val('');
        //    let chk= x.checked;
        // $("#bondc").removeAttr("checked");
    let loanLeft=$('#creditBalance').val();
    console.log(loanLeft);
    let banlanceLeft= $('#balance').val();
       if(x==1){
           $('#balanceNeed').val($('#payMoney').val());
       }
        if ( x==3) {
            if(loanLeft==0){
                alert("请申请账户授信");
                $.get("${pageContext.request.contextPath}/customer/isCreditApplyAvailable",{},function(r){
                    if(r.code==0){
                        url = "${pageContext.request.contextPath}/customer/creditApply";
                        window.location.href = url;
                    }else {
                        alert(r.msg);
                    }
                },"json");}
            $('#combox2').hide(1000);
            $('#combox').show(1000);
            $("#bondc").prop("checked", "checked");
            // console.log($("#bondc")); 
        }
        else if(x===4){
            $('#combox').hide(1000);
      $('#combox2').show(1000);
      $("#proflag").prop("checked", "checked");
        }
         else {
            $("#bondc").removeAttr("checked");
             $("#proflag").removeAttr("checked");
            $('#combox').hide(1000);
            $('#combox2').hide(1000);
        }
        if (x===2){
            $('#loanId').val($('#payMoney').val());
            if(loanLeft==0){
                alert("请申请账户授信");
                $.get("${pageContext.request.contextPath}/customer/isCreditApplyAvailable",{},function(r){
                    if(r.code==0){
                        url = "${pageContext.request.contextPath}/customer/creditApply";
                        window.location.href = url;
                    }else {
                        alert(r.msg);
                    }
                },"json");
            }
        }
        // console.log(x)
    }

    function moneyCheck(_this){
        let loanLeft=$('#creditBalance').val();
        let banlanceLeft= $('#balance').val();
        let payMoney=$('#payMoney').val();
        console.log(payMoney);
        var xmin='';
        if(loanLeft>=payMoney){
            xmin=Number(payMoney);
        }
        else{
            xmin= Number(loanLeft);
        }

          var loanVal= _this.value;
            console.log(xmin);
            console.log(loanVal);
          if(loanVal >=xmin ) {
              console.log('小！')
             _this.value =xmin;
             $("#balancePay").val(payMoney-_this.value);
          }
        
          if(loanVal<xmin){
               $("#balancePay").val(payMoney-_this.value);
          }
        
    }


    // $(function () {
    //     valid();
    // })

</script>

    <script>
          const balanceUrl='${pageContext.request.contextPath}/finance/balance';
          const creditUrl='${pageContext.request.contextPath}/finance/creditBalance';
          const orderInfoUrl='${pageContext.request.contextPath}/order/orderinfo';
          const paymentUrl='${pageContext.request.contextPath}/customer/secondPayOrderSub';
          const orderId=${param.orderId};
          const vm =new Vue({
              el:'#bb',
              beforeCreate:function(){
                  let that= this;
                  //账户余额
               
                $.get(balanceUrl,function (result){
                     result= JSON.parse(result);
                    if(result.code==0){

                       that.balance=result.balance;
                       $('#loader').hide();
                     }else{
                          console.log(result)
                     }
                         });
                        //  授信余额
                $.get(creditUrl,function (result){
                     result= JSON.parse(result);
                    if(result.code==0){
                       that.creditBalance=result.userCreditMoney;
                     }else{
                          console.log(result)
                     }
                         });
                    //  订单信息
                    $.ajax({
                        type:'get', 
                        url:orderInfoUrl,
                        data:{'orderId':orderId},
                        dataType:'json',
                        success:function(result){
                                if(result.code==0){
                                      console.log(result.orderinfo);
                                      that.paidAmount= result.orderinfo.confirm_number*result.orderinfo.confirm_price;
                                      that.sum=result.orderinfo.sale_sj_money;
                                      that.payMoney=that.sum- that.paidAmount;
                                      if(that.sum== that.paidAmount){
                                           that.payState=0;
                                      }
                                      if(that.sum> that.paidAmount){
                                           that.payState=1;
                                      }
                                      if(that.sum< that.paidAmount){
                                           that.payState=2;
                                      }
                                }
                                else{
                                            console.log(result.msg)
                                    }
                                        }

                                                                
                        })  
                        //
                    

              },
               data:{
                    balance:'',
                    creditBalance:'',
                    sum:'',
                    paidAmount:'',
                    payState:'',
                    payMoney:'',

               },
               methods:{
                   finish:function(){
                       $("#paySub").addClass("disabled loading");
                       let that = this;
                    $.ajax({
                        type:'post',
                        url:paymentUrl,
                        data:{
                        orderId:orderId,
                        secondPayMoney:that.payMoney,
                        zfMethod:'',
                        balance:'',
                        loan:'',
                        certificateImg:'',
                        },
                        dataType:'json',
                        success:
                                function(result){
                                        if(result.code==0){
                                                console.log('success!')
                                        }
                                        else{
                                                    console.log(result.msg)
                                            }
                                    $("#paySub").removeClass("disabled loading");
                                        window.history.go(-1);
                                                }


                        })
                   },
                   paypay:function(){
                    $('.ui.modal').modal('show');
                    $('#balanceNeed').val($('#payMoney').val());
                   }
               }
              
          })
        </script>
<%@ include file="../footer.jsp"%>