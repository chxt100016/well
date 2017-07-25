<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
					
<!-- pagenation -->
<c:if test="${pageCount>1}">
	<ul class="ui pagination menu">
		<c:if test = "${page == 1}">
			<li>
			     <a href="#" aria-label="Previous" class="icon item">
					 <!-- <span aria-hidden="true">&laquo;</span> -->
						  <i class="left chevron icon"></i>
				 </a>
			</li>
		</c:if>
		<c:if test = "${page > 1}">
			<li>
			     <a onclick="searchData('${page-1}')" aria-label="Previous" style="cursor:pointer;" class="icon item">
					 <!-- <span aria-hidden="true">&laquo;</span> -->
						  <i class="left chevron icon"></i>
				 </a>
			</li>	
		</c:if>
		<c:set var="initGap" value="1"/>  
		<c:forEach begin="1" end="${pageCount}" var="i">
			<c:choose> 
				<c:when test="${page==i}">
					<li><a href="#" class="active item">${i}</a></li>
					<c:set var="initGap" value="1"/> 
				</c:when>
				<c:when test="${gap+1 > i || i + gap > pageCount || (  ( i >= page - gap ) && (i <= page + gap))}">
					<li><a onclick="searchData('${i}');" style="cursor:pointer;" class="item">${i}</a></li>
					<c:set var="initGap" value="1"/> 
				</c:when>
				<c:otherwise>
					<c:if test = "${initGap ==1}">
						<li><span class="disabled item">...</span></li>
						<c:set var="initGap" value="0"/> 
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test = "${page == pageCount}">
			<li>
                  <a href="#" aria-label="Next" class="icon item">
					  <!-- <span aria-hidden="true">&raquo;</span> -->
					  <i class="right chevron icon"></i>
                  </a>
              </li>
		</c:if>
		<c:if test = "${page != pageCount}">
			  <li>
                  <a onclick="searchData('${page + 1}')" aria-label="Next" style="cursor:pointer;" class="icon item">
					  <!-- <span aria-hidden="true">&raquo;</span> -->
					  <i class="right chevron icon"></i>
                  </a>
              </li>
		</c:if>
	</ul>
</c:if>
<%-- <script src="<c:url value="/resources/basic/js/global.js"/>"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/sys/js/common.js"/>"></script> --%>
<script type = "text/javascript">

	//搜索函数
	function searchData(page) {
		$("#page").val(page);
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
		$("#searchFrm").submit();
	}
</script>
<!-- pagenation -->