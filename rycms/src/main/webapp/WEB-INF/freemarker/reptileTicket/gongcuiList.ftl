<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>市场信息</title>
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
	function updateInformation(newsid){
		window.location.href="../../gongcui/toupdate/?id="+newsid;
	}
</script>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
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
          <tr style="text-align:left;">
        	<td>
        		<input class="btn" style="width:100px;line-height:30px;margin-top:5px;" type="button" value="公催信息列表" />
        	</td>
        </tr> 
        <!-- 一条线 -->
        <!-- 产品列表开始 -->
        
        
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="100%"><table class="title-6" style="margin-left:1%;" width="90%"  >
              <tr>
                <td colspan="2"><form action="" method="">
                    <table  class="cont tr_color" style="width:96%;table-layout:fixed;">
                      <tr>                      
                        <th style="width:220px;">标题</th>
                        <th>发布时间</th>
                        <th>创建时间</th>
                        <th style="width:80px;">操作</th>
                      </tr>
	                 <#if pr??  && pr.results??>
						<#list pr.results as reprile>                     
	                      <tr align="center" class="d">
	                        <td class="dd" align="left" title="${reprile.title }"><a href="#"><font color="blue">${reprile.title }</font></a></td>
	                        <td class="dd">
	                        		<#if reprile.releaseTime??>
										${reprile.releaseTime?string("yyyy-MM-dd HH:mm:ss")}
									</#if>	
	                        </td>
	                        <td class="dd">
	                        		<#if reprile.createTime??>
										${reprile.createTime?string("yyyy-MM-dd HH:mm:ss")}
									</#if>	
	                        </td>
	                        <td class="dd"><a onclick="updateInformation(${reprile.id})"><font color="blue">查看</font></a></td>
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
		  			<@q.pager pageNo=pr.currentPage pageSize=pr.pageSize recordCount=pr.totalCount toURL="../../gongcui/search/"  />
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

<!-- 产品列表结束 --> 
<!-- 分页按钮--> 
<!-- 底部部分 -->

<tr style="height:10px;">
  <td>&nbsp;</td>
</tr>
</body>
</html>