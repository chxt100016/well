<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="<c:url value="/resources/houtai/js/KindEditor/kindeditor-all.js"/>"></script>
<style>
	.error-div{text-align:left; line-height: 10px;}
</style>
<form method="POST" id="mainData" enctype="multipart/form-data">
<div class="box">
	<div class="col-xs-12" style="padding-left:0px;padding-right:0px;">
		<div class="box box-solid">
        	<div class="box-header with-border">
            	<i class="fa fa-text-width"></i>
             	<h3 class="box-title">编辑公告</h3>
           	</div>
           	<div class="box-body">
            	<div class="dl-horizontal detail-hz">
               		<dl class="ddOdd">
               			<dt>公告名称 : </dt>
               			<dd><input type="text" name="noticeTitle" id="noticeTitle"  value="${noticeInfo.noticeTitle}" style="width:550px;"/></dd>
               		</dl>
               		<dl class="">
               			<dt>公告内容 : </dt>
               			<dd style="padding-top:10px;padding-bottom:10px;"><textarea name="noticeContent">${noticeInfo.noticeContent}</textarea></dd>
               		</dl>
               		<dl class="ddOdd">
               			<dt>公告类型 : </dt>
               			<dd>
               				<select id="noticeType" name="noticeType">
			                    <option <c:if test="${'0' == noticeInfo.state}"> selected="selected" </c:if> value="0">正常</option>
			                    <option <c:if test="${'1' == noticeInfo.state}"> selected="selected" </c:if> value="1">暂停</option>
		                	</select>
           				</dd>
           			</dl>
           			<dl>
               			<dt>状态 : </dt>
               			<dd>
               				<select id="state" name="state">
			                    <option <c:if test="${'0' == noticeInfo.state}"> selected="selected" </c:if> value="0">正常</option>
			                    <option <c:if test="${'1' == noticeInfo.state}"> selected="selected" </c:if> value="1">暂停</option>
		                	</select>
           				</dd>
           			</dl>
             	</div>
           </div>
        </div>
	</div>
	<div class="col-xs-12" style="margin-top: 10px;">
		<input type="hidden" id="noticeId" value="${noticeInfo.noticeId}" />
		<button class="btn btn-danger" type="button" onclick="setNoticeContent();">确定</button>
     	
	</div>
</div>
</form>

<script type="text/javascript">

	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="noticeContent"]', {
			uploadJson : '${pageContext.request.contextPath}/kindeditor/file-upload',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor/file-manager',
			langType : 'zh_CN',
			allowFileManager : true,
			height : '400px',
			width : '90%',
			resizeType : 0,
			items : [
			         'undo', 'redo', '|', 'preview', 'template', 'cut', 'copy', 'paste',
			         'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			         'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			         'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			         'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			         'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			         'table', 'hr', 'emoticons', 'baidumap',
			         'link', 'unlink'
			     ]
		});
	});

	function setNoticeContent(){
		var nTitle = $("#noticeTitle").val();
		if(nTitle == ""){
			alert("请输入公告名称！");
			return;
		}
		var nType = $("#noticeType").val();
		var content = "";
		content = editor.html();
		if(content.trim() == ""){
			alert("请输入公告内容！");
			return;
		}
		var nId = $("#noticeId").val();
		var state = $("#state").val();
		$.post("${pageContext.request.contextPath}/houtai/notice/HoutaiNoticeListCtrl-updateNotice",{noticeId:nId,noticeTitle:nTitle,noticeType:nType, state:state, noticeContent:escape2Html(content)},function(data){
			 var ret = $.parseJSON(data); 
			 if(ret.state=="1"){
				alert("保存成功！");
            	var Url = '${pageContext.request.contextPath}/houtai/notice/HoutaiNoticeListCtrl-getNoticeList';	
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