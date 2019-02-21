import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,ModalController,AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderDakuan1Page } from '../order/orderDakuan1';
import { OrderPage } from '../order/order';
import { SignaturePage } from '../order/signature';
import { PhotoViewer } from '@ionic-native/photo-viewer';
@Component({
  selector: 'page-orderDakuan',
  templateUrl: 'orderDakuan.html'
})
export class OrderDakuanPage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;
  public memberophone :'';

  public item:any = {};
  public itemPinfen:any = {};
  public itemComapny:any = {};

  public maskDiv=false; //弹出层

  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public info = "0";
  public memo = "";
  public isCancel=false;
  public isConfirm=false;
  public chgemoney = 0;
  public exmoney = "0";
  public isMemo=false;
  public isShow=true;
  public signature='';
  public discountpay:any;
  public Actual:any;

  public swjx:any=0;

  constructor(public storage: Storage,public navCtrl: NavController,public alertCtrl: AlertController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService, public modalCtrl:ModalController) {
      this.item=this.params.get('item');
	   this.storage.get('userInfo').then((res)=>{
		this.memberId=res.id;
	   });
	   this.ALLPay();
  }


  //贴现总金额
  ALLPay(){
    this.discountpay=this.item.txje*10000+this.item.txlx*1;
    let data:any={
      money:this.discountpay,
      txje:this.item.txje*10000,
      jxts:this.item.jxts
    };
    this.apiSev.api("newpost", "dispatch/fee", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let discounttxty = data.data.fee;
        if (this.item.type1 == '2') {
          this.Actual = (this.item.txje*10000+discounttxty*1).toFixed(2);
        }else if (this.item.type1 == '1'){
          this.Actual = (this.item.txje*10000).toFixed(2);
        }
        if(data.data.price2!=null&&data.data.price2!=''&&data.data.price2!='undefined'){
          this.swjx=data.data.price2;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
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
    if(this.maskDiv){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }

  bigpic(){
	  if (this.item.picpath!=null) {
      if(this.item.type1=='2'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'票号：'+this.item.draft_no+' | 备注：'+this.item.memberother, {share:false});
      }else if(this.item.type1=='1'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'备注：'+this.item.memberother, {share:false});
      }
	  }else{
		  this.apiSev.itip('未上传票面');
	  }
  }

  Save(){
    //是否签章识别,running已签章。
    if(this.item.type1=='2'){
      if (this.apiSev.parmData != null && this.apiSev.parmData != '') {
        let signature = this.apiSev.parmData.signature;
        this.signature = signature;
        if(this.signature=='running'||(this.item.advance_time!=null&&this.item.advance_time!=''&&this.item.advance_time!='undefined')){
          this.isShow = false;
          let data = {
            id: this.item.dist_id,
            state: this.item.state
          };

          let urlsp = "distributeorder/update/advancetime";
          if (this.item.order_type == 'DISCOUNTRECORDSP')
            urlsp = "distributeordersp/update/advancetime";

          this.apiSev.api("newpost", urlsp, (res) => {
            if (res.data.response == 'success') {
              this.isShow = true;
              this.item.advance_time = '2017-1-1';
              if (this.item.type1 == '2') {
                if (res.data.data.biz_order.status == 12) {
                  this.apiSev.itip("订单打款成功");
                  this.apiSev.parmData.signature = '';
                  this.navCtrl.pop();
                } else {
                  //执剑人账户余额不足，请到电脑端的票据管家官网www.utiexian.com访问订单并付款
                  let alert = this.alertCtrl.create({
                    title: '提示',
                    subTitle: '执剑人账户余额不足，请到电脑端的票据管家官网www.utiexian.com访问订单并付款',
                    buttons: [
                      {
                        text: '确定',
                        handler: data => {
                        }
                      },
                    ]
                  });
                  alert.present();
                }
                /*else if (res.data.data.biz_order.status == 11) {
                      let orderId = this.item.dist_id;
                      let orderType = this.item.order_type;
                      this.isShow=true;
                      this.navCtrl.push(Iframediv,{orderId:orderId,orderType:orderType});
                  } else {
                    this.isShow=true;
                    this.apiSev.itip("订单出错,请联系客服");
                  }*/
                if(res.data.data.return_msg!=null&&res.data.data.return_msg!='') {
                  this.apiSev.itip(res.data.data.return_msg);
                  return;
                }
                if(res.data.data.error_msg!=null&&res.data.data.error_msg!='') {
                  this.apiSev.itip(res.data.data.error_msg);
                  return;
                }
              }
              else {
                this.apiSev.itip("订单确认打款");
                this.navCtrl.pop();
              }
            } else {
              this.isShow = true;
              this.apiSev.itip("订单出错,请联系客服"+res.data.msg);
            }
          }, (error) => {
            this.isShow = true;
          }, 6100, data);
        }else {
          let profileModal = this.modalCtrl.create(SignaturePage,{
            item:this.item
          });
          profileModal.present();
        }
      }
    }else if(this.item.type1=='1'){
      this.isShow = false;
      let data = {
        id: this.item.dist_id,
        state: this.item.state
      };

      let urlsp = "distributeorder/update/advancetime";
      if (this.item.order_type == 'DISCOUNTRECORDSP')
        urlsp = "distributeordersp/update/advancetime";

      this.apiSev.api("newpost", urlsp, (res) => {
        if (res.data.response == 'success') {

          this.item.advance_time = '2017-1-1';
          if (this.item.type1 == '2') {
            if (res.data.data.biz_order.status == 12) {
              this.apiSev.itip("订单打款成功");
              this.apiSev.parmData.signature = '';
              this.navCtrl.pop();
            } else {
              //执剑人账户余额不足，请到电脑端的票据管家官网www.utiexian.com访问订单并付款
              let alert = this.alertCtrl.create({
                title: '提示',
                subTitle: '执剑人账户余额不足，请到电脑端的票据管家官网www.utiexian.com访问订单并付款',
                buttons: [
                  {
                    text: '确定',
                    handler: data => {
                    }
                  },
                ]
              });
              alert.present();
            }
            /*else if (res.data.data.biz_order.status == 11) {

                  let orderId = this.item.dist_id;
                  let orderType = this.item.order_type;
                  this.isShow=true;
                  this.navCtrl.push(Iframediv,{orderId:orderId,orderType:orderType});


              } else {
                this.isShow=true;
                this.apiSev.itip("订单出错,请联系客服");
              }*/
          }
          else {
            this.apiSev.itip("订单确认打款");
            this.navCtrl.pop();
          }
        } else {
          this.isShow = true;
          this.apiSev.itip("订单出错,请联系客服。"+res.data.msg);
        }
      }, (error) => {
        this.isShow = true;
      }, 6100, data);
    }
  }
  Help(){
    let profileModal = this.modalCtrl.create(OrderDakuan1Page);
    profileModal.present();
  }
  Cancel(){
	  this.maskDiv = true;
  }
  CancelMask(){
		let data: any={

		};
    if (this.item.cib_cancel != null && this.item.cib_cancel != '') {
      if (this.item.cib_cancel.id != null && this.item.cib_cancel.id != '') {
        data.id = this.item.cib_cancel.id;
      }else {
        data.memberId = this.memberId;
        data.orderId = this.item.id;
        data.orderType = this.item.order_type;
        data.type = 1;
        data.cancel = this.info;
        data.cancelCause = this.memo
      }
    } else {
      data.memberId = this.memberId;
      data.orderId = this.item.id;
      data.orderType = this.item.order_type;
      data.type = 1;
      data.cancel = this.info;
      data.cancelCause = this.memo
    }
		let objs=[];

		this.apiSev.api("newpost", "cibcancel/save", (res) => {
		  if(res.data.response == 'success') {
			this.apiSev.itip("已成功申请取消订单");
			this.navCtrl.setRoot(OrderPage,{sel:2});
		  }
		}, (error) => {},6100,data);
    this.maskDiv = false;
  }

  Chage(){
    if (this.info=="4")
	  this.isMemo=true;
    else
	  this.isMemo=false;
  }


  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }
  okMask(){
    this.maskDiv = false;
  }
  ionViewDidEnter(){

	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];

	this.storage.get('userInfo').then((res)=>{

		this.memberId=res.id;

		this.initData();

	})

  }

  Show(){
/*    let data = {
		memberId:this.memberId,
		type:1
	}
    this.apiSev.api("newpost", "cib/corp/query", (res) => {
      this.navCtrl.push(Iframe,{url:res.data.data.login_url});
    }, (error) => {},2000,data);*/
    let alert = this.alertCtrl.create({
      title: '提示',
      subTitle: '请到电脑端的票据管家官网www.utiexian.com访问订单并付款',
      buttons: [
        {
          text: '确定',
          handler: data => {
          }
        },
      ]
    });
    alert.present();
  }

  close(){

  }

  initData() {

	let data={
		role:1,
		memberId:this.item.bns_member_id
	}

    this.apiSev.api("newpost", "comments/get", (res) => {
      if(res.data.response == 'success') {
        this.itemPinfen=res.data.data;
        let min = Math.floor(this.itemPinfen.endorseTime / 60);//计算整数分
        let afterMin = this.itemPinfen.endorseTime - min * 60;//取得算出分后剩余的秒数
        this.itemPinfen.endorseTime = this.apiSev.PrefixInteger(min, 2) + ':' + this.apiSev.PrefixInteger(afterMin, 2);
      }
    }, (error) => {},2000,data);

	//获得出票企业信息
/*	let data1={
		memberId:this.item.bns_member_id,
		type:0
	}

    this.apiSev.api("newpost", "orginfo/get", (res) => {
      if(res.data.response == 'success') {
        this.itemComapny=res.data.data.orgInfo;
        this.memberophone=res.data.data.member.mobile;
      }
    }, (error) => {},2000,data1);*/
    let url:any;
    let data1={
      id:this.item.dist_id,
    };
    if(this.item.order_type=="DISCOUNTRECORD"){
      url='distributeorder/get/details';
    }else {
      url='distributeordersp/get/details';
    }
    this.apiSev.api("newpost", url, (res) => {
      if(res.data.response == 'success') {
        this.itemComapny=res.data.data;
        this.memberophone=res.data.data.member.mobile;
        if(res.data.data.orgInfo!=null&&res.data.data.orgInfo!=''&&res.data.data.orgInfo!='undefined'){
          this.memberophone=res.data.data.orgInfo.phone;
          this.itemComapny=res.data.data.orgInfo;
        }
      }
    }, (error) => {},500,data1);

  }



  getDurg (test) {
    //artime.push(timePromise);
  }


}
