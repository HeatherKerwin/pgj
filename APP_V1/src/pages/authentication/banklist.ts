import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { BankAddPage } from './bankadd';

@Component({
  selector: 'page-banklist',
  templateUrl: 'banklist.html'
})


export class BankListPage {
  public memberid:any;
  public orgType:any;
  public banklist:any=[];
  public bank:any=[];
  public isOk:boolean; //是否还有数据
  public cibbank=true;
  public discountorder:any = '';

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams) {
  }
  //public item:any = {};

  //银行卡遮挡显示
/*
  checkNum(cardNum){
    this.kahao = cardNum;
    alert("银行卡号：" + this.kahao + ", 隐藏后：" + this.kahao.replace(this.kahao.substr(10,6), "******"));
    console.log(this.kahao);
  }
*/
  ionViewDidEnter(){
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
    this.storage.get('userInfo').then((data) => {
      this.memberid = data.id;
      this.dataLoad();
    });
  }

  //点击银行卡之后
  bankchanger(id,bankAcctAcctNo,bankAcctAcctName,bankAcctBankBranch,status,bankAcctCnapsCode,bankAcctBankNo,vAcctAcctNo){
    if(status!=1 && this.discountorder!=1){
      this.navCtrl.push(BankAddPage,{
        ORG_TYPE: this.orgType,
        bankAcctAcctNo:bankAcctAcctNo,
        id:id,
        bankAcctAcctName:bankAcctAcctName,
        status:status,
        bankAcctBankBranch:bankAcctBankBranch,
        bankAcctBankNo:bankAcctBankNo,
        bankAcctCnapsCode:bankAcctCnapsCode
      });
    }else if(this.discountorder==1){
      this.apiSev.parmData = {
        bankAcctAcctName:bankAcctAcctName,
        bankAcctAcctNo:bankAcctAcctNo,
        bankAcctBankBranch:bankAcctBankBranch,
        vAcctAcctNo:vAcctAcctNo,
      };
      this.navCtrl.pop();
    }else {
      return;
    }
  }

  dataLoad() {
    this.banklist=[];
    let data: any = {
      memberId: this.memberid,
      type:this.orgType,
    };
    let DISCOUNTORDER = this.params.get("DISCOUNTORDER"); //贴现入口进传值
    if (DISCOUNTORDER == 1) {
      this.discountorder = DISCOUNTORDER;
      data.status=1;
    }

    this.apiSev.api("newpost", "cibbank/list", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data;
        this.isOk = false;
        if (objs.length > 0) {
          if (objs.length == 10) {//说明可能还有数据
            this.isOk = true;
          }
          if(objs.length < 4){
            this.cibbank=true;
          }else {
            this.cibbank=false;
          }
          for (let i = 0; i < objs.length; i++) {
            this.banklist.push(objs[i]);
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }

  returnCl(){
	  this.apiSev.tempId="4";
	  this.navCtrl.pop();
  }
  //添加银行卡
  bankpay(){
    this.navCtrl.push(BankAddPage,{
      ORG_TYPE: this.orgType
    });
  }
}
