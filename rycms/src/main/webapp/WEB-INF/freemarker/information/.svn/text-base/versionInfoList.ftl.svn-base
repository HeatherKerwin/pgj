<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>app下载页维护</title>
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/skin-1.css" />
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${rc.contextPath}/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${rc.contextPath}/commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/default.css" />
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/WebCalendar.js"></script>
</head>
<script language="javascript">
	function checkForm(){
		return true;
	}
	function query(){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		window.location.href="../versioninfo/list?begintime="+begintime+"&endtime="+endtime;
	}
	function addVersionInfo(){
		window.location.href="../versioninfo/toAddOrEdit";
	}
	function fabu(id){
		$.ajax({
			url:"../versioninfo/down.htm",
			type:"POST",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				alert(data.message);
				window.location.href="../versioninfo/list";
			}
		});
	}
</script>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="${rc.contextPath}/images/sctg/content_bg.gif">
    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
	        <tr>
	          <td height="31"><div class="title">app下载页维护</div></td>
	        </tr>
    	</table>
    </td>
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
        <tr style="border:#CCC 1px solid; margin:6px 10px;">
          <td colspan="4">
            
            <table class="title-2">
              <tr>
                <td width="20%" style="text-align:right;"> 更新时间：</td>
                	<td width="30%"><input type="text" id="begintime" name ="begintime" value="${begintimeStr }" placeholder="起始时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
                <td>
	                <td width="20%" style="text-align:right;">到</td>
	                <td width="30%"><input type="text" id="endtime" name ="endtime" value="${endtimeStr }"  placeholder="截止时间"  onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
	                <td>&nbsp;</td>
	                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="搜索" onclick="query();" ></td>
	                <td>&nbsp;</td>
	                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="新增" onclick="addVersionInfo();" ></td>
		            <table class="title-3" style="margin-bottom:5px;">
		              <tr>
		              </tr>
		            </table>
            	</td>
        </tr>
        
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              
            </table></td>
            
        </tr>
        <!-- 产品列表开始 -->
        
        
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="96%"><table class="title-6" style="margin-left:8%;" width="80%"  >
              <tr>
                <td colspan="2"><form action="" method="">
                    <table  class="cont tr_color" style="width:80%;table-layout:fixed;">
                      <tr>                      
                        <th >版本</th>
                        <th >大小</th>
                        <th >系统</th>
                        <th >更新时间</th>
                        <th >操作</th>
                      </tr>
                      <#if  pr??  && pr.results??>
						<#list pr.results as info>                     
	                      <tr align="center" class="d">
	                        <td class="dd">
	                        	${info.version }
	                        </td>
	                        <td class="dd">
	                        	${info.size }
	                        </td>
	                        <td class="dd">
	                        	${info.xitong }
	                        </td>
	                        <td class="dd">
	                        	${info.shijian }
	                        </td>
	                        <td class="dd"><a target="_blank" href="../versioninfo/preview?id=${info.id }"><font color="blue">预览</font></a><#if  info.shijian==maxDate><a href="../versioninfo/toAddOrEdit?id=${info.id }"><font color="blue">修改</font></a></#if><#if  info.state==2><a href="javascript:void(0);" onclick="fabu(${info.id})"><font color="blue">更新</font></a><a href="javascript:void(0);" onclick=""><font color="blue">(当前版本)</font></a></#if><#if info.state!=2><a href="javascript:void(0);" onclick="fabu(${info.id})"><font color="blue">发布</font></a></#if></td>
	                      </tr>
						</#list>
				     </#if>  
                    </table>
                  </form>
                 </td>
              </tr>
              	
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr>
        <tr>
	      <td colspan="7">
		    <div id="pager">
		  		<#import "/common/pager.ftl" as q>
		  		<#if pr.totalCount??>
		  			<@q.pager pageNo=pr.currentPage pageSize=pr.pageSize recordCount=pr.totalCount toURL="../versioninfo/list"  />
		  		</#if>
		    </div>	
		</td>
       	</tr> 
        <!-- 产品列表结束 -->
        
        <!-- 一条线 -->
        <tr>
        	<td height="40" colspan="4">
	          	<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
	            </table>
        	</td>
        </tr>
        <tr >
          <td height="30px"></td>
        </tr>
        <tr>
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
<input id="fabu" type="button" value="发布" /><input id="back" type="button" value="返回" />
<!-- 产品列表结束 --> 
<!-- 分页按钮--> 
<!-- 底部部分 -->

<tr style="height:10px;">
  <td>&nbsp;</td>
</tr>
</body>
</html>