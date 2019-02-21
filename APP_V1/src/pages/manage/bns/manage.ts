import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { Platform } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { apiService } from "../../../api.service";
import { DayPage } from "../../manage/bns/books/day";
import { WeekPage } from "../../manage/bns/books/week";
import { MonthPage } from "../../manage/bns/books/month";
import { YearPage } from "../../manage/bns/books/year";
import { RecordPage } from "../../manage/bns/books/record";
import { ManageeditPage } from "../../manage/bns/books/edit";
import { RemindPage } from "../../manage/bns/books/remind";
import { HomePageB } from "../../home/homeB";
import {ExportPage} from "../export";

@Component({
  selector: 'page-manage',
  templateUrl: 'manage.html'
})
export class ManagePage {
  //切换
  segmentsArray = ['manage1','manage2','manage3','manage4'];
  pet:any;
  tempidex:any;

  isAndroid: boolean = false;

  // 账簿
  public content:any;
  public notifications:number;
  // 年月日
  public myDate=new Date().toISOString();
  //本周时间
  public now:any;
  public nowDayOfWeek:any;
  public nowDay:any;
  public nowMonth:any;
  public nowYear:any;
  public date1:any;
  public date2:any;
  public weekTime='';

  //日周月年贴现
  public  monthdiscounted: any;
  public  monthundiscount: any;
  public  weekundiscount: any;
  public  weekdiscounted: any;
  public  dayundiscount: any;
  public  daydiscounted: any;
  public  yeardiscounted: any;
  public  yearundiscount: any;

  //已贴现、未贴现
  public value1s = [];
  public value2s = [];
  public value={
    type1: '',
    tiexiandate: '',
    allprice: '',
    tiexianlixi: '',
    tiexianjine: '',
    orderStatue: '',
    currentPrice: '',
    daoqidate: '',
  };
  //搜索
  public search={
    isTiexian:0,
    start:new Date().toISOString(),
    end: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),
    min:new Date().toISOString(),
    type1:1,
    type:1,
    limit:'1',
    limitShow:true,
    allprice:''
  }

  //显示隐藏
  public  isShow1=true;
  public  isShow2=false;
  public  isShow3=false;
  public  isShow4=false;
  public  isShow2con=false;

  //统计分析
  public Statistics:any = {
    type : 1 ,
    startDate :'' ,
    endDate : '',
    diyStartDate:'',
    diyEndDate:'',
    minDate:'',
    maxDate:''
  };
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};

  public memberId='';


  constructor(
    public platform: Platform,
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public sanitizer: DomSanitizer
  ){
    this.Statistics.startDate = new Date().setMonth(new Date().getMonth()-1);
    this.Statistics.endDate = new Date();
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      //引用wap项目appPiechart页面
      let url=this.apiSev.getWapUrl()+'app/pieChart?belong=0&type='+this.Statistics.type+'&memberId='+this.memberId;
      this.browser.url = url;
      this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
    })
    this.isAndroid = platform.is('android');
    // 本周

    //已贴现、未贴现
    this.discount(1);
    this.discount(0);

  }

  changeStart(){
    if (this.search.limit=='0'){
      this.search.end = new Date(new Date(this.search.start.replace(/-/g, "/")).getTime() + 180 * 24 * 60 * 60 * 1000).toISOString();
      this.search.min = new Date(this.search.start.replace(/-/g, "/")).toISOString();
    }else if(this.search.limit=='1') {
      this.search.end = new Date(new Date(this.search.start.replace(/-/g, "/")).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString();
      this.search.min = new Date(this.search.start.replace(/-/g, "/")).toISOString();
    };
  }

  //打开提醒
  remind(){
    this.navCtrl.push(RemindPage);
  }
  //打开票据编辑
  openEdit(tiexianId,tempidex){
    this.navCtrl.push(ManageeditPage,{
      tiexianId : tiexianId,
      tempidex: tempidex,
    });
  }

//--------------账簿开始-----------------------
  //提醒
  loaddatatixing() {
    let data:any={
      memberid:this.memberId,
      belong:'0'
    }
    this.apiSev.api("post", "zhangbu/piaojuremind", (res) => {
      this.notifications=res;
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }ssss
  //进入页面刷新
  ionViewDidEnter(){
    this.loaddatatixing();
    this.booksData();
    this.segmentShow();
    // 本周
    this.getTime();
    this.discount(1);
    this.discount(0);
    this.btnIcon();
  }

  //账簿
  booksData() {
    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "zhangbu/index/?memberid="+data.id+"&belong=0", (res) => {
        this.content = res;
        this.monthdiscounted = this.content.monthdiscounted;
        this.monthundiscount = this.content.monthundiscount;
        this.weekundiscount = this.content.weekundiscount;
        this.weekdiscounted = this.content.weekdiscounted;
        this.dayundiscount = this.content.dayundiscount;
        this.daydiscounted = this.content.daydiscounted;
        this.yeardiscounted = this.content.yeardiscounted;
        this.yearundiscount = this.content.yearundiscount;
        if(this.monthdiscounted == "null" || this.monthdiscounted == null || this.monthdiscounted==undefined  || this.monthdiscounted == ""){
          this.monthdiscounted = 0;
        }
        if(this.monthundiscount==undefined || this.monthundiscount == null || this.monthundiscount == "null" || this.monthundiscount == ""){
          this.monthundiscount = 0;
        }
        if(this.weekundiscount==undefined || this.weekundiscount == null || this.weekundiscount == "null" || this.weekundiscount == ""){
          this.weekundiscount=0;
        }
        if(this.weekdiscounted==undefined || this.weekdiscounted == null || this.weekdiscounted == "null" || this.weekdiscounted == ""){
          this.weekdiscounted=0;
        }
        if(this.dayundiscount==undefined || this.dayundiscount == null || this.dayundiscount == "null" || this.dayundiscount == ""){
          this.dayundiscount=0;
        }
        if(this.daydiscounted==undefined || this.daydiscounted == null || this.daydiscounted == "null" || this.daydiscounted == ""){
          this.daydiscounted=0;
        }
        if(this.yeardiscounted==undefined || this.yeardiscounted == null || this.yeardiscounted == "null" || this.yeardiscounted == ""){
          this.yeardiscounted=0;
        }
        if(this.yearundiscount==undefined || this.yearundiscount == null || this.yearundiscount == "null" || this.yearundiscount == ""){
          this.yearundiscount=0;
        }
      }, (error) => {},1,{});
    })
  }
  // 本周时间
  getTime(){
    this.now = new Date();//当前日期
    this.nowDayOfWeek = this.now.getDay();//今天本周的第几天
    if(this.nowDayOfWeek == 0){
      this.nowDayOfWeek = 6;
    }else{
      this.nowDayOfWeek -= 1;
    }
    this.nowDay = this.now.getDate();//当前日
    this.nowMonth = this.now.getMonth();//当前月
    this.nowYear = this.now.getYear();//当前年
    this.nowYear += (this.nowYear < 2000) ? 1900 : 0;
    this.date1 = new Date(this.nowYear,this.nowMonth,this.nowDay - this.nowDayOfWeek);
    this.date2 = new Date(this.nowYear,this.nowMonth,this.date1.getDate()+6);
    this.weekTime=((this.date1.getMonth() + 1) + "月" + this.date1.getDate() + "日~" + (this.date2.getMonth() + 1) + "月" + this.date2.getDate() + "日");
  }
  //跳转日周月年
  dayPage() {
    this.navCtrl.push(DayPage);
  }
  weekPage() {
    this.navCtrl.push(WeekPage);
  }
  monthPage() {
    this.navCtrl.push(MonthPage);
  }
  yearPage() {
    this.navCtrl.push(YearPage);
  }
  recordPage() {
    this.navCtrl.push(RecordPage);
  }
//--------------账簿结束-----------------------


//--------------已贴现未贴现搜索开始-----------------------
  //已贴现、未贴现搜索弹出层
  searchIcon(isTX) {
    this.search.isTiexian = isTX;
    this.isShow2con = true;
  }
  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isShow2con = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
  }
  //弹出下拉框时，取消scroll
  hiddenscroll(){
    //获取当前组件的ID
    let aboutContent = document.querySelector("#aboutContent");
    //获取当前组件下的scroll-content元素
    let scroll:any = aboutContent.querySelector(".scroll-content");
    if(this.isShow2con){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }
  //选择承兑期限
  searchLimit(){
    if(this.search.type1==0){
      this.search.limitShow=false;
    }else if (this.search.type1==1){
      this.search.limitShow=true;
    }
  }

  /**
   * 去票据导出页面
   * @author 朱伟定
   */
  ToExportPage(){
    this.navCtrl.push(ExportPage,{ROLE:0});
  }
  // 搜索
  searchBtn(type){
    let starttime = new Date(this.search.start.replace(/-/g, "/")).getFullYear()+"-"+new Date(this.search.start.replace(/-/g, "/")).getMonth()+"-"+new Date(this.search.start.replace(/-/g, "/")).getDate();
    let endtime = new Date(this.search.end.replace(/-/g, "/")).getFullYear()+"-"+new Date(this.search.end.replace(/-/g, "/")).getMonth()+"-"+new Date(this.search.end.replace(/-/g, "/")).getDate();

    let data:any={
      memberId:this.memberId,
      istiexian:type,
      start:starttime,
      end:endtime,
      type1:this.search.type,//1已贴现，0未贴现
      size:'5',
      belong:'0',
      type:this.search.type1,
    }
    if (this.search.type==1){
      data.limit=this.search.limit
    }
    if (this.search.allprice!=null && this.search.allprice.trim()!=""){
      data.allprice=this.search.allprice
    }
    this.apiSev.api("post", "zhangbu/piaojulist", (res) => {
      if(res==[]||res==null||res==''){
        return;
      }
      console.log(res);
      if (res.response == 'success') {
        this.search = res;
        if (type==1){
          this.value1s.length=0;
          this.value1s = res;
        }else if (type==0){
          this.value2s.length=0;
          this.value2s = res;
        };
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);

    this.isShow2con = false;
  }
//--------------已贴现未贴现搜索结束-----------------------
//--------------已贴现未贴现列表开始-----------------------
  discount(isTX) {
    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "zhangbu/piaojulist/?memberId="+data.id+"&istiexian="+isTX+"&belong=0", (res) => {
        if (isTX==1){
            this.value1s.length = 0;
            this.value1s = res;

        }else if (isTX==0){
            this.value2s.length=0;
            this.value2s = res;
        }
      }, (error) => {},1,{});
    })
  }
//--------------已贴现未贴现列表结束-----------------------


//-------------- 统计分析开始 -----------------------
  /*统计时间段*/
  typeChange() {
    this.Statistics.startDate = new Date();
    this.Statistics.endDate = new Date();
    if(this.Statistics.type == 1){
      if(this.Statistics.endDate.getMonth()-1<0){
        this.Statistics.startDate.setFullYear(this.Statistics.endDate.getFullYear()-1);
        this.Statistics.startDate.setMonth(12-1);
      }else{
        this.Statistics.startDate.setMonth(this.Statistics.endDate.getMonth()-1);
      }
    }else if(this.Statistics.type == 2){
      if(this.Statistics.endDate.getMonth()-3<0){
        this.Statistics.startDate.setFullYear(this.Statistics.endDate.getFullYear()-1);
        this.Statistics.startDate.setMonth(12-3);
      }else{
        this.Statistics.startDate.setMonth(this.Statistics.endDate.getMonth()-3);
      }
    }else if(this.Statistics.type == 3){
      if(this.Statistics.endDate.getMonth()-6<0){
        this.Statistics.startDate.setFullYear(this.Statistics.endDate.getFullYear()-1);
        this.Statistics.startDate.setMonth(12-6);
      }else{
        this.Statistics.startDate.setMonth(this.Statistics.endDate.getMonth()-6);
      }
    }
    let url = this.apiSev.getWapUrl() + 'app/pieChart?belong=0&type=' + this.Statistics.type + '&memberId=' + this.memberId;
    if (this.Statistics.type == 4) {
      this.Statistics.diyStartDate = new Date().toISOString();
      this.Statistics.diyEndDate = new Date().toISOString();
      this.Statistics.minDate = new Date().toISOString();
      this.Statistics.maxDate = new Date().toISOString();
      url = this.apiSev.getWapUrl() + 'app/pieChart?belong=0&type=' + this.Statistics.type + '&memberId=' + this.memberId + '&start=' + this.Statistics.diyStartDate + '&end=' + this.Statistics.diyEndDate;
    }
    console.log(url);
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
  /*自定义结束日期最小值*/
  minChange(){
    this.Statistics.minDate =new Date(this.Statistics.diyStartDate.replace(/-/g, "/")).toISOString();
  }

//-------------- 统计分析结束 -----------------------


  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==2){
      if(this.segmentsArray.indexOf(this.pet)<3){
        this.pet = this.segmentsArray[this.segmentsArray.indexOf(this.pet)+1];
      }
    }
    //向右滑
    if(event.direction==4){
      if(this.segmentsArray.indexOf(this.pet)>0){
        this.pet = this.segmentsArray[this.segmentsArray.indexOf(this.pet)-1];
      }
    }
    this.btnIcon();
  }
  // 判断初始展示页面
  segmentShow(){
    let tempId = this.apiSev.tempId;
    if(tempId!=null&&tempId!=''){
      this.tempidex = tempId;
      tempId = '';
    }
    this.tempidex=this.params.get('tempidex');
    if (this.tempidex =='' || this.tempidex == 0 || this.tempidex == undefined){
      this.pet = this.segmentsArray[0];
    }else if(this.tempidex) {
      this.pet = this.segmentsArray[this.tempidex];
    }
  }
  //头部右侧图标按钮
  btnIcon(){
    if(this.segmentsArray.indexOf(this.pet)==0){
      this.isShow1 = true;
      this.isShow2 = false;
      this.isShow3 = false;
      this.isShow4 = false;
    }
    if(this.segmentsArray.indexOf(this.pet)==1) {
      this.isShow2 = true;
      this.isShow1 = false;
      this.isShow3 = false;
      this.isShow4 = false;
    }
    if(this.segmentsArray.indexOf(this.pet)==2) {
      this.isShow3 = true;
      this.isShow1 = false;
      this.isShow2 = false;
      this.isShow4 = false;
    }
    if(this.segmentsArray.indexOf(this.pet)==3){
      this.isShow1 = false;
      this.isShow2 = false;
      this.isShow3 = false;
      this.isShow4 = true;
    }

  }
//跳转到homeB
  closeme(){
    this.navCtrl.setRoot(HomePageB);
  }



  }
