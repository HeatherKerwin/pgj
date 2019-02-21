<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传图片</title>
<script type="text/javascript" src="../../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
<script type="text/javascript">
function uploadImg(targetId, targetInputId,uploadInputId) {			
	var uploadId="ryUpload";	
	if(uploadInputId==undefined){
		var fileName=jQuery("#wokeUpload").val();
	}else{
		var fileName=jQuery("#"+uploadInputId).val();
		uploadId=uploadInputId;
	}
	
	var strtype=fileName.substring(fileName.length-3);
	strtype=strtype.toLowerCase();
	if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
		alert("请上传JPG、GIF、PNG格式的图片！");
		return false;
	}
	$.ajaxFileUpload({
		url : '<%=path %>/pic/upload/',
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
</script>
<style type="text/css">
.my-url{
	width:220px;
}
.li{
	margin-top:10px;
}
.img{
	max-height:180px;
}
</style>
</head>
<body>
    <div id="contet">    	
    	<form id="form1" name="form1" method="post" action="<%=path %>/pic/save/?code=index_rili">
	    	<input type="hidden" id="meth" name="meth" value="save"/>
	    	<ol>
	        	<li class="li">
		        	日历图片：<input type="file" id="fileId1" name="file" style="width:200px;"/>
		            <input type="button" id="uploadClickInput1" value="上传" onclick="uploadImg('img1','picpath1','fileId1');"/>
	            </li>
	            <c:choose>
	            	<c:when test="${appimageList[0].id >0 }">
	            		<img class="img" id="img1" src="${appimageList[0].path}" alt="图片在此显示"/>
	            		<input type="hidden" id="picpath1" name="picpath1" value="${appimageList[0].path}"/>
	            	</c:when>
	            	<c:otherwise>
	            		<img class="img" id="img1" alt="图片在此显示"/>
	            		<input type="hidden" id="picpath1" name="picpath1" value=""/>
	            	</c:otherwise>
	            </c:choose>
	        </ol>
	       	
			<div class="shangchuan" style="margin:20px;">
				<input type="submit" value="提交"  name="submit"></input>
			</div>
        </form>
     </div>
</body>
</html>