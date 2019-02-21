<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>价格修改</title>
<link rel="stylesheet" href="${rc.contextPath}/css/historyprice/Tmain.css" />
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/default.css" />
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${rc.contextPath}/commons/scripts/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/WebCalendar.js"></script>
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
                      <td width="1%">&nbsp;</td>
                      <td width="10%" style="text-align:right;">起始时间：</td>
                      <td width="10%" id="startDate"></td>
                      
                      <td class="orgCity" width="8%" style="text-align:right;">交易城市：</td>
                      <td class="orgCity" width="5%">
						<select id="orgCityList" style="width:80px;">
						</select>
					  </td>
					  
                      <td width="1%">&nbsp;</td>
                      <td width="10%" style="text-align:right;">票据类型：</td>
                      <td width="10%">
						<select id="tktType" style="width:60px;">
							<option value="0">纸票</option>
							<option value="1">电票</option>
						</select>
					  </td>
					  
                      <td width="2%">&nbsp;</td>
                      <td width="15%"><input style="width: 100px;" class="btn" type="button" value="复制至价格修改" onclick="xiugai()" /></td>
                      <td width="2%">&nbsp;</td>
                      
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
        <tr class="zhi">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="minMoney">50万以下纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoney" day="187">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月票</h3>
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
                <td colspan="2" class="_dian" style="border:1px #DDDDDD solid;">
                    <table class="minMoney" day="187">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月票</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="guogu2" /><input style="width: 150px;" type="text" class="guogu3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="chengshang2" /><input style="width: 150px;" type="text" class="chengshang3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="waizi2" /><input style="width: 150px;" type="text" class="waizi3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nongshang2" /><input style="width: 150px;" type="text" class="nongshang3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nonghe2" /><input style="width: 150px;" type="text" class="nonghe3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nongxin2" /><input style="width: 150px;" type="text" class="nongxin3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="cunzhen2" /><input style="width: 150px;" type="text" class="cunzhen3" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
	<tr class="zhi">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="midMoney">50万-100万以上（含50万）纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoney" day="187">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月票</h3>
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
                <td colspan="2" class="_dian" style="border:1px #DDDDDD solid;">
                    <table class="midMoney" day="<#if minDay??>${minDay}</#if>">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月票</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="guogu2" /><input style="width: 150px;" type="text" class="guogu3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="chengshang2" /><input style="width: 150px;" type="text" class="chengshang3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="waizi2" /><input style="width: 150px;" type="text" class="waizi3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nongshang2" /><input style="width: 150px;" type="text" class="nongshang3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nonghe2" /><input style="width: 150px;" type="text" class="nonghe3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nongxin2" /><input style="width: 150px;" type="text" class="nongxin3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="cunzhen2" /><input style="width: 150px;" type="text" class="cunzhen3" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
	<tr class="zhi">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="maxMoney">100万以上（含100万）纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoney" day="<#if minDay??>${minDay}</#if>">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月票</h3>
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
                <td colspan="2" class="_dian" style="border:1px #DDDDDD solid;">
                    <table class="maxMoney" day="<#if minDay??>${minDay}</#if>">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">足月票</h3>
                      </caption>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="guogu2" /><input style="width: 150px;" type="text" class="guogu3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="chengshang2" /><input style="width: 150px;" type="text" class="chengshang3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="waizi2" /><input style="width: 150px;" type="text" class="waizi3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nongshang2" /><input style="width: 150px;" type="text" class="nongshang3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nonghe2" /><input style="width: 150px;" type="text" class="nonghe3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="nongxin2" /><input style="width: 150px;" type="text" class="nongxin3" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="" align="center" height="30px">
                        <td width="250px">每10万约</td>
                        <td width=""><input style="width: 150px;" type="hidden" class="cunzhen2" /><input style="width: 150px;" type="text" class="cunzhen3" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr class="dian">
          <td width="5%">&nbsp;</td>
          <td width="350px"><table>
              <tr>
                <h2 id="maiDuanElec">500万以上买断电票(年利率)一年期</h2>
              </tr>
              <tr>
                <td colspan="2">
                    <table class="cont tr_color maiDuanElec">
                      <tr align="center" class="d">
                        <td width="150px">国股</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">大商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="dashang" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">小商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td>%</td>
                      </tr>
                      <tr align="center" class="d">
                        <td width="150px">外资</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="50%">农商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农合</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农信</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">村镇</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td>%</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
          <td width="350px"><table>
              <tr>
                <h2 id="maiDuanElecHalf">500万以上买断电票(年利率)半年期</h2>
              </tr>
              <tr>
                <td colspan="2">
                    <table class="cont tr_color maiDuanElecHalf">
                      <tr align="center" class="d">
                        <td width="150px">国股</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">大商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="dashang" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">小商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td>%</td>
                      </tr>
                      <tr align="center" class="d">
                        <td width="150px">外资</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="50%">农商</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农合</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">农信</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td>%</td>
                      </tr>
                      <tr  align="center" class="d">
                        <td width="150px">村镇</td>
                        <td width="150px"><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td>%</td>
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
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="maxMoneyMinDay">100万以上（含100万）纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMinDay" day="<#if minDay??>${minDay}</#if>">
                      <caption>
                      <h3 style="height:30px; line-height:30px;">不足月票</h3>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="maxMoneyMidDay">100万以（含100万）的91-178天的纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMidDay" day="<#if midDay??>${midDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="maxMoneyMaxDay">100万以上（含100万)纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="maxMoneyMaxDay" day="<#if maxDay??>${maxDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">%+ </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="midMoneyMinDay">50万-100万以上（含50万）纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMinDay" day="<#if minDay??>${minDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="midMoneyMidDay">50万-100万以上（含50万）的90-178天的纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMidDay" day="<#if midDay??>${midDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="midMoneyMaxDay">50万-100万以上（含50万）的大于等于179天的纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="midMoneyMaxDay" day="<#if maxDay??>${maxDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="minMoneyMinDay">50万以下纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMinDay" day="<#if minDay??>${minDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="minMoneyMidDay">50万以下的90-178天的纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMidDay" day="<#if midDay??>${midDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        <tr height="10px"></tr>
        <tr class="dian">
          <td width="15%">&nbsp;</td>
          <td width=""><table width="">
              <tr><h2 id="minMoneyMaxDay">50万以下的大于等于179天的纸票</h2></tr>
              <tr  width="">
                <td colspan="2" style="border:1px #DDDDDD solid;">
                    <table class="minMoneyMaxDay" day="<#if maxDay??>${maxDay}</#if>">
                      <caption>
                      </caption>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">国股:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="guogu1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">城商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="chengshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">外资:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="waizi1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农商:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongshang1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农合:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nonghe1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">农信:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="nongxin1" /></td>
                        <td width="150px">元</td>
                      </tr>
                      <tr width="800px" align="center" height="30px">
                        <td width="150px">村镇:</td>
                        <td width="150px">年利率</td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen" /></td>
                        <td width="150px">% + </td>
                        <td width=""><input style="width: 150px;" type="text" class="cunzhen1" /></td>
                        <td width="150px">元</td>
                      </tr>
                    </table>
                  </td>
              </tr>
            </table></td>
        </tr>
        
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
    <td background="${rc.contextPath}/images/sctg/buttom_bgs.gif"><img src="${rc.contextPath}/images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_right.gif" width="16" height="17" /></td>
  </tr>
</table>

<script type="text/javascript">

	$(document).ready(function(){
	
	    getCurrentDate();
	    setInput();
	    setOrgCityList();
		
		$("#tktType").change(function() {
		   setInput();
		   setType();
		});
		
		$("#orgCityList").change(function() {
		   setInput();
		   setType();
		});
		
	});
	
	//初始化所有输入框
	function setInput(){
		$("input").not(".btn").val("--");
		$("input").not(".btn").attr("readonly","readonly");
	}
	
	function setInputOnblur(name) {
		countType("guogu",name);
		countType("chengshang",name);countType("waizi",name);countType("nongshang",name);
		countType("nongxin",name);countType("nonghe",name);countType("cunzhen",name);
		
	}
	
	
	//设置交易城市下拉框
	function setOrgCityList() {
		
		$.ajax({
			url: '../../historyprice/baojia/search/minprice/citylist',
			type: 'POST',
			dataType: 'json',
			data: {},
			success: function(result){
				var data = eval(result);
				var orgCityList = data.orgCityList;
				var str = '';
				str = "<option value='1'>全国</option>";
				$("#orgCityList").append(str);
				if (orgCityList != null) { 
					str = '';
					for(var i in orgCityList){
						var orgCity = orgCityList[i];
						if (orgCity.cityid != null && orgCity.name != null) {
							str += "<option value='" + orgCity.cityid + "'>"+ orgCity.name + "</option>";
						}
					}
					$("#orgCityList").append(str);
				}else{
				    alert("今天暂时没有报价城市!");
				}
				setType();
			}
		});
	}
	function setType() {
		$.ajax({
			url: '../../historyprice/search/type',
			type: 'POST',
			data: {},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				var type = $("#tktType").val();
				if (type == 0) {	//纸票
					$(".zhi").show();
					$(".dian").hide();
					showTypePagerData(data);
				} else if(type == 1) {
					$(".dian").show();
					$(".zhi").hide();
					showTypeElecData(data);
				}
				loadData();
			}
		});
	}
	
	//加载今天的最新数据:historyPrice
	function loadData(){
	    var cityId = $("#orgCityList").val();
	    if( cityId == null){
	    alert("没有城市报过价!");
	    return;
	    }
		var type = $("#tktType").val();
		if (type == 0) {
			type1 = 1;
		} else if(type == 1) {
			type1 = 2;
		}
		if (type1 == null) {
			return;
		}
		$.ajax({
			url: '../../historyprice/minprice/searchminprice',
			type: 'POST',
			dataType: 'json',
			data: {'type': type1,'cityId':cityId},
			success: function(result){
				var data = eval(result);
				var historyPriceList = data.historyPriceList;
				if (historyPriceList != null) {
					for(var i in historyPriceList){
						setHistoryPriceData(historyPriceList[i],type1);
						if(type1==2) setInputOnblur(historyPriceList[i].id);
					}
				}
			}
		});
	}
	
	//设置页面的数据(数据在后台组装)
	function setHistoryPriceData(data,type){
		var className = data.id;
		$("." + className + " .guogu").val(data.guogu);
		$("." + className + " .chengshang").val(data.chengshang);
		$("." + className + " .dashang").val(data.dashang);
		$("." + className + " .waizi").val(data.waizi);
		$("." + className + " .nongshang").val(data.nongshang);
		$("." + className + " .nonghe").val(data.nonghe);
		$("." + className + " .nongxin").val(data.nongxin);
		$("." + className + " .cunzhen").val(data.cunzhen);
		if(typeof(className) != "undifind"){
			if(className != "maiDuanPager" && className != "maiDuanElec" && className != "maiDuanElecHalf") {
				$("." + className + " .guogu1").val(data.guogu1);
				$("." + className + " .chengshang1").val(data.chengshang1);
				$("." + className + " .waizi1").val(data.waizi1);
				$("." + className + " .nongshang1").val(data.nongshang1);
				$("." + className + " .nonghe1").val(data.nonghe1);
				$("." + className + " .nongxin1").val(data.nongxin1);
				$("." + className + " .cunzhen1").val(data.cunzhen1);
			}
			if(className == "maxMoney" || className == "midMoney" || className == "minMoney"){
				$("." + className + " .guogu2").val(data.guogu2);
				$("." + className + " .chengshang2").val(data.chengshang2);
				$("." + className + " .waizi2").val(data.waizi3);
				$("." + className + " .nongshang2").val(data.nongshang2);
				$("." + className + " .nonghe2").val(data.nonghe2);
				$("." + className + " .nongxin2").val(data.nongxin2);
				$("." + className + " .cunzhen2").val(data.cunzhen2);
				$("." + className + " .guogu3").val(data.guogu3);
				$("." + className + " .chengshang3").val(data.chengshang3);
				$("." + className + " .waizi3").val(data.waizi3);
				$("." + className + " .nongshang3").val(data.nongshang3);
				$("." + className + " .nonghe3").val(data.nonghe3);
				$("." + className + " .nongxin3").val(data.nongxin3);
				$("." + className + " .cunzhen3").val(data.cunzhen3);
			}
		}
	}
	
	function xiugai() {
	    var cityId = $("#orgCityList").val();
	    var type = $("#tktType").val();
	    if(cityId == null){
	    alert("复制失败，请选择城市!");
	    return;
	    }
		var hpArray = new Array();		//存放要修改的报价
		//大票
		if(type=="0"){
			hpArray = getData("minMoney", hpArray, "small");
			hpArray = getData("midMoney", hpArray, "small");
			hpArray = getData("maxMoney", hpArray, "small");
		}else{
			hpArray = getData("maiDuanElec", hpArray, "big");
			hpArray = getData("maiDuanElecHalf", hpArray, "big");
			//小票
			hpArray = getData("maxMoneyMinDay", hpArray, "small");
			hpArray = getData("maxMoneyMidDay", hpArray, "small");
			hpArray = getData("maxMoneyMaxDay", hpArray, "small");
			hpArray = getData("midMoneyMinDay", hpArray, "small");
			hpArray = getData("midMoneyMidDay", hpArray, "small");
			hpArray = getData("midMoneyMaxDay", hpArray, "small");
			hpArray = getData("minMoneyMinDay", hpArray, "small");
			hpArray = getData("minMoneyMidDay", hpArray, "small");
			hpArray = getData("minMoneyMaxDay", hpArray, "small");
		}
		var priceList = JSON.stringify(hpArray);
		$.ajax({
			url: '../../historyprice/minprice/updateprice',
			type: 'POST',
			dataType: 'json',
			data: {"priceList": priceList, "cityId": cityId},
			success: function(result) {
				var data = eval(result);
				alert(data.msg);
			}
		});
	}
	
	//获取大票的数据 id:该id/class下的数据, obj:将获取到的数据存放的地方, style：所属type(big/small,遍历获取数据)
	function getData(id, array, style){
		if (style == "big") {
			$.each($("." + id + " input"), function(){
				var obj = new Object();
				obj.type2 = 1;		//500以上
				obj.type3 = 1;		//买断
				obj.type4 = 1;		//长三角
				if (id == "maiDuanPager"){
					obj.type6 = 1;	//纸票
				}else if(id == "maiDuanElec") {
					obj.type6 = 2;	//电票
					obj.type7 = 1;	//一年期
				}else if (id == "maiDuanElecHalf") {
					obj.type6 = 2;	//电票
					obj.type7 = 0;	//半年期
				}
				var className = $(this).attr("class");
				var hpId = $(this).attr("hp-id");
				if (hpId != undefined) {
					obj.id = hpId;
				}
				obj = setBigTkt(id, obj, className);
				array.push(obj);
			});
		} else if(style == "small") {
			$.each($("." + id + " input"), function(){
				var obj = new Object();
				obj.type3 = 1;	//买断：现在默认都是买断
				obj.type4 = 1;	//长三角
				var typeStyle = $("#tktType").val();
				if (typeStyle == 0) {					
					obj.type6 = 1;	//纸票
				}else if(typeStyle == 1) {
					obj.type6 = 2;	//电票
					//obj.type7 = 1;	//一年期
				}
				var hpId = $(this).attr("hp-id");
				if (hpId != undefined) {
					obj.id = hpId;
				}
				var className = $(this).attr("class");
				if (className.indexOf("1") < 0 && className.indexOf("2") < 0 && className.indexOf("3") < 0){
					obj = setSmallTkt(id, obj, className);
					array.push(obj);
				}
			});
		}
		return array;
	}
	
	//保存大票对象
	function setBigTkt(id, obj, className) {
		if (className == "guogu") {
			obj.type1 = 1;		//国股
			obj.price = $("." + id + " .guogu").val();
		}else if(className == "chengshang") {
			obj.type1 = 2;		//城商
			obj.price = $("." + id + " .chengshang").val();
		}else if(className == "waizi") {
			obj.type1 = 3;		//外资
			obj.price = $("." + id + " .waizi").val();				
		}else if(className == "nongshang") {
			obj.type1 = 4;		//农商
			obj.price = $("." + id + " .nongshang").val();				
		}else if(className == "nonghe") {
			obj.type1 = 5;		//农合
			obj.price = $("." + id + " .nonghe").val();				
		}else if(className == "nongxin") {
			obj.type1 = 6;		//农信
			obj.price = $("." + id + " .nongxin").val();				
		}else if(className == "cunzhen") {
			obj.type1 = 7;		//村镇
			obj.price = $("." + id + " .cunzhen").val();
		}else if(className == "dashang") {
			obj.type1 = 8;
			obj.price = $("." + id + " .dashang").val();
		}
		return obj;
	}
	
	//保存小票对象
	function setSmallTkt(id, obj, className) {
		if (id == "maxMoneyMinDay") {
			obj.type2 = 2;
			obj.type5 = 1;
		}else if(id == "maxMoneyMidDay"){
			obj.type2 = 2;
			obj.type5 = 2;
		}else if(id == "maxMoneyMaxDay"){
			obj.type2 = 2;
			obj.type5 = 3;
		}else if(id == "midMoneyMinDay"){
			obj.type2 = 3;
			obj.type5 = 1;
		}else if(id == "midMoneyMidDay"){
			obj.type2 = 3;
			obj.type5 = 2;
		}else if(id == "midMoneyMaxDay"){
			obj.type2 = 3;
			obj.type5 = 3;
		}else if(id == "minMoneyMinDay"){
			obj.type2 = 4;
			obj.type5 = 1;
		}else if(id == "minMoneyMidDay"){
			obj.type2 = 4;
			obj.type5 = 2;
		}else if(id == "minMoneyMaxDay"){
			obj.type2 = 4;
			obj.type5 = 3;
		}else if(id == "maxMoney"){
			obj.type2 = 2;
			obj.type5 = 2;
		}else if(id == "midMoney"){
			obj.type2 = 3;
			obj.type5 = 2;
		}else if(id == "minMoney"){
			obj.type2 = 4;
			obj.type5 = 2;
		}
		if (className == "guogu") {
			obj.type1 = 1;		//国股
			obj.price = $("." + id + " .guogu").val();
			obj.price1 = $("." + id + " .guogu1").val();
			obj.price2 = $("." + id + " .guogu2").val();
			obj.matrueprice = $("." + id + " .guogu3").val();
		}else if(className == "chengshang") {
			obj.type1 = 2;		//城商
			obj.price = $("." + id + " .chengshang").val();
			obj.price1 = $("." + id + " .chengshang1").val();
			obj.price2 = $("." + id + " .chengshang2").val();
			obj.matrueprice = $("." + id + " .chengshang3").val();
		}else if(className == "waizi") {
			obj.type1 = 3;		//外资
			obj.price = $("." + id + " .waizi").val();				
			obj.price1 = $("." + id + " .waizi1").val();				
			obj.price2 = $("." + id + " .waizi2").val();				
			obj.matrueprice = $("." + id + " .waizi3").val();
		}else if(className == "nongshang") {
			obj.type1 = 4;		//农商
			obj.price = $("." + id + " .nongshang").val();				
			obj.price1 = $("." + id + " .nongshang1").val();				
			obj.price2 = $("." + id + " .nongshang2").val();				
			obj.matrueprice = $("." + id + " .nongshang3").val();				
		}else if(className == "nonghe") {
			obj.type1 = 5;		//农合
			obj.price = $("." + id + " .nonghe").val();				
			obj.price1 = $("." + id + " .nonghe1").val();				
			obj.price2 = $("." + id + " .nonghe2").val();				
			obj.matrueprice = $("." + id + " .nonghe3").val();				
		}else if(className == "nongxin") {
			obj.type1 = 6;		//农信
			obj.price = $("." + id + " .nongxin").val();				
			obj.price1 = $("." + id + " .nongxin1").val();				
			obj.price2 = $("." + id + " .nongxin2").val();				
			obj.matrueprice = $("." + id + " .nongxin3").val();				
		}else if(className == "cunzhen") {
			obj.type1 = 7;		//村镇
			obj.price = $("." + id + " .cunzhen").val();
			obj.price1 = $("." + id + " .cunzhen1").val();
			obj.price2 = $("." + id + " .cunzhen2").val();
			obj.matrueprice = $("." + id + " .cunzhen3").val();
		}
		return obj;
	}
	
	//动态展现报价类型数据(大票小票、价格、天数等数据):	纸票
	function showTypePagerData(data){
		//隐藏电票表单
		$(".dian").hide();
		var typeList = data.priceTypeList;
		for(var i in typeList){
			var type = typeList[i];
			var type1 = type.type1,type2 = type.type2,type3 = type.type3,type4 = type.type4,type5 = type.type5;
			//只给纸票表单赋值 type2=0
			if(type1 == 1 && type2 == 0 && type3 == 2) { setTypeData("maxMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 1) { setTypeData("midMoneyMinDay", type); }
			else if(type1 == 1 && type2 == 0 && type3 == 0) { setTypeData("minMoneyMinDay", type); }
		}
	}
	
	//动态展现报价类型数据(大票小票、价格、天数等数据):	电票
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
		$("." + id).attr("pt-id", "pt_id" + type.id);
	}
	
	
	//准备计算，开始获取需要的所有数据：obj-对象，style-A/B,className, money-金额, rate-利率
	function countType(name,parentCname) {
         var day = $("." + parentCname).attr("day");				//该属性所在的table中的day的值	--计算时要用到的： 天数
         day = parseFloat(day);
         //用户输入的利率和要加的参数，算出每10W多少钱
         var rate = parseFloat($("." + parentCname + " ." + name).val());
         if (isNaN(rate) || rate == "" || rate == undefined) {
	         rate = 0;
	        }
			countData( name, parentCname, day, rate);
    }
	
	//名称
	function countData(className, parentCname, day, rate) {
	    if(rate != 0) {
	    var countResult =parseFloat( (100000 * day * ((rate / 30) / 1000)).toFixed(2) );
	    $("." + parentCname + " ." + className + "2").val(countResult);
        }
    }
	
	// 获取当前日期
	function getCurrentDate() {
		var d = new Date();
		var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		$("#startDate").html(str);
	}
</script>
</body>
</html>