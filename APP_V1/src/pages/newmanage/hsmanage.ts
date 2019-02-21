import { Component } from '@angular/core';
import { NavController, ViewController,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { AddhsPage } from './addhs';

@Component({
  selector: 'page-hsmanage',
  templateUrl: 'hsmanage.html'
})


export class HsmanagePage {

  public messagecontent: '';
  public memberId;

  //默认弹窗隐藏
  public maskDiv: boolean = false;

  public hsLists: any = [];
  public newsListsB: any = [];
  public pageMax1: any = 1;
  public pageNo1 = 1;

  public count:any=0;
  public countmoeny:any=0;
  //选择属性
  public backgroundchager:any;

    constructor(public storage: Storage, public navCtrl: NavController,public modalCtrl: ModalController, public viewCtrl: ViewController, public apiSev: apiService) {
      this.storage.get('userInfo').then((data) => {
        this.memberId = data.id;
        this.apiSev.hsLists=[];
        this.initData1(1);
      });
    }


    //前往添加列表
  addHsData(){
    let profileModal = this.modalCtrl.create(AddhsPage);
    profileModal.onDidDismiss(data => {
      this.countData();
    });
    profileModal.present();
  }

  ionViewDidEnter(){
    this.countData();
  }


  //分页显示票据管理
  initData1(pagenumber) {
    let data:any={
      pageIndex:pagenumber, //第几页
      pageSiz:10,           //一页多少条
      memberId:this.memberId,            //用户主键
      recordType :'HOLD',    //类型（HOLD持有、OUT已出票、IN待入账）
    };
    this.apiSev.api("newpost", "draftrecord/page", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.pageMax1=data.data.pages;
        let objs = data.data.list;
        if(objs.length>0) {
          if(objs.length==10){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            objs[i].xuanz=0;
            this.apiSev.hsLists.push(objs[i]);
          }
        }
        console.log(this.apiSev.hsLists);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

  doInfinite1(infiniteScroll) {
    setTimeout(() => {
      if (this.pageMax1<1)
      {
        infiniteScroll.complete();
      }
      else{
        this.pageNo1=this.pageNo1+1;
        this.initData1(this.pageNo1);
        infiniteScroll.complete();
      }
    }, 500);
  }


  countData(){
    this.count=0;
    this.countmoeny=0;
    let objs = this.apiSev.hsLists;
    if(objs.length>0) {
      for (let i = 0; i < objs.length; i++) {
        if(objs[i].xuanz==1){
          this.count=this.count+1;
          this.countmoeny=this.countmoeny+objs[i].allmoney
        }
      }
    }
  }

  //保存（编辑）货款核算
  draftcalculatesave() {
    if(this.countmoeny<=0){
      this.apiSev.itip("请添加预收货款");
      return;
    }

    let objs = this.apiSev.hsLists;
    let draftRecordIds:any=[];
    if(objs.length>0) {
      for (let i = 0; i < objs.length; i++) {
        if(objs[i].xuanz==1){
          draftRecordIds.push(objs[i].id);
        }
      }
    }


    let data:any={
      memberId:this.memberId,            //用户主键
      allmoney :this.countmoeny,    //核算的金额
      draftRecordIds:draftRecordIds,            //票据管理（票据管理主键）集合，多个主键
    };
    this.apiSev.api("newpost", "draftcalculate/save", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.apiSev.itip("保存成功");
        this.navCtrl.pop();
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

  popData(){
    this.navCtrl.pop();
  }
}
