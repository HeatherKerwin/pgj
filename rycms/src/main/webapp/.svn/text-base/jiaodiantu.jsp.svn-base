<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- <link href="css/style3.css" rel="stylesheet" type="text/css" /> -->
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
    	<form id="form1" name="form1" method="post" action="<%=path %>/pic/save/?code=index_banner">
	    	<input type="hidden" id="meth" name="meth" value="save" />
		    	<ol>
		        	<li class="li">
		        		请求：<input type="text" id="url1" name="url1" value="${appimageList[0].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId1" name="file" style="width:200px;"/>
		            	<input type="button" id="uploadClickInput1" value="上传" onclick="uploadImg('img1','picpath1','fileId1');"/>
		            </li>
		            <c:choose>
		            	<c:when test="${appimageList[0].id >0 }">	
		            		<!--<c:out value="${appimageList[1].path}"></c:out>-->	            		
		            		<img class="img" id="img1" src="${appimageList[0].path}" alt="图片在此显示"/>	
		            		<input type="hidden" id="picpath1" name="picpath1" value="${appimageList[0].path}"/>
		            	</c:when>
		            	<c:otherwise>		            		
		            		<img class="img" id="img1" alt="图片在此显示"  />
		            		<input type="hidden" id="picpath1" name="picpath1" value=""/>	
		            	</c:otherwise>
		            </c:choose>	
		            <br/>
		            
		            <li class="li">
		            	请求：<input type="text" id="url2" name="url2" value="${appimageList[1].url}" class="my-url"/>
		      			图片：<input type="file" id="fileId2" name="file" style="width:200px;" />
		            	<input type="button" id="uploadClickInput2" value="上传" onclick="uploadImg('img2','picpath2','fileId2');"/>	            
		            </li>
		            
		            <c:choose>
		            	<c:when test="${appimageList[1].id >0 }">	
		            		<!--<c:out value="${appimageList[1].path}"></c:out>-->	            		
		            		<img class="img" id="img2" src="${appimageList[1].path}" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath2" name="picpath2" value="${appimageList[1].path}"/>
		            	</c:when>
		            	<c:otherwise>		            		
		            		<img class="img" id="img2" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath2" name="picpath2" value=""/>
		            	</c:otherwise>
		            </c:choose>		            		             
		            <br/> 
		            		            
		            <li class="li">
		            	请求：<input type="text" id="url3" name="url3" value="${appimageList[2].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId3" name="file" style="width:200px;" />
		            	<input type="button" id="uploadClickInput3" value="上传" onclick="uploadImg('img3','picpath3','fileId3');"/>		            
		            </li>
		            <c:choose>
		            	<c:when test="${appimageList[2].id >0 }">	
		            		<!--<c:out value="${appimageList[1].path}"></c:out>-->	            		
		            		<img class="img" id="img3" src="${appimageList[2].path}" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath3" name="picpath3" value="${appimageList[2].path}"/>
		            	</c:when>
		            	<c:otherwise>		            		
		            		<img class="img" id="img3" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath3" name="picpath3" value=""/>
		            	</c:otherwise>
		            </c:choose>		
		            <br/>
		             
		            <li class="li">
		            	请求：<input type="text" id="url4" name="url4" value="${appimageList[3].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId4" name="file" style="width:200px;" />
		            	<input type="button" id="uploadClickInput4" value="上传" onclick="uploadImg('img4','picpath4','fileId4');"/>		            		           
		            </li>
		            <c:choose>
		            	<c:when test="${appimageList[3].id >0 }">	
		            		<!--<c:out value="${appimageList[1].path}"></c:out>-->	            		
		            		<img class="img" id="img4" src="${appimageList[3].path}" alt="图片在此显示"  />
		            		<input type="hidden" id="picpath4" name="picpath4" value="${appimageList[3].path}"/>	
		            	</c:when>
		            	<c:otherwise>		            		
		            		<img class="img" id="img4" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath4" name="picpath4" value=""/>
		            	</c:otherwise>
		            </c:choose>		
		           <br/>
		           
		           <li class="li">
		            	请求：<input type="text" id="url5" name="url5" value="${appimageList[4].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId5" name="file" style="width:200px;"/>
		            	<input type="button" id="uploadClickInput5" value="上传" onclick="uploadImg('img5','picpath5','fileId5');"/>		            
		            </li>
		            <c:choose>
		            	<c:when test="${appimageList[4].id >0 }">	
		            		<img class="img" id="img3" src="${appimageList[4].path}" alt="图片在此显示"/>	
		            		<input type="hidden" id="picpath5" name="picpath5" value="${appimageList[4].path}"/>
		            	</c:when>
		            	<c:otherwise>		            		
		            		<img class="img" id="img5" alt="图片在此显示"/>	
		            		<input type="hidden" id="picpath5" name="picpath5" value=""/>
		            	</c:otherwise>
		            </c:choose>		
		        </ol>
	       	 <div class="shangchuan" style="margin:20px;">
	       	 	<input type="submit" value="提交" name="submit" ></input>
	         </div>
        </form>
     </div>
</body>
</html>