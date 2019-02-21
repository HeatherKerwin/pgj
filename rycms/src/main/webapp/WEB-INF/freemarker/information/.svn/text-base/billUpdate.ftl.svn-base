<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>xi</title>
<link rel="stylesheet" type="text/css" href="../../css/skin-1.css" />
<link rel="stylesheet" href="../../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../../commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="../../commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../../commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<link rel="stylesheet" href="../../commons/styles/default.css" />
<script src="../../commons/scripts/ajaxupload.js"></script>
<!-- 上传组件Uploadify -->
<script src="../../commons/scripts/jquery.uploadify.js"></script>

<script type="text/javascript" src="../../commons/scripts/WebCalendar.js"></script>

<!--编辑器-->
<link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6WIEK15yVVcchWSWBg1GVSx8"></script>

<script type="text/javascript">
	KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '../../kindeditor/plugins/code/prettify.css',
				uploadJson : '../../pic/uploadpick',				
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms[0].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms[0].submit();
					});
				},
				afterChange : function(){
					this.sync();
				}
			});
			prettyPrint();
	});
</script>
<script>
	function chooseSubmitMethod(flag){
		if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	
	function checkForm(){
		return true;
	}
</script>
</head>
<body>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="58" border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="31" colspan="2"><div class="title">票据讲堂 </div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  
  <tr> 
    
    <!--第一行左边框-->
    <td valign="middle" background="../../images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="1380px;" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 空白行-->


        
       <!-- <tr >
        	<td>&nbsp;</td>
        </tr>
        
     <tr style="text-align:left; margin-top:6px;">
              <td ><input class="btn" style=" width:100px; line-height:30px;" type="button" value="修改" onclick="chooseSubmitMethod()" /></td>
        </tr>  -->
        
        <!-- 一条线 --> 
        <!-- 一条线 -->
    
        <!-- 产品列表开始 -->
        <tr>
        	<td colspan="4">
        	<form method="post" action="../../piaojujiangtang/update/" onsubmit="return checkForm()" id="form1">
        	<table width="100%">
        		<tr>&nbsp;</tr>
        		 <tr >
        		 	<div id="edit">
						<div id="edit-main">
							<div class="edit-box">
								<input type="hidden" value="${piaojujiangtang.id }" name="id"/>
								<div class="label"><label>类型</label></div>
								<div class="">
									<select name="type">
										<option value="1" <#if piaojujiangtang.type==1>selected="selected"</#if>>新手指引</option>
										<option value="2" <#if piaojujiangtang.type==2>selected="selected"</#if>>票据知识</option>
										<option value="3" <#if piaojujiangtang.type==3>selected="selected"</#if>>如何验票</option>
										<option value="4" <#if piaojujiangtang.type==4>selected="selected"</#if>>法律法规</option>
									</select>
								</div>
							</div>
							<div class="edit-box">
								<div class="label"><label>标题</label></div>
								<div class="">
									<input type="text" name="title"  value="${piaojujiangtang.title }"/>
								</div>
							</div>
							<br/>
							<div class="edit-box">
								<div class="label"><label>时间</label></div>
								<div class="">
									<input name="publishtime" id="day" type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm')" readonly style="width:206px;cursor:pointer" value="${piaojujiangtang.publishtime?string("yyyy-MM-dd HH:mm")}"/>   
								</div>
							</div>
							<br/>
							<div class="edit-box">
								<div class="label"><label>内容</label></div>
								<div class="">
									<textarea name="content" id="content" cols="100" rows="25" style="visibility:hidden;width:92%;height:300px">${piaojujiangtang.content }</textarea>
								</div>
							</div>
						
							<br/>
						</div>
					
					
					</div>
        		 </tr>
        		 <tr>
        		 	 <tr style="text-align:left; margin-top:6px;">
		              	<td  colspan="2"><input class="btn" style=" width:100px; line-height:30px;" type="button" value="修改" onclick="chooseSubmitMethod()" /></td>
		        	 </tr> 
        		 </tr>
        	</table>
        	</form>
        </td>
        </tr>
       
        <!-- 产品列表结束 -->
        
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="12"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              
            </table></td>
            
        </tr>
        <tr >
          <td height="30px"></td>
        </tr>
        <tr >
          <td width="2%">&nbsp;</td>
          <td width="51%" class="left_txt"><img src="../../images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="../../images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="../../images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td valign="bottom" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="../../images/sctg/buttom_bgs.gif"><img src="../../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/buttom_right.gif" width="16" height="17" /></td>
  </tr>
</table>

<!-- 产品列表结束 --> 
<!-- 分页按钮--> 
<!-- 底部部分 -->

<tr style="height:10px;">
  <td>&nbsp;</td>
</tr>
</from>
</body>
</html>