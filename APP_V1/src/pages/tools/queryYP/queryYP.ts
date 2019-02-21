import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {MyInquiryReplyPage} from "../../me/myInquiryReply/myInquiryReply";
import {TransactionPage} from "../../account/transaction";

@Component({
  selector: 'page-queryYP',
  templateUrl: 'queryYP.html'
})


export class QueryYP {

  public maskDiv = false; //弹出层
  public Confirm = false;
  public user: any = {};
  public btnText = '获取验证码';
  public mobile=''; //手机号
  public isCodeDisabled = false;

  public orgType:any= '';
  public memberId: '';
  public orgId: '';
  public payFree = '';
  public money = 0; //账号余额押金

  public Query: any = {
    startDate: new Date().toISOString(),  //出票日期
    endDate: new Date(new Date().getTime() + 180 * 24 * 3600 * 1000).toISOString(),   //结束日期
    min: new Date().toISOString(),       //最小结束日期
    draftNo: '', //票号
    money: '',   //金额
    drawer: '',  //出票人
    payee: '',   //收款人
    bank: '',    //承兑行全称
    bankNo: '',  //承兑行号
    payMoney: 20, //付款金额
    code: '',     //验证码
    payWay:5 //支付方式-余额
  };


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams) {

  }

  initPage() {
    let datas: any = {
      memberId: this.memberId
    };

    this.apiSev.api("newpost", "inquiryreply/init", (res) => {
      let data = res.data;
      if (data.data.free) {
        this.payFree='0.0';
        this.Query.payMoney = 0
      }else {
        this.payFree=data.data.money;
        this.Query.payMoney = data.data.money
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');
    }, 500, datas);


    //加载账户信息
      let data:any={
        memberId:this.memberId
      };
      this.apiSev.api("newpost", "account/info/by/member", (res) => {
        let objs = res.data;
        if (objs.response == 'success') {
          this.money=objs.data.account.money;
        } else {
          this.apiSev.itip(objs.msg);
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
  }


  queryPay(e) {
    let startDate = new Date(this.Query.startDate).getFullYear() + "-" + ("0" + (new Date(this.Query.startDate).getMonth() + 1)).slice(-2) + "-" + new Date(this.Query.startDate).getDate();
    let endDate = new Date(this.Query.endDate).getFullYear() + "-" + ("0" + (new Date(this.Query.endDate).getMonth() + 1)).slice(-2) + "-" + new Date(this.Query.endDate).getDate();
    let data: any = {
      memberid: this.memberId,
      draftNo: this.Query.draftNo,
      money: this.Query.money,
      drawer: this.Query.drawer,
      payee: this.Query.payee,
      bank: this.Query.bank,
      bankNo: this.Query.bankNo,
      start: startDate,
      end: endDate,
      payMoney: this.Query.payMoney,
      needInvoice: 1,
      code: this.Query.code,
      orgType: this.orgType,
      payWay:this.Query.payWay
    };
    if (this.orgId != null && this.orgId != "" && this.orgId != undefined) {
      data.orgId = this.orgId;
    }

    if (this.Query.draftNo == null || this.Query.draftNo == '') {
      this.apiSev.itip("请输入票号");
      return;
    } else if (this.Query.draftNo.length != 16 && this.Query.draftNo.length != 30) {
      this.apiSev.itip("您输入的票号位数不对，请仔细核对。");
      return;
    }
    if (Number(this.Query.money) < 1) {
      this.apiSev.itip("您输入的票面金额不能小于1万元。");
      return;
    }
    if (this.Query.drawer == null || this.Query.drawer == '') {
      this.apiSev.itip("请输入出票人。");
      return;
    }
    if (this.Query.payee == null || this.Query.payee == '') {
      this.apiSev.itip("请输入收款人。");
      return;
    }
    if (this.Query.bank == null || this.Query.bank == '') {
      this.apiSev.itip("请输入承兑行全称。");
      return;
    }
    if (this.Query.bankNo == null || this.Query.bankNo == '') {
      this.apiSev.itip("请输入承兑行号。");
      return;
    }
    if (this.Query.code == null || this.Query.code == '') {
      this.apiSev.itip("请输入验证码。");
      return;
    } else if (this.Query.code.length != 4) {
      this.apiSev.itip("请输入正确的4位数验证码。");
      return;
    }
    if (Number(this.money)<Number(this.Query.payMoney)) {
      this.apiSev.itip("您的余额不足,请充值。");
      return;
    }

    this.apiSev.api("newpost", "inquiryreply/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.maskDiv = true;
        this.Confirm = true;
        this.initPage();
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');
    }, 6100, data);
    e.preventDefault();
  }


  getCode() {
    let data: any = {
      type: 'INQUIRY',
      mobile:this.mobile,
    };
    this.apiSev.api("newpost", "send/sms", (res) => {
      if (res.response == 'success') {
        this.apiSev.itip("已经发送至您的手机"+this.mobile);
        let second = 60;

        let timePromise = setInterval(() => {
          if (second <= 0) {
            clearInterval(timePromise);
            timePromise = undefined;
            second = 60;
            this.btnText = "获取验证码";
            this.isCodeDisabled = false;
          } else {
            second--;
            this.btnText = second + "秒后可重发";
            this.isCodeDisabled = true;
          }
        }, 1000, 100);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //查询查复界面
  ionViewDidEnter() {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.mobile=data.mobile;
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
        this.initPage();
      });
    });
    this.storage.get('ORGID').then((data) => {
      this.orgId = data;
    });

    this.maskDiv = false;
    this.Confirm = false;
    this.Query.startDate = new Date().toISOString();  //出票日期
    this.Query.endDate = new Date(new Date().getTime() + 180 * 24 * 3600 * 1000).toISOString();   //结束日期
    this.Query.min = new Date().toISOString();       //最小结束日期
    this.Query.draftNo = ''; //票号
    this.Query.money = '';   //金额
    this.Query.drawer = '';  //出票人
    this.Query.payee = '';   //收款人
    this.Query.bank = '';    //承兑行全称
    this.Query.bankNo = '';  //承兑行号
    this.Query.payMoney = 20; //付款金额
    this.Query.code = '';     //验证码

  }

  ToMyInquiryPage() {
    this.navCtrl.push(MyInquiryReplyPage, {
      ORG_TYPE: this.orgType,//机构 收票方
    });
  }

  ToTransactionPage(){
    this.navCtrl.push(TransactionPage,{
      ORG_TYPE: this.orgType,//机构 收票方
    });
  }

}
