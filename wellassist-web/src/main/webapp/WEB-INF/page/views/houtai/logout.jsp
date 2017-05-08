<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script language="javascript" type="text/javascript"
	src="<c:url value="/resources/houtai/js/jquery-1.10.1.min.js"/>"></script>	
<script type="text/javascript">
	// 初始化函数
	$(function(){
		top.location.href = "${pageContext.request.contextPath}/back";
	});
	
</script>


</body>
</html>