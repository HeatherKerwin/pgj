[#include "../../website/common/setting.ftl"]
<!-- 这是公共html标准内容 -->
[#macro body title='票管家官方网站' head=seoMap.homepage]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">	
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh">
	<head lang="en">
	    <meta charset="UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	    <title>${head.title!title}</title>
		<meta name="keywords" content="${head.keywords!' '}">
	    <meta name="description" content="${head.description!' '}">
	    <link rel="shortcut icon" href="${imagePath}/common/icon/32.png"/>
	    <link rel="icon" href="${imagePath}/common/icon/32.png" sizes="32x32"/>
	    <link rel="icon" href="${imagePath}/common/icon/192.png" sizes="192x192"/>
	    <link rel="apple-touch-icon-precomposed" href="${imagePath}/common/icon/180.png"/>
	    <meta name="msapplication-TileImage" content="${imagePath}/common/icon/270.png"/>
	    
	    <link rel="stylesheet" type="text/css" href="${pluPath}/ajaxPager/page.css"/>
	    
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/homepage.css"/>
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css"/>
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css"/>
	    	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css">
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/left.css"/>
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/tool.css"/>
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/header.css"/>
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/foot.css"/>
	    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/right.css"/>
	    
	   	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/newIndex.css" />
    	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/global.css" />
    	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/redPackets.css" />
	    
	    <script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${comPath}/jquery.cookie.js"></script>
		<script type="text/javascript" src="${comPath}/jquery.uploadify.js"></script>
		<script type="text/javascript" src="${comPath}/md5.js"></script>

		<script type="text/javascript" src="${jsPath}/enum.js"></script>
		<script type="text/javascript" src="${jsPath}/visitLog.js"></script>
		<script type="text/javascript" src="${jsPath}/config.js"></script>
		<script type="text/javascript" src="${jsPath}/map.js"></script>
		
		
		<script type="text/javascript" src="${pluPath}/validation-engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
		<script type="text/javascript" src="${pluPath}/validation-engine/js/jquery.validationEngine.js"></script>
	    
	    <script type="text/javascript" src="${jsPath}/left.js"></script>
	    
	    <link rel="stylesheet" href="${pluPath}/validation-engine/css/validationEngine.jquery.css" type="text/css"/>
		<link rel="stylesheet" href="${pluPath}/validation-engine/css/template.css" type="text/css"/>
	    <script type="text/javascript">
		/**
	     * boot 项目的提交
	     */
	    function bootPost(url,params){
	    	var res;
	    	$.ajax({
	    		url:url,
	    		type:"POST",
	    		data:params,
	    		dataType:"json",
	    		async: false,
	    		success:function(data){
	    			res = data;
	    		},
	    		error:function(json){
    				console.log("获取数据失败！");
	    		}
	    	});
	    	return res;
		}
	    
    	/**
	     * 跳转页面，并根据url传递参数
	     */
	    function _OPENPAGE_FORM(map){
	    	var tempForm = document.createElement("form");
	    	tempForm.action="${basePath}/open/page";
	    	tempForm.method="post";
	    	tempForm.id="_OPENPAGE_FORM";
	    	document.body.appendChild(tempForm);
	    	
	    	var array = map.keySet();
	    	for(var i in array) {
	    	 	var tempInput = document.createElement("input");
		    	tempInput.type="hidden";
		    	tempInput.name=array[i];
		    	tempInput.value=map.get(array[i]);
		    	tempForm.appendChild(tempInput);
	    	}
	    	tempForm.submit();
	    }
	    </script>
	    [#if from?? && from=='pinyou']
		    <script type="text/javascript">
		    //品友转化代码
			function pyRegisterCvt1(){//提交留言
				var w=window,d=document,e=encodeURIComponent;
				var b=location.href,c=d.referrer,f,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);
				if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('Bxs.D5h.G0ehu6F4CHRZ-SiKCB_nW_')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&e=';(new Image()).src=u;
			}
			function pyRegisterCvt3(){//询价
				var w=window,d=document,e=encodeURIComponent;
				var b=location.href,c=d.referrer,f,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);
				if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('Bxs.k5h.BfajYfvfS4kSh_hvRXeFl0')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&e=';(new Image()).src=u;
			}
			</script>
	    [#else]
			<script type="text/javascript">
		   	function pyRegisterCvt1(){}//提交留言
			function pyRegisterCvt3(){}//询价
		   </script>
	    [/#if]
	</head>
	<body class="setting" style="background: #f5f5f5">
		[#nested/]
		
		[#if from?? && from=='pinyou']
		<script type="text/javascript">
		function pyRegisterCvt2(){
			var w=window,d=document,e=encodeURIComponent;
			var b=location.href,c=d.referrer,f,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);
			if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('Bxs.35h.tIN5bLOrvz5ZUTFdcjDS-P')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&e=';
			(new Image()).src=u;
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
		</script>
		[/#if]
	</body>
</html>
[/#macro]

<!-- 这是头部 -->
[#macro header currentmenu='1' topindex='1' returnIndex="1"]
<div class="w ha bcf8f8f8">
    <!-- 内容区域-->
    <div class="w h34 bc3a3a3a heard">
        <div class="w1200 bc h34 f13 lh34 cb0b0b0">
            <div id="has_member" style="display:none;">
				<p id="member_name" class="fl cwhite mr15">
					您好，欢迎来到票据管家
				</p>
				<a href="${basePath}/member/logout" class="fl cwhite">退出</a>
			</div>
			<div id="null_member" style="display:none;">
				<p class="fl cwhite mr15">
	           		  您好，欢迎来到票据管家
	            </p>
				<a href="${basePath}/login" class="fl cd43c33 mr10">登录</a>
	           	<span class="fl cd43c33 mr15">|</span>
	            <a href="${basePath}/login" class="fl cd43c33 mr15">注册</a>
			</div>
            <div class="fr">
                <ul class="fl">
                    <li class="fl mr15"><a href="${basePath }/bisicmessage/information" class="fl mr9">我的票据管家</a><i class="fl Hright"></i></li>
                    <li class="fl mr15"><a href="${basePath }/discountorder/discount" class="fl mr9">我的订单</a><i class="fl Hright"></i></li>
                    <li class="fl mr15"><a href="${basePath }/systeminfo/list" class="fl mr9">消息中心<span style="color:red;font-size:12px;">${MEMBER_MESSAGE?default('')}</span></a><i class="fl Hright"></i></li>
                    <li class="fl mr15"><a href="${basePath}/help" class="fl mr9">帮助中心</a><i class="fl Hright"></i></li>
                </ul>
                <p class="fl">客服热线：<span class="cd43c33">400-067-0002（接听时间：工作日09：00-17：30）</span></p>
            </div>
        </div>
    </div>
    
    <!-- 头部导航栏 -->
    <script>
    /*导航栏触发下拉，触发样式*/
    jQuery(document).ready(function () {
    	console.log(1);
        function initCss() {
            var left = (jQuery('body').width() - 1200) / 2;
            jQuery('.headerMenu').width(jQuery('body').width());
            jQuery('.headerMenu').css("left", -left)
        }

        if (window.location.pathname.indexOf("caseDetail.htm") != -1 || window.location.pathname.indexOf("caseCenter.htm") != -1) {
            jQuery('.sloveMenu').addClass('sloveMenuActive');
        }
        jQuery('.caseHeaderCategory').click(function () {
            var id = jQuery(this).attr('data-Id');
            jQuery('.caseHeaderLeftItem').removeClass('selected');
            jQuery(this).parent('li').addClass('selected')
            jQuery('.caseHeaderDetailItemNew').hide(0, function () {
                jQuery('.caseHeaderDetailItemNew[data-id=' + id + ']').show();
            })
        })
        jQuery('.sloveMenu').hover(function () {
            jQuery(this).children('.headerMenu').stop(true, true).fadeIn(200);
        }, function () {
            jQuery(this).children('.headerMenu').stop(true, true).fadeOut(200);
        });
        jQuery(window).resize(function () {
            initCss();
        })
        initCss();
    });
	</script>
    [#if currentmenu =='1']
    	[#include "../common/head.ftl"]
    	<div class="spNavTopWrap" id="J_spNavTopWrap">
        <div class="spNavWrap home" id="J_spNavWrap">
            <!-- 头部导航栏:START -->
            <div class="spNav home" id="J_spNav">
                <div class="spLogoWrap">
                    <a class="spLogoLink" href="${basePath}/index.html"><img class="spLogo" src="https://img.utiexian.com/website/header/logo2.png" alt="票据管家" /></a>
                </div>
                <!--导航栏-->
                <ul class="spNavList home">
                    <li class="spNavItem active"> <a class="ent-uri-placeholder" href="${basePath}/index.html"> 首页 </a> </li>
                    <li class="spNavItem sloveMenu"> <a class="ent-uri-placeholder" href="javascript:void(0)"> 出票业务 <div class="orderRemind bns_orderRemind none">您有<span class="bnsCount"></span>个订单</div></a> 
                    	<!-- 报价收票－菜单分类:START -->
                    	<div class="caseHeaderMenu headerMenu">
                            <div class="caseHeaderContainer clearFloat">
                                <ul class="caseHeaderDetailItem clearFloat">
                                    <li ><a class="caseHeaderItemtitle w280" href="javascript:checkAccount(0,'/discountrecord/discount_yp1');"> <span class="caseHeaderAlinkTitle ">我要出票</span> <span class="caseHeaderAlinkDes">在线交易，让出票更容易</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="javascript:checkAccount(0,'/discountorder/discountorderall');"> <span class="caseHeaderAlinkTitle ">出票订单<b class="bns_orderRemind bnsCount none"></b></span> <span class="caseHeaderAlinkDes">实时查看贴现中订单</span> </a></li>
                                    <li class="none openBnsCib"><a class="caseHeaderItemtitle w280" id="openBnsCib" href="" target="_blank"> <span class="caseHeaderAlinkTitle ">登录执剑人</span><span class="caseHeaderAlinkDes">及时查看资金动向</span></a></li>
                                </ul>
                            </div>
                        </div>
                        </li>
                    <li class="spNavItem sloveMenu"> <a class="ent-uri-placeholder" href="javascript:void(0)"> 收票业务 <div class="orderRemind org_orderRemind none">您有<span class="orgCount"></span>个订单</div> </a> 
                    	<!-- 报价收票－菜单分类:START -->
                    	<div class="caseHeaderMenu headerMenu">
                            <div class="caseHeaderContainer clearFloat">
                                <ul class="caseHeaderDetailItem clearFloat">
                                    <li><a class="caseHeaderItemtitle w280" href="javascript:checkAccount(1,'/hall/receiveOrderall1');"> <span class="caseHeaderAlinkTitle ">我要收票</span> <span class="caseHeaderAlinkDes">见票报价</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="javascript:checkAccount(1,'/hall/receiveOrderall2');"> <span class="caseHeaderAlinkTitle ">收票订单<b class="org_orderRemind orgCount none"></b></span> <span class="caseHeaderAlinkDes">实时查看收票信息</span> </a></li>
                                    <li class="none openOrgCib"><a class="caseHeaderItemtitle w280" id="openOrgCib" href="" target="_blank"> <span class="caseHeaderAlinkTitle ">登录执剑人</span><span class="caseHeaderAlinkDes">及时查看资金动向</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="spNavItem "> <a class="ent-uri-placeholder" href="${basePath}/zhangbu/index"> 票据管理 </a> </li>
                    <li class="spNavItem sloveMenu"> <a class="ent-uri-placeholder" href="javascript:void(0)"> 工具 </a>
                        <!-- 工具－菜单分类:START -->
                        <div class="caseHeaderMenu headerMenu">
                            <div class="caseHeaderContainer clearFloat">
                                <ul class="caseHeaderDetailItem clearFloat f18">
                                    <li><a class="caseHeaderItemtitle w280" href="${basePath}/homepage/tool/inquiry"> <span class="caseHeaderAlinkTitle ">我要询价</span> <span class="caseHeaderAlinkDes">实时查询最低报价</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="${basePath}/homepage/tool/calculator"> <span class="caseHeaderAlinkTitle ">贴现计算器</span> <span class="caseHeaderAlinkDes">计算票据贴现利息</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="${basePath}/inquiryreply/inquiryreply"> <span class="caseHeaderAlinkTitle ">银票查询查复</span> <span class="caseHeaderAlinkDes">对接合作银行，查询银行</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="${basePath}/homepage/tool/lianhang"> <span class="caseHeaderAlinkTitle ">联行号查询</span> <span class="caseHeaderAlinkDes">实时查询银行联行号</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="${basePath}/homepage/tool/gongcui"> <span class="caseHeaderAlinkTitle ">公催查询</span> <span class="caseHeaderAlinkDes">查看票据的公示催告</span> </a></li>
                                    <li><a class="caseHeaderItemtitle w280" href="${basePath}/homepage/tool/shibor"> <span class="caseHeaderAlinkTitle ">shibor查询</span> <span class="caseHeaderAlinkDes">上海银行间同业拆放率</span> </a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- 解决方案－菜单分类:END -->

                    </li>
                    <li class="spNavItem "> <a class="ent-uri-placeholder" href="https://bbs.utiexian.com/" target="_blank"> 社区 </a> </li>
                </ul>
                <!-- 导航菜单:END -->
            </div>
            <!-- END -->
        </div>
    </div>
    	
    [#elseif currentmenu=='2']
    	<div class="w h120">
	        <div class="w1200 bc h120">
	            <a href="${basePath}/index.html"><img src="${imagePath}/website/header/logo.png" alt="" class="fl mt35 mr20"/></a>
	            <ul class="fr f20 mt59">
	                <li class="fl"><a href="${basePath}/index.html" class="c2d2d2d">返回首页</a></li>
	                <li class="fl"><a  href="[#if returnIndex=='2']${basePath}/news/index[#else]javascript:history.go(-1)[/#if]" class="c2d2d2d ml60">返回上一页</a></li>
	            </ul>
	        </div>
	    </div>
    [#elseif currentmenu=='3']
    	<div class="w h120" >
	         <div class="w1200 bc h120">
	            <a href="${basePath}/index.html"><img src="${imagePath}/website/header/logo.png" alt="" class="fl mt35 mr20"/></a>
	            <ul class="fr f20 mt59">
	                <li class="fl"><a href="${basePath}/index.html" class="c2d2d2d">返回首页</a></li>
	            </ul>
	        </div>
	    </div>
    [#elseif currentmenu=='4']
    <div class="w h120">
	        <div class="w1200 bc h120">
	            <a href="${basePath}/zhangbu/index"><img src="${imagePath}/website/header/logo4.png" alt="票据管理" class="fl mt35 mr20"/></a>
	            <ul class="fr f20 mt59 f18">
	                <li class="fl"><a href="${basePath}/index.html" class="c2d2d2d">返回首页</a></li>
	                <li class="fl"><a  href="javascript:history.go(-1)" class="c2d2d2d ml60">返回上一页</a></li>
	            </ul>
	        </div>
	    </div>
    [#elseif currentmenu=='5']
    <div class="w h120">
        <div class="w1200 bc h120">
            <a href="${basePath}/help"><img src="${imagePath}/website/header/logo5.png" alt="帮助中心" class="fl mt35 mr20"/></a>
            <ul class="fr f20 mt59 f18">
                <li class="fl help"><a href="${basePath}/index.html" class="c2d2d2d">返回首页</a></li>
                <li class="fl w106 ml50 help [#if topindex=='1']MY-SELECT[/#if]"><img src="${imagePath}/website/header/xuan.png" width="14" height="14" class="fl mr10 mt3 [#if topindex=='1'][#else]none[/#if]"><a href="${basePath}/help" class="fr [#if topindex=='1']cd43c33[#else]c2d2d2d[/#if]">常见问题</a></li>
                <li class="fl w106 ml50 help [#if topindex=='2']MY-SELECT[/#if]"><img src="${imagePath}/website/header/xuan.png" width="14" height="14" class="fl mr10 mt3 [#if topindex=='2'][#else]none[/#if]"><a href="${basePath}/xueyuan" class="fr [#if topindex=='2']cd43c33[#else]c2d2d2d[/#if]">票据学院</a></li>
                <li class="fl w106 ml50 help [#if topindex=='3']MY-SELECT[/#if]"><img src="${imagePath}/website/header/xuan.png" width="14" height="14" class="fl mr10 mt3 [#if topindex=='3'][#else]none[/#if]"><a href="${basePath}/contact" class="fr [#if topindex=='3']cd43c33[#else]c2d2d2d[/#if]">联系客服</a></li>
            </ul>
        </div>
    </div>
    [/#if]
</div>
[/#macro]

<!-- 这是底部 -->
[#macro footer remark='这是底部这是传值']
<div class="w h450 cababab f16 pa" style="z-index: 1">
    <!-- 底部标签-->
    <div class="w bc363636 h60">
        <ul class="w970 bc cwhite f16 h60">
            <li class="fl mr30"><a href="${basePath }/aboutus/choice/suggest" class="fl mr30 cababab lh60">关于我们</a><i class="fr Fright mt21" ></i></li>
            <li class="fl mr30"><a href="${basePath}/join/app" class="fl mr30 cababab lh60">加入我们</a><i class="fr Fright mt21" ></i></li>
            <li class="fl mr30"><a href="javascript:checkAccount(0,'/discountrecord/discount_yp1');" class="fl mr30 cababab lh60">我要贴现</a><i class="fr Fright mt21" ></i></li>
            <li class="fl mr30"><a href="javascript:checkAccount(1,'/hall/receiveOrderall1');" class="fl mr30 cababab lh60">报价收票</a><i class="fr Fright mt21" ></i></li>
            <li class="fl mr30"><a href="${basePath}/help" class="fl mr30 cababab lh60">帮助中心</a><i class="fr Fright mt21" ></i></li>
            <li class="fl mr30"><a href="${basePath}/news/index" class="fl mr30 cababab lh60">市场信息</a><i class="fr Fright mt21" ></i></li>
            <li class="fl mr30"><a href="${basePath}/xieyi" class="fl mr30 cababab lh60">用户协议</a><i class="fr Fright mt21" ></i></li>
            <li class="fl"><a href="${basePath }/statement" class="fl mr30 cababab lh60">免责声明</a></li>
        </ul>
    </div>
    <!-- 底部信息-->
    <div class="w h390 bc404040 pr">
        <div class="w1200 bc">
            <div class="fl mt120 mt73">
                <p class="fb">联系我们</p>
                <ul class="mt30">
                    <li class="mt16"><i class="Ficon Ficon1 vm mr16"></i>电话：400-067-0002</li>
                    <li class="mt16"><i class="Ficon Ficon2 vm mr16"></i>邮箱：ryfinance@ryfinance.com</li>
                    <li class="mt16"><i class="Ficon Ficon3 vm mr16"></i>网址：www.utiexian.com</li>
                    <li class="mt16"><i class="Ficon Ficon4 mr16"></i>地址：上海市虹口区东大名路501白玉兰广场1207</li>
                    <!-- 友情链接-->
                    <li class="mt16"><a class="cwhite lh20 dsb" href="http://www.wingroup.com.cn/" target="_blank" rel="nofollow">
                        友情链接：睿银控股集团
                    </a></li>
                </ul>
            </div>
            <div class="fr mt53">
                <div class="fl mr89">
                    <img src="${imagePath}/website/foot/app.png" />
                    <p class="tc lh200 mt20">扫描二维码<br/>下载APP</p>
                </div>
                <div class="fl mr120">
                    <img src="${imagePath}/website/foot/weixin.png" />
                    <p class="tc lh200 mt20">扫描二维码<br/>关注公众号</p>
                </div>
            </div>
        </div>
        <div class="cb"></div>
        <p class="tc mt59">© Copyright 2015 上海票管家金融服务有限公司  <a style="color:#a8a8a8;" href="http://www.miitbeian.gov.cn" target="_blank">沪ICP备16003046号</a></p>
    	<div class="tc pa l_50 ml-63"><a href="http://webscan.360.cn/index/checkwebsite/url/www.ryfinance.com"><img border="0" src="http://webscan.360.cn/status/pai/hash/5aca64d4d048d43880ab34a213dabf88"/></a></div>
    </div>
</div>
<script type='text/javascript' src='https://www.365webcall.com/IMMe1.aspx?settings=mw7mbmImIw7mN0z3AP0XNPz3AI00Imz3A66mmX0&LL=0'></script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256299776'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256299776' type='text/javascript'%3E%3C/script%3E"));</script>
<!-- <script type="text/javascript" src="http://js.ad7.com/u/1/dc83eda4b85782150937f33e500af7cf.js"></script> -->
 <script type="text/javascript">
var _mvq = _mvq || [];
_mvq.push(['$setAccount', 'm-236927-0']);

_mvq.push(['$logConversion']);
(function() {
var mvl = document.createElement('script');
mvl.type = 'text/javascript'; mvl.async = true;
mvl.src = ('https:' == document.location.protocol ? 'https://static-ssl.mediav.com/mvl.js' : 'http://static.mediav.com/mvl.js');
var s = document.getElementsByTagName('script')[0];
s.parentNode.insertBefore(mvl, s);
})();

</script>
[/#macro]

[#macro left remark='这是左侧这是传值' leftindex='1' leftindex1='1' leftindex4='1']
<div class="">
    <!-- 左侧标签-->
    [#if remark =='1']
    <div class="fl w200 bte0e0e0 ble0e0e0 bbe0e0e0 bcf9f9f9 f14">
	    <h1 class="w200 h30 lh30 bc777777 cwhite ti10">我的票据管家</h1>
	    <ul class="w200 lh30 mb100" id="PJGJ">
	        <li class="w PJGJli">
	            <a href="#" class="w ti10 fb c2d2d2d bbe0e0e0 dsb" id="account">账户信息<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	            <ul class="w200" id="accountCon">
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath }/bisicmessage/information" class="pl65 c2d2d2d dsb">基本信息<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <li class="h30 bbe0e0e0">
	                    <a href="javascript:checkAccount(0,'')" class="pl65 c2d2d2d dsb">开户管理<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath }/bisicmessage/discountplace" class="pl65 c2d2d2d dsb">贴现地址<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath }/bisicmessage/deposit" class="pl65 c2d2d2d dsb">押金管理<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath}/bisicmessage/password" class="pl65 c2d2d2d dsb">更改密码<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	            </ul>
	        </li>
	        <li class="w PJGJli">
	            <a href="#" class="w ti10 fb h30 bbe0e0e0 c2d2d2d dsb" id="discount">贴现订单</a>
	            <ul class="w200" id="discountCon">
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath }/discountorder/discount?ym=yp" class="pl65 c2d2d2d dsb">银票订单<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath }/discountorder/discount?ym=sp" class="pl65 c2d2d2d dsb">商票订单<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <!--
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath }/discountorder/discount?ym=pl" class="pl65 c2d2d2d dsb">批量订单<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>-->
	            </ul>
	        </li>
	        <li class="w PJGJli">
	            <a href="#" class="w ti10 fb h30 bbe0e0e0 c2d2d2d dsb" id="collect">收票订单</a>
	            <ul class="w200" id="collectCon">
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath}/distributeorder/list" class="pl65 c2d2d2d dsb">银票订单<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <li class="h30 bbe0e0e0">
	                    <a href="${basePath}/distributeordersp/list" class="pl65 c2d2d2d dsb">商票订单<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li>
	                <!-- <li class="h30 bbe0e0e0">
	                    <a href="${basePath}/distributeorderpl/list" class="pl65 c2d2d2d dsb">批量订单<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	                </li> -->
	            </ul>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli">
	            <a href="${basePath}/inquiryreply/list" class="w c2d2d2d dsb">查询查复<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli">
	            <a href="${basePath}/comments/comments" class="w c2d2d2d dsb">我的评分<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli">
	            <a href="${basePath}/systeminfo/list" class="w c2d2d2d dsb">消息中心<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli">
	            <a href="${basePath }/bisicmessage/men" class="w c2d2d2d dsb">推荐人<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="ml8 none" /></a>
	        </li>
	    </ul>
	    <div class="cb"></div>
	</div>
	[#elseif remark=='2']
	<div class="fl w200 h700 bte0e0e0 ble0e0e0 bbe0e0e0 bcf9f9f9 f14">
	    <h1 class="w200 h30 lh30 bc777777 cwhite ti10">常见问题</h1>
	    <ul class="w200 lh30 mb100">
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='1']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=1" class="w dsb ti65 title [#if leftindex=='1']cd43c33[#else]c2d2d2d[/#if]">注册登录<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='1'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='2']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=2" class="w dsb ti65 title [#if leftindex=='2']cd43c33[#else]c2d2d2d[/#if]">如何认证<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='2'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='3']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=3" class="w dsb ti65 title [#if leftindex=='3']cd43c33[#else]c2d2d2d[/#if]">我要询价<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='3'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='4']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=4" class="w dsb ti65 title [#if leftindex=='4']cd43c33[#else]c2d2d2d[/#if]">我要贴现<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='4'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='5']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=5" class="w dsb ti65 title [#if leftindex=='5']cd43c33[#else]c2d2d2d[/#if]">报价收票<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='5'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='6']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=6" class="w dsb ti65 title [#if leftindex=='6']cd43c33[#else]c2d2d2d[/#if]">票据管理<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='6'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='7']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=7" class="w dsb ti65 title [#if leftindex=='7']cd43c33[#else]c2d2d2d[/#if]">贴现工具<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='7'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli [#if leftindex=='8']MY-SELECT[/#if]">
	            <a href="${basePath}/help?num=8" class="w dsb ti65 title [#if leftindex=='8']cd43c33[#else]c2d2d2d[/#if]">其他问题<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon ml8 [#if leftindex=='8'][#else]none[/#if]" /></a>
	        </li>
	    </ul>
	    <div class="cb"></div>
	</div>
	[#elseif remark=='3'  ]
	<div class="fl w220 h480 bae0e0e0 bcwhite ti20">
        <div class="w h40 f20 lh40 bc778ffd cwhite">关于我们</div>
        <ul class="w f16 lh32">
            <a href="${basePath }/aboutus/choice/suggest"><li class="w h32 mb1 bcf2f2f2 [#if leftindex=='1']c778ffd[#else]c666666[/#if]">票管家介绍</li></a>
            <a href="${basePath }/aboutus/choice/ceo"><li  class="w h32 mb1 bcf2f2f2 [#if leftindex=='2']c778ffd[#else]c666666[/#if]">票管家CEO</li></a>
             <a href="${basePath }/aboutus/choice/partner"><li class="w h32 mb1 bcf2f2f2 [#if leftindex=='3']c778ffd[#else]c666666[/#if]">合作伙伴</li></a>
             <a href="${basePath }/aboutus/choice/contact"><li class="w h32 mb1 bcf2f2f2 [#if leftindex=='4']c778ffd[#else]c666666[/#if]">联系我们</li></a>
        </ul>
    </div>
	[#elseif remark=='4'  ]
	<div class="fl w200 h700 bte0e0e0 ble0e0e0 bbe0e0e0 bcf9f9f9 f14">
	    <h1 class="w200 h30 lh30 bc777777 cwhite ti10">票据学院</h1>
	    <ul class="w200 lh30 mb100" id="PJGJ">
	        <li class="w PJGJli2">
	            <a href="javascript:void(0)" class="w ti10 fb bbe0e0e0 dsb [#if leftindex=='1']cd43c33[#else]c2d2d2d[/#if]" id="xinshouzhiyin">新手指引<img src="${imagePath}/website/header/[#if leftindex=='1']down[#else]open[/#if].png" width="15" height="7" class="icon ml8" /></a>
	            <ul class="w200" id="zhiyin">
	                <li class="h30 bbe0e0e0 [#if leftindex1=='1']MY-SELECT1[/#if]">
	                    <a href="${basePath}/xueyuan?num=1" class="pl65 dsb title2 [#if leftindex1=='1']cd43c33[#else]c2d2d2d[/#if]">企业用户指引<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon2 ml8 [#if leftindex1=='1'][#else]none[/#if]" /></a>
	                </li>
	                <li class="h30 bbe0e0e0 [#if leftindex1=='2']MY-SELECT1[/#if]">
	                    <a href="${basePath}/xueyuan?num=2" class="pl65 dsb title2 [#if leftindex1=='2']cd43c33[#else]c2d2d2d[/#if]">机构用户指引<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon2 ml8 [#if leftindex1=='2'][#else]none[/#if]" /></a>
	                </li>
	                <li class="h30 bbe0e0e0 [#if leftindex1=='3']MY-SELECT1[/#if]">
	                    <a href="${basePath}/xueyuan?num=3" class="pl65 dsb title2 [#if leftindex1=='3']cd43c33[#else]c2d2d2d[/#if]">票据管理使用指引<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon2 ml8 [#if leftindex1=='3'][#else]none[/#if]" /></a>
	                </li>
	            </ul>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli2 [#if leftindex=='2']MY-SELECT2[/#if]">
	            <a href="${basePath}/piaojuxueyuan/index?type=1" class="w dsb title1 [#if leftindex=='2']cd43c33[#else]c2d2d2d[/#if]">票据知识<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon1 ml8 [#if leftindex=='2'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli2 [#if leftindex=='3']MY-SELECT2[/#if]">
	            <a href="${basePath}/piaojuxueyuan/index?type=2" class="w dsb title1 [#if leftindex=='3']cd43c33[#else]c2d2d2d[/#if]">如何验票<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon1 ml8 [#if leftindex=='3'][#else]none[/#if]" /></a>
	        </li>
	        <li class="ti10 fb h30 bbe0e0e0 PJGJli2 [#if leftindex=='4']MY-SELECT2[/#if]">
	            <a href="${basePath}/piaojuxueyuan/index?type=3" class="w dsb title1 [#if leftindex=='4']cd43c33[#else]c2d2d2d[/#if]">法律法规<img src="${imagePath}/website/PJGJ/leftIcon.png" width="6" height="8" class="icon1 ml8 [#if leftindex=='4'][#else]none[/#if]" /></a>
	        </li>
	    </ul>
	    <div class="cb"></div>
	</div>
  	[#elseif remark=='5'  ]
       <!--左菜单-->
    <div class="fl w220 h480 bae0e0e0 bcwhite ti20">
        <div class="w h40 f20 lh40 bc778ffd cwhite">加入我们</div>
        <ul class="w f16 lh32">
            <li class="w h32 mb1 bcf2f2f2"><a class="[#if leftindex4=='1']c778ffd[#else]c666666[/#if]" href="${basePath}/join/app">APP推广</a></li>
         <!--   <li class="w h32 mb1 bcf2f2f2"><a class="[#if leftindex4=='2']c778ffd[#else]c666666[/#if]" href="${basePath}/join?num=2">公司前台</a></li>
            <li class="w h32 mb1 bcf2f2f2"><a class="[#if leftindex4=='3']c778ffd[#else]c666666[/#if]" href="${basePath}/join?num=3">互联网产品经理</a></li>
            <li class="w h32 mb1 bcf2f2f2"><a class=" [#if leftindex4=='4']c778ffd[#else]c666666[/#if]" href="${basePath}/join?num=4">JAVA开发工程师</a></li>-->
            <li class="w h32 mb1 bcf2f2f2 "><a class="[#if leftindex4=='5']c778ffd[#else]c666666[/#if]" href="${basePath}/join/dianhua">电话销售</a></li>
            <li class="w h32 mb1 bcf2f2f2"><a class=" [#if leftindex4=='6']c778ffd[#else]c666666[/#if]" href="${basePath}/join/xiaoshou">销售专员</a></li>
            <li class="w h32 mb1 bcf2f2f2 "><a class="[#if leftindex4=='7']c778ffd[#else]c666666[/#if]" href="${basePath}/join/shixi">销售实习生/应届生</a></li>
        </ul>
    </div>
	[/#if]
</div>
[/#macro]

[#macro right remark='这是右侧这是传值']
[#include "../common/yijian.ftl"]
<!--评价弹窗-->
<div class="w h pf bc05 zi190 top none" id="yijian">
    <div class="w612 h550 yjBackground t_10 l_50 ml-306 pr zi200">
        <!--关闭按钮-->
        <div class="w30 h30 pa t20 r50 zi200 yjClose cp" onclick="yjclose();"></div>

        <div class="w530 pr t20 bc zi190">
            <div class="pr f24 fb tc">意见反馈</div>
            <div class="mt16 h24 f18 lh24">
                <img src="${imagePath}/website/homepage/opinion1.png" width="29" height="23" class="fl mr20">我们期待您的意见和建议：
            </div>
            <textarea class="w510 h100 bae0e0e0 mt10 pl10 pt10 pr10 validate[required]" placeholder="如果使用中有什么不好用的地方，请大声说出来！
我们会每天关注您的反馈，不断优化产品，为您提供更好的服务！" id="messagecontent"></textarea>
            <div class="mt10 f18 fb">请留下您的联系方式，以便我们及时反馈</div>
            <div class="mt10">
                <div class="fl lh34">联系方式：</div>
                <input type="text" class="fl w410 h34 bae0e0e0 f14 lh34 ti25 validate[required]" placeholder="常用手机号/QQ/微信" id="messagenumber">
            </div>
            <div class="cb"></div>
            <input type="button" class="w125 h40 lh40 bcd43c33 b0 br5 cwhite f20 tc bc dsb mt20 cp" value="提交" onclick="yjsave()">
        </div>
    </div>
</div>
<!--红包展示-->
<img id="coupon" src="https://img.utiexian.com/website/redPackets/rightIcon.png" alt="新手红包" class="rightIcon cp none" style="display:none;">

<div class="w180 h300 pf zi13 t_40 right">
    <!-- 右侧标签-->
    <div class="w180 h300">
	    <!-- 右侧浮动 开始 -->
	    <div id="moquu_wxin" class="moquu_wxin">
	        <a href="javascript:void(0);">
	            <div class="moquu_wxinh">
	            </div>
	        </a>
	    </div>
	    <div id="moquu_wshare" class="moquu_wshare">
	        <a href="javascript:void(0);">
	            <div class="moquu_wshareh">
	            </div>
	        </a>
	    </div>
	    <div id="moquu_collection">
        	<a href="#" class='moquu_collection' rel="sidebar" onclick="join_favorite(window.location,document.title);">
       	 </a>
    	</div>
	    <div id="moquu_wyijian">
	        <a href="#" class='moquu_wyijian' onclick="suggest();">
	        </a>
	    </div>
	    <a id="moquu_top" href="javascript:void(0);">
	    </a>
	</div>
</div>

<!-- 红包的弹出 -->
<div class="pf w h bc05 zi200 top none" id="redWindow">
    <!--居中-->
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-alignItems-center flex-justify-center  w h">
            <!--新手红包-->
            <div class="flex-row flex-direction-column w400">
                <!--关闭按钮-->
                <div class="flex-row flex-justify-end">
                    <lable for="closeIcon" class="mr-40 closeCouponBtn">
                        <img src="https://img.utiexian.com/website/close/closeIcon1.png" alt="关闭" class="cp">
                        <button id="closeIcon" class="oln none"></button>
                    </lable>
                </div>

                <!--新手红包-->
                <div class="pr tc flex flex-justify-center none" id="noviceWindow">
                    <img src="https://img.utiexian.com/website/redPackets/openRedBg.png" alt="" width="420" height="474" class="cp openRed">
                    <div class="pa t140 f30 ce84c3d"><span class="f65" id="couponNum"></span>个</div>
                </div>

            </div>
        </div>
    </div>
</div>
[/#macro]

[#macro news tabIndex='1']
<ul class="w1198 h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite SCXXtab">
	<li class="w240 lh50 fl bre0e0e0 [#if tabIndex=='1']bbd43c33[#else][/#if]">
        <input type="radio" name="marketTab" id="market1"><label for="market1" class="dsb cp"><a href="${basePath}/news/index" class="[#if tabIndex=='1']cd43c33[#else]c2d2d2d[/#if]">全部</a></label>
    </li>
    <li class="w238 lh50 fl bre0e0e0 [#if tabIndex=='2']bbd43c33[#else][/#if]">
        <input type="radio" name="marketTab" id="market2"><label for="market2" class="dsb cp"><a href="${basePath}/news/pjxw" class="[#if tabIndex=='2']cd43c33[#else]c2d2d2d[/#if]">票据新闻</a></label>
    </li>
    <li class="w238 lh50 fl bre0e0e0 [#if tabIndex=='3']bbd43c33[#else][/#if]">
        <input type="radio" name="marketTab" id="market3"><label for="market3" class="dsb cp"><a href="${basePath}/news/jrdt" class="[#if tabIndex=='3']cd43c33[#else]c2d2d2d[/#if]">金融动态</a></label>
    </li>
    <li class="w238 lh50 fl bre0e0e0 [#if tabIndex=='4']bbd43c33[#else][/#if]">
        <input type="radio" name="marketTab" id="market4"><label for="market4" class="dsb cp"><a href="${basePath}/news/gjss" class="[#if tabIndex=='4']cd43c33[#else]c2d2d2d[/#if]">管家说事</a></label>
    </li>
    <li class="w240 lh50 fl [#if tabIndex=='5']bbd43c33[#else][/#if]">
        <input type="radio" name="marketTab" id="market5"><label for="market5" class="dsb cp"><a href="${basePath}/news/mtbd" class="[#if tabIndex=='5']cd43c33[#else]c2d2d2d[/#if]">媒体报道</a></label>
    </li>
</ul>
[/#macro]

[#macro cib_tool]
<div class="w h34 lh34 f14 bcfaf7f7 flex-row flex-justify-space-between">
    <div class="ml10">开户管理</div>
    <a href="${basePath}/bisicmessagemember/authentication_material"><div class="mr10 c3366cc cp">开户材料</div></a>
</div>
<!--tab-->
<ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite rzTab">
    <li class="w250 lh50 fl bre0e0e0">
        <input type="radio" name="rzTab" id="rzTab1" value="1" class="none"><a href="#" class="c2d2d2d"><label
            for="rzTab1" class="dsb"></label></a>
    </li>
    <li class="w250 lh50 fl bre0e0e0">
        <input type="radio" name="rzTab" id="rzTab2" value="2" class="none"><a href="#" class="c2d2d2d"><label
            for="rzTab2" class="dsb"></label></a>
    </li>
</ul>
[/#macro]
<script type="text/javascript">

</script>