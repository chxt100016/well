<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<style>
	.cd-rect{width: 1000px; margin: auto;overflow: auto;}
	.row-fld{width:300px; background: white;box-shadow: 3px 3px 3px #c0c0c0;padding:0px;overflow: auto; float: left; margin-top:10px; margin-right:20px;}
	.sp_img{width:290px; height:220px;}
</style>

<div class= "container1">
	<div class="container2">
	<div class = "" style="padding:5px; width:80%; margin:0px auto">
		 <c:forEach var="item" items="${spList}">
			<div  class = "row-fld" style="">
				<div style="position:relative;">
					<%--<a href="${pageContext.request.contextPath}/front/customer/CustomerHomeCtrl-prodDetail?prodId=${item.prodId}">--%>
						<a href="${pageContext.request.contextPath}/userinfo/prodDetail?prodId=${item.prodId}">
							<img class = "sp_img" src="${item.prodImg}" onerror = "noExitImg(this, '${pageContext.request.contextPath}');">
					</a>
					<div class="img-title"><span>
						<c:if test = "${item.prodType == '0'}">
							气体
						</c:if>
						<c:if test = "${item.prodType == '1'}">
							燃油
						</c:if>
						<c:if test = "${item.prodType == '2'}">
							管道气
						</c:if>
						</span></div>
				</div>
				<div style="padding:10px;">
					<span class="ware_maker_label">${item.prodName}</span>
					<a href="${pageContext.request.contextPath}/customer/makeOrder?prodId=${item.prodId}">
						<span class="ware_button" style="float:right; <c:if test = "${user.userState != 1}">display:none;</c:if>">下单</span>
					</a>
					<div style="clear:both;"></div>
				</div>
			</div>
	 	</c:forEach> 
	</div>
	</div>
</div>

<div style="clear:both;width:50%;height:30px;"></div>

<script type = "text/javascript">

</script>

<%@ include file="../footer.jsp"%>