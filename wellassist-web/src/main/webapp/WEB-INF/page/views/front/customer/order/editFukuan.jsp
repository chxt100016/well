<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<script src="${pageContext.request.contextPath}/statics/libs/ajaxupload.js"></script>
<style>
	.pd-10 {
        padding: 10px !important;
    }

    .pd-lf-30 {
        padding-left: 30px
    }

    .ft-sz-15 {
        font-size: 15px
    }

    .ft-sz-16 {
        font-size: 16px
    }

    .ft-sz-17 {
        font-size: 17px
    }
    #combox{
        display: none;
    }
    #combox2{
        display: none;
    }
     .file {
            position: relative;
            display: inline-block;
            background: #21BA45;
            border: 1px solid #21BA45;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #FFF;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
</style>	
<body>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
    <div class="ui container" id="app1" style="width:90%;">
        <div class=" ui items column">
            <div class="item" style="display:block">
                <div class="ui right floated">
                    <img src="<c:url value="/resources/wella/front/images/zhifu1.png"/>" alt="">
                </div>
            </div>
            <div class="column">
                <h4>订单信息</h4>
                <div class="ui divider"></div>
            </div>
            <div class="ui container pd-10 pd-lf-30">
                <span class="ft-sz-15">订单金额:</span> <span class="ft-sz-17">
                <fmt:formatNumber value="${orderInfo.confirmPrice*orderInfo.confirmNumber}" pattern="#.00" type="number"/>
                元</span>
                <table class="ui table">
                    <tr>
                        <td>订单编号 ${orderInfo.orderNo}</td>
                        <td>公司名称 ：${orderInfo.sellerUserName}</td>
                    </tr>

                    <tr>
                        <td>账户余额: <span id='balance'> </span>元</td>
                        <td>授信余额 <c:if test="${userSumCredit!=0}">${orderInfo.userCreditMoney}元</c:if>
                            <c:if test="${userSumCredit==0}">&nbsp;&nbsp;未授信</c:if>
                        </td>
					</tr>
					<tr>
						<td>订单内容:${orderInfo.prodName}</td>
						<td>吨数：${orderInfo.saleNum}吨</td>
					</tr>
                </table>
            </div>
            <br>

            <div class="column">
                <h4>付款方式</h4>
                <div class="ui divider"></div>
            </div>
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
                            <div style="width:300px;margin-bottom:0px;height:140px" class="inline  pd-10" id="combox">
                                <div class="field" >
                                    <label style="display:block;float:left">授信金额:</lebel>
                                        <input type="text" style="display:none">
                                    <input type="text" name="loans" style="display:block;float:left" placeholder="输入授信金额"  onchange="moneyCheck(this)">
                                    <input type="checkbox" name="cb" id="bondc"  style=" visibility:hidden;">
                                </div>
                                <div class="">
                                    <label style="display:block;float:left">余额金额:</lebel>
                                        <input type="text" name="" id="balancePay" style="display:block;float:left" disabled=true >
                                </div>
                             </div>
                        </div>

                        <!-- <div class="field pd-10">
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
                        </div> -->
                    </div>

                </div>
                <br>
                <div class="ui divider"></div>
                <div class="ui container field ">
                    <div  style="width:100% ">
                    <span>支付密码：</span> <input type="password" style="width:250px" name="pass" placeholder="别忘记输入密码哦" autocomplete = 'new-password'>
                    </div>
                </div>
                <div class=" ui button primary submit" id="paySub">确定支付</div> <div class="ui button " onclick=" window.location.href ='${pageContext.request.contextPath}/customer/orderList'">返回</div>
                 

            </form>
        </div>

    </div>
</div>
    </div>

</body>
<script>
    var xm =1000;
    var payform = $('#payform');
    const url1="${pageContext.request.contextPath}/customer/checkCzPassword";
    const url2="${pageContext.request.contextPath}/customer/payOrder";
    const payMoney=${orderInfo.confirmPrice*orderInfo.confirmNumber};
    const loanLeft=${orderInfo.userCreditMoney};
    const banlanceLeft= ${orderInfo.userMoney};

    $('.ui.form')
        .form({
            on: 'blur',
            inline: true,
            fields: {
                payment: {
                    identifier: 'payment',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '请勾选账户支付方式'
                        }
                    ]
                },
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
                $("#paySub").addClass("disabled");
                //阻止表单的提交
                // console.log("onSuccess");
                var allFields = payform.form('get values');
                var paypass= payform.form('get value','pass');
                // var zfMethod= payform.form("get value","zfMethod");
                // var loan = payform.form("get value","loans");
                // var balance= $("balancePay").value;
                // console.log(paypass);
                // console.log($("#balancePay").val());
                // console.log(allFields);
               function GetJsonData() {
                    var json = {
                        
                        "zfMethod":payform.form("get value","zfMethod"),
                        "loan": payform.form("get value","loans"),
                        "balance": $("#balancePay").val(),
                        'orderId':${orderInfo.orderId},
						'saleMoney': ${orderInfo.confirmPrice*orderInfo.confirmNumber},
                        'certificateImg':$("#prohidden").val()
                    };
                    return json;
                };
                console.log(GetJsonData());
                e.preventDefault();
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
                            if(result.status==1){
                                      alert(result.content);
                                       window.location.href = "${pageContext.request.contextPath}/customer/orderList";
                                  }else{
                                      alert(result.content);
                                  }
                           $("#paySub").removeClass("disabled");
                         },'json');
                         }
                         else if(result.code==500){
                             alert("朋友你的密码好像有点问题");
                              console.log("error pass");
                             $("#paySub").removeClass("disabled");
                         }
                         else{
                             console.log("fail!");
							 
                         }
                     }
                });

            }

        });



    function bond(x) {
        //    let chk= x.checked;
        // $("#bondc").removeAttr("checked");


        if (x ===3) {

            if(${userSumCredit}==0){
                alert("请申请账户授信");
                $.get("${pageContext.request.contextPath}/customer/isCreditApplyAvailable",{},function(r){
                    if(r.code==0){
                        url = "${pageContext.request.contextPath}/customer/creditApply";
                        window.location.href = url;
                    }else {
                        alert(r.msg);
                    }
                },"json");}

            $('#combox2').hide(500);
            $('#combox').show(500);
            $("#bondc").prop("checked", "checked");
            // console.log($("#bondc")); 
        }
        else if(x===4){
            $('#combox').hide(500);
			$('#combox2').show(500);
			$("#proflag").prop("checked", "checked");
        }
         else {
            	$("#bondc").removeAttr("checked");
				$("#proflag").removeAttr("checked");
				$('#combox').hide(500);
				$('#combox2').hide(500);
        }
        if (x===2){

            if(${userSumCredit}==0){
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

        console.log(x)
    }

    function moneyCheck(_this){
        var xmin='';
        if(loanLeft>=payMoney){
            xmin=payMoney;
        }
        else{
            xmin= loanLeft;
        }

          var loanVal= _this.value;
        
          if(loanVal >=xmin ) {
             _this.value =xmin;
             $("#balancePay").val(0);
          }
        
          else{
               $("#balancePay").val(payMoney-_this.value);
          }
        //   console.log(loanVal);
    }


    // $(function () {
    //     valid();
    // })

</script>
<script>
       $(document).ready(function(){
           $("#paySub").addClass("disabled");
        /*new AjaxUpload('#profile', {
            action: '${pageContext.request.contextPath}/uploadFile',
            name: 'file',
            autoSubmit:true,
            responseType:"json",
            onSubmit:function(file, extension){
                if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                    alert('只支持jpg、png、gif格式的图片！');
                    return false;
                }
            },
            onComplete : function(file, data){
                if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
                // $("#profile").val(data.path);
				$("#proimg").attr("src",data.path);
                $("#prohidden").val(data.path);
                 $("#proInfo").html( "<p>" + file + "</p>" );
                 $("#proimg").show();
                return;
            }
        });*/

	  //获取账户余额
      const urrr = '${pageContext.request.contextPath}/finance/localBalance';
			
				$.ajax({
					type: 'get',
					url: urrr,
					data: '',
					dataType: 'json',
					success:
					function (result) {
						if (result.code == 0) {
							let bal= result.balance;
							console.log(result.msg);
                             $('#balance').html(bal)
						}
						else {
							console.log(result.msg)
						}
					}


				})
           $("#paySub").removeClass("disabled");

       })
</script>
</html>

<%@ include file="../footer.jsp"%>