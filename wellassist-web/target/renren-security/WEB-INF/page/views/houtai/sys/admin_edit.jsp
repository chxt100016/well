<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error-div{text-align:left; line-height: 10px;}
	ul,ol{list-style-type:none;}
	.clearit{clear:both;}
	.clearfix:before, .clearfix:after{content: "\0020";display: block;height: 0;overflow: hidden;}
	.clearfix:after{clear: both;}
	.clearfix{zoom: 1;}
	.adminPS ul,.adminPS li,.adminPS dl,.adminPS dt,.adminPS dd{margin: 0;padding: 0;}
	.adminPS b{color: #000;display: block;font-weight: bold;font-size: 12px;font-family: '微软雅黑';}
	.adminPS dl{border: 1px solid #B9C8E7;background: #EEF5FF;border-radius: 5px;display: block;float: left;margin: 10px 10px 0 0;padding: 8px 12px;}
	.adminPS dl:hover{border: 1px solid #F60;background: #FFE0CC;}
	.adminPS dt{margin-bottom: 3px;}
	.adminPS dl:hover dt b{color: #C00;}
	.adminPS li{float: left;white-space:nowrap;}
	 
		
</style>
<form method="POST" id="mainData" enctype="multipart/form-data">
<div class="box">
	<div class="col-xs-12" style="padding-left:0px;padding-right:0px;">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">编辑管理员</h3>
           	</div>
           	<div class="box-body">
            	<div class="dl-horizontal detail-hz">
               		<dl class="ddOdd">
               			<dt>名称 : </dt>
               			<dd><input type="text" name="adminName" id="adminName"  value="${adminInfo.adminName}" style="width:550px;"/></dd>
               		</dl>
               		<dl class="">
               			<dt>手机号码 : </dt>
               			<dd><input type="text" name="adminPhone" id="adminPhone"  value="${adminInfo.adminPhone}" style="width:550px;"/></dd>
               		</dl>
               		<dl  class="ddOdd">
               			<dt>权限 : </dt>
               			<dd>
               				<select id="roleId" name="roleId">
               				<c:forEach var="item" items="${roleList}" varStatus="status">
		                          <option value="${item.roleId}" <c:if test="${item.roleId==adminInfo.role}">selected</c:if> >${item.roleName}</option>
		                  	</c:forEach>
		                  	</select>
           				</dd>
           			</dl>
             	</div>
           </div>
        </div>
	</div>
	<div class="col-xs-12" style="margin-top: 10px;">
		<input type="hidden" id="adminId" value="${adminInfo.id}" />
		<button class="btn btn-danger" type="button" onclick="setAdmin();">确定</button>
     	
	</div>
</div>
</form>

<script type="text/javascript">

	function setAdmin(){
		var adminName = $("#adminName").val();
		if(adminName == ""){
			alert("请输入角色名称！");
			return;
		}
		var adminPhone = $("#adminPhone").val();
		
		var adminId = $("#adminId").val();
		var roleId = $("#roleId").val();
		$.post("${pageContext.request.contextPath}/ht/sys/AdminCtrl-updateAdminInfo",{adminId:adminId,adminName:adminName, adminPhone:adminPhone,roleId:roleId},function(data){
			 var ret = $.parseJSON(data); 
			 if(ret.state=="1"){
				alert("保存成功！");
            	var Url = '${pageContext.request.contextPath}/ht/sys/AdminCtrl-adminList';	
            	location = Url;
	    	 } else {
	    		 alert(ret.msg);
         	 }
		})
		.error(function(data){
			alert("操作失败！");
		})
	}
	
	function escape2Html(str) {
	    var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
	    return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
	}
	
	$(".ModuleAll").click(function () {
        $(this).parent().parent().next().find('input[type="checkbox"]').prop('checked', this.checked);
    });
	
	$(function(){
 		$('#btn_cancle').click(function() {
 			history.back();
		});
 		
 		$("input[type='checkbox']").click(function(){
			 var pId=this.id;
			 var parent = $(this).attr("codeval");
			 var cur_parent = $(this).attr("parent");
			 if(cur_parent>0)
	       		$("input[codeval='"+cur_parent+"']").prop("checked",true);
			 else
				$("input[parent='"+parent+"']").prop("checked",this.checked);
				 
	 	});
 		
 	});
</script>

<%@ include file="../footer.jsp"  %>