<%@ include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/houtai/css/jquery.fancybox.css" />" />
<script type="text/javascript" src="<c:url value="/resources/houtai/js/jquery.fancybox.pack.js" />" /></script>   
<script type="text/javascript" src="<c:url value="/resources/houtai/js/gallery.js" />" /></script> 

<div class="row-fluid" id="rt-list-area">
	<div class="span12">
		<div class="alert alert-warning">
			<i class="icon-search"></i>
			<input type="text" class="m-wrap medium" style="margin-bottom:0px;" id="searchKey" value="" placeholder="">
			<button type="button" class="btn blue" onclick="onSearch()">搜索</button>
			<button type="button" class="btn" onclick="onKeyClear()">清空</button>
			
			<a href="javascript:onShowCarType();" class="btn blue fr hide"><i class="icon-plus"></i> 添加</a>
		</div> 
		
		<div id="table_content"></div>
		<div id="table_pagination" class="table_pagination"></div>
	</div>
</div>

<input type=hidden id="sortFld"  value="repairEntId">
<input type=hidden id="sortDesc" value="desc">

<div class="row-fluid" id="rt-detail-area" style="display:none;"></div>

<script type="text/javascript">

	//--- 列表分页 ---//   	
    $('#table_pagination').pagination({ 
        total       : 1, 
        pageSize    : 10,
        pageNumber  : 1,
        pageList	: [5, 10, 15, 20, 25],
		layout      : ['list','first','prev','links','next','last'],
        onSelectPage:function(pageNum, pageSz){
            $(this).pagination('loading');
            onSearch();
            $(this).pagination('loaded');
        }  
    });
	
	//--- 列表搜索 ---//
    function onSearch(){ 
        var searchKey	= $("#searchKey").val();
		var sortFld		= $("#sortFld").val();
		var sortDesc	= $("#sortDesc").val();
        var option      = $('#table_pagination').pagination("options");
        
        $.post("{$adminPath}Company/tablePageSwitch",
            {	subIdx		: 0,
				pageNumber	: option.pageNumber,
				pageSize	: option.pageSize,
				searchKey	: searchKey,
				sortFld		: sortFld,
				sortDesc	: sortDesc
			}, function(data) {
            $("#table_content").html(data);
        });
    }
	
	//--- 显示详情信息 ---//
    function onGetDetailData(idx){
		$.post("{$adminPath}Company/getDetailData",{subIdx: 0,selIdx: idx}, function(data) {
            $("#rt-list-area").hide();
			$("#rt-detail-area").show();
			$("#rt-detail-area").html(data);
        });
    }
	
	//--- 删掉详情信息 ---//
    function onDeleteData(cid,uid){
		showConfirm("确认要删除该用户吗？", 
			function(){
				$.post("{$adminPath}Company/deleteData",{subIdx: 0,cid: cid,uid: uid}, function(data) {
					if(data == "error_user"){
						showAlert("当用户不存在","warn");
					}
					else{
						onSearch();
					}
				});
			}
		);
    }
	
	//--- 设置冻结，解冻 ---//
    function onSetLockState(idx,state){
		$.post("{$adminPath}Member/setLockState",{subIdx: 0,selIdx: idx,state:state}, function(data) {
            onSearch();
        });
    }
	
	function onSetStatus(idx,state){
		state = state - 1;
		if(state<0 || state>1) state = 0;
		
		$.post("{$adminPath}Common/showDialog",{dlgType : 1}, function(data) {
            $("#dialog-area").html(data);
			openStatusDialog("审核",state,"",
				function(state,content){
					$.post("{$adminPath}Company/setStatus",{subIdx: 0,selIdx: idx,state: state,reason: content}, function(data) {
						onSearch();
					});
				}
			);
        });
	}
	
	function onKeyClear(){
		$("#searchKey").val("");
		onSearch();
	}
</script>
<%@ include file="../footer.jsp"  %>