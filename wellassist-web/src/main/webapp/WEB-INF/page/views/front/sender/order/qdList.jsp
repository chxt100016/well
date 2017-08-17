<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <style>
 .ulSelected{
            color: #617B90;
            border-bottom: 2px solid #617B90;
        }
 </style>
<div class="container1">
	<div class="container2">


<div id = "content-rect" style="width:90%;" >
	<!-- <div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align:left;">&nbsp;&nbsp;抢单列表</div> -->

	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/sender/logisticsGrabList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="grabState" name="grabState" value="${param.grabState}">
		<div class="row-header">
		     <h4 class="ui header">订单列表</h4>
    		<div class="ui divider"></div>
		     <div class="ui input"style="float:right;">

		     	<input type="text" name="orderNo" style="height:33px; float:left;margin-bottom:0px;" value="${param.orderNo}" placeholder = "订单编号"/>
		     	<span class="ui button small blue" style="margin-bottom:0px;" onclick="searchData(1);">搜索</span>
		     </div>
		</div>
	</form>
	
	<ul class="ds-bl pd-10 ft-sz-15">
            <li class="ds-bl fl-lt pd-10  ulSelected">全部订单</li>
            <li class="ds-bl fl-lt pd-10">已选择</li>
            <li class="ds-bl fl-lt pd-10">未选择</li>
            <li class="ds-bl fl-lt pd-10">已取消</li>
        </ul>
        <br>
        <table class="ui basic table">
            <thead>
                <tr class="grey-4">
                    <th width="30%">产品详情</th>
                    <th>卖家</th>
                    <th>买家</th>
                    <th>报价（元）</th>
                    <th>
							<div class="ui dropdown">
									<div class="text">状态</div>
									<i class="dropdown icon"></i>
									<div class="menu">
											<div class="item" onclick="$('#grabState').val('');searchData(1);">全部</div>
											<div class="item" onclick="$('#grabState').val('-1');searchData(1);">取消</div>
											<div class="item" onclick="$('#grabState').val('0');searchData(1);">未确定</div>
											<div class="item" onclick="$('#grabState').val('1');searchData(1);">确定</div>
									</div>
							</div>
					</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
					<c:forEach var="item" items="${list}">
                <tr class="blue-3">
                    <td colspan="5"><span></span>${item.grapDate} <span>订单号：${item.orderNo}</span></td>
                    <td> <a href="" ><i class="trash icon"></i></a></td>
                </tr>
                <tr>
                    <td class="right-border">
                        <img src="${item.prodImg}" alt="" width="100px" height="74px" class="ds-bl fl-lt">
                        <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="padding-top: 25px;width: 190px;"> ${item.prodName}</span>
                        <br>
                        <span class="ds-bl fl-lt  pd-lf-20">${item.num}吨</span>
                        
                    </td>
                    <td class="right-border tx-ct" >${item.sellerUserName}</td>
                    <td class="right-border tx-ct" >${item.userName}
                    </td>
                    <td class="right-border tx-ct" >
                        <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style="">${item.prodPrice} 元</span>
                        
                    </td>
                    <td class="right-border tx-ct" >
                         <span class="ds-bl fl-lt pd-lf-20 ft-wt-bd " style=""> 
								<c:if test = "${item.grabState == '-1'}">
										失败
									</c:if>
									<c:if test = "${item.grabState == '0'}">
										未选择
									</c:if>		
									<c:if test = "${item.grabState == '1'}">
										已中标
									</c:if>		
						</span>
                        
                    </td>
                    <td>
                        	<span class="span_btn pointer" >
									<c:if test = "${item.grabState == '0'}">
											<span class="span_btn" onClick = "toURL('cancelGrab','${item2.grabId}')">取消</span>
									</c:if>
									<c:if test = "${item.grabState == '-1'}">
											<span class="span_btn" onClick = "toURL('reGrab','${item2.logisticsInfoId}',this)">再抢单</span>
									</c:if>
							</span>
                    </td>
                  
				</tr>
			</c:forEach>
			
			</tbody>
			<tfoot>
				<tr>
					<td></td>
					<td></td>
					<td>
					<div class="right-pagination">
							<%@ include file="../../pagination.jsp"%>
						</div>
					</td>
			</tfoot>
        </table>
	<c:if test="${list== null || fn:length(list) == 0}">
		     	<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	
</div>
	</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		$('.fancybox').fancybox();
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
	});
	// 功能函数
	function toURL(action, id,com){
		var url = "";
			if(action=='qdPage'){// 抢单页面
				window.location.href = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-qdDetail?grabId=" + id;
			} else if(action=='quxiao'){
				url = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-updateQd?grabId="+id+"&grabState=-1"
				if(!confirm("您确定操作吗？")) return;
				$.post(url,{},function(data){
							alert(data.content);
				            if(data.state==1 ){
				            	window.location.reload();
				            }
				 },"json");
			}else if(action == 'zaiqiangdan'){
				url = "${pageContext.request.contextPath}/front/sender/FrontSenderOrderCtrl-updateQd?grabId="+id+"&grabState=0"
				if(!confirm("您确定操作吗？")) return;
				$.post(url,{},function(data){
							alert(data.content);
				            if(data.state==1 ){
				            	window.location.reload();
				            }
				 },"json");
			}else if(action=='cancelGrab'){
			    if(confirm('你确定取消此抢单？')){
                    $.get('${pageContext.request.contextPath}/sender/cancelGrab',{vehicleGrabId:id},function(data){
                        if(data.code==0){
                            alert('操作成功');
                            window.location.reload();
                        }else{
                            alert(data.content);
						}
                    },'json');
                }
            }else if (action=='reGrab'){
			    $.get('${pageContext.request.contextPath}/sender/reGrab',{logisticsId:id},function (data) {
					if(data.code==0){
                        window.location.href='${pageContext.request.contextPath}/sender/grabLogistics?logisticsId='+id;
					}else{
					    alert(data.msg);
					    com.style.display="none";
                    }
                },'json');
            }
	}
</script>

<%@ include file="../footer.jsp"%>