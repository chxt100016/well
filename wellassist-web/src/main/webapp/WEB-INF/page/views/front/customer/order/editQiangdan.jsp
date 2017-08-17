<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">
<head>
	  <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	     .middleAlign {
            display: table-cell;
            vertical-align: middle;
        }
     .pd-lf-10{padding-left: 10px}
        .pd-lf-30 {
            padding-left: 30px;
        }
        .ft-sz-18{
 font-size: 18px;
 font-weight: bold;
        }

        .blue33 {
            background: #f2f7fb !important;
        }

        .grey4 {
            background: #fafafa!important;
        }

        .pd-tp-20 {
            padding-top: 20px
        }
        .pd-bt-10{padding-bottom: 10px}
        .pd-bt-15{padding-bottom: 15px}
        .pd-lf-40{padding-left: 40px;} 
         .pd-bt-40{padding-bottom: 40px;} 
        .pd-tp-40{padding-top: 40px;}

        .pd-lf-30 {
            padding-left: 30px;
        }
        .mg-lf-30{margin-left: 30px}

        .pd-rg-30 {
            padding-right: 30px;
        }
        .fl-lf{
             float: left
        }
        .dp-bl{
            display: block;
        }
         .middleAlign{
  display: table-cell;
vertical-align: middle;
 }
 .extable tr td{
     width: 50%
 }
 .tablebox:nth-child(odd){
  background: #fafafa;
  border-right: 2px solid #fff;
   border-top:  2px solid #fff; 
 }
  .tablebox:nth-child(even){
  background: #f2f7fb;
   border-top:  2px solid #fff; 
 }

</style>
</head>
<body>
	

<div class="container1">
	<div style="margin:40px 0 0 210px;">
		 <div class="ui  container" style="width:90%;">
        <div class=" ui items ">
            <div class="column">
                <h4>物流选择</h4>
                <div class="ui divider"></div>
            </div>
            <div class="column"> 
                <table class="ui table">
                    <thead>
                        <th class="blue33" colspan="2">订单号：${info.logisticsInfoView.orderNo}</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="middleAlign" style="height:99px">
                                <img src="${info.logisticsInfoView.prodImg}" alt="" width="70px" height="70px" class="ds-bl mg-lf-30 fl-lf ">
                                <span class="middleAlign pd-tp-20 pd-lf-10">${info.logisticsInfoView.prodName}</span>
                                <span class="fl-lf pd-lf-10">  期望运费：${info.logisticsInfoView.customerExceptCarriage} 元</span>
                            </td>
                            <td  class="middleAlign">
                                <span>提货地址：${info.logisticsInfoView.toAddress}</span>
                                <br>
                                <span>配送地址：${info.logisticsInfoView.fromAddress}</span>
                            </td>
                        </tr>
                    </tbody>

                </table>

            </div>
            <div class="column" style="margin-top:30px">
                <div class="extable" width="100%"> 
                    <div>
                        <c:if test="${empty info.vehicleGrabs}"><span class="ft-sz-16">没有抢单...</span></c:if>
						<c:forEach var="item" items="${info.vehicleGrabs}">
                        <div  style="width:49%;float:left;" class="tablebox pd-bt-40">
                            <div class="pd-lf-40 pd-tp-40 middleAlign" style="height:140px" >
                                <img src="${item.companyImg}" alt="" width="100px" height="100px" class="ds-bl fl-lf  ">
                                <div class="fl-lf pd-bt-15 pd-lf-30  ft-sz-18" style="width:350px" >${item.senderUserName}</div>
                                <div class="fl-lf pd-bt-15 pd-lf-30  " style="width:350px">${item.senderZcAddress}</div>
                                <div class="fl-lf  pd-lf-30  pd-bt-15 " style="width:350px">报价：${item.grabMoney} 元<span>联系人：${item.companyLxr}</span></div>
                                
                                <div class="fl-lf" style="width:350px;margin-top:20px" >
									<div class="ui button fl-lf " style="width:100px;"  onclick="choseExp('${item.vehicleGrabId}')">选择</div>
									
                                    <div class="fl-lf pd-bt-15 pd-lf-30  " style="">联系电话：${item.companyLxrPhone}</div>
                                </div>
                            </div>
						</div>
                        </c:forEach>
                        <br>
                        <div  class="column mg-tp-20">
                            <div  class="ui button primary" onclick="window.history.go(-1)">返回</div>
                        </div>
                     
                    </div>
                </div>

            </div>

        </div>

    </div>

	</div>

</div>
</body>

<script type="text/javascript">

	$(".backBtn").click(function(){
		goBack();
	});
	const url= "${pageContext.request.contextPath}/customer/chooseGrab"
function choseExp(grabId){
   $.ajax({
	  	 url:url,
		   type:'post',
		 data:{
			 logisticsInfoId:${logisticsInfoId},
			 grabId:grabId
			},
		dataType:'json',
		 success:function(result){
			 if(result.code==0){
				 alert("操作成功！");
				window.location.href = "${pageContext.request.contextPath}/customer/logisticsInfoList";

			 }
			else{
				alert(result.msg)
			}
		 }

   })
}
</script>

<!-- <%@ include file="../footer.jsp"%> -->