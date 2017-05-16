<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	</div>
</div>
<script type="text/javascript">
	//初始化函数
	$(function(){
		var leftmenuHeight = $("#leftmenu").height();
		var rectHeight 	   = $("#content-rect").height();
		if(rectHeight>leftmenuHeight) $("#leftmenu").height($("#content-rect").height());
		$('.ui.accordion').accordion();	
			
		//左侧导航栏
	});
</script>
	<c:if test = "${parentMenuNo == '5'}">
				<script type="text/javascript">
						$("#leftmenu").hide();
				</script>
		</c:if>