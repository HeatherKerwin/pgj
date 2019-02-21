<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="applicable-device" content="mobile" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>票据价格</title>
<link href="https://static.utiexian.com/css/common/flex.css" rel="stylesheet" type="text/css" />
<link href="https://static.utiexian.com/css/common/webBase.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<style>
    .selectbar{
        color: #d43c33;
        border-bottom: solid 2px #d43c33;
    }
</style>
<body class="bcwhite">
<!--header 开始-->
<header class="flex-row bcd43c33 w h45 pf top f18 lh45 cwhite tc zi12">
    <h2 class="w">票据价格</h2>
</header>
<!--header 结束-->

<!-- 公司名字 -->  
<div class="flex-row h24 pa lh25 pl10 t45 f14">
	<#if orgName>
		${orgName}&nbsp;
	</#if>
	<#if day>
		${day}
	</#if>
	报价：
</div>

<div class="flex flex-direction-column pa t70 w zi10 f14" style="height: calc(100% - 125px)" id="tanContainer">
    <!-- tab标签-->
    <div class="title_bar pf left right zi11 bcwhite pl10 pr10" id="tab">
        <ul id="pagenavi" class="tabTitle flex-row w tc lh35 c2d2d2d br3 bae0e0e0">
            <li class="flex-col-4 selectbar bre0e0e0" onclick="changeTab('0')">银票大电票</li>
            <li class="flex-col-4 bre0e0e0" onclick="changeTab('1')">银票小纸票</li>
            <li class="flex-col-4" onclick="changeTab('2')">银票小电票</li>
        </ul>
    </div>
    <!-- tab内容-->
    <div class="swiper-container pa t45 left right zi10 pl10 pr10 pb150" id="tabCon">
        <!--银票大电票-->
        <div class="flex-row flex-direction-column w" id="tabCon_0">
            <!--半年期-->
            <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 none big_half">
                <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                    半年期:年利率（%）
                </div>
                <div class="flex-row w tc lh30">
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">国股</div>
                            <div id="big_half_0"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">大商</div>
                            <div id="big_half_1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">小商</div>
                            <div id="big_half_2"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">外资</div>
                            <div id="big_half_3"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农商</div>
                            <div id="big_half_4"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农合</div>
                            <div id="big_half_5"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农信</div>
                            <div id="big_half_6"></div>
                        </div>
                    </div>
                    <div class="flex-col-1">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">村镇</div>
                            <div id="big_half_7"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!--一年期-->
            <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 none big_year">
                <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                    一年期:年利率（%）
                </div>
                <div class="flex-row w tc lh30">
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">国股</div>
                            <div id="big_year_0"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">大商</div>
                            <div id="big_year_1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">小商</div>
                            <div id="big_year_2"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">外资</div>
                            <div id="big_year_3"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农商</div>
                            <div id="big_year_4"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农合</div>
                            <div id="big_year_5"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农信</div>
                            <div id="big_year_6"></div>
                        </div>
                    </div>
                    <div class="flex-col-1">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">村镇</div>
                            <div id="big_year_7"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--银票大电票end-->

        <!--银票小纸票-->
        <div class="flex-row flex-direction-column w pb10 none" id="tabCon_1">
            <!--100万-300万-->
            <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 none zhi_smallA">
                <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                    100万-300万（含100万，含300万）
                </div>
                <div class="flex-row w tc lh30 flex-align-center">
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">&nbsp;</div>
                            <div class="bbe0e0e0 h60">月利率</div>
                            <div>足月</div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">国股</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallA_gg"></div>
                            <div class="zhi_smallA_gg1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">城商</div>
                            <div class="bbe0e0e0 h60 zhi_smallA_cs"></div>
                            <div  class="zhi_smallA_cs1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">外资</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallA_wz"></div>
                            <div class="zhi_smallA_wz1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农商</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallA_ns"></div>
                            <div class="zhi_smallA_ns1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农合</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallA_nh"></div>
                            <div class="zhi_smallA_nh1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农信</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallA_nx"></div>
                            <div class="zhi_smallA_nx1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">村镇</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallA_cz"></div>
                            <div class="zhi_smallA_cz1"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!--50万-100万-->
            <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 none zhi_smallB">
                <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                    50万-100万（含50万）
                </div>
                <div class="flex-row w tc lh30 flex-align-center">
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">&nbsp;</div>
                            <div class="bbe0e0e0 h60">月利率</div>
                            <div>足月</div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">国股</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_gg"></div>
                            <div class="zhi_smallB_gg1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">城商</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_cs"></div>
                            <div class="zhi_smallB_cs1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">外资</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_wz"></div>
                            <div class="zhi_smallB_wz1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农商</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_ns"></div>
                            <div class="zhi_smallB_ns1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农合</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_nh"></div>
                            <div class="zhi_smallB_nh1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农信</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_nx"></div>
                            <div class="zhi_smallB_nx1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">村镇</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallB_cz"></div>
                            <div class="zhi_smallB_cz1"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!--50万以下-->
            <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 zhi_smallC none">
                <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                    50万以下
                </div>
                <div class="flex-row w tc lh30 flex-align-center">
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">&nbsp;</div>
                            <div class="bbe0e0e0 h60">月利率</div>
                            <div>足月</div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">国股</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_gg"></div>
                            <div class="zhi_smallC_gg1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">城商</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_cs"></div>
                            <div class="zhi_smallC_cs1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">外资</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_wz"></div>
                            <div class="zhi_smallC_wz1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农商</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_ns"></div>
                            <div class="zhi_smallC_ns1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农合</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_nh"></div>
                            <div class="zhi_smallC_nh1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1 bre0e0e0">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">农信</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_nx"></div>
                            <div class="zhi_smallC_nx1"></div>
                        </div>
                    </div>
                    <div class="flex-col-1">
                        <div class="flex-row flex-direction-column w">
                            <div class="bbe0e0e0">村镇</div>
                            <div class="bbe0e0e0 h60 f12 zhi_smallC_cz"></div>
                            <div class="zhi_smallC_cz1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--银票小纸票end-->

        <!--银票小电票-->
        <div class="flex-row flex-direction-column w pb10 none" id="tabCon_2">
            <!--100万以上（含100万）-->
            <div class="dian_smallA none">
                <div class="f18 cfc4d42 lh40 mt20">100万以上（含100万）:年利率</div>
                <!--0-90天-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 dian_smallA1 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        0-90天
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallA1_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallA1_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallA1_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallA1_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallA1_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallA1_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallA1_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--90天-178天-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 dian_smallA2 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        90天-178天
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallA2_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallA2_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallA2_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallA2_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallA2_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallA2_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallA2_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--178天以上-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 dian_smallA3 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        178天以上
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallA3_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallA3_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallA3_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallA3_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallA3_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallA3_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallA3_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--50万-100万（含50万）-->
            <div class="dian_smallB none">
                <div class="f18 cfc4d42 lh40 mt20">50万-100万（含50万）:年利率</div>
                <!--0-90天-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 dian_smallB1 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        0-90天
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallB1_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallB1_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallB1_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallB1_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallB1_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallB1_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallB1_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--90天-178天-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 dian_smallB2 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        90天-178天
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallB2_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallB2_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallB2_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallB2_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallB2_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallB2_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallB2_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--178天以上-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 dian_smallB3 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        178天以上
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallB3_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallB3_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallB3_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallB3_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallB3_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallB3_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallB3_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--50万以下年利率-->
            <div class="dian_smallC none">
                <div class="f18 cfc4d42 lh40 mt20">50万以下:年利率</div>
                <!--0-90天-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 dian_smallC1 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        0-90天
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallC1_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallC1_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallC1_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallC1_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallC1_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallC1_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallC1_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--90天-178天-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 dian_smallC2 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        90天-178天
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallC2_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallC2_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallC2_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallC2_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallC2_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallC2_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallC2_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--178天以上-->
                <div class="flex-row flex-direction-row flex-direction-column w bae0e0e0 mt10 dian_smallC3 none">
                    <div class="flex-row flex-justify-center w bbe0e0e0 f18 cfc4d42 lh40">
                        178天以上
                    </div>
                    <div class="flex-row w tc lh30">
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">国股</div>
                                <div class="dian_smallC3_gg"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">城商</div>
                                <div class="dian_smallC3_cs"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">外资</div>
                                <div class="dian_smallC3_wz"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农商</div>
                                <div class="dian_smallC3_ns"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农合</div>
                                <div class="dian_smallC3_nh"></div>
                            </div>
                        </div>
                        <div class="flex-col-1 bre0e0e0">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">农信</div>
                                <div class="dian_smallC3_nx"></div>
                            </div>
                        </div>
                        <div class="flex-col-1">
                            <div class="flex-row flex-direction-column w">
                                <div class="bbe0e0e0">村镇</div>
                                <div class="dian_smallC3_cz"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--银票小电票end-->
    </div>
</div>

<div class="flex flex-direction-column w pf zi12 bottom h150 f14 lh30 bcf5f5f5">
    <div class="flex-row flex-direction-row pl20 pr20 mt10">
        <div class="w80">手机号</div>
        <input class="flex-col-auto b0 bbe0e0e0 bcn" type="tel" placeholder="请输入手机号" id="mobile"/>
    </div>
    <div class="flex-row flex-direction-row pl20 pr20 mt10">
        <div class="w80">验证码</div>
        <input type="tel" placeholder="请输入验证码" class="yzm_t flex-col-auto b0 bbe0e0e0 bcn" id="code"/>
        <a class="yzm bae0e0e0 br3 w120 tc ml10">获取验证码</a>
    </div>
    <input onclick="save()" class="b0 bcd43c33 cwhite lh35 br5 w240 bc mt16" type="button" value="点我注册票据管家" />
</div>



<div class="none">
	<header class="header">
		<h2>票据价格</h2>
	</header>
	<div style="margin:2px;">
		<#if orgName>
			${orgName}&nbsp;机构
		</#if>
		<#if day>
			${day}
		</#if>
		报价：
	</div>
	
	<div id="styleA" class="offer_con" style="display:none;">
		<div id="titleA1" class="offer_title">纸票大票价格（‰）</div>
		<table class="offer_form">
			<tr style="border: #E7E7E7 solid .1rem">
				<td class="border_r">国股</td>
				<td class="border_r">大商</td>
				<td class="border_r">小商</td>
				<td class="border_r">外资</td>
				<td class="border_r">农商</td>
				<td class="border_r">农合</td>
				<td class="border_r">农信</td>
				<td class="border_r">村镇</td>
			</tr>
			<tr id="zhiBig">
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
			</tr>
		</table>
		<div id="titleA2" class="offer_title">纸票大票价格（‰）</div>
		<table class="offer_form">
			<tr style="border: #E7E7E7 solid .1rem">
				<td class="border_r">国股</td>
				<td class="border_r">大商</td>
				<td class="border_r">小商</td>
				<td class="border_r">外资</td>
				<td class="border_r">农商</td>
				<td class="border_r">农合</td>
				<td class="border_r">农信</td>
				<td class="border_r">村镇</td>
			</tr>
			<tr id="dianBig">
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
			</tr>
		</table>
	</div>
	<div id="styleB" class="offer_con" style="display:none;">
		<div id="titleB" class="offer_title">--</div>
		<table class="offer_form">
			<tr style="border: #E7E7E7 solid .1rem">
				<td class="border_r">国股</td>
				<td class="border_r">城商</td>
				<td class="border_r">外资</td>
				<td class="border_r">农商</td>
				<td class="border_r">农合</td>
				<td class="border_r">农信</td>
				<td class="border_r">村镇</td>
			</tr>
			<tr id="other">
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
			</tr>
		</table>
	</div>
	<div id="styleB1" class="offer_con" style="display:none;">
		<div id="titleB1" class="offer_title">--</div>
		<table class="offer_form">
			<tr style="border: #E7E7E7 solid .1rem">
				<td class="border_r">国股</td>
				<td class="border_r">大商</td>
				<td class="border_r">小商</td>
				<td class="border_r">外资</td>
				<td class="border_r">农商</td>
				<td class="border_r">农合</td>
				<td class="border_r">农信</td>
				<td class="border_r">村镇</td>
			</tr>
			<tr id="other1">
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
			</tr>
		</table>
	</div>
	<div id="styleB2" class="offer_con" style="display:none;">
		<div id="titleB2" class="offer_title">--</div>
		<table class="offer_form">
			<tr style="border: #E7E7E7 solid .1rem">
				<td class="border_r"></td>
				<td class="border_r">国股</td>
				<td class="border_r">城商</td>
				<td class="border_r">外资</td>
				<td class="border_r">农商</td>
				<td class="border_r">农合</td>
				<td class="border_r">农信</td>
				<td class="border_r">村镇</td>
			</tr>
			<tr id="other2" style="border: #E7E7E7 solid .1rem">
				<td class="border_r">月利率（‰）</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
			</tr>
			<tr id="other3">
				<td class="border_r" style="font-size: 5">足月(每10万)</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
				<td class="border_r">--</td>
			</tr>
		</table>
	</div>
	<div class="login_t">
		<form id="my-form" action="../app/offer/register.htm" method="post">
			<div class="phone">
				<div class="fl w_20">手机号</div>
				<div class="fl w_50">
					<input type="tel" id="mobile" placeholder="请输入手机号"/>
				</div>
			</div>
			<div class="cleaar"></div>
			<div class="captcha">
				<div class="fl w_20">验证码</div>
				<div class="fl w_40">
					<input type="tel" id="code" placeholder="请输入验证码" class="yzm_t"/>
				</div>
				<div class="fl">
					<a class="yzm">获取验证码</a>
				</div>
			</div>
		</form>
	</div>
	<input type="hidden" id="orgId" value="${orgId}">
    <input type="hidden" id="cityId" value="${cityId}">
    <input type="hidden" id="day" value="${day}">
    <input onclick="save();" class="offer_btn" type="button" value="点我注册获取更多票据价格"/>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var validCode=true;
	$(".yzm").click (function() {
		var mobile = $("#mobile").val();
		var time=60;
		var code=$(this);
		if (validCode) {
			validCode=false;
			code.addClass("msgs1");
			if(!checkMobile(mobile)){
				alert("手机号码不正确!");
				code.removeClass("msgs1");
				validCode = true;
				return;
			}
			$.ajax({
				url:"../app/sendcode.htm",
				type:"post",
				data:{"mobile":mobile},
				dataType:"text",
				success:function(data){
					if(data=="error"){
						alert("发送失败，请稍后...");
						code.removeClass("msgs1");
						validCode = true;
					}else{
						var t=setInterval(function(){
							time--;
							code.html(time+"秒");
							if (time==0) {
								clearInterval(t);
								code.html("重新获取");
								validCode=true;
								code.removeClass("msgs1");
							}
						},1000);
					}
				}
			});
		}
	});
	initData();
});

function initData(){
	var orgId = $("#orgId").val();
	var cityId = $("#cityId").val();
	var day = $("#day").val();
	
	var data = {"orgId":orgId,"day":day};
	if(cityId != null && cityId != ""){
		data.cityId = cityId;
	}

	$.ajax({
		url:"../app/offer/get.htm",
		type:"post",
		data:data,
		dataType:"json",
		success:function(data){
			for (var i = 0; i < data.data.length; i++) {
				if(data.data[i].ptid == 21){//大电票一年期
					$(".big_half").removeClass("none");
					if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
						$("#big_half_0").html(data.data[i].gg);
					}else{
						$("#big_half_0").html("--");
					}
					if(data.data[i].ds != "--" && data.data[i].ds != "" && data.data[i].ds != null){
						$("#big_half_1").html(data.data[i].ds);
					}else{
						$("#big_half_1").html("--");
					}
					if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
						$("#big_half_2").html(data.data[i].cs);
					}else{
						$("#big_half_2").html("--");
					}
					if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
						$("#big_half_3").html(data.data[i].wz);
					}else{
						$("#big_half_3").html("--");
					}
					if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
						$("#big_half_4").html(data.data[i].ns);
					}else{
						$("#big_half_4").html("--");
					}
					if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
						$("#big_half_5").html(data.data[i].nh);
					}else{
						$("#big_half_5").html("--");
					}
					if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
						$("#big_half_6").html(data.data[i].nx);
					}else{
						$("#big_half_6").html("--");
					}
					if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
						$("#big_half_7").html(data.data[i].cz);
					}else{
						$("#big_half_7").html("--");
					}
				}
				if(data.data[i].ptid == 2){//大点票半年期
					$(".big_year").removeClass("none");
					if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
						$("#big_year_0").html(data.data[i].gg);
					}else{
						$("#big_year_0").html("--");
					}
					if(data.data[i].ds != "--" && data.data[i].ds != "" && data.data[i].ds != null){
						$("#big_year_1").html(data.data[i].ds);
					}else{
						$("#big_year_1").html("--");
					}
					if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
						$("#big_year_2").html(data.data[i].cs);
					}else{
						$("#big_year_2").html("--");
					}
					if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
						$("#big_year_3").html(data.data[i].wz);
					}else{
						$("#big_year_3").html("--");
					}
					if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
						$("#big_year_4").html(data.data[i].ns);
					}else{
						$("#big_year_4").html("--");
					}
					if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
						$("#big_year_5").html(data.data[i].nh);
					}else{
						$("#big_year_5").html("--");
					}
					if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
						$("#big_year_6").html(data.data[i].nx);
					}else{
						$("#big_year_6").html("--");
					}
					if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
						$("#big_year_7").html(data.data[i].cz);
					}else{
						$("#big_year_7").html("--");
					}
				}
				if(data.data[i].ptid == 5){//纸票小票100-300万
					$(".zhi_smallA").removeClass("none");
					if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
						if(data.data[i].gg1 != "--" && data.data[i].gg1 != "" && data.data[i].gg1 != null){
							$(".zhi_smallA_gg").html(data.data[i].gg+"‰+"+data.data[i].gg1);
						}else{
							$(".zhi_smallA_gg").html(data.data[i].gg+"‰");
						}
					}else{
						$(".zhi_smallA_gg").html("--");
					}
					if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
						if(data.data[i].cs1 != "--" && data.data[i].cs1 != "" && data.data[i].cs1 != null){
							$(".zhi_smallA_cs").html(data.data[i].cs+"‰+"+data.data[i].cs1);
						}else{
							$(".zhi_smallA_cs").html(data.data[i].cs+"‰");
						}
					}else{
						$(".zhi_smallA_cs").html("--");
					}
					if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
						if(data.data[i].wz1 != "--" && data.data[i].wz1 != "" && data.data[i].wz1 != null){
							$(".zhi_smallA_wz").html(data.data[i].wz+"‰+"+data.data[i].wz1);
						}else{
							$(".zhi_smallA_wz").html(data.data[i].wz+"‰");
						}
					}else{
						$(".zhi_smallA_wz").html("--");
					}
					if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
						if(data.data[i].ns1 != "--" && data.data[i].ns1 != "" && data.data[i].ns1 != null){
							$(".zhi_smallA_ns").html(data.data[i].ns+"‰+"+data.data[i].ns1);
						}else{
							$(".zhi_smallA_ns").html(data.data[i].ns+"‰");
						}
					}else{
						$(".zhi_smallA_ns").html("--");
					}
					if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
						if(data.data[i].nh1 != "--" && data.data[i].nh1 != "" && data.data[i].nh1 != null){
							$(".zhi_smallA_nh").html(data.data[i].nh+"‰+"+data.data[i].nh1);
						}else{
							$(".zhi_smallA_nh").html(data.data[i].nh+"‰");
						}
					}else{
						$(".zhi_smallA_nh").html("--");
					}
					if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
						if(data.data[i].nx1 != "--" && data.data[i].nx1 != "" && data.data[i].nx1 != null){
							$(".zhi_smallA_nx").html(data.data[i].nx+"‰+"+data.data[i].nx1);
						}else{
							$(".zhi_smallA_nx").html(data.data[i].nx+"‰");
						}
					}else{
						$(".zhi_smallA_nx").html("--");
					}
					if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
						if(data.data[i].cz1 != "--" && data.data[i].cz1 != "" && data.data[i].cz1 != null){
							$(".zhi_smallA_cz").html(data.data[i].cz+"‰+"+data.data[i].cz1);
						}else{
							$(".zhi_smallA_cz").html(data.data[i].cz+"‰");
						}
					}else{
						$(".zhi_smallA_cz").html("--");
					}
					
					if(data.data[i].gg3 != "--" && data.data[i].gg3 != "" && data.data[i].gg3 != null){
						$(".zhi_smallA_gg1").html(data.data[i].gg3);
					}else{
						$(".zhi_smallA_gg1").html("--");
					}
					if(data.data[i].cs3 != "--" && data.data[i].cs3 != "" && data.data[i].cs3 != null){
						$(".zhi_smallA_cs1").html(data.data[i].cs3);
					}else{
						$(".zhi_smallA_cs1").html("--");
					}
					if(data.data[i].wz3 != "--" && data.data[i].wz3 != "" && data.data[i].wz3 != null){
						$(".zhi_smallA_wz1").html(data.data[i].wz3);
					}else{
						$(".zhi_smallA_wz1").html("--");
					}
					if(data.data[i].ns3 != "--" && data.data[i].ns3 != "" && data.data[i].ns3 != null){
						$(".zhi_smallA_ns1").html(data.data[i].ns3);
					}else{
						$(".zhi_smallA_ns1").html("--");
					}
					if(data.data[i].nh3 != "--" && data.data[i].nh3 != "" && data.data[i].nh3 != null){
						$(".zhi_smallA_nh1").html(data.data[i].nh3);
					}else{
						$(".zhi_smallA_nh1").html("--");
					}
					if(data.data[i].nx3 != "--" && data.data[i].nx3 != "" && data.data[i].nx3 != null){
						$(".zhi_smallA_nx1").html(data.data[i].nx3);
					}else{
						$(".zhi_smallA_nx1").html("--");
					}
					if(data.data[i].cz3 != "--" && data.data[i].cz3 != "" && data.data[i].cz3 != null){
						$(".zhi_smallA_cz1").html(data.data[i].cz3);
					}else{
						$(".zhi_smallA_cz1").html("--");
					}
				}
				if(data.data[i].ptid == 8){//纸票小票50-100万
					$(".zhi_smallB").removeClass("none");
					if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
						if(data.data[i].gg1 != "--" && data.data[i].gg1 != "" && data.data[i].gg1 != null){
							$(".zhi_smallB_gg").html(data.data[i].gg+"‰+"+data.data[i].gg1);
						}else{
							$(".zhi_smallB_gg").html(data.data[i].gg+"‰");
						}
					}else{
						$(".zhi_smallB_gg").html("--");
					}
					if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
						if(data.data[i].cs1 != "--" && data.data[i].cs1 != "" && data.data[i].cs1 != null){
							$(".zhi_smallB_cs").html(data.data[i].cs+"‰+"+data.data[i].cs1);
						}else{
							$(".zhi_smallB_cs").html(data.data[i].cs+"‰");
						}
					}else{
						$(".zhi_smallB_cs").html("--");
					}
					if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
						if(data.data[i].wz1 != "--" && data.data[i].wz1 != "" && data.data[i].wz1 != null){
							$(".zhi_smallB_wz").html(data.data[i].wz+"‰+"+data.data[i].wz1);
						}else{
							$(".zhi_smallB_wz").html(data.data[i].wz+"‰");
						}
					}else{
						$(".zhi_smallB_wz").html("--");
					}
					if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
						if(data.data[i].ns1 != "--" && data.data[i].ns1 != "" && data.data[i].ns1 != null){
							$(".zhi_smallB_ns").html(data.data[i].ns+"‰+"+data.data[i].ns1);
						}else{
							$(".zhi_smallB_ns").html(data.data[i].ns+"‰");
						}
					}else{
						$(".zhi_smallB_ns").html("--");
					}
					if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
						if(data.data[i].nh1 != "--" && data.data[i].nh1 != "" && data.data[i].nh1 != null){
							$(".zhi_smallB_nh").html(data.data[i].nh+"‰+"+data.data[i].nh1);
						}else{
							$(".zhi_smallB_nh").html(data.data[i].nh+"‰");
						}
					}else{
						$(".zhi_smallB_nh").html("--");
					}
					if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
						if(data.data[i].nx1 != "--" && data.data[i].nx1 != "" && data.data[i].nx1 != null){
							$(".zhi_smallB_nx").html(data.data[i].nx+"‰+"+data.data[i].nx1);
						}else{
							$(".zhi_smallB_nx").html(data.data[i].nx+"‰");
						}
					}else{
						$(".zhi_smallB_nx").html("--");
					}
					if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
						if(data.data[i].cz1 != "--" && data.data[i].cz1 != "" && data.data[i].cz1 != null){
							$(".zhi_smallB_cz").html(data.data[i].cz+"‰+"+data.data[i].cz1);
						}else{
							$(".zhi_smallB_cz").html(data.data[i].cz+"‰");
						}
					}else{
						$(".zhi_smallB_cz").html("--");
					}
					
					if(data.data[i].gg3 != "--" && data.data[i].gg3 != "" && data.data[i].gg3 != null){
						$(".zhi_smallB_gg1").html(data.data[i].gg3);
					}else{
						$(".zhi_smallB_gg1").html("--");
					}
					if(data.data[i].cs3 != "--" && data.data[i].cs3 != "" && data.data[i].cs3 != null){
						$(".zhi_smallB_cs1").html(data.data[i].cs3);
					}else{
						$(".zhi_smallB_cs1").html("--");
					}
					if(data.data[i].wz3 != "--" && data.data[i].wz3 != "" && data.data[i].wz3 != null){
						$(".zhi_smallB_wz1").html(data.data[i].wz3);
					}else{
						$(".zhi_smallB_wz1").html("--");
					}
					if(data.data[i].ns3 != "--" && data.data[i].ns3 != "" && data.data[i].ns3 != null){
						$(".zhi_smallB_ns1").html(data.data[i].ns3);
					}else{
						$(".zhi_smallB_ns1").html("--");
					}
					if(data.data[i].nh3 != "--" && data.data[i].nh3 != "" && data.data[i].nh3 != null){
						$(".zhi_smallB_nh1").html(data.data[i].nh3);
					}else{
						$(".zhi_smallB_nh1").html("--");
					}
					if(data.data[i].nx3 != "--" && data.data[i].nx3 != "" && data.data[i].nx3 != null){
						$(".zhi_smallB_nx1").html(data.data[i].nx3);
					}else{
						$(".zhi_smallB_nx1").html("--");
					}
					if(data.data[i].cz3 != "--" && data.data[i].cz3 != "" && data.data[i].cz3 != null){
						$(".zhi_smallB_cz1").html(data.data[i].cz3);
					}else{
						$(".zhi_smallB_cz1").html("--");
					}
				}
				if(data.data[i].ptid == 11){//纸票小票50万以下
					$(".zhi_smallC").removeClass("none");
					if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
						if(data.data[i].gg1 != "--" && data.data[i].gg1 != "" && data.data[i].gg1 != null){
							$(".zhi_smallC_gg").html(data.data[i].gg+"‰+"+data.data[i].gg1);
						}else{
							$(".zhi_smallC_gg").html(data.data[i].gg+"‰");
						}
					}else{
						$(".zhi_smallC_gg").html("--");
					}
					if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
						if(data.data[i].cs1 != "--" && data.data[i].cs1 != "" && data.data[i].cs1 != null){
							$(".zhi_smallC_cs").html(data.data[i].cs+"‰+"+data.data[i].cs1);
						}else{
							$(".zhi_smallC_cs").html(data.data[i].cs+"‰");
						}
					}else{
						$(".zhi_smallC_cs").html("--");
					}
					if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
						if(data.data[i].wz1 != "--" && data.data[i].wz1 != "" && data.data[i].wz1 != null){
							$(".zhi_smallC_wz").html(data.data[i].wz+"‰+"+data.data[i].wz1);
						}else{
							$(".zhi_smallC_wz").html(data.data[i].wz+"‰");
						}
					}else{
						$(".zhi_smallC_wz").html("--");
					}
					if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
						if(data.data[i].ns1 != "--" && data.data[i].ns1 != "" && data.data[i].ns1 != null){
							$(".zhi_smallC_ns").html(data.data[i].ns+"‰+"+data.data[i].ns1);
						}else{
							$(".zhi_smallC_ns").html(data.data[i].ns+"‰");
						}
					}else{
						$(".zhi_smallC_ns").html("--");
					}
					if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
						if(data.data[i].nh1 != "--" && data.data[i].nh1 != "" && data.data[i].nh1 != null){
							$(".zhi_smallC_nh").html(data.data[i].nh+"‰+"+data.data[i].nh1);
						}else{
							$(".zhi_smallC_nh").html(data.data[i].nh+"‰");
						}
					}else{
						$(".zhi_smallC_nh").html("--");
					}
					if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
						if(data.data[i].nx1 != "--" && data.data[i].nx1 != "" && data.data[i].nx1 != null){
							$(".zhi_smallC_nx").html(data.data[i].nx+"‰+"+data.data[i].nx1);
						}else{
							$(".zhi_smallC_nx").html(data.data[i].nx+"‰");
						}
					}else{
						$(".zhi_smallC_nx").html("--");
					}
					if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
						if(data.data[i].cz1 != "--" && data.data[i].cz1 != "" && data.data[i].cz1 != null){
							$(".zhi_smallC_cz").html(data.data[i].cz+"‰+"+data.data[i].cz1);
						}else{
							$(".zhi_smallC_cz").html(data.data[i].cz+"‰");
						}
					}else{
						$(".zhi_smallC_cz").html("--");
					}
					
					if(data.data[i].gg3 != "--" && data.data[i].gg3 != "" && data.data[i].gg3 != null){
						$(".zhi_smallC_gg1").html(data.data[i].gg3);
					}else{
						$(".zhi_smallC_gg1").html("--");
					}
					if(data.data[i].cs3 != "--" && data.data[i].cs3 != "" && data.data[i].cs3 != null){
						$(".zhi_smallC_cs1").html(data.data[i].cs3);
					}else{
						$(".zhi_smallC_cs1").html("--");
					}
					if(data.data[i].wz3 != "--" && data.data[i].wz3 != "" && data.data[i].wz3 != null){
						$(".zhi_smallC_wz1").html(data.data[i].wz3);
					}else{
						$(".zhi_smallC_wz1").html("--");
					}
					if(data.data[i].ns3 != "--" && data.data[i].ns3 != "" && data.data[i].ns3 != null){
						$(".zhi_smallC_ns1").html(data.data[i].ns3);
					}else{
						$(".zhi_smallC_ns1").html("--");
					}
					if(data.data[i].nh3 != "--" && data.data[i].nh3 != "" && data.data[i].nh3 != null){
						$(".zhi_smallC_nh1").html(data.data[i].nh3);
					}else{
						$(".zhi_smallC_nh1").html("--");
					}
					if(data.data[i].nx3 != "--" && data.data[i].nx3 != "" && data.data[i].nx3 != null){
						$(".zhi_smallC_nx1").html(data.data[i].nx3);
					}else{
						$(".zhi_smallC_nx1").html("--");
					}
					if(data.data[i].cz3 != "--" && data.data[i].cz3 != "" && data.data[i].cz3 != null){
						$(".zhi_smallC_cz1").html(data.data[i].cz3);
					}else{
						$(".zhi_smallC_cz1").html("--");
					}
				}
				if(data.data[i].ptid == 12 || data.data[i].ptid == 13 || data.data[i].ptid == 14){//电票小票100万以上
					$(".dian_smallA").removeClass("none");
					if(data.data[i].ptid == 12){//0-90
						$(".dian_smallA1").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallA1_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallA1_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallA1_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallA1_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallA1_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallA1_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallA1_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallA1_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallA1_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallA1_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallA1_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallA1_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallA1_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallA1_cz").html("--");
						}
					}
					if(data.data[i].ptid == 13){//90-178
						$(".dian_smallA2").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallA2_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallA2_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallA2_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallA2_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallA2_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallA2_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallA2_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallA2_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallA2_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallA2_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallA2_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallA2_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallA2_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallA2_cz").html("--");
						}
					}
					if(data.data[i].ptid == 14){//大于178
						$(".dian_smallA3").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallA3_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallA3_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallA3_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallA3_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallA3_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallA3_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallA3_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallA3_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallA3_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallA3_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallA3_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallA3_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallA3_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallA3_cz").html("--");
						}
					}
				}
				if(data.data[i].ptid == 15 || data.data[i].ptid == 16 || data.data[i].ptid == 17){//电票小票50-100万
					$(".dian_smallB").removeClass("none");
					if(data.data[i].ptid == 15){//0-90
						$(".dian_smallB1").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallB1_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallB1_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallB1_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallB1_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallB1_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallB1_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallB1_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallB1_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallB1_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallB1_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallB1_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallB1_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallB1_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallB1_cz").html("--");
						}
					}
					if(data.data[i].ptid == 16){//90-178
						$(".dian_smallB2").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallB2_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallB2_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallB2_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallB2_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallB2_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallB2_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallB2_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallB2_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallB2_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallB2_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallB2_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallB2_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallB2_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallB2_cz").html("--");
						}
					}
					if(data.data[i].ptid == 17){//大于178
						$(".dian_smallB3").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallB3_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallB3_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallB3_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallB3_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallB3_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallB3_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallB3_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallB3_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallB3_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallB3_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallB3_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallB3_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallB3_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallB3_cz").html("--");
						}
					}
				}
				if(data.data[i].ptid == 18 || data.data[i].ptid == 19 || data.data[i].ptid == 20){//电票小票50万以下
					$(".dian_smallC").removeClass("none");
					if(data.data[i].ptid == 18){//0-90
						$(".dian_smallC1").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallC1_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallC1_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallC1_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallC1_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallC1_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallC1_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallC1_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallC1_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallC1_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallC1_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallC1_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallC1_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallC1_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallC1_cz").html("--");
						}
					}
					if(data.data[i].ptid == 19){//90-178
						$(".dian_smallC2").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallC2_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallC2_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallC2_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallC2_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallC2_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallC2_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallC2_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallC2_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallC2_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallC2_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallC2_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallC2_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallC2_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallC2_cz").html("--");
						}
					}
					if(data.data[i].ptid == 20){//大于178
						$(".dian_smallC3").removeClass("none");
						if(data.data[i].gg != "--" && data.data[i].gg != "" && data.data[i].gg != null){
							$(".dian_smallC3_gg").html(data.data[i].gg);
						}else{
							$(".dian_smallC3_gg").html("--");
						}
						if(data.data[i].cs != "--" && data.data[i].cs != "" && data.data[i].cs != null){
							$(".dian_smallC3_cs").html(data.data[i].cs);
						}else{
							$(".dian_smallC3_cs").html("--");
						}
						if(data.data[i].wz != "--" && data.data[i].wz != "" && data.data[i].wz != null){
							$(".dian_smallC3_wz").html(data.data[i].wz);
						}else{
							$(".dian_smallC3_wz").html("--");
						}
						if(data.data[i].ns != "--" && data.data[i].ns != "" && data.data[i].ns != null){
							$(".dian_smallC3_ns").html(data.data[i].ns);
						}else{
							$(".dian_smallC3_ns").html("--");
						}
						if(data.data[i].nh != "--" && data.data[i].nh != "" && data.data[i].nh != null){
							$(".dian_smallC3_nh").html(data.data[i].nh);
						}else{
							$(".dian_smallC3_nh").html("--");
						}
						if(data.data[i].nx != "--" && data.data[i].nx != "" && data.data[i].nx != null){
							$(".dian_smallC3_nx").html(data.data[i].nx);
						}else{
							$(".dian_smallC3_nx").html("--");
						}
						if(data.data[i].cz != "--" && data.data[i].cz != "" && data.data[i].cz != null){
							$(".dian_smallC3_cz").html(data.data[i].cz);
						}else{
							$(".dian_smallC3_cz").html("--");
						}
					}
				}
			}
		}
	});
}

/*tab*/
$("#pagenavi li").each(function(){
    $(this).click(function(){
      $("#pagenavi li").removeClass("selectbar");
      $(this).addClass("selectbar");
    })
});

function changeTab(tabCon_num){
    for(i=0;i<=3;i++) {
 		$("#tabCon_"+i).addClass("none"); //将所有的层都隐藏
 	}
 	$("#tabCon_"+tabCon_num).removeClass("none");//显示当前层
};

//验证手机号码
function checkMobile(str) {
	var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	//如果手机号码不能通过验证
	return telReg;
}

function save(){//表单请求
	var mobile = $("#mobile").val();
	var code = $("#code").val();
	if(mobile==null || mobile.trim()==""){
		alert("请输入手机号！");
		return false;
	}
	if(code==null || code.trim()==""){
		alert("请输入验证码！");
		return false;
	}
	$.ajax({
		url:"../app/offer/register.htm",
		type:"post",
		data:{"mobile":mobile,"code":code},
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				window.location.href="https://app.utiexian.com/down.html";
			}else{
				alert(data.msg);
			}
		}
	});
}
</script>
</html>