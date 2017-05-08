<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ include file="../../header.jsp" %>

<div class="row-fluid" id="rt-list-area">
	<div class="span12">
		<div class="alert alert-warning">
			<i class="icon-search"></i>
			<input type="text" class="m-wrap medium" style="margin-bottom:0px;" id="searchKey" value="" placeholder="">
			<button type="button" class="btn blue" onclick="onSearch()">搜索</button>
			<button type="button" class="btn" onclick="onKeyClear()">清空</button>
			
			<a href="javascript:onGetDetailData(0);" class="btn blue fr hide"><i class="icon-plus"></i> æ·»å </a>
		</div> 
		<div class="tabbable tabbable-custom boxless">
			<ul class="nav nav-tabs">
				<li class="active"><a href="javascript:void(0)" data-toggle="tab" tabId="2">未审核</a></li>
				<li class=""><a href="javascript:void(0)" data-toggle="tab" tabId="3">审核中(供货方)</a></li>
				<li class=""><a href="javascript:void(0)" data-toggle="tab" tabId="5">不通过	</a></li>
			</ul>
			
			<div id="table_content"></div>
			<div id="table_pagination" class="table_pagination"></div>
		</div>
	</div>
</div>

<input type=hidden id="sortFld"  value="id">
<input type=hidden id="sortDesc" value="desc">
<input type=hidden id="entType"  value="2">
<input type=hidden id="userType"  value="${userType}">

<div class="row-fluid" id="rt-detail-area" style="display:none;"></div>

<script type="text/javascript">

	jQuery(document).ready(function() {   
		onSearch();
	});

	$(".nav-tabs a").click(function(){
		var tabId = $(this).attr("tabId");
		$("#entType").val(tabId);		
		onSearch();
	});

	//--- åè¡¨åé¡µ ---//   	
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
	
	//--- åè¡¨æç´¢ ---//
    function onSearch(){ 
        var searchKey	= $("#searchKey").val();
		var sortFld		= $("#sortFld").val();
		var sortDesc	= $("#sortDesc").val();
        var option      = $('#table_pagination').pagination("options");
        var searchType 	= $("#entType").val();
        var userType = $("#userType").val();       
		 $.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-tablePageSwitch",
            {	subIdx		: 0,
				pageNumber	: option.pageNumber,
				pageSize	: option.pageSize,
				searchKey	: searchKey,
				searchType	: searchType,
				sortFld		: sortFld,
				sortDesc	: sortDesc,
				userType	: userType
			}, function(data) {					
            $("#table_content").html(data);
        });
    }
	
	//--- æ¾ç¤ºè¯¦æä¿¡æ¯ ---//
    function onGetDetailData(idx){
		$.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-getDetailData",{subIdx: 0,selIdx: idx}, function(data) {
            $("#rt-list-area").hide();
			$("#rt-detail-area").show();
			$("#rt-detail-area").html(data);
        });
    }
	
	//--- æ¾ç¤ºè¯¦æä¿¡æ¯ ---//
    function onDeleteData(idx){
		showConfirm("确认要删除该用户吗？", 
			function(){
				$.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-deleteData",{subIdx: 0,selIdx: idx}, function(data) {
					onSearch();
				});
			}
		);
    }
	
	//--- è®¾ç½®å»ç»ï¼è§£å» ---//
    function onSetLockState(idx,state){
		$.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-onSetLockState",{subIdx: 0,selIdx: idx,state:state}, function(data) {
            onSearch();
        });
    }
    function onSetStatus(idx,state){
    	
// 		state = state - 1;
// 		if(state < 0 || state > 1) state = 0;
		
		$.post("${pageContext.request.contextPath}/ht/Common/showDialog",{dlgType : 2}, function(data) {
						
            $("#dialog-area").html(data);
			openStatusDialog("审核",state,"",
				function(state,content,gonghuofang){
					$.post("${pageContext.request.contextPath}/ht/Enterprise/UserCtrl-setGonghuofang",{subIdx: 0,selIdx: idx,state: state,reason: content, gonghuofang : gonghuofang}, function(data) {
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
<%@ include file="../../footer.jsp" %>