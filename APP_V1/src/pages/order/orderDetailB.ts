import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,AlertController,ModalController  } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderPageB } from '../order/orderB';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import {DiscountOrderPage} from "../discount/discountOrder";
import { SignatureifrmPage } from '../discount/signatureifrm';

@Component({
  selector: 'page-orderDetailB',
  templateUrl: 'orderDetailB.html'
})
export class OrderDetailPageB {
  public user:any = {};
  public orderflag = '';
  public isCodeDisabled = true;

  public item:any = {};
  public item2:any = {};
  public maskDiv=false; //弹出层

  public ListsTime1 = [];
  public Lists1 = [];
  public isSelect='';
  public ORGID = "";
  public info = "0";
  public isCancel=false;
  public isConfirm=false;
  public memo = "";
  public endtime = "";
  public isMemo=false;
  public overtime=0;

  public data:any={};

  constructor( public phCtrl: PhotoViewer, public storage: Storage,public alertCtrl: AlertController,public modalCtrl:ModalController,public navCtrl: NavController, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      this.item=this.params.get('item');
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

  Chage(){
	if (this.info=="4")
	  this.isMemo=true;
    else
	  this.isMemo=false;
  }

  showCancel(e){
    e.preventDefault();
	this.isConfirm=false;
	this.isCancel=true;
    this.maskDiv = true;

  }

  showRe(e){

    let confirm = this.alertCtrl.create({
      title: '重新派单',
      message: '确认把这个票据重新推送给收票机构吗?',
      buttons: [
        {
          text: '否',
          handler: () => {
          }
        },
        {
          text: '是',
          handler: () => {
			let data={
				id:this.item.id
			}
			let objs=[];
			let order_type='yp';
			let url = "discountrecord/activation" ;
			if (this.item.order_type=='DISCOUNTRECORDSP')
			{
				url="discountrecordsp/activation";
				order_type='sp';
			}
			this.apiSev.api("newpost", url , (res) => {
			  if(res.data.response == 'success') {

			    let state;
				if (res.data.data.depositState!=null) state=res.data.data.depositState;
			    if (state==0)
				{
					//去往订单确认页，注意传参
				    this.navCtrl.push(DiscountOrderPage,
					{
					  order_type:order_type,
            type1:this.item.type1,
					  DISCOUNTRECORDID:res.data.data.id,
				    });
				}
				else{
					this.apiSev.itip("已成功重新派单");
					this.navCtrl.pop();
				}
			  }else {
			    this.apiSev.itip(res.data.msg);
        }
			}, (error) => {},6100,data);
          }
        }
      ]
    });
    confirm.present();
    e.preventDefault();
  }

  showConfirm(){
	this.isConfirm=true;
	this.isCancel=false;
    this.maskDiv = true;
  }

  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }

  okMask(){
    if(this.item.type1=='2') {
      this.navCtrl.push(SignatureifrmPage, {
        item: this.item,
        orderflag: this.orderflag,
        selectDistId: this.item2.distId,
        txlx:this.item2.txlx,
        DATA:this.data
      });
    }else if(this.item.type1=='1') {
      let data={
        id:this.item.id,
        orderflag:this.orderflag ,
        selectDistId:this.item2.distId,
        txlx:this.item2.txlx
      };
      let url="discountrecord/update/select";
      if (this.item.order_type=='DISCOUNTRECORDSP')
        url="discountrecordsp/update/select";
      this.apiSev.api("newpost", url, (res) => {

        if(res.data.response == 'success') {
          this.apiSev.itip("已成功选定报价");
          this.navCtrl.setRoot(OrderPageB);
        }
      }, (error) => {},6100,data);
    }
    this.maskDiv = false;
  }

  CancelMask(){
  	let data={
		id:this.item.id,
		orderflag:this.orderflag ,
		cancel:this.info,
		cancelCause:this.memo
	};
	let url="discountrecord/cancel/unconfirm";
	if (this.item.order_type=='DISCOUNTRECORDSP')
		url="discountrecordsp/cancel/unconfirm";
    this.apiSev.api("newpost", url , (res) => {

      if(res.data.response == 'success') {
        this.apiSev.itip("已成功申请取消订单");
		this.navCtrl.setRoot(OrderPageB);
      }
    }, (error) => {},6100,data);
    this.maskDiv = false;
  }


  ionViewDidEnter(){

    this.Lists1 = [];
	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];
	this.storage.get('ORGID').then((res)=>{
		this.ORGID=res;


		let date1 = new Date();//'2017-9-26 15:36:00'
		let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
		date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
		let s1 = date1.getTime(),s2 = date2.getTime();
    var afterHour = Math.floor((s2 - s1)/1000);
    var hour=Math.floor(afterHour/3600);
    var min = Math.floor((afterHour-hour*3600)/60);//计算整数分
    var afterMin = afterHour - min*60;//取得算出分后剩余的秒数
		if (afterHour<1){
			this.item.difDate = ''
		}else{
			this.item.difDate = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
		}
		this.initData();

	})

  }



  initData() {




	let data={
		discId:this.item.id,
		order_type:this.item.order_type,
	}
	let objs=[];
    this.apiSev.api("newpost", "discountrecord/details", (res) => {
      if((res.data.response == 'success')&&(res.data.data.listmap!=null)) {
        objs=res.data.data.listmap;
		this.endtime=res.data.data.endtime;
		this.orderflag = res.data.data.orderflag;
		this.overtime = res.data.data.overtime;
        for (let i=0;i<objs.length;i++){
            this.Lists1.push(objs[i]);
        }
      }
    }, (error) => {},2000,data);




	    setTimeout(() => {



			let date1 = new Date();
			let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
			date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();


			var afterHour = Math.floor((s2 - s1)/1000);

			document.getElementById("hdid").innerHTML = afterHour.toString();

			let timePromise = setInterval(()=>{

				if (document.getElementById("hdid")!=null)
				{
				  var minjj=parseInt(document.getElementById("hdid").innerHTML)-1;
				  document.getElementById("hdid").innerHTML= minjj.toString();
				  if(minjj<=0){
					clearInterval(timePromise);
					timePromise = undefined;

					document.getElementById("did").innerHTML = '' ;
				  }else{

            var hour=Math.floor(minjj/3600);
            var min = Math.floor((minjj-hour*3600)/60);//计算整数分
            var afterMin = minjj - min*60;//取得算出分后剩余的秒数
					document.getElementById("did").innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
				  }
				}
				else{
					clearInterval(timePromise);
					timePromise = undefined;
				}

			},1000,1000);
			this.ListsTime1.push(timePromise);


    }, 100);

  }



  Selected (item) {

    this.data=item;

    let s1 = new Date().getTime();
    let s2 = new Date(item.createTime.replace(/-/g, "/")).getTime();
    let intDiff =Math.floor((s1-s2)/1000);



    let y = new Date(item.createTime.replace(/-/g, "/")).getFullYear();
    let m = new Date(item.createTime.replace(/-/g, "/")).getMonth() + 1;
    let d = new Date(item.createTime.replace(/-/g, "/")).getDate();
    let time1 = y + '-' + m + '-' + d;

    let yy = new Date().getFullYear();
    let mm = new Date().getMonth() + 1;
    let dd = new Date().getDate();
    let time2 = yy + '-' + mm + '-' + dd;


    if(item.isPreference=='T'){
      if(intDiff<=this.overtime){
        this.item2=item;
        for (let i=0;i<this.Lists1.length;i++){
          document.getElementById(this.Lists1[i].memberId).style.border="0";
          document.getElementById('h'+this.Lists1[i].memberId).setAttribute("src","assets/images/order/weixuanzhong.png");
        }
        document.getElementById(item.memberId).style.border="1px solid #e84c3d";

        document.getElementById('h'+item.memberId).setAttribute("src","assets/images/order/xuanzhong.png");

        this.isSelect=item.memberId;
        this.showConfirm();
      }else {
        this.apiSev.itip("该报价已超时无效");
      }
    }else if(time1 == time2){
      this.item2=item;
      for (let i=0;i<this.Lists1.length;i++){
        document.getElementById(this.Lists1[i].memberId).style.border="0";
        document.getElementById('h'+this.Lists1[i].memberId).setAttribute("src","assets/images/order/weixuanzhong.png");
      }
      document.getElementById(item.memberId).style.border="1px solid #e84c3d";

      document.getElementById('h'+item.memberId).setAttribute("src","assets/images/order/xuanzhong.png");

      this.isSelect=item.memberId;
      this.showConfirm();
    }else {
      this.apiSev.itip("该报价已超时无效");
    }

  }

  getDurg (test) {
    //artime.push(timePromise);
  }

  close (e) {
  	this.viewCtrl.dismiss();
    e.preventDefault();
  }

}
