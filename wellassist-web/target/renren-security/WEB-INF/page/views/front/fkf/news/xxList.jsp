<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>

<style>
	div.newsitem{
		font-size: 14px;
		border-bottom: solid 1px #ccc;
		padding-top: 12px;
		padding-bottom: 8px;
		display: inline-block;
		width: 100%;
		min-height: 30px;
		text-align: left;
	}
	div.newsincontent{
		clear: both;
		display: none;
		margin-left: 5%;
		margin-right: 14%;
		word-break: break-all;
		padding: 12px 0px;
		color: #777;
	}
</style>

</head>
<div id = "content-rect">
	<div style="border:solid 1px #d0d0d0;font-size:18px;font-weight:bold;color:#0557ab;line-height:36px;text-align: left;">&nbsp;&nbsp;消息列表</div>
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/fkf/FkfNewsController-xxList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<%--
		<div class="row-header">
		     <span class="header-title">订单列表</span>
		     <div style="float:right;">
		     	<input type="text" name="prodName" style="height:10px; float:left;margin-bottom:0px;" value="${param.prodName}"/>
		     	<span class="span_search_btn_blue" style="margin-bottom:0px;" onclick="searchData(1);">搜索</span>
		     </div>
		</div>
		--%>
	</form>
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:32px;">
		<div style="float:left;">
			<input type="button" id="selReadBtn" value="标记所选为已读" />
			<input type="button" id="selDelBtn"  value="删除所选" />
			<input type="button" id="allDelBtn"  value="清空所有" />
		</div>
		<div style="float:right;margin-top:12px;">未读 <span id="noReadCount">${noReadCount}</span>/全部 <span>${totalCount}</span></div>
	</div>
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;text-align:left;">
		<div style="float:left;width:5%;">选择</div>
		<div style="float:left;width:65%;">标题</div>
		<div style="float:left;width:20%;">时间</div>
		<div style="float:left;width:8%;">状态</div>
	</div>
	<c:forEach var="item" items="${newsList}">
		<div class="newsitem">
			<div>
				<div style="float:left;width:5%;"><input name="chkNewsId" value="${item.txId}" type="checkbox"></div>
				<div style="float:left;width:65%;"><a style="cursor: pointer;" onclick="viewContent('${item.txId}')">${item.txName}</a></div>
				<div style="float:left;width:20%;"><fmt:formatDate value="${item.txDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				<div style="float:left;">
					<input type="hidden" id="isReadHidden_${item.txId}" value="${item.isRead}" />
					<c:if test="${item.isRead==0}"><img id="isReadImg_${item.txId}" src="<c:url value="/resources/wella/front/images/news_unread.png"/>"></c:if>
					<c:if test="${item.isRead==1}"><img id="isReadImg_${item.txId}" src="<c:url value="/resources/wella/front/images/news_read.png"/>"></c:if>
				</div>
			</div>
			<div class="newsincontent" id="newsincontent_${item.txId}">
				${item.txContent}
			</div>
		</div>
	</c:forEach>
	<c:if test="${newsList== null || fn:length(newsList) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有资料</div>	 
    </c:if>	
	<div class="right-pagination">
		<%@ include file="../../pagination.jsp"%>
    </div>
</div>

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
 		setPageUrl(url);
 		
 		$("#selReadBtn").click(function(){
 			
 			var ids = "";
 			
 			$("input[name='chkNewsId']").each(function(){
 				if($(this).is(':checked')) {
 					ids = ids == "" ? $(this).val() : ids + "," + $(this).val();    
 				}
 			});
 			
 			if(ids!=""){
 				if(confirm("你要确定已读操作吗?")){
 					$.post("${pageContext.request.contextPath}/front/fkf/FkfNewsController-setIsRead",{ids:ids},function(data){
 			    		data = $.parseJSON(data);
 			            if(data.status=="1"){
 			            	window.location.reload();
 						}
 			      	})
 			      	.error(function(data){
 			      		alert("操作失败！")
 			      	});
 				}
 			}
 			
 		});
 		
		$("#selDelBtn").click(function(){
			var ids = "";
 			
 			$("input[name='chkNewsId']").each(function(){
 				if($(this).is(':checked')) {
 					ids = ids == "" ? $(this).val() : ids + "," + $(this).val();    
 				}
 			});
 			
 			if(ids!=""){
 				if(confirm("你要确定删除操作吗?")){
 					$.post("${pageContext.request.contextPath}/front/fkf/FkfNewsController-delXx",{ids:ids},function(data){
 			    		data = $.parseJSON(data);
 			            if(data.status=="1"){
 			            	searchData(1);
 						}
 			      	})
 			      	.error(function(data){
 			      		alert("操作失败！")
 			      	});
 				}
 			}
 		});
 		
		$("#allDelBtn").click(function(){
			if(confirm("你要确定所有删除操作吗?")){
				$.post("${pageContext.request.contextPath}/front/fkf/FkfNewsController-delXx",{ids:0},function(data){
		    		data = $.parseJSON(data);
		            if(data.status=="1"){
		            	searchData(1);
					}
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
			}
		});
	});
	
	function viewContent(txId){
		if($("#newsincontent_" + txId).css('display')=='none'){
			if($("#isReadHidden_" + txId).val()=="0"){
				$.post("${pageContext.request.contextPath}/front/fkf/FkfNewsController-setIsRead",{ids:txId},function(data){
		    		data = $.parseJSON(data);
		            if(data.status=="1"){
		            	var noReadCount = $("#noReadCount").html();
		            	if(parseInt(noReadCount)>0){
		            		$("#noReadCount").html(parseInt(noReadCount)-1);
		            	}
		            	$("#isReadHidden_" + txId).val(1);
						$("#isReadImg_" + txId).attr("src","<c:url value='/resources/wella/front/images/news_read.png'/>");
					}
		      	})
		      	.error(function(data){
		      		alert("操作失败！")
		      	});
			}
			$("#newsincontent_" + txId).show();
		} else {
			$("#newsincontent_" + txId).hide();
		}
	}
</script>
<%@ include file="../footer.jsp"%>