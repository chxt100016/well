<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>卖家物流详情</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>--%>
    <style>
        .mid_box {
            width: 1100px;
            margin: 0px auto;
            border: 1px solid rgba(34, 36, 38, .15);
            box-shadow: 0 2px 4px 0 rgba(34, 36, 38, .12), 0 2px 10px 0 rgba(34, 36, 38, .15);
            padding: 1em;
            font-size: 14px;
            position: absolute;
        }
        
        .span_time {}
        
        .span_status {
            color: #adadad;
        }
        
        .fl-rg {
            float: right;
        }
        
        .fl-lf {
            float: left;
        }
        .tx-rg{
            text-align:right;
        }
        
        .pd-lf-10 {
            padding-left: 10px;
        }
        
        .pd-bt-10 {
            padding-bottom: 10px;
        }
        
        table {
            margin: 10px;
        }
        
        .container .item span {
            display: block;
            float: left
        }
        .bluetable tr th{
            background-color:#daf3ff !important;
        }
        .greytable tr th{
           background-color: #e6f0f8  !important
        }
    </style>
</head>

<body>
<div class="container1">
    <div style="margin:40px 0 0 210px;">
        <div id = "content-rect" style="width:90%;">
            <div class="ui container" id="content-rect" style="margin-top:0px;left: 10px;width:100%;" >
                <h4 class="ui header">物流详情</h4>
                <div class="ui divider"></div>
                
                <div class="column" style="font-size: 14px;font-weight: 600;margin: 20px 0 40px 60px;line-height: 25px;">
                    <c:if test="${info.isSelfCar==0}">
                        <div class="fl-lf ">取货方式：买家自提</div><br>
                    </c:if>
                    <c:if test="${info.isSelfCar==1}">
                        <div class="fl-lf ">
                        <div  class="fl-lf "><span class="a-color-base">物流公司名称：</span> </div>
                        <div class="fl-rg "> <span >${info.senderName}</span></div>
                        </div><br>
                    </c:if>

                    <div class="fl-lf "> 
                        <div  class="fl-lf "><span class="a-color-base">发货时间：</span> </div>
                        <div class="fl-rg "> <span ><fmt:formatDate value="${info.deliverDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span></div>
                    </div><br>

                    <div class="fl-lf ">
                        <div  class="fl-lf "><span class="a-color-base">收货时间：</span> </div>
                        <div class="fl-rg "> <span ><fmt:formatDate value="${info.receiveDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span> </div>
                     </div><br>
                    <div class="fl-lf ">
                        <div  class="fl-lf "><span class="a-color-base">发货地址：</span> </div>
                        <div class="fl-rg "> <span >${info.fromAddress}</span> </div>
                    </div><br>
                    <div class="fl-lf ">
                        <div  class="fl-lf "><span class="a-color-base">收货地址：</span> </div>
                        <div class="fl-rg "><span> ${info.toAddress}</span> </div>
                    </div><br>
                    <div class="fl-lf ">
                        <div  class="fl-lf "><span class="a-color-base">买家联系人：</span> </div>
                        <div class="fl-rg "><span>${info.customerContacts}</span> </div>
                    </div><br>
                    <div class="fl-lf ">
                        <div  class="fl-lf "><span class="a-color-base">买家联系电话：</span> </div>
                        <div class="fl-rg "><span>${info.customerConTel}</span> </div>
                    </div><br>
                  
                    
                </div>
                <!-- <div class="column">
                    <table class="ui celled padded table bluetable"> 
                          <thead>
                            <tr>
                                <th width='30%'>产品</th>
                                <th>品类</th>
                                <th>状态</th>
                                <th>单价（元/吨)</th>
                                <th>数量(吨)</th>
                                <th>总价(元)</th>
                            </tr>
                          </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div>
                                        <img src="http://ww4.sinaimg.cn/bmiddle/7320126fgy1fifux3iuw9j20zk0k075d.jpg" alt="" width="100px" height="75px">
                                        <div style="float:right;height:75px;padding-top:25px;margin-right:30px" class="middleAlign">
                                            <span style="">液化天然气</span>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>

                    </table>

                </div> -->
                 <h4 class="ui header">司机详情 <a href="" onclick="addDrivers()" style="float:right">+添加司机</a> </h4>

                <div class="ui divider"></div>
                <form class="ui form drivers segment" action="" >
                     <div class="two fields">
                        <div class="inline field">
                            <label>司机姓名：</label>
                            <input type="text" name="driverName">
                        </div>
                         <div class="inline field">
                            <label>&#8195司机电话：</label>
                            <input type="text" name="driverPhone">
                        </div>
                     </div>
                    <div class="two fields">
                        <div class="inline field">
                            <label>司机车牌：</label>
                            <input type="text" name="vehicleNo">
                        </div>
                         <div class="inline field">
                            <label>司机车挂号：</label>
                            <input type="text" name="vehicleHangingNo">
                        </div>
                     </div>
                    <div class="two fields">
                        <div class="inline field">
                            <label>&#8195&#8195容量：</label>
                            <input type="text" name="vehicleSize">
                            <input type="hidden" name="orderId" value="${info.orderId}">
                        </div>

                     </div>
                    <div class="ui button submit" >保存 </div>

                </form>
                 
                <c:if test="${!empty info.vehicles}">
                    <table class="ui celled padded table greytable ">
                        <thead>
                            <tr>
                                <th class="single line ">司机</th>
                                <th>电话</th>
                                <th>车牌号</th>
                                <th>车挂号</th>
                                <th>容量</th>
                                <th>操作 </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${info.vehicles}" var="vehicle">
                            <tr>
                                <td>
                                    <a class="ui cen" >${vehicle.driverName}</a>
                                </td>
                                <td class="single line ">${vehicle.driverPhone}</td>
                                <td> ${vehicle.vehicleNo}</td>
                                <td>${vehicle.vehicleHangingNo}</td>
                                <td>${vehicle.vehicleSize} 吨</td>
                                <td><a href="" onclick="delVihicle()">删除</a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    </c:if>
                <button class="ui button primary" onclick="javascript:window.history.go(-1);">返回</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    const orderId= ${info.orderId};
    const url1='${pageContext.request.contextPath}/userinfo/operationDriver';
    
    $('.drivers').form({
        on:'blur',
        fields:{
            driverName :{
                identifier:'driverName',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            driverPhone :{
                identifier:'driverPhone',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                },{
                    type:'regExp[/^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$/]',
                    prompt:'手机号哦'
                }
            ]
            },
            vehicleHangingNo :{
                identifier:'vehicleHangingNo',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            vehicleNo :{
                identifier:'vehicleNo',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
            vehicleSize :{
                identifier:'vehicleSize',
                rules:[{
                    type   : 'empty',
                    prompt : '朋友别忘记填写哦'
                }]
            },
        },
         inline : true,
         onSuccess:function(e){
              e.preventDefault();
              let allFields= $('.drivers').form('get values');
              console.log(allFields);
              $.ajax({
                    type: 'post', 
                    url:url1,
                    data:JSON.stringify(allFields),
                    dataType:'json',
                    contentType:'application/json',
                    success:function(result){
                        if(result.code == 0){
                            alert("成功")
                        }
                        else{
                            alert(result.msg)
                        }
                    }

                    })
         }
    })
    
</script>
<%@ include file="../footer.jsp"%>