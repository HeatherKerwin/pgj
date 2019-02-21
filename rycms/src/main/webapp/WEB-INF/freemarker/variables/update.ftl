<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/skin-1.css" />

<link rel="stylesheet" href="${rc.contextPath}/commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${rc.contextPath}/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${rc.contextPath}/commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/default.css" />
<script src="${rc.contextPath}/js/ajaxfileupload.js"></script>
<title>无标题文档</title>
</head>
<script type="text/javascript" language="javascript">
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
			url : '${rc.contextPath}/pic/upload/',
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
	
	function chooseSubmitMethod(){
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
		formSubmit.submit();
	}
</script>
<script>
    function update()
	{
		document.updateForm.action="${rc.contextPath}/variables/update";
		document.updateForm.submit();
	}
</script>
<body>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="${rc.contextPath}/images/sctg/content_bg.gif"><table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" background="./${rc.contextPath}/images/sctg/content_bg.gif">
        <tr>
          <td height="50">
          	<div class="title">新增优惠消息</div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  <tr> 
    <!--第一行左边框-->
    <td valign="middle" background="${rc.contextPath}/images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 空白行-->
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
        </tr>
   	<tr>
          <td width="2%">&nbsp;</td>
          <td width="96%"><table class="title-6" width="100%" >
              <tr>
                <td colspan="2">
                <form style="overflow-y: scroll;" name="updateForm" method="post">
                <input type="hidden" name="id" value="${variables.id}">
                    <table width="60%"  class="cont sctg tr_color">
                      <tr align="center" class="d">
	                    <th>code</th>
	                    <td>
	                    	<input type="text" class="input-sm" name="code" id="title" value="${variables.code}"/>
	                    </td>
                      </tr>
                      <tr align="center" class="d">
	                    <th>name</th>
	                    <td>
	                    	<input type="text" class="input-sm" name="name" id="title" value="${variables.name}"/>
	                    </td>
                      </tr>
                      <tr align="center" class="d">
                        <th>remarks</th>
                        <td><input type="text" class="input-sm" name="remarks" id="url" value="${variables.remarks}"/></td>                        
                      </tr>
              		<tr align="center" class="d" >
                        <th>value</th>
                        <td><input type="text" class="input-sm" name="value" id="url" value="${variables.value}"/></td>    
             	 	</tr>
             	 	<tr>
             	 		<td colspan="2" align="center"><input type="button"style=" width:100px;"  value="修改" onclick="update()"></td>
             	 	</tr>
                    </table>
                  </form>
                  
                 </td>
              </tr>
    		 </tr>
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr> <tr >
          <td height="30px"></td>
        </tr>
        <tr >
          <td width="2%">&nbsp;</td>
          <td width="51%" class="left_txt"><img src="${rc.contextPath}/images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="${rc.contextPath}/images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="${rc.contextPath}/images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="${rc.contextPath}/images/sctg/buttom_bgs.gif"><img src="${rc.contextPath}/images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_right.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
</html>