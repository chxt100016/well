var CenterTimer, AW1, AW2;
function ShowWindowAlert(title, msgcontent, btn_ok, btn_cancel, callback) {
	AW1 = 'messWindow';
	AW2 = 'promptWindow';

	// if(btn_cancel=="") btn_cancel = "取消";

	var content = '				<div id="messWindow" class="messWindow">';
	content += ' 			</div>';
	content += ' 			<div id="promptWindow" class="promptWindow" align="center" style="">';
	content += ' 			    <div class="modal modal-dialog clearfix" id="" style="">';
	content += ' 				  <div class="modal-dialog-message" style="width:100%!important;">';
	content += ' 				    <h3>' + title + '</h3>';
	content += ' 				    <p>' + msgcontent + '</p>';
	content += ' 			    	<div class="modal-controls">';
	if (btn_ok != "") {
		content += ' 			      	<button class="btn btn-minor submit" style="margin-right:20px;">';
		content += ' 			        	' + btn_ok;
		content += ' 			      	</button>';
	}
	if (btn_cancel != "") {
		content += ' 			      	<button class="btn btn-minor shutdown" >';
		content += ' 			        	' + btn_cancel;
		content += ' 			     	 </button>';
	}
	content += ' 			    </div>';
	content += ' 			  </div>';
	content += ' 			</div>';
	content += ' 		</div>';
	$("body").append(content);
	$('#' + AW2).css({
		'position' : 'absolute',
		'z-index' : '100000'
	});
	$('#' + AW1).css("opacity", 0.8);
	$('#' + AW1)
			.width(
					document.body.scrollWidth > document.documentElement.clientWidth ? document.body.scrollWidth
							: document.documentElement.clientWidth);
	$('#' + AW1)
			.height(
					document.body.scrollHeight > document.documentElement.clientHeight ? document.body.scrollHeight
							: document.documentElement.clientHeight);
	$('#' + AW1).fadeIn();
	$('#' + AW2).fadeIn();
	DivCenter();
	// dialog btn2按键点击事件
	$(".shutdown").click(function() {
		HideWindow();
	});

	// dialog btn1按键点击事件
	$(".submit").click(function() {
		callback();
		HideWindow();
	});

}

function HideWindow() {
	$("#" + AW1).fadeOut("slow");
	$("#" + AW2).fadeOut("slow");
	clearTimeout(CenterTimer);
	$("#" + AW1).remove();
	$("#" + AW2).remove();
}




function ShowWindowNew(W1, W2, a, b) {
	AW1 = W1; AW2 = W2; 
	if (!AW1) AW1 = 'messWindow_new'; 
	if (!AW2) AW2 = 'promptWindow_new'; 
	if (!b) b = '#333333'; 
	if ($('#' + AW1).length == 0) 
		$('<div id="' + AW1 + '" style="position:absolute;z-index:99999;display:none;background-color:' + b + ';top:0;left:0"></div>').appendTo("body");
	if ($('#' + AW2).length == 0) { 
		$('<IFRAME id="' + AW2 + '" name="' + AW2 + '" style="position:absolute;z-index:100000;" src="" frameborder="0" scrolling="no" allowTransparency="true" ></IFRAME>').appendTo("body") 
	}
	var d = document, e = d.documentElement; 
	b = d.body; $('#' + AW2).css({ 'position': 'absolute', 'z-index': '100000' }); 
	$('#' + AW1).css("opacity", 0.8).width(b.scrollWidth > e.clientWidth ? b.scrollWidth : e.clientWidth).height(b.scrollHeight > e.clientHeight ? b.scrollHeight : e.clientHeight).fadeIn("slow"); 
	if (a) { $("#" + AW2).load(function () { IFrameReSize(AW2) }).attr('src', a) } $('#' + AW2).fadeIn("slow");
		DivCenter() 
}

function HideWindowNew() {
	AW1 = 'messWindow_new'; 
	AW2 = 'promptWindow_new';
	$("#" + AW1).fadeOut("slow");
	$("#" + AW2).fadeOut("slow");
	clearTimeout(CenterTimer);
}






function DivCenter() {
	var scrollY = 0;
	if (document.documentElement && document.documentElement.scrollTop) {
		scrollY = document.documentElement.scrollTop;
	} else if (document.body && document.body.scrollTop) {
		scrollY = document.body.scrollTop;
	} else if (window.pageYOffset) {
		scrollY = window.pageYOffset;
	} else if (window.scrollY) {
		scrollY = window.scrollY;
	}
	var Div = document.getElementById(AW2);
	var Dh = Div.offsetHeight;
	if (Dh > $('#' + AW1).height())
		$('#' + AW1).height(Div.offsetHeight + $('#' + AW2).offset().top * 2);
	if (document.documentElement.clientHeight > Dh) {
		Div.style.top = (document.documentElement.clientHeight - Div.offsetHeight)
				/ 2 + scrollY + 'px';
	}
	Div.style.left = (document.body.clientWidth - Div.clientWidth) / 2
			+ document.body.scrollLeft + 'px';
	CenterTimer = setTimeout("DivCenter()", 500);
}
function AlertLoginReturn(S) {
	if (S == 1)
		location.reload();
}
