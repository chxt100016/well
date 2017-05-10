function ajaxMyFrom(formid){
	var myForm = $("#"+formid);//$('input[type=submit]').closest("form"); // $('input[name="submitButton"]').closest("form");
	var callUrl = $(myForm).attr("action");
	if(callUrl!=null && callUrl!=""){
		$.post(callUrl, $(myForm).serialize(), function(data) {
	        alert(data.info);
            if(data.status==1){
                 window.location.reload();
            }
	    });
	}else{
		alert("找不到服务器地址!");
	}
}

function ajaxCallUrl(callUrl){
	if(callUrl!=null && callUrl!=""){
		$.post(callUrl,{}, function(data) {
	        alert(data.info);
            if(data.status==1){
                 window.location.reload();
            }
	    });
	}else{
		alert("找不到服务器地址!");
	}
}