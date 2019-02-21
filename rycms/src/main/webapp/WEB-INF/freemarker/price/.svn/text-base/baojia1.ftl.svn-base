<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>机构报价</title>
<link rel="stylesheet" href="${rc.contextPath}/css/historyprice/Tmain.css" />
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/default.css" />
<script type="text/javascript" src="${rc.contextPath}/js/laydate.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${rc.contextPath}/commons/scripts/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/WebCalendar.js"></script>
<script type="text/css">
 .uhide {
    display: none !important;
}
</script>
</head>
<body>


<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="${rc.contextPath}/images/top_left.gif"><img src="${rc.contextPath}/images/top_left.gif"/></td>
    <td valign="top" background="${rc.contextPath}/images/top_bgs .gif"></td>
    <td width="16" valign="top" background="${rc.contextPath}/images/top_right.gif"><img src="${rc.contextPath}/images/top_right.gif" /></td>
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
        
        <!-- 空白行-->
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" width="100%"><table width="100%">
              <tr width="100%">
                <td width="5%">&nbsp;</td>
                <td width="90%" ><table width="100%">
                    <tr width="100%">
                      <td width="5%">&nbsp;</td> 
                      <td width="10%" style="text-align:right;">机构：</td>
                      <td width="10%">
                      <input style="width:120px;" type="text" class="orgId onlyread" id="orgId"/>
					  </td>
					  
					  <td width="8%" style="text-align:right;">手机号：</td>
                      <td width="5%">
                      <input style="width:120px;" type="text" class="phoneList onlyread" id="phoneList"/>
					  </td>
					  
					  <td width="8%" style="text-align:right;">订单类型：</td>
                      <td width="5%">
						<select id="ddType" style="width:60px;">
							<option value="0" class="sp">商票</option>
							<option value="1" class="yp" selected>银票</option>
						</select>
					  </td>
					  
                      <td width="8%" style="text-align:right;">票据类型：</td>
                      <td width="5%">
						<select id="tktType" style="width:60px;">
							<option value="0">纸票</option>
							<option value="1">电票</option>
						</select>
					  </td>
					  
                      <td class="orgCity" width="8%" style="text-align:right;">交易城市：</td>
                      <td class="orgCity" width="5%">
						<select id="orgCityList" style="width:80px;">
						</select>
					  </td>
					  
                      <td width="5%" style="text-align:right;">日期：</td>
                      <td width="10%"><input class="laydate-icon" value="" id="demo" style="width:90px;"> </td>
                      <td width="1%">&nbsp;</td>
					  <td width="5%"><input class="btn" style=" width:80px; line-height:30px;" type="submit" value="搜索" onclick="search()" ></td>
                      </tr>
                      <tr class="yinpiao">
                      <td  width="8%" style="text-align:right;">上次报价时间：</td>
                      <td  width="10%" id="lastTime" style="width:90px;"></td>
                      <td  width="8%" style="text-align:right;">机构额度：</td>
                      <td  width="10%" id="orgPrice"></td>
                      
                    </tr>
                    <tr  class="yinpiao" style="display: none">
                    	<td width="5%"></td>
                    	<td width="10%"><input style="width: 100px;" class="btn" type="button" value="导出今日报价" onclick="exportExcel()" /></td>
                    </tr>
                  </table></td>
                <td width="5%" >&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        
        
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
        </tr>
        <!-- 产品列表开始 -->
        
        
        <tr >
        <tr class="shangpiao" style="display:none">
        <td width="150px">额度</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="edu" /></td>
        </tr>
        <tr class="shangpiao" style="display:none">
        <td width="150px">备注</td>
                        <td width="150px"><textarea id="remarksp" rows="" cols=""></textarea></td>
        </tr>
        <tr class="shangpiao zhiprice" style="display:none"><!-- 纸票 -->
        <td width="150px">报价区间1</td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Zmin1" /></td>
                        <td>万元</td>
                        <td width="50px"> ~ </td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Zmax1" /></td>
                         <td>万元</td>
         </tr>
         <tr class="shangpiao zhiprice" style="display:none">
        <td width="150px">收票期限</td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Zmin2" /></td>
                         <td>万元</td>
                        <td width="50px"> ~ </td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Zmax2" /></td>
                        <td>万元</td>
        </tr>
        <tr class="shangpiao dianprice" style="display:none"><!-- 电票 -->
        <td width="150px">报价区间2</td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Dmin1" /></td>
                        <td>万元</td>
                        <td width="50px"> ~ </td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Dmax1" /></td>
                         <td>万元</td>
         </tr>
         <tr class="shangpiao dianprice" style="display:none">
        <td width="150px">收票期限</td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Dmin2" /></td>
                         <td>万元</td>
                        <td width="50px"> ~ </td>
                        <td width="50px"><input style="width: 150px;" type="text" class="Dmax2" /></td>
                        <td>万元</td>
        </tr>
        </tr>
        
        <tr class="yinpiao ypnone" style="display: none">
          <td width="5%">&nbsp;</td>
          <td width="350px" style="display: none"><table width="350px">
              <tr>
                <h2 id="maiDuanPager">500万以上买断纸票(月利率)</h2>
              </tr>
              <tr width="350px">
                <td colspan="2">
                    <table width=""  class="cont tr_color maiDuanPager">
                      <tr width="" align="center" class="d">
                        <td width="150px">国股</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="guogu" /></td>
                      </tr>
                      <tr width="" align="center" class="d">
                        <td width="150px">大商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="dashang" /></td>
                      </tr>
                      <tr width="" align="center" class="d">
                        <td width="150px">小商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="chengshang" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">外资</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="waizi" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongshang" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农合</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nonghe" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="">农信</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongxin" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="">村镇</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="cunzhen" /></td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
          <td width="350px"><table width="350px">
              <tr>
                <h2 id="maiDuanElec">500万以上买断电票(年利率)一年期</h2>
              </tr>
              <tr>
                <td colspan="2">
                    <table class="cont tr_color maiDuanElec">
                      <tr width="" align="center" class="d">
                        <td width="150px">国股</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="guogu" /></td>
                      </tr>
                      <tr width="" align="center" class="d">
                        <td width="150px">大商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="dashang" /></td>
                      </tr>
                      <tr width="" align="center" class="d">
                        <td width="150px">小商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="chengshang" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">外资</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="waizi" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongshang" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农合</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nonghe" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="">农信</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongxin" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="">村镇</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="cunzhen" /></td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
            <td width="350px"><table>
              <tr>
                <h2 id="maiDuanElecHalf">500万以上买断电票(年利率)半年期</h2>
              </tr>
              <tr width="350px">
                <td colspan="2">
                    <table class="cont tr_color maiDuanElecHalf">
                      <tr width="" align="center" class="d">
                        <td width="150px">国股</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="guogu" /></td>
                      </tr>
                      <tr width="" align="center" class="d">
                        <td width="150px">大商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="dashang" /></td>
                      </tr>
                      <tr width="" align="center" class="d">
                        <td width="150px">小商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="chengshang" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">外资</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="waizi" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongshang" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农合</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nonghe" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="">农信</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongxin" /></td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="">村镇</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="cunzhen" /></td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr>
        <!-- 产品列表结束 -->
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
            </table></td>
        </tr>
        <tr class="yinpiao ypnone" style="display:none">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="maxMoneyMinDay">100万以上（含100万）的小于等于90天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMinDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMinDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
     <tr class="yinpiao ypnone" style="display:none">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="maxMoneyMidDay">100万以上（含100万）的91-178天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMidDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMidDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
    <tr class="yinpiao">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="maxMoneyMaxDay">100万以上（含100万大于等于179天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMaxDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMaxDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
   <tr class="yinpiao ypnone" style="display:none">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="midMoneyMinDay">50万-100万以上（含50万）的小于等于90天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMinDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMinDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
   <tr class="yinpiao ypnone" style="display:none">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="midMoneyMidDay">50万-100万以上（含50万）的90-178天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMidDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMidDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
  <tr class="yinpiao">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="midMoneyMaxDay">50万-100万以上（含50万）的大于等于179天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMaxDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMaxDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
  <tr class="yinpiao ypnone" style="display:none">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="minMoneyMinDay">50万以下的小于等于90天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMinDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMinDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
 <tr class="yinpiao ypnone" style="display:none">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="minMoneyMidDay">50万以下的90-178天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMidDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMidDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
 <tr class="yinpiao">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr>
                <h2 id="minMoneyMaxDay">50万以下的大于等于179天的纸票</h2>
              </tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMaxDay">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月方式</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">月利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">‰ + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMaxDay spnone">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月方式</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin2" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen2" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        </div>
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
            </table></td>
        </tr>
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="51%" class="left_txt"><img src="${rc.contextPath}/images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="${rc.contextPath}/images/sctg/icon_phone.gif" width="17" height="14"> 官方网站： <a href="https://www.utiexian.com/" target="_blank"> 上海票管家金融服务有限公司 </a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="${rc.contextPath}/images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="${rc.contextPath}/images/buttom_bgs.gif"><img src="${rc.contextPath}/images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_right.gif" width="16" height="17" /></td>
  </tr>
</table>

<script type="text/javascript">
	$(document).ready(function(){
	
	var oid= ${orgId};
	$.ajax({ //获取机构名称，手机号可以这里，也可以用传值过来的
			url: '../historyprice/baojia/searchOrgAndName2',
			type: 'POST',
			dataType: 'json',
			data: {'id':oid},
			success: function(result){
				var data = eval(result);
				var org = data.orgInfoList;
				if (org.company != null) {
				$("#orgId").val(org.company);
		     	$("#phoneList").val(org.phone);
				}
	           }
	          
		});
		 
		$("#tktType").change(function() {
			if($("#ddType").val() == 1){
				if($("#tktType").val() == 0 ){//纸票
					$(".ypnone").hide();
					$(".spnone").show();
					setOrgCityList();
				}else{
					$(".ypnone").show();
					$(".spnone").hide();
					quanguo();
				}
			setType();}
			else{
				if($("#tktType").val() == 0 ){//纸票
					$(".dianprice").hide();
					$(".zhiprice").show();
				}
				else{//电票
					$(".zhiprice").hide();
					$(".dianprice").show();
				}
			}
		}); 
		
		function quanguo(){
			var str = "<option value='1'>全国</option>";
			$("#orgCityList").text('');
			$("#orgCityList").append(str);
		}
		
		//加载数据
		setOrgCityList();
		setType();
		setInput();	
		
		
		$("#ddType").change(function() {
		    $(".orgCity").toggle();
			$(".shangpiao").toggle();
		    if($("#ddType").val() == 0 ){
		    	$(".yinpiao").hide();
				if($("#tktType").val() == 0 ){//纸票
					$(".dianprice").hide();
					$(".zhiprice").show();
				}else{//电票
					$(".zhiprice").hide();
					$(".dianprice").show();
				}
			}else{
				$(".zhiprice").hide();
				$(".dianprice").hide();
				$(".yinpiao").show();
				if($("#tktType").val() == 0 ){//纸票
					$(".ypnone").hide();
					$(".spnone").show();
					setOrgCityList();
				}else{
					$(".ypnone").show();
					$(".spnone").hide();
					quanguo();
				}
			}
			
		});
	 });		
		
	//设置报价类型
	function setType(){
		$.ajax({
			url: '../historyprice/search/type',
			type: 'POST',
			data: {},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				var type = $("#tktType").val();
				if (type == 0) {	//纸票
					showTypePagerData(data);
				} else if(type == 1) {
					showTypeElecData(data);
				}
			}
		});
	}
     
	//将所有页面中的输入框全部置为：--
	function setInput(){
		$("input").not(".btn,.onlyread, #demo").val("--");
		$("input").not("#demo").attr("readonly","readonly");
	}
	//设置交易城市下拉框
	function setOrgCityList() {
		var memberId = ${memberId};
		$.ajax({
			url: '../historyprice/baojia/search/citylist',
			type: 'POST',
			dataType: 'json',
			data: {'memberId':memberId},
			success: function(result){
				var data = eval(result);
				var orgCityList = data.orgCityList;
				if (orgCityList != null) {
					var str = '';
					$("#orgCityList").text('');
					for(var i in orgCityList){
						var orgCity = orgCityList[i];
						if (orgCity.cityid != null && orgCity.name != null) {
							str += "<option value='" + orgCity.cityid + "'>" + orgCity.name + "</option>";
						}
					}
					$("#orgCityList").append(str);
				}
			}
		});
	}

	//根据机构ID和日期查报价并展现到页面
	function search(){
		setType();
		setInput();	
		var orgId = ${orgId};
		
		var orgDate = $("#demo").val();//时间没填时 默认为今天
		var cityId = $("#orgCityList").val();
		
		$.ajax({
			url: '${rc.contextPath}/historyprice/baojia/searchPrice',
			type: 'POST',
			dataType: 'json',
			data: {'orgId':orgId, 'orgDate':orgDate, 'cityId':cityId ,
			},
			success: function(result) {
			var data = eval(result);
			    //加载银票
			   if(data.state == "success")
				{ $("#lastTime").html("");
				showPriceData(data);
				showLimit(data); }
				
			    //加载电票
			    var zp=data.zhipiao;
			    var dp=data.dianpiao;
			    if(zp != null){
				$(".Zmin1").val(zp.minPrice);	
				$(".Zmin2").val(zp.minDay);
				$(".Zmax1").val(zp.maxPrice);
				$(".Zmax2").val(zp.maxDay);	
				}
				if(dp != null){
				$(".Dmin1").val(dp.minPrice);	
				$(".Dmin2").val(dp.minDay);
				$(".Dmax1").val(dp.maxPrice);
				$(".Dmax2").val(dp.maxDay);	
				}
				alert(data.msg);
			}
		});
	}
	//将查询到的机构额度展示到页面
	function showLimit(data){
		var limit = data.orgLimit;
		if (limit != undefined){
			$("#orgPrice").html(limit.price + "万元");
			$(".edu").val(limit.priceSp);
			$("#remarksp").val(limit.remarkSp);
		} else {
			$("#orgPrice").html("0" + "万元");
		}
	}

	//将查询到的机构报价展示到页面
	function showPriceData(data){
		var priceList = data.priceList;
		var type = $("#tktType").val();//电票1，纸票0
		var data1 = new Array();//大票和[小票纸票 || 小票电票]
		
		for(var k in priceList) {
			var obj = priceList[k];
			if (obj.t1 == 0) {
				data1.push(obj);
				
			} else if(obj.t2 == type) {
				data1.push(obj);
			}
		}
		for(var i in data1){
			var price = data1[i];
			var pt_id = price.ptid;
			if (price.gg != null){ $(".pt_id" + pt_id + " .guogu").val(price.gg); }
			if (price.gg1 != null){ $(".pt_id" + pt_id + " .guogu1").val(price.gg1); }
			if (price.gg3 != null){ $(".pt_id" + pt_id + " .guogu2").val(price.gg3); }
			if (price.t1 == 0) {	//大票有大商
				if (price.ds != null){ $(".pt_id" + pt_id + " .dashang").val(price.ds); }
				if (price.ds1 != null){ $(".pt_id" + pt_id + " .dashang1").val(price.ds1); }
				if (price.ds3 != null){ $(".pt_id" + pt_id + " .dashang2").val(price.ds3); }
			}
			if (price.cs != null){ $(".pt_id" + pt_id + " .chengshang").val(price.cs); }
			if (price.cs1 != null){ $(".pt_id" + pt_id + " .chengshang1").val(price.cs1); }
			if (price.cs3 != null){ $(".pt_id" + pt_id + " .chengshang2").val(price.cs3); }
			if (price.wz != null){ $(".pt_id" + pt_id + " .waizi").val(price.wz); }
			if (price.wz1 != null){ $(".pt_id" + pt_id + " .waizi1").val(price.wz1); }
			if (price.wz3 != null){ $(".pt_id" + pt_id + " .waizi2").val(price.wz3); }
			if (price.ns != null){ $(".pt_id" + pt_id + " .nongshang").val(price.ns); }
			if (price.ns1 != null){ $(".pt_id" + pt_id + " .nongshang1").val(price.ns1); }
			if (price.ns3 != null){ $(".pt_id" + pt_id + " .nongshang2").val(price.ns3); }
			if (price.nh != null){ $(".pt_id" + pt_id + " .nonghe").val(price.nh); }
			if (price.nh1 != null){ $(".pt_id" + pt_id + " .nonghe1").val(price.nh1); }
			if (price.nh3 != null){ $(".pt_id" + pt_id + " .nonghe2").val(price.nh3); }
			if (price.nx != null){ $(".pt_id" + pt_id + " .nongxin").val(price.nx); }
			if (price.nx1 != null){ $(".pt_id" + pt_id + " .nongxin1").val(price.nx1); }
			if (price.nx3 != null){ $(".pt_id" + pt_id + " .nongxin2").val(price.nx3); }
			if (price.cz != null){ $(".pt_id" + pt_id + " .cunzhen").val(price.cz); }
			if (price.cz1 != null){ $(".pt_id" + pt_id + " .cunzhen1").val(price.cz1); }
			if (price.cz3 != null){ $(".pt_id" + pt_id + " .cunzhen2").val(price.cz3); }
			if (i == data1.length - 1) {
				var lastTime = price.time;
				$("#lastTime").html(lastTime.substring(10, lastTime.length));
			}
		}
	}

	//动态展现报价类型数据(大票小票、价格、天数等数据):小票纸票
	function showTypePagerData(data){
		var typeList = data.priceTypeList;
		for(var i in typeList){
			var type = typeList[i];
			var type1 = type.type1,type2 = type.type2,type3 = type.type3,type4 = type.type4,type5 = type.type5;

			if(type1 == 0 && type2 == 0) { setTypeData("maiDuanPager", type, "(月利率)"); }
			else if(type1 == 0 && type2 == 1 && type5 == 1) { setTypeData("maiDuanElec", type, "(年利率)一年期"); }
			else if(type1 == 0 && type2 == 1 && type5 == 0) { setTypeData("maiDuanElecHalf", type, "(年利率)半年期"); }
			else if(type1 == 1 && type2 == 0 && type3 == 2 && type4 == 0) { setTypeData("maxMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 2 && type4 == 1) { setTypeData("maxMoneyMidDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 2 && type4 == 2) { setTypeData("maxMoneyMaxDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 1 && type4 == 0) { setTypeData("midMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 1 && type4 == 1) { setTypeData("midMoneyMidDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 1 && type4 == 2) { setTypeData("midMoneyMaxDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 0 && type4 == 0) { setTypeData("minMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 0 && type4 == 1) { setTypeData("minMoneyMidDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 0 && type4 == 2) { setTypeData("minMoneyMaxDay", type); }
		}
	}
	
	//动态展现报价类型数据(大票小票、价格、天数等数据):小票电票
	function showTypeElecData(data){
		var typeList = data.priceTypeList;
		for(var i in typeList){
			var type = typeList[i];
			var type1 = type.type1,type2 = type.type2,type3 = type.type3,type4 = type.type4,type5 = type.type5;

			if(type1 == 0 && type2 == 0) { setTypeData("maiDuanPager", type, "(月利率)"); }
			else if(type1 == 0 && type2 == 1 && type5 == 1) { setTypeData("maiDuanElec", type, "(年利率)一年期"); }
			else if(type1 == 0 && type2 == 1 && type5 == 0) { setTypeData("maiDuanElecHalf", type, "(年利率)半年期"); }
			else if(type1 == 1 && type2 == 1 && type3 == 2 && type4 == 0) { setTypeData("maxMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 2 && type4 == 1) { setTypeData("maxMoneyMidDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 2 && type4 == 2) { setTypeData("maxMoneyMaxDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 1 && type4 == 0) { setTypeData("midMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 1 && type4 == 1) { setTypeData("midMoneyMidDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 1 && type4 == 2) { setTypeData("midMoneyMaxDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 0 && type4 == 0) { setTypeData("minMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 0 && type4 == 1) { setTypeData("minMoneyMidDay", type); }
			else if(type1 == 1 && type2 == 1 && type3 == 0 && type4 == 2) { setTypeData("minMoneyMaxDay", type); }
		}
	}
	
	//填充小票类型数据
	function setTypeData(id, type, style) {
		if (style != undefined) {
			$("#" + id).html(type.title4 + type.title2 + style);
		} else {
			$("#" + id).html(type.title4 + type.title3 + type.title2);
		}
		$("." + id).addClass("pt_id" + type.id);
		//$("." + id).attr("class", "pt_id" + type.id);
	}
	
	//导出execl报表
	function exportExcel() {
		var time = $("#demo").val();
		var cityId = $("#orgCityList").val();
		window.location.href="${rc.contextPath}/price/exportExcel?time=" + time + "&cityId=" + cityId;
	}
</script>
</body>
<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
}();
</script>
</html>