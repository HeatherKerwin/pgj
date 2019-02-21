[#import "./common/main.ftl" as main]
[#include "./common/setting.ftl"]
[#include "./common/data.ftl"]
[@main.body head=seoMap.homepage]

<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css" />
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/homepage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/right.css"/>

[@main.header currentmenu='1' topindex="1"/]
[@main.right /]
<script type="text/javascript" src="${staticPath}/js/common/jquery.superslide.2.1.1.js"></script>
<style type="text/css">
.my-pl{
	margin-left:10px
}
</style>
<script type="text/javascript">
	var memberId;
	//动态添加pinyou访客代码
	var from = "${from?default('')}";
	if("pinyou" == from){
		var _py = _py || [];
		_py.push(['a', 'Bxs..lqGDxUeDQQG_QBeKEVRbw_']);
		_py.push(['domain','stats.ipinyou.com']);
		_py.push(['e','']);
		-function(d) {
		var s = d.createElement('script'),
		e = d.body.getElementsByTagName('script')[0]; e.parentNode.insertBefore(s, e),
		f = 'https:' == location.protocol;
		s.src = (f ? 'https' : 'http') + '://'+(f?'fm.ipinyou.com':'fm.p0y.cn')+'/j/adv.js';
		}(document);
	}
	
	/**
	 * Sitestatistics表的添加
	 */
	$.cookie("hezuo", from);
	url = window.location.href;

	$.ajax({ 
		type: "post",  
		url : "site/addSitestatistics",  		 
		data: 'from='+from+'&url='+url+'&type=PC', //发给php的数据有两项，分别是上面传来的u和p 
		success: function(data){} 
	});
</script>
<noscript><img src="//stats.ipinyou.com/adv.gif?a=Bxs..lqGDxUeDQQG_QBeKEVRbw_&e=" style="display:none;"/></noscript>

<!--左侧导航定位-->
<div class="pf l30 t210 w120 zi190 tc lh45 c545563 bcwhite bae0e0e0 none" id="leftNavigation">
    <ul id="leftBox">
        <li class="bbe0e0e0 cp bcd43c33 cwhite">
               专业技术支持
        </li>
        <li class="bbe0e0e0 cp">
                票据流程
        </li>
        <li class="bbe0e0e0 cp">
               市场信息
        </li>
        <li class="bbe0e0e0 cp">
                公催查询
        </li>
        <li>
                合作伙伴
        </li>
    </ul>
</div>
<div class="w ha">
    <!--banner-->
    <div class="w h360 pr">
         <!--foucs-->
        <div class="foucs">
            <ul class="lipic">
                [#if banners??]
                [#list banners as banner]
                <li style=" background:url('${imagePath}/${banner.path}') no-repeat center top;"><a href="${banner.url}" target="_blank" class="w h dsb"></a></li>
                [/#list]
                [/#if]
            </ul>
            <div class="num">
                <ul>
                </ul>
            </div>
        </div>
        <!--/foucs-->
        
        <!-- 焦点图上的内容 -->
        <div class="pa w500 h260 t50 l_50 ml-600">
        	<div class="w500 h260 pr clearFloat">
               <ul class="caseHeaderLeft">
                   <li class="caseHeaderLeftItem selected"><a class="caseHeaderCategory" data-id="1">银票询价</a> </li>
                   <li class="caseHeaderLeftItem "><a class="caseHeaderCategory" data-id="2">计算器</a> </li>
                   <li class="caseHeaderLeftItem "><a class="caseHeaderCategory" data-id="3">开票日历</a> </li>
               </ul>
               <div class="pr fl zi10 w400 f13 h" style="background-color: rgba(255,255,255,0.66)">
                   <!--银票询价-->
                   <ul class="caseHeaderDetailItemNew pl10 pr10 clearFloat" data-id="1">
                       <div class="w mt12">
                           <div class="fl w_50 lh24">
                               <div class="fl">票据属性：</div>
                               <select id="type6" class="fl w85 h24 select107 pl10">
                                   <option value="1">纸票</option>
                                   <option value="2" selected="selected">电票</option>
                               </select>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">承兑行类型：</div>
                               <select id="type1" class="fl w85 h24 select107 pl10">
                                   <option value="1" selected="selected">国股</option>
                                   <option value="8">大商</option>
                                   <option value="2">小商</option>
                                   <option value="3">外资</option>
                                   <option value="4">农商</option>
                                   <option value="5">农合</option>
                                   <option value="6">农信</option>
                                   <option value="7">村镇</option>
                               </select>
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl">票据金额：</div>
                               <input id="allmoney" type="text" class="fl w85 h22 f12 ti10 bae0e0e0 br3 validate[required,custom[allprice]]" placeholder="输入金额">
                               <div class="fl ml6 f12">万元</div>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">交易日期：</div>
                               <input id="startDate" type="text" class=" fl w85 h22 f12 tc bae0e0e0 br3" placeholder="2017-10-10" readonly="readonly">
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl">调整天数：</div>
                               <input type="text" id="day" class="fl w85 h22 f12 ti10 bae0e0e0 br3 validate[maxSize[2],custom[integer1]]" placeholder="0">
                               <div class="fl ml6 f12">天</div><input type="hidden" id="day-hidden"/>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">到期日期：</div>
                               <input type="text" id="endDate" class=" fl w85 h22 f12 tc bae0e0e0 br3" placeholder="2011-11-11" readonly="readonly">
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl">计息天数：</div>
                               <div class="fl f12"><span id="days" class="f14" >183</span>天</div>
                           </div>
                           <div class="fl w_50 lh24" id="years">
                               <div class="fl w80 ml15">承兑期限：</div>
                               <select id="limit" class="fl w85 h24 select107 pl10">
                                   <option value="0">半年期</option>
                                   <option value="1" selected="selected">一年期</option>
                               </select>
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w125 lh27 bc mt44">
                           <input type="button" class="w125 h27 bce84c3d b0 br5 cwhite tc f14 cp" value="询价" onclick="xunjia();">
                       </div>
                       <div class="w mt13 tc lh18">
                           <div class="fl w_50">
                               <div ><span id="showinfo1">每10万贴息</span>(买断)</div>
                               <div class="cd43c33 f14" id="showinfo2">--</div>
                           </div>
                           <div class="fl w_50">
                               <div class="ml10" ><span id="showinfo3">您的贴现利息</span>(买断)</div>
                               <div class="cd43c33 f14" id="showinfo4">--</div>
                           </div>
                       </div>
                   </ul>
                   <!--计算器-->
                   <ul class="caseHeaderDetailItemNew pl10 pr10 clearFloat" data-id="2" style="display:none">
                       <div class="w mt12">
                           <div class="fl w_50 lh24">
                               <div class="fl">票据属性：</div>
                               <select class="fl w85 h24 select107 pl10" id="c_type1">
                                   <option value="1">纸票</option>
                                   <option value="2" selected="selected">电票</option>
                               </select>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">承兑行类型：</div>
                               <select class="fl w85 h24 select107 pl10" id="c_type2">
                                   <option value="1" selected="selected">国股</option>
                                   <option value="8">大商</option>
                                   <option value="2">小商</option>
                                   <option value="3">外资</option>
                                   <option value="4">农商</option>
                                   <option value="5">农合</option>
                                   <option value="6">农信</option>
                                   <option value="7">村镇</option>
                               </select>
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl w65">总金额：</div>
                               <input id="c_allmoney" type="text" class="fl w85 h22 f12 ti10 bae0e0e0 br3 validate[required,custom[allprice]]" placeholder="输入金额">
                               <div class="fl ml6 f12">万元</div>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">交易日期：</div>
                               <input type="text" id="c_startDate" class="fl w85 h22 f12 tc bae0e0e0 br3" placeholder="2011-11-11" readonly="readonly" >
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl ">调整天数：</div>
                               <input type="text" id="c_day" class="fl w85 h22 f12 ti10 bae0e0e0 br3 validate[maxSize[2],custom[integer1]]" placeholder="0">
                               <div class="fl ml6 f12">天</div><input type="hidden" id="c_day-hidden"/>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">到期日期：</div>
                               <input type="text" id="c_endDate" class="fl w85 h22 f12 tc bae0e0e0 br3" placeholder="2010-77-88" readonly="readonly" >
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div id="c_years" class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl ">计息天数：</div>
                               <div class="fl f12"><span class="f14" id="c_jxts">0</span>天</div>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">承兑期限：</div>
                               <select class="fl w85 h24 select107 pl10" id="c_acceptTime">
                                   <option value="0" >半年期</option>
                                   <option value="1" selected="selected">一年期</option>
                               </select>
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10">
                           <div class="fl w_50 lh24">
                               <div class="fl pr3">票据价格</div>
                               <div class="fl ml8 yuexi" >月息</div>
                               <div class="none fl ml8 nianxi" >年息</div>
                               <div class="none fl ml8 meishiwan" >每十万扣</div>
                               <input type="text" id="rate" class="yuexi fl w60 h22 f12 ti8 bae0e0e0 br3 ml8 validate[custom[jisuan]]" placeholder="00.00"><div class="yuexi fl ml6 f12">‰ </div>
                               <input type="text" id="rateYear" class="none nianxi fl w60 h22 f12 ti8 bae0e0e0 br3 ml8 validate[custom[jisuan]]" placeholder="0.00"><div class=" none nianxi fl ml6 f12">%</div>
                               <input type="text" id="rate2" class="none meishiwan fl w60 h22 f12 ti8 bae0e0e0 br3 ml8 validate[custom[tennumber]]" placeholder="0.00"><div class="none meishiwan fl fl ml6 f12">元</div>
                           </div>
                           <div class="fl w_50 lh24">
                               <div class="fl w80 ml15">手续费：</div>
                               <input type="text" id="c_poundage" class="fl w60 h22 ti10 bae0e0e0 br3 validate[custom[integer1]]" placeholder="">
                               <div class="fl ml6 f12">元</div>
                           </div>
                           <div class="cb"></div>
                       </div>
                       <div class="w mt10 lh27 tc">
                               <div class="fl w_50">
                                   <input type="button" class="w125 h27 bcwhite cd43c33 bad43c33 br5 cp" onclick="c_clear();" value="清空">
                               </div>
                               <div class="fl w_50">
                                   <input class="w125 h27 bcd43c33 bad43c33 br5 cwhite cp" onclick="c_jisuan()" value="计算" type="button">
                               </div>
                               <div class="cb"></div>
                           </div>
                       <div class="w mt13 tc lh18">
                           <div class="fl w_50">
                               <div class="">贴现利息(元)</div>
                               <div class="cd43c33" id="c_txlx">--</div>
                           </div>
                           <div class="fl w_50">
                               <div class="ml10">贴现金额(万元)</div>
                               <div class="cd43c33" id="c_txje">--</div>
                           </div>
                       </div>
                   </ul>
                   <!--开票日历-->
                   <ul class="caseHeaderDetailItemNew pl14 pr14 clearFloat" data-id="3" style="display:none">
                       <div class="w h30 pr">
                           <div class="w h30 lh30 pa b0 f12 left">
                               <a class="w121 tc cd43c33 dsib h30 oln" id="zhiHalf" href="javascript:void(0);" tabindex="7">纸票半年期</a>
                               <a class="w121 tc c2d2d2d dsib h30 oln" id="dianHalf" href="javascript:void(0);" tabindex="8">电票半年期</a>
                               <a class="w121 tc c2d2d2d dsib h30 oln" id="dianYear" href="javascript:void(0);" tabindex="8">电票一年期</a>
                               <div class="pa w122 left b-2 bbd43c33" id="rili_bottom" ></div>
                               <div class="cb"></div>
                           </div>
                       </div>
                       <div class="w h220 mt3" id="time1">
                           <img src="${imagePath}/website/homepage/zhih.jpg" width="372" height="220" id="calendar1">
                       </div>
                       <div class="w h220 mt3 none" id="time2">
                           <img src="${imagePath}/website/homepage/dianh.jpg" width="372" height="220" id="calendar2">
                       </div>
                       <div class="w h220 mt3 none" id="time3">
                           <img src="${imagePath}/website/homepage/diany.jpg" width="372" height="220" id="calendar3">
                       </div>
                   </ul>
               </div>
          	</div>
        </div>
    </div>
    <div class="w h390 bcwhite">
	    <!--最新公告+成交量-->
	    <div class="mpHomeNoticeWrap" id="J_mpHomeNoticeWrap">
	        <span class="mpHomeNoticeTip">最新公告</span>
	        <ul class="mpHomeNoticeList">
	            <li class="mpHomeNoticeItem" style="max-width: 500px"><a href="javascript:void(0);"><span data-time="1512708892829"></span> 周一到周五，票据管家为您嘘寒问暖，让您的贴现更容易</a></li>
	        	<li class="mpHomeNoticeItemMore ml20"><a href="javascript:void(0);" class="mpNew pr50"></a></li>
	            <li class="mpHomeNoticeItemMore"><a href="javascript:void(0);"></a></li>
	        </ul>
	    </div>
	    <!-- 特点 -->
	    <div class="mpHomeServiceList" id="J_mpHomeServiceList">
	        <div class="mpHomeServiceListWrap">
	            <!--专业-->
	            <a class="mpHomeServiceItem none" href="${basePath }/index/introduce#zyCon" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img src="https://img.utiexian.com/website/homepage/171219/majorIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">专业</h3>
	                <div class="mpHomeServiceDesc">
	                    全面的用户核准机制给平台提前设好门槛
	                </div> <span class="mpHomeServiceLink">查看详情</span> </a>
	            <!--安全-->
	            <a class="mpHomeServiceItem none" href="${basePath }/index/introduce#aqCon" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img src="https://img.utiexian.com/website/homepage/171219/securityIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">安全</h3>
	                <div class="mpHomeServiceDesc">
	                    靠谱的系统管理运作为贴现再添一道保障
	                </div> <span class="mpHomeServiceLink">查看详情</span> </a>
	            <!--便捷-->
	            <a class="mpHomeServiceItem none" href="${basePath }/index/introduce#bjCon" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img src="https://img.utiexian.com/website/homepage/171219/convenientIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">便捷</h3>
	                <div class="mpHomeServiceDesc">
	                    高效的一键下单模式贴现比想象中更容易
	                </div> <span class="mpHomeServiceLink">查看详情</span> </a>
	            <!--新手指南-->
	            <a class="mpHomeServiceItem none" href="${basePath }/piaojuxueyuan/index?type=4" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img src="https://img.utiexian.com/website/homepage/171219/guideIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">新手指南</h3>
	                <div class="mpHomeServiceDesc">
	                    快速学习，熟练操作电票交易，了然于心
	                </div> <span class="mpHomeServiceLink">查看详情</span> </a>
	            <!--社区交流-->
	            <a class="mpHomeServiceItem none" href="https://bbs.utiexian.com" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img src="https://img.utiexian.com/website/homepage/171219/CommunityIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">社区交流</h3>
	                <div class="mpHomeServiceDesc">
	                    自由交流，分享见解知识共享，经验互通
	                </div>
	                <span class="mpHomeServiceLink">查看详情</span> </a>
	                
				<!--我要报价-->
	            <a class="mpHomeServiceItem" id="inquiryItem" href="javascript:checkAccount(1,'/price/price');" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img class="ImageBefor" src="https://img.utiexian.com/website/homepage/171219/180403/wybjIconBf.png" alt="" />
	                    <img class="ImageHover" src="https://img.utiexian.com/website/homepage/171219/180403/wybjIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">我要报价</h3>
	                <span class="mpHomeServiceLink">我要报价</span> </a>
                <!--我要收票-->
	            <a class="mpHomeServiceItem" id="receiveItem" href="javascript:checkAccount(1,'/hall/receiveOrderall1');" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img class="ImageBefor" src="https://img.utiexian.com/website/homepage/171219/180403/wyspIconBf.png" alt="" />
	                    <img class="ImageHover" src="https://img.utiexian.com/website/homepage/171219/180403/wyspIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">我要收票</h3>
	                <span class="mpHomeServiceLink">我要收票</span> </a>
                <!--我要出票-->
	            <a class="mpHomeServiceItem mpHomeServiceSpecial" id="discountItem" href="javascript:checkAccount(0,'/discountrecord/discount_yp1');" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img class="ImageBefor" src="https://img.utiexian.com/website/homepage/171219/180403/wycpIconBf.png" alt="" />
	                    <img class="ImageHover" src="https://img.utiexian.com/website/homepage/171219/180403/wycpIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">我要出票</h3>
	                <span class="mpHomeServiceLink">我要出票</span> </a>
                <!--新手指南-->
	            <a class="mpHomeServiceItem" id="guideItem" href="${basePath}/piaojuxueyuan/index?type=4" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img class="ImageBefor" src="https://img.utiexian.com/website/homepage/171219/180403/xsznIconBf.png" alt="" />
	                    <img class="ImageHover" src="https://img.utiexian.com/website/homepage/171219/180403/xsznIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">新手指南</h3>
	                <span class="mpHomeServiceLink">新手指南</span> </a>
                <!--票据市场-->
	            <a class="mpHomeServiceItem" id="paperMarketItem" href="${basePath}/paperMarket" target="_blank">
	                <div class="mpHomeServiceImage">
	                    <img class="ImageBefor" src="https://img.utiexian.com/website/homepage/171219/180403/pjscIconBf.png" alt="" />
	                    <img class="ImageHover" src="https://img.utiexian.com/website/homepage/171219/180403/pjscIcon.png" alt="" />
	                </div> <h3 class="mpHomeServiceTitle">票据市场</h3>
	                <span class="mpHomeServiceLink">票据市场</span> </a>
	        </div>
	    </div>
    </div>

     <div id="leftForBox">
	<!--专业技术支持-->
    <div class="w flex flex-justify-center h635" id="jszcNav">
        <div class="w1200 flex-row flex-direction-column tc">
            <div class="f30 c4c4c4c mt50">专业技术支持</div>
            <div class="flex-row w510 flex-direction-column pr bc">
	            <div class="flex-row w510 flex-direction-row flex-justify-space-between bb1_f74d52 f16 lh30  mt45">
	                <div class="w148 shiborBtn cp cd43c33">同业拆放利率</div>
	                <div class="w148 zsBtn cp">票据指数</div>
	            </div>
            	<div class="pa left bottom w148 bb2_f74d52" id="zyjszc_bottom" ></div>
            	<div class="cb"></div>
            </div>
            
            <div class="flex-row w flex-direction-column">
                <div class="flex-row flex-direction-row shiborCon">
                    <div class="flex-col-6 tl">
                        <div class="pl50 pr50 pt90">
                            <div class="f20 c4c4c4c">同业拆放利率</div>
                            <div class="c808080 mt30">Shibor报价银行团现由18家商业银行组成。报价银行是公开市场一级交易商或外汇市场做市商，在中国货币市场上人民币交易相对活跃、信息披露比较充分的银行。中国人民银行成立Shibor工作小组，依据《上海银行间同业拆放利率(Shibor)实施准则》确定和调整报价银行团成员、监督和管理Shibor运行、规范报价行与指定发布人行为。</div>
                            <input type="button" value="查看更多" onclick="window.location.href='${basePath}/homepage/tool/shibor'" class="w125 h35 lh35 cwhite bce84c3d bae84c3d br3 mt50 cp">
                        </div>
                    </div>
                    <div class="flex-col-6">
                        <div class="mt30 ml100 mr69 bcwhite flex-direction-column f14 lh40" style="box-shadow: 1px 3px 3px 3px #faf9f9;">
                            <div class="flex-row w flex-direction-row f18 c4c4c4c">
                                <div class="flex-col-4">期限</div>
                                <div class="flex-col-4">shibor(%)</div>
                                <div class="flex-col-4">涨跌（BP)</div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">0/N
                                </div>
                                <div class="flex-col-4">${shibor.shibor1 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown1 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">1W
                                </div>
                                <div class="flex-col-4">${shibor.shibor2 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown2 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">2W
                                </div>
                                <div class="flex-col-4">${shibor.shibor3 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown3 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">3W
                                </div>
                                <div class="flex-col-4">${shibor.shibor4 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown4 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">1M
                                </div>
                                <div class="flex-col-4">${shibor.shibor5 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown5 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">3M
                                </div>
                                <div class="flex-col-4">${shibor.shibor6 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown6 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row bbe0e0e0">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">6M
                                </div>
                                <div class="flex-col-4">${shibor.shibor7 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown7 }
                                </div>
                            </div>
                            <div class="flex-row w flex-direction-row">
                                <div class="flex-col-4 c2d2d2d">
                                    <img src="https://img.utiexian.com/website/homepage/171219/rIcon.png" alt="" class="mr10">9M
                                </div>
                                <div class="flex-col-4">${shibor.shibor8 }</div>
                                <div class="flex-col-4 [#if shibor.updown1?index_of('-')!=-1]c7790fe[#else]cfa3c3c[/#if]">
                                    <img src="https://img.utiexian.com/website/homepage/171219/[#if shibor.updown1?index_of('-')!=-1]upIcon[#else]downIcon[/#if].png" alt="" class="mr10">${shibor.updown8 }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="flex-row flex-direction-row pjzsCon none" >
                 	<div class="flex-col-6 tl">
                         <div class="pl50 pr50 pt90">
                             <div class="f20 c4c4c4c">票据指数</div>
                             <div class="c808080 mt30">票据管家根据平台和报价情况和票据市场价格，通过科学的计算方法，得到每日一个市场价格，以国股为准，统计该数值的变化情况，用以表现每日的票据价格的变化规律，称之为票据指数。</div>
                         </div>
                     </div>
                     <div class="flex-col-6">
						<div class="pt30 pl50 pr69">
							<div class="flex-row flex-direction-row-reverse w">
			                    <ul class="flex-row flex-direction-row tc lh34">
			                        <li id="one" class="w80 bce84c3d bad43c33 cwhite cp" >一个月</li>
			                        <li id="three" class="w80 bae0e0e0 cp" >三个月</li>
			                        <li id="half" class="w80 bae0e0e0 cp" >半年</li>
			                        <li id="year" class="w80 bae0e0e0 cp" >一年</li>
			                    </ul>
		                    </div>
			                <div class="mt16 w bc bcwhite">
			                    <div id="container" style="min-width:480px;height:320px" class="mt6"></div>
			                </div>
			            </div>
                    </div>
                 </div>
            </div>
        </div>
    </div>
    <!--票据流程-->
    <div class="w flex flex-justify-center h347" id="pjlcNav" style="background: url('https://img.utiexian.com/website/homepage/171219/pjlcBg.png') center no-repeat;background-size:100% 100%">
         <div class="w1200 flex-row flex-direction-column tc">
             <div class="f30 cwhite mt50">票据流程</div>
             <img src="https://img.utiexian.com/website/homepage/171219/pjlcCon.png" alt="" class="mt50">
         </div>
    </div>
	<!--市场信息-->
    <div class="w flex flex-justify-center h610 bcwhite" id="scxxNav">
        <div class="w1200 flex-row flex-direction-column tc">
            <div class="f30 c4c4c4c mt50">市场信息</div>
            <div class="flex-row w flex-direction-row mt45">
                <div class="flex-col-6 tl">
                    <div class="pl30 pr50 ">
                    	<img src="${imagePath}/${news.pic}" alt="" width="500" height="240">
                    	<a class="f20 c4c4c4c" href="${news.url}">${news.title}</a>
                		<div class="c808080 mt30">${news.abstracts}</div>
                        <a href="${basePath}/news/index" class="w125 h35 lh35 cwhite bce84c3d bae84c3d br3 mt50 cp dsb tc">查看更多</a>
                    </div>
                </div>
                <div class="flex-col-6">
                     <ul>
	                	 [#if newsList??]
		                 [#list newsList as new]
                         <li class="pl16 mb40">
                         	<a href="${basePath}/news/detail/${new.id}" class="flex-row flex-direction-row ">
                             <div class="flex-direction-column w100 h100 bcf9f9f9 bae0e0e0 tc c808080">
                                 <div class="f48">${new.publishtime?string("dd") }</div>
                                 <div class="f20">${new.publishtime?string("yyyy-MM") }</div>
                             </div>
                             <div class="flex-direction-column w450 ml20 tl">
                                 <div class="f16 c2d2d2d">${new.titleShow}</div>
                                 <div class="mt20 f14 oh toe nowrap h60 lh30 c808080">${new.contentShow}</div>
                             </div>
                             </a>
                         </li>
                         [/#list]
               			 [/#if]
                     </ul>
                 </div>
            </div>
        </div>
    </div>
        <!--公催查询-->
    <div class="w flex flex-justify-center h584" id="gccxNav">
        <div class="w1200 flex-row flex-direction-column tc">
            <div class="f30 c4c4c4c mt50">公催查询</div>
            <div class="flex-row flex-direction-column bcwhite pl20 pr20 pt40 pb40 tc bae0e0e0 mt50" style="box-shadow: 1px 5px 10px #d6d6d6">
                <div class="flex-row flex-direction-row cd43c33 f17 h90">
                    <div class="flex-col-3 flex-direction-column h90"><img src="https://img.utiexian.com/website/homepage/171219/invoiceIcon.png" alt="汇票票号" width="56" height="56">
                        <div class="mt13">汇票票号</div>
                    </div>
                    <div class="flex-col-3 flex-direction-column h90"><img src="https://img.utiexian.com/website/homepage/171219/companyIcon.png" alt="公司名称" width="56" height="56">
                        <div class="mt13">公司名称</div>
                    </div>
                    <div class="flex-col-3 flex-direction-column h90"><img src="https://img.utiexian.com/website/homepage/171219/courtIcon.png" alt="法院名称" width="56" height="56">
                        <div class="mt13">法院名称</div>
                    </div>
                    <div class="flex-col-3 flex-direction-column h90"><img src="https://img.utiexian.com/website/homepage/171219/timeIcon.png" alt="时间" width="56" height="56">
                        <div class="mt13">时间</div>
                    </div>
                </div>
                <ul class="lh30 f16 mt16">
	                [#if gongcuiList??]
	                [#list gongcuiList as gg]
                    <li class="flex-row flex-direction-row w h30">
                        <div class="flex-col-3">${gg.gongcuinumber}</div>
                        <div class="flex-col-3">${gg.gongcuimember}</div>
                        <div class="flex-col-3">${gg.fayuan}</div>
                        <div class="flex-col-3">${gg.gongcuidate?string("yyyy-MM-dd")}</div>
                    </li>
                    [/#list]
                	[/#if]
                </ul>
            </div>
            <input type="button" value="汇票查询" onclick="window.location.href='${basePath}/homepage/tool/gongcui'" class="w125 h35 lh35 cd43c33 bcn bae84c3d br3 mt35 cp bc">
        </div>
    </div>
    <!--合作伙伴-->
    <div class="w flex flex-justify-center h480" id="hzhbNav" style="background: url('https://img.utiexian.com/website/homepage/171219/featureBg.png') center no-repeat;background-size:100% auto">
        <div class="w1200 flex-row flex-direction-column tc">
            <div class="f30 c4c4c4c mt50 w tc">合作伙伴</div>
            <div class="flex-row w flex-wrap mt30">
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.icbc.com.cn" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/gsBankIcon.png" width="222" height="61" alt="中国工商银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.ccb.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/jsBankIcon.png" width="222" height="61" alt="中国建设银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.bankcomm.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/jtBankIcon.png" width="222" height="61" alt="中国交通银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.abchina.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/nyBankIcon.png" width="222" height="61" alt="中国农业银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.cmbc.com.cn" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/msBankIcon.png" width="222" height="61" alt="中国民生银行">
            	</a>
            	
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.cebbank.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/gdBankIcon.png" width="222" height="61" alt="中国光大银行" class="dsib">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.citicbank.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/zxBankIcon.png" width="222" height="61" alt="中信银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.bankofdl.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/dlBankIcon.png" width="222" height="61" alt="大连银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://bank.pingan.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/paBankIcon.png" width="222" height="61" alt="平安银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.gdnybank.com" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/gdnyBankIcon.png" width="222" height="61" alt="广东南粤银行">
            	</a>
            	
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.cibfintech.com/ph/ph-digit.html" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/xysjBankIcon.png" width="222" height="61" alt="兴业数金">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="javascript:void(0)">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/pxwhczBankIcon.png" width="222" height="61" alt="盘县万和村镇银行">
            	</a>
            	<a class="flex-row cp w240 h90 flex-justify-center flex-alignItems-center" href="http://www.boimc.com.cn" target="_blank">
            		<img src="https://img.utiexian.com/website/homepage/171219/bank/nmgBankIcon.png" width="222" height="61" alt="内蒙古银行">
            	</a>
            </div>
        </div>
    </div>
    </div>
</div>
<script type="text/javascript" src="${jsPath}/index.js"></script>
<script type="text/javascript" src="${jsPath}/clickCount.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${pluPath}/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${pluPath}/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="${comPath}/md5.js"></script>
<script type="text/javascript" src="${staticPath}/js/common/jquery.superslide.2.1.1.js"></script>


<script type="text/javascript">

//左侧滑动
var leftNavigation =$("#leftNavigation");
var leftForBox = $("#leftForBox");
var leftBoxChild = $("#leftBox").children();//li
var leftForBoxChild = leftForBox.children();
window.onscroll = function(){
    var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
    if(scrollTop >= 500){
        leftNavigation.removeClass("none");

    }else {
        leftNavigation.addClass("none");
    }

    //当导航与相应文档接触的时候自动切换
    for(var i=0;i<leftBoxChild.length;i++){
        if( scrollTop >= leftForBoxChild[i].offsetTop){
            for(var j=0;j<leftBoxChild.length;j++){
            	leftBoxChild.eq(j).removeClass("bcd43c33 cwhite");
            }
            leftBoxChild.eq(i).addClass("bcd43c33 cwhite");
        }
    }
};
/*点击跳转到指定位置*/
for(var i=0;i<leftBoxChild.length;i++){
    var interval;
    leftBoxChild[i].index = i;
    leftBoxChild[i].onclick = function(){
        var self = this;
        clearInterval(interval);
        interval = setInterval(function(){
            if(document.documentElement.scrollTop <=leftForBoxChild[self.index].offsetTop){
            	document.documentElement.scrollTop += 40;
            	document.documentElement.scrollTop = leftForBoxChild[self.index].offsetTop;
                clearInterval(interval);
            }else{
            	document.documentElement.scrollTop /= 1.1;
                if(document.documentElement.scrollTop <=leftForBoxChild[self.index].offsetTop){
                	document.documentElement.scrollTop = leftForBoxChild[self.index].offsetTop;
                    clearInterval(interval);
                }
            }
        },40);
    };
}
//最新公告区域，初始页面时‘我要出票’模块默认被选中，鼠标触发同层级模块之后清除默认
var flag = true;
function once() {
    if (flag) {
    	$(".mpHomeServiceItem").hover(function(){  
    		$("#discountItem").removeClass("mpHomeServiceSpecial");  
        },function(){  
        	console.log("已清除默认样式");
        }) 
        flag = false;
    } else {
        return;
    }
}
$(".mpHomeServiceList").mouseleave(function(){
	$("#discountItem").addClass("mpHomeServiceSpecial");
});

var from = '';
$(document).ready(function() {
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid = sUserAgent.match(/android/i) == "android";
	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	//document.writeln("您的浏览设备为：");
	if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
		document.location = 'https://wap.utiexian.com';
	}
	from = getParam('from');
	isLogin();
	
	once();
	//日历
	$('#zhiHalf').click(function() {
		$('#dianHalf').removeClass("cd43c33").addClass('c2d2d2d');
		$('#zhiHalf').removeClass("c2d2d2d").addClass('cd43c33');
		$('#dianYear').removeClass("cd43c33").addClass('c2d2d2d');
		$('#rili_bottom').animate({
			left : '0px',
			width : '123px'
		});
		$("#time2").addClass("none");
		$("#time3").addClass("none");
		$("#time1").removeClass("none");
	});
	$('#dianHalf').click(function() {
		$('#dianHalf').removeClass("c2d2d2d").addClass('cd43c33');
		$('#zhiHalf').removeClass("cd43c33").addClass('c2d2d2d');
		$('#dianYear').removeClass("cd43c33").addClass('c2d2d2d');
		$('#rili_bottom').animate({
			left : '122px',
			width : '123px'
		});
		$("#time1").addClass("none");
		$("#time3").addClass("none");
		$("#time2").removeClass("none");
	});
	$('#dianYear').click(function() {
		$('#dianHalf').removeClass("cd43c33").addClass('c2d2d2d');
		$('#zhiHalf').removeClass("cd43c33").addClass('c2d2d2d');
		$('#dianYear').removeClass("c2d2d2d").addClass('cd43c33');
		$('#rili_bottom').animate({
			left : '244px',
			width : '123px'
		});
		$("#time2").addClass("none");
		$("#time1").addClass("none");
		$("#time3").removeClass("none");
	});
	if (getParam1("a") == '0') {
		$('#dianHalf').trigger('click');
	}
	
	/* 一个月 */
    $('#one').click(function() {
		$('#one').removeClass("bae0e0e0").addClass('bce84c3d bad43c33');
		$('#three').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#half').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#year').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#one').addClass("cwhite");
		$('#three').removeClass("cwhite");
		$('#half').removeClass("cwhite");
		$('#year').removeClass("cwhite");
		piaoju(1);
 	});
    /* 三个月 */
    $('#three').click(function() {
		$('#three').removeClass("bae0e0e0").addClass('bce84c3d bad43c33');
		$('#one').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#half').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#year').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#three').addClass("cwhite");
		$('#one').removeClass("cwhite");
		$('#half').removeClass("cwhite");
		$('#year').removeClass("cwhite");
		piaoju(3);
 	});
    /* 半年 */
    $('#half').click(function() {
		$('#half').removeClass("bae0e0e0").addClass('bce84c3d bad43c33');
		$('#one').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#three').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#year').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#half').addClass("cwhite");
		$('#one').removeClass("cwhite");
		$('#three').removeClass("cwhite");
		$('#year').removeClass("cwhite");
		piaoju(6);
 	});
    /* 一年 */
    $('#year').click(function() {
		$('#year').removeClass("bae0e0e0").addClass('bce84c3d bad43c33');
		$('#one').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#three').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#half').removeClass("bce84c3d bad43c33").addClass('bae0e0e0');
		$('#year').addClass("cwhite");
		$('#one').removeClass("cwhite");
		$('#three').removeClass("cwhite");
		$('#half').removeClass("cwhite");
		piaoju(12);
 	});
	loaddate();
	
	initPinyou();//全站访客代码（品友）
	
	/*关闭弹窗*/
	$(".closeCouponBtn").click(function () {
	    $("#redWindow").addClass("none");
	    $("#noviceWindow").addClass("none");
	});
	
	/*点击右侧红包悬浮按钮弹出新手红包*/
	$(".rightIcon").click(function () {
	    $("#redWindow").removeClass("none");
	    $("#noviceWindow").removeClass("none");
		var url = "${bootAppPath}/coupon/register/get";
		var params = {memberId:memberId};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$("#couponNum").html(data.data.data);
			}
		}
	});

	/**
	* 查看红包列表
	*/
	$("#noviceWindow").click(function(){
		location.href = "${basePath }/bisicmessage/redPackets"
	});
});

	/**
 	* 加载企业交易中的总数
 	*/
 	function loadDiscountCount(){
	 	if(memberId == null || memberId == "")return;
	 	var url = "${bootAppPath}/order/get/count";
	 	var params = {memberId:memberId};
	 	var data = bootPost(url,params);
	 	if(data != null){
	 		if(data.data.response == 'success'){
	 			if(data.data.data != null && data.data.data.count >0){
					$(".bns_orderRemind").removeClass("none");
					$(".bnsCount").html(data.data.data.count);
	 			}
			}
	 	}
 	};
 
 	/**
  	* 加载机构交易中的总数
  	*/
	function loadDistributeCount(){
		if(memberId == null || memberId == "")return;
		var url = "${bootAppPath}/dispatch/get/count";
		var params = {memberId:memberId};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				if(data.data.data != null && data.data.data.count >0){
					$(".org_orderRemind").removeClass("none");
					$(".orgCount").html(data.data.data.count);
				}
			}
		}
	};
  
  	/**
   	* 登录执剑人
   	*/
	function openCib(){
	   	if(memberId == null || $.trim(memberId) == "")return;
		var params = {memberId:memberId};
		var url = "${bootAppPath}/cib/corp/query";
		var res = bootPost(url,params);
		if(res!=null){
			var data = res.data;
			if(data.response == "success"){
				if(data.data.cib != null && data.data.cib.type == 1){
					$(".openOrgCib").removeClass("none");
					$("#openOrgCib").attr("href",data.data.login_url);
					
					$(".openBnsCib").removeClass("none");
					$("#openBnsCib").attr("href",data.data.login_url);
				}else if(data.data.cib != null && data.data.cib.type == 0){
					$(".openBnsCib").removeClass("none");
					$("#openBnsCib").attr("href",data.data.login_url);
				}
			}
		}
	};

var _py = _py || [];
function initPinyou(){
	if("pinyou" == from){//添加pinyou访客代码
		_py.push(['a', 'Bxs..lqGDxUeDQQG_QBeKEVRbw_']);
		_py.push(['domain','stats.ipinyou.com']);
		_py.push(['e','']);
		-function(d) {
		var s = d.createElement('script'),
		e = d.body.getElementsByTagName('script')[0]; e.parentNode.insertBefore(s, e),
		f = 'https:' == location.protocol;
		s.src = (f ? 'https' : 'http') + '://'+(f?'fm.ipinyou.com':'fm.p0y.cn')+'/j/adv.js';
		}(document);
	}
}

/**
 * 验证登录
 */
function isLogin(){
	$.ajax({
		url:"${basePath}/islogin",
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.member!=null){
				enableBootAuth(data.member);
				loadRedPackets(data.member.id);
				memberId = data.member.id;
				
				loadDiscountCount();
				loadDistributeCount();
				openCib();
				piaoju(1);
				var str = "您好，欢迎来到票据管家";
				if(data.member.username!=null){
					str = "您好，"+ data.member.username +"，欢迎来到票据管家";
					recordOpenAccountState(1);
					recordOpenAccountState(0);
				}else{
					localStorage["ORGSTATE"] = '';
					localStorage["BNSSTATE"] = '';
					str = "您好，"+ hideMobile(data.member.mobile) +"欢迎来到票据管家";
				}
				$("#member_name").text(str);
				$("#has_member").show();
				$("#null_member").hide();
				$("#coupon").show();
				if(data.message!=null){
					$("#message").text(data.message);
				}
			}else{
				isNoPwdLogin();
				$("#null_member").show();
			}
		}
	});
}

/**
* 判断红包是否加载
*/
function loadRedPackets(memberId){
	var url = "${bootAppPath}/coupon/register/init";
	var params = {memberId:memberId};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			$("#coupon").removeClass("none");
		}
	}
};

/**
 * 验证是否密码登录
 */
function isNoPwdLogin(){
	var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for(var i = 0; i < arrCookie.length; i++){
		var arr = arrCookie[i].split("=");
        if("nopwdlogin" == arr[0]){
        	var params = arr[1].replace(/"/g, "").split(",");
			var mobile = params[0];
			var pwd = params[1];
           	if(memberId == null || memberId == ""){
				$.ajax({
					url:"${basePath}/member/nopwdlogin",
	       			type:"POST",
	       			data:{"mobile":mobile,"pwd":pwd},
	       			dataType:"json",
	       			success:function(data){
	       				if(data.response == "success"){
	       					location.href = "index";
	       				}else{
	       					
	       				}
	       			}
	       		});
           }
        }
    }
};

/** 
 * 开启请求BOOT登录验证
 */
function enableBootAuth(m){
	var _USERNAME = m.mobile;
	var _PASSWORD = hex_md5(m.mobile+'SIGN:@UTIEXIAN@50965066'+m.id);
	$(document).ajaxSend(function(e, xhr, options) {
		var _add = "_USERNAME=" + _USERNAME + "&source=PC&_PASSWORD=" + _PASSWORD;
		var _old = options.data;
		if($.trim(_old)==""){
			options.data = _add;
		}else{
			options.data += "&" + _add;
		}
	});
	$("form").append('<input type="hidden" name="_USERNAME" value="'+ _USERNAME +'" data-desc="登录校验"/>');
	$("form").append('<input type="hidden" name="_PASSWORD" value="'+ _PASSWORD +'" data-desc="登录校验"/>');
	$("form").append('<input type="hidden" name="source" value="PC" data-desc="标识来源"/>');
}
/**
 * @param type 0出票方   1资方（收票方）
 */
 var bool = false;
 function checkAccount(type,path){
		var url = '${bootAppPath}/orginfo/rz';
		if(memberId == null || memberId == ""){
			if(path != null && path != ""){
				location.href = "${basePath}/login";
			}
		}
		var params = {memberId:memberId,type:type};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				var active = data.data;
				var cib = active.cib;
				var cibCheckState;
				if(active.info!=null){
					cibCheckState = active.info.cibCheckState;
				}
				if(path!=''){
					if(cibCheckState != 'PASS' && cib.status != 2){//没开户
						var map = new Map();
						map.put("_PAGE", "price/renzheng");//必传
						map.put("role", type);
						_OPENPAGE_FORM(map);
					}else{//已开户
						var map = new Map();
						map.put("_PAGE", path);//必传
						map.put("role", type);
						map.put("orgId", active.info.orgId);
						
						_OPENPAGE_FORM(map);
					}
				}else{
					if((cibCheckState != "PENDING"&&cibCheckState != "PASS")||cibCheckState == "NOPASS"){//orgInfo的审核状态
						if(cibCheckState == "NOPASS"&&cib.name == null){
							//绑定审核失败，回到绑定审核第二步页面，页面中重新获取开户信息将审核中按钮隐藏，重新开户按钮显示
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgUser2");//必传
							map.put("bizLicenceRegisteredNo", active.info.blNumber);//注册号
							map.put("role", type);
							_OPENPAGE_FORM(map);
							return;
						}
						if(cib.name == null || cib.status==7){//没有企业名称，或者有，开户信息已失效
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}else if(bool && type == 1){
								checkAccount(0,path);
								return ;
							}else{
								var map = new Map();
								map.put("_PAGE", "bisic_message/authentication_open");//必传
								map.put("role", type);
								_OPENPAGE_FORM(map);
							}
						}else if(cib.imgPath20 == ""||cib.imgPath20 == null){//图片为空，去证件上传页面
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgUpload");//必传
							map.put("role", type);
							_OPENPAGE_FORM(map);
						}else if(cib.status == 0){//开户待审核
							//到审核的页面，可修改信息和图片
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgExamine");//必传
							map.put("role", type);
							map.put("cibId", cib.id);
							_OPENPAGE_FORM(map);
						}else if(cib.status == 1){//开户审核失败
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgExamine");//必传
							map.put("role", type);
							map.put("cibId", cib.id);
							_OPENPAGE_FORM(map);
						}else if(cib.status == 5){//审核通过,待鉴定
							//到小额鉴定
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgFinish");//必传
							map.put("role", type);
							map.put("bankAcctAcctNo", cib.bankAcctAcctNo);//银行账号
							map.put("bankAcctAcctName", cib.bankAcctAcctName);//银行账户名
							map.put("cibId", cib.id);//银行账户名
							_OPENPAGE_FORM(map);
						}else if(cib.status == 6){//小额鉴定失败
							//重新鉴定
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgFinish");//必传
							map.put("role", type);
							_OPENPAGE_FORM(map);
						}else if(cib.status == 2){
							//已通过审核显示开户信息
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_success");//必传
							map.put("openFlag", "ok");
							map.put("name", cib.name);//公司名
							map.put("bizLicenceRegisteredNo", cib.bizLicenceRegisteredNo);//注册号
							map.put("bizLicenceLegalName", cib.bizLicenceLegalName);//法人姓名
							map.put("bankAcctCnapsCode", cib.bankAcctCnapsCode);//开户行支行号
							map.put("bankAcctAcctNo", cib.bankAcctAcctNo);//银行账号
							map.put("contactName", cib.contactName);//联系人
							map.put("contactMobile", cib.contactMobile);//手机号
							map.put("role", type);
							_OPENPAGE_FORM(map);
						}
					}else{//已有orgInfo
						if(active.info.cibCheckState == 'PENDING'){
							//绑定审核中
							if(type == 0 && !bool){
								bool = true;
								checkAccount(1,path);
								return ;
							}
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgUser2");//必传
							map.put("role", type);
							_OPENPAGE_FORM(map);
						}else if(active.info.cibCheckState == 'PASS'){
							if(localStorage["FLAG"]!="1"){//直接去开户信息
								//已通过审核显示开户信息
								var map = new Map();
								map.put("_PAGE", "bisic_message/authentication_success");//必传
								map.put("openFlag", "ok");
								map.put("name", cib.name);//公司名
								map.put("bizLicenceRegisteredNo", cib.bizLicenceRegisteredNo);//注册号
								map.put("bizLicenceLegalName", cib.bizLicenceLegalName);//法人姓名
								map.put("bankAcctCnapsCode", cib.bankAcctCnapsCode);//开户行支行号
								map.put("bankAcctAcctNo", cib.bankAcctAcctNo);//银行账号
								map.put("contactName", cib.contactName);//联系人
								map.put("contactMobile", cib.contactMobile);//手机号
								map.put("role", type);
								_OPENPAGE_FORM(map);
							}else{//去绑定完成页面
								//已通过审核显示开户信息
								var map = new Map();
								map.put("_PAGE", "bisic_message/authentication_orgUser3");//必传
								map.put("role", type);
								map.put("name", cib.name);//公司名
								map.put("bizLicenceRegisteredNo", cib.bizLicenceRegisteredNo);//注册号
								_OPENPAGE_FORM(map);
								
							}
						}
					}
				}
				
			}
		}
	}
	/**
	* 记录开户状态
	*/
	function recordOpenAccountState(type){
		var url = '${bootAppPath}/orginfo/rz';
		var params = {memberId:memberId,type:type};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;

			if (data.response == 'success') {
				var active = data.data;
				var cib = active.cib;
				var cibCheckState;
				if(active.info!=null){
					cibCheckState = active.info.cibCheckState;
				}
				if(cibCheckState == 'PASS' || cib.status == 2){//已开户
					if(type == 1){//资方
						localStorage["ORGSTATE"] = 'T';
					}else{//企业方
						localStorage["BNSSTATE"] = 'T';
					}
				}else{
					if(type == 1){//资方
						localStorage["ORGSTATE"] = '';
					}else{//企业方
						localStorage["BNSSTATE"] = '';
					}
				}
			}
		}
	}
	function pyRegisterCvt2(){//[品友转化代码]在线咨询
		if("pinyou" == from){
			var w=window,d=document,e=encodeURIComponent;
			var b=location.href,c=d.referrer,f,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);
			if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('Bxs.35h.tIN5bLOrvz5ZUTFdcjDS-P')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&e=';
			(new Image()).src=u;
		}
	}
	
	var _intervalCount = 0;
	var lxbCbInputBtnBindedOne = false;
	var lxbCbInputBtnBindedTwo = false;
	var _interval = window.setInterval(function(){
		if($('#qiao-icon-group1').length>0 && !lxbCbInputBtnBindedOne){
			$('#qiao-icon-group1').bind("click",function(){
				pyRegisterCvt2();
			});
			lxbCbInputBtnBindedOne = true;
		}
		
		if($('#qiao-icon-group2').length>0 && !lxbCbInputBtnBindedTwo){
			$('#qiao-icon-group2').bind("click",function(){
				pyRegisterCvt2();
			});
			lxbCbInputBtnBindedTwo = true;
		}
		
		if(lxbCbInputBtnBindedOne && lxbCbInputBtnBindedTwo){
			window.clearInterval(_interval);
		}
		
		_intervalCount++;
		if(_intervalCount>60){
			window.clearInterval(_interval);
		}
	},200);
	
	/**
	 * Sitestatistics表的添加
	 */
	$.cookie("hezuo", from);
	url = window.location.href;

	$.ajax({
		type : "post",
		url : "${basePath}/site/addSitestatistics",
		data : 'from=' + from + '&url=' + url + '&type=PC', //发给php的数据有两项，分别是上面传来的u和p 
		success : function(data) {
		}
	});
	
	//tab切换
	function setTab(name, cursel, n) {
		for (i = 1; i <= n; i++) {
			var tab = document.getElementById(name + i);
			var menu = document.getElementById(name + "_" + i);
			menu.className = i == cursel ? "on" : "";
			tab.style.display = i == cursel ? "block" : "none";
		}
	}
	$(document).keydown(function(e) {
		if (e.keyCode == 13) {
			if (document.activeElement.id == 'search_ask') {
				search_ask();
			}
		}
	});
	//焦点图
	$(".foucs").slide({
		titCell : ".num ul",
		mainCell : ".lipic",
		effect : "fold",
		autoPlay : true,
		interTime : 5000,
		delayTime : 500,
		autoPage : "<li><a></a></li>"
	});
	
	var type=1;//初始化票据指数一个月
	var type1=1;//初始化票据指数纸票
	//初始化日期
	var begintimelong = Date.parse(new Date());
	var begintime = new Date(begintimelong);
	var fullyear = begintime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var month = begintime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var date = begintime.getDate();
	//获取当前日(1-31)

	var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
	var endfullyear = endtime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var endmonth = endtime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var enddate = endtime.getDate();
	//获取当前日(1-31)

	if (month < 10) {
		month = "0" + month;
	}
	if (date < 10) {
		date = "0" + date;
	}
	if (endmonth < 10) {
		endmonth = "0" + endmonth;
	}
	if (enddate < 10) {
		enddate = "0" + enddate;
	}
	$("#type6").change(function (){
		if($("#type6").val()==2){
			$("#years").removeClass("none");
			$("#orgCityList").addClass("none");
			$("#cityChose1").removeClass("none");
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}else{
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
			$("#years").addClass("none");
			$("#orgCityList").removeClass("none");
			$("#cityChose1").addClass("none");
		}
		jixidate();
	})
	
	$("#limit").change(function (){
		if($("#limit").val()==1){
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}else{
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}
		jixidate();
	})
	
	$("#day").blur(function (){
		var dayold=$("#day-hidden").val();
		var day=$("#day").val();
		if(!$("#day").validationEngine("validate")){
    		$("#day").focus();
    		setTimeout(function(){$("#day").validationEngine('hideAll');},1000);
    		$("#day").val(dayold);//重置
    		return;
    	}else{
			$("#day-hidden").val(day);
			var days=$("#days").html();
			$("#days").html(Number(days)-Number(dayold)+Number(day));
		}
	})
	
	//计算器
	$("#c_day").blur(function (){
		var day=$("#c_day").val();
		var dayold=$("#c_day-hidden").val();
		if(!$("#c_day").validationEngine("validate")){
    		$("#c_day").focus();
    		setTimeout(function(){$("#c_day").validationEngine('hideAll');},1000);
    		$("#c_day").val(dayold);//重置
    		return;
    	}else{
			$("#c_day-hidden").val(day);
			var days=$("#c_jxts").html();
			$("#c_jxts").html(Number(days)-Number(dayold)+Number(day));
		}
	})
	$("#c_type1").change(function (){
		
		if($("#c_type1").val()==2){
			$("#c_years").removeClass("none");
			$("#c_orgCityList").addClass("none");
			$("#cityChose2").removeClass("none");
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}else{
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
			$("#c_years").addClass("none");
			$("#c_orgCityList").removeClass("none");
			$("#cityChose2").addClass("none");
		}
		setPanel();//设置对应的票据价格
	})
	$("#c_allmoney").change(function (){
		if(!$("#c_allmoney").validationEngine("validate")){
    		$("#c_allmoney").focus();
    		setTimeout(function(){$("#c_allmoney").validationEngine('hideAll');},1000);
    		return;
    	}
		setPanel();//设置对应的票据价格
	})
	
	$("#c_acceptTime").change(function (){
		if($("#c_acceptTime").val()==1){
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}else{
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}
		setPanel();
	})
	
	//计息天数
	function jixidate() {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var type6 = $("#type6").val();//纸票1、电票2
		$.ajax({
			url:"${basePath}/homepage/xunjia/jixidate",
			type:"post",
			data:{"startDate":startDate,"endDate":endDate,"type6":type6},
			dataType:"json",
			success:function(data){
                $("#days").html(data.jxts);
                $("#day-hidden").val(data.tzts);
                $("#day").val(data.tzts);
			}
		});
	}
	
	
	
	function loaddate(){
		$(".today").text(fullyear + "-" + month + "-" + date);//日期
		$("#startDate").val(fullyear + "-" + month + "-" + date);//询价
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		
		$("#c_startDate").val(fullyear + "-" + month + "-" + date);//计算器
		$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//计算器
		jixidate();
		setCityList();
		loadCalendar();
	}
	
	//设置报价城市下拉框
	function setCityList() {
		$.ajax({
			url: '${basePath}/homepage/xunjia/cityid',
			type: 'POST',
			dataType: 'json',
			data: {},
			success: function(data){
				var orgCityList = data.orgCityList;
				if (orgCityList != null) {
					var str = '';
					$("#orgCityList").text('');
					$("#c_orgCityList").text('');
					for(var i in orgCityList){
						var orgCity = orgCityList[i];
						if (orgCity.cityid != null && orgCity.name != null) {
							str += "<option value='" + orgCity.cityid + "'>" + orgCity.name + "</option>";
						}
					}
					$("#orgCityList").append(str);
					$("#c_orgCityList").append(str);
				}
			}
		});
		
	}
	
	//询价
	function xunjia() {
		var limit=$("#limit").val();
		var type6 = $("#type6").val();//纸票1、电票2
		var type1 = $("#type1").val();//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
		var allmoney = $("#allmoney").val();//票据金额（对应type2 票据金额 :1:500以上 2:100-500 3:50-100  4:<50）
		if(!$("#allmoney").validationEngine("validate")){
    		$("#allmoney").focus();
    		setTimeout(function(){$("#allmoney").validationEngine('hideAll');},1000);
    		return;
    	}
		var type5="";
		var dates = $("#days").html();
		
		if(Number(allmoney)<500){//大票没有票据属性
        	if (Number(dates) <= 90) {
    			type5 = '1';
    		} else if (Number(dates) <= 178) {
    			type5 = '2';
    		} else {
    			type5 = '3';
    		}
        }
		/* var cityId = $("#orgCityList").val();
		if(cityId==null||$.trim(cityId)==""){
			alert("城市获取失败");return;
		}
		if(type6 ==2){
			cityId = 1;
		} */
		if(type6 == 1){
			type5 = '2';
		}
		cityId = 1;
		$.ajax({
			url:"${basePath}/homepage/xunjia/xunjia",
			type:"post",
			data:{"type6":type6,"type1":type1,"cityId":cityId,"type3":"1","type5":type5,"limit":limit,"allmoney":allmoney,"dates":dates,},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 var result = data.data;
	                    var price = result.price;
	                    var price1 = result.price1;
	                    var price2 = result.price2;
	                    var price3 = data.txlx;
	                    if($("#type6").val()==1){//纸票
	                        if(data.type2==1){//大票
	                            $("#showinfo1").html("当前价格-月利率");
	                            if(price=='--'||price==''){
	                                alert("暂无该选项数据");
	                                $("#showinfo2").html("——");
	                            }else{
	                                $("#showinfo2").html(price+"‰");
	                            }
	                        }else{
	                            $("#showinfo1").html("每10万贴息");
	                            if(price2=='--'||price2==''){
	                                $("#showinfo2").html("——");
	                            }else{
	                                $("#showinfo2").html(price2+"元");
	                            }
	                        }
	                    }else{//电票
	                        $("#showinfo1").html("当前价格-年利 率：");
	                        if(price=='--'||price==''){
	                            alert("暂无该选项数据");
	                            $("#showinfo2").html("——");
	                        }else{
	                            $("#showinfo2").html(price+"%");
	                        }
	                    }
	                    if(price3 != null && price3 != "" && price3!='--'){
	                        $("#showinfo4").html(price3+"元");
	                    }else{
	                        $("#showinfo4").html("——");
	                    }
				}else{
					$("#showinfo2").html("——");
					alert(data.msg);
				}
			}
		});
		
		pyRegisterCvt3();//品友转化代码（统计首页询价功能）
	}
	
	function c_jisuan(){
        var type1 = $("#c_type1").val();
        var type2 = $("#c_type2").val();
        var start = $("#c_startDate").val();
        var end = $("#c_endDate").val();
        var allmoney = $("#c_allmoney").val();
        var jxts=$("#c_jxts").text();
        var rate1=$("#rate2").val();
        var rate = $("#rateYear").val();//年息
        if(!$("#c_allmoney").validationEngine("validate")){
    		$("#c_allmoney").focus();
    		setTimeout(function(){$("#c_allmoney").validationEngine('hideAll');},1000);
    		return;
    	}
        if(!$("#c_poundage").validationEngine("validate")){
    		$("#c_poundage").focus();
    		setTimeout(function(){$("#c_poundage").validationEngine('hideAll');},1000);
    		return;
    	}
        if(type1==2){//电票
        	if(!$("#rateYear").validationEngine("validate")){
        		$("#rateYear").focus();
        		setTimeout(function(){$("#rateYear").validationEngine('hideAll');},1000);
        		return;
        	}
        }else if(type1==1 && Number(allmoney)>=500){
        	if(!$("#rate").validationEngine("validate")){
        		$("#rate").focus();
        		setTimeout(function(){$("#rate").validationEngine('hideAll');},1000);
        		return;
        	}
        	rate = $("#rate").val();//月利率 纸票大票
        }else{
        	if(!$("#rate2").validationEngine("validate")){
        		$("#rate2").focus();
        		setTimeout(function(){$("#rate2").validationEngine('hideAll');},1000);
        		return;
        	}
        	var res = Number(rate1);
    	    var result = res/Number(jxts)/100000*1000*30;
            rate1 =result.toFixed(2) ;//每十万  纸票小票
        }
        $.ajax({
			url:"${basePath}/homepage/calculator",
			type:"post",
			data:{"type2":type2,"type1":type1,"rate":rate,"rate1":rate1,"allmoney":allmoney,"start":start,"end":end,"jxts":jxts},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 $("#c_txlx").text(data.txlx);//贴现利息
					 var allmoney = $("#c_allmoney").val();
		             var poundage = $("#c_poundage").val();
		             var txje = getTXJE(allmoney,data.txlx,poundage);
		             $("#c_txje").text(txje);//贴现金额
				}
			}
        })	
	}
	
	function initExcel(){
		if($("#c_allmoney").val()=="")return;
		var cityId = $("#c_orgCityList").val();
        var type1 = $("#c_type1").val();
        var type2 = $("#c_type2").val();
        var start = $("#c_startDate").val();
        var end = $("#c_endDate").val();
        var allmoney = $("#c_allmoney").val();
        var acceptTime = $("#c_acceptTime").val();
        $.ajax({
			url:"${basePath}/homepage/calculator/init",
			type:"post",
			data:{"type2":type2,"type1":type1,"cityId":cityId,"limit":acceptTime,"allmoney":allmoney,"start":start,"end":end,},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 $("#c_day").val(data.tzts);
		             $("#c_day-hidden").val(data.tzts);
		             
		             if($("#c_type1").val()==2){
		                    if(data.rate!=null && data.rate!="" && data.rate!="--"){
		                        $("#rateYear").val(data.rate);
		                    }
		                }else{
		                    if(data.rate!=null && data.rate!="" && data.rate!="--"){
		                    	if($("#c_allmoney").val()>=500){
		                    		$("#rate").val(data.rate);//月（年）利率
		                    	}else{
		                    		$("#rate2").val(data.rate2);//每十万贴息
		                    	}
		                    }
		                }
		                $("#c_jxts").text(data.jxts);//计息天数
				}
			}	
        })
        
	}
	
	function banner1(obj) {
		$(obj).children("#img1").attr("src",
				"${imagePath}/website/homepage/inquiryIcon2.png");
		$("#img2").attr("src",
				"${imagePath}/website/homepage/calculatorIcon1.png");
		$("#img4").attr("src", "${imagePath}/website/homepage/dateIcon1.png");
		$("#tu1").addClass("cd43c33").addClass("my-pl");
		$("#tu2").removeClass("cd43c33").removeClass("my-pl");
		$("#tu3").removeClass("cd43c33").removeClass("my-pl");
		$("#tu1").parent("li").addClass("bcwhite");
		$("#tu2").parent("li").removeClass("bcwhite");
		$("#tu3").parent("li").removeClass("bcwhite");
		$("#inquiryIcon").removeClass("none");
		$("#calculatorIcon").addClass("none");
		$("#dateIcon").addClass("none");
		$("#shengdan1").removeClass("none");
		$("#shengdan2").addClass("none");
		$("#shengdan3").addClass("none");
		loaddate();
	}
	function banner2(obj) {
		$(obj).children("#img2").attr("src",
				"${imagePath}/website/homepage/calculatorIcon2.png");
		$("#img1").attr("src", "${imagePath}/website/homepage/inquiryIcon1.png");
		$("#img4").attr("src", "${imagePath}/website/homepage/dateIcon1.png");
		$("#tu2").addClass("cd43c33").addClass("my-pl");;
		$("#tu1").removeClass("cd43c33").removeClass("my-pl");
		$("#tu3").removeClass("cd43c33").removeClass("my-pl");
		$("#tu2").parent("li").addClass("bcwhite");
		$("#tu1").parent("li").removeClass("bcwhite");
		$("#tu3").parent("li").removeClass("bcwhite");
		$("#inquiryIcon").addClass("none");
		$("#calculatorIcon").removeClass("none");
		$("#dateIcon").addClass("none");
		$("#shengdan2").removeClass("none");
		$("#shengdan1").addClass("none");
		$("#shengdan3").addClass("none");
	}
	function banner4(obj) {
		$(obj).children("#img4").attr("src",
				"${imagePath}/website/homepage/dateIcon2.png");
		$("#img2").attr("src",
				"${imagePath}/website/homepage/calculatorIcon1.png");
		$("#img1").attr("src", "${imagePath}/website/homepage/inquiryIcon1.png");
		$("#tu3").addClass("cd43c33").addClass("my-pl");;
		$("#tu2").removeClass("cd43c33").removeClass("my-pl");
		$("#tu1").removeClass("cd43c33").removeClass("my-pl");
		$("#tu3").parent("li").addClass("bcwhite");
		$("#tu2").parent("li").removeClass("bcwhite");
		$("#tu1").parent("li").removeClass("bcwhite");
		$("#inquiryIcon").addClass("none");
		$("#calculatorIcon").addClass("none");
		$("#dateIcon").removeClass("none");
		$("#shengdan3").removeClass("none");
		$("#shengdan2").addClass("none");
		$("#shengdan1").addClass("none");
	}
	
	function rilitab() {
		scrollTo(0);
		$('#zhiHalf').removeClass("cd43c33").addClass('c2d2d2d');
		$('#dianYear').removeClass("cd43c33").addClass('c2d2d2d');
		$('#dianHalf').removeClass("c2d2d2d").addClass('cd43c33');
		$('#rili_bottom').animate({
			left : '244px',
			width : '123px'
		});
	}
	//根据参数名获得该参数 pname等于想要的参数名
	function getParam1(pname) {
		var params = location.search.substr(1); // 获取参数 平且去掉？
		var ArrParam = params.split('&');
		if (ArrParam.length == 1) {
			//只有一个参数的情况
			return params.split('=')[1];
		} else {
			//多个参数参数的情况
			for (var i = 0; i < ArrParam.length; i++) {
				if (ArrParam[i].split('=')[0] == pname) {
					return ArrParam[i].split('=')[1];
				}
			}
		}
	}
	/**
	* 加载首页的日历图
	*/
	function loadCalendar(){
		$.ajax({
			url:"${basePath}/pc/img/calendar",
			type:"post",
			data:{code:"index_rili_pc"},
			dataType:"json",
			success:function(data){
				if(data.success == "success"){
					for(var i = 0 ; i <  data.calendar.length; i++){
						if(data.calendar[i].sort == 1){
							$("#calendar1").attr("src","${imagePath}/"+data.calendar[i].path);
						}else if(data.calendar[i].sort == 2){
							$("#calendar2").attr("src","${imagePath}/"+data.calendar[i].path);
						}else if(data.calendar[i].sort == 3){
							$("#calendar3").attr("src","${imagePath}/"+data.calendar[i].path);
						}
					}					
				}
			}	
        });
	};
	
	//票据指数
	function piaoju(months) {
		$.ajax({
			url: '${bootAppPath}/out/historyprice/chart',
			type: 'POST',
			dataType: 'json',
			data: {'months':months},
			success: function(data){
				if(data.data.response=="success")
				highcharts(data.data);
			}
		});
	};
	/* shibor 票据指数 */
	$(function() {
		$('.shiborBtn').click(function() {
			$('.zsBtn').removeClass("cd43c33").addClass('c2d2d2d');
			$('.shiborBtn').removeClass("c2d2d2d").addClass('cd43c33');
			$('#zyjszc_bottom').animate({left : "0px"});
			$('.shiborCon').removeClass("none");
			$('.pjzsCon').addClass("none");
		});
		$('.zsBtn').click(function() {
			$('.zsBtn').removeClass("c2d2d2d").addClass('cd43c33');
			$('.shiborBtn').removeClass("cd43c33").addClass('c2d2d2d');
			$('#zyjszc_bottom').animate({left : "362px"});
			$('.shiborCon').addClass("none");
			$('.pjzsCon').removeClass("none");
		});
	});
	
	function logintab() {
		scrollTo(0);
		$('#zhi').removeClass("cd43c33").addClass('c2d2d2d');
		$('#dian').removeClass("c2d2d2d").addClass('cd43c33');
		$('#switch_bottom').animate({
			left : '204px',
			width : '204px'
		});
	}
	//根据参数名获得该参数 pname等于想要的参数名
	function getParam2(pname) {
		var params = location.search.substr(1); // 获取参数 平且去掉？
		var ArrParam = params.split('&');
		if (ArrParam.length == 1) {
			//只有一个参数的情况
			return params.split('=')[1];
		} else {
			//多个参数参数的情况
			for (var i = 0; i < ArrParam.length; i++) {
				if (ArrParam[i].split('=')[0] == pname) {
					return ArrParam[i].split('=')[1];
				}
			}
		}
	 }
	
	 //票据指数
    function highcharts(obj) { 
    	var priceStr=String(obj.data.price);//原始字符串  
        var priceStrArr=priceStr.split(",");//分割成字符串数组  
        var priceIntArr=[];//保存转换后的整型字符串 
        priceStrArr.forEach(function(data,index,arr){  
        	priceIntArr.push(+data);  
        });  
        for(var i =0;i<priceIntArr.length;i++){
   		  if(priceIntArr[i] == 0){
   			priceIntArr[i] =null;
   		  }
   		}
        
        var price1Str=String(obj.data.price_1);//原始字符串  
        var price1StrArr=price1Str.split(",");//分割成字符串数组  
        var price1IntArr=[];//保存转换后的整型字符串 
        price1StrArr.forEach(function(data,index,arr){ 
        	price1IntArr.push(+data); 
        });
        for(var i =0;i<price1IntArr.length;i++){
   		  if(price1IntArr[i] == 0){
   			price1IntArr[i] =null;
   		  }
   		}
        
        var price2Str=String(obj.data.price_2);//原始字符串  
        var price2StrArr=price2Str.split(",");//分割成字符串数组  
        var price2IntArr=[];//保存转换后的整型字符串 
        price2StrArr.forEach(function(data,index,arr){  
        	price2IntArr.push(+data);  
        });
        /* price2IntArr=price2StrArr.map(function(data){
        	return +data;
       	}); */
        for(var i =0;i<price2IntArr.length;i++){
   		  if(price2IntArr[i] == 0){
   			price2IntArr[i] =null;
   		  }
   		}
    	
	    $('#container').highcharts({
	        chart: {
	            zoomType: 'xy'
	        },
	        title: {
	            text: '国股年利率'
	        },
	        xAxis: [{
	        	categories: eval(obj.data.dates),
	            crosshair: true,
	            tickInterval: 0
	        }],
	        yAxis: [{
	            title: {
	                text: '转贴现利率 (‰)'
	            },
	            crosshair: true
	        }],
	        tooltip: {
	            shared: true
	        },
	        credits : {
				text : 'www.utiexian.com',
				href : 'https://www.utiexian.com'
			},
			/*数据点设置*/
	        plotOptions: {
	            series: {
	                marker: {
	                	enabled: false, /*数据点是否显示*/
	                },
	            }
	        },
	        legend: {
	            align: 'left',
	            x: 120,
	            verticalAlign: 'top',
	            y: 20,
	            floating: true
	        },
	        series: [{
	            name: '2016',
	            type: 'spline',
	            data: price2IntArr,
	            tooltip: {
	                valueSuffix: '‰'
	            },
	            color:'#7790fe',
	            lineWidth:0.5,
	            connectNulls: true
	        }, {
	            name: '2017',
	            type: 'spline',
	            data: price1IntArr,
	            tooltip: {
	                valueSuffix: '‰'
	            },
	            color:'#3ad45a',
	            lineWidth:0.5,
	            connectNulls: true
	        }, {
	            name: '2018',
	            type: 'spline',
	            data: priceIntArr,
	            tooltip: {
	                valueSuffix: '‰'
	            },
	            color:'#d43c33',
	            lineWidth: 2,
	            connectNulls: true
	        }]
	    });
	}; 
	
	//日历
	!function() {
		laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
	}();
	//日期范围限制
	//    贴现日期
	var start = {
		elem : '#startDate',
		format : 'YYYY-MM-DD',
		//min: laydate.now(), //设定最小日期为当前日期
		min : '1900-01-01', //设定最小日期
		max : '2099-06-16', //最大日期
		istime : true,
		istoday : false,
		choose : function(datas) {
			jixidate();
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	//    到期日期
	var end = {
		elem : '#endDate',
		format : 'YYYY-MM-DD',
		min : laydate.now(),
		max : '2099-06-16',
		istime : true,
		istoday : false,
		choose : function(datas) {
			jixidate();
			start.max = datas; //结束日选好后，充值开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);
	
	//计算器功能
	//计算器的票据价格类型
	function setPanel(){
		var type=$("#c_type1").val();
		var money=$("#c_allmoney").val();
		if(type==2){//电票只显示年息
			$("#rateYear").addClass("validate[required]");
			$("#rate").removeClass("validate[required]");
			$("#rate2").removeClass("validate[required]");
			$(".nianxi").removeClass("none");
			$(".yuexi").addClass("none");
			$(".meishiwan").addClass("none");
		}else if(type==1 && money<500&&money!=""){//纸票500万以下显示每十万
			$("#rate2").addClass("validate[required]");
			$("#rate").removeClass("validate[required]");
			$("#rateYear").removeClass("validate[required]");
			$(".nianxi").addClass("none");
			$(".yuexi").addClass("none");
			$(".meishiwan").removeClass("none");
		}else if(type==1 && (money>=500||money=="")){//纸票500玩以上显示月息
			$("#rate").addClass("validate[required]");
			$("#rateYear").removeClass("validate[required]");
			$("#rate2").removeClass("validate[required]");
			$(".nianxi").addClass("none");
			$(".yuexi").removeClass("none");
			$(".meishiwan").addClass("none");
		}
		initExcel();
	}

	function c_clear() {
		$("#c_jxts").val("");
		$("#c_allmoney").val("");
		$("#c_day").val("0");
		$("#c_txlx").val("");
		$("#c_txje").val("");
		$("#c_poundage").val("");
		$("#rate").val("");
		$("#rate1").val("");
		$("#rate2").val("");
		$("#rateYear").val("");
	}
	function getTXJE(allmoney,txlx,poundage){
	    var res = (Number(allmoney)*10000)-Number(txlx);
	    if(poundage!=null)res -= Number(poundage);
	    return (res/10000).toFixed(2);
	}
	var start1 = {
			elem : '#c_startDate',
			format : 'YYYY-MM-DD',
			//min: laydate.now(), //设定最小日期为当前日期
			min : '1900-01-01', //设定最小日期
			max : '2099-06-16', //最大日期
			istime : true,
			istoday : false,
			choose : function(datas) {
				initExcel();
				end1.min = datas; //开始日选好后，重置结束日的最小日期
				end1.start = datas; //将结束日的初始值设定为开始日
			}
		};
		//    到期日期
		var end1 = {
			elem : '#c_endDate',
			format : 'YYYY-MM-DD',
			min : laydate.now(),
			max : '2099-06-16',
			istime : true,
			istoday : false,
			choose : function(datas) {
				initExcel();
				start1.max = datas; //结束日选好后，充值开始日的最大日期
			}
		};
		laydate(start1);
		laydate(end1);
		
		/**
		* 当城市改变的时候，清空原本的数据
		*/
		$("#c_orgCityList").change(function(){
			c_clear();
			$("#c_txlx").html("---");
			$("#c_txje").html("---");
		});
</script>
<noscript><img src="//stats.ipinyou.com/adv.gif?a=Bxs..lqGDxUeDQQG_QBeKEVRbw_&e=" style="display:none;"/></noscript>

[@main.footer/]
[/@main.body]