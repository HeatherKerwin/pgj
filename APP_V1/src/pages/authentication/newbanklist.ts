import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { BankAddPage } from './bankadd';
import {jdbankbdPage} from "../jd/jdbankbd";

@Component({
  selector: 'page-newbanklist',
  templateUrl: 'newbanklist.html'
})


export class NewBankListPage {
  public memberid:any;
  public orgType:any;
  public banklist:any=[];
  public bank:any=[];
  public isOk:boolean; //是否还有数据
  public cibbank=true;
  public discountorder:any = '';
  public jdData:any={};
  public DD:any={}; //新订单详情

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams) {
    let DD = this.params.get("DD"); //角色类型
    if(DD!=null&&DD!=''&&DD!='undefined'){
      this.DD=DD;
    }
    console.log(this.DD);
  }



  //银行卡遮挡显示
  ionViewDidEnter(){
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
    this.storage.get('userInfo').then((data) => {
      this.memberid = data.id;
      this.Toactive();
      this.dataLoad();
    });
  }

  //点击银行卡之后
  bankchanger(bank) {
    this.apiSev.parmData = {
      bankAcctAcctName: bank.bankName,
      bankAcctAcctNo: bank.bankCnaps,
      bankAcctBankBranch: bank.accountNo,
      vAcctAcctNo: bank.vAcctAcctNo,
      bindId:bank.bindId,
    };
    this.navCtrl.pop();
    return;
  }

  dataLoad() {
    this.banklist=[];
    let data: any = {
      memberId: this.memberid,
      type:this.orgType,
    };
/*    let DISCOUNTORDER = this.params.get("DISCOUNTORDER"); //贴现入口进传值
    if (DISCOUNTORDER == 1) {
      this.discountorder = DISCOUNTORDER;
      data.status=1;
    }*/

    this.apiSev.api("newpost", "jdjr/cib/account/card", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data;
        this.isOk = false;
        if (objs.length > 0) {
          if (objs.length == 10) {//说明可能还有数据
            this.isOk = true;
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
    }, 6000, data);
  }

  returnCl(){
	  this.apiSev.tempId="4";
	  this.navCtrl.pop();
  }
  //添加银行卡
  bankpay(){
    if(this.jdData.status==2&&this.jdData.check_state=='PASS'){
      this.navCtrl.push(jdbankbdPage,{
        ORG_TYPE: this.orgType
      });
    }else {
      this.navCtrl.push(BankAddPage,{
        ORG_TYPE: this.orgType
      });
    }

  }


  //开户判断
  Toactive() {
    let data:any={
      memberId:this.memberid,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.jdjr != null && data.data.jdjr != '' && data.data.jdjr != 'undefined') {
          this.jdData = data.data.jdjr;
        }
      } else {
        this.apiSev.itip(data.msg);
        return;
      }
    }, (error) => {
      this.apiSev.itip("操作失败");
      return;
    }, 500, data);
  }
}
