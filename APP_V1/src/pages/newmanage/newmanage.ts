import { Component } from '@angular/core';
import { NavController, ViewController,AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { AddmanagePage } from './addmanage';
import { ExpiremanagePage } from './expiremanage';
import { AdmissionmanagePage } from './admissionmanage';
import { HsmanagePage } from './hsmanage';
import { Editmanage1Page } from './editmanage1';
import { Editmanage2Page } from './editmanage2';
import { DomSanitizer } from "@angular/platform-browser";
import { DiscountYPAttributePage } from '../discount/discountYPAttribute';
import { DiscountSPAttributePage } from '../discount/discountSPAttribute';
import { HomePageB } from '../home/homeB';
import {ExportPage} from "../manage/export";
import {PledgemanagePage} from "./pledgemanage";
import {Editmanage4Page} from "./editmanage4";

@Component({
  selector: 'page-newmanage',
  templateUrl: 'newmanage.html'
})


export class NewmanagePage {

  public messagecontent: '';
  public memberId;

  segmentsArray = ['manage4','manage1', 'manage2', 'manage3'];
  pet: any = this.segmentsArray[0];

  segmentsArray1 = ['me1','me2', 'me3','me4'];
  public pet1: any = this.segmentsArray1[0];


  //过期提醒
  public guoqiLists:any=[];
  //待入账提醒
  public drzLists:any=[];
  //赎回提醒
  public pledgeLists:any=[];


  public newsLists:any=[];
  public pageMax1:any=1;
  public pageNo1=1;
  public recordType:any='OUT';


  //默认弹窗隐藏
  public maskDiv:boolean=false;

  //核算自动输入金额
  public manage2_allmoney:any='';
  //票据核算总计金额
  public hsallmoney:any=0;
  public hsLists:any=[];
  public hslistmoney:any=0;

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

  public browser: any = {url: '', secUrl: ''};

  //指引
  public zy1: boolean = false;
  public zy2: boolean = false;
  public zy3: boolean = false;
  public zy4: boolean = false;
  public zy5: boolean = false;


  //承兑行类型筛选
  public cdhtype:any;
  //筛选时间
  public startTime:any=new Date().toISOString();
  public endTime:any=new Date().toISOString();

    constructor(public storage: Storage,public alertCtrl: AlertController,public sanitizer: DomSanitizer, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
      this.storage.get('userInfo').then((data) => {
        this.memberId = data.id;
        this.guoqiData();
        this.drzData();
        this.pledgeDatas();
        //引用website项目统计分析页面
        let url = this.apiSev.getWebsite() + 'aboutus/statistical?memberId=' + this.memberId;
        console.log(url);
        this.browser.url = url;
        this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
      });

      this.storage.get('manage1').then((data) => {
        if(data==null||data==''||data=='undefined'){
          this.zy1=true;
          this.storage.set('manage1', 1);
        }
      });
    }


  ionViewDidEnter() {
    this.pageNo1=1;
    if(this.apiSev.newState==1){
      this.pet = 'manage1';
      this.pet1 = this.segmentsArray1[0];
      this.newsLists = [];
      this.initData1(1,'OUT');
      this.apiSev.newState=0;
    }
    if(this.apiSev.newState==2){
      this.pet = 'manage1';
      this.pet1 = this.segmentsArray1[1];
      this.newsLists = [];
      this.initData1(1,'HOLD');
      this.apiSev.newState=0;
    }
    if(this.apiSev.newState==3){
      this.pet = 'manage1';
      this.pet1 = this.segmentsArray1[2];
      this.newsLists = [];
      this.initData1(1,'IN');
      this.apiSev.newState=0;
    }
    if(this.apiSev.newState==9){
      this.pet = 'manage1';
      this.pet1 = this.segmentsArray1[3];
      this.newsLists = [];
      this.initData1(1,'PLEDGE');
      this.apiSev.newState=0;
    }
    if(this.apiSev.newState==4){
      this.pet = 'manage4';
      this.guoqiLists=[];
      this.drzLists=[];
      this.pledgeLists=[];
      this.guoqiData();
      this.drzData();
      this.pledgeDatas();
      this.apiSev.newState=0;
    }
    if(this.apiSev.newState==5){
      this.pet = 'manage2';
      this.draftcalculateget();
      this.apiSev.newState=0;
    }
  }

  /**
   * 去票据导出页面
   *
   */
  ToExportPage(){
    this.navCtrl.push(ExportPage,{ROLE:0});
  }


  //显示过期
  guoqiData() {
      this.guoqiLists=[];
    let data:any={
      pageSize:3,
      memberId:this.memberId,            //用户主键
      recordType :'HOLD',    //类型（HOLD持有、OUT已出票、IN待入账）
    };
    this.apiSev.api("newpost", "draftrecord/page/notice", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if(objs.length>0) {
          if(objs.length==10){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            this.guoqiLists.push(objs[i]);
          }
        }
        console.log(this.guoqiLists);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

  //显示待入账提醒
  drzData() {
      this.drzLists=[];
    let data:any={
      pageSize:3,
      memberId:this.memberId,            //用户主键
      recordType :'IN',    //类型（HOLD持有、OUT已出票、IN待入账）
    };
    this.apiSev.api("newpost", "draftrecord/page/notice", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if(objs.length>0) {
          if(objs.length==10){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            this.drzLists.push(objs[i]);
          }
        }
        console.log(this.drzLists);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

  //赎回提醒
  pledgeDatas(){
    this.pledgeLists=[];
    let data:any={
      pageSize:3,
      memberId:this.memberId,            //用户主键
      recordType :'PLEDGE',    //类型（HOLD持有、OUT已出票、IN待入账）
    };
    this.apiSev.api("newpost", "draftrecord/page/notice", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if(objs.length>0) {
          if(objs.length==10){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            this.pledgeLists.push(objs[i]);
          }
        }
        console.log(this.pledgeLists);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

  //分页显示票据管理
  initData1(pagenumber,type) {
    if(type!=null&&type!=''&&type!='undefined'){

    }else {
      type='OUT';
    }
    this.storage.get('manage4').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy4=true;
        this.storage.set('manage4', 1);
      }
    });
    this.storage.get('manage5').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy5=true;
        this.storage.set('manage5', 1);
      }
    });

    this.recordType=type;
    let data:any={
      pageIndex:pagenumber, //第几页
      pageSiz:5,           //一页多少条
      memberId:this.memberId,            //用户主键
      recordType :this.recordType,    //类型（HOLD持有、OUT已出票、IN待入账）
    };
    if (this.cdhtype != 'clean' && this.cdhtype != '' && this.cdhtype != 'undefined' && this.cdhtype != null) {
      data.type2 = this.cdhtype;
      data.start = new Date(this.startTime).getFullYear() + "-" + ("0" + (new Date(this.startTime).getMonth() + 1)).slice(-2) + "-" + new Date(this.startTime).getDate();
      data.end = new Date(this.endTime).getFullYear() + "-" + ("0" + (new Date(this.endTime).getMonth() + 1)).slice(-2) + "-" + new Date(this.endTime).getDate();
    }

    this.apiSev.api("newpost", "draftrecord/page", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.pageMax1=data.data.pages;
        let objs = data.data.list;
        if(objs.length>0) {
          if(objs.length==10){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            this.newsLists.push(objs[i]);
          }
        }
        console.log(this.newsLists);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

  doInfinite1(infiniteScroll) {
    setTimeout(() => {
      if (this.pageMax1<this.pageNo1+1)
      {
        infiniteScroll.complete();
      }
      else{
        this.pageNo1=this.pageNo1+1;
        this.initData1(this.pageNo1,this.recordType);
        infiniteScroll.complete();
      }
    }, 500);
  }


  //保存（编辑）货款核算
  draftcalculatesave() {
    if(this.manage2_allmoney<=0){
      this.apiSev.itip("请输入大于0元的预收货款总额");
      return;
    }
    this.hsLists=[];
    this.hslistmoney=0;
    let data:any={
      //pageIndex:pagenumber, //第几页
      //pageSiz:3,           //一页多少条
      memberId:this.memberId,            //用户主键
      allmoney :this.manage2_allmoney,    //核算的金额
      //draftRecordIds:'',            //票据管理（票据管理主键）集合，多个主键
    };
    this.apiSev.api("newpost", "draftcalculate/save", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.hsallmoney=data.data.draftCalculate.allmoney;
        let objs = data.data.draftCalculateEachs;
        if(objs.length>0) {
          for (let i = 0; i < objs.length; i++) {
            this.hslistmoney=this.hslistmoney+objs[i].allmoney;
            this.hsLists.push(objs[i]);
          }
        }
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

  //根据用户主键货款核算（含明细）
  draftcalculateget() {
    this.storage.get('manage3').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy3=true;
        this.storage.set('manage3', 1);
      }
    });

    this.storage.get('manage2').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy2=true;
        this.storage.set('manage2', 1);
      }
    });

    this.hsLists=[];
    this.hslistmoney=0;
    let data:any={
      memberId:this.memberId,            //用户主键
    };
    this.apiSev.api("newpost", "draftcalculate/get", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.hsallmoney=data.data.draftCalculate.allmoney;
        let objs = data.data.draftCalculateEachs;
        if(objs.length>0) {
          for (let i = 0; i < objs.length; i++) {
            this.hslistmoney=this.hslistmoney+objs[i].allmoney;
            this.hsLists.push(objs[i]);
          }
        }
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }



  //分享弹出层
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
      this.cdhtype='clean';
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
  }

  //前往新增票据
  add(){
    this.navCtrl.push(AddmanagePage);
  }

  //去往到期提醒
  expireData(){
    this.navCtrl.push(ExpiremanagePage);
    this.apiSev.newState=4;
  }

  //去往待入账提醒
  admissionData(){
    this.navCtrl.push(AdmissionmanagePage);
    this.apiSev.newState=4;
  }

  //去往质押提醒
  pledgeData(){
    this.navCtrl.push(PledgemanagePage);
    this.apiSev.newState=4;
  }

  //前往票据核算
  hsmanageData(){
    this.navCtrl.push(HsmanagePage);
    this.apiSev.newState=5;
  }


  //票据编辑1(已出库)
  editmanage1Data(item){
    if(this.pet1=='me1'){
      this.navCtrl.push(Editmanage1Page,
        {
          NAME:'票据编辑(已出库)',
          ITEM:item,
        });
      this.apiSev.newState=1;
    }else if(this.pet1=='me2'){
      this.navCtrl.push(Editmanage1Page,
        {
          NAME:'票据编辑(持有中)',
          ITEM:item,
        });
      this.apiSev.newState=2;
    }else if(this.pet1=='me3') {
      this.navCtrl.push(Editmanage2Page,
        {
          ITEM:item,
        });
      this.apiSev.newState=3;
    }else if(this.pet1=='me4'){
      //质押票
      this.navCtrl.push(Editmanage4Page,
        {
          ITEM:item,
        });
      this.apiSev.newState=9;
    }
  }

  //核算去出票
  outCp(){
    this.navCtrl.setRoot(HomePageB);
  }

  //去出票
  outData(item) {
    if (item.draftType == 'YZ' || item.draftType == 'YD') {
      this.navCtrl.push(DiscountYPAttributePage);
    }

    if (item.draftType == 'SZ' || item.draftType == 'SD') {
      this.navCtrl.push(DiscountSPAttributePage);
    }
  }


  //删除
  delData(item,i) {
    let alert = this.alertCtrl.create({
      title: '提示',
      message: '是否确认删除?',
      buttons: [
        {
          text: '取消',
          handler: () => {
          }
        },
        {
          text: '确认',
          handler: () => {
            this.delChange(item.id,i);
          }
        }
      ]
    });
    alert.present();
  }

  delChange(id,i){
    let data:any={
      id:id, //票据管理主键
    };
    this.apiSev.api("newpost", "draftrecord/del", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.apiSev.itip("删除成功");
        this.newsLists.splice(i,1);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }



  //清空
  cleanData(){
    this.cdhtype=1;
    this.startTime=new Date().toISOString();
    this.endTime=new Date().toISOString();
  }

}
