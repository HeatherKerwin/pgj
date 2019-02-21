<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>市场推广</title>
<link rel="stylesheet" type="text/css" href="../../css/skin-1.css" />
<link rel="stylesheet" href="../../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../../commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="../../commons/styles/default.css" />
<script type="text/javascript" src="../../commons/scripts/WebCalendar.js"></script>
</head>
<script type="text/javascript">
var tagCode = "HONGBAO";//查询大类为红包的小类字典表
function badd() {
	document.location.href= "../../hongbao/badd/?tagCode="+tagCode;
}
function query() {
	var start = $("#startdateStr").val();
	var end = $("#enddateStr").val();
	var tagId = $("#tagId").val();
	var name = $("#name").val();
	location.href="../../hongbao/list/?startdateStr="+start+"&enddateStr="+end+"&tagId="+tagId+"&name="+name+"&tagCode="+tagCode;
}
</script>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif">
    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
	        <tr>
	          <td height="31"><div class="title">红包活动</div></td>
	        </tr>
    	</table>
    </td>
    <td width="16" valign="top" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  
  <tr> 
    
    <!--第一行左边框-->
    <td valign="middle" background="../../images/sctg/mail_left_bg.gif">&nbsp;</td>
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
                <td width="10%" style="text-align:right;">开始时间</td>
                <#if begintime??>
                	<td width="15%"><input type="text" id="startdateStr" value="${begintime}" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                <#else>
                	<td width="15%"><input type="text" id="startdateStr" placeholder="开始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                </#if>                
                <td width="10%" style="text-align:right;">结束时间</td>
                <#if endtime??>
                	<td width="15%"><input type="text" id="enddateStr" value="${endtime}" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                <#else>
                	<td width="15%"><input type="text" id="enddateStr" placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                </#if>
                          
                <td width="10%" style="text-align:right;">红包类型</td>
            	<td width="15%">
            		<select id="tagId" style="width: 90%;">
        				<option value="">-全部-</option>
            			<#list tags as tag>
            				<option value="${tag.id}" <#if tagId==tag.id>selected</#if>>${tag.name}</option>
            			</#list>
            		</select>
            	</td>
                <td width="10%" style="text-align:right;">活动名称</td>
                <td width="15%">
                	<input type="text" id="name" placeholder="活动名称" value="${name}"/>
                </td>
                
	            <table class="title-3" style="margin-bottom:5px;">
	              <tr>
	              	<td>&nbsp;</td>
	                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="query();" ></td>
	                <td>&nbsp;</td>
	              </tr>
	            </table>
            </td>
        </tr>
        
        <tr style="text-align:left;">
        	<td>
        		<input class="btn" style="width:100px;line-height:30px;margin-top:5px;" type="button" value="添加" onclick="badd();"/>
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
                        <th>市场名称</th>
                        <th>红包类型</th>
                        <th>推广费用</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>负责人</th>
                        <th>链接</th>
                        <th>创建时间</th>                                                
                      </tr>
                 <#if page?? && page.results??>
					<#list page.results as activity>                     
                      <tr align="center" class="d">
                        <td class="dd"><a href="../../hongbao/bupdate/?id=${activity.id}&tagCode=HONGBAO">${activity.name}</a></td>
                        <td>
                        	<#list tags as tag>
	            				<#if activity.tagId==tag.id>${tag.name}</#if>
	            			</#list>
                        </td>
                        <td>${activity.totalprice}<span>元</span></td>
                        <td>${activity.startdate?string("yyyy-MM-dd")}</td>
                        <td>${activity.enddate?string("yyyy-MM-dd")}</td>
                        <td>${activity.header}</td>
                        <td>${activity.lianjie}</td>
                        <td>${activity.createtime?string("yyyy-MM-dd HH:mm")}</td>                                                
                      </tr>
					</#list>
			     </#if>                                           
                    </table>
                  </form></td>
              </tr>
            </table></td>
          <td width="2%">&nbsp;</td>
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
</body>
</html>