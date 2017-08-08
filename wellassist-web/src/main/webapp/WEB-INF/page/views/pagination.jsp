<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- pagenation -->
<c:if test="${pageCount>1}">
	<div class="pagination page" style="text-align:center;padding-top:15px;">
		<c:if test="${page == 1}">
			<span class="previous_page disabled" style="border-left-width: 1px;">上一页</span>
		</c:if>
		<c:if test="${page > 1}">
			<a class="previous_page" style="border-left-width: 1px;"
				onclick="searchData('${page-1}')">上一页</a>

		</c:if>
		<c:set var="initGap" value="1" />
		<c:forEach begin="1" end="${pageCount}" var="i">
			<c:choose>
				<c:when test="${page==i}">
					<span class="active">${i}</span>
					<c:set var="initGap" value="1" />
				</c:when>
				<c:when
					test="${gap+1 > i || i + gap > pageCount || (  ( i >= page - gap ) && (i <= page + gap))}">
					<a onclick="searchData('${i}');">${i}</a>
					<c:set var="initGap" value="1" />
				</c:when>
				<c:otherwise>
					<c:if test="${initGap ==1}">
						<span class='gap'>...</span>
						<c:set var="initGap" value="0" />
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${page == pageCount}">
			<span class='next_page disabled'>下一页</span>
		</c:if>
		<c:if test="${page != pageCount}">
			<a class="next_page" style="background-position: 65px 50%;"
				onclick="searchData('${page + 1}')">下一页</a>
		</c:if>
	</div>
</c:if>
<script type="text/javascript">
	//搜索函数
	function searchData(page) {
		$("#page").val(page);
		var url = $("#searchFrm").attr("action")+"?"+$("#searchFrm").serialize();
		setPageUrl(url);
		$("#searchFrm").submit();
	}
</script>
<!-- pagenation -->