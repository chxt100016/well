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
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/front/seller/SellerNewsController-xxList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<div class="row-header">
		     <span class="header-title">消息中心</span>
		     <div style="float:right;">
		     	<span class="span_search_btn_blue_1"   onclick="searchData(1);">搜索</span>
				 <input type="text" name="title"  value="${param.prodName}"/>
			 </div>
		</div>
	</form>
	<div style="border-bottom:solid 1px #d0d0d0;padding:2px;font-size:14px;margin-top:16px;height:32px;">
		<div style="float:left;">
			<input type="button" id="selReadBtn" value="标记所选为已读" />
			<input type="button" id="selDelBtn"  value="删除所选" />
			<input type="button" id="allDelBtn"  value="清空所有" />
		</div>
		<div style="float:right;margin-top:12px;">未读 <span id="noReadCount">${noReadCount}</span>/全部 <span>${totalCount}</span></div>
	</div>
	<div style="border-bottom:solid 1px #d0d0d0;font-size:14px;margin-top:16px;height:16px;">
		<div style="float:left;width:5%;">选择</div>
		<div style="float:left;width:20%;">标题</div>
		<div style="float:left;width:20%;">时间</div>
		<div style="float:left;width:55%;">内容</div>
	</div>
	<c:forEach var="item" items="${message}">
		<div class="newsitem">
			<div>
				<div style="float:left;width:5%;"><input name="chkNewsId" value="${item.id}" type="checkbox"></div>
				<div style="float:left;width:20%;"><a style="cursor: pointer;" onclick="viewContent('${item.id}')">${item.title}</a></div>
				<div style="float:left;width:20%;">${item.date}</div>
				<div style="float:left;width:55%;">${item.content}</div>
			</div>
		</div>
	</c:forEach>
	<c:if test="${message== null || fn:length(message) == 0}">
		<div style = "margin-top:10px; margin-left:20px; float:left;">没有消息</div>
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
 					$.post("${pageContext.request.contextPath}/front/seller/SellerNewsController-setIsRead",{ids:ids},function(data){
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
 					$.post("${pageContext.request.contextPath}/front/seller/SellerNewsController-delXx",{ids:ids},function(data){
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
				$.post("${pageContext.request.contextPath}/front/seller/SellerNewsController-delXx",{ids:0},function(data){
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
				$.post("${pageContext.request.contextPath}/front/seller/SellerNewsController-setIsRead",{ids:txId},function(data){
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
<%@ include file="../footer.html"%>