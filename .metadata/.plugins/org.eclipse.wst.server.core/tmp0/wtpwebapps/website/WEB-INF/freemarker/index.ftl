[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.homepage]

    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css" />
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/swiper.min.css" />
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/home-1114.css">
    <link href="https://static.utiexian.com/css/yonyou/mask.css" rel="stylesheet">
    <script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="https://static.utiexian.com/js/common/swiper.min.js"></script>
    <script type="text/javascript" src="https://static.utiexian.com/js/website/home-1114.js"></script>
    <script type="text/javascript" src="https://static.utiexian.com/js/common/swiper.min.js"></script>

<!-- home-1114 -->
<div class="home1114">
    <!-- body -->
    <div class="homeBody">
        <!-- header -->
        <header>
            <!-- 顶部 -->
            <div class="headTop">
                <div class="rowCenter none">
                [#if member??]
                    <div class="user">您好，${member.username?default(hideMobile(member.mobile))} 欢迎来到票据管家</div>
                    <a href="${basePath}/member/logout" class="exitLogin">退出</a>
                [#else]
                	<div class="user">您好，欢迎来到票据管家</div>
	            	<a href="${basePath}/login" class="login">登录</a>
                    <a href="${basePath}/login" class="register">注册</a>
                [/#if]
                    <div class="tel">客服热线：400-067-0002（接听时间：工作日09：00-17：30 )</div>
                	<div class="myPJGJ"><a href="${basePath}/bisicmessage/information">我的票据管家</a></div>
                </div>
                <div class="rowCenter">
                	<div class="workTime">工作日09：00-17：30</div>
                	<div class="topAbout">
                		<div class="WeChat">
               				<img class="aboutIcon" src="https://img.utiexian.com/website/newhome/190219/WeChat.png">
	               			<div class="topMa">
	               				<img src="https://img.utiexian.com//website/newhome/guanzhu.png" width="70" height="70" alt="微信公众号">
	               			</div>
               			</div>
                		
                		<div class="mApp">
                			<img class="aboutIcon" src="https://img.utiexian.com/website/newhome/190219/APP.png">
                			<div  class="topMa">
                				<img src="https://img.utiexian.com//website/newhome/xaizai.png" width="70" height="70" alt="下载app">
                			</div>
                		</div>
                		
                		<div class="tel"><img class="aboutIcon" src="https://img.utiexian.com/website/newhome/190219/Tel.png">联系客服</div>
                		
                		
                	</div>
                </div>
                
            </div>
            <!-- 顶部 end -->

            <!-- 菜单 -->
            <div class="headMenu">
                <div class="rowCenter">
                    <a href="${basePath}/" class="logo"><img src="${imagePath}/website/newhome/logo.png" width="173" height="44" alt="票据管家" title="票据管家"></a>
                    <div class="menuTel">400-067-0002</div>
                    <div class="menuCon">
                        <ul class="menuList">
                            <li class="menuItem"><a href="${basePath}/" class="menuItemTitle">首页</a></li>
                            
                            <li class="menuItem SubmenuChose">
                                <a href="javascript:void(0)" class="menuItemTitle">出票业务</a>
                                <div class="ItemSubmenu menuInUp">
                                    <ul>
                                        <li class="menuDetailItem"><a href="javascript:checkAccount(0,'/discountrecord/discount_yp1');">我要出票</a></li>
                                        <li class="menuDetailItem"><a href="javascript:checkAccount(0,'/discountorder/discountorderall');">出票订单</a></li>
                                        <li class="menuDetailItem"><a href="${basePath }/systeminfo/list">消息中心</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="menuItem SubmenuChose">
                                <a href="javascript:void(0)" class="menuItemTitle">收票业务</a>
                                <div class="ItemSubmenu menuInUp">
                                    <ul>
                                        <li class="menuDetailItem"><a href="javascript:checkAccount(1,'/hall/receiveOrderall1');">我要收票</a></li>
                                        <li class="menuDetailItem"><a href="javascript:checkAccount(1,'/hall/receiveOrderall2');">收票订单</a></li>
                                        <li class="menuDetailItem"><a href="${basePath }/systeminfo/list">消息中心</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="menuItem SubmenuChose">
                                <a href="javascript:void(0)" class="menuItemTitle">票据管理</a>
                                <div class="ItemSubmenu menuInUp">
                                    <ul>
                                        <li class="menuDetailItem"><a href="${basePath}/zhangbu/account/book/out">我的账簿</a></li>
                                        <li class="menuDetailItem"><a href="${basePath}/zhangbu/draftrecord">货款核算</a></li>
                                        <li class="menuDetailItem"><a href="${basePath}/zhangbu/statistical/analysis">统计分析</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="menuItem SubmenuChose">
                                <a href="javascript:void(0)" class="menuItemTitle">工具</a>
                                <div class="ItemSubmenu menuInUp">
                                    <ul>
                                        <li class="menuDetailItem"><a href="${basePath}/homepage/tool/inquiry">我要询价</a></li>
                                        <li class="menuDetailItem"><a href="${basePath}/homepage/tool/calculator">计算器</a></li>
                                        <li class="menuDetailItem"><a href="${basePath}/homepage/tool/lianhang">联行号查询</a></li>
                                        <li class="menuDetailItem"><a href="${basePath}/homepage/tool/gongcui">公催查询</a></li>
                                        <li class="menuDetailItem"><a href="${basePath}/homepage/tool/shibor">shibor查询</a></li>
                                        [#if member?? && member.draftExchangeBankId??]
						           		<li class="menuDetailItem"><a href="${basePath}/bank/sizeticket/backstage/enterprise">大小票互换</a></li>
							            [#else]
							           	<li class="menuDetailItem"><a href="javascript:enterprise();">大小票互换</a></li>
							            [/#if]
                                    </ul>
                                </div>
                            </li>
                            <li class="menuItem"><a href="${basePath}/help" class="menuItemTitle">常见问题</a></li>
                            <li class="menuItem"><a href="https://bbs.utiexian.com/" class="menuItemTitle">社区</a></li>
                            
                            
                            <li class="menuItem SubmenuChose">
	                            [#if member??]
				                    <a href="javascript:void(0)" class="menuItemTitle">我的账户</a>
	                                <div class="ItemSubmenu menuInUp">
	                                    <ul>
	                                        <li class="menuDetailItem openCib none"><a href="" id="openCib">登录执剑人</a></li>
	                                        <li class="menuDetailItem"><a href="${basePath}/bisicmessage/deposit">我的钱包</a></li>
	                                        <li class="menuDetailItem"><a href="${basePath}/bisicmessage/information">账户信息</a></li>
	                                        <li class="menuDetailItem"><a href="${basePath}/member/logout" class="exitLogin">退出</a></li>
	                                    </ul>
	                                </div>
				                [#else]
					            	<a href="${basePath}/login" class="login">登录</a>
				                    <a href="${basePath}/login" class="register">注册</a>
				                [/#if]
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 菜单 end -->
            </div>

        </header>
        <!-- header end -->

        <!-- content -->
        <div class="homeContent">

            <!-- 秒贴交易 -->
            <div class="module-mtjy">
			    <!-- Swiper -->
		        <div class="swiper-container">
		            <div class="parallax-bg" style="background-image: url(https://img.utiexian.com/website/newhome/banner-mtjy.png);"></div>
		            <div class="swiper-wrapper">
		                <div class="swiper-slide">
		                    <a href="#">
		                        <div class="title" data-swiper-parallax="-100">京东智票</div>
		                        <div class="subtitle" data-swiper-parallax="-200">见证票据流转，保障资金安全</div>
		                    </a>
		                </div>
		                <div class="swiper-slide">
		                    <div class="title" data-swiper-parallax="-100">票据管家</div>
		                    <div class="subtitle" data-swiper-parallax="-200">节省您的每一分财务成本，让贴票更容易</div>
		                </div>
		                <div class="swiper-slide">
		                    <div class="title" data-swiper-parallax="-100">票据管理</div>
		                    <div class="subtitle" data-swiper-parallax="-200">兼具移动管理与网页管理，让管理更清晰</div>
		                </div>
		            </div>
		            <!-- Add Pagination -->
		            <div class="swiper-pagination swiper-pagination-white"></div>
		        </div>
            </div>
            <!-- 秒贴交易 end -->
            
            <div class="module-jy">
            	<div>
            		<!-- 票据询价 -->
	            	<div class="sjxj">
	            		<div class="jy-label">票据询价</div>
	            		<div>
	            			<input placeholder="票面金额">
	            			<select>
	            				<option value="承兑行类型">承兑行类型</option>
	            			</select>
	            			<button>询价</button>
	            		</div>
	            	</div>
	            	<!-- 票据询价 end -->
	            	
	            	<!-- 实时交易 -->
	            	<div class="ssjy">
	            		<div class="jy-label">实时交易</div>
	            		<div class="marquee">
	            			<marquee direction="up" scrollamount="4" onmouseover="this.stop()" onmouseout="this.start()">
							    <p><span>票管家公司</span><span>100万</span><span>虚假票据</span><span>倒闭银行</span><span>点背</span></p>
							    <p><span>票管家公司</span><span>100万</span><span>虚假票据</span><span>倒闭银行</span><span>点背</span></p>
							    <p><span>票管家公司</span><span>100万</span><span>虚假票据</span><span>倒闭银行</span><span>点背</span></p>
							</marquee>
	            		</div>
	            	</div>
	            	<!-- 实时交易 end -->
            	</div>
            	
            </div>

            <!-- 功能模块 -->
            <div class="module-gnmk">
                <div class="gnmlRow">
                    <!-- 我要出票 -->
	                <div class="gnmkItem">
                    	<a href="javascript:checkAccount(0,'/discountrecord/discount_yp1');">
	                        <div class="gnmkShow">
	                            <img src="${imagePath}/website/newhome/icon-wycp.png" alt="我要出票" width="80" height="80">
	                            <h3>我要出票</h3>
	                            <div class="hengxian"></div>
	                        </div>
	                        <div class="gnmkHide">
	                            <p>在线下单，线上交易让出票更容易</p>
	                            <h3>我要出票</h3>
	                        </div>
                    	</a>
	                </div>
                    <!-- 我要出票 end -->

                    <!-- 我要收票 -->
                    <div class="gnmkItem">
                    	<a href="javascript:checkAccount(1,'/hall/receiveOrderall1');">
	                        <div class="gnmkShow">
	                            <img src="${imagePath}/website/newhome/icon-wysp.png" alt="我要收票" width="80" height="80">
	                            <h3>我要收票</h3>
	                            <div class="hengxian"></div>
	                        </div>
	                        <div class="gnmkHide">
	                            <p>见票报价，价格精准，线上交易，公开透明！</p>
	                            <h3>我要收票</h3>
	                        </div>
	                	</a>
                    </div>
                    <!-- 我要收票  end-->

                    <!-- 票据管理 -->
                    <div class="gnmkItem">
                    	<a href="${basePath}/zhangbu/account/book/out">
	                        <div class="gnmkShow">
	                            <img src="${imagePath}/website/newhome/icon-pjgl.png" alt="票据管理" width="80" height="80">
	                            <h3>票据管理</h3>
	                            <div class="hengxian"></div>
	                        </div>
	                        <div class="gnmkHide">
	                            <p>票据批量录入，核算预出货款票，管理更高效。</p>
	                            <h3>票据管理</h3>
	                        </div>
                        </a>
                    </div>
                    <!-- 票据管理 end -->

                    <!-- 票据市场 -->
                    <div class="gnmkItem">
                    	<a href="${basePath}/paperMarket">
	                        <div class="gnmkShow">
	                            <img src="${imagePath}/website/newhome/icon-pjsc.png" alt="票据市场" width="80" height="80">
	                            <h3>票据市场</h3>
	                            <div class="hengxian"></div>
	                        </div>
	                        <div class="gnmkHide">
	                            <p>集成票据订单，打造票据市场，收票更方便！</p>
	                            <h3>票据市场</h3>
	                        </div>
                        </a>
                    </div>
                    <!-- 票据市场 end -->
                </div>

            </div>
            <!-- 功能模块 end -->

            <!-- 技术支持 -->
            <div class="module-jszc">
                <div class="rowCenter">
                    <!-- 计算器 -->
                    <div id="calculator" class="jszcCon">
                        <h3>计算器</h3>
                        <div class="jsq-con">
                            <div class="jsq-left">
                                <div class="inputItem long">
                                    <input type="number" placeholder="请输入票面金额" id="allmoney" class="validate[required,custom[allprice]]">
                                    <span class="unit">万元</span>
                                </div>
                                <div>
                                    <div class="inputItem time">
                                        <input type="text" id="start" readonly="readonly" placeholder="请选择贴票日期">
                                        <span class="timeIcon"></span>
                                    </div>
                                    <div class="inputItem time" style="float: right">
                                        <input type="text" id="end" readonly="readonly" placeholder="请选择到期日期">
                                        <span class="timeIcon"></span>
                                    </div>
                                </div>
                                <div class="inputItem long">
                                    <input type="number" placeholder="请输入年利率" id="rateYear" onblur="rateYear();" class="validate[required,custom[jisuan]]">
                                    <span class="unit">%</span>
                                </div>
                                <div class="inputItem long">
                                    <input type="number" placeholder="请输入月利率" id="rate" onblur="rate();" class="validate[required,custom[jisuan]]">
                                    <span class="unit">‰</span>
                                </div>
                                <div class="inputItem long">
                                    <input type="number" placeholder="请输入每十万贴息" id="rate2" onblur="rate2();" class="validate[custom[tennumber]]">
                                    <span class="unit">元</span>
                                </div>
                                <div class="inputItem long">
                                    <input type="number" placeholder="每十万的手续费用" id="rate1" onblur="rate();">
                                    <span class="unit">元</span>
                                </div>

                                <!-- 计算结果 -->
                                <table border="1">
                                    <tr>
                                        <th>计息天数（天）</th>
                                        <th>贴现利息（元）</th>
                                        <th>贴现金额（万元）</th>
                                    </tr>
                                    <tr>
                                        <td id="jxts">183</td>
                                        <td id="txlx"></td>
                                        <td id="txje"></td>
                                    </tr>
                                </table>
                                <!-- 计算结果 end-->
                            </div>

                            <div class="jsq-right">
                                <button type="button" class="qingkongBtn" onclick="empty();">清空</button>
                                <button type="button" class="jisuanBtn" onclick="c_jisuan();">=</button>
                            </div>
                        </div>
                    </div>
                    <!-- 计算器 end -->

                    <!-- 实时报价 -->
                    <div id="offer" class="jszcCon">
                        <h3>票据指数</h3>
                        <div class="ssbjCon">
                            <div>
                                <ul class="ssbjTab" style="display: none">
                                    <li>
                                        <input type="radio" name="ssbjTab" id="ssbjTab1">
                                        <label for="ssbjTab1">
                                            银行价格
                                        </label>
                                    </li>
                                    <li>
                                        <input type="radio" name="ssbjTab" id="ssbjTab2">
                                        <label for="ssbjTab2">
                                            票据指数
                                        </label>
                                    </li>
                                </ul>

                                <!-- 银行价格 -->
                                <div class="tabDiv1 none">
                                    <div>
                                        <div class="inputItem short">
                                            <input type="number" placeholder="请输入票面金额" >
                                        </div>
                                        <div class="inputItem time">
                                            <input type="date" placeholder="请输入到期日期">
                                            <span class="timeIcon"></span>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="inputItem long">
                                            <input type="number" placeholder="请输入承兑行行号/名称">
                                        </div>
                                        <button class="searchBtn">搜索</button>
                                    </div>

                                    <!-- 搜索结果 -->
                                    <table border="1">
                                        <tr>
                                            <th>价格来源</th>
                                            <th>银行名称</th>
                                            <th>银行类型</th>
                                            <th>剩余天数</th>
                                            <th>贴现利率</th>
                                        </tr>
                                        <tr>
                                            <td>银行秒贴报价</td>
                                            <td>建设银行</td>
                                            <td>国股</td>
                                            <td>365天</td>
                                            <td>4.1%</td>
                                        </tr>
                                    </table>
                                    <!-- 搜索结果 end-->

                                    <button type="button" class="ljcpBtn">立即出票</button>
                                </div>
                                <!-- 银行价格 end -->

                                <!-- 票据指数 -->
                                <div class="tabDiv2">
                                    <!-- 时间段选择 -->
                                    <ul class="zhishuTab">
                                        <li>
                                            <input type="radio" name="zhishu" id="one">
                                            <label for="one" class="active">一个月</label>
                                        </li>
                                        <li>
                                            <input type="radio" name="zhishu" id="three">
                                            <label for="three">三个月</label>
                                        </li>
                                        <li>
                                            <input type="radio" name="zhishu" id="half">
                                            <label for="half">六个月</label>
                                        </li>
                                        <li>
                                            <input type="radio" name="zhishu" id="year">
                                            <label for="year">一年</label>
                                        </li>
                                    </ul>
                                    <!-- 时间段选择 end -->


                                    <!-- 图标展示 -->
                                    <div class="chartShow" id="container">


                                    </div>
                                    <!-- 图标展示 end -->
                                </div>
                                <!-- 票据指数 end -->
                            </div>

                        </div>
                    </div>
                    <!-- 实时报价 end -->
                </div>
            </div>
            <!-- 技术支持 end -->

            <!-- 票据管理 -->
            <div class="module-pjgl">
                <div class="rowCenter">
                    <h2>票据管理</h2>
                    <p>票据在线全流程管理，批量导入、定时提醒、核算货款、分析数据，您想要的我们都可以给你！</p>
                    <div class="pjglGif-Con">
                        <div class="live live1">
                            <h3>质押</h3>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="live live2">
                            <h3>价格录入</h3>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="live live3">
                            <h3>入库</h3>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="live live4">
                            <span></span>
                            <span></span>
                        </div>
                        <div class="live live5">
                            <h3>待入账票据</h3>
                            <span></span>
                            <span></span>
                        </div>
                        <a href="${basePath}/zhangbu/account/book/out">
	                        <div class="live live6">
	                            <h3>表格<br>上传</h3>
	                            <span></span>
	                            <span></span>
	                        </div>
	                    </a>
                        <a href="${basePath}/zhangbu/statistical/analysis">
	                        <div class="live live7">
	                            <h3>统计<br>分析</h3>
	                            <span></span>
	                            <span></span>
	                        </div>
                        </a>
                        <a href="${basePath}/zhangbu/main">
	                        <div class="live live8">
	                            <h3>票据<br>提醒</h3>
	                            <span></span>
	                            <span></span>
	                        </div>
                        </a>
                        <a href="${basePath}/zhangbu/draftrecord">
	                        <div class="live live9">
	                            <h3>货款<br>核算</h3>
	                            <span></span>
	                            <span></span>
	                        </div>
                        </a>
                        <a href="${basePath}/zhangbu/account/book/out">
                        <div class="live live10">
                            <h3>账簿</h3>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="live live11">
                            <h3>出库</h3>
                            <span></span>
                            <span></span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 票据管理 end -->

            <!-- 市场信息 -->
            <div class="module-scxx">
                <div class="rowCenter">
                    <h2>市场信息</h2>
                    <div class="scxxCon">
                        <div class="scxx-left">
                            <img src="${imagePath}/website/newhome/shichangxinxi.png" alt="市场信息">
                        </div>
                        <div class="scxx-right">
                            <ul>
                            	[#if newsList??]
		                 		[#list newsList as new]
                        		<li>
                                    <a href="${basePath}/news/detail/${new.id}">
                                        <div>
                                            <p>${new.titleShow}</p>
                                            <div class="releaseTime">
                                                <span></span>${new.publishtime?string("yyyy-MM-dd") }
                                            </div>
                                        </div>
                                    </a>
                                </li>
                         		[/#list]
               			 		[/#if]
                            </ul>
                            <a href="${basePath}/news/index" class="seeMore">查看更多 > </a>
                        </div>
                    </div>

                </div>
                
            </div>
            <!-- 市场信息 end-->
            
        </div>
        <!-- content end -->

        <!-- footer -->
        <footer>
            <div class="rowCenter">
                <!-- 菜单 -->
                <div class="footMenu">
                    <ul>
                        <li><a href="${basePath }/aboutus/choice/suggest">关于我们</a></li>
                        <li><a href="javascript:checkAccount(0,'/discountrecord/discount_yp1');">我要出票</a></li>
                        <li><a href="javascript:checkAccount(1,'/hall/receiveOrderall1');">我要收票</a></li>
                        <li><a href="${basePath}/paperMarket">票据市场</a></li>
                        <li class="none"><a href="#">秒贴出票</a></li>
                        <li><a href="${basePath}/xieyi">用户协议</a></li>
                        <li><a href="${basePath }/statement">免责声明</a></li>
                    </ul>
                </div>
                <!-- 菜单 end -->


                <!-- 合作信息-->
                <div class="about">
                    <div class="hotline">
                        <p>客服热线</p>
                        <p>400-067-0002</p>
                        <p>（接听时间：09：00-17：30 )</p>
                    </div>


                    <div class="partner">
                        <h2>合作伙伴</h2>
                        <ul>
                            <li><a href="http://www.yonyou.com" target="_blank">用友</a></li>
                            <li><a href="http://www.ccb.com" target="_blank">中国建设银行</a></li>
                            <li><a href="http://www.bankcomm.com" target="_blank">交通银行</a></li>
                            <li><a href="http://www.bankofdl.com" target="_blank">大连银行</a></li>
                            <li><a href="http://www.boimc.com.cn" target="_blank">内蒙古银行</a></li>


                            <li><a href="http://www.cibfintech.com/ph/ph-digit.html" target="_blank">兴业数金</a></li>
                            <li><a href="http://www.icbc.com.cn" target="_blank">中国工商银行</a></li>
                            <li><a href="http://www.citicbank.com" target="_blank">中信银行</a></li>
                            <li><a href="http://bank.pingan.com" target="_blank">平安银行</a></li>
                            <li><a href="http://www.gdnybank.com" target="_blank">广州南粤银行</a></li>


                            <li><a href="http://jr.jd.com" target="_blank">京东金融</a></li>
                            <li><a href="http://www.abchina.com" target="_blank">中国农业银行</a></li>
                            <li><a href="http://www.cebbank.com" target="_blank">中国光大银行</a></li>
                            <li><a href="http://www.cmbc.com.cn" target="_blank">中国民生银行</a></li>
                            <li><a href="javascript:void(0);" target="_blank">盘县万和村镇银行</a></li>
                        </ul>
                    </div>
                </div>
                <!-- 合作信息 end-->


                <!-- 友情链接 -->
                <p class="link">友情链接：<a href="http://www.wingroup.com.cn/" target="_blank">睿银控股集团</a></p>
                <!-- 友情链接 end -->


                <!-- 版权 -->
                <p class="copyright">© Copyright 2015 上海票管家金融服务有限公司 沪ICP备16003046号      地址：上海市虹口区东大名路501白玉兰广场1207</p>


            </div>


        </footer>
        <!-- footer end -->
    </div>
    <!-- body end -->

    <!-- 侧边悬浮 -->
    <div class="homeFold">
        <div class="foldfTitle">
            <img src="${imagePath}/website/newhome/fuwu.png" alt="建议">
            <p>二维码 · 建议</p>
        </div>
        <div class="foldfCon">
            <!-- <button class="close">×</button> -->
            <div>
                <div class="saomiao">
                    <div class="option">
                        <div class="optionIcon">
                            <img src="${imagePath}/website/newhome/icon-right-sm.png" width="30" height="30" alt="二维码">
                        </div>
                        <div class="optionNotes">
                            <p>扫描二维码</p>
                            <p>扫描他们，方便您的交易</p>
                        </div>
                    </div>
                    <div class="QRcode">
                        <div>
                            <img src="${imagePath}/website/newhome/xaizai.png" width="70" height="70" alt="下载app">
                            <p>下载app</p>
                        </div>
                        <div>
                            <img src="${imagePath}/website/newhome/guanzhu.png" width="70" height="70" alt="微信公众号">
                            <p>微信公众号</p>
                        </div>
                    </div>
                </div>
                <div class="option" id="opinion">
                    <div class="optionIcon">
                        <img src="${imagePath}/website/newhome/icon-right-yj.png" width="30" height="30" alt="意见反馈">
                    </div>
                    <div class="optionNotes">
                        <p class="title">意见反馈</p>
                        <p class="Notes">我们并不完美，需要您的建议</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 侧边悬浮 end -->

	
	<!-- mask -->
 	<div class="maskWindow" style="display: block;">
	    <div class="maskBg"></div>
	    <div class="maskWrap maskWrapSmall">
	      <!-- maskTop -->
	      <div class="maskTop">
	        <div class="maskTitle">手机号验证</div>
	        <button class="maskCloseIcon" onclick="closeMask()">×</button>
	      </div>
	      <!-- maskTop -->
	
	      <!-- maskCon -->
	      <div class="maskCon">
	        <div class="maskDiv">
	        
	        <!-- 注册弹窗 -->
	        <div>
	        	<div>
	        		<span>价格</span>
	        		<span>属性</span>
	        	</div>
	        	<div><input></div>
	        	<div><button>提交</button></div>
	        </div>
	        
	        <div>
	        	<div>
	        		<img src="https://img.utiexian.com/website/newhome/190219/xunjia.png">
	        	</div>
	        	<p>
	        </div>
	        <!-- 注册弹窗 end -->
	        </div>
	       </div>	
	      </div>
      </div>	
	<!-- mask end -->

    <!-- 意见弹窗 -->
    <div class="popupWindow">
        <div class="popup">
            <button id="closePopup" class="close"></button>
            <div class="popupCon">
                <p>我们期待你的意见</p>
                <textarea id="messagecontent" placeholder="如果在使用过程中有不好用的地方，请您大声说出来。我们会每天关注您的反馈，不断优化产品，为您提供更好的服务。" class="validate[required]" style="resize:none;"></textarea>

                <p>请留下您的联系方式</p>
                <input id="messagenumber" type="text" placeholder="请输入常用手机号/QQ号/微信" class="validate[required]">

                <button type="button" onclick="yjsave()">提交</button>
            </div>
        </div>
    </div>
    <!-- 意见弹窗 end -->
</div>
</body>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${pluPath}/highcharts/highcharts.js"></script>
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
<script type="text/javascript">
$(function(){
	openCib();
	$("#start").val(fullyear + "-" + month + "-" + date);//计算器
	$("#end").val(endfullyear + "-" + endmonth + "-" + enddate);//计算器
	
	 $("#opinion").click("on",function () {
         $(".popupWindow").show("slow");
         $(".popup").addClass("open");
     });

     $("#closePopup").click("on",function () {
         $(".popup").removeClass("open");
         $(".popupWindow").hide(500);
     });
     
     piaoju(1);
});

/*  swiper  */
var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    paginationClickable: true,
    parallax: true,
    loop : true,
    speed: 600,
    autoplay : 15000,
    autoplayDisableOnInteraction : false,
});

//初始化日期
var begintimelong = Date.parse(new Date());
var begintime = new Date(begintimelong);
var fullyear = begintime.getFullYear();
//获取完整的年份(4位,1970-????)
var month = begintime.getMonth() + 1;
//获取当前月份(0-11,0代表1月)
var date = begintime.getDate();
//获取当前日(1-31)

var endtime = new Date(begintimelong + 183 * 24 * 60 * 60 * 1000);
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
/**
 * 登录执剑人
 */
function openCib(){
	var memberId = '${member.id}';
  	if(memberId == null || $.trim(memberId) == "")return;
  	$.ajax({
		url:"${bootAppPath}/cib/corp/query",
		type:"POST",
		data:{memberId:memberId},
		dataType:"json",
		success:function(data){
			var data = data.data;
			console.log(data);
			if(data.response == "success"){
				if(data.data.cib != null && (data.data.cib.type == 1 || data.data.cib.type == 0)){
					$(".openCib").removeClass("none");
					$("#openCib").attr("href",data.data.login_url);
				}
			}
		},error:function(json){
			console.log("获取数据失败！");
		}
	});
 };
 
//日历
 !function(){
     laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
 }();
 //日期范围限制
	//贴现日期
 var start = {
     elem: '#start',
     format: 'YYYY-MM-DD',
     min: laydate.now(), //设定最小日期为当前日期
     max: '2099-06-16', //最大日期
     istime: false,//时分秒
     isclear: false,//清空
     istoday: false,//今天
     issure: false,//确认
     choose: function(datas){
         end.min = datas; //开始日选好后，重置结束日的最小日期
         end.start = datas //将结束日的初始值设定为开始日
         jixidate();
     }
 };
	//到期日期
 var end = {
     elem: '#end',
     format: 'YYYY-MM-DD',
     min: laydate.now(),
     max: '2099-06-16',
     istime: false,//时分秒
     isclear: false,//清空
     istoday: false,//今天
     issure: false,//确认
     choose: function(datas){
         start.max = datas; //结束日选好后，充值开始日的最大日期
         jixidate();
     }
 };
 laydate(start);
 laydate(end);
	
	/**
	* 年利率修改触发
	*/
	function rateYear(){
		var rateYear = $("#rateYear").val();
		var rate =(Number(rateYear)/12*10).toFixed(2);
		var jxts = $("#jxts").html();
		var rate1 = $("#rate1").val();
		var rate2 = Number(rate1) + (Number(rate*1)/30/1000*100000*Number(jxts));
		$("#rate").val((rate*1).toFixed(2));
	    $("#rate2").val((rate2*1).toFixed(2));
	};
	
	/**
	* 月利率修改触发
	*/
	function rate(){
		var jxts = $("#jxts").html();
		var rate = $("#rate").val();
		var rate1 = $("#rate1").val();
	    var rate2 = Number(rate1) + (Number(rate*1)/30/1000*100000*Number(jxts));
	    $("#rate2").val((rate2*1).toFixed(2));
	    $("#rateYear").val((rate*1.2).toFixed(2));
	};
	
	/**
	* 每十万修改触发
	*/
	function rate2(){
		var jxts = $("#jxts").html();
		var rate2 = $("#rate2").val();
		var rate1 = $("#rate1").val();
	    var rate =  (Number(rate2*1)-Number(rate1))/Number(jxts*1)/100000*1000*30;
	    $("#rate").val((rate*1).toFixed(2));
	    $("#rateYear").val((rate*12/10).toFixed(2));
	};
	
	//计息天数
	function jixidate() {
		var startDate = $("#start").val();
		var endDate = $("#end").val();
		var type6 = 2;//纸票1、电票2
		$.ajax({
			url:"${basePath}/homepage/xunjia/jixidate",
			type:"post",
			data:{"startDate":startDate,"endDate":endDate,"type6":type6},
			dataType:"json",
			success:function(data){
                $("#jxts").html(data.jxts);
			}
		});
	}
	
	function c_jisuan(){
        var type1 = 2;
        var type2 = 1;
        var start = $("#start").val();
        var end = $("#end").val();
        var allmoney = $("#allmoney").val();
        var jxts=$("#jxts").text();
        var rate1=$("#rate1").val();
        var rate2=$("#rate2").val();
        var rate = $("#rateYear").val();//年息
        if(!$("#allmoney").validationEngine("validate")){
    		$("#allmoney").focus();
    		setTimeout(function(){$("#allmoney").validationEngine('hideAll');},1000);
    		return;
    	}
       	if(!$("#rateYear").validationEngine("validate")){
       		$("#rateYear").focus();
       		setTimeout(function(){$("#rateYear").validationEngine('hideAll');},1000);
       		return;
       	}
        
        $.ajax({
			url:"${basePath}/homepage/calculator1",
			type:"post",
			data:{"type2":1,"type1":2,"rate":rate,"rate1":rate1,"rate2":rate2,"allmoney":allmoney,"start":start,"end":end,"jxts":jxts},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 $("#txlx").text(data.txlx);//贴现利息
					 var allmoney = $("#allmoney").val();
					 var txje = (Number(allmoney)*10000)-Number(data.txlx);
		             $("#txje").text((txje/10000).toFixed(2));//贴现金额
				}
			}
        })	
	}
	
	/**
	*  计算器清空数据
	*/
	function empty(){
		$("#allmoney").val("");
		$("#rate2").val("");
		$("#rateYear").val("");
		$("#rate").val("");
		$("#rate1").val("");
		$("#poundage").val("");
	};
	
	/**
	* 意见反馈
	*/
	function yjsave(){
		if(!$("#messagecontent").validationEngine("validate")){
			$("#messagecontent").focus();
			setTimeout(function(){$("#messagecontent").validationEngine('hideAll');},1000);
			return;
		}
		if(!$("#messagenumber").validationEngine("validate")){
			$("#messagenumber").focus();
			setTimeout(function(){$("#messagenumber").validationEngine('hideAll');},1000);
			return;
		}
		var messagecontent=$("#messagecontent").val();
		var messagenumber=$("#messagenumber").val();
		$.ajax({
			url:"${basePath}/homepage/message/save",
			type:"POST",
			data:{'messagenumber':messagenumber,'messagecontent':messagecontent},
			dataType:"json",
			success:function(data){
				if(data.response == 'success'){
					alert(data.msg);
					$("#messagecontent").val("");
					$("#messagenumber").val("");
					$(".popup").removeClass("open");
			        $(".popupWindow").hide(500);
				}else{
					alert(data.msg);
				}
			}
		});
	}
	
	/* 一个月 */
    $('#one').click(function() {
		$('#one').next().addClass("active");
		$('#three').next().removeClass("active");
		$('#half').next().removeClass("active");
		$('#year').next().removeClass("active");
		piaoju(1);
 	});
    /* 三个月 */
    $('#three').click(function() {
		$('#three').next().addClass("active");
    	$('#one').next().removeClass("active");
		$('#half').next().removeClass("active");
		$('#year').next().removeClass("active");
		piaoju(3);
 	});
    /* 半年 */
    $('#half').click(function() {
		$('#half').next().addClass("active");
    	$('#one').next().removeClass("active");
		$('#three').next().removeClass("active");
		$('#year').next().removeClass("active");
		piaoju(6);
 	});
    /* 一年 */
    $('#year').click(function() {
		$('#year').next().addClass("active");
    	$('#one').next().removeClass("active");
		$('#three').next().removeClass("active");
		$('#half').next().removeClass("active");
		piaoju(12);
 	});
    
	//票据指数
	function piaoju(months) {
		$.ajax({
			url: '${bootAppPath}/out/historyprice/chart',
			type: 'POST',
			dataType: 'json',
			data: {'months':months},
			success: function(data){
				if(data.data.response=="success")
					console.log(data);
				highcharts(data.data);
			}
		});
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

/**
 *  大小票的跳转
 */
function enterprise(){
	var memberId = '${member.id}';
	if(memberId==null||memberId==""){
		window.location.href ="${basePath}/login";
		return;
	}
	$.ajax({
		url:"${bootAppPath}/draftexchangecheck/get/by/memberid",
		type:"POST",
		data:{memberId:memberId},
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.data.response == 'success'){
				var data = data.data.data;
				if(data!=null&&data!=""){
					window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
				}else{
					window.location.href ="${basePath}/bigsmall/qiye/yushen";
				}
			}else{
				layer.alert(data.data.msg);
			}
		},error:function(json){
			layer.alert("获取信息失败！");
		}
	});
}
</script>
[/@main.body]