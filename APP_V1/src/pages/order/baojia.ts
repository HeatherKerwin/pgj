import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderDetailNewShowPage } from '../order/orderDetailNewShow';
import { TransactionPage } from '../account/transaction';
import { RedListPage } from '../account/redList';
import { VipaggregatePage } from "../account/vipaggregate";
import { OrderPage } from "./order";
import {BankListPage} from "../authentication/banklist";
import {NewBankListPage} from "../authentication/newbanklist";

@Component({
  selector: 'page-baojia',
  templateUrl: 'baojia.html'
})
export class baojiaPage {
  public user:any = {};
  public btnText = '获取验证码';

  public item:any = {};
  public itemPinfen:any = {};

  public isMask:boolean;//弹出层
  public isOffer:boolean;//确认报价

  public isCodeDisabled:boolean;

  public moneyYes:boolean;
  public moneyNo:boolean;
  public redYesVIP:boolean;
  public redNoVIP:boolean;
  public JredYes:boolean;
  public redYes:boolean;
  public redNo:boolean;
  public isPay:boolean;
  public isCash:boolean;
  public Totalamount=0;  //实付总金额
  public rednumber='';    //红包个数
  public type1:any='';  //票据属性
  public redmoney:any='';   //红包金额
  public RedID:any='';    //红包ID
  public OnePrice:any=''; //一口价

  public todoor_price='';
  public code='';
  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public mobile = "";
  public orgId = "";
  public info = false;
  public isCancel=false;
  public isConfirm=false;
  public isMemo=false;
  public ismonybut:boolean=true;  //确认报价按钮判断
  public TradingMarket='';

  public addkeep = true;
  public cibkeep = false;
  public bankAcctAcctName:any=''; //账户名
  public bankAcctAcctNo:any=''; //账户
  public bankAcctBankBranch:any=''; //支行名称
  public vAcctAcctNo:any=''; //兴业虚拟账户
  public bindId:any='';   //京东金融ID

  public Choice:any;  //1为兴业0为京东


  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService,public modalCtrl:ModalController) {
    //window.clearInterval(mapPanel.drawTick);
    this.item = this.params.get('itemPinfen');
    this.TradingMarket=this.params.get('TradingMarket');  //是否是交易市场
    this.OnePrice=this.params.get('OnePrice');  //来自于一口价
    this.Choice=this.params.get('Choice');
    console.log(this.item);
    console.log(this.Choice);


    //this.exmoney=this.item.allmoney;
	  this.storage.get('ORGID').then((res)=>{
		this.orgId=res;
	  });
    this.storage.get('userInfo').then((res)=> {
      this.memberId = res.id;
      this.dataLoad();
    })
  }

  //实际支付总金额调用

  ionViewDidEnter(){
    //取卡银行值
    if (this.apiSev.parmData != null && this.apiSev.parmData != '') {
      let bankAcctAcctName = this.apiSev.parmData.bankAcctAcctName;
      if (bankAcctAcctName != null && bankAcctAcctName != '') {
        this.bankAcctAcctName = bankAcctAcctName;
        this.apiSev.parmData.bankAcctAcctName = '';
        this.addkeep = false;
        this.cibkeep = true;
      }
      if (this.bankAcctAcctName != null && this.bankAcctAcctName != '') {
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
    }



    if (this.apiSev.parmData != null && this.apiSev.parmData != '') {
      let RedID = this.apiSev.parmData.RedID;
      if (RedID != null && RedID != '') {
        this.RedID = RedID;
        console.log("ID" + this.RedID);
        this.apiSev.parmData.RedID = '';
        this.JredYes = true;
        this.redYes = false;
        this.redNo = false;
      }
      let RedMoney = this.apiSev.parmData.RedMoney;
      if (RedMoney != null && RedMoney != '') {
        this.redmoney = RedMoney;
        console.log("金额" + this.redmoney);
        this.apiSev.parmData.RedMoney = '';
      }
    }
    if (this.RedID != null && this.RedID != '') {
      this.JredYes = true;
      this.redYes = false;
      this.redNo = false;
    } else {
      this.RedloadData();
    }
    this.yajin();

    for (let i=0;i<this.ListsTime1.length;i++){
      clearInterval(this.ListsTime1[i]);
    }
    this.ListsTime1 = [];


    this.storage.get('userInfo').then((res)=>{

      this.memberId=res.id;
      this.mobile=res.mobile;

      if(this.item.create_time!=null||this.item.create_time!=''&&this.item.create_time!=undefined) {
        let date1 = new Date();//'2017-9-26 15:36:00'
        console.log("this.item.create_time======" + this.item.create_time);
        let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
        date2.setMinutes(date2.getMinutes() + 105, date2.getSeconds(), 0);
        let s1 = date1.getTime(), s2 = date2.getTime();
        var afterHour = Math.floor((s2 - s1) / 1000);
        var hour = Math.floor(afterHour / 3600);
        var min = Math.floor((afterHour - hour * 3600) / 60);//计算整数分
        var afterMin = afterHour - min * 60;//取得算出分后剩余的秒数
        if (afterHour < 1) {
          this.info = true;
          this.item.difDate = '无'
        } else {
          this.item.difDate = this.apiSev.PrefixInteger(hour, 1) + ':' + this.apiSev.PrefixInteger(min, 2) + ':' + this.apiSev.PrefixInteger(afterMin, 2)
        }
      }
      this.initData();

    });
  }


  initData() {


    setTimeout(() => {

      if(this.TradingMarket!='true') {
        if (this.item.create_time != null && this.item.create_time != null && this.item.create_time != undefined) {
          let date1 = new Date();
          let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
          date2.setMinutes(date2.getMinutes() + 105, date2.getSeconds(), 0);
          let s1 = date1.getTime(), s2 = date2.getTime();
          var afterHour = Math.floor((s2 - s1) / 1000);
          document.getElementById("hptdid").innerHTML = afterHour.toString();
          let timePromise3 = setInterval(() => {
            if (document.getElementById("hptdid") != null) {
              var minjj = parseInt(document.getElementById("hptdid").innerHTML) - 1;
              document.getElementById("hptdid").innerHTML = minjj.toString();
              if (minjj <= 0) {
                clearInterval(timePromise3);
                timePromise3 = undefined;
                this.info = true;
                document.getElementById("ptid").innerHTML = '无';
              } else {
                var hour = Math.floor(minjj / 3600);
                var min = Math.floor((minjj - hour * 3600) / 60);//计算整数分
                var afterMin = minjj - min * 60;//取得算出分后剩余的秒数

                document.getElementById("ptid").innerHTML = this.apiSev.PrefixInteger(hour, 1) + ':' + this.apiSev.PrefixInteger(min, 2) + ':' + this.apiSev.PrefixInteger(afterMin, 2);//second+"";
              }
            }
            else {
              clearInterval(timePromise3);
              timePromise3 = undefined;
            }

          }, 1000, 1000);
          this.ListsTime1.push(timePromise3);
        }
      }
    }, 100);


    let data={
      memberId:this.memberId,
      allmoney:this.item.allmoney,
      order_type:this.item.order_type
    };
    this.apiSev.api("newpost", "dispatch/get/deposit", (res) => {
      if(res.data.response == 'success') {
        this.itemPinfen=res.data.data;
        this.VIPloadData();
      }
    }, (error) => {},2000,data);

  }

  /*确认报价提示*/
  SaveAlert() {
    if (((this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&(this.item.disc_v_acct_acct_no==''||this.item.disc_v_acct_acct_no==null||this.item.disc_v_acct_acct_no=='undefined'))||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.item.disc_v_acct_acct_no!=''&&this.item.disc_v_acct_acct_no!=null&&this.item.disc_v_acct_acct_no!='undefined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.Choice==0)||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==0))&&(this.bankAcctAcctName == null || this.bankAcctAcctName == '' || this.bankAcctAcctName=='undefined')&&this.item.type1==2) {
      this.apiSev.itip("请选择银行卡");
      return;
    }


    if (this.todoor_price == "" && this.item.type1 == '1' && this.item.need_todoor == '1') {
      this.apiSev.itip("请输入上门费用");
      return;
    }
    this.isCash = false;
    this.isMask = true;
    this.isPay = true;
    this.ismonybut=false;

    let data: any = {
      memberId: this.memberId
    };
    this.apiSev.api("newpost", "account/info/by/member", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.itemPinfen.money = objs.data.account.money;
        if (Number(this.Totalamount) <= Number(this.itemPinfen.money)) {
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

  /*确认报价*/
  Save(){
    if(this.OnePrice=='true'){
      this.buyoutprice();
    }else {
      let data:any={

      };
      if(this.RedID!=null&&this.RedID!=''){
        data.couponId=this.RedID;
      }

      let url= "" ;
      if (this.TradingMarket == 'true') {
        data.orderId = this.item.id;
        data.orderType = this.item.order_type;
        data.orgId = this.orgId;
        data.txje = this.item.txje;
        data.txlx = this.item.txlx;
        data.todoor_price = this.todoor_price;
        url = "dispatch/save/disp/dist/price";
      } else {
        data.memberId = this.memberId;
        data.id = this.item.did;
        data.order_type = this.item.order_type;
        data.orderId = this.item.id;
        data.jxts = this.item.jxts;
        data.orgId = this.orgId;
        data.txje = this.item.txje;
        data.txlx = this.item.txlx;
        data.deposit = this.itemPinfen.deposit;
        data.todoor_price = this.todoor_price;
        data.is_designated = this.item.is_designated;
        url = "dispatch/save/dist/price";
      }
      if(((this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&(this.item.disc_v_acct_acct_no==''||this.item.disc_v_acct_acct_no==null||this.item.disc_v_acct_acct_no=='undefined'))||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.item.disc_v_acct_acct_no!=''&&this.item.disc_v_acct_acct_no!=null&&this.item.disc_v_acct_acct_no!='undefined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.Choice==0)||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==0))){
        data.bindId=this.bindId;
      }

      this.apiSev.api("newpost", url, (res) => {
        let data = res.data;
        if(res.data.response == 'success') {
          this.item.todoor_price=this.todoor_price;
          this.navCtrl.push(OrderDetailNewShowPage,{item:this.item});
        }else {
          this.apiSev.itip(data.msg);
          this.isMask = false;
          this.isCash = false;
          this.isPay = false;
          return;
        }
      }, (error) => {},6100,data);
    }
  }

  /*确认报价*/
  buyoutprice(){
    let data:any={
      orderId:this.item.id,
      orderType:this.item.order_type,
      orgId:this.orgId,
    };
    if(((this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&(this.item.disc_v_acct_acct_no==''||this.item.disc_v_acct_acct_no==null||this.item.disc_v_acct_acct_no=='undefined'))||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.item.disc_v_acct_acct_no!=''&&this.item.disc_v_acct_acct_no!=null&&this.item.disc_v_acct_acct_no!='undefined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.Choice==0)||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==0))){
      data.bindId=this.bindId;
    }
    if(this.RedID!=null&&this.RedID!=''){
      data.couponId=this.RedID;
    }

    this.apiSev.api("newpost", 'dispatch/save/buyoutprice', (res) => {
      let data = res.data;
      if(res.data.response == 'success') {
        this.item.todoor_price=this.todoor_price;
        this.navCtrl.setRoot(OrderPage,{sel:2});
      }else {
        this.apiSev.itip(data.msg);
        this.isMask = false;
        this.isCash = false;
        this.isPay = false;
        return;
      }
    }, (error) => {},6100,data);


  }


  close () {
    this.viewCtrl.dismiss();
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
      memberId:this.memberId,
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
      memberId:this.memberId,
      vipType:1
    };
    this.apiSev.api("newpost", "vipmember/get/by/memberid", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.vipMember != null && data.data.vipMember != '') {
          this.redYesVIP = true;
          this.redNoVIP = false;
          if(this.item.type1==2){
            if(((this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&(this.item.disc_v_acct_acct_no==''||this.item.disc_v_acct_acct_no==null||this.item.disc_v_acct_acct_no=='undefined'))||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.item.disc_v_acct_acct_no!=''&&this.item.disc_v_acct_acct_no!=null&&this.item.disc_v_acct_acct_no!='undefined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.Choice==0)||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==0))){
              this.Totalamount=Number((data.data.fee*1+this.itemPinfen.deposit*1)-20);
            }else {
              this.Totalamount=Number((data.data.fee*1+this.itemPinfen.deposit*1+5)-20);
            }
          }else if(this.item.type1==1){
            this.Totalamount=Number((data.data.fee*1+this.itemPinfen.deposit*1)-20);
          }
        } else {
          this.redYesVIP = false;
          this.redNoVIP = true;
          if(this.item.type1==2){
            if(((this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&(this.item.disc_v_acct_acct_no==''||this.item.disc_v_acct_acct_no==null||this.item.disc_v_acct_acct_no=='undefined'))||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.item.disc_v_acct_acct_no!=''&&this.item.disc_v_acct_acct_no!=null&&this.item.disc_v_acct_acct_no!='undefined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.Choice==0)||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==0))){
              this.Totalamount=Number((data.data.fee*1+this.itemPinfen.deposit*1)-this.redmoney*1);
            }else {
              this.Totalamount=Number((data.data.fee*1+this.itemPinfen.deposit*1+5)-this.redmoney*1);
            }
          }else if(this.item.type1==1){
            this.Totalamount=Number((data.data.fee*1+this.itemPinfen.deposit*1)-this.redmoney*1);
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6500, data);
  }

  redlist(){
    this.navCtrl.push(RedListPage,{
      RedType:1
    });
  }
  //会员中心
  vipgate(){
    this.navCtrl.push(VipaggregatePage,{
      ORG_TYPE:1  //企业 出票方
    });
  }

  //交易完成后文字提醒
  cashBtn() {
    this.isCash = true;
    this.isMask = true;
    this.isPay = false;
  }

  //补充押金
  addDeposit(){
    this.navCtrl.push(TransactionPage,{
      ORG_TYPE:1
    });
  }

  //押金
  yajin(){
    let data:any={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "account/info/by/member", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.itemPinfen.money=objs.data.account.money;
        if (Number(this.Totalamount) <= Number(this.itemPinfen.money)) {
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


  //银行卡选择
  chosePlace(){
    this.navCtrl.push(NewBankListPage,{
      ORG_TYPE: 1,
      DISCOUNTORDER: 1,
      DD:this.item
    });
  }


  dataLoad() {
    let data: any = {
      memberId: this.memberId,
      type:1,
    };
    this.apiSev.api("newpost", "jdjr/cib/account/card", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data;
        if (objs.length == 1) {
          if(((this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&(this.item.disc_v_acct_acct_no==''||this.item.disc_v_acct_acct_no==null||this.item.disc_v_acct_acct_no=='undefined'))||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.item.disc_v_acct_acct_no!=''&&this.item.disc_v_acct_acct_no!=null&&this.item.disc_v_acct_acct_no!='undefined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.Choice==0)||(this.item.disc_bind_id!=null&&this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undifined'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==0))&&this.item.disc_bind_id!=null&&objs[0].bindId != null && objs[0].bindId != ''){
            if (objs[0].accountNo != null && objs[0].accountNo != '') {
              this.bankAcctBankBranch = objs[0].accountNo;
            }
            if (objs[0].bindId != null && objs[0].bindId != '') {
              this.bindId = objs[0].bindId;
            }
            if (objs[0].bankName != null && objs[0].bankName != '') {
              this.bankAcctAcctName = objs[0].bankName;
            }
            if (objs[0].bankCnaps != null && objs[0].bankCnaps != '') {
              this.bankAcctAcctNo = objs[0].bankCnaps;
            }
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

}
