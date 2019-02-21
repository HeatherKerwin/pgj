import { Component } from '@angular/core';
import { NavController , NavParams , AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import {AccountPage} from "../account/account";
import {PaymentPage} from "../account/payment";
import {ExamplesPage} from "./examples";

@Component({
  selector: 'page-transaction',
  templateUrl: 'transaction.html'
})
export class TransactionPage {

  public account:any={};
  public recharge:any={
    type:'3'
  };
  public memberId='';
  public orgType:any;
  public isMask:boolean;
  public isCash:boolean;
  public isPay:boolean;

  public card:any={};
  public mobile='';
  public btnText = '获取验证码';
  public isCodeDisabled:boolean;
  public cardBank=''; //提现账户（银行）
  public cardUserName=''; //用户名
  public cardNumber=''; //卡号
  public cashmoney:any='';  //提现金额


  public txtype:any;  //提现类型

  constructor(
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public params: NavParams,
    public alertCtrl: AlertController,
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('userInfo').then((data)=>{
      this.mobile=data.mobile;
    })
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType
  }

  ionViewDidEnter(){
    this.loadData();
  }

  //加载账户信息
  loadData(){
    let data:any={
      memberId:this.memberId
    }
    this.apiSev.api("newpost", "account/info/by/member", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.account=objs.data;
        this.account.money=objs.data.account.money;
        if(objs.data.disc_deposit!=null&&objs.data.disc_deposit!=''){
          this.account.disc_deposit = objs.data.disc_deposit;
        }else {
          this.account.disc_deposit = 0;
        }
        if(objs.data.dist_deposit!=null&&objs.data.dist_deposit!=''){
          this.account.dist_deposit = objs.data.dist_deposit;
        }else {
          this.account.dist_deposit = 0;
        }
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
  loadBank(){
    let data:any={
      memberId:this.memberId,
      type:0,
    };
    this.apiSev.api("newpost", "orginfo/rz/", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        if (objs.data.info_state == 'NONE' || objs.data.card_state == 'NONE' || objs.data.info_state == 'NOPASS' || objs.data.card_state == 'NOPASS' || objs.data.card_state == 'PENDING' || objs.data.info_state == 'PENDING') {

          //获取银行卡信息
          let ORG_TYPE=this.params.get("ORG_TYPE");
          let data1:any={
            memberId: this.memberId,
            orgType:ORG_TYPE//0企业1机构
          }
          this.apiSev.api("newpost", "bankcard/default", (res) => {
            let thisData=res.data;
            if (thisData.response == 'success') {

              let result = thisData.data;
              this.card.bank=result.bankCard.bank;
              this.card.cardNum=result.bankCard.cardNum;
              this.card.company=result.orgInfo.company;
              this.card.bankCardId=result.bankCard.id;
            }
          },(error) => {}, 500,data1);

          return;
        }
      }else {
        this.apiSev.itip(data.msg);
      }

    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  //提现
  cashBtn(txtype) {
    if (this.account.money > 0) {
      this.txtype=txtype;
      this.isCash = true;
      this.isMask = true;
      if(this.txtype=='ALIPAY'){
        if(this.apiSev.ALIPAY!=null&&this.apiSev.ALIPAY!=''&&this.apiSev.ALIPAY!='undefined'){
          this.cardUserName=this.apiSev.ALIPAY.cardUserName;
          this.cardNumber=this.apiSev.ALIPAY.cardNumber;
        }
      }
      if(this.txtype=='BANKPAY'){
        if(this.apiSev.BANKPAY!=null&&this.apiSev.BANKPAY!=''&&this.apiSev.BANKPAY!='undefined'){
          this.cardUserName=this.apiSev.BANKPAY.cardUserName;
          this.cardNumber=this.apiSev.BANKPAY.cardNumber;
          this.cardBank=this.apiSev.BANKPAY.cardBank;
        }
      }
    } else {
      this.apiSev.itip("账户余额为0，不可提现");
      return;
    }
  }

  cash(){
    if(this.account.money < this.cashmoney||this.cashmoney < 1){
      this.apiSev.itip("您输入的金额不正确,请输入小于或等于钱包余额的提现金额！");
      return;
    }
    if (this.card.code==''){
      this.apiSev.itip("请输入正确验证码！");
	  return;
    }
    let data:any={
      code:this.card.code,
      memberId:this.memberId,
      money:this.cashmoney,
      cardUserName:this.cardUserName,
      cardNumber:this.cardNumber,
    };

    if(this.txtype=='ALIPAY'){
        data.way='TX_OFFLINE_ALIPAY';
        this.apiSev.ALIPAY.cardUserName=this.cardUserName;
        this.apiSev.ALIPAY.cardNumber=this.cardNumber;
    }
    if(this.txtype=='BANKPAY'){
      data.way='TX_OFFLINE_BANK';
      data.cardBank=this.cardBank;
      this.apiSev.BANKPAY.cardUserName=this.cardUserName;
      this.apiSev.BANKPAY.cardNumber=this.cardNumber;
      this.apiSev.BANKPAY.cardBank=this.cardBank;

    }

    this.apiSev.api("newpost", "accountlog/save/tx", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.isCash = false;
        this.isMask = false;
        this.loadData();
        let alert = this.alertCtrl.create({
          title: '提示',
          subTitle: '提现将于两个工作日之内到账，请注意查收！',
          buttons: [
            {
              text: '确定',
              handler: data => {
              }
            },
          ]
        });
        alert.present();
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  //立即支付
  pay(){
    if(this.recharge.type!='1'&& this.recharge.type!='2'&& this.recharge.type!='3'){
      this.apiSev.itip("请选择充值方式");
      return;
    }
    if ((this.recharge.addmoney<=0)||(this.recharge.addmoney=='')||(this.recharge.addmoney==null)){
      this.apiSev.itip("充值金额不能为空！");
	  return;
    }
    if (this.recharge.type=='1'){
      this.recharge.type='BAOFOO';
    }else if (this.recharge.type=='2'){
      this.recharge.type='BILL99';
    }else if (this.recharge.type=='3'){
      this.recharge.type='ALIPAY';
    }
    this.isMask=true;
    this.isPay=true;
    this.isCodeDisabled=false;
    this.navCtrl.push(PaymentPage,{
      money:this.recharge.addmoney,
      memberId:this.memberId,
      type:this.recharge.type
    });

  }

  //交易记录
  ToAccountPage(){
    this.navCtrl.push(AccountPage);
  }

  //获取验证码
  getCode () {
    let data2={
      type:'TX',
      mobile:this.mobile
    }
    this.apiSev.api("newpost", "/send/sms", (res) => {
      if (res.response == 'success') {
        let second = 60;

        let timePromise = setInterval(()=>{
          if(second<=0){
            clearInterval(timePromise);
            timePromise = undefined;
            second = 60;
            this.btnText = "重发验证码";
            this.isCodeDisabled = false;
          }else{
            second--;
            this.btnText = second + "秒后可重发";
            this.isCodeDisabled = true;
          }
        },1000,100);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data2);

  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.isCash = false;
      this.isPay = false;
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

  //前往支付示例子
  examplespage(){
    this.navCtrl.push(ExamplesPage);
  }
}
