<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error-div{text-align:left; line-height: 10px;}
</style>
<form method="POST" id="mainData" enctype="multipart/form-data">
<div class="box">
	<div class="col-xs-12" style="padding-left:0px;padding-right:0px;">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">编辑提醒</h3>
           	</div>
           	<div class="box-body">
            	<div class="dl-horizontal detail-hz">
               		<dl class="ddOdd">
               			<dt>提醒名称 : </dt>
               			<dd><input type="text" name="tx_name" id="tx_name"  value="${notifyInfo.txName}" style="width:550px;"/></dd>
               		</dl>
               		<dl class="">
               			<dt>提醒内容 : </dt>
               			<dd style="padding-top:10px;padding-bottom:10px;"><textarea name="tx_content" id="tx_content" style="line-height:20px!important;width:600px;height:250px;">${notifyInfo.txContent}</textarea></dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>提醒类型 : </dt>
               			<dd>
               				<select id="tx_type" name="tx_type">
			                    <option <c:if test="${'0' == notifyInfo.txType}"> selected="selected" </c:if> value="0">正常</option>
			                    <option <c:if test="${'1' == notifyInfo.txType}"> selected="selected" </c:if> value="1">暂停</option>
		                	</select>
           				</dd>
           			</dl>
           			<dl>
               			<dt>状态 : </dt>
               			<dd>
               				<select id="state" name="state">
			                    <option <c:if test="${'0' == notifyInfo.state}"> selected="selected" </c:if> value="0">正常</option>
			                    <option <c:if test="${'1' == notifyInfo.state}"> selected="selected" </c:if> value="1">暂停</option>
		                	</select>
           				</dd>
           			</dl>
             	</div>
           </div>
        </div>
	</div>
	<div class="col-xs-12" style="margin-top: 10px;">
		<input type="hidden" id="tx_id" value="${notifyInfo.txId}" />
		<button class="btn btn-danger" type="button" onclick="setNotifyContent();">确定</button>
     	
	</div>
</div>
</form>

<script type="text/javascript">

	function setNotifyContent(){
		var txName = $("#tx_name").val();
		if(txName == ""){
			alert("请输入公告名称！");
			return;
		}
		var txType = $("#tx_type").val();
		var txContent = $("#tx_content").val();
		if(txContent.trim() == ""){
			alert("请输入公告内容！");
			return;
		}
		var txId = $("#tx_id").val();
		var state = $("#state").val();
		$.post("${pageContext.request.contextPath}/houtai/notify/HoutaiNotifyListCtrl-updateNotify",{txId:txId,txName:txName,txType:txType, state:state, txContent:txContent},function(data){
			 var ret = $.parseJSON(data); 
			 if(ret.state=="1"){
				alert("保存成功！");
            	var Url = '${pageContext.request.contextPath}/houtai/notify/HoutaiNotifyListCtrl-getNotifyList';	
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
	
 	$(function(){
 		
 		$('#btn_cancle').click(function() {
 			history.back();
		});
 	});
</script>

<%@ include file="../footer.jsp"  %>