[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/offer.css"/>
[@main.header currentmenu='1' topindex="3"/]
<!-- 
	提高百度地图所在的层级
 -->
<style>
.tangram-suggestion-main  {
    z-index: 13;
}
</style>
[@main.right /]
 <div class="w h pf bc05 zi10 top none" id="window">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <!--确认认证信息-->
            <div class="pr t50 l50" id="org_authentication">
            	<div class="f18 fb mt20">编辑此贴现地址：</div>
            	<div class="w mt25">
                    <div class="fl tl w85 lh27">我的位置：</div> 
                    <input type="text" onblur="myToCity();" placeholder="请输入关键字，例如东方明" id="tjaddress" class="validate[required] w238 h27 lh27 bae0e0e0 ti8">
                	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                </div>
            	<div class="w mt20">
                    <div class="fl tl w85 lh27">详细地址：</div>
                    <input type="text" id="tjother" maxlength="140" placeholder="请输入您的详细地址" class="w491 h27 lh27 bae0e0e0 ti8">
                </div>
                <div class="w mt20">
                    <div class="fl tl w85 lh27">交易城市：</div>
                    <select id="initcity" class="w320 h40 select320 ti10"></select>
                </div>
                <div class="w400 h230 bae0e0e0 ml100 mt20" id="pcmap"></div>
                <div class="cb"></div>
                <input id="tjlon" type="hidden">
                <input id="tjlat" type="hidden">
                <input id="tjid" type="hidden">
                <input type="button" id="baocunCity" class="w268 h44 bc lh45 b0 cwhite bcfc4d42 br5 f18 tc ml190 mt30" value="保存">
            </div>
            <!--提交成功-->
            <div class="pr top h600 bcwhite none" id="org_success">
                <div class="w577 pr t50 bc">
                    <div class="w577 f24 lh48">感谢您提交认证资料，与此同时我们发送了一份<span class="fb">《报价方合作银行承诺函》</span>到您的邮箱中，请查看并按照邮件要求回复我们。</div>
                    <div class="f24 mt30">我们收到您的邮件后，将在<span class="fb">1-3</span>个工作日内给您回复。</div>
                    <div class="f18 mt30 cd43c33">温馨提示：审核未通过的用户，只有三次报价收票机会，请尽快按照要求回复邮件，以免影响您的使用。</div>
                    <div class="w268 bc">
                        <input type="button" id="org_queren" class="w268 h44 lh45 b0 br5 bcfc4d42 cwhite f18 tc mt80" value="确认">
                    </div>
                </div>
            </div>
            <!--提交成功-->
            <div class="pr t125 none" id="bns_success">
                <div class="tc f24 lh48">感谢您提交认证资料，<br>我们将在1-3个工作日内给您回复</div>
                <div class="f18 mt30 cd43c33">温馨提示：审核未通过的用户，只有三次报价收票机会，请尽快按照要求回复邮件，以免影响您的使用。</div>
                <div class="w268 bc">
                    <input type="button" id="bns_queren" class="w268 h44 lh45 b0 br5 bcfc4d42 cwhite f18 tc mt100" value="确认">
                </div>
            </div>
        </div>
    </div>
    <div class="cb"></div>
</div>
<div class="w1200 ha bc f16 c2d2d2d mb20">
    <!-- tab-->
    <div class="w1200 h52 bcwhite mt20">
        <ul class="w1198 h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite offerTab1">
            <li class="w250 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="offerTab1" id="offerTab1" class="none"><a  class="cd43c33"><label for="offerTab1" class="dsb">银票报价</label></a>
            </li>
            <li class="w250 lh50 fl bre0e0e0 " >
                <input type="radio" name="offerTab1" id="offerTab2" class="none"><label for="offerTab2" class="dsb"><a href="${basePath}/price/change" class="c2d2d2d">商票报价</a></label>
            </li>
        </ul>
    </div>
    <!-- 报价--> 	
    <div class="w1200 h1200 bae0e0e0 mt10 bcwhite">
        <!-- 菜单-->
        <div class="fl w200 h1180 bre0e0e0 bbe0e0e0 bcf9f9f9">
            <ul class="w162 bc lh40 tc mt40">
                <li class="bcfc4d42 cwhite" id="order1"><a class="w160 h40 bae0e0e0 lh40 dsb offerTsb2 cp">填写额度</a></li>
                <li class="mt30" id="order2"><a class="w160 h40  bae0e0e0 lh40 dsb offerTsb2 cp">填写报价</a></li>
                <li class="mt30 " id="order3"><a class="w160 h40  bae0e0e0 lh40 dsb offerTsb2 cp">查看报价</a></li>
            </ul>
        </div>
        
        <!--您今日所有票据的额度-->
        <div id="yinpiao1" class="fl w997 h500 bcwhite ">
            <div class="mt30 ml50 lh34">
                <div class="fl fb">
                    请先输入您今日所有票据的额度
                </div>
                <input id="price" type="text" class="fl w268 h34 bae0e0e0 ml20">
                <div class="fl ml10">万元</div>
                <div class="cb"></div>
            </div>
            <div class="mt30 ml50 lh34">
            	<div class="fl fb">
                    备注
                </div>
                <textarea name="textarea" id="remarkYp" placeholder="请输入备注" class="fl w400 h80 bae0e0e0 ml20"></textarea>
                <div class="cb"></div>
            </div>
            <input type="button" class="w200 h40 lh40 cwhite bce84c3d b0 br5 mt30 ml280 cp" id="tijiao1" value="提交" onclick="saveOrgLimit();" >
        </div>
                <!--您今日所有票据的额度-->
        <div id="yinpiao3" class="fl w997 lh34 bcwhite none">
            <!--您今日所有票据的额度-->
            <div class="ml50 mt20 xuxian">
                <div class="fl fb">
                    您今日所有票据的额度
                </div>
                <input type="text" id="limit" class="fl w120 h30 lh30 bae0e0e0 ml20">
                <div class="fl ml20">万元</div>
                <input type="button" class="fl w80 h34 lh34 b0 br5 cwhite bce84c3d ml20 mb20 cp" value="更改" onclick="saveLimit()">
                <div class="cb"></div>
            </div>
            <!--您报价的城市
            <div class="ml50 mt20 xuxian">
                <div class="fb">
                    您报价的城市
                </div>
                <ul class="fl lh34 tc ml30 mt16 mb20" id="cityList">

                </ul>
                <div class="cb"></div>
            </div>
            -->
            <!--您今日各类票据的报价-->
            <div class="ml50 mt20" >
                <div class="fb">
                    您今日各类票据的报价
                </div>
                <div class="w792 h403 bc bae0e0e0 f14 mt20">
                 <ul class="fl w150 h403 bre0e0e0 bcf8f7f7 tc scrollbar oxh oya" id="spOffer">   
                </ul>
                
                <div class="fl w640">
                    <div class="w640 bbe0e0e0">
                        <div class="h390 ml50 priceTable" id="big_1">
                            <div class="h30 mt13">
                                <div class="fl">国股：
                                </div>
                                <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="guoguPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]" type="text" id="guoguPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="guoguPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="guoguPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20" id="dashang">
                                <div class="fl">大商：</div>
                                <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="dashangPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]" type="text" id="dashangPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="dashangPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="dashangPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20">
                                <div class="fl" id="chengshangTitle">城商：</div>
                                <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="chengshangPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]" type="text" id="chengshangPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="chengshangPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="chengshangPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20">
                                <div class="fl">外资：
                                </div>
                                <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="waiziPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]"type="text" id="waiziPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="waiziPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="waiziPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20">
                                <div class="fl">农商：
                                </div>
                                <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="nongshangPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]"type="text" id="nongshangPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="nongshangPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="nongshangPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20">
                                <div class="fl">农合：
                                </div>
                               <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="nonghePrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]" type="text" id="nonghePrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="nonghePrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="nonghePrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20">
                                <div class="fl">农信：
                                </div>
                                <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div>
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="nongxinPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]" type="text" id="nongxinPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="nongxinPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="nongxinPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                            <div class="h30 mt20">
                                <div class="fl">村镇：
                                </div>
                               <div class="fl price"><!-- 利率 -->
	                                <div class="fl ml10 priceTitle">年利率
	                                </div> 
	                                <input type="text" class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[lilv]]" id="cunzhenPrice">
	                                <div class="fl ml10 priceSign">%
	                                </div>
                                </div>
                                
                                <div class="fl price1"><!-- 参数1 -->
	                                        <div class="fl ml10" >+
	                                        </div>
	                                        <input class="fl w60 h30 lh30 bae0e0e0 ml10 validate[custom[baojianumber]]"type="text" id="cunzhenPrice1">
	                                        <div class="fl ml10">元
	                                        </div>
                               	</div>
                               	
                               	<div class="fl price2 none"><!-- 每10W贴息 -->
	                                        <div class="fl ml20">每10万（足月）
	                                        </div>
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20 validate[custom[baojianumber]]" type="hidden" id="cunzhenPrice2">
	                                        <input class="fl w100 h30 lh30 bae0e0e0 ml20" type="text" id="cunzhenPrice3">
	                                        <div class="fl ml20">元
	                                        </div>
                               	</div>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div>
            </div>
                <div class="cb"></div>
            </div>
        </div>
        <!-- 报价内容-->
        <div id="yinpiao2" class="fl w917 pl30 pr50 none">
        <!--请选择您要报价的票据类型-->
            <div class="mt30 xuxian">
                <div class="fb mb20">
                    <img src="url(https://img.utiexian.com/website/offer/yuandian.png)" style=" width: 5px;height: 5px;"/>票据类型
                </div>
                <ul class="fl lh34 tc mb20">
                    <li class="fl ml15 checkbox1"><input type="radio" id="diantype1" class="none" name="checkbox1" value="0"><label class="fl w80 h34 br3 bae0e0e0 cwhite bce84c3d" for="type1">大票电票</label></li>
                    <li class="fl ml15 checkbox1"><input type="radio" id="zhitype2" class="none" name="checkbox1" value="1"><label class="fl w80 h34 br3 bae0e0e0" for="type2">小票纸票</label></li>
                    <li class="fl ml15 checkbox1"><input type="radio" id="zhitype3" class="none" name="checkbox1" value="2"><label class="fl w80 h34 br3 bae0e0e0" for="type3">小票电票</label></li>
                </ul>
                <div class="cb"></div>
            </div>
            <!--请选择您要报价的城市-->
            <div class="mt30 xuxian">
                <!-- <div class="fb fl mr20 lh40 mb20">
                    报价城市
                </div>
                <select class="fl w148 h40 select148 ti8" id="cityselect">
                    <option value="1">全国</option>
                </select>
                <a href="javascript:void(0);" class="fr lh40 f16 cd43c33 addcity" id="cityAdd">新增交易城市<span class="c4c4c4c">(最多可选择10个交易城市)</span></a>
                <div class="cb"></div>
                <ul class="fl lh34 tc mb20 none" id="cityChose">
                    
                </ul>
                <div class="cb"></div> -->
            </div>
            <div class="w h45 ml20" id="dadian-flag">
	            <!--请选择您要报价的期限-->
	            <div class="mt30 xuxian">
	                    <div class="fb fl mr20 lh40 mb20">
	                        报价期限 
	                    </div>
	                    <select class="fl w148 h40 select148 ti8 checkbox5">
	                        <option value="1">一年期 </option>
	                        <option value="0">半年期</option>
	                    </select>
	                    <div class="cb"></div>
	            </div>
	        </div>
	        
	        <div class="w h45 ml20 none" id="xiaodian-flag">
	            <!--请选择您要报价的期限-->
	            <div class="mt30 xuxian">
                    <div class="fb fl mr20 lh40 mb20">
                                   报价期限
                    </div>
                    <select class="fl w148 h40 select148 ti8 checkbox4">
                        <option value="0">0-90天 </option>
                        <option value="1" selected="selected">90-178天</option>
                        <option value="2">178天以上</option>
                    </select>
                    <div class="cb"></div>
	            </div>
	            <!--请选择您要报价的金额-->
	            <div class="mt30 xuxian">
	                    <div class="fb fl mr20 lh40 mb20">
	                        票面金额
	                    </div>
	                    <select class="fl w148 h40 select148 ti8 checkbox3">
	                        <!-- <option value="">不限    </option> -->
	                        <option value="2">100万-500万 </option>
	                        <option value="1" selected>50万-100万 </option>
	                        <option value="0">50万以下</option>
	                    </select>
	                    <div class="cb"></div>
	            </div>
	        </div>
            <div id="xiaozhi-flag" class="none">
            	
	            <!--请选择您要报价的金额-->
	            <div class="mt30 xuxian">
	                    <div class="fb fl mr20 lh40 mb20">
	                        票面金额
	                    </div>
	                    <select class="fl w148 h40 select148 ti8 checkbox3">
	                        <!-- <option value="">不限    </option> -->
	                        <option value="2">100万-300万 </option>
	                        <option value="1" selected x>50万-100万 </option>
	                        <option value="0">50万以下</option>
	                    </select>
	                    <div class="cb"></div>
	            </div>
            </div>
            <!--已选条件-->
            <div class="mt20 bbe0e0e0">
                <input type="hidden" id="type1" value="">
                <input type="hidden" id="type2" value="">
                <input type="hidden" id="type5" value="">
                <input type="hidden" id="type3" value="">
                <input type="hidden" id="type4" value="">
                <div class="cb"></div>
            </div>
            <!--承兑行对应的价格-->
            <div class="mt20 bbe0e0e0">
                <div class="fb mb20">
                    请填写各个承兑行对应的价格：
                </div>
                <!--1大票电票 -->
                <div class="w375 bae0e0e0 tc f14 lh30 mb20 ml90 none dadian">
                    <div class="w bbe0e0e0 h40 lh40 f16">
                        <div class="fl w187 bre0e0e0">承兑行
                        </div>
                        <div class="fl w187">年利率（%）
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">国股
                        </div>
                        <div class="fl w187">
                            <input type="text" id="guogu2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">大商
                        </div>
                        <div class="fl w187">
                            <input type="text" id="dashang2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">小商
                        </div>
                        <div class="fl w187">
                            <input type="text" id="chengshang2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">外资
                        </div>
                        <div class="fl w187">
                            <input type="text" id="waizi2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">农商
                        </div>
                        <div class="fl w187">
                            <input type="text" id="nongshang2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">农合
                        </div>
                        <div class="fl w187">
                            <input type="text" id="nonghe2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w bbe0e0e0 h30">
                        <div class="fl w187 bre0e0e0">农信
                        </div>
                        <div class="fl w187">
                            <input type="text" id="nongxin2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                    <div class="w h30">
                        <div class="fl w187 bre0e0e0">村镇
                        </div>
                        <div class="fl w187">
                            <input type="text" id="cunzhen2" class="w h27 b0 tc validate[custom[lilv]]" placeholder="请输入年利率">
                        </div>
                    </div>
                </div>
                <!-- 1大票电票-->

                <!--2小票纸票 -->
                <div class="mb20">
                    <!--2小票纸票 足月票 -->
                    <div id="monthYes" class="fl w620 bae0e0e0 tc f14 lh30 ml120 xiaozhi none">
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">承兑行</div>
                            <div class="fl w245 bre0e0e0">&nbsp;</div>
                            <div class="fl w245">足月票</div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                国股
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="guogu4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="guogu14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="guogu24" value=""><input type="text" value="" id="guogu34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml5" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                               城商行
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="chengshang4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="chengshang14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="chengshang24" value=""><input type="text" value="" id="chengshang34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml10" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                外资
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="waizi4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="waizi14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="waizi24" value=""><input type="text" value="" id="waizi34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml10" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                农商
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="nongshang4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="nongshang14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="nongshang24" value=""><input type="text" value="" id="nongshang34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml10" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                农合
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="nonghe4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="nonghe14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="nonghe24" value=""><input type="text" value="" id="nonghe34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml10" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                农信
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="nongxin4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="nongxin14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="nongxin24" value=""><input type="text" value="" id="nongxin34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml10" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40">
                            <div class="fl w125 bre0e0e0">
                                村镇
                            </div>
                            <div class="fl w245 bre0e0e0">
                                <input type="text" id="cunzhen4" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入月利率">
                                <div class="fl ml5">‰ +</div>
                                <input type="text" id="cunzhen14" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                            <div class="fl w245">
                                <input type="hidden" id="cunzhen24" value=""><input type="text" value="" id="cunzhen34" class="fl w200 h30 lh30 bae0e0e0 mt3 ml10" placeholder="请输入每十万贴息">
                                <div class="fl ml10">元</div>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div>
                <!--2小票纸票 -->

                <!--3小票电票 -->
                <div class="mb20">
                    <div id="monthYes" class="fl bae0e0e0 tc f14 lh30 ml120 xiaodian">
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">承兑行</div>
                            <div class="fl w270 bre0e0e0">&nbsp;</div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                国股
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="guogu3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="guogu13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                               城商行
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="chengshang3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="chengshang13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                外资
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="waizi3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="waizi13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                农商
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="nongshang3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="nongshang13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                农合
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="nonghe3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="nonghe13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40 bbe0e0e0">
                            <div class="fl w125 bre0e0e0">
                                农信
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="nongxin3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="nongxin13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                        <div class="w h40 lh40">
                            <div class="fl w125 bre0e0e0">
                                村镇
                            </div>
                            <div class="fl w270 bre0e0e0">
                                <input type="text" id="cunzhen3" class="fl w100 h30 lh30 bae0e0e0 mt3 ml10 ti10 validate[custom[lilv]]" placeholder="请输入年利率">
                                <div class="fl ml5">（%） +</div>
                                <input type="text" id="cunzhen13" class="fl w60 h30 lh30 bae0e0e0 mt3 ml5">
                                <div class="fl ml5">元</div>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div>
                <!--3小票电票-->
            </div>
                <!-- 报价按钮-->
                <div class="w mt20 tc">
                    <div class="mt20 ml256 lh40 tc">
                        <input type="button" class="fl w187 h40 bce84c3d b0 br5 cwhite f16 ml30 cp" value="一键清空" onclick="clearForm()">
                        <input type="button" class="fl w187 h40 bce84c3d b0 br5 cwhite f16 ml30 dadian cp" value="确认修改" onclick="savePrice(2);">
                        <input type="button" class="fl w187 h40 bce84c3d b0 br5 cwhite f16 ml30 xiaozhi none cp" value="确认修改" onclick="savePrice(4);">
                        <input type="button" class="fl w187 h40 bce84c3d b0 br5 cwhite f16 ml30 xiaodian none cp" value="确认修改" onclick="savePrice(3);">
                    </div>
                    <div class="cb"></div>
                    <div class="w f14 cd43c33 mt30">
                        *请注意： 当日价格1个小时可更改一次，请确认后再提交
                    </div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="cb"></div>
        </div>
        <div class="cb"></div>
    </div>
    <div class="cb"></div>
</div>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx"></script>
<script type="text/javascript">
	//主要是地图的处理
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	var map = null;
	
	var longitude = "121.506854";//经度
	var latitude = "31.2408";//纬度


	var memberId = '${member.id}';
	var yinpiaoedu=null;
	
	var checkbox1=0;//报价属性
	var checkbox3=1;//报价属性
	var checkbox4="2";//报价属性
	var checkbox5=1;//报价属性
	
	//查看报价
	var oldLimit;//用于检测更改时 是否真的更改、更新失败用于还原
	var cityId=null;//全局变量 用于保存当前选择的城市id
	var ptId;//全局变量 用于保存当前选择的报价类型id
	var curprice;//全局变量 用于保存当前展示的报价
	var day;//全局变量 保存pricetype中的day
	
	function clearForm(){//一键清空
		$(".offer2-select").val("");
		$("input[type='text']").val("");
	}
	
	$(document).ready(function() {
		check();
		setInputOnblur();
		$(".redClose").click(function(){
			$("#window").addClass("none");
		});
		$(".addcity").click(function(){
			initCity();
			if($(".citychoose").length == 10){
				 alert("贴现地址已经10个，不能添加");
				 return ;
			}
			
			$("#tjname").val("");
			$("#tjmobile").val("");
			$("#tjaddress").val("");
			$("#tjother").val("");
			$("#tjplace").val("");
			$("#tjlon").val("");
			$("#tjlat").val("");
			$("#tjid").val("");
			
			var p= "上海市";
	  	   	$("#initcity option[value='"+p+"']").attr("selected","selected");
			$("#window").removeClass("none");
		});
		$("#cityselect").change(function(){
			var city = $("#cityselect").val();
			if(city=="1"){
				$("#cityChose").addClass("none");
				$("#cityChose ").find("label").removeClass("cwhite bce84c3d");
				$("#cityChose ").find("input").attr("checked",false);
			}else{
				$("#cityChose").removeClass("none");
			}
		});
		
		/**
		* 添加交易城市，数据填写完成保存
		*/
		$("#baocunCity").click(function(){
			var address = $("#tjaddress").val();
			var city_id = $("#initcity").val();
			var longitude = $("#tjlon").val();
			var latitude = $("#tjlat").val();
			var id = $("#tjid").val();
			if(!$("#tjaddress").validationEngine("validate")){
				$("#tjaddress").focus();
				return ;
			}
			if(address=="" || $.trim(address).length == 0){
				alert("请填写你的交易地址");
				return ;
			}
			if($.trim(longitude).length==0 || $.trim(latitude).length==0){
				alert("贴现地址不全，请补充完整");
				return ;
			}
			$.ajax({
		 		type: "POST",
		      	url: "${basePath}/bisicmessage/save/orgcity",
		      	data: {address:address,cityId:city_id,longitude:longitude,latitude:latitude,id:id},
		      	dataType:"json",
		      	success:function(data){
		          	if(data.response == "success"){
		          		alert(data.msg);
		          		loadOrgCity();
		          		$("#window").addClass("none");
		          	}else{
		          		alert(data.msg);
		          	}
		     	}
			 });
			
		});
		$("input").keyup(function(){
	        var temp = $(this).val();
	        var id = $(this).attr("id");
	        if(id=="tjaddress" || id == "tjother"){
	        	
	        }else{
		        $(this).val(temp.replace(/[^0-9.]/g,''));
	        }
	    });
		$("#order1").click(function(){
			$("#order1").addClass("bcfc4d42 cwhite");
			$("#order2").removeClass("bcfc4d42 cwhite");
			$("#order3").removeClass("bcfc4d42 cwhite");
			$("#yinpiao1").removeClass("none");$("#yinpiao2").addClass("none");$("#yinpiao3").addClass("none");
			loadOrgLimit();//加载今日报价额度
		});
		$("#order2").click(function(){
			tiaozhuan();
		});
		$("#order3").click(function(){
			if(yinpiaoedu==null){ 
				alert("请先填写额度!");return;
			}
			$("#order3").addClass("bcfc4d42 cwhite");
			$("#order2").removeClass("bcfc4d42 cwhite");
			$("#order1").removeClass("bcfc4d42 cwhite");
			$("#yinpiao3").removeClass("none");$("#yinpiao2").addClass("none");$("#yinpiao1").addClass("none");
			cityId=null;
			getLimit();//加载报价额度
		});
		
		$(".checkbox1").click(function () {
			$(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
			$(this).children("label").addClass("cwhite bce84c3d");
			$(this).find("input").attr("checked",true);
			$("#cityselect").val(1);
			$("#cityChose").addClass("none");
			$(".citychoose").find("label").removeClass("cwhite bce84c3d");
			$(".citychoose").find("input").attr("checked",false);
			checkbox1=$(this).find("input").val();
		    setTab();
		});
		$(".checkbox3").change(function () {
			$(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
			$(this).children("label").addClass("cwhite bce84c3d");
			$(this).find("input").attr("checked",true);
			checkbox3=$(this).val();
		    setTab();
		});
		$(".checkbox4").click(function () {
			$(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
			$(this).children("label").addClass("cwhite bce84c3d");
			$(this).find("input").attr("checked",true);
			checkbox4=$(this).val();
		    setTab();
		});
		
		$(".checkbox5").click(function () {
			$(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
			$(this).children("label").addClass("cwhite bce84c3d");
			$(this).find("input").attr("checked",true);
			checkbox5=$(this).val();
		    setTab();
		});
		/*    小票纸票AB方式tab
	    $(".tabAB").click(function () {
	        $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cwhite');
	        $(this).parents("ul").children("li").removeClass("bce84c3d").addClass("bcf9f9f9");
	        $(this).parents("ul").children("li").children("label").removeClass("f20");
	        $(this).find("input").attr("checked",true);
	        $(this).removeClass("bcf9f9f9").addClass("bce84c3d");
	        $(this).children("a").children("label").removeClass("c2d2d2d").addClass('cwhite');
	        if($(this).find("input").val()=="1"){
	            $("#Adiv").addClass("none");
	            $("#Bdiv").removeClass("none");
	        }else{
	            $("#Bdiv").addClass("none");
	            $("#Adiv").removeClass("none");
	        }
	    })*/
		loadOrgLimit();//进入页面加载银票额度
		
		map = new BMap.Map("pcmap");
		var point = new BMap.Point(longitude,latitude);
		map.centerAndZoom(point,13);
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				setLngLat(r.point);
			}else {
				alert('failed'+this.getStatus());
			}
		},{enableHighAccuracy: true});
		
		map.enableScrollWheelZoom(true);
		map.disableDoubleClickZoom(true);
		map.addEventListener("click", addMarker);
		add_control();
	});
	
	function check(){
		if($("#guogu4").val()!="")
			$("#guogu34").removeAttr("readonly");
		else
			$("#guogu34").attr("readonly","readonly");
		if($("#chengshang4").val()!="")
			$("#chengshang34").removeAttr("readonly");
		else
			$("#chengshang34").attr("readonly","readonly");
		if($("#waizi4").val()!="")
			$("#waizi34").removeAttr("readonly");
		else
			$("#waizi34").attr("readonly","readonly");
		if($("#nongshang4").val()!="")
			$("#nongshang34").removeAttr("readonly");
		else
			$("#nongshang34").attr("readonly","readonly");
		if($("#nonghe4").val()!="")
			$("#nonghe34").removeAttr("readonly");
		else
			$("#nonghe34").attr("readonly","readonly");
		if($("#nongxin4").val()!="")
			$("#nongxin34").removeAttr("readonly");
		else
			$("#nongxin34").attr("readonly","readonly");
		if($("#cunzhen4").val()!="")
			$("#cunzhen34").removeAttr("readonly");
		else
			$("#cunzhen34").attr("readonly","readonly");
		if($("#guogu4").val()!="")
			$("#guogu34").removeAttr("readonly");
		else
			$("#guogu34").attr("readonly","readonly");
	}
	//跳转报价
	function tiaozhuan(){
		if(yinpiaoedu==null){ 
			alert("请先填写额度!");return;
		}
		$("#order2").addClass("bcfc4d42 cwhite");
		$("#order1").removeClass("bcfc4d42 cwhite");
		$("#order3").removeClass("bcfc4d42 cwhite");
		$("#yinpiao2").removeClass("none");$("#yinpiao1").addClass("none");$("#yinpiao3").addClass("none");
		loadOrgCity();//加载页面报价城市
	}
	
	//加载银票额度
	function loadOrgLimit(){
		$.ajax({
			url:"${basePath}/price/get/orglimit",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					$("#price").val(data.data.price);
					$("#remarkYp").val(data.data.remarkYp);
					yinpiaoedu=data.data.price;
				}else{
				}
			}
		});
	}
	
	//保存银票额度
	function saveOrgLimit(){ 
		if($("#tijiao1").attr("disabled")){
			return;
		}
		var price = $("#price").val();
		var remarkYp = $("#remarkYp").val();
		if(price==null || $.trim(price)==""){// 表单验证
			$("#price").validationEngine('showPrompt','* 额度不能为空',null,null,true);
			setTimeout(function(){$(".formError").addClass("none");},1500);
			return;
		}else{
			var r = /^([+]?\d{1,9})$/;
			if(!r.test(price)){
				$("#price").validationEngine('showPrompt','* 请输入9位内的正整数',null,null,true);
				setTimeout(function(){$(".formError").addClass("none");},1500);
				return;
			}
		}
		$("#tijiao1").attr("disabled","disabled");//按钮禁用
		$.ajax({
			url:"${basePath}/price/save/orglimit",
			type:"post",
			data:{"price":price,"remarkYp":remarkYp},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					$("#tijiao1").removeAttr("disabled");//按钮启用
					yinpiaoedu=price;
					//$(".formError").addClass("none");
					alert(data.msg);
					tiaozhuan();
				}else{
					$("#tijiao1").removeAttr("disabled");//按钮启用
					alert(data.msg);
				}
			}
		});
	}
	
	//加载城市
	function loadOrgCity(){
		$.ajax({
			url:"${basePath}/price/get/orgcity",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					var result = data.data;
					var html = '';
					var flag = true;
					for (x in result){
						var cId = result[x].city_id;
						if(result[x].cityId!=null){
							flag = false;
						}
						html += '<li class="fl ml15 citychoose"><input type="checkbox" id="city'+cId+'" class="none" name="cityId" value="'+cId+'"><label class="fl w80 h34 br3 bae0e0e0" for="city'+cId+'">'+result[x].name+'</li>';
					}
					if(flag)html = html;
					$("#cityChose").html(html);
				}else{
					alert(data.msg);
				}
				
				//银票报价城市样式变更
				$(".citychoose").click(function () {
					if($(this).find("input").attr("checked")){
						$(this).children("label").removeClass("cwhite bce84c3d");
						$(this).find("input").attr("checked",false);
					}else{
						$(this).children("label").addClass("cwhite bce84c3d");
						$(this).find("input").attr("checked",true);
					}
				})
				setTab();
			}
		});
	}
	
	//银票的各种报价属性改变时隐藏显示div,并查询该综合类型是否有城市报价
	function setTab(){
		var num1 = checkbox1;//银票类型
		var num3 = checkbox3;//票面金额
		var num4 = checkbox4;//天数
		var num5 = checkbox5;//期限
		if(num1==0){//大票电票
			$("#cityselect").removeClass("none");
			$("#dadian-flag").removeClass("none");
			$("#xiaozhi-flag").addClass("none");
			$("#xiaodian-flag").addClass("none");
			$("#type1").val("0");
			$("#type2").val("1");
			$("#type4").val("");
			$("#type5").val(num5);
			$("#cityAdd").addClass("none");
		}else if(num1==1){//小票纸票
			$("#cityAdd").removeClass("none");
			$("#cityselect").addClass("none");
			$("#cityChose").removeClass("none");
			$("#xiaozhi-flag").removeClass("none");
			$("#dadian-flag").addClass("none");
			$("#cityselect").find()
			$("#type1").val("1");$("#type2").val("0");$("#type3").val(num3);$("#type4").val("2");
			$("#xiaodian-flag").addClass("none");
			$("#type5").val("");
		}else if(num1==2){//小票电票
			$("#cityAdd").addClass("none");
			$("#cityselect").removeClass("none");
			$("#xiaodian-flag").removeClass("none");
			$("#xiaozhi-flag").addClass("none");
			$("#dadian-flag").addClass("none");
			$("#type1").val("1");$("#type2").val("1");$("#type3").val(num3);$("#type4").val(num4);
			$("#type5").val("");
		}
		
		if(num1==0){
			selectTab("dd");
		}
		else if(num1==1){
			selectTab("xz");
		}	
		else if(num1==2){
			selectTab("xd");
		}
	}
	
	/*加载已报价的城市，可以进行直接报价
	function initData(str){
		var diantype1 = $('#diantype1').val();
		var zhitype2 = $('#zhitype2').val();
		var zhitype3 = $('#zhitype3').val();
		var type1,type2,type3;
		if(diantype1=="0"){//大票电票
			type = 0;type2 = 1;
		}else if(zhitype2=="1"){//小票纸票
			type = 0;type2 = 1;
		}else if(zhitype3=="2"){//小票电票
			type = 0;type2 = 1;
		}
		var type4 = $('#type4').val();
		var type5 = $('#type5').val();
		if(type1=="")type1=null;
		if(type2=="")type2=null;
		if(type3=="")type3=null;
		if(type4=="")type4=null;
		if(type5=="")type5=null;
			
		$.ajax({
			url:"${basePath}/price/get/haspricecity",
			type:"post",
			data:{"type1":type1,"type2":type2,"type3":type3,"type4":type4,"type5":type5},
			dataType:"json",
			success:function(data){
				var html = '<option value="">=请选择=</option>';
				var result = data.data;
				if(result!=null){
					for (x in result){
						html += '<option value="'+result[x].id+'">'+result[x].name+'</option>';
					}
				}
				$(".offer2-select").html(html);
				$("#pricetype").val(str);
			}
		});
	}*/
	
	//直接采用报价城市的价格
	function getByCity(tag){
		var priceId = $(tag).val();
		if($("#pricetype").val()!=null)
		var pricetype = $("#pricetype").val();
		if(priceId!=null && $.trim(priceId)!=""){
			$.ajax({
				url:"${basePath}/price/get/price/by/id",
				type:"post",
				data:{"priceId":priceId},
				dataType:"json",
				success:function(data){
					var p = data.data;
					if(p!=null){
						if(pricetype=="typ1"){
							$("#guogu1").val(p.guogu);$("#waizi1").val(p.waizi);$("#nongshang1").val(p.nongshang);$("#nongxin1").val(p.nongxin);
							$("#chengshang1").val(p.chengshang);$("#dashang1").val(p.dashang);$("#nonghe1").val(p.nonghe);$("#cunzhen1").val(p.cunzhen);
						}else if(pricetype=="typ2"){
							$("#guogu2").val(p.guogu);$("#waizi2").val(p.waizi);$("#nongshang2").val(p.nongshang);$("#nongxin2").val(p.nongxin);
							$("#chengshang2").val(p.chengshang);$("#dashang2").val(p.dashang);$("#nonghe2").val(p.nonghe);$("#cunzhen2").val(p.cunzhen);
						}else if(pricetype=="typ3"){
							$("#guogu3").val(p.guogu);$("#waizi3").val(p.waizi);$("#nongshang3").val(p.nongshang);$("#nongxin3").val(p.nongxin);
							$("#chengshang3").val(p.chengshang);$("#nonghe3").val(p.nonghe);$("#cunzhen3").val(p.cunzhen);
						}else{
							$("#guogu4").val(p.guogu);$("#guogu14").val(p.guogu1);$("#guogu24").val(p.guogu2);
							$("#chengshang4").val(p.chengshang);$("#chengshang14").val(p.chengshang1);$("#chengshang24").val(p.chengshang2);
							$("#waizi4").val(p.waizi);$("#waizi14").val(p.waizi1);$("#waizi24").val(p.waizi2);
							$("#nongshang4").val(p.nongshang);$("#nongshang14").val(p.nongshang1);$("#nongshang24").val(p.nongshang2);
							$("#nonghe4").val(p.nonghe);$("#nonghe14").val(p.nonghe1);$("#nonghe24").val(p.nonghe2);
							$("#nongxin4").val(p.nongxin);$("#nongxin14").val(p.nongxin1);$("#nongxin24").val(p.nongxin2);
							$("#cunzhen4").val(p.cunzhen);$("#cunzhen14").val(p.cunzhen1);$("#cunzhen24").val(p.cunzhen2);
						}
					}
				}
			});
		}
	}
	
	//根据报价方式显示报价
	function selectTab(e){
		if(e=="xz"){//小纸票
			$(".xiaozhi").removeClass("none");
			$(".xiaodian").addClass("none");
			$(".dadian").addClass("none");
		}else if(e=="xd"){//小电票
			$(".xiaodian").removeClass("none");
			$(".xiaozhi").addClass("none");
			$(".dadian").addClass("none");
		}else if(e=="dd"){//大电票
			$(".dadian").removeClass("none");
			$(".xiaozhi").addClass("none");
			$(".xiaodian").addClass("none");
		}
		
	}
	$(function(){
		setTimeout(function(){$(".formError").addClass("none");},1500);
	});
	//保存银票报价
	function savePrice(num){
		//报价城市
		var cityid = 1;
		/* $(".citychoose").each(function(){
			if($(this).find("input").attr("checked")=="checked"){
				if(cityid!=""){
					cityid += "," + ($(this).find("input").val());
				}else{
					cityid += ($(this).find("input").val());
				}
			}
    	}) */
		//var way=null;  无B方式
		var type1 = $('#type1').val();
		var type2 = $('#type2').val();
		var type3 = $('#type3').val();
		var type4 = $('#type4').val();
		var type5 = $('#type5').val(); 
		if(type1=="")type1=null;
		if(type2=="")type2=null;
		if(type3=="")type3=null;
		if(type4=="")type4=null;
		if(type5=="")type5=null;
		/*if(cityid=="")cityid=null;
		 if($("#cityselect").val() ==2){
    		if(cityid == null){
				alert("请选择报价城市！");
				return;
			}
    	} */
		var guogu,guogu1,guogu2,guogu3,dashang,dashang1,dashang2,chengshang,chengshang1,chengshang2,chengshang3,waizi,waizi1,waizi2,waizi3,nonghe,nonghe1,nonghe2,nonghe3,nongxin,nongxin1,nongxin2,nongxin3;
		var nongshang,nongshang1,nongshang2,nongshang3,cunzhen,cunzhen1,cunzhen2,cunzhen3;
		if(!$("#guogu"+num).validationEngine("validate")){
			$("#guogu"+num).focus();
			return;
		}
	    if(!$("#chengshang"+num).validationEngine("validate")){
			$("#chengshang"+num).focus();
			return;
		}
	    if(!$("#waizi"+num).validationEngine("validate")){
			$("#waizi"+num).focus();
			return;
		}
	    if(!$("#nongshang"+num).validationEngine("validate")){
			$("#nongshang"+num).focus();
			return;
		}
	    if(!$("#nonghe"+num).validationEngine("validate")){
			$("#nonghe"+num).focus();
			return;
		}
	    if(!$("#nongxin"+num).validationEngine("validate")){
			$("#nongxin"+num).focus();
			return;
		}
	    if(!$("#cunzhen"+num).validationEngine("validate")){
			$("#cunzhen"+num).focus();
			return;
		}
		var guogu = $("#guogu"+num).val();
		var chengshang = $("#chengshang"+num).val();
		var waizi = $("#waizi"+num).val();
		var nongshang = $("#nongshang"+num).val();
		var nonghe = $("#nonghe"+num).val();
		var nongxin = $("#nongxin"+num).val();
		var cunzhen = $("#cunzhen"+num).val();
		if(num!=3 && num!=4){
			if(!$("#dashang"+num).validationEngine("validate")){
				$("#dashang"+num).focus();
				return;
			}
			var dashang = $("#dashang"+num).val();
		}
		if(num==3){	//小电票
		    if(!$("#guogu1"+num).validationEngine("validate")){
				$("#guogu1"+num).focus();
				return;
			}
		    if(!$("#chengshang1"+num).validationEngine("validate")){
				$("#chengshang1"+num).focus();
				return;
			}
		    if(!$("#waizi1"+num).validationEngine("validate")){
				$("#waizi1"+num).focus();
				return;
			}
		    if(!$("#nongshang1"+num).validationEngine("validate")){
				$("#nongshang1"+num).focus();
				return;
			}
		    if(!$("#nonghe1"+num).validationEngine("validate")){
				$("#nonghe1"+num).focus();
				return;
			}
		    if(!$("#nongxin1"+num).validationEngine("validate")){
				$("#nongxin1"+num).focus();
				return;
			}
		    if(!$("#cunzhen1"+num).validationEngine("validate")){
				$("#cunzhen1"+num).focus();
				return;
			}
			var guogu1 = $("#guogu1"+num).val();
			var chengshang1 = $("#chengshang1"+num).val();
			var waizi1 = $("#waizi1"+num).val();
			var nongshang1 = $("#nongshang1"+num).val();
			var nonghe1 = $("#nonghe1"+num).val();
			var nongxin1 = $("#nongxin1"+num).val();
			var cunzhen1 = $("#cunzhen1"+num).val();
		}else if(num==4){//小纸票
			/* if(cityid == null){
				alert("请选择报价城市！");
				return;
			} */
		    if(!$("#guogu1"+num).validationEngine("validate")){
				$("#guogu1"+num).focus();
				return;
			}
		    if(!$("#chengshang1"+num).validationEngine("validate")){
				$("#chengshang1"+num).focus();
				return;
			}
		    if(!$("#waizi1"+num).validationEngine("validate")){
				$("#waizi1"+num).focus();
				return;
			}
		    if(!$("#nongshang1"+num).validationEngine("validate")){
				$("#nongshang1"+num).focus();
				return;
			}
		    if(!$("#nonghe1"+num).validationEngine("validate")){
				$("#nonghe1"+num).focus();
				return;
			}
		    if(!$("#nongxin1"+num).validationEngine("validate")){
				$("#nongxin1"+num).focus();
				return;
			}
		    if(!$("#cunzhen1"+num).validationEngine("validate")){
				$("#cunzhen1"+num).focus();
				return;
			}
			//足月票价
			var guogu1 = $("#guogu1"+num).val();
			var chengshang1 = $("#chengshang1"+num).val();
			var waizi1 = $("#waizi1"+num).val();
			var nongshang1 = $("#nongshang1"+num).val();
			var nonghe1 = $("#nonghe1"+num).val();
			var nongxin1 = $("#nongxin1"+num).val();
			var cunzhen1 = $("#cunzhen1"+num).val();
			var guogu2 = $("#guogu2"+num).val();
			var chengshang2 = $("#chengshang2"+num).val();
			var waizi2 = $("#waizi2"+num).val();
			var nongshang2 = $("#nongshang2"+num).val();
			var nonghe2 = $("#nonghe2"+num).val();
			var nongxin2 = $("#nongxin2"+num).val();
			var cunzhen2 = $("#cunzhen2"+num).val();
			var guogu3 = $("#guogu3"+num).val();
			var chengshang3 = $("#chengshang3"+num).val();
			var waizi3 = $("#waizi3"+num).val();
			var nongshang3 = $("#nongshang3"+num).val();
			var nonghe3 = $("#nonghe3"+num).val();
			var nongxin3 = $("#nongxin3"+num).val();
			var cunzhen3 = $("#cunzhen3"+num).val();
			if(eval(guogu2) > eval(guogu3)){
				guogu3 = guogu3;
			}else{
				guogu3 = guogu2;
			}
			if(eval(chengshang2) > eval(chengshang3))
				chengshang3 = chengshang3;
			else
				chengshang3 = chengshang2;
			if(eval(waizi2) > eval(waizi3))
				waizi3 =waizi3;
			else
				waizi3 = waizi2;
			if(eval(nongshang2) > eval(nongshang3))
				nongshang3 = nongshang3;
			else
				nongshang3 = nongshang2; 
			if(eval(nonghe2) > eval(nonghe3))
				nonghe3 = nonghe3;
			else
				nonghe3 = nonghe2;
			if(eval(nongxin2) > eval(nongxin3))
				nongxin3 = nongxin3;
			else
				nongxin3 = nongxin2;
			if(eval(cunzhen2) > eval(cunzhen3))
				var cunzhen3 = cunzhen3;
			else
				var cunzhen3 = cunzhen2;
		}
		if(guogu == "" && chengshang == "" && waizi == "" && nongshang == "" && nonghe == "" && nongxin == "" && cunzhen == "" && (dashang == "" || dashang == null)){
			alert("请至少选择一个承兑行进行报价");
			return;
		}
		$.ajax({
			url:"${basePath}/price/save/price",
			type:"post",
			data:{"cityIds":cityid,"type1":type1,"type2":type2,"type3":type3,"type4":type4,"type5":type5,"guogu":guogu,"guogu1":guogu1,"guogu2":guogu2,"guogu3":guogu3,"chengshang":chengshang,"chengshang1":chengshang1,"chengshang2":chengshang2,"chengshang3":chengshang3,"dashang":dashang,"dashang1":dashang1,"dashang2":dashang2,"waizi":waizi,"waizi1":waizi1,"waizi2":waizi2,"waizi3":waizi3,"nongshang":nongshang,"nongshang1":nongshang1,"nongshang2":nongshang2,"nongshang3":nongshang3,"nonghe":nonghe,"nonghe1":nonghe1,"nonghe2":nonghe2,"nonghe3":nonghe3,"nongxin":nongxin,"nongxin1":nongxin1,"nongxin2":nongxin2,"nongxin3":nongxin3,"cunzhen":cunzhen,"cunzhen1":cunzhen1,"cunzhen2":cunzhen2,"cunzhen3":cunzhen3},
			dataType:"json",
			success:function(data){
				alert(data.msg);
				clearForm();
			}
		});
	}
	
//第三部分查看报价
	

	//获取额度
	function getLimit(){
		$.ajax({
			url:"${basePath}/price/get/orglimit",
			type:"post",
			data:{},
			dataType:"text",
			success:function(data){
				var res = JSON.parse(data);
				if(res.response=="success"){
					$("#limit").val(res.data.price);//设置额度
					oldLimit=res.data.price;
				}else{
					alert(res.msg);
				}
				getOrgCity();//加载已经报价的城市
			}
		});
	}
	
	//修改额度
	function saveLimit(){
		var limit = $("#limit").val();
		if(limit==null || $.trim(limit)==""){// 表单验证
			$("#limit").validationEngine('showPrompt','* 额度不能为空',null,null,true);
			setTimeout(function(){$(".formError").addClass("none");},1500);
			return;
		}else{
			var r = /^([+]?\d{1,9})$/;
			if(!r.test(limit)){
				$("#limit").validationEngine('showPrompt','* 请输入正确的额度',null,null,true);
				setTimeout(function(){$(".formError").addClass("none");},1500);
				return;
			}
		}
		if(limit==oldLimit)	return;//额度未发生变化
		$.ajax({
			url:"${basePath}/price/save/orglimit",
			type:"post",
			data:{'price':limit},
			dataType:"text",
			success:function(data){
				var res = JSON.parse(data);
				if(res.response=="success"){
					alert(res.msg);
				}else{
					alert(res.msg);
					$("#limit").val(oldLimit);//还原额度
				}
			}
		});
	}
	
	//获取报价城市列表
	function getOrgCity(){
		$.ajax({
			url:"${basePath}/price/get/orgcity",
			type:"post",
			data:{},
			dataType:"text",
			success:function(data){
				var res = JSON.parse(data);
				if(res.response=="success"){
					//setCity(res.data); 设置城市部分
					getPriceTypes(1);//默认选中第一城市加载报价
				}else{
					alert(res.msg);
				}
			}
		});
	}
	
	//设置报价城市
	function setCity(data){
		var html='<li class="fl ml15 checkbox10"><input type="radio" id="city10000" name="checkbox10" class="none" checked="checked" ><label class="fl w80 h34 br3 bae0e0e0 cwhite bce84c3d" for="city10000"  onclick="getPriceTypes(10000)">全国</label></li>';
		if(data.length==0){//该机构无报价城市
			html = '您还没有设置报价城市';
		}else{//有城市列出所有城市默认选择第一个城市
			for(var i=0;i<data.length;i++){
				var cityInfo = data[i];
				var flag = cityInfo.cityId;
				var cId = cityInfo.city_id;
				if(cityId==null && flag!=null){
					html+='<li class="fl ml15 checkbox10"><input type="radio" id="city'+cId+'" name="checkbox10" class="none " checked="checked" ><label class="fl w80 h34 br3 bae0e0e0" for="city'+cId+'"  onclick="getPriceTypes(' +cityInfo.cityId+ ')">' +cityInfo.name+'</label></li>';
					cityId = 10000;
				}else if(cityId!=null && flag!=null){
					html+='<li class="fl ml15 checkbox10"><input type="radio" id="city'+cId+'" name="checkbox10" class="none " ><label class="fl w80 h34 br3 bae0e0e0 " for="city'+cId+'" onclick="getPriceTypes(' +cityInfo.cityId+ ')">' +cityInfo.name+'</label></li>';
					cityId = 10000;
				}
			}
		}
		$("#cityList").html(html);
		 //地址
	    $(".checkbox10").click(function () {
	        $(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
	        $(this).find("input").attr("checked",true);
	        $(this).children("label").addClass("cwhite bce84c3d");
	    })
		getPriceTypes(cityId);//默认选中第一城市加载报价
	}
    
	//获取所有已报价类型
	function getPriceTypes(curId){
		if(curId==null){
			return;
		}
    	cityId = curId;
    	$.ajax({
    		url:"${basePath}/price/get/pricetypes",
    		type:"post",
    		data:{'cityId':cityId},
    		dataType:"text",
    		success:function(data){
    			var res = JSON.parse(data);
    			if(res.response=="success"){
    				setLeftMenu(res.priceTypes);
    			}else{
    				var html = '';
    				$("#spOffer").html(html);//清空杂项
    				$(".priceTable").addClass("none");
    				$(".operateButton").addClass("none");
    				ptId=null;//全局变量置空
    				curprice=null;
    				if(true==res.noPrice){//本机构今天还没有报过价
    					alert(res.msg);
    					tiaozhuan();
    				}
    			}
    		}
    	});
		
	}
	 
    //设置今日报价类型菜单
    function setLeftMenu(data){
    	var html = '';
    	$("#spOffer").html(html);//先清空左侧菜单
    	if(data.length==0){//该机构今日无报价
    		html = '';//？？
    	}else{//该机构有报价
    		for(var i=0;i<data.length;i++){
    			var menu = data[i];
    			if(i==0){//默认选择第一个
    				html+='<li class="w150 h100 bbe0e0e0 lh30 col1-title bce84c3d cwhite" onclick="setCurMenu('+menu.ptId+',this)">'+menu.title+'</li>';
    				ptId = menu.ptId;
    			}else{
    				html+='<li class="w150 h100 bbe0e0e0 lh30 col1-title" onclick="setCurMenu('+menu.ptId+',this)">'+menu.title+'</li>';
    			}
    		}
    		setCurMenu(ptId,null);//加载第一种类型的报价
    	}
    	$("#spOffer").html(html);
    }
    
    //选择菜单变化执行方法
    function setCurMenu(curId,obj){
    	if(obj!=null){
    		$(".col1-title").removeClass("bce84c3d cwhite")
    		$(obj).addClass("bce84c3d cwhite");
    	}
    	ptId = curId;
    	//请求day
    	$.ajax({
    		url:"${basePath}/price/get/daybyptid",
    		type:"post",
    		data: {"ptId": ptId},
    		dataType:"text",
    		success:function(data){
    			var res = JSON.parse(data);
    			if(res.response=="success"){
    				day=res.day;//获取到的day
    			}
    		}
    	});
    	getPrice();//重新加载报价
    }
    
  //获取选定的报价
    function getPrice(){
    	$.ajax({
    		url:"${basePath}/price/get/price",
    		type:"post",
    		data:{'cityId':cityId,'ptId':ptId},
    		dataType:"text",
    		success:function(data){
    			var res = JSON.parse(data);
    			if(res.response=="success"){
    				setPrice(res);
    			}else{
    				alert(res.msg);
    			}
    		}
    	});
    }
  
  //设置显示报价
    function setPrice(data){
    	var pt = data.priceType;
    	var price = data.price;
    	curprice = price;
    	$(".priceTable").removeClass("none");//数据区域
    	$(".operateButton").removeClass("none");//按钮区域
    	clearAll();//首先清空
    	if(price.priceTypeId==2 || price.priceTypeId==21){//大票
    		$(".price").removeClass("none");//所有利率显示
    		$(".price1").addClass("none");//所有参数1隐藏
    		$(".price2").addClass("none");//所有每10W隐藏
    		$("#dashang").removeClass("none");//大商显示
    		$("#chengshangTitle").text("小商：");//小商标题
    		if(pt.type1==0 && pt.type2==0){//纸票 大票 显示（月利率 ‰） 
    			$(".priceSign").text("‰");//所有符号设置为‰
    			$(".priceTitle").text("月利率");
    		}else if(pt.type1==0 && pt.type2==1){//电票 大票 显示 （年利率 %）
    			$(".priceSign").text("%");//所有符号设置为%
    			$(".priceTitle").text("年利率");
    		}else{
    			$("#dashang").addClass("none");//大商隐藏
    			$(".priceSign").text("%");//所有符号设置为%
    			$(".priceTitle").text("年利率");
    			$("#chengshangTitle").text("城商：");//显示为城商
    		}
    		
    		$("#guoguPrice").val(price.guogu);//8类型设置数值
    		$("#dashangPrice").val(price.dashang);
    		$("#chengshangPrice").val(price.chengshang);
    		$("#waiziPrice").val(price.waizi);
    		$("#nongshangPrice").val(price.nongshang);
    		$("#nonghePrice").val(price.nonghe);
    		$("#nongxinPrice").val(price.nongxin);
    		$("#cunzhenPrice").val(price.cunzhen);
    	}else if(price.priceTypeId==5 || price.priceTypeId==8 || price.priceTypeId==11){//月利率+参数
    		$(".price").removeClass("none");//所有利率显示
    		$(".price1").removeClass("none");//所有参数1显示
    		$(".price2").removeClass("none");
    		$("#dashang").addClass("none");//大商隐藏
    		$(".priceSign").text("‰");//所有符号设置为‰
    		$(".priceTitle").text("月利率");
    		$("#chengshangTitle").text("城商：");//显示为城商
    		
    		$("#guoguPrice").val(price.guogu);//7类型设置数值
    		$("#chengshangPrice").val(price.chengshang);
    		$("#waiziPrice").val(price.waizi);
    		$("#nongshangPrice").val(price.nongshang);
    		$("#nonghePrice").val(price.nonghe);
    		$("#nongxinPrice").val(price.nongxin);
    		$("#cunzhenPrice").val(price.cunzhen);
    		$("#guoguPrice1").val(price.guogu1);//7参数1设置数值
    		$("#chengshangPrice1").val(price.chengshang1);
    		$("#waiziPrice1").val(price.waizi1);
    		$("#nongshangPrice1").val(price.nongshang1);
    		$("#nonghePrice1").val(price.nonghe1);
    		$("#nongxinPrice1").val(price.nongxin1);
    		$("#cunzhenPrice1").val(price.cunzhen1);
    		$("#guoguPrice3").val(price.guogu3);//7参数1设置数值
    		$("#chengshangPrice3").val(price.chengshang3);
    		$("#waiziPrice3").val(price.waizi3);
    		$("#nongshangPrice3").val(price.nongshang3);
    		$("#nonghePrice3").val(price.nonghe3);
    		$("#nongxinPrice3").val(price.nongxin3);
    		$("#cunzhenPrice3").val(price.cunzhen3);
    	}else{//每十万贴现成本
    		$(".price").removeClass("none");//所有利率显示
    		$(".price1").removeClass("none");//所有参数1显示
    		$(".price2").addClass("none");//所有每10W隐藏
    		$("#dashang").addClass("none");//大商隐藏
    		$(".priceSign").text("‰");//所有符号设置为‰
    		$(".priceTitle").text("月利率");
    		$("#chengshangTitle").text("城商：");//显示为城商
    		
    		$("#guoguPrice").val(price.guogu);//7类型设置数值
    		$("#chengshangPrice").val(price.chengshang);
    		$("#waiziPrice").val(price.waizi);
    		$("#nongshangPrice").val(price.nongshang);
    		$("#nonghePrice").val(price.nonghe);
    		$("#nongxinPrice").val(price.nongxin);
    		$("#cunzhenPrice").val(price.cunzhen);
    		$("#guoguPrice1").val(price.guogu1);//7参数1设置数值
    		$("#chengshangPrice1").val(price.chengshang1);
    		$("#waiziPrice1").val(price.waizi1);
    		$("#nongshangPrice1").val(price.nongshang1);
    		$("#nonghePrice1").val(price.nonghe1);
    		$("#nongxinPrice1").val(price.nongxin1);
    		$("#cunzhenPrice1").val(price.cunzhen1);
    	}
    }
  
  //一键清空
    function clearAll(){
    	$("#guoguPrice").val('');
    	$("#dashangPrice").val('');
    	$("#chengshangPrice").val('');
    	$("#waiziPrice").val('');
    	$("#nongshangPrice").val('');
    	$("#nonghePrice").val('');
    	$("#nongxinPrice").val('');
    	$("#cunzhenPrice").val('');
    	$("#guoguPrice1").val('');
    	$("#dashangPrice1").val('');
    	$("#chengshangPrice1").val('');
    	$("#waiziPrice1").val('');
    	$("#nongshangPrice1").val('');
    	$("#nonghePrice1").val('');
    	$("#nongxinPrice1").val('');
    	$("#cunzhenPrice1").val('');
    	$("#guoguPrice2").val('');
    	$("#dashangPrice2").val('');
    	$("#chengshangPrice2").val('');
    	$("#waiziPrice2").val('');
    	$("#nongshangPrice2").val('');
    	$("#nonghePrice2").val('');
    	$("#nongxinPrice2").val('');
    	$("#cunzhenPrice2").val('');
    }
  
    //更新报价
    function update(){
    	curprice.cityId = cityId;
    	if(!$("#guoguPrice").validationEngine("validate")){
			$("#guoguPrice").focus();
			return;
		}
    	if(!$("#dashangPrice").validationEngine("validate")){
			$("#dashangPrice").focus();
			return;
		}
    	if(!$("#chengshangPrice").validationEngine("validate")){
			$("#chengshangPrice").focus();
			return;
		}
    	if(!$("#waiziPrice").validationEngine("validate")){
			$("#waiziPrice").focus();
			return;
		}
    	if(!$("#nongshangPrice").validationEngine("validate")){
			$("#nongshangPrice").focus();
			return;
		}
    	if(!$("#nonghePrice").validationEngine("validate")){
			$("#nonghePrice").focus();
			return;
		}
    	if(!$("#nongxinPrice").validationEngine("validate")){
			$("#nongxinPrice").focus();
			return;
		}
    	if(!$("#cunzhenPrice").validationEngine("validate")){
			$("#cunzhenPrice").focus();
			return;
		}
    	if(ptId==2||ptId==21){//当前修改的是大票
    		curprice.guogu=$("#guoguPrice").val();//仅保存利率
    		curprice.dashang=$("#dashangPrice").val();
    		curprice.chengshang=$("#chengshangPrice").val();
    		curprice.waizi=$("#waiziPrice").val();
    		curprice.nongshang=$("#nongshangPrice").val();
    		curprice.nonghe=$("#nonghePrice").val();
    		curprice.nongxin=$("#nongxinPrice").val();
    		curprice.cunzhen=$("#cunzhenPrice").val();
    	}else if(ptId==5 || ptId==8 || ptId==11){//当前修改的是小纸 票
    			curprice.guogu=$("#guoguPrice").val();//设置月利率
    			curprice.chengshang=$("#chengshangPrice").val();
    			curprice.waizi=$("#waiziPrice").val();
    			curprice.nongshang=$("#nongshangPrice").val();
    			curprice.nonghe=$("#nonghePrice").val();
    			curprice.nongxin=$("#nongxinPrice").val();
    			curprice.cunzhen=$("#cunzhenPrice").val();
    			curprice.guogu1=$("#guoguPrice1").val();//设置参数1
    			curprice.chengshang1=$("#chengshangPrice1").val();
    			curprice.waizi1=$("#waiziPrice1").val();
    			curprice.nongshang1=$("#nongshangPrice1").val();
    			curprice.nonghe1=$("#nonghePrice1").val();
    			curprice.nongxin1=$("#nongxinPrice1").val();
    			curprice.cunzhen1=$("#cunzhenPrice1").val();
    			computePrice2(curprice);//计算每十万贴息
    			curprice.guogu3=$("#guoguPrice3").val();//设置price3
    			curprice.chengshang3=$("#chengshangPrice3").val();
    			curprice.waizi3=$("#waiziPrice3").val();
    			curprice.nongshang3=$("#nongshangPrice3").val();
    			curprice.nonghe3=$("#nonghePrice3").val();
    			curprice.nongxin3=$("#nongxinPrice3").val();
    			curprice.cunzhen3=$("#cunzhenPrice3").val();
    	}else{
    			curprice.guogu=$("#guoguPrice").val();//设置月利率
    			curprice.chengshang=$("#chengshangPrice").val();
    			curprice.waizi=$("#waiziPrice").val();
    			curprice.nongshang=$("#nongshangPrice").val();
    			curprice.nonghe=$("#nonghePrice").val();
    			curprice.nongxin=$("#nongxinPrice").val();
    			curprice.cunzhen=$("#cunzhenPrice").val();
    			curprice.guogu1=$("#guoguPrice1").val();//设置参数1
    			curprice.chengshang1=$("#chengshangPrice1").val();
    			curprice.waizi1=$("#waiziPrice1").val();
    			curprice.nongshang1=$("#nongshangPrice1").val();
    			curprice.nonghe1=$("#nonghePrice1").val();
    			curprice.nongxin1=$("#nongxinPrice1").val();
    			curprice.cunzhen1=$("#cunzhenPrice1").val();
    			
    			computePrice2(curprice);//计算每十万贴息
    	}
    	var str = JSON.stringify(curprice);
    	//请求后台更新数据
    	$.ajax({
    		url:"${basePath}/price/update/price",
    		type:"post",
    		data: {"priceStr": str, "cityId": cityId},
    		dataType:"text",
    		success:function(data){
    			var res = JSON.parse(data);
    			if(res.response=="success"){
    				alert(res.msg);
    				
    			}else{
    				alert(res.msg);
    			}
    		}
    	});
    }
    
    function setInputOnblur() {
		$("#guogu4, #chengshang4, #waizi4, #nongshang4, #nongxin4, #nonghe4, #cunzhen4").bind("blur", function(){
			countPrice(this);
			check();
		});
		$("#guogu14, #chengshang14, #waizi14, #nongshang14, #nongxin14, #nonghe14, #cunzhen14").bind("blur", function(){
			countPrice1(this);
		});
	}
	
	function countPrice(obj){	// type_ 是用来取class的名称的（通过截取来获得）
		var className = $(obj).attr("id");		//该属性的id名称 -- 用于找到应往哪个地方赋值
		countType(obj, "A", className);
	}
	
	function countPrice1(obj){
		var className = $(obj).attr("id");		//该属性的id名称 -- 用于找到应往哪个地方赋值
		className = className.substring(0, className.length-1);
		countType(obj, "A", className);
	}
	
	function countType(obj, style, className) {
		 className = className.substring(0, className.length-1);
         var rate = parseFloat($("#" + className + "4").val());
         if (isNaN(rate) || rate == "" || rate == undefined) {
	         	rate = 0;
	     }
	     var money = parseFloat($("#" + className + "14").val());		//B方式要用户输入的每10W多少钱，算出利率
	     if (money == "" || money == undefined || isNaN(money)) {
	         money = 0;
	     }
	     var countResult = (100000 * 187 * ((rate / 30) / 1000) + parseFloat(money)).toFixed(2);
	     $("#"+className+"24").val(countResult);
	     $("#"+className+"34").val(countResult);
    }
    
    //计算月利率  对于以 每十万贴息报价
    function computePrice(price){
    	price.guogu=compute(day,price.guogu2);//用下面的方法计算利率 ，调用此方法前，guogu2已经设置为页面值 ，下同
    	price.chengshang=compute(day,price.chengshang2);
    	price.waizi=compute(day,price.waizi2);
    	price.nongshang=compute(day,price.nongshang2);
    	price.nonghe=compute(day,price.nonghe2);
    	price.nongxin=compute(day,price.nongxin2);
    	price.cunzhen=compute(day,price.cunzhen2);
    }

    //计算月利率 （子方法） 例: guogu = (guogu2 * 30 * 1000) / 100000 / day;
    function compute(day,price2){
    	var result = (price2 * 30 * 1000) / 100000 / day ;
    	return Math.round(result *100)/100;
    }
	
	
    //计算每十万贴息   对于以 月利率+参数1报价
    function computePrice2(price){
    	price.guogu2=compute2(day,price.guogu,price.guogu1);//用下面的方法计算利率 ，调用此方法前，guogu、guogu1已经设置为页面值 ，下同
    	price.chengshang2=compute2(day,price.chengshang,price.chengshang1);
    	price.waizi2=compute2(day,price.waizi,price.waizi1);
    	price.nongshang2=compute2(day,price.nongshang,price.nongshang1);
    	price.nonghe2=compute2(day,price.nonghe,price.nonghe1);
    	price.nongxin2=compute2(day,price.nongxin,price.nongxin1);
    	price.cunzhen2=compute2(day,price.cunzhen,price.cunzhen1);
    }

    //计算每十万贴息（子方法）   例：guogu2= (100000 * day * (guogu / 30) / 1000) + guogu1
    function compute2(day,price,price1){
    	var result = (100000 * day * (price / 30) / 1000) + price1;
    	return Math.round(result *100)/100;
    }
    
    /**
	* 初始化地图的城市选择
	*/
	function initCity(){
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/bisicmessage/init/city",
	      	data: {},
	      	dataType:"json",
	      	success:function(data){
	          	if(data.response == "success"){
	          		var phtml = "";
	                for(var i=0;i<data.c.length;i++){
	                    var provice = data.c[i];
	                    phtml += "<option value='"+provice.id+"'>"+provice.name+"</option>";
	                }
	                $("#initcity").html(phtml);
	          	}
	     	}
	 	 });
	};
	
	/**
	 * 添加控件和比例尺
	 */
	function add_control(){
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
	}

	/**
	 * 设置标记
	 */
	function addMarker(e){
		map.clearOverlays();
		
		var point = new BMap.Point(e.point.lng, e.point.lat);
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		setLngLat(e.point);
		var gc = new BMap.Geocoder();
		gc.getLocation(point, function(rs){
		var addComp = rs.addressComponents;
			$("#tjaddress").val(addComp.province + addComp.city + addComp.district +  addComp.street + addComp.streetNumber);
		});
	}

	/**
	 * 设置经纬度
	 */
	function setLngLat(point){
		longitude = point.lng;//经度
		latitude = point.lat;//纬度
		$("#tjlon").val(longitude);
		$("#tjlat").val(latitude);
	}

	/**
	 * 城市定位
	 */
	function myToCity(){
		map.clearOverlays();
		var city = document.getElementById("tjaddress").value;
		if(city != ""){
			map.centerAndZoom(city,13);//用城市名设置地图中心点
		}
		var geocoder = new BMap.Geocoder();  
		//获取起始地址经纬度  
		geocoder.getPoint(city,  function(point){  
            if(point){  
                longitude = point.lng;  
                latitude = point.lat;
                var new_point = new BMap.Point(longitude,latitude);
                var marker = new BMap.Marker(new_point);//创建标注
                map.addOverlay(marker);//将标注添加到地图中
                setLngLat(new_point);
            }  
		});  
	 };



	//建立一个自动完成的对象
	var ac = new BMap.Autocomplete({
		"input" : "tjaddress",
		"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {//鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district + _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district + _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {//鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
		$("#tjaddress").val(myValue);
		myToCity();
	});

	function setPlace(){
		map.clearOverlays();//清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;//获取第一个智能搜索的结果
			map.centerAndZoom(pp, 13);
			map.addOverlay(new BMap.Marker(pp));//添加标注
			setLngLat(pp);
		}
		var local = new BMap.LocalSearch(map, {//智能搜索
			onSearchComplete: myFun
		});
		local.search(myValue);
	}

	function G(id) {
		return document.getElementById(id);
	}

	//用经纬度设置地图中心点
	function myToLngLat(){
		if(document.getElementById("yclon").value != "" && document.getElementById("yclat").value != ""){
			map.clearOverlays();
			var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
			map.centerAndZoom(new_point,13);
			var marker = new BMap.Marker(new_point);//创建标注
			map.addOverlay(marker);//将标注添加到地图中
			map.panTo(new_point); 
			map.setCenter(new_point);
		}
	}
</script>
[@main.footer/]
[/@main.body]