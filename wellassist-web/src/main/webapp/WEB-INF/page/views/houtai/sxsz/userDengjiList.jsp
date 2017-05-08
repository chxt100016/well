<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">等级列表</h3>
            </div>
            <div class = "col-sm-12">	
	            <div class="dataTables_filter">
	            	<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxszUserDengjiCtrl-getUserDengjiList" >	
	            		<label>
	            			最小限度:<input type="search" name = "moneyFw"  value = "${param.moneyFw}"  class="form-control input-sm houtai-search-control" placeholder="最小限度"  onKeyUp="value=value.replace(/[^\d.]/g,'')">
	            			&nbsp;&nbsp;
	            			最小限度:<input type="search" name = "moneyFw1"  value = "${param.moneyFw1}"  class="form-control input-sm houtai-search-control" placeholder="最小限度" onKeyUp="value=value.replace(/[^\d.]/g,'')">
	            		</label>
	            		<input type = "hidden" id = "page" name = "page" value = "${page}"/>
	            		<button type="button" class="btn btn-default houtai-search-control houtai-search-btn" onclick = "$('#searchFrm').submit();">搜索</button>
	            		<button type="button" id = "btn_clear"  class="btn btn-default houtai-search-control houtai-search-btn" >清空</button>
	            		<button type="button" class="btn btn-default houtai-search-control houtai-search-btn" onclick = "showDialog('', '')">添加</button>
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
							<th>最小限度</th>
							<th>最大限度</th>
							<th style = "text-align:right;">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="item" items="${list}" varStatus="status">
								<tr>		
									<td>${status.count + start}</td>
									<td>${item.moneyFw}</td>
									<td>${item.moneyFw1}</td>			
									<td style = "text-align:right;">
										<a onclick="showDialog('${item.moneyFw}', '${item.moneyFw1}', '${item.degreeId}')" class="btn mini icn-only" >编辑</a>
										<a onclick="toURL('del', '${item.degreeId}')" class="btn mini red icn-only">删除</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${list== null || fn:length(list) == 0}">
						    	 <tr><td colspan="6">没有资料</td></tr>	 
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

<form class="form-horizontal" name  = "dlgForm" id = "dlgForm" method="post" action="${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxszUserDengjiCtrl-saveUserDengji" >
	<div class="modal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick = "$('.modal').hide();"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">添加等级</h4>
	      </div>
	      
	      <div class="modal-body">
	            <div class="box-body">
	              <div class="form-group">
	                <label  class="col-sm-3 control-label">最小限度：</label>
	                <div class="col-sm-9">
	                  <input type="text" class="form-control" name = "moneyFw" id="dlgMoneyFw" placeholder="请输入最大限度!" onKeyUp="value=value.replace(/[^\d.]/g,'')" >
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">最大限度：</label>
	                <div class="col-sm-9">
	                  <input type="text" class="form-control" name = "moneyFw1"  id="dlgMoneyFw1" placeholder="请输入最大限度!" onKeyUp="value=value.replace(/[^\d.]/g,'')">
	                </div>
	              </div>
	            </div>
	            <input type = "hidden" id = "dlgDegreeId" name = "degreeId"/>
	         
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default pull-left" data-dismiss="modal" onclick = "$('.modal').hide();">关闭</button>
	        <button type="button" class="btn btn-primary" onclick = "$('#dlgForm').submit();">添加</button>
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
		
		$("#btn_clear").click(function(){
 			$("input[name='moneyFw']").val("");
 			$("input[name='moneyFw1']").val("");
 		});

		
		// validate检查
		$("#dlgForm").validate({
	    rules: {
	    	moneyFw:{required: true,number:true},
	    	moneyFw1:{required: true,number:true}
	    },
	    
	    messages: {
	    	moneyFw:{required: "请输入最小限度!",number:"输入只数字形式！"},
	    	moneyFw1:{required: "请输入最大限度!",number:"输入只数字形式！"}
	    },
	    errorPlacement: function (error, element) { 
	    	if($(element).closest('div.form-group').children().filter("div.error-div").length < 1) 
			$(element).closest('div.form-group').append("<div class='error-div'></div>");	
		$(element).closest('div.form-group').children().filter("div.error-div").append(error);
	    },
	    submitHandler: function(form){
	    	var moneyFw = $("#dlgMoneyFw").val();
	    	var moneyFw1 = $("#dlgMoneyFw1").val();
	    	if(parseInt(moneyFw) > parseInt(moneyFw1)){
	    		alert("最小限度比最大限度少 ！");
	    		return;
	    	}
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
		var url = "";
		if(action == 'del'){ // 删除功能
			url = "${pageContext.request.contextPath}/houtai/sxsz/HoutaiSxszUserDengjiCtrl-delUserDengji?degreeId="+id;
			if(!confirm("您确定删除吗?")) return;
			$.post(url, {}, function(data){
				if(data.state==1 ){
	            	alert(data.content);
	            	window.location.reload();
	            }else{
	            	alert(data.content);
	            }
			}, "json");
		}
	}
	
	// dialog函数
	function showDialog(moneyFw, moneyFw1, degreeId){
		$("#dlgMoneyFw").val("");
		$("#dlgMoneyFw1").val("");
		$(".modal-title").text("添加等级");
		if(moneyFw != ''){
			$("#dlgMoneyFw").val(moneyFw);
			$("#dlgMoneyFw1").val(moneyFw1);
			$("#dlgDegreeId").val(degreeId);
			$(".modal-title").text("修改等级");
		}
		
		$('.modal').show();
		
	}
</script>
<%@ include file="../footer.jsp"  %>