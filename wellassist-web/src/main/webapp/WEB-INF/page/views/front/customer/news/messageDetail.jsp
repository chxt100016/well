<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${user.userType==0}">
    <%@ include file="/WEB-INF/page/views/front/seller/header.jsp"%>
</c:if>
<c:if test="${user.userType==1}">
    <%@ include file="/WEB-INF/page/views/front/customer/header.jsp"%>
</c:if>
<c:if test="${user.userType==3}">
    <%@ include file="/WEB-INF/page/views/front/sender/header.jsp"%>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .textbox{
         min-height: 600px;
         /* border: 1px solid rgba(34,36,38,.15); */
         padding: 10px;
         text-indent: 2em
    }
    </style>
   
<body>
    <div class="container1">
        <div class="container2">
            <div class="ui container segment" id='app'>
                    
                <div class="pd-bt-20">
                    <span class="ft-sz-16 pointer pd-lf-30" style="padding-right:50px"  @click='back2list'> <i class="reply icon"></i>返回</span>   
                    <span class="ft-sz-16 pointer" @click='deleteMsg'> <i class="trash outline icon"></i>删除</span>
                </div>
                <div style="max-width:700px">
                    <h3>{{message.title}}</h3>
                    <span>{{message.date}}</span>
                </div>
                <br>
                <div class="ui text container textbox">
                    {{message.content}}
                </div>
            </div>

        </div>

    </div>
</body>
<script>
    const url = '${pageContext.request.contextPath}/mes/messageDetail';
    const url2='${pageContext.request.contextPath}/mes/delete1Msg'
    const id= '${param.id}'
    const vm= new Vue({
        el:'#app',
        data:{
            message:{}
        },
        created:function(){
            let that= this;
            $.ajax({
            type: 'get', 
            url:url,
            data:{id:id},
            dataType:'json',
            success:function(result){
                if(result.code==0){
                 that.message=result.message
                }
                else{
                    console.log(result.msg)
                }
            }

            })
        },
        methods:{
            deleteMsg:function () {
                let r= confirm('确认要删除？');
                if(r==true){
                $.ajax({
                    type: 'get', 
                    url:url2,
                    data:{id:id},
                    dataType:'json',
                    success:function(result){
                        if(result.code==0){
                        alert('成功');
                     history.go(-1);
                        }
                        else{
                            console.log(result.msg);
                            alert('失败了')
                        }
                    }

                    })
                }else{
                    //do nothing
                }
            },
            back2list:function(){
                // window.location.href='${pageContext.request.contextPath}/mes/systemicMesPage'
                window.history.go(-1);
            }
        }
        
    })

</script>