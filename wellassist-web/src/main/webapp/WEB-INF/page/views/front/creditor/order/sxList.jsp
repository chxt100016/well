<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>

<style>
	.td_div{line-height:40px;height:40px;font-size:14px;float:left; border:none; border-right: solid 1px #d0d0d0;}	
	.td_div1{height:40px; line-height:40px;font-size:16px; float:right; border:none;text-align:center;}
	a{font-size: 13px; text-decoration: none; cursor: pointer;}
</style>

</head>
<div id = "content-rect">
	<form id="searchFrm" method="POST" action="${pageContext.request.contextPath}/front/fkf/FkfOrderCtrl-sxList">
		<input type="hidden" id="page" name="page" value="${param.page}">
		<input type="hidden" id="creditState" name="creditState" value="${param.creditState}">	
		<div class = "row-header">
		     <span class = "header-title">授信列表</span>
		     <div style = "float:right;">
		     	<input type = "text" name = "userName" style = "height:10px; float:left;margin-bottom:0px;" value = "${param.userName}" placeholder = "买家名"/>
		     	<span class="span_search_btn_blue" style = "margin-bottom:0px;" onclick = "searchData(1);" >搜索</span>
		     </div>
		</div>
	</form>
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;margin-top:16px;height:20px;">
		<div style="width:20%;text-align:center;font-size:16px;float:left;">申请时间</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">申请金额</div>
		<div style="width:20%;text-align:center;font-size:16px;float:left;">利息率</div>
		<div style="width:10%;text-align:center;font-size:16px;float:left;">
			<span class="dropdown"><a data-toggle="dropdown" class="dropdown">状态&nbsp;<b class="icon-angle-down"></b></a>
				<div class="dropdown-menu">
					<div onClick = "setState('');">全部</div>
					<div onClick = "setState('2');">待审核</div>
					<div onClick = "setState('3');">已审核</div>
					<div onClick = "setState('-2');">不通过</div>
				</div>
			</span>
		</div>
		<div style="width:30%;text-align:center;font-size:16px;float:left;">操作</div>
	</div>

	<c:forEach var = "item" items = "${list}">
		<div style="border:solid 1px #d0d0d0;font-size:24px;margin-top:16px; overflow:auto;">
			<c:forEach var="item0" items = "${item}" begin="0" end="0">
				<div style="height:60px;background:#e0e0e0;font-size:16px;">
					<div style = "margin-left:10px;line-height:56px; float:left;">
						<c:if test = "${item0.companyImg == ''}">
							<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:50px; height:50px;" /></a>
						</c:if>
						<c:if test = "${item0.companyImg != ''}">
							<a class="fancybox" href="${pageContext.request.contextPath}/${item0.companyImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item0.companyImg}"  style="width:50px; height:50px;"  onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>	
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
					<div style = "margin-right:10px; color: #807B7B; float:right; display:none;">
						<span class="span_btn_gray" onClick = "openAddProdDlg('${item0.userId}', ${item0.gysId})">添加</span>
					</div>
				</div>
			</c:forEach>
			<c:forEach var = "item2"  items = "${item}"> 
				<div style = "border-bottom: solid 1px#E0E0E0; overflow:auto;">
					<div class="graybox td_div"   style="width:20%;">
						<fmt:formatDate value="${item2.creditSqDate}" pattern="yyyy-MM-dd"/>
					</div>
					<div class="grayboxwithoutleft td_div" style="width:20%;">
						${item2.creditMoney}
					</div>
					<div class="grayboxwithoutleft td_div"  style="width:20%;">
						${item2.lixiRate}%
					</div>
					<div class="grayboxwithoutleft td_div"  style="width:10%;">
						<c:if test = "${item2.creditState == '-2'}">
							不通过
						</c:if>
						<c:if test = "${item2.creditState == '2'}">
							待审核
						</c:if>
						<c:if test = "${item2.creditState == '3'}">
							已审核
						</c:if>
					</div>
					<div class="grayboxwithoutleft td_div1" style="width:29%;">
						<c:if test = "${item2.creditState == '2'}">		
							<a onClick = "toURL('detailSx', '${item2.creditId}')">详情</a>
						</c:if>
						<c:if test = "${item2.creditState == '3'}">
							账期:&nbsp;&nbsp;${item2.creditOverDate}
						</c:if>		
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

<script type = "text/javascript">
	// 初始化函数
	$(function(){
		$('.fancybox').fancybox();
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
	});
	
	// 功能函数
	function toURL(action, id){
		var url = "";
		if(action == 'delProd'){ // 商品删除
			if(!confirm('您确定操作吗？')) return;
			var url = "${pageContext.request.contextPath}/front/seller/SellerOrderController-delUserProd?logId="+id;
			$.post(url,{},function(data){
	    		data = $.parseJSON(data);
	            if(data.state==1 ){
	            	alert(data.content);
	            	window.location.reload();
	            }else{
	            	alert(data.content);
	            }
	      	});
		}else if(action == 'detailSx'){// 详情
			url = "${pageContext.request.contextPath}/front/fkf/FkfOrderCtrl-sxDetail?creditId="+id;
			window.location.href = url;
		}
	}
	// 状态选择函数
	function setState(state){
		$("#creditState").val(state);
		$("#searchFrm").submit();
	}
	
	
	// 显示选择商品的dialog的函数
	function openAddProdDlg(userId, gysId){
		var url = "${pageContext.request.contextPath}/front/seller/SellerOrderController-addProdList?userId="+userId+"&gysId="+gysId;
		window.open(url, "选择添加商品", " height = 500, width = 500, top = 300 , left = 300  ");
	}
</script>
<%@ include file="../footer.jsp"%>