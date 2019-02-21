<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>新增编辑优惠消息</title>
	<link rel="stylesheet" href="<%=path %>/commons/styles/reset.css"/>
	<link rel="stylesheet" href="<%=path %>/commons/styles/bootstrap.css"/>
	<link rel="stylesheet" href="<%=path %>/commons/styles/default.css"/>
	<link rel="stylesheet" href="<%=path %>/kindeditor/themes/default/default.css"/>
	<link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css"/>
	
	<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
	<script src="<%=path %>/js/ajaxfileupload.js"></script>
	
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
	
	function save(){
		var title = $("#title").val();
		var url = $("#url").val();
		var imgPath = $("#imgPath").val();
		if(title==null || title.trim()==""){
			alert("请输入标题");
			return false;
		}
		if(url==null || url.trim()==""){
			alert("请输入请求");
			return false;
		}
		if(imgPath==null || imgPath.trim()==""){
			alert("请上传图片");
			return false;
		}
		$("#form1").submit();
	}
	</script>
	<style type="text/css">
	.img{
		max-height:260px;
	}
	</style>
</head>

<body>
	<div id="contet">
    	<form id="form1" name="form1" method="post" action="<%=path %>/preferentialInfo/save">
	    	<input type="hidden" id="id" name="id" value="${info.id}"/>
	    	<ol>
	    		<li class="li">
	    			标题：<input type="text" id="title" name="title" value="${info.title}" style="width:300px;"/>
	    		</li>
	    		<li class="li">
	    			请求：<input type="text" id="url" name="url" value="${info.url}" style="width:300px;"/>
	    		</li>
	        	<li class="li" style="margin-bottom:10px;">
		        	图片：<input type="file" id="fileId1" name="file" style="width:250px;"/>
		            <input type="button" id="uploadClickInput1" class="btn btn-success" value="上传" onclick="uploadImg('img1','imgPath','fileId1');"/>	            
	            </li>
	            
	            <c:choose>
	            	<c:when test="${info.id >0 }">
	            		<img class="img" id="img1" src="${info.imgPath}" alt="图片在此显示" onerror="this.src='<%=path%>/images/nopic.png;'"/>
	            		<input type="hidden" id="imgPath" name="imgPath" value="${info.imgPath}"/>
	            	</c:when>
	            	<c:otherwise>
	            		<img class="img" id="img1" alt="图片在此显示" src="<%=path%>/images/nopic.png"/>
	            		<input type="hidden" id="imgPath" name="imgPath" value=""/>
	            	</c:otherwise>
	            </c:choose>
	        </ol>
			<div>
				<input type="button" class="btn btn-success" value="提交" onclick="save();"/>
			</div>
        </form>
     </div>
</body>
</html>