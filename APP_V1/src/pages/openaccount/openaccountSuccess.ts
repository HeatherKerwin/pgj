import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';
import { Iframe } from '../order/iframe';
import { BankListPage } from '../authentication/banklist';

@Component({
  selector: 'page-openaccountSuccess',
  templateUrl: 'openaccountSuccess.html'
})


export class OpenaccountSuccessPage {

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




  public openaccount: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
    contactMobile:'',   //联系人手机号
    email:'',           //邮箱
    contactName:'',     //联系人姓名
    address:'',         //联系人公司地址
    agentCertNo:'',   //联系人的身份证号码
    bizLicenceType:'',  //营业执照上公司类型
    bizLicenceAddr:'',  //营业执照上的住所
    bizLicenceFoundedDate:'', //入营业执照上的成立日期
    bizLicenceLegalName:'',  //营业执照上的法人姓名
    legalCertNo:'', //营业执照上的法人身份证
    bankAcctAcctName:'', //卡号名
    bankAcctAcctNo:'',  //卡号
    bankAcctBankBranch:'',
    CnapsCode:'',
    bankNo:'', //企业开户行行号
    bankName:'',            //企业开户行名称
    treasurerName:'', //财务姓名
    treasurerCertNo:'', //财务身份证号
    //机构信用代码
    orgCreditCodeCertCode:'', //代码
    orgCreditCodeCertName:'', //名称
    orgCreditCodeCertAddr:'',//地址
    orgCreditCodeCertExpiredDate:'',  //有效期
    //开户许可证
    acctPermitNo:'', //编号
    acctPermitPermitNo:'', //核准号
    //税务登记号
    taxCertGovTaxNo:'',  //国税
    taxCertLocalTaxNo:'', //地税
    taxCertName:'',  //纳税人名称
    //组织机构代码
    orgCodeCertCode:'', //代码
    orgCodeCertName:'', //机构名称
    orgCodeCertType:'', //机构类型
  };
  public isMask:boolean;
  public isCash:boolean;


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams,public loadingCtrl: LoadingController) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.apiSev.pass='0';
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
        this.Show();
        this.openaccounTactive();
        if(this.orgType==1){
          this.judgeA = true;
          this.judgeB = false;
        }else if(this.orgType==0){
          this.judgeA = false;
          this.judgeB = true;
        }
      });
    });
    //this.orgType = this.params.get("ORG_TYPE"); //角色类型orgType
  }

  ionViewDidEnter(){
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.Show();
    loading.dismiss();
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

  openaccounTactive(){
    let data:any={
      memberId:this.memberId,
      type:this.orgType,
    };
    this.apiSev.api("newpost", "orginfo/rz", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.cib = data.data.cib;
        this.openaccount.name = this.cib.name;
        this.openaccount.bizLicenceRegisteredNo = this.cib.bizLicenceRegisteredNo;
        this.openaccount.bizLicenceLegalName = this.cib.bizLicenceLegalName;
        this.openaccount.bankAcctBankBranch = this.cib.bankAcctBankBranch;
        this.openaccount.bankAcctCnapsCode = this.cib.bankAcctCnapsCode;
        this.openaccount.bankAcctAcctNo = this.cib.bankAcctAcctNo;
        this.openaccount.contactName = this.cib.contactName;
        this.openaccount.contactMobile = this.cib.contactMobile;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //金额接口

  Show(){
    let data = {
      memberId:this.memberId,
      type:this.orgType,
    };
    this.apiSev.api("newpost", "cib/corp/query", (res) => {
      let data = res.data;
      if(data.data.corp==null) {
        this.v_acct.balance = '0.00';  //可用余额
        this.v_acct.frozen_balance = '0.00'; //冻结余额
      }else {
        let corp_no = res.data.data.corp.corp_no;
        let b = corp_no.length - 6;
        this.corp_no = corp_no.substring(b);
        this.mobile=data.data.cib.contactMobile;
        this.v_acct.balance = data.data.corp.v_acct.balance;
        this.v_acct.frozen_balance = data.data.corp.v_acct.frozen_balance;

      }
    }, (error) => {},2000,data);
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

  Showall(){
    let data = {
      memberId:this.memberId,
      type:this.orgType,
    };

    this.apiSev.api("newpost", "cib/corp/query", (res) => {
      let data = res.data;
      this.navCtrl.push(Iframe,{url:data.data.login_url});
    }, (error) => {},2000,data);
  }

  //银行列表
  banklist(){
    this.navCtrl.push(BankListPage,{
      ORG_TYPE: this.orgType
    });
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

}
