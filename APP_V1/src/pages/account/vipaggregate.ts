import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { TransactionPage } from './transaction';
import {VipupgradePage} from "./vipupgrade";

@Component({
  selector: 'page-vipaggregate',
  templateUrl: 'vipaggregate.html'
})


export class VipaggregatePage {
  public orgType:any;
  public mobile:any='';
  public memberId='';
  public enDat='';
  public money:any=0;
  public yearmony='';
  public seasonmony='';
  public monthmony='';
  public VIPmoney='';
  public vipId=3;
  public NoVIP:boolean;
  public YESVIP:boolean;
  public yearvip:boolean=false;
  public seasonvip:boolean=true;
  public monthvip:boolean=true;
  public MyRed='';


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController,public params: NavParams, public apiSev: apiService) {
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
    let MY_RED = this.params.get("MY_RED"); //角色类型
    this.MyRed = MY_RED;
    this.payData();
  }
  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.mobile=data.mobile;
      this.loadData();
      this.paymony();
    });
  }

  iPhoneqs(){
    let a = this.mobile.toString().substring(0,3);
    let b = this.mobile.toString().length - 4;
    let c = this.mobile.toString().substring(b);
    return a + "***"+ c;
  }

  //点击年卡会员
  yearVip(){
    this.yearvip = !this.yearvip;
    if (this.yearvip == false) {
      this.seasonvip = true;
      this.monthvip = true;
      this.VIPmoney = this.yearmony;
      this.vipId = 3
    }
  }
  //点击季卡会员
  seasonVip(){
    this.seasonvip = !this.seasonvip;
    if (this.seasonvip == false) {
      this.yearvip = true;
      this.monthvip = true;
      this.VIPmoney = this.seasonmony;
      this.vipId = 2
    }
  }
  //点击月卡会员
  monthVip(){
    this.monthvip = !this.monthvip;
    if (this.monthvip == false) {
      this.yearvip = true;
      this.seasonvip = true;
      this.VIPmoney = this.monthmony;
      this.vipId = 1
    }
  }

  //获取会员类型金额
  payData(){
    let data:any={
      vipType:this.orgType
    };
    this.apiSev.api("newpost", "vip/list", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data[0].id==1||data.data[0].id==4){
          this.monthmony = data.data[0].money;
        }
        if(data.data[1].id==2||data.data[1].id==5){
          this.seasonmony = data.data[1].money;
        }
        if(data.data[2].id==3||data.data[2].id==6){
          this.yearmony = data.data[2].money;
          this.VIPmoney = this.yearmony;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //获取用户购买的会员
  loadData(){
    let data:any={
      memberId:this.memberId,
      vipType:this.orgType
    };
    this.apiSev.api("newpost", "vipmember/get/by/memberid", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.vipMember!=null&&data.data.vipMember!=''){
          this.enDat=this.apiSev.transformdateB(data.data.vipMember.endDate);
          let enDat =new Date(data.data.vipMember.endDate.replace(/-/g, "/")).getTime();
          let xianzai=new Date().getTime();
          if(enDat >= xianzai){
            this.YESVIP = true;
            this.NoVIP = false;
          }else if(enDat < xianzai){
            this.NoVIP = true;
            this.YESVIP = false;
          }
        }else {
          this.NoVIP = true;
          this.YESVIP = false;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //确认支付
  savepay(){
    if(this.yearvip==true && this.monthvip==true && this.seasonvip==true){
      this.apiSev.itip("请选择充值的会员类型");
      return
    }
    this.paymony();
    let data: any = {
      memberId: this.memberId,
      vipType:this.orgType,
      vipId:this.vipId
    };
    this.apiSev.api("newpost", "vipmember/save", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.apiSev.itip("已成功开通会员");
        this.loadData();
        this.YESVIP = true;
        this.NoVIP = false;
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }


  //补充押金
  addDeposit(){
    this.navCtrl.push(TransactionPage,{
      ORG_TYPE:this.orgType
    });
  }

  paymony() {
    //加载押金账户信息
    let data: any = {
      memberId: this.memberId
    };
    this.apiSev.api("newpost", "account/info/by/member", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.money = objs.data.account.money;
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //会员升级
  vipupgradeData(){
    this.navCtrl.push(VipupgradePage);
  }
}
