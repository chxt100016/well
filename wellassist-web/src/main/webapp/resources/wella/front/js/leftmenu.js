/**
 * 
 */

	function menuparentclicked( obj){
		var nextobj = $(obj).nextAll("ul:first");
		if( $(nextobj).css("display")!="none"){
			$(nextobj).slideUp();
			$(obj).css("list-style-image", "url('../images/lftmnuitmcollapsed.png')");
		} else {
			$(nextobj).slideDown();
			$(obj).css("list-style-image", "url('../images/lftmnuitmexpanded.png')");
		}
	}
	
	function menuclicked( url, obj){
		$("li.leftmenuleaf").removeClass("selected");
		$(obj).addClass("selected");
		$("#contentFrame").attr( "src", "${pageContext.request.contextPath}"+url);
	}
