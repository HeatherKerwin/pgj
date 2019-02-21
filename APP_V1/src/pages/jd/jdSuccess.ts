import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';
import { Iframe } from '../order/iframe';
import { BankListPage } from '../authentication/banklist';
import {jdbanklistPage} from "../jd/jdbanklist";
import {jdxingyePage} from "./jdxingye";

@Component({
  selector: 'page-jdSuccess',
  templateUrl: 'jdSuccess.html'
})


export class JdSuccessPage {

  public memberId='';
  public orgType:any;
  public cib:any={
    id:'',
  };
  //金额
  public v_acct:any={
    balance:'0.00', //可用余额
    frozen_balance:'0.00', //冻结余额
  };
  public active:any={
  };

  public mobile;    //手机号
  public corp_no;   //企业编号

  //企业角标判断
  public enterprises=true;
  public enterprise = false;
  //银行角标判断
  public bankinfo = true;
  public bankinfos = false;
  //财务
  public finance = true;
  public finances = false;

  public judgeA = true; //行号判断
  public judgeB = false;
  public outputemail='';

  //京东数据
  public jdData:any={};

  //是否开兴业户
  public xyhu:any=0;

  public isMask:boolean;
  public isCash:boolean;


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams,public loadingCtrl: LoadingController) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.jdjr();
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
      });
    });
  }

  ionViewDidEnter(){
  }


//企业法人营业执照
  Arrow(){
    this.enterprises = !this.enterprises;
    if (this.enterprises == true) {
      this.enterprise = false
    } else if (this.enterprises == false) {
      this.enterprise = true
    }
  }
  //银行信息
  Tobanks(){
    this.bankinfo = !this.bankinfo;
    if (this.bankinfo == true) {
      this.bankinfos = false
    } else if (this.bankinfo == false) {
      this.bankinfos = true
    }
  }
  //财务
  Tofinance(){
    this.finance = !this.finance;
    if(this.finance==true){
      this.finances = false;
    }else if(this.finance==false){
      this.finances = true;
    }
  }


  //跳转到我里面
  closeme(){
    if (this.orgType == 1) {
      this.navCtrl.setRoot(MePage,{
        INDEX:5
      });
    } else if (this.orgType == 0) {
      this.navCtrl.setRoot(MePageB,{
        INDEX:5
      });
    }
  }


  outputHT(){
    this.isCash = true;
    this.isMask = true;
  }
  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.isCash = false;
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

  //邮箱发送
  cash(){
    if(this.outputemail == null || this.outputemail== ''){
      this.apiSev.itip("请输入您的邮箱");
      return;
    }else {
      let email = this.outputemail.match(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/);
      if (email) {
      } else {
        this.apiSev.itip("输入的邮箱输入格式有误");
        return;
      }
    }
    let data:any={
      no:this.cib.corpNo,
      email:this.outputemail,
      isCib:'true'
    };
    this.apiSev.api("newpost", "/cib/econtract/email", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.isMask = false;
        this.isCash = false;
        this.apiSev.itip(data.msg);
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }


  //京东银行卡展示
  jdbanklist(){
    this.navCtrl.push(jdbanklistPage,{
      accountNo:this.jdData.occBankAccount,
      bankName:this.jdData.occBankName
    });
  }

  //取京东信息
  //京东是否开户
  jdjr(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data!= null && data.data != '' && data.data != 'undefined') {
          if (data.data.status == 2) {
            this.jdData = data.data;
            return;
          }
        }else {
          this.jdjrbind();
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //京东是否绑定
  jdjrbind(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjrbind/get/jdjr/jdjrbind", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.jdData = data.data.jdjr;
        return;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //兴业补充信息开户
  realnamepaycheckmoney(){
    this.navCtrl.push(jdxingyePage);
  }

}
