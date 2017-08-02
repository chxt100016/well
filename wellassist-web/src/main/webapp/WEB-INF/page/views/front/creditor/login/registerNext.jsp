<%@ include file="../../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- custom css -->
<link rel="stylesheet" href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script
	src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>"></script>

<style>
</style>

	<div class="main-wrapper" style="margin-left: 0px;">

		<!-- navigation bar -->
		<div class="abovenavbar">
			<span style="margin-left: 5%;">WellAssist供应链系统</span>
			<span style="float: right; margin-right: 5%;"><a href="${pageContext.request.contextPath}/">返回首页</a></span>
		</div>
		
			
		<!-- form area -->
		<div align=center style="padding-bottom:1px;">
			
			<!-- form page3 -->

			<div id="kehuzhuce_page3">
				<!-- area of progress status -->
				<div style="width: 100%; margin-top: 16px; margin-bottom: 72px;" align = left>
					<span style="float: left; margin-left: 10%;">
						<img src="${pageContext.request.contextPath}/resources/wella/front/images/mainmark.png" style="margin-left: 20%;">
					</span>
					<div style="width: 100%;">
						<div class="label-heading3" style="padding-left: 30%; padding-bottom: 16px;">账号注册</div>
						<div style="padding-left: 30%; width: 40%;">
							<!-- top -->
							<span style="float: left;">
								<div align=center><img src="${pageContext.request.contextPath}/resources/wella/front/images/round1_1.png"></div>
								<div>创建账号</div>
							</span>
							<span style="float: left; width: 30%;">
								<div align=center>
									<img src="${pageContext.request.contextPath}/resources/wella/front/images/breakline_1.png" style="height: 30px; width: 100%;">
								</div>
							</span>
							<span style="float: left;">
								<div align=center>
									<img src="${pageContext.request.contextPath}/resources/wella/front/images/round2_1.png">
								</div>
								<div>资料完善</div>
							</span>
							<span style="float: left; width: 30%;">
								<div align=center>
									<img src="${pageContext.request.contextPath}/resources/wella/front/images/breakline_1.png" style="height: 30px; width: 100%;">
								</div>
							</span>
							<span style="float: left;">
								<div align=center>
									<img src="${pageContext.request.contextPath}/resources/wella/front/images/roundok_1.png">
								</div>
								<div>注册车成功</div>
							</span>
						</div>
					</div>
				</div>

				<div align=center style="margin-top: 70px; margin-bottom: 48px; width: 800px; height: 360px; background: white; box-shadow: 0 -1px 6px rgba(0, 0, 0, 0.5);">
					<div style="padding-top:82px;">
						<img src="${pageContext.request.contextPath}/resources/wella/front/images/zhuceok_icon.png">
						<span style="font-size:32px;font-weight:bold;padding-left:16px;">注册完成</span>
					</div>
					<div style="color:#2681c6;font-size:16px;font-weight: bold;padding-top: 50px;"><a href="${pageContext.request.contextPath}/">返回首页</a></div>

				</div>
				
				<div align=center style="padding-bottom:48px;">版权信息</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	</script>
</body>