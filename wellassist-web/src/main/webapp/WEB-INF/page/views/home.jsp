<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
    <!-- custom css -->
    <link rel="stylesheet" href="{rc.contextPaht}resources/wella/front/css/pagetempl.css">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WellAssist供应链系统</title>
    <link rel="Stylesheet" type="text/css"	href="<c:url value="/resources/wella/front/css/sender/start.css"/>" />
	<style>
		div.top-nav{padding-top:16px;padding-bottom:16px;}
		.start_heading2{padding-top:20px;padding-bottom:20px;font-weight:bold;font-size:24px;}
		.ware_button{float:right;width: 100%;background: #0077dd;color: white;text-align: center;padding-top: 6px;padding-bottom: 6px;cursor:pointer;}
		.ware_maker_label{color: #333333;padding: 6px;font-weight:500;font-size:20px;margin-top:20px; margin-bottom:10px}
		.buyer_start_item{background: white;border: #c0c0c0;border-style: solid;border-width: 1px;}
		.start-table {cursor:pointer;}
		.start-table>table{width:100%;}
		.start-unhang>img{}
		.start-content1>div>div>span{font-size:15px;}
		.cd-rect{width: 1000px; margin: auto;overflow: auto;}
		.row-fld{width: 230px; background-color: white;font-size: 13px;  overflow: auto; float: left; box-shadow: 3px 4px 9px 1px #BDBDBD;}
	</style>
</head>
<body style="margin:0; padding:0;">
			<!-- navigation bar -->
		<div class="abovenavbar">
		     <div  class="start-white">
				<div style="width:1060px; margin:auto;padding-top: 10px;">
					<%--统一登录界面--%>
					<span style="float: right;"><a onclick="login();">登录</a></span>
					<span style="float: right;padding-left:12px;padding-right:12px;">|</span>
					<span style="float: right;"><a onclick="regist();">注册</a></span>
					<span>WellAssist供应链系统</span>
				</div>
			</div>
         </div>
    <div class="layout" style="background-color:#f7f7fa;" > 
	<div align="center" style="background:url('${pageContext.request.contextPath}/resources/wella/front/images/sender/banner2.png');height: 405px;">
	<img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/banner1.png" style="width:1158px;height: 405px;"></div>
	<div class="start-heading1" >公司简介</div>
	<div style="background:#0077dd; width:50px; height:2px; margin:auto;margin-bottom:30px"></div>
    <div class="start-content" style="margin-bottom:40px">
	   重石油昆仑燃气有限公司（简称昆仑燃气公司）市委实现天然气业务上中下游一体化，更好地履行责任，
       服务社会， 经中国石油天然气集团公司批准、国家工商管理总局核准于2008年8月6日， 由中石油天然气管道燃气投资有限公 司、中国华油集团燃气事业部、中油然气有限责任公司、重组整合成立， 是中国石油城市燃气运菅的专业化公司。公司注册资本金60.6亿元。
	</div>
    <div class="start-heading1 start-white" >企业案例</div>
	<div   class="start-white" >
	<div style="background:#0077dd; width:50px; height:2px; margin:auto;"></div>
    <table  class="start-table1" style="width:1120px; margin:auto;">
     <tr>
		<td class="start-table"  onclick = "toURL('goto', 'http://www.kunlun.com.hk/c/index.php')">
		  <table>
		   <td>
			   <tr><div align="center"><img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/customer1.png" style="height:100px;"></div></tr>
				<tr>
				<div  class="ware_maker_label" style="padding:4px;text-align: center;" >昆仑能源</div>
				<div style="width: 100%;text-align: left; font-size:14px;">
	                                                        重石油昆仑燃气有限公司（简称昆仑燃气公司）
	                                                          市委实现天然气业务上中下游一体化,更好地履行责任，服务社会
	           </div>
			   </tr>
			</td>
		  </table>
		</td>
		<td class="start-table" onclick = "toURL('goto', 'http://www.yuanhenggas.com/')">
			<table>
		   <td>
			   <tr>
				<div align="center"><img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/customer2.png" style="height:100px;"></div></tr>
				<tr>
				<div  class="ware_maker_label" style="padding:4px;text-align: center;" >元享燃气
				</div>
				<div style="width: 100%;text-align: left; font-size:14px;">
	           重石油昆仑燃气有限公司（简称昆仑燃气公司）
	           市委实现天然气业务上中下游一体化,更好地履行责任，服务社会
	           </div>
			   </tr>
			</td>
		 </table>
		</td>
		<td class="start-table"  onclick = "toURL('goto', 'http://klrq.cnpc.com.cn/')">
			<table>
		   <td>
			   <tr>
				<div align="center"><img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/customer3.png" style="height:100px;"></div></tr>
				<tr>
				<div  class="ware_maker_label" style="padding:4px;text-align: center;" >昆仑燃气
				</div>
				<div style="width: 100%;text-align: left; font-size:14px;">
	                               重石油昆仑燃气有限公司（简称昆仑燃气公司）
	                              市委实现天然气业务上中下游一体化,更好地履行责任，服务社会
	           </div>
			   </tr>
			</td>
		 </table>
		</td>
		<td class="start-table"  onclick = "toURL('goto', 'http://www.sxycpc.com/')">
			<table>
		   <td>
			   <tr>
				<div align="center"><img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/customer4.png" style="height:100px;"></div></tr>
				<tr>
				<div  class="ware_maker_label" style="padding:4px;text-align: center;" >延长石油
				</div>
				<div style="width: 100%;text-align: left; font-size:14px;">
	                                                重石油昆仑燃气有限公司（简称昆仑燃气公司）
	                                                市委实现天然气业务上中下游一体化,更好地履行责任，服务社会
	           </div>
			   </tr>
			</td>
		 </table>
		</td>
	</tr>
</table>
</div>
<div class="start-heading1" style="background:#f7f7fa;">作合伙伴</div>
<div align="center" style="background:#f7f7fa;margin-bottom:60px;">
<div style="background:#0077dd; width:50px; height:2px; margin:auto;margin-bottom:30px"></div>
<table style="border-spacing: 20px;">
     <tr>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.citicbank.com/')"  alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark1.png" onclick = "toURL('goto', 'http://www.citicbank.com/')">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.cib.com.cn/cn/index.html')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark2.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.bankofbeijing.com.cn/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark3.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.bankofshanghai.com/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark4.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://card.cgbchina.com.cn/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark5.png">
			</div>
		</td>
	</tr>
</table>
<table  style="border-spacing: 20px;margin-top: -50px">
     <tr>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.icbc.com.cn/icbc/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark6.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.ccb.com/cn/home/indexv3.html')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark7.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.innovationpay.com.hk/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark8.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'https://www.antgroup.com/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark9.png">
			</div>
		</td>
	</tr>
</table>
<table  style="border-spacing: 20px;margin-top: -50px">
    <tr>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.jsbchina.cn/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark10.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.czbank.com/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark11.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.chinaunicom.com.cn/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark12.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.10086.cn/zj/index_571_571.html')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark13.png">
			</div>
		</td>
		<td >
			<div class="start-unhang" style="background-image: url('${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhang-back.png');">
				<img style = "cursor:pointer;"  onclick = "toURL('goto', 'http://www.chinatelecom.com.cn/')" alt="" src="${pageContext.request.contextPath}/resources/upload/images/sender/bank/unhangmark14.png">
			</div>
		</td>
	</tr>
</table>
</div>
<div class="start-heading1 start-white" align="center" style="">成为会员  </div>
<div class="start-white" align="center" style="padding-bottom: 70px;padding-top: 0px;">
	<div style="background:#0077dd; width:50px; height:2px; margin:auto;margin-bottom:80px"></div>
  <span style="cursor:pointer;"><a href="<c:url value="/front/customer/CustomerLoginCtrl-register"/>"> 
  <img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/sender_member1.png"></a></span>
  <span style="cursor:pointer;"> <a href="<c:url value="/front/sender/SenderLoginController-register"/>">
  <img src="${pageContext.request.contextPath}/resources/wella/front/images/sender/sender_member2.png"></a></span>
</div>
<div class="start-heading1" style="background: #f7f7fa;" align="center" style="">物流订单</div>
<div style="background: #f7f7fa; padding-bottom:50px;"> 
	<div style="background:#0077dd; width:50px; height:2px; margin:auto;margin-bottom:0px"></div>
	<div class = "cd-rect" style="padding:5px;">
		<c:forEach var="item" items="${recVehicleList}">
			<div class = "row-fld">	
			   	<div style="padding:20px 10px 10px 10px;text-align:left;">
					<div style="margin-bottom: 5px;"><span style="">提货地点: </span><span >${item.fromRegionName}</span></div>
					<div style="margin-bottom: 5px;"><span style="">配货地点: </span><span >${item.toRegionName}</span></div>
					<div style="margin-bottom: 5px;"><span style="">载货量: </span><span >${item.saleNum}顿</span></div>
				</div>
				<div class="ware_button" style="line-height:30px; font-size:17px;" onClick = "toURL('login', '${item.vehicleTrans}')">抢&nbsp;&nbsp;&nbsp;单</div>
			</div>
		</c:forEach>
    </div>
</div>
</div>
<div  class="start-heading1" style="background:#ffffff;padding:20px;font-size:16px;text-align:center;">
<span >联系地址:</span>
<span >&nbsp;&nbsp;联系地址联系地址联系地址联系地址</span>
<span style="margin-left: 5%;">联系地址:</span>
<span >&nbsp;&nbsp;联系地址联系地址联系地址联</span>
</div>
</body>

<script>
	// 功能函数	
	function toURL(action, id){
		var url = "";
		if (action == 'login'){ // 抢单点击
			url = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-qdPage?vehicleTrans="+id;
			window.location.href = url;
		}else if(action == 'goto'){
			window.open(id);
		}	
	}
	function login(){
	    url = "${pageContext.request.contextPath}/login/page";
	    window.location.href = url;
	}
	function regist(){
        url = "${pageContext.request.contextPath}/login/page";
        window.location.href = url;
	}
</script>
</html>

