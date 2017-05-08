<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">物流详情</h3>
            </div>
        </div>
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th width = "15%">车牌号</th>
							<th width = "15%">联系人</th>
							<th width = "15%">电话</th>
							<th width = "10%">运送量(吨)</th>
							<th width = "15%">出发时间</th>
							<th width = "15%">到达时间</th>
							<th width = "10%">状态</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "15%">${item.vehicleNo}</td>
									<td width = "15%">${item.sjLxr}</td>
									<td width = "15%">${item.sjLxrPhone}</td>
									<td width = "10%">${item.vehicleSjSize}</td>
									<td width = "15%"><fmt:formatDate value="${item.cfDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td width = "15%"><fmt:formatDate value="${item.ddDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td width = "10%">
										<c:if test = "${item.vehicleState == 0}">待发送</c:if>
										<c:if test = "${item.vehicleState == 1}">发送中</c:if>
										<c:if test = "${item.vehicleState == 2}">已发送</c:if>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${list== null || fn:length(list) == 0}">
						    	 <tr><td colspan="11">没有资料</td></tr>	 
						    </c:if>
	                    </tbody>
                  </table>
            </div>
		</div>
		
		<div>
			<input type="button" id="btn_cancle" value="取消" style="width: 50px; line-height: 25px; margin-left: 20px;">
		</div>
		
	</div>
</div>

<script type="text/javascript">
    // 初始化函数
	$(function(){
		$("#btn_cancle").click(function(){
			goBack();
		})
	});
</script>

<%@ include file="../footer.jsp"  %>