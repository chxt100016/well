<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">等级设置</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxszDengjiSzCtrl-getYhDengjiList" >	
	            		<label>
	            			公司名:<input type="search" name = "userName"  value = "${param.userName}"  class="form-control input-sm houtai-search-control" placeholder="公司名" >
	            			&nbsp;&nbsp;
	            			公司类型:
	            			<select name = "userType" style = "width:100px;">
	            				<option value = "">全部</option>
	            				<option value = "0" <c:if test = "${param.userType == 0 }"> selected = "selected"</c:if>>供货商</option>
	            				<option value = "1" <c:if test = "${param.userType == 1 }"> selected = "selected"</c:if>>客户方</option>
	            				<option value = "2" <c:if test = "${param.userType == 2 }"> selected = "selected"</c:if>>放款方</option>
	            				<option value = "3" <c:if test = "${param.userType == 3 }"> selected = "selected"</c:if>>物流方</option>
	            			</select>
	            			联系人:<input type="search" name = "companyLxr"  value = "${param.companyLxr}"  class="form-control input-sm houtai-search-control" placeholder="联系人">
	            			联系电话:<input type="search" name = "companyLxrPhone"  value = "${param.companyLxrPhone}"  class="form-control input-sm houtai-search-control" placeholder="联系电话">
	            		</label>
	            		<input type = "hidden" id = "page" name = "page" value = "${page}"/>
	            		<button type="button" class="btn btn-default houtai-search-control houtai-search-btn" onclick = "$('#searchFrm').submit();">搜索</button>
	            		<button type="button" id = "btn_clear"  class="btn btn-default houtai-search-control houtai-search-btn" >清空</button>
	            	</form>
	            </div>
            </div>
        </div>
      	
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th width ="10%">No </th>
							<th>图片</th>
							<th>公司名</th>
							<th>公司类型</th>
							<th>联系人</th>
							<th>联系人电话</th>
							<th>余额</th>
							<th>冻结金额</th>
							<th>授信金额</th>
							<th>等级</th>
							<th>最小限度</th>
							<th>最大限度</th>
							<th style = "text-align:right;">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td>${status.count + start}</td>
									<td>
										<c:if test = "${item.companyYyZzImg != ''}">
											<a class="fancybox" href="${pageContext.request.contextPath}/${item.companyYyZzImg}" data-fancybox-group="gallery" title=""><img src="${pageContext.request.contextPath}/${item.companyYyZzImg}" alt="" style="width:50px; height:50px;" onerror = "noExitImg(this, '${pageContext.request.contextPath}');"/></a>		
										</c:if>
									</td>
									<td>${item.userName}</td>
									<td>
										<c:if test = "${item.userType == 0}">供货商</c:if>
										<c:if test = "${item.userType == 1}">客户方</c:if>
										<c:if test = "${item.userType == 2}">放款方</c:if>
										<c:if test = "${item.userType == 3}">物流方</c:if>
									</td>
									<td>${item.companyLxr}</td>
									<td>${item.companyLxrPhone}</td>
									<td>${item.userMoney}</td>
									<td>${item.userLockMoney}</td>
									<td>${item.userCreditMoney}</td>
									<td>${item.creditDengji}</td>
									<td>${item.moneyFw}</td>
									<td>${item.moneyFw1}</td>
									<td style = "text-align:right;">
										<a onclick="showDialog('${item.userId}', '${item.creditDengji}')" class="btn mini icn-only" >设置</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${list== null || fn:length(list) == 0}">
						    	 <tr><td colspan="15">没有资料</td></tr>	 
						    </c:if>
	                    </tbody>
                  </table>
            </div>
		</div>
		
		<div id="table_pagination" class="table_pagination">
			<%@ include file="../pagination.jsp"%>
		</div>
	</div>
</div>

<form class="form-horizontal" name  = "dlgForm" id = "dlgForm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxszDengjiSzCtrl-saveUserDengji" >
	<div class="modal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick = "$('.modal').hide();"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">设置等级</h4>
	      </div>
	      
	      <div class="modal-body">
	            <div class="box-body">
	              <div class="form-group">
	                <label  class="col-sm-3 control-label">等值栏目：</label>
	                <div class="col-sm-9">
	                	<select name = "creditDengji" style = "width:200px;">
	                		<option value = "0">未设置</option>
		                  	<c:forEach var="item" items="${djList}">
		                  		<option value = "${item.degreeId}">${item.moneyFw}&nbsp;&nbsp;~&nbsp;&nbsp;${item.moneyFw1}</option>
		                  	</c:forEach>
	                  	</select>
	                </div>
	              </div>
	            </div>
	            <input type = "hidden" id = "DlgUserId" name = "userId"/>
	         
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default pull-left" data-dismiss="modal" onclick = "$('.modal').hide();">关闭</button>
	        <button type="button" class="btn btn-primary" onclick = "$('#dlgForm').submit();">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
</form>

<script type="text/javascript">
    // 初始化函数
	$(function(){
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
		
		$('.fancybox').fancybox();

		
		$("#btn_clear").click(function(){
 			$("input[name='userName']").val("");
 			$("select[name='userType']").val("");
 			$("input[name='companyLxr']").val("");
 			$("input[name='companyLxrPhone']").val("");
 		});

		
		// validate检查
		$("#dlgForm").validate({
	    rules: {
	    },
	    
	    messages: {
	    	
	    },
	    errorPlacement: function (error, element) { 
	    },
	    submitHandler: function(form){
	    	$.post($(form).attr("action"),$(form).serialize(),function(data){
	    		data = $.parseJSON(data);
	            if(data.state==1 ){
	            	alert(data.content);
	            	$('.modal').hide();
	            	window.location.reload();
	            }else{
	            	alert(data.content);
	            }
	      	});
	    	}
	    });
	});
    

	// 功能函数
	function toURL(action, id){
	}
	
	// dialog函数
	function showDialog(userId, degreeId){
		$("select[name='creditDengji']").val(degreeId);
		$("#DlgUserId").val(userId);
		$('.modal').show();
		
	}
</script>
<%@ include file="../footer.jsp"  %>