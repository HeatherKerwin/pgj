import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,AlertController,ModalController  } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderShoukuanPageB } from '../order/orderShoukuanB';
import { OrderPinjiaPageB } from '../order/orderPinjiaB';
import { Iframe } from '../order/iframe';
import { PhotoViewer } from '@ionic-native/photo-viewer';
@Component({
  selector: 'page-orderEndB',
  templateUrl: 'orderEndB.html'
})
export class OrderEndPageB {
  public user:any = {};
  public orderflag = '';
  public isCodeDisabled = true;

  public item:any = {};
  public item2:any = {};
  public maskDiv=false; //弹出层
  public maskDiv1=false;
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
  public memberId = "";
  public QXcash:boolean= false;
  public isCash:boolean;
  public outputemail='';
  constructor(public storage: Storage,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public alertCtrl: AlertController,public params: NavParams,public apiSev: apiService, public modalCtrl:ModalController) {
      this.item=this.params.get('item');
	this.storage.get('userInfo').then((res)=>{

		this.memberId=res.id;

	})
  }

  Chage(){
	if (this.info=="4")
	  this.isMemo=true;
    else
	  this.isMemo=false;
  }

  showConfirm1(){
    this.maskDiv1 = true;
    this.QXcash = true;
    this.isCash = false;
  }
  showCancel(){
	this.isConfirm=false;
	this.isCancel=true;
    this.maskDiv = true;
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
  	let data={
		id:this.item.id,
		orderflag:this.orderflag ,
		selectDistId:this.item2.distId
	}
	let objs=[];
	let url="discountrecord/update/select";
	if (this.item.order_type=='DISCOUNTRECORDSP')
		url="discountrecordsp/update/select";

    this.apiSev.api("newpost", url, (res) => {

      if(res.data.response == 'success') {
        this.apiSev.itip("已成功选定报价");
      }
    }, (error) => {},6100,data);
    this.maskDiv = false;
  }

  ionViewDidEnter(){

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
			this.item.difDate = '无'
		}else{
			this.item.difDate = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
		}
		this.initData();

	})

  }


    backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
	  this.maskDiv1 = false;
      this.QXcash = false;
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
    if(this.maskDiv){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }

   CancelMask1(){
     let data: any={

     };
     if (this.item.cib_cancel != null && this.item.cib_cancel != '') {
       if (this.item.cib_cancel.id != null && this.item.cib_cancel.id != '') {
         data.id = this.item.cib_cancel.id;
       }else {
         data.memberId = this.memberId;
         data.orderId = this.item.id;
         data.orderType = this.item.order_type;
         data.type = 0;
         data.cancel = this.info;
         data.cancelCause = this.memo
       }
     } else {
       data.memberId = this.memberId;
       data.orderId = this.item.id;
       data.orderType = this.item.order_type;
       data.type = 0;
       data.cancel = this.info;
       data.cancelCause = this.memo
     }
       let objs=[];

       this.apiSev.api("newpost", "cibcancel/save", (res) => {

         if(res.data.response == 'success') {
           this.apiSev.itip("已成功申请取消订单");
           this.navCtrl.pop();
         }
       }, (error) => {},6100,data);
     this.maskDiv = false;
  }

     closeMask1(){
	this.maskDiv1 = false;
  }


  initData() {




	let data={
		discId:this.item.id,
		order_type:this.item.order_type
	}
    this.apiSev.api("newpost", "order/isselect/details", (res) => {

      if((res.data.response == 'success')&&(res.data.data.listmap!=null)) {
        this.Lists1=res.data.data.listmap;
		this.endtime=res.data.data.endtime;
		this.orderflag = res.data.data.orderflag;

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

					document.getElementById("did").innerHTML = '无' ;
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

  Save(){



	let confirm = this.alertCtrl.create({
      title: '确认收款',
      message: '请先确认出票金额已经实际到账，然后再点确认收款',
      buttons: [
        {
          text: '取消',
          handler: () => {
          }
        },
        {
          text: '确认',
          handler: () => {

		  	let data={
				id:this.item.id,
				orderflag:this.orderflag
			}

			let url= "" ;
			if (this.item.order_type=="DISCOUNTRECORD"){
				url= "discountrecord/update/confirmaccounttime" ;
			}
			else{
				url= "discountrecordsp/update/confirmaccounttime" ;
			}

			this.apiSev.api("newpost", url , (res) => {
				  if(res.data.response == 'success') {
					this.apiSev.itip("确认收款成功");
					this.item.company=this.Lists1[0].company;
					this.navCtrl.push(OrderPinjiaPageB,{item:this.item} );
				  }
			}, (error) => {},6100,data);

          }
        }
      ]
    });
    confirm.present();



  }

  shoukuan(){
	    let data = {
			memberId:this.memberId,
			type:0
		}
		this.apiSev.api("newpost", "cib/corp/query", (res) => {
		  this.navCtrl.push(Iframe,{url:res.data.data.login_url});
		}, (error) => {},2000,data);

  }

  shoukuan1(){
    let profileModal = this.modalCtrl.create(OrderShoukuanPageB);
    profileModal.present();
  }

  Selected (item) {

    if (document.getElementById("did").innerHTML == '无')
	    return;
    this.item2=item;
	for (let i=0;i<this.Lists1.length;i++){
		document.getElementById(this.Lists1[i].memberId).style.border="0";
		document.getElementById('h'+this.Lists1[i].memberId).setAttribute("src","assets/images/order/weixuanzhong.png");
	}
    document.getElementById(item.memberId).style.border="1px solid #e84c3d";

    document.getElementById('h'+item.memberId).setAttribute("src","assets/images/order/xuanzhong.png");

	this.isSelect=item.memberId;
	this.showConfirm();
  }

  getDurg (test) {
    //artime.push(timePromise);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  outputHT(){
    this.isCash = true;
    this.maskDiv1 = true;
    this.QXcash = false;
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
      no:this.item.ordernumber,
      email:this.outputemail,
    };
    this.apiSev.api("newpost", "/cib/econtract/email", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.maskDiv1 = false;
        this.isCash = false;
        this.apiSev.itip(data.msg);
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}
