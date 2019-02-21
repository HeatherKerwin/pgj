import { Component } from '@angular/core';
import { NavController, ViewController , NavParams , AlertController , ModalController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { successPage } from '../../pages/discount/success1';
import { DiscountOrgSearchPage } from '../../pages/discount/orgSearch';
import { TransactionPage } from '../../pages/account/transaction';
import { BankListPage } from '../authentication/banklist';
import { RedListPage } from '../account/redList';
import { VipaggregatePage } from "../account/vipaggregate";
import { OrderPageB } from '../order/orderB';
import {el} from "@angular/platform-browser/testing/src/browser_util";
import { SignatureifrmPage } from './signatureifrm';
import {NewBankListPage} from "../authentication/newbanklist";

@Component({
  selector: 'page-discountOrder',
  templateUrl: 'discountOrder.html'
})
export class DiscountOrderPage {
  public order:any={
    dcrdId:null,//当前贴现订单主键
    memberId:null,
    company:'',//公司名字
    bank:'',//开户行名称（银行）
    cardNum:'',//对公账户账号
    checkState:'',
    isDefault:'',
    isValid:'',
    createTime:'',
    updateTime:null,
    deposit:0,//保证金
    money:0,//押金余额
    appoint:0,//是否指定机构0-否
  };
  public moneyYes:boolean;
  public moneyNo:boolean;
  public redYesVIP:boolean;
  public redNoVIP:boolean;
  public JredYes:boolean;
  public redYes:boolean;
  public redNo:boolean;
  public isPay:boolean;
  public isMask:boolean;
  public isCash:boolean;
  public Totalamount:any='';  //实付总金额
  public rednumber='';    //红包个数
  public type1:any='';  //票据属性
  public redmoney:any='';   //红包金额
  public RedID:any='';    //红包ID
  public mobile = '';
  public addkeep = true;
  public cibkeep = false;
  public bankAcctAcctName:any=''; //账户名
  public bankAcctAcctNo:any=''; //账户
  public bankAcctBankBranch:any=''; //支行名称
  public vAcctAcctNo:any=''; //兴业虚拟账户
  public bindId:any='';   //京东金融ID
  public ismonybut:boolean=true;  //确认订单按钮判断
  public OnePrice:any=''; //一口价

  public mechanism:any={
    company:'',
    name:'',
    price:'',
    service:'',
    speed:'',
    id:''
  };
  public order_type:any;//区分商票银票

  public isOrg:boolean=true;//显示指定机构
  public isOrgshow:boolean=false;

  public jdData:any={};

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public alertCtrl: AlertController,
    public modalCtrl:ModalController
  ) {
    this.OnePrice = this.params.get("OnePrice");//当前贴现订单主键
    this.storage.get('userInfo').then((data)=>{
      this.order.memberId=data.id;
      this.OrgShow();
      this.loadData();//加载订单数据
      this.Toactive();
    });
    this.storage.get('ORGID').then((data)=>{
      this.order.orgId=data;
    });
    this.storage.get('userInfo').then((data)=>{
      this.mobile=data.mobile;
    })
  }

  ionViewDidEnter(){
    this.yajin();
    this.VIPloadData();
    //取卡银行值
    if (this.apiSev.parmData != null && this.apiSev.parmData != '') {
      let bankAcctAcctName = this.apiSev.parmData.bankAcctAcctName;
      if (bankAcctAcctName != null && bankAcctAcctName != '') {
        this.bankAcctAcctName = bankAcctAcctName;
        this.apiSev.parmData.bankAcctAcctName = '';
        this.addkeep = false;
        this.cibkeep = true;
      }
      if(this.bankAcctAcctName != null && this.bankAcctAcctName != ''){
        this.addkeep = false;
        this.cibkeep = true;
      }
      let bankAcctAcctNo = this.apiSev.parmData.bankAcctAcctNo;
      if (bankAcctAcctNo != null && bankAcctAcctNo != '') {
        this.bankAcctAcctNo = bankAcctAcctNo;
        this.apiSev.parmData.bankAcctAcctNo = '';
      }
      let bankAcctBankBranch = this.apiSev.parmData.bankAcctBankBranch;
      if (bankAcctBankBranch != null && bankAcctBankBranch != '') {
        this.bankAcctBankBranch = bankAcctBankBranch;
        this.apiSev.parmData.bankAcctBankBranch = '';
      }
      let vAcctAcctNo = this.apiSev.parmData.vAcctAcctNo;
      if (vAcctAcctNo != null && vAcctAcctNo != '') {
        this.vAcctAcctNo = vAcctAcctNo;
        this.apiSev.parmData.vAcctAcctNo = '';
      }
      let bindId = this.apiSev.parmData.bindId;
      if (bindId != null && bindId != '') {
        this.bindId = bindId;
        this.apiSev.parmData.bindId = '';
      }
      let RedID = this.apiSev.parmData.RedID;
      if (RedID != null && RedID != '') {
        this.RedID = RedID;
        this.apiSev.parmData.RedID = '';
        this.JredYes = true;
        this.redYes = false;
        this.redNo = false;
      }
      let RedMoney = this.apiSev.parmData.RedMoney;
      if (RedMoney != null && RedMoney != '') {
        this.redmoney = RedMoney;
        this.apiSev.parmData.RedMoney = '';
      }
    }
    if(this.RedID != null && this.RedID != ''){
      this.JredYes = true;
      this.redYes = false;
      this.redNo = false;
    }else {
      this.RedloadData();
    }
  }

  //加载订单数据
  loadData(){
    this.order_type=this.params.get("order_type");//区分商票银票
    this.order.dcrdId = this.params.get("DISCOUNTRECORDID");//当前贴现订单主键
    let data2:any={
      id: this.order.dcrdId
    }
    let get_url;
    if(this.order_type=='yp'){
      get_url='discountrecord/get';
    }else if(this.order_type=='sp'){
      get_url='discountrecordsp/get';
    }
    console.log(this.order_type+"====++");
    this.apiSev.api("newpost", get_url, (res) => {//获取订单信息
      let thisData=res.data;
      if (thisData.response == 'success') {
        let result = thisData.data;
        if(result.cib!=null&&result.cib!=''&&result.cib!='undefined'){
          this.order.company=result.cib.bankAcctAcctName;
          this.order.cardNum=result.cib.bankAcctAcctNo;
        }
        this.order.money=result.account.money;
        this.order.deposit=result.deposit;
      }
    },(error) => {console.log(JSON.stringify(error))}, 500,data2);
  }

  //选择票据属性
  radioBtn(data) {
    if (data == this.order.appoint) {
      if (this.order.appoint == 1) {
      } else {
      }
    }
  }

  //补充押金
  addDeposit(){
    this.navCtrl.push(TransactionPage,{
      ORG_TYPE:0
    });
  }

  //选择机构
  mechanismChose(){
    this.apiSev.parmData = {
      bankAcctAcctName:this.bankAcctAcctName,
      bankAcctAcctNo:this.bankAcctAcctNo,
      bankAcctBankBranch:this.bankAcctBankBranch,
      vAcctAcctNo:this.vAcctAcctNo,
      bindId:this.bindId,
      RedID:this.RedID,
      RedMoney:this.redmoney,
    };
    this.type1=this.params.get("type1");
    this.navCtrl.push(DiscountOrgSearchPage,{
      order_type:this.order_type,//区分商票银票
      type1:this.type1,
      ORDER:this.order,
    });
  }
  OrgShow(){
    this.type1=this.params.get("type1");
    let COMPANY=this.params.get("COMPANY");
    let NAME=this.params.get("NAME");
    let PRICE=this.params.get("PRICE");
    let SERVICE=this.params.get("SERVICE");
    let SPEED=this.params.get("SPEED");
    let ID=this.params.get("ID");
    let ORGID=this.params.get("ORGID");
    let ORDER=this.params.get("ORDER");
    if (ORDER!='' && ORDER!= undefined){
      this.order=ORDER;
    }
    if (ID!=''&&ID!=undefined){
      this.order.appoint=1;
      this.isOrg=false;//显示指定机构
      this.isOrgshow=true;
      this.mechanism.company=COMPANY;
      this.mechanism.name=NAME;
      this.mechanism.price=PRICE;
      this.mechanism.service=SERVICE;
      this.mechanism.speed=SPEED;
      this.mechanism.id=ID;
      this.mechanism.orgId=ORGID;
    }
  }

  // 生成订单
  save() {
    this.yajin();
    let data:any={
      id:this.order.dcrdId,//订单主键
    };
    if(this.type1=='2'){
      if((this.bindId!=null&&this.bindId!=''&&this.bindId!='undefined')||(this.vAcctAcctNo!=null&&this.vAcctAcctNo!=''&&this.vAcctAcctNo!='undefined')){

      }else{
        this.apiSev.itip('请选择出票银行卡！');
        return;
      }
      data.bindId=this.bindId;
      data.vAcctAcctNo=this.vAcctAcctNo;
    }
    if(this.order.appoint==1){
      data.selectOrgId= this.mechanism.orgId;//指定机构
    }
    if(this.RedID!=null&&this.RedID!=''){
      data.couponId=this.RedID;
    }
    if(this.OnePrice!=null&&this.OnePrice!=''&&this.OnePrice!='undefined'){
      let profileModal = this.modalCtrl.create(SignatureifrmPage,{
        DATA:data,
        OnePrice:this.OnePrice,
        ORDERTYPE:this.order_type,
        DISCOUNTRECORDID:this.order.dcrdId, //贴现订单主键
        bindId:this.bindId,
      });
      profileModal.present();
    }else {
    let order_url;
    if(this.order_type=='yp'){
      order_url='discountrecord/update';
    }else if(this.order_type=='sp'){
      order_url='discountrecordsp/update';
    }
      this.apiSev.api("newpost", order_url, (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.navCtrl.push(successPage);
        } else {
          this.apiSev.itip(data.msg);
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 6100, data);
    }
  }

  //银行卡选择
  chosePlace(){
    this.navCtrl.push(NewBankListPage,{
      ORG_TYPE: 0,
      DISCOUNTORDER: 1,
    });
  }

  //点击确认订单
  saveGet(){
    this.isCash = false;
    this.isMask = true;
    this.isPay = true;
    this.ismonybut = false;
    let data: any = {
      memberId: this.order.memberId
    };
    this.apiSev.api("newpost", "account/info/by/member", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.order.money = objs.data.account.money;
        if (Number(this.Totalamount) <= Number(this.order.money)) {
          this.moneyYes = true;
          this.moneyNo = false;
        } else {
          this.moneyYes = false;
          this.moneyNo = true;
        }
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }

  //交易完成后文字提醒
  cashBtn() {
      this.isCash = true;
      this.isMask = true;
      this.isPay = false;
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.isCash = false;
      this.isPay = false;
      this.ismonybut = true;
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

  //加载账户信息
  RedloadData(){
    let data:any={
      memberId:this.order.memberId,
      couponState:'UNUSED'
    };
    this.apiSev.api("newpost", "coupon/page", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.list!=''&&data.data.list!=null){
          let objs = data.data.list;
          if(objs.length>0){
            this.rednumber=objs.length;
            this.redYes = true;
            this.redNo = false;
            this.JredYes = false
          }else {
            this.redYes = false;
            this.redNo = true;
            this.JredYes = false
          }
        }else {
          this.redYes = false;
          this.redNo = true;
          this.JredYes = false
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }
  //获取用户购买的会员
  VIPloadData(){
    let data:any={
      memberId:this.order.memberId,
      vipType:0
    };
    this.apiSev.api("newpost", "vipmember/get/by/memberid", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.vipMember != null && data.data.vipMember != '') {
          this.redYesVIP = true;
          this.redNoVIP = false;
          this.Totalamount=Number((data.data.fee*1+this.order.deposit*1)-20);
        } else {
          this.redYesVIP = false;
          this.redNoVIP = true;
          this.Totalamount=Number((data.data.fee*1+this.order.deposit*1)-this.redmoney*1);
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }

  redlist(){
    this.navCtrl.push(RedListPage,{
      RedType:0
    });
  }
  //会员中心
  vipgate(){
    this.navCtrl.push(VipaggregatePage,{
      ORG_TYPE:0  //企业 出票方
    });
  }

  //押金
  yajin(){
    let data:any={
      memberId:this.order.memberId
    };
    this.apiSev.api("newpost", "account/info/by/member", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.order.money=objs.data.account.money;
        if (Number(this.Totalamount) <= Number(this.order.money)) {
          this.moneyYes = true;
          this.moneyNo = false;
        } else {
          this.moneyYes = false;
          this.moneyNo = true;
        }
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //取消按钮
  cancelPage(){
    this.order_type=this.params.get("order_type");//区分商票银票
    this.order.dcrdId = this.params.get("DISCOUNTRECORDID");//当前贴现订单主键
    let data={
      id:this.order.dcrdId,
      orderflag:1,
      cancel:4,
      cancelCause:''
    };

    let get_url;
    if(this.order_type=='yp'){
      get_url='discountrecord/cancel/unconfirm';
    }else if(this.order_type=='sp'){
      get_url='discountrecordsp/cancel/unconfirm';
    }
    this.apiSev.api("newpost", get_url , (res) => {

      if(res.data.response == 'success') {
        this.apiSev.itip("已成功申请取消订单");
        this.navCtrl.setRoot(OrderPageB);
      }
    }, (error) => {},6100,data);
  }


  //开户判断
  Toactive() {
    let data:any={
      memberId:this.order.memberId,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.cib!=null&&data.data.cib!=''&&data.data.cib!='undefined'){
          //是否兴业开户
          if(data.data.cib.status==2&&data.data.cib.cib_check_state=='PASS'){
            this.apiSev.cibrz=1;
          }else {
            //兴业绑定开户判断
            this.apiSev.cibrz=0;
          }
        }
        if(data.data.jdjr!=null&&data.data.jdjr!=''&&data.data.jdjr!='undefined'){
          this.jdData=data.data.jdjr;
          //是否开京东户
          if(this.jdData.status==2&&this.jdData.check_state=='PASS'){
            this.apiSev.jdrz=1;
          }else {
            //京东绑定开户判断
            this.apiSev.jdrz=0;
          }
        } else {
        }
        //未开户-去往开户页面
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }
}
