<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>">
</script>

<style>
	div.newsitem{
		font-size:14px;
		border-bottom:solid 1px #ccc;
		padding-top: 12px;
		padding-bottom: 8px;
		display:inline-block;
		width:100%;
	}
	div.newsincontent{
		margin-left:8%;
		width:92%;
		display: inline-block;
		padding-top: 12px;
		padding-bottom: 8px;
		color:#777;
	}
</style>

</head>
<div id= "content-rect">
	<div style="border:solid 1px #d0d0d0;padding:6px;font-size:24px;color:#0000d0;">公告列表</div>

	<!-- top nav bar -->
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:32px;">
		<div style="float:left;margin-left:5%;">
			<input type="button" value="标记所选为已读">
			<input type="button" value="删除所选">
			<input type="button" value="清空所有">
		</div>
		<div style="float:right;margin-top:12px;">未读1/全部5</div>
	</div>



	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:16px;">
		<div style="width:26%;text-align:center;float:left;">时间</div>
		<div style="width:17%;text-align:center;float:left;">额度</div>
		<div style="width:40%;text-align:center;float:left;">资料</div>
		<div style="width:17%;text-align:center;float:left;">状态</div>
	</div>

	<!--  -->
	<div class="newsitem">
		<div style="float:left;width:4%;"><input id="1" type="checkbox"></div>
		<div style="float:left;width:4%;margin-top:3px;"><img src="<c:url value="/resources/wella/front/images/news_unread.png"/>"></div>
		<div style="float:left;width:22%;margin-top:6px;">LNG罐车特价</div>
		<div style="float:right;margin-top:6px;">2015-04-29 12:11:09</div>
	</div>
	<!--  -->
	<div class="newsitem">
		<div style="float:left;width:4%;"><input id="1" type="checkbox"></div>
		<div style="float:left;width:4%;"><img src="<c:url value="/resources/wella/front/images/news_read.png"/>"></div>
		<div style="float:left;width:22%;">LNG罐车特价</div>
		<div style="float:right;">2015-04-29 12:11:09</div>
	</div>
	<!--  -->
	<div class="newsitem">
		<div>
			<div style="float:left;width:4%;"><input id="1" type="checkbox"></div>
			<div style="float:left;width:4%;"><img src="<c:url value="/resources/wella/front/images/news_read.png"/>"></div>
			<div style="float:left;width:22%;">LNG现货价格走势</div>
			<div style="float:right;">2015-04-29 12:11:09</div>
		</div>
		<div class="newsincontent">
			<div>今日LNG现货均价4500元/吨</div>
			<div>想要了解更多请访问<a href="#">www.123456.cn</a></div>
		</div>
	</div>
	
	<!-- bottom nav bar -->
	<div style="border-bottom:solid 1px #d0d0d0;padding:6px;font-size:14px;margin-top:16px;height:32px;">
		<div style="float:left;margin-left:5%;">
			<input type="button" value="标记所选为已读">
			<input type="button" value="删除所选">
			<input type="button" value="清空所有">
		</div>
		<div style="float:right;margin-top:12px;">未读1/全部5</div>
	</div>
	
			
 	<div style="padding-top:12px;height:24px;">
		<input type="submit" class="bluebutton" style="float:right;" value="确定">
		<div style="float:right;font-size:14px;padding-top:8px;">&nbsp;页&nbsp;&nbsp;</div>
		<input type="text" name="pageno" style="float:right;width:64px;">
		<div style="float:right;font-size:14px;padding-top:8px;">共01页 至第</div>
		<div class="graywhitebutton" style="float:right;margin-right:32px;width:24px;">&gt;</div>
		<div class="grayrectbutton" style="float:right;width:24px;backgound:#e0e0e0;">1</div>
		<div class="graywhitebutton" style="float:right;width:24px;">&lt;</div>
	</div>
</div>
<%@ include file="../footer.jsp"%>