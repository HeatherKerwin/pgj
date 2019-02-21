<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单列表</title>
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
		var orderflag = $("#orderflag").val();
		window.location.href="../../discountrecord/list/?begintime="+begintime+"&endtime="+endtime+"&orderflag="+orderflag;
	}
	function confirmdelete(discountrecordid){
		if(confirm("确定删除吗?")){
			location.href="../../discountrecord/delete/?discountrecordid="+discountrecordid;
		}
	}
	window.onload = function(){
		var timeID = setTimeout("location.reload()",5000);
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
	          <td height="31"><div class="title">订单列表</div></td>
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
                <td  >&nbsp;</td>
                <td width="10%"  style="text-align:right;">起始时间</td>
                	<td width="20%"><input type="text" id="begintime" name ="begintime" value="${begintimeStr }" placeholder="起始时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
                
                
                 <td  width="20">&nbsp;</td>
                 
                   <td width="10%"  style="text-align:right;">截止时间</td>
     
                	<td width="20%"><input type="text" id="endtime" name ="endtime" value="${endtimeStr }"  placeholder="截止时间"  onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
          
                
                 <td  width="20">&nbsp;</td>
                
                <td width="10%" style="text-align:right;">状态</td>
                	<td width="20%">
                		<select name="orderflag" id="orderflag" >
								<option value=""   <#if orderflag=="">selected="selected"</#if>>全部</option>
								<option value="1"  <#if orderflag== 1>selected="selected"</#if>>待确认</option>
								<option value="2"  <#if orderflag== 2>selected="selected"</#if>>待验票</option>
								<option value="3"  <#if orderflag== 3>selected="selected"</#if>>已完成</option>
								<option value="4"  <#if orderflag== 4>selected="selected"</#if>>待收款</option>
								<option value="0"  <#if orderflag== 0>selected="selected"</#if>>无效订单</option>
								<option value="-1" <#if orderflag== -1>selected="selected"</#if>>订单失败</option>
						</select>	
                	</td>
                <td >&nbsp;</td>
                
              </tr>
	            </table> 
	             <table class="title-3">
              <tr>
              	<td>&nbsp;</td>
                <td ><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="query()" ></td>
                <td>&nbsp;</td>
              </tr>
            </table>
            </td>
        </tr>
       <!-- <tr style="text-align:left;">
        	<td>
        		<input class="btn" style="width:100px;line-height:30px;margin-top:5px;" type="button" value="添加" onclick="badd();"/>
        	</td>
        </tr> -->
        
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
                        <th style="width:200px;">订单号</th>
                        <th style="width:200px;">下单日期</th>
                        <th>订单状态</th>
                        <th>总额</th>
                        <th>总额情况</th>
                        <th>推荐人编号</th>
                        <th>操作</th>
                      </tr>
	                 <#if pageModel??  && pageModel.results??>
				   
						<#list pageModel.results as discountrecord>                     
		                      <tr align="center" class="d">
		                       <td class="dd">
									<a href="../../discountrecord/bupdate/?discountrecordid=${discountrecord.id}"><font color="blue">${discountrecord.ordernumber }</font></a>
								</td>
								<td class="dd">${discountrecord.ordertimeshow }</td>
		                       <td class="dd">
		                        	<#if discountrecord.orderflag==-1>超额订单</#if>
		                        	<#if discountrecord.orderflag==0>无效订单</#if>
		                        	<#if discountrecord.orderflag==1>待确认</#if>
		                        	<#if discountrecord.orderflag==2>待验票</#if>
		                        	<#if discountrecord.orderflag==3>已完成</#if>
		                        	<#if discountrecord.orderflag==4>待收款</#if>
								</td>
								<td class="dd">${discountrecord.allmoney }</td>
		                        <td class="dd">
			                        <#if discountrecord.orderflag==-1><font color="red">超出</font></#if>
			                        <#if discountrecord.orderflag==1><font color="green">正常</font></#if>
								</td>
								<td class="dd">${discountrecord.recommendpeople}</td>
								<td class="dd" onclick="confirmdelete(${discountrecord.id})">
									<font color="blue">删除</font>
								</td>
		                      </tr>
						</#list>
						
					 <#else>
					 	暂无数据！
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
		  		<#if pageModel.totalCount??>
		  			<@q.pager pageNo=pageModel.currentPage pageSize=pageModel.pageSize recordCount=pageModel.totalCount toURL="../../discountrecord/list/"/>
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