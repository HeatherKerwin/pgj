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
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6WIEK15yVVcchWSWBg1GVSx8"></script>
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
			},
			error : function() {
				alert("上传失败！");
			}
		});
	}
	 
	 function tp(){
	 	var tpxz=$("#tpxz").val();
		if(tpxz=="企业端"){
		 var qyd="index_banner";
		 var a=1;
		 $.ajax({
				type: "POST",
             	url: "../../pic/xztplist",
             	data: {code:qyd},
             	dataType:"json",
             	success:function(retValue){
					var data = retValue.data;
					if(data.length>0){
   				 		$("#url1").val(data[0].url);
   				 		$("#img1").attr("src",data[0].path);
   				 		$("#picpath1").val(data[0].path);
   				 		$("#url2").val(data[1].url);
   				 		$("#img2").attr("src",data[1].path);
   				 		$("#picpath2").val(data[1].path);
   				 		$("#url3").val(data[2].url);
   				 		$("#img3").attr("src",data[2].path);
   				 		$("#picpath3").val(data[2].path);
   				 		$("#url4").val(data[3].url);
   				 		$("#img4").attr("src",data[3].path);
   				 		$("#picpath4").val(data[3].path);
   				 	}else{
             			alert("暂无图片,请上传");
             			$("#url1").val("");
	   				 	$("#img1").attr("src","");
	   				 	$("#picpath1").val("");
	   				 	$("#url2").val("");
	   					$("#img2").attr("src","");
	   				 	$("#picpath2").val("");
	   				 	$("#url3").val("");
	   				 	$("#img3").attr("src","");
	   				 	$("#picpath3").val("");
	   				 	$("#url4").val("");
	   				 	$("#img4").attr("src","");
	   				 	$("#picpath4").val("");	
             		}
             	}
			});
			$("#code").val(qyd);
		}else{
			var jgd="index_jigou";
			var a=1;
  		 	$.ajax({
				type: "POST",
             	url: "../../pic/xztplist",
             	data: {code:jgd},
             	dataType:"json",
             	success:function(retValue){
             		var data = retValue.data;
             		if(data.length>0){
             			$("#url1").val(data[0].url);
	   				 	$("#img1").attr("src",data[0].path);
	   				 	$("#picpath1").val(data[0].path);
	   				 	$("#url2").val(data[1].url);
	   					$("#img2").attr("src",data[1].path);
	   				 	$("#picpath2").val(data[1].path);
	   				 	$("#url3").val(data[2].url);
	   				 	$("#img3").attr("src",data[2].path);
	   				 	$("#picpath3").val(data[2].path);
	   				 	$("#url4").val("");
	   				 	$("#img4").attr("src","");
	   				 	$("#picpath4").val("");	
             		}else{
             			alert("暂无图片,请上传");
             			$("#url1").val("");
	   				 	$("#img1").attr("src","");
	   				 	$("#picpath1").val("");
	   				 	$("#url2").val("");
	   					$("#img2").attr("src","");
	   				 	$("#picpath2").val("");
	   				 	$("#url3").val("");
	   				 	$("#img3").attr("src","");
	   				 	$("#picpath3").val("");
	   				 	$("#url4").val("");
	   				 	$("#img4").attr("src","");
	   				 	$("#picpath4").val("");	
             		}
             	}
			});
			$("#code").val(jgd);
		}	
	}
	 
	/**
	*  图片删除
	*/
	function deleteImg(num){
		if(num==1){
			$("#picpath1").val("");
			$("#img1").attr("src","");
		}else if(num==2){
			$("#picpath2").val("");
			$("#img2").attr("src","");
		}else if(num==3){
			$("#picpath3").val("");
			$("#img3").attr("src","");
		}else if(num==4){
			$("#picpath4").val("");
			$("#img4").attr("src","");
		}
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
          <td height="31" colspan="2"><div class="title">app焦点图维护 </div></td>
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
        <!-- 一条线 --> 
        <!-- 一条线 -->
    
        <!-- 产品列表开始 -->
        <tr>
			   <div id="contet">    
			   	
    	<form id="form1" name="form1" method="post" action="../../pic/save/">
	   			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择维护端口：<select id="tpxz" name="tpxz" onchange="tp()" style="width:100px">
				<#if (appimageList[0].code == "index_banner")>
					<option value="企业端" selected="selected">企业端</option>
	   				<option value="机构端" >机构端</option>
	   			<#else>
	   				<option value="企业端" >企业端</option>
	   				<option value="机构端" selected="selected" >机构端</option>	
				</#if>
			<select/>
	    	<input type="hidden" id="meth" name="meth" value="save" />
	    	<input type="hidden" id="code" name="code" value="${appimageList[0].code}" />	
		    	<ol class="ol">
		        	<li class="li">
		        		请求：<input type="text" id="url1" name="url1" value="${appimageList[0].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId1" name="file" style="width:200px;"/>
		            	<select name="sort" id="xb1" style="width:50px"><option selected="selected">1</option><option>2</option><option>3</option><option>4</option></select>
		            	<input type="button" id="uploadClickInput1" class="btn" style=" width:60px; line-height:15px;" value="上传" onclick="uploadImg('img1','picpath1','fileId1');"/>
		            	<input type="button" id="delede1" value="删除" class="btn" style=" width:60px; line-height:15px;" onclick="deleteImg(1);"/>
		            </li>
		            	<#if (appimageList[0].id >0) >
		            		<img class="img" id="img1" onerror="javascript:this.src='../../images/nopic.png';" src="${appimageList[0].path}" alt="图片在此显示"/>	
		            		<input type="hidden" id="picpath1" name="picpath1" value="${appimageList[0].path}"/>
		            	<#else>
		            		<img class="img" id="img1" alt="图片在此显示"  />
		            		<input type="hidden" id="picpath1" name="picpath1" value=""/>	
		            	</#if>
		            <br/>
		            
		            <li class="li">
		            	请求：<input type="text" id="url2" name="url2" value="${appimageList[1].url}" class="my-url"/>
		      			图片：<input type="file" id="fileId2" name="file" style="width:200px;" />
		      			<select name="sort" id="xb2" style="width:50px"><option>1</option><option selected="selected">2</option><option>3</option><option>4</option></select>
		            	<input type="button" id="uploadClickInput2" value="上传" class="btn" style=" width:60px; line-height:15px;" onclick="uploadImg('img2','picpath2','fileId2');"/>
		            	<input type="button" id="delede2" value="删除" class="btn" style=" width:60px; line-height:15px;" onclick="deleteImg(2);"/>	            
		            </li>
		            	<#if (appimageList[1].id > 0)>
		            		<img class="img" id="img2" onerror="javascript:this.src='../../images/nopic.png';" src="${appimageList[1].path}" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath2" name="picpath2" value="${appimageList[1].path}"/>
		            	<#else>
		            		<img class="img" id="img2" alt="图片在此显示"  />	
		            		<input type="hidden" id="picpath2" name="picpath2" value=""/>
		            	</#if>
		            <br/> 
		            		            
		            <li class="li">
		            	请求：<input type="text" id="url3" name="url3" value="${appimageList[2].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId3" name="file" style="width:200px;" />
		            	<select name="sort" id="xb3" style="width:50px"><option>1</option><option>2</option><option selected="selected">3</option><option>4</option></select>
		            	<input type="button" id="uploadClickInput3" value="上传" class="btn" style=" width:60px; line-height:15px;" onclick="uploadImg('img3','picpath3','fileId3');"/>		            
		            	<input type="button" id="delede3" value="删除" class="btn" style=" width:60px; line-height:15px;" onclick="deleteImg(3);"/>
		            </li>
			            <#if (appimageList[2].id > 0)>
			            	<img class="img" id="img3"  src="${appimageList[2].path}" alt="图片在此显示"  />	
			            	<input type="hidden" id="picpath3" name="picpath3" value="${appimageList[2].path}"/>
			            <#else>
			            	<img class="img" id="img3" alt="图片在此显示"  />	
			            	<input type="hidden" id="picpath3" name="picpath3" value=""/>
			            </#if>
		            <br/>
		             
		            <li class="li">
		            	请求：<input type="text" id="url4" name="url4" value="${appimageList[3].url}" class="my-url"/>
		            	图片：<input type="file" id="fileId4" name="file" style="width:200px;" />
		            	<select name="sort" id="xb4" style="width:50px"><option>1</option><option>2</option><option>3</option><option selected="selected">4</option></select>
		            	<input type="button" id="uploadClickInput4" value="上传" class="btn" style=" width:60px; line-height:15px;" onclick="uploadImg('img4','picpath4','fileId4');"/>	
		            	<input type="button" id="delede4" value="删除" class="btn" style=" width:60px; line-height:15px;" onclick="deleteImg(4);"/>	            		           
		            </li>
		            <#if (appimageList[3].id > 0)>
	          		    <img class="img" id="img4"  src="${appimageList[3].path}" alt="图片在此显示"  />
	            		<input type="hidden" id="picpath4" name="picpath4" value="${appimageList[3].path}"/>	
		            <#else>
	            		<img class="img" id="img4" alt="图片在此显示"  />	
	            		<input type="hidden" id="picpath4" name="picpath4" value=""/>
		            </#if>
				</ol>
			<div class="shangchuan" style="margin:20px;">
				<input type="submit" value="提交" class="btn" style=" width:60px; line-height:15px;" name="submit" ></input>
			</div>
			<span style="color:red;font-size:9px;">*注：请求中包含 ow=app 则轮播图图打开的方式为调用系统浏览器.（例如：http://www.baidu.com?ow=app）</span>
        </form>
     </div>       
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