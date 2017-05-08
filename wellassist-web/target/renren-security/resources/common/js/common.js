function goBack(){
	location.href = getPageUrl();
}

function getPageUrl(){
	var url = readCookie("pageUrl");
	if(url==null) url="";
	return url;
}

function setPageUrl(url){
	createCookie("pageUrl", url);
}

function replaceAll(str,s1,s2){
	if(str!=null)
	str = str.replace(new RegExp(s1,"gm"),s2);
	return str;
}

function removeStrInArrStr(strArr, str){
	var arr = strArr.split(",");
	var retStr = "";
	for(i = 0 ; i < arr.length;i++){
		if(str != arr[i]){
			retStr += retStr == "" ? arr[i] : "," + arr[i]; 
		}
	}
	return retStr;
}

function noExitImg(obj, rootPath){
	obj.src = rootPath + "/resources/common/images/icon_user_def.jpg";
}

function checkPhone(moble){
  var phone = /^((\d{3,4}-)?\d{7,8})$|(1[358]\d{9})$/;//µç»°ºÅÂë
  if(!phone.test(moble)){
	  return false;
  }
  return true;
}