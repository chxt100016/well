<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="container1">
    <div class="container2">
            <div class="ui segment container" id='app1'>
                    <div class="column">
                        <h4>发票地址信息</h4>
                        <div class="ui divider"></div>
                    </div>
                    <form class="ui form" id='adform'>
                        <div class="two fields">
                            <div class="field">
                                <label>收件人姓名</label>
                                <input placeholder="" name='receiveName' type="text" v-bind:value='info.receiveName'>
                            </div>
                            <div class="field">
                                <label>收件人地址</label>
                                <input placeholder="" type="text"  name='receiveAddress' v-bind:value='info.receiveAddress' >
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>企业名称（发票抬头）</label>
                                <input placeholder="" type="text"  name='receiveCompanyName' v-bind:value='info.receiveCompanyName'>
                               
                            </div>
                            <div class="field">
                                <label>企业识别号</label>
                                <input placeholder="" type="text"  name='receiveSh' v-bind:value='info.receiveSh' >
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>联系电话</label>
                                <input placeholder="" type="text"  name='receivePhone' v-bind:value='info.receivePhone' >
                            </div>
                            <div class="field">
                                <label>开户行</label>
                                <input placeholder="" type="text"  name='bankName'  v-bind:value='info.bankName' >
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>开户账号</label>
                                <input placeholder="" type="text"  name='bankAccount' v-bind:value='info.bankAccount' >
                            </div>
                            <div class="field">
                                    
                            </div>
            
                        </div>
                        <div class="ui button primary submit"> 保存</div>
                        <div class="ui button nagtive clear"> 清空</div>
                      
                    </form>
                  
            
                </div>
        
    </div>
    
</div>
<script>
    const url1='${pageContext.request.contextPath}/customer/saveOrUpdateBillAddress';
        $(function(){
            $('#adform').form({
       on:'blur',
       inline : true,
         fields:{
            receiveCompanyName :{
                identifier:'receiveCompanyName',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            receiveSh :{
                identifier:'receiveSh',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
         },
          onSuccess:function(e){
              e.preventDefault();
              let allFields = $('#adform').form('get values');
              let datax=JSON.stringify(allFields);
              console.log(datax);
              $.ajax({
                type:'post', 
                url:url1,
                data:datax,
                dataType:'json',
                contentType:'application/json',
                success:
                        function(result){
                                if(result.code==0){
                                  alert('保存成功！');
                                  window.location.href='${pageContext.request.contextPath}/customer/goBillApply'
                                }
                                else{
                                    alert('保存失败，请重试！')
                                            console.log(result.msg)
                                    }
                                        }


                })
           
          }
      
      
      
      
      
  })
        })
</script>
<script>
    const url2='${pageContext.request.contextPath}/customer/billAddress';
    const vm= new Vue({
        el:'#app1',
        data:{   
            info:''        
        },
        beforeCreate:()=>{
            // let that= this ;
            $.ajax({
            type:'get', 
            url:url2,
            data:'',
            dataType:'json',
            success:
                    function(result){
                            if(result.code==0){
                              vm.info=result.billAddress;
                            //   console.log(that.info)
                            }
                            else{
                                alert('加载失败，请稍后..')
                                history.go(-1);
                                        console.log(result.msg)
                                }
                                    }


            })
        }
    })
</script>
     

<%@ include file="../footer.jsp"%>