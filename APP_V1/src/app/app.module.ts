import { NgModule, ErrorHandler } from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { apiService } from "../api.service";
import { IonicStorageModule } from '@ionic/storage';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { Camera } from '@ionic-native/camera';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { InAppBrowser } from '@ionic-native/in-app-browser';
import {CityPickerModule} from 'ionic2-city-picker';//城市三级联动
import { JPush } from 'ionic3-jpush';
import {QQSDK} from "@ionic-native/qqsdk";
import {BindMobilePage} from "../pages/login/bindMobile";
//import {WechatChenyu} from "wechat-chenyu";

//红包
//我的红包
import { MyredgadPage } from '../pages/account/myredgad';
//会员中心
import { VipaggregatePage } from '../pages/account/vipaggregate';
//会员升级
import { VipupgradePage } from '../pages/account/vipupgrade';
//红包领取
import { RookieredPage }  from '../pages/account/Rookiered';
//红包领取
import  { RedListPage } from '../pages/account/redList';

//开户
//申请开户
import { OpenaccountPage } from '../pages/openaccount/openaccount';
import { OpenaccountBPage } from '../pages/openaccount/openaccountB';
import { OpenaccountCPage } from '../pages/openaccount/openaccountC';
//行号查询
import { OpenaccountListPage } from '../pages/openaccount/openaccountList';
import { OpenaccountListBPage } from '../pages/openaccount/openaccountListB'
//证书上传
import { openaccountDPage } from '../pages/openaccount/openaccountD';
//审核
import { OpenaccountEPage } from '../pages/openaccount/openaccountE';
//小额鉴定
import { OpenaccountFPage } from '../pages/openaccount/openaccountF';
//开户成功-开户信息
import { OpenaccountSuccessPage } from '../pages/openaccount/openaccountSuccess';
//开户ORC
import { OpenaccountOrcPage } from '../pages/openaccount/openaccountOrc';
//绑定账户
import { BindingPage } from  '../pages/openaccount/binding';
import { BindingBPage } from '../pages/openaccount/bindingB';
import { BindingCPage } from  '../pages/openaccount/bindingC'
//开户声明
import { StatementPage } from '../pages/openaccount/statement';
import { StatementBPage } from '../pages/openaccount/statementB';
//免责声明
import { DisclaimerPage } from '../pages/openaccount/disclaimer';


//京东开户
//京东开户协议
import { JdsignaPage } from '../pages/jd/jdsigna';
//开户声明
import { JdinfoPage } from '../pages/jd/jdinfo';
//上传营业执照
import { jdLicensePage } from '../pages/jd/jdLicense';
//上传身份证
import { jdLicense1Page } from '../pages/jd/jdLicense1';
//上传银行卡
import { jdLicense2Page } from '../pages/jd/jdLicense2';
//审核中
import { jdLicense3Page } from '../pages/jd/jdLicense3';
//编辑修改资料
import { jdLicense3editPage } from '../pages/jd/jdLicense3edit';
//编辑图片上传
import { jdimgeditPage } from '../pages/jd/jdimgedit';
//小额鉴定
import { jdlicense4Page } from '../pages/jd/jdLicense4';
//银行卡列表
import { jdbanklistPage } from '../pages/jd/jdbanklist';
//银行卡绑定
import { jdbankbdPage } from '../pages/jd/jdbankbd';
//支行号选择
import { BranchlistPage } from '../pages/jd/branchlist';
//智票支行号选择
import { BranchlistBPage } from '../pages/jd/branchlistB';
//开户成功
import { JdSuccessPage } from '../pages/jd/jdSuccess';
//智票绑卡小额鉴定
import { jdbankbdBPage } from '../pages/jd/jdbankbdB';
//绑定
import { jdbindingPage } from '../pages/jd/jdbinding';
import { jdbindingBPage } from '../pages/jd/jdbindingB';
import { jdbindingCPage } from '../pages/jd/jdbindingC';
//京东兴业补充信息
import { jdxingyePage } from '../pages/jd/jdxingye';

//新手指南图片
import { GuideimgPage } from '../pages/home/guide/guideimg';
import { GuideimgBPage } from '../pages/home/guide/guideimgB';

//登录
import { Login } from '../pages/login/login';
import { welcomePage } from '../pages/login/welcome';
//注册
import { Register } from '../pages/login/register';
//重置密码
import { Getpassword } from '../pages/login/getpassword';
//选择角色
import { Selrole } from '../pages/login/selrole';
//banner图片点击页面
import { ClickPage } from '../pages/home/ClickPage';

//我是报价方页面：
//报价方-tab
import { TabsPage } from '../pages/tabs/tabs';
//报价方-首页
import { HomePage } from '../pages/home/home';
//报价方-报价
import { OfferPage } from '../pages/offer/offer';
//报价签章
import { SignaturePage } from '../pages/order/signature';
//报价方-修改报价
import { OrderEditPage } from '../pages/order/orderEdit';
//交易市场-交易详情
import { OrderNewDetailPage } from '../pages/order/orderNewDetail';
//报价方-工具
import { ToolsPage } from '../pages/tools/tools';
//报价方-我
import { MePage } from '../pages/me/me';
//报价方-我-完善信息

//我是出票方页面：

//出票方-tab
import { TabsPageB } from '../pages/tabs/tabsB';
//出票方-首页
import { HomePageB } from '../pages/home/homeB';
//出票方-银票询价
import { InquiryPage } from '../pages/home/inquiry/inquiry';
//出票方选择
import { DiscountYPAttributePage } from '../pages/discount/discountYPAttribute';
//出票方Ocr
import { DiscountYPOcrPage } from '../pages/discount/discountYPOcr';
//出票方-银票贴现
import { DiscountYPPage } from '../pages/discount/discountYP';
//银票贴现下一步
import { DiscountYPNextPage } from '../pages/discount/discountYPNext';
//出票方选择-商票
import { DiscountSPAttributePage } from '../pages/discount/discountSPAttribute';
//出票方-商票OCR
import { DiscountSPOcrPage } from '../pages/discount/discountSPOcr';
//出票方-商票贴现
import { DiscountSPPage } from '../pages/discount/discountSP';
//商票贴现下一步
import { DiscountSPNextPage } from '../pages/discount/discountSPNext';
//签章
import { SignatureifrmPage } from '../pages/discount/signatureifrm';
//贴现确认订单
import { DiscountOrderPage } from '../pages/discount/discountOrder';
//贴现成功
import { successPage } from '../pages/discount/success1';
//贴现搜索机构
import { DiscountOrgSearchPage } from '../pages/discount/orgSearch';
//贴现地址
//贴现地址-列表
import { DiscountPlaceListPage } from '../pages/discount/discountPlaceList';
//贴现地址-新增
import { DiscountPlaceAddPage } from '../pages/discount/discountPlaceAdd';
//票据管理
import { ManagePage } from '../pages/manage/bns/manage';
//日贴现
import { DayPage } from "../pages/manage/bns/books/day";
//周贴现
import { WeekPage } from "../pages/manage/bns/books/week";
//月贴现
import { MonthPage } from "../pages/manage/bns/books/month";
//年贴现
import { YearPage } from "../pages/manage/bns/books/year";
//记一笔票据
import { RecordPage } from "../pages/manage/bns/books/record";
//票据提醒
import { RemindPage } from "../pages/manage/bns/books/remind";
//票据提醒-详情
import { ReminddetailsPage } from "../pages/manage/bns/books/remind-details";
//票据编辑
import { ManageeditPage } from "../pages/manage/bns/books/edit";

//新票据管理
import { NewmanagePage } from '../pages/newmanage/newmanage';
//新增票据管理
import { AddmanagePage } from '../pages/newmanage/addmanage'
//待入账
import {AdmissionmanagePage} from '../pages/newmanage/admissionmanage';
//到期日提醒
import {ExpiremanagePage} from '../pages/newmanage/expiremanage';
//质押提醒
import { PledgemanagePage } from '../pages/newmanage/pledgemanage';
//预收货款核算
import { HsmanagePage } from '../pages/newmanage/hsmanage';
import { AddhsPage } from '../pages/newmanage/addhs';
//票据编辑
import { Editmanage1Page } from '../pages/newmanage/editmanage1';
import { Editmanage2Page } from '../pages/newmanage/editmanage2';
import { Editmanage3Page } from '../pages/newmanage/editmanage3';
import { Editmanage4Page } from '../pages/newmanage/editmanage4';
//发现
import { NoticePage } from '../pages/notice/notice';
//发现-票据咨询
import { NoticeDetailPage } from '../pages/notice/noticeDetail';
//出票方-工具
import { ToolsPageB } from '../pages/tools/toolsB';
//出票方-我
import { MePageB } from '../pages/me/meB';
//出票方-我-完善信息

//票据管理
import { ManagePageB } from '../pages/manage/org/manage';
//日贴现
import { DayPageB } from "../pages/manage/org/books/day";
//周贴现
import { WeekPageB } from "../pages/manage/org/books/week";
//月贴现
import { MonthPageB } from "../pages/manage/org/books/month";
//年贴现
import { YearPageB } from "../pages/manage/org/books/year";
//记一笔票据
import { RecordPageB } from "../pages/manage/org/books/record";
//票据提醒
import { RemindPageB } from "../pages/manage/org/books/remind";
//票据提醒-详情
import { ReminddetailsPageB } from "../pages/manage/org/books/remind-details";
//票据编辑
import { ManageeditPageB } from "../pages/manage/org/books/edit";

//报价方和出票方公用页面：
//押金保证金交易记录
import { AccountPage } from '../pages/account/account';
//押金管理
import { TransactionPage } from '../pages/account/transaction';
//支付示例
import { ExamplesPage } from '../pages/account/examples';
//去支付
import { PaymentPage } from '../pages/account/payment';
//支付选择银行卡
import { BankListPage } from '../pages/authentication/banklist';
//新支付选择银行卡
import { NewBankListPage } from '../pages/authentication/newbanklist';
//添加银行卡
import { BankAddPage } from '../pages/authentication/bankadd';
//贴现计算器
import { CounterPage } from '../pages/tools/counter/counter';
//查询查复
import { QueryYP } from '../pages/tools/queryYP/queryYP';
//公催查询
import { ResultPage } from '../pages/tools/gongcui/result';
//联行号查询
import { Bankline } from '../pages/tools/banks/banks';
import { BankPage } from '../pages/tools/banks/banksoput';
//shibor查询
import { ShiborPage } from '../pages/tools/shibor/shibor';
//意见反馈
import { OpinionPage } from '../pages/tools/kefu/opinion';
//积分商城
import { mallPage } from '../pages/mall/mall';
//积分收支明细
import { mallDetailedPage } from '../pages/mall/mallDetailed';
//积分攻略
import { mallStrategyPage } from '../pages/mall/mallStrategy';
//收货地址
import { mallPlacePage } from '../pages/mall/mallPlace';
//新增或修改地址
import { mallPlaceAddPage } from '../pages/mall/mallPlaceAdd';
//商品列表（全部）
import { mallListPage } from '../pages/mall/mallList';
//兑换记录
import { mallExchangeNotePage } from '../pages/mall/mallExchangeNote';
//兑换记录详情
import { mallExchangeNoteDetailPage } from '../pages/mall/mallExchangeNoteDetail';
//兑换商品详情
import { mallExchangeDetailedPage } from '../pages/mall/mallExchangeDetailed';
//确认订单
import { mallExchangeOrderPage } from '../pages/mall/mallExchangeOrder';
//兑换成功
import { mallExchangeSuccessPage } from '../pages/mall/mallExchangeSuccess';
//机构接单
import { OrderPage } from '../pages/order/order';
import { OrderListPage } from '../pages/order/orderList';
//机构报价展示
import { OrderDetailNewShowPage } from '../pages/order/orderDetailNewShow';
//企业派单
import { OrderPageB } from '../pages/order/orderB';
import { OrderListPageB } from '../pages/order/orderListB';
//企业派单详情
import { OrderDetailPageB } from '../pages/order/orderDetailB';
import { OrderBeishuPageB } from '../pages/order/orderBeishuB';
import { OrderDetailPageShowB } from '../pages/order/orderDetailShowB';
import { OrderPinjiaPageB } from '../pages/order/orderPinjiaB';
import { OrderEndPageB } from '../pages/order/orderEndB';
import { OrderShoukuanPageB } from '../pages/order/orderShoukuanB';
//机构接单详情
import { OrderDetailPage } from '../pages/order/orderDetail';
import { OrderDetailShowPage } from '../pages/order/orderDetailShow';
import { OrderDakuanPage } from '../pages/order/orderDakuan';
import { OrderDakuan1Page } from '../pages/order/orderDakuan1';
import { baojiaPage } from '../pages/order/baojia';
import { OrderBeishuPage } from '../pages/order/orderBeishu';
import { OrderPinjiaPage } from '../pages/order/orderPinjia';
//社区动态
import { Bbs } from '../pages/home/bbs';
import { AndroidBbsPage } from '../pages/home/androidbbs';
import { Iframe } from '../pages/order/iframe';
import { Iframediv } from '../pages/order/iframediv';
//票据指数
import { ChartPage } from '../pages/home/chart/chart';
//新手指南
import {  } from '';
//日历
import { Calendar } from '../pages/home/calendar';
//关于我们
import {  } from '';
//操作指南
import {  } from '';
//票管家用户注册协议
import { Infomemo } from '../pages/login/infomemo';
//开户声明协议
import { ServicePage } from '../pages/home/ServicePage';
//开户签章
import { IframesigtureePage } from '../pages/openaccount/iframesigture';
//票管家免责声明
import {  } from '';
//日历？
import { Bonus } from '../pages/me/bonus';
//我的消息
import { MeMessagePage} from '../pages/me/meMessage';
//设置
import { SettingPage} from '../pages/me/setting';
//重置密码1
import {resetPwd1Page} from '../pages/me/resetPwd/resetPwd1';
//关于我们
import {AboutUsPage} from '../pages/me/aboutUs/aboutUs';
//推荐人
import {ReferrerPage} from '../pages/me/referrer/referrer';
//推荐好友
import {ShareFriendPage} from '../pages/me/referrer/shareFriend';
//绑定账号
import {BindPage} from "../pages/me/bind";
//我的评分机构报价
import {MyGradeOrgPage} from "../pages/me/myGrade/myGradeOrg";
//我的评分企业出票
import {MyGradeBnsPage} from "../pages/me/myGrade/myGradeBns";
//我的查询查复
import {MyInquiryReplyPage} from "../pages/me/myInquiryReply/myInquiryReply";
//我的查询查复详情
import {MyInquiryReplyDetailPage} from "../pages/me/myInquiryReply/myInquiryReplyDetail";
//查询查复修改
import {MyInquiryReplyEditPage} from "../pages/me/myInquiryReply/myInquriyReplyEdit";
//银票大电票报价
import {BigDianPage} from "../pages/offer/yin/bigDian";
//开始报价页面
import {BigDian1Page} from "../pages/offer/yin/bigDian1";
//确认价格页面
import {BigDian2Page} from "../pages/offer/yin/bigDian2";
//银票小电票报价
import {SmallDianPage} from "../pages/offer/yin/smallDian";
//银票小电票报价页面
import {SmallDian1Page} from "../pages/offer/yin/smallDian1";
//银票小电票确认报价
import {SmallDian2Page} from "../pages/offer/yin/smallDian2";
//银票小纸票报价
import {SmallZhiPage} from "../pages/offer/yin/smallZhi";
//银票小纸票报价页面
import {SmallZhi1Page} from "../pages/offer/yin/smallZhi1";
//银票小纸票确认价格页面
import {SmallZhi2Page} from "../pages/offer/yin/smallZhi2_2";
//商票电票报价页面
import {DianPage} from "../pages/offer/shang/dian";
//商票纸票报价页面
import {ZhiPage} from "../pages/offer/shang/zhi";
//我的报价页面
import {MyOfferPage} from "../pages/offer/myOffer";
import {ExportPage} from "../pages/manage/export";
import {WechatChenyu} from "wechat-chenyu";
import {ScrawlPanelPage} from "../pages/discount/scrawl-panel";

//我的库存
import { InventoryPage } from "../pages/Inventory/Inventory";
//库存清单
import { DetailedlistPage } from "../pages/Inventory/Detailedlist";
//资方库存清单下单
import { InventoryOrderPage } from "../pages/Inventory/InventoryOrder";

//import {Clipboard} from "@ionic-native/clipboard";    复制到剪切板组件  暂停使用
@NgModule({
  declarations: [
    MyApp,
    MePage,
    MePageB,
    ClickPage,
    ToolsPage,
    ToolsPageB,
    NoticePage,
    NoticeDetailPage,
    OrderPage,
    OrderDakuanPage,
    OrderDakuan1Page,
    OrderBeishuPage,
    OrderPinjiaPage,
    OrderPageB,
    OrderEditPage,
    OrderNewDetailPage,
    OrderDetailPageB,
    OrderBeishuPageB,
    OrderDetailPageShowB,
    OrderPinjiaPageB,
    OrderDetailNewShowPage,
    OrderEndPageB,
    OrderShoukuanPageB,
  	OrderDetailPage,
  	OrderDetailShowPage,
  	baojiaPage,
    HomePage,
    HomePageB,
    Bbs,
    AndroidBbsPage,
  	Iframe,
	  Iframediv,
    Calendar,
    TabsPage,
    TabsPageB,
    Login,
	  welcomePage,
    Register,
    Infomemo,
    Selrole,
    Getpassword,
    Bonus,
    InquiryPage,
    ChartPage,

    //开户
    OpenaccountPage,
    OpenaccountOrcPage,
    ServicePage,
    BindingPage,
    BindingBPage,
    BindingCPage,
    openaccountDPage,
    OpenaccountBPage,
    OpenaccountCPage,
    OpenaccountEPage,
    OpenaccountFPage,
    OpenaccountSuccessPage,
    StatementBPage,
    StatementPage,
    OpenaccountListPage,
    OpenaccountListBPage,
    DisclaimerPage,
    IframesigtureePage,

    //京东开户
    JdsignaPage,
    JdinfoPage,
    jdLicensePage,
    jdLicense1Page,
    jdLicense2Page,
    jdLicense3Page,
    jdLicense3editPage,
    jdimgeditPage,
    jdlicense4Page,
    jdbanklistPage,
    jdbankbdPage,
    BranchlistPage,
    BranchlistBPage,
    JdSuccessPage,
    jdbankbdBPage,
    jdbindingPage,
    jdbindingBPage,
    jdbindingCPage,
    jdxingyePage,

    //新手指南
    GuideimgPage,
    GuideimgBPage,

    //选择银行卡
    BankListPage,
    NewBankListPage,
    BankAddPage,

    //我的红包
    MyredgadPage,
    VipaggregatePage,
    VipupgradePage,
    RookieredPage,
    RedListPage,

    //工具
    BankPage,
    QueryYP,
    CounterPage,
    ResultPage,
    Bankline,
    ShiborPage,
    OpinionPage,


    //报价

    OfferPage,
    BigDianPage,
    BigDian1Page,
    BigDian2Page,
    SmallDianPage,
    SmallDian1Page,
    SmallDian2Page,
    SmallZhiPage,
    SmallZhi1Page,
    SmallZhi2Page,
    DianPage,
    ZhiPage,
    MyOfferPage,
    SignaturePage,


    //票据管理
    ManagePage,
    DayPage,
    WeekPage,
    MonthPage,
    YearPage,
    RecordPage,
    RemindPage,
    ReminddetailsPage,
    ManageeditPage,
    ManagePageB,
    DayPageB,
    WeekPageB,
    MonthPageB,
    YearPageB,
    RecordPageB,
    RemindPageB,
    ReminddetailsPageB,
    ManageeditPageB,
    OrderListPageB,
    OrderListPage,
    MeMessagePage,
    ExportPage,//导出票据
    NewmanagePage,
    AddmanagePage,
    AdmissionmanagePage,
    ExpiremanagePage,
    PledgemanagePage,
    HsmanagePage,
    Editmanage1Page,
    Editmanage2Page,
    Editmanage3Page,
    Editmanage4Page,
    AddhsPage,

    //贴现
    DiscountYPPage,
    DiscountSPPage,
    DiscountYPAttributePage,
    DiscountSPAttributePage,
    DiscountYPOcrPage,
    DiscountSPOcrPage,
    DiscountYPNextPage,
    DiscountSPNextPage,
    DiscountOrderPage,
    successPage,
    DiscountOrgSearchPage,
    DiscountPlaceListPage,
    DiscountPlaceAddPage,
    ScrawlPanelPage,
    SignatureifrmPage,

    //积分商城
    mallPage,
    mallDetailedPage,
    mallStrategyPage,
    mallPlacePage,
    mallPlaceAddPage,
    mallListPage,
    mallExchangeNotePage,
    mallExchangeNoteDetailPage,
    mallExchangeDetailedPage,
    mallExchangeOrderPage,
    mallExchangeSuccessPage,

    //保证金押金
    TransactionPage,
    AccountPage,
    PaymentPage,
    ExamplesPage,

    SettingPage,
    resetPwd1Page,
    AboutUsPage,
    ReferrerPage,
    ShareFriendPage,
    BindPage,
    MyGradeOrgPage,
    MyGradeBnsPage,
    MyInquiryReplyPage,
    MyInquiryReplyDetailPage,
    MyInquiryReplyEditPage,
    BindMobilePage,

    //库存清单
    InventoryPage,
    DetailedlistPage,
    InventoryOrderPage
  ],
  imports: [
    CityPickerModule,
    BrowserModule,
    HttpModule,
    IonicStorageModule.forRoot(),
    IonicModule.forRoot(MyApp, {
      backButtonText: '',
      backButtonIcon: '',
      modalEnter: 'modal-slide-in',
      modalLeave: 'modal-slide-out',
      tabsPlacement: 'bottom',
      pageTransition: 'ios',
      tabsHideOnSubPages:"true",
      statusbarPadding: "true",
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    MePage,
    MePageB,
    ClickPage,
    ToolsPage,
    ToolsPageB,
    NoticePage,
    NoticeDetailPage,
    //报价
    OfferPage,
    BigDianPage,
    BigDian1Page,
    BigDian2Page,
    SmallDianPage,
    SmallDian1Page,
    SmallDian2Page,
    SmallZhiPage,
    SmallZhi1Page,
    SmallZhi2Page,
    DianPage,
    ZhiPage,
    MyOfferPage,
    SignaturePage,

    OrderPage,
	  OrderListPageB,
	  OrderListPage,
	  OrderDakuanPage,
	  OrderDakuan1Page,
	  OrderBeishuPage,
	  OrderPinjiaPage,
	  OrderPageB,
    OrderEditPage,
  	OrderDetailPageB,
	  OrderBeishuPageB,
	  OrderPinjiaPageB,
    OrderDetailNewShowPage,
	  OrderEndPageB,
	  OrderShoukuanPageB,
	  OrderDetailPageShowB,
    OrderNewDetailPage,
	  OrderDetailPage,
  	OrderDetailShowPage,
  	baojiaPage,
    HomePage,
    HomePageB,
    Bbs,
    AndroidBbsPage,
	  Iframe,
	  Iframediv,
    Calendar,
    TabsPage,
    TabsPageB,
    Login,
	  welcomePage,
    Register,
    Infomemo,
    Selrole,
    Getpassword,
    Bonus,
    InquiryPage,
    ChartPage,

    //开户
    OpenaccountPage,
    OpenaccountOrcPage,
    ServicePage,
    BindingPage,
    BindingBPage,
    BindingCPage,
    OpenaccountBPage,
    OpenaccountCPage,
    openaccountDPage,
    OpenaccountEPage,
    OpenaccountFPage,
    OpenaccountSuccessPage,
    StatementBPage,
    StatementPage,
    OpenaccountListPage,
    OpenaccountListBPage,
    DisclaimerPage,
    IframesigtureePage,

    //京东开户
    JdsignaPage,
    JdinfoPage,
    jdLicensePage,
    jdLicense1Page,
    jdLicense2Page,
    jdLicense3Page,
    jdLicense3editPage,
    jdimgeditPage,
    jdlicense4Page,
    jdbanklistPage,
    jdbankbdPage,
    BranchlistPage,
    BranchlistBPage,
    JdSuccessPage,
    jdbankbdBPage,
    jdbindingPage,
    jdbindingBPage,
    jdbindingCPage,
    jdxingyePage,

    //新手指南
    GuideimgPage,
    GuideimgBPage,

    //选择银行卡
    BankListPage,
    NewBankListPage,
    BankAddPage,

    //我的红包
    MyredgadPage,
    VipaggregatePage,
    VipupgradePage,
    RookieredPage,
    RedListPage,

    //工具
    BankPage,
    QueryYP,
    CounterPage,
    ResultPage,
    Bankline,
    ShiborPage,
    OpinionPage,


    //票据管理
    ManagePage,
    DayPage,
    WeekPage,
    MonthPage,
    YearPage,
    RecordPage,
    RemindPage,
    ReminddetailsPage,
    ManageeditPage,
    ManagePageB,
    DayPageB,
    WeekPageB,
    MonthPageB,
    YearPageB,
    RecordPageB,
    RemindPageB,
    ReminddetailsPageB,
    ManageeditPageB,
    AddhsPage,

    MeMessagePage,
    ExportPage,
    NewmanagePage,
    AddmanagePage,
    AdmissionmanagePage,
    ExpiremanagePage,
    PledgemanagePage,
    HsmanagePage,
    Editmanage1Page,
    Editmanage2Page,
    Editmanage3Page,
    Editmanage4Page,

    //贴现
    DiscountYPPage,
    DiscountSPPage,
    DiscountYPAttributePage,
    DiscountSPAttributePage,
    DiscountYPOcrPage,
    DiscountSPOcrPage,
    DiscountYPNextPage,
    DiscountSPNextPage,
    DiscountOrderPage,
    successPage,
    DiscountOrgSearchPage,
    DiscountPlaceListPage,
    DiscountPlaceAddPage,
    SignatureifrmPage,

    //积分商城
    mallPage,
    mallDetailedPage,
    mallStrategyPage,
    mallPlacePage,
    mallPlaceAddPage,
    mallListPage,
    mallExchangeNotePage,
    mallExchangeNoteDetailPage,
    mallExchangeDetailedPage,
    mallExchangeOrderPage,
    mallExchangeSuccessPage,
    ScrawlPanelPage,

    //保证金押金
    TransactionPage,
    AccountPage,
    PaymentPage,
    ExamplesPage,

    SettingPage,
    resetPwd1Page,
    AboutUsPage,
    ReferrerPage,
    ShareFriendPage,
    BindPage,
    MyGradeOrgPage,
    MyGradeBnsPage,
    MyInquiryReplyPage,
    MyInquiryReplyDetailPage,
    MyInquiryReplyEditPage,
    BindMobilePage,

    //库存清单
    InventoryPage,
    DetailedlistPage,
    InventoryOrderPage
  ],
  providers: [
    //Clipboard,
    WechatChenyu,
    QQSDK,
    JPush,
    StatusBar,
    SplashScreen,
    apiService,
    Camera,
    PhotoViewer,
    InAppBrowser,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
