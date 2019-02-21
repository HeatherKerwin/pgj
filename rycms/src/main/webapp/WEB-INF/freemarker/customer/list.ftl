<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>会员信息</title>
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
<script type="text/javascript" src="../../js/conf.js"></script>
</head>
<script language="javascript">
	$(document).on('click','#chk-all',function(){
        $(this).closest('table').find('input[name="idcheckbox"]').prop('checked', this.checked);
	});

	$(document).on('click','.td-name',function(){
        var obj = $(this).closest('tr').find('input');
        if(obj.prop('checked') == true){   
          obj.prop('checked',false);  
        } else{   
          obj.prop('checked',true); 
		}
	});
	
	function updateStatus(flag){
		var checkboxObj = document.getElementsByName("idcheckbox");
		var chooseFlag = false;
		for(var i = 0; i < checkboxObj.length;i++){
			if(checkboxObj[i].checked){
				chooseFlag = true;
				break;
			}
		}
		if(chooseFlag){
			if(confirm("确定更改状态吗")){
				$("#choosestatus").val(flag);
				$("#form1").submit();
			}
		}
	}
	function jumpPage(jumpPageNumber){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var recommendpeople = $("#recommendpeople").val();
		window.location.href="../../member/list/?currentPage="+jumpPageNumber+"&begintime="+begintime+"&endtime="+endtime+"&recommendpeople="+recommendpeople;
	}
	function search(){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var recommendpeople = $("#recommendpeople").val();
		window.location.href="../../member/list/?begintime="+begintime+"&endtime="+endtime+"&recommendpeople="+recommendpeople;
	}
	
	function sendmessage(){
		var sendmessage = $('input[name="sendmessage"]:checked ').val();
		var idcheckbox = "";    
		  $('input[name="idcheckbox"]:checked').each(function(){    
		   idcheckbox += $(this).val()+",";    
		  }); 
		if(sendmessage!=undefined){
			$.ajax({
				url:"../../member/sendmessage/",
				type:"post",
				data:{"admindesc":$("#admindesc").val(),"sendmessage":sendmessage,"idcheckbox":idcheckbox},
				success:function(data){
					alert("发布成功");
				}
			});
		}
	}
	
	function confirmdelete(memberid){
		if(confirm("确定删除吗?")){
			location.href="../../member/delete/?memberid="+memberid;
		}
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
	          <td height="31"><div class="title">会员信息</div></td>
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
	            	<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已经注册用户数:${memberallcount }</td>
	            	<td >&nbsp;推广信息</td>
	            	<td colspan="1"><input type="text" name="admindesc" id="admindesc" style="width:85%;"/></td>
	            	<td colspan="5"><input type="radio" value="1" name="sendmessage"/>&nbsp;&nbsp;全部人&nbsp;&nbsp;<input type="radio" value="2" name="sendmessage"/>&nbsp;&nbsp;所选人&nbsp;&nbsp;<input type="button" class="btn btn-success" value="发送" onclick="sendmessage()"/></td>
	            </tr>
              <tr>
                <td width="10%" style="text-align:right;"> 起始时间</td>
                <td width="15%">
                	<input type="text" name="begintime" id="begintime" placeholder="开始时间" <#if begintimeStr??> value="${begintimeStr}" </#if> onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
                </td>
                <td width="10%" style="text-align:right;">截止时间</td>
                <td width="15%"><input type="text" name="endtime" id="endtime" <#if endtimeStr??> value="${endtimeStr}"</#if>  placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                <td width="12%" style="text-align:right;">推荐人编号</td>
            	<td width="15%">
            		<input type="text" name="recommendpeople" id="recommendpeople" />
            	</td>
                
	            <table class="title-3" style="margin-bottom:5px;">
	              <tr>
	              	<td>&nbsp;</td>
	                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="search();" ></td>
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
                        <th style="width: 50px;"><input type="checkbox" id="chk-all"  style="width: 13px;height: 13px;"/></th>
                        <th style="width: 140px;">手机号码</th>
                        <th style="width: 120px;">推荐人</th>
                        <th style="width: 180px;">注册日期</th>
                        <th>下单数</th>
                        <th>交易总额</th>
                        <th>真实姓名</th>
                        <th>月活</th>
                        <th>活跃度</th>   
                      </tr>
                 <#if pageModel?? && pageModel.results??>
					<#list pageModel.results as member>                     
                      <tr align="center" class="d">
                      	<td class="dd"><input type="checkbox" id="" value="${member.id }" name="idcheckbox" style="width: 13px;height: 13px;"/></td>
                        <td class="dd"><a href="../../member/bupdate/?memberid=${member.id}"><font color="blue">${fun.hideMobile(member.mobile,isAdmin)}</font></a></td>
                        <td class="dd">${member.recommendpeople }</td>
                        <td class="dd">
                      		 ${member.regtimeshow }
                        </td>
                        <td class="dd">${member.orderallcount }</td>
                        <td class="dd">${member.orderallprice }</td>
                        <td class="dd">${member.username }</td>
                        <td class="dd">${member.monthactivecount }</td>
                        <td class="dd">${member.activecount }</td>                                                
                      </tr>
					</#list>
			     </#if>    
				                                      
                    </table>
                  </form></td>
              </tr>
              	
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr>
        <tr>
	      <td colspan="7">
			    <div id="pager">
			  		<#import "/common/pager.ftl" as q>
			  		<#if pageModel.totalCount??>
			  			<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="../../member/list/"  />
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