
function uploadImg(targetId, targetInputId,uploadInputId) {			
	var uploadId="ryUpload";	
	if(uploadInputId==undefined){
		var fileName=$("#wokeUpload").val();
	}else{
		var fileName=$("#"+uploadInputId).val();
		uploadId=uploadInputId;
	}
	
	var strtype=fileName.substring(fileName.length-3);
	strtype=strtype.toLowerCase();
	if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
		alert("请上传JPG、GIF、PNG格式的图片！");
			return false;
	}
	$.ajaxFileUpload({
		url : 'api/picuploadJson.do',
		secureuri : false,
		dataType : 'json',
		data : {
			"targetDom" : "#"+targetId+",#"+targetInputId
		},
		fileElementId : uploadId,
		success : function(data) {
				if(data=="error"){
					alert("上传失败！");
				}else{
					$("#" + targetInputId).val(data);
					$("#" + targetId).attr("src", data);
				}
		},
		error : function() {
			alert("上传失败！");
		}
	});
}
