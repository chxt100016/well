<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error-div{text-align:left; line-height: 10px;}
</style>

<div class="box" >
	<div class="col-xs-12">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">授信信息</h3>
           	</div>
           	<div class="box-body">
           		<form id="sellerEditForm" name="sellerEditForm" action="${pageContext.request.contextPath}/houtai/user/HoutaiUserSellerCtrl-setSellerInfo">
            		
            		<div class="dl-horizontal detail-hz">
						<div class="pull-left col-xs-12 ddt-hz">授信方信息</div>
						<div class="pull-left col-xs-12 ddOdd">
							<dl>
		               			<dt>企业名称 : </dt>
		               			<dd><span>${info.userName}</span></dd>
		               		</dl>   							
		               		<dl>
		               			<dt>图片 : </dt>
		               			<dd>
		               				<c:if test = "${info.userImg != ''}">
		               					<a class="fancybox" href="${pageContext.request.contextPath}/${info.userImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${info.userImg}"  style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
		               				</c:if>
		               				<c:if test = "${info.userImg == ''}">
		               					<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:80px; height:80px;" /></a>
		               				</c:if>
		               			</dd>
		               		</dl>
		               		<dl>
		               			<dt>联系人 : </dt>
		               			<dd><span>${info.userLxr}</span></dd>
		               		</dl>
		               		<dl>
		               			<dt>联系电话 : </dt>
		               			<dd><span>${info.userLxrPhone}</span></dd>
		               		</dl>
		   				</div>
		   				<c:if test = "${ !empty info.creditUserName}">
			   				<div class="pull-left col-xs-12 ddt-hz">放款方信息</div>
							<div class="pull-left col-xs-12 ddOdd">
								<dl>
			               			<dt>企业名称 : </dt>
			               			<dd><span>${info.creditUserName}</span></dd>
			               		</dl>   							
			               		<dl>
			               			<dt>图片 : </dt>
			               			<dd>
			               				<c:if test = "${info.creditUserImg != ''}">
			               					<a class="fancybox" href="${pageContext.request.contextPath}/${info.creditUserImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${info.creditUserImg}"  style="width:80px; height:80px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>
			               				</c:if>
			               				<c:if test = "${info.creditUserImg == ''}">
			               					<a class="fancybox" href="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/resources/common/images/icon_user_def.jpg"  style="width:80px; height:80px;" /></a>
			               				</c:if>
			               			</dd>
			               		</dl>
			               		<dl>
			               			<dt>联系人 : </dt>
			               			<dd><span>${info.creditUserLxr}</span></dd>
			               		</dl>
			               		<dl>
			               			<dt>联系电话 : </dt>
			               			<dd><span>${info.creditUserLxrPhone}</span></dd>
			               		</dl>
			   				</div>
			   			</c:if>
		   				
		   				<div class="pull-left col-xs-12 ddt-hz">授信历史</div>
						<div class="pull-left col-xs-12" style = "padding:0px;">
	               			<table id="example2" class="table table-bordered table-striped table-hover">
			                    <thead>
			                      <tr>
									<th width="5%">No </th>
									<th width="20%">操作人</th>
									<th width="20%">操作内容</th>
									<th width="20%">授信状态</th>
									<th width="20%">操作时间</th>
									<th width="15%">操作IP</th>
								  </tr>
			                    </thead>
                    			<tbody>
                    				<c:forEach var="item" items="${list}" varStatus="status">
                    					<tr>
	                    					<td width="5%">${status.count}</td>
											<td width="20%">${item.mgrName}</td>
											<td width="20%">${item.content}</td>
											<td width="20%">
												<c:if test = "${item.creditState == -1}">审核不通过</c:if>
												<c:if test = "${item.creditState == 0}">未审核</c:if>
												<c:if test = "${item.creditState == 1}">审核通过</c:if>
												<c:if test = "${item.creditState == 2}">确认中</c:if>
												<c:if test = "${item.creditState == 3}">还款中</c:if>
												<c:if test = "${item.creditState == 4}">还款完成</c:if>
											</td>
											<td width="20%"><fmt:formatDate value="${item.mgrDate}" pattern="yyyy年MM月dd日"/></td>
											<td width="15%">${item.mgrIp}</td>
										</tr>
                    				</c:forEach>
	                    				<c:if test="${list== null || fn:length(list) == 0}">
											<tr><td colspan="8">没有资料</td></tr>	 
										</c:if>
								</tbody>
                 			</table>
		   				</div>
			   			<div class="pull-left col-xs-12 ddt-hz">
			   				<dl>
   								<dt><input type="button" id="btn_cancle" value="返回" style="width: 50px; line-height: 25px; " /></dt>
   							</dl>
	   					</div>
             		</div>
             	</form>
           </div>
        </div>
	</div>
</div>

<script type="text/javascript">
	// 初始化函数
 	$(function(){
 		$('.fancybox').fancybox();
 		
 		$('#btn_cancle').click(function() {
 			goBack();
		});
 	});
</script>

<%@ include file="../footer.jsp"  %>