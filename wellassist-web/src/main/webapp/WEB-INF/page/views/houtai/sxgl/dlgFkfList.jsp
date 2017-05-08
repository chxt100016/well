<%@ include file="../header.jsp"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	
</head>
<body style="background-color:#FDFDFD !important; padding:20px;min-width:auto">
	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxglSxZpListCtrl-getFkfList">
		<div class="" style="min-width:auto;background:none;border-bottom:2px solid #B1B1B1;margin-top:0px;padding-bottom:5px;text-align:right">
			<input type="text" name="companyName" style="width:100px;margin-bottom:0px;height:30px; padding:5px;" value="${param.companyName}" placeholder="方款放名称">
			<input id="btn_search" type="button" value="搜索">
			
			<a style="float:left;margin-top:7px;font-size:16px;font-weight:600;text-decoration:none;color: #232502;">放款放设定</a>
		</div>
		<input type="hidden" id="page" name="page" value="${page}">
		<input type = "hidden" id = "creditUserId" />
		<input type = "hidden" id = "creditUserName" />
		<input type = "hidden" id = "creditUserImg" />
		<input type = "hidden" id = "creditUserLxr" />
		<input type = "hidden" id = "creditUserLxrPhone" />
	</form>
		
	<table class="page-table table table-striped table-bordered tbl-paper-theme table-paper dataTable" id="data-table" style="margin-top:5px;">
		<thead>
			<tr style="">
				<th width=30 >选择</th>
				<th >放款放名称</th>
				<th >图片</th>
				<th >联系人</th>
				<th >联系电话</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${list}" varStatus="status">
				<tr>
					<td style = "text-align:center;">
						<input type="radio" name = "checkVar" value = "${item.userId}" onclick = "setFkf(this)" />
					</td>
					<td>${item.userName}
						<input type = "hidden" id = "userName" value = "${item.userName}"/>
						<input type = "hidden" id = "companyImg" value = "${item.companyImg}"/>
						<input type = "hidden" id = "companyLxr" value = "${item.companyLxr}"/>
						<input type = "hidden" id = "companyLxrPhone" value = "${item.companyLxrPhone}"/>
					</td>
					<td>
						<c:if test = "${item.companyImg != ''}">
           					<a class="fancybox" href="${pageContext.request.contextPath}/${item.companyImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.companyImg}"  style="width:80px; height:80px;" /></a>
           				</c:if>
           				<c:if test = "${item.companyImg == ''}">
           					<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:80px; height:80px;" /></a>
           				</c:if>
					</td>
					<td>${item.companyLxr}</td>
					<td>${item.companyLxrPhone}</td>
				</tr>
		    </c:forEach>
		    <c:if test="${list== null || fn:length(list) == 0}">
		     <tr><td colspan="10">没有资料!</td></tr>
		    </c:if>
		</tbody>
	</table>
	<div class="page-area">
		<%@ include file="../pagination.jsp"%>
	</div>
	<div style = "text-align:center; margin-top: 20px;">
		<input type="button" value="确定" onclick = "toURL('queding')">
		<input style = "margin-left:20px;"  type="button" value="返回" onclick = "window.close();">
	</div>
</body>	


 <script type="text/javascript">
 	// 初始化函数
 	$(function(){
 		$('.fancybox').fancybox();	
 		$("#btn_search").click(function(){
 			$("#searchFrm").submit();
 		});
 	});
 	// 选择放款放的函数
 	function setFkf(obj){
 		var tr  =  $(obj).parent().parent();
 		var creditUserName = tr.find("#userName").val();
 		var creditUserImg = tr.find("#companyImg").val();
 		var creditUserLxr = tr.find("#companyLxr").val();
 		var creditUserLxrPhone = tr.find("#companyLxrPhone").val();
 		var creditUserId = $(obj).val();
 		$("#creditUserName").val(creditUserName);
 		$("#creditUserImg").val(creditUserImg);
 		$("#creditUserLxr").val(creditUserLxr);
 		$("#creditUserLxrPhone").val(creditUserLxrPhone);
 		$("#creditUserId").val(creditUserId);
 	}
 	
 	function toURL(action){
 		if(action == 'queding'){
 			if($("#creditUserId").val() == ""){
 				alert("请选择放款放！");
 				return;
 			}
 			var retValue = new Array();
 			retValue['creditUserId'] = $("#creditUserId").val();
 			retValue['creditUserName'] = $("#creditUserName").val();
 			retValue['creditUserImg'] = $("#creditUserImg").val();
 			retValue['creditUserLxr'] = $("#creditUserLxr").val();
 			retValue['creditUserLxrPhone'] = $("#creditUserLxrPhone").val();
 			if(opener.setFkf) opener.setFkf(retValue);
 			window.close();
 		}
 	}
 	
 	//搜索函数
	function searchData(page) {
		$("#page").val(page);
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		$("#searchFrm").submit();
	}
 	
</script>