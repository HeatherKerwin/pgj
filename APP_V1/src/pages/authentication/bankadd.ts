import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountListBPage } from "../openaccount/openaccountListB";

@Component({
  selector: 'page-bankadd',
  templateUrl: 'bankadd.html'
})


export class BankAddPage {

  public memberId:any;
  public orgType:any;
  public bankAcctBankBranch:any='请点击选择银行'; //支行名称
  public CnapsCode='';     //支行行号
  public bankNo='';       //总行行号
  public bankName='';     //总行名称
  public bankAcctAcctNo=''; //账户行号
  public bankAcctAcctName=''; //账户名称
  public cibBankId='';      //银行卡id
  public allprice:any=''; //小额鉴定金额
  public status:any;      //银行卡当前状态
  //按钮判断
  public Tonosuccess = true;
  public Tosuccess = false;

  //页面展示判断
  public addkeep = true;
  public cibkeep = false;


  constructor(public storage: Storage, public navCtrl: NavController,public params: NavParams,public loadingCtrl: LoadingController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;

  }
  ionViewDidEnter(){
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    let status = this.params.get("status"); //银行卡状态
    if (status != null && status != '') {
      this.status = status;
      if (this.status != 1) {
        this.addkeep = false;
        this.cibkeep = true;
      }
    }
    let bankAcctAcctNo = this.params.get("bankAcctAcctNo"); //账号
    if (bankAcctAcctNo != null && bankAcctAcctNo != '') {
      this.bankAcctAcctNo = bankAcctAcctNo;
    }
    let bankAcctAcctName = this.params.get("bankAcctAcctName"); //账号名
    if (bankAcctAcctName != null && bankAcctAcctName != '') {
      this.bankAcctAcctName = bankAcctAcctName;
    }
    let bankAcctCnapsCode = this.params.get("bankAcctCnapsCode"); //支行行号
    if (bankAcctCnapsCode != null && bankAcctCnapsCode != '') {
      this.CnapsCode = bankAcctCnapsCode;
    }
    let bankAcctBankBranch = this.params.get("bankAcctBankBranch"); //支行行号
    if (bankAcctBankBranch != null && bankAcctBankBranch != '') {
      this.bankAcctBankBranch = bankAcctBankBranch;
    }
    loading.dismiss();
    //取银行值
    if(this.apiSev.parmData!= null && this.apiSev.parmData !='') {
      let bankNo = this.apiSev.parmData.bankNo;
      if (bankNo != null && bankNo != '') {
        this.bankNo = bankNo;
        this.apiSev.parmData.bankNo = '';
      }
      let bankName = this.apiSev.parmData.bankName;
      if (bankName != null && bankName != '') {
        this.bankName = bankName;
        this.bankAcctBankBranch = bankName;
        this.apiSev.parmData.bankName = '';
      }
      let CnapsCode = this.apiSev.parmData.CnapsCode;
      if (CnapsCode != null && CnapsCode != '') {
        this.CnapsCode = CnapsCode;
        this.apiSev.parmData.CnapsCode = '';
      }
    }
  }

  //支行号查询
  banklistB() {
    this.navCtrl.push(OpenaccountListBPage, {
      ORG_TYPE: this.orgType,
    });
  }

  cardbinding() {
    if (this.CnapsCode == null || this.CnapsCode == '') {
      this.apiSev.itip("请选择对应对公银行");
      return;
    }
    if (this.bankAcctAcctNo == null || this.bankAcctAcctNo == '') {
      this.apiSev.itip("请填写对应账号");
      return;
    }
    if (this.bankAcctAcctName == null || this.bankAcctAcctName == '') {
      this.apiSev.itip("请填写对应账户名");
      return;
    }
    let data:any={
      memberId:this.memberId,
      type:this.orgType,
      bankAcctBankNo:this.bankNo,
      bankAcctCnapsCode:this.CnapsCode,
      bankAcctBankBranch:this.bankAcctBankBranch,
      bankAcctAcctNo:this.bankAcctAcctNo,
      bankAcctAcctName:this.bankAcctAcctName
    };
    this.apiSev.api("newpost", "cibbank/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.error_msg!=null&&data.data.error_msg!=''){
          this.apiSev.itip(data.data.error_msg);
          return;
        }else {
          this.cibBankId = data.data.cibBankId;
          this.addkeep = false;
          this.cibkeep = true;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }

  banklistpop(){
    let id = this.params.get("id"); //银行卡ID
    if (id != null && id != '') {
      this.cibBankId = id;
    }
    let data:any={
      memberId:this.memberId,
      cibBankId:this.cibBankId,
      amt:this.allprice
    };
    this.apiSev.api("newpost", "cibbank/authenticate", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.return_msg != null && data.data.return_msg != '') {
          this.apiSev.itip(data.data.return_msg);
          return;
        }
        if (data.data.error_msg != null && data.data.error_msg != '') {
          this.apiSev.itip(data.data.error_msg);
          return;
        }
        if (data.data.cibBank != null && data.data.cibBank != '') {
          if (data.data.cibBank.status == 5) {
            this.apiSev.itip("您输入的小额鉴定金额有误,您还有" + data.data.authenticate_order.left_count + "机会可操作");
            return;
          }
          if (data.data.cibBank.status == 6) {
            this.apiSev.itip("鉴定失败,请联系客服");
            return;
          }
          if (data.data.cibBank.status == 1) {
            this.apiSev.itip("鉴定成功");
            this.navCtrl.pop();
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

  switching(){
    if(this.allprice > 0){
      this.Tonosuccess = false;
      this.Tosuccess = true;
    }else {
      this.Tonosuccess = true;
      this.Tosuccess = false;
    }
  }
}
