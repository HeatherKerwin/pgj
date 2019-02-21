import { Component,ViewChild,ElementRef} from '@angular/core';
import { ActionSheetController,Platform } from 'ionic-angular';
import { AlertController, App } from 'ionic-angular';
import { NavController,ModalController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { apiService } from "../../api.service";
import { Camera } from '@ionic-native/camera';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { ClickPage } from './ClickPage';
import { ToolsPage } from '../tools/tools';
import { Selrole } from '../login/selrole';
import { InAppBrowser } from '@ionic-native/in-app-browser';
import { Calendar } from './calendar';
import { NoticePage } from '../notice/notice';
import { ChartPage } from './chart/chart';
import { NewmanagePage } from '../newmanage/newmanage';
import { CounterPage} from "../tools/counter/counter";
import { OfferPage} from "../offer/offer";
import { mallPage } from '../mall/mall';
import { Login } from '../login/login';
import { Bbs } from './bbs';
import { AndroidBbsPage } from './androidbbs';
import { JPush } from 'ionic3-jpush';
import { RookieredPage } from "../account/Rookiered";
import ECharts from 'echarts';
import {OrderShoukuanPageB} from "../order/orderShoukuanB";
import { GuideimgPage } from './guide/guideimg';
import { TabsPage } from '../tabs/tabs';
import { InventoryOrderPage } from '../Inventory/InventoryOrder';
declare var window;
declare let Swiper: any;

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})


export class HomePage {

  @ViewChild('container') container: ElementRef;
  chart: any;

  alias: string = '';
  msgList:Array<any>=[];
  public orgId:any;

  public appVersion = '0.0.0';
  public updateFlag = false;
  public btnText = '重启进入新版本';
  public appDes = '';
  public appUrl = '';
  public backDropShow = false;
  public imgList = [];
  public memberid = '';
  public moneyOfWeek = '';  //上周成交金额
  public moneyOfAll = ''; //成交总金额
  public index_app_zx = []; //初始化[机构端首页资讯]
  public Notices=false; //公告
  public getnotice:any=[];
  public Newred:boolean=false;
  public mobile='';
  public isMask:boolean;
  public Guide:boolean;
  public guideDateC:any;
  public roleTop=0;
  public roleLeft=0;
  public st= 's4';
  public Lists3:any = [];



  constructor(private app: App,
              public platform: Platform,
              public iab: InAppBrowser,
              public pv: PhotoViewer,
              public camera: Camera,
              public storage: Storage,
              public jPush: JPush,
              public actionSheetCtrl: ActionSheetController,
              public navCtrl: NavController,
              public alertCtrl: AlertController,
              public apiSev: apiService,
              public modalCtrl: ModalController) {
    this.navCtrl = navCtrl;
  }

  bbs(){
    if(this.platform.is('ios')){
      let profileModal = this.modalCtrl.create(Bbs,{
        MEMBERID:this.memberid,
        MOBILE:this.mobile
      });
      profileModal.present();
    }else if(this.platform.is('android')){
      let profileModal = this.modalCtrl.create(AndroidBbsPage,{
        MEMBERID:this.memberid,
        MOBILE:this.mobile
      });
      profileModal.present();
    }
  }


  ionViewDidEnter() {
    //JPush第三方插件
    this.imgGuide();
    if(this.platform.is('ios')){
      this.jPush.setApplicationIconBadgeNumber(0);
    }
    this.getAppVersion();
    // this.getAllImgAndPrice();
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
    this.storage.get('userInfo').then((data) => {
      this.memberid = data.id;
      this.getNotice();
      this.surplus();
    });
    this.createCharts4();
    this.CompletedOrder();
  }

  ionViewDidLoad() {
    var mySwiper = new Swiper('.swiper-Newcontainer',{
      direction: 'vertical',
      loop: true,
      pagination: {
        el: '.swiper-pagination',
      },
      touchRatio : 0,
      allowTouchMove:false,
      centeredSlides : false,
      autoplay:true,//等同于以下设置
      slidesPerView : 3,
      slidesPerGroup : 1,
      observer:true,
      observeParents:true,
    })
  }

  sure () {
    if(this.updateFlag)
    	this.iab.create(this.appUrl, '_system');
    else
		this.platform.exitApp();
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

/*  getAllImgAndPrice () {
    this.apiSev.api("get", "app/getAllImgAndPrice", (res) => {
	   this.imgList = res.bannerImgList;
    }, (error) => {
    },3000,{})
  }*/

  getAppVersion () {

    this.apiSev.api("get", "app/version/", (res) => {
console.log(res[0]);
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
			this.storage.get('userInfo').then((data) => {
			  this.memberid = data.id;
        this.mobile=data.mobile;
        this.loadMoney();
        this.NewMessage();
			});
		}
	});


    }, (error) => {
    },3000,{})
  }

  loadMoney(){
    let data:any={
      memberId:this.memberid,
    };

    this.apiSev.api("post", "app/discountrecord/getMoneyByIdAndTime", (res) => {
      if (res.state == 'success') {
        this.imgList = res.banners_org;   //图片集合
        this.moneyOfAll = res.moneyOfAll;
        this.moneyOfWeek = res.moneyOfWeek;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  ngOnInit() {
  }

  ngOnDestroy(){
  }

  juese(){
    let profileModal = this.modalCtrl.create(Selrole);
    profileModal.present();
  }

  viewPhoto (img) {
    this.pv.show(img);
  }

  tools () {
    this.navCtrl.push(ToolsPage);
  }

  //接单大厅
  orderPush(){
    this.navCtrl.push(TabsPage,{
      INDEX:2
    });
  }

   //票据咨询
    noticePage () {
    this.navCtrl.push(NoticePage);
  }
   //票据指数
  chartPage () {
    this.navCtrl.push(ChartPage);
  }
  //票据管理
  manage(e) {
    this.navCtrl.push(NewmanagePage);
    e.preventDefault();
  }
  //贴现计算器
  counTer() {
    this.navCtrl.push(CounterPage,{
      ORG_TYPE: 1,//企业
    });
  }
  //我要报价
  offer(){
    this.navCtrl.push(OfferPage);
  }
  //查询查复
  queryYp(){
    let profileModal = this.modalCtrl.create(GuideimgPage);
    profileModal.present();
  }
  //查询查复
  mallPage(){
    this.navCtrl.push(mallPage,{FLAG:1});
  }
  Go(){
    this.navCtrl.push(OrderShoukuanPageB)
  }

  //tab红点
  surplus(){
    if(this.memberid!=null && this.memberid!='') {
      let data: any = {
        memberid: this.memberid,
      };
      this.apiSev.api("newpost", "dispatch/get/count", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data.count >= 1) {
            this.apiSev.Singular = data.data.count;
          } else if (data.data.count == 0) {
            this.apiSev.Singular = '';
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
      memberId:this.memberid
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
    this.Notices=false;
    if(this.memberid!=null&&this.memberid!=''){
      let data: any = {
        memberId: this.memberid,
        announcementRole: 'ORG'
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
            this.Guide=false;
            this.isMask=false;
            this.Newred=false;
            this.Notices = true;
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
      memberId:this.memberid,
      announcementId:id
    };
    this.apiSev.api("newpost", "announcementlog/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.Notices = false;
        let elements = document.querySelectorAll(".tabbar");
        if(elements != null) {
          Object.keys(elements).map((key) => {
            elements[key].style.display ='flex';
          });
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
      memberId:this.memberid
    };
    this.apiSev.api("newpost", "coupon/register/init", (res) => {
      let data = res.data;
        if (data.response == 'success') {
          this.Newred = true;
          this.isMask = true;
        } else {
          this.Newred = false;
          this.storage.get('sysguied').then((sysguied) => {
            if ((sysguied < 3 || sysguied == '' || sysguied == 'null' || sysguied == undefined) && this.roleTop > 0 && this.roleLeft > 0) {
              if (sysguied == '' || sysguied == 'null' || sysguied == undefined) {
                this.storage.set('sysguied', 1);
              } else {
                this.storage.set('sysguied', sysguied + 1);
              }
              console.log(sysguied);
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
      ORG_TYPE:1  //企业 出票方
    });
    this.Newred=false;
    this.isMask=false;
    this.Guide=false;
  }

  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.Newred = false;
      this.Guide=false;
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
    if(this.isMask){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
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

  //根据DIV指引定位
  imgGuide(){
    setTimeout(() => {
      this.roleTop = document.getElementById("guideTsC").offsetTop;
      this.roleLeft = document.getElementById("guideTsC").offsetLeft;
      this.guideDateC = {
        "top": (this.roleTop + 15) + 'px',
        "left": (this.roleLeft - 130) + 'px'
      };
    },500)
  }

  CompletedOrder(){
    let data:any={
      pageSize:20,
      orderflags:3
    };
    this.apiSev.api("newpost", "dispatch/page/hall", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.page.list!=''&&data.data.page.list!=null) {
          let objs = data.data.page.list;
          if (objs.length > 0) {
            for (let i = 0; i < objs.length; i++) {
              this.Lists3.push(objs[i]);
            }
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //前往库存订单下单
  Inventoryorder(){
    this.navCtrl.push(InventoryOrderPage);
  }
}
