<%@ include file="../header.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*,org.wella.common.utils.CommonUtil" %>
<%
	int gap = 5;
	int pageSize = Integer.parseInt(request.getAttribute("pageSize").toString());
	int curentPage = Integer.parseInt(request.getAttribute("page").toString());
	int totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
	int totalPage = (int)Math.floor((totalCount + pageSize - 1) / pageSize) - 1;
%>	
	
<div class="box" >
	<div class="col-xs-12">
		<div class = "row">
			<div class="box-header">
                  <h3 class="box-title">菜单列表</h3>
            </div>
          </div>
      	
		<div class = "row">
			<div class = "col-sm-12">
				<table id="example2" class="table table-bordered table-striped table-hover">
	                    <thead>
	                      <tr>
							<th width ="10%">ID </th>
							<th style = "">标题</th>
							<th style = "">链接</th>
							<th style = "width:60px;">排序</th>
							<th style = "">显示</th>
							<th style = "">操作</th>
						  </tr>
	                    </thead>
	                    <tbody>
	                      <c:forEach var="vo" items="${topMenuList}" varStatus="status">
								<tr>		
									<td>
										<c:if test="${ empty vo.menuId}">无</c:if>
										<c:if test="${ !empty vo.menuId}">${vo.menuId}</c:if>
									</td>
									<td>
										<c:if test="${ empty vo.title}">无</c:if>
										<c:if test="${ !empty vo.title}">${vo.title}</c:if>
									</td>			
									<td>
										<c:if test="${ empty vo.url}">无</c:if>
										<c:if test="${ !empty vo.url}">${vo.url}</c:if>
									</td>
									<td>${vo.orderNum}</td>	
									<td>
										<c:if test="${vo.isShow==0}"><span class="badge">&nbsp;否&nbsp;</span></c:if>
										<c:if test="${vo.isShow==1}"><span class="badge badge-info">&nbsp;是&nbsp;</span></c:if>
									</td>
									<td>
										<c:if test="${vo.parentId ==0}"> 
											<a onclick="viewSubMenu('${vo.menuId}')" class="btn mini icn-only" >下级</a>
										</c:if>
										<a onclick="editMenu('${vo.menuId}')" class="btn mini icn-only" >编辑</a>
										<a onclick="delMenu('${vo.menuId}')" class="btn mini red icn-only">删除</a>
									</td>
								</tr>
							</c:forEach>
	                    </tbody>
	                  </table>
	              </div>
		</div>
		<div class="page-area">
		<%
			out.println(CommonUtil.DispPagination(totalPage, curentPage, gap, "searchData", "上一页", "下一页"));
		%>
		</div>
		<div class="controls" style="margin-left:5px;padding-top:20px;padding-bottom:20px;">
			<button type="button" class="btn btn-primary" style="margin-left:30px;" onclick="addMenu();">添加</button>
		</div>
	</div>
</div>

<form id="searchFrm" method="post" action="${pageContext.request.contextPath}/ht/sys/MenuCtrl-menuList">
	<input type="hidden" id="page" name="page" value="1" />
	<input type="hidden" id="act" name="act" value="" />
	<input type="hidden" id="parent_id" name="parent_id" value="${parentId}">
</form>

<input type=hidden id="sortFld"  value="sortNum">
<input type=hidden id="sortDesc" value="asc">

<form class="form-horizontal" name  = "dlgForm" id = "dlgForm" method="post" action="${pageContext.request.contextPath}/ht/sys/MenuCtrl-updateMenuInfo" >
	<div class="modal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick = "$('.modal').hide();"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">编辑菜单</h4>
	      </div>
	      
	      <div class="modal-body">
	            <div class="box-body">
	              <div class="form-group">
	                <label  class="col-sm-3 control-label">标题：</label>
	                <div class="col-sm-9">
	                  <input type="text" class="form-control" name = "title" id="title" placeholder="请输入标题!">
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">链接地址：</label>
	                <div class="col-sm-9">
	                  <input type="text" class="form-control" name = "url"  id="url" placeholder="请输入链接地址!">
	                </div>
	              </div>
	              <div class="form-group">
	                <label class="col-sm-3 control-label">顺序：</label>
	                <div class="col-sm-9">
	                  <input type="text" class="form-control" name = "orderNum"  id="order_num" placeholder="请输入顺序!" onKeyUp="value=value.replace(/[^\d.]/g,'')">
	                </div>
	              </div>
	            </div>
	            <input type = "hidden" id = "menu_id" name = "menuId"/>
	            <input type = "hidden" id = "parent_id" name = "parentId" value="${parent_id}"/>
	         
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default pull-left" data-dismiss="modal" onclick = "$('.modal').hide();">关闭</button>
	        <button type="button" class="btn btn-primary" onclick = "$('#dlgForm').submit();">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
</form>
<div class="row-fluid" id="rt-detail-area" style="display:none;"></div>

<script type="text/javascript">

	$(function(){
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
		
		// validate检查
		$("#dlgForm").validate({
	    rules: {
	    	title:{required: true},
	    	order_num:{required: true,number:true}
	    },
	    messages: {
	    	title:{required: "请输入标题!"},
	    	order_num:{required: "请输入顺序!",number:"输入只数字形式！"}
	    },
	    errorPlacement: function (error, element) { 
	    	if($(element).closest('div.form-group').children().filter("div.error-div").length < 1) 
			$(element).closest('div.form-group').append("<div class='error-div'></div>");	
		$(element).closest('div.form-group').children().filter("div.error-div").append(error);
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
	
	//查看下级的栏目
 	function viewSubMenu(id){
 		var Url = '?act=submenu&parent_id='+id;
 		location = Url;
 	}
	
	function addMenu(){
		$(".modal-title").text("编辑菜单");
		$('.modal').show();
	}
	function searchData(page) {
 		$("#page").val(page);
		$("#searchFrm").submit();
	}
	
    function editMenu(menuId){
    	$.post("${pageContext.request.contextPath}/ht/sys/MenuCtrl-getMenuInfo",{menuId: menuId}, function(data) {
    		if(data.result && data.result.menu_id>0){
    			$("#menu_id").val(data.result.menu_id);
    			$("#title").val(data.result.title);
    			$("#url").val(data.result.url);
    			$("#order_num").val(data.result.order_num);
    			$("#parent_id").val(data.result.parent_id);
    			$(".modal-title").text("编辑菜单");
    			$('.modal').show();
    		}
        }, "json");
		
		
	}
    
    function delMenu(menuId){
    	if(confirm("你确定删除吗？")){
	    	$.post("${pageContext.request.contextPath}/ht/sys/MenuCtrl-delMenu",{menuId: menuId}, function(data) {
	    		if(data.result==1){
	    			alert("删除成功！");
	    			searchData(0);
	    		}
	        }, "json");
    	}
	}
    
</script>
<%@ include file="../footer.jsp"  %>