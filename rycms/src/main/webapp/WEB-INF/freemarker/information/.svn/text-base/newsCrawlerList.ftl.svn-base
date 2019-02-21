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
	function checkForm(){
		return true;
	}
	function query(){
		$("#form").action="../../newscrawler/search/";
		$("#form").submit();
	}
	function confirmdelete(newsid){
		if(confirm("确定删除吗?")){
			location.href="../../newscrawler/delete/?id="+newsid;
		}
	}
	function updateInformation(newsid){
		window.location.href="../../newscrawler/toupdate/?id="+newsid;
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
	          <td height="31"><div class="title">爬虫资讯</div></td>
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
            <form id="form" action="" method="post">
            <table class="title-2">
              <tr>
                <td width="20%" style="text-align:right;"> 起始时间</td>
                <td width="30%">
                	<input type="text" id="begintime" name ="beginDate" value="${begintimeStr }" placeholder="起始时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
                </td>
                <td width="20%" style="text-align:right;">截止时间</td>
                <td width="30%"><input type="text" id="endtime" name ="endDate" value="${endtimeStr }"  placeholder="截止时间"  onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
	          </tr> 
	          <tr>
              	<td width="10%" style="text-align:right;">类型：</td>
                	<td width="20%">
                		<select name="type" id="type" >
								<option value="-1">-全部-</option>
								<option value="1"  <#if type== 1>selected="selected"</#if>>票据新闻</option>
								<option value="2"  <#if type== 2>selected="selected"</#if>>金融动态</option>
								<option value="3"  <#if type== 3>selected="selected"</#if>>管家动态</option>
								<option value="4"  <#if type== 4>selected="selected"</#if>>媒体报道</option>
						</select>	
                	</td>
              </tr>
              <tr>
              	<td width="10%" style="text-align:right;">关键字：</td>
              	<td width="30%"><input type="text" id="title" name ="title"  placeholder="请输入标题的关键字" value="${title}"/></td>
              </tr>
              <tr>
              	<td width="10%" style="text-align:right;">状态：</td>
                	<td width="20%">
                		<select name="state" id="state">
								<option value="-1">-全部-</option>
								<option value="0"  <#if state== 0>selected="selected"</#if>>已发布</option>
								<option value="1"  <#if state== 1>selected="selected"</#if>>未发布</option>
						</select>	
                	 </td>
              </tr>
	            <table class="title-3" style="margin-bottom:5px;">
	              
	              <tr>
	              	<td>&nbsp;</td>
	                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="query();" ></td>
	                <td>&nbsp;</td>
	              </tr>
	            </table> 
            </td>
        </form>
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
                        <th style="width:220px;">标题</th>
                        <th >地址来源</th>
                        <th >文章来源</th>
                        <th >类型</th>
                        <th >爬虫时间</th>
                        <th >状态</th>
                        <th>操作</th>
                      </tr>
	                 <#if pr??  && pr.results??>
						<#list pr.results as news>                     
	                      <tr align="center" class="d">
	                        <td class="dd" align="left" title="${news.title }"><a href="../../newscrawler/toupdate/?id=${news.id}"><font color="blue">${news.title }</font></a></td>
	                        <td class="dd">${news.urlSource}</td>
	                        <td class="dd">${news.articleSource}</td>
	                        <td class="dd">
	                        		<#if news.type == 1>票据新闻</#if>
		                        	<#if news.type == 2>金融动态</#if>
		                        	<#if news.type == 3>管家动态</#if>
		                        	<#if news.type == 4>媒体报道</#if>
	                        </td>
	                        <td class="dd">
	                        		<#if news.createTime??>
										${news.createTime?string("yyyy-MM-dd HH:mm:ss")}
									</#if>	
	                        </td>
	                        <td class="dd">
	                        		<#if news.state == 0>已发布</#if>
		                        	<#if news.state == 1>未发布</#if>
	                        </td>
	                        <td class="dd"><a onclick="confirmdelete(${news.id })"><font color="blue">删除</font></a>&nbsp;&nbsp;<a onclick="updateInformation(${news.id})"><font color="blue">编辑</font></a></td>
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
		  			<@q.pager pageNo=pr.currentPage pageSize=pr.pageSize recordCount=pr.totalCount toURL="../../newscrawler/search/"  />
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