import { Component,ViewChild,ElementRef } from '@angular/core';
import { ActionSheetController,Platform } from 'ionic-angular';
import { AlertController,App } from 'ionic-angular';
import { NavController,ModalController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { apiService } from "../../api.service";
import { Camera } from '@ionic-native/camera';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { ClickPage } from './ClickPage';
import { ToolsPage } from '../tools/tools';
import { Selrole } from '../login/selrole';
import { Bbs } from './bbs';
import { AndroidBbsPage } from './androidbbs';
import { JPush } from 'ionic3-jpush';
import { InAppBrowser } from '@ionic-native/in-app-browser';
import { Calendar } from './calendar';
import { ChartPage } from './chart/chart';
import { InquiryPage } from './inquiry/inquiry';
import { ManagePage } from '../manage/bns/manage';
import { DiscountYPAttributePage } from '../discount/discountYPAttribute';
import { DiscountSPAttributePage } from '../discount/discountSPAttribute';
import { Getpassword } from '../login/getpassword';
import { Infomemo } from '../login/infomemo';
import { CounterPage} from "../tools/counter/counter";
import {QueryYP} from "../tools/queryYP/queryYP"
import { ResultPage } from "../tools/gongcui/result";
import { mallPage } from '../mall/mall';
import { Login } from '../login/login';
import { GuideimgBPage } from './guide/guideimgB';
import { RookieredPage } from "../account/Rookiered";
//库存清单
import { InventoryPage } from "../Inventory/Inventory";
import ECharts from 'echarts';
import {NewmanagePage} from "../newmanage/newmanage";

@Component({
  selector: 'page-homeB',
  templateUrl: 'homeB.html'
})
export class HomePageB {

  @ViewChild('container') container: ElementRef;
  chart: any;

  public appVersion = '0.0.0';
  public updateFlag= false ;
  public btnText = '重启进入新版本';
  public appDes = '';
  public appUrl = '';
  public backDropShow = false;
  public imgList = [];
  public priceType = []; //值
  public guogu = '0.00';  //大票纸票国股
  public chengshang = '0.00';  //大票纸票城商
  public nongshang = '0.00'; //大票纸票农商
  public guoguB = '0.00';  //小票纸票国股
  public chengshangB = '0.00';  //小票纸票城商
  public nongshangB = '0.00'; //小票纸票农商

  public memberId='';
  public active:any={};
  public maskDiv=false; //弹出层
  public Authentication=false; //认证
  public Agreement=false; //贴现协议
  public getnotice:any=[];
  public Notices=false; //公告
  public Newred:boolean=false;//红包判断
  public mobile='';
  public isMask:boolean;
  public Guide:boolean;
  public guideDate:any;
  public guideDateB:any;
  public roleLeft=0;
  public roleLeftB=0;
  public roleTop=0;
  public roleTopB=0;
  public st= 's4';

  constructor(
    private app:App,
    public platform: Platform,
    public iab: InAppBrowser,
    public pv:PhotoViewer,
    public camera: Camera,
    public storage: Storage,
    public jPush: JPush,
    public actionSheetCtrl: ActionSheetController,
    public navCtrl: NavController,
    public alertCtrl: AlertController,
    public apiSev: apiService,
    public modalCtrl:ModalController
  ) {
    this.navCtrl = navCtrl;
  }


  ionViewDidEnter(){
    this.imgGuide();
    //JPush第三方插件
    if(this.platform.is('ios')){
      this.jPush.setApplicationIconBadgeNumber(0);
    }
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.mobile=data.mobile;
      this.surplus();
      this.getNotice();
      this.NewMessage();
    });
    this.getAppVersion();
    this.getAllImgAndPrice();
    this.createCharts4();
  }

  //根据DIV指引定位
  imgGuide(){
    this.roleTop = document.getElementById("guideTs").offsetTop;
    this.roleLeft = document.getElementById("guideTs").offsetLeft;
    this.guideDate = {
      "top" : (this.roleTop-130)+'px',
      "left" : (this.roleLeft-120)+'px'
    };
    this.roleTopB = document.getElementById("guideTsB").offsetTop;
    this.roleLeftB = document.getElementById("guideTsB").offsetLeft;
    this.guideDateB = {
      "top" : (this.roleTopB)+'px',
      "left" : (this.roleLeftB+70)+'px'
    }
  }


//banner图点击效果
  itemSelected(url) {
    if (url == 'calendar.html') {
      this.navCtrl.push(Calendar);
    } else if(url!=null&&url!=""){
      /*        let profileModal = this.modalCtrl.create(ClickPage);
              profileModal.present();*/
      this.navCtrl.push(ClickPage, {
        Click_Page: url,
      });
    }
  }

  getAllImgAndPrice () {
    this.Notices=false;
    let data:any={
    };
    this.apiSev.api("post", "app/getAllImgAndPrice", (res) => {
      if(res.state == 'success'){
        this.imgList = res.banners;
        let priceType = res.historypriceList;
        for (let i in priceType) {
          let hp = priceType[i];
          if(hp.type2 == 2){
            let type1 = hp.type1;
            let type6 = hp.type6;
            let price = hp.price==null?"--" : (hp.price/1).toFixed(2);
            if (type6 == 2 && type1 == 1) {  //大票电票国股
              this.guogu = price;
            }else if (type6 == 2 && type1 == 2) {  //大票电票城商
              this.chengshang = price;
            }else if (type6 == 2 && type1 == 4) {  //大票电票农商
              this.nongshang = price
            }
          }else{
            let type1 = hp.type1;
            let type6 = hp.type6;
            let type2 = hp.type2;
            let price = hp.price==null?"--" : (hp.price/1).toFixed(2);
            if (type2 == 4 && type6 == 2 && type1 == 1) {        //小票纸票国股
              this.guoguB = price
            }else if (type2 == 4 && type6 == 2 && type1 == 2) {  //小票纸票城商
              this.chengshangB = price
            }else if (type2 == 4 && type6 == 2 && type1 == 4) {  //小票纸票农商
              this.nongshangB = price
            }
          }
        }
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  sure () {
    if(this.updateFlag)
    	this.iab.create(this.appUrl, '_system');
    else
		this.platform.exitApp();
  }

  getAppVersion () {
    this.apiSev.api("get", "app/version/", (res) => {
      if(res[0].response=='success'){
          if(this.platform.is('ios')){

          if (res[0].msg.iosflag=="1") {
        this.updateFlag=true ;
        this.btnText='确定更新';
          }
              this.appUrl = 'https://itunes.apple.com/cn/app/id1001869394';
              this.appVersion = res[0].msg.iosversion;
          this.appDes = res[0].msg.iosdesc;
              if(this.apiSev.getVersionIos() < res[0].msg.iosversion){
            let elements = document.querySelectorAll(".tabbar");
            if(elements != null) {
                Object.keys(elements).map((key) => {
                    elements[key].style.display ='none';
                });
          }
                  this.backDropShow = true;
              }else{
                  this.backDropShow = false;
              }
          }else if(this.platform.is('android')){

          if (res[0].msg.androidflag=="1") {
        this.updateFlag=true ;
        this.btnText='确定更新';
          }
              this.appUrl = 'http://static.utiexian.com/andriod/piaojuguanjia.apk';
              this.appVersion = res[0].msg.androidversion;
          this.appDes = res[0].msg.androiddesc;
              if(this.apiSev.getVersion() < res[0].msg.androidversion){
            let elements = document.querySelectorAll(".tabbar");
            if(elements != null) {
                Object.keys(elements).map((key) => {
                    elements[key].style.display ='none';
                });
          }
                  this.backDropShow = true;
              }else{
                  this.backDropShow = false;
              }
          }
      }

      this.storage.get('lgtoken').then((lgtoken)=>{
        if (lgtoken === null || lgtoken === undefined || lgtoken === ''){
          let profileModal = this.modalCtrl.create(Login);
          profileModal.present();
        }
        else{
          this.storage.get('userInfo').then((data)=>{
            this.memberId=data.id;
          })
        }
      });

    }, (error) => {
    },3000,{})
  }

  ngOnInit() {
  }

  ngOnDestroy(){
  }

  juese(){
    let profileModal = this.modalCtrl.create(Selrole);
    profileModal.present();
  }

  bbs(){
    if(this.platform.is('ios')){
      let profileModal = this.modalCtrl.create(Bbs,{
        MEMBERID:this.memberId,
        MOBILE:this.mobile
      });
      profileModal.present();
    }else if(this.platform.is('android')){
      let profileModal = this.modalCtrl.create(AndroidBbsPage, {
          MEMBERID:this.memberId,
          MOBILE:this.mobile
        });
      profileModal.present();
    }
  }

   viewPhoto (img) {
     this.pv.show(img);
   }

   //工具
   tools () {
     this.navCtrl.push(ToolsPage);
   }
  //银票贴现
  discountYPPage () {
    this.navCtrl.push(DiscountYPAttributePage);
  }
  //商票贴现
  discountSPPage () {
    this.navCtrl.push(DiscountSPAttributePage);
  }
  //新手指南
  guide(){
    let profileModal = this.modalCtrl.create(GuideimgBPage);
    profileModal.present();
  }
   //银票询价
   inquiry(){
    this.navCtrl.push(InquiryPage);
   }
   //票据管理
  manage() {
    this.navCtrl.push(NewmanagePage);
  }
  //贴现计算器
  counTer() {
    this.navCtrl.push(CounterPage,{
      ORG_TYPE: 0,//机构
    });
  }
  //查询查复
  queryYp(){
    this.navCtrl.push(QueryYP,{
      ORG_TYPE: 0,//机构
    });
  }
  //公催查询
  reSult()  {
    this.navCtrl.push(ResultPage);
  }
  //积分商城
  mallPage(){
    this.navCtrl.push(mallPage,{FLAG:3});
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.Newred = false;
      this.Guide = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
  }
  //弹出下拉框时，取消scroll
  hiddenscroll(){
    //获取当前组件的ID
    let aboutContent = document.querySelector("ion-content");
    //获取当前组件下的scroll-content元素
    let scroll:any = aboutContent.querySelector(".scroll-content");
    if(this.maskDiv){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }
  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
    this.Agreement = true;
    this.Authentication = false;

    this.storage.set('Agreement', 'agree');//全局缓存，判断是否同意贴现协议
  }
  //我-认证信息
  ToAuthentication(){

  }
  //票据管家用户协议
  statement(){
    this.navCtrl.push(Getpassword);
  }
  //票管家平台免责声明
  agreement(){
    this.navCtrl.push(Infomemo);
  }
  //XXXXXX委托书
  entrust(){
    this.navCtrl.push(Infomemo);
  }
  //XXXXXX三方协议
  agreements(){
    this.navCtrl.push(Infomemo);
  }

  //tab红点
  surplus(){
    if(this.memberId!=null && this.memberId!=''){
      let data: any = {
        memberId: this.memberId,
      };
      this.apiSev.api("newpost", "order/get/count", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data.count >= 1) {
            this.apiSev.SingularB = data.data.count;
          } else if (data.data.count == 0) {
            this.apiSev.SingularB = '';
          }
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
    }
  }

  //是否有新消息
  NewMessage(){
    let data ={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "systeminfo/get/unread", (res) => {
      let num = res.data.data.num;
      if(num>0){
        this.apiSev.systeminfo = num;
      }else{
        this.apiSev.systeminfo = '';
      }
    },(error) => {

    }, 500,data);
  }

  //公告调用
  getNotice(){
    if(this.memberId!=null&&this.memberId!='') {
      let data: any = {
        memberId: this.memberId,
        announcementRole: 'BNS'
      };
      if (this.platform.is('ios')) {
        data.version = this.apiSev.getVersionIos();
        data.platform = 'IOS';
      } else if (this.platform.is('android')) {
        data.version = this.apiSev.getVersion();
        data.platform = 'ANDROID';
      }
      this.apiSev.api("newpost", "announcement/list", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data != "" && data.data != null) {
            this.Notices = true;
            this.maskDiv = false;
            this.Guide=false;
            this.isMask=false;
            this.Newred=false;
            let elements = document.querySelectorAll(".tabbar");
            if (elements != null) {
              Object.keys(elements).map((key) => {
                elements[key].style.display = 'none';
              });
            }
            if (data.data.length > 0) {
              if (data.data.length == 10) {//说明可能还有数据

              }
              for (let i = 0; i < data.data.length; i++) {
                this.getnotice.push(data.data[i]);
              }
            }
          } else {
            this.Notices = false;
            this.Myredgad();
          }
        } else {
          this.apiSev.itip(data.msg);
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
    }
  }
  //读公告
  ReadNotice(id){
    let data:any={
      memberId:this.memberId,
      announcementId:id
    };
    this.apiSev.api("newpost", "announcementlog/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let elements = document.querySelectorAll(".tabbar");
        if(elements != null) {
          Object.keys(elements).map((key) => {
            elements[key].style.display ='flex';
          });
          this.Notices = false;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
  //退出APP
  ReadNoticeS() {
    this.platform.exitApp();
  }

  //红包领取
  Myredgad(){
    let data:any={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "coupon/register/init", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.Newred=true;
        this.isMask=true;
        this.Guide=false;
      }else {
        this.Guide=false;
        this.imgGuide();
        this.storage.get('sysguiedB').then((sysguiedB) => {
          if ((sysguiedB < 3 || sysguiedB == '' || sysguiedB == 'null' || sysguiedB == undefined) && this.roleLeft > 0 && this.roleTopB > 0 && this.roleLeftB > 0 && this.roleTop > 0) {
            if (sysguiedB == '' || sysguiedB == 'null' || sysguiedB == undefined) {
              this.storage.set('sysguiedB', 1);
            } else {
              this.storage.set('sysguiedB', sysguiedB + 1);
            }
            console.log(sysguiedB);
            this.Guide = true;
            this.isMask = true;
          } else {
            this.Guide = false;
            this.isMask = false;
            this.Newred = false;
          }
        });
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //红包跳转
  Redinput(){
    this.navCtrl.push(RookieredPage,{
      ORG_TYPE:0  //企业 出票方
    });
    this.Newred=false;
    this.isMask=false;
    this.Guide=false;
  }

  createCharts() {
    let data:any={
      months:1
    };
    this.apiSev.api("newpost", "/historyprice/chart", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let Xaxis = data.data.dates;
        let price = data.data.price;
        let price_1 = data.data.price_1;
        let price_2 = data.data.price_2;

        //折线图调试
        let ctx = this.container.nativeElement;
        this.chart = ECharts.init(ctx);
        this.chart.setOption({
          title: {
            left: 'center',
            y: '190',
            text: '国股年利率',
            textStyle: {
              //文字颜色
              color: '#2d2d2d',
              //字体风格,'normal','italic','oblique'
              fontStyle: 'normal',
              //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
              fontWeight: 'bold',
              //字体系列
              fontFamily: 'sans-serif',
              //字体大小
              fontSize: 12
            }
          },
          grid:{
            x:45,
            y:10,
            x2:10,
            y2:85,
            borderWidth:1
          },
          tooltip: {},
          legend: {
            y: '210',
            data: ['2016年', '2017年', '2018年']
          },
          xAxis: {
            data:Xaxis,
          },
          yAxis: {
            minInterval : 0.5,
            boundaryGap : [ 0, 0.1 ],
            axisLabel: {
              formatter: '{value} %'
            },
            axisTick: {//去掉刻度
              show: false
            },
            min:2,
          },
          series: [{
            color:'#7790fe',
            connectNulls: true,
            name: '2016年',
            type: 'line',
            data:price_2,
            itemStyle: {
              normal: {
                lineStyle: {
                  width: 0.5// 0.1的线条是非常细的了
                }
              }
            }
          },
            {
              color:'#3ad45a',
              connectNulls: true,
              name: '2017年',
              type: 'line',
              data:price_1,
              itemStyle: {
                normal: {
                  lineStyle: {
                    width: 0.5// 0.1的线条是非常细的了
                  }
                }
              }
            },
            {
              color:'#d43c33',
              left: 'right',
              connectNulls: true,
              name: '2018年',
              type: 'line',
              data:price
            }]
        });
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  createCharts2() {
    let data:any={
      months:3
    };
    this.apiSev.api("newpost", "/historyprice/chart", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let Xaxis = data.data.dates;
        let price = data.data.price;
        let price_1 = data.data.price_1;
        let price_2 = data.data.price_2;

        //折线图调试
        let ctx = this.container.nativeElement;
        this.chart = ECharts.init(ctx);
        this.chart.setOption({
          title: {
            left: 'center',
            y: '190',
            text: '国股年利率',
            textStyle: {
              //文字颜色
              color: '#2d2d2d',
              //字体风格,'normal','italic','oblique'
              fontStyle: 'normal',
              //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
              fontWeight: 'bold',
              //字体系列
              fontFamily: 'sans-serif',
              //字体大小
              fontSize: 12
            }
          },
          grid:{
            x:45,
            y:10,
            x2:10,
            y2:85,
            borderWidth:1
          },
          tooltip: {},
          legend: {
            y: '210',
            data: ['2016年', '2017年', '2018年']
          },
          xAxis: {
            data:Xaxis,
          },
          yAxis: {
            minInterval : 0.5,
            boundaryGap : [ 0, 0.1 ],
            axisLabel: {
              formatter: '{value} %'
            },
            axisTick: {//去掉刻度
              show: false
            },
            min:2,
          },
          series: [{
            color:'#7790fe',
            connectNulls: true,
            name: '2016年',
            type: 'line',
            data:price_2,
            itemStyle: {
              normal: {
                lineStyle: {
                  width: 0.5// 0.1的线条是非常细的了
                }
              }
            }
          },
            {
              color:'#3ad45a',
              connectNulls: true,
              name: '2017年',
              type: 'line',
              data:price_1,
              itemStyle: {
                normal: {
                  lineStyle: {
                    width: 0.5// 0.1的线条是非常细的了
                  }
                }
              }
            },
            {
              color:'#d43c33',
              left: 'right',
              connectNulls: true,
              name: '2018年',
              type: 'line',
              data:price
            }]
        });
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  createCharts3() {
    let data:any={
      months:6
    };
    this.apiSev.api("newpost", "/historyprice/chart", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let Xaxis = data.data.dates;
        let price = data.data.price;
        let price_1 = data.data.price_1;
        let price_2 = data.data.price_2;

        //折线图调试
        let ctx = this.container.nativeElement;
        this.chart = ECharts.init(ctx);
        this.chart.setOption({
          title: {
            left: 'center',
            y: '190',
            text: '国股年利率',
            textStyle: {
              //文字颜色
              color: '#2d2d2d',
              //字体风格,'normal','italic','oblique'
              fontStyle: 'normal',
              //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
              fontWeight: 'bold',
              //字体系列
              fontFamily: 'sans-serif',
              //字体大小
              fontSize: 12
            }
          },
          grid:{
            x:45,
            y:10,
            x2:10,
            y2:85,
            borderWidth:1
          },
          tooltip: {},
          legend: {
            y: '210',
            data: ['2016年', '2017年', '2018年']
          },
          xAxis: {
            data:Xaxis,
          },
          yAxis: {
            minInterval : 0.5,
            boundaryGap : [ 0, 0.1 ],
            axisLabel: {
              formatter: '{value} %'
            },
            axisTick: {//去掉刻度
              show: false
            },
            min:2,
          },
          series: [{
            color:'#7790fe',
            connectNulls: true,
            name: '2016年',
            type: 'line',
            data:price_2,
            itemStyle: {
              normal: {
                lineStyle: {
                  width: 0.5// 0.1的线条是非常细的了
                }
              }
            }
          },
            {
              color:'#3ad45a',
              connectNulls: true,
              name: '2017年',
              type: 'line',
              data:price_1,
              itemStyle: {
                normal: {
                  lineStyle: {
                    width: 0.5// 0.1的线条是非常细的了
                  }
                }
              }
            },
            {
              color:'#d43c33',
              left: 'right',
              connectNulls: true,
              name: '2018年',
              type: 'line',
              data:price
            }]
        });
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  createCharts4() {
    let data:any={
      months:12
    };
    this.apiSev.api("newpost", "/historyprice/chart", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let Xaxis = data.data.dates;
        let price = data.data.price;
        let price_1 = data.data.price_1;
        let price_2 = data.data.price_2;

        //折线图调试
        let ctx = this.container.nativeElement;
        this.chart = ECharts.init(ctx);
        this.chart.setOption({
          title: {
            left: 'center',
            y: '190',
            text: '国股年利率',
            textStyle: {
              //文字颜色
              color: '#2d2d2d',
              //字体风格,'normal','italic','oblique'
              fontStyle: 'normal',
              //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
              fontWeight: 'bold',
              //字体系列
              fontFamily: 'sans-serif',
              //字体大小
              fontSize: 12
            }
          },
          grid:{
            x:45,
            y:10,
            x2:10,
            y2:85,
            borderWidth:1
          },
          tooltip: {},
          legend: {
            y: '210',
            data: ['2016年', '2017年', '2018年']
          },
          xAxis: {
            data:Xaxis,
          },
          yAxis: {
            minInterval : 0.5,
            boundaryGap : [ 0, 0.1 ],
            axisLabel: {
              formatter: '{value} %'
            },
            axisTick: {//去掉刻度
              show: false
            },
            min:2,
          },
          series: [{
            color:'#7790fe',
            connectNulls: true,
            name: '2016年',
            type: 'line',
            data:price_2,
            itemStyle: {
              normal: {
                lineStyle: {
                  width: 0.5// 0.1的线条是非常细的了
                }
              }
            }
          },
            {
              color:'#3ad45a',
              connectNulls: true,
              name: '2017年',
              type: 'line',
              data:price_1,
              itemStyle: {
                normal: {
                  lineStyle: {
                    width: 0.5// 0.1的线条是非常细的了
                  }
                }
              }
            },
            {
              color:'#d43c33',
              left: 'right',
              connectNulls: true,
              name: '2018年',
              type: 'line',
              data:price
            }]
        });
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //头部右侧图标按钮
  Changed(){
    if (this.st=='s1') {
      this.createCharts();
    }
    if(this.st=='s2') {
      this.createCharts2();
    }
    if(this.st=='s3') {
      this.createCharts3();
    }
    if(this.st=='s4') {
      this.createCharts4();
    }
  }

  //去往库存清单
  Inventorys(){
    this.navCtrl.push(InventoryPage);
  }

}
