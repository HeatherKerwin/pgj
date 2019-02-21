<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>APP首页咨询维护</title>
<link rel="stylesheet" type="text/css" href="../../css/skin-1.css" />
<link rel="stylesheet" href="../../commons/styles/reset.css" />
<link rel="stylesheet" href="../../commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="../../commons/styles/bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" href="../../commons/styles/default.css" />

<script src="../../commons/scripts/jquery-1.10.1.min.js"></script>
<script src="../../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../../commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="../../commons/scripts/ajaxupload.js"></script>
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>


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
		url : '../../pic/upload/',
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
		},error : function() {
			alert("上传失败！");
		}
	});
}
</script>
</head>
<body>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif">
	    	<img src="../../images/sctg/left_top_right.gif" width="17" height="29" />
	    </td>
	    <td valign="top" background="../../images/sctg/content_bg.gif">
	    	APP首页咨询维护
		</td>
	    <td width="16" valign="top" background="../../images/sctg/mail_right_bg.gif">
	    	<img src="../../images/sctg/nav_right_bg.gif" width="16" height="29" />
	    </td>
	</tr>
  
	<tr> 
	    <td valign="middle" background="../../images/sctg/mail_left_bg.gif">&nbsp;</td>
	    <td valign="top" bgcolor="#F7F8F9">
	    	<table width="1380px;" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
				   <div id="contet">    	
				    	<form id="form1" name="form1" method="post" action="../../pic/save/?code=index_app_zx">
					    	<input type="hidden" id="meth" name="meth" value="save"/>
					    	<ol>
					        	<li class="li">
					        		请求：<input type="text" id="url1" name="url1" value="${appimageList[0].url}" class="my-url"/>
						        	图片：<input type="file" id="fileId1" name="file" style="width:200px;"/>
						            <input type="button" id="uploadClickInput1" value="上传" class="btn" style=" width:60px; line-height:15px;"  onclick="uploadImg('img1','picpath1','fileId1');"/>
					           		<input name="sort" type="hidden" value="1">
					            </li>
					            <#if (appimageList[0].path > 0)>
					            	<img class="img" id="img1" src="${appimageList[0].path}" alt="图片在此显示"/>
					            	<input type="hidden" id="picpath1" name="picpath1" value="${appimageList[0].path}"/>
					            <#else>
					            	<img class="img" id="img1" alt="图片在此显示"/>
					            	<input type="hidden" id="picpath1" name="picpath1" value=""/>
					            </#if><br/>
					        </ol>
					       	
							<div class="shangchuan" style="margin:20px;">
								<input type="submit" value="提交" class="btn" style=" width:60px; line-height:15px;"  name="submit"></input>
							</div>
						</form>
					</div>
	        	</tr>
	        	<tr>
	          		<td height="40" colspan="12">
	          			<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
	            	</td>
	        	</tr>
		        <tr>
		        	<td height="30px"></td>
		        </tr>
				<tr>
					<td width="2%">&nbsp;</td>
	        		<td width="51%" class="left_txt"><img src="../../images/sctg/icon_mail.gif" width="16" height="11">客户服务邮箱：ryfinance@ryfinance.com<br/>
	       				<img src="../../images/sctg/icon_phone.gif" width="17" height="14">
	       				官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a>
	    			</td>
					<td>&nbsp;</td>
			  		<td>&nbsp;</td>
				</tr>
			</table>
		</td>
	    <td background="../../images/sctg/mail_right_bg.gif">&nbsp;</td>
	</tr>
	<tr>
	    <td valign="bottom" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/buttom_left.gif" width="17" height="17" /></td>
	    <td background="../../images/sctg/buttom_bgs.gif"><img src="../../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
	    <td valign="bottom" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/buttom_right.gif" width="16" height="17" /></td>
	</tr>
</table>
</body>
</html>