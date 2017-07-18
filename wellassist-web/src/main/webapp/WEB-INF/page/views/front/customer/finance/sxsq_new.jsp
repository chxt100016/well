<%@ include file="../header_new.jsp"%>
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
    </style>
</head>

<body>
    <div class="ui container segment" id="app1" style="width:990px">
        <h3>授信额度申请</h3>
        <div class="ui divider"></div>
        <div class="row">
            <table class="ui celled striped  table">
                <thead>
                    <th>当前额度(万元)</th>
                    <th>授信状态</th>
                    <th>授信期</th>
                </thead>
                <tbody>
                    <tr>
                        <td>100</td>
                        <td>授信中</td>
                        <td><span>2017-7-17 </sapn> 至 <span>2017-12-17 </sapn> </td>
                         </tr>
                     </tbody>
                 </table>
             </div>
                <br>
            <form class="ui form segment" action="${pageContext.request.contextPath}/customer/applyCreditLimit">
                <div class="ui three column grid">
                    <div class="row">
                        <div class="column field" >
                            <label for="">申请额度:</label>
                            <input type="text" name="creditMoney">
                            
                        </div>
                    </div>
                    <h5>资质提交：</h5>
                    <div class="row">
                        <div  class=" ui cards">
                             <div class="card" style=" margin-left: 24px;">
                                <div class="content">
                                    <div class="header">
                                       银行对账单
                                    </div>
                                    <div class="description">
                                        请上传EXCEL文件
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
                                       个人财务报表
                                    </div>
                                    <div class="description">
                                        请上传EXCEL文件
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
                                            会计报表
                                            </div>
                                            <div class="description">
                                                请上传EXCEL文件
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
                                            现金流量表
                                            </div>
                                            <div class="description">
                                                请上传EXCEL文件
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
                <div class="ui primary submit button">提交申请</div>
            </form>
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
                        if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))){
                            alert('只支持xls、xlsx格式的文件！');
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
                        if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))){
                            alert('只支持xls、xlsx格式的文件！');
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
                        if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))){
                            alert('只支持xls、xlsx格式的文件！');
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
                        if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))){
                            alert('只支持xls、xlsx格式的文件！');
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
                  filesShow.innerHTML = "<p>"+filename+"</p>"+ '<i class="file excel outline icon excel-inco">'+'</i>';
                }
            }
        </script>
</html>