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
             	<h3 class="box-title">编辑角色</h3>
           	</div>
           	<div class="box-body">
            	<div class="dl-horizontal detail-hz">
               		<dl class="ddOdd">
               			<dt>角色名称 : </dt>
               			<dd><input type="text" name="role_name" id="role_name"  value="${roleInfo.roleName}" style="width:550px;"/></dd>
               		</dl>
               		<dl class="">
               			<dt>顺序 : </dt>
               			<dd><input type="text" name="order_num" id="order_num"  value="${roleInfo.orderNum}" style="width:550px;"/></dd>
               		</dl>
               		<dl  class="ddOdd">
               			<dt>权限 : </dt>
               			<dd>
               				<div id="allpermissions" class="adminPS vertical clearfix">
				                <input type="hidden" id="hdPermissions" name="hdPermissions" />
				                <div>
					                <input type="checkbox" onclick="$('input[name=\'Permissions\']').prop('checked',this.checked)" id="pAll" />
					                <label for="pAll">全选</label>
				                </div>
				                <c:forEach var="item" items="${menuList}" varStatus="status">
				                	<dl>
				                        <dt style="line-height: 30px!important;float: none!important;text-align:left!important;"><b>${item.title}&nbsp;<input class="ModuleAll" codeval="${item.menuId}"  value="${item.menuId}" name="Permissions"  type="checkbox" title="全选" <c:if test="${fn:contains(roleInfo.permission, item.menuId)}">checked</c:if> /></b></dt>
				                        <dd><ul>
					                	<c:forEach var="item1" items="${item.submenu}" varStatus="status">
					                          <li>&nbsp;&nbsp;<input codeval="${item1.menuId}" parent="${item.menuId}" type="checkbox" name="Permissions" value="${item1.menuId}" id="p${item1.menuId}" <c:if test="${fn:contains(roleInfo.permission, item1.menuId)}">checked</c:if> /><label for="p${item1.menuId}" style="margin-bottom:0px;">${item1.title}</label></li>
					                  	</c:forEach>
				                         </ul></dd>
				                   </dl>  
				               </c:forEach>              
				               
				            </div>
           				</dd>
           			</dl>
             	</div>
           </div>
        </div>
	</div>
	<div class="col-xs-12" style="margin-top: 10px;">
		<input type="hidden" id="role_id" value="${roleInfo.roleId}" />
		<button class="btn btn-danger" type="button" onclick="setRole();">确定</button>
     	
	</div>
</div>
</form>

<script type="text/javascript">

	function setRole(){
		var roleName = $("#role_name").val();
		if(roleName == ""){
			alert("请输入角色名称！");
			return;
		}
		var orderNum = $("#order_num").val();
		if(orderNum == ""){
			alert("请输入顺序！");
			return;
		}
		var roleId = $("#role_id").val();
		var vlus = "";
        $('input[name="Permissions"]:checked').each(function () {
            vlus += "," + $(this).val();
        });
        permission = vlus.substring(1);
        
		$.post("${pageContext.request.contextPath}/ht/sys/RoleCtrl-updateRoleInfo",{roleId:roleId,roleName:roleName, orderNum:orderNum,permission:permission},function(data){
			 var ret = $.parseJSON(data); 
			 if(ret.state=="1"){
				alert("保存成功！");
            	var Url = '${pageContext.request.contextPath}/ht/sys/RoleCtrl-roleList';	
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