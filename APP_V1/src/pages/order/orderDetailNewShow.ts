import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderPage } from './order';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { OrderEditPage } from './orderEdit';

@Component({
  selector: 'page-orderDetailNewShow',
  templateUrl: 'orderDetailNewShow.html'
})
export class OrderDetailNewShowPage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;

  public item:any = {};
  public itemPinfen:any = {};
  public itemNew:any={};

  public maskDiv=false; //弹出层

  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public info = "0";
  public isCancel=false;
  public isConfirm=false;
  public chgemoney = 0;
  public exmoney = "0";
  public isMemo=false;
  public ORGID="";
  public memo='';
  public Newmonys:any='';

  public swjx:any=0;

  constructor(public storage: Storage,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      this.item=this.params.get('item');
      this.zhanshije();
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

  zhanshije(){
    this.item=this.params.get('item');
    this.Newmonys=this.item.allmoney*10000;
    if(this.Newmonys!=null&&this.Newmonys!=''&&this.Newmonys!='undefined'){
      let datas:any={
        money:this.Newmonys,
        txje:this.item.txje*10000,
        jxts:this.item.jxts
      };
      this.apiSev.api("newpost", "dispatch/fee", (res) => {
        let datas = res.data;
        if (datas.response == 'success') {
          let discounttxty = datas.data.fee;
          if (this.item.type1 == '2') {
            this.item.Newmony=this.item.txje*1+(discounttxty*0.0001);
          }else {
            this.item.Newmony=this.item.txje*1
          }
          if(datas.data.price2!=null&&datas.data.price2!=''&&datas.data.price2!='undefined'){
            this.swjx=datas.data.price2;
          }
        } else {
          this.apiSev.itip(datas.msg);
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, datas);

    }
  }

  Chage(){
    let z= (this.item.allmoney*10000 - this.chgemoney*1)/10000 ;
	this.exmoney=z.toFixed(2);

  }

  Chages(){
    if (this.info=="4")
      this.isMemo=true;
    else
      this.isMemo=false;
  }

  showCancel(){
	this.isConfirm=false;
	this.isCancel=true;
    this.maskDiv = true;
  }

/*  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
  }*/

/*  showConfirm(){
	this.isConfirm=true;
	this.isCancel=false;
    this.maskDiv = true;
  }*/
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
/*  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }*/
  okMask(){
    this.maskDiv = false;
  }
  ionViewDidEnter(){

	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];

	this.storage.get('userInfo').then((res)=>{
		this.ORGID=res.orgId;
		this.memberId=res.id;
		this.initData();

	});
  }

  orderEdit(){
    this.navCtrl.push(OrderEditPage,{
      item:this.item,
    })
  }


  close(e){
    this.navCtrl.setRoot(OrderPage,{sel:2});
    e.preventDefault();
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


  }


  getDurg (test) {
    //artime.push(timePromise);
  }

/*  //取消订单
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
        this.apiSev.itip("已成功取消订单");
        this.navCtrl.setRoot(OrderPage,{sel:2});
      }
    }, (error) => {},6100,data);
    //}
    this.maskDiv = false;
  }*/
}
