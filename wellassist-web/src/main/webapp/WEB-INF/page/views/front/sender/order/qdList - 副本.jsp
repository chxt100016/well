<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 
<div class="container1">
	<div style="margin:40px 0 0 210px;">


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
	
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;    height: 36px;">
		<div style="width:40%;text-align:center;font-size:16px;float:left;">运输产品</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">运输</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">
			状态
			<!-- <span class="dropdown">
				<a data-toggle="dropdown" class="dropdown" style="color: #444444;">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onclick="$('#grabState').val('');searchData(1);">全部</div>
					<div onclick="$('#grabState').val('-1');searchData(1);">取消</div>
					<div onclick="$('#grabState').val('0');searchData(1);">未确定</div>
					<div onclick="$('#grabState').val('1');searchData(1);">确定</div>
				</div> -->
			</span>
		</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>
	<c:forEach var = "item" items = "${list}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;">
			<c:forEach var = "item0" items = "${item}" begin = "0" end = "0">
				<div style="height:60px;background:#e0e0e0;font-size:16px;">
					<div style = "margin-left:10px;line-height:56px; float:left;">
						<c:if test = "${item0.companyImg == '' or empty item0.companyImg}">
							<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:50px; height:50px;" /></a>
						</c:if>
						<c:if test = "${item0.companyImg != '' and not empty item0.companyImg}">
							<a class="fancybox" href="${item0.companyImg}" data-fancybox-group="gallery" title=""><img src="${item0.companyImg}"  style="width:50px; height:50px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
						</c:if>
					</div>
					<div style = "margin-left:10px;line-height:56px; color: #807B7B;float:left; font-size:10px;">
						公司名称:${item0.userName}
					</div>
					<div style = "margin-left:10px;line-height:56px; color: #807B7B; float:left; font-size:10px;">
						联系人:${item0.companyLxr}
					</div>
					<div style = "margin-left:10px;line-height:56px; color: #807B7B; float:left; font-size:10px;">
						联系电话:${item0.companyLxrPhone}
					</div>
				</div>
			</c:forEach>
			<c:forEach var = "item2"  items = "${item}"> 
				<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
					<div class="graybox" style="width:40%;height:110px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;">
						<div style = "margin-left:10px;line-height:106px; float:left;">
							<a class="fancybox" href="${item2.prodImg}" data-fancybox-group="gallery" title=""><img src="${item2.prodImg}"  style="width:80px; height:80px;" /></a>
						</div>
						<div style = "margin-left:10px;line-height:106px; float:left;">
							${item2.prodName}
						</div>
						<div style = "margin-right:10px;line-height:106px; float:right;color:#A1A2A9;">
							${item2.num}吨
						</div>	
					</div>
					
					<div class="grayboxwithoutleft" style="width:20%;line-height:106px;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
						${item2.grabMoney}元
					</div>
					<div class="grayboxwithoutleft" style="width:20%;line-height:106px;height:110px;font-size:14px;float:left;border:none; border-right: solid 1px #d0d0d0;">
						<c:if test = "${item2.grabState == '-1'}">
							失败
						</c:if>
						<c:if test = "${item2.grabState == '0'}">
							未选择
						</c:if>		
						<c:if test = "${item2.grabState == '1'}">
							已中标
						</c:if>		
					</div>
					<div class="grayboxwithoutleft" style="height:110px;font-size:16px; float:right; border:none;text-align:center; width:19%; ">
						<c:if test = "${item2.grabState == '0'}">
							<%--<span class="span_btn" onClick = "toURL('qdPage', '${item2.grabId}')">详情</span>--%>
							<span class="span_btn" onClick = "toURL('cancelGrab','${item2.grabId}')">取消</span>
						</c:if>
						<c:if test = "${item2.grabState == '-1'}">
							<%--<span class="span_btn_gray" onClick = "toURL('zaiqiangdan', '${item2.grabId}')">再抢单</span>--%>
							<span class="span_btn" onClick = "toURL('reGrab','${item2.logisticsInfoId}',this)">再抢单</span>
						</c:if>
						<c:if test = "${item2.grabState == '1'}"><%--暂无操作--%></c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	<c:if test="${list== null || fn:length(list) == 0}">
		     	<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
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