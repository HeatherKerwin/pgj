<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>xi</title>
<link rel="stylesheet" type="text/css" href="../css/skin-1.css" />
<link rel="stylesheet" href="../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="../commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="..//commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<link rel="stylesheet" href="../commons/styles/default.css" />
<link rel="stylesheet" type="text/css" href="../css/notice/notice.css">
<link rel="Stylesheet" type="text/css" href="../css/notice/notice-dialog.css" />
<script type="text/javascript" src="../js/laydate.js"></script>
<script src="../commons/scripts/ajaxupload.js"></script>
<!-- 上传组件Uploadify -->
<script src="../commons/scripts/jquery.uploadify.js"></script>

<script type="text/javascript">
//多行输入
function textCounter(field, countfield, maxlimit) {
	if ($("#"+field).val().length > maxlimit)  
		$("#"+field).val($("#"+field).val().substring(0, maxlimit));  
	else  
		$("#"+countfield).val(maxlimit - $("#"+field).val().length);  
}

$(function ($) {//弹窗
	//非工作日弹出窗口
	$(".example1").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 600);
	}, function () {
		$(this).stop().animate({
			opacity: '0.6'
		}, 1000);
	}).on('click', function () {
		$("body").append("<div id='mask'></div>");
		$("#mask").addClass("mask").fadeIn("slow");
		$("#LoginBox1").find(".id").val($(this).attr("data-id"));
		$("#LoginBox1").find(".code").val($(this).attr("data-code"));
		$("#LoginBox1").find("#content1").val($(this).attr("data-alert"));
		$("#LoginBox1").fadeIn("slow");
		textCounter('content1','remLen1',30);
	});
	//节假日弹出窗口
	$(".example2").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 600);
	}, function () {
		$(this).stop().animate({
			opacity: '0.6'
		}, 1000);
	}).on('click', function () {
		$("body").append("<div id='mask'></div>");
		$("#mask").addClass("mask").fadeIn("slow");
		$("#LoginBox2").find(".id").val($(this).attr("data-id"));
		$("#LoginBox2").find(".code").val($(this).attr("data-code"));
		$("#LoginBox2").find("#content2").val($(this).attr("data-content"));
		$("#LoginBox2").find("#content2_").val($(this).attr("data-alert"));
		$("#LoginBox2").fadeIn("slow");
		textCounter('content2','remLen2',50);
		textCounter('content2_','remLen2_',50);
	})
	//额度弹出窗口
	$(".example3").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 600);
	}, function () {
		$(this).stop().animate({
			opacity: '0.6'
		}, 1000);
	}).on('click', function () {
		$("body").append("<div id='mask'></div>");
		$("#mask").addClass("mask").fadeIn("slow");
		$("#LoginBox3").find(".id").val($(this).attr("data-id"));
		$("#LoginBox3").find(".code").val($(this).attr("data-code"));
		$("#LoginBox3").find("#content3").val($(this).attr("data-content"));
		$("#LoginBox3").find("#content3_").val($(this).attr("data-alert"));
		$("#LoginBox3").find("#content3__").val($(this).attr("data-remark"));
		
		var a = $(this).attr("data-start");
		if(a!=null && a!= ""){
			var strs = a.split(" ");
			if(strs.length>1)a = strs[0];
		}
		$("#LoginBox3").find("#start").val(a);
		
		var b = $(this).attr("data-end");
		if(b!=null && b!= ""){
			var strs = b.split(" ");
			if(strs.length>1)b = strs[0];
		}else{
			b = a;
		}
		$("#LoginBox3").find("#end").val(b);
		
		$("#LoginBox3").fadeIn("slow");
		textCounter('content3','remLen3',30);
		textCounter('content3_','remLen3_',30);
		textCounter('content3__','remLen3__',30);
	});
	//按钮的透明度
	$("#loginbtn").hover(function () {
		$(this).stop().animate({
			opacity: '1'
		}, 600);
	}, function () {
		$(this).stop().animate({
			opacity: '0.8'
		}, 1000);
	});
	//关闭
	$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
		$("#LoginBox1,#LoginBox2,#LoginBox3").fadeOut("fast");
		$("#mask").css({ display: 'none' });
	});
});

function save(){
	var holidayTips = $("#holidayTips");
	var start = $("#start").val();
	var end = $("#end").val();
	if(start==null || start==""){
		alert("请输入开始时间");
		return false;
	}
	if(end==null && end==""){
		alert("请输入截止时间");
		return false;
	}
	holidayTips.submit();
}
</script>
</head>
<body>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../images/sctg/content_bg.gif"><table width="100%" height="58" border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="31" colspan="2"><div class="title">贴现提示信息 </div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  
  <tr> 
    
    <!--第一行左边框-->
    <td valign="middle" background="../images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="1380px;" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 产品列表开始 -->
        <tr>
    			<div class="reminder">
					<div class="s1">
						<div class="s1_1">
							弹出框（额度不够）
						</div>
						<div class="s1_2">
							<div class="s1_21 fl">
								${ERROR.alert}&nbsp;
							</div>
							<div class="s1_22 fl">
								<a href="#" id="example" class="example1" data-id="${ERROR.id}" data-code="ERROR" data-alert="${ERROR.alert}">
									编辑 
								</a>
							</div>
						</div>
					</div>
					<div class="clear space">
					</div>
					<div class="s1">
						<div class="s1_1">
							非工作日
						</div>
						<div class="s1_2">
							<div class="s1_21 fl">
								<span style="color:red;">提示语：</span>${OFFDAY.content}
							</div>
							<div class="s1_21 fl">
								<span style="color:red;">弹出框：</span>${OFFDAY.alert}
							</div>
							<div class="s1_22 fl">
								<a href="#" id="example" class="example2" data-id="${OFFDAY.id}" data-code="OFFDAY" data-content="${OFFDAY.content}" data-alert="${OFFDAY.alert}">
									编辑 
								</a>
							</div>
						</div>
						<br/>
					</div>
					<div class="clear space"></div>
					
					<!--s2-->
					<div class="s2">
						<div class="s2_1">
							<div class="s2_11 fl">
								节日
							</div>
							<div class="s2_12 fl">
								提示语
							</div>
							<div class="s2_13 fl">
								弹出框
							</div>
							<div class="s2_14 fl">
								备注
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_YD.id}" data-code="FESTIVAL_YD" data-content="${FESTIVAL_YD.content}" data-alert="${FESTIVAL_YD.alert}" data-remark="${FESTIVAL_YD.remark}" data-start="${FESTIVAL_YD.startDate}" data-end="${FESTIVAL_YD.endDate}">
									元旦
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_YD.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_YD.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_YD.remark}&nbsp;
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_CJ.id}" data-code="FESTIVAL_CJ" data-content="${FESTIVAL_CJ.content}" data-alert="${FESTIVAL_CJ.alert}" data-remark="${FESTIVAL_CJ.remark}" data-start="${FESTIVAL_CJ.startDate}" data-end="${FESTIVAL_CJ.endDate}">
									春节
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_CJ.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_CJ.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_CJ.remark}&nbsp;
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_QMJ.id}" data-code="FESTIVAL_QMJ" data-content="${FESTIVAL_QMJ.content}" data-alert="${FESTIVAL_QMJ.alert}" data-remark="${FESTIVAL_QMJ.remark}" data-start="${FESTIVAL_QMJ.startDate}" data-end="${FESTIVAL_QMJ.endDate}">
									清明节
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_QMJ.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_QMJ.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_QMJ.remark}&nbsp;
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_LDJ.id}" data-code="FESTIVAL_LDJ" data-content="${FESTIVAL_LDJ.content}" data-alert="${FESTIVAL_LDJ.alert}" data-remark="${FESTIVAL_LDJ.remark}" data-start="${FESTIVAL_LDJ.startDate}" data-end="${FESTIVAL_LDJ.endDate}">
									劳动节
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_LDJ.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_LDJ.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_LDJ.remark}&nbsp;
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_DWJ.id}" data-code="FESTIVAL_DWJ" data-content="${FESTIVAL_DWJ.content}" data-alert="${FESTIVAL_DWJ.alert}" data-remark="${FESTIVAL_DWJ.remark}" data-start="${FESTIVAL_DWJ.startDate}" data-end="${FESTIVAL_DWJ.endDate}">
									端午节
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_DWJ.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_DWJ.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_DWJ.remark}&nbsp;
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_ZQJ.id}" data-code="FESTIVAL_ZQJ" data-content="${FESTIVAL_ZQJ.content}" data-alert="${FESTIVAL_ZQJ.alert}" data-remark="${FESTIVAL_ZQJ.remark}" data-start="${FESTIVAL_ZQJ.startDate}" data-end="${FESTIVAL_ZQJ.endDate}">
									中秋节
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_ZQJ.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_ZQJ.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_ZQJ.remark}&nbsp;
							</div>
						</div>
						<div class="s2_2">
							<div class="s2_21 fl">
								<a class="example3" data-id="${FESTIVAL_GQJ.id}" data-code="FESTIVAL_GQJ" data-content="${FESTIVAL_GQJ.content}" data-alert="${FESTIVAL_GQJ.alert}" data-remark="${FESTIVAL_GQJ.remark}" data-start="${FESTIVAL_GQJ.startDate}" data-end="${FESTIVAL_GQJ.endDate}">
									国庆节
								</a>
							</div>
							<div class="s2_22 fl">
								${FESTIVAL_GQJ.content}&nbsp;
							</div>
							<div class="s2_23 fl">
								${FESTIVAL_GQJ.alert}&nbsp;
							</div>
							<div class="s2_24 fl">
								${FESTIVAL_GQJ.remark}&nbsp;
							</div>
						</div>
					</div>
					<!--额度弹窗-->
					<div id="LoginBox1" class="aa">
						<div class="row1">
							额度不够提示
							<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">
								×
							</a>
						</div>
						<form name="myform" method="post" action="../tiexian/add/">
							<input type="hidden" class="id" name="id" value="">
							<input type="hidden" class="code" name="code" value="">
							<textarea class="in-t" id="content1" name="alert" cols="28" rows="5" oninput="textCounter('content1','remLen1',30);"></textarea>
							<p class="in-tt">
								您还可以输入:<input id="remLen1" name="remLen" style="margin-top: 10px;width: 70px;height: 15px;" type="text" value="30" size="15" readonly>个字符
							</p>
							<div class="row">
								<input type="submit" value="提交" class="loginbtn">
							</div>
						</form>
					</div>
					<!--弹窗2-->
					<div id="LoginBox2" class="aa">
						<div class="row1">
							非工作日贴现提示
							<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">
								×
							</a>
						</div>
						<form name="myform" method="post" action="../tiexian/add/">
							<input type="hidden" class="id" name="id" value="">
							<input type="hidden" class="code" name="code" value="">
							<p class="in-l fl">
								提示语：
							</p>
							<textarea class="in-t1 fl" id="content2" name="content" cols="28" rows="5" oninput="textCounter('content2','remLen2',50);"></textarea>
							<div class="clear"></div>
							<p class="in-tt1">
								您还可以输入:
								<input id="remLen2" name="remLen2" type="text" style=" width: 70px;height: 15px;" value="30" size="15" readonly>
								个字符
							</p>
							<p class="in-l fl">
								弹出框：
							</p>
							<textarea class="in-t1" id="content2_" name="alert" cols="28" rows="5" oninput="textCounter('content2_','remLen2_',50);"></textarea>
							<p class="in-tt1">
								您还可以输入:
								<input id="remLen2_" name="remLen" type="text" style=" width: 70px;height: 15px;" value="30" size="15" readonly>
								个字符
							</p>
							<div class="row" style="margin-left: 45px;">
								<input type="submit" value="提交" class="loginbtn">
							</div>
						</form>
					</div>
					<!--弹窗3-->
					<div id="LoginBox3">
						<div class="row1">
							节假日贴现提示
							<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">
								×
							</a>
						</div>
						<form id="holidayTips" method="post" action="../tiexian/add/">
							<input type="hidden" class="id" name="id" value="">
							<input type="hidden" class="code" name="code" value="">
							<input type="hidden" class="pageName" name="pageName" value="prompt"/>
							<div class="demo3" style="margin-top: 10px;">
								<ul class="inline">
									<li style="font-size:16px;float:left;width:80px;margin-left:30px;">
										<span style="margin-top: 10px;">开始日：</span>
									</li>
									<li style="width:200px;margin:0 36px;float:left;">
										<input class="laydate-icon" id="start" style="width: 185px;" name="start" readonly="readonly" onclick="laydate({elem:'#start',istime:true,istoday:true,max:$('#end').val(),format: 'YYYY-MM-DD'});" value="">
									</li>
									<li style="height:5px;clear:both;">&nbsp;</li>
									<li style="font-size:16px;float:left;width:80px;margin-left:30px;">
										结束日：
									</li>
									<li style="width:200px;margin:0 36px;float:left;margin-top: 10px;margin-bottom: 10px;">
										<input class="laydate-icon" id="end" name="end" style="width: 185px;" readonly="readonly" onclick="laydate({elem:'#end',istime:true,istoday:true,min:$('#start').val(),format: 'YYYY-MM-DD'});" value="">
									</li>
									<li style="height:5px;clear:both;">&nbsp;</li>
								</ul>
							</div>
							<p class="in-l fl">
								&nbsp;提示语：
							</p>
							<textarea class="in-t1 fl" id="content3" name="content" cols="28" rows="3" oninput="textCounter('content3','remLen3',30);"></textarea>
							<div class="clear"></div>
							<p class="in-tt1">
								您还可以输入:
								<input id="remLen3" name="remLen" type="text" value="30" size="15" style=" width: 70px;height: 15px;" readonly>
								个字符
							</p>
							<p class="in-l fl">
								&nbsp;弹出框：
							</p>
							<textarea class="in-t1" id="content3_" name="alert" cols="28" rows="3" oninput="textCounter('content3_','remLen3_',30);"></textarea>
							<p class="in-tt1">
								您还可以输入:
								<input id="remLen3_" name="remLen" type="text" value="50" size="15" style=" width: 70px;height: 15px;" readonly>
								个字符
							</p>
							<p class="in-l fl">
								&nbsp;备&nbsp;&nbsp;&nbsp;注：
							</p>
							<textarea class="in-t1" id="content3__" name="remark" cols="28" rows="3" oninput="textCounter('content3__','remLen3__',30);"></textarea>
							<p class="in-tt1">
								您还可以输入:
								<input id="remLen3__" name="remLen" type="text" value="50" size="15" style=" width: 70px;height: 15px;" readonly>
								个字符
							</p>
							<div class="row" style="margin-left: 45px;">
								<input type="button" onclick="save(this);" value="提交" class="loginbtn">
							</div>
						</form>
					</div>
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
          <td width="51%" class="left_txt"><img src="../images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="../images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="../images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td valign="bottom" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="../images/sctg/buttom_bgs.gif"><img src="../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/buttom_right.gif" width="16" height="17" /></td>
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