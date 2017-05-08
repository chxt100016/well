<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">交易详情</h3>
            </div>
        </div>
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th width = "15%">处理人</th>
							<th width = "60%">内容</th>
							<th width = "25%">处理时间</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td width = "15%">${item.userName}</td>
									<td width = "60%">${item.content}</td>
									<td width = "25%">
										<fmt:formatDate value="${item.mgrDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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