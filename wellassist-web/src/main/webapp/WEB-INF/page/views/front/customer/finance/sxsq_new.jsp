<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title></title>
    <%--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>--%>
    <script src="${pageContext.request.contextPath}/statics/libs/ajaxupload.js"></script>
    <style>
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

        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
        .white-color{color: #fff}
        .excel-inco{
                font-size: 25px !important;
    width: 45px !important;
    background: #fff;
    border: none;
    color: green;
    float: right;
    margin-top: -25px !important;
        }
        .redtext{
       color:#db2828
        }
    </style>
</head>

<body>
<div class="container1">
    <div class="container2">
        <div id = "content-rect" style="width:90%;">
 
        <h3>授信额度申请</h3>
        <div class="ui divider"></div>
        <div class="row">
            <table class="ui celled striped  table">
                <thead>
                    <th>当前额度(元)</th>
                    <th>授信状态</th>
                    <th>授信期</th>
                </thead>
                <tbody>
                    <tr>
                        <td><c:if test="${not empty info.sjCredit}"> <span class="redtext ft-sz-16"><fmt:formatNumber value="${info.sjCredit.creditSjMoney}" pattern="#,###.##" type="number"/></span> </c:if>
                            <c:if test="${empty info.sjCredit}">0</c:if></td>
                        <td><c:if test="${not empty info.sjCredit}"><c:if test="${info.sjCredit.creditState==1}">授信中</c:if><c:if test="${info.sjCredit.creditState==-2}">已过期</c:if></c:if>
                            <c:if test="${empty info.sjCredit}">无</c:if></td>
                        <td><c:if test="${not empty info.sjCredit}"><span><fmt:formatDate value="${info.sjCredit.creditStartDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span> 至 <span><fmt:formatDate value="${info.sjCredit.creditDeadline}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></c:if>
                            <c:if test="${empty info.sjCredit}">0</c:if>
                             </td>
                         </tr>
                     </tbody>
                 </table>
             </div>
                <br>
            <form class="ui form" action="${pageContext.request.contextPath}/customer/applyCreditLimit" method="post">
                <div class="ui three column grid">
                    <div class="row">
                        <div class="column field" >
                            <label for="">期望额度:(元)</label>
                            <input type="text" name="creditMoney"> 
                            
                        </div>
                    </div>
                </div>
                <div  class="ui four column grid">
                    <h5>资质提交： （请上传贵公司近三年以下资料）</h5>
                    <div class="ui divider"></div>
                    <div class="">
                        <div  class=" ui cards">
                             <div class="card" style=" margin-left: 24px;">
                                <div class="content">
                                    <div class="header">
                                       利润表
                                    </div>
                                    <div class="description">
                                        请上传rar、zip文件
                                    </div>
                                   
                                 </div>
                                <div class="extra content field" >
                                      <div class="meta"  style=" height: 35px">
                                     无文件
                                    </div>
                                    <a href="javascript:;" class="file" >
                                         <span class="white-color">选择文件</span>
                                          <i class="upload icon white-color" ></i>
                                         <input type="file"  id="upload1">
                                        <input type="hidden" name="bankBill">
                                        <%--<a id="bankBill" download="bankBill.xlsx">download</a>--%>
                                    </a>
                                        
                                 </div>
                                
                             </div>
                             <div class="card">
                                <div class="content">
                                    <div class="header">
                                       资产负债表
                                    </div>
                                    <div class="description">
                                        请上传rar、zip文件
                                    </div>
                                   
                                 </div>
                                <div class="extra content field" >
                                      <div class="meta"  style=" height: 35px">
                                     无文件
                                    
                                    </div>
                                    <a href="javascript:;" class="file" >
                                         <span class="white-color">选择文件</span>
                                          <i class="upload icon white-color" ></i>
                                         <input type="file" id="upload2">
                                        <input type="hidden" name="companyBill">
                                    </a>
                                        
                                 </div>
                             </div>
                            <div class="card">
                                        <div class="content">
                                            <div class="header">
                                            现金流量表
                                            </div>
                                            <div class="description">
                                                请上传rar、zip文件
                                            </div>
                                        </div>
                                        <div class="extra content field" >
                                            <div class="meta"  style=" height: 35px">
                                            无文件
                                            
                                            </div>
                                            <a href="javascript:;" class="file" >
                                                <span class="white-color">选择文件</span>
                                                <i class="upload icon white-color" ></i>
                                                <input type="file" id="upload3">
                                                <input type="hidden" name="accountingBill">
                                            </a>
                                                
                                        </div>
                                        
                             </div>
                            <div class="card" style=" margin-left: 24px;">
                                        <div class="content">
                                            <div class="header">
                                            纳税表
                                            </div>
                                            <div class="description">
                                                请上传rar、zip文件
                                            </div>
                                        </div>
                                        <div class="extra content field" >
                                            <div class="meta"  style=" height: 35px">
                                            无文件
                                            </div>
                                            <a href="javascript:;" class="file" >
                                                <span class="white-color">选择文件</span>
                                                <i class="upload icon white-color" ></i>
                                                <input type="file" id="upload4">
                                                <input type="hidden" name="cashBill">
                                            </a>
                                                
                                        </div>
                                        
                             </div>
                        </div>
                    </div>
                </div>
                <br>
                <div style="text-align:center;" class="mg-tp-20">
                <button  class="ui primary button">提交申请</button>
                </div>
            
            </form>
         </div>
         </div>
    </div>

    </body>
        <script>
            $(function () {
                new AjaxUpload('#upload1', {
                    action: '${pageContext.request.contextPath}/uploadFile',
                    name: 'file',
                    autoSubmit:true,
                    responseType:"json",
                    onSubmit:function(file, extension){
                        if (!(extension && /^(rar|zip)$/.test(extension.toLowerCase()))){
                            alert('只支持rar、zip格式的文件！');
                            return false;
                        }
                    },
                    onComplete : function(file, data){
                        if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
                        $(":input[name='bankBill'][type='hidden']").val(data.path);
                        /*$("a#bankBill").attr("href",data.path);*/
                        handleFiles(document.getElementById("upload1"),file);
                        return;
                    }
                });
                new AjaxUpload('#upload2', {
                    action: '${pageContext.request.contextPath}/uploadFile',
                    name: 'file',
                    autoSubmit:true,
                    responseType:"json",
                    onSubmit:function(file, extension){
                        if (!(extension && /^(rar|zip)$/.test(extension.toLowerCase()))){
                            alert('只支持rar、zip格式的文件！');
                            return false;
                        }
                    },
                    onComplete : function(file, data){
                        if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
                        $(":input[name='companyBill'][type='hidden']").val(data.path);
                        handleFiles(document.getElementById("upload2"),file);
                        return;
                    }
                });
                new AjaxUpload('#upload3', {
                    action: '${pageContext.request.contextPath}/uploadFile',
                    name: 'file',
                    autoSubmit:true,
                    responseType:"json",
                    onSubmit:function(file, extension){
                        if (!(extension && /^(rar|zip)$/.test(extension.toLowerCase()))){
                            alert('只支持rar、zip格式的文件！');
                            return false;
                        }
                    },
                    onComplete : function(file, data){
                        if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
                        $(":input[name='accountingBill'][type='hidden']").val(data.path);
                        handleFiles(document.getElementById("upload3"),file);
                        return;
                    }
                });
                new AjaxUpload('#upload4', {
                    action: '${pageContext.request.contextPath}/uploadFile',
                    name: 'file',
                    autoSubmit:true,
                    responseType:"json",
                    onSubmit:function(file, extension){
                        if (!(extension && /^(rar|zip)$/.test(extension.toLowerCase()))){
                            alert('只支持rar、zipx格式的文件！');
                            return false;
                        }
                    },
                    onComplete : function(file, data){
                        if(data.result=="-10") { ShowWindowAlert("提示",data.msg,"","确 定",""); return; }
                        $(":input[name='cashBill'][type='hidden']").val(data.path);
                        handleFiles(document.getElementById("upload4"),file);
                        return;
                    }
                });
                $('.ui.form').form({
                    fields: {
                        creditMoney: {
                            identifier: 'creditMoney',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '请填写申请额度'
                                }
                            ]
                        },
                        bankBill:{
                            identifier: 'bankBill',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '请上传银行账单'
                                }
                            ]
                        },
                        companyBill:{
                            identifier: 'companyBill',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '请上传银行账单'
                                }
                            ]
                        },
                        accountingBill:{
                            identifier: 'accountingBill',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '请会计报表'
                                }
                            ]
                        },
                        cashBill:{
                             identifier: 'cashBill',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '请现金报表'
                                }
                            ]
                        }
                    },
                    inline: true,
                    on: 'blur'
                })
                    ;
            })
            function handleFiles(files,filename) {
                var filesShow= files.parentNode.parentNode.firstChild.nextElementSibling;
                if (!filename) {
                }
                else{
                  filesShow.innerHTML = "<p>"+filename+"</p>"+ '<i class="file archive outline icon excel-inco">'+'</i>';
                }
            }
         
        </script>
</html>